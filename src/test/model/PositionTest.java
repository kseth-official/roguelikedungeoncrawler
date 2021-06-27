package model;

import exceptions.DistanceNegativeException;
import model.Position;
import model.tile.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

// Test class for the Position Class
public class PositionTest {
    Position position;

    @BeforeEach
    void setup() {
        position = new Position(3,4);
    }

    @Test
    void testConstructor() {
        assertEquals(position.getX(),3);
        assertEquals(position.getY(),4);
    }

    @Test
    void testCopyConstructor() {
        Position clone = new Position(position);
        assertFalse(clone == position);
        assertEquals(position.getX(),clone.getX());
        assertEquals(position.getY(),clone.getY());
    }

    @Test
    void testSetPosition() {
        position.setPosition(4,5);
        assertEquals(position.getX(), 4);
        assertEquals(position.getY(), 5);
    }

    @Test
    void testGenerateNewPositionSingleIncrementLeft() {
        // call method & check for required output
        assertTrue(position.generateNewPosition(Direction.LEFT).equals(new Position(2,4)));
        // check if only required output occurs
        assertEquals(position.getX(),3);
        assertEquals(position.getY(),4);
    }

    @Test
    void testGenerateNewPositionSingleIncrementDown() {
        // call method & check for required output
        assertTrue(position.generateNewPosition(Direction.DOWN).equals(new Position(3,5)));
        // check if only required output occurs
        assertEquals(position.getX(),3);
        assertEquals(position.getY(),4);
    }

    @Test
    void testGenerateNewPositionSingleIncrementUp() {
        // call method & check for required output
        assertTrue(position.generateNewPosition(Direction.UP).equals(new Position(3,3)));
        // check if only required output occurs
        assertEquals(position.getX(),3);
        assertEquals(position.getY(),4);
    }

    @Test
    void testGenerateNewPositionSingleIncrementRight() {
        // call method & check for required output
        assertTrue(position.generateNewPosition(Direction.RIGHT).equals(new Position(4,4)));
        // check if only required output occurs
        assertEquals(position.getX(),3);
        assertEquals(position.getY(),4);
    }

    @Test
    void testGenerateNewPositionMultipleIncrementsExceptionalEntriesLeft() {
        int distance = -2;
        // call method
        try {
            position.generateNewPosition(Direction.LEFT,distance);
            fail("A distance negative exception should have been thrown!");
        } catch (DistanceNegativeException e) {
            // this is good
        }
    }

    @Test
    void testGenerateNewPositionMultipleIncrementsExceptionalEntriesRight() {
        int distance = -2;
        // call method
        try {
            position.generateNewPosition(Direction.RIGHT,distance);
            fail("A distance negative exception should have been thrown!");
        } catch (DistanceNegativeException e) {
            // this is good
        }
    }

    @Test
    void testGenerateNewPositionMultipleIncrementsExceptionalEntriesUp() {
        int distance = -2;
        // call method
        try {
            position.generateNewPosition(Direction.UP,distance);
            fail("A distance negative exception should have been thrown!");
        } catch (DistanceNegativeException e) {
            // this is good
        }
    }

    @Test
    void testGenerateNewPositionMultipleIncrementsExceptionalEntriesDown() {
        int distance = -2;
        // call method
        try {
            position.generateNewPosition(Direction.DOWN,distance);
            fail("A distance negative exception should have been thrown!");
        } catch (DistanceNegativeException e) {
            // this is good
        }
    }

    @Test
    void testGenerateNewPositionMultipleIncrementsNonExceptionalEntriesLeft() {
        // check setup
        int distance = 3;
        // call method
        try {
            // call method & check for required output
            assertTrue(position.generateNewPosition(Direction.LEFT,distance).equals(new Position(0,4)));
            // check if only required output occurs
            assertEquals(position.getX(),3);
            assertEquals(position.getY(),4);
        } catch (DistanceNegativeException e) {
            fail("A distance negative exception should not have been thrown!");
        }
    }

    @Test
    void testGenerateNewPositionMultipleIncrementsNonExceptionalEntriesRight() {
        // check setup
        int distance = 3;
        // call method
        try {
            // call method & check for required output
            assertTrue(position.generateNewPosition(Direction.RIGHT,distance).equals(new Position(6,4)));
            // check if only required output occurs
            assertEquals(position.getX(),3);
            assertEquals(position.getY(),4);
        } catch (DistanceNegativeException e) {
            fail("A distance negative exception should not have been thrown!");
        }
    }

    @Test
    void testGenerateNewPositionMultipleIncrementsNonExceptionalEntriesUp() {
        // check setup
        int distance = 3;
        // call method
        try {
            // call method & check for required output
            assertTrue(position.generateNewPosition(Direction.UP,distance).equals(new Position(3,1)));
            // check if only required output occurs
            assertEquals(position.getX(),3);
            assertEquals(position.getY(),4);
        } catch (DistanceNegativeException e) {
            fail("A distance negative exception should not have been thrown!");
        }
    }

    @Test
    void testGenerateNewPositionMultipleIncrementsNonExceptionalEntriesDown() {
        // check setup
        int distance = 3;
        // call method
        try {
            // call method & check for required output
            assertTrue(position.generateNewPosition(Direction.DOWN,distance).equals(new Position(3,7)));
            // check if only required output occurs
            assertEquals(position.getX(),3);
            assertEquals(position.getY(),4);
        } catch (DistanceNegativeException e) {
            fail("A distance negative exception should not have been thrown!");
        }
    }

    @Test
    void testTrivialEquals() {
        Position nullPosition = null;
        Wall wall = new Wall();
        assertTrue(position.equals(position));
        assertFalse(position.equals(nullPosition));
        assertFalse(position.equals(wall));
    }

    @Test
    void testEqualsDifferentReferencesEqualContents() {
        Position other = new Position(3,4);
        assertTrue(position.equals(other));
    }

    @Test
    void testEqualsDifferentReferencesUnequalContents() {
        Position other = new Position(4,5);
        assertFalse(position.equals(other));
    }

    @Test
    void testHashCode() {
        Position other = new Position(3,4);
        assertEquals(position.hashCode(), other.hashCode());
    }
}