import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Notebook on 27.04.2015.
 */
public class NumberTable extends JTable {

    public NumberTable(TableModel model){
        setModel(model);

        setTableHeader(null);

        setOpaque(false);
        setGridColor(new Color(0, 0, 0, 50));

        setFillsViewportHeight(true);
        disable();

        setBackgrounds();
        setForegrounds();
        setTextFormat();
    }

    private void setBackgrounds(){
        setBackground(new Color(52, 52, 56, 50));
        setSelectionBackground(new Color(94, 93, 169, 50));
    }

    private void setForegrounds(){
        setForeground(new Color(255, 255, 210, 255));
        setSelectionForeground(new Color(255, 251, 8, 255));
    }

    private void setTextFormat(){
        setRowHeight(49);
        setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 20));
        DefaultTableCellRenderer align = new DefaultTableCellRenderer();
        align.setHorizontalAlignment(SwingConstants.CENTER);
        getColumnModel().getColumn(0).setCellRenderer(align);
        getColumnModel().getColumn(1).setCellRenderer(align);
        getColumnModel().getColumn(2).setCellRenderer(align);
        getColumnModel().getColumn(0).setPreferredWidth(200);
        getColumnModel().getColumn(1).setPreferredWidth(75);
        getColumnModel().getColumn(2).setPreferredWidth(75);
    }
}

