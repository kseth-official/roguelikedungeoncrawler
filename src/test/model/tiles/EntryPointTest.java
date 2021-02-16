package model.tiles;

import model.Position;
import model.tiles.EntryPoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for the EntryPoint Class
public class EntryPointTest {
    EntryPoint entryPoint;

    @BeforeEach
    public void setup() {
        entryPoint = new EntryPoint();
    }

    @Test
    public void testDisplay() {
        assertEquals(entryPoint.display("hello"), "Ehello");
    }

    @Test
    public void testSetPosition() {
        Position temp = new Position(0,0);
        entryPoint.setPosition(temp);
        assertEquals(entryPoint.getPosition(), temp);
    }
}