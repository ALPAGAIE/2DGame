package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Chest extends Item {

    private boolean isOpen = false;

    public Chest() {

        this.name = "Chest";

        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/chest.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } // try/catch

        this.hitBox.x = 0;
        this.hitBox.y = 0;
        this.hitBox.width = 64;
        this.hitBox.height = 64;
        this.hitBoxDefaultX = 0;
        this.hitBoxDefaultY = 0;

        this.collision = true;

    } // ObjChest()

    public void switchToOpenChest() {
        try {
            this.image = ImageIO.read(getClass().getResourceAsStream("/objects/chest_open.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } // try/catch

        this.isOpen = true;
    } // switchToOpenChest()

    public boolean isOpen() {

        return this.isOpen;

    } // isOpen()

} // ObjChest
