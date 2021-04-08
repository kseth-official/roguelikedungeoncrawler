package model;

import exceptions.CellAtMaximumOrMinimumException;
import org.json.JSONObject;
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
    void testAddSmallHealthPotionsNonExceptionalInputs() {
        try {
            // check setup
            assertEquals(inventory.getNumberOfSmallHealthPotions(),0);
            // add 0 Small Health Potions (special case)
            inventory.addSmallHealthPotions(0);
            assertEquals(inventory.getNumberOfSmallHealthPotions(),0);
            // add maximum Small Health Potions
            inventory.addSmallHealthPotions(Inventory.CELL_CAPACITY);
            assertEquals(inventory.getNumberOfSmallHealthPotions(),Inventory.CELL_CAPACITY);
            assertTrue(inventory.smallHealthPotionSetIsFull());
        } catch (CellAtMaximumOrMinimumException e) {
            fail("An exception should not have been thrown!");
        }
    }

    @Test
    void testAddSmallHealthPotionsExceptionalInputs() {
        try {
            // check setup
            assertEquals(inventory.getNumberOfSmallHealthPotions(),0);
            // add maximum Small Health Potions + 1
            inventory.addSmallHealthPotions(Inventory.CELL_CAPACITY + 1);
            fail("An exception should have been thrown!");
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
            fail("Exception for adding small health potions thrown!");
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
            fail("Exception should not have been thrown!");
        }
    }

    @Test
    void testSubtractOneSmallHealthPotionExceptionalInputs() {
        try {
            assertEquals(inventory.getNumberOfSmallHealthPotions(),0);
            inventory.subtractOneSmallHealthPotion();
            fail("An exception should have been thrown!");
        } catch (CellAtMaximumOrMinimumException exception) {
            // this is good
        }
    }

    @Test
    void testSubtractOneSmallHealthPotionNonExceptionalInputs() {
        try {
            assertEquals(inventory.getNumberOfSmallHealthPotions(),0);
            inventory.addSmallHealthPotions(1);
            assertEquals(inventory.getNumberOfSmallHealthPotions(),1);
            inventory.subtractOneSmallHealthPotion();
            assertEquals(inventory.getNumberOfSmallHealthPotions(),0);
        } catch (CellAtMaximumOrMinimumException exception) {
            fail("An exception should not have been thrown!");
        }
    }

    @Test
    void testAddOneSmallHealthPotionExceptionalInputs() {
        try {
            assertEquals(inventory.getNumberOfSmallHealthPotions(),0);
            inventory.addSmallHealthPotions(Inventory.CELL_CAPACITY);
            inventory.addOneSmallHealthPotion();
            fail("An exception should have been thrown!");
        } catch (CellAtMaximumOrMinimumException exception) {
            // this is good
        }
    }

    @Test
    void testAddOneSmallHealthPotionNonExceptionalInputs() {
        try {
            assertEquals(inventory.getNumberOfSmallHealthPotions(),0);
            inventory.addSmallHealthPotions(Inventory.CELL_CAPACITY - 1);
            assertEquals(inventory.getNumberOfSmallHealthPotions(),Inventory.CELL_CAPACITY - 1);
            inventory.addOneSmallHealthPotion();
            assertEquals(inventory.getNumberOfSmallHealthPotions(),Inventory.CELL_CAPACITY);
        } catch (CellAtMaximumOrMinimumException exception) {
            fail("An exception should not have been thrown!");
        }
    }

    @Test
    void testToJson() {
        try {
            inventory.addOneSmallHealthPotion();
            assertEquals(inventory.getNumberOfSmallHealthPotions(),1);
            JSONObject testObject = new JSONObject();
            testObject.put("numberOfSmallHealthPotions", inventory.getNumberOfSmallHealthPotions());
            String testString = testObject.toString();
            String toJsonString = inventory.toJson().toString();
            assertTrue(testString.equals(toJsonString));
        } catch (CellAtMaximumOrMinimumException exception) {
            fail("Exception should not have been thrown!");
        }
    }
}
