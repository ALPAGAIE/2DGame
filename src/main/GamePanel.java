package main;

import entity.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //setting the screen settings
    final int ogTileSize = 16;
    final int scale = 4;

    public final int tileSize = ogTileSize * scale;
    final int maxScreenColumn = 18;
    final int maxScreenRow = 10;

    public final int screenWidth = maxScreenColumn * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;

    KeyHandler keyHandler = new KeyHandler();
    Thread clock; // "clock"
    Player player = new Player(this, this.keyHandler);

    //FPS
    int FPS = 60;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);  // improve game render design
        this.addKeyListener(this.keyHandler);
        this.setFocusable(true); //

    } // main.GamePanel()

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

        player.update();

    } // update()

    public void paintComponent(final Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        player.display(g2);
        g2.dispose();

    } // paintComponent()

} // main.GamePanel
