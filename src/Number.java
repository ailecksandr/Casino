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
public class Number {
    private Casino window;
    private ContentPanel background;
    private SoundOptions sound;
    private Player player;
    private ArrayList<SerializableImage> collection;

    public Number(Casino window){
        this.window=window;
        collection = new ArrayList<SerializableImage>();
        sound=window.getSoundOptions();
        sound.changeMusic("source/Sound/Number.wav");
        background= window.getBckground();
        player=window.getPlayer();

        //writeCollection();
        readCollectionFromFile();
        betting();
    }

    public void betting() {

        if (player.getBank()==0) {
            window.bankrupt();
            return;
        }

        System.gc();
        window.formPanel(collection.get(0).getImg());

        final Label lbl1 = new Label("Player: ", 85, 0, 100, 27, new Color(255, 255, 255, 255),new Font("Rockwell", Font.ITALIC , 18), SwingConstants.LEFT),
                currentPlayer = new Label(player.getNickname(), 145, 0, 200, 24, new Color(255, 251, 8, 255),new Font("Lucida Typewriter", Font.ITALIC+ Font.BOLD , 17), SwingConstants.LEFT),
                lbl2= new Label("Bank: ", 635-(String.valueOf(player.getBank()).length()*7+String.valueOf(player.getBank()).length()/3*5), 0, 75, 27, new Color(255, 255, 255, 255),new Font("Rockwell", Font.ITALIC , 18), SwingConstants.LEFT),
                currentBank = new Label("$ "+String.valueOf(player.getBank()), 590, 0, 120, 24, new Color(255, 251, 8, 255),new Font("Lucida Typewriter", Font.ITALIC+ Font.BOLD , 17), SwingConstants.RIGHT),
                error=new Label("", 313, 300, 200, 50, new Color(255, 2, 44, 255),new Font("Rockwell", Font.ITALIC+Font.BOLD, 14), SwingConstants.CENTER),
                lbl3= new Label("Your bet: ", 313, 200, 200, 50, new Color(255, 255, 255, 255), new Font("Rockwell", Font.ITALIC , 24), SwingConstants.CENTER);
        background.add(lbl1);
        background.add(lbl2);
        background.add(error);
        background.add(currentPlayer);
        background.add(currentBank);
        background.add(lbl3);

        final BetInput bet=new BetInput(323,250,150,50);
        bet.addKeyListener(new BetListener(player));
        background.add(bet);

        final Timer increase=new Timer(25,new ChangeEventListener(bet, player.getBank(), true)),
                decrease=new Timer(25, new ChangeEventListener(bet, player.getBank(), false));

        Button upperBet=new Button(478,250,23,23,collection.get(4).getImg());
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

        Button lowerBet=new Button(478,279,23,23,collection.get(6).getImg());
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

        final Button start=new Button(312, 360, 196, 70, collection.get(8).getImg());
        start.addMouseListener(new EventReader(start, collection.get(7).getImg(), collection.get(8).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText()) == 0)
                    error.setText("Place your bet");
                else {
                    player.decreaseFromBank(Integer.valueOf(bet.getText()));
                    player.getStats().decreaseFromProfit(Integer.valueOf(bet.getText()));
                    player.getStats().incGames();
                    formGame(Integer.valueOf(bet.getText()));
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

    public void formGame(final int bet){
        System.gc();
        window.formPanel(collection.get(9).getImg());

        window.setGameStatus(1);

        final int secretNumber=formSecretNumber();
        final int[] tries = {9};

        final Label lbl1 = new Label("Player: ", 85, 0, 100, 27, new Color(255, 255, 255, 255),new Font("Rockwell", Font.ITALIC , 18), SwingConstants.LEFT),
                currentPlayer = new Label(player.getNickname(), 145, 0, 200, 24, new Color(255, 251, 8, 255),new Font("Lucida Typewriter", Font.ITALIC+ Font.BOLD , 17), SwingConstants.LEFT),
                lbl2= new Label("Bank: ", 635-(String.valueOf(player.getBank()).length()*7+String.valueOf(player.getBank()).length()/3*5), 0, 75, 27, new Color(255, 255, 255, 255),new Font("Rockwell", Font.ITALIC , 18), SwingConstants.LEFT),
                currentBank = new Label("$ "+String.valueOf(player.getBank()), 590, 0, 120, 24, new Color(255, 251, 8, 255),new Font("Lucida Typewriter", Font.ITALIC+ Font.BOLD , 17), SwingConstants.RIGHT),
                lbl3= new Label("Enter the number: ", 100, 100, 200, 50, new Color(221, 221, 221, 255), new Font("Rockwell", Font.ITALIC , 24), SwingConstants.CENTER),
                info=new Label("-$"+String.valueOf(bet), 665-("-$ "+String.valueOf(bet)).length()*7, 16, 200, 25, new Color(254, 0, 0, 255), new Font("Lucida Typewriter", Font.ITALIC+ Font.BOLD , 17), SwingConstants.CENTER),
                tableHeader1 = new Label("Numbers", 410, 25, 200, 50, new Color(221, 221, 221, 255), new Font("Rockwell", Font.ITALIC+Font.BOLD , 16), SwingConstants.CENTER),
                tableHeader2 = new Label("Guess", 610, 25, 75, 50, new Color(221, 221, 221, 255), new Font("Rockwell", Font.ITALIC+Font.BOLD , 16), SwingConstants.CENTER),
                tableHeader3 = new Label("Place", 685, 25, 75, 50, new Color(221, 221, 221, 255), new Font("Rockwell", Font.ITALIC+Font.BOLD , 16), SwingConstants.CENTER),
                currentTries = new Label("Remaining "+String.valueOf(tries[0])+" moves", 520, 515, 200, 50, new Color(6, 255, 2, 255), new Font("Rockwell", Font.ITALIC+Font.BOLD , 16), SwingConstants.LEFT),
                lbl4=new Label("", 100,450,200,50, new Color(221, 221, 221, 255), new Font("Rockwell", Font.ITALIC , 24), SwingConstants.CENTER),
                random=new Label("", 100,500,200,50, new Color(255, 251, 8, 255), new Font("Rockwell", Font.ITALIC , 32), SwingConstants.CENTER);
        background.add(lbl1);
        background.add(lbl2);
        background.add(currentPlayer);
        background.add(currentBank);
        background.add(lbl3);
        background.add(info);
        background.add(tableHeader1);
        background.add(tableHeader2);
        background.add(tableHeader3);
        background.add(currentTries);
        background.add(lbl4);
        background.add(random);

        Timer time=new Timer(350,null);
        time.addActionListener(new ProfitListener(time, info, 10));
        time.start();

        final NumberTableModel model=new NumberTableModel();
        ScrollBar scroll= new ScrollBar(410, 70, 350, 447, new Color(0, 0, 0, 50), 3);
        final NumberTable table=new NumberTable(model);

        scroll.setViewportView(table);
        background.add(scroll);

        final NumberInput numberInput=new NumberInput(100,150,200,50);
        final Button enter=new Button(98, 210, 196, 70, collection.get(10).getImg());

        Button betsInfo=new Button(744, 566, 41, 39, collection.get(15).getImg());
        betsInfo.addMouseListener(new EventReader(betsInfo, collection.get(14).getImg(), collection.get(15).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                numberInfo();
            }
        });
        background.add(betsInfo);

        numberInput.addKeyListener(new NumberListener(enter, collection.get(13).getImg(), collection.get(10).getImg()));
        background.add(numberInput);

        enter.addMouseListener(new DisabledEventReader(enter, false, collection.get(12).getImg(), collection.get(13).getImg(), collection.get(10).getImg(), collection.get(11).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (isEnabled()) {
                    int currentPlaced = placed(numberInput, secretNumber),
                            currentGuessed = guessed(numberInput, secretNumber);
                    model.addRow(new Object[]{numberInput.getText(), currentGuessed, currentPlaced});
                    numberInput.setText("");
                    if (currentPlaced == 4) {
                        window.setGameStatus(0);
                        this.disable();
                        numberInput.disable();
                        numberInput.setFocusable(false);
                        sound.createSound("source/Sound/NumberWin.wav");
                        player.getStats().incWins();
                        player.addToBank(bet * 2);
                        player.getStats().addToProfit(bet * 2);
                        player.getStats().formWinrate();
                        currentTries.setText("");
                        currentTries.setForeground(new Color(221, 221, 221, 255));
                        currentBank.setText("$ " + String.valueOf(player.getBank()));
                        lbl2.setLocation(635-(String.valueOf(player.getBank()).length()*7+String.valueOf(player.getBank()).length()/3*5), 0);

                        info.setText("+$ " + String.valueOf(bet * 2));
                        info.setForeground(new Color(135, 255, 135, 255));

                        Timer time=new Timer(350,null);
                        time.addActionListener(new ProfitListener(time, info, 10));
                        time.start();

                        table.setRowSelectionInterval(table.getRowCount()-1,table.getRowCount()-1);
                    }
                    else {
                        this.disable();
                        if (tries[0]!=0) {
                            tries[0]--;
                            if (tries[0]>5)
                                currentTries.setForeground(new Color(6, 255, 2, 255));
                            else
                                if (tries[0]>3)
                                    currentTries.setForeground(new Color(255, 251, 8, 255));
                                else
                                    if (tries[0]>1)
                                        currentTries.setForeground(new Color(255, 144, 0, 255));
                                    else
                                        currentTries.setForeground(new Color(254, 0, 0, 255));
                            currentTries.setText("Remaining "+String.valueOf(tries[0])+" moves");
                        }
                        if (tries[0]==0){
                            numberInput.disable();
                            lbl4.setText("Number:");
                            currentTries.setText("");
                            random.setText("\"" + secretNumber + "\"");
                            numberInput.setFocusable(false);
                        }
                    }
                }

            }

            public void disable(){
                enter.setIcon(new ImageIcon(collection.get(11).getImg()));
                setDisabled();
            }

            public void enable(){
                enter.setIcon(new ImageIcon(collection.get(12).getImg()));
                setEnabled();
            }
        });
        background.add(enter);

        Button back=new Button(10, 2, 21, 21, collection.get(2).getImg());
        back.addMouseListener(new EventReader(back, collection.get(1).getImg(), collection.get(2).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (window.getGameStatus()==1) {
                    window.setGameStatus(0);
                    player.getStats().incDefeats();
                    player.getStats().formWinrate();
                }
                betting();
            }
        });
        background.add(back);
    }

    private int formSecretNumber(){
        int i=1;
        String temp;
        do {
            temp = String.valueOf((int) (Math.random() * 10));
        } while (temp.equals("0"));

        while (i<4){
            int secretDigit=(int)(Math.random()*10);
            Boolean check=true;

            for (int j=0; j<i; j++) {
                int previous=Character.getNumericValue(temp.charAt(j));
                if (secretDigit==previous) check=false;
            }

            if (check){
                temp+=String.valueOf(secretDigit);
                i++;
            }
        }
        return Integer.valueOf(temp);
    }

    private int guessed(NumberInput input, int secret){
        int count=0;
        String numberInput=input.getText();
        ArrayList<Character> numberSecret=new ArrayList<Character>();

        for (int i=0; i<4; i++)
            numberSecret.add(String.valueOf(secret).charAt(i));

        for (int i=0; i<4; i++){
            for (int j=0; j<numberSecret.size(); j++)
                if (numberInput.charAt(i)==numberSecret.get(j)) {
                    count++;
                    numberSecret.remove(j);
                }
        }

        return count;
    }

    private int placed(NumberInput input, int secret){
        int count=0;
        String numberInput=input.getText(),
                numberSecret=String.valueOf(secret);

        for (int i=0; i<4; i++)
            if (numberInput.charAt(i)==numberSecret.charAt(i))
                count++;

        return count;
    }

    public void numberInfo(){
        System.gc();
        final JFrame exitFrame = new JFrame();
        int height=java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height,
                width=java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;

        window.disable();
        window.cursorOptions(exitFrame);
        exitFrame.setIconImage(collection.get(14).getImg());
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
        exitBackground.changeImage(collection.get(16).getImg());
        exitFrame.getContentPane().add(exitBackground);

        final Button ok=new Button(242, 425, 157, 68, collection.get(18).getImg());
        ok.addMouseListener(new EventReader(ok, collection.get(17).getImg(), collection.get(18).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                window.enable();
                exitFrame.dispose();
            }
        });
        exitBackground.add(ok);
}

    // ************ для дозапису малюнків
    private void writeCollection(){
        formImage("source/Images/numberBackground.png"); //0
        formImage("source/Images/backIconActive.png"); //1
        formImage("source/Images/backIconInactive.png"); //2
        formImage("source/Images/upperBeta.png"); //3
        formImage("source/Images/upperBetNumber.png"); //4
        formImage("source/Images/lowerBeta.png"); //5
        formImage("source/Images/lowerBetNumber.png"); //6
        formImage("source/Images/numberStartActive.png"); //7
        formImage("source/Images/numberStartInactive.png"); //8
        formImage("source/Images/numberBackground2.png"); //9
        formImage("source/Images/enterNumberDisabled.png"); //10
        formImage("source/Images/enterNumberDisabledHover.png"); //11
        formImage("source/Images/enterNumberActive.png"); //12
        formImage("source/Images/enterNumberInactive.png"); //13
        formImage("source/Images/gameInfoActive.png"); //14
        formImage("source/Images/numberInfo.png"); //15
        formImage("source/Images/numberFAQ.png"); //16
        formImage("source/Images/gameOkActive.png"); //17
        formImage("source/Images/gameOkInactive.png"); //18
        writeCollectionToFile();
    }

    private void writeCollectionToFile(){
        try{
            File fail = new File("source/Images/number.res");
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
            JOptionPane.showMessageDialog(new JFrame(), "There are not graphic files!", "Error!", 3);
            System.exit(0);
        }
    }

    private void readCollectionFromFile(){
        try{
            File fail = new File("source/Images/number.res");
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
