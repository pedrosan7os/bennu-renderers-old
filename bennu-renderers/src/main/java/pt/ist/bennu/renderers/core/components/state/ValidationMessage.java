package pt.ist.bennu.renderers.core.components.state;

import pt.ist.bennu.renderers.core.model.MetaSlot;

public class ValidationMessage extends SlotMessage {

	public ValidationMessage(MetaSlot slot, String message) {
		super(Message.Type.VALIDATION, slot, message);
	}

}
