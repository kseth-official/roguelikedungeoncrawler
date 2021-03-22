package model.tile;

import model.Game;
import model.Position;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for the Spike Class
public class SpikeTest {
    Spike spike;

    @BeforeEach
    public void setup() {
        spike = new Spike();
    }

    @Test
    public void testDisplay() {
        assertEquals(spike.display("hello"), "░hello");
    }

    @Test
    public void testAddPosition() {
        Position temp = new Position(0,0);
        spike.addPosition(temp);
        assertTrue(spike.getPositionSet().contains(temp));
    }

    @Test
    public void testToJson() {
        Game game = new Game();
        JSONObject jsonObject;
        JSONObject otherJsonObject = new JSONObject();

        spike.setPositionSet(game.spike().getPositionSet());

        jsonObject = spike.toJson();
        String jsonObjectString = jsonObject.toString();
        otherJsonObject.put("positions",new JSONArray(spike.getPositionSet()));
        String otherJsonObjectString = otherJsonObject.toString();

        assertTrue(jsonObjectString.equals(otherJsonObjectString));
    }
}