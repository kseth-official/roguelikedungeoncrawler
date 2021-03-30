package model.tile;

import model.Position;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;

// Represents tiles which have multiple positions. This class holds their set and corresponding operations.
public abstract class MultipleTile extends Tile {
    protected HashSet<Position> positionSet = new HashSet<>();

    public HashSet<Position> getPositionSet() {
        return this.positionSet;
    }

    public void setPositionSet(HashSet<Position> positionSet) {
        this.positionSet = positionSet;
    }

    // MODIFIES: this
    // EFFECTS: adds a tile at a given position to the set of tiles
    public void addPosition(Position p) {
        this.positionSet.add(p);
    }

    // EFFECTS: creates a new JSONObject for a multiple tile class object using its position set
    @Override
    public JSONObject toJson() {
        return toJson(new JSONArray(positionSet));
    }

    // EFFECTS: creates a JSONObject with a key and a multiple tile object converted to a JSONArray as value
    protected JSONObject toJson(JSONArray jsonArray) {
        JSONObject json = new JSONObject();
        json.put("positions", jsonArray);
        return json;
    }
}