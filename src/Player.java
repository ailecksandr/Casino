import java.io.Serializable;

/**
 * Created by Notebook on 14.04.2015.
 */
public class Player implements Serializable {
    private String nickname;
    private int bank;
    private Stats stats;

    public Player(String nickname){
        this.nickname=nickname;
        if (nickname.equals("greedisgood")) bank=1000000;
        else bank=1000;
        stats=new Stats();
    }

    public String getNickname(){
        return this.nickname;
    }

    public int getBank(){
        return this.bank;
    }

    public void addToBank(int cash){
        this.bank+=cash;
    }

    public void decreaseFromBank(int cash) { this.bank-=cash; }

    public Stats getStats(){
        return this.stats;
    }

}
