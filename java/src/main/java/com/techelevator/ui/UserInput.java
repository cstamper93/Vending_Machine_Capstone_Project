package com.techelevator.ui;

import com.techelevator.application.Sales;
import com.techelevator.application.VendingMachine;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Responsibilities: This class should handle receiving ALL input from the User
 * 
 * Dependencies: None
 */
public class UserInput {

    private static Scanner scanner = new Scanner(System.in);

    public static String getMainMenuOption() {
        String selectedOption = scanner.nextLine();
        String option = selectedOption.trim();

        if (option.equalsIgnoreCase("D")) {
            return "display";
        } else if (option.equalsIgnoreCase("P")) {
            return "purchase";
        } else if (option.equalsIgnoreCase("E")) {
            return "exit";
        } else if (option.equalsIgnoreCase("S")) {
            return "sales report";
        } else {
            return "";
        }
    }

    public static String getPurchaseMenuOption() {
            String selectedOption = scanner.nextLine();
            String option = selectedOption.trim();

            if (option.equalsIgnoreCase("M")) {
                return "Feed Money";
            } else if (option.equalsIgnoreCase("S")) {
                return "Select Item";
            } else if (option.equalsIgnoreCase("F")) {
                return "Finish";
            } else {
                return "";
            }
    }

    public static BigDecimal getMoneyOption() {
        String moneyIn = scanner.nextLine();
        BigDecimal dollarsIn = new BigDecimal(moneyIn);
        BigDecimal zero = new BigDecimal(0);
        BigDecimal one = new BigDecimal(1);
        BigDecimal five = new BigDecimal(5);
        BigDecimal ten = new BigDecimal(10);
        BigDecimal twenty = new BigDecimal(20);
        if(dollarsIn.compareTo(one) == 0) {
            return one;
        } else if(dollarsIn.compareTo(five) == 0) {
            return five;
        } else if(dollarsIn.compareTo(ten) == 0) {
            return ten;
        } else if(dollarsIn.compareTo(twenty) == 0) {
            return twenty;
        } else {
            System.out.println("Invalid amount. Please select $1, $5, $10, $20");
            return zero;
        }
    }

    public static String getItemOption (){
        String chosenItem = scanner.nextLine();
        return chosenItem;
    }
}
