package model;

import exceptions.*;
import model.tile.Coin;
import model.tile.*;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;
import ui.frames.MainMenu;

import java.io.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
// FIXME: Initial staircase.
// TODO: make walking into health potion the cause for picking up and not walking over and across.
// TODO: make walking into coin the cause for picking up and not walking over and across.
// -T-O-D-O-: Create an empty constructor for game and another method called initializeGame to be called after creating
// an empty game object. This will improve code testability by providing access to a null game object. - Not doing this

// A class for modeling the Game
public class Game implements Writable, Serializable {
    public static final int GAME_TERMINAL_WIDTH = MainMenu.GAME_TERMINAL_WIDTH;
    public static final int GAME_TERMINAL_HEIGHT = MainMenu.GAME_TERMINAL_HEIGHT;
    public static final int NUMBER_OF_SPIKES = 3;
    public static final int NUMBER_OF_COINS = 20;
    public static final int NUMBER_OF_SMALL_HEALTH_POTIONS = 3;
    public static final int NUMBER_OF_ENEMIES = 20;

    // PROCEDURAL GENERATION
    public static final int MAX_TUNNEL_LENGTH = GAME_TERMINAL_WIDTH - 2;
    public static final int MAX_TUNNEL_TURNS = 100;
    public static final Direction[] DIRECTIONS = Direction.values();

    private Direction initialDiggingDirection;
    private Position initialDiggingPosition;

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

    // EFFECTS: Copy constructor for game.
    //          TODO: Throw a new exception after catching IOException or ClassNotFoundException.
    public Game(Game clone) {
//        Copy Constructor Using Java's Inbuilt Serialization
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(bos);
            oos.writeObject(clone);
            oos.flush();
            byte[] bytes = bos.toByteArray();
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bis);
            Game toClone = (Game) ois.readObject();

            this.initialDiggingDirection = toClone.getInitialDiggingDirection();
            this.initialDiggingPosition = toClone.getInitialDiggingPosition();
            this.unoccupiedTiles = toClone.getUnoccupiedTiles();
            this.air = toClone.air();
            this.wall = toClone.wall();
            this.entryPoint = toClone.entryPoint();
            this.exitPoint = toClone.exitPoint();
            this.player = toClone.player();
            this.spike = toClone.spike();
            this.coin = toClone.coin();
            this.enemy = toClone.enemy();
            this.smallHealthPotion = toClone.smallHealthPotion();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.toString());
        }
//        Copy Constructor by creating deep copies of all objects using object copy constructors
//        this.initialDiggingDirection = DIRECTIONS[toClone.getInitialDiggingDirection().ordinal()];
//        this.initialDiggingPosition = new Position(toClone.getInitialDiggingPosition());
//        this.unoccupiedTiles = new HashSet<>(toClone.getUnoccupiedTiles());
//        this.air = new Air(toClone.air());
//        this.wall = new Wall(toClone.wall());
//        this.entryPoint = new EntryPoint(toClone.entryPoint());
//        this.exitPoint = new ExitPoint(toClone.exitPoint());
//        this.player = new Player(toClone.player());
//        this.spike = new Spike(toClone.spike());
//        this.coin = new Coin(toClone.coin());
//        this.enemy = new Enemy(toClone.enemy());
//        this.smallHealthPotion = new SmallHealthPotion(toClone.smallHealthPotion());
    }

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
        initializeEntryPoint();
        initializePlayer();
        try {
            initializeExitPoint();
            initializeCoins();
            initializeSmallHealthPotions();
            initializeEnemies();
            initializeSpikes();
        } catch (NoUnoccupiedTileForExitPointException e) {
            System.out.println("No unoccupied tiles to allot ExitPoint!");
        } catch (NoUnoccupiedTileForCoinException e) {
            System.out.println("No unoccupied tiles to allot a Coin!");
        } catch (NoUnoccupiedTileForSpikeException e) {
            System.out.println("No unoccupied tiles to allot a Spike!");
        } catch (NoUnoccupiedTileForSmallHealthPotionException e) {
            System.out.println("No unoccupied tiles to allot a SmallHealthPotion!");
        } catch (NoUnoccupiedTileForEnemyException e) {
            System.out.println("No unoccupied tiles to allot an Enemy!");
        }
        initializeAir();
    }

    // EFFECTS: initializes a game with all the given objects
    public Game(Direction initialDiggingDirection,
                Position initialDiggingPosition,
                Set<Position> unoccupiedTiles,
                Air air,
                Wall wall,
                EntryPoint entryPoint,
                ExitPoint exitPoint,
                Player player,
                Spike spike,
                Coin coin,
                Enemy enemy,
                SmallHealthPotion smallHealthPotion) {
        this.initialDiggingDirection = initialDiggingDirection;
        this.initialDiggingPosition = initialDiggingPosition;
        this.unoccupiedTiles = unoccupiedTiles;
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

    public Direction getInitialDiggingDirection() {
        return this.initialDiggingDirection;
    }

    public Position getInitialDiggingPosition() {
        return this.initialDiggingPosition;
    }

    public Set<Position> getUnoccupiedTiles() {
        return this.unoccupiedTiles;
    }

    public void setInitialDiggingDirection(Direction initialDiggingDirection) {
        this.initialDiggingDirection = initialDiggingDirection;
    }

    public void setInitialDiggingPosition(Position initialDiggingPosition) {
        this.initialDiggingPosition = initialDiggingPosition;
    }

    public void setUnoccupiedTiles(Set<Position> unoccupiedTiles) {
        this.unoccupiedTiles = unoccupiedTiles;
    }

    public void setAir(Air air) {
        this.air = air;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }

    public void setEntryPoint(EntryPoint entryPoint) {
        this.entryPoint = entryPoint;
    }

    public void setExitPoint(ExitPoint exitPoint) {
        this.exitPoint = exitPoint;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setSpike(Spike spike) {
        this.spike = spike;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public void setSmallHealthPotion(SmallHealthPotion smallHealthPotion) {
        this.smallHealthPotion = smallHealthPotion;
    }

    // MODIFIES: this
    // EFFECTS: Sets all of the game object's fields to null.
    public void setFieldsToNull() {
        this.initialDiggingDirection = null;
        this.initialDiggingPosition = null;
        this.unoccupiedTiles = null;
        this.air = null;
        this.wall = null;
        this.entryPoint = null;
        this.exitPoint = null;
        this.player = null;
        this.spike = null;
        this.coin = null;
        this.enemy = null;
        this.smallHealthPotion = null;
    }

    // EFFECTS: Procedurally Generates the Map
    public void procedurallyGenerateMap() {
        Direction currentDiggingDirection;
        Direction previousDiggingDirection;
        Position currentDiggingPosition;

        initializeWalls();
        initialDiggingPosition = chooseInitialDiggingPosition();
        currentDiggingPosition = new Position(initialDiggingPosition);
        wall.getPositionSet().remove(initialDiggingPosition);
        unoccupiedTiles.add(initialDiggingPosition);

        Random random = new Random();

        int index = Math.abs(random.nextInt()) % 4;
        initialDiggingDirection = DIRECTIONS[index];
        currentDiggingDirection = DIRECTIONS[index];

        int randomTunnelLength;

        for (int i = MAX_TUNNEL_TURNS; i >= 0; --i) {
            while (true) {
                randomTunnelLength = Math.abs(random.nextInt()) % (MAX_TUNNEL_LENGTH + 1);
                if (isRandomTunnelLengthValid(
                        randomTunnelLength,
                        currentDiggingPosition,
                        currentDiggingDirection)) {
                    break;
                }
            }
            for (int j = 0; j < randomTunnelLength; ++j) {
                currentDiggingPosition = currentDiggingPosition.generateNewPosition(currentDiggingDirection);
                wall.getPositionSet().remove(currentDiggingPosition);
                unoccupiedTiles.add(currentDiggingPosition);
            }
            previousDiggingDirection = DIRECTIONS[currentDiggingDirection.ordinal()];

            while (true) {
                index = Math.abs(random.nextInt()) % 4;
                if (index == previousDiggingDirection.ordinal()) {
                    continue;
                }
                currentDiggingDirection = DIRECTIONS[index];
                break;
            }
        }
    }

    // EFFECTS: Chooses an initial digging position on the map.
    public Position chooseInitialDiggingPosition() {
        Random randomX = new Random();
        Random randomY = new Random();
        return new Position(
                Math.abs(randomX.nextInt()) % (GAME_TERMINAL_WIDTH - 2) + 1,
                Math.abs(randomY.nextInt()) % (GAME_TERMINAL_HEIGHT - 2) + 1
        );
    }

    /* EFFECTS: Returns false if digging a length equal to the randomTunnelLength in the currentDirection makes the
                miner attempt to dig into the wall boundary of the map.
                Also returns false if the randomTunnelLength is negative when it shouldn't be.
                Returns true otherwise
                Current Map Wall Boundaries:
                Columns exist at indexes x = 0, Width - 1
                Rows exist at indexes y = 0, Height - 1
     */
    public boolean isRandomTunnelLengthValid(int randomTunnelLength,
                                             Position currentDiggingPosition,
                                             Direction currentDiggingDirection) {
        Position newPosition;
        try {
            newPosition = currentDiggingPosition.generateNewPosition(currentDiggingDirection,randomTunnelLength);
        } catch (DistanceNegativeException e) {
            // Indicates that the tunnel length provided was negative, i.e.,
            // vector information in distance measurement although distance is a scalar.
            return false;
        }
        if (newPosition.getX() > 0
                && newPosition.getX() < GAME_TERMINAL_WIDTH - 1
                && newPosition.getY() > 0
                && newPosition.getY() < GAME_TERMINAL_HEIGHT - 1) {
            return true;
        }
        return false;
    }

    // EFFECTS: Chooses a random position from the unoccupied tiles on the map.
    //          Throws a NoUnoccupiedTilesException if there aren't any unoccupied tiles to choose from.
    public Position chooseRandomPositionFromUnoccupiedTiles() throws NoUnoccupiedTilesException {
        Random random = new Random();
        Position[] unoccupiedPositionsArray = unoccupiedTiles.toArray(new Position[0]);
        int numberOfUnoccupiedTiles = unoccupiedTiles.size();
        if (numberOfUnoccupiedTiles == 0) {
            throw new NoUnoccupiedTilesException();
        }
        int randomIndex = Math.abs(random.nextInt()) % numberOfUnoccupiedTiles;
        return unoccupiedPositionsArray[randomIndex];
    }

    // TODO: Fix parameter lists for single position initialization methods.
    // MODIFIES: this
    // EFFECTS: sets the player's position to the initial digging position.
    public void initializePlayer() {
        player.setPosition(initialDiggingPosition);
    }

    // MODIFIES: this
    // EFFECTS: sets the Entry Point's position to the initial digging position and removes it from the unoccupied tiles
    // set.
    public void initializeEntryPoint() {
        entryPoint.setPosition(initialDiggingPosition);
        unoccupiedTiles.remove(entryPoint.getPosition());
    }

    // MODIFIES: this
    // EFFECTS: Sets the ExitPoint's position by choosing a Position from the unoccupied tiles and removes the position
    // from the unoccupied tiles. Throws a NoUnoccupiedTilesForExitPointException if no unoccupied tiles exist for the
    // ExitPoint to be placed on.
    public void initializeExitPoint() throws NoUnoccupiedTileForExitPointException {
        try {
            exitPoint.setPosition(chooseRandomPositionFromUnoccupiedTiles());
            unoccupiedTiles.remove(exitPoint.getPosition());
        } catch (NoUnoccupiedTilesException e) {
            throw new NoUnoccupiedTileForExitPointException();
        }
    }

    // MODIFIES: this
    // EFFECTS: Sets the Spike tile positions by choosing Positions from the unoccupied tiles. Removes each spike
    // position from the unoccupied tiles in the process. Throws a NoUnoccupiedTileForSpikeException if no unoccupied
    // tiles exist for a Spike to be placed on.
    public void initializeSpikes() throws NoUnoccupiedTileForSpikeException {
        Position randomPosition;
        for (int i = 0;i < NUMBER_OF_SPIKES; ++i) {
            try {
                randomPosition = chooseRandomPositionFromUnoccupiedTiles();
                spike.getPositionSet().add(randomPosition);
                unoccupiedTiles.remove(randomPosition);
            } catch (NoUnoccupiedTilesException e) {
                throw new NoUnoccupiedTileForSpikeException();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Sets the Coin tile positions by choosing Positions from the unoccupied tiles. Removes each coin
    // position from the unoccupied tiles in the process. Throws a NoUnoccupiedTileForCoinException if no unoccupied
    // tiles exist for a Coin to be placed on.
    public void initializeCoins() throws NoUnoccupiedTileForCoinException {
        Position randomPosition;
        for (int i = 0;i < NUMBER_OF_COINS; ++i) {
            try {
                randomPosition = chooseRandomPositionFromUnoccupiedTiles();
                coin.getPositionSet().add(randomPosition);
                unoccupiedTiles.remove(randomPosition);
            } catch (NoUnoccupiedTilesException e) {
                throw new NoUnoccupiedTileForCoinException();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up the Wall positions
    public void initializeWalls() {
        for (int i = 0; i < GAME_TERMINAL_WIDTH; ++i) {
            for (int j = 0; j < GAME_TERMINAL_HEIGHT; ++j) {
                wall.addPosition(new Position(i,j));
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up the air positions
    public void initializeAir() {
        air.addPosition(new Position(2,6));
    }

    // MODIFIES: this
    // EFFECTS: Sets the Enemy tile positions by choosing Positions from the unoccupied tiles. Removes each Enemy
    // position from the unoccupied tiles in the process. Throws a NoUnoccupiedTileForEnemyException if no unoccupied
    // tiles exist for an Enemy to be placed on.
    public void initializeEnemies() throws NoUnoccupiedTileForEnemyException {
        Position randomPosition;
        for (int i = 0;i < NUMBER_OF_ENEMIES; ++i) {
            try {
                randomPosition = chooseRandomPositionFromUnoccupiedTiles();
                enemy.getPositionSet().add(randomPosition);
                unoccupiedTiles.remove(randomPosition);
            } catch (NoUnoccupiedTilesException e) {
                throw new NoUnoccupiedTileForEnemyException();
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Sets the SmallHealthPotion tile positions by choosing Positions from the unoccupied tiles. Removes each
    // SmallHealthPotion position from the unoccupied tiles in the process. Throws a
    // NoUnoccupiedTileForSmallHealthPotionException if no unoccupied tiles exist for a SmallHealthPotion to be placed
    // on.
    public void initializeSmallHealthPotions() throws NoUnoccupiedTileForSmallHealthPotionException {
        Position randomPosition;
        for (int i = 0;i < NUMBER_OF_SMALL_HEALTH_POTIONS; ++i) {
            try {
                randomPosition = chooseRandomPositionFromUnoccupiedTiles();
                smallHealthPotion.getPositionSet().add(randomPosition);
                unoccupiedTiles.remove(randomPosition);
            } catch (NoUnoccupiedTilesException e) {
                throw new NoUnoccupiedTileForSmallHealthPotionException();
            }
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("initialDiggingDirection", initialDiggingDirection.toJson());
        json.put("initialDiggingPosition",initialDiggingPosition.toJson());
        json.put("unoccupiedTiles", new JSONArray(unoccupiedTiles));
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Game game = (Game) o;
        return initialDiggingDirection == game.initialDiggingDirection
                && initialDiggingPosition.equals(game.initialDiggingPosition)
                && unoccupiedTiles.equals(game.unoccupiedTiles)
                && air.equals(game.air)
                && wall.equals(game.wall)
                && entryPoint.equals(game.entryPoint)
                && exitPoint.equals(game.exitPoint)
                && player.equals(game.player)
                && spike.equals(game.spike)
                && coin.equals(game.coin)
                && enemy.equals(game.enemy)
                && smallHealthPotion.equals(game.smallHealthPotion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                initialDiggingDirection,
                initialDiggingPosition,
                unoccupiedTiles,
                air,
                wall,
                entryPoint,
                exitPoint,
                player,
                spike,
                coin,
                enemy,
                smallHealthPotion);
    }
}


