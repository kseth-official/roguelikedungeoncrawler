package model.tile;

import exceptions.CellAtMaximumOrMinimumException;
import model.Direction;
import model.Game;
import model.Position;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// TODO: Optimize player-enemy collision and update PlayerTest accordingly.
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
        assertEquals(player.getHealthBar().getHealth(), 100);
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
//
//    @Test
//    public void testPlayerMovementUp() {
//        // move freely
//        game.player().move("w",game);
//        assertTrue(game.player().getPosition().equals(new Position(1,5)));
//        // move into an obstacle (wall)
//        game.initializePlayer(new Position(1,1));
//        game.player().move("w",game);
//        assertTrue(game.player().getPosition().equals(new Position(1,1)));
//        // move into a spike
//        game.initializePlayer(new Position(9,10));
//        game.player().move("w",game);
//        assertTrue(game.player().getPosition().equals(new Position(9,10)));
//        // move into an enemy
//        game.initializePlayer(new Position(3,10));
//        game.player().move("w",game);
//        assertTrue(game.player().getPosition().equals(new Position(3,10)));
//    }
//
//    @Test
//    public void testPlayerMovementRight() {
//        // move freely
//        game.player().move("d",game);
//        assertTrue(game.player().getPosition().equals(new Position(2,6)));
//        // move into wall
//        game.initializePlayer(new Position(11,1));
//        game.player().move("d",game);
//        assertTrue(game.player().getPosition().equals(new Position(11,1)));
//        // move into a spike
//        game.initializePlayer(new Position(8,9));
//        game.player().move("d",game);
//        assertTrue(game.player().getPosition().equals(new Position(8,9)));
//        // move into an enemy
//        game.initializePlayer(new Position(2,9));
//        game.player().move("d",game);
//        assertTrue(game.player().getPosition().equals(new Position(2,9)));
//    }
//
//    @Test
//    public void testPlayerMovementDown() {
//        // move freely
//        game.player().move("s",game);
//        assertTrue(game.player().getPosition().equals(new Position(1,7)));
//        // move into wall
//        game.initializePlayer(new Position(1,11));
//        game.player().move("s",game);
//        assertTrue(game.player().getPosition().equals(new Position(1,11)));
//        // move into a spike
//        game.initializePlayer(new Position(9,7));
//        game.player().move("s",game);
//        assertTrue(game.player().getPosition().equals(new Position(9,7)));
//        // move into an enemy
//        game.initializePlayer(new Position(3,8));
//        game.player().move("s",game);
//        assertTrue(game.player().getPosition().equals(new Position(3,8)));
//    }
//
//    @Test
//    public void testPlayerMovementLeft() {
//        // move freely
//        game.initializePlayer(new Position(2, 6));
//        game.player().move("a",game);
//        assertTrue(game.player().getPosition().equals(new Position(1,6)));
//        // move into wall
//        game.initializePlayer(new Position(1,1));
//        game.player().move("a",game);
//        assertTrue(game.player().getPosition().equals(new Position(1,1)));
//        // move into a spike
//        game.initializePlayer(new Position(10,9));
//        game.player().move("a",game);
//        assertTrue(game.player().getPosition().equals(new Position(10,9)));
//        // move into an enemy
//        game.initializePlayer(new Position(4,9));
//        game.player().move("a",game);
//        assertTrue(game.player().getPosition().equals(new Position(4,9)));
//    }
//
//    @Test
//    public void testMoveNotADirection() {
//        assertFalse(game.player().move("q",game));
//    }
//

    @Test
    void testInteractTrueOutcomePlayerOnExitPoint() {
        // setup
        Player initialPlayer = new Player();
        initialPlayer.setPosition(new Position(player.getPosition()));
        initialPlayer.getHealthBar().setHealth(player.getHealthBar().getHealth());
        initialPlayer.setWalletBalance(player.getWalletBalance());

        player.setPosition(game.exitPoint().getPosition());
        // check setup
        assertTrue(player.getPosition().equals(game.exitPoint().getPosition()));
        // call appropriate method and check for required output
        assertTrue(player.interact("e",game));
        // see that nothing else changes
        player.setPosition(new Position(0,0));
        assertTrue(initialPlayer.getPosition().equals(player.getPosition()));
        assertEquals(initialPlayer.getHealthBar().getHealth(),player.getHealthBar().getHealth());
        assertEquals(
                initialPlayer.getInventory().getNumberOfSmallHealthPotions(),
                player.getInventory().getNumberOfSmallHealthPotions()
        );
        assertEquals(initialPlayer.getWalletBalance(),player.getWalletBalance());
    }

    @Test
    void testInteractFalseOutcomePlayerNotOnExitPoint() {
        // setup
        Player initialPlayer = new Player();
        initialPlayer.setPosition(new Position(player.getPosition()));
        initialPlayer.getHealthBar().setHealth(player.getHealthBar().getHealth());
        initialPlayer.setWalletBalance(player.getWalletBalance());

        player.setPosition(game.exitPoint().getPosition().generateNewPosition(Direction.UP));
        // check setup
        assertTrue(player.getPosition().equals(game.exitPoint().getPosition().generateNewPosition(Direction.UP)));
        // call appropriate method and check for required output
        assertFalse(player.interact("e",game));
        // see that nothing else changes
        player.setPosition(new Position(0,0));
        assertTrue(initialPlayer.getPosition().equals(player.getPosition()));
        assertEquals(initialPlayer.getHealthBar().getHealth(),player.getHealthBar().getHealth());
        assertEquals(
                initialPlayer.getInventory().getNumberOfSmallHealthPotions(),
                player.getInventory().getNumberOfSmallHealthPotions()
        );
        assertEquals(initialPlayer.getWalletBalance(),player.getWalletBalance());
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
        player.getHealthBar().setHealth(22);
        player.getHealthBar().setIsZero(false);
        try {
            player.getInventory().addSmallHealthPotions(3);
        } catch (CellAtMaximumOrMinimumException e) {
            // this should not happen
            fail();
        }

        jsonObject = player.toJson();
        String jsonObjectString = jsonObject.toString();

        otherJsonObject.put("position",player.getPosition().toJson());
        otherJsonObject.put("wallet",player.getWallet().toJson());
        otherJsonObject.put("healthBar",player.getHealthBar().toJson());
        otherJsonObject.put("inventory",player.getInventory().toJson());

        String otherJsonObjectString = otherJsonObject.toString();

        assertTrue(jsonObjectString.equals(otherJsonObjectString));
    }
}