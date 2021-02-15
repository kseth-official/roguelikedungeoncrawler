package model;

// A class for modeling the player on the map.
// This player may move around, pick up coins, and interact with the exit.
public class Player  {
    private static final String PLAYER_CHARACTER_SYMBOL = "P";
    private static final int DX = 1;
    private static final int DY = 1;
    private Position position = new Position();
    private Wallet wallet;

    public Player() {
        wallet = new Wallet(0);
    }

    // EFFECTS: displays the symbol for the player character + an optional string
    public String display(String s) {
        System.out.print(PLAYER_CHARACTER_SYMBOL + s);
        return PLAYER_CHARACTER_SYMBOL + s;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position p) {
        this.position = p;
    }

    // MODIFIES: this
    // EFFECTS: moves the player in the given direction, unless movement in the direction would cause a collision with
    //          a wall
    public void move(String direction, Game game) {
        int currentXPosition = this.position.getX();
        int currentYPosition = this.position.getY();

        switch (direction) {
            case "w":
                if (!game.wall().getPositionSet().contains(new Position(currentXPosition, currentYPosition - DY))) {
                    this.position.setPosition(currentXPosition, currentYPosition - DY);
                }
                break;
            case "a":
                if (!game.wall().getPositionSet().contains(new Position(currentXPosition - DX, currentYPosition))) {
                    this.position.setPosition(currentXPosition - DX, currentYPosition);
                }
                break;
            case "s":
                if (!game.wall().getPositionSet().contains(new Position(currentXPosition, currentYPosition + DY))) {
                    this.position.setPosition(currentXPosition, currentYPosition + DY);
                }
                break;
            case "d":
                if (!game.wall().getPositionSet().contains(new Position(currentXPosition + DX, currentYPosition))) {
                    this.position.setPosition(currentXPosition + DX, currentYPosition);
                }
        }
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
