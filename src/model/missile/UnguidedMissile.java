package model.missile;

import javax.imageio.ImageIO;
import java.io.IOException;

public class UnguidedMissile extends Missile{

    public UnguidedMissile(double x, double y, int orientation) {
        super(x, y, 8, orientation, 0, null);

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/missile/Unguided.png"));
        } catch (IOException e) {
            System.out.println("Picture not found");
        }
    }

}
