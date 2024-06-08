package entity;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(final GamePanel gamePanel, final KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        this.setDefaultValues();
        this.getImage();
    } // Player(..)

    public void setDefaultValues() {
        this.x = 100;
        this.y = 100;
        this.speed = 5;
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
            if(keyHandler.zPressed) {
                this.direction = "up";
                this.y -= this.speed;
            } // if "z" is pressed
            if(keyHandler.sPressed) {
                this.direction = "down";
                this.y += this.speed;
            } // if "s" is pressed
            if(keyHandler.dPressed) {
                this.direction = "right";
                this.x += this.speed;
            } // if "d" is pressed
            if(keyHandler.qPressed) {
                this.direction = "left";
                this.x -= this.speed;
            } // if "q" is pressed

            this.spriteCounter++;
            if(this.spriteCounter > 10) { // manage the speed of the animation.
                if(this.spriteNum == 1) {
                    this.spriteNum = 2;
                } // if 2nd
                else this.spriteNum = 1;

                this.spriteCounter = 0;
            } // if 1st
        } // if gen

    } // update()

    public void display(final Graphics2D g2) {
        BufferedImage image = null;
        switch (this.direction) {
            case "up":
                if(this.spriteNum == 1) {
                    image = up2;
                } // if
                else {
                    image = up1;
                } // else
                break;
            case "down":
                if(this.spriteNum == 1) {
                    image = down2;
                } // if
                else {
                    image = down1;
                } // else
                break;
            case "left":
                if(this.spriteNum == 1) {
                    image = left2;
                } // if
                else {
                    image = left1;
                } // else
                break;
            case "right":
                if(this.spriteNum == 1) {
                    image = right2;
                } // if
                else {
                    image = right1;
                } // else
                break;
        } // switch

        g2.drawImage(image, this.x, this.y, gamePanel.tileSize, gamePanel.tileSize, null);

    } // display()

} // Player
