/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Control.Exceptions.NaoEncontradoException;
import Modelo.EstoqueModel;
import Modelo.ItemVenda;
import Modelo.ProdutoModel;
import Modelo.VendaModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author pedro
 */
public class ItemVendaDAO {
    public void inserir(ItemVenda itemVenda) throws SQLException {
         try {
             System.out.println("INSERE VENDA DO ORÇAMENTO");
             
            java.sql.Connection conexao = Conexao.getConexao();

            String comandoSQL = "insert into item_venda (item_descricao, item_valor_unitario, item_quantidade, item_unidade_medida, venda_ven_id, estoque_est_id)"
                    + "values(?, ?, ?, ?, ?, ?)";

            PreparedStatement execSQL;
            execSQL = conexao.prepareStatement(comandoSQL);

            execSQL.setString(1, itemVenda.getDescricao());
            execSQL.setDouble(2, itemVenda.getValor());
            execSQL.setDouble(3, itemVenda.getQuantidade());
            execSQL.setString(4, itemVenda.getUnidadeMedida());
            execSQL.setInt(5, itemVenda.getVenda().getId());
            execSQL.setInt(6, itemVenda.getEstoque().getId());
            

            execSQL.executeUpdate();

            conexao.commit();

            execSQL.close();

        } catch (SQLException ex) {
             System.err.println("teste1");
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o cadastro de itens de venda ", "ERRO ao realizar Venda",
                    JOptionPane.ERROR_MESSAGE);
            
        }    
}
    
public ArrayList<ItemVenda> pesquisarItemVenda(int idVenda) throws SQLException, NaoEncontradoException, ParseException{
  
        System.err.println("ID VENDA DO ITEM VENDA"+ idVenda);
        ArrayList<ItemVenda>listaVenda = new ArrayList<>();
        //try {
        Connection conexao = Conexao.getConexao();

        PreparedStatement execSQL;
        
        execSQL = conexao.prepareStatement("SELECT * FROM item_venda WHERE venda_ven_id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setInt(1, idVenda);
        ResultSet resultadoConsulta;
        
        resultadoConsulta = execSQL.executeQuery();
        
        EstoqueModel estoque;
        ItemVenda itemVenda;
        VendaModel venda;
        
        while (resultadoConsulta.next()) {
            VendaDAO vendaDao = new VendaDAO();
            EstoqueDAO estoqueDao = new EstoqueDAO();
            
            estoque = new EstoqueModel();
            itemVenda = new ItemVenda();
            venda = new VendaModel();
            
            estoque = estoqueDao.findByID(resultadoConsulta.getInt("estoque_est_id"));
            
            venda = vendaDao.pesquisarId(resultadoConsulta.getInt("venda_ven_id"));
            
            itemVenda.setId(resultadoConsulta.getInt("item_id"));
            itemVenda.setDescricao(resultadoConsulta.getString("item_descricao"));
            itemVenda.setQuantidade(resultadoConsulta.getDouble("item_quantidade"));
            itemVenda.setUnidadeMedida(resultadoConsulta.getString("item_unidade_medida"));
            itemVenda.setValor(resultadoConsulta.getDouble("item_valor_unitario"));
            itemVenda.setVenda(venda);
            itemVenda.setEstoque(estoque);
            listaVenda.add(itemVenda);
            
        }
        if (listaVenda.isEmpty()) {
            throw new NaoEncontradoException("Não foi possivel encontrar venda");
        }
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

   return listaVenda;
  }
    
}
