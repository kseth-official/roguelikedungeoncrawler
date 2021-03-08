package model.tiles;

import model.MultipleTile;
import model.Position;
import org.json.JSONArray;
import org.json.JSONObject;

// A class representing the Spike tile on the Map, which will cause the player to die upon interaction.
public class Spike extends MultipleTile {
    private static final String SPIKE_TILE_SYMBOL = "░";

    // EFFECTS: displays the symbol for the spike tile + an optional string
    public String display(String s) {
        return super.display(SPIKE_TILE_SYMBOL,s);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("positions", spikeTilePositionSetToJson());
        return json;
    }

    // EFFECTS: returns position set of spike tiles as a JSON array
    public JSONArray spikeTilePositionSetToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Position p : positionSet) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
