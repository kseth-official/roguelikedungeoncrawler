package model.tile;


import model.Position;
import org.json.JSONObject;

import java.util.Objects;

// Represents tiles which are single entities.
// This class holds their position and corresponding operations.
public abstract class SingleTile extends Tile {
    protected Position position = new Position();

    public Position getPosition() {
        return this.position;
    }

    // MODIFIES: this
    // EFFECTS: sets the tile's point position
    public void setPosition(Position position) {
        this.position = position;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SingleTile that = (SingleTile) o;
        return Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
