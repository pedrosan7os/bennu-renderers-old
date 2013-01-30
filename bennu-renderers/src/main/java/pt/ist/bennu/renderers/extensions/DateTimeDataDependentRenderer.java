/*
 * Author : Goncalo Luiz Creation Date: Jul 26, 2006,10:56:35 AM
 */
package pt.ist.bennu.renderers.extensions;

import org.joda.time.DateTime;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.annotation.RendererProperty;
import pt.ist.bennu.renderers.core.FormatRenderer;
import pt.ist.bennu.renderers.core.layouts.Layout;

/**
 * @author <a href="mailto:goncalo@ist.utl.pt">Goncalo Luiz</a><br>
 * <br>
 *         Created on Jul 26, 2006,10:56:35 AM
 * 
 */
@Renderer(type = DateTime.class, layout = "dataDependent", properties = {
		@RendererProperty(
				name = "formatWithTime",
				value = "${dayOfMonth,02d}-${monthOfYear,02d}-${year} ${hourOfDay,02d}:${minuteOfHour,02d}"),
		@RendererProperty(name = "formatWithoutTime", value = "${dayOfMonth,02d}-${monthOfYear,02d}-${year}") })
public class DateTimeDataDependentRenderer extends FormatRenderer {

	private String formatWithTime;
	private String formatWithoutTime;

	public String getFormatWithoutTime() {
		return formatWithoutTime;
	}

	public void setFormatWithoutTime(String formatWithoutTime) {
		this.formatWithoutTime = formatWithoutTime;
	}

	public String getFormatWithTime() {
		return formatWithTime;
	}

	public void setFormatWithTime(String formatWithTime) {
		this.formatWithTime = formatWithTime;
	}

	@Override
	protected Layout getLayout(Object object, Class type) {
		if (object == null) {
			return super.getLayout(object, type);
		}

		DateTime dateTime = (DateTime) object;
		if (dateTime.getHourOfDay() == 0 && dateTime.getMinuteOfHour() == 0) {
			setFormat(getFormatWithoutTime());
		} else {
			setFormat(getFormatWithTime());
		}

		return super.getLayout(object, type);
	}

}
