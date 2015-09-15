import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Notebook on 16.04.2015.
 */
public class PlayerInput extends JTextField {
    public PlayerInput(int x, int y, int objWidth, int objHeight){
        setBounds(x, y, objWidth, objHeight);
        setBackground(new Color(94, 93, 169, 50));
        setOpaque(false);
        setBorder(BorderFactory.createLineBorder(new Color(182, 183, 223, 50), 4));
        addKeyListener(new KeyListener() {

                           public void keyPressed(KeyEvent e) {
                           }

                           public void keyReleased(KeyEvent e) {
                           }

                           public void keyTyped(KeyEvent e) {
                               JTextField temp = (PlayerInput) e.getSource();
                               if (temp.getText().length() >= 14) e.consume();
                               if (e.getKeyChar() == ' ') e.consume();
                           }
                       }
        );
        setHorizontalAlignment(CENTER);
        setFont(new Font("Monotype Corsiva", Font.BOLD, 28));
        setForeground(new Color(255, 251, 8, 255));
        setCaretColor(new Color(255, 251, 8, 50));
        setHighlighter(null);
    }
}
