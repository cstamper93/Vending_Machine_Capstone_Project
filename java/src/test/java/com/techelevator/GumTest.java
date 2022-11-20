package com.techelevator;

import com.techelevator.models.Gum;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class GumTest {

    @Test
    public void gum_constructor_should_build_object_with_proper_attributes() {
        BigDecimal priceBD;
       Gum gum = new Gum("DoubleBuble", priceBD = new BigDecimal(3.17), "B1", "Pop!");
        String expectedName = "DoubleBuble";
        String actualName = gum.getName();
        assertEquals(expectedName, actualName);

        BigDecimal expectedPrice = new BigDecimal(3.17);
        BigDecimal actualPrice = gum.getPrice();
        assertEquals(expectedPrice, actualPrice);

        String expectedSlot = "B1";
        String actualSlot = gum.getSlotNumber();
        assertEquals(expectedSlot, actualSlot);

        String expectedMessage = "Pop!";
        String actualMessage = gum.getDispenseMessage();
        assertEquals(expectedMessage, actualMessage);
    }
    
}
