package org.borlin.elevator;

import org.borlin.elevator.humaninterface.Button;

public interface ElevatorGotoRequest {
    public void gotoFloor(Button button, Floor floor);
    public Floor getFloor();
    public Button getButton();
}
