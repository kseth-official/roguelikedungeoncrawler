package model;

// A class representing the game wallet
// A player may add coins to the wallet by moving into a tile with a Coin on it.
public class Wallet {
    private int balance;

    // EFFECTS: Creates a new wallet with balance
    public Wallet(int balance) {
        this.balance = balance;
    }

    // MODIFIES: this
    // EFFECTS: adds coins to the wallet of a given amount
    public void addAmount(int amount) {
        this.balance += amount;
    }

    // EFFECTS: gives the current wallet balance
    public int getBalance() {
        return this.balance;
    }

}
