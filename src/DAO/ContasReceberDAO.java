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
import Modelo.ContaReceberModel;
import Modelo.FornecedorModel;
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
public class ContasReceberDAO {
    
    
    public ArrayList<ContaReceberModel>findAll() throws SQLException, NaoEncontradoException {
    
        int codigo, codigoCliente;

        ArrayList<ContaReceberModel> listaContaReceber = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();

            String comandoSQL = "SELECT * FROM conta_receber";

            PreparedStatement execSQL;
            
            execSQL = conexao.prepareStatement(comandoSQL);

            ResultSet resultadoConsulta;

            resultadoConsulta = execSQL.executeQuery();
            ContaReceberModel contaReceber;
            ClienteModel cliente;
  
            while (resultadoConsulta.next()) {

                contaReceber = new ContaReceberModel();
                cliente = new ClienteModel();
                
                codigoCliente = resultadoConsulta.getInt("cliente_clie_id");
                
                ClienteDAO clienteDao = new ClienteDAO();
                cliente = clienteDao.findByID(codigoCliente);
           
                contaReceber.setId(resultadoConsulta.getInt("rec_Id"));
                contaReceber.setDataCompra(resultadoConsulta.getTimestamp("rec_data"));
                contaReceber.setDescricao(resultadoConsulta.getString("rec_descricao"));
                contaReceber.setCliente(cliente);
                contaReceber.setSituacao(resultadoConsulta.getString("rec_situacao"));
                contaReceber.setValor(resultadoConsulta.getDouble("rec_valor"));
                contaReceber.setVencimento(resultadoConsulta.getDate("rec_vencimento"));
                listaContaReceber.add(contaReceber);
               
            }
            resultadoConsulta.close();
            execSQL.close();
            conexao.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaContaReceber;
    }
    
    public void receberConta(ContaReceberModel receber) throws SQLException{
    String sql = "UPDATE conta_receber SET rec_descricao=?, rec_vencimento=?,rec_data=?, rec_valor=?, rec_situacao=?,"
            + "cliente_clie_id=?  WHERE rec_id = ? ";
            
            Connection conexao;
            PreparedStatement execSQL;
            conexao = Conexao.getConexao();
            execSQL = conexao.prepareStatement(sql);
            
            execSQL.setString(1, receber.getDescricao());
            execSQL.setDate(2, receber.getVencimento());
            execSQL.setTimestamp(3, receber.getDataCompra());
            execSQL.setDouble(4, receber.getValor());
            execSQL.setString(5, receber.getSituacao());
          
            execSQL.setInt(6, receber.getCliente().getId());
            execSQL.setInt(7, receber.getId());
            
            execSQL.executeUpdate();

            conexao.commit();
            execSQL.close();
            conexao.close();
    }
    public void inserir(ContaReceberModel contaReceber) throws SQLException {
          
        try (java.sql.Connection conexao = Conexao.getConexao()) {
            
            String comandoSQL = "insert into conta_receber (rec_descricao, rec_vencimento, rec_data,"
                    + " rec_valor, rec_situacao, cliente_clie_id)"
                    + " values(?, ?, ?, ?, ?, ?)";

            PreparedStatement execSQL;
            execSQL = conexao.prepareStatement(comandoSQL);

            execSQL.setString(1, contaReceber.getDescricao());
            execSQL.setDate(2, contaReceber.getVencimento());
            execSQL.setTimestamp(3, contaReceber.getDataCompra());
            execSQL.setDouble(4, contaReceber.getValor());
            execSQL.setString(5, contaReceber.getSituacao());
            execSQL.setInt(6, contaReceber.getCliente().getId());
            
            execSQL.executeUpdate();

            conexao.commit();

            execSQL.close();

            JOptionPane.showMessageDialog(null, "Conta adicionada!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o cadastro de Conta a Receber ", "ERRO ao cadastrar",
                    JOptionPane.ERROR_MESSAGE);
            
        }

    }
    public ContaReceberModel pesquisarID(int id) throws SQLException, NaoEncontradoException{
    
        Connection conexao = getConexao();
        int codigo, codigoCliente;
        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM conta_receber WHERE rec_id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setInt(1, id);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();
        
        resultadoConsulta.last();
        
        ContaReceberModel contaReceber = new ContaReceberModel();
        ClienteModel cliente = new ClienteModel();
  
        if (resultadoConsulta.getRow() > 0) {
         
                codigoCliente = resultadoConsulta.getInt("cliente_clie_id");
                
                ClienteDAO clienteDao = new ClienteDAO();
                cliente = clienteDao.findByID(codigoCliente);
           
                contaReceber.setId(resultadoConsulta.getInt("rec_Id"));
                contaReceber.setDataCompra(resultadoConsulta.getTimestamp("rec_data"));
                contaReceber.setDescricao(resultadoConsulta.getString("rec_descricao"));
                contaReceber.setCliente(cliente);
                
                contaReceber.setSituacao(resultadoConsulta.getString("rec_situacao"));
                contaReceber.setValor(resultadoConsulta.getDouble("rec_valor"));
                contaReceber.setVencimento(resultadoConsulta.getDate("rec_vencimento"));
         
        }
        
        return contaReceber;
    }
    
     public ArrayList<ContaReceberModel> pesquisarPorCodigo(String codigo) throws SQLException, NaoEncontradoException {
          ArrayList<ContaReceberModel> listaContaReceber = new ArrayList<>();
        try {
            int codigoCliente;
            ResultSet resultadoConsulta;
            Connection conexao = Conexao.getConexao();
            PreparedStatement execSQL;

            execSQL = conexao.prepareStatement("SELECT * FROM conta_receber where rec_id LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            execSQL.setString(1, '%' + codigo + '%');
            resultadoConsulta = execSQL.executeQuery();
            
            CompraModel compra;
            ContaReceberModel contaReceber;
            ClienteModel cliente;
            
            while (resultadoConsulta.next()) {

                contaReceber = new ContaReceberModel();
                cliente = new ClienteModel();
                
                codigoCliente = resultadoConsulta.getInt("cliente_clie_id");
                
                ClienteDAO clienteDao = new ClienteDAO();
                cliente = clienteDao.findByID(codigoCliente);
           
                contaReceber.setId(resultadoConsulta.getInt("rec_Id"));
                contaReceber.setDataCompra(resultadoConsulta.getTimestamp("rec_data"));
                contaReceber.setDescricao(resultadoConsulta.getString("rec_descricao"));
                contaReceber.setCliente(cliente);
                contaReceber.setSituacao(resultadoConsulta.getString("rec_situacao"));
                contaReceber.setValor(resultadoConsulta.getDouble("rec_valor"));
                contaReceber.setVencimento(resultadoConsulta.getDate("rec_vencimento"));
                listaContaReceber.add(contaReceber);
               
            }
            resultadoConsulta.close();
            execSQL.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaContaReceber;
    
    }
     
     public ArrayList<ContaReceberModel> pesquisarPorNomeCliente(String nome) throws SQLException, NaoEncontradoException {
          ArrayList<ContaReceberModel> listaContaReceber = new ArrayList<>();
          
        try {
            int codigoCliente;
            ResultSet resultadoConsulta;
            Connection conexao = Conexao.getConexao();
            PreparedStatement execSQL;

            execSQL = conexao.prepareStatement("SELECT * FROM conta_receber inner join cliente where clie_nome LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            execSQL.setString(1, '%' + nome + '%');
            resultadoConsulta = execSQL.executeQuery();
            
            CompraModel compra;
            ContaReceberModel contaReceber;
            ClienteModel cliente;
            while (resultadoConsulta.next()) {

                contaReceber = new ContaReceberModel();
                cliente = new ClienteModel();
                
                codigoCliente = resultadoConsulta.getInt("cliente_clie_id");
                
                ClienteDAO clienteDao = new ClienteDAO();
                cliente = clienteDao.findByID(codigoCliente);
           
                contaReceber.setId(resultadoConsulta.getInt("rec_Id"));
                contaReceber.setDataCompra(resultadoConsulta.getTimestamp("rec_data"));
                contaReceber.setDescricao(resultadoConsulta.getString("rec_descricao"));
                contaReceber.setCliente(cliente);
                contaReceber.setSituacao(resultadoConsulta.getString("rec_situacao"));
                contaReceber.setValor(resultadoConsulta.getDouble("rec_valor"));
                contaReceber.setVencimento(resultadoConsulta.getDate("rec_vencimento"));
                listaContaReceber.add(contaReceber);
               
            }
            resultadoConsulta.close();
            execSQL.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaContaReceber;
    
    }
     
     public ArrayList<ContaReceberModel> pesquisarPorCpf(String cpf) throws SQLException, NaoEncontradoException {
          ArrayList<ContaReceberModel> listaContaReceber = new ArrayList<>();
          
        try {
            int codigoCliente;
            ResultSet resultadoConsulta;
            Connection conexao = Conexao.getConexao();
            PreparedStatement execSQL;

            execSQL = conexao.prepareStatement("SELECT * FROM conta_receber inner join cliente where clie_cpf LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            execSQL.setString(1, '%' + cpf + '%');
            resultadoConsulta = execSQL.executeQuery();
            
            CompraModel compra;
            ContaReceberModel contaReceber;
            ClienteModel cliente;
            while (resultadoConsulta.next()) {

                contaReceber = new ContaReceberModel();
                cliente = new ClienteModel();
                
                codigoCliente = resultadoConsulta.getInt("cliente_clie_id");
                
                ClienteDAO clienteDao = new ClienteDAO();
                cliente = clienteDao.findByID(codigoCliente);
           
                contaReceber.setId(resultadoConsulta.getInt("rec_Id"));
                contaReceber.setDataCompra(resultadoConsulta.getTimestamp("rec_data"));
                contaReceber.setDescricao(resultadoConsulta.getString("rec_descricao"));
                contaReceber.setCliente(cliente);
                contaReceber.setSituacao(resultadoConsulta.getString("rec_situacao"));
                contaReceber.setValor(resultadoConsulta.getDouble("rec_valor"));
                contaReceber.setVencimento(resultadoConsulta.getDate("rec_vencimento"));
                listaContaReceber.add(contaReceber);
               
            }
            resultadoConsulta.close();
            execSQL.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaContaReceber;
    
    }
     public String excluir(ContaReceberModel contaReceber) throws SQLException{
        
        Connection conexao = Conexao.getConexao();
        int linhas;
       
      
        String comandoSQL = "DELETE FROM Conta_receber WHERE rec_id = ?";

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement(comandoSQL);

        execSQL.setInt(1, contaReceber.getId());

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
