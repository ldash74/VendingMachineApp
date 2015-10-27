package com.vendingmachine.dispensers;


import com.vendingmachine.state.IStateManager;
import com.vendingmachine.types.DenominationType;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

public class MoneyDispenserTest {

    MoneyDispenser classUnderTest;
    IStateManager mockStateManager;

    @Before
    public void setup() {
        mockStateManager = mock(IStateManager.class);
        classUnderTest = new MoneyDispenser(mockStateManager);
    }

    @Test
    public void testReturnMoney() {

        //when
        when(mockStateManager.getDenomination(DenominationType.DIME)).thenReturn(true);
        when(mockStateManager.getDenomination(DenominationType.QUARTER)).thenReturn(true);
        when(mockStateManager.getDenomination(DenominationType.DOLLAR)).thenReturn(true);

        classUnderTest.returnMoney(new BigDecimal("1.35"));

        //verify
        verify(mockStateManager, times(1)).getDenomination(DenominationType.DIME);
        verify(mockStateManager, times(1)).getDenomination(DenominationType.QUARTER);
        verify(mockStateManager, times(1)).getDenomination(DenominationType.DOLLAR);
    }
}
