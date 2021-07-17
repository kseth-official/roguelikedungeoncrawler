package ui.frames;

import exceptions.CellAtMaximumOrMinimumException;
import model.*;
import model.tile.SmallHealthPotion;
import ui.GameAudioPlayer;
import ui.buttons.*;
import ui.labels.ControlsLabel;
import ui.panels.GameInformationPanel;
import ui.panels.GamePanel;
import ui.progressbars.HealthProgressBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// A class that sets up the game's user interface.
public class RogueLikeGameGUI extends JFrame implements ActionListener, KeyListener {
    private static final int FRAME_WIDTH = MainMenu.FRAME_WIDTH;
    private static final int FRAME_HEIGHT = MainMenu.FRAME_HEIGHT;

    private static final int BASIC_COIN_WORTH = 1;
    public static final int GAME_FINAL_LEVEL = 3;
    private static final String PLAYER_IS_DEAD_SOUND = "./data/audio/mixkit-spooky-game-over-1948.wav";
    private static final String PLAYER_WON_SOUND = "./data/audio/mixkit-game-level-completed-2059.wav";
    private static final String LEVEL_OVER_SOUND = "./data/audio/mixkit-long-pop-2358.wav";

    private static int gameTerminalWidth;
    private static int gameTerminalHeight;

    private int frameContentPaneWidth;
    private int frameContentPaneHeight;

    private Game game;
    private GameAudioPlayer gameAudioPlayer;
    private int levelCount;

    // COMPONENTS
    // PANELS
    private JPanel gamePanel;
    private JPanel inventoryPanel;

    // LABELS
    private JLabel controlsAndInformationLabel;

    // BUTTONS
    private JButton pauseButton;
    private JButton useSmallHealthPotionButton;
    private JButton descendButton;
    private JButton buyPotionButton;
    private JButton disposeCoinButton;
//    private JButton inventoryButton;
//    private JButton shopButton;

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
        buyPotionButton.addActionListener(this);
        disposeCoinButton.addActionListener(this);
//        inventoryButton.addActionListener(this);
//        shopButton.addActionListener(this);
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


    // MODIFIES: this
    // EFFECTS: Initializes all the Game Frame Components
    public void initializeGraphics() {
        setFrameCharacteristics();

        initializeGamePanels();

        // LABELS
        controlsAndInformationLabel = new ControlsLabel(
                frameContentPaneWidth,
                frameContentPaneHeight
        );

        initializeGameButtons();

        // PROGRESS BAR
        healthBar = new HealthProgressBar(frameContentPaneWidth,frameContentPaneHeight,game);

        addComponentsToFrame();
    }

    // MODIFIES: this
    // EFFECTS: adds the components to the current frame
    public void addComponentsToFrame() {
        add(gamePanel);
        add(controlsAndInformationLabel);
        add(inventoryPanel);
        add(pauseButton);
        add(descendButton);
        add(buyPotionButton);
        add(disposeCoinButton);
//        add(inventoryButton);
//        add(shopButton);
        add(useSmallHealthPotionButton);
        add(healthBar);
    }

    // MODIFIES: this
    // EFFECTS: initializes the game buttons
    public void initializeGameButtons() {
        // BUTTONS
        pauseButton = new PauseButton(frameContentPaneWidth,frameContentPaneHeight);
        useSmallHealthPotionButton = new UsePotionButton(frameContentPaneWidth,frameContentPaneHeight);
        descendButton = new DescendButton(frameContentPaneWidth, frameContentPaneHeight);
        buyPotionButton = new BuyPotionButton(frameContentPaneWidth, frameContentPaneHeight);
        disposeCoinButton = new DisposeCoinButton(frameContentPaneWidth, frameContentPaneHeight);
//        inventoryButton = new InventoryButton(frameContentPaneWidth, frameContentPaneHeight);
//        shopButton = new ShopButton(frameContentPaneWidth, frameContentPaneHeight);
    }

    // MODIFIES: this
    // EFFECTS: initializes the game panels
    public void initializeGamePanels() {
        // PANELS
        gamePanel = new GamePanel(
                frameContentPaneWidth,
                frameContentPaneHeight,
                gameTerminalWidth,
                gameTerminalHeight,
                game);

        inventoryPanel = new GameInformationPanel(
                frameContentPaneWidth,
                frameContentPaneHeight,
                game);
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

        frameContentPaneWidth = (int) getContentPane().getSize().getWidth();
        frameContentPaneHeight = (int) getContentPane().getSize().getHeight();
    }

    // EFFECTS: Handles in game events. Returns true if the game should be running else returns false.
    public boolean handleGameEvents() {
        if (game.player().getHealthBar().isZero()) {
            return false;
        }
        return true;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pauseButton) {
            new PauseMenu(this,game);
        } else if (e.getSource() == descendButton) {
            boolean isLevelOver = game.player().interact("e", game);
            boolean isGameOver = isLevelOver && (game.getLevelNumber() == GAME_FINAL_LEVEL);
            if (isLevelOver) {
                if (isGameOver) {
                    gameAudioPlayer.play(PLAYER_WON_SOUND);
                    dispose();
                    new MainMenu();
                    return;
                }
                gameAudioPlayer.play(LEVEL_OVER_SOUND);
                game.generateLevel(game.getLevelNumber() + 1,game.player());
            }
        } else if (e.getSource() == useSmallHealthPotionButton) {
            useSmallHealthPotionIfPossible();
        } else if (e.getSource() == buyPotionButton) {
            buyPotionIfPossible();
        } else if (e.getSource() == disposeCoinButton) {
            if (game.player().getWalletBalance() > 0) {
                game.player().setWalletBalance(game.player().getWalletBalance() - 1);
            }
        }
        repaint();
    }


    // MODIFIES: Game
    // EFFECTS: If player inventory has at least one small health potion, removes that health
    // potion, uses it, and adds 25 health to the player's health bar.
    public void useSmallHealthPotionIfPossible() {
        if (game.player().getInventory().hasAtLeastOneSmallHealthPotion()) {
            SmallHealthPotion.use(game.player().getHealthBar());
            try {
                game.player().getInventory().subtractOneSmallHealthPotion();
            } catch (CellAtMaximumOrMinimumException exception) {
                // let exception be for now
            }
        } else {
            System.out.println("Player has no Small Health Potions!");
        }
    }

    // MODIFIES: Player.Wallet && Player.Inventory
    // EFFECTS: If player has a wallet balance of 10 or more and player small health potion capacity
    // isn't full, subtracts 10 from the wallet and adds a potion to the inventory.
    public void buyPotionIfPossible() {
        int walletBalance = game.player().getWallet().getBalance();
        boolean isPotionSetFull = game.player().getInventory().smallHealthPotionSetIsFull();
        if (walletBalance >= 10 && !isPotionSetFull) {
            game.player().getWallet().setBalance(walletBalance - 10);
            try {
                game.player().getInventory().addOneSmallHealthPotion();
            } catch (CellAtMaximumOrMinimumException exception) {
                System.out.println("An exception that shouldn't have been thrown was thrown!");
            }
        }
    }


    // MODIFIES: game
    // EFFECTS: Takes the typed in key and performs player movement, checking for collisions, and
    // checking for game events.
    @Override
    public void keyTyped(KeyEvent e) {
        boolean gameIsRunning = (handleCollisions() && handleGameEvents());

        String keyPress = String.valueOf(e.getKeyChar());
        if (!gameIsRunning) {
            gameAudioPlayer.play(PLAYER_IS_DEAD_SOUND);
            dispose();
            new MainMenu();
        }

        switch (keyPress) {
            case "w":
            case "a":
            case "s":
            case "d":
                game.player().move(keyPress, game);
                game.enemy().move(game);
            default:
                break;
        }

        if (gameIsRunning) {
            repaint();
        }
    }

    // UNUSED
    @Override
    public void keyPressed(KeyEvent e) {

    }

    // UNUSED
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
