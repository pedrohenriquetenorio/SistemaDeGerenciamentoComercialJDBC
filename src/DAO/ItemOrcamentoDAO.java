/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Control.Exceptions.NaoEncontradoException;
import Modelo.ItemOrcamento;
import Modelo.OrcamentoModel;
import Modelo.ProdutoModel;
import Modelo.VendaModel;
import DAO.OrcamentoDAO;
import Modelo.EstoqueModel;
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
public class ItemOrcamentoDAO {
      public void inserir(ItemOrcamento itemOrcamento) throws SQLException {
         try {
             System.out.println("ITEM ORCAMENTO ESTOQUE "+ itemOrcamento.getEstoque()); 
             System.out.println("ITEM ORCAMENTO UNIDADE "+ itemOrcamento.getUnidadeMedida()); 
             System.out.println("ITEM ORCAMENTO DESCRICAO "+ itemOrcamento.getDescricao()); 
             System.out.println("ITEM ORCAMENTO ORCAMENTO "+ itemOrcamento.getOrcamento()); 
             System.out.println("ITEM ORCAMENTO QUANTIDADE "+ itemOrcamento.getQuantidade()); 
             System.out.println("ITEM ORCAMENTO VALOR "+ itemOrcamento.getValorUnitario()); 
             
            java.sql.Connection conexao = Conexao.getConexao();

            String comandoSQL = "insert into item_orcamento (item_descricao, item_valor_unitario, item_quantidade, "
                    + "item_unidade_medida, orcamento_orc_id, estoque_est_id)"
                    + "values(?, ?, ?, ?, ?, ?)";

            PreparedStatement execSQL;
            execSQL = conexao.prepareStatement(comandoSQL);

            execSQL.setString(1, itemOrcamento.getDescricao());
            execSQL.setDouble(2, itemOrcamento.getValorUnitario());
            execSQL.setDouble(3, itemOrcamento.getQuantidade());
            execSQL.setString(4, itemOrcamento.getUnidadeMedida());
            execSQL.setInt(5, itemOrcamento.getOrcamento().getOrc_id());
            execSQL.setInt(6, itemOrcamento.getEstoque().getId());
            
            execSQL.executeUpdate();

            conexao.commit();

            execSQL.close();

        } catch (SQLException ex) {
             System.err.println(ex); 
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o cadastro de itens de orçamento ", "ERRO ao realizar Orcamento",
                    JOptionPane.ERROR_MESSAGE);
            
        }    
}
    
public ArrayList<ItemOrcamento> pesquisarItemOrcamento(int idOrcamento) throws SQLException, NaoEncontradoException, ParseException{
  
        ArrayList<ItemOrcamento>listaOrcamento = new ArrayList<>();
        //try {
        Connection conexao = Conexao.getConexao();

        PreparedStatement execSQL;
        
        execSQL = conexao.prepareStatement("SELECT * FROM item_orcamento WHERE orcamento_orc_id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setInt(1, idOrcamento);
        ResultSet resultadoConsulta;
        
        resultadoConsulta = execSQL.executeQuery();
        
        EstoqueModel estoque;
        ItemOrcamento itemOrcamento;
        OrcamentoModel orcamento;
        
        while (resultadoConsulta.next()) {
            OrcamentoDAO orcamentoDao = new OrcamentoDAO();
            EstoqueDAO estoqueDao = new EstoqueDAO();
            
            estoque = new EstoqueModel();
            itemOrcamento = new ItemOrcamento();
            orcamento = new OrcamentoModel();
            
            estoque = estoqueDao.findByID(resultadoConsulta.getInt("estoque_est_id"));
            
            orcamento = orcamentoDao.pesquisarId(resultadoConsulta.getInt("orcamento_orc_id"));
            
            itemOrcamento.setId(resultadoConsulta.getInt("item_id"));
            itemOrcamento.setDescricao(resultadoConsulta.getString("item_descricao"));
            itemOrcamento.setQuantidade(resultadoConsulta.getDouble("item_quantidade"));
            itemOrcamento.setUnidadeMedida(resultadoConsulta.getString("item_unidade_medida"));
            itemOrcamento.setValorUnitario(resultadoConsulta.getDouble("item_valor_unitario"));
            itemOrcamento.setOrcamento(orcamento);
            itemOrcamento.setEstoque(estoque);
            listaOrcamento.add(itemOrcamento);
            
        }
        if (listaOrcamento.isEmpty()) {
            throw new NaoEncontradoException("Não foi possivel encontrar orcamento, Verifique o banco de Dados");
        }
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

   return listaOrcamento;
  }

    public void Alterar(ItemOrcamento itemOrcamento) throws SQLException{
    
        try {
            String sql = "UPDATE item_orcamento SET item_descricao = ?, item_valor_unitario = ? , item_quantidade = ?, "
                    + "item_unidade_medida = ?, orcamento_orc_id = ?, estoque_est_id = ? where item_id = ?"; 
            
            Connection conexao;
            PreparedStatement execSQL;
                 
            conexao = Conexao.getConexao();
            execSQL = conexao.prepareStatement(sql);

            execSQL.setString(1, itemOrcamento.getDescricao());
            execSQL.setDouble(2, itemOrcamento.getValorUnitario());
            execSQL.setDouble(3, itemOrcamento.getQuantidade());
            execSQL.setString(4, itemOrcamento.getUnidadeMedida());
            execSQL.setInt(5, itemOrcamento.getOrcamento().getOrc_id());
            execSQL.setInt(6, itemOrcamento.getEstoque().getId());
            execSQL.setInt(7, itemOrcamento.getId());
            execSQL.executeUpdate();

            conexao.commit();
            execSQL.close();
            conexao.close();
            
        } catch (SQLException se) {
            
            System.out.println("ERRO   " + se);
            throw se;

        }
    
    }
    
        
        //DELETE FROM `tcc_pedro_v12`.`item_orcamento` WHERE (`item_id` = '2') and (`orcamento_orc_id` = '6');
        
    public void excluir (int idItemOrcamento) throws SQLException{
       
       String idItemString = String.valueOf(idItemOrcamento);
      // String idOrcString = String.valueOf(idOrcamento);
        Connection conexao = Conexao.getConexao();
        int linhas;
        
        String comandoSQL = "delete FROM item_orcamento WHERE item_id = ?";

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement(comandoSQL);

        execSQL.setString(1, idItemString);
        
        linhas = execSQL.executeUpdate();

        conexao.commit();

        execSQL.close();

        conexao.close();

        if (linhas == 0) {
            //throw new NotExistException("Curso com a sigla " + sigla + " não existe");
        }
   
   
   
   }
    
    public void excluirItemOrcamento (int idOrcamento) throws SQLException{
       

      // String idOrcString = String.valueOf(idOrcamento);
        Connection conexao = Conexao.getConexao();
        int linhas;
        System.out.println("ID EXCLUIR DAO "+ idOrcamento);
        String comandoSQL = "delete FROM item_orcamento WHERE orcamento_orc_id = ?";
                            
        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement(comandoSQL);

        execSQL.setInt(1, idOrcamento);
        
        linhas = execSQL.executeUpdate();

        conexao.commit();

        execSQL.close();

        conexao.close();

        if (linhas == 0) {
            //throw new NotExistException("Curso com a sigla " + sigla + " não existe");
        }
   
   
   
   }
}
