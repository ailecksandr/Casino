import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * Created by Notebook on 27.04.2015.
 */
public class LeaderboardTableModel extends DefaultTableModel {

    public LeaderboardTableModel(){
        addColumn("Name");
        addColumn("Profit");
    }

    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }

}

