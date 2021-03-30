package model.tile;

import exceptions.CellAtMaximumOrMinimumException;
import model.*;
import org.json.JSONObject;

// A class for modeling the player on the map.
// This player may move around, pick up coins, and interact with the exit.
public class Player extends SingleTile {
    private static final String PLAYER_CHARACTER_SYMBOL = "P";
    public static final String PLAYER_TILE_IMAGE_SOURCE = "./data/graphics/playerCharacter.jpeg";
    private static final int DX = 1;
    private static final int DY = 1;
    private final Wallet wallet;
    private final HealthBar healthBar;
    private Inventory inventory;
    private Direction direction;

    // EFFECTS: initializes the player's wallet with a balance of zero and the player's healthbar with full health
    public Player() {
        this.wallet = new Wallet(0);
        this.healthBar = new HealthBar();
        this.inventory = new Inventory();
        // sets the position to right temporarily
        this.direction = Direction.RIGHT;
    }

    public HealthBar getHealthBar() {
        return this.healthBar;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    // EFFECTS: displays the symbol for the player character + an optional string
    public String display(String s) {
        return super.display(PLAYER_CHARACTER_SYMBOL,s);
    }

    /* MODIFIES: this
     * EFFECTS: Moves the player in the given direction, unless movement in the direction is forbidden due to a wall,
     * enemy, or spike tile.
     * If the player attempts to walk onto an enemy,
     *      The player loses 20 health.
     * else If the player attempts to walk onto a spike,
     *      The player loses all health.
     * Returns true if direction is a valid direction else returns false.
     */
    public boolean move(String direction, Game game) {
        switch (direction) {
            case "w":
                moveUp(game);
                return true;
            case "a":
                moveLeft(game);
                return true;
            case "s":
                moveDown(game);
                return true;
            case "d":
                moveRight(game);
                return true;
        }
        return false;
    }

    /* MODIFIES: this
     * EFFECTS: Moves the player to the right if possible.
     * If the player attempts to walk onto an enemy,
     *      The player loses 20 health.
     * else If the player attempts to walk onto a spike,
     *      The player loses all health.
     * Changes the direction of the player to RIGHT.
     */
    public void moveRight(Game game) {
        Position positionToRight = new Position(this.position.getX() + DX, this.position.getY());
        boolean playerIsInWall = game.wall().getPositionSet().contains(positionToRight);
        boolean playerHitASpike = game.spike().getPositionSet().contains(positionToRight);
        boolean playerWalkedIntoEnemy = game.enemy().getPositionSet().contains(positionToRight);
        if (playerWalkedIntoEnemy) {
            this.healthBar.subtract(20);
            return;
        }
        if (playerHitASpike) {
            this.healthBar.subtract(100);
            return;
        }
        if (!playerIsInWall) {
            this.position.setPosition(positionToRight);
        }

        this.direction = Direction.RIGHT;
    }

    /* MODIFIES: this
     * EFFECTS: Moves the player down if possible.
     * If the player attempts to walk onto an enemy,
     *      The player loses 20 health.
     * else If the player attempts to walk onto a spike,
     *      The player loses all health.
     * Changes the direction of the player to DOWN.
     */
    public void moveDown(Game game) {
        Position positionBelow = new Position(this.position.getX(), this.position.getY() + DY);
        boolean playerIsInWall = game.wall().getPositionSet().contains(positionBelow);
        boolean playerHitASpike = game.spike().getPositionSet().contains(positionBelow);
        boolean playerWalkedIntoEnemy = game.enemy().getPositionSet().contains(positionBelow);
        if (playerWalkedIntoEnemy) {
            this.healthBar.subtract(20);
            return;
        }
        if (playerHitASpike) {
            this.healthBar.subtract(100);
            return;
        }
        if (!playerIsInWall) {
            this.position.setPosition(positionBelow);
        }

        this.direction = Direction.DOWN;
    }

    /* MODIFIES: this
     * EFFECTS: Moves the player to the left if possible.
     * If the player attempts to walk onto an enemy,
     *      The player loses 20 health.
     * else If the player attempts to walk onto a spike,
     *      The player loses all health.
     * Changes the direction of the player to LEFT.
     */
    public void moveLeft(Game game) {
        Position positionToLeft = new Position(this.position.getX() - DX, this.position.getY());
        boolean playerIsInWall = game.wall().getPositionSet().contains(positionToLeft);
        boolean playerHitASpike = game.spike().getPositionSet().contains(positionToLeft);
        boolean playerWalkedIntoEnemy = game.enemy().getPositionSet().contains(positionToLeft);
        if (playerWalkedIntoEnemy) {
            this.healthBar.subtract(20);
            return;
        }
        if (playerHitASpike) {
            this.healthBar.subtract(100);
            return;
        }
        if (!playerIsInWall) {
            this.position.setPosition(positionToLeft);
        }

        this.direction = Direction.LEFT;
    }

    /* MODIFIES: this
     * EFFECTS: Moves the player up if possible.
     * If the player attempts to walk onto an enemy,
     *      The player loses 20 health.
     * else If the player attempts to walk onto a spike,
     *      The player loses all health.
     * Changes the direction of the player to UP.
     */
    public void moveUp(Game game) {
        Position positionAbove = new Position(this.position.getX(), this.position.getY() - DY);
        boolean playerIsInWall = game.wall().getPositionSet().contains(positionAbove);
        boolean playerHitASpike = game.spike().getPositionSet().contains(positionAbove);
        boolean playerWalkedIntoEnemy = game.enemy().getPositionSet().contains(positionAbove);

        if (playerWalkedIntoEnemy) {
            this.healthBar.subtract(20);
            return;
        }
        if (playerHitASpike) {
            this.healthBar.subtract(100);
            return;
        }
        if (!playerIsInWall) {
            this.position.setPosition(positionAbove);
        }

        this.direction = Direction.UP;
    }

    // EFFECTS: allows the player to perform certain interactions
    //          "e": let's the player end the game on an exitPoint tile
    //          returns true to indicate that the level is over
    //          returns false to indicate that the level is not
    public boolean interact(String typeOfInteraction, Game game) {
        if ("e".equals(typeOfInteraction)) {
            if (game.exitPoint().getPosition().equals(this.position)) {
                return true;
            }
        }
        return false;
    }

    public Wallet getWallet() {
        return this.wallet;
    }

    // EFFECTS: returns the player's current wallet balance
    public int getWalletBalance() {
        return wallet.getBalance();
    }

    public void setWalletBalance(int balance) {
        this.wallet.setBalance(balance);
    }

    // MODIFIES: this
    // EFFECTS: adds coins to the players wallet
    public void addToWallet(int amount) {
        wallet.addAmount(amount);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("position", position.toJson());
        json.put("wallet", wallet.toJson());
        json.put("healthBar", healthBar.toJson());
        json.put("inventory", inventory.toJson());
        return json;
    }
}
