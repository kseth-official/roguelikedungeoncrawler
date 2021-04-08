package ui.frames;

import ui.RogueLikeGame;
import ui.frames.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// A frame for the Main Menu
public class MainMenu extends JFrame implements ActionListener {
    // ROGUELIKEGAME WIDTH & HEIGHT
    public static final int GAME_TERMINAL_WIDTH = 13;
    public static final int GAME_TERMINAL_HEIGHT = 13;

    // MENU FRAME
    //      DIMENSIONS AND POSITION
    public static final int FRAME_WIDTH = 1280;
    public static final int FRAME_HEIGHT = 720;

    // MENU LABEL
    //      DIMENSIONS AND POSITION
    public static final int LABEL_WIDTH = FRAME_WIDTH * 2 / 8;
    public static final int LABEL_HEIGHT = FRAME_HEIGHT / 8;
    public static final int LABEL_X = FRAME_WIDTH / 2 - LABEL_WIDTH / 2;
    public static final int LABEL_Y = FRAME_HEIGHT / 2 - 3 * LABEL_HEIGHT;
    //      CHARACTERISTICS
    public static final Color LABEL_BACKGROUND_COLOR = Color.BLACK;
    public static final Color LABEL_BORDER_COLOR = Color.BLACK;
    public static final Color LABEL_TEXT_COLOR = Color.WHITE;
    public static final Font LABEL_TEXT_FONT = new Font("Times New Roman", Font.BOLD,LABEL_HEIGHT / 2);

    // MENU BUTTONS
    //      DIMENSIONS AND POSITIONS
    public static final int BUTTON_WIDTH = LABEL_WIDTH;
    public static final int BUTTON_HEIGHT = LABEL_HEIGHT / 2;
    public static final int COMMON_BUTTON_X = LABEL_X;
    public static final int NEW_GAME_BUTTON_Y = LABEL_Y + 4 * BUTTON_HEIGHT;
    public static final int LOAD_GAME_BUTTON_Y = LABEL_Y + 6 * BUTTON_HEIGHT;
    public static final int EXIT_GAME_BUTTON_Y = LABEL_Y + 8 * BUTTON_HEIGHT;
    //      CHARACTERISTICS
    public static final Color BUTTON_BACKGROUND_COLOR = Color.WHITE;
    public static final Color BUTTON_BORDER_COLOR = Color.GRAY;
    public static final Color BUTTON_TEXT_COLOR = Color.WHITE;
    public static final Font BUTTON_TEXT_FONT = new Font("Times New Roman", Font.PLAIN,BUTTON_HEIGHT * 3 / 4);

    // BORDERS
    Border mainMenuBorder;
    Border buttonBorder;

    // ICON PATHS
    public static final String ICON_IMAGE_PATH = "./data/tobs.jpg";

    // MENU OBJECT DECLARATIONS
    private final JLabel mainMenuLabel = new JLabel();
    private final JButton newGameButton = new JButton();
    private final JButton loadGameButton = new JButton();
    private final JButton exitGameButton = new JButton();

    // MODIFIES: this
    // EFFECTS: Creates a new game main menu
    public MainMenu() {
        // super initializes the frame with the text
        super("RogueLikeGame");
        setFrameCharacteristics();
        createMenuLabel();
        createMenuButtons();
        addComponentsToFrame();
        // sets the frame's visibility to true loading everything added to it
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Sets the frame's characteristics
    public void setFrameCharacteristics() {
        // sets the frame's background color
        getContentPane().setBackground(Color.BLACK);
        // sets the default close operation
        // JFrame.DO_NOTHING_ON_CLOSE, JFrame.HIDE_ON_CLOSE, JFrame.DISPOSE_ON_CLOSE are other possible options
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // sets the frame size
        setSize(FRAME_WIDTH,FRAME_HEIGHT);
        // sets the frame to the center of the screen
        setLocationRelativeTo(null);
        // sets the frame's resizability
        setResizable(false);
        // sets the frame's layout manager
        setLayout(null);
    }

    // MODIFIES: this
    // EFFECTS: Adds all the generated components to the current frame
    public void addComponentsToFrame() {
        // adds the label to the frame
        add(mainMenuLabel);

        // add the buttons
        add(newGameButton);
        add(loadGameButton);
        add(exitGameButton);
    }

    // MODIFIES: this
    // EFFECTS: Creates the menu buttons
    public void createMenuButtons() {
        // set the button texts
        newGameButton.setText("New Game");
        loadGameButton.setText("Load Game");
        exitGameButton.setText("Exit Game");

        // set border layouts
        newGameButton.setBounds(COMMON_BUTTON_X,NEW_GAME_BUTTON_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
        loadGameButton.setBounds(COMMON_BUTTON_X,LOAD_GAME_BUTTON_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
        exitGameButton.setBounds(COMMON_BUTTON_X,EXIT_GAME_BUTTON_Y,BUTTON_WIDTH,BUTTON_HEIGHT);

        // set backgrounds
        newGameButton.setBackground(BUTTON_BACKGROUND_COLOR);
        loadGameButton.setBackground(BUTTON_BACKGROUND_COLOR);
        exitGameButton.setBackground(BUTTON_BACKGROUND_COLOR);

        // add this as an action listener
        newGameButton.addActionListener(this);
        loadGameButton.addActionListener(this);
        exitGameButton.addActionListener(this);

        // set button text font
        newGameButton.setFont(BUTTON_TEXT_FONT);
        loadGameButton.setFont(BUTTON_TEXT_FONT);
        exitGameButton.setFont(BUTTON_TEXT_FONT);

        // setting buttons to not be focusable (box around text)
        newGameButton.setFocusable(false);
        loadGameButton.setFocusable(false);
        exitGameButton.setFocusable(false);

        // creating Button Borders
        buttonBorder = BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED);

        // adding borders to the buttons
        newGameButton.setBorder(buttonBorder);
        loadGameButton.setBorder(buttonBorder);
        exitGameButton.setBorder(buttonBorder);
    }

    // MODIFIES: this
    // EFFECTS: Creates the menu label
    public void createMenuLabel() {
        // creates a new Matte border
        mainMenuBorder = BorderFactory.createMatteBorder(3,3,3,3,LABEL_BORDER_COLOR);

        // sets the text of the label
        mainMenuLabel.setText("MAIN MENU");
        // sets the font of the text
        mainMenuLabel.setFont(LABEL_TEXT_FONT);
        // sets the font color
        mainMenuLabel.setForeground(LABEL_TEXT_COLOR);
        // sets the background color of the label
        mainMenuLabel.setBackground(LABEL_BACKGROUND_COLOR);
        // displays the background color
        mainMenuLabel.setOpaque(true);

        // sets the label's border
        mainMenuLabel.setBorder(mainMenuBorder);

        // sets the label's vertical alignment
        mainMenuLabel.setVerticalAlignment(JLabel.TOP);
        // sets the label's horizontal alignment
        mainMenuLabel.setHorizontalAlignment(JLabel.CENTER);

        // sets the label's bounds when the layout manager is null
        mainMenuLabel.setBounds(LABEL_X,LABEL_Y,LABEL_WIDTH,LABEL_HEIGHT);
    }


    // MODIFIES: RogueLikeGame
    // EFFECTS: An action listener for buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        if (e.getSource() == newGameButton) {
            new RogueLikeGameGUI(GAME_TERMINAL_WIDTH,GAME_TERMINAL_HEIGHT);
        } else if (e.getSource() == loadGameButton) {
            new LoadGame();
        } else if (e.getSource() == exitGameButton) {
            System.exit(0);
        }
    }
}
