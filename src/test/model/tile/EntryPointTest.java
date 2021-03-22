package model.tile;

import model.Game;
import model.Position;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for the EntryPoint Class
public class EntryPointTest {
    EntryPoint entryPoint;

    @BeforeEach
    public void setup() {
        entryPoint = new EntryPoint();
    }

    @Test
    public void testDisplay() {
        assertEquals(entryPoint.display("hello"), "Ehello");
    }

    @Test
    public void testSetPosition() {
        Position temp = new Position(0,0);
        entryPoint.setPosition(temp);
        assertEquals(entryPoint.getPosition(), temp);
    }

    @Test
    public void testToJson() {
        Game game = new Game();
        JSONObject jsonObject;
        JSONObject otherJsonObject = new JSONObject();

        entryPoint.setPosition(game.entryPoint().getPosition());

        jsonObject = entryPoint.toJson();
        String jsonObjectString = jsonObject.toString();
        otherJsonObject.put("position",entryPoint.getPosition().toJson());
        String otherJsonObjectString = otherJsonObject.toString();

        assertTrue(jsonObjectString.equals(otherJsonObjectString));
    }
}