package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class ObjKey extends SuperObject {

    public ObjKey() {

        this.name = "Key";

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        } // try/catch

        this.hitBox.x = 16;
        this.hitBox.y = 4;
        this.hitBox.width = 32;
        this.hitBox.height = 60;
        this.hitBoxDefaultX = 16;
        this.hitBoxDefaultY = 4;

        this.collision = true;

    } // ObjKey()

} // ObjKey
