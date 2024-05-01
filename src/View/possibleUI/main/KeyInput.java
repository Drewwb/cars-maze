package src.View.possibleUI.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W) {
            upPressed = true;
        }
        if(key == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if(key == KeyEvent.VK_S) {
            downPressed = true;
        }
        if(key == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(key == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(key == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(key == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
