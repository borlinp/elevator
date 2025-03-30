package org.borlin.elevator.movement;

import org.borlin.elevator.Floor;

public interface Mover {
    Floor move(Floor currentFloor) throws ArrayIndexOutOfBoundsException;
}
