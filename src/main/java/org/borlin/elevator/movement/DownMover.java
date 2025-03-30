package org.borlin.elevator.movement;

import org.borlin.elevator.Floor;

import java.util.List;

public class DownMover implements Mover {

    List<Floor> floors;
    public DownMover(List<Floor> floors) {
        this.floors = floors;
    }

    @Override
    public Floor move(Floor currentFloor) throws ArrayIndexOutOfBoundsException {
        int currentFloorIndex = this.floors.indexOf(currentFloor);
        if (currentFloorIndex <= 0) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return floors.get(currentFloorIndex - 1);
    }
}
