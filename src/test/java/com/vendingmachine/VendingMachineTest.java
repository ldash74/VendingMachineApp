package com.vendingmachine;

import com.vendingmachine.dispensers.IItemDispenser;
import com.vendingmachine.dispensers.IMoneyDispenser;
import com.vendingmachine.exceptions.VendingMachineException;
import com.vendingmachine.state.IStateManager;
import com.vendingmachine.types.DenominationType;
import com.vendingmachine.types.ItemType;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class VendingMachineTest {

    VendingMachine classUnderTest;

    IItemDispenser mockItemDispenser;
    IMoneyDispenser mockMoneyDispenser;
    IStateManager mockStateManager;

    @Before
    public void setup() {

        mockItemDispenser = mock(IItemDispenser.class);
        mockMoneyDispenser = mock(IMoneyDispenser.class);
        mockStateManager = mock(IStateManager.class);

        classUnderTest = new VendingMachine(mockItemDispenser, mockMoneyDispenser, mockStateManager);
    }

    @Test
    public void testAcceptMoney() {

        classUnderTest.acceptMoney(DenominationType.DIME);

        assertThat(classUnderTest.getCurrentlyInsertedMoney().size()).isEqualTo(1);
        assertThat(classUnderTest.getCurrentlyInsertedMoney().get(0)).isEqualTo(DenominationType.DIME);

    }

    @Test
    public void testReturnMoney() {

        //given
        classUnderTest.acceptMoney(DenominationType.DIME);
        classUnderTest.acceptMoney(DenominationType.NICKEL);

        //when
        classUnderTest.returnInsertedMoney();

        //verify
        verify(mockMoneyDispenser, times(1)).returnDenomination(DenominationType.DIME);
        verify(mockMoneyDispenser, times(1)).returnDenomination(DenominationType.NICKEL);

    }

    @Test
    public void testVendWithExactMoneyItemAvailable() {
        //given
        classUnderTest.acceptMoney(DenominationType.QUARTER);
        classUnderTest.acceptMoney(DenominationType.QUARTER);
        classUnderTest.acceptMoney(DenominationType.QUARTER);
        classUnderTest.acceptMoney(DenominationType.QUARTER);

        //when
        classUnderTest.vend(ItemType.B);

        //verify
        verify(mockItemDispenser, times(1)).dispense(ItemType.B);
        verify(mockStateManager, times(4)).addDenomination(DenominationType.QUARTER);

        assertThat(classUnderTest.getCurrentlyInsertedMoney().size()).isEqualTo(0);
    }

    @Test (expected = VendingMachineException.class)
    public void testVendWithInsufficientMoneyItemAvailable() {
        //given
        classUnderTest.acceptMoney(DenominationType.QUARTER);
        classUnderTest.acceptMoney(DenominationType.QUARTER);
        classUnderTest.acceptMoney(DenominationType.QUARTER);

        //when
        classUnderTest.vend(ItemType.B);
    }

}
