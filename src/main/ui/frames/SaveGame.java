package ui.frames;

import model.Game;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.RogueLikeGame;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// A frame for the save game menu
public class SaveGame extends JFrame implements ActionListener {
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
    public static final int LABEL_WIDTH = FRAME_WIDTH * 1 / 2;
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
    private final JLabel saveGameMenuLabel = new JLabel();
    private final JButton saveFileOneButton = new JButton();
    private final JButton saveFileTwoButton = new JButton();
    private final JButton saveFileThreeButton = new JButton();
    private final JButton returnToMenu = new JButton();

    private Game game;
    private JsonWriter jsonWriter;

    // MODIFIES: this
    // EFFECTS: Creates a new game main menu
    public SaveGame(Game game) {
        // super initializes the frame with the text
        super("RogueLikeGame");
        setFrameCharacteristics();
        createMenuLabel();
        createMenuButtons();
        addComponentsToFrame();
        // sets the frame's visibility to true loading everything added to it
        this.setVisible(true);
        this.game = game;
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
    }

    // MODIFIES: this
    // EFFECTS: Adds all the generated components to the current frame
    public void addComponentsToFrame() {
        // adds the label to the frame
        this.add(saveGameMenuLabel);

        // add the buttons
        this.add(saveFileOneButton);
        this.add(saveFileTwoButton);
        this.add(saveFileThreeButton);
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
        saveFileOneButton.setBorder(buttonBorder);
        saveFileTwoButton.setBorder(buttonBorder);
        saveFileThreeButton.setBorder(buttonBorder);
        returnToMenu.setBorder(buttonBorder);
    }

    // MODIFIES: this
    // EFFECTS: Sets the buttons as non-focusable
    public void setButtonsFocusable() {
        // setting buttons to not be focusable (box around text)
        saveFileOneButton.setFocusable(false);
        saveFileTwoButton.setFocusable(false);
        saveFileThreeButton.setFocusable(false);
        returnToMenu.setFocusable(false);

    }

    // MODIFIES: this
    // EFFECTS: Sets the button text fonts
    public void setButtonTextFont() {
        // set button text font
        saveFileOneButton.setFont(BUTTON_TEXT_FONT);
        saveFileTwoButton.setFont(BUTTON_TEXT_FONT);
        saveFileThreeButton.setFont(BUTTON_TEXT_FONT);
        returnToMenu.setFont(BUTTON_TEXT_FONT);
    }

    // MODIFIES: this
    // EFFECTS: Makes the buttons listen to button clicks on the menu
    public void addActionListeners() {
        // add this as an action listener
        saveFileOneButton.addActionListener(this);
        saveFileTwoButton.addActionListener(this);
        saveFileThreeButton.addActionListener(this);
        returnToMenu.addActionListener(this);
    }

    // MODIFIES: this
    // EFFECTS: Sets the menu button backgrounds
    public void setBackgrounds() {
        // set backgrounds
        saveFileOneButton.setBackground(BUTTON_BACKGROUND_COLOR);
        saveFileTwoButton.setBackground(BUTTON_BACKGROUND_COLOR);
        saveFileThreeButton.setBackground(BUTTON_BACKGROUND_COLOR);
        returnToMenu.setBackground(BUTTON_BACKGROUND_COLOR);
    }

    // MODIFIES: this
    // EFFECTS: Sets the menu button bounds
    public void setButtonBounds() {
        // set border layouts
        saveFileOneButton.setBounds(COMMON_BUTTON_X, SAVE_FILE_ONE_BUTTON_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
        saveFileTwoButton.setBounds(COMMON_BUTTON_X, SAVE_FILE_TWO_BUTTON_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
        saveFileThreeButton.setBounds(COMMON_BUTTON_X, SAVE_FILE_THREE_BUTTON_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
        returnToMenu.setBounds(COMMON_BUTTON_X,EXIT_GAME_BUTTON_Y,BUTTON_WIDTH,BUTTON_HEIGHT);
    }

    // MODIFIES: this
    // EFFECTS: Sets the menu button texts
    public void setMenuButtonTexts() {
        // set the button texts
        saveFileOneButton.setText("Save File One");
        saveFileTwoButton.setText("Save File Two");
        saveFileThreeButton.setText("Save File Three");
        returnToMenu.setText("Return To Menu");
    }

    // MODIFIES: this
    // EFFECTS: Creates the menu label
    public void createMenuLabel() {
        // creates a new Matte border
        loadSaveFileLabelBorder = BorderFactory.createMatteBorder(3,3,3,3,LABEL_BORDER_COLOR);

        // sets the text of the label
        saveGameMenuLabel.setText("CHOOSE A SAVE FILE");
        // sets the font of the text
        saveGameMenuLabel.setFont(LABEL_TEXT_FONT);
        // sets the font color
        saveGameMenuLabel.setForeground(LABEL_TEXT_COLOR);
        // sets the background color of the label
        saveGameMenuLabel.setBackground(LABEL_BACKGROUND_COLOR);
        // displays the background color
        saveGameMenuLabel.setOpaque(true);

        // sets the label's border
        saveGameMenuLabel.setBorder(loadSaveFileLabelBorder);

        // sets the label's vertical alignment
        saveGameMenuLabel.setVerticalAlignment(JLabel.TOP);
        // sets the label's horizontal alignment
        saveGameMenuLabel.setHorizontalAlignment(JLabel.CENTER);

        // sets the label's bounds when the layout manager is null
        saveGameMenuLabel.setBounds(LABEL_X,LABEL_Y,LABEL_WIDTH,LABEL_HEIGHT);
    }


    // MODIFIES: this
    // EFFECTS: An action listener for buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        this.dispose();
        if (e.getSource() == saveFileOneButton) {
            writeToSaveFile("1",JSON_STORE_SAVE_FILE_ONE);
        } else if (e.getSource() == saveFileTwoButton) {
            writeToSaveFile("2",JSON_STORE_SAVE_FILE_TWO);
        } else if (e.getSource() == saveFileThreeButton) {
            writeToSaveFile("3",JSON_STORE_SAVE_FILE_THREE);
        } else if (e.getSource() == returnToMenu) {
            // return to menu
        }
    }

    // Code citation: JsonSerializationDemo (CPSC 210; The University of British Columbia, Vancouver)
    // MODIFIES: this
    // EFFECTS: writes a save file of given number to a source
    //          catches a FileNotFoundException if is unable to write to the file
    public void writeToSaveFile(String number, String source) {
        int answer;
        try {
            while (true) {
                answer = JOptionPane.showConfirmDialog(
                        this,
                        "Do you wish to overwrite?",
                        "Save Game",
                        JOptionPane.YES_NO_OPTION);

//                int exit = -1;
//                int yes = 0;
//                int no = 1;

                if (answer == -1 || answer == 1) {
                    return;
                } else if (answer == 0) {
                    this.jsonWriter = new JsonWriter(source);
                    jsonWriter.open();
                    jsonWriter.write(game);
                    jsonWriter.close();
                    return;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + source);
        }
    }
}
