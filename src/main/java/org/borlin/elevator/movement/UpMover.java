package org.borlin.elevator.movement;

import org.borlin.elevator.Floor;

import java.util.List;

public class UpMover implements Mover {

    List<Floor> floors;
    public UpMover(List<Floor> floors) {
        this.floors = floors;
    }

    @Override
    public Floor move(Floor currentFloor) throws ArrayIndexOutOfBoundsException {
        int currentFloorIndex = floors.indexOf(currentFloor);
        if (currentFloorIndex >= floors.size() - 1) {
            throw new ArrayIndexOutOfBoundsException();
        }

        return floors.get(currentFloorIndex + 1);
    }
}
