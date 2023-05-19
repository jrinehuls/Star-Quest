package view.panel.display;

import controller.ButtonHandler;
import view.panel.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MissilePanel extends JPanel {

    public static final int SCREEN_WIDTH = DisplayPanel.SCREEN_WIDTH;
    public static final int SCREEN_HEIGHT = DisplayPanel.SCREEN_HEIGHT - ImagePanel.SCREEN_HEIGHT;
    private final JLabel missileLabel = new JLabel("Missiles:");
    private JPanel textPanel = new JPanel();
    public static final int TEXT_PANEL_HEIGHT = 50;
    private JPanel buttonPanel = new JPanel();
    private Icon unguidedMissile = new ImageIcon("src/res/missile/Unguided.png");

    private ArrayList<JButton> buttonList = new ArrayList<>();

    ButtonHandler bh;

    public MissilePanel() {

        bh = new ButtonHandler(this);

        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBorder(BorderFactory.createLineBorder(Color.black, 1, false));

        // -------------------------------------Top Panel-----------------------------------------

        textPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        textPanel.setPreferredSize(new Dimension(SCREEN_WIDTH, TEXT_PANEL_HEIGHT));
        missileLabel.setPreferredSize(new Dimension(SCREEN_WIDTH, 50));
        missileLabel.setHorizontalAlignment(JLabel.CENTER);
        missileLabel.setVerticalAlignment(JLabel.CENTER);
        missileLabel.setFont(new Font("sans serif", Font.BOLD, 36));
        textPanel.add(missileLabel);
        this.add(textPanel);

        // ------------------------------------Bottom Panel---------------------------------------

        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        buttonPanel.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT - TEXT_PANEL_HEIGHT));
        updateButtons();
        this.add(buttonPanel);
    }

    public void updateButtons() {
        removeButtons();
        loadButtonList();
        growButtons();
    }

    public void removeButtons() {
        for (JButton button: buttonList) {
            buttonPanel.remove(button);
        }
        repaint();
        revalidate();
    }

    public void loadButtonList() {
        buttonList.clear();
        for (int i = 1; i <= GamePanel.getPlayer().getUnguidedMissileCount(); i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(25, 50));
            button.setIcon(unguidedMissile);
            button.setFocusable(false);
            button.addActionListener(bh);
            buttonList.add(button);
        }
    }

    public void growButtons() {
        for (JButton button: buttonList) {
            buttonPanel.add(button);
        }
    }

}
