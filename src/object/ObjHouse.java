package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjHouse extends SuperObject{

    public ObjHouse() {

        this.name = "House";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/house.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } // try/catch

        this.hitBox.x = 0;
        this.hitBox.y = 0;
        this.hitBox.width = 64;
        this.hitBox.height = 64;
        this.hitBoxDefaultX = 0;
        this.hitBoxDefaultY = 0;

        this.collision = true;

    } // ObjHouse()

} // ObjHouse
