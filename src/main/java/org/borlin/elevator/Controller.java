package org.borlin.elevator;

import org.borlin.elevator.movement.*;

import java.util.List;

public class Controller {

    private final List<Floor> floors;
    private Floor currentFloor;

    public Controller(List<Floor> floors, Floor currentFloor) {
        this.floors = floors;
        this.currentFloor = currentFloor;
    }

    public Floor getCurrentFloor() {
        return this.currentFloor;
    }

    public void run(MovementCalculator movementCalculator) throws FaultException {
        processMovements(movementCalculator);
    }

    private void processMovements (MovementCalculator movementCalculator) {
        while (true) { //TODO: make this more asynchronous rather than loop. Similar note in StayMover.
            Mover mover = movementCalculator.calculateNextMove(this.currentFloor);
            if (mover == null) {
                break;
            }
            this.currentFloor = mover.move(this.currentFloor);
        }
    }
}
