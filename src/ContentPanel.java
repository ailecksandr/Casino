import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Created by Notebook on 14.04.2015.
 */
public class ContentPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    private Image bimage;

    public ContentPanel(){
        setLayout(null);
    }

    public ContentPanel(Image img) {
            bimage = img;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bimage, 0, 0, null);
    }

    public void changeImage(Image img){
            bimage = img;
    }
}