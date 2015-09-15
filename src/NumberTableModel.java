import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * Created by Notebook on 27.04.2015.
 */
public class NumberTableModel extends DefaultTableModel {

        public NumberTableModel(){
            addColumn("Number");
            addColumn("Place");
            addColumn("Guess");
        }

        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }

}
