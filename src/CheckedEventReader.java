import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

/**
 * Created by Notebook on 21.04.2015.
 */
public class CheckedEventReader extends MouseAdapter {
    private BufferedImage inactive, active, activeEnter;
    private SelectedImage object, first, second;
    private Label choise;
    private Button enter;
    private Boolean checked;

    public CheckedEventReader(SelectedImage object, SelectedImage first, SelectedImage second, Label choise, Button enter, BufferedImage activeEnter, BufferedImage active, BufferedImage inactive){
        this.object=object;
        this.active=active;
        this.inactive=inactive;
        this.first=first;
        this.second=second;
        this.choise=choise;
        this.enter=enter;
        this.activeEnter=activeEnter;
        checked=false;
    }

    public void uncheck(){
        object.setIcon(new ImageIcon(inactive));
        checked=false;
    }

    public Boolean isChecked(){
        return checked;
    }

    public void mousePressed(MouseEvent e){
        object.setIcon(new ImageIcon(active));
        choise.setText(object.getHint());
        checked=true;

        MouseListener ml=enter.getMouseListeners()[0];
        DisabledEventReader der=(DisabledEventReader)ml;
        if (!der.isEnabled()) {
            der.enable();
            enter.setIcon(new ImageIcon(activeEnter));
        }

        ml=first.getMouseListeners()[0];
        CheckedEventReader cer=(CheckedEventReader)ml;
        cer.uncheck();

        ml=second.getMouseListeners()[0];
        cer=(CheckedEventReader)ml;
        cer.uncheck();
    }
}
