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
        this.tile = new Tile[20];
        this.mapTimeNum = new int[gamePanel.maxWorldColumn][gamePanel.maxWorldRow];

        this.getTileImage();
        this.loadMap("/maps/bigMap");
    } // TileManager(.)

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_hor_up.png"));
            tile[0].collision = true;

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_hor_down.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_vert_right.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
            tile[5].collision = true;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[6].collision = true;

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall_vert_left.png"));
            tile[7].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        } // try/catch
    } // getTileImage()

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
