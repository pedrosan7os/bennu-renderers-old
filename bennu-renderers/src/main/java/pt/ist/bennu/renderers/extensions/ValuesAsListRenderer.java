package pt.ist.bennu.renderers.extensions;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.core.ValuesRenderer;
import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.components.HtmlList;
import pt.ist.bennu.renderers.core.components.HtmlListItem;
import pt.ist.bennu.renderers.core.layouts.Layout;
import pt.ist.bennu.renderers.core.model.MetaObject;

@Renderer(type = Object.class, layout = "values-as-list")
public class ValuesAsListRenderer extends ValuesRenderer {

	private String itemClasses;

	public String getItemClasses() {
		return itemClasses;
	}

	public void setItemClasses(String itemClasses) {
		this.itemClasses = itemClasses;
	}

	@Override
	protected Layout getLayout(Object object, Class type) {
		return new ValuesAsListLayout(getContext().getMetaObject());
	}

	public class ValuesAsListLayout extends ValuesLayout {

		String[] classes;
		HtmlList list;

		public ValuesAsListLayout(MetaObject object) {
			super(object);
			classes = getItemClasses() != null ? getItemClasses().split(",") : null;
		}

		public String getClasses(int index) {
			return classes != null && index < classes.length ? classes[index].trim() : null;
		}

		@Override
		public HtmlComponent createComponent(Object object, Class type) {
			list = new HtmlList();

			while (hasMoreComponents()) {
				HtmlListItem item = list.createItem();
				item.addChild(getNextComponent());
			}
			return list;
		}

		@Override
		public void applyStyle(HtmlComponent component) {
			int index = 0;
			for (HtmlComponent item : list.getChildren()) {
				item.setClasses(getClasses(index++));
			}
		}
	}
}
