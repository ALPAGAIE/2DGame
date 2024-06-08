import javax.swing.*;
import java.awt.*;
import java.security.Key;

public class GamePanel extends JPanel implements Runnable {

    //setting the screen settings
    final int ogTileSize = 16;
    final int scale = 4;

    final int tileSize = ogTileSize * scale;
    final int maxScreenColumn = 18;
    final int maxScreenRow = 10;

    final int screenWidth = maxScreenColumn * tileSize;
    final int screenHeight = maxScreenRow * tileSize;

    KeyHandler keyHandler = new KeyHandler();
    Thread clock; // "clock"

    //setting the player's settings
    int playerXPos = 100;
    int playerYPos = 100;
    int playerSpeed = 5;

    //FPS
    int FPS = 60;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);  // improve game render design
        this.addKeyListener(this.keyHandler);
        this.setFocusable(true); //

    } // GamePanel()

    public void startGameClock() {

        clock = new Thread(this);
        clock.start();

    } // startGameClock()

    @Override
    public void run() {

        //fps management
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while(clock != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1) {

                this.update();

                this.repaint();

                delta--;

            } // if

        } // while()

    } // run()

    public void update() {

        if(keyHandler.zPressed) {
            this.playerYPos -= this.playerSpeed;
        } // if "z" is pressed
        if(keyHandler.sPressed) {
            this.playerYPos += this.playerSpeed;
        } // if "s" is pressed
        if(keyHandler.dPressed) {
            this.playerXPos += this.playerSpeed;
        } // if "d" is pressed
        if(keyHandler.qPressed) {
            this.playerXPos -= this.playerSpeed;
        } // if "q" is pressed

    } // update()

    public void paintComponent(final Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.GREEN);
        g2.fillRect(this.playerXPos, this.playerYPos, this.tileSize, this.tileSize);
        g2.dispose();

    } // paintComponent()

} // GamePanel
