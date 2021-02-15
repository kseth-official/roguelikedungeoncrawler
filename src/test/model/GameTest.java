package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test class for the Game Class
public class GameTest {
    Game game;

    @BeforeEach
    public void setup() {
        game = new Game();
    }

    @Test
    public void testInitializePlayer() {
        game.initializePlayer(new Position(0,0));
        assertEquals(game.player().getPosition(),new Position(0,0));
    }

    @Test
    public void testInitializeEntryPoint() {
        game.initializeEntryPoint(new Position(0,0));
        assertEquals(game.entryPoint().getPosition(),new Position(0,0));
    }

    @Test
    public void testInitializeExitPoint() {
        game.initializeExitPoint(new Position(0,0));
        assertEquals(game.exitPoint().getPosition(),new Position(0,0));
    }

    @Test
    public void testInitializeSpikes() {
        game.initializeSpikes();
        assertTrue(game.spike().getPositionSet().contains(new Position(9,3)));
        assertTrue(game.spike().getPositionSet().contains(new Position(9,4)));
        assertTrue(game.spike().getPositionSet().contains(new Position(9,8)));
        assertTrue(game.spike().getPositionSet().contains(new Position(9,9)));
    }

    @Test
    public void testInitializeCoins() {
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
    public void testInitializeWalls() {
        game.initializeWalls();
        assertTrue(game.wall().getPositionSet().contains(new Position(0,0)));
        assertTrue(game.wall().getPositionSet().contains(new Position(12,0)));
        assertTrue(game.wall().getPositionSet().contains(new Position(0,12)));
        assertTrue(game.wall().getPositionSet().contains(new Position(12,12)));

        for (int i = 1; i < 12; ++i) {
            assertTrue(game.wall().getPositionSet().contains(new Position(i,0)));
            assertTrue(game.wall().getPositionSet().contains(new Position(i,12)));
            assertTrue(game.wall().getPositionSet().contains(new Position(0,i)));
            assertTrue(game.wall().getPositionSet().contains(new Position(12,i)));
        }
    }

    @Test
    public void testConstructor() {
        testInitializePlayer();
        testInitializeEntryPoint();
        testInitializeExitPoint();
        testInitializeCoins();
        testInitializeSpikes();
        testInitializeWalls();
    }
}