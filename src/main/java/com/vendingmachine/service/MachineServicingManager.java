package com.vendingmachine.service;


import com.vendingmachine.items.Item;
import com.vendingmachine.money.MoneyItem;
import com.vendingmachine.state.StateManager;

import java.util.List;

/**
 * Class used by servicing person to load item and money into the vending machine
 */
public class MachineServicingManager {
    final StateManager itsStateManager;

    public MachineServicingManager(StateManager itsStateManager) {
        this.itsStateManager = itsStateManager;
    }

    public void loadItemsInMachine(List<Item> listOfItems) {
        itsStateManager.loadItemsInMachine(listOfItems);
    }

    public void loadChangesInMachine(List<MoneyItem> moneyItemList) {

        itsStateManager.loadDenominationsInMachine(moneyItemList);

    }
}
