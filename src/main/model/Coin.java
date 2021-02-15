package model;

import javafx.geometry.Pos;

import java.util.HashSet;

// A class modeling the coins a player may pickup during the game.
// The coins are an indicator of the points scored by the player.
public class Coin {
    private static final String COIN_SYMBOL = "¤";
    private HashSet<Position> positionSet = new HashSet<>();

    // EFFECTS: displays the symbol for the coin + an optional string
    public String display(String s) {
        System.out.print(COIN_SYMBOL + s);
        return COIN_SYMBOL + s;
    }

    // MODIFIES: this
    // EFFECTS: adds a coin to the set of positions
    public void addPosition(Position p) {
        this.positionSet.add(p);
    }

    public HashSet<Position> getPositionSet() {
        return this.positionSet;
    }

}
