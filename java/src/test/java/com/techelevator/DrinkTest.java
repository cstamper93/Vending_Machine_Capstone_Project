package com.techelevator;

import com.techelevator.models.Candy;
import com.techelevator.models.Drink;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class DrinkTest {

    @Test
    public void drink_constructor_should_build_object_with_proper_attributes() {
        Drink drink = new Drink("Pepsey", "2.50", "A4", "Refreshing!");
        String expectedName = "Pepsey";
        String actualName = drink.getName();
        assertEquals(expectedName, actualName);

        BigDecimal expectedPrice = new BigDecimal("2.50");
        BigDecimal actualPrice = drink.getPrice();
        assertEquals(expectedPrice, actualPrice);

        String expectedSlot = "A4";
        String actualSlot = drink.getSlotNumber();
        assertEquals(expectedSlot, actualSlot);

        String expectedMessage = "Refreshing!";
        String actualMessage = drink.getDispenseMessage();
        assertEquals(expectedMessage, actualMessage);
    }

}
