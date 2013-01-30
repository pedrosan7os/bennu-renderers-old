package pt.ist.bennu.renderers.core.components.controllers;

import pt.ist.bennu.renderers.core.components.HtmlActionLink;
import pt.ist.bennu.renderers.core.components.state.IViewState;

public abstract class HtmlActionLinkController extends HtmlController {

	@Override
	public void execute(IViewState viewState) {
		HtmlActionLink link = (HtmlActionLink) getControlledComponent();

		if (link.isActivated()) {
			viewState.setSkipUpdate(isToSkipUpdate());

			linkPressed(viewState, link);
		}
	}

	protected boolean isToSkipUpdate() {
		return true;
	}

	public abstract void linkPressed(IViewState viewState, HtmlActionLink link);
}
