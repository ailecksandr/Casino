import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Notebook on 14.04.2015.
 */
public class Casino extends JFrame {

    private static final long serialVersionUID = 1L;
    private ContentPanel background;
    private int gameStatus;
    private SoundOptions sound;
    private Player player;
    private Integer id;
    private ArrayList<PlayerList> list;
    private ArrayList<SerializableImage> collection;

    public Casino(){

        collection = new ArrayList<SerializableImage>();
        //writeCollection();
        readCollectionFromFile();

        background = new ContentPanel();
        gameStatus=0;
        getContentPane().add(background);

        windowOptions();
        cursorOptions(this);
        sound=new SoundOptions("source/Sound/Background.wav");

        startMenu();
    }

    private void windowOptions(){
        setSize(800, 640);
        setTitle("Casino 1.3b");
        setIconImage(collection.get(0).getImg());
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        WindowListener exitListener = new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e){
                exit();
            }
        };
        addWindowListener(exitListener);
        setLocationRelativeTo(null);
    }

    public void cursorOptions(JFrame source){
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(collection.get(28).getImg(), new Point(0,0), "cursor");
        source.setCursor(cursor);
    }

    private void readCollectionFromFile(){
        try{
            File fail = new File("source/Images/img.res");
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

// ************ для дозапису малюнків
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

    private void writeCollection(){
        formImage("source/Images/icon.png"); //0
        formImage("source/Images/background.png"); //1
        formImage("source/Images/background1.png"); //2
        formImage("source/Images/loginActive.png"); //3
        formImage("source/Images/loginInactive.png"); //4
        formImage("source/Images/exitButtonActive.png"); //5
        formImage("source/Images/exitButtonInactive.png"); //6
        formImage("source/Images/infoActive.png"); //7
        formImage("source/Images/infoInactive.png"); //8
        formImage("source/Images/soundOffActive.png"); //9
        formImage("source/Images/soundOffInactive.png"); //10
        formImage("source/Images/soundOnActive.png"); //11
        formImage("source/Images/soundOnInactive.png"); //12
        formImage("source/Images/ChoosePlayerDisabled.png"); //13
        formImage("source/Images/ChoosePlayerDisabledHover.png"); //14
        formImage("source/Images/ChoosePlayerActive.png"); //15
        formImage("source/Images/ChoosePlayerInactive.png"); //16
        formImage("source/Images/CreatePlayerActive.png"); //17
        formImage("source/Images/CreatePlayerInactive.png"); //18
        formImage("source/Images/DeletePlayerDisabled.png"); //19
        formImage("source/Images/DeletePlayerDisabledHover.png"); //20
        formImage("source/Images/DeletePlayerActive.png"); //21
        formImage("source/Images/DeletePlayerInactive.png"); //22
        formImage("source/Images/backActive.png"); //23
        formImage("source/Images/backInactive.png"); //24
        formImage("source/Images/soundOffPressed.png"); //25
        formImage("source/Images/soundOnPressed.png"); //26
        formImage("source/Images/background2.png"); //27
        formImage("source/Images/cursor.png"); //28
        formImage("source/Images/okActive.png"); //29
        formImage("source/Images/okInactive.png"); //30
        formImage("source/Images/exitActive.png"); //31
        formImage("source/Images/exitInactive.png"); //32
        formImage("source/Images/background3.png"); //33
        formImage("source/Images/cancelActive.png"); //34
        formImage("source/Images/cancelInactive.png"); //35
        formImage("source/Images/playActive.png"); //36
        formImage("source/Images/playInactive.png"); //37
        formImage("source/Images/optionsActive.png"); //38
        formImage("source/Images/optionsInactive.png"); //39
        formImage("source/Images/background4.png"); //40
        formImage("source/Images/logOutActive.png"); //41
        formImage("source/Images/logOutInactive.png"); //42
        formImage("source/Images/playMenu.png"); //43
        formImage("source/Images/roulette.png"); //44
        formImage("source/Images/rouletteChecked.png"); //45
        formImage("source/Images/jackpot.png"); //46
        formImage("source/Images/jackpotChecked.png"); //47
        formImage("source/Images/number.png"); //48
        formImage("source/Images/numberChecked.png"); //49
        formImage("source/Images/enterActive.png"); //50
        formImage("source/Images/enterInactive.png"); //51
        formImage("source/Images/enterDisabled.png"); //52
        formImage("source/Images/enterDisabledHover.png"); //53
        formImage("source/Images/backIconActive.png"); //54
        formImage("source/Images/backIconInactive.png"); //55
        formImage("source/Images/statsBackground.png"); //56
        formImage("source/Images/statisticsActive.png"); //57
        formImage("source/Images/statisticsInactive.png"); //58
        formImage("source/Images/optionsBackground.png"); //59
        formImage("source/Images/changeMusicActive.png"); //60
        formImage("source/Images/changeMusicInactive.png"); //61
        formImage("source/Images/resetStatsActive.png"); //62
        formImage("source/Images/resetStatsInactive.png"); //63
        formImage("source/Images/leaderboardActive.png"); //64
        formImage("source/Images/leaderboardInactive.png"); //65
        writeCollectionToFile();
    }

    private void writeCollectionToFile(){
        try{
            File fail = new File("source/Images/img.res");
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

    public SoundOptions getSoundOptions(){
        return sound;
    }

    public ContentPanel getBckground(){
        return background;
    }

    public Player getPlayer(){
        return player;
    }

    public Casino getWindow() {
        return this;
    }

    public int getGameStatus() { return gameStatus; }

    public void setGameStatus(int id){
        gameStatus=id;
    }

    public void exit(){
        System.gc();
        final JFrame exitFrame = new JFrame();
        int height=java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height,
                width=java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;

        disable();
        cursorOptions(exitFrame);
        exitFrame.setIconImage(collection.get(0).getImg());
        exitFrame.setResizable(false);
        exitFrame.setVisible(true);
        exitFrame.setLocation(width / 2 - 200, height / 2 - 160);
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                enable();
                exitFrame.dispose();
            }
        };
        exitFrame.addWindowListener(exitListener);
        exitFrame.setSize(400, 220);

        ContentPanel exitBackground=new ContentPanel();
        exitBackground.changeImage(collection.get(33).getImg());
        exitFrame.getContentPane().add(exitBackground);

        final Label lbl = new Label("Are you sure?", 50, 40, 300, 50,new Color(221, 221, 221, 255),new Font("Rockwell", Font.ITALIC + Font.BOLD, 32), SwingConstants.CENTER);
        exitBackground.add(lbl);

        Button ok = new Button(64, 95, 136, 77, collection.get(30).getImg());
        ok.addMouseListener(new EventReader(ok, collection.get(29).getImg(), collection.get(30).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (gameStatus==1) {
                    player.getStats().incDefeats();
                    player.getStats().formWinrate();
                }
                if (gameStatus==-1) player.addToBank(50);
                if (player!=null) {
                    savePlayer(player);
                    readFromFile();
                    list.get(id).setProfit(player.getStats().getProfit());
                }
                if (list!=null) writeToFile();
                System.exit(0);
            }
        });
        exitBackground.add(ok);

        Button cancel = new Button(195, 95, 136, 77, collection.get(35).getImg());
        cancel.addMouseListener(new EventReader(cancel, collection.get(34).getImg(), collection.get(35).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                enable();
                exitFrame.dispose();
            }
        });
        exitBackground.add(cancel);
    }

    public void formPanel(Image bGround){
        background.removeAll();
        background.changeImage(bGround);
        background.repaint();
    }

    public void startMenu(){
        System.gc();
        formPanel(collection.get(1).getImg());

        Button enter=new Button(282, 175, 236, 84, collection.get(4).getImg()),
                leaderboard=new Button(282, 250, 236, 84, collection.get(65).getImg()),
                exit=new Button(282, 325, 236, 84, collection.get(6).getImg()),
                info=new Button(735, 553, 41, 39, collection.get(8).getImg()),
                mute;
        if (sound.isEnabled())
            mute=new Button(685, 553, 41, 39, collection.get(10).getImg());
        else
            mute=new Button(685, 553, 41, 39, collection.get(12).getImg());


        enter.addMouseListener(new EventReader(enter, collection.get(3).getImg(), collection.get(4).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                readFromFile();
                authorization();
            }
        });
        background.add(enter);

        leaderboard.addMouseListener(new EventReader(leaderboard, collection.get(64).getImg(), collection.get(65).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
               showLeaderboard();
            }
        });
        background.add(leaderboard);


        exit.addMouseListener(new EventReader(exit, collection.get(5).getImg(), collection.get(6).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (list != null) writeToFile();
                System.exit(0);
            }
        });
        background.add(exit);

        info.addMouseListener(new EventReader(info, collection.get(7).getImg(), collection.get(8).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                about(0);
            }
        });
        background.add(info);


        mute.addMouseListener(new SoundEventReader(mute, collection.get(11).getImg(), collection.get(12).getImg(), collection.get(26).getImg(), collection.get(9).getImg(), collection.get(10).getImg(), collection.get(25).getImg(), sound));
        background.add(mute);

    }

    public void showLeaderboard(){
        System.gc();
        formPanel(collection.get(2).getImg());

        readFromFile();

        for (int i=0; i<list.size(); i++)
            for (int j=0; j<list.size()-1-i; j++)
                    if (list.get(j).getProfit()<list.get(j+1).getProfit())
                        Collections.swap(list,j, j+1);

        int pos=400;
        if (list.size()>9) pos=pos-10;
        Label lbl= new Label("Leaderboard", 200, 10, 400, 25, new Color(135, 255, 135, 255),new Font("Rockwell", Font.ITALIC , 26), SwingConstants.CENTER),
                lbl1= new Label("Player", 200, 55, 200, 25, new Color(221, 221, 221, 255),new Font("Rockwell", Font.BOLD + Font.ITALIC , 20), SwingConstants.CENTER),
                lbl2= new Label("Profit", pos, 55, 200, 25, new Color(221, 221, 221, 255),new Font("Rockwell", Font.BOLD + Font.ITALIC , 20), SwingConstants.CENTER);
        background.add(lbl);
        background.add(lbl1);
        background.add(lbl2);

        final LeaderboardTableModel model=new LeaderboardTableModel();
        ScrollBar scroll= new ScrollBar(200, 84, 400, 447, new Color(0, 0, 0, 100), 3);
        final LeaderboardTable table=new LeaderboardTable(model);
        scroll.setViewportView(table);
        background.add(scroll);

        for (int i=0; i<list.size(); i++) {
            String temp="$ "+list.get(i).getProfit();
            if (list.get(i).getProfit()<0)
                temp="-$ "+(-list.get(i).getProfit());
            else
                if (list.get(i).getProfit()>0)
                    temp="+$ "+list.get(i).getProfit();
                else
                    temp="$ "+list.get(i).getProfit();
            model.addRow(new Object[]{list.get(i).getNickname(), temp});
            if (i==14) break;
        }

        if (list.size()!=0) table.setRowSelectionInterval(0,0);
        list=null;

        Button back=new Button(282, 530, 236, 84, collection.get(24).getImg());
        back.addMouseListener(new EventReader(back, collection.get(23).getImg(), collection.get(24).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                startMenu();
            }
        });
        background.add(back);

    }

    public void about(final int backPoint){
        System.gc();
        formPanel(collection.get(27).getImg());

        final Label lbl = new Label("Intelligent Wizards", 200, 210, 400, 50,new Color(255, 255, 255, 255),new Font("Rockwell", Font.ITALIC + Font.BOLD, 32), SwingConstants.CENTER),
                lbl2 = new Label("2015", 200, 300, 400, 50,new Color(255, 255, 255, 255),new Font("Rockwell", Font.ITALIC + Font.BOLD, 32), SwingConstants.CENTER);
        background.add(lbl);
        background.add(lbl2);

        Button add = new Button(336, 360, 136, 77, collection.get(30).getImg());
        add.addMouseListener(new EventReader(add, collection.get(29).getImg(), collection.get(30).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                switch (backPoint) {
                    case 0:
                        startMenu();
                        break;
                    case 1:
                        mainMenu();
                        break;
                }
            }
        });
        background.add(add);
    }

    public void authorization() {
        System.gc();
        formPanel(collection.get(2).getImg());

        Button play=new Button(552, 61, 236, 84, collection.get(13).getImg()),
                create=new Button(552, 300, 236, 84, collection.get(18).getImg()),
                back=new Button(552, 488, 236, 84, collection.get(24).getImg()),
                delete=new Button(552, 354, 236, 84, collection.get(19).getImg());

        final PlayerTable table=new PlayerTable(play, delete, new PlayerTableModel(list));
        ScrollBar scroll = new ScrollBar(25, 25, 500, 550, new Color(94, 93, 169, 50), 4);

        scroll.setViewportView(table);
        background.add(scroll);

        play.addMouseListener(new DisabledEventReader(play, false, collection.get(15).getImg(), collection.get(16).getImg(), collection.get(13).getImg(), collection.get(14).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (isEnabled())
                    try {
                        readPlayer(list.get(table.getSelectedRow()).getNickname());
                        list.get(table.getSelectedRow()).setProfit(player.getStats().getProfit());
                        id=table.getSelectedRow();
                        writeToFile();
                        mainMenu();
                    } catch (Exception ex) {
                        File fail = new File("players/" + list.get(table.getSelectedRow()).getNickname() + ".gj");
                        fail.delete();
                        list.remove(table.getSelectedRow());
                        authorization();
                    }
            }
        });
        background.add(play);

        create.addMouseListener(new EventReader(create, collection.get(17).getImg(), collection.get(18).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                createPlayer();
            }
        });
        background.add(create);

        delete.addMouseListener(new DisabledEventReader(delete, false, collection.get(21).getImg(), collection.get(22).getImg(), collection.get(19).getImg(), collection.get(20).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (isEnabled())
                    deletePlayer(table.getSelectedRow());
            }
        });
        background.add(delete);

        back.addMouseListener(new EventReader(back, collection.get(23).getImg(), collection.get(24).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                writeToFile();
                startMenu();
            }
        });
        background.add(back);

    }

    // ***********Створити гравця
    public void createPlayer() {
        System.gc();
        formPanel(collection.get(27).getImg());

        Button close = new Button(587, 141, 21, 21, collection.get(32).getImg());
        close.addMouseListener(new EventReader(close, collection.get(31).getImg(), collection.get(32).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                authorization();
            }
        });
        background.add(close);

        final Label lbl = new Label("Enter player name", 250, 200, 300, 50, new Color(221, 221, 221, 255),new Font("Rockwell", Font.ITALIC + Font.BOLD, 32), SwingConstants.CENTER),
                error=new Label("This name already exists.", 250, 300, 300, 50, new Color(255, 2, 44, 255),new Font("Rockwell", Font.ITALIC + Font.BOLD, 14), SwingConstants.CENTER);
        background.add(lbl);
        background.add(error);
        error.setVisible(false);

        final PlayerInput newPlayer = new PlayerInput(250, 260, 300, 50);
        background.add(newPlayer);

        Button add = new Button(336, 360, 136, 77, collection.get(30).getImg());
        add.addMouseListener(new EventReader(add, collection.get(29).getImg(), collection.get(30).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                Boolean access = true;
                for (PlayerList temp : list)
                    if (newPlayer.getText().equals(temp.getNickname())) {
                        access = false;
                        error.setVisible(true);
                    }
                if (newPlayer.getText().length()==0) access=false;
                if (access) {
                    Player newbee = new Player(newPlayer.getText());
                    list.add(0, new PlayerList(newbee.getNickname(), newbee.getStats().getProfit()));
                    savePlayer(newbee);
                    authorization();
                }
            }
        });
        background.add(add);
    }
// ***********

    // ***********Видалити гравця
    public void deletePlayer(final int row){
        System.gc();
        formPanel(collection.get(27).getImg());

        final Label lbl = new Label("Delete", 200, 210, 400, 50,new Color(221, 221, 221, 255),new Font("Rockwell", Font.ITALIC + Font.BOLD, 32), SwingConstants.CENTER),
                name=new Label("\""+list.get(row).getNickname()+"\"", 200, 260, 400, 50, new Color(255, 251, 8, 255),new Font("Tahoma Bold", Font.PLAIN, 26), SwingConstants.CENTER);
        background.add(lbl);
        background.add(name);

        Button add = new Button(264, 360, 136, 77, collection.get(30).getImg());
        add.addMouseListener(new EventReader(add, collection.get(29).getImg(), collection.get(30).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                File fail = new File("players/"+list.get(row).getNickname() + ".gj");
                fail.delete();
                list.remove(row);
                authorization();
            }
        });
        background.add(add);

        Button cancel = new Button(400, 360, 136, 77, collection.get(35).getImg());
        cancel.addMouseListener(new EventReader(cancel, collection.get(34).getImg(), collection.get(35).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                authorization();
            }
        });
        background.add(cancel);
    }
// ***********

    private void savePlayer(Player pl){
        try{
            File fail = new File("players/"+pl.getNickname()+".gj");
            if (!fail.exists())
                fail.createNewFile();
            FileOutputStream fos = new FileOutputStream(fail);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(pl);
            fos.flush();
            fos.close();
            oos.flush();
            oos.close();
        }
        catch(Exception e){  };
    }

    private void readPlayer(String nickname) throws Exception{
        File fail = new File("players/"+nickname+".gj");
        FileInputStream fis = new FileInputStream(fail);
        ObjectInputStream oin = new ObjectInputStream(fis);
        player = (Player) oin.readObject();
        oin.close();
        fis.close();
    }

    private void writeToFile(){
        try{
            File fail = new File("player.tsm");
            if(!fail.exists())
                fail.createNewFile();
            FileOutputStream fos = new FileOutputStream(fail);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(list);
            fos.flush();
            fos.close();
            oos.flush();
            oos.close();
        }
        catch(Exception e){  };
    }

    private void readFromFile(){
        try{
            list=new ArrayList<PlayerList>();
            File fail = new File("player.tsm");
            if(!fail.exists())
                fail.createNewFile();
            FileInputStream fis = new FileInputStream(fail);
            ObjectInputStream oin = new ObjectInputStream(fis);
            list = (ArrayList<PlayerList>) oin.readObject();
            oin.close();
            fis.close();
        }
        catch(Exception e){ };
    }

    public void bankrupt(){
        System.gc();
        formPanel(collection.get(27).getImg());
        sound.changeMusic(sound.getMusicDestination());

        setGameStatus(-1);

        final Label lbl = new Label("Extra money earned", 200, 210, 400, 50,new Color(221, 221, 221, 255),new Font("Rockwell", Font.ITALIC + Font.BOLD, 32), SwingConstants.CENTER);
        background.add(lbl);

        Button add = new Button(336, 360, 136, 77, collection.get(30).getImg());
        add.addMouseListener(new EventReader(add, collection.get(29).getImg(), collection.get(30).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                    player.addToBank(50);
                    setGameStatus(0);
                    mainMenu();
            }
        });
        background.add(add);
    }

    public void mainMenu(){

        if (player.getBank()==0) { bankrupt(); return; }
        list=null;

        System.gc();
        formPanel(collection.get(40).getImg());

        Label lbl1 = new Label("Welcome, ", 85, 0, 100, 27, new Color(255, 255, 255, 255),new Font("Rockwell", Font.ITALIC , 18), SwingConstants.LEFT),
               currentPlayer = new Label(player.getNickname(), 175, 0, 200, 24, new Color(255, 251, 8, 255),new Font("Lucida Typewriter", Font.ITALIC+ Font.BOLD , 17), SwingConstants.LEFT),
                lbl2= new Label("Bank: ", 635-(String.valueOf(player.getBank()).length()*7+String.valueOf(player.getBank()).length()/3*5), 0, 75, 27, new Color(255, 255, 255, 255),new Font("Rockwell", Font.ITALIC , 18), SwingConstants.LEFT),
                currentBank = new Label("$ "+String.valueOf(player.getBank()), 590, 0, 120, 24, new Color(255, 251, 8, 255),new Font("Lucida Typewriter", Font.ITALIC+ Font.BOLD , 17), SwingConstants.RIGHT);
        background.add(lbl1);
        background.add(lbl2);
        background.add(currentPlayer);
        background.add(currentBank);

        final Button play=new Button(282, 175, 236, 84, collection.get(37).getImg()),
                statistics=new Button(282, 250, 236, 84, collection.get(58).getImg()),
                options=new Button(282, 325, 236, 84, collection.get(39).getImg()),
                info=new Button(735, 553, 41, 39, collection.get(8).getImg()),
                logOut=new Button(764,3,21,21, collection.get(42).getImg()),
                mute;
        if (sound.isEnabled())
            mute=new Button(685, 553, 41, 39, collection.get(10).getImg());
        else
            mute=new Button(685, 553, 41, 39, collection.get(12).getImg());

        play.addMouseListener(new EventReader(play, collection.get(36).getImg(), collection.get(37).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                playMenu();
            }
        });
        background.add(play);

        statistics.addMouseListener(new EventReader(statistics, collection.get(57).getImg(), collection.get(58).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                statsMenu();
            }
        });
        background.add(statistics);

        options.addMouseListener(new EventReader(options, collection.get(38).getImg(), collection.get(39).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                optionsMenu();
            }
        });
        background.add(options);

        info.addMouseListener(new EventReader(info, collection.get(7).getImg(), collection.get(8).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                about(1);
            }
        });
        background.add(info);

        logOut.addMouseListener(new EventReader(logOut, collection.get(41).getImg(), collection.get(42).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                savePlayer(player);
                readFromFile();
                list.get(id).setProfit(player.getStats().getProfit());
                player=null;
                authorization();
            }
        });
        background.add(logOut);

        mute.addMouseListener(new SoundEventReader(mute, collection.get(11).getImg(), collection.get(12).getImg(), collection.get(26).getImg(), collection.get(9).getImg(), collection.get(10).getImg(), collection.get(25).getImg(), sound));
        background.add(mute);
    }

    public void statsMenu(){
        System.gc();
        formPanel(collection.get(56).getImg());

        Label currentPlayer = new Label(player.getNickname(), 276, 32, 250, 50, new Color(221, 221, 221, 255),new Font("Lucida Typewriter", Font.ITALIC + Font.BOLD , 30), SwingConstants.CENTER),
                lbl2= new Label("Cash: ", 225, 175, 75, 27, new Color(255, 251, 8, 255),new Font("Rockwell", Font.ITALIC + Font.BOLD , 24), SwingConstants.LEFT),
                currentBank = new Label("$ "+String.valueOf(player.getBank()), 305, 175, 200, 27, new Color(221, 221, 221, 255),new Font("Rockwell", Font.ITALIC + Font.BOLD , 24), SwingConstants.LEFT),
                lbl3= new Label("Profit: ", 225, 275, 150, 27, new Color(255, 251, 8, 255),new Font("Rockwell", Font.ITALIC + Font.BOLD , 24), SwingConstants.LEFT),
                currentProfit = new Label("$ "+String.valueOf(player.getStats().getProfit()), 305, 275, 200, 27, new Color(221, 221, 221, 255),new Font("Rockwell", Font.ITALIC + Font.BOLD , 24), SwingConstants.LEFT),
                lbl4= new Label("Wins: ", 465, 175, 75, 27, new Color(255, 251, 8, 255),new Font("Rockwell", Font.ITALIC + Font.BOLD , 24), SwingConstants.LEFT),
                currentWins = new Label(String.valueOf(player.getStats().getWins()), 540, 175, 100, 27, new Color(221, 221, 221, 255),new Font("Rockwell", Font.ITALIC + Font.BOLD , 24), SwingConstants.LEFT),
                lbl5= new Label("Defeats: ", 465, 275, 150, 27, new Color(255, 251, 8, 255),new Font("Rockwell", Font.ITALIC + Font.BOLD , 24), SwingConstants.LEFT),
                currentDefeats = new Label(String.valueOf(player.getStats().getDefeats()), 567, 275, 100, 27, new Color(221, 221, 221, 255),new Font("Rockwell", Font.ITALIC + Font.BOLD , 24), SwingConstants.LEFT),
                lbl6= new Label("Winrate: ", 465, 450, 150, 27, new Color(255, 251, 8, 255),new Font("Rockwell", Font.ITALIC + Font.BOLD , 16), SwingConstants.LEFT),
                currentWinrate = new Label(new DecimalFormat("##.##").format(player.getStats().getWinrate())+" %", 535, 450, 100, 27, new Color(255, 144, 0, 255),new Font("Rockwell", Font.ITALIC + Font.BOLD , 16), SwingConstants.LEFT);

        if (player.getStats().getProfit()<0) {
            currentProfit.setText("-$ " + String.valueOf(-player.getStats().getProfit()));
            currentProfit.setForeground(new Color(254, 0, 0, 255));
        }
        else currentProfit.setForeground(new Color(6, 255, 2, 255));

        if (player.getStats().getWinrate()<35) {
            if (player.getStats().getGames() == 0) {
                currentWinrate.setForeground(new Color(221, 221, 221, 255));
                currentProfit.setForeground(new Color(221, 221, 221, 255));
            }
            else
                currentWinrate.setForeground(new Color(254, 0, 0, 255));
        }
        else
        if (player.getStats().getWinrate()>75)
            currentWinrate.setForeground(new Color(6, 255, 2, 255));

        background.add(currentPlayer);
        background.add(lbl2);
        background.add(currentBank);
        background.add(lbl3);
        background.add(currentProfit);
        background.add(lbl4);
        background.add(currentWins);
        background.add(lbl5);
        background.add(currentDefeats);
        background.add(lbl6);
        background.add(currentWinrate);

        Button back=new Button(282, 525, 236, 84, collection.get(24).getImg());
        back.addMouseListener(new EventReader(back, collection.get(23).getImg(), collection.get(24).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                mainMenu();
            }
        });
        background.add(back);

    }

    public void optionsMenu(){
        System.gc();
        formPanel(collection.get(59).getImg());

        Label lbl1 = new Label("Player: ", 85, 0, 100, 27, new Color(255, 255, 255, 255),new Font("Rockwell", Font.ITALIC , 18), SwingConstants.LEFT),
                currentPlayer = new Label(player.getNickname(), 145, 0, 200, 24, new Color(255, 251, 8, 255),new Font("Lucida Typewriter", Font.ITALIC+ Font.BOLD , 17), SwingConstants.LEFT),
                lbl2= new Label("Bank: ", 635-(String.valueOf(player.getBank()).length()*7+String.valueOf(player.getBank()).length()/3*5), 0, 75, 27, new Color(255, 255, 255, 255),new Font("Rockwell", Font.ITALIC , 18), SwingConstants.LEFT),
                currentBank = new Label("$ "+String.valueOf(player.getBank()), 590, 0, 120, 24, new Color(255, 251, 8, 255),new Font("Lucida Typewriter", Font.ITALIC+ Font.BOLD , 17), SwingConstants.RIGHT),
                lbl3 = new Label("Options", 282, 200, 236, 50, new Color(221, 221, 221, 255),new Font("Rockwell", Font.ITALIC+ Font.BOLD , 26), SwingConstants.CENTER);
        background.add(lbl1);
        background.add(lbl2);
        background.add(lbl3);
        background.add(currentPlayer);
        background.add(currentBank);

        Button change=new Button(282, 260, 236, 84, collection.get(61).getImg());
        change.addMouseListener(new EventReader(change, collection.get(60).getImg(), collection.get(61).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                int musValue;
                switch (sound.getMusicDestination()) {
                    case "source/Sound/Background.wav":
                        musValue = 0;
                        break;
                    case "source/Sound/Background1.wav":
                        musValue = 1;
                        break;
                    case "source/Sound/Background9.wav":
                        musValue = 2;
                        break;
                    case "source/Sound/Background7.wav":
                        musValue = 3;
                        break;
                    case "source/Sound/Background4.wav":
                        musValue = 4;
                        break;
                    case "source/Sound/Background5.wav":
                        musValue = 5;
                        break;
                    case "source/Sound/Background6.wav":
                        musValue = 6;
                        break;
                    case "source/Sound/Background3.wav":
                        musValue = 7;
                        break;
                    case "source/Sound/Background8.wav":
                        musValue = 8;
                        break;
                    case "source/Sound/Background2.wav":
                        musValue = 9;
                        break;
                    default:
                        musValue=0;
                        break;
                }

                if (musValue==9) musValue=0;
                else musValue++;

                switch (musValue) {
                    case 0:
                        sound.setMusicDestination("source/Sound/Background.wav");
                        sound.changeMusic(sound.getMusicDestination());
                        break;
                    case 1:
                        sound.setMusicDestination("source/Sound/Background1.wav");
                        sound.changeMusic(sound.getMusicDestination());
                        break;
                    case 2:
                        sound.setMusicDestination("source/Sound/Background9.wav");
                        sound.changeMusic(sound.getMusicDestination());
                        break;
                    case 3:
                        sound.setMusicDestination("source/Sound/Background7.wav");
                        sound.changeMusic(sound.getMusicDestination());
                        break;
                    case 4:
                        sound.setMusicDestination("source/Sound/Background4.wav");
                        sound.changeMusic(sound.getMusicDestination());
                        break;
                    case 5:
                        sound.setMusicDestination("source/Sound/Background5.wav");
                        sound.changeMusic(sound.getMusicDestination());
                        break;
                    case 6:
                        sound.setMusicDestination("source/Sound/Background6.wav");
                        sound.changeMusic(sound.getMusicDestination());
                        break;
                    case 7:
                        sound.setMusicDestination("source/Sound/Background3.wav");
                        sound.changeMusic(sound.getMusicDestination());
                        break;
                    case 8:
                        sound.setMusicDestination("source/Sound/Background8.wav");
                        sound.changeMusic(sound.getMusicDestination());
                        break;
                    case 9:
                        sound.setMusicDestination("source/Sound/Background2.wav");
                        sound.changeMusic(sound.getMusicDestination());
                        break;
                }

            }
        });
        background.add(change);

        Button reset=new Button(282, 335, 236, 84, collection.get(63).getImg());
        reset.addMouseListener(new EventReader(reset, collection.get(62).getImg(), collection.get(63).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                resetStats();
            }
        });
        background.add(reset);

        Button back=new Button(10, 2, 21, 21, collection.get(55).getImg());
        back.addMouseListener(new EventReader(back, collection.get(54).getImg(), collection.get(55).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                mainMenu();
            }
        });
        background.add(back);

    }

    public void resetStats(){
        System.gc();
        final JFrame resetFrame = new JFrame();
        int height=java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height,
                width=java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;

        disable();
        cursorOptions(resetFrame);
        resetFrame.setIconImage(collection.get(0).getImg());
        resetFrame.setResizable(false);
        resetFrame.setVisible(true);
        resetFrame.setLocation(width / 2 - 200, height / 2 - 160);
        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                enable();
                resetFrame.dispose();
            }
        };
        resetFrame.addWindowListener(exitListener);
        resetFrame.setSize(400, 220);

        ContentPanel resetBackground=new ContentPanel();
        resetBackground.changeImage(collection.get(33).getImg());
        resetFrame.getContentPane().add(resetBackground);

        final Label lbl = new Label("Reset \""+player.getNickname()+"\"", 50, 40, 300, 50,new Color(221, 221, 221, 255),new Font("Lucida Typewriter", Font.ITALIC + Font.BOLD, 24), SwingConstants.CENTER);
        resetBackground.add(lbl);

        Button ok = new Button(64, 95, 136, 77, collection.get(30).getImg());
        ok.addMouseListener(new EventReader(ok, collection.get(29).getImg(), collection.get(30).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                player.getStats().reset();
                enable();
                resetFrame.dispose();
            }
        });
        resetBackground.add(ok);

        Button cancel = new Button(195, 95, 136, 77, collection.get(35).getImg());
        cancel.addMouseListener(new EventReader(cancel, collection.get(34).getImg(), collection.get(35).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                enable();
                resetFrame.dispose();
            }
        });
        resetBackground.add(cancel);
    }

    public void playMenu(){

        list=null;
        if (player.getBank()==0) bankrupt();

        System.gc();
        formPanel(collection.get(43).getImg());

        Label lbl1 = new Label("Player: ", 85, 0, 100, 27, new Color(255, 255, 255, 255),new Font("Rockwell", Font.ITALIC , 18), SwingConstants.LEFT),
                currentPlayer = new Label(player.getNickname(), 145, 0, 200, 24, new Color(255, 251, 8, 255),new Font("Lucida Typewriter", Font.ITALIC+ Font.BOLD , 17), SwingConstants.LEFT),
                lbl2= new Label("Bank: ", 635-(String.valueOf(player.getBank()).length()*7+String.valueOf(player.getBank()).length()/3*5), 0, 75, 27, new Color(255, 255, 255, 255),new Font("Rockwell", Font.ITALIC , 18), SwingConstants.LEFT),
                currentBank = new Label("$ "+String.valueOf(player.getBank()), 590, 0, 120, 24, new Color(255, 251, 8, 255),new Font("Lucida Typewriter", Font.ITALIC+ Font.BOLD , 17), SwingConstants.RIGHT);
        background.add(lbl1);
        background.add(lbl2);
        background.add(currentPlayer);
        background.add(currentBank);

        Button back=new Button(10, 2, 21, 21, collection.get(55).getImg());
        back.addMouseListener(new EventReader(back, collection.get(54).getImg(), collection.get(55).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                mainMenu();
            }
        });
        background.add(back);

        Label choise = new Label("", 300, 463, 200, 50, new Color(255, 255, 255, 255),new Font("Rockwell", Font.ITALIC + Font.BOLD, 32), SwingConstants.CENTER);
        background.add(choise);

        Label hint = new Label("Select the game", 300, 25, 200, 50, new Color(221, 221, 221, 255),new Font("Rockwell", Font.ITALIC + Font.BOLD, 24), SwingConstants.CENTER);
        background.add(hint);

        final SelectedImage roulette = new SelectedImage(0, 70, 266, 320, collection.get(44).getImg(), "Roulette"),
                number=new SelectedImage(266, 70, 267, 320, collection.get(48).getImg(), "The number"),
                jackpot=new SelectedImage(533, 70, 267, 320, collection.get(46).getImg(), "JackPot");

        Button enter=new Button(308, 535, 196, 70, collection.get(52).getImg());
        enter.addMouseListener(new DisabledEventReader(enter, false, collection.get(50).getImg(), collection.get(51).getImg(), collection.get(52).getImg(), collection.get(53).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (isEnabled()) {
                    if (selectGame(roulette)) {
                        Roulette rouletteGame = new Roulette(getWindow());
                    } else if (selectGame(jackpot)) {
                        Jackpot jackpotGame = new Jackpot(getWindow());
                    } else {
                        Number numberGame = new Number(getWindow());
                    }
                }
            }
        });
        background.add(enter);

        roulette.addMouseListener(new CheckedEventReader(roulette, number, jackpot, choise, enter, collection.get(51).getImg(), collection.get(45).getImg(), collection.get(44).getImg()));
        number.addMouseListener(new CheckedEventReader(number, jackpot, roulette, choise, enter, collection.get(51).getImg(), collection.get(49).getImg(), collection.get(48).getImg()));
        jackpot.addMouseListener(new CheckedEventReader(jackpot, number, roulette, choise, enter, collection.get(51).getImg(), collection.get(47).getImg(), collection.get(46).getImg()));
        background.add(roulette);
        background.add(number);
        background.add(jackpot);
    }

    private Boolean selectGame(SelectedImage img){
        CheckedEventReader cer=(CheckedEventReader)img.getMouseListeners()[0];
        return cer.isChecked();
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        Casino casino=new Casino();
        casino.setVisible(true);
    }
}
