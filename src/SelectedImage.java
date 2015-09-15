import javax.swing.*;
import java.awt.*;

/**
 * Created by Notebook on 22.04.2015.
 */
public class SelectedImage extends JLabel {
    private String hint;

    public SelectedImage(int x, int y, int objWidth, int objHeight, Image inactive, String hint){
        setIcon(new ImageIcon(inactive));
        setBounds(x, y, objWidth, objHeight);
        this.hint=hint;
    }

    public String getHint(){
        return this.hint;
    }
}
