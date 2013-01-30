package pt.ist.bennu.renderers.core.components.controllers;

import java.io.Serializable;

import pt.ist.bennu.renderers.core.components.HtmlComponent;
import pt.ist.bennu.renderers.core.components.state.IViewState;

public abstract class HtmlController implements Serializable {
	private HtmlComponent controlledComponent;

	public HtmlComponent getControlledComponent() {
		return controlledComponent;
	}

	public void setControlledComponent(HtmlComponent controlledComponent) {
		this.controlledComponent = controlledComponent;
	}

	public abstract void execute(IViewState viewState);
}
