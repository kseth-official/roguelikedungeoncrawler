package model.tiles;

import model.Game;
import model.tiles.Enemy;
import org.json.JSONObject;
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
    public void testConstructor() {
        assertEquals(enemy.getHealthBar().getHealth(),100);
    }
    @Test
    public void testDisplay() {
        assertEquals(enemy.display("hello"), "Ohello");
    }

    @Test
    public void testToJson() {
        Game game = new Game();
        JSONObject jsonObject;
        JSONObject otherJsonObject = new JSONObject();

        enemy.setPositionSet(game.enemy().getPositionSet());

        jsonObject = enemy.toJson();
        String jsonObjectString = jsonObject.toString();
        otherJsonObject.put("positions",enemy.enemyTilePositionSetToJson());
        String otherJsonObjectString = otherJsonObject.toString();

        assertTrue(jsonObjectString.equals(otherJsonObjectString));
    }
}