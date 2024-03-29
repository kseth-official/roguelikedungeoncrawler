package ui.frames;

import model.Game;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// A frame for the pause menu
public class PauseMenu extends JFrame implements ActionListener {

    // ROGUELIKEGAME WIDTH & HEIGHT
    public static final int GAME_TERMINAL_WIDTH = 13;
    public static final int GAME_TERMINAL_HEIGHT = 13;

    // MENU FRAME
    //      DIMENSIONS AND POSITION
    private static final int FRAME_WIDTH = MainMenu.FRAME_WIDTH;
    private static final int FRAME_HEIGHT = MainMenu.FRAME_HEIGHT;

    // MENU LABEL
    //      DIMENSIONS AND POSITION
    public static final int LABEL_WIDTH = FRAME_WIDTH * 3 / 8;
    public static final int LABEL_HEIGHT = FRAME_HEIGHT / 8;
    public static final int LABEL_X = FRAME_WIDTH / 2 - LABEL_WIDTH / 2;
    public static final int LABEL_Y = FRAME_HEIGHT / 2 - 3 * LABEL_HEIGHT;
    //      CHARACTERISTICS
    public static final Color LABEL_BACKGROUND_COLOR = Color.BLACK;
    public static final Color LABEL_BORDER_COLOR = Color.BLACK;
    public static final Color LABEL_TEXT_COLOR = Color.WHITE;
    public static final Font LABEL_TEXT_FONT = new Font("Times New Roman", Font.BOLD, LABEL_HEIGHT / 2);

    // MENU BUTTONS
    //      DIMENSIONS AND POSITIONS
    public static final int BUTTON_WIDTH = LABEL_WIDTH;
    public static final int BUTTON_HEIGHT = LABEL_HEIGHT / 2;
    public static final int COMMON_BUTTON_X = LABEL_X;
    public static final int SAVE_GAME_BUTTON_Y = LABEL_Y + 4 * BUTTON_HEIGHT;
    public static final int MAIN_MENU_BUTTON_Y = LABEL_Y + 6 * BUTTON_HEIGHT;
    public static final int EXIT_GAME_BUTTON_Y = LABEL_Y + 8 * BUTTON_HEIGHT;
//    public static final int CONTROLS_BUTTON_Y = LABEL_Y + 9 * BUTTON_HEIGHT;
    public static final int RETURN_TO_GAME_BUTTON_Y = LABEL_Y + 10 * BUTTON_HEIGHT;

    //      CHARACTERISTICS
    public static final Color BUTTON_BACKGROUND_COLOR = Color.WHITE;
    public static final Color BUTTON_BORDER_COLOR = Color.GRAY;
    public static final Color BUTTON_TEXT_COLOR = Color.WHITE;
    public static final Font BUTTON_TEXT_FONT = new Font("Times New Roman", Font.PLAIN, BUTTON_HEIGHT * 3 / 4);

    // BORDERS
    Border pauseMenuBorder;
    Border buttonBorder;

    // ICON PATHS
    public static final String ICON_IMAGE_PATH = "./data/tobs.jpg";

    // MENU OBJECT DECLARATIONS
    private final JLabel pauseMenuLabel = new JLabel();

    private final JButton saveGameButton = new JButton();
    private final JButton mainMenuButton = new JButton();
    private final JButton exitGameButton = new JButton();
    private final JButton controlsButton = new JButton();
    private final JButton returnToGameButton = new JButton();
    private List<JButton> pauseMenuButtons = new ArrayList<>();

    private JPanel controlsPanel;

    private RogueLikeGameGUI currentGameGuiObject;
    private Game currentGame;

    // MODIFIES: this
    // EFFECTS: Creates a new game main menu
    public PauseMenu(RogueLikeGameGUI fromGameGuiObject, Game currentGame) {
        // super initializes the frame with the text
        super("RogueLikeGame");
        this.currentGameGuiObject = fromGameGuiObject;
        this.currentGame = currentGame;
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
        getContentPane().setBackground(Color.BLACK);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
    }

    // MODIFIES: this
    // EFFECTS: Adds all the generated components to the current frame
    public void addComponentsToFrame() {
        // adds the label to the frame
        add(pauseMenuLabel);

        // add the buttons
        for (JButton button: pauseMenuButtons) {
            add(button);
        }
//        add(saveGameButton);
//        add(mainMenuButton);
//        add(exitGameButton);
//        add(returnToGameButton);
    }

    // MODIFIES: this
    // EFFECTS: Creates the menu buttons
    public void createMenuButtons() {
        pauseMenuButtons.add(saveGameButton);
        pauseMenuButtons.add(mainMenuButton);
        pauseMenuButtons.add(exitGameButton);
//        pauseMenuButtons.add(controlsButton);
        pauseMenuButtons.add(returnToGameButton);

        setMenuButtonTexts();

        setButtonBounds();

        setBackgrounds();

        addActionListeners();

        setButtonTextFont();

        setButtonsFocusable();

        setButtonBorders();
    }


    // MODIFIES: this
    // EFFECTS: Sets the menu button borders
    public void setButtonBorders() {
        // creating Button Borders
        buttonBorder = BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED);

        // adding borders to the buttons
        for (JButton button: pauseMenuButtons) {
            button.setBorder(buttonBorder);
        }
//        saveGameButton.setBorder(buttonBorder);
//        mainMenuButton.setBorder(buttonBorder);
//        exitGameButton.setBorder(buttonBorder);
//        returnToGameButton.setBorder(buttonBorder);
    }

    // MODIFIES: this
    // EFFECTS: Sets the buttons as non-focusable
    public void setButtonsFocusable() {
        // setting buttons to not be focusable (box around text)
        for (JButton button: pauseMenuButtons) {
            button.setFocusable(false);
        }
//        saveGameButton.setFocusable(false);
//        mainMenuButton.setFocusable(false);
//        exitGameButton.setFocusable(false);
//        returnToGameButton.setFocusable(false);
    }

    // MODIFIES: this
    // EFFECTS: Sets the button text fonts
    public void setButtonTextFont() {
        // set button text font
        for (JButton button: pauseMenuButtons) {
            button.setFont(BUTTON_TEXT_FONT);
        }
//        saveGameButton.setFont(BUTTON_TEXT_FONT);
//        mainMenuButton.setFont(BUTTON_TEXT_FONT);
//        exitGameButton.setFont(BUTTON_TEXT_FONT);
//        controlsButton.setFont(BUTTON_TEXT_FONT);
//        returnToGameButton.setFont(BUTTON_TEXT_FONT);
    }

    // MODIFIES: this
    // EFFECTS: Makes the buttons listen to button clicks on the menu
    public void addActionListeners() {
        // add this as an action listener
        for (JButton button: pauseMenuButtons) {
            button.addActionListener(this);
        }
//        saveGameButton.addActionListener(this);
//        mainMenuButton.addActionListener(this);
//        exitGameButton.addActionListener(this);
//        controlsButton.addActionListener(this);
//        returnToGameButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: Sets the menu button backgrounds
    public void setBackgrounds() {
        // set backgrounds
        for (JButton button: pauseMenuButtons) {
            button.setBackground(BUTTON_BACKGROUND_COLOR);
        }
//        saveGameButton.setBackground(BUTTON_BACKGROUND_COLOR);
//        mainMenuButton.setBackground(BUTTON_BACKGROUND_COLOR);
//        exitGameButton.setBackground(BUTTON_BACKGROUND_COLOR);
//        controlsButton.setBackground(BUTTON_BACKGROUND_COLOR);
//        returnToGameButton.setBackground(BUTTON_BACKGROUND_COLOR);
    }

    // MODIFIES: this
    // EFFECTS: Sets the menu button bounds
    public void setButtonBounds() {
        // set border layouts
        saveGameButton.setBounds(COMMON_BUTTON_X, SAVE_GAME_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        mainMenuButton.setBounds(COMMON_BUTTON_X, MAIN_MENU_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        exitGameButton.setBounds(COMMON_BUTTON_X, EXIT_GAME_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
//        controlsButton.setBounds(COMMON_BUTTON_X, CONTROLS_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        returnToGameButton.setBounds(COMMON_BUTTON_X, RETURN_TO_GAME_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    // MODIFIES: this
    // EFFECTS: Sets the menu button texts
    public void setMenuButtonTexts() {
        // set the button texts
        saveGameButton.setText("Save Game");
        mainMenuButton.setText("Main Menu");
        exitGameButton.setText("Exit Game");
//        controlsButton.setText("Controls");
        returnToGameButton.setText("Return to Game");
    }

    // MODIFIES: this
    // EFFECTS: Creates the menu label
    public void createMenuLabel() {
        // creates a new Matte border
        pauseMenuBorder = BorderFactory.createMatteBorder(3, 3, 3, 3, LABEL_BORDER_COLOR);

        // sets the text of the label
        pauseMenuLabel.setText("GAME PAUSED");
        // sets the font of the text
        pauseMenuLabel.setFont(LABEL_TEXT_FONT);
        // sets the font color
        pauseMenuLabel.setForeground(LABEL_TEXT_COLOR);
        // sets the background color of the label
        pauseMenuLabel.setBackground(LABEL_BACKGROUND_COLOR);
        // displays the background color
        pauseMenuLabel.setOpaque(true);

        // sets the label's border
        pauseMenuLabel.setBorder(pauseMenuBorder);

        // sets the label's vertical alignment
        pauseMenuLabel.setVerticalAlignment(JLabel.TOP);
        // sets the label's horizontal alignment
        pauseMenuLabel.setHorizontalAlignment(JLabel.CENTER);

        // sets the label's bounds when the layout manager is null
        pauseMenuLabel.setBounds(LABEL_X, LABEL_Y, LABEL_WIDTH, LABEL_HEIGHT);
    }


    // MODIFIES: currentGameGuiObject
    // EFFECTS: An action listener for buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveGameButton) {
            this.dispose();
            new SaveGame(currentGame);
        } else if (e.getSource() == mainMenuButton) {
            this.dispose();
            currentGameGuiObject.dispose();
            new MainMenu();
        } else if (e.getSource() == exitGameButton) {
            System.exit(0);
//        } else if (e.getSource() == controlsButton) {
//            this.dispose();
//            new ControlsFrame(FRAME_WIDTH,FRAME_HEIGHT);
        } else if (e.getSource() == returnToGameButton) {
            this.dispose();
        }
    }
}

