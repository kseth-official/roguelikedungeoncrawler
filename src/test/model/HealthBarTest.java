package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HealthBarTest {
    HealthBar healthBar;
    final int maxHealth = HealthBar.MAX_HEALTH;
    final int zeroHealth = HealthBar.ZERO_HEALTH;

    @BeforeEach
    public void setup() {
        healthBar = new HealthBar();
    }
    @Test
    public void testConstructor() {
        assertEquals(healthBar.getHealth(),100);
        assertFalse(healthBar.isZero());
    }

    @Test
    public void testAddHealth() {
        // set health to particular value
        // add health that does not require the method to constrain health
        // check if health has been added
        healthBar.set(50);
        healthBar.add(25);
        assertEquals(healthBar.getHealth(),75);

        // set health to a particular value
        // add health that requires the method to constrain health
        // check if health = MAX_HEALTH
        healthBar.set(50);
        healthBar.add(75);
        assertEquals(healthBar.getHealth(),maxHealth);
    }

    @Test
    public void testSubtractHealth() {
        // set health to particular value
        // subtract health that does not require the method to constrain health
        // check if health has been subtracted
        healthBar.set(50);
        healthBar.subtract(25);
        assertEquals(healthBar.getHealth(),25);

        // set health to a particular value
        // subtract health that requires the method to constrain health
        // check if health = ZERO_HEALTH and isZero is true
        healthBar.set(50);
        assertFalse(healthBar.isZero());
        healthBar.subtract(75);
        assertTrue(healthBar.isZero());
    }

    @Test
    void testSetIsZero() {
        assertFalse(healthBar.isZero());
        healthBar.setIsZero(true);
        assertTrue(healthBar.isZero());
    }
}
