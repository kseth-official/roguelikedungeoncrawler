package model.tiles;

import model.MultipleTile;

// A class representing the Wall tile on the Map, which will define the boundaries of the map.
public class Wall extends MultipleTile {
    private static final String WALL_TILE_SYMBOL = "W";

    // EFFECTS: displays the symbol for the wall tile + an optional string
    public String display(String s) {
        return super.display(WALL_TILE_SYMBOL,s);
    }
}
