package com.vendingmachine.state;

import com.vendingmachine.money.MoneyItem;
import com.vendingmachine.items.Item;
import com.vendingmachine.types.DenominationType;
import com.vendingmachine.types.ItemType;

import java.util.List;
import java.util.Map;

public interface IStateManager {


    void loadDenominationsInMachine(List<MoneyItem> changeList);

    void addDenomination(DenominationType denominationType);

    void loadItemsInMachine(List<Item> itemList);

    boolean getItem(ItemType itemType);

    boolean getDenomination(DenominationType denominationType);

    Map<DenominationType, MoneyItem> getListOfAvailableDenominations();

    Map<ItemType, Item> getListOfAvailableItems();
}
