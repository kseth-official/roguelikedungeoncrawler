package ui.panels;

import javafx.print.PageLayout;
import model.Game;
import model.Position;
import model.tile.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

// A panel within which the game is rendered
public class GamePanel extends JPanel {

    private int gameTerminalWidth;
    private int gameTerminalHeight;
    private Game game;
    boolean isLevelOver;

    // JLABELS
//    private JLabel playerLabel;
//    private JLabel wallLabel;
//    private JLabel coinLabel;

    // ICONS
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
                contentPaneWidth / 2 - contentPaneHeight * 3 / 8,
                contentPaneHeight / 13,
                contentPaneHeight * 3 / 4,
                contentPaneHeight * 3 / 4);

        this.gameTerminalWidth = gameTerminalWidth;
        this.gameTerminalHeight = gameTerminalHeight;
        this.game = game;
        this.isLevelOver = false;
        initializeLabel();
    }

    // MODIFIES: this
    // EFFECTS: Initializes all the labels with icons set to their appropriate sizes
    private void initializeLabel() {

        playerIcon = new ImageIcon(Player.PLAYER_TILE_IMAGE_SOURCE);
        Image playerImage = playerIcon.getImage();
        Image newPlayerImage = playerImage.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        playerIcon = new ImageIcon(newPlayerImage);

        coinIcon = new ImageIcon(Coin.COIN_TILE_IMAGE_SOURCE);
        Image coinImage = coinIcon.getImage();
        Image newCoinImage = coinImage.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        coinIcon = new ImageIcon(newCoinImage);

        wallIcon = new ImageIcon(Wall.WALL_TILE_IMAGE_SOURCE);
        Image image = wallIcon.getImage();
        Image newWallImage = image.getScaledInstance(40,40,Image.SCALE_FAST);
        wallIcon = new ImageIcon(newWallImage);

//        airIcon = new ImageIcon(Air.AIR_TILE_IMAGE_SOURCE);
//        Image airImage = airIcon.getImage();
//        Image newImage = airImage.getScaledInstance(40,40,Image.SCALE_SMOOTH);
//        airIcon = new ImageIcon(newImage);

        smallHealthPotionIcon = new ImageIcon(SmallHealthPotion.SMALL_HEALTH_POTION_TILE_IMAGE_SOURCE);
        Image smallHealthPotionImage = smallHealthPotionIcon.getImage();
        Image newSmallHealthPotionImage = smallHealthPotionImage.getScaledInstance(
                40,
                40,
                Image.SCALE_SMOOTH);
        smallHealthPotionIcon = new ImageIcon(newSmallHealthPotionImage);

        spikeIcon = new ImageIcon(Spike.SPIKE_TILE_IMAGE_SOURCE);
        Image spikeImage = spikeIcon.getImage();
        Image newSpikeImage = spikeImage.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        spikeIcon = new ImageIcon(newSpikeImage);

        exitPointIcon = new ImageIcon(ExitPoint.EXIT_POINT_TILE_IMAGE_SOURCE);
        Image exitPointImage = exitPointIcon.getImage();
        Image nexExitPointImage = exitPointImage.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        exitPointIcon = new ImageIcon(nexExitPointImage);

        entryPointIcon = new ImageIcon(EntryPoint.ENTRY_POINT_TILE_IMAGE_SOURCE);
        Image entryPointImage = entryPointIcon.getImage();
        Image newEntryPointImage = entryPointImage.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        entryPointIcon = new ImageIcon(newEntryPointImage);

        enemyIcon = new ImageIcon(Enemy.ENEMY_TILE_IMAGE_SOURCE);
        Image enemyImage = enemyIcon.getImage();
        Image newEnemyImage = enemyImage.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        enemyIcon = new ImageIcon(newEnemyImage);


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

    // MODIFIES: this
    // EFFECTS: Creates all the labels that must be added to the game panel
    public void drawGrid() {
//        for (int j = 0; j < gameTerminalHeight;++j) {
//
//            for (int i = 0; i < gameTerminalWidth;++i) {
//
//                if (game.player().getPosition().equals(new Position(i,j))) {
//                    add(new JLabel(game.player().display(" ")));
//
//                } else if (game.enemy().getPositionSet().contains(new Position(i,j))) {
//                    add(new JLabel(game.enemy().display(" ")));
//
//                } else if (game.coin().getPositionSet().contains(new Position(i,j))) {
//                    add(new JLabel(game.coin().display(" ")));
//
//                } else if (game.smallHealthPotion().getPositionSet().contains(new Position(i,j))) {
//                    add(new JLabel(game.smallHealthPotion().display(" ")));
//
//                } else if (game.entryPoint().getPosition().equals(new Position(i,j))) {
//                    add(new JLabel(game.entryPoint().display(" ")));
//
//                } else if (game.exitPoint().getPosition().equals(new Position(i,j))) {
//                    add(new JLabel(game.exitPoint().display(" ")));
//
//                } else if (game.spike().getPositionSet().contains(new Position(i,j))) {
//                    add(new JLabel(game.spike().display(" ")));
//
//                } else if (game.wall().getPositionSet().contains(new Position(i,j))) {
//                    add(new JLabel(game.wall().display(" ")));
//
//                } else {
//                    add(new JLabel(game.air().display(" ")));
//                }
//            }
//        }
        for (int j = 0; j < gameTerminalHeight;++j) {

            for (int i = 0; i < gameTerminalWidth;++i) {

                if (game.player().getPosition().equals(new Position(i,j))) {
                    JLabel playerLabel = new JLabel();
                    playerLabel.setIcon(playerIcon);
                    add(playerLabel);

                } else if (game.enemy().getPositionSet().contains(new Position(i,j))) {
                    JLabel label = new JLabel();
                    label.setIcon(enemyIcon);
                    add(label);

                } else if (game.coin().getPositionSet().contains(new Position(i,j))) {
                    JLabel coinLabel = new JLabel();
                    coinLabel.setIcon(coinIcon);
                    add(coinLabel);

                } else if (game.smallHealthPotion().getPositionSet().contains(new Position(i,j))) {
                    JLabel label = new JLabel();
                    label.setIcon(smallHealthPotionIcon);
                    add(label);

                } else if (game.entryPoint().getPosition().equals(new Position(i,j))) {
                    JLabel label = new JLabel();
                    label.setIcon(entryPointIcon);
                    add(label);

                } else if (game.exitPoint().getPosition().equals(new Position(i,j))) {
                    JLabel label = new JLabel();
                    label.setIcon(exitPointIcon);
                    add(label);

                } else if (game.spike().getPositionSet().contains(new Position(i,j))) {
                    JLabel label = new JLabel();
                    label.setIcon(spikeIcon);
                    add(label);

                } else if (game.wall().getPositionSet().contains(new Position(i,j))) {
                    JLabel wallLabel = new JLabel();
                    wallLabel.setIcon(wallIcon);
                    add(wallLabel);

                } else {
                    JLabel label = new JLabel();
                    add(label);
                }
            }
        }
    }
}
