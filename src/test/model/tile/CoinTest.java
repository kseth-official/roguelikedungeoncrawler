package model.tile;

import model.Game;
import model.Position;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test Class for the Wall Class
public class CoinTest {
    Coin coin;

    @BeforeEach
    public void setup() {
        coin = new Coin();
    }

    @Test
    public void testDisplay() {
        assertEquals(coin.display("hello"), "¤hello");
    }

    @Test
    public void testAddPosition() {
        Position temp = new Position(0,0);
        coin.addPosition(temp);
        assertTrue(coin.getPositionSet().contains(temp));
    }

    @Test
    void testRemovePosition() {
        // setup
        coin.addPosition(new Position(1,2));
        coin.addPosition(new Position(3,4));
        // call method and check for expected outcome
        coin.removePosition(new Position(1,2));
        assertTrue(coin.getPositionSet().contains(new Position(3,4)));
        assertFalse(coin.getPositionSet().contains(new Position(1,2)));
    }

    @Test
    void testEqualsReferencesSame() {
        coin.addPosition(new Position(4,5));
        assertTrue(coin.equals(coin));
    }

    @Test
    public void testToJson() {
        Game game = new Game();
        JSONObject jsonObject;
        JSONObject otherJsonObject = new JSONObject();

        coin.setPositionSet(game.coin().getPositionSet());

        jsonObject = coin.toJson();
        String jsonObjectString = jsonObject.toString();
        otherJsonObject.put("positions",new JSONArray(coin.getPositionSet()));
        String otherJsonObjectString = otherJsonObject.toString();

        assertTrue(jsonObjectString.equals(otherJsonObjectString));
    }
}