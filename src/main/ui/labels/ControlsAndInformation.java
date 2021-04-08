package ui.labels;

import javax.swing.*;
import java.awt.*;

// A label that displays the controls and information
public class ControlsAndInformation extends JLabel {
    private int frameContentPaneWidth;
    private int frameContentPaneHeight;

    // EFFECTS: Displays the controls and information label
    public ControlsAndInformation(int frameContentPaneWidth, int frameContentPaneHeight) {
        this.frameContentPaneWidth = frameContentPaneWidth;
        this.frameContentPaneHeight = frameContentPaneHeight;
        setLayout(null);
        String labelText;
        labelText = "Use WASD to move around and the buttons \nto perform special tasks.";
        String labelTextWithHtml = "<html>" + labelText.replaceAll("\n", "<br/>") + "</html>";
        setText(labelTextWithHtml);
        setBackground(Color.WHITE);
        setOpaque(true);
        setFont(new Font("Times New Roman",Font.BOLD, 18));
        setVerticalAlignment(JLabel.CENTER);
        setHorizontalAlignment(JLabel.CENTER);
        setForeground(Color.BLACK);
        setBounds(
                0,
                frameContentPaneHeight - frameContentPaneHeight / 6,
                frameContentPaneWidth / 3,
                frameContentPaneHeight / 6);
    }
}
