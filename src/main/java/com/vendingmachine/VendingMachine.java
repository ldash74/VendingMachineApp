package com.vendingmachine;


import com.vendingmachine.dispensers.IItemDispenser;
import com.vendingmachine.dispensers.IMoneyDispenser;
import com.vendingmachine.exceptions.VendingMachineException;
import com.vendingmachine.state.IStateManager;
import com.vendingmachine.types.DenominationType;
import com.vendingmachine.types.ItemType;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * This class encapsulates the working of Vending Machine
 */
public class VendingMachine implements IVendingMachine {

    private final IItemDispenser itemDispenser;
    private final IMoneyDispenser moneyDispenser;
    private final IStateManager stateManager;

    final List<DenominationType> insertedMoney = new LinkedList<DenominationType>();

    public VendingMachine(IItemDispenser itemDispenser, IMoneyDispenser moneyDispenser, IStateManager stateManager) {
        this.itemDispenser = itemDispenser;
        this.moneyDispenser = moneyDispenser;
        this.stateManager = stateManager;
    }


    public void acceptMoney(DenominationType moneyItem) {
        if (moneyItem != null) {
            insertedMoney.add(moneyItem);
        }
    }

    public void returnInsertedMoney() {
        for (DenominationType aDenomination : insertedMoney) {
            moneyDispenser.returnDenomination(aDenomination);
        }
        insertedMoney.clear();
    }

    public List<DenominationType> getCurrentlyInsertedMoney() {
        return Collections.unmodifiableList(insertedMoney);
    }

    public void vend(ItemType itemType) {

        BigDecimal totalEnteredAmount = BigDecimal.ZERO;

        //Calculate money entered
        for (DenominationType aDenomination : insertedMoney) {
            totalEnteredAmount = totalEnteredAmount.add(new BigDecimal(aDenomination.getText()));
        }

        BigDecimal change = totalEnteredAmount.subtract(new BigDecimal(itemType.getText()));

        //If amount entered is sufficient then instruct the itemdispenser to dispense and return change (if required).
        if (change.compareTo(BigDecimal.ZERO) >= 0) { // exact or more amount entered
            //dispense item
            itemDispenser.dispense(itemType);

            //Commit Inserted Money - Can't return the coin anymore!
            commitInsertedMoney();

            //Return change
            if (change.compareTo(BigDecimal.ZERO) ==0) {
                returnChange(change);
            }
        } else {
            throw new VendingMachineException("Not enough money to dispense item");
        }
    }

    private void commitInsertedMoney() {
        for (DenominationType aDenomination : insertedMoney) {
            stateManager.addDenomination(aDenomination);
        }

        insertedMoney.clear();
    }

    private void returnChange(BigDecimal moneyToReturn) {
        moneyDispenser.returnMoney(moneyToReturn);
    }
}
