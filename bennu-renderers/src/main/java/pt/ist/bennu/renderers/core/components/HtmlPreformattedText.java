package pt.ist.bennu.renderers.core.components;

import javax.servlet.jsp.PageContext;

import pt.ist.bennu.renderers.core.components.tags.HtmlTag;

public class HtmlPreformattedText extends HtmlBlockContainer {

	@Override
	public HtmlTag getOwnTag(PageContext context) {
		HtmlTag tag = super.getOwnTag(context);

		tag.setName("pre");

		return tag;
	}

}
