package com.vendingmachine.dispensers;


import com.vendingmachine.exceptions.VendingMachineException;
import com.vendingmachine.state.IStateManager;
import com.vendingmachine.types.DenominationType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MoneyDispenser implements IMoneyDispenser<DenominationType> {
    IStateManager itsStateManager;

    public MoneyDispenser(IStateManager itsStateManager) {
        this.itsStateManager = itsStateManager;
    }

    public void dispense(DenominationType denominationType) {
        if (itsStateManager.getDenomination(denominationType)) {
            System.out.println(denominationType.name() + " dispensed.");
        } else {
            throw new VendingMachineException("Cannot vend denomination : " + denominationType.name() + ". Not enough of them in the machine. Press Coin Return to get your money back");
        }
    }

    public void returnDenomination(DenominationType denominationType) {
        System.out.println(denominationType.name() + " returned.");
    }


    public void returnMoney(BigDecimal amount) {

        List<DenominationType> moneyToBeReturned = new ArrayList<DenominationType>();

        while (amount.compareTo(BigDecimal.ZERO) > 0) {
            if (amount.compareTo(new BigDecimal(DenominationType.DOLLAR.getText())) >= 0) {
                moneyToBeReturned.add(DenominationType.DOLLAR);
                amount = amount.subtract(new BigDecimal(DenominationType.DOLLAR.getText()));
            } else if (amount.compareTo(new BigDecimal(DenominationType.QUARTER.getText())) >= 0) {
                moneyToBeReturned.add(DenominationType.QUARTER);
                amount = amount.subtract(new BigDecimal(DenominationType.QUARTER.getText()));
            } else if (amount.compareTo(new BigDecimal(DenominationType.DIME.getText())) >= 0) {
                moneyToBeReturned.add(DenominationType.DIME);
                amount = amount.subtract(new BigDecimal(DenominationType.DIME.getText()));
            } else if (amount.compareTo(new BigDecimal(DenominationType.NICKEL.getText())) >= 0) {
                moneyToBeReturned.add(DenominationType.NICKEL);
                amount = amount.subtract(new BigDecimal(DenominationType.NICKEL.getText()));
            }
        }

        for (DenominationType aType : moneyToBeReturned) {
            if (itsStateManager.getDenomination(aType)) {
                returnDenomination(aType);
            } else {
                throw new VendingMachineException("Cannot vend denomination : " + aType.name() + ". Not enough of them in the machine. Press Coin Return to get your money back");
            }
        }

    }
}
