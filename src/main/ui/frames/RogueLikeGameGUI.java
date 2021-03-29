package ui.frames;

import exceptions.CellAtMaximumOrMinimumException;
import model.*;
import model.tile.SmallHealthPotion;
import persistence.JsonWriter;
import ui.GameAudioPlayer;
import ui.RogueLikeGameMainMenu;
import ui.buttons.DescendButton;
import ui.buttons.UsePotionButton;
import ui.buttons.PauseButton;
import ui.labels.ControlsAndInformation;
import ui.labels.GameOverLabel;
import ui.panels.InventoryPanel;
import ui.panels.GamePanel;
import ui.panels.PausePanel;
import ui.progressbars.HealthProgressBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.Scanner;

// A class that sets up the game's user interface.
public class RogueLikeGameGUI extends JFrame implements ActionListener, KeyListener {
    private static final int FRAME_WIDTH = MainMenu.FRAME_WIDTH;
    private static final int FRAME_HEIGHT = MainMenu.FRAME_HEIGHT;

    private int frameContentPaneWidth;
    private int frameContentPaneHeight;

    private static final int BASIC_COIN_WORTH = 1;
    private static final String PLAYER_IS_DEAD_SOUND = "./data/audio/mixkit-spooky-game-over-1948.wav";
    private static final String PLAYER_WON_SOUND = "./data/audio/mixkit-game-level-completed-2059.wav";

    private static int gameTerminalWidth;
    private static int gameTerminalHeight;

    private Game game;
    private GameAudioPlayer gameAudioPlayer;

    // COMPONENTS
    // PANELS
    private JPanel gamePanel;
    private JPanel inventoryPanel;
//    private JPanel pausePanel;

    // LABELS
    private JLabel controlsAndInformationLabel;

    // BUTTONS
    private JButton pauseButton;
    private JButton useSmallHealthPotionButton;
    private JButton descendButton;

    // PROGRESS BAR
    private JProgressBar healthBar;

    // MODIFIES: this
    // EFFECTS: sets up the width and height of the game arena, creates a new game
    public RogueLikeGameGUI(int width, int height) {
        super("RogueLikeGame");
        initializeFields(new Game(),width,height);
        initializeGraphics();
        initializeInteraction();
    }

    // MODIFIES: this
    // EFFECTS: loads the game from another Game instance
    public RogueLikeGameGUI(Game other, int width, int height) {
        super("RogueLikeGame");
        initializeFields(other,width,height);
        initializeGraphics();
        initializeInteraction();
    }

    private void initializeInteraction() {
        pauseButton.addActionListener(this);
        useSmallHealthPotionButton.addActionListener(this);
        descendButton.addActionListener(this);
        addKeyListener(this);
    }

    // MODIFIES: this
    // EFFECTS: Sets the the number of rows and columns in the game
    private void initializeFields(Game game, int width, int height) {
        RogueLikeGameGUI.gameTerminalWidth = width;
        RogueLikeGameGUI.gameTerminalHeight = height;
        this.game = game;
        this.gameAudioPlayer = new GameAudioPlayer();
    }

    public Game getGame() {
        return game;
    }

    public int getGameTerminalWidth() {
        return RogueLikeGameGUI.gameTerminalWidth;
    }

    public int getGameTerminalHeight() {
        return RogueLikeGameGUI.gameTerminalHeight;
    }

    // EFFECTS: clears the console screen by printing newline characters
    public void clearScreen() {
        for (int i = 0;i < 5;++i) {
            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        }
        System.out.print("\r");
    }


    // MODIFIES: this
    // EFFECTS: Initializes all the Game Frame Components
    public void initializeGraphics() {
        setFrameCharacteristics();

        frameContentPaneWidth = (int) getContentPane().getSize().getWidth();
        frameContentPaneHeight = (int) getContentPane().getSize().getHeight();

        // PANELS
        gamePanel = new GamePanel(frameContentPaneWidth, frameContentPaneHeight, gameTerminalWidth, gameTerminalHeight,
                game);

        inventoryPanel = new InventoryPanel(frameContentPaneWidth, frameContentPaneHeight, game);

//        pausePanel = new PausePanel(frameContentPaneWidth,frameContentPaneHeight,game);

//        pausePanel.setVisible(false);

        // LABELS
        controlsAndInformationLabel = new ControlsAndInformation(frameContentPaneWidth, frameContentPaneHeight);

        // BUTTONS
        pauseButton = new PauseButton(frameContentPaneWidth,frameContentPaneHeight);

        useSmallHealthPotionButton = new UsePotionButton(frameContentPaneWidth,frameContentPaneHeight);

        descendButton = new DescendButton(frameContentPaneWidth, frameContentPaneHeight);

        // PROGRESS BAR
        healthBar = new HealthProgressBar(frameContentPaneWidth,frameContentPaneHeight,game);


        add(gamePanel);
//        add(pausePanel);
        add(controlsAndInformationLabel);
        add(inventoryPanel);
        add(pauseButton);
        add(descendButton);
        add(useSmallHealthPotionButton);
        add(healthBar);
    }

    // MODIFIES: this
    // EFFECTS: Sets the frame's characteristics
    public void setFrameCharacteristics() {
        // FRAME CHARACTERISTICS
        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setVisible(true);
    }

    private void displayInventory() {
        int numberOfSmallHealthPotions = game.player().getInventory().getNumberOfSmallHealthPotions();
        System.out.println("INVENTORY");
        System.out.println("\tSmall Health Potions: " + numberOfSmallHealthPotions);
    }

    // EFFECTS: Handles in game events. Returns true if the game should be running else returns false.
    public boolean handleGameEvents() {
        if (game.player().getHealthBar().isZero()) {
            return false;
        }
        return true;
    }

    public void paintComponent(Graphics g) {

    }


    // EFFECTS: displays the game controls and game map information
    public void displayControlsAndInformation() {
        System.out.print("Use wasd to move, ");
        System.out.print("e to interact with an exit, ");
        System.out.print("o to open inventory, and ");
        System.out.println("p to pause the game.");
        System.out.println("P represents the Player");
        System.out.println("O represents an Enemy");
        System.out.println("E represents the Entry Point");
        System.out.println("e represents the Exit Point");
        System.out.println("¤ represents a Coin");
        System.out.println("░ represents a Spike Tile");
        System.out.println("W represents a Wall");
        System.out.println("h represents a Small Health Potion");
        System.out.println();
    }

    // EFFECTS: displays the player's current health
    public void displayPlayerHealth() {
        System.out.println("HEALTH ");
        System.out.println("***** " + game.player().getHealthBar().getHealth() + " *****");
    }

    // EFFECTS: displays the player's wallet balance
    public void displayWallet() {
        System.out.println("WALLET BALANCE ");
        System.out.println("***** " + game.player().getWallet().getBalance() + " *****");
    }

    // EFFECTS: Displays the in-game pause menu

    public void pauseGame() {
        clearScreen();
        while (true) {
            System.out.println("GAME PAUSED");
            System.out.println("\t1. Save Game");
            System.out.println("\t2. Main Menu");
            System.out.println("\t3. Exit Game");
            System.out.println("\t4. Return to Game");
            System.out.print("Enter here: ");

            Scanner input = new Scanner(System.in);
            String keyPress = input.next();

            clearScreen();

            if (keyPress.equals("1")) {
//                saveGame();
                return;
            } else if (keyPress.equals("2")) {
                new RogueLikeGameMainMenu();
            } else if (keyPress.equals("3")) {
                System.out.println("Thank you for playing!");
                System.exit(0);
            } else if (keyPress.equals("4")) {
                return;
            }
        }
    }

    // MODIFIES: game
    // EFFECTS: Checks if the player has collided with a coin or Small Health Potions and adds the coins to the player's
    // wallet and Small Health Potion to the player's wallet unless the inventory cell is full.
    // Returns true if the game is still running and returns false otherwise.
    public boolean handleCollisions() {
        Position currentPlayerPosition = game.player().getPosition();

        if (game.coin().getPositionSet().contains(currentPlayerPosition)) {
            game.coin().getPositionSet().remove(currentPlayerPosition);
            game.player().addToWallet(BASIC_COIN_WORTH);
        } else if (game.smallHealthPotion().getPositionSet().contains(currentPlayerPosition)) {
            try {
                game.player().getInventory().addOneSmallHealthPotion();
                game.smallHealthPotion().getPositionSet().remove(currentPlayerPosition);
            } catch (CellAtMaximumOrMinimumException e) {
                System.out.println("INVENTORY FULL!");
            }
        }

        return true;
    }

    // EFFECTS: displays the ending message
    public void endScreen(String endMessage) {
        System.out.println(endMessage);
        System.exit(0);
    }

//    // MODIFIES: this
//    // EFFECTS: saves the game to a save file
//    public void saveGame() {
//        clearScreen();
//        System.out.println("CHOOSE A SAVE FILE");
//        System.out.println("\tSave File 1");
//        System.out.println("\tSave File 2");
//        System.out.println("\tSave File 3");
//        System.out.print("Enter here: ");
//
//        Scanner input = new Scanner(System.in);
//        String keyPress = input.next();
//
//        if (keyPress.equals("1")) {
//            writeToSaveFile("1",JSON_STORE_SAVE_FILE_ONE);
//        } else if (keyPress.equals("2")) {
//            writeToSaveFile("2",JSON_STORE_SAVE_FILE_TWO);
//        } else if (keyPress.equals("3")) {
//            writeToSaveFile("3",JSON_STORE_SAVE_FILE_THREE);
//        }
//
//    }

    // Code citation: JsonSerializationDemo (CPSC 210; The University of British Columbia, Vancouver)
    // MODIFIES: this
    // EFFECTS: writes a save file of given number to a source
    //          catches a FileNotFoundException if is unable to write to the file
    public void writeToSaveFile(String number, String source) {
        System.out.println();
        try {
            while (true) {
                System.out.println("DO YOU WISH TO OVERWRITE? y/n");
                System.out.print("Enter here: ");

                Scanner scanner = new Scanner(System.in);
                String keyInput = scanner.next();

                if (keyInput.equals("n") || keyInput.equals("N")) {
                    return;
                } else if (keyInput.equals("y") || keyInput.equals("Y")) {
                    JsonWriter jsonWriter = new JsonWriter(source);
                    jsonWriter.open();
                    jsonWriter.write(game);
                    jsonWriter.close();
                    System.out.println("Saved to save file " + number + " at " + source);
                    return;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + source);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pauseButton) {
            new PauseMenu(this,game);
        } else if (e.getSource() == descendButton) {
            boolean isLevelOver = game.player().interact("e", game);
            if (isLevelOver) {
//                removeAll();
//                add(new GameOverLabel(
//                        "You've won!\nThank You For Playing!",
//                        frameContentPaneWidth,
//                        frameContentPaneHeight
//                ));
//                validate();
//                repaint();
                gameAudioPlayer.play(PLAYER_WON_SOUND);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException interruptedException) {
//                    System.out.println("This interrupt should not have occurred!");
//                }
                dispose();
                new MainMenu();
            }
        } else if (e.getSource() == useSmallHealthPotionButton) {
            if (game.player().getInventory().hasAtLeastOneSmallHealthPotion()) {
                SmallHealthPotion.use(game.player().getHealthBar());
                try {
                    game.player().getInventory().subtractOneSmallHealthPotion();
                } catch (CellAtMaximumOrMinimumException exception) {
                    // let exception be for now
                }
            } else {
                System.out.println("Player has no Small Health Potions!");
                // ???
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        boolean gameIsRunning = (handleCollisions() && handleGameEvents());

        String keyPress = String.valueOf(e.getKeyChar());
        if (!gameIsRunning) {
//            removeAll();
//            JLabel gameOverLabel = new GameOverLabel(
//                    "Game Over!",
//                    frameContentPaneWidth,
//                    frameContentPaneHeight
//            );
//            add(gameOverLabel);
//            validate();
//            repaint();
            gameAudioPlayer.play(PLAYER_IS_DEAD_SOUND);
//            try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException interruptedException) {
//                    System.out.println("This interrupt should not have occurred!");
//                }
            dispose();
            new MainMenu();
        }

        switch (keyPress) {
            case "w":
            case "a":
            case "s":
            case "d":
                game.player().move(keyPress, game);
            default:
                break;
        }

        if (gameIsRunning) {
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
