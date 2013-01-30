package pt.ist.bennu.renderers.core;

import pt.ist.bennu.renderers.core.components.converters.Converter;

public interface DataProvider {
	public Object provide(Object source, Object currentValue);

	public Converter getConverter();
}
