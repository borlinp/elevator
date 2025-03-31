package org.borlin;

import org.borlin.elevator.Controller;
import org.borlin.elevator.FaultException;
import org.borlin.elevator.Floor;
import org.borlin.elevator.humaninterface.Button;
import org.borlin.elevator.humaninterface.FloorRequestButton;
import org.borlin.elevator.humaninterface.InternalPanel;
import org.borlin.elevator.movement.DefaultUpDownMovementCalculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Config config = buildConfig();
        Controller controller = new Controller(config.getFloors(), config.getFirstFloor());
        try {
            controller.run(new DefaultUpDownMovementCalculator(config.getFloors(), config.getInternalPanel()));
            //TODO: have a way to interrupt run.  Idea: have a return from run that could allow a STOP to be called or thread shutdown signal.
        } catch (FaultException e) {
            System.out.println(e.getFaultReason());
        }
    }

    private static Config buildConfig() {
        //TODO: config in injection
        List<Floor> floors = new ArrayList<>();

        Floor firstFloor = new Floor(1);
        Floor secondFloor = new Floor(2);
        Floor thirdFloor = new Floor(3);
        Floor fourthFloor = new Floor(4);
        Floor fifthFloor = new Floor(5);
        Floor sixthFloor = new Floor(6);

        floors.add(firstFloor);
        floors.add(secondFloor);
        floors.add(thirdFloor);
        floors.add(fourthFloor);
        floors.add(fifthFloor);
        floors.add(sixthFloor);

        HashMap<Button, Floor> internalButtons = new HashMap<Button, Floor>(16, 8);
        internalButtons.put(new FloorRequestButton(), firstFloor);
        InternalPanel internalPanel = new InternalPanel(internalButtons);

        Config config = new Config();
        config.setFloors(floors);
        config.setFirstFloor(firstFloor); //TODO: How do we know where the elevator is when it's turned on?  Don't assume floor 1.
        config.setInternalPanel(internalPanel);

        return config;
    }

    private static class Config {
        private List<Floor> floors = new ArrayList<>();
        private Floor firstFloor;

        public InternalPanel getInternalPanel() {
            return internalPanel;
        }

        public void setInternalPanel(InternalPanel internalPanel) {
            this.internalPanel = internalPanel;
        }

        public Floor getFirstFloor() {
            return firstFloor;
        }

        public void setFirstFloor(Floor firstFloor) {
            this.firstFloor = firstFloor;
        }

        public List<Floor> getFloors() {
            return floors;
        }

        public void setFloors(List<Floor> floors) {
            this.floors = floors;
        }

        private InternalPanel internalPanel;


    }

}
