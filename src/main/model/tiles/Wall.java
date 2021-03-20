package model.tiles;

import model.MultipleTile;
import model.Position;
import org.json.JSONArray;
import org.json.JSONObject;

// A class representing the Wall tile on the Game Map, which will define the boundaries of the map.
public class Wall extends MultipleTile {
    private static final String WALL_TILE_SYMBOL = "W";

    // EFFECTS: displays the symbol for the wall tile + an optional string
    public String display(String s) {
        return super.display(WALL_TILE_SYMBOL,s);
    }
}
