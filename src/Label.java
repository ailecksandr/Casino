
import javax.swing.*;
import java.awt.*;

/**
 * Created by Notebook on 17.04.2015.
 */
public class Label extends JLabel {
    Label(String text, int x, int y, int objWidth, int objHeight, Color color, Font font, int alignment){
        setBounds(x, y, objWidth, objHeight);
        setOpaque(false);
        setFont(font);
        setHorizontalAlignment(CENTER);
        setForeground(color);
        setText(text);
        setHorizontalAlignment(alignment);
    }
}
