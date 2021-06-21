package ui.progressbars;

import model.Game;
import model.HealthBar;

import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;

// A class representing the player's health bar
public class HealthProgressBar extends JProgressBar {
    private int frameContentPaneWidth;
    private int frameContentPaneHeight;
    private Game game;

    // EFFECTS: Initializes the progress bar
    public HealthProgressBar(int frameContentPaneWidth, int frameContentPaneHeight, Game game) {
        super(JProgressBar.HORIZONTAL, HealthBar.ZERO_HEALTH,HealthBar.MAX_HEALTH);
        this.frameContentPaneWidth = frameContentPaneWidth;
        this.frameContentPaneHeight = frameContentPaneHeight;
        this.game = game;
        setupProgressBar();
    }

    // MODIFIES: this
    // EFFECTS: Sets up the progress bar
    private void setupProgressBar() {
        setBorderPainted(true);
        setStringPainted(true);
        setBorder(new SoftBevelBorder(SoftBevelBorder.LOWERED));
        setFont(new Font("Times New Roman", Font.BOLD,14));
        setForeground(Color.RED);
        setBackground(Color.LIGHT_GRAY);
        setOpaque(true);
        setBounds(
                0,
                0,
                frameContentPaneWidth / 5,
                frameContentPaneHeight / 16);
    }

    // MODIFIES: this
    // EFFECTS: Paints the progress bar
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setValue(game.player().getHealthBar().getHealth());
    }
}
