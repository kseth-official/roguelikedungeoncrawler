package model.tiles;

import model.MultipleTile;
import model.Position;
import org.json.JSONArray;
import org.json.JSONObject;

// A class representing the Air tile on the map. This has no special interaction.
public class Air extends MultipleTile {
    private static final String AIR_TILE_SYMBOL = " ";

    // EFFECTS: displays the symbol for the air tile + an optional string
    public String display(String s) {
        return super.display(AIR_TILE_SYMBOL, s);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("positions", airTilePositionSetToJson());
        return json;
    }

    // EFFECTS: returns position set of air tiles as a JSON array
    public JSONArray airTilePositionSetToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Position p : positionSet) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
