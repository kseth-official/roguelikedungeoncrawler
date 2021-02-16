package model.tiles;

import model.MultipleTile;

// A class representing the Air tile on the map. This has no special interaction.
public class Air extends MultipleTile {
    private static final String AIR_TILE_SYMBOL = " ";

    // EFFECTS: displays the symbol for the air tile + an optional string
    public String display(String s) {
        return super.display(AIR_TILE_SYMBOL, s);
    }
}
