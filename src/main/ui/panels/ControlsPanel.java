package ui.panels;

import ui.labels.ControlsAndInformationLabel;

import javax.swing.*;
import java.awt.*;

public class ControlsPanel extends JPanel {
    private int panelWidth;
    private int panelHeight;
    // TODO: Make player controls editable by the user.
    // TODO: Delete this class.
    private JLabel controlsAndInformation;

    // EFFECTS: Sets up the controls panel;
    public ControlsPanel(int frameWidth,int frameHeight) {
        this.panelWidth = frameWidth * 2 / 3;
        this.panelHeight = frameHeight * 2 / 3;
        setBackground(Color.WHITE);
        setOpaque(true);
        setBounds(
//                frameContentPaneWidth - frameContentPaneWidth / 3,
                frameWidth / 2 - panelWidth / 2,
                frameHeight / 2 - panelHeight / 2,
                this.panelWidth,
                this.panelHeight
        );
        controlsAndInformation = new ControlsAndInformationLabel(frameWidth,frameHeight);
        add(controlsAndInformation);
    }
}
