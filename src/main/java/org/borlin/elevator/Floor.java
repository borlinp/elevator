package org.borlin.elevator;

import org.borlin.elevator.humaninterface.ButtonDepressedReceiver;
import org.borlin.elevator.humaninterface.Panel;
import org.borlin.elevator.humaninterface.RequestTransportButton;

public class Floor {

    int floorIdentifier;
    ElevatorCallRequest elevatorCallRequest;

    Panel panel;

    public Floor(int floorIdentifier) {
        this.floorIdentifier = floorIdentifier;
    }

    public Floor(int floorIdentifier, ElevatorCallRequest elevatorCallRequest) {
        this(floorIdentifier);
        this.registerElevatorCallRequest(elevatorCallRequest);
    }

    public int getFloorIdentifier() {
        return floorIdentifier;
    }

    public void registerElevatorCallRequest(ElevatorCallRequest elevatorCallRequest) {
        //Depending on business rules, we may want to check if one is already registered and throw and exception
        this.elevatorCallRequest = elevatorCallRequest;
    }


    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
        panel.getButtons().forEach(button -> {
            if (button instanceof RequestTransportButton) {
                ButtonDepressedReceiver alert = (b) -> {
                    this.elevatorCallRequest.call((RequestTransportButton) b, this);
                };
                button.registerButtonDepressedReceivers(alert);
            }
        });
    }
}
