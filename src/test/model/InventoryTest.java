package model;

import exceptions.CellAtMaximumOrMinimumException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryTest {
    Inventory inventory;

    @BeforeEach
    void setup() {
        inventory = new Inventory();
    }

    @Test
    void testConstructor() {
        assertEquals(inventory.getNumberOfSmallHealthPotions(),0);
    }

    @Test
    void testAddSmallHealthPotions() {
        try {
            // check number of Small Health Potions
            assertEquals(inventory.getNumberOfSmallHealthPotions(),0);
            // add 0 Small Health Potions
            inventory.addSmallHealthPotions(0);
            assertEquals(inventory.getNumberOfSmallHealthPotions(),0);
            // add 5 Small Health Potions
            inventory.addSmallHealthPotions(5);
            assertEquals(inventory.getNumberOfSmallHealthPotions(),5);
            assertTrue(inventory.smallHealthPotionSetIsFull());
            // add 1 more Small Health Potion
            inventory.addSmallHealthPotions(1);
            fail();
        } catch (CellAtMaximumOrMinimumException e) {
            // this means it caught the exception when num(Small Health Potions) went above maximum cell size
        }
    }

    @Test
    void testIsSmallHealthPotionSetFull() {
        try {
            assertFalse(inventory.smallHealthPotionSetIsFull());
            inventory.addSmallHealthPotions(Inventory.CELL_CAPACITY);
            assertTrue(inventory.smallHealthPotionSetIsFull());
        } catch (CellAtMaximumOrMinimumException e) {
            fail(); // this is bad
        }
    }

    @Test
    void testHasAtLeastOneSmallHealthPotion() {
        try {
            assertEquals(inventory.getNumberOfSmallHealthPotions(),0);
            assertFalse(inventory.hasAtLeastOneSmallHealthPotion());
            inventory.addOneSmallHealthPotion();
            assertTrue(inventory.hasAtLeastOneSmallHealthPotion());
            inventory.addOneSmallHealthPotion();
            assertTrue(inventory.hasAtLeastOneSmallHealthPotion());
            inventory.subtractOneSmallHealthPotion();
            assertTrue(inventory.hasAtLeastOneSmallHealthPotion());
        } catch (CellAtMaximumOrMinimumException exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void testAddOneSmallHealthPotionExceptionalInputs() {
        try {
            assertEquals(inventory.getNumberOfSmallHealthPotions(),0);
            inventory.subtractOneSmallHealthPotion();
            fail("Did not expect to reach this line!");
        } catch (CellAtMaximumOrMinimumException exception) {
            // this is good
        }
    }



    @Test
    void testSubtractOneSmallHealthPotionExceptionalInputs() {
        try {
            assertEquals(inventory.getNumberOfSmallHealthPotions(),0);
            inventory.addSmallHealthPotions(Inventory.CELL_CAPACITY);
            inventory.addOneSmallHealthPotion();
            fail("Did not expect to reach this line!");
        } catch (CellAtMaximumOrMinimumException exception) {
            // this is good
        }
    }
}
