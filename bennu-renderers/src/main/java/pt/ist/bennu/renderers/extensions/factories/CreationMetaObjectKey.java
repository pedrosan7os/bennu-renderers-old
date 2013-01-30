package pt.ist.bennu.renderers.extensions.factories;

import pt.ist.bennu.renderers.core.model.MetaObjectKey;

public class CreationMetaObjectKey extends MetaObjectKey {
	public CreationMetaObjectKey(Class type) {
		super(type, 0);
	}

	@Override
	public String toString() {
		return getType().getName();
	}
}
