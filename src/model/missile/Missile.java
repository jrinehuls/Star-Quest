package model.missile;

import java.awt.image.BufferedImage;

public abstract class Missile {

    protected double x;
    protected double y;
    protected int speed;
    protected int orientation;
    protected int rotationSpeed;


    protected boolean isFired;

    protected BufferedImage image = null;


    public Missile(double x, double y, int speed, int orientation, int rotationSpeed, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.orientation = orientation;
        this.rotationSpeed = rotationSpeed;
        this.image = image;
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

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setFired(boolean fired) {
        isFired = fired;
    }

    public boolean isFired() {
        return isFired;
    }

}
