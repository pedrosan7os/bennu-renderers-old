package pt.ist.bennu.renderers.core.layouts;

import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.components.HtmlList;
import pt.ist.bennu.renderers.core.components.HtmlListItem;

public abstract class ListLayout extends FlowLayout {

	public ListLayout() {
		super();
	}

	@Override
	protected HtmlComponent getContainer() {
		return new HtmlList();
	}

	@Override
	protected void addComponent(HtmlComponent container, HtmlComponent component) {
		HtmlList list = (HtmlList) container;

		HtmlListItem item = list.createItem();
		item.setBody(component);
	}
}
