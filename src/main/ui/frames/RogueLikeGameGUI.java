package ui;

import exceptions.CellAtMaximumOrMinimumException;
import model.*;
import model.tile.SmallHealthPotion;
import persistence.JsonWriter;
import ui.frames.MainMenu;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.util.Scanner;

// A class that sets up the game's user interface. Allows the user to save the game to a file as well.
public class RogueLikeGameGUI extends JFrame implements ActionListener, KeyListener {
    private static final int FRAME_WIDTH = MainMenu.FRAME_WIDTH;
    private static final int FRAME_HEIGHT = MainMenu.FRAME_HEIGHT;
    private int frameContentPaneWidth;
    private int frameContentPaneHeight;

    private static final int BASIC_COIN_WORTH = 1;

    private static int gameTerminalWidth;
    private static int gameTerminalHeight;

    private Game game;

    // COMPONENTS
    // PANELS
    private JPanel gamePanel;
    // LABELS
    private JLabel controlsAndInformationLabel;
    private JLabel coinCountLabel;
    // BUTTONS
    private JButton pauseButton;
    private JButton inventoryButton;

    // PROGRESS BAR
    private JProgressBar healthBar;


    // MODIFIES: this
    // EFFECTS: sets up the width and height of the game arena, creates a new game
    public RogueLikeGameGUI(int width, int height) {
        super("RogueLikeGame");
        this.game = new Game();
        initializeFields(width,height);
        initializeGraphics();
        initializeInteraction();
//        runRogueLikeGame();
    }

    // MODIFIES: this
    // EFFECTS: loads the game from another Game instance
    public RogueLikeGameGUI(Game other, int width, int height) {
        super("RogueLikeGame");
        this.game = other;
        initializeFields(width,height);
        initializeGraphics();
        initializeInteraction();
//        runRogueLikeGame();
    }

    private void initializeInteraction() {
        addKeyListener(this);
    }

    // MODIFIES: this
    // EFFECTS: Sets the the number of rows and columns in the game
    private void initializeFields(int width, int height) {
        RogueLikeGameGUI.gameTerminalWidth = width;
        RogueLikeGameGUI.gameTerminalHeight = height;
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

    // MODIFIES: game
    // EFFECTS: performs the Game map setup, enters the game loop, and exits on game over or level complete
    public void runRogueLikeGame() {
        boolean gameIsRunning = true;
        boolean levelIsOver = false;

        while (gameIsRunning && (!levelIsOver)) {
            displayControlsAndInformation();
            displayPlayerHealth();
            displayWallet();
            displayMap();
            displayInventory();
            levelIsOver = handleKeyEvent();
            gameIsRunning = (handleCollisions() && handleGameEvents());
            this.repaint();
        }

        if (gameIsRunning) {
            endScreen("You have won!\nThank You For Playing!");
        } else {
            endScreen("Game Over :(\nYou've lost");
        }

    }

    // MODIFIES: this
    // EFFECTS: Initializes all the Game Frame Components
    private void initializeGraphics() {
        // FRAME CHARACTERISTICS
        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        setVisible(true);

//        System.out.println(getInsets().top);
//        System.out.println(getInsets().bottom);
//        System.out.println(getInsets().left);
//        System.out.println(getInsets().right);

        frameContentPaneWidth = (int) getContentPane().getSize().getWidth();
        frameContentPaneHeight = (int) getContentPane().getSize().getHeight();

//        System.out.println(frameContentPaneHeight);
//        System.out.println(frameContentPaneWidth);

        // PANELS
        gamePanel = new JPanel();
        gamePanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        gamePanel.setLayout(new GridLayout(gameTerminalHeight,gameTerminalWidth));
        gamePanel.setBounds(
                frameContentPaneWidth / 8,
                frameContentPaneHeight / 13,
                frameContentPaneWidth * 3 / 4,
                frameContentPaneHeight * 3 / 4);

        for (int j = 0; j < gameTerminalHeight;++j) {

            for (int i = 0; i < gameTerminalWidth;++i) {

                if (game.player().getPosition().equals(new Position(i,j))) {
                    gamePanel.add(new JLabel(game.player().display(" ")));

                } else if (game.enemy().getPositionSet().contains(new Position(i,j))) {
                    gamePanel.add(new JLabel(game.enemy().display(" ")));

                } else if (game.coin().getPositionSet().contains(new Position(i,j))) {
                    gamePanel.add(new JLabel(game.coin().display(" ")));

                } else if (game.smallHealthPotion().getPositionSet().contains(new Position(i,j))) {
                    gamePanel.add(new JLabel(game.smallHealthPotion().display(" ")));

                } else if (game.entryPoint().getPosition().equals(new Position(i,j))) {
                    gamePanel.add(new JLabel(game.entryPoint().display(" ")));

                } else if (game.exitPoint().getPosition().equals(new Position(i,j))) {
                    gamePanel.add(new JLabel(game.exitPoint().display(" ")));

                } else if (game.spike().getPositionSet().contains(new Position(i,j))) {
                    gamePanel.add(new JLabel(game.spike().display(" ")));

                } else if (game.wall().getPositionSet().contains(new Position(i,j))) {
                    gamePanel.add(new JLabel(game.wall().display(" ")));

                } else {
                    gamePanel.add(new JLabel(game.air().display(" ")));

                }
            }
        }

        // LABELS
        controlsAndInformationLabel = new JLabel("temp",JLabel.CENTER);
        controlsAndInformationLabel.setLayout(null);
        String labelText;
        labelText = "Use WASD to move around and the space \nbar to interact with your surroundings.";
        String labelTextWithHtml = "<html>" + labelText.replaceAll("\n", "<br/>") + "</html>";
        controlsAndInformationLabel.setText(labelTextWithHtml);
        controlsAndInformationLabel.setBackground(Color.WHITE);
        controlsAndInformationLabel.setOpaque(true);
        controlsAndInformationLabel.setFont(new Font("Century Gothic (Body)",Font.BOLD, 14));
        controlsAndInformationLabel.setVerticalAlignment(JLabel.CENTER);
        controlsAndInformationLabel.setHorizontalAlignment(JLabel.CENTER);
//        System.out.println(controlsAndInformationLabel.getForeground());
        controlsAndInformationLabel.setForeground(Color.BLACK);
        controlsAndInformationLabel.setBounds(
                0,
                frameContentPaneHeight - frameContentPaneHeight / 6,
                frameContentPaneWidth / 3,
                frameContentPaneHeight / 6);
//        System.out.println(controlsAndInformationLabel.getX());
//        System.out.println(controlsAndInformationLabel.getY());

        coinCountLabel = new JLabel();
        coinCountLabel.setText("Coins: " + game.player().getWallet().getBalance());
        coinCountLabel.setBackground(Color.WHITE);
        coinCountLabel.setOpaque(true);
        coinCountLabel.setFont(new Font("Century Gothic (Body)",Font.BOLD, 14));
        coinCountLabel.setVerticalAlignment(JLabel.CENTER);
        coinCountLabel.setHorizontalAlignment(JLabel.CENTER);
        controlsAndInformationLabel.setForeground(Color.BLACK);
        coinCountLabel.setBounds(
                frameContentPaneWidth - frameContentPaneWidth / 3,
                frameContentPaneHeight - frameContentPaneHeight / 6,
                frameContentPaneWidth / 3,
                frameContentPaneHeight / 6);

        // BUTTONS
        pauseButton = new JButton();
        pauseButton.setText("Pause");
        pauseButton.setBounds(
                frameContentPaneWidth - frameContentPaneWidth / 16,
                0,
                frameContentPaneWidth / 16,
                frameContentPaneWidth / 16);

        pauseButton.setBackground(Color.RED);
        pauseButton.setFont(new Font("Century Gothic (Body)",Font.BOLD, 14));
        pauseButton.setForeground(Color.WHITE);
        pauseButton.addActionListener(this);
        pauseButton.setFocusable(false);
        pauseButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));

        inventoryButton = new JButton();
        inventoryButton.setText("Inventory");
        inventoryButton.setBounds(
                frameContentPaneWidth - frameContentPaneWidth * 7 / 12,
                frameContentPaneHeight - frameContentPaneHeight / 6,
                frameContentPaneWidth / 6,
                frameContentPaneHeight / 6);

        inventoryButton.setBackground(new Color(0x8B4513));
        inventoryButton.setFont(new Font("Century Gothic (Body)",Font.BOLD, 14));
        inventoryButton.setForeground(Color.WHITE);
        inventoryButton.addActionListener(this);
        inventoryButton.setFocusable(false);
//        inventoryButton.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

        // int HORIZONTAL_ORIENTATION = 0;
        // PROGRESS BAR
        healthBar = new JProgressBar(0,HealthBar.ZERO_HEALTH,HealthBar.MAX_HEALTH);
        healthBar.setBorderPainted(true);
        healthBar.setStringPainted(true);
        healthBar.setValue(game.player().getHealthBar().getHealth());
        healthBar.setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        healthBar.setBackground(Color.RED);
        System.out.println(healthBar.getBackground());
//        System.out.println(healthBar.getColorModel());
//        healthBar.setString("HP");
//        System.out.println(healthBar.isDisplayable());
//        System.out.println(healthBar.isStringPainted());
        healthBar.setOpaque(true);
        healthBar.setBounds(
                0,
                0,
                frameContentPaneWidth / 4,
                frameContentPaneHeight / 16);

        add(gamePanel);
        add(controlsAndInformationLabel);
        add(coinCountLabel);
        add(pauseButton);
        add(inventoryButton);
        add(healthBar);
        repaint();
//        setVisible(true);
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
                } else if (game.smallHealthPotion().getPositionSet().contains(new Position(i,j))) {
                    game.smallHealthPotion().display(" ");
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

    // REQUIRES: The game must be initialized
    // MODIFIES: game
    // EFFECTS: Takes the user's input for the player character.
    // Returns true if the level is over otherwise returns false.
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
            case "p":
                pauseGame();
                break;
            case "o":
                openInventory();
                break;
            case "e":
                return game.player().interact(keyPress, game);

            default:
        }

        return false;
    }

    // EFFECTS:
    public void openInventory() {
        int numberOfSmallHealthPotions = game.player().getInventory().getNumberOfSmallHealthPotions();

        while (true) {
            System.out.println();
            System.out.println("INVENTORY OPERATIONS");
            System.out.println("\t1. Use Small Health Potion");
            System.out.println("\t2. Return to Game");
            System.out.print("Enter here: ");

            Scanner input = new Scanner(System.in);
            String keyPress = input.next();

            if (keyPress.equals("1")) {
                if (game.player().getInventory().hasAtLeastOneSmallHealthPotion()) {
                    SmallHealthPotion.use(game.player().getHealthBar());
                    try {
                        game.player().getInventory().subtractOneSmallHealthPotion();
                        return;
                    } catch (CellAtMaximumOrMinimumException e) {
                        return;
                    }
                } else {
                    System.out.println("Player has no Small Health Potions!");
                }
            } else if (keyPress.equals("2")) {
                return;
            }
        }
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

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e.getKeyChar());
        String keyPress = String.valueOf(Character.toLowerCase(e.getKeyChar()));
        switch (keyPress) {
            case "w":
            case "a":
            case "s":
            case "d":
                game.player().move(keyPress, game);
            default:
                break;
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            game.player().interact("e",game);
        }
        initializeGraphics();
        validate();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
