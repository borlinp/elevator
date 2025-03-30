package org.borlin.elevator.movement;

import org.borlin.elevator.Floor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UpMoverTest {

    static List<Floor> floors = new ArrayList<>();

    static Floor floor1;
    static Floor floor2;
    static Floor floor3;
    static Floor floor4;
    static Floor floor5;
    static Floor floor6;

    @BeforeAll
    static void setup() {

        floor1 = new Floor(1);
        floor2 = new Floor(2);
        floor3 = new Floor(3);
        floor4 = new Floor(4);
        floor5 = new Floor(5);
        floor6 = new Floor(6);

        floors.add(floor1);
        floors.add(floor2);
        floors.add(floor3);
        floors.add(floor4);
        floors.add(floor5);
        floors.add(floor6);
    }

//    @AfterEach
//    void tearDown() {
//        this.floors.clear(); //not needed?
//    }

    @Test
    void moveFromThirdFloor() {
        UpMover dm = new UpMover(floors);
        Floor resultingFloor = dm.move(floor3);
        assertEquals(floor4, resultingFloor);
    }

    @Test
    void moveFromBottomFloor() {
        UpMover dm = new UpMover(floors);
        Floor resultingFloor = dm.move(floor1);
        assertEquals(floor2, resultingFloor);
    }

    @Test
    void moveFromTopFloor() {
        UpMover dm = new UpMover(floors);
        try {
            dm.move(floor6);
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }
        fail("move() method did not throw and exception due to being on the top floor and asking to go up");
    }
}