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
public class SoundEventReader extends MouseAdapter {

    private ImageIcon onActive, onInactive, onPressed, offActive, offInactive, offPressed;
    private Button object;
    private SoundOptions sound;

    public SoundEventReader(Button object, Image offActive, Image offInactive, Image offPressed, Image onActive, Image onInactive, Image onPressed, SoundOptions sound){
        this.object=object;
        this.offActive=new ImageIcon(offActive);
        this.offInactive=new ImageIcon(offInactive);
        this.offPressed=new ImageIcon(offPressed);
        this.onActive=new ImageIcon(onActive);
        this.onInactive=new ImageIcon(onInactive);
        this.onPressed=new ImageIcon(onPressed);
        this.sound=sound;
    }

    public void mouseEntered(MouseEvent e) {
        if (sound.isEnabled()){
            sound.buttonSound();
            object.setIcon(onActive);
        }
        else object.setIcon(offActive);
    }

    public void mouseExited(MouseEvent e) {
        if (sound.isEnabled()) object.setIcon(onInactive);
        else object.setIcon(offInactive);
    }

    public void mousePressed(MouseEvent e) {
        if (sound.isEnabled()) object.setIcon(offPressed);
        else object.setIcon(onPressed);
        sound.changeSound();
    }

    public void mouseReleased(MouseEvent e) {
        if (sound.isEnabled()) object.setIcon(onActive);
        else object.setIcon(offActive);
    }

}