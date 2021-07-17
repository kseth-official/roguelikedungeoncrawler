package model.tile;

import model.Game;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// TODO: Update Enemy test to include pathfinding.
// Test class for the Enemy Class
public class EnemyTest {
    Enemy enemy;

    @BeforeEach
    public void setup() {
        enemy = new Enemy();
    }

//    @Test
//    public void testConstructor() {
//        assertEquals(enemy.getHealthBar().getHealth(),100);
//    }

    @Test
    public void testDisplay() {
        assertEquals(enemy.display("hello"), "Ohello");
    }

    @Test
    public void testMove() {
        // perform correct setup
        Game game = new Game();
        for (int i = 0;i < Game.NUMBER_OF_ENEMIES;++i) {

        }
    }

    @Test
    public void testToJson() {
        Game game = new Game();
        JSONObject jsonObject;
        JSONObject otherJsonObject = new JSONObject();

        enemy.setPositionSet(game.enemy().getPositionSet());

        jsonObject = enemy.toJson();
        String jsonObjectString = jsonObject.toString();
        otherJsonObject.put("positions",new JSONArray(enemy.getPositionSet()));
        String otherJsonObjectString = otherJsonObject.toString();

        assertTrue(jsonObjectString.equals(otherJsonObjectString));
    }
}