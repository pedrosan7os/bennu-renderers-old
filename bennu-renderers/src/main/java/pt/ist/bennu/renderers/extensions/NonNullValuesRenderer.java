package pt.ist.bennu.renderers.extensions;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.annotation.RendererProperty;
import pt.ist.bennu.renderers.annotation.Renderers;
import pt.ist.bennu.renderers.core.ValuesRenderer;
import pt.ist.bennu.renderers.core.layouts.Layout;
import pt.ist.bennu.renderers.core.model.MetaObject;
import pt.ist.bennu.renderers.core.model.MetaSlot;

@Renderers({
		@Renderer(layout = "nonNullValues-commaSeparated", type = Object.class, properties = { @RendererProperty(
				name = "htmlSeparator",
				value = ", ") }), @Renderer(layout = "nonNullValues", type = Object.class) })
public class NonNullValuesRenderer extends ValuesRenderer {

	@Override
	protected Layout getLayout(final Object object, Class type) {
		return new NonNullValuesLayout(getContext().getMetaObject());
	}

	class NonNullValuesLayout extends ValuesLayout {

		public NonNullValuesLayout(MetaObject metaObject) {
			super(metaObject);
		}

		@Override
		protected boolean hasMoreComponents() {
			for (int pos = this.index; pos < this.slots.size(); pos++) {
				MetaSlot slot = slots.get(pos);
				Object object = slot.getObject();

				if (object instanceof String) {
					String string = (String) object;
					object = string.length() == 0 ? null : object;
				}

				if (object != null) {
					return true;
				}
			}

			return false;
		}

		@Override
		protected MetaSlot getNextSlot() {
			for (int pos = this.index; pos < this.slots.size(); pos++) {
				MetaSlot slot = slots.get(pos);
				Object object = slot.getObject();

				if (object instanceof String) {
					String string = (String) object;
					object = string.length() == 0 ? null : object;
				}

				if (object != null) {
					this.index = pos + 1;
					return slot;
				}
			}

			return null;
		}

	}
}
