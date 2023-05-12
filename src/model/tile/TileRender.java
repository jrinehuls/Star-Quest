package model.tile;

import character.Player;
import view.panel.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TileRender {

    private final byte NUM_TILE_TYPES = 3;

    Tile[] tiles = new Tile[NUM_TILE_TYPES];
    BufferedImage[] tileImages = new BufferedImage[NUM_TILE_TYPES];

    public TileRender() {

        try {
            tileImages[0] = ImageIO.read(getClass().getResourceAsStream("/res/tile/red.png"));
            tileImages[1] = ImageIO.read(getClass().getResourceAsStream("/res/tile/green.png"));
            tileImages[2] = ImageIO.read(getClass().getResourceAsStream("/res/tile/blue.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        for (int i = 0; i < NUM_TILE_TYPES; i++) {
            tiles[i] = new Tile(tileImages[i]);
        }
    }

    
    public void drawTiles(Graphics2D g2d) {
        int tileSize = GamePanel.TILE_SIZE;                         // 32
        int x = (int) Player.getScreenX() - Player.getWorldX();     // 560 - 1712 = -1152
        int y = (int) Player.getScreenY() - Player.getWorldY();     // 368 - 1136 = -768
        System.out.println(x + " " + y);
        for (int i = 0; i < GamePanel.WORLD_ROWS; i++) {            // 72
            for (int j = 0; j < GamePanel.WORLD_COLS; j++) {        // 108
                g2d.drawImage(tileImages[TileMap.TILE_MAP[i][j]], x, y, tileSize, tileSize, null);
                x += tileSize;
            }
            x = (int) Player.getScreenX() - Player.getWorldX();
            y += tileSize;
        }
    }





}
