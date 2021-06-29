package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.frames.MainMenu;

import static org.junit.jupiter.api.Assertions.*;

// TODO: Complete procedural generation and update GameTest accordingly.
// Test class for the Game Class
public class GameTest {
    private Game game;
    private final int gameTerminalWidth = MainMenu.GAME_TERMINAL_WIDTH;
    private final int gameTerminalHeight = MainMenu.GAME_TERMINAL_HEIGHT;

    @BeforeEach
    void setup() {
        game = new Game();
    }

    @Test
    void testCopyConstructor() {
        Game clone = new Game(game);
        assertFalse(clone == game);
        // FIXME: Hashcode and equals not implemented for all subclasses of tile.
        //        Needs to be implemented for test to be successful.
//        assertTrue(game.equals(clone));
    }

    @Test
    void testInitializePlayer() {
        assertTrue(game.player().getPosition().equals(game.getInitialDiggingPosition()));
    }

    @Test
    void testInitializeEntryPoint() {
        assertTrue(game.entryPoint().getPosition().equals(game.getInitialDiggingPosition()));
    }

    @Test
    void testInitializeExitPoint() {
        game.initializeExitPoint(new Position(0,0));
        assertEquals(game.exitPoint().getPosition(),new Position(0,0));
    }

    @Test
    void testInitializeSpikes() {
        game.initializeSpikes();
        assertTrue(game.spike().getPositionSet().contains(new Position(9,3)));
        assertTrue(game.spike().getPositionSet().contains(new Position(9,4)));
        assertTrue(game.spike().getPositionSet().contains(new Position(9,8)));
        assertTrue(game.spike().getPositionSet().contains(new Position(9,9)));
    }

    @Test
    void testInitializeCoins() {
        game.initializeCoins();
        assertTrue(game.coin().getPositionSet().contains(new Position(2,6)));
        assertTrue(game.coin().getPositionSet().contains(new Position(3,6)));
        assertTrue(game.coin().getPositionSet().contains(new Position(4,6)));
        assertTrue(game.coin().getPositionSet().contains(new Position(4,5)));
        assertTrue(game.coin().getPositionSet().contains(new Position(4,4)));
        assertTrue(game.coin().getPositionSet().contains(new Position(4,3)));
        assertTrue(game.coin().getPositionSet().contains(new Position(4,2)));
        assertTrue(game.coin().getPositionSet().contains(new Position(4,1)));
        assertTrue(game.coin().getPositionSet().contains(new Position(5,1)));
        assertTrue(game.coin().getPositionSet().contains(new Position(6,1)));
        assertTrue(game.coin().getPositionSet().contains(new Position(7,1)));
        assertTrue(game.coin().getPositionSet().contains(new Position(7,2)));
        assertTrue(game.coin().getPositionSet().contains(new Position(7,3)));
        assertTrue(game.coin().getPositionSet().contains(new Position(7,4)));
        assertTrue(game.coin().getPositionSet().contains(new Position(7,5)));
        assertTrue(game.coin().getPositionSet().contains(new Position(7,6)));
        assertTrue(game.coin().getPositionSet().contains(new Position(8,6)));
        assertTrue(game.coin().getPositionSet().contains(new Position(9,6)));
        assertTrue(game.coin().getPositionSet().contains(new Position(10,6)));
    }

    @Test
    void testInitializeWalls() {
        // check for required output, which is the setup
        for (int i = 0; i < gameTerminalWidth; ++i) {
            for (int j = 0; j < gameTerminalHeight; ++j) {
                assertTrue(game.wall().getPositionSet().contains(new Position(i,j)));
            }
        }
//        assertTrue(game.wall().getPositionSet().contains(new Position(0,0)));
//        assertTrue(game.wall().getPositionSet().contains(new Position(12,0)));
//        assertTrue(game.wall().getPositionSet().contains(new Position(0,12)));
//        assertTrue(game.wall().getPositionSet().contains(new Position(12,12)));
//
//        for (int i = 1; i < 12; ++i) {
//            assertTrue(game.wall().getPositionSet().contains(new Position(i,0)));
//            assertTrue(game.wall().getPositionSet().contains(new Position(i,12)));
//            assertTrue(game.wall().getPositionSet().contains(new Position(0,i)));
//            assertTrue(game.wall().getPositionSet().contains(new Position(12,i)));
//        }
    }

    @Test
    void testConstructor() {
        testInitializePlayer();
        testInitializeEntryPoint();
        testInitializeExitPoint();
        testInitializeCoins();
        testInitializeSpikes();
        testInitializeWalls();
    }

    @Test
    void testChooseInitialDiggingPosition() {
        // save initial information
        Game initialGame = new Game(game);
        // call method and check setup
        Position newInitialPosition = game.chooseInitialDiggingPosition();
        assertNotNull(newInitialPosition);
        // check for required outcome
        boolean isValidXPosition = newInitialPosition.getX() > 0 && newInitialPosition.getX() < gameTerminalWidth - 1;
        boolean isValidYPosition = newInitialPosition.getY() > 0 && newInitialPosition.getY() < gameTerminalHeight - 1;
        assertTrue(isValidXPosition && isValidYPosition);
        // check that only required outcome occurs
        // FIXME: This is related to the previous fix on the hashcode and equals of the Game class.
//        assertTrue(initialGame.equals(game));
    }

    @Test
    void testGetAir() {
        Position pos = new Position(2,3);
        game.air().addPosition(pos);
        assertTrue(game.air().getPositionSet().contains(pos));
    }

    @Test
    void testGetSmallHealthPotion() {
        Position pos = new Position(3,4);
        game.smallHealthPotion().addPosition(pos);
        assertTrue(game.smallHealthPotion().getPositionSet().contains(pos));
    }
}