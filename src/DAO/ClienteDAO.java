package DAO;

import Control.Exceptions.JaExisteException;
import Control.Exceptions.NaoEncontradoException;
import static DAO.Conexao.getConexao;
import Modelo.ClienteModel;
import Modelo.FuncionarioModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Modelo.VendaModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ClienteDAO {

    public void inserir(ClienteModel clienteModel) throws SQLException {
        System.err.println("1    "+clienteModel.getId());
        try (
              java.sql.Connection conexao = Conexao.getConexao()) {

           
            String comandoSQL = "insert into cliente (clie_nome, clie_cpf, clie_telefone, clie_celular, clie_rua, clie_numero, "
                    + "clie_bairro, clie_cidade, clie_uf, clie_cep, clie_tipo, clie_cnpj, clie_data_cadastro, clie_status)"
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?)";

            PreparedStatement execSQL;

            execSQL = conexao.prepareStatement(comandoSQL);

            execSQL.setString(1, clienteModel.getNome());
            
            execSQL.setString(2, clienteModel.getCpf());
            
            execSQL.setString(3, clienteModel.getTelefone());
            
            execSQL.setString(4, clienteModel.getCelular());
            
            execSQL.setString(5, clienteModel.getRua());
            
            execSQL.setString(6, clienteModel.getNumero());
            
            execSQL.setString(7, clienteModel.getBairro());
            
            execSQL.setString(8, clienteModel.getCidade());
            
            execSQL.setString(9, clienteModel.getUf());
            
            execSQL.setString(10, clienteModel.getCep());
            
            execSQL.setString(11, clienteModel.getTipo());
            
            execSQL.setString(12, clienteModel.getCnpj());
            
            execSQL.setDate(13, clienteModel.getDataCadastro());
            execSQL.setBoolean(14, clienteModel.getStatus());
            
            execSQL.executeUpdate();
            
            conexao.commit();
            
            execSQL.close();

            JOptionPane.showMessageDialog(null, "Cliente cadastrado!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
System.err.println("ex    "+ex.getMessage());
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o cadastro de cliente", "ERRO ao cadastrar",
                    JOptionPane.ERROR_MESSAGE);
            
        }
    }

    public ArrayList<ClienteModel> findAll() throws SQLException, ParseException {
        String codigoString;

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        int codigo;

        ArrayList<ClienteModel> listaClientes = new ArrayList<>();

        try {
            Connection conexao = Conexao.getConexao();

            String comandoSQL = "SELECT * FROM cliente";

            PreparedStatement execSQL;

            execSQL = conexao.prepareStatement(comandoSQL);

            ResultSet resultadoConsulta;

            resultadoConsulta = execSQL.executeQuery();

            ClienteModel cliente;

            while (resultadoConsulta.next()) {

                cliente = new ClienteModel();
                codigoString = (resultadoConsulta.getString("clie_id"));
                codigo = Integer.parseInt(codigoString);

                cliente.setId(codigo);
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
                cliente.setStatus(resultadoConsulta.getBoolean("clie_status"));
                listaClientes.add(cliente);
            }

            resultadoConsulta.close();
            execSQL.close();
            conexao.close();

        } catch (SQLException e) {

        }

        return listaClientes;
    }

    //PESQUISAR POR NOME 
    public ArrayList<ClienteModel> pesquisarNome(String nome) throws SQLException, NaoEncontradoException {
        String codigoString;

        int codigo;

        ArrayList<ClienteModel> listaClientes = new ArrayList<>();

        Connection conexao = Conexao.getConexao();

        PreparedStatement execSQL;
        execSQL = conexao.prepareStatement("SELECT * FROM cliente WHERE clie_nome LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, '%' + nome + '%');
        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        ClienteModel cliente;

        while (resultadoConsulta.next()) {

            cliente = new ClienteModel();
            codigoString = (resultadoConsulta.getString("clie_id"));
            codigo = Integer.parseInt(codigoString);
       
            cliente.setId(codigo);
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
             cliente.setStatus(resultadoConsulta.getBoolean("clie_status"));
            listaClientes.add(cliente);

        }
        if (listaClientes.isEmpty()) {
            throw new NaoEncontradoException("Cliente não encontrado");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return listaClientes;
    }

    public ArrayList<ClienteModel> pesquisarNomeEspecifico(String nome) throws SQLException, NaoEncontradoException {
        String codigoString;

        int codigo;

        ArrayList<ClienteModel> listaClientes = new ArrayList<>();

        Connection conexao = Conexao.getConexao();

        PreparedStatement execSQL;
        execSQL = conexao.prepareStatement("SELECT * FROM cliente WHERE clie_nome = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, nome);
        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        ClienteModel cliente;

        while (resultadoConsulta.next()) {

            cliente = new ClienteModel();
            codigoString = (resultadoConsulta.getString("clie_id"));
            codigo = Integer.parseInt(codigoString);
          
            cliente.setId(codigo);
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
             cliente.setStatus(resultadoConsulta.getBoolean("clie_status"));
            listaClientes.add(cliente);

        }
        if (listaClientes.isEmpty()) {
            throw new NaoEncontradoException("Cliente não encontrado");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return listaClientes;
    }

    public ClienteModel verificaCadastro(String sql, String tipo, String tela) throws SQLException, JaExisteException {

        Boolean verifica = false;

        Connection conexao = getConexao();

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM cliente WHERE clie_" + tipo + " = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, sql);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();
        ClienteModel cliente = new ClienteModel();
        try {
        if (resultadoConsulta.getRow() > 0 && resultadoConsulta.getBoolean("clie_status")==true && tela.equals("cadastro")) {
            
            throw new JaExisteException("Erro, não foi possivel efetuar o cadastro, o " + tipo + " já existe no sistema!");
            
        }else if(resultadoConsulta.getRow() > 0 && resultadoConsulta.getBoolean("clie_status")==false){
            
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
            cliente.setStatus(resultadoConsulta.getBoolean("clie_status"));
            
        }
        
        } catch (SQLException ex) {
            return cliente = null;
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();
        return cliente;
    }

   public String verificaCadastroString(String sql, String tipo, String tela) throws SQLException, JaExisteException {

        Boolean verifica = false;

        Connection conexao = getConexao();

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM cliente WHERE clie_" + tipo + " = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, sql);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();
        ClienteModel cliente = new ClienteModel();
        
        if (resultadoConsulta.getRow() > 0 && resultadoConsulta.getBoolean("clie_status")==true && tela.equals("cadastro")) {
            
            throw new JaExisteException("Erro, não foi possivel efetuar o cadastro, o " + tipo + " já existe no sistema!");
            
        }else if(resultadoConsulta.getRow() > 0 && resultadoConsulta.getBoolean("clie_status")==false){
            
            return "ClienteDesativado";
            
        }
        
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();
        return "NovoCadastro";
    }
    public ClienteModel findByID(int id) throws SQLException{
    
        Connection conexao = getConexao();

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM cliente WHERE clie_id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setInt(1, id);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();
        
        resultadoConsulta.last();
        ClienteModel cliente = new ClienteModel();
         if (resultadoConsulta.getRow() > 0) {
           
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
             cliente.setStatus(resultadoConsulta.getBoolean("clie_status"));
         }
        
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();
        
    return cliente;
    }
    public ClienteModel findByCPF(String cpf) throws SQLException{
    
        Connection conexao = getConexao();

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM cliente WHERE clie_cpf = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, cpf);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();
        
        resultadoConsulta.last();
        ClienteModel cliente = new ClienteModel();
         if (resultadoConsulta.getRow() > 0) {
           
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
             cliente.setStatus(resultadoConsulta.getBoolean("clie_status"));
         }
        
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();
        
    return cliente;
    }
    public ClienteModel pesquisar(String parametro, String tipo) throws SQLException, NaoEncontradoException {

        String codigoString;
        ClienteModel cliente = null;
        int codigoInt, codigo;

        Connection conexao = getConexao();

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM cliente WHERE clie_" + tipo + " LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, '%' + parametro + '%');

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();

        if (resultadoConsulta.getRow() > 0) {

            cliente = new ClienteModel();
            codigoString = (resultadoConsulta.getString("clie_id"));
            codigo = Integer.parseInt(codigoString);

            cliente.setId(codigo);
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
             cliente.setStatus(resultadoConsulta.getBoolean("clie_status"));
        } else if (resultadoConsulta.getRow() <= 0) {

            throw new NaoEncontradoException("O " + tipo + " do cliente não foi encontrado!");

        } else {
            throw new SQLException("Erro ao consultar cliente");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return cliente;

    }

    public String excluir(ClienteModel clienteModel) throws SQLException, NaoEncontradoException {

         String sql = "UPDATE cliente SET clie_nome = ?, clie_cpf = ?, clie_telefone = ?, clie_celular=?, clie_rua=?, clie_numero=?,"
                + "clie_bairro=?, clie_cidade = ?, clie_uf = ?, clie_cep = ?, clie_tipo = ?, clie_cnpj = ?, clie_data_cadastro=?, clie_status = ?"
                + " WHERE clie_id = ? ";
        try {
            Connection conexao;
            PreparedStatement execSQL;
            conexao = Conexao.getConexao();
            execSQL = conexao.prepareStatement(sql);

            execSQL.setString(1, clienteModel.getNome());
            execSQL.setString(2, clienteModel.getCpf());
            execSQL.setString(3, clienteModel.getTelefone());
            execSQL.setString(4, clienteModel.getCelular());
             execSQL.setString(5, clienteModel.getRua());
            execSQL.setString(6, clienteModel.getNumero());
            execSQL.setString(7, clienteModel.getBairro());
            execSQL.setString(8, clienteModel.getCidade());
            execSQL.setString(9, clienteModel.getUf());
            execSQL.setString(10, clienteModel.getCep());
            execSQL.setString(11, clienteModel.getTipo());
            execSQL.setString(12, clienteModel.getCnpj());
            execSQL.setDate(13, clienteModel.getDataCadastro());
            execSQL.setBoolean(14, clienteModel.getStatus());
            execSQL.setInt(15, clienteModel.getId());

            execSQL.executeUpdate();

            conexao.commit();
            execSQL.close();
            conexao.close();

        } catch (SQLException ex) {
            ex.fillInStackTrace();
        }
        
        return "Cliente Excluido!";
    }

    public void alterar(ClienteModel clienteModel) throws SQLException {

        String sql = "UPDATE cliente SET clie_nome = ?, clie_cpf = ?, clie_telefone = ?, clie_celular=?, clie_rua=?, clie_numero=?,"
                + "clie_bairro=?, clie_cidade = ?, clie_uf = ?, clie_cep = ?, clie_tipo = ?, clie_cnpj = ?, clie_data_cadastro=?, clie_status = ?"
                + " WHERE clie_id = ? ";
        try {
            Connection conexao;
            PreparedStatement execSQL;
            conexao = Conexao.getConexao();
            execSQL = conexao.prepareStatement(sql);

            execSQL.setString(1, clienteModel.getNome());
            execSQL.setString(2, clienteModel.getCpf());
            execSQL.setString(3, clienteModel.getTelefone());
            execSQL.setString(4, clienteModel.getCelular());
            execSQL.setString(5, clienteModel.getRua());
            execSQL.setString(6, clienteModel.getNumero());
            execSQL.setString(7, clienteModel.getBairro());
            execSQL.setString(8, clienteModel.getCidade());
            execSQL.setString(9, clienteModel.getUf());
            execSQL.setString(10, clienteModel.getCep());
            execSQL.setString(11, clienteModel.getTipo());
            execSQL.setString(12, clienteModel.getCnpj());
            execSQL.setDate(13, clienteModel.getDataCadastro());
            execSQL.setBoolean(14, clienteModel.getStatus());
            execSQL.setInt(15, clienteModel.getId());

            execSQL.executeUpdate();

            conexao.commit();
            execSQL.close();
            conexao.close();

        } catch (SQLException ex) {
            ex.fillInStackTrace();
        }
    }
}
