import javax.swing.*;
import javax.swing.text.StringContent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Notebook on 27.04.2015.
 */
public class NumberListener implements KeyListener {
    private Button enter;
    private BufferedImage enabled, disabled;

    public NumberListener(Button enter, BufferedImage enabled, BufferedImage disabled){
        this.enter=enter;
        this.enabled=enabled;
        this.disabled=disabled;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        JTextField temp=(NumberInput)e.getSource();
        boolean check=false;
        for (int i=0; i<temp.getText().length(); i++)
            if (e.getKeyChar()==temp.getText().charAt(i))
                check=true;

        switch (e.getKeyChar()) {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                if (temp.getText().length()==0 && e.getKeyChar()=='0') e.consume();
                if ((temp.getText()+e.getKeyChar()).length() > 4 || check) e.consume();
                else {
                    MouseListener ml = enter.getMouseListeners()[0];
                    DisabledEventReader der = (DisabledEventReader) ml;
                    if ((temp.getText()+e.getKeyChar()).length() == 4) {
                        der.setEnabled();
                        enter.setIcon(new ImageIcon(enabled));
                    }
                }
                break;

            case '\b':
                MouseListener ml = enter.getMouseListeners()[0];
                DisabledEventReader der = (DisabledEventReader) ml;
                der.setDisabled();
                enter.setIcon(new ImageIcon(disabled));
                break;

            default:
                e.consume();
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
