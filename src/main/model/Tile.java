package model;

import persistence.Json;
import persistence.Writable;

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
public abstract class Tile implements Writable {
    // a field to allow all tiles to access json related functionality for persistence
    protected Json jsonFunctionality;

    // EFFECTS: displays the symbol for a tile + an optional string
    protected String display(String tileSymbol, String s) {
        System.out.print(tileSymbol + s);
        return tileSymbol + s;
    }
}
