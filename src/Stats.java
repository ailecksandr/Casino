import java.io.Serializable;

/**
 * Created by Notebook on 14.04.2015.
 */
public class Stats implements Serializable {
    private int games, wins, defeats, profit;
    private float winrate;

    Stats(){
        games=0;
        wins=0;
        defeats=0;
        profit=0;
        winrate=0;
    }

    public int getGames(){
        return this.games;
    }

    public int getWins(){
        return this.wins;
    }

    public int getDefeats(){
        return this.defeats;
    }

    public int getProfit(){
        return this.profit;
    }

    public float getWinrate(){
        return this.winrate;
    }

    public void incGames(){
        this.games++;
    }

    public void incWins(){
        this.wins++;
    }

    public void incDefeats(){
        this.defeats++;
    }

    public void addToProfit(int cash){
        this.profit+=cash;
    }

    public void decreaseFromProfit(int cash){
        this.profit-=cash;
    }

    public void formWinrate(){
        if (wins==0) this.winrate=0;
        else this.winrate=(float)wins/games*100;
    }

    public void reset(){
        games=0;
        wins=0;
        defeats=0;
        winrate=0;
        profit=0;
    }
}
