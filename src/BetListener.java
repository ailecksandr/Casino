import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Notebook on 25.04.2015.
 */
public class BetListener implements KeyListener {
    private Player player;

    BetListener(Player player){
        this.player=player;
    }

    @Override
    public void keyTyped(KeyEvent e) {
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
                JTextField temp=(BetInput)e.getSource();
                if (player.getBank()!=0) {
                    if (!temp.getText().equals("")) {
                        if (Integer.valueOf(temp.getText() + e.getKeyChar()) > player.getBank()) {
                            temp.setText(String.valueOf(player.getBank()));
                            e.consume();
                        }
                        else
                            if (temp.getText().equals("0"))
                                temp.setText("");
                            else
                                if (Integer.valueOf(temp.getText()) > 999999) {
                                    temp.setText(String.valueOf(1000000));
                                    e.consume();
                                }
                    }
                }
                else e.consume();
                break;

            default:
                e.consume();
                break;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}
