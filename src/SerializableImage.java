/**
 * Created by Notebook on 19.04.2015.
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by Notebook on 19.04.2015.
 */
public class SerializableImage implements Serializable {
    private static final long serialVersionUID = 1124;
    private BufferedImage bimage;

    SerializableImage(String destination) throws IOException{
        try {
            File imgSource = new File(destination);
            bimage = ImageIO.read(imgSource);
            bimage.flush();
        }
        catch(IOException e){
        }
    }

    public BufferedImage getImg(){
        return bimage;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.writeInt(bimage.getWidth());
        s.writeInt(bimage.getHeight());
        s.writeInt(bimage.getType());

        for(int i=0;i<bimage.getWidth();i++) {
            for(int j=0;j<bimage.getHeight();j++) {
                s.writeInt(bimage.getRGB(i,j));
            }
        }
        s.flush();
    }

    private void readObject(ObjectInputStream s) throws IOException,ClassNotFoundException  {
        bimage = new BufferedImage(s.readInt(),s.readInt(),s.readInt());
        for(int i=0;i<bimage.getWidth();i++) {
            for(int j=0;j<bimage.getHeight();j++) {
                bimage.setRGB(i,j,s.readInt());
            }
        }

    }

}

