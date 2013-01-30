package pt.ist.bennu.renderers.core.components.state;

import pt.ist.bennu.renderers.core.model.MetaSlot;

public class SlotMessage extends Message {

	private MetaSlot slot;

	protected SlotMessage(Type type, MetaSlot slot, String message) {
		super(type, message);

		setSlot(slot);
	}

	public MetaSlot getSlot() {
		return this.slot;
	}

	protected void setSlot(MetaSlot slot) {
		this.slot = slot;
	}

}
