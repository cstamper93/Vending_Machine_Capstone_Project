package com.techelevator.models;

import java.math.BigDecimal;

public abstract class Items {

    private final String name;
    private final BigDecimal price;
    private final String slotNumber;
    private int amountLeft;
    private int soldAtDiscount;
    private final String dispenseMessage;

    public Items(String name, String price, String slotNumber, String dispenseMessage) {
        this.name = name;
        this.price = new BigDecimal(price);
        this.slotNumber = slotNumber;
        amountLeft = 6;
        soldAtDiscount = 0;
        this.dispenseMessage = dispenseMessage;

    }

    public String getName() {
        return this.name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public String getSlotNumber() {
        return this.slotNumber;
    }

    public int getAmountLeft() {
        return this.amountLeft;
    }

    public String getDispenseMessage() {
        return this.dispenseMessage;
    }

    public int getSoldAtDiscount() {
        return this.soldAtDiscount;
    }

    public void setSoldAtDiscount(int itemSold) {
        soldAtDiscount += itemSold;
    }

    public void removeItem() {
        amountLeft -= 1;
    }

    public String toString() {
        if(amountLeft < 1) {
            return slotNumber + ": " + name + ", Price: $" + price + " Remaining: NO LONGER AVAILABLE" ;
        }
        return slotNumber + ": " + name + ", Price: $" + price + " Remaining: " + amountLeft;
    }
}
