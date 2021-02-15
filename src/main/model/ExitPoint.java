package model;

// A class representing the Exit Point tile on the game map, which the player will interact with to complete the level.
public class ExitPoint {

    private static final String EXIT_POINT_TILE_SYMBOL = "e";
    private Position position = new Position();

    // EFFECTS: displays the symbol for the exit tile + an optional string
    public String display(String s) {
        System.out.print(EXIT_POINT_TILE_SYMBOL + s);
        return EXIT_POINT_TILE_SYMBOL + s;
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
