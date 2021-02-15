package model;

// A class representing the Entry Point tile on the game map, where the player will begin.
public class EntryPoint {

    private static final String ENTRY_POINT_TILE_SYMBOL = "E";
    private Position position = new Position();

    // EFFECTS: displays the symbol for the exit tile + an optional string
    public String display(String s) {
        System.out.print(ENTRY_POINT_TILE_SYMBOL + s);
        return ENTRY_POINT_TILE_SYMBOL + s;
    }

    // MODIFIES: this
    // EFFECTS: sets the exit point position
    public void setPosition(Position p) {
        this.position = p;
    }

    public Position getPosition() {
        return this.position;
    }
}
