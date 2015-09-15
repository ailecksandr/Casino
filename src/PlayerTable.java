import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Notebook on 16.04.2015.
 */
public class PlayerTable extends JTable {
    public PlayerTable(JLabel play, JLabel delete, TableModel model){
        setModel(model);

        setFillsViewportHeight(true);
        setTableHeader(null);

        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ListSelectionModel selectionModel = getSelectionModel();
        selectionModel.addListSelectionListener(new CellSelectedListener(play, delete));

        setOpaque(false);
        setShowGrid(false);

        setBackgrounds();
        setForegrounds();
        setTextFormat();
    }

    private void setBackgrounds(){
        setBackground(new Color(2, 0, 69, 100));
        setSelectionBackground(new Color(2, 0, 69, 25));
    }

    private void setForegrounds() {
        setForeground(new Color(221, 221, 221, 255));
        setSelectionForeground(new Color(255, 255, 255, 255));
    }

    private void setTextFormat(){
        setRowHeight(65);
        setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
        DefaultTableCellRenderer align = new DefaultTableCellRenderer();
        align.setHorizontalAlignment(SwingConstants.CENTER);
        getColumnModel().getColumn(0).setCellRenderer(align);
    }
}
