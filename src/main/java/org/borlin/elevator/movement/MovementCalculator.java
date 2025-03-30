package org.borlin.elevator.movement;

import org.borlin.elevator.Floor;

public interface MovementCalculator {
    public Mover calculateNextMove(Floor currentFloor);
}
