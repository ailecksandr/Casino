import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * Created by Notebook on 16.04.2015.
 */
public class PlayerTableModel extends DefaultTableModel {

    public PlayerTableModel(ArrayList<PlayerList> list){
        addColumn("");
        for (PlayerList temp : list)
            addRow(new Object[]{temp.getNickname()});

    }

    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }
}
