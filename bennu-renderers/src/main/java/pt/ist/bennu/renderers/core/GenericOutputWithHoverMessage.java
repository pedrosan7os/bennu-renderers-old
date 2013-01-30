package pt.ist.bennu.renderers.core;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.annotation.RendererProperty;
import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.components.HtmlText;
import pt.ist.bennu.renderers.core.contexts.PresentationContext;
import pt.ist.bennu.renderers.core.layouts.Layout;
import pt.ist.bennu.renderers.core.utils.RenderKit;
import pt.ist.bennu.renderers.core.utils.RenderUtils;

@Renderer(layout = "output-with-hover", type = Object.class, properties = {
		@RendererProperty(name = "closedClasses", value = "tooltip tooltipClosed"),
		@RendererProperty(name = "openClasses", value = "tooltip tooltipOpen"),
		@RendererProperty(name = "textClasses", value = "tooltipText") })
public class GenericOutputWithHoverMessage extends AbstractToolTipRenderer {

	private String format;

	private String hoverMessage;

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getHoverMessage() {
		return hoverMessage;
	}

	public void setHoverMessage(String hover) {
		this.hoverMessage = hover;
	}

	@Override
	protected Layout getLayout(Object object, Class type) {

		return new ToolTipLayout() {
			@Override
			public HtmlComponent createComponent(Object object, Class type) {
				PresentationContext context = getContext();

				context.setLayout(getSubLayout());
				context.setProperties(getProperties());

				HtmlComponent component = RenderKit.getInstance().render(context, object, type);
				String hoverMessage = null;

				if (getFormat() != null) {
					hoverMessage = RenderUtils.getFormattedProperties(getFormat(), getTargetObject(object));
				} else {
					if (isKey()) {
						hoverMessage = RenderUtils.getResourceString(getBundle(), getHoverMessage());
					} else {
						hoverMessage = getHoverMessage();
					}
				}

				return wrapUpCompletion(component, new HtmlText(hoverMessage, isEscape()));
			}
		};
	}

}
