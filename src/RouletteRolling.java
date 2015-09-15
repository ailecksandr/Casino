import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Notebook on 25.04.2015.
 */
public class RouletteRolling implements ActionListener {
    private JLabel object;
    private Timer timer;
    private SoundOptions sound;
    private Casino window;
    private int reason;
    private Player player;
    private Label bank, info;
    private BetInput bet;

    public RouletteRolling(JLabel object, Timer timer, SoundOptions sound, Casino window, int reason, Player player, Label bank, BetInput bet, Label info){
        this.object=object;
        this.timer=timer;
        this.window=window;
        this.sound=sound;
        this.reason=reason;
        this.player=player;
        this.bank=bank;
        this.bet=bet;
        this.info=info;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int delay=timer.getInitialDelay();
        if (delay<1000) {
            int num = (int) (Math.random() * 37);
            switch (num) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 9:
                case 12:
                case 14:
                case 16:
                case 18:
                case 19:
                case 21:
                case 23:
                case 25:
                case 27:
                case 30:
                case 32:
                case 34:
                case 36:
                    object.setBackground(new Color(254, 0, 0, 255));
                    break;
                case 2:
                case 4:
                case 6:
                case 8:
                case 10:
                case 11:
                case 13:
                case 15:
                case 17:
                case 20:
                case 22:
                case 24:
                case 26:
                case 28:
                case 29:
                case 31:
                case 33:
                case 35:
                    object.setBackground(new Color(0, 0, 0, 255));
                    break;
                case 0:
                    object.setBackground(new Color(6, 255, 2, 255));
                    break;
            }
            object.setText(String.valueOf(num));
        }

        timer.stop();
        if (delay>240) delay=delay+150;
        else delay += 5;
        timer.setInitialDelay(delay);
        timer.start();

        if (delay>1200) {
            timer.stop();
            sound.changeMusic("source/Sound/Roulette.wav");
            result();
            window.enable();
            window.cursorOptions(window);
            bet.setFocusable(true);
            bet.setText("");
            object.setBorder(BorderFactory.createLineBorder(new Color(254, 255, 1, 150), 3));
        }
    }

    private void result(){
        Boolean checked=false;
        int profit=Integer.valueOf(bet.getText());
        if (Integer.valueOf(object.getText())==reason) {
            profit *= 35;
            checked=true;
        }
        switch (reason){
            case 37:    //1-12
                if (Integer.valueOf(object.getText())>0 && Integer.valueOf(object.getText())<=12) {
                    profit *= 2;
                    checked=true;
                }
                break;
            case 38:    //12-24
                if (Integer.valueOf(object.getText())>12 && Integer.valueOf(object.getText())<=24){
                    checked=true;
                    profit *= 2;
                    checked=true;
                }
                break;
            case 39:    //24-36
                if (Integer.valueOf(object.getText())>24 && Integer.valueOf(object.getText())<=36) {
                    checked=true;
                    profit *= 2;
                    checked=true;
                }
                break;
            case 40:    //1-18
                if (Integer.valueOf(object.getText())>0 && Integer.valueOf(object.getText())<=18) {
                    checked=true;
                }
                break;
            case 41:    //парне
                if (Integer.valueOf(object.getText())%2==0 && Integer.valueOf(object.getText())!=0){
                    checked=true;
                }
                break;
            case 42:    //чорне
                switch(Integer.valueOf(object.getText())){
                    case 2:
                    case 4:
                    case 6:
                    case 8:
                    case 10:
                    case 11:
                    case 13:
                    case 15:
                    case 17:
                    case 20:
                    case 22:
                    case 24:
                    case 26:
                    case 28:
                    case 29:
                    case 31:
                    case 33:
                    case 35:
                        checked=true;
                        break;
                }
                break;
            case 43:    //червоне
                switch (Integer.valueOf(object.getText())){
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 9:
                    case 12:
                    case 14:
                    case 16:
                    case 18:
                    case 19:
                    case 21:
                    case 23:
                    case 25:
                    case 27:
                    case 30:
                    case 32:
                    case 34:
                    case 36:
                        checked=true;
                        break;
                }
                break;
            case 44:    //непарне
                if (Integer.valueOf(object.getText())%2!=0 && Integer.valueOf(object.getText())!=0){
                    checked=true;
                }
                break;
            case 45:    //19-36
                if (Integer.valueOf(object.getText())>18 && Integer.valueOf(object.getText())<=36) {
                    checked=true;
                }
                break;
            case 46:     //3 ряд
                switch(Integer.valueOf(object.getText())){
                    case 3:
                    case 6:
                    case 9:
                    case 12:
                    case 15:
                    case 18:
                    case 21:
                    case 24:
                    case 27:
                    case 30:
                    case 33:
                    case 36:
                        profit *= 2;
                        checked=true;
                        break;
                }
                break;
            case 47:    //2 ряд
                switch(Integer.valueOf(object.getText())){
                    case 2:
                    case 5:
                    case 8:
                    case 11:
                    case 14:
                    case 17:
                    case 20:
                    case 23:
                    case 26:
                    case 29:
                    case 32:
                    case 35:
                        profit *= 2;
                        checked=true;
                        break;
                }
                break;
            case 48:    //1 ряд
                switch(Integer.valueOf(object.getText())){
                    case 1:
                    case 4:
                    case 7:
                    case 10:
                    case 13:
                    case 16:
                    case 19:
                    case 22:
                    case 25:
                    case 28:
                    case 31:
                    case 34:
                        profit *= 2;
                        checked=true;
                        break;
                }
                break;
        }

            if (checked) {
                player.addToBank(profit);
                player.getStats().addToProfit(profit);
                player.getStats().incWins();
                info.setText("+$ " + String.valueOf(profit));
                info.setForeground(new Color(135, 255, 135, 255));
            }
            else {
                player.decreaseFromBank(profit);
                player.getStats().decreaseFromProfit(profit);
                player.getStats().incDefeats();
                info.setText("-$ " + String.valueOf(profit));
                info.setForeground(new Color(254, 0, 0, 255));
            }
            info.setLocation(665 - info.getText().length() * 7, 15);
            player.getStats().incGames();
            player.getStats().formWinrate();

            if (player.getBank()==0) {
                window.bankrupt();
                return;
            }

            bank.setText("$ " + String.valueOf(player.getBank()));
            Timer time=new Timer(350,null);
            time.addActionListener(new ProfitListener(time,info,10));
            time.start();
        }

}
