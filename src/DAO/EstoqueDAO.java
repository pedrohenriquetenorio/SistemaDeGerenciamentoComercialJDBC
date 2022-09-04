/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Control.Exceptions.NaoCadastradoEstoqueException;
import Control.Exceptions.NaoEncontradoException;
import static DAO.Conexao.getConexao;
import Modelo.EstoqueModel;
import Modelo.FuncionarioModel;
import Modelo.ProdutoModel;
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
public class EstoqueDAO {
    
    
    public void inserir(EstoqueModel estoqueModel) throws SQLException{
       
        java.sql.Connection conexao = Conexao.getConexao();
        String comandoSQL = "insert into estoque (est_quantidade_estoque, est_quantidade_minima, produto_pro_id, funcionario_fun_id, est_status) "
                    + "values(?, ?, ?, ?,?)";
        
        PreparedStatement execSQL;
        execSQL = conexao.prepareStatement(comandoSQL);
        execSQL.setDouble(1, estoqueModel.getQtdEstoque());
        execSQL.setDouble(2, estoqueModel.getQtdMinima());
        execSQL.setInt(3, estoqueModel.getProduto().getId());
        execSQL.setInt(4, estoqueModel.getFuncionario().getId());
        execSQL.setBoolean(5,estoqueModel.getStatus());
         
        execSQL.executeUpdate();

        conexao.commit();

        execSQL.close();

    }
                    
    public String excluir(EstoqueModel estoque) throws SQLException {

      
         Connection conexao = Conexao.getConexao();
        int linhas;
       
      
        String comandoSQL = "DELETE FROM estoque WHERE est_id = ?";

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement(comandoSQL);

        execSQL.setInt(1, estoque.getId());

        linhas = execSQL.executeUpdate();

        conexao.commit();

        execSQL.close();

        conexao.close();

        if (linhas <= 0) {
            throw new SQLException("Não foi possivel remover o Produto");
        }

        return "Produto Removido!";
    }
    
    public void alteraQuantidade(EstoqueModel estoque) throws SQLException{
          
            String sql = "UPDATE estoque SET est_quantidade_estoque=?, est_quantidade_minima=?, produto_pro_id=?, funcionario_fun_id=?, est_status = ? WHERE est_id=?";

            Connection conexao;
            PreparedStatement execSQL;
            
            try {     
            conexao = Conexao.getConexao();
            execSQL = conexao.prepareStatement(sql);

            execSQL.setDouble(1, estoque.getQtdEstoque());
            execSQL.setDouble(2, estoque.getQtdMinima());
            execSQL.setInt(3, estoque.getProduto().getId());
            execSQL.setInt(4, estoque.getFuncionario().getId());
            execSQL.setBoolean(5, estoque.getStatus());
            execSQL.setInt(6, estoque.getId());
            
            execSQL.executeUpdate();

            conexao.commit();
            execSQL.close();
            conexao.close();
            
        } catch (SQLException se) {
            
            System.out.println("ERRO   " + se);
            throw se;

        }

    }
    
    public ArrayList<EstoqueModel> findAll() throws SQLException, ParseException {
        String codigoString;

        int codigo, codigoProduto, tamanho, i;

        ArrayList<EstoqueModel> listaEstoque = new ArrayList<>();

        try {
            Connection conexao = Conexao.getConexao();

            String comandoSQL = "SELECT * FROM estoque e inner join produto p where e.produto_pro_id = p.pro_id";
           
            PreparedStatement execSQL;

            execSQL = conexao.prepareStatement(comandoSQL);

            ResultSet resultadoConsulta;

            resultadoConsulta = execSQL.executeQuery();

            EstoqueModel estoque;
            ProdutoModel produto;
            
            while (resultadoConsulta.next()) {

                estoque = new EstoqueModel();
                

                estoque.setId(resultadoConsulta.getInt("est_id"));
                estoque.setQtdEstoque(resultadoConsulta.getDouble("est_quantidade_estoque"));
                estoque.setQtdMinima(resultadoConsulta.getDouble("est_quantidade_minima"));
                estoque.setStatus(resultadoConsulta.getBoolean("est_status"));
                
                produto = new ProdutoModel();
                produto.setId(resultadoConsulta.getInt("pro_id"));
                produto.setNome(resultadoConsulta.getString("pro_nome"));
                produto.setCodigoBarra(resultadoConsulta.getString("pro_codigo_barras"));
                produto.setUnidadeMedida(resultadoConsulta.getString("pro_unidade_medida"));
                produto.setCodigoBalanca(resultadoConsulta.getInt("pro_codigo_balanca"));
                produto.setValor(resultadoConsulta.getDouble("pro_valor"));
                produto.setDataCadastro(resultadoConsulta.getDate("pro_data_cadastro"));
                
                estoque.setProduto(produto);
                
                listaEstoque.add(estoque);
                
            }

            resultadoConsulta.close();
            execSQL.close();
            conexao.close();

        } catch (SQLException e) {

        }

        return listaEstoque;
    }
    
    public ArrayList<EstoqueModel> pesquisarNome(String nomeProduto) throws SQLException, ParseException, NaoEncontradoException {

        String codigoString;

        int codigo;

        ArrayList<EstoqueModel> listaProdutos = new ArrayList<>();

        Connection conexao = Conexao.getConexao();

        PreparedStatement execSQL; 
        execSQL = conexao.prepareStatement("SELECT * FROM estoque inner join produto on produto_pro_id = pro_id WHERE pro_nome LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, '%' + nomeProduto + '%');
        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();
        EstoqueModel estoque;
        
        ProdutoModel produto;

        while (resultadoConsulta.next()) {

            produto = new ProdutoModel();
            codigoString = (resultadoConsulta.getString("pro_id"));
            codigo = Integer.parseInt(codigoString);
            
            estoque = new EstoqueModel();
            estoque.setId(resultadoConsulta.getInt("est_id"));
            estoque.setQtdEstoque(resultadoConsulta.getDouble("est_quantidade_estoque"));
            estoque.setQtdMinima(resultadoConsulta.getDouble("est_quantidade_minima"));
            estoque.setStatus(resultadoConsulta.getBoolean("est_status"));
            
            produto.setId(codigo);
            produto.setNome(resultadoConsulta.getString("pro_nome"));
            produto.setCodigoBarra(resultadoConsulta.getString("pro_codigo_barras"));
            produto.setUnidadeMedida(resultadoConsulta.getString("pro_unidade_medida"));
            produto.setCodigoBalanca(resultadoConsulta.getInt("pro_codigo_balanca"));
            produto.setValor(resultadoConsulta.getDouble("pro_valor"));
            produto.setDataCadastro(resultadoConsulta.getDate("pro_data_cadastro"));
            produto.setStatus(resultadoConsulta.getBoolean("pro_status"));
            
            estoque.setProduto(produto);
            
            listaProdutos.add(estoque);
        
        }
        
        if (listaProdutos.isEmpty()) {
            throw new NaoEncontradoException("Produto não encontrado");
        }
        
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return listaProdutos;
    }
    
    //Parametro ID do produto e Retorna o ID do estoque
    public int pesquisaIDEstoque(int id) throws SQLException, ParseException, NaoEncontradoException {
       
        Connection conexao = getConexao();
        
        PreparedStatement execSQL;
        
        execSQL = conexao.prepareStatement("SELECT est_id FROM estoque e inner join produto p where e.produto_pro_id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setInt(1, id);

        ResultSet resultadoConsulta;
         EstoqueModel estoque;
        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();

        if (resultadoConsulta.getRow() > 0) {
            
            id = resultadoConsulta.getInt("est_id");
            
        } else {
            
            throw new NaoEncontradoException("estoque não encontrado!");
            
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return id;
    }
     
    public EstoqueModel pesquisaEstoque(int produtoId) throws SQLException, ParseException, NaoEncontradoException {
        
        String codigoString; 
        int codigo, codigoInt, codigoProduto;
        Connection conexao = getConexao();
        
        PreparedStatement execSQL;
        // SELECT estoque.*, produto.* FROM estoque inner join produto on produto_pro_id = pro_id ORDER BY produto_pro_id = ? DESC LIMIT 1
        execSQL = conexao.prepareStatement("SELECT estoque.*, produto.* FROM estoque inner join produto on produto_pro_id = pro_id WHERE pro_id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        
        execSQL.setInt(1, produtoId);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();
        EstoqueModel estoque;
        ProdutoModel produto;
        
        if (resultadoConsulta.getRow() > 0) {
                
                FuncionarioDAO funcionarioDao = new FuncionarioDAO();
                FuncionarioModel funcionarioModel = new FuncionarioModel();
                
                funcionarioModel = funcionarioDao.findByID(resultadoConsulta.getInt("funcionario_fun_id"));
                estoque = new EstoqueModel();
                produto = new ProdutoModel();
                
                estoque.setId(resultadoConsulta.getInt("est_id"));
                estoque.setQtdEstoque(resultadoConsulta.getDouble("est_quantidade_estoque"));
                estoque.setQtdMinima(resultadoConsulta.getDouble("est_quantidade_minima"));
                estoque.setStatus(resultadoConsulta.getBoolean("est_status")); 
                
                produto.setId(resultadoConsulta.getInt("pro_id"));
                produto.setNome(resultadoConsulta.getString("pro_nome"));
                produto.setCodigoBarra(resultadoConsulta.getString("pro_codigo_barras"));
                produto.setUnidadeMedida(resultadoConsulta.getString("pro_unidade_medida"));
                produto.setCodigoBalanca(resultadoConsulta.getInt("pro_codigo_balanca"));
                produto.setValor(resultadoConsulta.getDouble("pro_valor"));
                produto.setDataCadastro(resultadoConsulta.getDate("pro_data_cadastro"));
                
                estoque.setFuncionario(funcionarioModel);
                
                estoque.setProduto(produto);
            
        } else {
            throw new NaoEncontradoException("Erro ao encontrar produto em estoque");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return estoque;
    }
    
    public EstoqueModel pesquisarUltimoEstoque() throws SQLException, NaoEncontradoException {
        Connection conexao = Conexao.getConexao();

        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT estoque.*, produto.* FROM estoque inner join produto on produto_pro_id = pro_id ORDER BY est_id DESC LIMIT 1", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();
        EstoqueModel estoque;
        ProdutoModel produto;
        resultadoConsulta.last();

        if (resultadoConsulta.getRow() > 0) {

            
                FuncionarioDAO funcionarioDao = new FuncionarioDAO();
                FuncionarioModel funcionarioModel = new FuncionarioModel();
                
                funcionarioModel = funcionarioDao.findByID(resultadoConsulta.getInt("funcionario_fun_id"));
                estoque = new EstoqueModel();
                produto = new ProdutoModel();
                
                estoque.setId(resultadoConsulta.getInt("est_id"));
                estoque.setQtdEstoque(resultadoConsulta.getDouble("est_quantidade_estoque"));
                estoque.setQtdMinima(resultadoConsulta.getDouble("est_quantidade_minima"));
                estoque.setStatus(resultadoConsulta.getBoolean("est_status")); 
                
                produto.setId(resultadoConsulta.getInt("pro_id"));
                produto.setNome(resultadoConsulta.getString("pro_nome"));
                produto.setCodigoBarra(resultadoConsulta.getString("pro_codigo_barras"));
                produto.setUnidadeMedida(resultadoConsulta.getString("pro_unidade_medida"));
                produto.setCodigoBalanca(resultadoConsulta.getInt("pro_codigo_balanca"));
                produto.setValor(resultadoConsulta.getDouble("pro_valor"));
                produto.setDataCadastro(resultadoConsulta.getDate("pro_data_cadastro"));
                
                estoque.setFuncionario(funcionarioModel);
                
                estoque.setProduto(produto);
                
        } else {
            throw new NaoEncontradoException("não há caixa, na base de dados");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return estoque;

    }
    
    public EstoqueModel pesquisaProdutoEstoque(String codigoBarrasProduto) throws SQLException, ParseException, NaoEncontradoException {
        
        Connection conexao = getConexao();
        
        PreparedStatement execSQL;
        
        execSQL = conexao.prepareStatement("SELECT estoque.*, produto.* FROM estoque inner join produto on pro_codigo_barras = ? where produto_pro_id = pro_id", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                                            
        execSQL.setString(1, codigoBarrasProduto);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();
        
        EstoqueModel estoque;
        ProdutoModel produto;
        
        if (resultadoConsulta.getRow() > 0) {
                
                estoque = new EstoqueModel();
                produto = new ProdutoModel();
                ProdutoDAO produtoDao = new ProdutoDAO();
               
                estoque.setId(resultadoConsulta.getInt("est_id"));
                estoque.setQtdEstoque(resultadoConsulta.getDouble("est_quantidade_estoque"));
                estoque.setQtdMinima(resultadoConsulta.getDouble("est_quantidade_minima"));
                 estoque.setStatus(resultadoConsulta.getBoolean("est_status")); 
                produto = produtoDao.pesquisaId(resultadoConsulta.getInt("pro_id"));
                
                estoque.setProduto(produto);
               System.out.println("TESTE DE PRODUTO ESTOQUE 4"+ estoque.getProduto().getNome());
        } else {
            throw new NaoEncontradoException("Erro ao encontrar produto em estoque");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return estoque;
    }
    
    public EstoqueModel pesquisaEstoqueCodigoBarras(String codigoDeBarras) throws SQLException, ParseException, NaoEncontradoException {
        
        String codigoString; 
        int codigo, codigoInt, codigoProduto;
        Connection conexao = getConexao();
        
        PreparedStatement execSQL;
        //SELECT estoque.*, produto.* FROM estoque inner join produto on pro_codigo_barras = ? ORDER BY produto_pro_id DESC LIMIT 1
        execSQL = conexao.prepareStatement("SELECT  produto.*, estoque.* FROM produto inner join estoque on pro_codigo_barras = ? where pro_id = produto_pro_id", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, codigoDeBarras);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();
        EstoqueModel estoque;
        ProdutoModel produto;
        
        if (resultadoConsulta.getRow() > 0) {
                
                estoque = new EstoqueModel();
                produto = new ProdutoModel();
                
                estoque.setId(resultadoConsulta.getInt("est_id"));
                estoque.setQtdEstoque(resultadoConsulta.getDouble("est_quantidade_estoque"));
                estoque.setQtdMinima(resultadoConsulta.getDouble("est_quantidade_minima"));
                estoque.setStatus(resultadoConsulta.getBoolean("est_status")); 
                
                produto.setId(resultadoConsulta.getInt("pro_id"));
                produto.setNome(resultadoConsulta.getString("pro_nome"));
                produto.setCodigoBarra(resultadoConsulta.getString("pro_codigo_barras"));
                produto.setUnidadeMedida(resultadoConsulta.getString("pro_unidade_medida"));
                produto.setCodigoBalanca(resultadoConsulta.getInt("pro_codigo_balanca"));
                produto.setValor(resultadoConsulta.getDouble("pro_valor"));
                produto.setDataCadastro(resultadoConsulta.getDate("pro_data_cadastro"));
               
                estoque.setProduto(produto);
            
        } else {
            throw new NaoEncontradoException("Erro ao encontrar produto em estoque");
        }

        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return estoque;
    }
    
    public ArrayList<EstoqueModel> pesquisaEstoqueCodigoBarrasLIKE(String codigoDeBarras) throws SQLException, ParseException, NaoEncontradoException {
        
        
        String codigoString;

        int codigo;

        ArrayList<EstoqueModel> listaProdutos = new ArrayList<>();

        Connection conexao = Conexao.getConexao();

        PreparedStatement execSQL; 
        execSQL = conexao.prepareStatement("SELECT  produto.*, estoque.* FROM produto inner join estoque on produto_pro_id = pro_id where pro_codigo_barras LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, '%' + codigoDeBarras + '%');
        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();
        EstoqueModel estoque;
        
        ProdutoModel produto;

        while (resultadoConsulta.next()) {

            produto = new ProdutoModel();
            codigoString = (resultadoConsulta.getString("pro_id"));
            codigo = Integer.parseInt(codigoString);
            
            estoque = new EstoqueModel();
            estoque.setId(resultadoConsulta.getInt("est_id"));
            estoque.setQtdEstoque(resultadoConsulta.getDouble("est_quantidade_estoque"));
            estoque.setQtdMinima(resultadoConsulta.getDouble("est_quantidade_minima"));
            estoque.setStatus(resultadoConsulta.getBoolean("est_status")); 
            
            produto.setId(codigo);
            produto.setNome(resultadoConsulta.getString("pro_nome"));
            produto.setCodigoBarra(resultadoConsulta.getString("pro_codigo_barras"));
            produto.setUnidadeMedida(resultadoConsulta.getString("pro_unidade_medida"));
            produto.setCodigoBalanca(resultadoConsulta.getInt("pro_codigo_balanca"));
            produto.setValor(resultadoConsulta.getDouble("pro_valor"));
            produto.setDataCadastro(resultadoConsulta.getDate("pro_data_cadastro"));
            produto.setStatus(resultadoConsulta.getBoolean("pro_status"));
            
            estoque.setProduto(produto);
            
            listaProdutos.add(estoque);
        
        }
        
        if (listaProdutos.isEmpty()) {
            throw new NaoEncontradoException("Produto não encontrado");
        }
        
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return listaProdutos;
    }
    
     public EstoqueModel findByID(int id) throws SQLException, ParseException, NaoEncontradoException {
         String codigoString; 
         int codigo, codigoInt, codigoProduto;
        
        Connection conexao = getConexao();
        
        PreparedStatement execSQL;

        execSQL = conexao.prepareStatement("SELECT * FROM estoque e inner join produto p on e.produto_pro_id = p.pro_id where e.est_id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setInt(1, id);

        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

        resultadoConsulta.last();
        EstoqueModel estoque;
        ProdutoModel produto;
        
        if (resultadoConsulta.getRow() > 0) {
                
                estoque = new EstoqueModel();
                codigoString = (resultadoConsulta.getString("est_id"));
                codigo = Integer.parseInt(codigoString);
                codigoString = (resultadoConsulta.getString("pro_id"));
                codigoProduto = Integer.parseInt(codigoString);
                
                estoque.setId(codigo);
                estoque.setQtdEstoque(resultadoConsulta.getDouble("est_quantidade_estoque"));
                estoque.setQtdMinima(resultadoConsulta.getDouble("est_quantidade_minima"));
                estoque.setStatus(resultadoConsulta.getBoolean("est_status")); 
                produto = new ProdutoModel();
                produto.setId(resultadoConsulta.getInt("pro_id"));
                produto.setNome(resultadoConsulta.getString("pro_nome"));
                produto.setCodigoBarra(resultadoConsulta.getString("pro_codigo_barras"));
                produto.setUnidadeMedida(resultadoConsulta.getString("pro_unidade_medida"));
                produto.setCodigoBalanca(resultadoConsulta.getInt("pro_codigo_balanca"));
                produto.setValor(resultadoConsulta.getDouble("pro_valor"));
                produto.setDataCadastro(resultadoConsulta.getDate("pro_data_cadastro"));
               
                estoque.setProduto(produto);        
            
        } else {
            throw new NaoEncontradoException("Erro ao encontrar produto em estoque");
        }
               
//                produto.setId(codigoProduto);
//                produto.setNome(resultadoConsulta.getString("pro_nome"));
//                produto.setCodigoBarra(resultadoConsulta.getString("pro_codigo_barras"));
//                produto.setUnidadeMedida(resultadoConsulta.getString("pro_unidade_medida"));
//                produto.setCodigoBalanca(resultadoConsulta.getInt("pro_codigo_balanca"));
//                produto.setValor(resultadoConsulta.getDouble("pro_valor"));
//                produto.setDataCadastro(resultadoConsulta.getDate("pro_data_cadastro"));
//                
//                estoque.setListaProdutos(produto);
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();

        return estoque;
    }
    
     public ArrayList<EstoqueModel> pesquisarNomeEspecifico(String nome) throws SQLException, ParseException, NaoEncontradoException {

        String codigoString;

        int codigo, codigoProduto;

        ArrayList<EstoqueModel> listaEstoque = new ArrayList<>();

        Connection conexao = Conexao.getConexao();
        //ANTIGO SELECT SELECT est_id FROM estoque e inner join produto p where p.pro_nome = ?"
        PreparedStatement execSQL;
        execSQL = conexao.prepareStatement("SELECT  produto.*, estoque.* FROM estoque inner join produto on produto_pro_id = pro_id where pro_nome = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        execSQL.setString(1, nome);
        ResultSet resultadoConsulta;

        resultadoConsulta = execSQL.executeQuery();

         EstoqueModel estoque;
         ProdutoModel produto;
         
        while (resultadoConsulta.next()) {
            
                estoque = new EstoqueModel();
                codigoString = (resultadoConsulta.getString("est_id"));
                codigo = Integer.parseInt(codigoString);
               
                estoque.setId(codigo);
                estoque.setQtdEstoque(resultadoConsulta.getDouble("est_quantidade_estoque"));
                estoque.setQtdMinima(resultadoConsulta.getDouble("est_quantidade_minima"));
                estoque.setStatus(resultadoConsulta.getBoolean("est_status")); 
                
                produto = new ProdutoModel();
                produto.setId(resultadoConsulta.getInt("pro_id"));
                produto.setNome(resultadoConsulta.getString("pro_nome"));
                produto.setCodigoBarra(resultadoConsulta.getString("pro_codigo_barras"));
                produto.setUnidadeMedida(resultadoConsulta.getString("pro_unidade_medida"));
                produto.setCodigoBalanca(resultadoConsulta.getInt("pro_codigo_balanca"));
                produto.setValor(resultadoConsulta.getDouble("pro_valor"));
                produto.setDataCadastro(resultadoConsulta.getDate("pro_data_cadastro"));
                
                estoque.setProduto(produto);
                
                listaEstoque.add(estoque);
                
        }
        resultadoConsulta.close();
        execSQL.close();
        conexao.close();
        
        if (listaEstoque.isEmpty()) {
            throw new NaoEncontradoException("Produto não encontrado!");
        }

        return listaEstoque;
    }
     
     public ArrayList<EstoqueModel> pesquisa(int id){
                String codigoString;

                int codigo;

                   ArrayList<EstoqueModel> listaEstoque = new ArrayList<>();
                 
                try {
                  Connection conexao = Conexao.getConexao();

                  PreparedStatement execSQL; 
                  execSQL = conexao.prepareStatement("SELECT * FROM estoque WHERE produto_pro_id LIKE ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

                  execSQL.setInt(1, '%' + id + '%');
                  ResultSet resultadoConsulta;

                   resultadoConsulta = execSQL.executeQuery();

                    EstoqueModel estoque;
                    ProdutoModel produto;
                  
                 while (resultadoConsulta.next()) {

                        estoque = new EstoqueModel();
                        produto = new ProdutoModel();
                        
                        estoque.setId(resultadoConsulta.getInt("est_id"));
                        estoque.setQtdEstoque(resultadoConsulta.getDouble("est_quantidade_estoque"));
                        estoque.setQtdMinima(resultadoConsulta.getDouble("est_quantidade_minima"));
                        estoque.setStatus(resultadoConsulta.getBoolean("est_status")); 
                             produto.setId(resultadoConsulta.getInt("pro_id"));
                            produto.setNome(resultadoConsulta.getString("pro_nome"));
                            produto.setCodigoBarra(resultadoConsulta.getString("pro_codigo_barras"));
                            produto.setUnidadeMedida(resultadoConsulta.getString("pro_unidade_medida"));
                            produto.setCodigoBalanca(resultadoConsulta.getInt("pro_codigo_balanca"));
                            produto.setValor(resultadoConsulta.getDouble("pro_valor"));
                            produto.setDataCadastro(resultadoConsulta.getDate("pro_data_cadastro"));

                            estoque.setProduto(produto);

                        listaEstoque.add(estoque);
                }
                  resultadoConsulta.close();
                  execSQL.close();
                  conexao.close();
              } catch (SQLException e) {
                  e.printStackTrace();
              }

            return listaEstoque;
     }
     
     public void mudaStatus(){
         
         
     }
     
}

