package model.ship;

public class Fighter extends Ship {

    public Fighter() {
        super("/res/ship/Fighter.png", 3, 1);
        unguidedMissileCount = 8;
    }

}
