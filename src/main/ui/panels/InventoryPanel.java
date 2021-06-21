package ui.panels;

import model.Game;

import javax.swing.*;
import java.awt.*;

// A panel that shows the player's coins + inventory items
public class InventoryPanel extends JPanel {
    private int frameContentPaneWidth;
    private int frameContentPaneHeight;
    private JLabel coinCountLabel;
    private JLabel potionCountLabel;
    private Game game;

    // EFFECTS: Initializes all the members of the inventory panel
    public InventoryPanel(int frameContentPaneWidth, int frameContentPaneHeight, Game game) {
        this.frameContentPaneWidth = frameContentPaneWidth;
        this.frameContentPaneHeight = frameContentPaneHeight;
        this.game = game;
        setBackground(Color.WHITE);
        setOpaque(true);
        setBounds(
//                frameContentPaneWidth - frameContentPaneWidth / 3,
                0,
                frameContentPaneHeight / 15,
                frameContentPaneWidth / 5,
                frameContentPaneHeight / 11);
        setupCoinCountLabel();
        setupPotionCountLabel();
        add(coinCountLabel);
        add(potionCountLabel);
    }

    // MODIFIES: this
    // EFFECTS: Sets up the potion count label
    public void setupPotionCountLabel() {
        potionCountLabel = new JLabel();
        potionCountLabel.setBackground(Color.WHITE);
        potionCountLabel.setOpaque(true);
        potionCountLabel.setFont(new Font("Times New Roman",Font.BOLD, 20));
        potionCountLabel.setVerticalAlignment(JLabel.CENTER);
        potionCountLabel.setHorizontalAlignment(JLabel.CENTER);
        potionCountLabel.setBounds(
                frameContentPaneWidth - frameContentPaneWidth / 3,
                frameContentPaneHeight - frameContentPaneHeight / 6,
                frameContentPaneWidth / 3,
                frameContentPaneHeight / 6);
    }

    // MODIFIES: this
    // EFFECTS: Sets up the coin count label
    private void setupCoinCountLabel() {
        coinCountLabel = new JLabel();
        coinCountLabel.setBackground(Color.WHITE);
        coinCountLabel.setOpaque(true);
        coinCountLabel.setFont(new Font("Times New Roman",Font.BOLD, 20));
        coinCountLabel.setVerticalAlignment(JLabel.CENTER);
        coinCountLabel.setHorizontalAlignment(JLabel.CENTER);
        coinCountLabel.setBounds(
                frameContentPaneWidth - frameContentPaneWidth / 3,
                frameContentPaneHeight - frameContentPaneHeight / 6,
                frameContentPaneWidth / 3,
                frameContentPaneHeight / 6);
    }

    // MODIFIES: this
    // EFFECTS: Paints the labels with their updated values together
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        coinCountLabel.setText("Coins: " + game.player().getWallet().getBalance());
        potionCountLabel.setText(
                "Small Health Potions: " + game.player().getInventory().getNumberOfSmallHealthPotions()
        );
        validate();
    }
}
