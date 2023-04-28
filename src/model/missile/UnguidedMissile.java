package model.missile;

import javax.imageio.ImageIO;
import java.io.IOException;

public class UnguidedMissile extends Missile{

    public UnguidedMissile(double x, double y, int orientation) {
        super(x, y, 8, orientation, 0, "/res/missile/Unguided.png");
    }


}
