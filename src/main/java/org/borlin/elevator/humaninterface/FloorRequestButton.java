package org.borlin.elevator.humaninterface;

import java.util.HashSet;
import java.util.List;

public class FloorRequestButton implements Button {

    private final HashSet<ButtonDepressedReceiver> alerts = new HashSet<>();

    @Override
    public void push() {
        for (ButtonDepressedReceiver alert: alerts) {
            alert.notifier(this);
        }
    }

    @Override
    public void registerButtonDepressedReceivers(ButtonDepressedReceiver... alerts) {
        this.alerts.addAll(List.of(alerts));
    }
}
