package pt.ist.bennu.renderers.core;

import pt.ist.bennu.renderers.core.contexts.OutputContext;

/**
 * The base renderer for every output renderer.
 * 
 * @author cfgi
 */
public abstract class OutputRenderer extends AbstractRenderer {

	public OutputContext getOutputContext() {
		return (OutputContext) getContext();
	}
}
