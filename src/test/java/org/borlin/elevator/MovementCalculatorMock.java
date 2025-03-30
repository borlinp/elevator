package org.borlin.elevator;

import org.borlin.elevator.movement.MovementCalculator;
import org.borlin.elevator.movement.Mover;

public class MovementCalculatorMock implements MovementCalculator {

    private final Mover mover;


    private boolean calculateNextMoveCalled = false;

    public MovementCalculatorMock(Mover mc) {
        this.mover = mc;
    }

    @Override
    public Mover calculateNextMove(Floor currentFloor) {
        this.calculateNextMoveCalled = true;
        return this.mover;
    }

    public boolean isCalculateNextMoveCalled() {
        return calculateNextMoveCalled;
    }
}
