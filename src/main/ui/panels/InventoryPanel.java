package ui.panels;

import model.Game;

import javax.swing.*;
import java.awt.*;

public class InventoryPanel extends JPanel {
    private int frameContentPaneWidth;
    private int frameContentPaneHeight;
    private JLabel coinCountLabel;
    private JLabel potionCountLabel;
    private Game game;

    public InventoryPanel(int frameContentPaneWidth, int frameContentPaneHeight, Game game) {
        this.frameContentPaneWidth = frameContentPaneWidth;
        this.frameContentPaneHeight = frameContentPaneHeight;
        this.game = game;
        setBackground(Color.WHITE);
        setOpaque(true);
        setBounds(
                frameContentPaneWidth - frameContentPaneWidth / 3,
                frameContentPaneHeight - frameContentPaneHeight / 6,
                frameContentPaneWidth / 3,
                frameContentPaneHeight / 6);
        setupCoinCountLabel();
        setupPotionCountLabel();
        add(coinCountLabel);
        add(potionCountLabel);
    }

    private void setupPotionCountLabel() {
        potionCountLabel = new JLabel();
        potionCountLabel.setBackground(Color.WHITE);
        potionCountLabel.setOpaque(true);
        potionCountLabel.setFont(new Font("Century Gothic (Body)",Font.BOLD, 32));
        potionCountLabel.setVerticalAlignment(JLabel.CENTER);
        potionCountLabel.setHorizontalAlignment(JLabel.CENTER);
        potionCountLabel.setBounds(
                frameContentPaneWidth - frameContentPaneWidth / 3,
                frameContentPaneHeight - frameContentPaneHeight / 6,
                frameContentPaneWidth / 3,
                frameContentPaneHeight / 6);
    }

    private void setupCoinCountLabel() {
        coinCountLabel = new JLabel();
        coinCountLabel.setBackground(Color.WHITE);
        coinCountLabel.setOpaque(true);
        coinCountLabel.setFont(new Font("Century Gothic (Body)",Font.BOLD, 32));
        coinCountLabel.setVerticalAlignment(JLabel.CENTER);
        coinCountLabel.setHorizontalAlignment(JLabel.CENTER);
        coinCountLabel.setBounds(
                frameContentPaneWidth - frameContentPaneWidth / 3,
                frameContentPaneHeight - frameContentPaneHeight / 6,
                frameContentPaneWidth / 3,
                frameContentPaneHeight / 6);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        coinCountLabel.setText("Coins: " + game.player().getWallet().getBalance());
        potionCountLabel.setText("Small Health Potions: " + game.player().getInventory().getNumberOfSmallHealthPotions());
        validate();
    }
}
