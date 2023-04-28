package model.missile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Missile {

    protected double x;
    protected double y;
    protected int speed;
    protected int orientation;
    protected int rotationSpeed;
    protected String pathToImage;
    protected boolean isVisible;

    protected BufferedImage image;

    public Missile(double x, double y, int speed, int orientation, int rotationSpeed, String pathToImage) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.orientation = orientation;
        this.rotationSpeed = rotationSpeed;
        this.pathToImage = pathToImage;

        image = null;

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

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public int getRotationSpeed() {
        return rotationSpeed;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setVisible(Boolean visible) {
        this.isVisible = visible;
        if (this.isVisible) {
            try {
                image = ImageIO.read(getClass().getResourceAsStream(this.pathToImage));
            } catch (IOException e) {
                System.out.println("Picture not found");
                image = null;
            }
        }
        else {
            image = null;
        }
    }

    public boolean isVisible() {
        return this.isVisible;
    }

}
