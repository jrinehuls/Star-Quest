package view;

import model.ship.Player;
import controller.KeyHandler;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    public static final int TILE_SIZE = 32;
    private static final int NUM_COLS = 36;
    private static final int NUM_ROWS = 24;
    public static final int SCREEN_WIDTH = TILE_SIZE * NUM_COLS;
    public static final int SCREEN_HEIGHT = TILE_SIZE * NUM_ROWS;

    private Thread gameThread = new Thread(this);

    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(keyHandler);

    public GamePanel() {
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(new Color(0, 0, 0));
        this.addKeyListener(keyHandler);

        gameThread.start();
    }

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        player.draw(g2d);
    }

    @Override
    public void run() {

        final int FPS = 60;
        long lastTime = System.nanoTime();
        long currentTime;
        double drawInterval = 1_000_000_000 / FPS;
        double delta = 0;
        while(true) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
            lastTime = currentTime;
        }
    }

}
