package pt.ist.bennu.renderers.core.components;

import javax.servlet.jsp.PageContext;

import pt.ist.bennu.renderers.core.components.tags.HtmlTag;

public class HtmlBlockContainer extends HtmlContainer {

	@Override
	public HtmlTag getOwnTag(PageContext context) {
		HtmlTag tag = super.getOwnTag(context);

		tag.setName("div");

		return tag;
	}
}
