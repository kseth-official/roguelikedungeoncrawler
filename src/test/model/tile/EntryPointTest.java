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
    void testSingleTileEqualsReferencesSame() {
        assertTrue(entryPoint.equals(entryPoint));
    }

    @Test
    void testSingleTileEqualsClassesDifferent() {
        // setup
        ExitPoint exitPoint = new ExitPoint();
        // call appropriate method and check for expected outcome
        assertFalse(entryPoint.equals(exitPoint));
    }

    @Test
    void testSingleTileEqualsObjectBeingEquatedToIsNull() {
        EntryPoint entryPoint2 = null;
        assertFalse(entryPoint.equals(entryPoint2));
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