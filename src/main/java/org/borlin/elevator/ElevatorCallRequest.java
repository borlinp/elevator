package org.borlin.elevator;

import org.borlin.elevator.humaninterface.RequestTransportButton;

public interface ElevatorCallRequest {
    public void call(RequestTransportButton button, Floor floor);
    public Floor getFloor();
    public RequestTransportButton getButton();
}
