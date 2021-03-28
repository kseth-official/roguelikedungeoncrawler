package ui.panels;

import model.Game;
import model.Position;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class GamePanel extends JPanel {

    private int gameTerminalWidth;
    private int gameTerminalHeight;
    private Game game;
    boolean isLevelOver;

    public GamePanel(
            int contentPaneWidth,
            int contentPaneHeight,
            int gameTerminalWidth,
            int gameTerminalHeight,
            Game game) {
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        setLayout(new GridLayout(gameTerminalHeight,gameTerminalWidth));
        setBounds(
                contentPaneWidth / 8,
                contentPaneHeight / 13,
                contentPaneWidth * 3 / 4,
                contentPaneHeight * 3 / 4);

        this.gameTerminalWidth = gameTerminalWidth;
        this.gameTerminalHeight = gameTerminalHeight;
        this.game = game;
        this.isLevelOver = false;
    }

    // MODIFIES: this
    // EFFECTS: sets whether the level is over
    public void setIsLevelOver(boolean value) {
        this.isLevelOver = value;
    }

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

    private void drawGrid() {
        for (int j = 0; j < gameTerminalHeight;++j) {

            for (int i = 0; i < gameTerminalWidth;++i) {

                if (game.player().getPosition().equals(new Position(i,j))) {
                    add(new JLabel(game.player().display(" ")));

                } else if (game.enemy().getPositionSet().contains(new Position(i,j))) {
                    add(new JLabel(game.enemy().display(" ")));

                } else if (game.coin().getPositionSet().contains(new Position(i,j))) {
                    add(new JLabel(game.coin().display(" ")));

                } else if (game.smallHealthPotion().getPositionSet().contains(new Position(i,j))) {
                    add(new JLabel(game.smallHealthPotion().display(" ")));

                } else if (game.entryPoint().getPosition().equals(new Position(i,j))) {
                    add(new JLabel(game.entryPoint().display(" ")));

                } else if (game.exitPoint().getPosition().equals(new Position(i,j))) {
                    add(new JLabel(game.exitPoint().display(" ")));

                } else if (game.spike().getPositionSet().contains(new Position(i,j))) {
                    add(new JLabel(game.spike().display(" ")));

                } else if (game.wall().getPositionSet().contains(new Position(i,j))) {
                    add(new JLabel(game.wall().display(" ")));

                } else {
                    add(new JLabel(game.air().display(" ")));
                }
            }
        }
    }
}
