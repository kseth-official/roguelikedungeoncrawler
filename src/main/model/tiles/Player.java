package model.tiles;

import model.Game;
import model.Position;
import model.SingleTile;
import model.Wallet;

// A class for modeling the player on the map.
// The player is a subclass of SingleTile meaning that the player has only a single position.
// This player may move around, pick up coins, and interact with the exit.
public class Player extends SingleTile {
    private static final String PLAYER_CHARACTER_SYMBOL = "P";
    private static final int DX = 1;
    private static final int DY = 1;
    private final Wallet wallet;

    // EFFECTS: initializes the player's wallet with a balance of 0
    public Player() {
        wallet = new Wallet(0);
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

    // EFFECTS: returns the player's current wallet balance
    public int getWalletBalance() {
        return wallet.getBalance();
    }

    // MODIFIES: this
    // EFFECTS: adds coins to the players wallet
    public void addToWallet(int amount) {
        wallet.addAmount(amount);
    }
}
