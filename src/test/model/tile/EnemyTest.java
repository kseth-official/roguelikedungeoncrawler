package model.tile;

import exceptions.DistanceNegativeException;
import model.Direction;
import model.Game;
import model.Position;
import model.pathfinding.Pathfinder;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

// TODO: Update Enemy test to include pathfinding.
// Test class for the Enemy Class
public class EnemyTest {
    Enemy enemy;
    Game game;

    @BeforeEach
    public void setup() {
        enemy = new Enemy();
        game = new Game();
    }

//    @Test
//    public void testConstructor() {
//        assertEquals(enemy.getHealthBar().getHealth(),100);
//    }

    @Test
    public void testDisplay() {
        assertEquals(enemy.display("hello"), "Ohello");
    }

    @Test
    void testMoveEnemiesDoNotMoveEnemyPositionSameAsPlayer() {
        // setup
        Game initialGame = game;
        HashSet<Position> enemyPositions = new HashSet<>();
        enemyPositions.add(game.player().getPosition());
        Enemy enemy = new Enemy();
        enemy.setPositionSet(enemyPositions);
        game.setEnemy(enemy);
        // check setup
        assertEquals(game.enemy(),enemy);
        // call appropriate method
        game.enemy().move(game);
        // check for desired output
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition()));
        assertEquals(game.enemy().getPositionSet().size(),1);
        // check that nothing else changes
        game.setEnemy(enemy);
        assertTrue(game.equals(initialGame));
    }

    @Test
    void testMoveEnemiesDoNotMoveSingleEnemyNextToPlayerLeft() {
        // setup
        Game initialGame = game;
        Wall initialWall = game.wall();
        Spike initialSpike = game.spike();
        Enemy initialEnemy = game.enemy();

        Wall emptyWall = new Wall();
        Spike emptySpike = new Spike();
        game.setWall(emptyWall);
        game.setSpike(emptySpike);
        HashSet<Position> enemyPositions = new HashSet<>();
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.LEFT));
        Enemy enemy = new Enemy();
        enemy.setPositionSet(enemyPositions);
        game.setEnemy(enemy);
        // check setup
        assertEquals(game.wall(),emptyWall);
        assertEquals(game.spike(),emptySpike);
        assertEquals(game.enemy(),enemy);
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.LEFT)));
        // call appropriate method
        game.enemy().move(game);
        // check for desired output
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.LEFT)));
        assertEquals(game.enemy().getPositionSet().size(),1);
        // check that nothing else changes
        game.setWall(initialWall);
        game.setSpike(initialSpike);
        game.setEnemy(initialEnemy);
        assertTrue(game.equals(initialGame));
    }

    @Test
    void testMoveEnemiesDoNotMoveSingleEnemyNextToPlayerRight() {
        // setup
        Game initialGame = game;
        Wall initialWall = game.wall();
        Spike initialSpike = game.spike();
        Enemy initialEnemy = game.enemy();

        Wall emptyWall = new Wall();
        Spike emptySpike = new Spike();
        game.setWall(emptyWall);
        game.setSpike(emptySpike);
        HashSet<Position> enemyPositions = new HashSet<>();
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.RIGHT));
        Enemy enemy = new Enemy();
        enemy.setPositionSet(enemyPositions);
        game.setEnemy(enemy);
        // check setup
        assertEquals(game.wall(),emptyWall);
        assertEquals(game.spike(),emptySpike);
        assertEquals(game.enemy(),enemy);
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.RIGHT)));
        // call appropriate method
        game.enemy().move(game);
        // check for desired output
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.RIGHT)));
        assertEquals(game.enemy().getPositionSet().size(),1);
        // check that nothing else changes
        game.setWall(initialWall);
        game.setSpike(initialSpike);
        game.setEnemy(initialEnemy);
        assertTrue(game.equals(initialGame));
    }

    @Test
    void testMoveEnemiesDoNotMoveSingleEnemyNextToPlayerUp() {
        // setup
        Game initialGame = game;
        Wall initialWall = game.wall();
        Spike initialSpike = game.spike();
        Enemy initialEnemy = game.enemy();

        Wall emptyWall = new Wall();
        Spike emptySpike = new Spike();
        game.setWall(emptyWall);
        game.setSpike(emptySpike);
        HashSet<Position> enemyPositions = new HashSet<>();
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.UP));
        Enemy enemy = new Enemy();
        enemy.setPositionSet(enemyPositions);
        game.setEnemy(enemy);
        // check setup
        assertEquals(game.wall(),emptyWall);
        assertEquals(game.spike(),emptySpike);
        assertEquals(game.enemy(),enemy);
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.UP)));
        // call appropriate method
        game.enemy().move(game);
        // check for desired output
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.UP)));
        assertEquals(game.enemy().getPositionSet().size(),1);
        // check that nothing else changes
        game.setWall(initialWall);
        game.setSpike(initialSpike);
        game.setEnemy(initialEnemy);
        assertTrue(game.equals(initialGame));
    }

    @Test
    void testMoveEnemiesDoNotMoveSingleEnemyNextToPlayerDown() {
        // setup
        Game initialGame = game;
        Wall initialWall = game.wall();
        Spike initialSpike = game.spike();
        Enemy initialEnemy = game.enemy();

        Wall emptyWall = new Wall();
        Spike emptySpike = new Spike();
        game.setWall(emptyWall);
        game.setSpike(emptySpike);
        HashSet<Position> enemyPositions = new HashSet<>();
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.DOWN));
        Enemy enemy = new Enemy();
        enemy.setPositionSet(enemyPositions);
        game.setEnemy(enemy);
        // check setup
        assertEquals(game.wall(),emptyWall);
        assertEquals(game.spike(),emptySpike);
        assertEquals(game.enemy(),enemy);
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.DOWN)));
        // call appropriate method
        game.enemy().move(game);
        // check for desired output
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.DOWN)));
        assertEquals(game.enemy().getPositionSet().size(),1);
        // check that nothing else changes
        game.setWall(initialWall);
        game.setSpike(initialSpike);
        game.setEnemy(initialEnemy);
        assertTrue(game.equals(initialGame));
    }

    @Test
    void testMoveEnemiesDoNotMoveTwoEnemiesNextToPlayerLeftRight() {
        // setup
        Game initialGame = game;
        Wall initialWall = game.wall();
        Spike initialSpike = game.spike();
        Enemy initialEnemy = game.enemy();

        Wall emptyWall = new Wall();
        Spike emptySpike = new Spike();
        game.setWall(emptyWall);
        game.setSpike(emptySpike);
        HashSet<Position> enemyPositions = new HashSet<>();
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.LEFT));
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.RIGHT));
        Enemy enemy = new Enemy();
        enemy.setPositionSet(enemyPositions);
        game.setEnemy(enemy);
        // check setup
        assertEquals(game.wall(),emptyWall);
        assertEquals(game.spike(),emptySpike);
        assertEquals(game.enemy(),enemy);
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.LEFT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.RIGHT)));
        // call appropriate method
        game.enemy().move(game);
        // check for desired output
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.LEFT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.RIGHT)));
        assertEquals(game.enemy().getPositionSet().size(),2);
        // check that nothing else changes
        game.setWall(initialWall);
        game.setSpike(initialSpike);
        game.setEnemy(initialEnemy);
        assertTrue(game.equals(initialGame));
    }

    @Test
    void testMoveEnemiesDoNotMoveTwoEnemiesNextToPlayerLeftUp() {
        // setup
        Game initialGame = game;
        Wall initialWall = game.wall();
        Spike initialSpike = game.spike();
        Enemy initialEnemy = game.enemy();

        Wall emptyWall = new Wall();
        Spike emptySpike = new Spike();
        game.setWall(emptyWall);
        game.setSpike(emptySpike);
        HashSet<Position> enemyPositions = new HashSet<>();
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.LEFT));
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.UP));
        Enemy enemy = new Enemy();
        enemy.setPositionSet(enemyPositions);
        game.setEnemy(enemy);
        // check setup
        assertEquals(game.wall(),emptyWall);
        assertEquals(game.spike(),emptySpike);
        assertEquals(game.enemy(),enemy);
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.LEFT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.UP)));
        // call appropriate method
        game.enemy().move(game);
        // check for desired output
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.LEFT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.UP)));
        assertEquals(game.enemy().getPositionSet().size(),2);
        // check that nothing else changes
        game.setWall(initialWall);
        game.setSpike(initialSpike);
        game.setEnemy(initialEnemy);
        assertTrue(game.equals(initialGame));
    }

    @Test
    void testMoveEnemiesDoNotMoveTwoEnemiesNextToPlayerLeftDown() {
        // setup
        Game initialGame = game;
        Wall initialWall = game.wall();
        Spike initialSpike = game.spike();
        Enemy initialEnemy = game.enemy();

        Wall emptyWall = new Wall();
        Spike emptySpike = new Spike();
        game.setWall(emptyWall);
        game.setSpike(emptySpike);
        HashSet<Position> enemyPositions = new HashSet<>();
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.LEFT));
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.DOWN));
        Enemy enemy = new Enemy();
        enemy.setPositionSet(enemyPositions);
        game.setEnemy(enemy);
        // check setup
        assertEquals(game.wall(),emptyWall);
        assertEquals(game.spike(),emptySpike);
        assertEquals(game.enemy(),enemy);
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.LEFT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.DOWN)));
        // call appropriate method
        game.enemy().move(game);
        // check for desired output
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.LEFT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.DOWN)));
        assertEquals(game.enemy().getPositionSet().size(),2);
        // check that nothing else changes
        game.setWall(initialWall);
        game.setSpike(initialSpike);
        game.setEnemy(initialEnemy);
        assertTrue(game.equals(initialGame));
    }

    @Test
    void testMoveEnemiesDoNotMoveTwoEnemiesNextToPlayerRightUp() {
        // setup
        Game initialGame = game;
        Wall initialWall = game.wall();
        Spike initialSpike = game.spike();
        Enemy initialEnemy = game.enemy();

        Wall emptyWall = new Wall();
        Spike emptySpike = new Spike();
        game.setWall(emptyWall);
        game.setSpike(emptySpike);
        HashSet<Position> enemyPositions = new HashSet<>();
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.RIGHT));
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.UP));
        Enemy enemy = new Enemy();
        enemy.setPositionSet(enemyPositions);
        game.setEnemy(enemy);
        // check setup
        assertEquals(game.wall(),emptyWall);
        assertEquals(game.spike(),emptySpike);
        assertEquals(game.enemy(),enemy);
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.RIGHT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.UP)));
        // call appropriate method
        game.enemy().move(game);
        // check for desired output
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.RIGHT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.UP)));
        assertEquals(game.enemy().getPositionSet().size(),2);
        // check that nothing else changes
        game.setWall(initialWall);
        game.setSpike(initialSpike);
        game.setEnemy(initialEnemy);
        assertTrue(game.equals(initialGame));
    }

    @Test
    void testMoveEnemiesDoNotMoveTwoEnemiesNextToPlayerRightDown() {
        // setup
        Game initialGame = game;
        Wall initialWall = game.wall();
        Spike initialSpike = game.spike();
        Enemy initialEnemy = game.enemy();

        Wall emptyWall = new Wall();
        Spike emptySpike = new Spike();
        game.setWall(emptyWall);
        game.setSpike(emptySpike);
        HashSet<Position> enemyPositions = new HashSet<>();
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.RIGHT));
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.DOWN));
        Enemy enemy = new Enemy();
        enemy.setPositionSet(enemyPositions);
        game.setEnemy(enemy);
        // check setup
        assertEquals(game.wall(),emptyWall);
        assertEquals(game.spike(),emptySpike);
        assertEquals(game.enemy(),enemy);
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.RIGHT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.DOWN)));
        // call appropriate method
        game.enemy().move(game);
        // check for desired output
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.RIGHT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.DOWN)));
        assertEquals(game.enemy().getPositionSet().size(),2);
        // check that nothing else changes
        game.setWall(initialWall);
        game.setSpike(initialSpike);
        game.setEnemy(initialEnemy);
        assertTrue(game.equals(initialGame));
    }

    @Test
    void testMoveEnemiesDoNotMoveTwoEnemiesNextToPlayerUpDown() {
        // setup
        Game initialGame = game;
        Wall initialWall = game.wall();
        Spike initialSpike = game.spike();
        Enemy initialEnemy = game.enemy();

        Wall emptyWall = new Wall();
        Spike emptySpike = new Spike();
        game.setWall(emptyWall);
        game.setSpike(emptySpike);
        HashSet<Position> enemyPositions = new HashSet<>();
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.UP));
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.DOWN));
        Enemy enemy = new Enemy();
        enemy.setPositionSet(enemyPositions);
        game.setEnemy(enemy);
        // check setup
        assertEquals(game.wall(),emptyWall);
        assertEquals(game.spike(),emptySpike);
        assertEquals(game.enemy(),enemy);
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.UP)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.DOWN)));
        // call appropriate method
        game.enemy().move(game);
        // check for desired output
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.UP)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.DOWN)));
        assertEquals(game.enemy().getPositionSet().size(),2);
        // check that nothing else changes
        game.setWall(initialWall);
        game.setSpike(initialSpike);
        game.setEnemy(initialEnemy);
        assertTrue(game.equals(initialGame));
    }

    @Test
    void testMoveEnemiesDoNotMoveThreeEnemiesNextToPlayerLeftRightUp() {
        // setup
        Game initialGame = game;
        Wall initialWall = game.wall();
        Spike initialSpike = game.spike();
        Enemy initialEnemy = game.enemy();

        Wall emptyWall = new Wall();
        Spike emptySpike = new Spike();
        game.setWall(emptyWall);
        game.setSpike(emptySpike);
        HashSet<Position> enemyPositions = new HashSet<>();
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.LEFT));
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.RIGHT));
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.UP));
        Enemy enemy = new Enemy();
        enemy.setPositionSet(enemyPositions);
        game.setEnemy(enemy);
        // check setup
        assertEquals(game.wall(),emptyWall);
        assertEquals(game.spike(),emptySpike);
        assertEquals(game.enemy(),enemy);
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.LEFT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.RIGHT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.UP)));
        // call appropriate method
        game.enemy().move(game);
        // check for desired output
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.LEFT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.RIGHT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.UP)));
        assertEquals(game.enemy().getPositionSet().size(),3);
        // check that nothing else changes
        game.setWall(initialWall);
        game.setSpike(initialSpike);
        game.setEnemy(initialEnemy);
        assertTrue(game.equals(initialGame));
    }

    @Test
    void testMoveEnemiesDoNotMoveThreeEnemiesNextToPlayerRightUpDown() {
        // setup
        Game initialGame = game;
        Wall initialWall = game.wall();
        Spike initialSpike = game.spike();
        Enemy initialEnemy = game.enemy();

        Wall emptyWall = new Wall();
        Spike emptySpike = new Spike();
        game.setWall(emptyWall);
        game.setSpike(emptySpike);
        HashSet<Position> enemyPositions = new HashSet<>();
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.RIGHT));
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.UP));
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.DOWN));
        Enemy enemy = new Enemy();
        enemy.setPositionSet(enemyPositions);
        game.setEnemy(enemy);
        // check setup
        assertEquals(game.wall(),emptyWall);
        assertEquals(game.spike(),emptySpike);
        assertEquals(game.enemy(),enemy);
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.RIGHT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.UP)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.DOWN)));
        // call appropriate method
        game.enemy().move(game);
        // check for desired output
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.RIGHT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.UP)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.DOWN)));
        assertEquals(game.enemy().getPositionSet().size(),3);
        // check that nothing else changes
        game.setWall(initialWall);
        game.setSpike(initialSpike);
        game.setEnemy(initialEnemy);
        assertTrue(game.equals(initialGame));
    }

    @Test
    void testMoveEnemiesDoNotMoveThreeEnemiesNextToPlayerUpDownLeft() {
        // setup
        Game initialGame = game;
        Wall initialWall = game.wall();
        Spike initialSpike = game.spike();
        Enemy initialEnemy = game.enemy();

        Wall emptyWall = new Wall();
        Spike emptySpike = new Spike();
        game.setWall(emptyWall);
        game.setSpike(emptySpike);
        HashSet<Position> enemyPositions = new HashSet<>();
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.UP));
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.DOWN));
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.LEFT));
        Enemy enemy = new Enemy();
        enemy.setPositionSet(enemyPositions);
        game.setEnemy(enemy);
        // check setup
        assertEquals(game.wall(),emptyWall);
        assertEquals(game.spike(),emptySpike);
        assertEquals(game.enemy(),enemy);
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.UP)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.DOWN)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.LEFT)));
        // call appropriate method
        game.enemy().move(game);
        // check for desired output
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.UP)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.DOWN)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.LEFT)));
        assertEquals(game.enemy().getPositionSet().size(),3);
        // check that nothing else changes
        game.setWall(initialWall);
        game.setSpike(initialSpike);
        game.setEnemy(initialEnemy);
        assertTrue(game.equals(initialGame));
    }

    @Test
    void testMoveEnemiesDoNotMoveThreeEnemiesNextToPlayerDownLeftRight() {
        // setup
        Game initialGame = game;
        Wall initialWall = game.wall();
        Spike initialSpike = game.spike();
        Enemy initialEnemy = game.enemy();

        Wall emptyWall = new Wall();
        Spike emptySpike = new Spike();
        game.setWall(emptyWall);
        game.setSpike(emptySpike);
        HashSet<Position> enemyPositions = new HashSet<>();
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.DOWN));
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.LEFT));
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.RIGHT));
        Enemy enemy = new Enemy();
        enemy.setPositionSet(enemyPositions);
        game.setEnemy(enemy);
        // check setup
        assertEquals(game.wall(),emptyWall);
        assertEquals(game.spike(),emptySpike);
        assertEquals(game.enemy(),enemy);
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.DOWN)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.LEFT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.RIGHT)));
        // call appropriate method
        game.enemy().move(game);
        // check for desired output
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.DOWN)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.LEFT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.RIGHT)));
        assertEquals(game.enemy().getPositionSet().size(),3);
        // check that nothing else changes
        game.setWall(initialWall);
        game.setSpike(initialSpike);
        game.setEnemy(initialEnemy);
        assertTrue(game.equals(initialGame));
    }

    @Test
    void testMoveEnemiesDoNotMoveFourEnemiesNextToPlayer() {
        // setup
        Game initialGame = game;
        Wall initialWall = game.wall();
        Spike initialSpike = game.spike();
        Enemy initialEnemy = game.enemy();

        Wall emptyWall = new Wall();
        Spike emptySpike = new Spike();
        game.setWall(emptyWall);
        game.setSpike(emptySpike);
        HashSet<Position> enemyPositions = new HashSet<>();
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.LEFT));
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.RIGHT));
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.UP));
        enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.DOWN));
        Enemy enemy = new Enemy();
        enemy.setPositionSet(enemyPositions);
        game.setEnemy(enemy);
        // check setup
        assertEquals(game.wall(),emptyWall);
        assertEquals(game.spike(),emptySpike);
        assertEquals(game.enemy(),enemy);
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.LEFT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.RIGHT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.UP)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.DOWN)));
        // call appropriate method
        game.enemy().move(game);
        // check for desired output
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.LEFT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.RIGHT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.UP)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.DOWN)));
        assertEquals(game.enemy().getPositionSet().size(),4);
        // check that nothing else changes
        game.setWall(initialWall);
        game.setSpike(initialSpike);
        game.setEnemy(initialEnemy);
        assertTrue(game.equals(initialGame));
    }

    @Test
    void testMoveEnemiesDoNotMoveEnemyPositionsWouldOverlapOtherwise() {
        // setup
        Game initialGame = game;
        Wall initialWall = game.wall();
        Spike initialSpike = game.spike();
        Enemy initialEnemy = game.enemy();

        Wall customWall = new Wall();
        Spike emptySpike = new Spike();

        Position positionLeft = game.player().getPosition().generateNewPosition(Direction.LEFT);
        Position positionRight = game.player().getPosition().generateNewPosition(Direction.RIGHT);
        customWall.addPosition(positionLeft);
        customWall.addPosition(positionRight);
        customWall.addPosition(positionLeft.generateNewPosition(Direction.DOWN));
        customWall.addPosition(positionRight.generateNewPosition(Direction.DOWN));

        game.setWall(customWall);

        game.setSpike(emptySpike);
        HashSet<Position> enemyPositions = new HashSet<>();
        Position positionDown = game.player().getPosition().generateNewPosition(Direction.DOWN);

        enemyPositions.add(positionDown);
        enemyPositions.add(positionDown.generateNewPosition(Direction.DOWN));
        Enemy enemy = new Enemy();
        enemy.setPositionSet(enemyPositions);
        game.setEnemy(enemy);
        // check setup
        assertEquals(game.wall(),customWall);
        assertTrue(game.wall().getPositionSet().contains(positionLeft));
        assertTrue(game.wall().getPositionSet().contains(positionLeft.generateNewPosition(Direction.DOWN)));
        assertTrue(game.wall().getPositionSet().contains(positionRight));
        assertTrue(game.wall().getPositionSet().contains(positionRight.generateNewPosition(Direction.DOWN)));

        assertEquals(game.spike(),emptySpike);
        assertEquals(game.enemy(),enemy);
        assertTrue(game.enemy().getPositionSet().contains(positionDown));
        assertTrue(game.enemy().getPositionSet().contains(positionDown.generateNewPosition(Direction.DOWN)));
        // call appropriate method
        game.enemy().move(game);
        // check for desired output
        assertTrue(game.enemy().getPositionSet().contains(positionDown));
        assertTrue(game.enemy().getPositionSet().contains(positionDown.generateNewPosition(Direction.DOWN)));
        assertEquals(game.enemy().getPositionSet().size(),2);
        // check that nothing else changes
        game.setWall(initialWall);
        game.setSpike(initialSpike);
        game.setEnemy(initialEnemy);
        assertTrue(game.equals(initialGame));
    }

    @Test
    void testMoveEnemiesDoNotMoveNoPathToPlayer() {
        // setup
        Game initialGame = game;
        Wall initialWall = game.wall();
        Spike initialSpike = game.spike();
        Enemy initialEnemy = game.enemy();

        Wall customWall = new Wall();
        Spike emptySpike = new Spike();

        game.setSpike(emptySpike);
        HashSet<Position> enemyPositions = new HashSet<>();
        Position positionDown = game.player().getPosition().generateNewPosition(Direction.DOWN);
        Position positionDownDown = positionDown.generateNewPosition(Direction.DOWN);

        enemyPositions.add(positionDownDown);

        Enemy enemy = new Enemy();
        enemy.setPositionSet(enemyPositions);
        game.setEnemy(enemy);

        customWall.addPosition(positionDown);
        customWall.addPosition(positionDownDown.generateNewPosition(Direction.LEFT));
        customWall.addPosition(positionDownDown.generateNewPosition(Direction.RIGHT));
        customWall.addPosition(positionDownDown.generateNewPosition(Direction.DOWN));
        game.setWall(customWall);

        // check setup
        assertEquals(game.wall(),customWall);
        assertTrue(game.wall().getPositionSet().contains(positionDown));
        assertTrue(game.wall().getPositionSet().contains(positionDownDown.generateNewPosition(Direction.LEFT)));
        assertTrue(game.wall().getPositionSet().contains(positionDownDown.generateNewPosition(Direction.RIGHT)));
        assertTrue(game.wall().getPositionSet().contains(positionDownDown.generateNewPosition(Direction.DOWN)));
        assertEquals(game.spike(),emptySpike);
        assertEquals(game.enemy(),enemy);
        assertTrue(game.enemy().getPositionSet().contains(positionDownDown));
        // call appropriate method
        game.enemy().move(game);
        // check for desired output
        assertTrue(game.enemy().getPositionSet().contains(positionDownDown));
        assertEquals(game.enemy().getPositionSet().size(),1);
        // check that nothing else changes
        game.setWall(initialWall);
        game.setSpike(initialSpike);
        game.setEnemy(initialEnemy);
        assertTrue(game.equals(initialGame));
    }

    @Test
    void testMoveEnemiesMove() {
        // setup
        Game initialGame = game;
        Wall initialWall = game.wall();
        Spike initialSpike = game.spike();
        Enemy initialEnemy = game.enemy();

        Wall emptyWall = new Wall();
        Spike emptySpike = new Spike();
        game.setWall(emptyWall);
        game.setSpike(emptySpike);
        HashSet<Position> enemyPositions = new HashSet<>();
        try {
            enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.LEFT,2));
            enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.RIGHT,2));
            enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.UP,2));
            enemyPositions.add(game.player().getPosition().generateNewPosition(Direction.DOWN,2));
        } catch (DistanceNegativeException e) {
            fail("Distance Should Not Have Been Negative!");
        }

        Enemy enemy = new Enemy();
        enemy.setPositionSet(enemyPositions);
        game.setEnemy(enemy);
        // check setup
        assertEquals(game.wall(),emptyWall);
        assertEquals(game.spike(),emptySpike);
        assertEquals(game.enemy(),enemy);
        try {
            assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                    Direction.LEFT,2)));
            assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                    Direction.RIGHT,2)));
            assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                    Direction.UP,2)));
            assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                    Direction.DOWN,2)));
        } catch (DistanceNegativeException e) {
            fail("Distance Should Not Have Been Negative!");
        }

        // call appropriate method
        game.enemy().move(game);
        // check for desired output
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.LEFT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.RIGHT)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.UP)));
        assertTrue(game.enemy().getPositionSet().contains(game.player().getPosition().generateNewPosition(
                Direction.DOWN)));
        assertEquals(game.enemy().getPositionSet().size(),4);
        // check that nothing else changes
        game.setWall(initialWall);
        game.setSpike(initialSpike);
        game.setEnemy(initialEnemy);
        assertTrue(game.equals(initialGame));
    }


    @Test
    public void testToJson() {
        JSONObject jsonObject;
        JSONObject otherJsonObject = new JSONObject();

        enemy.setPositionSet(game.enemy().getPositionSet());

        jsonObject = enemy.toJson();
        String jsonObjectString = jsonObject.toString();
        otherJsonObject.put("positions",new JSONArray(enemy.getPositionSet()));
        String otherJsonObjectString = otherJsonObject.toString();

        assertTrue(jsonObjectString.equals(otherJsonObjectString));
    }
}