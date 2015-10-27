package com.vendingmachine.items;


import com.vendingmachine.types.ItemType;

public class Item {

    private ItemType typeOfThisItem;
    private int count = 0;

    public Item(ItemType typeOfThisItem) {
        this.typeOfThisItem = typeOfThisItem;
    }

    public ItemType getTypeOfThisItem() {
        return typeOfThisItem;
    }

    public void increaseCount() {
        count++;
    }

    public void decreaseCount() {
        count--;
    }

    public int getCount() {
        return count;
    }

}
