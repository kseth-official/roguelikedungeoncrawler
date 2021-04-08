package ui.buttons;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

// A button to allow the player to interact with the exit point and descend through the dungeon
public class DescendButton extends JButton {
    private int frameContentPaneWidth;
    private int frameContentPaneHeight;

    // EFFECTS: Initializes and creates a descend button
    public DescendButton(int frameContentPaneWidth, int frameContentPaneHeight) {
        this.frameContentPaneWidth = frameContentPaneWidth;
        this.frameContentPaneHeight = frameContentPaneHeight;
        setText("Descend");
        setBackground(Color.RED);
        setFont(new Font("Times New Roman",Font.BOLD, 14));
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
