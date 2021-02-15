package model;

import java.util.Objects;

// Represents the (x,y) positions of members on the map
public class Position {
    private int abscissaCoordinate;
    private int ordinateCoordinate;

    // EFFECTS: instantiates a position allowing later position assignment
    public Position() {
    }

    // MODIFIES: this
    // EFFECTS: instantiates the position object with initial position (x,y)
    public Position(int x, int y) {
        this.abscissaCoordinate = x;
        this.ordinateCoordinate = y;
    }

    public int getX() {
        return abscissaCoordinate;
    }

    public int getY() {
        return ordinateCoordinate;
    }

    // MODIFIES: this
    // EFFECTS: sets the position of the position object
    public void setPosition(int x, int y) {
        this.abscissaCoordinate = x;
        this.ordinateCoordinate = y;
    }

    // EFFECTS: overrides the equals method of the Position HashSet and establishes that 2 positions
    //          are equal if their abscissa and ordinate are equal. This allows the hashset to use
    //          contains to compare for equality of positions as stated above and not for equality
    //          via the position's references.
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return abscissaCoordinate == position.abscissaCoordinate && ordinateCoordinate == position.ordinateCoordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(abscissaCoordinate, ordinateCoordinate);
    }

}
