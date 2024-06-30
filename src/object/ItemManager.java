package object;

import main.GamePanel;

public class ItemManager {
    GamePanel gamePanel;

    public ItemManager(final GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    } // ObjectManager(.)

    public void getObjectImage() {
        gamePanel.obj[0] = new Key();
        this.setItemDefaultValue(0, 9, 14);

        gamePanel.obj[1] = new House();
        this.setItemDefaultValue(1, 7, 26);

        gamePanel.obj[2] = new Chest();
        this.setItemDefaultValue(2, 7, 28);

        gamePanel.obj[3] = new Chest();
        this.setItemDefaultValue(3, 11, 28);

        gamePanel.obj[4] = new Chest();
        this.setItemDefaultValue(4, 9, 28);

        gamePanel.obj[5] = new Key();
        this.setItemDefaultValue(5, 10, 14);

        gamePanel.obj[6] = new Key();
        this.setItemDefaultValue(6, 11, 14);

    } // getObjectImage()

    public void setItemDefaultValue(final int index, final int worldX, final int worldY) {
        gamePanel.obj[index].worldX = worldX * gamePanel.tileSize;
        gamePanel.obj[index].worldY = worldY * gamePanel.tileSize;
    } // setObject(...)

    public void interact(final int objIndex) {
        if(objIndex == 11) {return;}

        if(this.gamePanel.keyHandler.ePressed) {

            Item object = this.gamePanel.obj[objIndex];

            switch(object.name) {
                case "Key":
                    this.gamePanel.player.inventory.add(object);
                    this.gamePanel.obj[objIndex] = null;
                    this.gamePanel.playSoundEffects(4);
                    this.gamePanel.GUI.displayMessage("+1 key");
                    break;
                case "House":
                    break;
                case "Chest":
                    int index = 0;
                    int keyCount = 0;
                    for(int i = 0; i < this.gamePanel.player.inventory.size(); i++) {
                        if(this.gamePanel.player.inventory.get(i).name.equals("Key")) {
                            keyCount++;
                            index = i;
                        } // if
                    } // for
                    if(!((Chest) object).isOpen()) {
                        if(keyCount > 0) {
                            ((Chest) object).switchToOpenChest();
                            this.gamePanel.GUI.displayMessage("Chest opened !");
                            this.gamePanel.player.inventory.remove(index);
                            this.gamePanel.playSoundEffects(6);
                            keyCount--;
                        } // if
                        else {
                            this.gamePanel.GUI.displayMessage("need a key...");
                        }

                    }
                    break;
            } // switch
        } // if "e" pressed
    }
} // ObjectManager
