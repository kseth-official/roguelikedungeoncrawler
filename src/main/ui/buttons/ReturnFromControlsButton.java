package ui.buttons;

import javax.swing.*;
import java.awt.*;

// A button that allows the player to use a small health potion
public class ReturnFromControlsButton extends JButton {
    private int frameContentPaneWidth;
    private int frameContentPaneHeight;

    // EFFECTS: Initializes and creates a use potion button
    public ReturnFromControlsButton(int frameContentPaneWidth, int frameContentPaneHeight) {
        this.frameContentPaneWidth = frameContentPaneWidth;
        this.frameContentPaneHeight = frameContentPaneHeight;
        String labelText = "Return To Game";
        setText(labelText);
        setSize(frameContentPaneWidth / 6,frameContentPaneHeight / 6);
        setBounds(
                0,
                frameContentPaneHeight - frameContentPaneHeight / 2 - frameContentPaneHeight / 12,
                frameContentPaneWidth / 6,
                frameContentPaneHeight / 3
        );
        setBackground(new Color(0x8B4513));
        setFont(new Font("Times New Roman",Font.BOLD, 14));
        setForeground(Color.WHITE);
        setFocusable(false);
    }
}
