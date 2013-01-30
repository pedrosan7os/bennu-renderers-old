package pt.ist.bennu.renderers.extensions;

import pt.ist.bennu.core.util.MultiLanguageString;
import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.annotation.RendererProperty;
import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.components.HtmlText;
import pt.ist.bennu.renderers.core.layouts.Layout;

/**
 * This renderer has the same behaviour as the
 * {@link net.sourceforge.fenixedu.presentationTier.renderers.MultiLanguageStringRenderer} but the context is limited to a certain
 * number of characters.
 * 
 * @author cfgi
 */
@Renderer(layout = "short", type = MultiLanguageString.class, properties = { @RendererProperty(name = "length", value = "40"),
		@RendererProperty(name = "languageShown", value = "false") })
public class ShortMultiLanguageStringRenderer extends MultiLanguageStringRenderer {

	private Integer length;
	private boolean tooltipShown;

	public ShortMultiLanguageStringRenderer() {
		super();

		this.tooltipShown = true;
	}

	public boolean isTooltipShown() {
		return tooltipShown;
	}

	/**
	 * Chooses if a tooltip is added to the text when text is bigger than the value given in {@link #setLength(Integer) length}.
	 * 
	 * @property
	 */
	public void setTooltipShown(boolean tooltipShown) {
		this.tooltipShown = tooltipShown;
	}

	@Override
	public Integer getLength() {
		return this.length;
	}

	/**
	 * Choose the amount of characters displayed. If the original text has more than the value given the elipses are added.
	 * 
	 * @property
	 */
	@Override
	public void setLength(Integer length) {
		this.length = length;
	}

	@Override
	protected String getRenderedText(MultiLanguageString mlString) {
		String content = super.getRenderedText(mlString);

		if (content != null && getLength() != null) {
			if (content.length() > getLength()) {
				return content.substring(0, getLength()) + "...";
			}
		}

		return content;
	}

	@Override
	protected HtmlComponent renderComponent(Layout layout, Object object, Class type) {
		HtmlComponent component = super.renderComponent(layout, object, type);

		MultiLanguageString mlString = (MultiLanguageString) object;

		String previous = super.getRenderedText(mlString);
		String current = getRenderedText(mlString);

		if (isTooltipShown()) {
			if (!String.valueOf(previous).equals(String.valueOf(current))) {
				component.setTitle(HtmlText.escape(previous));
			}
		}

		return component;
	}
}
