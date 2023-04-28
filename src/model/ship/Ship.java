package model.ship;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Ship {

    protected double x;
    protected double y;
    protected int speed;
    protected int orientation;
    protected int rotationSpeed;

    protected BufferedImage image;

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


    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
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


}
