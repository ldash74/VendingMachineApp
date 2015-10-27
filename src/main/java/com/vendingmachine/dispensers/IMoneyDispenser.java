package com.vendingmachine.dispensers;

import java.math.BigDecimal;

public interface IMoneyDispenser<DenominationType> extends IDispenser<DenominationType>{
    void returnDenomination(DenominationType type);
    void returnMoney(BigDecimal amount);
}
