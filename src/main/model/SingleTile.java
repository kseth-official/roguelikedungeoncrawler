package model;


// Represents tiles which are single entities.
// This class holds their position and corresponding operations.
public abstract class SingleTile extends Tile {
    protected Position position = new Position();

    public Position getPosition() {
        return this.position;
    }

    // MODIFIES: this
    // EFFECTS: sets the tile's point position
    public void setPosition(Position p) {
        this.position = p;
    }
}
