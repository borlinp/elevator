package org.borlin.elevator;

public class FaultException extends Exception {

    String faultReason = "unknown";

    public String getFaultReason() {
        return faultReason;
    }

    public void setFaultReason(String faultReason) {
        this.faultReason = faultReason;
    }


}
