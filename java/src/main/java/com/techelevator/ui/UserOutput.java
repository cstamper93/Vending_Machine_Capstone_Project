package com.techelevator.ui;

import com.techelevator.models.Items;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * Responsibilities: This class should handle formatting and displaying ALL
 * messages to the user
 * 
 * Dependencies: None
 */
public class UserOutput
{

    public static void displayMessage(String message)
    {
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    public static void displayHomeScreen()
    {
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("                      Home");
        System.out.println("***************************************************");
        System.out.println();
    }

    public static void displayMainMenu() {
        System.out.println("What would you like to do?\n");

        System.out.println("D) Display Vending Machine Items");
        System.out.println("P) Purchase");
        System.out.println("E) Exit");

        System.out.print("\nPlease select an option: ");
    }

    public static void displayPurchaseMenu(BigDecimal moneyInserted) {
        System.out.println("(M) Feed Money");
        System.out.println("(S) Select Item");
        System.out.println("(F) Finish Transaction\n");

        System.out.println();
        System.out.printf("Current Money Provided: $%.2f", moneyInserted);
        System.out.println();
        System.out.print("Please select an option: ");
    }

    public static void displayMoneyMenu() {
        System.out.println("How much money would you like to insert? ($1, $5, $10, $20 only): ");
    }

    public static void displayItemPrompt() {
        System.out.println("Please select slot number for item: ");
    }

    public static void displayVendingMachineItems(List<Items> items) {
        for (Items item : items)
            System.out.println(item);
    }

    public static void displayChangeMessage(int dollars, int quarters, int dimes, int nickels, BigDecimal changeDue) {
        System.out.println("\nThank you! Your change is:");
        System.out.println(dollars + " dollars");
        System.out.println(quarters + " quarters");
        System.out.println(dimes + " dimes");
        System.out.println(nickels + " nickels");
        System.out.printf("Total change: $%.2f", changeDue);
    }
}
