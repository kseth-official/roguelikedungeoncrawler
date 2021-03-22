package model.tile;

import model.Game;
import model.HealthBar;
import model.Position;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test Class for the Wall Class
public class SmallHealthPotionTest {
    SmallHealthPotion smallHealthPotion;

    @BeforeEach
    public void setup() {
        smallHealthPotion = new SmallHealthPotion();
    }

    @Test
    public void testDisplay() {
        assertEquals(smallHealthPotion.display("old"), "hold");
    }

    @Test
    public void testAddPosition() {
        Position temp = new Position(0,0);
        smallHealthPotion.addPosition(temp);
        assertTrue(smallHealthPotion.getPositionSet().contains(temp));
    }

    @Test
    public void testToJson() {
        Game game = new Game();
        JSONObject jsonObject;
        JSONObject otherJsonObject = new JSONObject();

        smallHealthPotion.setPositionSet(game.coin().getPositionSet());

        jsonObject = smallHealthPotion.toJson();
        String jsonObjectString = jsonObject.toString();
        otherJsonObject.put("positions",new JSONArray(smallHealthPotion.getPositionSet()));
        String otherJsonObjectString = otherJsonObject.toString();

        assertTrue(jsonObjectString.equals(otherJsonObjectString));
    }

    @Test
    public void testUse() {
        HealthBar healthBar = new HealthBar(50);
        SmallHealthPotion.use(healthBar);
        assertEquals(healthBar.getHealth(),75);
    }
}