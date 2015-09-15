import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * Created by Notebook on 14.04.2015.
 */
public class EventReader extends MouseAdapter {

    private ImageIcon active, inactive;
    private Button object;
    private SoundOptions sound;

    public EventReader(Button object, Image active, Image inactive, SoundOptions sound){
        this.object=object;
        this.active=new ImageIcon(active);
        this.inactive=new ImageIcon(inactive);
        this.sound=sound;
    }

    public void mouseEntered(MouseEvent e) {
        if (sound.isEnabled()) sound.buttonSound();
        object.setIcon(active);
    }

    public void mouseExited(MouseEvent e) {
        object.setIcon(inactive);
    }

}
