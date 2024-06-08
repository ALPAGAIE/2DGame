import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean zPressed, sPressed, qPressed, dPressed;

    @Override
    public void keyTyped(KeyEvent e) {} // keyTyped(.)

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        if(key == KeyEvent.VK_Z) {
            zPressed = true;
        } // if key = "Z"

        if(key == KeyEvent.VK_Q) {
            qPressed = true;
        } // if key = "Q"

        if(key == KeyEvent.VK_D) {
            dPressed = true;
        } // if key = "D"

        if(key == KeyEvent.VK_S) {
            sPressed = true;
        } // if key = "S"

    } // keyPressed(.)

    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        if(key == KeyEvent.VK_Z) {
            zPressed = false;
        } // if key = "Z"

        if(key == KeyEvent.VK_Q) {
            qPressed = false;
        } // if key = "Q"

        if(key == KeyEvent.VK_D) {
            dPressed = false;
        } // if key = "D"

        if(key == KeyEvent.VK_S) {
            sPressed = false;
        } // if key = "S"

    } // keyReleased(.)

} // KeyHandler

