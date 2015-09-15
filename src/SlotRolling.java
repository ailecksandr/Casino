import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Notebook on 26.04.2015.
 */
public class SlotRolling implements ActionListener{
        private JLabel first, second, third;
        private Timer timer;
        private SoundOptions sound;
        private Casino window;
        private Player player;
        private Label bank, info;
        private ArrayList<SerializableImage> collection;
        private BetInput bet;
        private int num1,num2,num3;

        public SlotRolling(JLabel first, JLabel second, JLabel third, Timer timer, SoundOptions sound, Casino window, Player player, Label bank, BetInput bet, Label info, ArrayList<SerializableImage> collection){
            this.first=first;
            this.second=second;
            this.third=third;
            this.timer=timer;
            this.window=window;
            this.sound=sound;
            this.player=player;
            this.bank=bank;
            this.bet=bet;
            this.info=info;
            this.collection=collection;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int delay = timer.getInitialDelay();
            if (delay < 1000) {
                num1 = (int) (Math.random() * 8);
                num2 = (int) (Math.random() * 8);
                num3 = (int) (Math.random() * 8);

                setImage(first, num1);
                setImage(second, num2);
                setImage(third, num3);
            }

            timer.stop();
            if (delay > 240) delay = delay + 150;
            else delay+=5;
            timer.setInitialDelay(delay);
            timer.start();

            if (delay > 1200) {
                timer.stop();
                sound.changeMusic("source/Sound/Jackpot.wav");
                result(num1, num2, num3);
                window.enable();
                window.cursorOptions(window);
                bet.setText("");
                bet.setFocusable(true);
            }
        }


        private void result(int a, int b, int c){
            int profit=Integer.valueOf(bet.getText()), value=-2;
            if (a==b || a==c ) value=a;
            else if (b==c) value=b;
            if (a==b && b==c) value=-1;

            switch (value) {
                case -1:
                    switch (a) {
                        case 0:
                        case 3:
                        case 6:
                            profit *= 5;
                            break;
                        case 1:
                        case 2:
                            profit *= 10;
                            break;
                        case 4:
                        case 5:
                            profit *= 15;
                            break;
                        case 7:
                            profit = 100000;
                            break;
                    }
                    break;

                case 0:
                    profit /= 3;
                    break;
                case 3:
                case 6:
                    profit /= 2;
                    break;
                case 4:
                case 5:
                    profit *= 2;
                    break;
                case 7:
                    profit *= 3;
                    break;
            }

                if (value!=-2) {
                    player.getStats().addToProfit(profit);
                    player.addToBank(profit);
                    player.getStats().incWins();
                    info.setText("+$ " + String.valueOf(profit));
                    info.setLocation(665 - info.getText().length() * 7, 15);
                    info.setForeground(new Color(135, 255, 135, 255));
                    sound.createSound("source/Sound/SlotWin.wav");
                }
                else player.getStats().incDefeats();

                player.getStats().incGames();
                player.getStats().formWinrate();

                if (player.getBank()==0) {
                    window.bankrupt();
                    return;
                }

            bank.setText("$ " + String.valueOf(player.getBank()));

                Timer time=new Timer(350,null);
                time.addActionListener(new ProfitListener(time, info, 10));
                time.start();
        }

    private void setImage(JLabel object, int id){
        switch (id){
            case 0:
                object.setIcon(new ImageIcon(collection.get(9).getImg()));
                break;
            case 1:
                object.setIcon(new ImageIcon(collection.get(10).getImg()));
                break;
            case 2:
                object.setIcon(new ImageIcon(collection.get(11).getImg()));
                break;
            case 3:
                object.setIcon(new ImageIcon(collection.get(12).getImg()));
                break;
            case 4:
                object.setIcon(new ImageIcon(collection.get(13).getImg()));
                break;
            case 5:
                object.setIcon(new ImageIcon(collection.get(14).getImg()));
                break;
            case 6:
                object.setIcon(new ImageIcon(collection.get(15).getImg()));
                break;
            case 7:
                object.setIcon(new ImageIcon(collection.get(16).getImg()));
                break;
        }
    }

}
