/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Control.Exceptions.NaoEncontradoException;
import Modelo.CaixaModel;
import Modelo.FuncionarioModel;

import Modelo.RetiradaModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author pedro
 */
public class RetiradaDAO {
    public RetiradaModel pesquisarDataHora(String dataHora) throws ParseException, SQLException, NaoEncontradoException{
        
        Connection conexao = Conexao.getConexao();
      
        PreparedStatement execSQL;
        
        execSQL = conexao.prepareStatement("SELECT * FROM retirada_caixa WHERE ret_data = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        execSQL.setString(1, dataHora);
        
        RetiradaModel retirada;
        ResultSet resultadoConsulta;
        resultadoConsulta = execSQL.executeQuery();
        resultadoConsulta.last();
        CaixaModel caixaModel;
        CaixaDAO caixaDao;
        FuncionarioModel funcionario;
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
        
        
        if (resultadoConsulta.getRow() > 0) {

            retirada = new RetiradaModel();
            caixaModel = new CaixaModel();
            caixaDao = new CaixaDAO();
           
            int caixaID = resultadoConsulta.getInt("caixa_ca_id");
            String caixaString = String.valueOf(caixaID);
            caixaModel = caixaDao.pesquisar(caixaString);
            
            funcionario = funcionarioDao.pesquisarDataHoralogin();
            
            retirada.setId(resultadoConsulta.getInt("ret_id"));
            retirada.setValor(resultadoConsulta.getDouble("ret_valor"));
            retirada.setData(resultadoConsulta.getTimestamp("ret_data"));
            retirada.setDescricao(resultadoConsulta.getString("ret_descricao"));
            retirada.setFuncionario(funcionario);
            retirada.setCaixa(caixaModel);
            
        } else {
            throw new NaoEncontradoException("Produto não encontrado");
        }
        
     return retirada;
    }
    
    
    public ArrayList<RetiradaModel> pesquisarRetirada(int idCaixa) throws SQLException, NaoEncontradoException{
  
     String codigoString, idString;
   
        int codigo;
        
        ArrayList<RetiradaModel>listaRetirada = new ArrayList<>();
        //try {
        Connection conexao = Conexao.getConexao();

        PreparedStatement execSQL;
        
        execSQL = conexao.prepareStatement("SELECT * FROM retirada_caixa WHERE caixa_ca_id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setInt(1, idCaixa);
        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        RetiradaModel retirada;
        CaixaModel caixaModel;
        CaixaDAO caixaDao;
        
        FuncionarioModel funcionario;
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
        
        while (resultadoConsulta.next()) {
            
            
            retirada = new RetiradaModel();
            caixaModel = new CaixaModel();
            caixaDao = new CaixaDAO();
           
            int caixaID = resultadoConsulta.getInt("caixa_ca_id");
            String caixaString = String.valueOf(caixaID);
            caixaModel = caixaDao.pesquisar(caixaString);
            
            funcionario = funcionarioDao.pesquisarDataHoralogin();
            
            retirada.setId(resultadoConsulta.getInt("ret_id"));
            retirada.setValor(resultadoConsulta.getDouble("ret_valor"));
            retirada.setData(resultadoConsulta.getTimestamp("ret_data"));
            retirada.setDescricao(resultadoConsulta.getString("ret_descricao"));
            retirada.setFuncionario(funcionario);
            retirada.setCaixa(caixaModel);
  
            listaRetirada.add(retirada);

        }
        
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

   return listaRetirada;
   
  }
    
   public void retirarValor(RetiradaModel retirar){
        try {
            CaixaDAO caixaDao = new CaixaDAO();
            java.sql.Connection conexao = Conexao.getConexao();
            String comandoSQL = "insert into retirada_caixa(ret_valor, ret_data, ret_descricao, funcionario_fun_id, caixa_ca_id) "
                    + "values(?, ?, ?, ?, ?)";
            
            PreparedStatement execSQL;
            execSQL = conexao.prepareStatement(comandoSQL);

            execSQL.setDouble(1, retirar.getValor());
            execSQL.setTimestamp(2, retirar.getData());
            execSQL.setString(3, retirar.getDescricao());
            execSQL.setInt(4, retirar.getFuncionario().getId());
            execSQL.setInt(5, retirar.getCaixa().getId());
            
            caixaDao.alterarValorFinal(retirar.getCaixa());
            
            execSQL.executeUpdate();

            conexao.commit();

            execSQL.close();

           

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar a retirada de valores ", "ERRO ao Retirar",
                    JOptionPane.ERROR_MESSAGE);

        }
   
   
   
   }
    
}
