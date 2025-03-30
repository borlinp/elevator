package org.borlin.elevator.movement;

import org.borlin.elevator.Floor;

import java.util.List;

public class StayMover implements Mover {

    List<Floor> floors;
    public StayMover(List<Floor> floors) {
        this.floors = floors;
    }
    @Override
    public Floor move(Floor currentFloor) throws ArrayIndexOutOfBoundsException {
        try {
            Thread.sleep(1000);  //Temporary hack.  Something should wait until a button is pressed before moving again.
        } catch (InterruptedException e) {
            return currentFloor;
        }
        return currentFloor;
    }
}
