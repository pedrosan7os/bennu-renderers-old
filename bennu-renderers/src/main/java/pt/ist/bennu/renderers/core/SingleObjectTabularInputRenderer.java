package pt.ist.bennu.renderers.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.model.MetaObjectCollection;
import pt.ist.bennu.renderers.core.model.MetaObjectFactory;
import pt.ist.bennu.renderers.core.utils.RenderMode;

/**
 * This renderer can be used to edit a single object in a tabular view as if you were editing a collection with only the given
 * object.
 * <p>
 * If you edit a collection with this renderer then it will behave exactly as the
 * {@link pt.ist.bennu.renderers.core.TabularInputRenderer}.
 * 
 * @author cfgi
 */
@Renderer(mode = RenderMode.INPUT, type = Object.class, layout = "tabular-row")
public class SingleObjectTabularInputRenderer extends TabularInputRenderer {

	@Override
	public HtmlComponent render(Object object, Class type) {
		if (object instanceof Collection) {
			return super.render(object, type);
		} else {
			List list = new ArrayList();
			list.add(object);

			MetaObjectCollection multipleMetaObject = MetaObjectFactory.createObjectCollection();
			multipleMetaObject.add(getInputContext().getMetaObject());

			getInputContext().setMetaObject(multipleMetaObject);

			return super.render(list, list.getClass());
		}
	}
}
