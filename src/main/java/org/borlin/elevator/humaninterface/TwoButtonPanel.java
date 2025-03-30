package org.borlin.elevator.humaninterface;

import java.util.Set;

public class TwoButtonPanel extends ButtonPanel {

    public TwoButtonPanel(Set<Button> buttons, Set<Control> controls) throws Exception {
        super(buttons, controls);
        if (buttons.size() > 2) {
            throw new Exception("Too many buttons");
        }
    }

}
