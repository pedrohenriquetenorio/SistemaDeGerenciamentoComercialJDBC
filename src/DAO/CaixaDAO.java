/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Control.Exceptions.NaoEncontradoException;
import static DAO.Conexao.getConexao;
import Modelo.CaixaModel;
import Modelo.CompraModel;
import Modelo.FornecedorModel;
import Modelo.FuncionarioModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;


/**
 *
 * @author pedro
 */
public class CaixaDAO {
    
    public void abrir(CaixaModel caixaModel) throws SQLException {

            
            try (java.sql.Connection conexao = Conexao.getConexao()) {
                
            String comandoSQL = "insert into caixa (ca_data_abertura, ca_data_fechamento, ca_valor_inicial,"
                    + "ca_troco, ca_valor_final, ca_valor_fechamento, ca_funcionario_abertura, ca_situacao, ca_finaliza, ca_justificativa, funcionario_fun_id)"
                    + "values(?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement execSQL;

            execSQL = conexao.prepareStatement(comandoSQL);

            execSQL.setTimestamp(1, caixaModel.getDataAbertura());
            
            execSQL.setTimestamp(2, caixaModel.getDataFechamento());

            execSQL.setDouble(3, caixaModel.getValorInicial());
            execSQL.setDouble(4, caixaModel.getTroco());
            execSQL.setDouble(5, caixaModel.getValorFinal());
            execSQL.setDouble(6, caixaModel.getValorFechamento());
            execSQL.setString(7, caixaModel.getFuncionarioAberturaCaixa());
            execSQL.setString(8, caixaModel.getSituacao());
            execSQL.setBoolean(9, caixaModel.getFinaliza());
            execSQL.setString(10, caixaModel.getJustificativa());
            execSQL.setInt(11, caixaModel.getFuncionarioModel().getId());
          
            execSQL.executeUpdate();
            
            conexao.commit();
           
            execSQL.close();
      
            }catch(Exception e){
                
                System.err.println("ERRO DO TRY"+ e);
                
            };
    }
    public void fechar(CaixaModel caixaModel) throws SQLException{
    
        String sql = "UPDATE caixa SET ca_data_abertura = ?, ca_data_fechamento = ?, ca_valor_inicial = ?, ca_troco = ?,"
                    + "ca_valor_final = ?,ca_valor_fechamento = ?, ca_funcionario_abertura = ?, ca_situacao = ?,  ca_finaliza = ?,ca_justificativa=?, funcionario_fun_id = ? WHERE ca_id = ?";

        Connection conexao;
        PreparedStatement execSQL;

        try {
            
            conexao = Conexao.getConexao();
            execSQL = conexao.prepareStatement(sql);

            execSQL.setTimestamp(1, caixaModel.getDataAbertura());
            
            execSQL.setTimestamp(2, caixaModel.getDataFechamento());

            execSQL.setDouble(3, caixaModel.getValorInicial());
           execSQL.setDouble(4, caixaModel.getTroco());
            execSQL.setDouble(5, caixaModel.getValorFinal());
            execSQL.setDouble(6, caixaModel.getValorFechamento());
            execSQL.setString(7, caixaModel.getFuncionarioAberturaCaixa());
            execSQL.setString(8, caixaModel.getSituacao());
            execSQL.setBoolean(9, caixaModel.getFinaliza());
            execSQL.setString(10, caixaModel.getJustificativa());
            execSQL.setInt(11, caixaModel.getFuncionarioModel().getId());
            execSQL.setInt(12, caixaModel.getId());
            
            execSQL.executeUpdate();

            conexao.commit();
            execSQL.close();
            conexao.close();
            
        } catch (SQLException se) {
            System.out.println("ERRO   " + se);
            throw se;

        }

    }
    
    public ArrayList<CaixaModel> pesquisarGraficoPorAnoMes(String anoMes) throws SQLException, NaoEncontradoException {
           String codigoString;

        int codigo;

        ArrayList<CaixaModel> listaCaixas = new ArrayList<>();
        Connection conexao = Conexao.getConexao();
        PreparedStatement execSQL;
        
        execSQL = conexao.prepareStatement("SELECT *, sum(ca_valor_final) FROM caixa WHERE ca_data_abertura LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        execSQL.setString(1, '%' + anoMes + '%');
        ResultSet resultadoConsulta;
        resultadoConsulta = execSQL.executeQuery();
        FuncionarioModel funcionarioModel;
        FuncionarioDAO  funcionarioDAO = new FuncionarioDAO();
        CaixaModel caixaModel;
        
        while (resultadoConsulta.next()) {

            funcionarioModel = new FuncionarioModel();
            caixaModel = new CaixaModel();
            int idInt = (resultadoConsulta.getInt("funcionario_fun_id"));
            String id = String.valueOf(idInt);
            
             funcionarioModel = funcionarioDAO.findByID(idInt);
             
             caixaModel.setDataAbertura(resultadoConsulta.getTimestamp("ca_data_abertura"));
             caixaModel.setDataFechamento(resultadoConsulta.getTimestamp("ca_data_fechamento"));
             caixaModel.setFuncionarioModel(funcionarioModel);
             caixaModel.setTroco(resultadoConsulta.getDouble("ca_troco"));
             caixaModel.setId(resultadoConsulta.getInt("ca_id"));
             caixaModel.setFuncionarioAberturaCaixa(resultadoConsulta.getString("ca_funcionario_abertura"));
             caixaModel.setSituacao(resultadoConsulta.getString("ca_situacao"));
             caixaModel.setJustificativa(resultadoConsulta.getString("ca_justificativa"));
             caixaModel.setFinaliza(resultadoConsulta.getBoolean("ca_finaliza"));
             caixaModel.setValorFechamento(resultadoConsulta.getDouble("ca_valor_fechamento"));
             caixaModel.setValorFinal(resultadoConsulta.getDouble("sum(ca_valor_final)"));
             caixaModel.setValorInicial(resultadoConsulta.getDouble("ca_valor_inicial"));
            
            listaCaixas.add(caixaModel);

        }
        
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return listaCaixas;
    }
    
     public CaixaModel pesquisarDataHora(String dataHora) throws ParseException, SQLException, NaoEncontradoException{
        
      Connection conexao = Conexao.getConexao();
      
        PreparedStatement execSQL;
        
        execSQL = conexao.prepareStatement("SELECT * FROM caixa WHERE ca_data_abertura = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        execSQL.setString(1, dataHora);
        
        CaixaModel caixaModel = new CaixaModel();
        ResultSet resultadoConsulta;
        resultadoConsulta = execSQL.executeQuery();
        resultadoConsulta.last();
       
        FuncionarioModel funcionarioModel = new FuncionarioModel();
        FuncionarioDAO  funcionarioDAO = new FuncionarioDAO();
        
        if (resultadoConsulta.getRow() > 0) {

            int idInt = (resultadoConsulta.getInt("funcionario_fun_id"));
            String id = String.valueOf(idInt);
            
             funcionarioModel = funcionarioDAO.pesquisar( id, "id");
             
             caixaModel.setDataAbertura(resultadoConsulta.getTimestamp("ca_data_abertura"));
             
             caixaModel.setDataFechamento(resultadoConsulta.getTimestamp("ca_data_fechamento"));
             
             caixaModel.setFuncionarioModel(funcionarioModel);
             
             caixaModel.setId(resultadoConsulta.getInt("ca_id"));
             
             caixaModel.setFuncionarioAberturaCaixa(resultadoConsulta.getString("ca_funcionario_abertura"));
             
             caixaModel.setFinaliza(resultadoConsulta.getBoolean("ca_finaliza"));
             
             caixaModel.setSituacao(resultadoConsulta.getString("ca_situacao"));
             
             caixaModel.setValorFinal(resultadoConsulta.getDouble("ca_valor_final"));
             
             caixaModel.setValorFechamento(resultadoConsulta.getDouble("ca_valor_fechamento"));
             
             caixaModel.setValorInicial(resultadoConsulta.getDouble("ca_valor_inicial"));
            
             caixaModel.setTroco(resultadoConsulta.getDouble("ca_troco"));
        } else {
            throw new NaoEncontradoException("Produto não encontrado");
        }
        
     return caixaModel;
    }
     
     public ArrayList<CaixaModel> pesquisarData(String data) throws SQLException, ParseException, NaoEncontradoException {

        String codigoString;

        int codigo;

        ArrayList<CaixaModel> listaCaixas = new ArrayList<>();
        Connection conexao = Conexao.getConexao();
        PreparedStatement execSQL;
        execSQL = conexao.prepareStatement("SELECT * FROM caixa WHERE ca_data_abertura LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        execSQL.setString(1, '%' + data + '%');
        ResultSet resultadoConsulta;
        resultadoConsulta = execSQL.executeQuery();
        FuncionarioModel funcionarioModel;
        FuncionarioDAO  funcionarioDAO = new FuncionarioDAO();
        CaixaModel caixaModel;
        
        while (resultadoConsulta.next()) {

            funcionarioModel = new FuncionarioModel();
             caixaModel = new CaixaModel();
             int idInt = (resultadoConsulta.getInt("funcionario_fun_id"));
            String id = String.valueOf(idInt);
            
             funcionarioModel = funcionarioDAO.pesquisar( id, "id");
             
             caixaModel.setDataAbertura(resultadoConsulta.getTimestamp("ca_data_abertura"));
             caixaModel.setDataFechamento(resultadoConsulta.getTimestamp("ca_data_fechamento"));
             caixaModel.setFuncionarioModel(funcionarioModel);
             caixaModel.setTroco(resultadoConsulta.getDouble("ca_troco"));
             caixaModel.setId(resultadoConsulta.getInt("ca_id"));
             caixaModel.setFuncionarioAberturaCaixa(resultadoConsulta.getString("ca_funcionario_abertura"));
             caixaModel.setSituacao(resultadoConsulta.getString("ca_situacao"));
             caixaModel.setJustificativa(resultadoConsulta.getString("ca_justificativa"));
             caixaModel.setFinaliza(resultadoConsulta.getBoolean("ca_finaliza"));
             caixaModel.setValorFechamento(resultadoConsulta.getDouble("ca_valor_fechamento"));
             caixaModel.setValorFinal(resultadoConsulta.getDouble("ca_valor_final"));
             caixaModel.setValorInicial(resultadoConsulta.getDouble("ca_valor_inicial"));
            
            listaCaixas.add(caixaModel);

        }
        if (listaCaixas.isEmpty()) {
            throw new NaoEncontradoException("Caixa não encontrado");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return listaCaixas;
    }
     
      public void alterarValorFinal(CaixaModel caixaModel) throws SQLException{
    
        String sql = "UPDATE caixa SET ca_data_abertura = ?, ca_data_fechamento = ?, ca_valor_inicial = ?, ca_troco=?, "
                    + "ca_valor_final = ?, ca_valor_fechamento = ?, ca_funcionario_abertura = ?, ca_situacao = ?, ca_finaliza = ? , ca_justificativa = ?, funcionario_fun_id = ? WHERE ca_id = ?";
        
        Connection conexao;
        PreparedStatement execSQL;
//      
//        
//          System.out.println("1 "+caixaModel.getFuncionarioAberturaCaixa());
//          System.out.println("2 "+caixaModel.getSituacao());
//          System.out.println("3 "+caixaModel.getDataAbertura());
//          System.out.println("4 "+caixaModel.getDataFechamento());
//          System.out.println("5 "+caixaModel.getFinaliza());
//          System.out.println("6 "+caixaModel.getFuncionarioModel());
//          System.out.println("7 "+caixaModel.getId());
//          System.out.println("8 "+caixaModel.getTroco());
//          System.out.println("9 "+caixaModel.getValorFechamento());
//          System.out.println("10 "+caixaModel.getValorFinal());
//          System.out.println("11 "+caixaModel.getValorInicial());
        try {
            
            conexao = Conexao.getConexao();
            execSQL = conexao.prepareStatement(sql);
            
            execSQL.setTimestamp(1, caixaModel.getDataAbertura());
            execSQL.setTimestamp(2, caixaModel.getDataFechamento());
            execSQL.setDouble(3, caixaModel.getValorInicial());
            execSQL.setDouble(4, caixaModel.getTroco());
            execSQL.setDouble(5, caixaModel.getValorFinal());
            execSQL.setDouble(6, caixaModel.getValorFechamento());
            execSQL.setString(7, caixaModel.getFuncionarioAberturaCaixa());
            execSQL.setString(8, caixaModel.getSituacao());
            execSQL.setBoolean(9, caixaModel.getFinaliza());
            execSQL.setString(10, caixaModel.getJustificativa());
            execSQL.setInt(11, caixaModel.getFuncionarioModel().getId());
            execSQL.setInt(12, caixaModel.getId());
            
            execSQL.executeUpdate();

            conexao.commit();
            execSQL.close();
            conexao.close();
            
        } catch (SQLException se) {
            
            System.out.println("ERRO   a" + se);
            throw se;
        
        }

    }
    public CaixaModel pesquisar(String id) throws SQLException, NaoEncontradoException{
        int idInt;
        CaixaModel caixaModel = new CaixaModel();
        FuncionarioModel funcionarioModel = new FuncionarioModel();
        FuncionarioDAO  funcionarioDAO = new FuncionarioDAO();
        Boolean verifica = false;

        Connection conexao = getConexao();

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM caixa WHERE ca_id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, id);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();
        
        resultadoConsulta.last();

         if (resultadoConsulta.getRow() > 0) {
            
             idInt = (resultadoConsulta.getInt("funcionario_fun_id"));
             id = String.valueOf(idInt);
            
            funcionarioModel = funcionarioDAO.pesquisar( id, "id");
             
             caixaModel.setDataAbertura(resultadoConsulta.getTimestamp("ca_data_abertura"));
             
             caixaModel.setDataFechamento(resultadoConsulta.getTimestamp("ca_data_fechamento"));
              caixaModel.setValorFechamento(resultadoConsulta.getDouble("ca_valor_fechamento"));
             caixaModel.setFuncionarioModel(funcionarioModel);
             caixaModel.setTroco(resultadoConsulta.getDouble("ca_troco"));
             caixaModel.setId(resultadoConsulta.getInt("ca_id"));
             caixaModel.setFuncionarioAberturaCaixa(resultadoConsulta.getString("ca_funcionario_abertura"));
             caixaModel.setSituacao(resultadoConsulta.getString("ca_situacao"));
             caixaModel.setFinaliza(resultadoConsulta.getBoolean("ca_finaliza"));
             caixaModel.setValorFinal(resultadoConsulta.getDouble("ca_valor_final"));
             caixaModel.setJustificativa(resultadoConsulta.getString("ca_justificativa"));
             caixaModel.setValorInicial(resultadoConsulta.getDouble("ca_valor_inicial"));
         
         }
        
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();
        
    
    return caixaModel;
    }
    
    
    
     public CaixaModel pesquisarCaixa() throws SQLException, NaoEncontradoException {
        String id;
        CaixaModel caixaModel;
        FuncionarioModel funcionarioModel;
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
        int idInt;

        Connection conexao = Conexao.getConexao();

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM caixa ORDER BY ca_id DESC LIMIT 1", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();

        if (resultadoConsulta.getRow() > 0) {

            funcionarioModel = new FuncionarioModel();
            caixaModel = new CaixaModel();
            
            idInt = (resultadoConsulta.getInt("funcionario_fun_id"));
            id = String.valueOf(idInt);
            
             funcionarioModel = funcionarioDao.pesquisar( id, "id");
            
             caixaModel.setDataAbertura(resultadoConsulta.getTimestamp("ca_data_abertura"));
             caixaModel.setDataFechamento(resultadoConsulta.getTimestamp("ca_data_fechamento"));
             caixaModel.setFuncionarioModel(funcionarioModel);
             caixaModel.setId(resultadoConsulta.getInt("ca_id"));
             caixaModel.setValorFechamento(resultadoConsulta.getDouble("ca_valor_fechamento"));
             caixaModel.setFuncionarioAberturaCaixa(resultadoConsulta.getString("ca_funcionario_abertura"));
             caixaModel.setTroco(resultadoConsulta.getDouble("ca_troco"));
             caixaModel.setSituacao(resultadoConsulta.getString("ca_situacao"));
             caixaModel.setFinaliza(resultadoConsulta.getBoolean("ca_finaliza"));
             caixaModel.setValorFinal(resultadoConsulta.getDouble("ca_valor_final"));
             caixaModel.setValorInicial(resultadoConsulta.getDouble("ca_valor_inicial"));
             caixaModel.setJustificativa(resultadoConsulta.getString("ca_justificativa"));

        } else {
            throw new NaoEncontradoException("não há caixa, na base de dados");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return caixaModel;

    }

    public ArrayList<CaixaModel> findAll() throws SQLException, ParseException, NaoEncontradoException {
        String codigoString;

        int codigo;

        ArrayList<CaixaModel> listaCaixa = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();

            String comandoSQL = "SELECT * FROM caixa";

            PreparedStatement execSQL;

            execSQL = conexao.prepareStatement(comandoSQL);

            ResultSet resultadoConsulta;

            resultadoConsulta = execSQL.executeQuery();

           CaixaModel caixa;
           FuncionarioModel funcionario;
           FuncionarioDAO funcionarioDao = new FuncionarioDAO();
            while (resultadoConsulta.next()) {

                caixa = new CaixaModel();
                funcionario = new FuncionarioModel();
                
                funcionario = funcionarioDao.findByID(resultadoConsulta.getInt("funcionario_fun_id"));
                
                caixa.setId(resultadoConsulta.getInt("ca_id"));
                caixa.setFuncionarioAberturaCaixa(resultadoConsulta.getString("ca_funcionario_abertura"));
                caixa.setDataAbertura(resultadoConsulta.getTimestamp("ca_data_abertura"));
                caixa.setDataFechamento(resultadoConsulta.getTimestamp("ca_data_fechamento"));
                caixa.setValorInicial(resultadoConsulta.getDouble("ca_valor_inicial"));
                caixa.setTroco(resultadoConsulta.getDouble("ca_troco"));
                caixa.setFinaliza(resultadoConsulta.getBoolean("ca_finaliza"));
                caixa.setValorFinal(resultadoConsulta.getDouble("ca_valor_final"));
                caixa.setValorFechamento(resultadoConsulta.getDouble("ca_valor_fechamento"));
                caixa.setSituacao(resultadoConsulta.getString("ca_situacao"));
                caixa.setJustificativa(resultadoConsulta.getString("ca_justificativa"));
                caixa.setFuncionarioModel(funcionario);
                listaCaixa.add(caixa);
            }
            resultadoConsulta.close();
            execSQL.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaCaixa;
    }
}
