package ui.panels;

import model.Game;
import model.Position;
import model.tile.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

// A panel within which the game is rendered
public class GamePanel extends JPanel {

    private int gameTerminalWidth;
    private int gameTerminalHeight;
    private Game game;
    boolean isLevelOver;

    // ICONS
    private int iconDimension;
    private ImageIcon playerIcon;
    private ImageIcon coinIcon;
    private ImageIcon wallIcon;
    private ImageIcon airIcon;
    private ImageIcon smallHealthPotionIcon;
    private ImageIcon enemyIcon;
    private ImageIcon entryPointIcon;
    private ImageIcon exitPointIcon;
    private ImageIcon spikeIcon;

    // EFFECTS: Initializes all the components of the game panel
    public GamePanel(
            int contentPaneWidth,
            int contentPaneHeight,
            int gameTerminalWidth,
            int gameTerminalHeight,
            Game game) {
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        setLayout(new GridLayout(gameTerminalHeight,gameTerminalWidth));
//        setBounds(
//                contentPaneWidth / 8,
//                contentPaneHeight / 13,
//                contentPaneWidth * 3 / 4,
//                contentPaneHeight * 3 / 4);

        setBounds(
                contentPaneWidth / 2 - contentPaneWidth / 4,
                0,
                contentPaneHeight,
                contentPaneHeight
        );

        this.gameTerminalWidth = gameTerminalWidth;
        this.gameTerminalHeight = gameTerminalHeight;
        this.game = game;
        this.isLevelOver = false;
        this.iconDimension = contentPaneHeight / gameTerminalHeight;
        initializeLabelIcons();
    }

    // MODIFIES: this
    // EFFECTS: Initializes all the labels with icons set to their appropriate sizes
    private void initializeLabelIcons() {
        initializePlayerIcon();
        initializeCoinIcon();
        initializeWallIcon();
//        initializeAirIcon();
        initializeSmallHealthPotionIcon();
        initializeSpikeIcon();
        initializeExitPointIcon();
        initializeEntryPointIcon();
        initializeEnemyIcon();
    }

    // MODIFIES: this
    // EFFECTS: Creates a new wall icon with the icon dimension and wall image
    private void initializeWallIcon() {
        wallIcon = new ImageIcon(Wall.WALL_TILE_IMAGE_SOURCE);
        Image image = wallIcon.getImage();
        Image newWallImage = image.getScaledInstance(iconDimension,iconDimension,Image.SCALE_FAST);
        wallIcon = new ImageIcon(newWallImage);
    }

    // MODIFIES: this
    // EFFECTS: Creates a new enemy icon with the icon dimension and enemy image
    private void initializeEnemyIcon() {
        enemyIcon = new ImageIcon(Enemy.ENEMY_TILE_IMAGE_SOURCE);
        Image enemyImage = enemyIcon.getImage();
        Image newEnemyImage = enemyImage.getScaledInstance(iconDimension,iconDimension,Image.SCALE_SMOOTH);
        enemyIcon = new ImageIcon(newEnemyImage);
    }

    // MODIFIES: this
    // EFFECTS: Creates a new entry point icon with the icon dimension and entry point image
    private void initializeEntryPointIcon() {
        entryPointIcon = new ImageIcon(EntryPoint.ENTRY_POINT_TILE_IMAGE_SOURCE);
        Image entryPointImage = entryPointIcon.getImage();
        Image newEntryPointImage = entryPointImage.getScaledInstance(iconDimension,iconDimension,Image.SCALE_SMOOTH);
        entryPointIcon = new ImageIcon(newEntryPointImage);
    }

    // MODIFIES: this
    // EFFECTS: Creates a new exit point icon with the icon dimension and exit point image
    private void initializeExitPointIcon() {
        exitPointIcon = new ImageIcon(ExitPoint.EXIT_POINT_TILE_IMAGE_SOURCE);
        Image exitPointImage = exitPointIcon.getImage();
        Image newExitPointImage = exitPointImage.getScaledInstance(iconDimension,iconDimension,Image.SCALE_SMOOTH);
        exitPointIcon = new ImageIcon(newExitPointImage);
    }

    // MODIFIES: this
    // EFFECTS: Creates a new spike icon with the icon dimension and spike image
    private void initializeSpikeIcon() {
        spikeIcon = new ImageIcon(Spike.SPIKE_TILE_IMAGE_SOURCE);
        Image spikeImage = spikeIcon.getImage();
        Image newSpikeImage = spikeImage.getScaledInstance(iconDimension,iconDimension,Image.SCALE_SMOOTH);
        spikeIcon = new ImageIcon(newSpikeImage);
    }

    // MODIFIES: this
    // EFFECTS: Creates a new small health potion icon with the icon dimension and small health potion image
    private void initializeSmallHealthPotionIcon() {
        smallHealthPotionIcon = new ImageIcon(SmallHealthPotion.SMALL_HEALTH_POTION_TILE_IMAGE_SOURCE);
        Image smallHealthPotionImage = smallHealthPotionIcon.getImage();
        Image newSmallHealthPotionImage = smallHealthPotionImage.getScaledInstance(
                iconDimension,
                iconDimension,
                Image.SCALE_SMOOTH);
        smallHealthPotionIcon = new ImageIcon(newSmallHealthPotionImage);
    }

    // MODIFIES: this
    // EFFECTS: Creates a new air icon with the icon dimension and air image
    private void initializeAirIcon() {
        airIcon = new ImageIcon(Air.AIR_TILE_IMAGE_SOURCE);
        Image airImage = airIcon.getImage();
        Image newImage = airImage.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        airIcon = new ImageIcon(newImage);
    }

    // MODIFIES: this
    // EFFECTS: Creates a new coin icon with the icon dimension and coin image
    private void initializeCoinIcon() {
        coinIcon = new ImageIcon(Coin.COIN_TILE_IMAGE_SOURCE);
        Image coinImage = coinIcon.getImage();
        Image newCoinImage = coinImage.getScaledInstance(iconDimension,iconDimension,Image.SCALE_SMOOTH);
        coinIcon = new ImageIcon(newCoinImage);
    }

    // MODIFIES: this
    // EFFECTS: Creates a new player icon with the icon dimension and player image
    private void initializePlayerIcon() {
        playerIcon = new ImageIcon(Player.PLAYER_TILE_IMAGE_SOURCE);
        Image playerImage = playerIcon.getImage();
        Image newPlayerImage = playerImage.getScaledInstance(iconDimension,iconDimension,Image.SCALE_SMOOTH);
        playerIcon = new ImageIcon(newPlayerImage);
    }

    // MODIFIES: this
    // EFFECTS: sets whether the level is over
    public void setIsLevelOver(boolean value) {
        this.isLevelOver = value;
    }

    // MODIFIES: this
    // EFFECTS: Checks if the level is not over and draws the game
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isLevelOver) {
            drawGame();
        }
    }

    // MODIFIES: this
    // EFFECTS: Draws the game panel
    public void drawGame() {
        removeAll();
        drawGrid();
        validate();
    }

    // EFFECTS: Creates and returns a new label with the image icon provided
    public JLabel createLabelWithIcon(ImageIcon icon) {
        JLabel label = new JLabel();
        label.setIcon(icon);
        return label;
    }

    // MODIFIES: this
    // EFFECTS: Creates all the labels that must be added to the game panel
    public void drawGrid() {
        for (int j = 0; j < gameTerminalHeight;++j) {

            for (int i = 0; i < gameTerminalWidth;++i) {

                if (game.player().getPosition().equals(new Position(i,j))) {
                    add(createLabelWithIcon(playerIcon));

                } else if (game.enemy().getPositionSet().contains(new Position(i,j))) {
                    add(createLabelWithIcon(enemyIcon));

                } else if (game.coin().getPositionSet().contains(new Position(i,j))) {
                    add(createLabelWithIcon(coinIcon));

                } else if (game.smallHealthPotion().getPositionSet().contains(new Position(i,j))) {
                    add(createLabelWithIcon(smallHealthPotionIcon));

                } else if (game.entryPoint().getPosition().equals(new Position(i,j))) {
                    add(createLabelWithIcon(entryPointIcon));

                } else if (game.exitPoint().getPosition().equals(new Position(i,j))) {
                    add(createLabelWithIcon(exitPointIcon));

                } else if (game.spike().getPositionSet().contains(new Position(i,j))) {
                    add(createLabelWithIcon(spikeIcon));

                } else if (game.wall().getPositionSet().contains(new Position(i,j))) {
                    add(createLabelWithIcon(wallIcon));

                } else {
                    add(createLabelWithIcon(airIcon));

                }
            }
        }
    }
}
