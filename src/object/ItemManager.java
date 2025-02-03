package object;

import main.GamePanel;

public class ItemManager {
    GamePanel gamePanel;

    public ItemManager(final GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    } // ObjectManager(.)

    public void getObjectImage() {
        gamePanel.obj[0] = new Key();
        this.setItemDefaultValue(0, 33, 8);

        gamePanel.obj[1] = new House();
        this.setItemDefaultValue(1, 39, 37);

        gamePanel.obj[2] = new Chest();
        this.setItemDefaultValue(2, 29, 42);

        gamePanel.obj[3] = new Chest();
        this.setItemDefaultValue(3, 11, 41);

        gamePanel.obj[4] = new Chest();
        this.setItemDefaultValue(4, 29, 25);

        gamePanel.obj[5] = new Key();
        this.setItemDefaultValue(5, 30, 30);

        gamePanel.obj[6] = new Key();
        this.setItemDefaultValue(6, 13, 22);

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
                    if(allChestsOpened()) {
                        this.gamePanel.GUI.setWin();
                        break;
                    }
                    this.gamePanel.GUI.displayMessage("Some chests are not opened..");
                    break;
                case "Chest":
                    int[] values = countKeys();
                    int keyCount = values[0];
                    int index = values[1];

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
                            this.gamePanel.playSoundEffects(5);
                        }

                    }
                    break;
            } // switch
        } // if "e" pressed
    }

    public int[] countKeys() {
        int index = 0;
        int keyCount = 0;
        for(int i = 0; i < this.gamePanel.player.inventory.size(); i++) {
            if(this.gamePanel.player.inventory.get(i).name.equals("Key")) {
                keyCount++;
                index = i;
            } // if
        } // for

        return new int[]{keyCount, index};
    }

    public boolean allChestsOpened() {
        boolean chestOpened = true;

        for(int i = 2; i < 5; i++) {
            Item item = gamePanel.obj[i];
            if (!((Chest) item).isOpen()) {
                chestOpened = false;
            }
        }

        return chestOpened;
    }
} // ObjectManager
