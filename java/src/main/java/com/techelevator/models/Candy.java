package com.techelevator.models;

import java.math.BigDecimal;

public class Candy extends Items {

    public Candy(String name, BigDecimal price, String slotNumber, String dispenseMessage) {
        super(name, price, slotNumber, dispenseMessage);
    }
}
