package controller;

import character.Player;
import model.missile.UnguidedMissile;
import view.panel.GamePanel;
import view.panel.display.MissilePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonHandler implements ActionListener {

    // TODO: Remove commented out functionality of fire missile in player class
    Player player;
    MissilePanel mp;
    public ButtonHandler(MissilePanel mp) {
        this.mp = mp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player = GamePanel.getPlayer();
        if (player.getUnguidedMissileCount() > 0) {
            UnguidedMissile unguidedMissile = new UnguidedMissile(Player.getScreenX(), Player.getScreenY(), player.getPlayerShip().getOrientation());
            unguidedMissile.setVisible(true);
            player.getFiredUnguidedMissiles().add(unguidedMissile);
            player.setUnguidedMissileCount(player.getUnguidedMissileCount() - 1);
        }
        mp.updateButtons();
    }
}
