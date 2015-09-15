import javax.swing.*;
import java.awt.*;

/**
 * Created by Notebook on 27.04.2015.
 */
public class NumberInput extends JTextField {
    public NumberInput(int x, int y, int objWidth, int objHeight){
        setBounds(x, y, objWidth, objHeight);
        setBackground(new Color(41, 41, 41, 100));
        setOpaque(false);
        setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 50), 3));
        setHorizontalAlignment(CENTER);
        setFont(new Font("Tahoma Bold", Font.BOLD, 26));
        setForeground(new Color(254, 255, 1, 255));
        setCaretColor(new Color(200, 200, 255, 50));
        setHighlighter(null);
    }
}
