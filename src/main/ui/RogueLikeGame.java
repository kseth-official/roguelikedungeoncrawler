package ui;

import model.*;

import java.util.Scanner;

// A class that sets up the game's user interface.
public class RogueLikeGame {
    private static final String CONSOLE_CLEANER = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
    private static final int NUMBER_OF_CONSOLE_CLEANER_REPEATS = 3;
    private static final int BASIC_COIN_WORTH = 1;

    private static int gameTerminalWidth;
    private static int gameTerminalHeight;

    private Game game;

    // MODIFIES: this
    // EFFECTS: sets up the width and height of the game arena and runs the game
    RogueLikeGame(int width, int height) {
        RogueLikeGame.gameTerminalWidth = width;
        RogueLikeGame.gameTerminalHeight = height;
        runRogueLikeGame();
    }

    public int getGameTerminalWidth() {
        return RogueLikeGame.gameTerminalWidth;
    }

    public int getGameTerminalHeight() {
        return RogueLikeGame.gameTerminalHeight;
    }

    // EFFECTS: clears the console screen by printing newline characters
    public void clearScreen() {
        for (int i = 0;i < NUMBER_OF_CONSOLE_CLEANER_REPEATS;++i) {
            System.out.print(CONSOLE_CLEANER);
        }
        System.out.print("\r");
    }

    // MODIFIES: game
    // EFFECTS: performs the map setup, enters the game loop, and exits on game over or level complete
    public void runRogueLikeGame() {
        boolean gameIsRunning = true;
        boolean levelIsOver = false;

        initialize();

        while (gameIsRunning && (!levelIsOver)) {
            displayControlsAndInformation();
            displayWallet();
            displayMap();
            levelIsOver = handleKeyEvent();
            gameIsRunning = handleCollisions();
            clearScreen();
        }

        if (gameIsRunning) {
            endScreen("You have won!\nThank You For Playing!");
        } else {
            endScreen("Game Over :(\nYou've lost");
        }

    }

    // MODIFIES: this
    // EFFECTS: initializes the game map
    public void initialize() {
        this.game = new Game();
    }

    // EFFECTS: displays the game controls and game map information
    public void displayControlsAndInformation() {
        System.out.println("Use WASD to move, I to interact with an exit, and E to quit the game");
        System.out.println("P represents the Player");
        System.out.println("E represents the Entry Point");
        System.out.println("e represents the Exit Point");
        System.out.println("¤ represents a Coin");
        System.out.println("░ represents a Spike Tile");
        System.out.println("W represents a Wall");
        System.out.println();
    }

    // EFFECTS: displays the player's wallet balance
    public void displayWallet() {
        System.out.print("Wallet: ");
        System.out.println(game.player().getWalletBalance());
    }

    // REQUIRES: game map must be initialized
    // EFFECTS: displays the game map
    public void displayMap() {
        for (int j = 0; j < gameTerminalHeight;++j) {

            for (int i = 0; i < gameTerminalWidth;++i) {

                if (game.player().getPosition().equals(new Position(i,j))) {
                    game.player().display(" ");

                } else if (game.coin().getPositionSet().contains(new Position(i,j))) {
                    game.coin().display(" ");

                } else if (game.entryPoint().getPosition().equals(new Position(i,j))) {
                    game.entryPoint().display(" ");

                } else if (game.exitPoint().getPosition().equals(new Position(i,j))) {
                    game.exitPoint().display(" ");

                } else if (game.spike().getPositionSet().contains(new Position(i,j))) {
                    game.spike().display(" ");

                } else if (game.wall().getPositionSet().contains(new Position(i,j))) {
                    game.wall().display(" ");

                } else {
                    game.air().display(" ");
                }

                if (i == (gameTerminalWidth - 1)) {
                    System.out.println();
                }
            }
        }
    }

    // REQUIRES: the game must be initialized
    // MODIFIES: game
    // EFFECTS: takes the user's input for the player character
    //          returns true if the level is over otherwise returns false
    public boolean handleKeyEvent() {
        System.out.print("Input a key and press enter : ");
        Scanner input = new Scanner(System.in);
        String keyPress = input.next();

        switch (keyPress) {
            case "w":
            case "a":
            case "s":
            case "d":
                game.player().move(keyPress, game);
                break;
            case "i":
                return game.player().interact(keyPress, game);
            case "e":
                System.exit(0);
            default:
        }

        return false;
    }

    // MODIFIES: game
    // EFFECTS: checks if the player has collided with a coin, an obstacle, or an enemy and creates the
    //          corresponding outcome. returns false if the game has ended else returns true
    public boolean handleCollisions() {
        Position currentPlayerPosition = game.player().getPosition();

        if (game.coin().getPositionSet().contains(currentPlayerPosition)) {
            game.coin().getPositionSet().remove(currentPlayerPosition);
            game.player().addToWallet(BASIC_COIN_WORTH);
        } else if (game.spike().getPositionSet().contains(currentPlayerPosition)) {
            return false;
        }

        return true;
    }

    // EFFECTS: displays the ending message
    public void endScreen(String endMessage) {
        System.out.println(endMessage);
    }
}
