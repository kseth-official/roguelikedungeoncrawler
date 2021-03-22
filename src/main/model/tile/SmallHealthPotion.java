package model.tile;

import model.HealthBar;
import model.tile.MultipleTile;

// A class that models a small health potion that a player may pick up and use.
public class SmallHealthPotion extends MultipleTile {
    private static final String SMALL_HEALTH_POTION_SYMBOL = "h";
    private static final int HEALTH_VALUE = 25;

    // EFFECTS: Adds 25 health to the user's Health Bar
    public static void use(HealthBar userHealthBar) {
        userHealthBar.add(HEALTH_VALUE);
    }

    // EFFECTS: displays the symbol for the coin + an optional string
    public String display(String s) {
        return super.display(SMALL_HEALTH_POTION_SYMBOL,s);
    }
}
