import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

/**
 * Created by Notebook on 14.04.2015.
 */
class UI extends BasicScrollBarUI {
    Color one, two;
    public UI(){
        Color[] colors;
        one = new Color(94, 93, 169, 50);
        two = new Color(16, 14, 78, 150);
    }

    @Override
    protected JButton createDecreaseButton(int orientation) {
        JButton button = new JButton("zero button");
        Dimension zeroDim = new Dimension(0,0);
        button.setPreferredSize(zeroDim);
        button.setMinimumSize(zeroDim);
        button.setMaximumSize(zeroDim);
        return button;
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        JButton button = new JButton("zero button");
        Dimension zeroDim = new Dimension(0,0);
        button.setPreferredSize(zeroDim);
        button.setMinimumSize(zeroDim);
        button.setMaximumSize(zeroDim);
        return button;
    }

    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(one);
        g.translate(trackBounds.x, trackBounds.y);
        g.fillRect(0, 0, trackBounds.width, trackBounds.height);
        g.translate(-trackBounds.x, -trackBounds.y);
    }

    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        g.setColor(two);
        g.translate(thumbBounds.x, thumbBounds.y);
        g.fillRect(0, 0, thumbBounds.width, thumbBounds.height);
        g.translate(-thumbBounds.x, -thumbBounds.y);
    }
}
