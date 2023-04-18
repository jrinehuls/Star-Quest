package model.ship;

import java.awt.image.BufferedImage;

public abstract class Ship {

    public double x;
    public double y;
    public int speed;
    public int orientation;
    public int rotationSpeed;

    public BufferedImage image = null;
}
