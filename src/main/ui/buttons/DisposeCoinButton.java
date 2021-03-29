package ui.buttons;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

public class DisposeCoinButton extends JButton {
    private int frameContentPaneWidth;
    private int frameContentPaneHeight;

    // EFFECTS: Initializes and creates a dispose coin button
    public DisposeCoinButton(int frameContentPaneWidth, int frameContentPaneHeight) {
        this.frameContentPaneWidth = frameContentPaneWidth;
        this.frameContentPaneHeight = frameContentPaneHeight;
        String labelText = "Dispose Coin";
        String labelTextWithHtml = "<html>" + labelText.replaceAll("\n", "<br/>") + "</html>";
        setText(labelTextWithHtml);
        setBackground(Color.RED);
        setFont(new Font("Century Gothic (Body)",Font.BOLD, 14));
        setForeground(Color.WHITE);
        setFocusable(false);
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        setBounds(
                frameContentPaneWidth - frameContentPaneWidth / 12,
                frameContentPaneHeight / 2 - frameContentPaneHeight / 8,
                frameContentPaneWidth / 12,
                frameContentPaneWidth / 16);
    }
}
