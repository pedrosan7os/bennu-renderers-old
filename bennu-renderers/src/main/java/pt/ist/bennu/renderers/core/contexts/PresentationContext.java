package pt.ist.bennu.renderers.core.contexts;

import java.util.Properties;

import pt.ist.bennu.renderers.core.components.state.IViewState;
import pt.ist.bennu.renderers.core.model.MetaObject;
import pt.ist.bennu.renderers.core.schemas.Schema;
import pt.ist.bennu.renderers.core.utils.RenderMode;

public abstract class PresentationContext {

	private PresentationContext parentContext;

	private RenderMode renderMode;

	private Schema schema;

	private String layout;

	private Properties properties;

	private IViewState viewState;

	private MetaObject metaObject;

	public PresentationContext() {
		super();

		this.parentContext = null;
	}

	protected PresentationContext(PresentationContext parent) {
		super();

		this.parentContext = parent;
	}

	public RenderMode getRenderMode() {
		return renderMode;
	}

	public void setRenderMode(RenderMode renderMode) {
		this.renderMode = renderMode;
	}

	public PresentationContext getParentContext() {
		return parentContext;
	}

	public void setParentContext(PresentationContext parentContext) {
		this.parentContext = parentContext;
	}

	public Schema getSchema() {
		return this.schema;
	}

	public void setSchema(Schema schema) {
		this.schema = schema;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public boolean hasViewState() {
		return getViewState() != null;
	}

	public IViewState getViewState() {
		if (viewState != null) {
			return viewState;
		}

		if (this.parentContext != null) {
			return this.parentContext.getViewState();
		}

		return null;
	}

	public void setViewState(IViewState viewState) {
		this.viewState = viewState;

		if (viewState != null) {
			setMetaObject(viewState.getMetaObject());
		}
	}

	public MetaObject getMetaObject() {
		return this.metaObject;
	}

	public void setMetaObject(MetaObject metaObject) {
		this.metaObject = metaObject;
	}

	public abstract PresentationContext createSubContext(MetaObject metaObject);
}
