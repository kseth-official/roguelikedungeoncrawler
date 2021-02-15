package ui;

import model.*;

import java.util.Scanner;

public class RogueLikeGame {
    private static final String CONSOLE_CLEANER = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
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
        return gameTerminalWidth;
    }

    public int getGameTerminalHeight() {
        return gameTerminalHeight;
    }

    // EFFECTS: clears the console screen by printing newline characters
    public void clearScreen() {
        for (int i = 0;i < 3;++i) {
            System.out.print(CONSOLE_CLEANER);
        }
        System.out.print("\r");
    }

    // MODIFIES: a lot
    // EFFECTS: performs the map setup, enters the game loop, and exits on game over or level complete
    public void runRogueLikeGame() {
        boolean gameIsRunning = true;
        boolean levelIsOver = false;

        initialize();

        while (gameIsRunning && (!levelIsOver)) {
            displayControls();
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
        game = new Game();
    }

    // EFFECTS: displays the game controls
    public void displayControls() {
        System.out.println("Use WASD to move, I to interact with an exit, and E to quit the game");
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

    // REQUIRES: a running game
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

    // MODIFIES: map, and ???
    // EFFECTS: checks if the player has collided with a coin, an obstacle, or an enemy and creates the
    //          corresponding outcome.
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
