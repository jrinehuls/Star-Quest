package character;

import controller.KeyHandler;
import model.missile.UnguidedMissile;
import model.ship.Ship;
import view.panel.GamePanel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Player {

    int m00 = 1; // width maybe
    int m10 = 0;
    int m01 = 0;
    int m11 = 1; // height maybe

    KeyHandler keyHandler;
    ArrayList<UnguidedMissile> firedUnguidedMissiles = new ArrayList<>();
    Ship playerShip;

    private boolean allowFireable;
    private int unguidedMissileCount;

    // TODO: Use worldX and worldY for player movement. Call playerX screenX and make final.
    private static double playerX;
    private static double playerY;
    private static int worldX;
    private static int worldY;
    public static int WIDTH;
    public static int HEIGHT;

    public Player(Ship playerShip, KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
        this.playerShip = playerShip;
        this.unguidedMissileCount = playerShip.getUnguidedMissileCount();

        WIDTH = playerShip.getImage().getWidth() * m00;
        HEIGHT = playerShip.getImage().getHeight() * m11;

        playerX = (GamePanel.SCREEN_WIDTH - WIDTH) / 2.0;
        playerY = (GamePanel.SCREEN_HEIGHT - HEIGHT) / 2.0;
        worldX = (GamePanel.WORLD_WIDTH - WIDTH) / 2;
        worldY = (GamePanel.WORLD_HEIGHT - HEIGHT) / 2;

        playerShip.setOrientation(0);
    }

    public void makeFireable() {
        if (keyHandler.unguidedMissileFired && unguidedMissileCount > 0) {
            allowFireable = true;
        }
    }

    public void fireMissile() {
        if (!keyHandler.unguidedMissileFired && allowFireable) {
            UnguidedMissile unguidedMissile = new UnguidedMissile(playerX, playerY, playerShip.getOrientation());
            unguidedMissile.setVisible(true);
            firedUnguidedMissiles.add(unguidedMissile);
            unguidedMissileCount--;
            allowFireable = false;
        }
    }

    public void moveMissiles() {
        for (UnguidedMissile ugm: firedUnguidedMissiles) {
            ugm.setX(ugm.getX() + ugm.getSpeed() * Math.sin(Math.toRadians(ugm.getOrientation())));
            ugm.setY(ugm.getY() - ugm.getSpeed() * Math.cos(Math.toRadians(ugm.getOrientation())));
        }
    }

    public void update() {
        if (keyHandler.forwardPressed) {
            worldY -= playerShip.getSpeed() * Math.cos(Math.toRadians(playerShip.getOrientation()));
            worldX += playerShip.getSpeed() * Math.sin(Math.toRadians(playerShip.getOrientation()));

        }
        if (keyHandler.rotateRight) {
            playerShip.setOrientation(playerShip.getOrientation() + playerShip.getRotationSpeed());
        }
        if (keyHandler.rotateLeft) {
            playerShip.setOrientation(playerShip.getOrientation() - playerShip.getRotationSpeed());
        }
        makeFireable();
        fireMissile();
        moveMissiles();

    }

    public void draw(Graphics2D g2d) {

        AffineTransform playerTransformer = new AffineTransform(m00, m10, m01, m11, playerX, playerY);
        playerTransformer.rotate(Math.toRadians(playerShip.getOrientation()),  playerShip.getImage().getWidth()/2.0,
                playerShip.getImage().getHeight()/2.0);
        g2d.drawImage(playerShip.getImage(), playerTransformer, null);
// -----------------------------Draw the unguided missiles------------------------------
        for (UnguidedMissile ugm: firedUnguidedMissiles) {
            //if (ugm != null && ugm.getImage() != null) {
                AffineTransform unguidedMissileTransformer = new AffineTransform(m00, m10, m01, m11, ugm.getX(), ugm.getY());
                unguidedMissileTransformer.rotate(Math.toRadians(ugm.getOrientation()), ugm.getImage().getWidth() / 2.0,
                        ugm.getImage().getHeight() / 2.0);
                g2d.drawImage(ugm.getImage(), unguidedMissileTransformer, null);
            //}
        }
    }

    public static int getWorldX() {
        return worldX;
    }

    public static int getWorldY() {
        return worldY;
    }

    public static double getScreenX() {
        return playerX;
    }

    public static double getScreenY() {
        return playerY;
    }
}
