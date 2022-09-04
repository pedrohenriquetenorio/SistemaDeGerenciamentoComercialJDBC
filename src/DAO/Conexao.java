/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author pedro
 */
public class Conexao {
    
      public static Connection getConexao(){
        
        Connection conexao = null;
        
        try{
                                    
         conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/tcc_pedro_v12?useLegacyDatetimeCode=false&serverTimezone=America/Sao_Paulo", "root", "ifsp");
           
         conexao.setAutoCommit(false);
            
        }catch(SQLException ex){
            System.err.println("erro de conexao"+ex);
            JOptionPane.showMessageDialog(null, "Erro ao conectar com o banco de dados");
        }
        
     return conexao;
    }
    
}
