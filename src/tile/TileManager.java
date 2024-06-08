package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    Tile[] tiles;
    int[][] mapTimeNum;

    public TileManager(final GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.tiles = new Tile[20];
        this.mapTimeNum = new int[gamePanel.maxScreenColumn][gamePanel.maxScreenRow];

        this.getTileImage();
        this.loadMap("/maps/map01.txt");
    } // TileManager(.)

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path.png"));

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

            while(col < gamePanel.maxScreenColumn && row < gamePanel.maxScreenRow) {

                String line = bufferedReader.readLine(); // read one line of the txt file

                while (col < gamePanel.maxScreenColumn) {

                    String[] numbers = line.split(" "); // split the line into numbers
                    int num = Integer.parseInt(numbers[col]); // convert "number" into number
                    this.mapTimeNum[col][row] = num;
                    col++;

                } // while 2nd

                if(col == gamePanel.maxScreenColumn) {

                    col = 0;
                    row++;

                } // if

            } // while 1st
            bufferedReader.close();
        } catch (Exception e) {

        } // try/catch
    } // loadMap()

    public void display(final Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gamePanel.maxScreenColumn && row < gamePanel.maxScreenRow) {

            int tileNum = this.mapTimeNum[col][row];

            g2.drawImage(this.tiles[tileNum].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            col++;
            x += gamePanel.tileSize;

            if(col == gamePanel.maxScreenColumn) {
                col = 0;
                x = 0;
                y += gamePanel.tileSize;
                row++;
            } // if
        } // while
    } // display()

} // TileManager
