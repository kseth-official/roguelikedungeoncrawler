//package ui.frames;
//
//import ui.buttons.ReturnFromControlsButton;
//import ui.labels.ControlsAndInformationLabel;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
////  A frame to represent a section in the pause menu that can be used to edit user controls.
////  Class Idea Abandoned
//public class ControlsFrame extends JFrame implements ActionListener {
//    private int frameWidth;
//    private int frameHeight;
//    private int frameContentPaneWidth;
//    private int frameContentPaneHeight;
//
//    // TODO: Make player controls editable by the user.
//    private JLabel controlsAndInformation;
//    private JButton returnButton;
//
//    // EFFECTS: Sets up the controls panel;
//    public ControlsFrame(int frameWidth, int frameHeight) {
//        super("RogueLikeGame");
//        this.frameWidth = frameWidth;
//        this.frameHeight = frameHeight;
//        // super initializes the frame with the text
//        setFrameCharacteristics();
//        createLabels();
//        createButtons();
//        addComponentsToFrame();
//        addListeners();
//        setVisible(true);
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: Sets the frame's characteristics
//    public void setFrameCharacteristics() {
//        getContentPane().setBackground(Color.WHITE);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setSize(frameWidth, frameHeight);
//        setLocationRelativeTo(null);
//        setResizable(false);
//        setLayout(new BorderLayout());
//        this.frameContentPaneWidth = (int) getContentPane().getSize().getWidth();
//        this.frameContentPaneHeight = (int) getContentPane().getSize().getHeight();
//    }
//
//
//    // MODIFIES: this
//    // EFFECTS: Adds all the components to the frame.
//    public void createLabels() {
//        controlsAndInformation = new ControlsAndInformationLabel(frameContentPaneWidth,frameContentPaneHeight);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: Creates all the buttons in this frame.
//    public void createButtons() {
//        returnButton = new ReturnFromControlsButton(frameContentPaneWidth,frameContentPaneHeight);
//    }
//
//    // EFFECTS: Adds all the components to the frame.
//    public void addComponentsToFrame() {
//        add(controlsAndInformation,BorderLayout.CENTER);
//        add(returnButton,BorderLayout.SOUTH);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: Adds this as a listener to certain frame components.
//    public void addListeners() {
//        returnButton.addActionListener(this);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == returnButton) {
//            this.dispose();
//        }
//    }
//}
