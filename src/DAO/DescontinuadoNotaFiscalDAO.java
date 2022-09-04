///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package DAO;
//
//import Control.Exceptions.NaoEncontradoException;
//import static DAO.Conexao.getConexao;
//import Modelo.EstoqueModel;
//import Modelo.FornecedorModel;
//import Modelo.DescontinuadoNotaFiscalModel;
//import Modelo.ProdutoModel;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.text.ParseException;
//import javax.swing.JOptionPane;
//
//public class DescontinuadoNotaFiscalDAO {
//    
//    public void inserir(DescontinuadoNotaFiscalModel notaFiscal) throws SQLException {
//         
//        try {
//            java.sql.Connection conexao = Conexao.getConexao();
//
//            String comandoSQL = "insert into nota_fiscal_estoque (ntf_numero_nota_fiscal, ntf_descricao, ntf_quantidade, ntf_valor, ntf_unidade_medida, ntf_data_hora, estoque_est_id, fornecedor_for_id, ntf_status)"
//                    + " values (?, ?, ?, ?, ?, ?, ?, ?,?)";
//            
//            PreparedStatement execSQL;
//            execSQL = conexao.prepareStatement(comandoSQL);
//            
//            execSQL.setInt(1, notaFiscal.getNumeroNotaFiscal());
//            execSQL.setString(2, notaFiscal.getDescricao());
//            execSQL.setDouble(3, notaFiscal.getQuantidade());
//            execSQL.setDouble(4, notaFiscal.getValor());
//            execSQL.setString(5, notaFiscal.getUnidadeMedida());
//            execSQL.setTimestamp(6, notaFiscal.getDataHora());
//            execSQL.setInt(7, notaFiscal.getEstoque().getId());
//            execSQL.setInt(8, notaFiscal.getFornecedor().getId());
//            execSQL.setBoolean(9, notaFiscal.getStatus());
//            execSQL.executeUpdate();
//
//            conexao.commit();
//
//            execSQL.close();
//            
//        } catch (SQLException ex) {
//          
//            System.err.println(ex.getMessage());
//            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o cadastro de nota fiscal ", "ERRO",
//                    JOptionPane.ERROR_MESSAGE);
//            
//        }
//         
//    }
//    //SELECT  nota_fiscal_estoque.*, estoque.* FROM nota_fiscal_estoque inner join estoque on est_id = ntf_nota_fiscal_estoque_id where est_id =
//    public DescontinuadoNotaFiscalModel pesquisarEstoque(int idEstoque) throws SQLException, NaoEncontradoException, ParseException{
//    
//        System.out.println("1= " + idEstoque);
//        String codigoString; 
//        int codigo, codigoInt, codigoProduto;
//        Connection conexao = getConexao();
//        
//        PreparedStatement execSQL;
//        //SELECT estoque.*, produto.* FROM estoque inner join produto on pro_codigo_barras = ? ORDER BY produto_pro_id DESC LIMIT 1
//        
//        execSQL = conexao.prepareStatement("SELECT  nota_fiscal_estoque.*, estoque.* FROM estoque inner join  nota_fiscal_estoque  on est_id = estoque_est_id where est_id = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
//
//        execSQL.setInt(1, idEstoque);
//
//        ResultSet resultadoConsulta;
//
//        resultadoConsulta = execSQL.executeQuery();
//
//        resultadoConsulta.last();
//        EstoqueModel estoque;
//        
//        DescontinuadoNotaFiscalModel notaFiscal;
//        FornecedorModel fornecedor;
////        ProdutoModel produto;
//        
//        if (resultadoConsulta.getRow() > 0) {
//                 
//                 estoque = new EstoqueModel();
//                 notaFiscal = new DescontinuadoNotaFiscalModel();
//                 fornecedor = new FornecedorModel();
//                EstoqueDAO estoqueDao = new EstoqueDAO();
//                 FornecedorDAO fornecedorDao = new FornecedorDAO();
//                 
//                estoque = estoqueDao.findByID(resultadoConsulta.getInt("est_id"));
//                fornecedor = fornecedorDao.findByID(resultadoConsulta.getInt("fornecedor_for_id"));
//                
//                //estoque.setId(resultadoConsulta.getInt("est_id"));
//                //estoque.setQtdEstoque(resultadoConsulta.getDouble("est_quantidade_estoque"));
//                //estoque.setQtdMinima(resultadoConsulta.getDouble("est_quantidade_minima"));
//               
//                notaFiscal.setDataHora(resultadoConsulta.getTimestamp("ntf_data_hora"));
//                notaFiscal.setDescricao(resultadoConsulta.getString("ntf_descricao"));
//                notaFiscal.setEstoque(estoque);
//                notaFiscal.setFornecedor(fornecedor);
//                notaFiscal.setId(resultadoConsulta.getInt("est_id"));
//                notaFiscal.setNumeroNotaFiscal(resultadoConsulta.getInt("ntf_numero_nota_fiscal"));
//                notaFiscal.setQuantidade(resultadoConsulta.getDouble("ntf_quantidade"));
//                notaFiscal.setStatus(resultadoConsulta.getBoolean("ntf_status"));
//                notaFiscal.setUnidadeMedida(resultadoConsulta.getString("ntf_unidade_medida"));
//                notaFiscal.setValor(resultadoConsulta.getDouble("ntf_valor"));
//                
//                
//        } else {
//            throw new NaoEncontradoException("Erro ao encontrar produto em estoque");
//        }
//
//        resultadoConsulta.close();
//        execSQL.close();
//        conexao.close();
//
//        return notaFiscal;
//    }
//    
//    
//    
//    }
//
