package ui.buttons;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

// A button that allows the player to pause the game
public class PauseButton extends JButton {
    public static final String PAUSE_BUTTON_ICON_SOURCE = "./data/graphics/pauseButtonIconTwo.png";
    private int frameContentPaneWidth;
    private int frameContentPaneHeight;
    private int buttonWidth;
    private int buttonHeight;

    // EFFECTS: Initializes and creates a pause button
    public PauseButton(int frameContentPaneWidth, int frameContentPaneHeight) {
        this.frameContentPaneWidth = frameContentPaneWidth;
        this.frameContentPaneHeight = frameContentPaneHeight;
        this.buttonWidth = frameContentPaneWidth / 16;
        this.buttonHeight = this.buttonWidth;
//        setText("Pause");
        setBackground(Color.BLACK);
//        setFont(new Font("Times New Roman",Font.BOLD, 14));
//        setForeground(Color.WHITE);
        ImageIcon pauseButtonIcon = new ImageIcon(PAUSE_BUTTON_ICON_SOURCE);
        Image newImage = pauseButtonIcon.getImage();
        newImage = newImage.getScaledInstance(
                this.buttonWidth * 4 / 5,
                this.buttonHeight * 4 / 5,
                Image.SCALE_SMOOTH
        );
        pauseButtonIcon = new ImageIcon(newImage);
        setIcon(pauseButtonIcon);
        setFocusable(false);
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        setBounds(
                frameContentPaneWidth - frameContentPaneWidth / 16,
                0,
                this.buttonWidth,
                this.buttonHeight);
    }
}
