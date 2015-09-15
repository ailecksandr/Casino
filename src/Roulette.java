import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;

/**
 * Created by Notebook on 24.04.2015.
 */
public class Roulette {
    private Casino window;
    private ContentPanel background;
    private SoundOptions sound;
    private Player player;
    private ArrayList<SerializableImage> collection;

    public Roulette(Casino window){
        this.window=window;
        collection = new ArrayList<SerializableImage>();
        sound=window.getSoundOptions();
        sound.changeMusic("source/Sound/Roulette.wav");
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
                lbl3= new Label("Your bet: ", 613, 485, 150, 50, new Color(255, 255, 255, 255),new Font("Rockwell", Font.ITALIC , 24), SwingConstants.CENTER),
                error=new Label("", 613, 570, 150, 50, new Color(255, 2, 44, 255),new Font("Rockwell", Font.ITALIC+Font.BOLD, 14), SwingConstants.CENTER),
                info=new Label("",665, 25, 200, 50, new Color(255, 251, 8, 255),new Font("Lucida Typewriter", Font.ITALIC+ Font.BOLD , 24), SwingConstants.CENTER);
        background.add(lbl1);
        background.add(lbl2);
        background.add(currentPlayer);
        background.add(currentBank);
        background.add(lbl3);
        background.add(error);
        background.add(info);

        final Slot number=new Slot(38, 512, 75, 75, new Color(255,255,255,255), new Font("Rockwell", Font.ITALIC , 18));
        background.add(number);

        final BetInput bet=new BetInput(600,530,150,50);
        bet.addKeyListener(new BetListener(player));
        background.add(bet);

        final Timer increase=new Timer(25,new ChangeEventListener(bet, player.getBank(), true)),
                decrease=new Timer(25, new ChangeEventListener(bet, player.getBank(), false));

        Button upperBet=new Button(755,530,21,21,collection.get(98).getImg());
        upperBet.addMouseListener(new EventReader(upperBet, collection.get(97).getImg(), collection.get(98).getImg(), sound) {
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

        Button lowerBet=new Button(755,559,21,21,collection.get(100).getImg());
        lowerBet.addMouseListener(new EventReader(lowerBet, collection.get(99).getImg(), collection.get(100).getImg(), sound) {
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

        Button back=new Button(10, 2, 21, 21, collection.get(39).getImg());
        back.addMouseListener(new EventReader(back, collection.get(38).getImg(), collection.get(39).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                sound.changeMusic(sound.getMusicDestination());
                collection.clear();
                window.playMenu();
            }
        });
        background.add(back);

        final Button b0=new Button(36, 81, 91, 284, collection.get(1).getImg());
        b0.addMouseListener(new EventReader(b0, collection.get(50).getImg(), collection.get(1).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b0.setIcon(new ImageIcon(collection.get(1).getImg()));
                    letsRoll(number, 0, currentBank, error, bet, info);
                }
            }
        });
        background.add(b0);

        final Button b1=new Button(129, 271, 48, 94, collection.get(2).getImg());
        b1.addMouseListener(new EventReader(b1, collection.get(51).getImg(), collection.get(2).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b1.setIcon(new ImageIcon(collection.get(2).getImg()));
                    letsRoll(number, 1, currentBank, error, bet, info);
                }

            }
        });
        background.add(b1);

        final Button b2=new Button(129, 176, 48, 94, collection.get(3).getImg());
        b2.addMouseListener(new EventReader(b2, collection.get(52).getImg(), collection.get(3).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b2.setIcon(new ImageIcon(collection.get(3).getImg()));
                    letsRoll(number, 2, currentBank, error, bet, info);
                }
            }
        });
        background.add(b2);

        final Button b3=new Button(129, 81, 48, 95, collection.get(4).getImg());
        b3.addMouseListener(new EventReader(b3, collection.get(53).getImg(), collection.get(4).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b3.setIcon(new ImageIcon(collection.get(4).getImg()));
                    letsRoll(number, 3, currentBank, error, bet, info);
                }
            }
        });
        background.add(b3);

        final Button b4=new Button(179, 271, 48, 94, collection.get(5).getImg());
        b4.addMouseListener(new EventReader(b4, collection.get(54).getImg(), collection.get(5).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b4.setIcon(new ImageIcon(collection.get(5).getImg()));
                    letsRoll(number, 4, currentBank, error, bet, info);
                }
            }
        });
        background.add(b4);

        final Button b5=new Button(179, 176, 48, 94, collection.get(6).getImg());
        b5.addMouseListener(new EventReader(b5, collection.get(55).getImg(), collection.get(6).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b5.setIcon(new ImageIcon(collection.get(6).getImg()));
                    letsRoll(number, 5, currentBank, error, bet, info);
                }
            }
        });
        background.add(b5);

        final Button b6=new Button(179, 81, 48, 95, collection.get(7).getImg());
        b6.addMouseListener(new EventReader(b6, collection.get(56).getImg(), collection.get(7).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b6.setIcon(new ImageIcon(collection.get(7).getImg()));
                    letsRoll(number, 6, currentBank, error, bet, info);
                }
            }
        });
        background.add(b6);

        final Button b7=new Button(228, 271, 48, 94, collection.get(8).getImg());
        b7.addMouseListener(new EventReader(b7, collection.get(57).getImg(), collection.get(8).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b7.setIcon(new ImageIcon(collection.get(8).getImg()));
                    letsRoll(number, 7, currentBank, error, bet, info);
                }
            }
        });
        background.add(b7);

        final Button b8=new Button(228, 176, 48, 94, collection.get(9).getImg());
        b8.addMouseListener(new EventReader(b8, collection.get(58).getImg(), collection.get(9).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b8.setIcon(new ImageIcon(collection.get(9).getImg()));
                    letsRoll(number, 8, currentBank, error, bet, info);
                }
            }
        });
        background.add(b8);

        final Button b9=new Button(228, 81, 48, 95, collection.get(10).getImg());
        b9.addMouseListener(new EventReader(b9, collection.get(59).getImg(), collection.get(10).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b9.setIcon(new ImageIcon(collection.get(10).getImg()));
                    letsRoll(number, 9, currentBank, error, bet, info);
                }
            }
        });
        background.add(b9);

        final Button b10=new Button(278, 271, 48, 94, collection.get(11).getImg());
        b10.addMouseListener(new EventReader(b10, collection.get(60).getImg(), collection.get(11).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b10.setIcon(new ImageIcon(collection.get(11).getImg()));
                    letsRoll(number, 10, currentBank, error, bet, info);
                }
            }
        });
        background.add(b10);

        final Button b11=new Button(278, 176, 48, 94, collection.get(12).getImg());
        b11.addMouseListener(new EventReader(b11, collection.get(61).getImg(), collection.get(12).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b11.setIcon(new ImageIcon(collection.get(12).getImg()));
                    letsRoll(number, 11, currentBank, error, bet, info);
                }
            }
        });
        background.add(b11);

        final Button b12=new Button(278, 81, 48, 95, collection.get(13).getImg());
        b12.addMouseListener(new EventReader(b12, collection.get(62).getImg(), collection.get(13).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b12.setIcon(new ImageIcon(collection.get(13).getImg()));
                    letsRoll(number, 12, currentBank, error, bet, info);
                }
            }
        });
        background.add(b12);

        final Button b13=new Button(327, 271, 48, 94, collection.get(14).getImg());
        b13.addMouseListener(new EventReader(b13, collection.get(63).getImg(), collection.get(14).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b13.setIcon(new ImageIcon(collection.get(14).getImg()));
                    letsRoll(number, 13, currentBank, error, bet, info);
                }
            }
        });
        background.add(b13);

        final Button b14=new Button(327, 176, 48, 94, collection.get(15).getImg());
        b14.addMouseListener(new EventReader(b14, collection.get(64).getImg(), collection.get(15).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b14.setIcon(new ImageIcon(collection.get(15).getImg()));
                    letsRoll(number, 14, currentBank, error, bet, info);
                }
            }
        });
        background.add(b14);

        final Button b15=new Button(327, 81, 48, 95, collection.get(16).getImg());
        b15.addMouseListener(new EventReader(b15, collection.get(65).getImg(), collection.get(16).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b15.setIcon(new ImageIcon(collection.get(16).getImg()));
                    letsRoll(number, 15, currentBank, error, bet, info);
                }
            }
        });
        background.add(b15);

        final Button b16=new Button(376, 271, 48, 94, collection.get(17).getImg());
        b16.addMouseListener(new EventReader(b16, collection.get(66).getImg(), collection.get(17).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b16.setIcon(new ImageIcon(collection.get(17).getImg()));
                    letsRoll(number, 16, currentBank, error, bet, info);
                }
            }
        });
        background.add(b16);

        final Button b17=new Button(376, 176, 48, 94, collection.get(18).getImg());
        b17.addMouseListener(new EventReader(b17, collection.get(67).getImg(), collection.get(18).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b17.setIcon(new ImageIcon(collection.get(18).getImg()));
                    letsRoll(number, 17, currentBank, error, bet, info);
                }
            }
        });
        background.add(b17);

        final Button b18=new Button(376, 81, 48, 95, collection.get(19).getImg());
        b18.addMouseListener(new EventReader(b18, collection.get(68).getImg(), collection.get(19).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b18.setIcon(new ImageIcon(collection.get(19).getImg()));
                    letsRoll(number, 18, currentBank, error, bet, info);
                }
            }
        });
        background.add(b18);

        final Button b19=new Button(426, 271, 48, 94, collection.get(20).getImg());
        b19.addMouseListener(new EventReader(b19, collection.get(69).getImg(), collection.get(20).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b19.setIcon(new ImageIcon(collection.get(20).getImg()));
                    letsRoll(number, 19, currentBank, error, bet, info);
                }
            }
        });
        background.add(b19);

        final Button b20=new Button(426, 176, 48, 94, collection.get(21).getImg());
        b20.addMouseListener(new EventReader(b20, collection.get(70).getImg(), collection.get(21).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b20.setIcon(new ImageIcon(collection.get(21).getImg()));
                    letsRoll(number, 20, currentBank, error, bet, info);
                }
            }
        });
        background.add(b20);

        final Button b21=new Button(426, 81, 48, 95, collection.get(22).getImg());
        b21.addMouseListener(new EventReader(b21, collection.get(71).getImg(), collection.get(22).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b21.setIcon(new ImageIcon(collection.get(22).getImg()));
                    letsRoll(number, 21, currentBank, error, bet, info);
                }
            }
        });
        background.add(b21);

        final Button b22=new Button(475, 271, 48, 94, collection.get(23).getImg());
        b22.addMouseListener(new EventReader(b22, collection.get(72).getImg(), collection.get(23).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b22.setIcon(new ImageIcon(collection.get(23).getImg()));
                    letsRoll(number, 22, currentBank, error, bet, info);
                }
            }
        });
        background.add(b22);

        final Button b23=new Button(475, 176, 48, 94, collection.get(24).getImg());
        b23.addMouseListener(new EventReader(b23, collection.get(73).getImg(), collection.get(24).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b23.setIcon(new ImageIcon(collection.get(24).getImg()));
                    letsRoll(number, 23, currentBank, error, bet, info);
                }
            }
        });
        background.add(b23);

        final Button b24=new Button(475, 81, 48, 95, collection.get(25).getImg());
        b24.addMouseListener(new EventReader(b24, collection.get(74).getImg(), collection.get(25).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b24.setIcon(new ImageIcon(collection.get(25).getImg()));
                    letsRoll(number, 24, currentBank, error, bet, info);
                }
            }
        });
        background.add(b24);

        final Button b25=new Button(525, 271, 48, 94, collection.get(26).getImg());
        b25.addMouseListener(new EventReader(b25, collection.get(75).getImg(), collection.get(26).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b25.setIcon(new ImageIcon(collection.get(26).getImg()));
                    letsRoll(number, 25, currentBank, error, bet, info);
                }
            }
        });
        background.add(b25);

        final Button b26=new Button(525, 176, 48, 94, collection.get(27).getImg());
        b26.addMouseListener(new EventReader(b26, collection.get(76).getImg(), collection.get(27).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b26.setIcon(new ImageIcon(collection.get(27).getImg()));
                    letsRoll(number, 26, currentBank, error, bet, info);
                }
            }
        });
        background.add(b26);

        final Button b27=new Button(525, 81, 48, 95, collection.get(28).getImg());
        b27.addMouseListener(new EventReader(b27, collection.get(77).getImg(), collection.get(28).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b27.setIcon(new ImageIcon(collection.get(28).getImg()));
                    letsRoll(number, 27, currentBank, error, bet, info);
                }
            }
        });
        background.add(b27);

        final Button b28=new Button(574, 271, 48, 94, collection.get(29).getImg());
        b28.addMouseListener(new EventReader(b28, collection.get(78).getImg(), collection.get(29).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b28.setIcon(new ImageIcon(collection.get(29).getImg()));
                    letsRoll(number, 28, currentBank, error, bet, info);
                }
            }
        });
        background.add(b28);

        final Button b29=new Button(574, 176, 48, 94, collection.get(30).getImg());
        b29.addMouseListener(new EventReader(b29, collection.get(79).getImg(), collection.get(30).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b29.setIcon(new ImageIcon(collection.get(30).getImg()));
                    letsRoll(number, 29, currentBank, error, bet, info);
                }
            }
        });
        background.add(b29);

        final Button b30=new Button(574, 81, 48, 95, collection.get(31).getImg());
        b30.addMouseListener(new EventReader(b30, collection.get(80).getImg(), collection.get(31).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b30.setIcon(new ImageIcon(collection.get(31).getImg()));
                    letsRoll(number, 30, currentBank, error, bet, info);
                }
            }
        });
        background.add(b30);

        final Button b31=new Button(623, 271, 48, 94, collection.get(32).getImg());
        b31.addMouseListener(new EventReader(b31, collection.get(81).getImg(), collection.get(32).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b31.setIcon(new ImageIcon(collection.get(32).getImg()));
                    letsRoll(number, 31, currentBank, error, bet, info);
                }
            }
        });
        background.add(b31);

        final Button b32=new Button(623, 176, 48, 94, collection.get(33).getImg());
        b32.addMouseListener(new EventReader(b32, collection.get(82).getImg(), collection.get(33).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b32.setIcon(new ImageIcon(collection.get(33).getImg()));
                    letsRoll(number, 32, currentBank, error, bet, info);
                }
            }
        });
        background.add(b32);

        final Button b33=new Button(623, 81, 48, 95, collection.get(34).getImg());
        b33.addMouseListener(new EventReader(b33, collection.get(83).getImg(), collection.get(34).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b33.setIcon(new ImageIcon(collection.get(34).getImg()));
                    letsRoll(number, 33, currentBank, error, bet, info);
                }
            }
        });
        background.add(b33);

        final Button b34=new Button(673, 271, 48, 94, collection.get(35).getImg());
        b34.addMouseListener(new EventReader(b34, collection.get(84).getImg(), collection.get(35).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b34.setIcon(new ImageIcon(collection.get(35).getImg()));
                    letsRoll(number, 34, currentBank, error, bet, info);
                }
            }
        });
        background.add(b34);

        final Button b35=new Button(673, 176, 48, 94, collection.get(36).getImg());
        b35.addMouseListener(new EventReader(b35, collection.get(85).getImg(), collection.get(36).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b35.setIcon(new ImageIcon(collection.get(36).getImg()));
                    letsRoll(number, 35, currentBank, error, bet, info);
                }
            }
        });
        background.add(b35);

        final Button b36=new Button(673, 81, 48, 95, collection.get(37).getImg());
        b36.addMouseListener(new EventReader(b36, collection.get(86).getImg(), collection.get(37).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b36.setIcon(new ImageIcon(collection.get(37).getImg()));
                    letsRoll(number, 36, currentBank, error, bet, info);
                }
            }
        });
        background.add(b36);

        final Button b37=new Button(129, 367, 196, 34, collection.get(40).getImg());
        b37.addMouseListener(new EventReader(b37, collection.get(87).getImg(), collection.get(40).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b37.setIcon(new ImageIcon(collection.get(40).getImg()));
                    letsRoll(number, 37, currentBank, error, bet, info);
                }
            }
        });
        background.add(b37);

        final Button b38=new Button(328, 367, 194, 34, collection.get(41).getImg());
        b38.addMouseListener(new EventReader(b38, collection.get(88).getImg(), collection.get(41).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b38.setIcon(new ImageIcon(collection.get(41).getImg()));
                    letsRoll(number, 38, currentBank, error, bet, info);
                }
            }
        });
        background.add(b38);

        final Button b39=new Button(526, 367, 195, 34, collection.get(42).getImg());
        b39.addMouseListener(new EventReader(b39, collection.get(89).getImg(), collection.get(42).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b39.setIcon(new ImageIcon(collection.get(42).getImg()));
                    letsRoll(number, 39, currentBank, error, bet, info);
                }
            }
        });
        background.add(b39);

        final Button b40=new Button(129, 404, 96, 33, collection.get(43).getImg());
        b40.addMouseListener(new EventReader(b40, collection.get(90).getImg(), collection.get(43).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b40.setIcon(new ImageIcon(collection.get(43).getImg()));
                    letsRoll(number, 40, currentBank, error, bet, info);
                }
            }
        });
        background.add(b40);

        final Button b41=new Button(228, 404, 97, 33, collection.get(44).getImg());
        b41.addMouseListener(new EventReader(b41, collection.get(91).getImg(), collection.get(44).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b41.setIcon(new ImageIcon(collection.get(44).getImg()));
                    letsRoll(number, 41, currentBank, error, bet, info);
                }
            }
        });
        background.add(b41);

        final Button b42=new Button(328, 404, 96, 33, collection.get(45).getImg());
        b42.addMouseListener(new EventReader(b42, collection.get(92).getImg(), collection.get(45).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b42.setIcon(new ImageIcon(collection.get(45).getImg()));
                    letsRoll(number, 42, currentBank, error, bet, info);
                }
            }
        });
        background.add(b42);

        final Button b43=new Button(427, 404, 95, 33, collection.get(46).getImg());
        b43.addMouseListener(new EventReader(b43, collection.get(93).getImg(), collection.get(46).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b43.setIcon(new ImageIcon(collection.get(46).getImg()));
                    letsRoll(number, 43, currentBank, error, bet, info);
                }
            }
        });
        background.add(b43);

        final Button b44=new Button(526, 404, 96, 33, collection.get(47).getImg());
        b44.addMouseListener(new EventReader(b44, collection.get(94).getImg(), collection.get(47).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b44.setIcon(new ImageIcon(collection.get(47).getImg()));
                    letsRoll(number, 44, currentBank, error, bet, info);
                }
            }
        });
        background.add(b44);

        final Button b45=new Button(625, 404, 96, 33, collection.get(48).getImg());
        b45.addMouseListener(new EventReader(b45, collection.get(95).getImg(), collection.get(48).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b45.setIcon(new ImageIcon(collection.get(48).getImg()));
                    letsRoll(number, 45, currentBank, error, bet, info);
                }
            }
        });
        background.add(b45);

        final Button b46=new Button(724, 82, 46, 93, collection.get(49).getImg());
        b46.addMouseListener(new EventReader(b46, collection.get(96).getImg(), collection.get(49).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b46.setIcon(new ImageIcon(collection.get(49).getImg()));
                    letsRoll(number, 46, currentBank, error, bet, info);
                }
            }
        });
        background.add(b46);

        final Button b47=new Button(724, 178, 46, 91, collection.get(49).getImg());
        b47.addMouseListener(new EventReader(b47, collection.get(96).getImg(), collection.get(49).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b47.setIcon(new ImageIcon(collection.get(49).getImg()));
                    letsRoll(number, 47, currentBank, error, bet, info);
                }
            }
        });
        background.add(b47);

        final Button b48=new Button(724, 273, 46, 91, collection.get(49).getImg());
        b48.addMouseListener(new EventReader(b48, collection.get(96).getImg(), collection.get(49).getImg(), sound) {
            public void mousePressed(MouseEvent e) {
                if (bet.getText().isEmpty() || Integer.valueOf(bet.getText())==0)
                    error.setText("Place your bet");
                else {
                    b48.setIcon(new ImageIcon(collection.get(49).getImg()));
                    letsRoll(number, 48, currentBank, error, bet, info);
                }
            }
        });
        background.add(b48);

    }

    // ************ для дозапису малюнків
    private void writeCollection(){
        formImage("source/Images/rouletteBackground.png"); //0
        formImage("source/Images/zero.png"); //1
        formImage("source/Images/1.png"); //2
        formImage("source/Images/2.png"); //3
        formImage("source/Images/3.png"); //4
        formImage("source/Images/4.png"); //5
        formImage("source/Images/5.png"); //6
        formImage("source/Images/6.png"); //7
        formImage("source/Images/7.png"); //8
        formImage("source/Images/8.png"); //9
        formImage("source/Images/9.png"); //10
        formImage("source/Images/10.png"); //11
        formImage("source/Images/11.png"); //12
        formImage("source/Images/12.png"); //13
        formImage("source/Images/13.png"); //14
        formImage("source/Images/14.png"); //15
        formImage("source/Images/15.png"); //16
        formImage("source/Images/16.png"); //17
        formImage("source/Images/17.png"); //18
        formImage("source/Images/18.png"); //19
        formImage("source/Images/19.png"); //20
        formImage("source/Images/20.png"); //21
        formImage("source/Images/21.png"); //22
        formImage("source/Images/22.png"); //23
        formImage("source/Images/23.png"); //24
        formImage("source/Images/24.png"); //25
        formImage("source/Images/25.png"); //26
        formImage("source/Images/26.png"); //27
        formImage("source/Images/27.png"); //28
        formImage("source/Images/28.png"); //29
        formImage("source/Images/29.png"); //30
        formImage("source/Images/30.png"); //31
        formImage("source/Images/31.png"); //32
        formImage("source/Images/32.png"); //33
        formImage("source/Images/33.png"); //34
        formImage("source/Images/34.png"); //35
        formImage("source/Images/35.png"); //36
        formImage("source/Images/36.png"); //37
        formImage("source/Images/backIconActive.png"); //38
        formImage("source/Images/backIconInactive.png"); //39
        formImage("source/Images/1st.png"); //40
        formImage("source/Images/2nd.png"); //41
        formImage("source/Images/3rd.png"); //42
        formImage("source/Images/1-18.png"); //43
        formImage("source/Images/even.png"); //44
        formImage("source/Images/black.png"); //45
        formImage("source/Images/red.png"); //46
        formImage("source/Images/odd.png"); //47
        formImage("source/Images/19-36.png"); //48
        formImage("source/Images/2to1.png"); //49
        formImage("source/Images/zeroa.png"); //50
        formImage("source/Images/1a.png"); //51
        formImage("source/Images/2a.png"); //52
        formImage("source/Images/3a.png"); //53
        formImage("source/Images/4a.png"); //54
        formImage("source/Images/5a.png"); //55
        formImage("source/Images/6a.png"); //56
        formImage("source/Images/7a.png"); //57
        formImage("source/Images/8a.png"); //58
        formImage("source/Images/9a.png"); //59
        formImage("source/Images/10a.png"); //60
        formImage("source/Images/11a.png"); //61
        formImage("source/Images/12a.png"); //62
        formImage("source/Images/13a.png"); //63
        formImage("source/Images/14a.png"); //64
        formImage("source/Images/15a.png"); //65
        formImage("source/Images/16a.png"); //66
        formImage("source/Images/17a.png"); //67
        formImage("source/Images/18a.png"); //68
        formImage("source/Images/19a.png"); //69
        formImage("source/Images/20a.png"); //70
        formImage("source/Images/21a.png"); //71
        formImage("source/Images/22a.png"); //72
        formImage("source/Images/23a.png"); //73
        formImage("source/Images/24a.png"); //74
        formImage("source/Images/25a.png"); //75
        formImage("source/Images/26a.png"); //76
        formImage("source/Images/27a.png"); //77
        formImage("source/Images/28a.png"); //78
        formImage("source/Images/29a.png"); //79
        formImage("source/Images/30a.png"); //80
        formImage("source/Images/31a.png"); //81
        formImage("source/Images/32a.png"); //82
        formImage("source/Images/33a.png"); //83
        formImage("source/Images/34a.png"); //84
        formImage("source/Images/35a.png"); //85
        formImage("source/Images/36a.png"); //86
        formImage("source/Images/1sta.png"); //87
        formImage("source/Images/2nda.png"); //88
        formImage("source/Images/3rda.png"); //89
        formImage("source/Images/1-18a.png"); //90
        formImage("source/Images/evena.png"); //91
        formImage("source/Images/blacka.png"); //92
        formImage("source/Images/reda.png"); //93
        formImage("source/Images/odda.png"); //94
        formImage("source/Images/19-36a.png"); //95
        formImage("source/Images/2to1a.png"); //96
        formImage("source/Images/upperBeta.png"); //97
        formImage("source/Images/upperBet.png"); //98
        formImage("source/Images/lowerBeta.png"); //99
        formImage("source/Images/lowerBet.png"); //100
        writeCollectionToFile();
    }

    private void letsRoll(JLabel number, int reason, Label bank, Label error, BetInput bet, Label info){
        window.setCursor(Cursor.getDefaultCursor());
        window.disable();
        error.setText("");
        bet.setFocusable(false);
        number.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 50), 3));
        sound.changeMusic("source/Sound/Rolling.wav");
        Timer timer = new Timer(10, null);
        timer.addActionListener(new RouletteRolling(number, timer, sound, window, reason, player, bank, bet, info));
        timer.start();
    }

    private void writeCollectionToFile(){
        try{
            File fail = new File("source/Images/roulette.res");
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
            File fail = new File("source/Images/roulette.res");
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
