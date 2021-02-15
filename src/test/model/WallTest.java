package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test Class for the Wall Class
public class WallTest {
    Wall wall;

    @BeforeEach
    public void setup() {
        wall = new Wall();
    }

    @Test
    public void testDisplay() {
        assertEquals(wall.display("hello"), "Whello");
    }

    @Test
    public void testAddPosition() {
        Position temp = new Position(0,0);
        wall.addPosition(temp);
        assertTrue(wall.getPositionSet().contains(temp));
    }
}