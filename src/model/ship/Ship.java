package model.ship;

import model.missile.UnguidedMissile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Ship {

    protected int speed;
    protected int orientation;
    protected int rotationSpeed;

    protected BufferedImage image;
    protected int unguidedMissileCount;

    public Ship(String pathToImage, int speed, int rotationSpeed) {
        this.speed = speed;
        this.rotationSpeed = rotationSpeed;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(pathToImage));
        } catch (IOException e) {
            System.out.println("Picture not found");
            image = null;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public int getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(int rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public int getUnguidedMissileCount() {
        return unguidedMissileCount;
    }

}
