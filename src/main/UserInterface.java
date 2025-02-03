package main;

import object.Key;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class UserInterface {

    private GamePanel gamePanel;
    private Font font;
    private BufferedImage keyImage;
    private BufferedImage winImage;
    private boolean messageDisplay = false;
    private String message = "";
    private int messageCounter = 0;
    private boolean hasWon = false;

    public UserInterface(final GamePanel gamePanel) {

        this.gamePanel = gamePanel;
        this.font = new Font("Calibri", Font.BOLD, 40);
        Key key = new Key();
        this.keyImage = key.image;
        try {
            this.winImage = ImageIO.read(getClass().getResourceAsStream("/maps/winImg.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        } // try/catch


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

    } // display(.)

    public void setWin() {
        this.hasWon = true;
    }

    public boolean hasWon() {return this.hasWon;}

    public void displayVictory(final Graphics2D g2) {
        g2.setFont(this.font);
        g2.drawImage(this.winImage, 0, 0, this.gamePanel.tileSize*50, this.gamePanel.tileSize*50, null);
        g2.setColor(Color.BLACK);

        g2.setFont(g2.getFont().deriveFont(25F));
        g2.drawString("CONGRATULATIONS, YOU WIN !!!", this.gamePanel.screenWidth/2 - this.gamePanel.tileSize/2, this.gamePanel.screenHeight/6);

    } // display(.)
} // UserInterface
