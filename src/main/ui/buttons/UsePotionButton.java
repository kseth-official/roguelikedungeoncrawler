package ui.buttons;

import javax.swing.*;
import java.awt.*;

// A button that allows the player to use a small health potion
public class UsePotionButton extends JButton {
    private int frameContentPaneWidth;
    private int frameContentPaneHeight;

    // EFFECTS: Initializes and creates a use potion button
    public UsePotionButton(int frameContentPaneWidth, int frameContentPaneHeight) {
        this.frameContentPaneWidth = frameContentPaneWidth;
        this.frameContentPaneHeight = frameContentPaneHeight;
        String labelText = "Use Small Health Potion";
        setText(labelText);
        setBounds(
//                frameContentPaneWidth - frameContentPaneWidth * 7 / 12,
                frameContentPaneWidth - frameContentPaneWidth / 6,
                frameContentPaneHeight / 6,
                frameContentPaneWidth / 6,
                frameContentPaneHeight / 6);

        setBackground(new Color(0x8B4513));
        setFont(new Font("Times New Roman",Font.BOLD, 14));
        setForeground(Color.WHITE);
        setFocusable(false);
    }
}
