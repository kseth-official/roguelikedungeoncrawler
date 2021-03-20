package ui;

import model.*;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.util.Scanner;

// A class that sets up the game's user interface. Allows the user to save the game to a file as well.
public class RogueLikeGame {
    private static final String JSON_STORE_SAVE_FILE_ONE = "./data/saveFileOne.json";
    private static final String JSON_STORE_SAVE_FILE_TWO = "./data/saveFileTwo.json";
    private static final String JSON_STORE_SAVE_FILE_THREE = "./data/saveFileThree.json";
    private static final String CONSOLE_CLEANER = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
    private static final int NUMBER_OF_CONSOLE_CLEANER_REPEATS = 3;
    private static final int BASIC_COIN_WORTH = 1;
    private JsonWriter jsonWriter;

    private static int gameTerminalWidth;
    private static int gameTerminalHeight;

    private Game game;

    // MODIFIES: this
    // EFFECTS: sets up the width and height of the game arena, creates a new game, and runs it
    RogueLikeGame(int width, int height) {
        RogueLikeGame.gameTerminalWidth = width;
        RogueLikeGame.gameTerminalHeight = height;
        this.game = new Game();
        runRogueLikeGame();
    }

    // MODIFIES: this
    // EFFECTS: loads the game from another Game instance
    RogueLikeGame(Game other, int width, int height) {
        RogueLikeGame.gameTerminalWidth = width;
        RogueLikeGame.gameTerminalHeight = height;
        this.game = other;
        runRogueLikeGame();
    }

    public Game getGame() {
        return game;
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

//    // EFFECTS:  moves main menu arrow if possible or else constrains it within the main menu option bounds
//    public int isArrowInBoundsAfterInput(int arrowPosition, char input) {
//        if (input == 'w' && (arrowPosition + 1 <= NUMBER_OF_MAIN_MENU_OPTIONS)) {
//            return arrowPosition + 1;
//        } else if (arrowPosition - 1 >= 1) {
//            return arrowPosition - 1;
//        }
//        return arrowPosition;
//    }

    // MODIFIES: game
    // EFFECTS: performs the Game map setup, enters the game loop, and exits on game over or level complete
    public void runRogueLikeGame() {
        boolean gameIsRunning = true;
        boolean levelIsOver = false;

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

    // EFFECTS: displays the game controls and game map information
    public void displayControlsAndInformation() {
        System.out.println("Use wasd to move, i to interact with an exit, p to save the game, and e to quit the game");
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

                } else if (game.enemy().getPositionSet().contains(new Position(i,j))) {
                    game.enemy().display(" ");

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
            case "p":
                saveGame();
                break;
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
        } else if (game.enemy().getPositionSet().contains(currentPlayerPosition)) {
            return false;
        }

        return true;
    }

    // EFFECTS: displays the ending message
    public void endScreen(String endMessage) {
        System.out.println(endMessage);
        System.exit(0);
    }

    // MODIFIES: this
    // EFFECTS: saves the game to a save file
    private void saveGame() {
        clearScreen();
        System.out.println("Select the save file you wish to save your game to");
        System.out.println("\tSave File 1");
        System.out.println("\tSave File 2");
        System.out.println("\tSave File 3");
        System.out.print("Enter here: ");

        Scanner input = new Scanner(System.in);
        String keyPress = input.next();

        if (keyPress.equals("1")) {
            writeToSaveFile("1",JSON_STORE_SAVE_FILE_ONE);
        } else if (keyPress.equals("2")) {
            writeToSaveFile("2",JSON_STORE_SAVE_FILE_TWO);
        } else if (keyPress.equals("3")) {
            writeToSaveFile("3",JSON_STORE_SAVE_FILE_THREE);
        }

    }

    // Code citation: JsonSerializationDemo (CPSC 210; The University of British Columbia, Vancouver)
    // MODIFIES: this
    // EFFECTS: writes a save file of given number to a source
    //          catches a FileNotFoundException if is unable to write to the file
    public void writeToSaveFile(String number, String source) {
        System.out.println();
        try {
            jsonWriter = new JsonWriter(source);
            jsonWriter.open();
            jsonWriter.write(game);
            jsonWriter.close();
            System.out.println("Saved to save file " + number + " at " + source);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + source);
        }
    }
}
