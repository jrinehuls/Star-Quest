package model.ship;

import controller.KeyHandler;
import model.missile.UnguidedMissile;
import view.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Ship {

    KeyHandler keyHandler;
    double m00 = 1; // width maybe
    double m10 = 0;
    double m01 = 0;
    double m11 = 1; // height maybe

    UnguidedMissile unguidedMissile = null;

    public Player(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;

        this.image = getImage();

        orientation = 0;
        rotationSpeed = 1;
        x = (GamePanel.SCREEN_WIDTH - image.getWidth() * m00) / 2.0;
        y = (GamePanel.SCREEN_HEIGHT - image.getHeight() * m11) / 2.0;
        speed = 3;

    }

    public BufferedImage getImage() {
        try {

            return ImageIO.read(getClass().getResourceAsStream("/res/ship/Fighter.png"));


        } catch (IOException e) {
            System.out.println("Picture not found");
            return null;
        }
    }

    public void fireMissile() {
        unguidedMissile = new UnguidedMissile(x, y, orientation);
        unguidedMissile.setFired(true);
    }

    public void update() {
        if (keyHandler.forwardPressed) {
            y -= speed * Math.cos(Math.toRadians(orientation));
            x += speed * Math.sin(Math.toRadians(orientation));
        }
        if (keyHandler.rotateRight) {
            orientation += rotationSpeed;
        }
        if (keyHandler.rotateLeft) {
            orientation -= rotationSpeed;
        }
        if (keyHandler.missileFired) {
            fireMissile();
        }
        if (unguidedMissile != null && unguidedMissile.isFired()) {
            unguidedMissile.setX(unguidedMissile.getX() + unguidedMissile.getSpeed() * Math.sin(Math.toRadians(unguidedMissile.getOrientation())));
            unguidedMissile.setY(unguidedMissile.getY() - unguidedMissile.getSpeed() * Math.cos(Math.toRadians(unguidedMissile.getOrientation())));
        }
    }

    public void draw(Graphics2D g2d) {

        AffineTransform playerTransformer = new AffineTransform(m00, m10, m01, m11, x, y);
        playerTransformer.rotate(Math.toRadians(orientation), image.getWidth()/2.0, image.getHeight()/2.0);
        g2d.drawImage(image, playerTransformer, null);

        if (unguidedMissile != null) {
            AffineTransform unguidedMissileTransformer = new AffineTransform(m00, m10, m01, m11, unguidedMissile.getX(), unguidedMissile.getY());
            unguidedMissileTransformer.rotate(Math.toRadians(unguidedMissile.getOrientation()), unguidedMissile.getImage().getWidth() / 2.0, unguidedMissile.getImage().getHeight() / 2.0);
            g2d.drawImage(unguidedMissile.getImage(), unguidedMissileTransformer, null);
        }
    }
}
