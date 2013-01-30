package pt.ist.bennu.renderers.extensions;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.YearMonthDay;

import pt.ist.bennu.renderers.core.DateInputRenderer;
import pt.ist.bennu.renderers.core.components.converters.Converter;
import pt.ist.bennu.renderers.core.converters.DateConverter;

public class JodaTimeInputRenderer extends DateInputRenderer {

	public class JodaTimeConverter extends DateConverter {

		public JodaTimeConverter(SimpleDateFormat format) {
			super(format);
		}

		@Override
		public Object convert(Class type, Object value) {
			Date date = (Date) super.convert(type, value);

			if (date == null) {
				return null;
			}

			return YearMonthDay.fromDateFields(date);
		}
	}

	@Override
	protected Converter getDateConverter(SimpleDateFormat format) {
		return new JodaTimeConverter(format);
	}
}
