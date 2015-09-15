import java.io.Serializable;

/**
 * Created by Notebook on 27.04.2015.
 */
public class PlayerList implements Serializable {
    private String nickname;
    private int profit;

    public PlayerList(String nickname, int profit){
        this.nickname=nickname;
        this.profit=profit;
    }

    public String getNickname(){
        return nickname;
    }

    public int getProfit(){
        return profit;
    }

    public void setProfit(int profit){
        this.profit=profit;
    }
}
