package pt.ist.bennu.renderers.core.contexts;

import pt.ist.bennu.renderers.core.model.MetaObject;
import pt.ist.bennu.renderers.core.utils.RenderMode;

public class OutputContext extends PresentationContext {

	public OutputContext() {
		super();

		setRenderMode(RenderMode.OUTPUT);
	}

	protected OutputContext(OutputContext parent) {
		super(parent);
	}

	@Override
	public OutputContext createSubContext(MetaObject metaObject) {
		OutputContext context = new OutputContext(this);

		// context.setLayout(getLayout());
		context.setMetaObject(metaObject);
		// context.setProperties(metaObject.getProperties());

		context.setRenderMode(getRenderMode());

		return context;
	}

}
