package entity;
import main.GamePanel;
import main.KeyHandler;
import object.Item;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;
    public ArrayList<Item> inventory;

    public final int screenX;
    public final int screenY;

    public Player(final GamePanel gamePanel, final KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.inventory = new ArrayList<Item>();

        this.hitBox = new Rectangle();
        this.hitBox.x = 20;
        this.hitBox.y = 40;
        this.hitBox.width = 32;
        this.hitBox.height = 16;
        this.hitBoxDefaultX = this.hitBox.x;
        this.hitBoxDefaultY = this.hitBox.y;

        this.screenX = (gamePanel.screenWidth/2) - (gamePanel.tileSize/2);
        this.screenY = (gamePanel.screenHeight/2) + gamePanel.tileSize;
        this.setDefaultValues();
        this.getImage();
    } // Player(..)

    public void setDefaultValues() {
        this.worldX = 200;
        this.worldY = 200;
        this.speed = 4;
        this.direction = "down";
    } // setDefaultValues()

    public void getImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    } // getImage()

    public void update() {
        if(this.keyHandler.aKeyIsPressed()) {
            if(keyHandler.zPressed) this.direction = "up"; // if "z" is pressed
            if(keyHandler.sPressed) this.direction = "down"; // if "s" is pressed
            if(keyHandler.dPressed) this.direction = "right"; // if "d" is pressed
            if(keyHandler.qPressed) this.direction = "left"; // if "q" is pressed

            //Checking if there's a collision with a tile
            collisionOn = false;
            gamePanel.collisionManager.checkTile(this);

            //Checking if there's a collision with an object
            int objIndex = gamePanel.collisionManager.checkObject(this, true);

            //if there's no collision
            if(!this.collisionOn) {
                switch (direction) {
                    case "up": this.worldY -= this.speed; break;
                    case "down": this.worldY += this.speed; break;
                    case "right": this.worldX += this.speed; break;
                    case "left": this.worldX -= this.speed; break;
                } // switch(.)
            } // if

            this.spriteCounter++;
            if(this.spriteCounter > 10) { // manage the speed of the animation.
                if(this.spriteNum == 1) this.spriteNum = 2; // if 2nd
                else this.spriteNum = 1;

                this.spriteCounter = 0;
            } // if 1st
        } // if move

        if(this.gamePanel.keyHandler.ePressed) {
            int obj = this.gamePanel.collisionManager.checkObject(this, true);
            if(this.collisionOn) {
                this.gamePanel.itemManager.interact(obj);
            } // if 2nd
        } // if

    } // update()

    public void display(final Graphics2D g2) {
        BufferedImage image = null;
        switch (this.direction) {
            case "up":
                if(this.spriteNum == 1) image = up2;
                else image = up1;
                break;
            case "down":
                if(this.spriteNum == 1) image = down2;
                else image = down1;
                break;
            case "left":
                if(this.spriteNum == 1)  image = left2;
                else image = left1;
                break;
            case "right":
                if(this.spriteNum == 1) image = right2;
                else image = right1;
                break;
        } // switch

        g2.drawImage(image, this.screenX, this.screenY, gamePanel.tileSize, gamePanel.tileSize, null);

    } // display()

    public int getKey() {

        int ans = 0;
        for(Item item : this.inventory) {
            if(item.name.equals("Key")) ans++;
        } // for each
        return ans;

    } // getKey()

} // Player
