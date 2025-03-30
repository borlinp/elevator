package org.borlin.elevator.movement;

import org.borlin.elevator.Floor;
import org.borlin.elevator.humaninterface.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class DefaultUpDownMovementCalculatorTest {

    List<Floor> floors = new ArrayList<>();

    Floor firstFloor;
    Floor secondFloor;
    Floor thirdFloor;
    Floor fourthFloor;
    Floor fifthFloor;
    Floor sixthFloor;

    RequestDownTransportButton secondFloorDownButton;
    RequestDownTransportButton thirdFloorDownButton;
    RequestDownTransportButton forthFloorDownButton;
    RequestDownTransportButton fifthFloorDownButton;
    RequestDownTransportButton sixthFloorDownButton;

    RequestUpTransportButton firstFloorUpButton;
    RequestUpTransportButton secondFloorUpButton;
    RequestUpTransportButton thirdFloorUpButton;
    RequestUpTransportButton forthFloorUpButton;
    RequestUpTransportButton fifthFloorUpButton;

    FloorRequestButton gotoFirstButton;
    FloorRequestButton gotoSecondButton;
    FloorRequestButton gotoThirdButton;
    FloorRequestButton gotoFourthButton;
    FloorRequestButton gotoFifthButton;
    FloorRequestButton gotoSixthButton;

    InternalPanel internalPanel;

    @BeforeEach
    void setup() {
        firstFloor = new Floor(1);
        secondFloor = new Floor(2);
        thirdFloor = new Floor(3);
        fourthFloor = new Floor(4);
        fifthFloor = new Floor(5);
        sixthFloor = new Floor(6);

        secondFloorDownButton = new RequestDownTransportButton();
        thirdFloorDownButton = new RequestDownTransportButton();
        forthFloorDownButton = new RequestDownTransportButton();
        fifthFloorDownButton = new RequestDownTransportButton();
        sixthFloorDownButton = new RequestDownTransportButton();

        firstFloorUpButton = new RequestUpTransportButton();
        secondFloorUpButton = new RequestUpTransportButton();
        thirdFloorUpButton = new RequestUpTransportButton();
        forthFloorUpButton = new RequestUpTransportButton();
        fifthFloorUpButton = new RequestUpTransportButton();

        gotoFirstButton = new FloorRequestButton();
        gotoSecondButton = new FloorRequestButton();
        gotoThirdButton = new FloorRequestButton();
        gotoFourthButton = new FloorRequestButton();
        gotoFifthButton = new FloorRequestButton();
        gotoSixthButton = new FloorRequestButton();
        floors.clear();
        floors.add(firstFloor);
        floors.add(secondFloor);
        floors.add(thirdFloor);
        floors.add(fourthFloor);
        floors.add(fifthFloor);
        floors.add(sixthFloor);

        firstFloor.setPanel(new ButtonPanel(new HashSet<>(Collections.singletonList(firstFloorUpButton)), null));
        secondFloor.setPanel(new ButtonPanel(new HashSet<>(Arrays.asList(secondFloorUpButton, secondFloorDownButton)), null));
        thirdFloor.setPanel(new ButtonPanel(new HashSet<>(Arrays.asList(thirdFloorUpButton, thirdFloorDownButton)), null));
        fourthFloor.setPanel(new ButtonPanel(new HashSet<>(Arrays.asList(forthFloorUpButton, forthFloorDownButton)), null));
        fifthFloor.setPanel(new ButtonPanel(new HashSet<>(Arrays.asList(fifthFloorUpButton, fifthFloorDownButton)), null));
        sixthFloor.setPanel(new ButtonPanel(new HashSet<>(Collections.singletonList(sixthFloorDownButton)), null));

        HashMap<Button, Floor> internalButtons = new HashMap<Button, Floor>(16, 8);
        internalButtons.put(gotoFirstButton, this.firstFloor);
        internalButtons.put(gotoSecondButton, this.secondFloor);
        internalButtons.put(gotoThirdButton, this.thirdFloor);
        internalButtons.put(gotoFourthButton, this.fourthFloor);
        internalButtons.put(gotoFifthButton, this.fifthFloor);
        internalButtons.put(gotoSixthButton, this.sixthFloor);
        internalPanel = new InternalPanel(internalButtons);
    }

    @Test
    void calculateNextMoveOnFifthFloorRequestingFirst() {

        DefaultUpDownMovementCalculator calc = new DefaultUpDownMovementCalculator(floors, internalPanel);

        gotoFirstButton.push();
        Mover mover = calc.calculateNextMove(fifthFloor);
        assertInstanceOf(DownMover.class, mover);

    }

    @Test
    void calculateNextMoveOnFirstFloorRequestingFirst() {

        DefaultUpDownMovementCalculator calc = new DefaultUpDownMovementCalculator(floors, internalPanel);

        gotoFirstButton.push();
        Mover mover = calc.calculateNextMove(firstFloor);
        assertInstanceOf(StayMover.class, mover);

    }

    @Test
    void calculateNextMoveOnFirstFloorRequestingSixth() {

        DefaultUpDownMovementCalculator calc = new DefaultUpDownMovementCalculator(floors, internalPanel);

        gotoSixthButton.push();
        Mover mover = calc.calculateNextMove(firstFloor);
        assertInstanceOf(UpMover.class, mover);

    }

    @Test
    void calculateNextMoveOnSecondFloorRequestingThird() {

        DefaultUpDownMovementCalculator calc = new DefaultUpDownMovementCalculator(floors, internalPanel);

        gotoThirdButton.push();
        Mover mover = calc.calculateNextMove(secondFloor);
        assertInstanceOf(UpMover.class, mover);

    }

    @Test
    void calculateNextMoveOnThirdFloorSixthFloorDownRequest() {

        DefaultUpDownMovementCalculator calc = new DefaultUpDownMovementCalculator(floors, internalPanel);

        sixthFloorDownButton.push();
        Mover mover = calc.calculateNextMove(thirdFloor);
        assertInstanceOf(UpMover.class, mover);

    }

    @Test
    void calculateNextMoveOnThirdFloorSixthFloorDownRequestFollowedBySecondUp() {

        DefaultUpDownMovementCalculator calc = new DefaultUpDownMovementCalculator(floors, internalPanel);

        //Trigger that the last move was Up
        gotoThirdButton.push();
        Mover firstMover = calc.calculateNextMove(secondFloor);
        assertInstanceOf(UpMover.class, firstMover, "Test setup failed.  Check all other tests are passing for UP.");

        sixthFloorDownButton.push(); //First pushed and should continue up
        firstFloorUpButton.push();
        Mover mover = calc.calculateNextMove(thirdFloor);
        assertInstanceOf(UpMover.class, mover);

    }
}