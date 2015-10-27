package com.vendingmachine.dispensers;


public interface IDispenser<K> {
    void dispense(K type);
}
