package ui;

import java.io.IOException;
import java.util.Scanner;

import model.Game;
import persistence.*;

// ARCHIVED CODE FOR OLD TEXT-BASED GUI IMPLEMENTATION OF THE GAME'S MAIN MENU
// Contains the game's main menu functionality, including creating a new game, loading a new game, and exiting.
public class RogueLikeGameMainMenu {
//    private static final String JSON_STORE_SAVE_FILE_ONE = "./data/saveFileOne.json";
//    private static final String JSON_STORE_SAVE_FILE_TWO = "./data/saveFileTwo.json";
//    private static final String JSON_STORE_SAVE_FILE_THREE = "./data/saveFileThree.json";
//    public static final int GAME_TERMINAL_WIDTH = 13;
//    public static final int GAME_TERMINAL_HEIGHT = 13;
//    private static final String CONSOLE_CLEANER = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
//    private static final int NUMBER_OF_CONSOLE_CLEANER_REPEATS = 3;
//    private JsonReader jsonReader;
//
//
//    // EFFECTS: Initializes a new main menu and displays it
//    public RogueLikeGameMainMenu() {
//        display();
//    }
//
//    // EFFECTS: clears the console screen by printing newline characters
//    public void clearScreen() {
//        for (int i = 0;i < NUMBER_OF_CONSOLE_CLEANER_REPEATS;++i) {
//            System.out.print(CONSOLE_CLEANER);
//        }
//        System.out.print("\r");
//    }
//
//    // EFFECTS: Displays the RogueLikeGame Main Menu
//    public void display() {
//        while (true) {
//            System.out.println("MAIN MENU");
//            System.out.println("\t1. New Game");
//            System.out.println("\t2. Load Game");
//            System.out.println("\t3. Exit Game");
//            System.out.print("Enter here: ");
//
//            Scanner input = new Scanner(System.in);
//            String keyPress = input.next();
//
//            clearScreen();
//
//            if (keyPress.equals("1")) {
//                RogueLikeGame newGame = new RogueLikeGame(GAME_TERMINAL_WIDTH,GAME_TERMINAL_HEIGHT);
//                newGame.runRogueLikeGame();
//            } else if (keyPress.equals("2")) {
//                loadRogueLikeGame();
//            } else if (keyPress.equals("3")) {
//                System.out.println("Come again!");
//                System.exit(0);
//            }
//
//        }
//    }
//
//    // EFFECTS: reads a rogue like game from one of three save files and loads it
//    private void loadRogueLikeGame() {
//        clearScreen();
//        System.out.println("LOAD SAVE FILE");
//        System.out.println("\tSave File 1");
//        System.out.println("\tSave File 2");
//        System.out.println("\tSave File 3");
//        System.out.print("Enter here: ");
//
//        Scanner input = new Scanner(System.in);
//        String keyPress = input.next();
//
//        if (keyPress.equals("1")) {
//            loadSaveFile("1",JSON_STORE_SAVE_FILE_ONE);
//        } else if (keyPress.equals("2")) {
//            loadSaveFile("2",JSON_STORE_SAVE_FILE_TWO);
//        } else if (keyPress.equals("3")) {
//            loadSaveFile("3",JSON_STORE_SAVE_FILE_THREE);
//        }
//    }
//
//    // Code citation: JsonSerializationDemo (CPSC 210; The University of British Columbia, Vancouver)
//    // EFFECTS: loads a save file of given number from a source
//    public void loadSaveFile(String number, String source) {
//        System.out.println();
//        try {
//            jsonReader = new JsonReader(source);
//            Game fromSource  = jsonReader.read();
//            Game defaultGame = new Game();
//            System.out.println("Loaded " + "Save File " + number + " from " + source);
//            clearScreen();
//            RogueLikeGame fromSaveFile = new RogueLikeGame(fromSource, GAME_TERMINAL_WIDTH, GAME_TERMINAL_HEIGHT);
//            fromSaveFile.runRogueLikeGame();
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + source);
//        }
//    }
}