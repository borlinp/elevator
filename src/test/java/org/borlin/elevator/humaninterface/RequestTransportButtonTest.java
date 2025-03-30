package org.borlin.elevator.humaninterface;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestTransportButtonTest {

    @Test
    void push() {
        RequestTransportButton rtb = new RequestTransportButtonStub();
        ButtonDepressedReceiverMock alert = new ButtonDepressedReceiverMock();
        rtb.registerButtonDepressedReceivers(alert);

        rtb.push();

        assertTrue(alert.isPressed());
        assertEquals(rtb, alert.getButtonPressed());
    }

    class ButtonDepressedReceiverMock implements ButtonDepressedReceiver{

        private boolean isPressed = false;
        private Button buttonPressed = null;

        @Override
        public void notifier(Button button) {
            this.isPressed = true;
            this.buttonPressed = button;
        }

        boolean isPressed() {
            return this.isPressed;
        }

        Button getButtonPressed() {
            return this.buttonPressed;
        }
    }

}