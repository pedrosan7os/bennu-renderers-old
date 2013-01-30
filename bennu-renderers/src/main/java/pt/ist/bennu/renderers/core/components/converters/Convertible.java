package pt.ist.bennu.renderers.core.components.converters;

import pt.ist.bennu.renderers.core.model.MetaSlot;

public interface Convertible {
	public boolean hasConverter();

	public Converter getConverter();

	public Object getConvertedValue(MetaSlot slot);
}
