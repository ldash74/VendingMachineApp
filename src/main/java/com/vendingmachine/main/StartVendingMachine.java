package com.vendingmachine.main;

import com.vendingmachine.VendingMachine;
import com.vendingmachine.dispensers.MoneyDispenser;
import com.vendingmachine.dispensers.ItemDispenser;
import com.vendingmachine.service.MachineServicingManager;
import com.vendingmachine.state.StateManager;

import java.util.Scanner;

public class StartVendingMachine {

    StateManager vendingMachineStateManager;
    MachineServicingManager vendingMachineServicingManager;
    VendingMachine vendingMachine;

    public static void main(String[] args) {
        StartVendingMachine startVendingMachine = new StartVendingMachine();
        startVendingMachine.intialise();

        startVendingMachine.start();
    }

    private void start() {

       //Code to show the prompt and collect the input goes here.

    }

    private void intialise() {

        /**
         * Initialises the application by instantiating the various
         * dependencies, and injecting them to a
         *
         * Usually, we will use some framework like Spring to achieve this.
         * We are doing it manually here to make the concept explicit.
         */

        //StateManager - Responsible for managing the number of items and changes in the vending machine
        vendingMachineStateManager = new StateManager();

        //Machine Servicing manager - to be used to top up items and money in the machine
        vendingMachineServicingManager = new MachineServicingManager(vendingMachineStateManager);

        //Item Dispenser subsystem of vending machine
        ItemDispenser itemDispenser = new ItemDispenser(vendingMachineStateManager);

        //Coin Dispenser subsystem of vending machine
        MoneyDispenser moneyDispenser = new MoneyDispenser(vendingMachineStateManager);

        //Vending Machine object
        vendingMachine = new VendingMachine(itemDispenser, moneyDispenser, vendingMachineStateManager);

        System.out.println("Machine initialised");
    }
}
