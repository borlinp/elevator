package org.borlin.elevator.humaninterface;

import org.borlin.elevator.movement.MovementDirection;

import java.util.HashSet;
import java.util.List;

public abstract class RequestTransportButton implements Button {

    private final HashSet<ButtonDepressedReceiver> alerts = new HashSet<>();

    public void push() {
        for (ButtonDepressedReceiver alert: alerts) {
            alert.notifier(this);
        }
    }

    public void registerButtonDepressedReceivers(ButtonDepressedReceiver... alerts) {
        this.alerts.addAll(List.of(alerts));
    }

    abstract public MovementDirection getDirection();
}
