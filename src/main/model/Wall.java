package model;

import java.util.HashSet;

// A class representing the Wall tile on the Map, which will define the boundaries of the map.
public class Wall {
    private static final String WALL_TILE_SYMBOL = "W";
    private HashSet<Position> positionSet = new HashSet<>();

    // EFFECTS: displays the symbol for the wall tile + an optional string
    public String display(String s) {
        System.out.print(WALL_TILE_SYMBOL + s);
        return WALL_TILE_SYMBOL + s;
    }

    // MODIFIES: this
    // EFFECTS: adds a wall tile to the set of wall tiles at the position
    public void addPosition(Position p) {
        this.positionSet.add(p);
    }

    public HashSet<Position> getPositionSet() {
        return this.positionSet;
    }
}
