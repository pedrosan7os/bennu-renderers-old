package pt.ist.bennu.renderers.core.contexts;

import pt.ist.bennu.core.domain.User;
import pt.ist.bennu.renderers.core.components.HtmlForm;
import pt.ist.bennu.renderers.core.components.state.IViewState;
import pt.ist.bennu.renderers.core.components.state.ViewStateWrapper;
import pt.ist.bennu.renderers.core.model.MetaObject;
import pt.ist.bennu.renderers.core.model.MetaObjectKey;
import pt.ist.bennu.renderers.core.model.MetaSlot;
import pt.ist.bennu.renderers.core.utils.RenderMode;

public class InputContext extends PresentationContext {

	private HtmlForm form;

	public InputContext() {
		super();

		setRenderMode(RenderMode.INPUT);
	}

	protected InputContext(InputContext parent) {
		super(parent);
	}

	@Override
	public IViewState getViewState() {
		IViewState viewState = super.getViewState();

		if (getMetaObject() instanceof MetaSlot) {
			MetaObjectKey key = getMetaObject().getKey();

			if (key != null) {
				String prefix = key.toString();

				if (viewState instanceof ViewStateWrapper) {
					ViewStateWrapper wrapper = (ViewStateWrapper) viewState;

					if (prefix.equals(wrapper.getPrefix())) {
						return wrapper;
					}
					return new ViewStateWrapper(viewState, prefix);
				}
				return new ViewStateWrapper(viewState, prefix);
			}
			return viewState;
		}
		return viewState;
	}

	protected User getUser() {
		return getViewState().getUser();
	}

	public HtmlForm getForm() {
		if (getParentContext() == null || !(getParentContext() instanceof InputContext)) {
			if (this.form == null) {
				this.form = new HtmlForm();
			}

			return this.form;
		}
		return ((InputContext) getParentContext()).getForm();
	}

	@Override
	public InputContext createSubContext(MetaObject metaObject) {
		InputContext context = new InputContext(this);

		// TODO: check this and compare with the version in OutputContext
		context.setLayout(getLayout());
		context.setMetaObject(metaObject);
		context.setProperties(metaObject.getProperties());

		context.setRenderMode(getRenderMode());

		return context;
	}
}
