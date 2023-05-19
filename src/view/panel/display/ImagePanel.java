package view.panel.display;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

    public static final int SCREEN_WIDTH = DisplayPanel.SCREEN_WIDTH;
    public static final int SCREEN_HEIGHT = DisplayPanel.SCREEN_WIDTH;
    public static final int IMAGE_WIDTH = 254;
    public static final int IMAGE_HEIGHT = 254;
    private static final JLabel shipImageLabel = new JLabel();

    public ImagePanel() {

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        Image shipImage = new ImageIcon("src/res/ship/Fighter.png").getImage()
                .getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_DEFAULT);
        shipImageLabel.setIcon(new ImageIcon(shipImage));
        shipImageLabel.setPreferredSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
        this.add(shipImageLabel);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));

    }

}
