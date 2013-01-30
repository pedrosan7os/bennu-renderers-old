package pt.ist.bennu.renderers.extensions;

import java.util.Collection;

import pt.ist.bennu.renderers.annotation.Renderer;
import pt.ist.bennu.renderers.core.MenuOptionListRenderer;
import pt.ist.bennu.renderers.core.components.converters.Converter;
import pt.ist.bennu.renderers.core.utils.RenderMode;

/**
 * Fenix extension to the {@link pt.ist.bennu.renderers.core.MenuOptionListRenderer}.
 * 
 * {@inheritDoc}
 * 
 * @author cfgi
 */
@Renderer(mode = RenderMode.INPUT, layout = "menu-select", type = Object.class)
public class InputMenuOptionListRenderer extends MenuOptionListRenderer {
	private String filterClass;

	public String getFilterClass() {
		return this.filterClass;
	}

	/**
	 * This property allows you to indicate a {@linkplain DataFilter data filter} that will remove values, from the collection
	 * returned by data provider, not valid in a specific context.
	 * 
	 * @property
	 */
	public void setFilterClass(String filterClass) {
		this.filterClass = filterClass;
	}

	// HACK: duplicated code, id=inputChoices.selectPossibilitiesAndConverter
	@Override
	protected Converter getConverter() {
		return super.getConverter();
	}

	// HACK: duplicated code, id=inputChoices.selectPossibilitiesAndConverter
	@Override
	protected Collection getPossibleObjects() {
		return super.getPossibleObjects();
	}

}
