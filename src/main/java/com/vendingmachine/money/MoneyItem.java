package com.vendingmachine.money;


import com.vendingmachine.types.DenominationType;

public class MoneyItem {
    private final DenominationType type;
    private int count = 0;

    public MoneyItem(DenominationType type) {
        this.type = type;
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

    public DenominationType getType() {
        return type;
    }
}
