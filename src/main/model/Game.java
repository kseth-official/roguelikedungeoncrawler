package model;

import exceptions.DistanceNegativeException;
import model.tile.Coin;
import model.tile.*;
import org.json.JSONObject;
import persistence.Writable;
import ui.frames.MainMenu;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

// A class for modeling the Game
public class Game implements Writable {
    public static final int GAME_TERMINAL_WIDTH = MainMenu.GAME_TERMINAL_WIDTH;
    public static final int GAME_TERMINAL_HEIGHT = MainMenu.GAME_TERMINAL_HEIGHT;
    private static final int NUMBER_OF_SPIKES = 0;
    public static final int NUMBER_OF_COINS = 15;
    public static final int NUMBER_OF_SMALL_HEALTH_POTIONS = 3;
    public static final int NUMBER_OF_ENEMIES = 3;

    // PROCEDURAL GENERATION
    public static final int MAX_TUNNEL_LENGTH = 20;
    public static final int MAX_TURNS_WHILE_GENERATING = 80;
    public static final Direction[] DIRECTIONS = Direction.values();

    private Direction initialDirection;
    private Direction currentDirection;
    private Direction previousDirection;
    private Position initialPosition;
    private Position currentPosition;

    private Set<Position> gameTiles = new HashSet<>();
    private Set<Position> unoccupiedTiles = new HashSet<>();

    private Air air = new Air();
    private Wall wall = new Wall();
    private EntryPoint entryPoint = new EntryPoint();
    private ExitPoint exitPoint = new ExitPoint();
    private Player player = new Player();
    private Spike spike = new Spike();
    private Coin coin = new Coin();
    private Enemy enemy = new Enemy();
    private SmallHealthPotion smallHealthPotion = new SmallHealthPotion();

    // FIXME: CPSC 210 Checkstyle Issues
    //        LineLength has been moved from under TreeWalker to Checker in checkstyle.xml
    //        based on fix from https://github.com/checkstyle/eclipse-cs/issues/190
    //        Checkstyle is mostly working
    //        However, MethodLength is counting commented out lines.

    // EFFECTS: sets up the initial game map
    public Game() {
        /* PSEUDOCODE FOR PROCEDURAL GENERATION:
        Create a Width x Height map of walls
        DEFINE:
        Position initialPosition = the initial position where map generation will begin
        Position currentPosition = the current position where generation is occurring
        choose a random position on the map and assign it to initialPosition and currentPosition
        remove initialPosition from the wallTilePositionSet
        add initialPosition to the set of unoccupiedTileSet

        DEFINE:
        int maxTunnelLength = maximum length of a tunnel that can be dug within a map
        int maxTurnsWhileGenerating = the maximum turns that can be taken while generating the tunnels
        Direction initialDirection = the initial digging direction
        Direction previousDirection = the previous digging direction
        Direction currentDirection = the current digging direction
        Direction[] directions = the array of all possible directions

        choose a random initial direction for digging from directions and assign it to
        initialDirection, previousDirection, and currentDirection

        for (int i = maxTurnsWhileGenerating; i >= 0; --i) {
            while (true) {
                choose a random number from 0 to maxTunnelLength to dig in the currentDirection = randomTunnelLength
                if (isRandomTunnelLengthValid(initialPosition,
                                              randomTunnelDepth,
                                              MainMenu.gameTerminalWidth,
                                              MainMenu.gameTerminalHeight) ) {
                    break;
                }
            }
            for (int j = 0; j < randomTunnelLength; ++j) {
                currentPosition = generateNewPosition(currentDirection,currentPosition)
                - use it to generate the next position for digging
                Remove the currentPosition from the wallTilePositionSet
                Add the currentPosition to a unoccupiedTilesSet
            }
            previousDirection = currentDirection
            choose a random direction from directions != previousDirection and assign it to currentDirection
        }

        entryPointGeneration
        set the entryPoint position to initialPosition
        remove initialPosition from the unoccupiedTileSet

        playerPositionGeneration
        set the playerPosition to entryPointPosition

        exitPointGeneration
        choose a random position from the unoccupiedTileSet
        set this position as the exitPointPosition
        remove exitPointPosition from the unoccupiedTileSet

        coinPositionGeneration
        for (int i = 0;i < NUMBER_OF_COINS; ++i) {
            choose a position from the unoccupiedTileSet at random
            add that position to the coinSet
            remove that position from the unoccupiedTileSet
        }

        smallHealthPositionGeneration
        for (int i = 0;i < NUMBER_OF_SMALL_HEALTH_POTIONS; ++i) {
            choose a position from the unoccupiedTileSet at random
            add that position to the smallHealthPotionSet
            remove that position from the unoccupiedTileSet
        }

        enemyPositionGeneration
        for (int i = 0;i < NUMBER_OF_ENEMIES; ++i) {
            choose a position from the unoccupiedTileSet at random
            add that position to the enemyPositionSet
            remove that position from the unoccupiedTileSet
        }

        spikePositionGeneration
        for (int i = 0; i < NUMBER_OF_SPIKES; ++i) {
            choose a position from the unoccupiedTileSet at random
            add that position to the spikePositionSet
            remove that position from the unoccupiedTileSet
        } */

        procedurallyGenerateMap();
        initializeEntryPoint(null);
        initializePlayer(null);
        initializeExitPoint(null);
        initializeCoins();
        initializeSmallHealthPotions();
        initializeEnemies();
        initializeSpikes();
//        initializeAir();
    }

    // EFFECTS: Procedurally Generates the Map
    public void procedurallyGenerateMap() {
        initializeWalls();
        initialPosition = chooseInitialDiggingPosition();
        currentPosition = new Position(initialPosition);
        wall.getPositionSet().remove(initialPosition);
        unoccupiedTiles.add(initialPosition);

        Random random = new Random();

        int index = Math.abs(random.nextInt()) % 4;
        initialDirection = DIRECTIONS[index];
        previousDirection = DIRECTIONS[index];
        currentDirection = DIRECTIONS[index];

        int randomTunnelLength;

        for (int i = MAX_TURNS_WHILE_GENERATING; i >= 0; --i) {
            while (true) {
                randomTunnelLength = Math.abs(random.nextInt()) % (MAX_TUNNEL_LENGTH + 1);
                if (isRandomTunnelLengthValid(randomTunnelLength)) {
                    break;
                }
            }
            for (int j = 0; j < randomTunnelLength; ++j) {
                currentPosition = currentPosition.generateNewPosition(currentDirection);
                wall.getPositionSet().remove(currentPosition);
                unoccupiedTiles.add(currentPosition);
            }
            previousDirection = DIRECTIONS[currentDirection.ordinal()];

            while (true) {

                index = Math.abs(random.nextInt()) % 4;
                if (index == previousDirection.ordinal()) {
                    continue;
                }
                currentDirection = DIRECTIONS[index];
                break;
            }
        }
    }

    // EFFECTS: Chooses an initial digging position on the map.
    public Position chooseInitialDiggingPosition() {
        Random randomX = new Random();
        Random randomY = new Random();
        return new Position(
                Math.abs(randomX.nextInt()) % (GAME_TERMINAL_WIDTH - 1) + 1,
                Math.abs(randomY.nextInt()) % (GAME_TERMINAL_HEIGHT - 1) + 1
        );
    }

    /* EFFECTS: Returns false if digging a length equal to the randomTunnelLength in the currentDirection pushes the
                miner off the map.
                Also returns false if the randomTunnelLength is negative when it shouldn't be.
                returns true otherwise
     */
    public boolean isRandomTunnelLengthValid(int randomTunnelLength) {
        Position newPosition;
        try {
            newPosition = currentPosition.generateNewPosition(currentDirection,randomTunnelLength);
        } catch (DistanceNegativeException e) {
            // indicates that the tunnel length provided was negative indicating vector information although the length
            // should be a scalar
            return false;
        }
        if (newPosition.getX() > 0
                && newPosition.getX() < GAME_TERMINAL_WIDTH
                && newPosition.getY() > 0
                && newPosition.getY() < GAME_TERMINAL_HEIGHT) {
            return true;
        }
        return false;
    }

    // EFFECTS: initializes a game with all the given objects
    public Game(Air air,
                Wall wall,
                EntryPoint entryPoint,
                ExitPoint exitPoint,
                Player player,
                Spike spike,
                Coin coin,
                Enemy enemy,
                SmallHealthPotion smallHealthPotion) {
        this.air = air;
        this.wall = wall;
        this.entryPoint = entryPoint;
        this.exitPoint = exitPoint;
        this.player = player;
        this.spike = spike;
        this.coin = coin;
        this.enemy = enemy;
        this.smallHealthPotion = smallHealthPotion;
    }

    public Wall wall() {
        return this.wall;
    }

    public EntryPoint entryPoint() {
        return this.entryPoint;
    }

    public ExitPoint exitPoint() {
        return this.exitPoint;
    }

    public Player player() {
        return this.player;
    }

    public Spike spike() {
        return this.spike;
    }

    public Coin coin() {
        return this.coin;
    }

    public Air air() {
        return this.air;
    }

    public Enemy enemy() {
        return this.enemy;
    }

    public SmallHealthPotion smallHealthPotion() {
        return this.smallHealthPotion;
    }

    // TODO: Fix parameter lists for single position initialization methods.
    // MODIFIES: this
    // EFFECTS: sets up the player's position
    public void initializePlayer(Position p) {
        player.setPosition(entryPoint.getPosition());
        unoccupiedTiles.remove(player.getPosition());
    }

    // MODIFIES: this
    // EFFECTS: sets up the Entry Point's position
    public void initializeEntryPoint(Position p) {
        entryPoint.setPosition(initialPosition);
        unoccupiedTiles.remove(entryPoint.getPosition());
    }

    // EFFECTS: Chooses a random position from the unoccupied tiles on the map
    public Position chooseRandomPositionFromUnoccupiedTiles() {
        Random random = new Random();
        Position[] unoccupiedPositionsArray = unoccupiedTiles.toArray(new Position[0]);
        int randomIndex = Math.abs(random.nextInt()) % unoccupiedTiles.size();
        return unoccupiedPositionsArray[randomIndex];
    }

    // MODIFIES: this
    // EFFECTS: sets up the Exit Point's position
    public void initializeExitPoint(Position p) {
        exitPoint.setPosition(chooseRandomPositionFromUnoccupiedTiles());
        unoccupiedTiles.remove(exitPoint.getPosition());
    }

    // MODIFIES: this
    // EFFECTS: sets up the Spike positions
    public void initializeSpikes() {
        Position randomPosition;
        for (int i = 0;i < NUMBER_OF_SPIKES; ++i) {
            randomPosition = chooseRandomPositionFromUnoccupiedTiles();
            spike.getPositionSet().add(randomPosition);
            unoccupiedTiles.remove(randomPosition);
        }
//        for (int i = 0;i < NUMBER_OF_SPIKES;++i) {
//            spike.addPosition(generateUsableMapPosition(gameTiles));
//        }
//
//        gameTiles.addAll(spike.getPositionSet());

//        spike.addPosition(new Position(9, 3));
//        spike.addPosition(new Position(9, 4));
//        spike.addPosition(new Position(9, 8));
//        spike.addPosition(new Position(9, 9));
    }

    // EFFECTS: Generates a random position on the game map that is not already occupied by another tile.
    private Position generateUsableMapPosition(Set<Position> gameTiles) {
        Random random = new Random();
        int abscissa;
        int ordinate;
        Position generatedPosition;

        while (true) {
            abscissa = Math.abs(random.nextInt()) % (MainMenu.GAME_TERMINAL_WIDTH - 2)  + 1;
            ordinate = Math.abs(random.nextInt()) % (MainMenu.GAME_TERMINAL_HEIGHT - 2) + 1;
            generatedPosition = new Position(abscissa,ordinate);
            if (gameTiles.contains(generatedPosition)) {
                continue;
            } else {
                return generatedPosition;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up the Coin positions
    public void initializeCoins() {
        Position randomPosition;
        for (int i = 0;i < NUMBER_OF_COINS; ++i) {
            randomPosition = chooseRandomPositionFromUnoccupiedTiles();
            coin.getPositionSet().add(randomPosition);
            unoccupiedTiles.remove(randomPosition);
        }
//        coin.addPosition(new Position(2, 6));
//        coin.addPosition(new Position(3, 6));
//        coin.addPosition(new Position(4, 6));
//        coin.addPosition(new Position(4, 5));
//        coin.addPosition(new Position(4, 4));
//        coin.addPosition(new Position(4, 3));
//        coin.addPosition(new Position(4, 2));
//        coin.addPosition(new Position(4, 1));
//        coin.addPosition(new Position(5, 1));
//        coin.addPosition(new Position(6, 1));
//        coin.addPosition(new Position(7, 1));
//        coin.addPosition(new Position(7, 2));
//        coin.addPosition(new Position(7, 3));
//        coin.addPosition(new Position(7, 4));
//        coin.addPosition(new Position(7, 5));
//        coin.addPosition(new Position(7, 6));
//        coin.addPosition(new Position(8, 6));
//        coin.addPosition(new Position(9, 6));
//        coin.addPosition(new Position(10, 6));
//
//        gameTiles.addAll(coin.getPositionSet());
    }

    // MODIFIES: this
    // EFFECTS: sets up the Wall positions
    public void initializeWalls() {
        for (int i = 0; i < GAME_TERMINAL_WIDTH; ++i) {
            for (int j = 0; j < GAME_TERMINAL_HEIGHT; ++j) {
                wall.addPosition(new Position(i,j));
            }
        }
//        final int ZERO = 0;
//        final int TWELVE = 12;
//
//        // adding corners
//        wall.addPosition(new Position(ZERO, ZERO));
//        wall.addPosition(new Position(TWELVE, ZERO));
//        wall.addPosition(new Position(ZERO, TWELVE));
//        wall.addPosition(new Position(TWELVE, TWELVE));
//
//        // adding boundaries
//        for (int i = 1; i < 12; ++i) {
//            wall.addPosition(new Position(i, ZERO));
//            wall.addPosition(new Position(i, TWELVE));
//            wall.addPosition(new Position(ZERO, i));
//            if (i == 6) {
//                continue;
//            } else {
//                wall.addPosition(new Position(TWELVE, i));
//            }
//        }
//
//        gameTiles.addAll(wall.getPositionSet());
    }

    // MODIFIES: this
    // EFFECTS: sets up the air positions
    public void initializeAir() {
        air.addPosition(new Position(2,6));
    }

    // MODIFIES: this
    // EFFECTS: sets up the enemy positions
    public void initializeEnemies() {
        Position randomPosition;
        for (int i = 0;i < NUMBER_OF_ENEMIES; ++i) {
            randomPosition = chooseRandomPositionFromUnoccupiedTiles();
            enemy.getPositionSet().add(randomPosition);
            unoccupiedTiles.remove(randomPosition);
        }
//        enemy.addPosition(new Position(5,9));
//        enemy.addPosition(new Position(10,1));
//        enemy.addPosition(new Position(10,10));
//        gameTiles.addAll(enemy.getPositionSet());
    }

    // MODIFIES: this
    // EFFECTS: sets up the enemy positions
    public void initializeSmallHealthPotions() {
        Position randomPosition;
        for (int i = 0;i < NUMBER_OF_SMALL_HEALTH_POTIONS; ++i) {
            randomPosition = chooseRandomPositionFromUnoccupiedTiles();
            smallHealthPotion.getPositionSet().add(randomPosition);
            unoccupiedTiles.remove(randomPosition);
        }
//        smallHealthPotion.addPosition(new Position(11,1));
//        smallHealthPotion.addPosition(new Position(11,2));
//        smallHealthPotion.addPosition(new Position(11,9));
//        smallHealthPotion.addPosition(new Position(11,10));
//        smallHealthPotion.addPosition(new Position(6,6));
//        smallHealthPotion.addPosition(new Position(6,7));
//        smallHealthPotion.addPosition(new Position(6,5));
//
//        gameTiles.addAll(smallHealthPotion.getPositionSet());
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("airTile", air.toJson());
        json.put("wallTile", wall.toJson());
        json.put("entryPointTile", entryPoint.toJson());
        json.put("exitPointTile", exitPoint.toJson());
        json.put("playerTile", player.toJson());
        json.put("spikeTile", spike.toJson());
        json.put("coinTile", coin.toJson());
        json.put("enemyTile", enemy.toJson());
        json.put("smallHealthPotionTile", smallHealthPotion.toJson());
        return json;
    }
}


