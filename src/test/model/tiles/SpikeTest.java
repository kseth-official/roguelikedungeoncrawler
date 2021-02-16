package model.tiles;

import model.Position;
import model.tiles.Spike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for the Spike Class
public class SpikeTest {
    Spike spike;

    @BeforeEach
    public void setup() {
        spike = new Spike();
    }

    @Test
    public void testDisplay() {
        assertEquals(spike.display("hello"), "░hello");
    }

    @Test
    public void testAddPosition() {
        Position temp = new Position(0,0);
        spike.addPosition(temp);
        assertTrue(spike.getPositionSet().contains(temp));
    }
}