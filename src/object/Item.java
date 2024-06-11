package object;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {

    public BufferedImage image;
    public String name;
    public boolean collision;
    public int worldX, worldY;
    public Rectangle hitBox = new Rectangle();
    public int hitBoxDefaultX, hitBoxDefaultY;

    public void display(final Graphics2D g2, final GamePanel gamePanel) {

        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

            g2.drawImage(this.image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

        } // if
    } // display(..)
} // SuperObject
