package model.tile;

// A class representing the Air tile on the game map. This has no special interaction.
public class Air extends MultipleTile {
    private static final String AIR_TILE_SYMBOL = " ";
    public static final String AIR_TILE_IMAGE_SOURCE = "./data/graphics/floor.jpg";

    // EFFECTS: displays the symbol for the air tile + an optional string
    public String display(String s) {
        return super.display(AIR_TILE_SYMBOL, s);
    }
}
