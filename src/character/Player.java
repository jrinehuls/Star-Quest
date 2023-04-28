package character;

import controller.KeyHandler;
import model.missile.UnguidedMissile;
import model.ship.Ship;
import view.panel.GamePanel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Player {

    double m00 = 1; // width maybe
    double m10 = 0;
    double m01 = 0;
    double m11 = 1; // height maybe

    KeyHandler keyHandler;
    ArrayList<UnguidedMissile> firedUnguidedMissiles = new ArrayList<>();
    Ship playerShip;
    double x;
    double y;
    boolean allowFireable;
    int unguidedMissileCount;

    public Player(Ship playerShip, KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
        this.playerShip = playerShip;
        this.unguidedMissileCount = playerShip.getUnguidedMissileCount();

        x = (GamePanel.SCREEN_WIDTH - playerShip.getImage().getWidth() * m00) / 2.0;
        y = (GamePanel.SCREEN_HEIGHT - playerShip.getImage().getHeight() * m11) / 2.0;
        playerShip.setOrientation(0);
    }

    public void makeFireable() {
        if (keyHandler.unguidedMissileFired && unguidedMissileCount > 0) {
            allowFireable = true;
        }
    }

    public void fireMissile() {
        if (!keyHandler.unguidedMissileFired && allowFireable) {
            UnguidedMissile unguidedMissile = new UnguidedMissile(x, y, playerShip.getOrientation());
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
            y -= playerShip.getSpeed() * Math.cos(Math.toRadians(playerShip.getOrientation()));
            x += playerShip.getSpeed() * Math.sin(Math.toRadians(playerShip.getOrientation()));
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

        AffineTransform playerTransformer = new AffineTransform(m00, m10, m01, m11, x, y);
        playerTransformer.rotate(Math.toRadians(playerShip.getOrientation()),  playerShip.getImage().getWidth()/2.0,
                playerShip.getImage().getHeight()/2.0);
        g2d.drawImage(playerShip.getImage(), playerTransformer, null);
// -----------------------------Draw the unguided missiles------------------------------
        for (UnguidedMissile ugm: firedUnguidedMissiles) {
            if (ugm != null && ugm.getImage() != null) {
                AffineTransform unguidedMissileTransformer = new AffineTransform(m00, m10, m01, m11, ugm.getX(), ugm.getY());
                unguidedMissileTransformer.rotate(Math.toRadians(ugm.getOrientation()), ugm.getImage().getWidth() / 2.0,
                        ugm.getImage().getHeight() / 2.0);
                g2d.drawImage(ugm.getImage(), unguidedMissileTransformer, null);
            }
        }

    }

}
