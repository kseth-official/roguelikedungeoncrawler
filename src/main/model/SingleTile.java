package model;


import org.json.JSONObject;

// Represents tiles which are single entities.
// This class holds their position and corresponding operations.
public abstract class SingleTile extends Tile {
    protected Position position = new Position();

    public Position getPosition() {
        return this.position;
    }

    // MODIFIES: this
    // EFFECTS: sets the tile's point position
    public void setPosition(Position p) {
        this.position = p;
    }

    // EFFECTS: creates a new JSONObject for a single tile class object using the tile's position
    @Override
    public JSONObject toJson() {
        return toJson(position.toJson());
    }

    // EFFECTS: creates a JSONObject with a key and single tile object converted to a JSONObject as value
    protected JSONObject toJson(JSONObject jsonObject) {
        JSONObject json = new JSONObject();
        json.put("position", jsonObject);
        return json;
    }
}
