package model.tiles;

import model.MultipleTile;
import model.Position;
import org.json.JSONArray;
import org.json.JSONObject;

// A class representing the Wall tile on the Map, which will define the boundaries of the map.
public class Wall extends MultipleTile {
    private static final String WALL_TILE_SYMBOL = "W";

    // EFFECTS: displays the symbol for the wall tile + an optional string
    public String display(String s) {
        return super.display(WALL_TILE_SYMBOL,s);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("positions", wallTilePositionSetToJson());
        return json;
    }

    // EFFECTS: returns position set of wall tiles as a JSON array
    public JSONArray wallTilePositionSetToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Position p : positionSet) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
