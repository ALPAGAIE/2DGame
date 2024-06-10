package object;

import main.GamePanel;

public class ObjectManager {
    GamePanel gamePanel;

    public ObjectManager(final GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    } // ObjectManager(.)

    public void setObject() {
        gamePanel.obj[0] = new ObjKey();
        gamePanel.obj[0].worldX = 9 * gamePanel.tileSize;
        gamePanel.obj[0].worldY = 14 * gamePanel.tileSize;

        gamePanel.obj[1] = new ObjHouse();
        gamePanel.obj[1].worldX = 7 * gamePanel.tileSize;
        gamePanel.obj[1].worldY = 26 * gamePanel.tileSize;

        gamePanel.obj[2] = new ObjChest();
        gamePanel.obj[2].worldX = 7 * gamePanel.tileSize;
        gamePanel.obj[2].worldY = 28 * gamePanel.tileSize;
    } // setObject()
} // ObjectManager
