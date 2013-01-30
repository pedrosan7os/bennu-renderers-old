package pt.ist.bennu.renderers.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.core.components.HtmlBlockContainer;
import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.components.HtmlContainer;
import pt.ist.bennu.renderers.core.components.HtmlText;
import pt.ist.bennu.renderers.core.contexts.InputContext;
import pt.ist.bennu.renderers.core.layouts.Layout;
import pt.ist.bennu.renderers.core.utils.RenderKit;
import pt.ist.bennu.renderers.core.utils.RenderMode;
import pt.ist.bennu.renderers.core.utils.RenderUtils;

/**
 * This renderer allows a comment to be added to any layout.
 * 
 * 
 * @author pcma
 */

@Renderer(mode = RenderMode.INPUT, layout = "input-with-comment", type = Object.class)
public class GenericInputWithComment extends InputRenderer {

	private static final String LOCATION_TOP = "top";
	private static final String LOCATION_BOTTOM = "bottom";
	private static final String LOCATION_RIGHT = "right";

	private String bundle;
	private String comment;
	private String commentClasses;
	private String commentLocation;
	private String subLayout;
	private Map<String, String> properties = new HashMap<>();

	public GenericInputWithComment() {
		super();
		this.commentLocation = LOCATION_BOTTOM;
	}

	private Map<String, String> getPropertiesMap() {
		return properties;
	}

	public void setSubProperty(String property, String value) {
		properties.put(property, value);
	}

	public String getSubProperty(String property) {
		return properties.get(property);
	}

	public String getBundle() {
		return bundle;
	}

	public void setBundle(String bundle) {
		this.bundle = bundle;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCommentClasses() {
		return commentClasses;
	}

	public void setCommentClasses(String commentClasses) {
		this.commentClasses = commentClasses;
	}

	public String getSubLayout() {
		return subLayout;
	}

	public void setSubLayout(String subLayout) {
		this.subLayout = subLayout;
	}

	public String getCommentLocation() {
		return this.commentLocation;
	}

	public void setCommentLocation(String commentLocation) {
		this.commentLocation = commentLocation;

		if (this.commentLocation != null) {
			this.commentLocation = this.commentLocation.toLowerCase();
		}
	}

	@Override
	protected Layout getLayout(Object object, Class type) {
		return new Layout() {

			private Properties getProperties() {
				Properties properties = new Properties();
				Map<String, String> map = getPropertiesMap();
				for (String property : map.keySet()) {
					properties.put(property, map.get(property));
				}
				return properties;
			}

			@Override
			public HtmlComponent createComponent(Object object, Class type) {
				InputContext context = getInputContext();

				context.setLayout(getSubLayout());
				context.setProperties(getProperties());

				HtmlComponent component = RenderKit.getInstance().render(context, object, type);

				HtmlText text =
						new HtmlText(
								getBundle() != null ? RenderUtils.getResourceString(getBundle(), getComment()) : getComment(),
								getBundle() == null);

				text.setClasses(getCommentClasses());

				final HtmlContainer container = new HtmlBlockContainer();
				final String commentLocation = String.valueOf(getCommentLocation());

				if (commentLocation.contains(LOCATION_TOP)) {
					container.addChild(text);
					container.addChild(new HtmlText("<br/>", false));
					container.addChild(component);

				} else if (commentLocation.contains(LOCATION_RIGHT)) {
					container.addChild(component);
					container.addChild(text);

				} else {
					container.addChild(component);
					container.addChild(new HtmlText("<br/>", false));
					container.addChild(text);
				}

				return container;

			}

		};

	}

}
