package model;

import org.json.JSONObject;
import persistence.Writable;

import java.io.Serializable;

// a class to represent a health bar
public class HealthBar implements Writable, Serializable {
    public static final int MAX_HEALTH = 100;
    public static final int ZERO_HEALTH = 0;
    private int health;
    private Boolean isZero;

    // EFFECTS: initializes the health bar with full health and sets isZero to false
    public HealthBar() {
        this.health = MAX_HEALTH;
        this.isZero = false;
    }

    // REQUIRES: health > 0
    // EFFECTS: initializes the health bar with health and sets isZero to false
    public HealthBar(int health) {
        this.health = health;
        this.isZero = false;
    }

    public int getHealth() {
        return this.health;
    }

    public Boolean isZero() {
        return this.isZero;
    }

    // MODIFIES: this
    // EFFECTS: Sets whether the health bar is zero or not
    public void setIsZero(Boolean value) {
        this.isZero = value;
    }

    // REQUIRES: value > 0
    // MODIFIES: this
    // EFFECTS: sets the health bar to a particular value
    public void set(int value) {
        this.health = value;
    }

    /* MODIFIES: this
     * EFFECTS: adds health of value to the healthBar
     *          if addition of value causes healthBar to go above MAX_HEALTH
     *              constrains the healthBar such that health = MAX_HEALTH
     */
    public void add(int value) {
        this.health += value;
        if (this.health >= MAX_HEALTH) {
            this.health = MAX_HEALTH;
        }
    }

    /* MODIFIES: this
     * EFFECTS: subtracts health from the healthBar by the given value
     *          if subtraction of health causes healthBar to go below ZERO_HEALTH
     *              constrains the healthBar such that the health = ZERO_HEALTH
     *              sets isZero to true
     */
    public void subtract(int value) {
        this.health -= value;
        if (this.health <= ZERO_HEALTH) {
            this.health = ZERO_HEALTH;
            setIsZero(true);
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("health", health);
        json.put("isZero",isZero);
        return json;
    }
}
