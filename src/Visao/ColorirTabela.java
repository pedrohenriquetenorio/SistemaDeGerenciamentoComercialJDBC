
package Visao;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ColorirTabela extends DefaultTableCellRenderer {

    Color defaultBackground, defaultForeground;

    public ColorirTabela() {
        
        this.defaultBackground = getBackground();
        this.defaultForeground = getForeground();
        setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        
    }

    @Override                                       /// TABELA  Legenda Coluna           
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
         
        int row, int column) {
        
        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            String saldo = (String) value;

            if (saldo == "Estoque Cheio") {
                c.setBackground(Color.red);
                c.setForeground(Color.white);
            }else{
                c.setBackground(defaultBackground);
                c.setForeground(defaultForeground);             
            }
            setText(saldo.toString());
            System.err.println(" TESTE DE PASSAGEM DE PARAMETRO TABELA " + table +" OBJETO "+value +" BOOLEAN SELECTED "+
                    isSelected +" OOLEAN HASFOCUS "+hasFocus+ " LINHA "+ row +"COLUNA"+ column);
            
            
        return c;
    }

}

    

