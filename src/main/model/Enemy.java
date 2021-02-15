package model;

import javafx.geometry.Pos;

import java.util.HashSet;

// A class representing the standard game enemy.
// This enemy moves around within a set boundary and causes the player to die upon interaction.
public class Enemy {

    private static final String ENEMY_CHARACTER_SYMBOL = "O";
    private Position position = new Position();

    // EFFECTS: displays the symbol for the enemy character + an optional string
    public String display(String s) {
        System.out.print(ENEMY_CHARACTER_SYMBOL + s);
        return ENEMY_CHARACTER_SYMBOL + s;
    }

}
