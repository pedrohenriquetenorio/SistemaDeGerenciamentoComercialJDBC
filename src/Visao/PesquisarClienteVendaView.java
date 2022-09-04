/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Control.Exceptions.NaoEncontradoException;
import DAO.ClienteDAO;
import Modelo.ClienteModel;
import Modelo.FornecedorModel;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Visao.Mascara;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author pedro
 */
public class PesquisarClienteVendaView extends javax.swing.JDialog {

    /**
     * Creates new form PesquisarClienteVendaView
     */
    Venda vendaView;
    ContaReceberView contaReceber;
    

    public PesquisarClienteVendaView(java.awt.Frame parent, boolean modal, Venda vendaView, ContaReceberView contaReceber) {
        super(parent, modal);
        initComponents();
        teclaAtalho();
        ClienteDAO clienteDAO = new ClienteDAO();
        ArrayList<ClienteModel>listaCliente = new ArrayList<>();
        
        try {
            
            listaCliente = clienteDAO.findAll();
            atualizaTabela(listaCliente);
            
        } catch (SQLException ex) {
            Logger.getLogger(PesquisarClienteVendaView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PesquisarClienteVendaView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        this.vendaView = vendaView;
        this.contaReceber = contaReceber;
    }
     public void adicionar(){
         int linha = -1;
            ArrayList<FornecedorModel> listaClientes;
            ClienteModel clienteModel = new ClienteModel();
            ClienteDAO clienteDao = new ClienteDAO();
            Mascara mascara = new Mascara();
            linha = jTable1.getSelectedRow();

            if (linha == -1) {

                JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta", JOptionPane.WARNING_MESSAGE);

            } else {

                String cpf = (String) jTable1.getValueAt(linha, 1);
                cpf = mascara.limpaMascara(cpf, "cpf");
                try {
                    clienteModel = clienteDao.pesquisar(cpf, "cpf");
                    
                    cpf = mascara.insereMascara(cpf, "cpf");
                    
                    clienteModel.setCpf(cpf);
                    if(this.contaReceber == null){
                    this.vendaView.adicionaCliente(clienteModel);
                    }else{
                    this.contaReceber.adicionaCliente(clienteModel);
                    }
                    super.dispose();
                } catch (SQLException ex) {
                    Logger.getLogger(PesquisarClienteVendaView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NaoEncontradoException ex) {
                    Logger.getLogger(PesquisarClienteVendaView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(PesquisarClienteVendaView.class.getName()).log(Level.SEVERE, null, ex);
                }
                   
            }

        
     
     }
     
     public void teclaAtalho(){
        
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            
            public void eventDispatched(AWTEvent event) {
                KeyEvent ev = (KeyEvent) event;
                
                if (ev.getID() == KeyEvent.KEY_RELEASED) {
                    
                    switch (ev.getKeyCode()) {
                        case KeyEvent.VK_F1:
                            dispose();
                            break;
                        case KeyEvent.VK_F2:
                            adicionar();
                            break;
                        case KeyEvent.VK_F5:
                            System.err.println("NOVO");
                            break;
                        case KeyEvent.VK_F4:
                            System.err.println("HISTORICO");
                            break;
                        case KeyEvent.VK_F3:
                            System.err.println("Salvar");
                            break;
                        case KeyEvent.VK_F8:
                          //  concluirVenda();
                            break;
                            
                        case 27:
                             //sair();
                            break;
                        
                        default:
                            break;
                    }  
                        return;
                }
            }
        }, AWTEvent.KEY_EVENT_MASK);
    }
     public ArrayList buscaTodos(){
            
        ClienteDAO clienteDao = new ClienteDAO();
        ArrayList<ClienteModel> listaClientes = null;
        
        try {
            listaClientes = clienteDao.findAll();
        } catch (SQLException ex) {
            Logger.getLogger(PesquisarClienteVendaView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PesquisarClienteVendaView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return listaClientes;   
      }
     
    
    public void atualizaTabela(ArrayList<ClienteModel>listaCliente){
      
            DefaultTableModel val = (DefaultTableModel) jTable1.getModel();
            val.setNumRows(0); // excluir os registros que estão na JTable
            //listaDeClientes = listaClientes;
             Mascara mascara = new Mascara();
            // Arrumar essa validação 
                if (jTable1 != null) {

                    listaCliente.forEach((cliente) -> {
                        String cpf, telefone, celular; 
                        if(cliente.getStatus() == true){
                        try {
                            cpf = mascara.insereMascara(cliente.getCpf(), "cpf");
                            telefone = mascara.insereMascara(cliente.getTelefone(), "telefone");
                            celular = mascara.insereMascara(cliente.getCelular(), "celular");
                            val.addRow(new Object[]{cliente.getNome(), cpf , telefone, celular});
                        } catch (ParseException ex) {
                            Logger.getLogger(PesquisarClienteVendaView.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        }
                    });
                }

    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButtonAdicionar = new javax.swing.JButton();
        jButtonVoltar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jComboBoxTipoPesquisa = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabelLegendaPesquisa = new javax.swing.JLabel();
        jFormattedTextFieldPesquisar = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisar Cliente");

        jPanel1.setBackground(new java.awt.Color(3, 133, 188));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Pesquisar Cliente");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(199, 199, 199)
                .addComponent(jLabel1)
                .addContainerGap(215, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButtonAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/adicionar24x24.png"))); // NOI18N
        jButtonAdicionar.setText("[F2] Adicionar");
        jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarActionPerformed(evt);
            }
        });

        jButtonVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/voltar24x24.png"))); // NOI18N
        jButtonVoltar.setText("[F1] Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Pesquisa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lupa.png"))); // NOI18N
        jButton3.setText("Pesquisar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBoxTipoPesquisa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "CPF" }));
        jComboBoxTipoPesquisa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxTipoPesquisaItemStateChanged(evt);
            }
        });
        jComboBoxTipoPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoPesquisaActionPerformed(evt);
            }
        });

        jLabel2.setText("Tipo:");

        jLabelLegendaPesquisa.setText("Nome:");

        jFormattedTextFieldPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldPesquisarKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxTipoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelLegendaPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, 597, Short.MAX_VALUE)
                    .addComponent(jFormattedTextFieldPesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabelLegendaPesquisa))
                .addGap(2, 2, 2)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jComboBoxTipoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tabela de Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "CPF", "Telefone", "Celular"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(517, Short.MAX_VALUE)
                .addComponent(jButtonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarActionPerformed
        
            adicionar();
        
        
        
    }//GEN-LAST:event_jButtonAdicionarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       
        
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBoxTipoPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoPesquisaActionPerformed
        
       int index = jComboBoxTipoPesquisa.getSelectedIndex();

        MaskFormatter mascara = null;

        switch (index) {

            case 0: {
              
                    //MASCARCA DE CARACTERES STRING 
                    
                    jLabelLegendaPesquisa.setText("Nome:");
                    jFormattedTextFieldPesquisar.setValue(null);
                    jFormattedTextFieldPesquisar.setFormatterFactory(null);
                    jFormattedTextFieldPesquisar.setText("");

            }

            break;

            case 1: {
                try {

                    jFormattedTextFieldPesquisar.setValue(null);
                    mascara = new MaskFormatter("###.###.###-##");

                } catch (ParseException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                }

                jFormattedTextFieldPesquisar.setFormatterFactory(new DefaultFormatterFactory(mascara));
            }

            jLabelLegendaPesquisa.setText("CPF:");

            break;

            
        }

        
    }//GEN-LAST:event_jComboBoxTipoPesquisaActionPerformed

    private void jComboBoxTipoPesquisaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxTipoPesquisaItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxTipoPesquisaItemStateChanged

    private void jFormattedTextFieldPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldPesquisarKeyReleased
       
        int index = jComboBoxTipoPesquisa.getSelectedIndex();

        ClienteDAO clienteDao = new ClienteDAO();
        ClienteModel clienteModel = new ClienteModel();
        Mascara mascaras = new Mascara();
        String nome, cnpj, cpf;
        
        ArrayList<ClienteModel> listaClientes = null;
        MaskFormatter mascara = null;
        switch (index) {

            //NOME
            case 0: {

                jLabelLegendaPesquisa.setText("Nome:");
                nome = jFormattedTextFieldPesquisar.getText().trim();

                if ("".equals(nome)) {

                        listaClientes = buscaTodos();
                        atualizaTabela(listaClientes);


                } else if (!nome.equals("")) {
                    try {

                        listaClientes = clienteDao.pesquisarNome(nome);
                        atualizaTabela(listaClientes);

                    } catch (SQLException ex) {

                    } catch (NaoEncontradoException ex) {
                            listaClientes = buscaTodos();
                            atualizaTabela(listaClientes);

                            
                    }
                }
            }

            break;

            case 1: //CPF
            {
                
                jLabelLegendaPesquisa.setText("CPF:");
                cpf = jFormattedTextFieldPesquisar.getText();
                
                cpf = mascaras.limpaMascara(cpf, "cpf");

                if ("".equals(cpf)) {

                    
                        listaClientes = buscaTodos();
                        atualizaTabela(listaClientes);

                } else if (!cpf.equals("")) {

                    try {
                        clienteModel = clienteDao.pesquisar(cpf, "cpf");
                        listaClientes = new ArrayList<>();
                        listaClientes.add(clienteModel);
                        atualizaTabela(listaClientes);
                        
                    } catch (SQLException ex) {
                            
                            listaClientes = new ArrayList<>();
                            listaClientes = buscaTodos();
                            atualizaTabela(listaClientes);
                        
                    } catch (NaoEncontradoException ex) {
                            listaClientes = new ArrayList<>();
                            listaClientes = buscaTodos();
                            atualizaTabela(listaClientes);
                      
                    }

                }

            }
            }
   
    }//GEN-LAST:event_jFormattedTextFieldPesquisarKeyReleased

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
      super.dispose();
    }//GEN-LAST:event_jButtonVoltarActionPerformed
        
   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PesquisarClienteVendaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesquisarClienteVendaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesquisarClienteVendaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesquisarClienteVendaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PesquisarClienteVendaView dialog = null;
                dialog = new PesquisarClienteVendaView(new javax.swing.JFrame(), true, null, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JComboBox<String> jComboBoxTipoPesquisa;
    private javax.swing.JFormattedTextField jFormattedTextFieldPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelLegendaPesquisa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
