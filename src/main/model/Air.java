package model;

import java.util.HashSet;

// A class representing the Air tile on the map. This has no special interaction.
public class Air {
    private static final String AIR_TILE_SYMBOL = " ";
    private HashSet<Position> positionSet = new HashSet<>();

    // EFFECTS: displays the symbol for the air tile + an optional string
    public String display(String s) {
        System.out.print(AIR_TILE_SYMBOL + s);
        return AIR_TILE_SYMBOL + s;
    }
}
