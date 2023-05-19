package view.frame;

import view.panel.display.DisplayPanel;
import view.panel.GamePanel;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon("src/res/ship/Fighter.png").getImage());
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.add(new GamePanel());
        this.add(new DisplayPanel());
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
