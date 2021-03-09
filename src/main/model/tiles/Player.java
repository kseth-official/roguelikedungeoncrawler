package model.tiles;

import model.*;
import org.json.JSONObject;

// A class for modeling the player on the map.
// The player is a subclass of SingleTile meaning that the player has only a single position.
// This player may move around, pick up coins, and interact with the exit.
public class Player extends SingleTile {
    private static final String PLAYER_CHARACTER_SYMBOL = "P";
    private static final int DX = 1;
    private static final int DY = 1;
    private final Wallet wallet;
    private final HealthBar healthBar = new HealthBar();

    // EFFECTS: initializes the player's
    //          1. wallet with a balance of 0
    //          2. health bar
    public Player() {
        this.wallet = new Wallet(0);
        // create a test for healthBar initialization
    }

    // EFFECTS: displays the symbol for the player character + an optional string
    public String display(String s) {
        return super.display(PLAYER_CHARACTER_SYMBOL,s);
    }

    // MODIFIES: this
    // EFFECTS: moves the player in the given direction, unless movement in the direction is forbidden due to a wall
    public boolean move(String direction, Game game) {
        if (direction.equals("w")) {
            if (!game.wall().getPositionSet().contains(new Position(this.position.getX(), this.position.getY() - DY))) {
                this.position.setPosition(this.position.getX(), this.position.getY() - DY);
            }
            return true;
        } else if (direction.equals("a")) {
            if (!game.wall().getPositionSet().contains(new Position(this.position.getX() - DX, this.position.getY()))) {
                this.position.setPosition(this.position.getX() - DX, this.position.getY());
            }
            return true;
        } else if (direction.equals("s")) {
            if (!game.wall().getPositionSet().contains(new Position(this.position.getX(), this.position.getY() + DY))) {
                this.position.setPosition(this.position.getX(), this.position.getY() + DY);
            }
            return true;
        } else if (direction.equals("d")) {
            if (!game.wall().getPositionSet().contains(new Position(this.position.getX() + DX, this.position.getY()))) {
                this.position.setPosition(this.position.getX() + DX, this.position.getY());
            }
            return true;
        }
        return false;
    }

    // EFFECTS: allows the player to perform certain interactions
    //          "i": let's the player end the game on an exitPoint tile
    //          returns true to indicate that the level is over
    //          returns false to indicate that the level is not
    public boolean interact(String typeOfInteraction, Game game) {
        switch (typeOfInteraction) {
            case "i":
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
        return json;
    }
}
