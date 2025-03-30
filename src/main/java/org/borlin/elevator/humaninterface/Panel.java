package org.borlin.elevator.humaninterface;

import java.util.Set;

public interface Panel {
    public Set<Button> getButtons();
    public Set<Control> getControls();
    public void registerButtonDepressedReceivers(ButtonDepressedReceiver... receivers);
}
