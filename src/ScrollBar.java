import javax.swing.*;
import java.awt.*;

/**
 * Created by Notebook on 16.04.2015.
 */
public class ScrollBar extends JScrollPane {
    public ScrollBar(int x, int y, int objWidth, int objHeight, Color colorBorder, int widthBorder){
        setBounds(x, y, objWidth, objHeight);
        setOpaque(false);
        getVerticalScrollBar().setUI(new UI());
        getVerticalScrollBar().setOpaque(false);
        setBorder(BorderFactory.createLineBorder(colorBorder, widthBorder));
        getViewport().setOpaque(false);
    }

    private class Border {
    }
}
