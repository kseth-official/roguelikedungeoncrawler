package model.tile;

import model.Game;
import model.Position;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test Class for the Wall Class
public class WallTest {
    Wall wall;

    @BeforeEach
    public void setup() {
        wall = new Wall();
    }

    @Test
    public void testDisplay() {
        assertEquals(wall.display("hello"), "Whello");
    }

    @Test
    public void testAddPosition() {
        Position temp = new Position(0,0);
        wall.addPosition(temp);
        assertTrue(wall.getPositionSet().contains(temp));
    }

    @Test
    public void testToJson() {
        Game game = new Game();
        JSONObject jsonObject;
        JSONObject otherJsonObject = new JSONObject();

        wall.setPositionSet(game.wall().getPositionSet());

        jsonObject = wall.toJson();
        String jsonObjectString = jsonObject.toString();
        otherJsonObject.put("positions",new JSONArray(wall.getPositionSet()));
        String otherJsonObjectString = otherJsonObject.toString();
        assertTrue(jsonObjectString.equals(otherJsonObjectString));
    }
}