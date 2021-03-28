package ui.labels;

import javax.swing.*;
import java.awt.*;

public class GameOverLabel extends JLabel {

    // EFFECTS: Displays the controls and information panel
    public GameOverLabel(String gameOverMessage) {
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
//        setBounds(
//                0,
//                frameContentPaneHeight - frameContentPaneHeight / 6,
//                frameContentPaneWidth / 3,
//                frameContentPaneHeight / 6);
    }
}
