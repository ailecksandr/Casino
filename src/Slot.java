import javax.swing.*;
import java.awt.*;

/**
 * Created by Notebook on 25.04.2015.
 */
public class Slot extends JLabel {
    public Slot(int x, int y, int objWidth, int objHeight, Color color, Font font){
        setBounds(x, y, objWidth, objHeight);
        setFont(font);
        setHorizontalAlignment(CENTER);
        setForeground(color);
        setHorizontalAlignment(SwingConstants.CENTER);
        setOpaque(true);
        setBackground(new Color(41, 41, 41, 100));
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 50), 3));
    }
}
