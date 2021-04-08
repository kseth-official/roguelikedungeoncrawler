package ui.panels;

import model.Game;
import persistence.JsonReader;
//import ui.RogueLikeGame;
import ui.frames.MainMenu;
import ui.frames.SaveGame;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// A panel that was previously used for a pause menu (currently not in use)
public class PausePanel extends JPanel implements ActionListener {

    private int frameContentPaneWidth;
    private int frameContentPaneHeight;
    private Game game;

    // SAVE FILES
    private static final String JSON_STORE_SAVE_FILE_ONE = "./data/saveFileOne.json";
    private static final String JSON_STORE_SAVE_FILE_TWO = "./data/saveFileTwo.json";
    private static final String JSON_STORE_SAVE_FILE_THREE = "./data/saveFileThree.json";


    // MENU LABEL
    //      DIMENSIONS AND POSITION
    private int labelWidth = frameContentPaneWidth * 19 / 64;
    private int labelHeight = frameContentPaneHeight / 8;
    private int labelX = frameContentPaneWidth / 2 - labelWidth / 2;
    private int labelY = frameContentPaneHeight / 2 - 3 * labelHeight;

    //      CHARACTERISTICS
    private Color labelBackgroundColor = Color.BLACK;
    private Color labelBorderColor = Color.BLACK;
    private Color labelTextColor = Color.WHITE;
    private Font labelTextFont = new Font("Century Gothic (Body)", Font.BOLD, labelHeight / 2);

    // MENU BUTTONS
    //      DIMENSIONS AND POSITIONS
    private int buttonWidth = labelWidth;
    private int buttonHeight = labelHeight / 2;
    private int commonButtonX = labelX;
    private int saveFileButtonOneY = labelY + 4 * buttonHeight;
    private int saveFileButtonTwoY = labelY + 6 * buttonHeight;
    private int saveFileButtonThreeY = labelY + 8 * buttonHeight;
    private int exitGameButtonY = labelY + 10 * buttonHeight;

    //      CHARACTERISTICS
    public static final Color BUTTON_BACKGROUND_COLOR = Color.WHITE;
    public static final Color BUTTON_BORDER_COLOR = Color.GRAY;
    public static final Color BUTTON_TEXT_COLOR = Color.WHITE;
    private Font buttonTextFont = new Font("Century Gothic (Body)", Font.PLAIN, buttonHeight * 3 / 4);

    // BORDERS
    Border pauseGameMenuLabelBorder;
    Border buttonBorder;

    // ICON PATHS
    public static final String ICON_IMAGE_PATH = "./data/tobs.jpg";

    // MENU OBJECT DECLARATIONS
    private final JLabel pauseGameMenuLabel = new JLabel();
    private final JButton saveGameButton = new JButton();
    private final JButton mainMenuButton = new JButton();
    private final JButton exitGameButton = new JButton();
    private final JButton returnToGameButton = new JButton();

    public PausePanel(int frameContentPaneWidth, int frameContentPaneHeight, Game game) {
        this.frameContentPaneWidth = frameContentPaneWidth;
        this.frameContentPaneHeight = frameContentPaneHeight;
        this.game = game;
        setPanelCharacteristics();
        createMenuLabel();
        createMenuButtons();
        addComponentsToPanel();
    }

    // MODIFIES: this
    // EFFECTS: Sets the frame's characteristics
    public void setPanelCharacteristics() {
        // sets the frame size
        this.setSize(frameContentPaneWidth, frameContentPaneHeight);
        // sets the frame's layout manager
        this.setLayout(null);
    }

    // MODIFIES: this
    // EFFECTS: Adds all the generated components to the current frame
    public void addComponentsToPanel() {
        // adds the label to the frame
        this.add(pauseGameMenuLabel);

        // add the buttons
        this.add(saveGameButton);
        this.add(mainMenuButton);
        this.add(exitGameButton);
        this.add(returnToGameButton);
    }

    // MODIFIES: this
    // EFFECTS: Creates the menu buttons
    public void createMenuButtons() {
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
        saveGameButton.setBorder(buttonBorder);
        mainMenuButton.setBorder(buttonBorder);
        exitGameButton.setBorder(buttonBorder);
        returnToGameButton.setBorder(buttonBorder);
    }

    // MODIFIES: this
    // EFFECTS: Sets the buttons as non-focusable
    public void setButtonsFocusable() {
        // setting buttons to not be focusable (box around text)
        saveGameButton.setFocusable(false);
        mainMenuButton.setFocusable(false);
        exitGameButton.setFocusable(false);
        returnToGameButton.setFocusable(false);

    }

    // MODIFIES: this
    // EFFECTS: Sets the button text fonts
    public void setButtonTextFont() {
        // set button text font
        saveGameButton.setFont(buttonTextFont);
        mainMenuButton.setFont(buttonTextFont);
        exitGameButton.setFont(buttonTextFont);
        returnToGameButton.setFont(buttonTextFont);
    }

    // MODIFIES: this
    // EFFECTS: Makes the buttons listen to button clicks on the menu
    public void addActionListeners() {
        // add this as an action listener
        saveGameButton.addActionListener(this);
        mainMenuButton.addActionListener(this);
        exitGameButton.addActionListener(this);
        returnToGameButton.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: Sets the menu button backgrounds
    public void setBackgrounds() {
        // set backgrounds
        saveGameButton.setBackground(BUTTON_BACKGROUND_COLOR);
        mainMenuButton.setBackground(BUTTON_BACKGROUND_COLOR);
        exitGameButton.setBackground(BUTTON_BACKGROUND_COLOR);
        returnToGameButton.setBackground(BUTTON_BACKGROUND_COLOR);
    }

    // MODIFIES: this
    // EFFECTS: Sets the menu button bounds
    public void setButtonBounds() {
        // set border layouts
        saveGameButton.setBounds(commonButtonX, saveFileButtonOneY, buttonWidth, buttonHeight);
        mainMenuButton.setBounds(commonButtonX, saveFileButtonTwoY, buttonWidth, buttonHeight);
        exitGameButton.setBounds(commonButtonX, saveFileButtonThreeY, buttonWidth, buttonHeight);
        returnToGameButton.setBounds(commonButtonX, exitGameButtonY, buttonWidth, buttonHeight);
    }

    // MODIFIES: this
    // EFFECTS: Sets the menu button texts
    public void setMenuButtonTexts() {
        // set the button texts
        saveGameButton.setText("Save Game");
        mainMenuButton.setText("Main Menu");
        exitGameButton.setText("Exit Game");
        returnToGameButton.setText("Return To Game");
    }

    // MODIFIES: this
    // EFFECTS: Creates the menu label
    public void createMenuLabel() {
        // creates a new Matte border
        pauseGameMenuLabelBorder = BorderFactory.createMatteBorder(3,3,3,3, labelBorderColor);

        // sets the text of the label
        pauseGameMenuLabel.setText("GAME PAUSED");
        // sets the font of the text
        pauseGameMenuLabel.setFont(labelTextFont);
        // sets the font color
        pauseGameMenuLabel.setForeground(labelTextColor);
        // sets the background color of the label
        pauseGameMenuLabel.setBackground(labelBackgroundColor);
        // displays the background color
        pauseGameMenuLabel.setOpaque(true);

        // sets the label's border
        pauseGameMenuLabel.setBorder(pauseGameMenuLabelBorder);

        // sets the label's vertical alignment
        pauseGameMenuLabel.setVerticalAlignment(JLabel.TOP);
        // sets the label's horizontal alignment
        pauseGameMenuLabel.setHorizontalAlignment(JLabel.CENTER);

        // sets the label's bounds when the layout manager is null
        pauseGameMenuLabel.setBounds(labelX, labelY, labelWidth, labelHeight);
    }


    // MODIFIES: RogueLikeGame
    // EFFECTS: An action listener for buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveGameButton) {
            new SaveGame(game);
        } else if (e.getSource() == mainMenuButton) {
            new MainMenu();
        } else if (e.getSource() == exitGameButton) {
            System.exit(0);
        } else if (e.getSource() == returnToGameButton) {
            setVisible(false);
        }
    }
}
