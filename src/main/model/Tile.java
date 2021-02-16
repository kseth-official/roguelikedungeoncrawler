package model;

/* Represents a tile in the game.
List of tiles:
a) Air
b) Coin
c) Enemy
d) EntryPoint
e) ExitPoint
f) Player
g) Spike
h) Wall
 */
public abstract class Tile {
    // EFFECTS: displays the symbol for a tile + an optional string
    protected String display(String tileSymbol, String s) {
        System.out.print(tileSymbol + s);
        return tileSymbol + s;
    }
}
