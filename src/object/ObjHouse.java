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

    } // ObjHouse()
} // ObjHouse
