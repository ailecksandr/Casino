import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by Notebook on 26.04.2015.
 */
public class Jackpot {
    private Casino window;
    private ContentPanel background;
    private SoundOptions sound;
    private Player player;
    private ArrayList<SerializableImage> collection;

    public Jackpot(Casino window){
        this.window=window;
        collection = new ArrayList<SerializableImage>();
        sound=window.getSoundOptions();
        sound.changeMusic("source/Sound/Jackpot.wav");
        background= window.getBckground();
        player=window.getPlayer();

        //writeCollection();
        readCollectionFromFile();
        formGame();
    }

    public void formGame(){
        System.gc();
        window.formPanel(collection.get(0).getImg());

        final Label lbl1 = new Label("Player: ", 85, 0, 100, 27, new Color(255, 255, 255, 255),new Font("Rockwell", Font.ITALIC , 18), SwingConstants.LEFT),
                currentPlayer = new Label(player.getNickname(), 145, 0, 200, 24, new Color(255, 251, 8, 255),new Font("Lucida Typewriter", Font.ITALIC+ Font.BOLD , 17), SwingConstants.LEFT),
                lbl2= new Label("Bank: ", 635-(String.valueOf(player.getBank()).length()*7+String.valueOf(player.getBank()).length()/3*5), 0, 75, 27, new Color(255, 255, 255, 255),new Font("Rockwell", Font.ITALIC , 18), SwingConstants.LEFT),
                currentBank = new Label("$ "+String.valueOf(player.getBank()), 590, 0, 120, 24, new Color(255, 251, 8, 255),new Font("Lucida Typewriter", Font.ITALIC+ Font.BOLD , 17), SwingConstants.RIGHT),
                error=new Label("", 275, 35, 250, 60, new Color(2, 120, 255, 255),new Font("Rockwell", Font.ITALIC+Font.BOLD, 36), SwingConstants.CENTER),
                lbl3= new Label("Your bet: ", 311, 370, 176, 50, new Color(255, 255, 255, 255),new Font("Rockwell", Font.ITALIC , 24), SwingConstants.CENTER),
                info=new Label("",665, 15, 200, 50, new Color(255, 251, 8, 255),new Font("Lucida Typewriter", Font.ITALIC+ Font.BOLD , 24), SwingConstants.CENTER);
        background.add(lbl1);
        background.add(lbl2);
        background.add(error);
        background.add(currentPlayer);
        background.add(currentBank);
        background.add(lbl3);
        background.add(info);

        final BetInput bet=new BetInput(310,420,150,50);
        bet.addKeyListener(new BetListener(player));
        background.add(bet);

        Button betsInfo=new Button(34, 536, 41, 39, collection.get(19).getImg());
        betsInfo.addMouseListener(new EventReader(betsInfo, collection.get(18).getImg(), collection.get(19).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                jackpotInfo();
            }
        });
        background.add(betsInfo);

        final Timer increase=new Timer(25,new ChangeEventListener(bet, player.getBank(), true)),
                decrease=new Timer(25, new ChangeEventListener(bet, player.getBank(), false));

        final Button first=new Button(89,160,169,146),
                second=new Button(315,160,169,146),
                third=new Button(540,160,169,146);
        background.add(first);
        background.add(second);
        background.add(third);


        Button upperBet=new Button(465,420,21,21,collection.get(4).getImg());
        upperBet.addMouseListener(new EventReader(upperBet, collection.get(3).getImg(), collection.get(4).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().equals("")) bet.setText("0");
                increase.start();
            }

            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                increase.stop();
            }

            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                increase.stop();
            }
        });
        background.add(upperBet);

        Button lowerBet=new Button(465,449,21,21,collection.get(6).getImg());
        lowerBet.addMouseListener(new EventReader(lowerBet, collection.get(5).getImg(), collection.get(6).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().equals("")) bet.setText("0");
                decrease.start();
            }

            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                decrease.stop();
            }

            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                decrease.stop();
            }
        });
        background.add(lowerBet);

        final Button start=new Button(298, 500, 196, 70, collection.get(8).getImg());
        start.addMouseListener(new EventReader(start, collection.get(7).getImg(), collection.get(8).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    start.setIcon(new ImageIcon(collection.get(8).getImg()));
                    letsRoll(first, second, third, currentBank, error, bet, info);
                }
            }
        });
        background.add(start);

        Button back=new Button(10, 2, 21, 21, collection.get(2).getImg());
        back.addMouseListener(new EventReader(back, collection.get(1).getImg(), collection.get(2).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                sound.changeMusic(sound.getMusicDestination());
                collection.clear();
                window.playMenu();
            }
        });
        background.add(back);
    }

    public void jackpotInfo(){
        System.gc();
        final JFrame exitFrame = new JFrame();
        int height=java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height,
                width=java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;

        window.disable();
        window.cursorOptions(exitFrame);
        exitFrame.setIconImage(collection.get(18).getImg());
        exitFrame.setTitle("FAQ");
        exitFrame.setResizable(false);
        exitFrame.setVisible(true);
        exitFrame.setLocation(width / 2 - 320, height / 2 - 280);
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                window.enable();
                exitFrame.dispose();
            }
        };
        exitFrame.addWindowListener(exitListener);
        exitFrame.setSize(640, 560);

        ContentPanel exitBackground=new ContentPanel();
        exitBackground.changeImage(collection.get(17).getImg());
        exitFrame.getContentPane().add(exitBackground);

        final Button ok=new Button(242, 465, 157, 68, collection.get(21).getImg());
        ok.addMouseListener(new EventReader(ok, collection.get(20).getImg(), collection.get(21).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                window.enable();
                exitFrame.dispose();
            }
        });
        exitBackground.add(ok);
    }

    private void letsRoll(JLabel first, JLabel second, JLabel third, Label bank, Label error, BetInput bet, Label info){
        window.setCursor(Cursor.getDefaultCursor());
        window.disable();
        error.setText("");
        bet.setFocusable(false);
        sound.changeMusic("source/Sound/Slot.wav");
        player.decreaseFromBank(Integer.valueOf(bet.getText()));
        player.getStats().decreaseFromProfit(Integer.valueOf(bet.getText()));
        bank.setText("$ " + String.valueOf(player.getBank()));
        info.setText("-$ " + bet.getText());
        info.setLocation(665 - info.getText().length() * 7, 15);
        info.setForeground(new Color(254, 0, 0, 255));
        Timer time=new Timer(350,null);
        time.addActionListener(new ProfitListener(time, info, 10));
        time.start();

        Timer timer = new Timer(10, null);
        timer.addActionListener(new SlotRolling(first, second, third, timer, sound, window, player, bank, bet, info, collection));
        timer.start();

    }

    // ************ для дозапису малюнків
    private void writeCollection(){
        formImage("source/Images/jackpotBackground.png"); //0
        formImage("source/Images/backIconActive.png"); //1
        formImage("source/Images/backIconInactive.png"); //2
        formImage("source/Images/upperBeta.png"); //3
        formImage("source/Images/upperBet.png"); //4
        formImage("source/Images/lowerBeta.png"); //5
        formImage("source/Images/lowerBet.png"); //6
        formImage("source/Images/slotStartActive.png"); //7
        formImage("source/Images/slotStartInactive.png"); //8
        formImage("source/Images/apple.png"); //9
        formImage("source/Images/grapes.png"); //10
        formImage("source/Images/lemon.png"); //11
        formImage("source/Images/cherry.png"); //12
        formImage("source/Images/banana.png"); //13
        formImage("source/Images/watermellon.png"); //14
        formImage("source/Images/orange.png"); //15
        formImage("source/Images/bar.png"); //16
        formImage("source/Images/jackpotBets.png"); //17
        formImage("source/Images/gameInfoActive.png"); //18
        formImage("source/Images/jackpotInfo.png"); //19
        formImage("source/Images/gameOkActive.png"); //20
        formImage("source/Images/gameOkInactive.png"); //21
        writeCollectionToFile();
    }

    private void writeCollectionToFile(){
        try{
            File fail = new File("source/Images/jackpot.res");
            if(!fail.exists())
                fail.createNewFile();
            FileOutputStream fos = new FileOutputStream(fail);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(collection);
            fos.flush();
            fos.close();
            oos.flush();
            oos.close();
        }
        catch(Exception e){  };
    }
// ************

    private void formImage(String destination){
        try{
            SerializableImage img = new SerializableImage(destination);
            collection.add(img);
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(new JFrame(),"There are not graphic files!", "Error!",3);
            System.exit(0);
        }
    }

    private void readCollectionFromFile(){
        try{
            File fail = new File("source/Images/jackpot.res");
            FileInputStream fis = new FileInputStream(fail);
            ObjectInputStream oin = new ObjectInputStream(fis);
            collection = (ArrayList<SerializableImage>) oin.readObject();
            oin.close();
            fis.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(new JFrame(),"There are not graphic files!", "Error",3);
            System.exit(0);
        };
    }
}
