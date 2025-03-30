package org.borlin.elevator.humaninterface;

import org.borlin.elevator.movement.MovementDirection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestDownTransportButtonTest {

    @Test
    void getDirection() {
        RequestDownTransportButton button = new RequestDownTransportButton();
        MovementDirection direction = button.getDirection();
        assertEquals(MovementDirection.DOWN, direction);
    }
}