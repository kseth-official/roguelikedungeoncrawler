package model.pathfinding;

import model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RadarTest {
    Radar radar;
    Position source;
    int range;
    Position target;

    @BeforeEach
    void setup() {
        source = new Position(0,0);
        range = 5;
        radar = new Radar(source,range);
    }

    @Test
    void testConstructor() {
        assertEquals(radar.getSource(),source);
        assertEquals(radar.getRange(),range);
    }

    @Test
    void testHasDetectedFalseBothCoordinatesOutOfBounds() {
        target = new Position(6,6);
        // check setup
        assertEquals(radar.getSource(),source);
        assertEquals(radar.getRange(),range);
        // call specific method
        // check for expected outcome
        assertFalse(radar.hasDetected(target));
        // check if nothing else changes
        assertEquals(radar.getSource(),source);
        assertEquals(radar.getRange(),range);
    }

    @Test
    void testHasDetectedFalseXCoordinateOutOfBounds() {
        target = new Position(6,4);
        // check setup
        assertEquals(radar.getSource(),source);
        assertEquals(radar.getRange(),range);
        // call specific method
        // check for expected outcome
        assertFalse(radar.hasDetected(target));
        // check if nothing else changes
        assertEquals(radar.getSource(),source);
        assertEquals(radar.getRange(),range);
    }

    @Test
    void testHasDetectedFalseYCoordinateOutOfBounds() {
        target = new Position(4,6);
        // check setup
        assertEquals(radar.getSource(),source);
        assertEquals(radar.getRange(),range);
        // call specific method
        // check for expected outcome
        assertFalse(radar.hasDetected(target));
        // check if nothing else changes
        assertEquals(radar.getSource(),source);
        assertEquals(radar.getRange(),range);
    }

    @Test
    void testHasDetectedTrue() {
        target = new Position(4,4);
        // check setup
        assertEquals(radar.getSource(),source);
        assertEquals(radar.getRange(),range);
        // call specific method
        // check for expected outcome
        assertTrue(radar.hasDetected(target));
        // check if nothing else changes
        assertEquals(radar.getSource(),source);
        assertEquals(radar.getRange(),range);
    }
}
