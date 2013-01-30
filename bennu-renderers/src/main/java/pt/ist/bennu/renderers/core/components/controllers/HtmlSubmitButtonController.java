package pt.ist.bennu.renderers.core.components.controllers;

import pt.ist.bennu.renderers.core.components.HtmlSubmitButton;
import pt.ist.bennu.renderers.core.components.state.IViewState;

public abstract class HtmlSubmitButtonController extends HtmlController {

	public HtmlSubmitButtonController() {
		super();
	}

	@Override
	public void execute(IViewState viewState) {
		HtmlSubmitButton button = (HtmlSubmitButton) getControlledComponent();

		if (button.isPressed()) {
			viewState.setSkipUpdate(true);

			buttonPressed(viewState, button);
		}
	}

	protected abstract void buttonPressed(IViewState viewState, HtmlSubmitButton button);
}
