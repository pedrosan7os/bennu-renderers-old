package pt.ist.bennu.renderers.core.components.converters;

public abstract class BiDirectionalConverter extends Converter {

	@Override
	public abstract Object convert(Class type, Object value);

	public abstract String deserialize(Object object);
}
