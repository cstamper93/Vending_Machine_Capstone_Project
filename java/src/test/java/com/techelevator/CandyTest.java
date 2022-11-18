package com.techelevator;

import com.techelevator.models.Candy;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CandyTest {

    @Test
    public void candy_constructor_should_build_object_with_proper_attributes() {
        Candy candy = new Candy("w&w", 1.99, "D3", "Wow!");
        String expectedName = "w&w";
        String actualName = candy.getName();
        assertEquals(expectedName, actualName);

        double expectedPrice = 1.99;
        double actualPrice = candy.getPrice();
        assertEquals(expectedPrice, actualPrice, 0.001);

        String expectedSlot = "D3";
        String actualSlot = candy.getSlotNumber();
        assertEquals(expectedSlot, actualSlot);

        String expectedMessage = "Wow!";
        String actualMessage = candy.getDispenseMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
