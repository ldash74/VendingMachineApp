package com.vendingmachine.statemanager;


import com.vendingmachine.items.Item;
import com.vendingmachine.money.MoneyItem;
import com.vendingmachine.state.IStateManager;
import com.vendingmachine.state.StateManager;
import com.vendingmachine.types.DenominationType;
import com.vendingmachine.types.ItemType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StateManagerTest {

    IStateManager classUnderTest;

    @Before
    public void setup() {
        classUnderTest = new StateManager();
    }

    @Test
    public void testLoadMoneySuccess() {

        List<MoneyItem> aListOfMoneyItems = new ArrayList<MoneyItem>();

        aListOfMoneyItems.add(new MoneyItem(DenominationType.DIME));
        aListOfMoneyItems.add(new MoneyItem(DenominationType.DIME));

        classUnderTest.loadDenominationsInMachine(aListOfMoneyItems);

        assertThat(classUnderTest.getListOfAvailableDenominations().size()).isEqualTo(1);
        assertThat(classUnderTest.getListOfAvailableDenominations().get(DenominationType.DIME).getCount()).isEqualTo(2);

    }

    @Test
    public void testAddDenomination() {

        classUnderTest.addDenomination(DenominationType.DIME);
        classUnderTest.addDenomination(DenominationType.DIME);

        assertThat(classUnderTest.getListOfAvailableDenominations().size()).isEqualTo(1);
        assertThat(classUnderTest.getListOfAvailableDenominations().get(DenominationType.DIME).getCount()).isEqualTo(2);

    }

    @Test
    public void testLoadItemsInMachine() {

        List<Item> itemList = new ArrayList<Item>();

        itemList.add(new Item(ItemType.A));
        itemList.add(new Item(ItemType.A));
        itemList.add(new Item(ItemType.B));

        classUnderTest.loadItemsInMachine(itemList);

        assertThat(classUnderTest.getListOfAvailableItems().size()).isEqualTo(2);
        assertThat(classUnderTest.getListOfAvailableItems().get(ItemType.A).getCount()).isEqualTo(2);
        assertThat(classUnderTest.getListOfAvailableItems().get(ItemType.B).getCount()).isEqualTo(1);
    }

    @Test
    public void testGetItem() {
        List<Item> itemList = new ArrayList<Item>();

        itemList.add(new Item(ItemType.A));
        itemList.add(new Item(ItemType.A));
        itemList.add(new Item(ItemType.B));

        classUnderTest.loadItemsInMachine(itemList);

        assertThat(classUnderTest.getListOfAvailableItems().size()).isEqualTo(2);
        assertThat(classUnderTest.getListOfAvailableItems().get(ItemType.A).getCount()).isEqualTo(2);
        assertThat(classUnderTest.getListOfAvailableItems().get(ItemType.B).getCount()).isEqualTo(1);

        classUnderTest.getItem(ItemType.A);

        assertThat(classUnderTest.getListOfAvailableItems().size()).isEqualTo(2);
        assertThat(classUnderTest.getListOfAvailableItems().get(ItemType.A).getCount()).isEqualTo(1);
        assertThat(classUnderTest.getListOfAvailableItems().get(ItemType.B).getCount()).isEqualTo(1);

        classUnderTest.getItem(ItemType.A);

        assertThat(classUnderTest.getListOfAvailableItems().size()).isEqualTo(2);
        assertThat(classUnderTest.getListOfAvailableItems().get(ItemType.A).getCount()).isEqualTo(0);
        assertThat(classUnderTest.getListOfAvailableItems().get(ItemType.B).getCount()).isEqualTo(1);

        classUnderTest.getItem(ItemType.B);

        assertThat(classUnderTest.getListOfAvailableItems().size()).isEqualTo(2);
        assertThat(classUnderTest.getListOfAvailableItems().get(ItemType.A).getCount()).isEqualTo(0);
        assertThat(classUnderTest.getListOfAvailableItems().get(ItemType.B).getCount()).isEqualTo(0);
    }

    @Test
    public void testGetITeamInsufficientItems() {
        List<Item> itemList = new ArrayList<Item>();

        itemList.add(new Item(ItemType.A));
        itemList.add(new Item(ItemType.A));

        classUnderTest.loadItemsInMachine(itemList);

        assertThat(classUnderTest.getItem(ItemType.B)).isEqualTo(false);

    }


}
