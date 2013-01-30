package pt.ist.bennu.renderers.core.components.state;

import pt.ist.bennu.renderers.core.model.MetaSlot;

public class ConversionMessage extends SlotMessage {

	public ConversionMessage(MetaSlot slot, String message) {
		super(Message.Type.CONVERSION, slot, message);
	}

}
