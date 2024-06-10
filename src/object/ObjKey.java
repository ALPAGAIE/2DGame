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
    } // ObjKey()
} // ObjKey
