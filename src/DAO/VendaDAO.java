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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author pedro
 */
public class VendaDAO {
    
    public void inserir(VendaModel venda){
         try (java.sql.Connection conexao = Conexao.getConexao()) {
           
            String comandoSQL = "insert into venda (ven_valor_total, ven_desconto, ven_forma_pagamento, ven_data, ven_hora, "
                    + " funcionario_fun_id, caixa_ca_id, cliente_clie_id)"
                    + "values(?, ?, ?, ?,?, ?, ?, ?)";

            PreparedStatement execSQL;
            execSQL = conexao.prepareStatement(comandoSQL);

            execSQL.setDouble(1, venda.getValor());
            execSQL.setDouble(2, venda.getDesconto());
            execSQL.setString(3, venda.getFormaPagamento());
            execSQL.setDate(4, venda.getDataVenda());
            execSQL.setTime(5, venda.getHoraVenda());
            execSQL.setInt(6, venda.getFuncionario().getId());
            execSQL.setInt(7, venda.getCaixaModel().getId());
            execSQL.setInt(8, venda.getClienteModel().getId());
  
            execSQL.executeUpdate();

            conexao.commit();

            execSQL.close();

            JOptionPane.showMessageDialog(null, "Venda Realizada!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
       
             System.err.println(ex.getMessage());
            
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar a venda ", "Erro ao efetuar Venda",
            JOptionPane.ERROR_MESSAGE);
            
        }
        
    }
    public ArrayList<VendaModel> findAll() throws SQLException, NaoEncontradoException{
        String codigoString, idString;
         
        int codigo;
      
        ArrayList<VendaModel>listaVenda = new ArrayList<>();
     
        Connection conexao = Conexao.getConexao();

        String comandoSQL = "SELECT * FROM venda";    
        PreparedStatement execSQL;
        
        execSQL = conexao.prepareStatement(comandoSQL);
        
        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();
        
        VendaModel venda;
        CaixaModel caixaModel;
        CaixaDAO caixaDao;
        
        ClienteModel cliente;
        ClienteDAO clienteDao = null;
        
        FuncionarioModel funcionario;
        FuncionarioDAO funcionarioDao;
        
        while (resultadoConsulta.next()) {
            funcionario = new FuncionarioModel();
            funcionarioDao = new FuncionarioDAO();
            venda = new VendaModel();
            caixaModel = new CaixaModel();
            caixaDao = new CaixaDAO();
            clienteDao = new ClienteDAO();
            cliente = new ClienteModel();
            
            int caixaID = resultadoConsulta.getInt("caixa_ca_id");
            String caixaString = String.valueOf(caixaID);
            caixaModel = caixaDao.pesquisar(caixaString);
            
            int ClienteID = resultadoConsulta.getInt("cliente_clie_id");
            cliente = clienteDao.findByID(ClienteID);
            
            int funcionarioID = resultadoConsulta.getInt("funcionario_fun_id");
            funcionario = funcionarioDao.findByID(funcionarioID);
            
            venda.setId(resultadoConsulta.getInt("ven_id"));
            venda.setValor(resultadoConsulta.getDouble("ven_valor_total"));
            venda.setDesconto(resultadoConsulta.getDouble("ven_desconto"));
            venda.setFormaPagamento(resultadoConsulta.getString("ven_forma_pagamento"));
            venda.setDataVenda(resultadoConsulta.getDate("ven_data"));
            venda.setHoraVenda(resultadoConsulta.getTime("ven_hora"));
            venda.setCaixaModel(caixaModel);
            venda.setClienteModel(cliente);
            venda.setFuncionario(funcionario);
         
            listaVenda.add(venda);

        }
        
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

   return listaVenda;
    
    }
    public VendaModel ultimaVanda() throws SQLException{
        
        String codigoString;
        VendaModel venda;

        int codigo;

        Connection conexao = Conexao.getConexao();

        PreparedStatement execSQL;
        
        String sql = "SELECT venda.*, caixa.*, funcionario.*, cliente.* " +
                "FROM venda " +
                "INNER JOIN cliente on cliente_clie_id = clie_id " +
                "INNER JOIN caixa on caixa_ca_id = ca_id " +
                "INNER JOIN funcionario on funcionario_fun_id = fun_id ORDER BY ven_id DESC LIMIT 1";
        
        execSQL = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();
        FuncionarioModel funcionario;
        ClienteModel cliente;
        CaixaModel caixa;
        
        if (resultadoConsulta.getRow() > 0) {
            
            funcionario = new FuncionarioModel();
            cliente = new ClienteModel();
            caixa = new CaixaModel();
            
            venda = new VendaModel();
            
            funcionario.setId(resultadoConsulta.getInt("fun_id"));
            funcionario.setNome(resultadoConsulta.getString("fun_nome"));
            funcionario.setCpf(resultadoConsulta.getString("fun_cpf"));
            funcionario.setTelefone(resultadoConsulta.getString("fun_telefone"));
            funcionario.setCelular(resultadoConsulta.getString("fun_celular"));
            funcionario.setRua(resultadoConsulta.getString("fun_rua"));
            funcionario.setNumero(resultadoConsulta.getString("fun_numero"));
            funcionario.setBairro(resultadoConsulta.getString("fun_bairro"));
            funcionario.setCidade(resultadoConsulta.getString("fun_cidade"));
            funcionario.setUf(resultadoConsulta.getString("fun_uf"));
            funcionario.setCargo(resultadoConsulta.getString("fun_cargo"));
            funcionario.setCep(resultadoConsulta.getString("fun_cep"));
            funcionario.setLogin(resultadoConsulta.getString("fun_login"));
            funcionario.setSenha(resultadoConsulta.getString("fun_senha"));
            funcionario.setHoraDataLogin(resultadoConsulta.getTimestamp("fun_data_hora_login"));
            funcionario.setDataCadastro(resultadoConsulta.getDate("fun_data_cadastro"));
            
            caixa.setDataAbertura(resultadoConsulta.getTimestamp("ca_data_abertura"));          
            caixa.setDataFechamento(resultadoConsulta.getTimestamp("ca_data_abertura"));        
            caixa.setFuncionarioModel(funcionario);          
            caixa.setId(resultadoConsulta.getInt("ca_id"));      
            caixa.setSituacao(resultadoConsulta.getString("ca_situacao"));       
            caixa.setValorFinal(resultadoConsulta.getDouble("ca_valor_final"));
            caixa.setValorInicial(resultadoConsulta.getDouble("ca_valor_inicial"));
            
            cliente.setId(resultadoConsulta.getInt("clie_id"));
            cliente.setNome(resultadoConsulta.getString("clie_nome"));
            cliente.setCpf(resultadoConsulta.getString("clie_cpf"));
            cliente.setTelefone(resultadoConsulta.getString("clie_telefone"));
            cliente.setCelular(resultadoConsulta.getString("clie_celular"));
            cliente.setRua(resultadoConsulta.getString("clie_rua"));
            cliente.setNumero(resultadoConsulta.getString("clie_numero"));
            cliente.setBairro(resultadoConsulta.getString("clie_bairro"));
            cliente.setCidade(resultadoConsulta.getString("clie_cidade"));
            cliente.setUf(resultadoConsulta.getString("clie_uf"));
            cliente.setCep(resultadoConsulta.getString("clie_cep"));
            cliente.setTipo(resultadoConsulta.getString("clie_tipo"));
            cliente.setCnpj(resultadoConsulta.getString("clie_cnpj"));
            cliente.setDataCadastro(resultadoConsulta.getDate("clie_data_cadastro"));
            
            venda.setId(resultadoConsulta.getInt("ven_id"));
            venda.setValor(resultadoConsulta.getDouble("ven_valor_total"));
            venda.setDesconto(resultadoConsulta.getDouble("ven_desconto"));
            venda.setFormaPagamento(resultadoConsulta.getString("forma_pagamento"));
            venda.setDataVenda(resultadoConsulta.getDate("ven_data"));
            venda.setHoraVenda(resultadoConsulta.getTime("ven_hora"));
            venda.setCaixaModel(caixa);
            venda.setClienteModel(cliente);
            venda.setFuncionario(funcionario);
            
           
        } else {
            throw new SQLException("Erro, Não foi possivel efetuar busca de venda!");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return venda;
    
    }
    public VendaModel ultimaVanda2() throws SQLException{
        
        String codigoString;
        VendaModel venda;

        int codigo;

        Connection conexao = Conexao.getConexao();

        PreparedStatement execSQL;
        
        String sql = "SELECT * FROM venda ORDER BY ven_id DESC LIMIT 1";
        
        execSQL = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();
        FuncionarioModel funcionario;
        ClienteModel cliente;
        CaixaModel caixa;
        
        if (resultadoConsulta.getRow() > 0) {
            
            funcionario = new FuncionarioModel();
            cliente = new ClienteModel();
            caixa = new CaixaModel();
            
            FuncionarioDAO funcionarioDao = new FuncionarioDAO();
            ClienteDAO clienteDao = new ClienteDAO();
            CaixaDAO caixaDao = new CaixaDAO();
            
            try {
                funcionario = funcionarioDao.findByID(resultadoConsulta.getInt("ven_id"));
            } catch (NaoEncontradoException ex) {
                Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            cliente = clienteDao.findByID(resultadoConsulta.getInt("ven_id"));
            
            try {
                caixa = caixaDao.pesquisarCaixa();
            } catch (NaoEncontradoException ex) {
                Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            venda = new VendaModel();
            
            venda.setId(resultadoConsulta.getInt("ven_id"));
            venda.setValor(resultadoConsulta.getDouble("ven_valor_total"));
            venda.setDesconto(resultadoConsulta.getDouble("ven_desconto"));
            venda.setFormaPagamento(resultadoConsulta.getString("ven_forma_pagamento"));
            venda.setDataVenda(resultadoConsulta.getDate("ven_data"));
            venda.setHoraVenda(resultadoConsulta.getTime("ven_hora"));
            venda.setCaixaModel(caixa);
            venda.setClienteModel(cliente);
            venda.setFuncionario(funcionario);
           
        } else {
            throw new SQLException("Erro, Não foi possivel efetuar busca de venda!");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return venda;
    
    }
    
    
     public ArrayList<VendaModel> pesquisarVenda(int idCaixa) throws SQLException, NaoEncontradoException{
  
     String codigoString, idString;
         
        int codigo;
      
        ArrayList<VendaModel>listaVenda = new ArrayList<>();
     
        Connection conexao = Conexao.getConexao();

        PreparedStatement execSQL;
        execSQL = conexao.prepareStatement("SELECT * FROM venda WHERE caixa_ca_id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setInt(1, idCaixa);
        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();
     
        VendaModel venda;
        CaixaModel caixaModel;
        CaixaDAO caixaDao;
        
        ClienteModel cliente;
        ClienteDAO clienteDao = null;
        
        FuncionarioModel funcionario;
        FuncionarioDAO funcionarioDao;
        
        while (resultadoConsulta.next()) {
            funcionario = new FuncionarioModel();
            funcionarioDao = new FuncionarioDAO();
            venda = new VendaModel();
            caixaModel = new CaixaModel();
            caixaDao = new CaixaDAO();
            clienteDao = new ClienteDAO();
            cliente = new ClienteModel();
            
            int caixaID = resultadoConsulta.getInt("caixa_ca_id");
            String caixaString = String.valueOf(caixaID);
            caixaModel = caixaDao.pesquisar(caixaString);
            
            int ClienteID = resultadoConsulta.getInt("cliente_clie_id");
            cliente = clienteDao.findByID(ClienteID);
            
            int funcionarioID = resultadoConsulta.getInt("funcionario_fun_id");
            funcionario = funcionarioDao.findByID(funcionarioID);
            
            venda.setId(resultadoConsulta.getInt("ven_id"));
            venda.setValor(resultadoConsulta.getDouble("ven_valor_total"));
            venda.setDesconto(resultadoConsulta.getDouble("ven_desconto"));
            venda.setFormaPagamento(resultadoConsulta.getString("ven_forma_pagamento"));
            venda.setDataVenda(resultadoConsulta.getDate("ven_data"));
            venda.setHoraVenda(resultadoConsulta.getTime("ven_hora"));
            venda.setCaixaModel(caixaModel);
            venda.setClienteModel(cliente);
            venda.setFuncionario(funcionario);
         
            listaVenda.add(venda);

        }
        
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

   return listaVenda;
  }
    
    public VendaModel pesquisarDataHora(String data, String hora) throws ParseException, SQLException, NaoEncontradoException{
        
        Connection conexao = getConexao();
        String codigoString;
        ProdutoModel produto = null;
        int codigoInt, codigo;

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM tcc_pedro_v12.venda where ven_data = ? AND ven_hora = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, data);
        execSQL.setString(2, hora);
        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();
        
        VendaModel venda;
        CaixaModel caixaModel;
        CaixaDAO caixaDao;
        
        ClienteModel cliente;
        ClienteDAO clienteDao = null;
        
        FuncionarioModel funcionario;
        FuncionarioDAO funcionarioDao;
        
        if (resultadoConsulta.getRow() > 0) {

            funcionario = new FuncionarioModel();
            funcionarioDao = new FuncionarioDAO();
            venda = new VendaModel();
            caixaModel = new CaixaModel();
            caixaDao = new CaixaDAO();
            clienteDao = new ClienteDAO();
            cliente = new ClienteModel();
            
            int caixaID = resultadoConsulta.getInt("caixa_ca_id");
            String caixaString = String.valueOf(caixaID);
            caixaModel = caixaDao.pesquisar(caixaString);
            
            int ClienteID = resultadoConsulta.getInt("cliente_clie_id");
            cliente = clienteDao.findByID(ClienteID);
            
            int funcionarioID = resultadoConsulta.getInt("funcionario_fun_id");
            funcionario = funcionarioDao.findByID(funcionarioID);
            
            venda.setId(resultadoConsulta.getInt("ven_id"));
            venda.setValor(resultadoConsulta.getDouble("ven_valor_total"));
            venda.setDesconto(resultadoConsulta.getDouble("ven_desconto"));
            venda.setFormaPagamento(resultadoConsulta.getString("ven_forma_pagamento"));
            venda.setDataVenda(resultadoConsulta.getDate("ven_data"));
            venda.setHoraVenda(resultadoConsulta.getTime("ven_hora"));
            venda.setCaixaModel(caixaModel);
            venda.setClienteModel(cliente);
            venda.setFuncionario(funcionario);
            
        } else {
            throw new NaoEncontradoException("Produto não encontrado");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return venda;
    }
    // return venda;
    
    
    public VendaModel pesquisarId(int id) throws SQLException, NaoEncontradoException {
      
        Connection conexao = getConexao();
        String codigoString;
        ProdutoModel produto = null;
        int codigoInt, codigo;

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM venda WHERE ven_id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setInt(1, id);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();
        
        VendaModel venda;
        CaixaModel caixaModel;
        CaixaDAO caixaDao;
        
        ClienteModel cliente;
        ClienteDAO clienteDao = null;
        
        FuncionarioModel funcionario;
        FuncionarioDAO funcionarioDao;
        
        if (resultadoConsulta.getRow() > 0) {

            funcionario = new FuncionarioModel();
            funcionarioDao = new FuncionarioDAO();
            venda = new VendaModel();
            caixaModel = new CaixaModel();
            caixaDao = new CaixaDAO();
            clienteDao = new ClienteDAO();
            cliente = new ClienteModel();
            
            int caixaID = resultadoConsulta.getInt("caixa_ca_id");
            String caixaString = String.valueOf(caixaID);
            caixaModel = caixaDao.pesquisar(caixaString);
            
            int ClienteID = resultadoConsulta.getInt("cliente_clie_id");
            cliente = clienteDao.findByID(ClienteID);
            
            int funcionarioID = resultadoConsulta.getInt("funcionario_fun_id");
            funcionario = funcionarioDao.findByID(funcionarioID);
            
            venda.setId(resultadoConsulta.getInt("ven_id"));
            venda.setValor(resultadoConsulta.getDouble("ven_valor_total"));
            venda.setDesconto(resultadoConsulta.getDouble("ven_desconto"));
            venda.setFormaPagamento(resultadoConsulta.getString("ven_forma_pagamento"));
            venda.setDataVenda(resultadoConsulta.getDate("ven_data"));
            venda.setHoraVenda(resultadoConsulta.getTime("ven_hora"));
            venda.setCaixaModel(caixaModel);
            venda.setClienteModel(cliente);
            venda.setFuncionario(funcionario);
        } else {
            throw new NaoEncontradoException("Produto não encontrado");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return venda;
    }
    
    public ArrayList<VendaModel> pesquisarGraficoPorAnoMes(String anoMes) throws SQLException, NaoEncontradoException {
         ArrayList<VendaModel> listaVenda = new ArrayList<>();
        try {
            
            ResultSet resultadoConsulta;
            Connection conexao = Conexao.getConexao();
            PreparedStatement execSQL;

            execSQL = conexao.prepareStatement("SELECT ven_id, sum(ven_valor_total), ven_desconto, ven_forma_pagamento"
                    + ", ven_data, ven_hora, funcionario_fun_id, caixa_ca_id, "
                    + "cliente_clie_id FROM venda where ven_data LIKE ? GROUP BY ven_data", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                  
            execSQL.setString(1, '%' + anoMes + '%');
            resultadoConsulta = execSQL.executeQuery();
            
            VendaModel venda;
        CaixaModel caixaModel;
        CaixaDAO caixaDao;
        
        ClienteModel cliente;
        ClienteDAO clienteDao = null;
        
        FuncionarioModel funcionario;
        FuncionarioDAO funcionarioDao;
         String data;
        while (resultadoConsulta.next()) {
            funcionario = new FuncionarioModel();
            funcionarioDao = new FuncionarioDAO();
            venda = new VendaModel();
            caixaModel = new CaixaModel();
            caixaDao = new CaixaDAO();
            clienteDao = new ClienteDAO();
            cliente = new ClienteModel();
            
            int caixaID = resultadoConsulta.getInt("caixa_ca_id");
            String caixaString = String.valueOf(caixaID);
            caixaModel = caixaDao.pesquisar(caixaString);
            
            int ClienteID = resultadoConsulta.getInt("cliente_clie_id");
            cliente = clienteDao.findByID(ClienteID);
            
            int funcionarioID = resultadoConsulta.getInt("funcionario_fun_id");
            funcionario = funcionarioDao.findByID(funcionarioID);
            
            venda.setId(resultadoConsulta.getInt("ven_id"));
            venda.setValor(resultadoConsulta.getDouble("sum(ven_valor_total)"));
            venda.setDesconto(resultadoConsulta.getDouble("ven_desconto"));
            venda.setFormaPagamento(resultadoConsulta.getString("ven_forma_pagamento"));
           
            venda.setDataVenda(resultadoConsulta.getDate("ven_data"));
            venda.setHoraVenda(resultadoConsulta.getTime("ven_hora"));
            venda.setCaixaModel(caixaModel);
            venda.setClienteModel(cliente);
            venda.setFuncionario(funcionario);
         
            listaVenda.add(venda);

        }
            
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

   return listaVenda;
    
    }
    
}
   
