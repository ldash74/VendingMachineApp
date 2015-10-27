package com.vendingmachine.dispensers;


import com.vendingmachine.exceptions.VendingMachineException;
import com.vendingmachine.state.StateManager;
import com.vendingmachine.types.ItemType;

public class ItemDispenser implements IItemDispenser<ItemType>{

    StateManager itsStateManager;

    public ItemDispenser(StateManager itsStateManager) {
        this.itsStateManager = itsStateManager;
    }

    public void dispense (ItemType itemType) {
        if (itsStateManager.getItem(itemType)) {
            System.out.println(itemType.name() + " dispensed.");
        } else {
            throw new VendingMachineException("Cannot vend item: " + itemType.name() + ". Not enough of them in the machine. Press Coin Return to get your money back");
        }
    }
}
