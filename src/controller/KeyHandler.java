package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean forwardPressed, rotateRight, rotateLeft;

    public KeyHandler() {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            forwardPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            rotateLeft = true;
        }
        if (code == KeyEvent.VK_D) {
            rotateRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            forwardPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            rotateLeft = false;
        }
        if (code == KeyEvent.VK_D) {
            rotateRight = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
