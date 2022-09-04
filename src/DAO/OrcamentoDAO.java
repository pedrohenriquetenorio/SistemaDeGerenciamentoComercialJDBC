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
import Modelo.EstoqueModel;
import Modelo.FuncionarioModel;
import Modelo.OrcamentoModel;
import Modelo.ProdutoModel;
import Modelo.VendaModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author pedro
 */
public class OrcamentoDAO {
    
    
    //inserirOrcamento
    //excluirOrcamento
    //AprovarOrcamento *Atualizar Status*+Incluir em Venda
    //PesquisarOrcamento
    
    public void inserir(OrcamentoModel orcamento){
         try (java.sql.Connection conexao = Conexao.getConexao()) {
           
            String comandoSQL = "insert into orcamento (orc_valor_total, orc_data, orc_data_aprovacao, orc_vencimento,"
                    + " orc_forma_pagamento, orc_desconto, funcionario_fun_id, cliente_clie_id)"
                    + "values(?, ?, ?, ?, ?, ?, ? ,?)";
          
            PreparedStatement execSQL;
            execSQL = conexao.prepareStatement(comandoSQL);

            execSQL.setDouble(1, orcamento.getValorTotal());
            execSQL.setTimestamp(2, orcamento.getData());
            execSQL.setTimestamp(3, orcamento.getDataAprovacao());
            execSQL.setTimestamp(4, orcamento.getDataVencimento());
            execSQL.setString(5, orcamento.getFormaPagamento());
            execSQL.setDouble(6, orcamento.getDesconto());
            execSQL.setInt(7, orcamento.getFuncionario().getId());
            execSQL.setInt(8, orcamento.getCliente().getId());
        
            execSQL.executeUpdate();

            conexao.commit();

            execSQL.close();

        } catch (SQLException ex) {
             
             System.err.println(ex.getMessage());
            
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar o Orcamento ", "Erro no Banco de Dados",
            JOptionPane.ERROR_MESSAGE);
            
        }
        
    }
    
    public void Aprovar(OrcamentoModel orcamento) throws SQLException{
    
             System.err.println("**************************");
        try {
            String sql = "UPDATE orcamento SET orc_valor_total = ?, "
                    + "orc_data = ?, orc_data_aprovacao = ?, orc_vencimento = ?, "
                    + "orc_forma_pagamento = ?, orc_desconto= ?, funcionario_fun_id = ?, "
                    + "cliente_clie_id = ? where orc_id = ?"; 
                 Connection conexao;
            PreparedStatement execSQL;
                 
            conexao = Conexao.getConexao();
            execSQL = conexao.prepareStatement(sql);

            execSQL.setDouble(1, orcamento.getValorTotal());
            execSQL.setTimestamp(2, orcamento.getData());
            execSQL.setTimestamp(3, orcamento.getDataAprovacao());
            execSQL.setTimestamp(4, orcamento.getDataVencimento());
            execSQL.setString(5, orcamento.getFormaPagamento());
            execSQL.setDouble(6, orcamento.getDesconto());
            execSQL.setInt(7, orcamento.getFuncionario().getId());
            execSQL.setInt(8, orcamento.getCliente().getId());
            execSQL.setInt(9, orcamento.getOrc_id());
            
            execSQL.executeUpdate();

            conexao.commit();
            execSQL.close();
            conexao.close();
            
        } catch (SQLException se) {
            
            System.out.println("ERRO   " + se);
            throw se;

        }
    
    }
    
    
    
    public OrcamentoModel ultimoOrcamento() throws SQLException{
        
        String codigoString;
        VendaModel venda;

        int codigo;

        Connection conexao = Conexao.getConexao();

        PreparedStatement execSQL;
        
        String sql = "SELECT orcamento.*, funcionario.*, cliente.* " +
                "FROM orcamento " +
                "INNER JOIN cliente on cliente_clie_id = clie_id " +
                "INNER JOIN funcionario on funcionario_fun_id = fun_id ORDER BY orc_id DESC LIMIT 1";
        
        execSQL = conexao.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();
        FuncionarioModel funcionario;
        ClienteModel cliente;
        OrcamentoModel orcamento;
        
        if (resultadoConsulta.getRow() > 0) {
            
            funcionario = new FuncionarioModel();
            cliente = new ClienteModel();
            orcamento = new OrcamentoModel();
            FuncionarioDAO funcionarioDao = new FuncionarioDAO();
            ClienteDAO clienteDao = new ClienteDAO();
            venda = new VendaModel();
            try {
            funcionario = funcionarioDao.findByID(resultadoConsulta.getInt("fun_id"));
           
            cliente = clienteDao.findByID(resultadoConsulta.getInt("Clie_id"));
//            funcionario.setId(resultadoConsulta.getInt("fun_id"));
//            funcionario.setNome(resultadoConsulta.getString("fun_nome"));
//            funcionario.setCpf(resultadoConsulta.getString("fun_cpf"));
//            funcionario.setTelefone(resultadoConsulta.getString("fun_telefone"));
//            funcionario.setCelular(resultadoConsulta.getString("fun_celular"));
//            funcionario.setRua(resultadoConsulta.getString("fun_rua"));
//            funcionario.setNumero(resultadoConsulta.getString("fun_numero"));
//            funcionario.setBairro(resultadoConsulta.getString("fun_bairro"));
//            funcionario.setCidade(resultadoConsulta.getString("fun_cidade"));
//            funcionario.setUf(resultadoConsulta.getString("fun_uf"));
//            funcionario.setCargo(resultadoConsulta.getString("fun_cargo"));
//            funcionario.setCep(resultadoConsulta.getString("fun_cep"));
//            funcionario.setLogin(resultadoConsulta.getString("fun_login"));
//            funcionario.setSenha(resultadoConsulta.getString("fun_senha"));
//            funcionario.setHoraDataLogin(resultadoConsulta.getTimestamp("fun_data_hora_login"));
//            funcionario.setDataCadastro(resultadoConsulta.getDate("fun_data_cadastro"));
//            
//            cliente.setId(resultadoConsulta.getInt("clie_id"));
//            cliente.setNome(resultadoConsulta.getString("clie_nome"));
//            cliente.setCpf(resultadoConsulta.getString("clie_cpf"));
//            cliente.setTelefone(resultadoConsulta.getString("clie_telefone"));
//            cliente.setCelular(resultadoConsulta.getString("clie_celular"));
//            cliente.setRua(resultadoConsulta.getString("clie_rua"));
//            cliente.setNumero(resultadoConsulta.getString("clie_numero"));
//            cliente.setBairro(resultadoConsulta.getString("clie_bairro"));
//            cliente.setCidade(resultadoConsulta.getString("clie_cidade"));
//            cliente.setUf(resultadoConsulta.getString("clie_uf"));
//            cliente.setCep(resultadoConsulta.getString("clie_cep"));
//            cliente.setTipo(resultadoConsulta.getString("clie_tipo"));
//            cliente.setCnpj(resultadoConsulta.getString("clie_cnpj"));
//            cliente.setDataCadastro(resultadoConsulta.getDate("clie_data_cadastro"));
//            
            orcamento.setOrc_id(resultadoConsulta.getInt("orc_id"));
            orcamento.setValorTotal(resultadoConsulta.getDouble("orc_valor_total"));
            orcamento.setFormaPagamento(resultadoConsulta.getString("orc_forma_pagamento"));
            orcamento.setData(resultadoConsulta.getTimestamp("orc_data"));
            orcamento.setDataAprovacao(resultadoConsulta.getTimestamp("orc_data_aprovacao"));
            orcamento.setDataVencimento(resultadoConsulta.getTimestamp("orc_vencimento"));
            orcamento.setDesconto(resultadoConsulta.getDouble("orc_desconto"));
            orcamento.setCliente(cliente);
            orcamento.setFuncionario(funcionario);
            } catch (NaoEncontradoException ex) {
                Logger.getLogger(OrcamentoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            throw new SQLException("Erro, Não foi possivel efetuar busca de orcamento!");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return orcamento;
    
    }
    public String excluir(OrcamentoModel orcamento) throws SQLException{
        
        Connection conexao = Conexao.getConexao();
        int linhas;
       
      
        String comandoSQL = "DELETE FROM orcamento WHERE orc_id = ?";

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement(comandoSQL);

        execSQL.setInt(1, orcamento.getOrc_id());

        linhas = execSQL.executeUpdate();

        conexao.commit();

        execSQL.close();

        conexao.close();

        if (linhas <= 0) {
            throw new SQLException("Não foi possivel remover o Orçamento");
        }

        return "Orcamento Removido!";
   
    }
    
     public OrcamentoModel pesquisarId(int id) throws SQLException, NaoEncontradoException {
      
        Connection conexao = getConexao();
        String codigoString;
        ProdutoModel produto = null;
        int codigoInt, codigo;

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM orcamento WHERE orc_id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setInt(1, id);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();
        OrcamentoModel orcamento;
        ClienteModel cliente;
        ClienteDAO clienteDao = null;
        
        FuncionarioModel funcionario;
        FuncionarioDAO funcionarioDao;
        
        if (resultadoConsulta.getRow() > 0) {
            orcamento = new OrcamentoModel();
            funcionario = new FuncionarioModel();
            funcionarioDao = new FuncionarioDAO();
          
            clienteDao = new ClienteDAO();
            cliente = new ClienteModel();
            
            int ClienteID = resultadoConsulta.getInt("cliente_clie_id");
            cliente = clienteDao.findByID(ClienteID);
            
            int funcionarioID = resultadoConsulta.getInt("funcionario_fun_id");
            funcionario = funcionarioDao.findByID(funcionarioID);
            
            orcamento.setOrc_id(resultadoConsulta.getInt("orc_id"));
            orcamento.setValorTotal(resultadoConsulta.getDouble("orc_valor_total"));
            orcamento.setFormaPagamento(resultadoConsulta.getString("orc_forma_pagamento"));
            orcamento.setData(resultadoConsulta.getTimestamp("orc_data"));
            orcamento.setDataAprovacao(resultadoConsulta.getTimestamp("orc_data_aprovacao"));
            orcamento.setDataVencimento(resultadoConsulta.getTimestamp("orc_vencimento"));
            orcamento.setDesconto(resultadoConsulta.getDouble("orc_desconto"));
            orcamento.setCliente(cliente);
            orcamento.setFuncionario(funcionario);
            
        } else {
            throw new NaoEncontradoException("orcamento não encontrado");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return orcamento;
    }
    
    public ArrayList<OrcamentoModel> findAll() throws SQLException, NaoEncontradoException{
        
        ArrayList<OrcamentoModel>listaOrcamento = new ArrayList<>();
       
        int codigo;
       
        Connection conexao = Conexao.getConexao();

        String comandoSQL = "SELECT * FROM orcamento";
        PreparedStatement execSQL;
        
        execSQL = conexao.prepareStatement(comandoSQL);
        
        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        OrcamentoModel orcamento;
        ClienteModel cliente;
        ClienteDAO clienteDao = null;
        
        FuncionarioModel funcionario;
        FuncionarioDAO funcionarioDao;
        
        while (resultadoConsulta.next()) {
            
            orcamento = new OrcamentoModel();
            funcionario = new FuncionarioModel();
            funcionarioDao = new FuncionarioDAO();
          
            clienteDao = new ClienteDAO();
            cliente = new ClienteModel();
            
            int ClienteID = resultadoConsulta.getInt("cliente_clie_id");
            cliente = clienteDao.findByID(ClienteID);
            
            int funcionarioID = resultadoConsulta.getInt("funcionario_fun_id");
            funcionario = funcionarioDao.findByID(funcionarioID);
            
            orcamento.setOrc_id(resultadoConsulta.getInt("orc_id"));
            orcamento.setValorTotal(resultadoConsulta.getDouble("orc_valor_total"));
            orcamento.setFormaPagamento(resultadoConsulta.getString("orc_forma_pagamento"));
            orcamento.setData(resultadoConsulta.getTimestamp("orc_data"));
            orcamento.setDataAprovacao(resultadoConsulta.getTimestamp("orc_data_aprovacao"));
            orcamento.setDataVencimento(resultadoConsulta.getTimestamp("orc_vencimento"));
            orcamento.setDesconto(resultadoConsulta.getDouble("orc_desconto"));
            orcamento.setCliente(cliente);
            orcamento.setFuncionario(funcionario);
          
            listaOrcamento.add(orcamento);
        }
        
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();
    
     return listaOrcamento;
    } 
    
    
    public ArrayList<OrcamentoModel> pesquisarPorNome(String nome) throws SQLException, NaoEncontradoException{
        
        ArrayList<OrcamentoModel> listaOrcamento = new ArrayList<>();
        ResultSet resultadoConsulta;
        Connection conexao = Conexao.getConexao();
        PreparedStatement execSQL;
        execSQL = conexao.prepareStatement("SELECT * FROM orcamento inner join cliente on clie_id = cliente_clie_id WHERE clie_nome LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        execSQL.setString(1, '%' + nome + '%');
        resultadoConsulta = execSQL.executeQuery();
        
        OrcamentoModel orcamento;
        ClienteModel cliente;
        ClienteDAO clienteDao = null;
        
        FuncionarioModel funcionario;
        FuncionarioDAO funcionarioDao;
        
        while (resultadoConsulta.next()) {
            
            orcamento = new OrcamentoModel();
            funcionario = new FuncionarioModel();
            funcionarioDao = new FuncionarioDAO();
          
            clienteDao = new ClienteDAO();
            cliente = new ClienteModel();
            
            int ClienteID = resultadoConsulta.getInt("cliente_clie_id");
            cliente = clienteDao.findByID(ClienteID);
            
            int funcionarioID = resultadoConsulta.getInt("funcionario_fun_id");
            funcionario = funcionarioDao.findByID(funcionarioID);
            
            orcamento.setOrc_id(resultadoConsulta.getInt("orc_id"));
            orcamento.setValorTotal(resultadoConsulta.getDouble("orc_valor_total"));
            orcamento.setFormaPagamento(resultadoConsulta.getString("orc_forma_pagamento"));
            orcamento.setData(resultadoConsulta.getTimestamp("orc_data"));
            orcamento.setDataAprovacao(resultadoConsulta.getTimestamp("orc_data_aprovacao"));
            orcamento.setDataVencimento(resultadoConsulta.getTimestamp("orc_vencimento"));
            orcamento.setDesconto(resultadoConsulta.getDouble("orc_desconto"));
            orcamento.setCliente(cliente);
            orcamento.setFuncionario(funcionario);
          
            listaOrcamento.add(orcamento);
        }
        
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();
        
        return listaOrcamento;
    
    }

    public ArrayList<OrcamentoModel> pesquisarPorCodigo(String codigo) throws SQLException, NaoEncontradoException{
         
        ArrayList<OrcamentoModel> listaOrcamento = new ArrayList<>();
        ResultSet resultadoConsulta;
        Connection conexao = Conexao.getConexao();
        PreparedStatement execSQL;
        
        execSQL = conexao.prepareStatement("SELECT * FROM orcamento WHERE orc_id LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        execSQL.setString(1, '%' + codigo + '%');
        resultadoConsulta = execSQL.executeQuery();
        
        OrcamentoModel orcamento;
        ClienteModel cliente;
        ClienteDAO clienteDao = null;
        
        FuncionarioModel funcionario;
        FuncionarioDAO funcionarioDao;
        
        while (resultadoConsulta.next()) {
            
            orcamento = new OrcamentoModel();
            funcionario = new FuncionarioModel();
            funcionarioDao = new FuncionarioDAO();
          
            clienteDao = new ClienteDAO();
            cliente = new ClienteModel();
            
            int ClienteID = resultadoConsulta.getInt("cliente_clie_id");
            cliente = clienteDao.findByID(ClienteID);
            
            int funcionarioID = resultadoConsulta.getInt("funcionario_fun_id");
            funcionario = funcionarioDao.findByID(funcionarioID);
            
            orcamento.setOrc_id(resultadoConsulta.getInt("orc_id"));
            orcamento.setValorTotal(resultadoConsulta.getDouble("orc_valor_total"));
            orcamento.setFormaPagamento(resultadoConsulta.getString("orc_forma_pagamento"));
            orcamento.setData(resultadoConsulta.getTimestamp("orc_data"));
            orcamento.setDataAprovacao(resultadoConsulta.getTimestamp("orc_data_aprovacao"));
            orcamento.setDataVencimento(resultadoConsulta.getTimestamp("orc_vencimento"));
            orcamento.setDesconto(resultadoConsulta.getDouble("orc_desconto"));
            orcamento.setCliente(cliente);
            orcamento.setFuncionario(funcionario);
          
            listaOrcamento.add(orcamento);
        }
        
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();
        
        return listaOrcamento;
    }
     
}
