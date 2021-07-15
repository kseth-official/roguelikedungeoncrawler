package ui.labels;

import model.Game;

import javax.swing.*;
import java.awt.*;

// A label that displays the controls and information.
public class ControlsLabel extends JLabel {
    private int frameContentPaneWidth;
    private int frameContentPaneHeight;

    // EFFECTS: Displays the controls and information label
    public ControlsLabel(int frameContentPaneWidth, int frameContentPaneHeight) {
        this.frameContentPaneWidth = frameContentPaneWidth;
        this.frameContentPaneHeight = frameContentPaneHeight;
        setLayout(null);
        String labelText;
        labelText = "Use WASD to move around and the buttons to perform special tasks.";
        String labelTextWithHtml = "<html>" + labelText.replaceAll("\n", "<br/>") + "</html>";
        setText(labelTextWithHtml);
        setOpaque(true);
        setBackground(Color.WHITE);
        setFont(new Font("Times New Roman",Font.BOLD, 18));
        setVerticalAlignment(JLabel.CENTER);
        setHorizontalAlignment(JLabel.CENTER);
        setForeground(Color.BLACK);
        setBounds(
//                frameContentPaneWidth / 2 - frameContentPaneWidth / 5,
//                frameContentPaneHeight / 2 - frameContentPaneHeight / 7,
//                frameContentPaneWidth / 5,
//                frameContentPaneHeight / 7
                0,
                frameContentPaneHeight - frameContentPaneHeight / 6,
                frameContentPaneWidth / 4,
                frameContentPaneHeight / 6
        );
    }
}
