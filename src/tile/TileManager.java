package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tile;
    public int[][] mapTimeNum;

    public TileManager(final GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.tile = new Tile[36];
        this.mapTimeNum = new int[gamePanel.maxWorldColumn][gamePanel.maxWorldRow];

        this.getTileImage();
        this.loadMap("/maps/map");
    } // TileManager(.)

    public void getTileImage() {

        this.setTile(0, "paths/end-top", false); //
        this.setTile(1, "waters/vert-left", true); //
        this.setTile(2, "waters/vert-right", true); //
        this.setTile(3, "walls/incorner-left", true); //
        this.setTile(4, "walls/corner-bot-right", true); //
        this.setTile(5, "walls/hor_down", true); //
        this.setTile(6, "walls/vert_right", true); //
        this.setTile(7, "waters/corner-top-right", true); //
        this.setTile(8, "waters/paddle", true); //
        this.setTile(9, "waters/cornerin-bot-left", true); //
        this.setTile(10, "waters/cornerin-top-right", true); //
        this.setTile(11, "paths/bot-right", false); //
        this.setTile(12, "paths/bot-left", false); //
        this.setTile(13, "walls/corner-bot-left", true); //
        this.setTile(14, "walls/vert_left", true); //
        this.setTile(15, "paths/vert", false); //
        this.setTile(16, "walls/corner-top-left", true); //
        this.setTile(17, "grass", false); //
        this.setTile(18, "paths/top-left", false); //
        this.setTile(19, "paths/end-bot", false); //
        this.setTile(20, "waters/corner-bot-right", true); //
        this.setTile(21, "walls/corner-top-right", true); //
        this.setTile(22, "paths/cross", false); //
        this.setTile(23, "waters/hor-down", true); //
        this.setTile(24, "waters/water", true); //
        this.setTile(25, "tree", true); //
        this.setTile(26, "walls/hor_up", true); //
        this.setTile(27, "paths/end-left", false); //
        this.setTile(28, "paths/end-right", false); //
        this.setTile(29, "paths/top-right", false); //
        this.setTile(30, "paths/t", false); //
        this.setTile(31, "walls/stairs", false); //
        this.setTile(32, "waters/corner-bot-left", true); //
        this.setTile(33, "walls/incorner-right", true); //
        this.setTile(34, "paths/hor", false); //
        this.setTile(35, "waters/corner-top-left", true); //
    } // getTileImage()

    public void setTile(final int index, final String name, final boolean collision) {

        this.tile[index] = new Tile();

        try {
            this.tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + name + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        } // try/catch

        this.tile[index].collision = collision;

    } // setTile(...)

    public void loadMap(final String path) {
        try {
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is)); // import and read the map file

            int col = 0;
            int row = 0;

            while(col < gamePanel.maxWorldColumn && row < gamePanel.maxWorldRow) {

                String line = bufferedReader.readLine(); // read one line of the txt file

                while (col < gamePanel.maxWorldColumn) {

                    String[] numbers = line.split(" "); // split the line into numbers
                    int num = Integer.parseInt(numbers[col]); // convert "number" into number
                    this.mapTimeNum[col][row] = num;
                    col++;

                } // while 2nd

                if(col == gamePanel.maxWorldColumn) {

                    col = 0;
                    row++;

                } // if

            } // while 1st
            bufferedReader.close();
        } catch (Exception e) {

        } // try/catch
    } // loadMap()

    public void display(final Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gamePanel.maxWorldColumn && worldRow < gamePanel.maxWorldRow) {

            int tileNum = this.mapTimeNum[worldCol][worldRow];

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if(worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
               worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
               worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
               worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY) {

                g2.drawImage(this.tile[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);

            } // if

            worldCol++;

            if(worldCol == gamePanel.maxWorldColumn) {
                worldCol = 0;
                worldRow++;
            } // if
        } // while
    } // display()

} // TileManager
