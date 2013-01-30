package pt.ist.bennu.renderers.core.components;

import pt.ist.bennu.renderers.core.model.MetaSlotKey;

public interface SlotChanger {
	public void setTargetSlot(MetaSlotKey key);

	public boolean hasTargetSlot();

	public MetaSlotKey getTargetSlot();
}
