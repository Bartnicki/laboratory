package app;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CustomJTable extends JTable {

    public Component prepareRenderer(TableCellRenderer ren, int r, int c){

        Component comp = super.prepareRenderer(ren,r,c);
        if (r%2 == 0 && !isCellSelected(r,c)){
            comp.setBackground(new Color(213, 207, 255));
        } else if(!isCellSelected(r,c)){
            comp.setBackground(new Color(131, 128, 255));
        }else{
            comp.setBackground(new Color(77, 77, 255));
        }
        return comp;
    }

}
