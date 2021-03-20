package model.tiles;

import model.Position;
import model.SingleTile;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

// A class representing the Entry Point tile on the game map, where the player will begin.
public class EntryPoint extends SingleTile {
    private static final String ENTRY_POINT_TILE_SYMBOL = "E";

    // EFFECTS: displays the symbol for the exit tile + an optional string
    public String display(String s) {
        return super.display(ENTRY_POINT_TILE_SYMBOL,s);
    }
}
