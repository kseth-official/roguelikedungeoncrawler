package model.tiles;

import model.SingleTile;

// A class representing the standard game enemy.
// This enemy moves around within a set boundary and causes the player to die upon interaction.
public class Enemy extends SingleTile {
    private static final String ENEMY_CHARACTER_SYMBOL = "O";

    // EFFECTS: displays the symbol for the enemy character + an optional string
    public String display(String s) {
        return super.display(ENEMY_CHARACTER_SYMBOL,s);
    }
}
