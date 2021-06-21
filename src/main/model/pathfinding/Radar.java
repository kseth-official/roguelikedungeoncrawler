package model.pathfinding;

import model.Position;

// A class modelling a radar with a source and range.
public class Radar {
    private Position source;
    private int range;

    // EFFECTS: Sets the radar's source position and range.
    public Radar(Position source, int range) {
        this.source = source;
        this.range = range;
    }

    public Position getSource() {
        return this.source;
    }

    public int getRange() {
        return this.range;
    }

    // EFFECTS: Checks if a target position is in the Radar's range.
    public boolean hasDetected(Position target) {
        int radarRightBoundary = this.source.getX() + this.range;
        int radarLeftBoundary  = this.source.getX() - this.range;
        int radarLowerBoundary = this.source.getY() + this.range;
        int radarUpperBoundary = this.source.getY() - this.range;

        int targetX = target.getX();
        int targetY = target.getY();

        boolean withinHorizontalBoundaries = targetX >= radarLeftBoundary && targetX <= radarRightBoundary;
        boolean withinVerticalBoundaries = targetY <= radarLowerBoundary && targetY >= radarUpperBoundary;

        if (withinHorizontalBoundaries && withinVerticalBoundaries) {
            return true;
        }

        return false;
    }
}
