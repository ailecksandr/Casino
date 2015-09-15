import javax.swing.*;
import java.awt.*;

/**
 * Created by Notebook on 17.04.2015.
 */
public class Button extends JLabel {
    Button(int x, int y, int objWidth, int objHeight, Image inactive){
        setIcon(new ImageIcon(inactive));
        setBounds(x, y, objWidth, objHeight);
    }

    Button(int x, int y, int objWidth, int objHeight){
        setBounds(x, y, objWidth, objHeight);
    }
}
