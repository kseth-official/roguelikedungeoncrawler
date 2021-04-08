package model;

import exceptions.CellAtMaximumOrMinimumException;
import model.tile.SmallHealthPotion;
import org.json.JSONObject;
import persistence.Writable;

// A class to represent the player's inventory.
// Each HashSet is a block in the inventory.
public class Inventory implements Writable {
    public static final int CELL_CAPACITY = 5;
    public static final int NUMBER_OF_INVENTORY_CELLS = 4;
    private int smallHealthPotions;

    // EFFECTS: Initializes each inventory cell with zero items.
    public Inventory() {
        this.smallHealthPotions = 0;
    }

    // EFFECTS: Produces the number of SmallHealthPotions in the inventory.
    public int getNumberOfSmallHealthPotions() {
        return smallHealthPotions;
    }


    // EFFECTS: Adds a number of Small Health Potions to the inventory.
    // Throws a CellAtMaximumOrMinimumException if adding the number of Small Health Potions will cause the number
    // of Small Health Potions to be > CELL_CAPACITY
    public void addSmallHealthPotions(int number) throws CellAtMaximumOrMinimumException {
        if (this.smallHealthPotions + number > CELL_CAPACITY) {
            throw new CellAtMaximumOrMinimumException();
        }
        this.smallHealthPotions += number;
    }

    // EFFECTS: Adds a Small Health Potion to the inventory.
    // Throws a CellAtMaximumOrMinimumException if cell is at maximum size already.
    public void addOneSmallHealthPotion() throws CellAtMaximumOrMinimumException {
        if (smallHealthPotionSetIsFull()) {
            throw new CellAtMaximumOrMinimumException();
        }
        ++this.smallHealthPotions;
    }

    // EFFECTS: Subtracts a Small Health Potion to the inventory.
    // Throws a CellAtMaximumOrMinimumException if cell is at minimum size already.
    public void subtractOneSmallHealthPotion() throws CellAtMaximumOrMinimumException {
        if (this.smallHealthPotions <= 0) {
            throw new CellAtMaximumOrMinimumException();
        }
        --this.smallHealthPotions;
    }

    // EFFECTS: Checks if the inventory has at least one Small Health Potion
    public boolean hasAtLeastOneSmallHealthPotion() {
        return this.smallHealthPotions > 0;
    }

    // EFFECTS: Checks whether the Small Health Potions in the cell are at CELL_CAPACITY.
    public boolean smallHealthPotionSetIsFull() {
        return this.smallHealthPotions == CELL_CAPACITY;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("numberOfSmallHealthPotions", smallHealthPotions);
        return json;
    }
}
