package org.borlin.elevator.humaninterface;

import org.borlin.elevator.movement.MovementDirection;

public class RequestUpTransportButton extends RequestTransportButton{

    @Override
    public MovementDirection getDirection() {
        return MovementDirection.UP;
    }
}
