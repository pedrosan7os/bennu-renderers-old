package pt.ist.bennu.renderers.extensions.factories;

import java.util.List;
import java.util.concurrent.Callable;

import pt.ist.bennu.renderers.core.model.MetaObject;
import pt.ist.bennu.renderers.core.model.MetaObjectCollection;
import pt.ist.bennu.service.ServiceManager;

public class DomainMetaObjectCollection extends MetaObjectCollection {

	@Override
	public void commit() {
		final List<MetaObject> metaObjects = getAllMetaObjects();
		Callable<Object> service = new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				for (MetaObject object : metaObjects) {
					object.commit();
				}
				return null;
			}
		};
		try {
			ServiceManager.execute(service);
		} catch (Exception e) {
			throw new Error(e);
		}
	}
}
