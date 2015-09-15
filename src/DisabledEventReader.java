import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Notebook on 14.04.2015.
 */
public class DisabledEventReader extends MouseAdapter {

    private BufferedImage active, inactive, disabled, disabledHover;
    private Button object;
    private SoundOptions sound;
    private Boolean enabled;

    public DisabledEventReader(Button object, Boolean enabled, BufferedImage active, BufferedImage inactive, BufferedImage disabled, BufferedImage disabledHover, SoundOptions sound){
        this.object=object;
        this.enabled=enabled;
        this.active=active;
        this.inactive=inactive;
        this.disabled=disabled;
        this.disabledHover=disabledHover;
        this.sound=sound;
    }

    public void enable(){
        setEnabled();
        object.setIcon(new ImageIcon(inactive));
    }

    public void disable(){
        setDisabled();
        object.setIcon(new ImageIcon(disabled));
    }

    public void setEnabled(){
        enabled=true;
    }

    public void setDisabled(){
        enabled=false;
    }

    public Boolean isEnabled(){
        return enabled;
    }

    public void mouseEntered(MouseEvent e) {
        if (sound.isEnabled()) sound.buttonSound();
        if (enabled) object.setIcon(new ImageIcon(active));
        else object.setIcon(new ImageIcon(disabledHover));
    }

    public void mouseExited(MouseEvent e) {
        if (enabled) object.setIcon(new ImageIcon(inactive));
        else object.setIcon(new ImageIcon(disabled));
    }

}
