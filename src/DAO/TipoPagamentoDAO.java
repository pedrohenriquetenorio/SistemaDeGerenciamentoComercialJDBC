/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.TipoPagamentoModel;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author pedro
 */
public class TipoPagamentoDAO {
    public void inserir(TipoPagamentoModel tipoPagamento){
        
        try (java.sql.Connection conexao = Conexao.getConexao()) {

            String comandoSQL = "insert into tipo (tip_forma_pagamento, tip_valor, venda_ven_id)"
                    + "values(?, ?, ?, ?)";

            PreparedStatement execSQL;
            execSQL = conexao.prepareStatement(comandoSQL);

            execSQL.setString(1,  tipoPagamento.getFormaPagamento());
            execSQL.setDouble(2,  tipoPagamento.getValor());
            execSQL.setObject(3,  tipoPagamento.getVenda());
            execSQL.executeUpdate();

            conexao.commit();

            execSQL.close();
           
        }catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar a venda ", "ERRO no tipo de venda",
                    JOptionPane.ERROR_MESSAGE);
            
        }
    
    }
           
}
