package org.borlin.elevator;

import org.borlin.elevator.humaninterface.RequestDownTransportButton;
import org.borlin.elevator.humaninterface.RequestUpTransportButton;
import org.borlin.elevator.humaninterface.TwoButtonPanel;
import org.borlin.elevator.humaninterface.Button;
import org.borlin.elevator.movement.DownMover;
import org.junit.jupiter.api.Disabled;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    @org.junit.jupiter.api.Test
    @Disabled("Unable to test this until the loop in Controller is replaced with asynchronous callbacks and interrupts")
    void runMoveUp() {
        List<Floor> floors = new ArrayList<>();

        Floor floor1 = new Floor(1);
        Floor floor2 = new Floor(2);
        Floor floor3 = new Floor(3);
        Floor floor4 = new Floor(4);
        Floor floor5 = new Floor(5);
        Floor floor6 = new Floor(6);

        floors.add(floor1);
        floors.add(floor2);
        floors.add(floor3);
        floors.add(floor4);
        floors.add(floor5);
        floors.add(floor6);

        Set<Button> buttons = new HashSet<Button>();
        RequestUpTransportButton upButton = new RequestUpTransportButton();
        buttons.add(upButton);
        RequestDownTransportButton downButton = new RequestDownTransportButton();
        buttons.add(downButton);
        TwoButtonPanel panel = null;
        try {
            panel = new TwoButtonPanel(buttons, null);
        } catch (Exception e) {
            System.out.println("Oops. Something went wrong with my panel creation.  " + e.toString()); //TODO: handle correctly
        }
        floor1.setPanel(panel);

        Controller controller = new Controller(floors, floor4);

        MovementCalculatorMock mcm = new MovementCalculatorMock(new DownMover(floors));
        try {
            controller.run(mcm);
        } catch (FaultException e) {
            fail(e.toString());
        }

        Assert.isTrue(mcm.isCalculateNextMoveCalled());

    }

}
