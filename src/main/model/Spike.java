package model;

import java.util.HashSet;

// A class representing the Spike tile on the Map, which will cause the player to die upon interaction.
public class Spike {

    private static final String SPIKE_TILE_SYMBOL = "░";
    private HashSet<Position> positionSet = new HashSet<>();

    // EFFECTS: displays the symbol for the spike tile + an optional string
    public String display(String s) {
        System.out.print(SPIKE_TILE_SYMBOL + s);
        return SPIKE_TILE_SYMBOL + s;
    }

    // MODIFIES: this
    // EFFECTS: adds a spike to the set of positions
    public void addPosition(Position p) {
        this.positionSet.add(p);
    }

    public HashSet<Position> getPositionSet() {
        return this.positionSet;
    }
}
