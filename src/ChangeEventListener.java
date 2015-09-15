import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Notebook on 26.04.2015.
 */
public class ChangeEventListener implements ActionListener {
    private BetInput bet;
    private Boolean increase;
    private int bank;

    public ChangeEventListener(BetInput bet, int bank, Boolean increase){
        this.bet=bet;
        this.increase=increase;
        this.bank=bank;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int value=Integer.valueOf(bet.getText());
        if (increase) {
            if (value + 1 > bank) value = bank;
            else value++;
        }
        else
            if (value-1<0) value=0;
            else value--;
        bet.setText(String.valueOf(value));
    }
}
