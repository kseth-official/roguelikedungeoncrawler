package model;

import exceptions.*;
import model.tile.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.frames.MainMenu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        assertTrue(game.equals(clone));
    }

    @Test
    void testSetFieldsToNull() {
        // check setup
        game.setInitialDiggingDirection(Direction.LEFT);
        game.setInitialDiggingPosition(new Position(0,0));
        assertNotNull(game.getInitialDiggingDirection());
        assertNotNull(game.getInitialDiggingPosition());
        assertNotNull(game.getUnoccupiedTiles());
        assertNotNull(game.air());
        assertNotNull(game.wall());
        assertNotNull(game.entryPoint());
        assertNotNull(game.exitPoint());
        assertNotNull(game.player());
        assertNotNull(game.spike());
        assertNotNull(game.coin());
        assertNotNull(game.enemy());
        assertNotNull(game.smallHealthPotion());

        // call required method
        game.setFieldsToNull();

        // check for expected outcome
        assertNull(game.getInitialDiggingDirection());
        assertNull(game.getInitialDiggingPosition());
        assertNull(game.getUnoccupiedTiles());
        assertNull(game.air());
        assertNull(game.wall());
        assertNull(game.entryPoint());
        assertNull(game.exitPoint());
        assertNull(game.player());
        assertNull(game.spike());
        assertNull(game.coin());
        assertNull(game.enemy());
        assertNull(game.smallHealthPotion());
    }

    @Test
    void testInitializePlayer() {
        // check pre-setup
        assertNotNull(game.player().getPosition());
        // setup
        Position initialPosition = new Position(game.player().getPosition());
        Game initialGame = new Game(game);
        game.getUnoccupiedTiles().add(game.player().getPosition());
        game.player().setPosition(null);
        // check setup
        assertTrue(game.getUnoccupiedTiles().contains(initialPosition));
        assertNull(game.player().getPosition());
        // call appropriate method and check for expected outcome
        game.initializePlayer();
        assertTrue(game.player().getPosition().equals(game.getInitialDiggingPosition()));
        assertTrue(game.getUnoccupiedTiles().contains(game.player().getPosition()));
        // check that nothing else occurs
        game.getUnoccupiedTiles().remove(game.player().getPosition());
        assertTrue(initialGame.equals(game));
    }

    @Test
    void testInitializeEntryPoint() {
        // check pre-setup
        assertNotNull(game.entryPoint().getPosition());
        // setup
        Position initialPosition = new Position(game.entryPoint().getPosition());
        Game initialGame = new Game(game);
        game.getUnoccupiedTiles().add(game.entryPoint().getPosition());
        game.entryPoint().setPosition(null);
        // check setup
        assertTrue(game.getUnoccupiedTiles().contains(initialPosition));
        assertNull(game.entryPoint().getPosition());
        // call appropriate method and check for expected outcome
        game.initializeEntryPoint();
        assertTrue(game.entryPoint().getPosition().equals(game.getInitialDiggingPosition()));
        assertFalse(game.getUnoccupiedTiles().contains(game.entryPoint().getPosition()));
        // check that nothing else occurs
        game.getUnoccupiedTiles().remove(game.entryPoint().getPosition());
        assertTrue(initialGame.equals(game));
    }

    @Test
    void testInitializeExitPointUnoccupiedTilesUnavailable() {
        // save initial information
        Game initialGame = new Game(game);
        Set<Position> initialSet = game.getUnoccupiedTiles();
        // setup unoccupiedTiles
        Set<Position> setWithSomeTiles = new HashSet<>();
        game.setUnoccupiedTiles(setWithSomeTiles);
        try {
            game.initializeExitPoint();
            fail("An exception for exit point allocation when no unoccupied tiles exist was not caught!");
        } catch (NoUnoccupiedTileForExitPointException e) {
            // this is good
        }
        // check that nothing else happens other than the exception
        game.setUnoccupiedTiles(initialSet);
        assertTrue(initialGame.equals(game));
    }

    @Test
    void testInitializeExitPointUnoccupiedTilesAvailable() {
        // save initial information
        Game initialGame = new Game(game);
        Set<Position> initialSet = game.getUnoccupiedTiles();
        ExitPoint initialExitPoint = game.exitPoint();
        // setup unoccupiedTiles
        Set<Position> setWithSomeTiles = new HashSet<>();
        setWithSomeTiles.add(new Position(1,1));
        setWithSomeTiles.add(new Position(2,2));
        setWithSomeTiles.add(new Position(3,3));
        setWithSomeTiles.add(new Position(4,4));
        setWithSomeTiles.add(new Position(5,5));
        game.setUnoccupiedTiles(new HashSet<>(setWithSomeTiles));
        // run tests enough times to occupy all the unoccupied tiles to check that no randomly chosen position was not
        // from the unoccupied tiles
        for (int i = 0; i < 5; ++i) {
            try {
                game.initializeExitPoint();
                assertFalse(game.getUnoccupiedTiles().contains(game.exitPoint().getPosition()));
                assertTrue(setWithSomeTiles.contains(game.exitPoint().getPosition()));
            } catch (NoUnoccupiedTileForExitPointException e) {
                fail("An exception for no unoccupied tiles while allocating ExitPoint position " +
                        "when there are unoccupied tiles was thrown!");
            }
        }
        game.setUnoccupiedTiles(initialSet);
        game.setExitPoint(initialExitPoint);
        game.equals(initialGame);
    }

    @Test
    void testInitializeSpikeUnoccupiedTilesUnavailable() {
        // save initial information
        Game initialGame = new Game(game);
        Set<Position> initialSet = game.getUnoccupiedTiles();
        // setup unoccupiedTiles
        Set<Position> setWithSomeTiles = new HashSet<>();
        game.setUnoccupiedTiles(setWithSomeTiles);
        try {
            game.initializeSpikes();
            fail("An exception for spike tile allocation when no unoccupied tiles exist was not caught!");
        } catch (NoUnoccupiedTileForSpikeException e) {
            // this is good
        }
        // check that nothing else happens other than the exception
        game.setUnoccupiedTiles(initialSet);
        assertTrue(initialGame.equals(game));
    }

    @Test
    void testInitializeSpikeUnoccupiedTilesAvailable() {
        // save initial information
        Game initialGame = new Game(game);
        Set<Position> initialSet = game.getUnoccupiedTiles();
        Spike initialSpike = game.spike();
        // setup unoccupiedTiles
        Set<Position> setWithEnoughUnoccupiedTilesForAllocation = new HashSet<>();
        for (int i = 0; i < Game.NUMBER_OF_SPIKES + 2; ++i) {
            setWithEnoughUnoccupiedTilesForAllocation.add(new Position(i,i));
        }
        game.setUnoccupiedTiles(new HashSet<>(setWithEnoughUnoccupiedTilesForAllocation));
        game.spike().getPositionSet().clear();
        try {
            game.initializeSpikes();
            List<Position> spikePositions = new ArrayList<>(game.spike().getPositionSet());
            for (int i = 0; i < Game.NUMBER_OF_SPIKES; ++i) {
                assertFalse(game.getUnoccupiedTiles().contains(spikePositions.get(i)));
                assertTrue(setWithEnoughUnoccupiedTilesForAllocation.contains(spikePositions.get(i)));
            }
        } catch (NoUnoccupiedTileForSpikeException e) {
            fail("An exception for no unoccupied tiles while allocating a Spike position " +
                        "when there are unoccupied tiles was thrown!");
        }
        game.setUnoccupiedTiles(initialSet);
        game.setSpike(initialSpike);
        game.equals(initialGame);
    }

    @Test
    void testInitializeCoinUnoccupiedTilesUnavailable() {
        // save initial information
        Game initialGame = new Game(game);
        Set<Position> initialSet = game.getUnoccupiedTiles();
        // setup unoccupiedTiles
        Set<Position> setWithSomeTiles = new HashSet<>();
        game.setUnoccupiedTiles(setWithSomeTiles);
        try {
            game.initializeCoins();
            fail("An exception for coin tile allocation when no unoccupied tiles exist was not caught!");
        } catch (NoUnoccupiedTileForCoinException e) {
            // this is good
        }
        // check that nothing else happens other than the exception
        game.setUnoccupiedTiles(initialSet);
        assertTrue(initialGame.equals(game));
    }

    @Test
    void testInitializeCoinUnoccupiedTilesAvailable() {
        // save initial information
        Game initialGame = new Game(game);
        Set<Position> initialSet = game.getUnoccupiedTiles();
        Coin initialCoin = game.coin();
        // setup unoccupiedTiles
        Set<Position> setWithEnoughUnoccupiedTilesForAllocation = new HashSet<>();
        for (int i = 0; i < (Game.NUMBER_OF_COINS + 2); ++i) {
            setWithEnoughUnoccupiedTilesForAllocation.add(new Position(i,i));
        }
        game.setUnoccupiedTiles(new HashSet<>(setWithEnoughUnoccupiedTilesForAllocation));
        game.coin().getPositionSet().clear();
        try {
            game.initializeCoins();
            List<Position> coinPositionList = new ArrayList<>(game.coin().getPositionSet());
            for (int i = 0; i < Game.NUMBER_OF_COINS; ++i) {
                assertTrue(setWithEnoughUnoccupiedTilesForAllocation.contains(coinPositionList.get(i)));
                assertFalse(game.getUnoccupiedTiles().contains(coinPositionList.get(i)));
            }
        } catch (NoUnoccupiedTileForCoinException e) {
            fail("An exception for no unoccupied tiles while allocating a coin position " +
                    "when there are unoccupied tiles was thrown!");
        }
        game.setUnoccupiedTiles(initialSet);
        game.setCoin(initialCoin);
        game.equals(initialGame);
    }

    @Test
    void testInitializeEnemyUnoccupiedTilesUnavailable() {
        // save initial information
        Game initialGame = new Game(game);
        Set<Position> initialSet = game.getUnoccupiedTiles();
        // setup unoccupiedTiles
        Set<Position> setWithSomeTiles = new HashSet<>();
        game.setUnoccupiedTiles(setWithSomeTiles);
        try {
            game.initializeEnemies();
            fail("An exception for coin tile allocation when no unoccupied tiles exist was not caught!");
        } catch (NoUnoccupiedTileForEnemyException e) {
            // this is good
        }
        // check that nothing else happens other than the exception
        game.setUnoccupiedTiles(initialSet);
        assertTrue(initialGame.equals(game));
    }

    @Test
    void testInitializeEnemyUnoccupiedTilesAvailable() {
        // save initial information
        Game initialGame = new Game(game);
        Set<Position> initialSet = game.getUnoccupiedTiles();
        Enemy initialEnemy = game.enemy();
        // setup unoccupiedTiles
        Set<Position> setWithEnoughUnoccupiedTilesForAllocation = new HashSet<>();
        for (int i = 0; i < (Game.NUMBER_OF_ENEMIES + 2); ++i) {
            setWithEnoughUnoccupiedTilesForAllocation.add(new Position(i,i));
        }
        game.setUnoccupiedTiles(new HashSet<>(setWithEnoughUnoccupiedTilesForAllocation));
        game.enemy().getPositionSet().clear();
        // call appropriate method
        try {
            game.initializeEnemies();
            List<Position> enemyPositionList = new ArrayList<>(game.enemy().getPositionSet());
            for (int i = 0; i < Game.NUMBER_OF_ENEMIES; ++i) {
                assertTrue(setWithEnoughUnoccupiedTilesForAllocation.contains(enemyPositionList.get(i)));
                assertFalse(game.getUnoccupiedTiles().contains(enemyPositionList.get(i)));
            }
        } catch (NoUnoccupiedTileForEnemyException e) {
            fail("An exception for no unoccupied tiles while allocating a coin position " +
                    "when there are unoccupied tiles was thrown!");
        }
        game.setUnoccupiedTiles(initialSet);
        game.setEnemy(initialEnemy);
        game.equals(initialGame);
    }

    @Test
    void testInitializeSmallHealthPotionUnoccupiedTilesUnavailable() {
        // save initial information
        Game initialGame = new Game(game);
        Set<Position> initialSet = game.getUnoccupiedTiles();
        // setup unoccupiedTiles
        Set<Position> setWithSomeTiles = new HashSet<>();
        game.setUnoccupiedTiles(setWithSomeTiles);
        try {
            game.initializeSmallHealthPotions();
            fail("An exception for coin tile allocation when no unoccupied tiles exist was not caught!");
        } catch (NoUnoccupiedTileForSmallHealthPotionException e) {
            // this is good
        }
        // check that nothing else happens other than the exception
        game.setUnoccupiedTiles(initialSet);
        assertTrue(initialGame.equals(game));
    }

    @Test
    void testInitializeSmallHealthPotionUnoccupiedTilesAvailable() {
        // save initial information
        Game initialGame = new Game(game);
        Set<Position> initialSet = game.getUnoccupiedTiles();
        SmallHealthPotion initialSmallHealthPotion = game.smallHealthPotion();
        // setup unoccupiedTiles
        Set<Position> setWithEnoughUnoccupiedTilesForAllocation = new HashSet<>();
        for (int i = 0; i < (Game.NUMBER_OF_SMALL_HEALTH_POTIONS + 2); ++i) {
            setWithEnoughUnoccupiedTilesForAllocation.add(new Position(i,i));
        }
        game.setUnoccupiedTiles(new HashSet<>(setWithEnoughUnoccupiedTilesForAllocation));
        game.smallHealthPotion().getPositionSet().clear();
        // call appropriate method
        try {
            game.initializeSmallHealthPotions();
            List<Position> smallHealthPotionPositionList = new ArrayList<>(game.smallHealthPotion().getPositionSet());
            for (int i = 0; i < Game.NUMBER_OF_SMALL_HEALTH_POTIONS; ++i) {
                // check for required output
                assertTrue(setWithEnoughUnoccupiedTilesForAllocation.contains(smallHealthPotionPositionList.get(i)));
                assertFalse(game.getUnoccupiedTiles().contains(smallHealthPotionPositionList.get(i)));
            }
        } catch (NoUnoccupiedTileForSmallHealthPotionException e) {
            fail("An exception for no unoccupied tiles while allocating a coin position " +
                    "when there are unoccupied tiles was thrown!");
        }
        // check only required output occurs
        game.setUnoccupiedTiles(initialSet);
        game.setSmallHealthPotion(initialSmallHealthPotion);
        game.equals(initialGame);
    }

    // TODO: ENEMY AND SMALL HEALTH POTION TESTS

    @Test
    void testInitializeWalls() {
        // save initial game
        Game initialGame = new Game(game);
        // call method
        game.initializeWalls();
        // check for required output, which is the setup
        for (int i = 0; i < gameTerminalWidth; ++i) {
            for (int j = 0; j < gameTerminalHeight; ++j) {
                assertTrue(game.wall().getPositionSet().contains(new Position(i,j)));
            }
        }
        // check that only the required output occurs
        game.setWall(initialGame.wall());
        assertTrue(game.equals(initialGame));
    }

    @Test
    void testConstructor() {
//        testInitializePlayer();
//        testInitializeEntryPoint();
//        testInitializeExitPoint();
//        testInitializeCoins();
//        testInitializeSpikes();
//        testInitializeWalls();
    }

    @Test
    void testChooseInitialDiggingPosition() {
        // save initial information
        Game initialGame = new Game(game);
        // run test multiple times to reduce the probability that generated numbers in a specific instance are not
        // randomly within the range (i.e., reduce the possibility of false positives)
        // NOTE: A unit test cannot be used to check whether randomly generated numbers are within a given range.
        //       A set of statistical tests are required for this, which I don't currently understand.
        for (int i = 0;i < 10000; ++i) {
            // call method and check setup
            Position newInitialPosition = game.chooseInitialDiggingPosition();
            assertNotNull(newInitialPosition);
            // check for required outcome
            boolean isValidXPosition = newInitialPosition.getX() > 0
                    && newInitialPosition.getX() < gameTerminalWidth - 1;
            boolean isValidYPosition = newInitialPosition.getY() > 0
                    && newInitialPosition.getY() < gameTerminalHeight - 1;
            assertTrue(isValidXPosition && isValidYPosition);
        }
        // check that only required outcome occurs
        assertTrue(initialGame.equals(game));
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

    @Test
    void testIsRandomTunnelLengthValidTrue() {
        // save initial information
        Game initialGame = new Game(game);
        // perform setup
        int randomTunnelLength = gameTerminalHeight / 8;
        Position currentDiggingPosition = new Position(
                gameTerminalWidth - gameTerminalWidth / 2,
                gameTerminalHeight - gameTerminalHeight / 4);
        Direction currentDiggingDirection = Direction.DOWN;

        // call method & check for required output
        assertTrue(game.isRandomTunnelLengthValid(randomTunnelLength,currentDiggingPosition,currentDiggingDirection));
        // check nothing else has changed
        assertTrue(initialGame.equals(game));
    }

    @Test
    void testIsRandomTunnelLengthValidFalseMinerFallsOffMap() {
        // save initial information
        Game initialGame = new Game(game);
        // perform setup
        int randomTunnelLength = gameTerminalHeight / 2;
        Position currentDiggingPosition = new Position(
                gameTerminalWidth - gameTerminalWidth / 2,
                gameTerminalHeight - gameTerminalHeight / 4);
        Direction currentDiggingDirection = Direction.DOWN;

        // call method & check for required output
        assertFalse(game.isRandomTunnelLengthValid(randomTunnelLength,currentDiggingPosition,currentDiggingDirection));
        // check nothing else has changed
        assertTrue(initialGame.equals(game));
    }

    @Test
    void testIsRandomTunnelLengthValidFalseRandomTunnelLengthIsNegative() {
        // save initial information
        Game initialGame = new Game(game);
        // perform setup
        int randomTunnelLength = -3;
        Position currentDiggingPosition = new Position(
                gameTerminalWidth - gameTerminalWidth / 2,
                gameTerminalHeight - gameTerminalHeight / 4);
        Direction currentDiggingDirection = Direction.DOWN;

        // call method & check for required output
        assertFalse(game.isRandomTunnelLengthValid(randomTunnelLength,currentDiggingPosition,currentDiggingDirection));
        // check nothing else has changed
        assertTrue(initialGame.equals(game));
    }

    @Test
    void testChooseRandomPositionFromUnoccupiedTilesUnoccupiedTilesExist() {
        // save initial information
        Game initialGame = new Game(game);
        // setup unoccupiedTiles
        Set<Position> setWithSomeTiles = new HashSet<>();
        setWithSomeTiles.add(new Position(1,1));
        setWithSomeTiles.add(new Position(2,2));
        setWithSomeTiles.add(new Position(3,3));
        setWithSomeTiles.add(new Position(4,4));
        setWithSomeTiles.add(new Position(5,5));
        game.setUnoccupiedTiles(setWithSomeTiles);
        // run test multiple times to reduce the probability that chosen positions from the unoccupied tiles are truly
        // random or at least different and not the same, i.e., reduce the possibility that the same position is chosen
        // at random.
        // NOTE: A unit test cannot be used to check whether randomly generated positions are within a given plane.
        //       A set of statistical tests are required for this, which I don't currently understand.
        for (int i = 0;i < 10000; ++i) {
            // call method and check setup
            Position randomMapPosition;
            try {
                randomMapPosition = game.chooseRandomPositionFromUnoccupiedTiles();
                assertNotNull(randomMapPosition);
                // check for required outcome
                boolean isValidXPosition = randomMapPosition.getX() > 0
                        && randomMapPosition.getX() < gameTerminalWidth - 1;
                boolean isValidYPosition = randomMapPosition.getY() > 0
                        && randomMapPosition.getY() < gameTerminalHeight - 1;
                assertTrue(isValidXPosition && isValidYPosition);

                assertTrue(setWithSomeTiles.contains(randomMapPosition));
            } catch (NoUnoccupiedTilesException e) {
                fail("An exception for no unoccupied tiles was caught when there are unoccupied tiles!");
            }
        }
        // check that only required outcome occurs
        game.setUnoccupiedTiles(initialGame.getUnoccupiedTiles());
        assertTrue(initialGame.equals(game));
    }

    @Test
    void testChooseRandomPositionFromUnoccupiedTilesNoUnoccupiedTiles() {
        // save initial information
        Game initialGame = new Game(game);
        // setup unoccupiedTiles
        Set<Position> emptySet = new HashSet<>();
        game.setUnoccupiedTiles(emptySet);
        // call method and check setup
        Position randomMapPosition;
        try {
            randomMapPosition = game.chooseRandomPositionFromUnoccupiedTiles();
            fail("An exception for no unoccupied tiles should have been caught!");
        } catch (NoUnoccupiedTilesException e) {
            // this is good
        }
        // check that only required outcome occurs
        game.setUnoccupiedTiles(initialGame.getUnoccupiedTiles());
        assertTrue(initialGame.equals(game));
    }

    @Test
    void testEqualsReferencesSame() {
        // check setup
        assertNotNull(game);
        // call method and check for expected outcome
        assertTrue(game.equals(game));
    }

    @Test
    void testEqualsObjectBeingComparedToIsNull() {
        // perform setup
        Game nullGame = null;
        // check setup
        assertNull(nullGame);
        // call method and check for expected outcome
        assertFalse(game.equals(nullGame));
    }

    @Test
    void testEqualsObjectClassesNotTheSame() {
        Position position = new Position(3,4);
        assertFalse(game.equals(position));
    }
    @Test
    void testEqualsDifferentReferencesSameContents() {
        Game clone = new Game(game);
        assertFalse(game == clone);
        assertTrue(game.equals(clone));
    }

    @Test
    void testEqualsDifferentReferencesDifferentContents() {
        // perform setup
        Game clone = new Game(game);
        // check setup
        assertFalse(game == clone);
        clone.setWall(null);
        assertNull(clone.wall());
        // call method and check for required outcome
        assertFalse(game.equals(clone));
    }

    @Test
    void testHashCode() {
        Game clone = new Game(game);
        assertEquals(game.hashCode(), clone.hashCode());
    }
}