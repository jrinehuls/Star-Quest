package view.panel.display;

import view.panel.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DisplayPanel extends JPanel {

    public static final int SCREEN_WIDTH = GamePanel.TILE_SIZE * 8;
    public static final int SCREEN_HEIGHT = GamePanel.SCREEN_HEIGHT;
    public static final ImagePanel imagePanel = new ImagePanel();
    public static final MissilePanel missilePanel = new MissilePanel();

    public DisplayPanel() {

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(imagePanel);
        this.add(missilePanel);

    }










}
