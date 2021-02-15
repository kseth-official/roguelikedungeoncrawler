package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for the ExitPoint Class
public class ExitPointTest {
    ExitPoint exitPoint;

    @BeforeEach
    public void setup() {
        exitPoint = new ExitPoint();
    }

    @Test
    public void testDisplay() {
        assertEquals(exitPoint.display("hello"), "ehello");
    }

    @Test
    public void testSetPosition() {
        Position temp = new Position(0,0);
        exitPoint.setPosition(temp);
        assertEquals(exitPoint.getPosition(), temp);
    }
}