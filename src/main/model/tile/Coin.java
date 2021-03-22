package model.tile;

import model.tile.MultipleTile;

// A class modeling the coins a player may pickup during the game.
// The coins are an indicator of the points scored by the player.
public class Coin extends MultipleTile {
    private static final String COIN_SYMBOL = "¤";

    // EFFECTS: displays the symbol for the coin + an optional string
    public String display(String s) {
        return super.display(COIN_SYMBOL,s);
    }
}
