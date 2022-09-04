/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Control.Exceptions.NaoEncontradoException;
import Modelo.CompraModel;
import Modelo.EstoqueModel;
import Modelo.ItemCompra;
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

public class ItemCompraDAO {
      public void inserir(ItemCompra itemCompra) throws SQLException {
         try {
             
            java.sql.Connection conexao = Conexao.getConexao();
                                                
            String comandoSQL = "insert into item_compra(item_descricao,"
                    + " item_quantidade, item_valor, "
                    + "item_unidade_medida,"
                    + "item_data_hora, item_status,"
                    + "item_nota_fiscal, item_quantidade_verificacao,  produto_pro_id, compra_comp_id)"
                    + "values(?, ?, ?, ?, ?, ?, ?,?,?, ?)";

            PreparedStatement execSQL;
            execSQL = conexao.prepareStatement(comandoSQL);

            execSQL.setString(1, itemCompra.getDescricao());
            execSQL.setDouble(2, itemCompra.getQuantidade());
            execSQL.setDouble(3, itemCompra.getValorUnitario());
            execSQL.setString(4, itemCompra.getUnidadeMedida());
            execSQL.setTimestamp(5, itemCompra.getDataHora());
            execSQL.setBoolean(6, itemCompra.getStatus());
            execSQL.setString(7, itemCompra.getNotaFiscal());
            execSQL.setDouble(8, itemCompra.getQuantidadeVerificacao());
            execSQL.setInt(9, itemCompra.getProduto().getId());
            execSQL.setInt(10, itemCompra.getCompra().getId());
            
            
            execSQL.executeUpdate();

            conexao.commit();

            execSQL.close();

        } catch (SQLException ex) {
          
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o cadastro de itens de compra ", "ERRO ao realizar Compra",
                    JOptionPane.ERROR_MESSAGE);
            
        }    
}
      
public ArrayList<ItemCompra> findAll () throws SQLException, NaoEncontradoException, ParseException{
    
    ArrayList<ItemCompra>itemCompras = new ArrayList<>();
        //try {
        Connection conexao = Conexao.getConexao();

        
        String comandoSQL = "SELECT * FROM item_compra";

        PreparedStatement execSQL;
        
        execSQL = conexao.prepareStatement(comandoSQL);
        
        ResultSet resultadoConsulta;
      
        resultadoConsulta = execSQL.executeQuery();
        
        ProdutoModel produto;
        ItemCompra itemCompra;
        CompraModel compra;
        
        while (resultadoConsulta.next()) {
            CompraDAO compraDao = new CompraDAO();
            ProdutoDAO produtoDao = new ProdutoDAO();
            
            produto = new ProdutoModel();
            itemCompra = new ItemCompra();
            compra = new CompraModel();
            
            produto = produtoDao.pesquisaId(resultadoConsulta.getInt("produto_pro_id"));
            compra = compraDao.pesquisarId(resultadoConsulta.getInt("compra_comp_id"));
            
            itemCompra.setId(resultadoConsulta.getInt("item_compra_id"));
            itemCompra.setDescricao(resultadoConsulta.getString("item_descricao"));
            itemCompra.setQuantidade(resultadoConsulta.getDouble("item_quantidade"));
            itemCompra.setUnidadeMedida(resultadoConsulta.getString("item_unidade_medida"));
            itemCompra.setValorUnitario(resultadoConsulta.getDouble("item_valor"));
            itemCompra.setDataHora(resultadoConsulta.getTimestamp("item_data_hora"));
            itemCompra.setStatus(resultadoConsulta.getBoolean("item_status"));
            itemCompra.setNotaFiscal(resultadoConsulta.getString("item_nota_fiscal"));
            itemCompra.setQuantidadeVerificacao(resultadoConsulta.getDouble("item_quantidade_verificacao"));
            itemCompra.setCompra(compra);
            itemCompra.setProduto(produto);
            itemCompras.add(itemCompra);
            
        }
        if (itemCompras.isEmpty()) {
            throw new NaoEncontradoException("Não foi possivel encontrar venda");
        }
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

   return itemCompras;
    
}
public void alterar(ItemCompra itemCompra) throws SQLException, SQLException, SQLException{
        
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("ID "+itemCompra.getId());
        System.out.println("DESCRICAO "+itemCompra.getDescricao());
        System.out.println("NOTA FISCAL "+itemCompra.getNotaFiscal());
        System.out.println("UNIDADE MEDIDA "+itemCompra.getUnidadeMedida());
        System.out.println("COMPRA "+itemCompra.getCompra());
        System.out.println("DATA HORA "+itemCompra.getDataHora());
        System.out.println("ESTOQUE "+itemCompra.getProduto());
        System.out.println("QUANTIDADE "+itemCompra.getQuantidade());
        System.out.println("STATUS "+itemCompra.getStatus());
        System.out.println("VALOR UNITARIO "+itemCompra.getValorUnitario());
        System.out.println("-------------------------------------------");
        
        
        
        String sql = "UPDATE item_compra SET item_descricao = ?,"
                        + " item_quantidade = ? , item_valor = ?, "
                        + "item_unidade_medida = ?,"
                        + "item_data_hora = ?, item_status = ?,"
                        + "item_nota_fiscal = ?, item_quantidade_verificacao = ?, produto_pro_id = ? , compra_comp_id = ?  WHERE item_compra_id = ? ";

        Connection conexao;
        PreparedStatement execSQL;

        conexao = Conexao.getConexao();
        execSQL = conexao.prepareStatement(sql);

        execSQL.setString(1, itemCompra.getDescricao());
        execSQL.setDouble(2, itemCompra.getQuantidade());
        execSQL.setDouble(3, itemCompra.getValorUnitario());
        execSQL.setString(4, itemCompra.getUnidadeMedida());
        execSQL.setTimestamp(5, itemCompra.getDataHora());
        execSQL.setBoolean(6, itemCompra.getStatus());
        execSQL.setString(7, itemCompra.getNotaFiscal());
        execSQL.setDouble(8, itemCompra.getQuantidadeVerificacao());
        execSQL.setInt(9, itemCompra.getProduto().getId());
        execSQL.setInt(10, itemCompra.getCompra().getId());
        execSQL.setInt(11, itemCompra.getId());

        execSQL.executeUpdate();
        conexao.commit();
        execSQL.close();
        conexao.close();


}      
    
public ArrayList<ItemCompra> pesquisarItemCompra(int idCompra) throws SQLException, NaoEncontradoException, ParseException{
      
        ArrayList<ItemCompra>itemCompras = new ArrayList<>();
        //try {
        Connection conexao = Conexao.getConexao();

        PreparedStatement execSQL;
        
        execSQL = conexao.prepareStatement("SELECT * FROM item_compra WHERE compra_comp_id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setInt(1, idCompra);
        ResultSet resultadoConsulta;
        
        resultadoConsulta = execSQL.executeQuery();
        
        ProdutoModel produto;
        ItemCompra itemCompra;
        CompraModel compra;
        
        while (resultadoConsulta.next()) {
            CompraDAO compraDao = new CompraDAO();
            ProdutoDAO produtoDao = new ProdutoDAO();
            
            produto = new ProdutoModel();
            itemCompra = new ItemCompra();
            compra = new CompraModel();
            
            produto = produtoDao.pesquisaId(resultadoConsulta.getInt("produto_pro_id"));
            compra = compraDao.pesquisarId(resultadoConsulta.getInt("compra_comp_id"));
            
            itemCompra.setId(resultadoConsulta.getInt("item_compra_id"));
            itemCompra.setDescricao(resultadoConsulta.getString("item_descricao"));
            itemCompra.setQuantidade(resultadoConsulta.getDouble("item_quantidade"));
            itemCompra.setUnidadeMedida(resultadoConsulta.getString("item_unidade_medida"));
            itemCompra.setValorUnitario(resultadoConsulta.getDouble("item_valor"));
            itemCompra.setDataHora(resultadoConsulta.getTimestamp("item_data_hora"));
            itemCompra.setStatus(resultadoConsulta.getBoolean("item_status"));
            itemCompra.setNotaFiscal(resultadoConsulta.getString("item_nota_fiscal"));
            itemCompra.setQuantidadeVerificacao(resultadoConsulta.getDouble("item_quantidade_verificacao"));
            itemCompra.setCompra(compra);
            itemCompra.setProduto(produto);
            itemCompras.add(itemCompra);
            
        }
        if (itemCompras.isEmpty()) {
            throw new NaoEncontradoException("Não foi possivel encontrar venda");
        }
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

   return itemCompras;
  }
    
}
