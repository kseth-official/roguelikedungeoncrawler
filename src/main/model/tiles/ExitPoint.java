package model.tiles;

import model.SingleTile;

// A class representing the Exit Point tile on the game map, which the player will interact with to complete the level.
public class ExitPoint extends SingleTile {
    private static final String EXIT_POINT_TILE_SYMBOL = "e";

    // EFFECTS: displays the symbol for the exit tile + an optional string
    public String display(String s) {
        return super.display(EXIT_POINT_TILE_SYMBOL,s);
    }
}
