package model.tiles;

import model.MultipleTile;
import model.Position;
import org.json.JSONArray;
import org.json.JSONObject;

// A class modeling the coins a player may pickup during the game.
// The coins are an indicator of the points scored by the player.
public class Coin extends MultipleTile {
    private static final String COIN_SYMBOL = "¤";

    // EFFECTS: displays the symbol for the coin + an optional string
    public String display(String s) {
        return super.display(COIN_SYMBOL,s);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("positions", coinTilePositionSetToJson());
        return json;
    }

    // EFFECTS: returns position set of coin tiles as a JSON array
    private JSONArray coinTilePositionSetToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Position p : positionSet) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
