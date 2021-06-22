//package ui.buttons;
//
//import javax.swing.*;
//import javax.swing.border.BevelBorder;
//import java.awt.*;
//
//public class InventoryButton extends JButton {
//    public static final String INVENTORY_BUTTON_ICON_SOURCE = "./data/graphics/inventoryIcon.png";
//    private int frameContentPaneWidth;
//    private int frameContentPaneHeight;
//    private int buttonWidth;
//    private int buttonHeight;
//
//    // EFFECTS: Initializes and creates a dispose coin button
//    public InventoryButton(int frameContentPaneWidth, int frameContentPaneHeight) {
//        this.frameContentPaneWidth = frameContentPaneWidth;
//        this.frameContentPaneHeight = frameContentPaneHeight;
//        this.buttonWidth = frameContentPaneHeight / 7;
//        this.buttonHeight = frameContentPaneHeight / 7;
////        String labelText = "Inventory";
////        String labelTextWithHtml = "<html>" + labelText.replaceAll("\n", "<br/>") + "</html>";
////        setText(labelTextWithHtml);
//        setBackground(Color.BLACK);
////        setFont(new Font("Times New Roman",Font.BOLD, 14));
////        setForeground(Color.WHITE);
//        ImageIcon imageIcon = new ImageIcon(INVENTORY_BUTTON_ICON_SOURCE);
//        Image newImage = imageIcon.getImage();
//        newImage = newImage.getScaledInstance(
//                this.buttonWidth * 3 / 4,
//                this.buttonHeight * 3 / 4,
//                Image.SCALE_SMOOTH
//        );
//        imageIcon = new ImageIcon(newImage);
//        setIcon(imageIcon);
//        setFocusable(false);
//        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
//        setBounds(
//                frameContentPaneWidth - frameContentPaneHeight / 7,
//                frameContentPaneHeight - frameContentPaneHeight / 7,
//                this.buttonWidth,
//                this.buttonHeight
//        );
//    }
//}
