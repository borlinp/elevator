package org.borlin.elevator.humaninterface;

/*
Defines a button an elevator user can press
 */
public interface Button {
    public void push();
    public void registerButtonDepressedReceivers(ButtonDepressedReceiver... alerts);
}
