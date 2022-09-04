/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Control.Exceptions.NaoEncontradoException;
import static DAO.Conexao.getConexao;
import Modelo.ClienteModel;
import Modelo.CompraModel;
import Modelo.ContaPagarModel;
import Modelo.FornecedorModel;
import Modelo.FuncionarioModel;
import Modelo.OrcamentoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author pedro
 */
public class ContasPagarDAO {
    
      public void inserir(ContaPagarModel contaPagar) throws SQLException {
          
        try (java.sql.Connection conexao = Conexao.getConexao()) {

            String comandoSQL = "insert into conta_pagar (pag_descricao, pag_vencimento, pag_data, pag_data_cadastro,"
                    + " pag_valor, pag_situacao, pag_categoria, fornecedor_for_id)"
                    + " values(?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement execSQL;
            execSQL = conexao.prepareStatement(comandoSQL);

            execSQL.setString(1, contaPagar.getDescricao());
            execSQL.setDate(2, contaPagar.getVencimento());
            execSQL.setDate(3, contaPagar.getData());
            execSQL.setTimestamp(4, contaPagar.getDataCadastro());
            execSQL.setDouble(5, contaPagar.getValor());
            execSQL.setString(6, contaPagar.getSituacao());
            execSQL.setString(7, contaPagar.getCategoria());
          
            execSQL.setInt(8, contaPagar.getFornecedor().getId());
          
            execSQL.executeUpdate();

            conexao.commit();

            execSQL.close();

            JOptionPane.showMessageDialog(null, "Conta adicionada!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o cadastro de Conta a Pagar ", "ERRO ao cadastrar",
                    JOptionPane.ERROR_MESSAGE);
            
        }

    }
    
    public ContaPagarModel pesquisarID(int id) throws SQLException, NaoEncontradoException{
    Connection conexao = getConexao();
        int codigo, codigoFornecedor;
        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM conta_pagar WHERE pag_id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setInt(1, id);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();
        
        resultadoConsulta.last();
        
        ContaPagarModel contaPagar = new ContaPagarModel();
        FornecedorModel fornecedor = new FornecedorModel();
  
        if (resultadoConsulta.getRow() > 0) {
         
                codigoFornecedor = resultadoConsulta.getInt("fornecedor_for_id");
                
                FornecedorDAO fornecedorDao = new FornecedorDAO();
                fornecedor = fornecedorDao.findByID(codigoFornecedor);
           
                contaPagar.setId(resultadoConsulta.getInt("pag_id"));
                contaPagar.setDescricao(resultadoConsulta.getString("pag_descricao"));
                contaPagar.setData(resultadoConsulta.getDate("pag_data"));
                contaPagar.setDataCadastro(resultadoConsulta.getTimestamp("pag_data_cadastro"));
               
                contaPagar.setFornecedor(fornecedor);
                contaPagar.setCategoria(resultadoConsulta.getString("pag_categoria"));
                contaPagar.setSituacao(resultadoConsulta.getString("pag_situacao"));
                contaPagar.setValor(resultadoConsulta.getDouble("pag_valor"));
                contaPagar.setVencimento(resultadoConsulta.getDate("pag_vencimento"));
         
        }
        
        return contaPagar;
    }
    
    public void pagarConta(ContaPagarModel conta) throws SQLException{
        String sql = "UPDATE conta_pagar SET pag_descricao=?, pag_vencimento=?, pag_data=?, pag_data_cadastro=?,"
                    + " pag_valor=?, pag_situacao=?, pag_categoria=?, fornecedor_for_id=?"
                + " WHERE pag_id = ? ";
            Connection conexao;
            PreparedStatement execSQL;
            conexao = Conexao.getConexao();
            execSQL = conexao.prepareStatement(sql);
            
            execSQL.setString(1, conta.getDescricao());
            execSQL.setDate(2, conta.getVencimento());
            execSQL.setDate(3, conta.getData());
            execSQL.setTimestamp(4, conta.getDataCadastro());
            execSQL.setDouble(5, conta.getValor());
            execSQL.setString(6, conta.getSituacao());
            execSQL.setString(7, conta.getCategoria());
       
            execSQL.setInt(8, conta.getFornecedor().getId());
            execSQL.setInt(9, conta.getId());
            
            execSQL.executeUpdate();

            conexao.commit();
            execSQL.close();
            conexao.close();
    }
    
    public ArrayList<ContaPagarModel> findAll() throws SQLException, NaoEncontradoException {
    
        int codigo, codigoFornecedor;

        ArrayList<ContaPagarModel> listaContaPagar = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();

            String comandoSQL = "SELECT * FROM conta_pagar";

            PreparedStatement execSQL;

            execSQL = conexao.prepareStatement(comandoSQL);

            ResultSet resultadoConsulta;

            resultadoConsulta = execSQL.executeQuery();
            ContaPagarModel contaPagar;
            FornecedorModel fornecedor;
  
            while (resultadoConsulta.next()) {

                contaPagar = new ContaPagarModel();
                fornecedor = new FornecedorModel();
                
                codigoFornecedor = resultadoConsulta.getInt("fornecedor_for_id");
                
                FornecedorDAO fornecedorDao = new FornecedorDAO();
                fornecedor = fornecedorDao.findByID(codigoFornecedor);
           
                contaPagar.setId(resultadoConsulta.getInt("pag_id"));
                contaPagar.setDescricao(resultadoConsulta.getString("pag_descricao"));
                contaPagar.setData(resultadoConsulta.getDate("pag_data"));
                contaPagar.setDataCadastro(resultadoConsulta.getTimestamp("pag_data_cadastro"));
              
                contaPagar.setFornecedor(fornecedor);
                contaPagar.setCategoria(resultadoConsulta.getString("pag_categoria"));
                contaPagar.setSituacao(resultadoConsulta.getString("pag_situacao"));
                contaPagar.setValor(resultadoConsulta.getDouble("pag_valor"));
                contaPagar.setVencimento(resultadoConsulta.getDate("pag_vencimento"));
               
                listaContaPagar.add(contaPagar);
            }
            resultadoConsulta.close();
            execSQL.close();
            conexao.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaContaPagar;
    }
    
    public ArrayList<ContaPagarModel> pesquisarGraficoPorAnoMes(String anoMes) throws SQLException, NaoEncontradoException {
    
        int codigo, codigoFornecedor;

        ArrayList<ContaPagarModel> listaContaPagar = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();

            String comandoSQL = "  SELECT pag_id, pag_data,pag_descricao,pag_data_cadastro,pag_categoria,pag_situacao,"
                    + " sum(pag_valor), pag_vencimento, fornecedor_for_id FROM conta_pagar where pag_data LIKE ? GROUP BY pag_data";
            
            PreparedStatement execSQL;
           
            execSQL = conexao.prepareStatement(comandoSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            execSQL.setString(1, '%' + anoMes + '%');
            
            ResultSet resultadoConsulta;

            resultadoConsulta = execSQL.executeQuery();
            ContaPagarModel contaPagar;
            FornecedorModel fornecedor;
  
            while (resultadoConsulta.next()) {

                contaPagar = new ContaPagarModel();
                fornecedor = new FornecedorModel();
                
                codigoFornecedor = resultadoConsulta.getInt("fornecedor_for_id");
                
                FornecedorDAO fornecedorDao = new FornecedorDAO();
                fornecedor = fornecedorDao.findByID(codigoFornecedor);
           
                contaPagar.setId(resultadoConsulta.getInt("pag_id"));
                contaPagar.setDescricao(resultadoConsulta.getString("pag_descricao"));
                contaPagar.setData(resultadoConsulta.getDate("pag_data"));
                contaPagar.setDataCadastro(resultadoConsulta.getTimestamp("pag_data_cadastro"));
            
                contaPagar.setFornecedor(fornecedor);
                contaPagar.setCategoria(resultadoConsulta.getString("pag_categoria"));
                contaPagar.setSituacao(resultadoConsulta.getString("pag_situacao"));
                contaPagar.setValor(resultadoConsulta.getDouble("sum(pag_valor)"));
                contaPagar.setVencimento(resultadoConsulta.getDate("pag_vencimento"));
               
                listaContaPagar.add(contaPagar);
            }
            resultadoConsulta.close();
            execSQL.close();
            conexao.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaContaPagar;
    }
    
    public ArrayList<ContaPagarModel> pesquisarPorCodigo(String codigo) throws SQLException, NaoEncontradoException {
          ArrayList<ContaPagarModel> listaContaPagar = new ArrayList<>();
        try {
            int codigoFornecedor;
            ResultSet resultadoConsulta;
            Connection conexao = Conexao.getConexao();
            PreparedStatement execSQL;

            execSQL = conexao.prepareStatement("SELECT * FROM conta_pagar where pag_id LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            execSQL.setString(1, '%' + codigo + '%');
            resultadoConsulta = execSQL.executeQuery();
            
            CompraModel compra;
            ContaPagarModel contaPagar;
            FornecedorModel fornecedor;
            
            while (resultadoConsulta.next()) {

                contaPagar = new ContaPagarModel();
                fornecedor = new FornecedorModel();
                
                codigoFornecedor = resultadoConsulta.getInt("fornecedor_for_id");
                
                FornecedorDAO fornecedorDao = new FornecedorDAO();
                fornecedor = fornecedorDao.findByID(codigoFornecedor);
           
                contaPagar.setId(resultadoConsulta.getInt("pag_id"));
                contaPagar.setDescricao(resultadoConsulta.getString("pag_descricao"));
                contaPagar.setData(resultadoConsulta.getDate("pag_data"));
                contaPagar.setDataCadastro(resultadoConsulta.getTimestamp("pag_data_cadastro"));
   
                contaPagar.setFornecedor(fornecedor);
                contaPagar.setCategoria(resultadoConsulta.getString("pag_categoria"));
                contaPagar.setSituacao(resultadoConsulta.getString("pag_situacao"));
                contaPagar.setValor(resultadoConsulta.getDouble("pag_valor"));
                contaPagar.setVencimento(resultadoConsulta.getDate("pag_vencimento"));
               
                listaContaPagar.add(contaPagar);
            }
            resultadoConsulta.close();
            execSQL.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaContaPagar;
    
    }
    public ArrayList<ContaPagarModel> pesquisarPorNomeFornecedor(String nome) throws SQLException, NaoEncontradoException {
          ArrayList<ContaPagarModel> listaContaPagar = new ArrayList<>();
        try {
            int codigoFornecedor;
            ResultSet resultadoConsulta;
            Connection conexao = Conexao.getConexao();
            PreparedStatement execSQL;

            execSQL = conexao.prepareStatement("SELECT * FROM conta_pagar inner join fornecedor where for_nome LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            execSQL.setString(1, '%' + nome + '%');
            resultadoConsulta = execSQL.executeQuery();
            
            CompraModel compra;
            ContaPagarModel contaPagar;
            FornecedorModel fornecedor;
            
            while (resultadoConsulta.next()) {

                contaPagar = new ContaPagarModel();
                fornecedor = new FornecedorModel();
                
                codigoFornecedor = resultadoConsulta.getInt("fornecedor_for_id");
                
                FornecedorDAO fornecedorDao = new FornecedorDAO();
                fornecedor = fornecedorDao.findByID(codigoFornecedor);
           
                contaPagar.setId(resultadoConsulta.getInt("pag_id"));
                contaPagar.setDescricao(resultadoConsulta.getString("pag_descricao"));
                contaPagar.setData(resultadoConsulta.getDate("pag_data"));
                contaPagar.setDataCadastro(resultadoConsulta.getTimestamp("pag_data_cadastro"));
            
                contaPagar.setFornecedor(fornecedor);
                contaPagar.setCategoria(resultadoConsulta.getString("pag_categoria"));
                contaPagar.setSituacao(resultadoConsulta.getString("pag_situacao"));
                contaPagar.setValor(resultadoConsulta.getDouble("pag_valor"));
                contaPagar.setVencimento(resultadoConsulta.getDate("pag_vencimento"));
               
                listaContaPagar.add(contaPagar);
            }
            resultadoConsulta.close();
            execSQL.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaContaPagar;
    
    }
    
    
    
    public String excluir(ContaPagarModel contaPagar) throws SQLException{
        
        Connection conexao = Conexao.getConexao();
        int linhas;
       
      
        String comandoSQL = "DELETE FROM conta_pagar WHERE pag_id = ?";

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement(comandoSQL);

        execSQL.setInt(1, contaPagar.getId());

        linhas = execSQL.executeUpdate();

        conexao.commit();

        execSQL.close();

        conexao.close();

        if (linhas <= 0) {
            throw new SQLException("Não foi possivel remover a Conta");
        }

        return "Conta Removida!";
   
    }
}
