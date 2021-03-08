package model;

import org.json.JSONObject;

import java.util.HashSet;

// Represents tiles which have multiple positions. This class holds their set and corresponding operations.
public abstract class MultipleTile extends Tile {
    protected HashSet<Position> positionSet = new HashSet<>();

    // MODIFIES: this
    // EFFECTS: adds a tile at a given position to the set of tiles
    public void addPosition(Position p) {
        this.positionSet.add(p);
    }

    public HashSet<Position> getPositionSet() {
        return this.positionSet;
    }

    public void setPositionSet(HashSet<Position> positionSet) {
        this.positionSet = positionSet;
    }

}
