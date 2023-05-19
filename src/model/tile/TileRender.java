package model.tile;

import character.Player;
import view.panel.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileRender {

    private final byte NUM_TILE_TYPES = 6;
    BufferedImage[] tileImages = new BufferedImage[NUM_TILE_TYPES];

    byte[][] tile_map = new byte[GamePanel.WORLD_ROWS][GamePanel.WORLD_COLS];


    public TileRender() {
        // Assign buffered images to array
        try {
            tileImages[0] = ImageIO.read(getClass().getResourceAsStream("/res/stars/stars00.png"));
            tileImages[1] = ImageIO.read(getClass().getResourceAsStream("/res/stars/stars01.png"));
            tileImages[2] = ImageIO.read(getClass().getResourceAsStream("/res/stars/stars02.png"));
            tileImages[3] = ImageIO.read(getClass().getResourceAsStream("/res/stars/stars03.png"));
            tileImages[4] = ImageIO.read(getClass().getResourceAsStream("/res/stars/stars04.png"));
            tileImages[5] = ImageIO.read(getClass().getResourceAsStream("/res/stars/stars05.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        loadMapArrayRandomly();

    }

    public void loadMapArrayRandomly() {

        int col = 0;
        int row = 0;

        while(row < GamePanel.WORLD_ROWS) {
            while(col < GamePanel.WORLD_COLS) {
                tile_map[row][col] = (byte) (NUM_TILE_TYPES * Math.random());
                col++;
            }
            col = 0;
            row++;
        }
    }

    public void loadMapArrayFromFile() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/res/maps/map.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

        int col = 0;
        int row = 0;

        while(row < GamePanel.WORLD_ROWS) {
            String line = br.readLine();

            String[] numbers = line.split(" ");

            while(col < GamePanel.WORLD_COLS) {
                byte num = Byte.parseByte(numbers[col]);
                tile_map[row][col] = num;
                col++;
            }
            col = 0;
            row++;

        }
        br.close();
    }
    
    public void drawTiles(Graphics2D g2d) {
        int tileSize = GamePanel.TILE_SIZE;                         // 32
        int x = Player.getScreenX() - (int) Math.round(Player.getWorldX());     // 560 - 1712 = -1152
        int y = Player.getScreenY() - (int) Math.round(Player.getWorldY());     // 368 - 1136 = -768
        for (int i = 0; i < GamePanel.WORLD_ROWS; i++) {            // 72
            for (int j = 0; j < GamePanel.WORLD_COLS; j++) {        // 108
                g2d.drawImage(tileImages[tile_map[i][j]], x, y, tileSize, tileSize, null);
                x += tileSize;
            }
            x = Player.getScreenX() - (int) Player.getWorldX();
            y += tileSize;
        }
    }





}
