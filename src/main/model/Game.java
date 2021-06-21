package model;

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
    private static final int NUMBER_OF_SPIKES = 50;

    private Air air = new Air();
    private Wall wall = new Wall();
    private EntryPoint entryPoint = new EntryPoint();
    private ExitPoint exitPoint = new ExitPoint();
    private Player player = new Player();
    private Spike spike = new Spike();
    private Coin coin = new Coin();
    private Enemy enemy = new Enemy();
    private SmallHealthPotion smallHealthPotion = new SmallHealthPotion();
    private Set<Position> gameTiles = new HashSet<>();

    // EFFECTS: sets up the initial game map
    public Game() {
        initializePlayer(new Position(1, 6));
        initializeEntryPoint(new Position(1, 6));
        initializeExitPoint(new Position(11, 6));
        initializeCoins();
        initializeWalls();
        initializeAir();
        initializeEnemies();
        initializeSmallHealthPotions();
        initializeSpikes();
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

    // MODIFIES: this
    // EFFECTS: sets up the player's position
    public void initializePlayer(Position p) {
        player.setPosition(p);
        gameTiles.add(p);
    }

    // MODIFIES: this
    // EFFECTS: sets up the Entry Point's position
    public void initializeEntryPoint(Position p) {
        entryPoint.setPosition(p);
        gameTiles.add(p);
    }

    // MODIFIES: this
    // EFFECTS: sets up the Exit Point's position
    public void initializeExitPoint(Position p) {
        exitPoint.setPosition(p);
        gameTiles.add(p);
    }

    // MODIFIES: this
    // EFFECTS: sets up the Spike positions
    public void initializeSpikes() {

        for (int i = 0;i < NUMBER_OF_SPIKES;++i) {
            spike.addPosition(generateUsableMapPosition(gameTiles));
        }

        gameTiles.addAll(spike.getPositionSet());

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
        coin.addPosition(new Position(2, 6));
        coin.addPosition(new Position(3, 6));
        coin.addPosition(new Position(4, 6));
        coin.addPosition(new Position(4, 5));
        coin.addPosition(new Position(4, 4));
        coin.addPosition(new Position(4, 3));
        coin.addPosition(new Position(4, 2));
        coin.addPosition(new Position(4, 1));
        coin.addPosition(new Position(5, 1));
        coin.addPosition(new Position(6, 1));
        coin.addPosition(new Position(7, 1));
        coin.addPosition(new Position(7, 2));
        coin.addPosition(new Position(7, 3));
        coin.addPosition(new Position(7, 4));
        coin.addPosition(new Position(7, 5));
        coin.addPosition(new Position(7, 6));
        coin.addPosition(new Position(8, 6));
        coin.addPosition(new Position(9, 6));
        coin.addPosition(new Position(10, 6));

        gameTiles.addAll(coin.getPositionSet());
    }

    // MODIFIES: this
    // EFFECTS: sets up the Wall positions
    public void initializeWalls() {
        final int ZERO = 0;
        final int TWELVE = 12;

        // adding corners
        wall.addPosition(new Position(ZERO, ZERO));
        wall.addPosition(new Position(TWELVE, ZERO));
        wall.addPosition(new Position(ZERO, TWELVE));
        wall.addPosition(new Position(TWELVE, TWELVE));

        // adding boundaries
        for (int i = 1; i < 12; ++i) {
            wall.addPosition(new Position(i, ZERO));
            wall.addPosition(new Position(i, TWELVE));
            wall.addPosition(new Position(ZERO, i));
            if (i == 6) {
                continue;
            } else {
                wall.addPosition(new Position(TWELVE, i));
            }
        }

        gameTiles.addAll(wall.getPositionSet());
    }

    // MODIFIES: this
    // EFFECTS: sets up the air positions
    public void initializeAir() {
        air.addPosition(new Position(2,6));
    }

    // MODIFIES: this
    // EFFECTS: sets up the enemy positions
    public void initializeEnemies() {
        enemy.addPosition(new Position(5,9));
        enemy.addPosition(new Position(10,1));
        enemy.addPosition(new Position(10,10));
        gameTiles.addAll(enemy.getPositionSet());
    }

    // MODIFIES: this
    // EFFECTS: sets up the enemy positions
    public void initializeSmallHealthPotions() {
        smallHealthPotion.addPosition(new Position(11,1));
        smallHealthPotion.addPosition(new Position(11,2));
        smallHealthPotion.addPosition(new Position(11,9));
        smallHealthPotion.addPosition(new Position(11,10));
        smallHealthPotion.addPosition(new Position(6,6));
        smallHealthPotion.addPosition(new Position(6,7));
        smallHealthPotion.addPosition(new Position(6,5));

        gameTiles.addAll(smallHealthPotion.getPositionSet());
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


