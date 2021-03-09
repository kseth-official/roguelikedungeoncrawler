package model.tiles;

import model.MultipleTile;
import model.Position;
import org.json.JSONArray;
import org.json.JSONObject;

// A class representing the standard game enemy.
// This enemy moves around within a set boundary and causes the player to die upon interaction.
public class Enemy extends MultipleTile {
    private static final String ENEMY_CHARACTER_SYMBOL = "O";

    // EFFECTS: displays the symbol for the enemy character + an optional string
    public String display(String s) {
        return super.display(ENEMY_CHARACTER_SYMBOL,s);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("positions", enemyTilePositionSetToJson());
        return json;
    }

    // EFFECTS: returns position set of enemy tiles as a JSON array
    public JSONArray enemyTilePositionSetToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Position p : positionSet) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}
