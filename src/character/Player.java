package character;

import controller.KeyHandler;
import model.missile.Missile;
import model.missile.UnguidedMissile;
import model.ship.Ship;
import view.panel.GamePanel;
import view.panel.display.MissilePanel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Player {

    int m00 = 1; // width maybe
    int m10 = 0;
    int m01 = 0;
    int m11 = 1; // height maybe

    Ship playerShip;
    KeyHandler keyHandler;



    ArrayList<UnguidedMissile> firedUnguidedMissiles = new ArrayList<>();

    private boolean allowFireable;
    private int unguidedMissileCount;

    // Changed screen x & y to int from double.
    private static int screenX;
    private static int screenY;
    private static double worldX;
    private static double worldY;
    public static int WIDTH;
    public static int HEIGHT;

    AffineTransform playerTransformer;

    public Player(Ship playerShip, KeyHandler keyHandler) {
        this.playerShip = playerShip;
        this.keyHandler = keyHandler;
        this.unguidedMissileCount = playerShip.getUnguidedMissileCount();

        WIDTH = playerShip.getImage().getWidth() * m00;
        HEIGHT = playerShip.getImage().getHeight() * m11;

        screenX = (GamePanel.SCREEN_WIDTH - WIDTH) / 2;
        screenY = (GamePanel.SCREEN_HEIGHT - HEIGHT) / 2;
        worldX = (GamePanel.WORLD_WIDTH - WIDTH) / 2.0;
        worldY = (GamePanel.WORLD_HEIGHT - HEIGHT) / 2.0;

        playerTransformer = new AffineTransform(m00, m10, m01, m11, screenX, screenY);
        playerShip.setOrientation(0);
    }

    public void moveMissiles() {
        for (UnguidedMissile ugm: firedUnguidedMissiles) {
            ugm.setX(ugm.getX() + ugm.getSpeed() * Math.sin(Math.toRadians(ugm.getOrientation())));
            ugm.setY(ugm.getY() - ugm.getSpeed() * Math.cos(Math.toRadians(ugm.getOrientation())));
        }
    }

    public void movePlayer() {
        if (keyHandler.forwardPressed) {
            worldY -= playerShip.getSpeed() * Math.cos(Math.toRadians(playerShip.getOrientation()));
            worldX += playerShip.getSpeed() * Math.sin(Math.toRadians(playerShip.getOrientation()));
        }
        if (keyHandler.backwardPressed) {
            worldY += playerShip.getSpeed() * Math.cos(Math.toRadians(playerShip.getOrientation()));
            worldX -= playerShip.getSpeed() * Math.sin(Math.toRadians(playerShip.getOrientation()));
        }
        if (keyHandler.rotateRight) {
            playerShip.setOrientation(playerShip.getOrientation() + playerShip.getRotationSpeed());
        }
        if (keyHandler.rotateLeft) {
            playerShip.setOrientation(playerShip.getOrientation() - playerShip.getRotationSpeed());
        }
    }

    public void resetOrientation() {
        if (playerShip.getOrientation() == 360 || playerShip.getOrientation() == -360) {
            playerShip.setOrientation(0);
        }
    }

    public void update() {
        movePlayer();
        resetOrientation();
        moveMissiles();
    }

    public void draw(Graphics2D g2d) {

        playerTransformer.setToTranslation(screenX, screenY);
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

    public static double getWorldX() {
        return worldX;
    }

    public static double getWorldY() {
        return worldY;
    }

    public static int getScreenX() {
        return screenX;
    }

    public static int getScreenY() {
        return screenY;
    }

    public Ship getPlayerShip() {
        return playerShip;
    }

    public ArrayList<UnguidedMissile> getFiredUnguidedMissiles() {
        return firedUnguidedMissiles;
    }

    public void setFiredUnguidedMissiles(ArrayList<UnguidedMissile> firedUnguidedMissiles) {
        this.firedUnguidedMissiles = firedUnguidedMissiles;
    }

    public int getUnguidedMissileCount() {
        return unguidedMissileCount;
    }

    public void setUnguidedMissileCount(int unguidedMissileCount) {
        this.unguidedMissileCount = unguidedMissileCount;
    }

}
