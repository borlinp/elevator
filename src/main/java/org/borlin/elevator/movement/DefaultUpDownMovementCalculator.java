package org.borlin.elevator.movement;

import org.borlin.elevator.ElevatorCallRequest;
import org.borlin.elevator.ElevatorGotoRequest;
import org.borlin.elevator.Floor;
import org.borlin.elevator.humaninterface.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultUpDownMovementCalculator implements MovementCalculator {

    private final List<Floor> floors;

    private MovementDirection direction = MovementDirection.STOP;
    private MovementDirection lastMovementDirection = MovementDirection.STOP;

    private final HashMap<Floor, CallRequest> upRequests = new HashMap<Floor, CallRequest>(50,10);
    private final HashMap<Floor, CallRequest> downRequests = new HashMap<Floor, CallRequest>(50,10);
    //TODO: private ArrayList<CallRequest> stopRequests = new ArrayList<>();
    private CallRequest firstCallRequest = null;

    private final HashMap<Floor, GotoRequest> gotoRequests = new HashMap<>(50,10);

    private final UpMover upMover;
    private final DownMover downMover;
    private final StayMover stayMover;

    public DefaultUpDownMovementCalculator(List<Floor> floors, InternalPanel interiorPanel) {
        this.floors = floors;
        this.upMover = new UpMover(floors);
        this.downMover = new DownMover(floors);
        this.stayMover = new StayMover(floors);
        for (Floor floor : floors) {
            CallRequest callRequest = new CallRequest();
            floor.registerElevatorCallRequest(callRequest);
        }
        for (Map.Entry<Button, Floor> buttons : interiorPanel.getButtonsInOrder().entrySet()) {
            GotoRequest gotoRequest = new GotoRequest();
            ButtonDepressedReceiver receiver = (b) -> gotoRequest.gotoFloor(b, buttons.getValue());
            buttons.getKey().registerButtonDepressedReceivers(receiver);
        }

    }

    public Mover calculateNextMove(Floor currentFloor) {

        if (upRequests.isEmpty() && downRequests.isEmpty() && gotoRequests.isEmpty()) {
            return this.stayMover;
        } else {
            upRequests.remove(currentFloor);
            downRequests.remove(currentFloor);
            gotoRequests.remove(currentFloor);
            if (firstCallRequest != null && firstCallRequest.getFloor().equals(currentFloor)) {
                firstCallRequest = null; //This may leave us with an out-of-order request. Fix in the future.
            }
        }

        int currentFloorIndex = this.floors.indexOf(currentFloor);

        switch (this.direction) {
            case UP:
                return determineContinueGoingUp(currentFloorIndex);
            case DOWN:
                return determineContinueGoingDown(currentFloorIndex);
            case STOP:
                return calculateNextEfficientMove(currentFloor);
            default:
                setDirection(MovementDirection.STOP);
                return this.stayMover;
        }
    }

    private Mover determineContinueGoingUp (int currentFloorIndex) {
        if (currentFloorIndex >= this.floors.size() - 1 || (upRequests.isEmpty() && gotoRequests.isEmpty())) {
            setDirection(MovementDirection.STOP);
            return this.stayMover;
        }
        for (Floor floor : this.gotoRequests.keySet()) {
            if (floors.indexOf(floor) > currentFloorIndex) {
                setDirection(MovementDirection.UP);
                return this.upMover;
            }
        }
        for (Floor floor : this.upRequests.keySet()) {
            if (floors.indexOf(floor) > currentFloorIndex) {
                setDirection(MovementDirection.UP);
                return this.upMover;
            }
        }
        for (Floor floor : this.downRequests.keySet()) {
            if (floors.indexOf(floor) > currentFloorIndex) {
                setDirection(MovementDirection.UP);
                return this.upMover;
            }
        }
        setDirection(MovementDirection.STOP);
        return this.stayMover;
    }

    private Mover determineContinueGoingDown (int currentFloorIndex) {

        if (currentFloorIndex >= this.floors.size() - 1 || (downRequests.isEmpty() && gotoRequests.isEmpty())) {
            setDirection(MovementDirection.STOP);
            return this.stayMover;
        }
        for (Floor floor : this.gotoRequests.keySet()) {
            if (floors.indexOf(floor) > currentFloorIndex) {
                return this.downMover;
            }
        }
        for (Floor floor : this.downRequests.keySet()) {
            if (floors.indexOf(floor) > currentFloorIndex) {
                return this.downMover;
            }
        }
        for (Floor floor : this.upRequests.keySet()) {
            if (floors.indexOf(floor) > currentFloorIndex) {
                return this.downMover;
            }
        }
        setDirection(MovementDirection.STOP);
        return this.stayMover;
    }

    private Mover calculateNextEfficientMove(Floor currentFloor) {

        //TODO: there's an assumption in here that our direction is STOP. Potentially fix.

        int currentFloorIndex = this.floors.indexOf(currentFloor);
        int nextFloorIndex;
        if (upRequests.isEmpty() && downRequests.isEmpty() && gotoRequests.isEmpty()) {
            setDirection(MovementDirection.STOP);
            return this.stayMover;
        } else if (this.firstCallRequest != null) {
            nextFloorIndex = this.floors.indexOf(this.firstCallRequest.getFloor());
        } else if (currentFloorIndex == 0) {
            setDirection(MovementDirection.UP);
            return this.upMover;
        } else if (currentFloorIndex == this.floors.size() - 1) {
            setDirection(MovementDirection.DOWN);
            return this.downMover;
        } else {
            boolean goUp = this.stayMover != determineContinueGoingUp(currentFloorIndex);
            boolean goDown = this.stayMover != determineContinueGoingDown(currentFloorIndex);
            if (goUp && (this.lastMovementDirection == MovementDirection.UP || lastMovementDirection == MovementDirection.STOP)) {
                nextFloorIndex = currentFloorIndex + 1;
            } else if (goDown && (this.lastMovementDirection == MovementDirection.DOWN || lastMovementDirection == MovementDirection.STOP)) {
                nextFloorIndex = currentFloorIndex - 1;
            } else {
                setDirection(MovementDirection.DOWN);
                return this.downMover;
            }
        }

        int floorRelativity = nextFloorIndex - currentFloorIndex;
        if (floorRelativity > 0) {
            setDirection(MovementDirection.UP);
            return this.upMover;
        } else if (floorRelativity < 0) {
            setDirection(MovementDirection.DOWN);
            return this.downMover;
        }
        setDirection(MovementDirection.STOP);
        return this.stayMover;
    }

    private void setDirection(MovementDirection direction) {
        this.lastMovementDirection = this.direction;
        this.direction = direction;
    }

    public class CallRequest implements ElevatorCallRequest {

        private RequestTransportButton button;
        private Floor floor;

        public Floor getFloor() {
            return floor;
        }

        public RequestTransportButton getButton() {
            return button;
        }


        @Override
        public void call(RequestTransportButton button, Floor floor) {
            this.button = button;
            this.floor = floor;

            if (firstCallRequest == null) {
                firstCallRequest = this;
            }
            switch (button.getDirection()) {
                case UP:
                    upRequests.put(floor, this);
                    break;
                case DOWN:
                    downRequests.put(floor, this);
                    break;
                //                case Stop:
                //                    stopRequests.add(this);
                default:
                    break; //TODO: throw exception
            }
        }
    }

    public class GotoRequest implements ElevatorGotoRequest {

        private Button button;
        private Floor floor;

        @Override
        public void gotoFloor(Button button, Floor floor) {
            this.floor = floor;
            this.button = button;
            gotoRequests.put(floor, this);
        }

        @Override
        public Floor getFloor() {
            return this.floor;
        }

        @Override
        public Button getButton() {
            return this.button;
        }
    }
}
