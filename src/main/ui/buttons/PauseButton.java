package ui.buttons;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionListener;

// A buttone that allows the player to pause the game
public class PauseButton extends JButton {
    private int frameContentPaneWidth;
    private int frameContentPaneHeight;

    // EFFECTS: Initializes and creates a pause button
    public PauseButton(int frameContentPaneWidth, int frameContentPaneHeight) {
        this.frameContentPaneWidth = frameContentPaneWidth;
        this.frameContentPaneHeight = frameContentPaneHeight;
        setText("Pause");
        setBackground(Color.RED);
        setFont(new Font("Century Gothic (Body)",Font.BOLD, 14));
        setForeground(Color.WHITE);
        setFocusable(false);
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        setBounds(
                frameContentPaneWidth - frameContentPaneWidth / 16,
                0,
                frameContentPaneWidth / 16,
                frameContentPaneWidth / 16);
    }
}
