/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import java.awt.Color;
import java.awt.Component;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author pedro
 */

public class tableRenderer extends DefaultTableCellRenderer{
ImageIcon icone;    
String status;
    tableRenderer(ImageIcon icone, String status){
    this.icone = icone;
    this.status = status;
    
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
         final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);       
            String s = table.getModel().getValueAt(row, column).toString();
            if("".equals(s) ){
                
                icone = new ImageIcon("C:\\Users\\pedro\\Documents\\NetBeansProjects\\TrabalhoConclusaoCursoPedroTenorio\\src\\icon\\alerta20x20.png");
            
            }else{
              
                icone = new ImageIcon("C:\\Users\\pedro\\Documents\\NetBeansProjects\\TrabalhoConclusaoCursoPedroTenorio\\src\\icon\\confirmar20x20.png");
            
            }
            setIcon(this.icone);
           return c; 
         
    }
   
}
