package character;

import controller.KeyHandler;
import model.missile.UnguidedMissile;
import model.ship.Ship;
import view.panel.GamePanel;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Player {

    double m00 = 1; // width maybe
    double m10 = 0;
    double m01 = 0;
    double m11 = 1; // height maybe

    KeyHandler keyHandler;
    UnguidedMissile unguidedMissile = null;
    Ship playerShip;
    double x;
    double y;

    public Player(Ship playerShip, KeyHandler keyHandler) {
        this.keyHandler = keyHandler;
        this.playerShip = playerShip;

        x = (GamePanel.SCREEN_WIDTH - playerShip.getImage().getWidth() * m00) / 2.0;
        y = (GamePanel.SCREEN_HEIGHT - playerShip.getImage().getHeight() * m11) / 2.0;
        playerShip.setOrientation(0);
    }

    public void createMissile() {
        if (keyHandler.missileFired) {
            unguidedMissile = new UnguidedMissile(x, y, playerShip.getOrientation());
        }
    }
    public void fireMissile() {
        if (!keyHandler.missileFired && unguidedMissile != null) {
            if (!unguidedMissile.isVisible()) {
                unguidedMissile.setVisible(true);
            }
            unguidedMissile.setX(unguidedMissile.getX() + unguidedMissile.getSpeed() * Math.sin(Math.toRadians(unguidedMissile.getOrientation())));
            unguidedMissile.setY(unguidedMissile.getY() - unguidedMissile.getSpeed() * Math.cos(Math.toRadians(unguidedMissile.getOrientation())));
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
        createMissile();
        fireMissile();

    }

    public void draw(Graphics2D g2d) {

        AffineTransform playerTransformer = new AffineTransform(m00, m10, m01, m11, x, y);
        playerTransformer.rotate(Math.toRadians(playerShip.getOrientation()),  playerShip.getImage().getWidth()/2.0,
                playerShip.getImage().getHeight()/2.0);
        g2d.drawImage(playerShip.getImage(), playerTransformer, null);

        if (unguidedMissile != null && unguidedMissile.getImage() != null) {
            AffineTransform unguidedMissileTransformer = new AffineTransform(m00, m10, m01, m11, unguidedMissile.getX(), unguidedMissile.getY());
            unguidedMissileTransformer.rotate(Math.toRadians(unguidedMissile.getOrientation()), unguidedMissile.getImage().getWidth() / 2.0, unguidedMissile.getImage().getHeight() / 2.0);
            g2d.drawImage(unguidedMissile.getImage(), unguidedMissileTransformer, null);
        }
    }
}
