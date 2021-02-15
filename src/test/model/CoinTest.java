package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test Class for the Wall Class
public class CoinTest {
    Coin coin;

    @BeforeEach
    public void setup() {
        coin = new Coin();
    }

    @Test
    public void testDisplay() {
        assertEquals(coin.display("hello"), "¤hello");
    }

    @Test
    public void testAddPosition() {
        Position temp = new Position(0,0);
        coin.addPosition(temp);
        assertTrue(coin.getPositionSet().contains(temp));
    }
}