package pt.ist.bennu.renderers.extensions.factories;

import pt.ist.bennu.renderers.core.model.DefaultValues;
import pt.ist.bennu.renderers.core.model.MetaObject;

public class MetaSlotWithDefault extends pt.ist.bennu.renderers.core.model.MetaSlotWithDefault {

	public MetaSlotWithDefault(MetaObject metaObject, String name) {
		super(metaObject, name);
	}

	@Override
	protected Object createDefault(Class type, String defaultValue) {
		DefaultValues instance = FenixDefaultValues.getInstance();
		return instance.createValue(type, defaultValue);
	}

}
