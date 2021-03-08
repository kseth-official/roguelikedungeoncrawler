package model.tiles;

import model.Position;
import model.tiles.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for the Position Class
public class PositionTest {
    Position position;

    @BeforeEach
    public void setup() {
        position = new Position(3,4);
    }

    @Test
    public void testConstructor() {
        assertEquals(position.getX(),3);
        assertEquals(position.getY(),4);
    }

    @Test
    public void testSetPosition() {
        position.setPosition(4,5);
        assertEquals(position.getX(), 4);
        assertEquals(position.getY(), 5);
    }

    @Test
    public void testTrivialEquals() {
        Position nullPosition = null;
        Wall wall = new Wall();
        assertTrue(position.equals(position));
        assertFalse(position.equals(nullPosition));
        assertFalse(position.equals(wall));
    }

    @Test
    void testEquals() {
        Position other = new Position(3,4);
        assertTrue(position.equals(other));
    }

    @Test
    void testHashCode() {
        Position other = new Position(3,4);
        assertEquals(position.hashCode(), other.hashCode());
    }
}