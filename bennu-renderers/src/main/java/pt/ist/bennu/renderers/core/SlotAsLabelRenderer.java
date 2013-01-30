package pt.ist.bennu.renderers.core;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.components.HtmlText;
import pt.ist.bennu.renderers.core.layouts.Layout;
import pt.ist.bennu.renderers.core.utils.RenderUtils;

@Renderer(type = String.class, layout = "slot-as-label")
public class SlotAsLabelRenderer extends OutputRenderer {

	private String bundle;

	public String getBundle() {
		return this.bundle;
	}

	public void setBundle(String bundle) {
		this.bundle = bundle;
	}

	@Override
	protected Layout getLayout(Object object, Class type) {
		return new Layout() {

			@Override
			public HtmlComponent createComponent(Object object, Class type) {
				final String key = (String) object;

				final String label = RenderUtils.getResourceString(getBundle(), key);
				return new HtmlText(label);
			}

		};
	}

}
