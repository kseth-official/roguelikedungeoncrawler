package ui.labels;

import javax.swing.*;
import java.awt.*;

// A label that displays game over information (currently not in use)
public class GameOverLabel extends JLabel {

    private int frameContentPaneWidth;
    private int frameContentPaneHeight;

    // EFFECTS: Displays the game over label
    public GameOverLabel(String gameOverMessage, int frameContentPaneWidth, int frameContentPaneHeight) {
        setLayout(new BorderLayout());
        String labelText = gameOverMessage;
        String labelTextWithHtml = "<html>" + labelText.replaceAll("\n", "<br/>") + "</html>";
        setText(labelTextWithHtml);
        setBackground(Color.WHITE);
        setOpaque(true);
        setFont(new Font("Century Gothic (Body)",Font.BOLD, 18));
        setVerticalAlignment(JLabel.CENTER);
        setHorizontalAlignment(JLabel.CENTER);
        setForeground(Color.BLACK);
        setBounds(
                frameContentPaneWidth / 2,
                frameContentPaneHeight / 2,
                frameContentPaneWidth / 3,
                frameContentPaneHeight / 6);
    }
}
