package object;

import main.GamePanel;
import tile.Tile;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjectManager {
    GamePanel gamePanel;

    public ObjectManager(final GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    } // ObjectManager(.)

    public void getObjectImage() {
        gamePanel.obj[0] = new ObjKey();
        this.setObject(0, 9, 14);

        gamePanel.obj[1] = new ObjHouse();
        this.setObject(1, 7, 26);

        gamePanel.obj[2] = new ObjChest();
        this.setObject(2, 7, 28);

    } // getObjectImage()

    public void setObject(final int index, final int worldX, final int worldY) {
        gamePanel.obj[index].worldX = worldX * gamePanel.tileSize;
        gamePanel.obj[index].worldY = worldY * gamePanel.tileSize;
    } // setObject(...)

    public void interact(final int objIndex) {
        if(objIndex == 11) {return;}

        if(this.gamePanel.keyHandler.ePressed) {

            SuperObject object = this.gamePanel.obj[objIndex];

            switch(object.name) {
                case "Key":
                    this.gamePanel.player.inventory.add(object);
                    this.gamePanel.obj[objIndex] = null;
                    break;
                case "House":
                    break;
                case "Chest":
                    int keyCount = 0;
                    for(SuperObject obj : this.gamePanel.player.inventory) {
                        if(obj.name.equals("Key")) keyCount++;
                    }
                    if(keyCount > 0) {
                        ((ObjChest) object).switchToOpenChest();
                        keyCount--;
                    } // if
                    break;
            } // switch
        } // if "e" pressed
    }
} // ObjectManager
