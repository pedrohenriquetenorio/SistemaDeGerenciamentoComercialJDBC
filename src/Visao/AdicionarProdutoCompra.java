package Visao;

import Control.Exceptions.NaoEncontradoException;
import DAO.ProdutoDAO;
import Modelo.ProdutoModel;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AdicionarProdutoCompra extends javax.swing.JDialog {

    String quantidadeEstoque;
    CompraView compraView;

    public AdicionarProdutoCompra(java.awt.Frame parent, boolean modal, CompraView compra) {
        super(parent, modal);
        initComponents();
        atualizaTabelaSParametro();
        this.compraView = compra;

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
                        case KeyEvent.VK_F6:
                            System.err.println("REMOVER");
                            break;

                        default:
                            break;
                    }
                    return;
                }
            }
        }, AWTEvent.KEY_EVENT_MASK);

    }

    public void atualizaTabelaSParametro() {

        ProdutoDAO produtoDao = new ProdutoDAO();
        ArrayList<ProdutoModel> listaDeProdutos;
        try {
            listaDeProdutos = produtoDao.findAll();
            atualizaTabelaProdutos(listaDeProdutos);
        } catch (SQLException ex) {
            Logger.getLogger(AdicionarProdutoVendaView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AdicionarProdutoVendaView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void adicionar() {

        int linha = -1;

        ProdutoModel produtoModel = new ProdutoModel();

        ProdutoDAO produtoDao = new ProdutoDAO();

        linha = jTableProduto.getSelectedRow();

        if (linha == -1) {

            JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta", JOptionPane.INFORMATION_MESSAGE);

        } else {
            try {

                String codigoBarras = (String) jTableProduto.getValueAt(linha, 0);
                produtoModel = produtoDao.pesquisarCodigoBarras(codigoBarras);
                this.compraView.alteraCampos();
                this.compraView.preencheCampoProduto(produtoModel);
                super.dispose();

            } catch (SQLException ex) {
                Logger.getLogger(AdicionarProdutoVendaView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NaoEncontradoException ex) {
                Logger.getLogger(AdicionarProdutoVendaView.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void atualizaTabelaProdutos(ArrayList<ProdutoModel> listaProdutos) {

        Locale localBrasil = new Locale("pt", "BR");

        DefaultTableModel val = (DefaultTableModel) jTableProduto.getModel();
        val.setNumRows(0); // excluir os registros que estão na JTable

        if (jTableProduto != null) {

            listaProdutos.forEach((produto) -> {
                if (produto.getStatus() == true) {
                    String valor = NumberFormat.getCurrencyInstance(localBrasil).format(produto.getValor());

                    val.addRow(new Object[]{produto.getCodigoBarra(), produto.getNome(),
                        valor, produto.getUnidadeMedida()});
                }
            });
        }
    }

    public void atualizaTabelaProduto(ProdutoModel produto) {

//        DefaultTableModel val = (DefaultTableModel) jTableProduto.getModel();
//        val.setNumRows(0); 
//        
//        if (jTableProduto != null) {
//
//            val.addRow(new Object[]{produto.getId(), produto.getListaProdutos().getNome(), produto.getListaProdutos().getValor(), produto.getListaProdutos().getUnidadeMedida(),
//                    produto.getListaProdutos().getCodigoBalanca(), produto.getListaProdutos().getCodigoBarra(), produto.getQtdEstoque()});
//
//        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jTextFieldPesquisar = new javax.swing.JTextField();
        jComboBoxTipoPesquisa = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabelLegendaPesquisa = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProduto = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Adicionar Produto Compra");

        jPanel1.setBackground(new java.awt.Color(3, 133, 188));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Pesquisar Produtos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Pesquisa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lupa.png"))); // NOI18N
        jButton3.setText("Pesquisar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextFieldPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPesquisarActionPerformed(evt);
            }
        });
        jTextFieldPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPesquisarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldPesquisarKeyTyped(evt);
            }
        });

        jComboBoxTipoPesquisa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "Código de Barras" }));
        jComboBoxTipoPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoPesquisaActionPerformed(evt);
            }
        });

        jLabel2.setText("Tipo:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxTipoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldPesquisar)
                    .addComponent(jLabelLegendaPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelLegendaPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jTextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxTipoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/adicionar24x24.png"))); // NOI18N
        jButton1.setText("[F2] Adicionar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/voltar24x24.png"))); // NOI18N
        jButton2.setText("[F1] Voltar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tabela de Produtos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTableProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo de Barras", "Produto", "Valor", "Unidade Medida"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableProduto);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int index = jComboBoxTipoPesquisa.getSelectedIndex(), codigoInt;

        String nome, codigo;

        ProdutoDAO produtoDao = new ProdutoDAO();
        ProdutoModel produtoModel = new ProdutoModel();
        ArrayList<ProdutoModel> listaProdutos = new ArrayList<>();

        switch (index) {

            case 0: {//NOME
                nome = jTextFieldPesquisar.getText();

                try {

                    listaProdutos = produtoDao.pesquisarNomeEspecifico(nome);
                    atualizaTabelaProdutos(listaProdutos);

                } catch (ParseException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NaoEncontradoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }

            break;

            case 1: //Codigo
            {

                codigo = jTextFieldPesquisar.getText();

                try {

                    ProdutoModel produto = produtoDao.pesquisarCodigoBarras(codigo);
                    ArrayList<ProdutoModel> lista = new ArrayList<>();
                    lista.add(produto);
                    atualizaTabelaProdutos(lista);

                } catch (SQLException ex) {

                    Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);

                } catch (NaoEncontradoException ex) {

                    JOptionPane.showMessageDialog(null, ex.getMessage());

                }

            }

            break;

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextFieldPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPesquisarActionPerformed

    }//GEN-LAST:event_jTextFieldPesquisarActionPerformed

    private void jTextFieldPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisarKeyReleased

        int index = jComboBoxTipoPesquisa.getSelectedIndex(), codigoInt;
        ProdutoDAO produtoDao = new ProdutoDAO();

        ArrayList<ProdutoModel> listProdutos = new ArrayList<>();

        String nome, codigoDeBarras;

        switch (index) {

            case 0: {//NOME

                nome = jTextFieldPesquisar.getText().trim();

                if (nome.equals("")) {

                    try {

                        listProdutos = produtoDao.findAll();
                        atualizaTabelaProdutos(listProdutos);

                    } catch (SQLException ex) {
                        Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else if (!nome.equals(" ")) {

                    try {

                        listProdutos = produtoDao.pesquisarNome(nome);
                        atualizaTabelaProdutos(listProdutos);
                        //atualizaTabelaSParametro();

                    } catch (SQLException ex) {
                        Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NaoEncontradoException ex) {

                    }

                }
            }

            break;

            case 1: //Codigo
            {
                codigoDeBarras = jTextFieldPesquisar.getText().trim();

                if (codigoDeBarras.equals("")) {

                    try {

                        listProdutos = produtoDao.findAll();
                        atualizaTabelaProdutos(listProdutos);

                    } catch (SQLException ex) {
                        Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else if (!codigoDeBarras.equals(" ")) {

                    try {

                        ProdutoModel produto = produtoDao.pesquisarCodigoBarras(codigoDeBarras);
                        ArrayList<ProdutoModel> lista = new ArrayList<>();
                        lista.add(produto);
                        atualizaTabelaProdutos(lista);

                    } catch (SQLException ex) {

                        atualizaTabelaSParametro();

                    } catch (NaoEncontradoException ex) {

                        atualizaTabelaSParametro();

                    }
                }

            }
        }
    }//GEN-LAST:event_jTextFieldPesquisarKeyReleased

    private void jTextFieldPesquisarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisarKeyTyped
        int qtdCaracteres = 100;

        if (jTextFieldPesquisar.getText().length() >= qtdCaracteres) {
            evt.consume();
        }

        if (jComboBoxTipoPesquisa.getSelectedIndex() == 1) {

            char c = evt.getKeyChar();

            if (!Character.isDigit(c)) {
                evt.consume();
            }

        }
    }//GEN-LAST:event_jTextFieldPesquisarKeyTyped

    private void jComboBoxTipoPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoPesquisaActionPerformed
        int index = jComboBoxTipoPesquisa.getSelectedIndex();

        String caractere;

        switch (index) {

            case 0: {

                jLabelLegendaPesquisa.setText("Nome:");
                jTextFieldPesquisar.setText("");

            }

            break;

            case 1: //Codigo
            {

                jLabelLegendaPesquisa.setText("Código:");
                jTextFieldPesquisar.setText("");
            }

            break;

        }
    }//GEN-LAST:event_jComboBoxTipoPesquisaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        adicionar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        super.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(AdicionarProdutoCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdicionarProdutoCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdicionarProdutoCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdicionarProdutoCompra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AdicionarProdutoCompra dialog = new AdicionarProdutoCompra(new javax.swing.JFrame(), true, null);
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
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBoxTipoPesquisa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelLegendaPesquisa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProduto;
    private javax.swing.JTextField jTextFieldPesquisar;
    // End of variables declaration//GEN-END:variables
}
