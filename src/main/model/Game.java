package model;

// A class for modeling the Game
public class Game {
    private Air air = new Air();
    private Wall wall = new Wall();
    private EntryPoint entryPoint = new EntryPoint();
    private ExitPoint exitPoint = new ExitPoint();
    private Player player = new Player();
    private Spike  spike = new Spike();
    private Coin coin = new Coin();



    // MODIFIES: this
    // EFFECTS: sets up the initial game map
    public Game() {
        initializePlayer(new Position(1,6));
        initializeEntryPoint(new Position(1,6));
        initializeExitPoint(new Position(11,6));
        initializeSpikes();
        initializeCoins();
        initializeWalls();
    }

    // MODIFIES: this
    // EFFECTS: sets up the player's position
    public void initializePlayer(Position p) {
        player.setPosition(p);
    }

    // MODIFIES: this
    // EFFECTS: sets up the Entry Point's position
    public void initializeEntryPoint(Position p) {
        entryPoint.setPosition(p);
    }

    // MODIFIES: this
    // EFFECTS: sets up the Exit Point's position
    public void initializeExitPoint(Position p) {
        exitPoint.setPosition(p);
    }

    // MODIFIES: this
    // EFFECTS: sets up the Spike positions
    public void initializeSpikes() {
        spike.addPosition(new Position(9,3));
        spike.addPosition(new Position(9,4));
        spike.addPosition(new Position(9,8));
        spike.addPosition(new Position(9,9));
    }

    // MODIFIES: this
    // EFFECTS: sets up the Coin positions
    public void initializeCoins() {
        coin.addPosition(new Position(2,6));
        coin.addPosition(new Position(3,6));
        coin.addPosition(new Position(4,6));
        coin.addPosition(new Position(4,5));
        coin.addPosition(new Position(4,4));
        coin.addPosition(new Position(4,3));
        coin.addPosition(new Position(4,2));
        coin.addPosition(new Position(4,1));
        coin.addPosition(new Position(5,1));
        coin.addPosition(new Position(6,1));
        coin.addPosition(new Position(7,1));
        coin.addPosition(new Position(7,2));
        coin.addPosition(new Position(7,3));
        coin.addPosition(new Position(7,4));
        coin.addPosition(new Position(7,5));
        coin.addPosition(new Position(7,6));
        coin.addPosition(new Position(8,6));
        coin.addPosition(new Position(9,6));
        coin.addPosition(new Position(10,6));
    }

    // MODIFIES: this
    // EFFECTS: sets up the Wall positions
    public void initializeWalls() {
        final int ZERO = 0;
        final int TWELVE = 12;

        // adding corners
        wall.addPosition(new Position(ZERO,ZERO));
        wall.addPosition(new Position(TWELVE,ZERO));
        wall.addPosition(new Position(ZERO,TWELVE));
        wall.addPosition(new Position(TWELVE,TWELVE));

        // adding boundaries
        for (int i = 1; i < 12; ++i) {
            wall.addPosition(new Position(i,ZERO));
            wall.addPosition(new Position(i,TWELVE));
            wall.addPosition(new Position(ZERO,i));
            wall.addPosition(new Position(TWELVE,i));

        }
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
}
