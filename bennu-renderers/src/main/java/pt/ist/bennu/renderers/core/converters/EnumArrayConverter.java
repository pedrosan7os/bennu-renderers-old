/**
 * 
 */
package pt.ist.bennu.renderers.core.converters;

import java.util.ArrayList;
import java.util.List;

import pt.ist.bennu.renderers.core.components.converters.Converter;

public class EnumArrayConverter extends Converter {

	private EnumConverter concreteConverter;

	public EnumArrayConverter() {
		this.concreteConverter = new EnumConverter();
	}

	public EnumArrayConverter(Class enumClass) {
		this.concreteConverter = new EnumConverter(enumClass);
	}

	@Override
	public Object convert(Class type, Object value) {
		List<Object> enumValues = new ArrayList<>();

		String[] values = (String[]) value;
		for (String enumString : values) {
			enumValues.add(this.concreteConverter.convert(type, enumString));
		}

		return enumValues;
	}
}