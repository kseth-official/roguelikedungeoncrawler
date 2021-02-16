package model.tiles;

import model.tiles.Enemy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for the Enemy Class
public class EnemyTest {
    Enemy enemy;

    @BeforeEach
    public void setup() {
        enemy = new Enemy();
    }

    @Test
    public void testDisplay() {
        assertEquals(enemy.display("hello"), "Ohello");
    }
}