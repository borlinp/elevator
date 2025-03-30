package org.borlin.elevator.humaninterface;

import org.borlin.elevator.movement.MovementDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestUpTransportButtonTest {

    @Test
    void getDirection() {
        RequestUpTransportButton button = new RequestUpTransportButton();
        MovementDirection direction = button.getDirection();
        assertEquals(MovementDirection.UP, direction);
    }
}