package DAO;

import Control.Exceptions.JaExisteException;
import Control.Exceptions.NaoEncontradoException;
import static DAO.Conexao.getConexao;

import Modelo.FornecedorModel;
import Modelo.FuncionarioModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FornecedorDAO {

    public void inserir(FornecedorModel fornecedorModel) throws SQLException {

        try (java.sql.Connection conexao = Conexao.getConexao()) {

            String comandoSQL = "insert into fornecedor (for_nome, for_cnpj, for_telefone, for_celular, for_email, "
                    + "for_rua, for_numero, for_bairro, for_cidade, for_uf, for_cep,for_categoria, for_data_cadastro, for_status)"
                    + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";

            PreparedStatement execSQL;
            execSQL = conexao.prepareStatement(comandoSQL);

            execSQL.setString(1, fornecedorModel.getNome());
            execSQL.setString(2, fornecedorModel.getCnpj());
            execSQL.setString(3, fornecedorModel.getTelefone());
            execSQL.setString(4, fornecedorModel.getCelular());
            execSQL.setString(5, fornecedorModel.getEmail());
            execSQL.setString(6, fornecedorModel.getRua());
            execSQL.setString(7, fornecedorModel.getNumero());
            execSQL.setString(8, fornecedorModel.getBairro());
            execSQL.setString(9, fornecedorModel.getCidade());
            execSQL.setString(10, fornecedorModel.getUf());
            execSQL.setString(11, fornecedorModel.getCep());
            execSQL.setString(12, fornecedorModel.getCategoria());
            execSQL.setDate(13, fornecedorModel.getDataCadastro());
            execSQL.setBoolean(14, fornecedorModel.getStatus());
            
            execSQL.executeUpdate();

            conexao.commit();

            execSQL.close();

            JOptionPane.showMessageDialog(null, "Fornecedor cadastrado!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o cadastro de fornecedor ", "ERRO ao cadastrar",
                    JOptionPane.ERROR_MESSAGE);
            
        }

    }

    public ArrayList<FornecedorModel> pesquisarNome(String nome) throws SQLException, ParseException, NaoEncontradoException {

        String codigoString;

        int codigo;

        ArrayList<FornecedorModel> listaFornecedores = new ArrayList<>();

        Connection conexao = Conexao.getConexao();

        PreparedStatement execSQL;
        execSQL = conexao.prepareStatement("SELECT * FROM fornecedor WHERE for_nome LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, '%' + nome + '%');
        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        FornecedorModel fornecedor;

        while (resultadoConsulta.next()) {

            fornecedor = new FornecedorModel();
            codigoString = (resultadoConsulta.getString("for_id"));
            codigo = Integer.parseInt(codigoString);

            fornecedor.setId(codigo);
            fornecedor.setNome(resultadoConsulta.getString("for_nome"));
            fornecedor.setCnpj(resultadoConsulta.getString("for_cnpj"));
            fornecedor.setTelefone(resultadoConsulta.getString("for_telefone"));
            fornecedor.setCelular(resultadoConsulta.getString("for_celular"));
            fornecedor.setEmail(resultadoConsulta.getString("for_email"));
            fornecedor.setRua(resultadoConsulta.getString("for_rua"));
            fornecedor.setNumero(resultadoConsulta.getString("for_numero"));
            fornecedor.setBairro(resultadoConsulta.getString("for_bairro"));
            fornecedor.setCidade(resultadoConsulta.getString("for_cidade"));
            fornecedor.setUf(resultadoConsulta.getString("for_uf"));
            fornecedor.setCep(resultadoConsulta.getString("for_cep"));
            fornecedor.setCategoria(resultadoConsulta.getString("for_categoria"));
            fornecedor.setDataCadastro(resultadoConsulta.getDate("for_data_cadastro"));
            fornecedor.setStatus(resultadoConsulta.getBoolean("for_status"));
       
            listaFornecedores.add(fornecedor);

        }

        if (listaFornecedores.isEmpty()) {
            throw new NaoEncontradoException("Fornecedor não encontrado");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return listaFornecedores;

    }

    public ArrayList<FornecedorModel> pesquisarNomeEspecifico(String nome) throws SQLException, ParseException, NaoEncontradoException {

        String codigoString;

        int codigo;

        ArrayList<FornecedorModel> listaFornecedores = new ArrayList<>();

        Connection conexao = Conexao.getConexao();

        PreparedStatement execSQL;
        execSQL = conexao.prepareStatement("SELECT * FROM fornecedor WHERE for_nome = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, nome);
        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        FornecedorModel fornecedor;

        while (resultadoConsulta.next()) {

            fornecedor = new FornecedorModel();
            codigoString = (resultadoConsulta.getString("for_id"));
            codigo = Integer.parseInt(codigoString);

            fornecedor.setId(codigo);
            fornecedor.setNome(resultadoConsulta.getString("for_nome"));
            fornecedor.setCnpj(resultadoConsulta.getString("for_cnpj"));
            fornecedor.setTelefone(resultadoConsulta.getString("for_telefone"));
            fornecedor.setCelular(resultadoConsulta.getString("for_celular"));
            fornecedor.setEmail(resultadoConsulta.getString("for_email"));
            fornecedor.setRua(resultadoConsulta.getString("for_rua"));
            fornecedor.setNumero(resultadoConsulta.getString("for_numero"));
            fornecedor.setBairro(resultadoConsulta.getString("for_bairro"));
            fornecedor.setCidade(resultadoConsulta.getString("for_cidade"));
            fornecedor.setUf(resultadoConsulta.getString("for_uf"));
            fornecedor.setCep(resultadoConsulta.getString("for_cep"));
            fornecedor.setCategoria(resultadoConsulta.getString("for_categoria"));
            fornecedor.setDataCadastro(resultadoConsulta.getDate("for_data_cadastro"));
            fornecedor.setStatus(resultadoConsulta.getBoolean("for_status"));
          
            listaFornecedores.add(fornecedor);

        }

        if (listaFornecedores.isEmpty()) {
            throw new NaoEncontradoException("Fornecedor não encontrado");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return listaFornecedores;

    }

    public FornecedorModel verificaCadastro(String sql, String tipo, String tela) throws SQLException, JaExisteException {

        Boolean verifica = false;

        Connection conexao = getConexao();

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM fornecedor WHERE for_" + tipo + " = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, sql);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();
        FornecedorModel fornecedor = new FornecedorModel();
        
        if (resultadoConsulta.getRow() > 0 && resultadoConsulta.getBoolean("for_status")==true && tela.equals("cadastro")) {

            throw new JaExisteException("Erro, não foi possivel efetuar o cadastro, o " + tipo + " já existe no sistema!");

        }else if(resultadoConsulta.getRow() > 0 && resultadoConsulta.getBoolean("for_status")==false){
            
                fornecedor.setId(resultadoConsulta.getInt("for_id"));
                fornecedor.setNome(resultadoConsulta.getString("for_nome"));
                fornecedor.setCnpj(resultadoConsulta.getString("for_cnpj"));
                fornecedor.setTelefone(resultadoConsulta.getString("for_telefone"));
                fornecedor.setCelular(resultadoConsulta.getString("for_celular"));
                fornecedor.setEmail(resultadoConsulta.getString("for_email"));
                fornecedor.setRua(resultadoConsulta.getString("for_rua"));
                fornecedor.setNumero(resultadoConsulta.getString("for_numero"));
                fornecedor.setBairro(resultadoConsulta.getString("for_bairro"));
                fornecedor.setCidade(resultadoConsulta.getString("for_cidade"));
                fornecedor.setUf(resultadoConsulta.getString("for_uf"));
                fornecedor.setCep(resultadoConsulta.getString("for_cep"));
                fornecedor.setCategoria(resultadoConsulta.getString("for_categoria"));
                fornecedor.setDataCadastro(resultadoConsulta.getDate("for_data_cadastro"));
                fornecedor.setStatus(resultadoConsulta.getBoolean("for_status"));
        
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();
        return fornecedor;
    }
    
    public String verificaCadastroString(String sql, String tipo, String tela) throws SQLException, JaExisteException {

        Boolean verifica = false;

        Connection conexao = getConexao();

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM fornecedor WHERE for_" + tipo + " = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, sql);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();
        FornecedorModel fornecedor = new FornecedorModel();
        
        if (resultadoConsulta.getRow() > 0 && resultadoConsulta.getBoolean("for_status")==true && tela.equals("cadastro")) {

            throw new JaExisteException("Erro, não foi possivel efetuar o cadastro, o " + tipo + " já existe no sistema!");

        }else if(resultadoConsulta.getRow() > 0 && resultadoConsulta.getBoolean("for_status")==false){
            
                return "FonecedorDesativado";
        
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();
        
        return "novoCadastro";
    }
    
    public ArrayList<FornecedorModel> findAll() throws SQLException {
        String codigoString;
        int codigo;

        ArrayList<FornecedorModel> listaFornecedores = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();

            String comandoSQL = "SELECT * FROM fornecedor";

            PreparedStatement execSQL;

            execSQL = conexao.prepareStatement(comandoSQL);

            ResultSet resultadoConsulta;

            resultadoConsulta = execSQL.executeQuery();
            FornecedorModel fornecedor;
            while (resultadoConsulta.next()) {

                fornecedor = new FornecedorModel();
                fornecedor.setId(resultadoConsulta.getInt("for_id"));
                fornecedor.setNome(resultadoConsulta.getString("for_nome"));
                fornecedor.setCnpj(resultadoConsulta.getString("for_cnpj"));
                fornecedor.setTelefone(resultadoConsulta.getString("for_telefone"));
                fornecedor.setCelular(resultadoConsulta.getString("for_celular"));
                fornecedor.setEmail(resultadoConsulta.getString("for_email"));
                fornecedor.setRua(resultadoConsulta.getString("for_rua"));
                fornecedor.setNumero(resultadoConsulta.getString("for_numero"));
                fornecedor.setBairro(resultadoConsulta.getString("for_bairro"));
                fornecedor.setCidade(resultadoConsulta.getString("for_cidade"));
                fornecedor.setUf(resultadoConsulta.getString("for_uf"));
                fornecedor.setCep(resultadoConsulta.getString("for_cep"));
                fornecedor.setCategoria(resultadoConsulta.getString("for_categoria"));
                fornecedor.setDataCadastro(resultadoConsulta.getDate("for_data_cadastro"));
                fornecedor.setStatus(resultadoConsulta.getBoolean("for_status"));
                listaFornecedores.add(fornecedor);
            }
            resultadoConsulta.close();
            execSQL.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaFornecedores;
    }
    
    public FornecedorModel findByName(String nome) throws SQLException, NaoEncontradoException{
    
        Connection conexao = getConexao();

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM fornecedor WHERE for_nome = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, nome);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();
        FornecedorModel fornecedor; 
        if (resultadoConsulta.getRow() > 0) {

            fornecedor = new FornecedorModel();         
            fornecedor.setId(resultadoConsulta.getInt("for_id"));         
            fornecedor.setNome(resultadoConsulta.getString("for_nome"));         
            fornecedor.setCnpj(resultadoConsulta.getString("for_cnpj"));         
            fornecedor.setTelefone(resultadoConsulta.getString("for_telefone"));          
            fornecedor.setCelular(resultadoConsulta.getString("for_celular"));          
            fornecedor.setEmail(resultadoConsulta.getString("for_email"));          
            fornecedor.setRua(resultadoConsulta.getString("for_rua"));           
            fornecedor.setNumero(resultadoConsulta.getString("for_numero"));           
            fornecedor.setBairro(resultadoConsulta.getString("for_bairro"));           
            fornecedor.setCidade(resultadoConsulta.getString("for_cidade"));          
            fornecedor.setUf(resultadoConsulta.getString("for_uf"));        
            fornecedor.setCep(resultadoConsulta.getString("for_cep"));
            fornecedor.setCategoria(resultadoConsulta.getString("for_categoria"));
            fornecedor.setDataCadastro(resultadoConsulta.getDate("for_data_cadastro"));
            fornecedor.setStatus(resultadoConsulta.getBoolean("for_status"));
        } else {
            throw new NaoEncontradoException("Fornecedor não encontrado!");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();
        
        return fornecedor;
    }
    
    
    
    public FornecedorModel pesquisar(String cnpj, String sql) throws SQLException, NaoEncontradoException {

        Connection conexao = getConexao();
        String codigoString;
        FornecedorModel fornecedor = null;
        int codigoInt, codigo;

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM fornecedor WHERE for_" + sql + " LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, '%' + cnpj + '%');

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();

        if (resultadoConsulta.getRow() > 0) {

            fornecedor = new FornecedorModel();
            codigoString = (resultadoConsulta.getString("for_id"));
            codigo = Integer.parseInt(codigoString);

            fornecedor.setId(codigo);
            
            fornecedor.setNome(resultadoConsulta.getString("for_nome"));
            
            fornecedor.setCnpj(resultadoConsulta.getString("for_cnpj"));
            
            fornecedor.setTelefone(resultadoConsulta.getString("for_telefone"));
            
            fornecedor.setCelular(resultadoConsulta.getString("for_celular"));
            
            fornecedor.setEmail(resultadoConsulta.getString("for_email"));
            
            fornecedor.setRua(resultadoConsulta.getString("for_rua"));
            
            fornecedor.setNumero(resultadoConsulta.getString("for_numero"));
            
            fornecedor.setBairro(resultadoConsulta.getString("for_bairro"));
            
            fornecedor.setCidade(resultadoConsulta.getString("for_cidade"));
            
            fornecedor.setUf(resultadoConsulta.getString("for_uf"));
            
            fornecedor.setCep(resultadoConsulta.getString("for_cep"));
            fornecedor.setCategoria(resultadoConsulta.getString("for_categoria"));
            fornecedor.setDataCadastro(resultadoConsulta.getDate("for_data_cadastro"));
            fornecedor.setStatus(resultadoConsulta.getBoolean("for_status"));
        } else {
            throw new NaoEncontradoException("Fornecedor não encontrado!");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return fornecedor;
    }

    public String excluir(FornecedorModel fornecedor) throws SQLException {

         String sql = "UPDATE fornecedor SET for_nome = ?, for_cnpj = ?, for_telefone = ?, for_celular=?, for_email=? ,for_rua=?, for_numero=?,"
                + "for_bairro=?, for_cidade = ?, for_uf = ?, for_cep = ?,for_categoria = ?, for_data_cadastro=?, for_status = ? "
                + "WHERE for_id = ? ";

        Connection conexao;
        PreparedStatement execSQL;

        try {

            conexao = Conexao.getConexao();
            execSQL = conexao.prepareStatement(sql);

            execSQL.setString(1, fornecedor.getNome());
            execSQL.setString(2, fornecedor.getCnpj());
            execSQL.setString(3, fornecedor.getTelefone());
            execSQL.setString(4, fornecedor.getCelular());
            execSQL.setString(5, fornecedor.getEmail());
            execSQL.setString(6, fornecedor.getRua());
            execSQL.setString(7, fornecedor.getNumero());
            execSQL.setString(8, fornecedor.getBairro());
            execSQL.setString(9, fornecedor.getCidade());
            execSQL.setString(10, fornecedor.getUf());
            execSQL.setString(11, fornecedor.getCep());
            execSQL.setString(12, fornecedor.getCategoria());
            execSQL.setDate(13, fornecedor.getDataCadastro());
            execSQL.setBoolean(14, fornecedor.getStatus());
            execSQL.setInt(15, fornecedor.getId());
            
            execSQL.executeUpdate();

            conexao.commit();
            execSQL.close();
            conexao.close();
        } catch (SQLException se) {
            System.out.println("ERRO   " + se);
            throw se;

        }

        return "Fornecedor Excluido!";
    }

    public void alterar(FornecedorModel fornecedor) throws SQLException {
        String sql = "UPDATE fornecedor SET for_nome = ?, for_cnpj = ?, for_telefone = ?, for_celular=?, for_email=? ,for_rua=?, for_numero=?,"
                + "for_bairro=?, for_cidade = ?, for_uf = ?, for_cep = ?, for_categoria = ?, for_data_cadastro=?, for_status = ? "
                + "WHERE for_id = ? ";

        Connection conexao;
        PreparedStatement execSQL;

        try {

            conexao = Conexao.getConexao();
            execSQL = conexao.prepareStatement(sql);

            execSQL.setString(1, fornecedor.getNome());
            execSQL.setString(2, fornecedor.getCnpj());
            execSQL.setString(3, fornecedor.getTelefone());
            execSQL.setString(4, fornecedor.getCelular());
            execSQL.setString(5, fornecedor.getEmail());
            execSQL.setString(6, fornecedor.getRua());
            execSQL.setString(7, fornecedor.getNumero());
            execSQL.setString(8, fornecedor.getBairro());
            execSQL.setString(9, fornecedor.getCidade());
            execSQL.setString(10, fornecedor.getUf());
            execSQL.setString(11, fornecedor.getCep());
             execSQL.setString(12, fornecedor.getCategoria());
            execSQL.setDate(13, fornecedor.getDataCadastro());
            execSQL.setBoolean(14, fornecedor.getStatus());
            execSQL.setInt(15, fornecedor.getId());
            
            execSQL.executeUpdate();

            conexao.commit();
            execSQL.close();
            conexao.close();
        } catch (SQLException se) {
            System.out.println("ERRO   " + se);
            throw se;

        }

    }
    
    public FornecedorModel findByID(int id) throws SQLException, NaoEncontradoException{
        
        Connection conexao = getConexao();

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM fornecedor WHERE for_id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setInt(1, id);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();
        
        resultadoConsulta.last();
        FornecedorModel fornecedor = new FornecedorModel();
         if (resultadoConsulta.getRow() > 0) {
            
            fornecedor.setId(resultadoConsulta.getInt("for_id"));
            fornecedor.setNome(resultadoConsulta.getString("for_nome"));
            fornecedor.setCnpj(resultadoConsulta.getString("for_cnpj"));
            fornecedor.setTelefone(resultadoConsulta.getString("for_telefone"));
            fornecedor.setCelular(resultadoConsulta.getString("for_celular"));
            fornecedor.setEmail(resultadoConsulta.getString("for_email"));
            fornecedor.setRua(resultadoConsulta.getString("for_rua"));
            fornecedor.setNumero(resultadoConsulta.getString("for_numero"));
            fornecedor.setBairro(resultadoConsulta.getString("for_bairro"));
            fornecedor.setCidade(resultadoConsulta.getString("for_cidade"));
            fornecedor.setUf(resultadoConsulta.getString("for_uf"));
            fornecedor.setCep(resultadoConsulta.getString("for_cep"));
            fornecedor.setCategoria(resultadoConsulta.getString("for_categoria"));
            fornecedor.setDataCadastro(resultadoConsulta.getDate("for_data_cadastro"));
            fornecedor.setStatus(resultadoConsulta.getBoolean("for_status"));
         }
        
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();
        
    
    return fornecedor;
    }
}
