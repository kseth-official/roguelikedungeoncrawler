package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for the Air Class
public class AirTest {
    Air air;

    @BeforeEach
    public void setup() {
        air = new Air();
    }

    @Test
    public void testDisplay() {
        assertEquals(air.display("hello"), " hello");
    }
}
