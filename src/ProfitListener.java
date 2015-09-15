import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Notebook on 26.04.2015.
 */
public class ProfitListener implements ActionListener {
    Label object;
    Timer timer;
    int times;

    ProfitListener(Timer timer, Label object, int times){
        this.object=object;
        this.timer=timer;
        this.times=times;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (times!=0) times--;
        else {
            object.setText("");
            timer.stop();
        }
    }
}
