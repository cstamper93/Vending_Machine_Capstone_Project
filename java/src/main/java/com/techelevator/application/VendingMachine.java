package com.techelevator.application;

import com.techelevator.models.*;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine
{
    DateTimeFormatter targetFormat = DateTimeFormatter.ofPattern("MM-dd-yyyy hh-mm a");
    LocalDateTime timeStamp = LocalDateTime.now();
    String salesReportFileName = "SalesReport" + timeStamp.format(targetFormat) + ".txt";
    Sales sales = new Sales(salesReportFileName);
    Audit audit = new Audit("Audit.txt");
    private List<Items> items = new ArrayList<>();
    private BigDecimal moneyInserted;
    private BigDecimal totalMoney = new BigDecimal(0.00);
    private String chosenItem;
    private int itemsPurchased;
    private static final int SOLD_PER_DISCOUNT = 1;
    private static final BigDecimal DISCOUNT_AMOUNT = new BigDecimal(1.0);
    private BigDecimal zero = new BigDecimal(0);

    public void loadFile() {
        File file = new File("catering1.csv");
        try(Scanner fileScanner = new Scanner(file)) {

            while(fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] lineArr = line.split("\\,");
                BigDecimal priceBD;

                if(lineArr[3].equals("Munchy")) {
                    Items munchy = new Munchy(lineArr[1], priceBD = new BigDecimal(lineArr[2]), lineArr[0], "Munchy, Munchy, so Good!");
                    items.add(munchy);
                } else if(lineArr[3].equals("Candy")) {
                    Items candy = new Candy(lineArr[1], priceBD = new BigDecimal(lineArr[2]), lineArr[0], "Sugar, Sugar, so Sweet!");
                    items.add(candy);
                } else if(lineArr[3].equals("Drink")) {
                    Items drink = new Drink(lineArr[1], priceBD = new BigDecimal(lineArr[2]), lineArr[0], "Drinky, Drinky, Slurp Slurp!");
                    items.add(drink);
                } else {
                    Items gum = new Gum(lineArr[1], priceBD = new BigDecimal(lineArr[2]), lineArr[0], "Chewy, Chewy, Lots O Bubbles!");
                    items.add(gum);
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("Vending machine 404 error.");
        }
    }

    public void run() {
        loadFile();
        while (true) {
            UserOutput.displayHomeScreen();
            UserOutput.displayMainMenu();
            String choice = UserInput.getMainMenuOption();

            if (choice.equals("display")) {
                UserOutput.displayVendingMachineItems(items);
            } else if (choice.equals("purchase")) {
                boolean keepGoing = true;
                while (keepGoing) {
                    UserOutput.displayPurchaseMenu(totalMoney);
                    choice = UserInput.getPurchaseMenuOption();
                    switch (choice) {
                        case "Feed Money":
                            UserOutput.displayMoneyMenu();
                            moneyInserted = UserInput.getMoneyOption();
                            totalMoney = totalMoney.add(moneyInserted);
                            if(moneyInserted.compareTo(zero) != 0) {
                                // Ten spaces for spacing
                                audit.write("MONEY FED:          $"
                                        + String.format("%.2f", moneyInserted) + "    $"
                                        + String.format("%.2f", totalMoney));
                            }
                            break;
                        case "Select Item":
                            UserOutput.displayVendingMachineItems(items);
                            UserOutput.displayItemPrompt();
                            chosenItem = UserInput.getItemOption();
                            getItem(chosenItem);
                            break;
                        case "Finish":
                            if(totalMoney.compareTo(zero) > 0) {
                                getChange();
                            }

                            keepGoing = false;

                    }
                }
            }
            else if (choice.equals("sales report")) {
                    sales.write(items);
            } else if (choice.equals("exit")) {
                    // good bye
                    break;
            }
        }
    }
    public void getItem (String chosenItem){
        for(Items item : items) {
            if(chosenItem.equalsIgnoreCase(item.getSlotNumber()) && item.getAmountLeft() == 0) {
                System.out.println("That item is no longer available, please choose again.");
                return;
            }
            if(chosenItem.equalsIgnoreCase(item.getSlotNumber()) && item.getPrice().compareTo(totalMoney) > 0) {
                System.out.println("You do not have enough money, please try adding more or selecting a cheaper item.\n");
                return;
            }
            if(chosenItem.equalsIgnoreCase(item.getSlotNumber())) {
                itemsPurchased++;
                BigDecimal beforePurchaseTotal = totalMoney;
                if(itemsPurchased % 2 == 0) {
                    item.removeItem();
                    sales.setTotalSales(item.getPrice().subtract(DISCOUNT_AMOUNT));
                    item.setSoldAtDiscount(SOLD_PER_DISCOUNT);
                    totalMoney = totalMoney.subtract(item.getPrice().subtract(DISCOUNT_AMOUNT));
                    System.out.println("Dispensing " + item.getName() + " for $"
                            + (item.getPrice().subtract(DISCOUNT_AMOUNT)) + ", money remaining: $" + totalMoney);
                    System.out.println(item.getDispenseMessage());
                    audit.write(item.getName() + "        " + item.getSlotNumber() + " $"
                            + String.format("%.2f", beforePurchaseTotal) + "    $"
                            + String.format("%.2f", totalMoney));
                    return;
                }
                else {
                    item.removeItem();
                    sales.setTotalSales(item.getPrice());
                    totalMoney = totalMoney.subtract(item.getPrice());
                    System.out.println("Dispensing " + item.getName() + " for $"
                            + item.getPrice() + ", money remaining: $" + String.format("%.2f", totalMoney));
                    System.out.println(item.getDispenseMessage());
                    audit.write(item.getName() + "        " + item.getSlotNumber() + " $"
                            + String.format("%.2f", beforePurchaseTotal) + "    $"
                            + String.format("%.2f", totalMoney));
                    return;
                }
            }
        }
        System.out.println("Invalid choice. Please try again.");
    }

    public void getChange() {
        BigDecimal changeDue = totalMoney;
        int dollars = 0;
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        BigDecimal dollarBD = new BigDecimal(1.00);
        BigDecimal quarterBD = new BigDecimal(0.25);
        BigDecimal dimeBD = new BigDecimal(0.10);
        BigDecimal nickelBD = new BigDecimal(0.05);
        while(changeDue.compareTo(zero) > 0) {
            if (changeDue.compareTo(dollarBD) > -1) {
                while(changeDue.compareTo(dollarBD) > -1) {
                    changeDue = changeDue.subtract(dollarBD);
                    dollars++;
                }
            }
            if(changeDue.compareTo(quarterBD) > -1) {
                while(changeDue.compareTo(quarterBD) > -1) {
                    changeDue = changeDue.subtract(quarterBD);
                    quarters++;
                }
            }
            if(changeDue.compareTo(dimeBD) > -1) {
                while(changeDue.compareTo(dimeBD) > -1) {
                    changeDue = changeDue.subtract(dimeBD);
                    dimes++;
                }
            }
            if(changeDue.compareTo(nickelBD) > -1) {
                while(changeDue.compareTo(nickelBD) > -1) {
                    changeDue = changeDue.subtract(nickelBD);
                    nickels++;
                }
            }
        }
        audit.write("CHANGE GIVEN:          $" + String.format("%.2f", totalMoney) + "    $" + "0.00");
        UserOutput.displayChangeMessage(dollars, quarters, dimes, nickels, changeDue);
        totalMoney = zero;
        moneyInserted = zero;
    }
    public List<Items> getItems() {
        return items;
    }
}


