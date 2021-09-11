package model;

import org.json.JSONObject;
import persistence.Writable;

// An enum to model the direction a tile is facing
public enum Direction implements Writable {
    UP, DOWN, LEFT, RIGHT;

    // EFFECTS: Returns the index (ordinal) of this as a JsonObject.
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("directionIndex",this.ordinal());
        return json;
    }
}