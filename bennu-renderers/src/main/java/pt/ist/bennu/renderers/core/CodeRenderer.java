package pt.ist.bennu.renderers.core;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.components.HtmlPreformattedText;
import pt.ist.bennu.renderers.core.components.HtmlText;
import pt.ist.bennu.renderers.core.layouts.Layout;

@Renderer(layout = "code-block", type = String.class)
public class CodeRenderer extends OutputRenderer {

	@Override
	protected Layout getLayout(Object object, Class type) {
		return new Layout() {

			@Override
			public HtmlComponent createComponent(Object object, Class type) {
				String text = (String) object;

				if (text == null) {
					return new HtmlText();
				}

				HtmlPreformattedText container = new HtmlPreformattedText();
				container.setIndented(false);

				container.addChild(new HtmlText(text, true, true));

				return container;
			}

		};
	}

}
