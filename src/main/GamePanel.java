package main;

import entity.Player;
import object.ItemManager;
import object.Item;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    //setting the screen settings
    final int ogTileSize = 16;
    final int scale = 4;

    public final int tileSize = ogTileSize * scale;
    public final int maxScreenColumn = 18;
    public final int maxScreenRow = 10;

    public final int screenWidth = maxScreenColumn * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;

    //SYSTEM
    public KeyHandler keyHandler = new KeyHandler();
    Thread clock; // "clock"
    public TileManager tileManager = new TileManager(this);
    public CollisionManager collisionManager = new CollisionManager(this);
    public Sound sound = new Sound();
    public UserInterface GUI = new UserInterface(this);


    //ENTITY AND OBJECTS
    public Player player = new Player(this, this.keyHandler);
    public ItemManager itemManager = new ItemManager(this);
    public Item[] obj = new Item[10];

    //FPS
    int FPS = 60;

    // World map parameters
    public final int maxWorldColumn = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = this.maxWorldColumn * this.tileSize;
    public final int worldHeight = this.maxWorldRow * this.tileSize;

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

    public void setupObjects() {

        this.itemManager.getObjectImage();
        this.playMusic(2);

    } // setupObjects()

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

        this.tileManager.display(g2);

        for(Item obj : this.obj) {
            if(obj != null) {
                obj.display(g2, this);
            } // if
        } // for each

        this.player.display(g2);
        this.GUI.display(g2);

        if(this.GUI.hasWon()) {
            this.GUI.displayVictory(g2);
            this.clock = null;
        }

        g2.dispose();

    } // paintComponent()

    public void playMusic(final int i) {

        sound.setClip(i);
        sound.play();
        sound.loop();

    } // playMusic(.)

    public void stopMusic() {

        sound.stop();

    } // stopMusic()

    public void playSoundEffects(final int i) {

        sound.setClip(i);
        sound.play();

    } // playSoundEffects(.)

} // main.GamePanel
