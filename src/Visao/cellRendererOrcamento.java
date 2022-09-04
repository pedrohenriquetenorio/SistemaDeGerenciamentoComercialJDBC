///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Visao;
//
//import Control.Exceptions.NaoEncontradoException;
//import DAO.EstoqueDAO;
//import Modelo.EstoqueModel;
//import Modelo.ItemOrcamento;
//import Modelo.ItemVenda;
////import Modelo.ItemVenda;
//import java.awt.Color;
//import java.awt.Component;
//import java.sql.SQLException;
//import java.text.ParseException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import java.util.stream.Collectors;
//import javax.swing.ImageIcon;
//import javax.swing.JTable;
//import javax.swing.table.DefaultTableCellRenderer;
//
///**
// *
// * @author pedro
// */
//public class cellRendererOrcamento extends DefaultTableCellRenderer {
//
//    List<ItemVenda> listaItemVenda;
//    List<EstoqueModel> listaEstoque;
//    EstoqueModel estoque;
//    JTable tabela;
//    Color defaultBackground, defaultForeground;
//
//    cellRendererOrcamento() {
//
//    }
//
//    cellRendererOrcamento(List itemVenda) {
//        listaItemVenda = itemVenda;
//
//        //his.tabela = tabela;
//        //verificaEstoque();
//        this.defaultBackground = getBackground();
//        this.defaultForeground = getForeground();
//    }
//
////    public void verificaEstoque(){
////     EstoqueModel estoque = new EstoqueModel();
////     EstoqueDAO estoqueDao = new EstoqueDAO();
////     
////     for(ItemVenda i: this.listaItemVenda){
////         System.out.println("LISTA DE ITEM VENDA"+ i.getEstoque().getProduto().getCodigoBarra());
////         estoque = i.getEstoque();
////         
//////        try {
//////            estoque = estoqueDao.pesquisaEstoqueCodigoBarras(estoque.getProduto().getCodigoBarra());
//////            listaEstoque.add(estoque);
//////           
//////        } catch (SQLException ex) {
//////            Logger.getLogger(cellRendererOrcamento.class.getName()).log(Level.SEVERE, null, ex);
//////        } catch (ParseException ex) {
//////            Logger.getLogger(cellRendererOrcamento.class.getName()).log(Level.SEVERE, null, ex);
//////        } catch (NaoEncontradoException ex) {
//////            Logger.getLogger(cellRendererOrcamento.class.getName()).log(Level.SEVERE, null, ex);
//////        }
//////        
////    }
////    }
//    @Override
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
//            boolean hasFocus, int row, int column) {
//
//        final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//        String s = table.getModel().getValueAt(row, column).toString();
//        String quantidadeString = (String) table.getModel().getValueAt(row, 2);
//        String unidade = (String) table.getModel().getValueAt(row, 3);
//        quantidadeString = quantidadeString.replace(",", "");
//       
//        Double quantidade = Double.parseDouble(quantidadeString);
//
//        if ("KG".equals(unidade)) {
//            quantidade = quantidade / 1000;
//        }
//        EstoqueDAO estoqueDao = new EstoqueDAO();
//        EstoqueModel estoque = new EstoqueModel();
//        
//        List<EstoqueModel>itemEstoque = new ArrayList<>();
//        
//        for (ItemVenda i : this.listaItemVenda) {
//            
//            try {
//                
//                estoque = estoqueDao.pesquisaEstoqueCodigoBarras(i.getEstoque().getProduto().getCodigoBarra());
//                
//                    if (i.getQuantidade() > estoque.getQtdEstoque() && i.getEstoque().getProduto().getCodigoBarra().equals(estoque.getProduto().getCodigoBarra())) {
//                        boolean a = itemEstoque.contains(estoque);
//                        if(a == false){
//                            itemEstoque.add(estoque);
//                        }
//                    }
//                   
//            } catch (SQLException ex) {
//                Logger.getLogger(cellRendererOrcamento.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ParseException ex) {
//                Logger.getLogger(cellRendererOrcamento.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (NaoEncontradoException ex) {
//                Logger.getLogger(cellRendererOrcamento.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//      
//      //  List<EstoqueModel> ListaFinalEstoque = itemEstoque.stream().distinct().collect(Collectors.toList());
//        for(EstoqueModel e: itemEstoque){
//                System.out.println("LISTA ITEM VENDA DEPOIS " + e.getProduto().getNome());
//                c.setBackground(Color.red);
//                c.setForeground(Color.white);
//            
//        }
//        return c;
//
//    }
//
//}
//
//
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Control.Exceptions.NaoEncontradoException;
import DAO.EstoqueDAO;
import Modelo.EstoqueModel;
import Modelo.ItemOrcamento;
import Modelo.ItemVenda;
//import Modelo.ItemVenda;
import java.awt.Color;
import java.awt.Component;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author pedro
 */
public class cellRendererOrcamento extends DefaultTableCellRenderer{
   
    List<ItemVenda>listaItemVenda;
    EstoqueModel estoque;
    JTable tabela;
    Color defaultBackground, defaultForeground;
    cellRendererOrcamento(){
   
    }
    
    cellRendererOrcamento(List itemVenda){
        listaItemVenda = itemVenda;
        //his.tabela = tabela;
        verificaEstoque();
        this.defaultBackground = getBackground();
        this.defaultForeground = getForeground();
    }
    
    public void verificaEstoque(){
     EstoqueModel estoque = new EstoqueModel();
     EstoqueDAO estoqueDao = new EstoqueDAO();
     
     
     for(ItemVenda i: this.listaItemVenda){
         estoque = i.getEstoque();
  
        try {
            estoque = estoqueDao.pesquisaEstoqueCodigoBarras(estoque.getProduto().getCodigoBarra());
            this.estoque=estoque;
           
        } catch (SQLException ex) {
            Logger.getLogger(cellRendererOrcamento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(cellRendererOrcamento.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(cellRendererOrcamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           }
    }
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
         
         final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);       
            String s = table.getModel().getValueAt(row, column).toString();
            
            for(ItemVenda i: this.listaItemVenda){ 
                if(i.getQuantidade() > this.estoque.getQtdEstoque()){

                    c.setBackground(Color.red);
                    c.setForeground(Color.white);

                }else{

                    c.setBackground(defaultBackground);
                    c.setForeground(defaultForeground); 

                }
            }
           return c; 
         
    }
   
    
}
