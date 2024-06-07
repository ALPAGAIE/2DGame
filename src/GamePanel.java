import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel
{
    //setting the screen settings
    final int ogTileSize = 16;
    final int scale = 4;

    final int tileSize = ogTileSize * scale;
    final int maxScreenColumn = 18;
    final int maxScreenRow = 10;

    final int screenWidth = maxScreenColumn * tileSize;
    final int screenHeight = maxScreenRow * tileSize;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);  // improve game render design
    } // GamePanel()

} // GamePanel
