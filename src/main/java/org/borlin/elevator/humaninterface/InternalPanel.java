package org.borlin.elevator.humaninterface;

import org.borlin.elevator.Floor;

import java.util.Map;

public class InternalPanel extends ButtonPanel {

    private final Map<Button, Floor> buttonsWithFloor;

    public InternalPanel(Map<Button, Floor> buttonsWithFloor ) {
        super(buttonsWithFloor.keySet(), null);
        this.buttonsWithFloor = buttonsWithFloor;
    }

    public Map<Button, Floor> getButtonsInOrder() {
        return this.buttonsWithFloor;
    }
}
