package pt.ist.bennu.renderers.extensions.factories;

import pt.ist.bennu.renderers.core.model.DefaultValues;
import pt.ist.fenixframework.DomainObject;

public class FenixDefaultValues extends pt.ist.bennu.renderers.core.model.DefaultValues {

	public static DefaultValues getInstance() {
		if (DefaultValues.instance == null) {
			DefaultValues.instance = new FenixDefaultValues();
		}

		return DefaultValues.instance;
	}

	public DomainObject createValue(DomainObject o, Class type, String defaultValue) {
		return null;
	}
}
