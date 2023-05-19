package view.panel;

import character.Player;
import controller.KeyHandler;
import model.ship.Fighter;
import model.tile.TileRender;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    public static final int TILE_SIZE = 32;

    public static final int SCREEN_COLS = 30;
    public static final int SCREEN_ROWS = 20;
    public static final int SCREEN_WIDTH = TILE_SIZE * SCREEN_COLS;
    public static final int SCREEN_HEIGHT = TILE_SIZE * SCREEN_ROWS;

    public static final int WORLD_COLS = 3 * SCREEN_COLS;
    public static final int WORLD_ROWS = 3 * SCREEN_ROWS;
    public static final int WORLD_WIDTH = TILE_SIZE * WORLD_COLS;
    public static final int WORLD_HEIGHT = TILE_SIZE * WORLD_ROWS;

    private Thread gameThread = new Thread(this);

    static KeyHandler keyHandler = new KeyHandler();

    static Player player = new Player(new Fighter(), keyHandler);
    TileRender tileRender = new TileRender();

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
        tileRender.drawTiles(g2d);
        player.draw(g2d);
    }

    @Override
    public void run() {

        final double FPS = 60;
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

    public static Player getPlayer() {
        return player;
    }

}
