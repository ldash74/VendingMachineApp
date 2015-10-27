package com.vendingmachine;


import com.vendingmachine.types.DenominationType;
import com.vendingmachine.types.ItemType;

import java.util.List;

public interface IVendingMachine {

    void acceptMoney(DenominationType moneyItem);

    void returnInsertedMoney();

    List<DenominationType> getCurrentlyInsertedMoney();

    void vend(ItemType itemType);
}
