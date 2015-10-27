package com.vendingmachine.state;


import com.vendingmachine.money.MoneyItem;
import com.vendingmachine.items.Item;
import com.vendingmachine.types.DenominationType;
import com.vendingmachine.types.ItemType;

import java.util.*;

/**
 * Class used to manage the state of the vending machine
 */
public class StateManager implements IStateManager {
    private Map<DenominationType, MoneyItem> listOfDenominations = new HashMap<DenominationType, MoneyItem>();
    private Map<ItemType, Item> listOfItems = new HashMap<ItemType, Item>();

    public void loadDenominationsInMachine(List<MoneyItem> changeList) {
        for (MoneyItem item : changeList) {
            if (listOfDenominations.containsKey(item.getType())) {
                listOfDenominations.get(item.getType()).increaseCount();
            } else {
                MoneyItem aNewMoneyItem = new MoneyItem(item.getType());
                aNewMoneyItem.increaseCount();
                listOfDenominations.put(item.getType(), aNewMoneyItem);
            }
        }
    }

    public void addDenomination(DenominationType denominationType) {
        if (listOfDenominations.containsKey(denominationType)) {
            listOfDenominations.get(denominationType).increaseCount();
        } else {
            MoneyItem aNewMoneyItem = new MoneyItem(denominationType);
            aNewMoneyItem.increaseCount();
            listOfDenominations.put(denominationType, aNewMoneyItem);
        }
    }

    public void loadItemsInMachine(List<Item> itemList) {
        for (Item item : itemList) {
            if (listOfItems.containsKey(item.getTypeOfThisItem())) {
                listOfItems.get(item.getTypeOfThisItem()).increaseCount();
            } else {
                Item aNewItem = new Item(item.getTypeOfThisItem());
                aNewItem.increaseCount();
                listOfItems.put(item.getTypeOfThisItem(), aNewItem);
            }
        }
    }

    public boolean getItem(ItemType itemType) {
        boolean itemPresent = false;

        if (listOfItems.containsKey(itemType) &&
                listOfItems.get(itemType).getCount() > 0) {

            listOfItems.get(itemType).decreaseCount();
            itemPresent = true;
        }

        return itemPresent;

    }

    public boolean getDenomination(DenominationType denominationType) {
        boolean changePresent = false;

        if (listOfDenominations.containsKey(denominationType) &&
                listOfDenominations.get(denominationType).getCount() > 0) {

            listOfDenominations.get(denominationType).decreaseCount();
            changePresent = true;
        }

        return changePresent;
    }

    public Map<DenominationType, MoneyItem> getListOfAvailableDenominations() {
        return Collections.unmodifiableMap(listOfDenominations);
    }

    public Map<ItemType, Item> getListOfAvailableItems() {
        return Collections.unmodifiableMap(listOfItems);
    }

}
