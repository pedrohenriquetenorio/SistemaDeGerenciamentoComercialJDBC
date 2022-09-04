/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Control.Exceptions.NaoEncontradoException;
import static DAO.Conexao.getConexao;
import Modelo.CaixaModel;
import Modelo.ClienteModel;
import Modelo.CompraModel;
import Modelo.FornecedorModel;
import Modelo.FuncionarioModel;
import Modelo.ProdutoModel;
import Modelo.VendaModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author pedro
 */
public class CompraDAO {
    

    
    public void inserir(CompraModel compra){
         try (java.sql.Connection conexao = Conexao.getConexao()) {
           
            String comandoSQL = "insert into compra (comp_data, comp_hora, comp_valor, comp_status, funcionario_fun_id, fornecedor_for_id) values (?, ?,? ,?, ?, ?)";
        
            PreparedStatement execSQL;
            execSQL = conexao.prepareStatement(comandoSQL);

            execSQL.setDate(1, compra.getDataCompra());
            execSQL.setTime(2,compra.getHoraCompra());
            execSQL.setDouble(3, compra.getValor());
            execSQL.setString(4, compra.getStatus());
             execSQL.setInt(5, compra.getFuncionario().getId());
            execSQL.setInt(6, compra.getFornecedor().getId());
          
            execSQL.executeUpdate();

            conexao.commit();

            execSQL.close();
            
            JOptionPane.showMessageDialog(null, "Compra Realizada!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
             
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar a compra ", "Erro ao efetuar compra",
            JOptionPane.ERROR_MESSAGE);
            
        }
        
    }
    public ArrayList<CompraModel> pesquisarPorCodigo(String codigo) throws SQLException, NaoEncontradoException {
         ArrayList<CompraModel> listaCompra = new ArrayList<>();
        try {
            
            ResultSet resultadoConsulta;
            Connection conexao = Conexao.getConexao();
            PreparedStatement execSQL;

            execSQL = conexao.prepareStatement("SELECT * FROM compra where comp_id LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            execSQL.setString(1, '%' + codigo + '%');
            resultadoConsulta = execSQL.executeQuery();
            
            CompraModel compra;
            FornecedorModel fornecedor;
            FornecedorDAO fornecedorDao;
            FuncionarioDAO funcionarioDao;
            FuncionarioModel funcionario;
            
            while (resultadoConsulta.next()) {
                 fornecedor = new FornecedorModel();
            fornecedorDao = new FornecedorDAO();
            
            funcionario = new FuncionarioModel();
            funcionarioDao = new FuncionarioDAO();
            
            compra = new CompraModel();
            
            int fornecedorID = resultadoConsulta.getInt("fornecedor_for_id");
            fornecedor = fornecedorDao.findByID(fornecedorID);
            
            int funcionarioID = resultadoConsulta.getInt("funcionario_fun_id");
            funcionario = funcionarioDao.findByID(funcionarioID);
            
            compra.setId(resultadoConsulta.getInt("comp_id"));
            compra.setValor(resultadoConsulta.getDouble("comp_valor"));
            compra.setDataCompra(resultadoConsulta.getDate("comp_data"));
            compra.setHoraCompra(resultadoConsulta.getTime("comp_hora"));
            compra.setStatus(resultadoConsulta.getString("comp_status"));
            compra.setFornecedor(fornecedor);
            compra.setFuncionario(funcionario);
            listaCompra.add(compra);
            }
            resultadoConsulta.close();
            execSQL.close();
            conexao.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaCompra;
    
    }
    public ArrayList<CompraModel> finalizados() throws NaoEncontradoException{
        ArrayList<CompraModel> listaCompra = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();

            String comandoSQL = "SELECT * FROM compra where comp_status = 'finalizado' ";

            PreparedStatement execSQL;

            execSQL = conexao.prepareStatement(comandoSQL);

            ResultSet resultadoConsulta;

            resultadoConsulta = execSQL.executeQuery();
            CompraModel compra;
            FornecedorModel fornecedor;
            FornecedorDAO fornecedorDao;
            FuncionarioDAO funcionarioDao;
            FuncionarioModel funcionario;
            
            while (resultadoConsulta.next()) {
                 fornecedor = new FornecedorModel();
            fornecedorDao = new FornecedorDAO();
            
            funcionario = new FuncionarioModel();
            funcionarioDao = new FuncionarioDAO();
            
            compra = new CompraModel();
            
            int fornecedorID = resultadoConsulta.getInt("fornecedor_for_id");
            fornecedor = fornecedorDao.findByID(fornecedorID);
            
            int funcionarioID = resultadoConsulta.getInt("funcionario_fun_id");
            funcionario = funcionarioDao.findByID(funcionarioID);
            
            compra.setId(resultadoConsulta.getInt("comp_id"));
            compra.setValor(resultadoConsulta.getDouble("comp_valor"));
            compra.setDataCompra(resultadoConsulta.getDate("comp_data"));
            compra.setHoraCompra(resultadoConsulta.getTime("comp_hora"));
            compra.setStatus(resultadoConsulta.getString("comp_status"));
            compra.setFornecedor(fornecedor);
            compra.setFuncionario(funcionario);
            listaCompra.add(compra);
            }
            resultadoConsulta.close();
            execSQL.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaCompra;
    
    }
    
    public ArrayList<CompraModel> pesquisarGraficoPorAnoMes(String anoMes) throws SQLException, NaoEncontradoException {
         ArrayList<CompraModel> listaCompra = new ArrayList<>();
        try {
            
            ResultSet resultadoConsulta;
            Connection conexao = Conexao.getConexao();
            PreparedStatement execSQL;

            execSQL = conexao.prepareStatement("SELECT comp_id, comp_data, comp_hora, sum(comp_valor), comp_status, funcionario_fun_id, "
                    + "fornecedor_for_id FROM compra where comp_status = 'finalizado' and  comp_data LIKE ? GROUP BY comp_data ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                  
            execSQL.setString(1, '%' + anoMes + '%');
            resultadoConsulta = execSQL.executeQuery();
            
            CompraModel compra;
            FornecedorModel fornecedor;
            FornecedorDAO fornecedorDao;
            FuncionarioDAO funcionarioDao;
            FuncionarioModel funcionario;
            
            while (resultadoConsulta.next()) {
            fornecedor = new FornecedorModel();
            fornecedorDao = new FornecedorDAO();
            
            funcionario = new FuncionarioModel();
            funcionarioDao = new FuncionarioDAO();
            
            compra = new CompraModel();
            
            int fornecedorID = resultadoConsulta.getInt("fornecedor_for_id");
            fornecedor = fornecedorDao.findByID(fornecedorID);
            
            int funcionarioID = resultadoConsulta.getInt("funcionario_fun_id");
            funcionario = funcionarioDao.findByID(funcionarioID);
            
            compra.setId(resultadoConsulta.getInt("comp_id"));
            compra.setValor(resultadoConsulta.getDouble("sum(comp_valor)"));
            compra.setDataCompra(resultadoConsulta.getDate("comp_data"));
            compra.setHoraCompra(resultadoConsulta.getTime("comp_hora"));
            compra.setStatus(resultadoConsulta.getString("comp_status"));
            compra.setFornecedor(fornecedor);
            compra.setFuncionario(funcionario);
            
            listaCompra.add(compra);
            
            }
            resultadoConsulta.close();
            execSQL.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaCompra;
    
    }
    
    public ArrayList<CompraModel> findAll() throws SQLException, NaoEncontradoException {
       

        ArrayList<CompraModel> listaCompra = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();

            String comandoSQL = "SELECT * FROM compra";

            PreparedStatement execSQL;

            execSQL = conexao.prepareStatement(comandoSQL);

            ResultSet resultadoConsulta;

            resultadoConsulta = execSQL.executeQuery();
            CompraModel compra;
            FornecedorModel fornecedor;
            FornecedorDAO fornecedorDao;
            FuncionarioDAO funcionarioDao;
            FuncionarioModel funcionario;
            
            while (resultadoConsulta.next()) {
                 fornecedor = new FornecedorModel();
            fornecedorDao = new FornecedorDAO();
            
            funcionario = new FuncionarioModel();
            funcionarioDao = new FuncionarioDAO();
            
            compra = new CompraModel();
            
            int fornecedorID = resultadoConsulta.getInt("fornecedor_for_id");
            fornecedor = fornecedorDao.findByID(fornecedorID);
            
            int funcionarioID = resultadoConsulta.getInt("funcionario_fun_id");
            funcionario = funcionarioDao.findByID(funcionarioID);
            
            compra.setId(resultadoConsulta.getInt("comp_id"));
            compra.setValor(resultadoConsulta.getDouble("comp_valor"));
            compra.setDataCompra(resultadoConsulta.getDate("comp_data"));
            compra.setHoraCompra(resultadoConsulta.getTime("comp_hora"));
            compra.setStatus(resultadoConsulta.getString("comp_status"));
            compra.setFornecedor(fornecedor);
            compra.setFuncionario(funcionario);
            listaCompra.add(compra);
            }
            resultadoConsulta.close();
            execSQL.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaCompra;
    }
    
    
    public ArrayList pesquisarData(Date data) throws SQLException, NaoEncontradoException{
     
        ArrayList<CompraModel> listaCompra = new ArrayList<>();
        Connection conexao = Conexao.getConexao();
    
        PreparedStatement execSQL;
        execSQL = conexao.prepareStatement("SELECT * FROM compra WHERE comp_data = ? ", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setDate(1,data);
        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        CompraModel compra;
        FornecedorModel fornecedor;
        FornecedorDAO fornecedorDao;
        FuncionarioDAO funcionarioDao;
        FuncionarioModel funcionario;
        
        
        while (resultadoConsulta.next()) {
            
            fornecedor = new FornecedorModel();
            fornecedorDao = new FornecedorDAO();
            
            funcionario = new FuncionarioModel();
            funcionarioDao = new FuncionarioDAO();
            
            compra = new CompraModel();
            
            int fornecedorID = resultadoConsulta.getInt("fornecedor_for_id");
            fornecedor = fornecedorDao.findByID(fornecedorID);
            
            int funcionarioID = resultadoConsulta.getInt("funcionario_fun_id");
            funcionario = funcionarioDao.findByID(funcionarioID);
            
            compra.setId(resultadoConsulta.getInt("comp_id"));
            compra.setValor(resultadoConsulta.getDouble("comp_valor"));
            compra.setDataCompra(resultadoConsulta.getDate("comp_data"));
            compra.setHoraCompra(resultadoConsulta.getTime("comp_hora"));
            compra.setFornecedor(fornecedor);
            compra.setFuncionario(funcionario);
        }
        if (listaCompra.isEmpty()) {
            throw new NaoEncontradoException("Nenhuma compra encontrada");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();
        return listaCompra;
    }
    
     public void alterarStatus(CompraModel compra) throws SQLException{
    
        String sql = "UPDATE compra SET comp_data = ?, comp_hora = ?, comp_valor = ?, comp_status = ?, "
                + "funcionario_fun_id = ?, fornecedor_for_id = ? WHERE comp_id = ?";
        
        Connection conexao;
        PreparedStatement execSQL;

        try {
            
            conexao = Conexao.getConexao();
            execSQL = conexao.prepareStatement(sql);
            
            execSQL.setDate(1, compra.getDataCompra());
            execSQL.setTime(2,compra.getHoraCompra());
            execSQL.setDouble(3, compra.getValor());
            execSQL.setString(4, compra.getStatus());
            execSQL.setInt(5, compra.getFuncionario().getId());
            execSQL.setInt(6, compra.getFornecedor().getId());
            execSQL.setInt(7, compra.getId());
            
            execSQL.executeUpdate();

            conexao.commit();
            execSQL.close();
            conexao.close();
            
        } catch (SQLException se) {
            
            System.out.println("ERRO   a" + se);
            throw se;
        
        }

    }
    public CompraModel pesquisarId(int id) throws SQLException, NaoEncontradoException {
      
        Connection conexao = getConexao();
        String codigoString;
        ProdutoModel produto = null;
        int codigoInt, codigo;

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM compra WHERE comp_id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setInt(1, id);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();
        
        CompraModel compra;
        FornecedorModel fornecedor;
        FornecedorDAO fornecedorDao;
         FuncionarioDAO funcionarioDao;
        FuncionarioModel funcionario;
        if (resultadoConsulta.getRow() > 0) {

            fornecedor = new FornecedorModel();
            fornecedorDao = new FornecedorDAO();
            compra = new CompraModel();
            
            funcionario = new FuncionarioModel();
            funcionarioDao = new FuncionarioDAO();
            
            int fornecedorID = resultadoConsulta.getInt("fornecedor_for_id");
            fornecedor = fornecedorDao.findByID(fornecedorID);
             int funcionarioID = resultadoConsulta.getInt("funcionario_fun_id");
            funcionario = funcionarioDao.findByID(funcionarioID);
            compra.setId(resultadoConsulta.getInt("comp_id"));
            compra.setValor(resultadoConsulta.getDouble("comp_valor"));
            compra.setDataCompra(resultadoConsulta.getDate("comp_data"));
            compra.setHoraCompra(resultadoConsulta.getTime("comp_hora"));
             compra.setStatus(resultadoConsulta.getString("comp_status"));
            compra.setFuncionario(funcionario);
            compra.setFornecedor(fornecedor);
            
        } else {
            throw new NaoEncontradoException("Produto não encontrado");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return compra;
    }
    
    public CompraModel pesquisaUltimaCompra() throws SQLException, NaoEncontradoException{
        
        Connection conexao = getConexao();
        String codigoString;
        ProdutoModel produto = null;
        int codigoInt, codigo;

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM compra ORDER BY comp_id DESC LIMIT 1", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();
        
        CompraModel compra;
        FornecedorModel fornecedor;
        FornecedorDAO fornecedorDao;
        
        FuncionarioModel funcionario;
        FuncionarioDAO funcionarioDao;
        
        if (resultadoConsulta.getRow() > 0) {

            fornecedor = new FornecedorModel();
            fornecedorDao = new FornecedorDAO();
            
            funcionario = new FuncionarioModel();
            funcionarioDao = new FuncionarioDAO();
            
            compra = new CompraModel();
            
            fornecedor = fornecedorDao.findByID(resultadoConsulta.getInt("fornecedor_for_id"));
            funcionario = funcionarioDao.findByID(resultadoConsulta.getInt("funcionario_fun_id"));
            
            compra.setId(resultadoConsulta.getInt("comp_id"));
            compra.setDataCompra(resultadoConsulta.getDate("comp_data"));
            compra.setValor(resultadoConsulta.getDouble("comp_valor"));
             compra.setHoraCompra(resultadoConsulta.getTime("comp_hora"));
            compra.setStatus(resultadoConsulta.getString("comp_status"));
            compra.setFornecedor(fornecedor);
            compra.setFuncionario(funcionario);
            
        } else {
            
            throw new NaoEncontradoException("Produto não encontrado");
        
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return compra;
    
    }
    
}
    
    

