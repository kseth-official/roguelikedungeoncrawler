package model.tile;

import model.Position;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashSet;
import java.util.Objects;

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

    // MODIFIES: this
    // EFFECTS: removes a tile at a given position from the set of tiles
    public void removePosition(Position p) {
        this.positionSet.remove(p);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MultipleTile that = (MultipleTile) o;
        return Objects.equals(positionSet, that.positionSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionSet);
    }
}
