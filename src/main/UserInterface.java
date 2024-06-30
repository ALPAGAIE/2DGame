package main;

import object.Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UserInterface {

    private GamePanel gamePanel;
    private Font font;
    private BufferedImage keyImage;
    private boolean messageDisplay = false;
    private String message = "";
    private int messageCounter = 0;

    public UserInterface(final GamePanel gamePanel) {

        this.gamePanel = gamePanel;
        this.font = new Font("Arial", Font.BOLD, 40);
        Key key = new Key();
        this.keyImage = key.image;

    } // UserInterface(.)

    public void displayMessage(final String txt) {
        this.message = txt;
        this.messageDisplay = true;
    } // displayMessage(.)

    public void display(final Graphics2D g2) {

        g2.setFont(this.font);
        g2.drawImage(this.keyImage, (this.gamePanel.tileSize)/2, (this.gamePanel.tileSize)/2, this.gamePanel.tileSize, this.gamePanel.tileSize, null);
        g2.setColor(Color.BLACK);
        g2.drawString(": " + this.gamePanel.player.getKey(), 95, 75);

        if(this.messageDisplay) {

            g2.setFont(g2.getFont().deriveFont(25F));
            g2.drawString(this.message, this.gamePanel.screenWidth/2 + this.gamePanel.tileSize, this.gamePanel.screenHeight/2 + this.gamePanel.tileSize/2);
            this.messageCounter++;

            if(this.messageCounter > 120) {
                this.messageCounter = 0;
                this.messageDisplay = false;
            } // if scnd

        } // if

    } // draw(.)

} // UserInterface
