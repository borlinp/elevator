package org.borlin.elevator.humaninterface;

import java.util.Set;

public class ButtonPanel implements Panel {
    Set<Button> buttons;

    public ButtonPanel(Set<Button> buttons, Set<Control> controls) {
        this.buttons = buttons;
    }

    @Override
    public Set<Button> getButtons() {
        return this.buttons;
    }

    @Override
    public Set<Control> getControls() {
        return null;
    }

    @Override
    public void registerButtonDepressedReceivers(ButtonDepressedReceiver... receivers) {
        this.getButtons().forEach(button -> {
            button.registerButtonDepressedReceivers(receivers);
        });
    }
}
