package model.tile;

import model.HealthBar;

// A class that models a small health potion that a player may pick up and use.
public class SmallHealthPotion extends MultipleTile {
    private static final String SMALL_HEALTH_POTION_SYMBOL = "h";
    public static final String SMALL_HEALTH_POTION_TILE_IMAGE_SOURCE = "./data/graphics/smallHealthPotion.png";
    private static final int HEALTH_VALUE = 25;

    // EFFECTS: Adds 25 health to the user's Health Bar
    public static void use(HealthBar userHealthBar) {
        userHealthBar.addHealth(HEALTH_VALUE);
    }

    // EFFECTS: displays the symbol for the coin + an optional string
    public String display(String s) {
        return super.display(SMALL_HEALTH_POTION_SYMBOL,s);
    }
}
