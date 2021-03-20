package model.tiles;

import model.Game;
import model.HealthBar;
import model.Position;
import model.tiles.Player;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for the Player Class
public class PlayerTest {
    Player player;
    Game game;

    @BeforeEach
    public void setup() {
        player = new Player();
        game = new Game();
    }

    @Test
    public void testConstructor() {
        assertEquals(player.getWalletBalance(),0);
//        assertEquals(player.getHealthBar().getHealth(), 100);
    }

    @Test
    public void testDisplay() {
        assertEquals(player.display("hello"), "Phello");
    }

    @Test
    public void testSetPosition() {
        Position temp = new Position(0,0);
        player.setPosition(temp);
        assertEquals(player.getPosition(), temp);
    }

    @Test
    public void testPlayerMovementUp() {
        game.player().move("w",game);
        assertTrue(game.player().getPosition().equals(new Position(1,5)));
        game.initializePlayer(new Position(1,1));
        game.player().move("w",game);
        assertTrue(game.player().getPosition().equals(new Position(1,1)));
    }

    @Test
    public void testPlayerMovementRight() {
        game.player().move("d",game);
        assertTrue(game.player().getPosition().equals(new Position(2,6)));
        game.initializePlayer(new Position(11,1));
        game.player().move("d",game);
        assertTrue(game.player().getPosition().equals(new Position(11,1)));
    }

    @Test
    public void testPlayerMovementDown() {
        game.player().move("s",game);
        assertTrue(game.player().getPosition().equals(new Position(1,7)));
        game.initializePlayer(new Position(1,11));
        game.player().move("s",game);
        assertTrue(game.player().getPosition().equals(new Position(1,11)));
    }

    @Test
    public void testPlayerMovementLeft() {
        game.initializePlayer(new Position(2, 6));
        game.player().move("a",game);
        assertTrue(game.player().getPosition().equals(new Position(1,6)));
        game.initializePlayer(new Position(1,1));
        game.player().move("a",game);
        assertTrue(game.player().getPosition().equals(new Position(1,1)));
    }

    @Test
    public void testMoveNotADirection() {
        assertFalse(game.player().move("q",game));
    }

    @Test
    public void testInteract() {
        assertFalse(game.player().interact("i",game));
        game.initializePlayer(new Position(2,2));
        game.initializeExitPoint(new Position(2,2));
        assertTrue(game.player().interact("i",game));
        assertFalse(game.player().interact("k",game));
    }

    @Test
    public void testAddToWallet() {
        game.player().addToWallet(1000);
        assertEquals(game.player().getWalletBalance(),1000);
    }

    @Test
    public void testToJson() {
        JSONObject jsonObject;
        JSONObject otherJsonObject = new JSONObject();

        player.setPosition(game.player().getPosition());
        player.setWalletBalance(game.player().getWalletBalance());

        jsonObject = player.toJson();
        String jsonObjectString = jsonObject.toString();
        otherJsonObject.put("position",player.getPosition().toJson());
        otherJsonObject.put("wallet",player.getWallet().toJson());
        String otherJsonObjectString = otherJsonObject.toString();

        assertTrue(jsonObjectString.equals(otherJsonObjectString));
    }


}