package ui.frames;

import model.Game;
import persistence.JsonReader;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// A frame for the load game menu
public class LoadGame extends JFrame implements ActionListener {
    // SAVE FILES
    private static final String JSON_STORE_SAVE_FILE_ONE = "./data/saveFileOne.json";
    private static final String JSON_STORE_SAVE_FILE_TWO = "./data/saveFileTwo.json";
    private static final String JSON_STORE_SAVE_FILE_THREE = "./data/saveFileThree.json";

    // ROGUELIKEGAME WIDTH & HEIGHT
    public static final int GAME_TERMINAL_WIDTH = 13;
    public static final int GAME_TERMINAL_HEIGHT = 13;

    // MENU FRAME
    //      DIMENSIONS AND POSITION
    private static final int FRAME_WIDTH = MainMenu.FRAME_WIDTH;
    private static final int FRAME_HEIGHT = MainMenu.FRAME_HEIGHT;

    // MENU LABEL
    //      DIMENSIONS AND POSITION
    public static final int LABEL_WIDTH = FRAME_WIDTH * 19 / 64;
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
    public static final int SAVE_FILE_ONE_BUTTON_Y = LABEL_Y + 4 * BUTTON_HEIGHT;
    public static final int SAVE_FILE_TWO_BUTTON_Y = LABEL_Y + 6 * BUTTON_HEIGHT;
    public static final int SAVE_FILE_THREE_BUTTON_Y = LABEL_Y + 8 * BUTTON_HEIGHT;
    public static final int EXIT_GAME_BUTTON_Y = LABEL_Y + 10 * BUTTON_HEIGHT;
    //      CHARACTERISTICS
    public static final Color BUTTON_BACKGROUND_COLOR = Color.WHITE;
    public static final Color BUTTON_BORDER_COLOR = Color.GRAY;
    public static final Color BUTTON_TEXT_COLOR = Color.WHITE;
    public static final Font BUTTON_TEXT_FONT = new Font("Times New Roman", Font.PLAIN,BUTTON_HEIGHT * 3 / 4);

    // BORDERS
    Border loadSaveFileLabelBorder;
    Border buttonBorder;

    // ICON PATHS
    public static final String ICON_IMAGE_PATH = "./data/tobs.jpg";

    // MENU OBJECT DECLARATIONS
    private final JLabel loadGameMenuLabel = new JLabel();
    private final JButton loadSaveFileOneButton = new JButton();
    private final JButton loadSaveFileTwoButton = new JButton();
    private final JButton loadSaveFileThreeButton = new JButton();
    private final JButton returnToMenu = new JButton();

    private JsonReader jsonReader;

    // MODIFIES: this
    // EFFECTS: Creates a new game main menu
    public LoadGame() {
        // super initializes the frame with the text
        super("RogueLikeGame");
        setFrameCharacteristics();
        createMenuLabel();
        createMenuButtons();
        addComponentsToFrame();
        // sets the frame's visibility to true loading everything added to it
        this.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: Sets the frame's characteristics
    public void setFrameCharacteristics() {
        // sets the frame's background color
        this.getContentPane().setBackground(Color.BLACK);
        // sets the default close operation
        // JFrame.DO_NOTHING_ON_CLOSE, JFrame.HIDE_ON_CLOSE, JFrame.DISPOSE_ON_CLOSE are other possible options
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // sets the frame size
        this.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        // sets the frame to the center of the screen
        this.setLocationRelativeTo(null);
        // sets the frame's resizability
        this.setResizable(false);
        // sets the frame's layout manager
        this.setLayout(null);

//         calculates the actual width of the content pane
//        int extraWidth = FRAME_WIDTH - (int) this.getContentPane().getSize().getWidth();
//        int extraHeight = FRAME_HEIGHT - (int) this.getContentPane().getSize().getHeight();
//
//        // resets the size of the frame so that the content pane has the size (FRAME_WIDTH, FRAME_HEIGHT)
//        this.setSize(FRAME_WIDTH + extraWidth, FRAME_HEIGHT + extraHeight);
    }

    // MODIFIES: this
    // EFFECTS: Adds all the generated components to the current frame
    public void addComponentsToFrame() {
        // adds the label to the frame
        this.add(loadGameMenuLabel);

        // add the buttons
        this.add(loadSaveFileOneButton);
        this.add(loadSaveFileTwoButton);
        this.add(loadSaveFileThreeButton);
        this.add(returnToMenu);
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
        loadSaveFileOneButton.setBorder(buttonBorder);
        loadSaveFileTwoButton.setBorder(buttonBorder);
        loadSaveFileThreeButton.setBorder(buttonBorder);
        returnToMenu.setBorder(buttonBorder);
    }

    // MODIFIES: this
    // EFFECTS: Sets the buttons as non-focusable
    public void setButtonsFocusable() {
        // setting buttons to not be focusable (box around text)
        loadSaveFileOneButton.setFocusable(false);
        loadSaveFileTwoButton.setFocusable(false);
        loadSaveFileThreeButton.setFocusable(false);
        returnToMenu.setFocusable(false);
    }

    // MODIFIES: this
    // EFFECTS: Sets the button text fonts
    public void setButtonTextFont() {
        // set button text font
        loadSaveFileOneButton.setFont(BUTTON_TEXT_FONT);
        loadSaveFileTwoButton.setFont(BUTTON_TEXT_FONT);
        loadSaveFileThreeButton.setFont(BUTTON_TEXT_FONT);
        returnToMenu.setFont(BUTTON_TEXT_FONT);
    }

    // MODIFIES: this
    // EFFECTS: Makes the buttons listen to button clicks on the menu
    public void addActionListeners() {
        // add this as an action listener
        loadSaveFileOneButton.addActionListener(this);
        loadSaveFileTwoButton.addActionListener(this);
        loadSaveFileThreeButton.addActionListener(this);
        returnToMenu.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: Sets the menu button backgrounds
    public void setBackgrounds() {
        // set backgrounds
        loadSaveFileOneButton.setBackground(BUTTON_BACKGROUND_COLOR);
        loadSaveFileTwoButton.setBackground(BUTTON_BACKGROUND_COLOR);
        loadSaveFileThreeButton.setBackground(BUTTON_BACKGROUND_COLOR);
        returnToMenu.setBackground(BUTTON_BACKGROUND_COLOR);
    }

    // MODIFIES: this
    // EFFECTS: Sets the menu button bounds
    public void setButtonBounds() {
        // set border layouts
        loadSaveFileOneButton.setBounds(COMMON_BUTTON_X, SAVE_FILE_ONE_BUTTON_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
        loadSaveFileTwoButton.setBounds(COMMON_BUTTON_X, SAVE_FILE_TWO_BUTTON_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
        loadSaveFileThreeButton.setBounds(COMMON_BUTTON_X, SAVE_FILE_THREE_BUTTON_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
        returnToMenu.setBounds(COMMON_BUTTON_X,EXIT_GAME_BUTTON_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
    }

    // MODIFIES: this
    // EFFECTS: Sets the menu button texts
    public void setMenuButtonTexts() {
        // set the button texts
        loadSaveFileOneButton.setText("Save File One");
        loadSaveFileTwoButton.setText("Save File Two");
        loadSaveFileThreeButton.setText("Save File Three");
        returnToMenu.setText("Return To Menu");
    }

    // MODIFIES: this
    // EFFECTS: Creates the menu label
    public void createMenuLabel() {
        // creates a new Matte border
        loadSaveFileLabelBorder = BorderFactory.createMatteBorder(3,3,3,3,LABEL_BORDER_COLOR);

        // sets the text of the label
        loadGameMenuLabel.setText("LOAD SAVE FILE");
        // sets the font of the text
        loadGameMenuLabel.setFont(LABEL_TEXT_FONT);
        // sets the font color
        loadGameMenuLabel.setForeground(LABEL_TEXT_COLOR);
        // sets the background color of the label
        loadGameMenuLabel.setBackground(LABEL_BACKGROUND_COLOR);
        // displays the background color
        loadGameMenuLabel.setOpaque(true);

        // sets the label's border
        loadGameMenuLabel.setBorder(loadSaveFileLabelBorder);

        // sets the label's vertical alignment
        loadGameMenuLabel.setVerticalAlignment(JLabel.TOP);
        // sets the label's horizontal alignment
        loadGameMenuLabel.setHorizontalAlignment(JLabel.CENTER);

        // sets the label's bounds when the layout manager is null
        loadGameMenuLabel.setBounds(LABEL_X,LABEL_Y,LABEL_WIDTH,LABEL_HEIGHT);
    }


    // MODIFIES: RogueLikeGame
    // EFFECTS: An action listener for buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        if (e.getSource() == loadSaveFileOneButton) {
            loadSaveFile("1",JSON_STORE_SAVE_FILE_ONE);
        } else if (e.getSource() == loadSaveFileTwoButton) {
            loadSaveFile("2",JSON_STORE_SAVE_FILE_TWO);
        } else if (e.getSource() == loadSaveFileThreeButton) {
            loadSaveFile("3",JSON_STORE_SAVE_FILE_THREE);
        } else if (e.getSource() == returnToMenu) {
            new MainMenu();
        }
    }

    // Code citation: JsonSerializationDemo (CPSC 210; The University of British Columbia, Vancouver)
    // EFFECTS: loads a save file of given number from a source
    public void loadSaveFile(String number, String source) {
        try {
            this.jsonReader = new JsonReader(source);
            Game fromSource  = this.jsonReader.read();
            new RogueLikeGameGUI(fromSource,GAME_TERMINAL_WIDTH,GAME_TERMINAL_HEIGHT);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + source);
        }
    }
}
