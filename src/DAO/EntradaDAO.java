/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Control.Exceptions.NaoEncontradoException;
import Modelo.CaixaModel;
import Modelo.EntradaModel;
import Modelo.FuncionarioModel;
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
public class EntradaDAO {
     public EntradaModel pesquisarDataHora(String dataHora) throws ParseException, SQLException, NaoEncontradoException{
        
      Connection conexao = Conexao.getConexao();
       
        PreparedStatement execSQL;
        
        execSQL = conexao.prepareStatement("SELECT * FROM entrada_caixa WHERE ent_data = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        execSQL.setString(1, dataHora);
        
        ResultSet resultadoConsulta;
        
        resultadoConsulta = execSQL.executeQuery();
        
        resultadoConsulta.last();
        
        EntradaModel entrada;
        CaixaModel caixaModel;
        CaixaDAO caixaDao;
        FuncionarioModel funcionarioModel;
        FuncionarioDAO funcionarioDao;
        
        if (resultadoConsulta.getRow() > 0) {

            entrada = new EntradaModel();
            caixaModel = new CaixaModel();
            caixaDao = new CaixaDAO();

            funcionarioModel = new FuncionarioModel();
            funcionarioDao = new FuncionarioDAO();
            
            String caixaString = resultadoConsulta.getString("caixa_ca_id");
            caixaModel = caixaDao.pesquisar(caixaString);
            
            funcionarioModel = funcionarioDao.pesquisarDataHoralogin();
           
            entrada.setId(resultadoConsulta.getInt("ent_id"));
            entrada.setValor(resultadoConsulta.getDouble("ent_valor"));
            entrada.setData(resultadoConsulta.getTimestamp("ent_data"));
            entrada.setDescricao(resultadoConsulta.getString("ent_descricao"));
            
            entrada.setFuncionario(funcionarioModel);
            entrada.setCaixa(caixaModel);
          
        } else {
            throw new NaoEncontradoException("Produto não encontrado");
        }
        
     return entrada;
    }
    
    
    public void reporValor(EntradaModel entrada) {
        try {
            CaixaDAO caixaDao = new CaixaDAO();
            java.sql.Connection conexao = Conexao.getConexao();
            String comandoSQL = "insert into entrada_caixa(ent_data, ent_valor, ent_descricao, funcionario_fun_id, caixa_ca_id) "
                    + "values(?, ?, ?,?,?)";
            
            PreparedStatement execSQL;
            execSQL = conexao.prepareStatement(comandoSQL);

            execSQL.setTimestamp(1, entrada.getData());
            execSQL.setDouble(2, entrada.getValor());
            execSQL.setString(3, entrada.getDescricao());
            execSQL.setInt(4, entrada.getFuncionario().getId());
            execSQL.setInt(5, entrada.getCaixa().getId());
           
            caixaDao.alterarValorFinal(entrada.getCaixa());
            
            execSQL.executeUpdate();

            conexao.commit();

            execSQL.close();

            JOptionPane.showMessageDialog(null, "Entrada de valor realizada!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar dar entrada de valores ", "Erro",
                    JOptionPane.ERROR_MESSAGE);

        }

    }

    public ArrayList<EntradaModel> pesquisarReposicao(int idCaixa) throws SQLException, NaoEncontradoException {

        String codigoString;

        int codigo;
        
        ArrayList<EntradaModel> listaReposicao = new ArrayList<>();
        //try {
        Connection conexao = Conexao.getConexao();

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT entrada_caixa.* FROM entrada_caixa WHERE caixa_ca_id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setInt(1, idCaixa);
        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        EntradaModel entrada;
        CaixaModel caixaModel;
        CaixaDAO caixaDao;
        FuncionarioModel funcionarioModel;
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
        while (resultadoConsulta.next()) {

            entrada = new EntradaModel();
            caixaModel = new CaixaModel();
            caixaDao = new CaixaDAO();
            funcionarioModel = new FuncionarioModel();
            
            String caixaString = resultadoConsulta.getString("caixa_ca_id");
            caixaModel = caixaDao.pesquisar(caixaString);
            int funcionarioInt = resultadoConsulta.getInt("funcionario_fun_id");

            funcionarioModel = funcionarioDao.findByID(funcionarioInt);
            
            entrada.setId(resultadoConsulta.getInt("ent_id"));
            entrada.setValor(resultadoConsulta.getDouble("ent_valor"));
            entrada.setData(resultadoConsulta.getTimestamp("ent_data"));
            entrada.setDescricao(resultadoConsulta.getString("ent_descricao"));
            
            entrada.setFuncionario(funcionarioModel);
            entrada.setCaixa(caixaModel);
            listaReposicao.add(entrada);

        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return listaReposicao;
    }
}
