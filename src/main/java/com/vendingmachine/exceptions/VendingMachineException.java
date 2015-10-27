package com.vendingmachine.exceptions;


public class VendingMachineException extends RuntimeException{

    String errorMsg;
    public VendingMachineException(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
