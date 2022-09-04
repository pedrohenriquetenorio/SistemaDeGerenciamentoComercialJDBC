package DAO;

import Control.Exceptions.JaExisteException;
import Control.Exceptions.NaoEncontradoException;
import static DAO.Conexao.getConexao;
import Modelo.ProdutoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutoDAO {

    public void inserir(ProdutoModel produtoModel) throws SQLException {

        try {
            java.sql.Connection conexao = Conexao.getConexao();

            String comandoSQL = "insert into produto (pro_nome, pro_valor, pro_codigo_balanca"
                    + ", pro_codigo_barras, pro_unidade_medida, pro_data_cadastro, pro_status) "
                    + "values(?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement execSQL;
            execSQL = conexao.prepareStatement(comandoSQL);

            execSQL.setString(1, produtoModel.getNome());
            execSQL.setDouble(2, produtoModel.getValor());
            execSQL.setInt(3, produtoModel.getCodigoBalanca());
            execSQL.setString(4, produtoModel.getCodigoBarra());
            execSQL.setString(5, produtoModel.getUnidadeMedida());
            execSQL.setDate(6, produtoModel.getDataCadastro());
            execSQL.setBoolean(7, produtoModel.getStatus());
            execSQL.executeUpdate();

            conexao.commit();

            execSQL.close();

            JOptionPane.showMessageDialog(null, "Produto cadastrado!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
           
            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o cadastro de produto ", "ERRO ao cadastrar",
                    JOptionPane.ERROR_MESSAGE);
            
        }

    }

    public ArrayList<ProdutoModel> findAll() throws SQLException, ParseException {
        String codigoString;

        int codigo;

        ArrayList<ProdutoModel> listaProdutos = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();

            String comandoSQL = "SELECT * FROM produto";

            PreparedStatement execSQL;

            execSQL = conexao.prepareStatement(comandoSQL);

            ResultSet resultadoConsulta;

            resultadoConsulta = execSQL.executeQuery();

            ProdutoModel produto;

            while (resultadoConsulta.next()) {

                produto = new ProdutoModel();
                produto.setId(resultadoConsulta.getInt("pro_id"));
                produto.setNome(resultadoConsulta.getString("pro_nome"));
                produto.setCodigoBarra(resultadoConsulta.getString("pro_codigo_barras"));
                produto.setUnidadeMedida(resultadoConsulta.getString("pro_unidade_medida"));
                produto.setCodigoBalanca(resultadoConsulta.getInt("pro_codigo_balanca"));
                produto.setValor(resultadoConsulta.getDouble("pro_valor"));
                produto.setDataCadastro(resultadoConsulta.getDate("pro_data_cadastro"));
                produto.setStatus(resultadoConsulta.getBoolean("pro_status"));
                listaProdutos.add(produto);
            }
            resultadoConsulta.close();
            execSQL.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaProdutos;
    }

    public ProdutoModel verificaCadastro(String sql, String tipo, String tela) throws SQLException, JaExisteException {
      
        Connection conexao = getConexao();

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM produto WHERE pro_" + tipo + " = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, sql);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();
        ProdutoModel produto = new ProdutoModel();
        if (resultadoConsulta.getRow() > 0) {
            if (resultadoConsulta.getRow() > 0 && resultadoConsulta.getBoolean("pro_status")==true && tela.equals("cadastro")) {
                throw new JaExisteException("Erro, não foi possivel efetuar o cadastro, o codigo de barras já existe no sistema!");
            } else if(resultadoConsulta.getRow() > 0 && resultadoConsulta.getBoolean("pro_status")==false){
                produto.setId(resultadoConsulta.getInt("pro_id"));
                produto.setNome(resultadoConsulta.getString("pro_nome"));
                produto.setCodigoBarra(resultadoConsulta.getString("pro_codigo_barras"));
                produto.setUnidadeMedida(resultadoConsulta.getString("pro_unidade_medida"));
                produto.setCodigoBalanca(resultadoConsulta.getInt("pro_codigo_balanca"));
                produto.setValor(resultadoConsulta.getDouble("pro_valor"));
                produto.setDataCadastro(resultadoConsulta.getDate("pro_data_cadastro"));
                produto.setStatus(resultadoConsulta.getBoolean("pro_status"));
            }
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();
        return produto;
    }

    public String verificaCadastroString(String sql, String tipo, String tela) throws SQLException, JaExisteException {
      
        Connection conexao = getConexao();

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM produto WHERE pro_" + tipo + " = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, sql);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();
        ProdutoModel produto = new ProdutoModel();
        if (resultadoConsulta.getRow() > 0) {
            if (resultadoConsulta.getRow() > 0 && resultadoConsulta.getBoolean("pro_status")==true && tela.equals("cadastro")) {
                throw new JaExisteException("Erro, não foi possivel efetuar o cadastro, o codigo de barras já existe no sistema!");
            } else if(resultadoConsulta.getRow() > 0 && resultadoConsulta.getBoolean("pro_status")==false){
               return "ProdutoDesativado";
            }
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();
        return "novoProduto";
    }
    
    public ArrayList<ProdutoModel> pesquisarNome(String nome) throws SQLException, ParseException, NaoEncontradoException {

        String codigoString;

        int codigo;

        ArrayList<ProdutoModel> listaProdutos = new ArrayList<>();

        Connection conexao = Conexao.getConexao();

        PreparedStatement execSQL; //"SELECT * FROM produto WHERE pro_nome = ?"
        execSQL = conexao.prepareStatement("SELECT * FROM produto WHERE pro_nome LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, '%' + nome + '%');
        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        ProdutoModel produto;

        while (resultadoConsulta.next()) {

            produto = new ProdutoModel();
            codigoString = (resultadoConsulta.getString("pro_id"));
            codigo = Integer.parseInt(codigoString);

            produto.setId(codigo);
            produto.setNome(resultadoConsulta.getString("pro_nome"));
            produto.setCodigoBarra(resultadoConsulta.getString("pro_codigo_barras"));
            produto.setUnidadeMedida(resultadoConsulta.getString("pro_unidade_medida"));
            produto.setCodigoBalanca(resultadoConsulta.getInt("pro_codigo_balanca"));
            produto.setValor(resultadoConsulta.getDouble("pro_valor"));
            produto.setDataCadastro(resultadoConsulta.getDate("pro_data_cadastro"));
            produto.setStatus(resultadoConsulta.getBoolean("pro_status"));
            listaProdutos.add(produto);
        }

        if (listaProdutos.isEmpty()) {
            throw new NaoEncontradoException("Produto não encontrado");
        }
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return listaProdutos;
    }

    public ArrayList<ProdutoModel> pesquisarNomeEspecifico(String nome) throws SQLException, ParseException, NaoEncontradoException {

        String codigoString;

        int codigo;

        ArrayList<ProdutoModel> listaProdutos = new ArrayList<>();

        Connection conexao = Conexao.getConexao();

        PreparedStatement execSQL;
        execSQL = conexao.prepareStatement("SELECT * FROM produto WHERE pro_nome = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, nome);
        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        ProdutoModel produto;

        while (resultadoConsulta.next()) {

            produto = new ProdutoModel();
            codigoString = (resultadoConsulta.getString("pro_id"));
            codigo = Integer.parseInt(codigoString);

            produto.setId(codigo);
            produto.setNome(resultadoConsulta.getString("pro_nome"));
            produto.setCodigoBarra(resultadoConsulta.getString("pro_codigo_barras"));
            produto.setUnidadeMedida(resultadoConsulta.getString("pro_unidade_medida"));
            produto.setCodigoBalanca(resultadoConsulta.getInt("pro_codigo_balanca"));
            produto.setValor(resultadoConsulta.getDouble("pro_valor"));
            produto.setDataCadastro(resultadoConsulta.getDate("pro_data_cadastro"));
            produto.setStatus(resultadoConsulta.getBoolean("pro_status"));
            listaProdutos.add(produto);
        }
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();
        
        if (listaProdutos.isEmpty()) {
            throw new NaoEncontradoException("Produto não encontrado!");
        }

        return listaProdutos;
    }

    public ArrayList<ProdutoModel> pesquisaCodigo(int id) throws SQLException {
        String codigoString;

        int codigo;

        ArrayList<ProdutoModel> listaProdutos = new ArrayList<>();
        try {
            Connection conexao = Conexao.getConexao();

            PreparedStatement execSQL; //"SELECT * FROM produto WHERE pro_nome = ?"
            execSQL = conexao.prepareStatement("SELECT * FROM produto WHERE pro_id LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            execSQL.setInt(1, '%' + id + '%');
            ResultSet resultadoConsulta;

            resultadoConsulta = execSQL.executeQuery();

            ProdutoModel produto;

            while (resultadoConsulta.next()) {

                produto = new ProdutoModel();
                codigoString = (resultadoConsulta.getString("pro_id"));
                codigo = Integer.parseInt(codigoString);

                produto.setId(codigo);

                produto.setNome(resultadoConsulta.getString("pro_nome"));
                produto.setCodigoBarra(resultadoConsulta.getString("pro_codigo_barras"));
                produto.setUnidadeMedida(resultadoConsulta.getString("pro_unidade_medida"));
                produto.setCodigoBalanca(resultadoConsulta.getInt("pro_codigo_balanca"));
                produto.setValor(resultadoConsulta.getDouble("pro_valor"));
                produto.setDataCadastro(resultadoConsulta.getDate("pro_data_cadastro"));
                produto.setStatus(resultadoConsulta.getBoolean("pro_status"));

                listaProdutos.add(produto);
            }
            resultadoConsulta.close();
            execSQL.close();
            conexao.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listaProdutos;
    }
    
     public ProdutoModel pesquisarCodigoBarras(String codigoBarras) throws SQLException, NaoEncontradoException {
         
        ProdutoModel produto;
        Connection conexao = getConexao();

        PreparedStatement execSQL;
         System.err.println("CODIGO "+codigoBarras);
        execSQL = conexao.prepareStatement("SELECT * FROM produto WHERE pro_codigo_barras = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, codigoBarras );

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();
        
        resultadoConsulta.last();
        
        if (resultadoConsulta.getRow() > 0) {

            produto = new ProdutoModel();

            produto.setId(resultadoConsulta.getInt("pro_id"));
            produto.setNome(resultadoConsulta.getString("pro_nome"));
            produto.setCodigoBarra(resultadoConsulta.getString("pro_codigo_barras"));
            produto.setUnidadeMedida(resultadoConsulta.getString("pro_unidade_medida"));
            produto.setCodigoBalanca(resultadoConsulta.getInt("pro_codigo_balanca"));
            produto.setValor(resultadoConsulta.getDouble("pro_valor"));
            produto.setDataCadastro(resultadoConsulta.getDate("pro_data_cadastro"));
            produto.setStatus(resultadoConsulta.getBoolean("pro_status"));
            
        } else {
            throw new NaoEncontradoException("Produto não encontrado, faça o cadastro do produto");
        }
        
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return produto;

    }
    
    
    public boolean pesquisar(String codigoBarras) throws SQLException, JaExisteException {

        Boolean verifica = false;

        Connection conexao = getConexao();

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM produto WHERE pro_codigo_barras = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, codigoBarras);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();

        if (resultadoConsulta.getRow() > 0) {
            throw new JaExisteException("Erro, nçao foi possivel efetuar o cadastro do codigo");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return verifica;

    }

   
    public ProdutoModel pesquisaId(int id) throws SQLException, NaoEncontradoException {
      
        Connection conexao = getConexao();
        String codigoString;
        ProdutoModel produto = null;
        int codigoInt, codigo;

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM produto WHERE pro_id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setInt(1, id);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();

        if (resultadoConsulta.getRow() > 0) {

            produto = new ProdutoModel();

            codigoString = (resultadoConsulta.getString("pro_id"));
            codigo = Integer.parseInt(codigoString);

            produto.setId(codigo);
            produto.setNome(resultadoConsulta.getString("pro_nome"));
            produto.setCodigoBarra(resultadoConsulta.getString("pro_codigo_barras"));
            produto.setUnidadeMedida(resultadoConsulta.getString("pro_unidade_medida"));
            produto.setCodigoBalanca(resultadoConsulta.getInt("pro_codigo_balanca"));
            produto.setValor(resultadoConsulta.getDouble("pro_valor"));
            produto.setDataCadastro(resultadoConsulta.getDate("pro_data_cadastro"));
            produto.setStatus(resultadoConsulta.getBoolean("pro_status"));
            
        } else {
            throw new NaoEncontradoException("Produto não encontrado");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return produto;
    }
 public String excluir(ProdutoModel produto) throws SQLException, NaoEncontradoException {

         String sql = "UPDATE produto SET pro_nome = ?, pro_valor = ?, pro_codigo_balanca = ?, pro_codigo_barras = ?,"
                + " pro_unidade_medida = ?, pro_data_cadastro = ?, pro_status = ? WHERE pro_id = ? ";

        Connection conexao;
        PreparedStatement execSQL;

        try {

            conexao = Conexao.getConexao();
            execSQL = conexao.prepareStatement(sql);

            execSQL.setString(1, produto.getNome());
            execSQL.setDouble(2, produto.getValor());
            execSQL.setInt(3, produto.getCodigoBalanca());
            execSQL.setString(4, produto.getCodigoBarra());
            execSQL.setString(5, produto.getUnidadeMedida());
            execSQL.setDate(6, produto.getDataCadastro());
            execSQL.setBoolean(7, produto.getStatus());
            execSQL.setInt(8, produto.getId());

            execSQL.executeUpdate();
            
            conexao.commit();
            execSQL.close();
            conexao.close();
            
        } catch (SQLException se) {
            System.out.println("ERRO   " + se);
            throw se;

        }
        return "Produto Excluido!";
    }

    public void alterar(ProdutoModel produto) throws SQLException {

        String sql = "UPDATE produto SET pro_nome = ?, pro_valor = ?, pro_codigo_balanca = ?, pro_codigo_barras = ?,"
                + " pro_unidade_medida = ?, pro_data_cadastro = ?, pro_status = ? WHERE pro_id = ? ";

        Connection conexao;
        PreparedStatement execSQL;

        try {

            conexao = Conexao.getConexao();
            execSQL = conexao.prepareStatement(sql);

            execSQL.setString(1, produto.getNome());
            execSQL.setDouble(2, produto.getValor());
            execSQL.setInt(3, produto.getCodigoBalanca());
            execSQL.setString(4, produto.getCodigoBarra());
            execSQL.setString(5, produto.getUnidadeMedida());
            execSQL.setDate(6, produto.getDataCadastro());
            execSQL.setBoolean(7, produto.getStatus());
            execSQL.setInt(8, produto.getId());

            execSQL.executeUpdate();
            
            conexao.commit();
            execSQL.close();
            conexao.close();
            
        } catch (SQLException se) {
            System.out.println("ERRO   " + se);
            throw se;

        }

    }
}
