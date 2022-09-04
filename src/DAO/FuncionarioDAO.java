package DAO;

import Control.Exceptions.JaExisteException;
import Control.Exceptions.NaoEncontradoException;
import static DAO.Conexao.getConexao;
import Modelo.CaixaModel;

import Modelo.FuncionarioModel;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FuncionarioDAO {

    public void inserir(FuncionarioModel funcionarioModel) throws SQLException {

        try (java.sql.Connection conexao = Conexao.getConexao()) {

            String comandoSQL = "insert into funcionario (fun_nome, fun_cpf, fun_telefone, fun_celular, fun_rua, fun_numero, "
                    + " fun_bairro, fun_cidade, fun_uf, fun_cargo, fun_cep,fun_login, fun_senha,fun_data_hora_login, fun_data_cadastro, fun_status)"
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement execSQL;
            execSQL = conexao.prepareStatement(comandoSQL);

            execSQL.setString(1, funcionarioModel.getNome());
            execSQL.setString(2, funcionarioModel.getCpf());
            execSQL.setString(3, funcionarioModel.getTelefone());
            execSQL.setString(4, funcionarioModel.getCelular());
            execSQL.setString(5, funcionarioModel.getRua());
            execSQL.setString(6, funcionarioModel.getNumero());
            execSQL.setString(7, funcionarioModel.getBairro());
            execSQL.setString(8, funcionarioModel.getCidade());
            execSQL.setString(9, funcionarioModel.getUf());
            execSQL.setString(10, funcionarioModel.getCargo());
            execSQL.setString(11, funcionarioModel.getCep());
            execSQL.setString(12, funcionarioModel.getLogin());
            execSQL.setString(13, funcionarioModel.getSenha());
            execSQL.setTimestamp(14, funcionarioModel.getHoraDataLogin());
            execSQL.setDate(15, funcionarioModel.getDataCadastro());
            execSQL.setBoolean(16, funcionarioModel.getStatus());
            execSQL.executeUpdate();

            conexao.commit();

            execSQL.close();
            
            JOptionPane.showMessageDialog(null, "Funcionário cadastrado!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
            
        }catch (SQLException ex) {
            System.err.println("ERRO AQUI -> "+ ex.getMessage());
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o cadastro de funcionario ", "ERRO ao cadastrar",
                    JOptionPane.ERROR_MESSAGE);
            
        }
    }

    public ArrayList<FuncionarioModel> findAll() throws SQLException {
        String codigoString;

        int codigo;

        ArrayList<FuncionarioModel> listaFuncionarios = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();

            String comandoSQL = "SELECT * FROM funcionario";

            PreparedStatement execSQL;

            execSQL = conexao.prepareStatement(comandoSQL);

            ResultSet resultadoConsulta;

            resultadoConsulta = execSQL.executeQuery();

            FuncionarioModel funcionario;

            while (resultadoConsulta.next()) {

                funcionario = new FuncionarioModel();
                codigoString = (resultadoConsulta.getString("fun_id"));
                codigo = Integer.parseInt(codigoString);

                funcionario.setId(codigo);
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
                funcionario.setStatus(resultadoConsulta.getBoolean("fun_status"));
                listaFuncionarios.add(funcionario);
            }
            resultadoConsulta.close();
            execSQL.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaFuncionarios;
    }

    public FuncionarioModel pesquisarDataHoralogin() throws SQLException {
        String codigoString;
        FuncionarioModel funcionario;

        int codigo;

        Connection conexao = Conexao.getConexao();

        //funcionario = new FuncionarioModel();

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM funcionario ORDER BY fun_data_hora_login DESC LIMIT 1", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        //execSQL.setString(1, '%'+parametro+'%');
        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();

        if (resultadoConsulta.getRow() > 0) {

            funcionario = new FuncionarioModel();

            codigoString = (resultadoConsulta.getString("fun_id"));
            codigo = Integer.parseInt(codigoString);

            funcionario.setId(codigo);
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
            funcionario.setStatus(resultadoConsulta.getBoolean("fun_status"));

        } else {
            throw new SQLException("Erro, Funcionário não foi encontrado!");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return funcionario;

    }

    public ArrayList<FuncionarioModel> pesquisarNome(String nome) throws SQLException, ParseException, NaoEncontradoException {

        String codigoString;

        int codigo;

        ArrayList<FuncionarioModel> listaFuncionarios = new ArrayList<>();
        //try {
        Connection conexao = Conexao.getConexao();

        PreparedStatement execSQL;
        execSQL = conexao.prepareStatement("SELECT * FROM funcionario WHERE fun_nome LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, '%' + nome + '%');
        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        FuncionarioModel funcionario;

        while (resultadoConsulta.next()) {

            funcionario = new FuncionarioModel();
            codigoString = (resultadoConsulta.getString("fun_id"));
            codigo = Integer.parseInt(codigoString);

            funcionario.setId(codigo);
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
            funcionario.setStatus(resultadoConsulta.getBoolean("fun_status"));

            listaFuncionarios.add(funcionario);

        }
        if (listaFuncionarios.isEmpty()) {
            throw new NaoEncontradoException("Funcionario não encontrado");
        }

//                if(resultadoConsulta.getRow() <= 0){
//                throw new NaoEncontradoException("Funcionario não encontrado");
//                }
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        //  JOptionPane.showMessageDialog(null, "Funcionário cadastrado!", "Sucesso!",JOptionPane.INFORMATION_MESSAGE);
        //} catch (SQLException e) {
        //   JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o cadastro de funcionario","ERRO ao cadastrar",
        //     JOptionPane.ERROR_MESSAGE);
        // }
        return listaFuncionarios;
    }

    public ArrayList<FuncionarioModel> pesquisarNomeEspecifico(String nome) throws SQLException, ParseException, NaoEncontradoException {

        String codigoString;

        int codigo;

        ArrayList<FuncionarioModel> listaFuncionarios = new ArrayList<>();
        //try {
        Connection conexao = Conexao.getConexao();

        PreparedStatement execSQL;
        execSQL = conexao.prepareStatement("SELECT * FROM funcionario WHERE fun_nome = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, nome);
        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        FuncionarioModel funcionario;

        while (resultadoConsulta.next()) {

            funcionario = new FuncionarioModel();
            codigoString = (resultadoConsulta.getString("fun_id"));
            codigo = Integer.parseInt(codigoString);

            funcionario.setId(codigo);
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
            funcionario.setStatus(resultadoConsulta.getBoolean("fun_status"));

            listaFuncionarios.add(funcionario);

        }
        if (listaFuncionarios.isEmpty()) {
            throw new NaoEncontradoException("Funcionario não encontrado");
        }
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        //  JOptionPane.showMessageDialog(null, "Funcionário cadastrado!", "Sucesso!",JOptionPane.INFORMATION_MESSAGE);
        //} catch (SQLException e) {
        //   JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o cadastro de funcionario","ERRO ao cadastrar",
        //     JOptionPane.ERROR_MESSAGE);
        // }
        return listaFuncionarios;
    }

    public FuncionarioModel pesquisar(String parametro, String sql) throws SQLException, NaoEncontradoException {
        Connection conexao = getConexao();
        String codigoString;
        FuncionarioModel funcionario = null;
        int codigoInt, codigo;

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM funcionario WHERE fun_" + sql + " LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, '%' + parametro + '%');

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();

        if (resultadoConsulta.getRow() > 0) {

            funcionario = new FuncionarioModel();

            codigoString = (resultadoConsulta.getString("fun_id"));
            codigo = Integer.parseInt(codigoString);

            funcionario.setId(codigo);
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
            funcionario.setStatus(resultadoConsulta.getBoolean("fun_status"));

        } else {
            throw new NaoEncontradoException("Funcionario não encontrado!");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return funcionario;
    }

    public FuncionarioModel verificaCadastro(String sql, String tipo, String tela) throws SQLException, JaExisteException {

        Connection conexao = getConexao();

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM funcionario WHERE fun_" + tipo + " = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, sql);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();
        FuncionarioModel funcionario = new FuncionarioModel();
        if (resultadoConsulta.getRow() > 0 && resultadoConsulta.getBoolean("fun_status")==true && tela.equals("cadastro")) {
            throw new JaExisteException("Erro, não foi possivel efetuar o cadastro, o " + tipo + " já existe no sistema!");
        }else if(resultadoConsulta.getRow() > 0 && resultadoConsulta.getBoolean("fun_status")==false){
            
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
            funcionario.setStatus(resultadoConsulta.getBoolean("fun_status"));
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();
        return funcionario;
    }

    public String verificaCadastroString(String sql, String tipo, String tela) throws SQLException, JaExisteException {

        Connection conexao = getConexao();

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM funcionario WHERE fun_" + tipo + " = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, sql);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();
        FuncionarioModel funcionario = new FuncionarioModel();
        if (resultadoConsulta.getRow() > 0 && resultadoConsulta.getBoolean("fun_status")==true && tela.equals("cadastro")) {
            throw new JaExisteException("Erro, não foi possivel efetuar o cadastro, o " + tipo + " já existe no sistema!");
        }else if(resultadoConsulta.getRow() > 0 && resultadoConsulta.getBoolean("fun_status")==false){
            
            return "FuncionarioDesativado";
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();
        return "NovoFuncionario";
    }
    
    public void verificaLogin(String login) throws SQLException, JaExisteException {

        Connection conexao = getConexao();

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM funcionario WHERE fun_login = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, login);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();

        if (resultadoConsulta.getRow() > 0) {
            throw new JaExisteException("Erro, o login informado já existe!");
        } 

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

    }

    public Boolean pesquisaLogin(String login) throws SQLException {

        Boolean verifica = false;

        Connection conexao = getConexao();

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM funcionario WHERE fun_login = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, login);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();

        if (resultadoConsulta.getRow() > 0) {
            verifica = true;
        } 

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return verifica;
    }

    public FuncionarioModel pesquisaLoginSenha(String login, String senha) throws SQLException, NaoEncontradoException {
        System.out.println("PING LOGIN SENHA " + login +" "+ senha);
        String codigoString;
        int codigo;
        FuncionarioModel funcionario = new FuncionarioModel();

        Connection conexao = getConexao();

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM funcionario WHERE fun_login = ? AND fun_senha = ? AND fun_status = true", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, login);
        execSQL.setString(2, senha);
        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();

        if (resultadoConsulta.getRow() <= 0) 
        {

            throw new NaoEncontradoException();
        } else {
      
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
            funcionario.setStatus(resultadoConsulta.getBoolean("fun_status"));

            resultadoConsulta.close();
            execSQL.close();
            conexao.close();
        }
        return funcionario;
    }

    public String excluir(FuncionarioModel funcionario) throws SQLException, NaoEncontradoException {

        String sql = "UPDATE funcionario SET fun_nome = ?, fun_cpf = ?, fun_telefone = ?, fun_celular=?, fun_rua=?, fun_numero=?,"
                + "fun_bairro=?, fun_cidade = ?, fun_uf = ?, fun_cargo=?, fun_cep = ?, fun_login =?,fun_senha=?, fun_data_hora_login=?,"
                + " fun_data_cadastro=?, fun_status=? WHERE fun_id = ? ";

        Connection conexao;
        PreparedStatement execSQL;

        conexao = Conexao.getConexao();
        execSQL = conexao.prepareStatement(sql);

        execSQL.setString(1, funcionario.getNome());
        execSQL.setString(2, funcionario.getCpf());
        execSQL.setString(3, funcionario.getTelefone());
        execSQL.setString(4, funcionario.getCelular());
        execSQL.setString(5, funcionario.getRua());
        execSQL.setString(6, funcionario.getNumero());
        execSQL.setString(7, funcionario.getBairro());
        execSQL.setString(8, funcionario.getCidade());
        execSQL.setString(9, funcionario.getUf());
        execSQL.setString(10, funcionario.getCargo());
        execSQL.setString(11, funcionario.getCep());
        execSQL.setString(12, funcionario.getLogin());
        execSQL.setString(13, funcionario.getSenha());
        execSQL.setTimestamp(14, funcionario.getHoraDataLogin());
        execSQL.setDate(15, funcionario.getDataCadastro());
        execSQL.setBoolean(16, funcionario.getStatus());
        execSQL.setInt(17, funcionario.getId());

        execSQL.executeUpdate();
        conexao.commit();
        execSQL.close();
        conexao.close();

        return "Funcionario Excluido!";
    }

    public void alterar(FuncionarioModel funcionario) throws SQLException {
        String sql = "UPDATE funcionario SET fun_nome = ?, fun_cpf = ?, fun_telefone = ?, fun_celular=?, fun_rua=?, fun_numero=?,"
                + "fun_bairro=?, fun_cidade = ?, fun_uf = ?, fun_cargo=?, fun_cep = ?, fun_login =?,fun_senha=?, fun_data_hora_login=?,"
                + " fun_data_cadastro=?, fun_status=? WHERE fun_id = ? ";

        Connection conexao;
        PreparedStatement execSQL;

        conexao = Conexao.getConexao();
        execSQL = conexao.prepareStatement(sql);

        execSQL.setString(1, funcionario.getNome());
        execSQL.setString(2, funcionario.getCpf());
        execSQL.setString(3, funcionario.getTelefone());
        execSQL.setString(4, funcionario.getCelular());
        execSQL.setString(5, funcionario.getRua());
        execSQL.setString(6, funcionario.getNumero());
        execSQL.setString(7, funcionario.getBairro());
        execSQL.setString(8, funcionario.getCidade());
        execSQL.setString(9, funcionario.getUf());
        execSQL.setString(10, funcionario.getCargo());
        execSQL.setString(11, funcionario.getCep());
        execSQL.setString(12, funcionario.getLogin());
        execSQL.setString(13, funcionario.getSenha());
        execSQL.setTimestamp(14, funcionario.getHoraDataLogin());
        execSQL.setDate(15, funcionario.getDataCadastro());
        execSQL.setBoolean(16, funcionario.getStatus());
        execSQL.setInt(17, funcionario.getId());

        execSQL.executeUpdate();
        conexao.commit();
        execSQL.close();
        conexao.close();

    }

      public FuncionarioModel findByID(int id) throws SQLException, NaoEncontradoException{
        
        Connection conexao = getConexao();

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM funcionario WHERE fun_id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setInt(1, id);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();
        
        resultadoConsulta.last();
        FuncionarioModel funcionario = new FuncionarioModel();
         if (resultadoConsulta.getRow() > 0) {
           
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
            funcionario.setStatus(resultadoConsulta.getBoolean("fun_status"));

         }
        
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();
        
    
    return funcionario;
    }
    
    
}
