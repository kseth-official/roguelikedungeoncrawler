package ui.buttons;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class BuyPotionButton extends JButton {
    private int frameContentPaneWidth;
    private int frameContentPaneHeight;

    public BuyPotionButton(int frameContentPaneWidth, int frameContentPaneHeight) {
        this.frameContentPaneWidth = frameContentPaneWidth;
        this.frameContentPaneHeight = frameContentPaneHeight;
        setText("Buy Small Health Potion");
        setBackground(Color.RED);
        setFont(new Font("Century Gothic (Body)",Font.BOLD, 14));
        setForeground(Color.WHITE);
        setFocusable(false);
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        setBounds(
                frameContentPaneWidth - frameContentPaneWidth / 16,
                frameContentPaneHeight / 2,
                frameContentPaneWidth / 16,
                frameContentPaneWidth / 16);
    }
}
