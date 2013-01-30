package pt.ist.bennu.renderers.actions;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jvstm.cps.ConsistencyException;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import pt.ist.bennu.core.domain.Bennu;
import pt.ist.bennu.core.util.BundleUtil;
import pt.ist.bennu.renderers.core.components.state.IViewState;
import pt.ist.bennu.renderers.core.model.MetaObject;
import pt.ist.bennu.renderers.core.utils.RenderUtils;
import pt.ist.bennu.renderers.filters.GenericChecksumRewriter;
import pt.ist.fenixframework.DomainObject;
import pt.ist.fenixframework.pstm.AbstractDomainObject;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;
import com.google.gson.JsonObject;

public abstract class BaseAction extends DispatchAction {

	@Override
	public ActionForward execute(final ActionMapping mapping, final ActionForm form, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		request.setAttribute("myOrg", Bennu.getInstance());
		return super.execute(mapping, form, request, response);
	}

	protected <T> T getAttribute(final HttpServletRequest request, final String attributeName) {
		final T t = (T) request.getAttribute(attributeName);
		return t == null ? (T) request.getParameter(attributeName) : t;
	}

	protected <T extends DomainObject> T getDomainObject(final HttpServletRequest request, final String attributeName) {
		String oid = request.getParameter(attributeName);
		if (oid == null || oid.length() == 0) {
			oid = (String) request.getAttribute(attributeName);
		}
		return (T) AbstractDomainObject.fromExternalId(oid);
	}

	/**
	 * @use {@link #getRenderedObject(String)} or {@link #getRenderedObject(IViewState)}
	 */
	@Deprecated
	@SuppressWarnings("unchecked")
	protected <T extends Object> T getRenderedObject() {
		final IViewState viewState = RenderUtils.getViewState();
		return (T) getRenderedObject(viewState);
	}

	@SuppressWarnings("unchecked")
	protected <T extends Object> T getRenderedObject(final String id) {
		final IViewState viewState = RenderUtils.getViewState(id);
		return (T) getRenderedObject(viewState);
	}

	protected <T extends Object> T getRenderedObject(final IViewState viewState) {
		if (viewState != null) {
			MetaObject metaObject = viewState.getMetaObject();
			if (metaObject != null) {
				return (T) metaObject.getObject();
			}
		}
		return null;
	}

	/**
	 * @use {@link ByteStreams#toByteArray(InputStream)}
	 */
	@Deprecated
	protected byte[] consumeInputStream(final InputStream inputStream) throws IOException {
		return ByteStreams.toByteArray(inputStream);
	}

	protected ActionForward download(final HttpServletResponse response, final String filename, final byte[] bytes,
			final String contentType) throws IOException {
		try (OutputStream outputStream = response.getOutputStream()) {
			if (filename != null) {
				response.addHeader("Content-Disposition",
						"attachment; filename=" + URLEncoder.encode(filename, Charsets.UTF_8.name()));
			}
			response.setContentType(contentType);
			if (bytes != null) {
				response.setContentLength(bytes.length);
				outputStream.write(bytes);
			}
		}
		return null;

	}

	protected ActionForward download(final HttpServletResponse response, final String filename, InputStream stream,
			final String contentType) throws IOException {
		return download(response, filename, ByteStreams.toByteArray(stream), contentType);
	}

	protected void addLocalizedMessage(final HttpServletRequest request, final String localizedMessage) {
		final ActionMessages messages = getMessages(request);
		ActionMessage actionMessage = new ActionMessage(localizedMessage, false);
		messages.add("message", actionMessage);
		saveMessages(request, messages);
	}

	protected void addLocalizedSuccessMessage(final HttpServletRequest request, final String localizedMessage) {
		final ActionMessages messages = getMessages(request);
		ActionMessage actionMessage = new ActionMessage(localizedMessage, false);
		messages.add("messageSuccess", actionMessage);
		saveMessages(request, messages);
	}

	protected void addLocalizedWarningMessage(final HttpServletRequest request, final String localizedMessage) {
		final ActionMessages messages = getMessages(request);
		ActionMessage actionMessage = new ActionMessage(localizedMessage, false);
		messages.add("messageWarning", actionMessage);
		saveMessages(request, messages);
	}

	protected void addMessage(final HttpServletRequest request, final String key, final String... args) {
		addMessage(request, "message", key, args);
	}

	protected void addMessage(final HttpServletRequest request, final String property, final String key, final String... args) {
		final ActionMessages messages = getMessages(request);
		messages.add(property, new ActionMessage(key, args));
		saveMessages(request, messages);
	}

	protected void setAttribute(final HttpServletRequest request, final String attributeName, final Object attributeValue) {
		if (request != null) {
			request.setAttribute(attributeName, attributeValue);
		}
	}

	protected void writeJsonReply(HttpServletResponse response, JsonObject jsonObject) throws IOException {
		download(response, null, jsonObject.toString().getBytes(Charsets.UTF_8), "application/json");
	}

	protected ActionForward redirect(final HttpServletRequest request, final String url) {
		final String digest = GenericChecksumRewriter.calculateChecksum(request.getContextPath() + url);
		final char seperator = url.indexOf('?') >= 0 ? '&' : '?';
		final String urlWithChecksum = url + seperator + GenericChecksumRewriter.CHECKSUM_ATTRIBUTE_NAME + '=' + digest;
		return new ActionForward(urlWithChecksum, true);
	}

	protected void displayConsistencyException(ConsistencyException exc, HttpServletRequest request) {
		if (exc.getLocalizedMessage() != null) {
			addLocalizedMessage(request, exc.getLocalizedMessage());
		} else {
			exc.printStackTrace();
			addLocalizedMessage(request,
					BundleUtil.getString("resources.BennuResources", "error.bennu.core.consistencyexception"));
		}
	}
}
