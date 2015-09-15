import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseListener;

/**
 * Created by Notebook on 14.04.2015.
 */
public class CellSelectedListener implements ListSelectionListener {
    private JLabel play, delete;
    private Boolean enabled=false;

    CellSelectedListener(JLabel play, JLabel delete){
        this.play=play;
        this.delete=delete;
    }

    public void valueChanged(ListSelectionEvent e) {
        if (!enabled) {

            MouseListener ml=play.getMouseListeners()[0];
            DisabledEventReader der=(DisabledEventReader)ml;
            der.enable();

            ml=delete.getMouseListeners()[0];
            der=(DisabledEventReader)ml;
            der.enable();

            enabled=true;
        }



    }
}
