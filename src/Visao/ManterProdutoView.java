package Visao;

import Control.Exceptions.JaExisteException;
import Control.Exceptions.NaoEncontradoException;
import DAO.EstoqueDAO;
import DAO.FuncionarioDAO;
import DAO.ProdutoDAO;
import Modelo.EstoqueModel;
import Modelo.FuncionarioModel;
import Modelo.ProdutoModel;
import java.awt.Toolkit;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ManterProdutoView extends javax.swing.JDialog {

    int idProduto, codigoBalanca;;
    String codigoBarra;
    Date dataCadastro;
    FuncionarioModel funcionarioModel;
    String tela;
    public ManterProdutoView(java.awt.Frame parent, boolean modal, String tela) throws SQLException, ParseException {
        
        super(parent, modal);
        initComponents();
        
        jTextFieldDataCadastro.setEditable(false);
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
        this.funcionarioModel = new FuncionarioModel();
        this.funcionarioModel = funcionarioDao.pesquisarDataHoralogin();
        
        limpaCampos();
        jRadioButton1.setSelected(true);
        jLabelLegendaPesquisa.setText("Nome do Produto");
        jTabbedPane1.setEnabledAt(1, false);
        jButtonCancelar.setEnabled(false);
        jButtonSalvar.setEnabled(false);
        
        this.tela=tela;
        jLabelLegendaPesquisa.setText("Nome:");
        //incluir tabala de produtos
        ProdutoDAO produtoControl = new ProdutoDAO();
        ArrayList<ProdutoModel> listaDeProdutos = produtoControl.findAll();
        atualizaTabelaProdutos(listaDeProdutos);
        
        if(this.tela.equals("telaPrincipal")){
             jTabbedPane1.setSelectedIndex(1);
            jTabbedPane1.setEnabledAt(0, false);
            jTabbedPane1.setEnabledAt(1, true);

            jButtonCancelar.setEnabled(true);
            jButtonSalvar.setEnabled(true);
            jButtonAlterar.setEnabled(false);
            jButtonExcluir.setEnabled(false);
        }else{
        
            setaDialog(0, true, true, true, false, false, true, true, false, "Cadastro");
            ativaCampos(true);
            
       
        }
        if (this.funcionarioModel.getCargo().equals("Funcionário")) {

            jButtonAlterar.setEnabled(false);
            jButtonExcluir.setEnabled(false);
            jButtonCadastrar.setEnabled(false);
            
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jButton5 = new javax.swing.JButton();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButtonCadastrar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButtonConsultar = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jTextFieldPesquisar = new javax.swing.JTextField();
        jButtonPesquisar = new javax.swing.JButton();
        jComboBoxTipoPesquisa = new javax.swing.JComboBox<>();
        jLabelLegendaPesquisa = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableProduto = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel11 = new javax.swing.JPanel();
        jButtonStatus = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jTextFieldValor = new javax.swing.JTextField();
        jTextFieldCodigoBalanca = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldDescricao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldCodigoBarra = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBoxUnidadeMedida = new javax.swing.JComboBox<>();
        jTextFieldDataCadastro = new javax.swing.JTextField();
        jLabelDataCadastro = new javax.swing.JLabel();

        jInternalFrame1.setVisible(true);

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton5.setText("Cadastro");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Produto");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/adicionar24x24.png"))); // NOI18N
        jButtonCadastrar.setText("Cadastro");
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });

        jButtonExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/excluir24x24.png"))); // NOI18N
        jButtonExcluir.setText("Excluir    ");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/voltar24x24.png"))); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/salvar24x24.png"))); // NOI18N
        jButtonSalvar.setText("Salvar     ");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Atualizar24x24.png"))); // NOI18N
        jButtonAlterar.setText("Editar      ");
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/VoltarSimples24x24.png"))); // NOI18N
        jButton2.setText("Voltar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButtonConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/consulta24x24.png"))); // NOI18N
        jButtonConsultar.setText("Consultar");
        jButtonConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSalvar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCadastrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonExcluir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAlterar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(3, 133, 188));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Controle de Produtos");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(178, 178, 178))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Pesquisar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

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

        jButtonPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lupa.png"))); // NOI18N
        jButtonPesquisar.setText("Pesquisar");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

        jComboBoxTipoPesquisa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "Código" }));
        jComboBoxTipoPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoPesquisaActionPerformed(evt);
            }
        });

        jLabel3.setText("Tipo:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxTipoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jTextFieldPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabelLegendaPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelLegendaPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPesquisar)
                    .addComponent(jComboBoxTipoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tabela de Produtos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTableProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo de Barras", "Nome", "Valor", "Unidade Medida", "Codigo balanca"
            }
        ));
        jScrollPane1.setViewportView(jTableProduto);
        if (jTableProduto.getColumnModel().getColumnCount() > 0) {
            jTableProduto.getColumnModel().getColumn(0).setResizable(false);
            jTableProduto.getColumnModel().getColumn(1).setResizable(false);
            jTableProduto.getColumnModel().getColumn(2).setResizable(false);
            jTableProduto.getColumnModel().getColumn(3).setResizable(false);
            jTableProduto.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Ativado");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Desativado");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addComponent(jRadioButton1)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addComponent(jRadioButton2)
                        .addContainerGap())))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Ativar Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jButtonStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Concluir30x30.png"))); // NOI18N
        jButtonStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButtonStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Consulta", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jTextFieldValor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldValorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldValorKeyTyped(evt);
            }
        });

        jTextFieldCodigoBalanca.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldCodigoBalancaFocusLost(evt);
            }
        });
        jTextFieldCodigoBalanca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCodigoBalancaKeyTyped(evt);
            }
        });

        jLabel1.setText("Código da Balança:");

        jLabel5.setText("Valor:");

        jTextFieldDescricao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldDescricaoKeyTyped(evt);
            }
        });

        jLabel2.setText("Descrição:");

        jTextFieldCodigoBarra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldCodigoBarraFocusLost(evt);
            }
        });
        jTextFieldCodigoBarra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCodigoBarraActionPerformed(evt);
            }
        });
        jTextFieldCodigoBarra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCodigoBarraKeyTyped(evt);
            }
        });

        jLabel8.setText("Codigo de Barras:");

        jLabel9.setText("Unidade de Medida:");

        jComboBoxUnidadeMedida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UNID", "KG" }));
        jComboBoxUnidadeMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxUnidadeMedidaActionPerformed(evt);
            }
        });

        jLabelDataCadastro.setText("Data de Cadastro:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jTextFieldDescricao, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelDataCadastro)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel8Layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addGap(72, 72, 72))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                            .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel1))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                            .addComponent(jComboBoxUnidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(jTextFieldCodigoBalanca))))
                                .addComponent(jTextFieldDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(28, 28, 28)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextFieldCodigoBarra, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel8))))
                    .addComponent(jLabel2))
                .addContainerGap(175, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldDescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxUnidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCodigoBalanca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCodigoBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelDataCadastro)
                .addGap(8, 8, 8)
                .addComponent(jTextFieldDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(46, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cadastro", jPanel3);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    public void ativaProduto(ProdutoModel produto){
        
        try {
            ProdutoDAO produtoDao = new ProdutoDAO();
            produto.setStatus(true);
            produtoDao.alterar(produto);
        } catch (SQLException ex) {
            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void limpaCampos() {

//      jTextFieldCodigoProduto.setText("");
        jTextFieldCodigoBalanca.setText("");
        jTextFieldDescricao.setText("");
        jTextFieldValor.setText("");
        jTextFieldCodigoBarra.setText("");
        jComboBoxUnidadeMedida.setSelectedIndex(0);
        jTextFieldPesquisar.setText("");
        jTextFieldDataCadastro.setVisible(false);
        jTextFieldDataCadastro.setEditable(false);
        jLabelDataCadastro.setVisible(false);
        
    }

    public void setaDialog(int telaAtual, boolean cadastro, boolean excluir,
            boolean alterar, boolean salvar, boolean cancelar, boolean consultar, boolean trocaTelaAtual,
            boolean trocaTelaAnterior, String legenda) {

        jTabbedPane1.setSelectedIndex(telaAtual);  //PAINEL 
        jButtonCadastrar.setEnabled(cadastro); //BOTÃO CADASTRAR
        jButtonExcluir.setEnabled(excluir); // BOTÃO EXCLUIR
        jButtonAlterar.setEnabled(alterar); //BOTÃO ALTERAR
        jButtonSalvar.setEnabled(salvar); //BOTÃO SALVAR
        jButtonCancelar.setEnabled(cancelar); //BOTÃO CANCELAR
        jButtonConsultar.setEnabled(consultar);//BOTÃO CONSULTAR
        jTabbedPane1.setEnabledAt(0, trocaTelaAtual); //TELA ATUAL
        jTabbedPane1.setEnabledAt(1, trocaTelaAnterior); //TELA ANTERIOR 
        jTabbedPane1.setTitleAt(1, legenda); //LEGENDA DO 

        if (this.funcionarioModel.getCargo().equals("Funcionário")) {

            jButtonAlterar.setEnabled(false);
            jButtonExcluir.setEnabled(false);
            jButtonCadastrar.setEnabled(false);

        }
    }

    public void ativaCampos(boolean status) {

        jTextFieldValor.setEditable(status);
        jTextFieldCodigoBalanca.setEditable(status);
        jTextFieldCodigoBarra.setEditable(status);
        jTextFieldDescricao.setEditable(status);
        
        jTextFieldDataCadastro.setVisible(false);
        jTextFieldDataCadastro.setEditable(false);
        jLabelDataCadastro.setVisible(false);

    }

    public void atualizaTabelaSParametro() throws SQLException, ParseException {

        ProdutoDAO produtoControl = new ProdutoDAO();
        ArrayList<ProdutoModel> listaDeProdutos = produtoControl.findAll();
        atualizaTabelaProdutos(listaDeProdutos);

    }

    public void atualizaTabelaProdutos(ArrayList<ProdutoModel> listaProdutos) {
        Locale localBrasil = new Locale("pt", "BR");
        DefaultTableModel val = (DefaultTableModel) jTableProduto.getModel();
        val.setNumRows(0); // excluir os registros que estão na JTable
        //listaDeClientes = listaClientes;

        // Arrumar essa validação 
        if (jTableProduto != null) {
              
            listaProdutos.forEach((produto) -> {
                
                 //if(produto.getStatus()==true){
                    if(jRadioButton1.isSelected() && produto.getStatus()==true){
                        
                    String valorAtual = NumberFormat.getCurrencyInstance(localBrasil).format(produto.getValor());
                    val.addRow(new Object[]{produto.getCodigoBarra(), produto.getNome(), valorAtual, produto.getUnidadeMedida(), produto.getCodigoBalanca(), produto.getCodigoBarra()});
//}
                        
                    }
                    else if(jRadioButton2.isSelected() && produto.getStatus()==false){

                    String valorAtual = NumberFormat.getCurrencyInstance(localBrasil).format(produto.getValor());
                    val.addRow(new Object[]{produto.getCodigoBarra(), produto.getNome(), valorAtual, produto.getUnidadeMedida(), produto.getCodigoBalanca(), produto.getCodigoBarra()});
//}
                    

                    } 
                   // String valorAtual = NumberFormat.getCurrencyInstance(localBrasil).format(produto.getValor());
                   // val.addRow(new Object[]{produto.getId(), produto.getNome(), valorAtual, produto.getUnidadeMedida(), produto.getCodigoBalanca(), produto.getCodigoBarra()});
//}
            });
        
        }
    }

    public void atualizaTabelaProduto(ProdutoModel produto) {
        Locale localBrasil = new Locale("pt", "BR");
        DefaultTableModel val = (DefaultTableModel) jTableProduto.getModel();
        val.setNumRows(0); // excluir os registros que estão na JTable
        //listaDeClientes = listaClientes;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date data = produto.getDataCadastro();
        String dataString = dateFormat.format(data);

        if (jTableProduto != null) {
            if(produto.getStatus()==true){
             String valorAtual = NumberFormat.getCurrencyInstance(localBrasil).format(produto.getValor());
            val.addRow(new Object[]{produto.getCodigoBarra(), produto.getNome(), valorAtual, produto.getUnidadeMedida(), produto.getCodigoBalanca(), produto.getCodigoBarra(), dataString});
            }
        }
    }
    
   
    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed
       
        jTextFieldValor.setDocument(new FormatacaoDinamicaDinheiro());
        jTextFieldValor.setText("0");
        int tamanho, codigoProduto;
        String codigoProdutoString;

        ProdutoDAO produtoControl = new ProdutoDAO();
        ArrayList<ProdutoModel> listaP = new ArrayList<>();
        limpaCampos();
        try {
            listaP = produtoControl.findAll();
        } catch (SQLException ex) {
            Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tamanho = listaP.size();
        codigoProduto = tamanho + 1;
        codigoProdutoString = String.valueOf(codigoProduto);
      
//        jTextFieldCodigoProduto.setText(codigoProdutoString);
        
        jTabbedPane1.setSelectedIndex(1);
        jTabbedPane1.setEnabledAt(0, false);
        jTabbedPane1.setEnabledAt(1, true);
      
        jButtonCancelar.setEnabled(true);
        jButtonSalvar.setEnabled(true);
        jButtonAlterar.setEnabled(false);
        jButtonExcluir.setEnabled(false);
        
        

    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed

        int resposta, id;
        
        resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir o Produto ? ", "Confirmar exclusao de produto",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
        if (resposta == 0) {

            int linha = -1;
            ArrayList<ProdutoModel> listaProdutos;
            ProdutoDAO produtoControl = new ProdutoDAO();
            ProdutoModel produto = new ProdutoModel();
            linha = jTableProduto.getSelectedRow();

            if (linha == -1) {

                JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta", JOptionPane.WARNING_MESSAGE);

            } else {

                String codigoBarras = (String)jTableProduto.getValueAt(linha, 0);

                try {
                    
                    produto = produtoControl.pesquisarCodigoBarras(codigoBarras);
                    produto.setStatus(false);
                    String msg = produtoControl.excluir(produto);
                    JOptionPane.showMessageDialog(null, msg);

                    listaProdutos = produtoControl.findAll();
                    atualizaTabelaProdutos(listaProdutos);

                } catch (SQLException ex) {
                    Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NaoEncontradoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }

        }
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        if(this.tela.equals("telaPrincipal")){
                            super.dispose();
        }
        setaDialog(0, true, true, true, false, false, true, true, false, "Cadastro");
        limpaCampos();
        ativaCampos(true);
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed
        
        Mascara mascara = new Mascara();
        String valorTotalSemMascara;
        String vTotal = jTextFieldValor.getText();
        
        String valorSemMascara = mascara.limpaMascara(vTotal, "valor");
        
        double totalDouble = Double.parseDouble(valorSemMascara);
        
        totalDouble = totalDouble / 100;
        
        valorTotalSemMascara = String.valueOf(totalDouble);
        valorTotalSemMascara = mascara.limpaMascara(valorTotalSemMascara, "valor");
        
        int codBalanca, resposta;
        String unidadeMedida = "", retornaCodigoBalanca;
        Date data = new Date(System.currentTimeMillis());
        Double valor = 0.0;
        
        ProdutoModel produtoModel = new ProdutoModel();
        ProdutoDAO produtoControl = new ProdutoDAO();

        int index = jComboBoxUnidadeMedida.getSelectedIndex();
        //validar campos em branco

        try {

            jTextFieldValor.setEditable(true);
        } catch (NumberFormatException ex) {

        }

        if (index == 0) {
            unidadeMedida = "UNID";
        } else if (index == 1) {
            unidadeMedida = "KG";
        }
        
        
        produtoModel.setCodigoBarra(jTextFieldCodigoBarra.getText());
        produtoModel.setUnidadeMedida(unidadeMedida);
        produtoModel.setNome(jTextFieldDescricao.getText());

        // produtoModel.setValor(valor);
        //SE O BOTÃO CADASTRAR ESTIVER ATIVO
        if (jButtonCadastrar.isEnabled()) {

            produtoModel.setDataCadastro(data);

            resposta = JOptionPane.showConfirmDialog(null, "Deseja efetuar o cadastro ?", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (resposta == 0) {
                try {

                    if ("".equals(produtoModel.getCodigoBarra()) || "".equals(produtoModel.getNome())
                            || "".equals(produtoModel.getValor()) || "".equals(produtoModel.getUnidadeMedida())) {

                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatorios", "Alerta", JOptionPane.WARNING_MESSAGE);

                    } else {
                        codBalanca = Integer.parseInt(jTextFieldCodigoBalanca.getText());
                        produtoModel.setCodigoBalanca(codBalanca);
                        //valor = Double.parseDouble(valorTotalSemMascara);
                        produtoModel.setValor(totalDouble);///0000000
                        ProdutoModel produto = new ProdutoModel();
                       // produtoControl.verificaCadastro(jTextFieldCodigoBalanca.getText(), "codigo_balanca","cadastro");
                        String codigoBarras = jTextFieldCodigoBarra.getText();
                      
                        //produto = produtoControl.verificaCadastro(codigoBarras,"codigo_barras","cadastro");
                        String p = produtoControl.verificaCadastroString(codigoBarras,"codigo_barras","cadastro");
                        
                        if(p.equals("ProdutoDesativado")){
                                
                                int b = JOptionPane.showConfirmDialog(null, "Este produto já esta cadastrado no sistema, porem, está desativado, deseja reativa-lo?", "Não é possivel efetuar o cadastro!",
                                            JOptionPane.YES_NO_OPTION,
                                            JOptionPane.ERROR_MESSAGE);
                                    if (b == 0) {
                                        
                                        //ATIVA O CLIENTE
                                        produto = produtoControl.pesquisarCodigoBarras(codigoBarras);
                                        ativaProduto(produto);
                                        JOptionPane.showMessageDialog(null, "O produto foi reativado! verifique a tabela de produtos");
                                        atualizaTabelaSParametro();
                                        setaDialog(0, true, true, true, false, false, true, true, false, "Cadastrar");
                                        ativaCampos(true);
                                        
                                    } else if (b == 1) {
                                        jTextFieldCodigoBarra.requestFocus();
                                    }
                                
                        }else{
                        produtoModel.setStatus(true);
                        
                        produtoControl.inserir(produtoModel);
                        atualizaTabelaSParametro();
                        produtoModel = produtoControl.pesquisarCodigoBarras(jTextFieldCodigoBarra.getText());
                        //ADICIONA E DEIXA DESATIVADO O PRODUTO NO ESTOQUE
                        EstoqueModel estoque = new EstoqueModel();
                        EstoqueDAO estoqueDao = new EstoqueDAO();
                        estoque.setFuncionario(funcionarioModel);
                        estoque.setProduto(produtoModel);
                        estoque.setQtdEstoque(0);
                        estoque.setQtdMinima(0);
                        estoque.setStatus(false);
                        estoqueDao.inserir(estoque);
                        
                        limpaCampos();
                        if(this.tela.equals("telaPrincipal")){
                            super.dispose();
                        }
                        
                        setaDialog(0, true, true, true, false, false, true, true, false, "Cadastro");
                        }
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JaExisteException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (NaoEncontradoException ex) {
                    Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } //SE O BOTÃO ALTERAR ESTIVER ATIVO
        else if (jButtonAlterar.isEnabled()) {
            retornaCodigoBalanca = Integer.toString(this.codigoBalanca);
            produtoModel.setId(this.idProduto);
            produtoModel.setDataCadastro(this.dataCadastro);
            resposta = JOptionPane.showConfirmDialog(null, "Deseja alterar produto?", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (resposta == 0) {
                try {
                    if ("".equals(produtoModel.getCodigoBarra()) || "".equals(produtoModel.getNome())
                            || "".equals(produtoModel.getValor()) || "".equals(produtoModel.getUnidadeMedida())) {

                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatorios", "Alerta", JOptionPane.WARNING_MESSAGE);
                    } else {
                        codBalanca = Integer.parseInt(jTextFieldCodigoBalanca.getText());
                        produtoModel.setCodigoBalanca(codBalanca);
                   
                        //valor = Double.parseDouble(valorTotalSemMascara);
                        produtoModel.setValor(totalDouble);
                       
                        if(!retornaCodigoBalanca.equals(jTextFieldCodigoBalanca.getText())){
                        produtoControl.verificaCadastro(jTextFieldCodigoBalanca.getText(), "codigo_balanca","cadastro");
                        }  
                        if(!this.codigoBarra.equals(jTextFieldCodigoBarra.getText())){
                        produtoControl.verificaCadastro(jTextFieldCodigoBarra.getText(), "codigo_barras","cadastro");
                        }
                        produtoModel.setStatus(true);
                        produtoControl.alterar(produtoModel);
                        if(this.tela.equals("telaPrincipal")){
                            super.dispose();
                        }
                        
                        limpaCampos();
                        atualizaTabelaSParametro();
                        setaDialog(0, true, true, true, false, false, true, true, false, "Cadastro");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JaExisteException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }

        }


    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
        Locale localBrasil = new Locale("pt", "BR");
        ProdutoDAO produtoControl = new ProdutoDAO();
        ProdutoModel produto = new ProdutoModel();

        int id, linha = -1;

        String codigoBalancaStrings,codigoBalancaString, valorString, codigodeProduto, unidadeMedida;
        linha = jTableProduto.getSelectedRow();

        if (linha == -1) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta", JOptionPane.WARNING_MESSAGE);

        } else {

            codigoBalancaStrings = (String) jTableProduto.getValueAt(linha, 0);

            try {
                produto = produtoControl.pesquisarCodigoBarras(codigoBalancaStrings);
            } catch (SQLException ex) {
                Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NaoEncontradoException ex) {
                Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
            }

            codigoBalancaString = String.valueOf(produto.getCodigoBalanca());
            Double valor = produto.getValor();
            valorString = String.valueOf(valor);
           
            String valorTotalString =  valorString;
            
            jTextFieldValor.setDocument(new FormatacaoDinamicaDinheiro());
            jTextFieldValor.setText("0");
            
            valorTotalString = NumberFormat.getCurrencyInstance(localBrasil).format(valor);
            jTextFieldValor.setText(valorTotalString);
            
            
            
            this.codigoBarra = produto.getCodigoBarra();
            this.codigoBalanca = produto.getCodigoBalanca();
            codigodeProduto = String.valueOf(produto.getId());
            this.dataCadastro = produto.getDataCadastro();
            jTextFieldDescricao.setText(produto.getNome());
            jTextFieldCodigoBalanca.setText(codigoBalancaString);
            
           // valorString = valorString.replace(",", "");
            //valorString = valorString.replace(".", "");
            
            //jTextFieldValor.setText("0");
           
            
            
            jTextFieldCodigoBarra.setText(produto.getCodigoBarra());
//            jTextFieldCodigoProduto.setText(codigodeProduto);
            unidadeMedida = produto.getUnidadeMedida();
            jComboBoxUnidadeMedida.setSelectedItem(unidadeMedida);

            this.idProduto = produto.getId();
        
            jTabbedPane1.setSelectedIndex(1);
            jTabbedPane1.setEnabledAt(0, false);
            jButtonCadastrar.setEnabled(false);
            jButtonExcluir.setEnabled(false);
            jButtonSalvar.setEnabled(true);
            jButtonCancelar.setEnabled(true);
            jButtonAlterar.setEnabled(true);
            jTabbedPane1.setEnabledAt(1, true);
            jTabbedPane1.setTitleAt(1, "Alterar");

        }

    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        super.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBoxUnidadeMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxUnidadeMedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxUnidadeMedidaActionPerformed

    private void jComboBoxTipoPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoPesquisaActionPerformed

        int index = jComboBoxTipoPesquisa.getSelectedIndex();

        String caractere;

        switch (index) {

            case 0: {

                jLabelLegendaPesquisa.setText("Nome:");

            }

            break;

            case 1: //Codigo
            {

                jLabelLegendaPesquisa.setText("Código:");

            }

            break;

        }

    }//GEN-LAST:event_jComboBoxTipoPesquisaActionPerformed

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed

        int index = jComboBoxTipoPesquisa.getSelectedIndex(), codigoInt;

        String nome, codigo;

        ProdutoDAO produtoControl = new ProdutoDAO();
        ProdutoModel produtoModel = new ProdutoModel();
        ArrayList<ProdutoModel> listaProdutos = new ArrayList<>();

        switch (index) {

            case 0: {//NOME
                nome = jTextFieldPesquisar.getText();

                try {
                    listaProdutos = produtoControl.pesquisarNomeEspecifico(nome);
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
                codigoInt = Integer.parseInt(codigo);
                    
                try {
                    
                    produtoModel = produtoControl.pesquisaId(codigoInt);
                    atualizaTabelaProduto(produtoModel);
                    
                } catch (SQLException ex) {
                    
                    Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
                        
                } catch (NaoEncontradoException ex) {
                    
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    
                }
                
            }

            break;

        }

    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jTextFieldPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPesquisarActionPerformed

    }//GEN-LAST:event_jTextFieldPesquisarActionPerformed

    private void jTextFieldPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisarKeyReleased

        int index = jComboBoxTipoPesquisa.getSelectedIndex(), codigoInt;
        ProdutoDAO produtoControl = new ProdutoDAO();
        ProdutoModel produtoModel = new ProdutoModel();
        ArrayList<ProdutoModel> listProdutos;

        String nome, codigo;
        switch (index) {

            case 0: {//NOME

                nome = jTextFieldPesquisar.getText().trim();

                if (nome.equals("")) {

                    try {

                        listProdutos = produtoControl.findAll();
                        atualizaTabelaProdutos(listProdutos);

                    } catch (SQLException ex) {
                        Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else if (!nome.equals(" ")) {

                    try {

                        listProdutos = produtoControl.pesquisarNome(nome);
                        atualizaTabelaProdutos(listProdutos);

                    } catch (SQLException ex) {
                        Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NaoEncontradoException ex) {
                        try {
                            atualizaTabelaSParametro();
                        } catch (SQLException ex1) {
                            Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (ParseException ex1) {
                            Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }

                }
            }

            break;

            case 1: //Codigo
            {
                codigo = jTextFieldPesquisar.getText().trim();

                if (codigo.equals("")) {

                    try {

                        listProdutos = produtoControl.findAll();
                        atualizaTabelaProdutos(listProdutos);

                    } catch (SQLException ex) {
                        Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else if (!codigo.equals(" ")) {

                    try {
                        codigoInt = Integer.parseInt(codigo);

                        produtoModel = produtoControl.pesquisaId(codigoInt);
                        atualizaTabelaProduto(produtoModel);
                    } catch (SQLException ex) {
                        try {
                            atualizaTabelaSParametro();
                        } catch (SQLException ex1) {
                            Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (ParseException ex1) {
                            Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    } catch (NaoEncontradoException ex) {
                        try {
                            atualizaTabelaSParametro();
                        } catch (SQLException ex1) {
                            Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (ParseException ex1) {
                            Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }

                }

            }
        }
    }//GEN-LAST:event_jTextFieldPesquisarKeyReleased

    private void jButtonConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultarActionPerformed

        ProdutoDAO produtoControl = new ProdutoDAO();
        ProdutoModel produto = new ProdutoModel();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");

        int  linha = -1;

        String codigoBalancaString, valorString, codigodeProduto, unidadeMedida, dataString, codigoBarra;
        linha = jTableProduto.getSelectedRow();

        if (linha == -1) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta", JOptionPane.WARNING_MESSAGE);

        } else {
           
            codigoBarra = (String) jTableProduto.getValueAt(linha, 0);

            try {
                produto = produtoControl.pesquisarCodigoBarras(codigoBarra);
            } catch (SQLException ex) {
                Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NaoEncontradoException ex) {
                Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
            }

            codigoBalancaString = String.valueOf(produto.getCodigoBalanca());
            valorString = String.valueOf(produto.getValor());

            codigodeProduto = String.valueOf(produto.getId());
            this.dataCadastro = produto.getDataCadastro();
            jTextFieldDescricao.setText(produto.getNome());
            jTextFieldCodigoBalanca.setText(codigoBalancaString);
            jTextFieldValor.setText(valorString);
            jTextFieldCodigoBarra.setText(produto.getCodigoBarra());
//            jTextFieldCodigoProduto.setText(codigodeProduto);
            dataString = formatador.format(produto.getDataCadastro());

            jTextFieldDataCadastro.setText(dataString);
            unidadeMedida = produto.getUnidadeMedida();
            jComboBoxUnidadeMedida.setSelectedItem(unidadeMedida);
            this.idProduto = produto.getId();

            setaDialog(1, false, false, false, false, true, true, false, true, "Consultar");
            jTextFieldDataCadastro.setVisible(true);
            jLabelDataCadastro.setVisible(true);

            jTextFieldCodigoBalanca.setEditable(false);
            jTextFieldCodigoBarra.setEditable(false);
//            jTextFieldCodigoProduto.setEditable(false);
            jTextFieldDescricao.setEditable(false);
            jTextFieldPesquisar.setEditable(false);
            jComboBoxUnidadeMedida.setEnabled(false);

            jTextFieldValor.setEditable(false);

        }


    }//GEN-LAST:event_jButtonConsultarActionPerformed

    private void jTextFieldCodigoBalancaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCodigoBalancaFocusLost

    }//GEN-LAST:event_jTextFieldCodigoBalancaFocusLost


    private void jTextFieldCodigoBarraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCodigoBarraFocusLost

    }//GEN-LAST:event_jTextFieldCodigoBarraFocusLost

    public double converteDouble(String valor) throws NumberFormatException {
        return Double.parseDouble(valor);
    }


    private void jTextFieldValorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldValorKeyReleased


    }//GEN-LAST:event_jTextFieldValorKeyReleased

    private void jTextFieldValorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldValorKeyTyped
        int qtdCaracteres = 10;
        char c = evt.getKeyChar();
        if(jTextFieldValor.getText().equals(".")){
        if (!Character.isDigit(c) ) {
            evt.consume();
        }
        }
        if (jTextFieldValor.getText().length() >= qtdCaracteres) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldValorKeyTyped

    private void jTextFieldCodigoBalancaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCodigoBalancaKeyTyped

        int qtdCaracteres = 10;
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }

        if (jTextFieldCodigoBalanca.getText().length() >= qtdCaracteres) {
            evt.consume();
        }
        
    }//GEN-LAST:event_jTextFieldCodigoBalancaKeyTyped

    private void jTextFieldCodigoBarraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCodigoBarraKeyTyped
        int qtdCaracteres = 20;

        if (jTextFieldCodigoBarra.getText().length() >= qtdCaracteres) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldCodigoBarraKeyTyped

    private void jTextFieldDescricaoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDescricaoKeyTyped
        int qtdCaracteres = 100;
        char c = evt.getKeyChar();

        if (jTextFieldDescricao.getText().length() >= qtdCaracteres) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldDescricaoKeyTyped

    private void jTextFieldPesquisarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisarKeyTyped
        int qtdCaracteres = 100;

        if (jTextFieldPesquisar.getText().length() >= qtdCaracteres) {
            evt.consume();
        }
        
    }//GEN-LAST:event_jTextFieldPesquisarKeyTyped

    private void jTextFieldCodigoBarraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodigoBarraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCodigoBarraActionPerformed

    private void jButtonStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStatusActionPerformed
         int resposta;

        resposta = JOptionPane.showConfirmDialog(null, "Deseja ativar o Produto ?", "Confirma",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (resposta == 0) {

            int linha = -1;
            
            ArrayList<ProdutoModel> listaProduto;
            ProdutoModel produto = new ProdutoModel();
            
            ProdutoDAO produtoDao = new ProdutoDAO();
            
            linha = jTableProduto.getSelectedRow();

            if (linha == -1) {
                
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta",JOptionPane.WARNING_MESSAGE);

            } else {
                
                try {
                    
                    String codigo = (String) jTableProduto.getValueAt(linha, 0);
                    produto = produtoDao.pesquisarCodigoBarras(codigo);
                    produto.setStatus(true);
                    produtoDao.alterar(produto);
                    atualizaTabelaSParametro();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NaoEncontradoException ex) {
                    Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }
        }
        
    }//GEN-LAST:event_jButtonStatusActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        try {
            
            jButtonAlterar.setEnabled(true);
            jButtonCadastrar.setEnabled(true);
            jButtonExcluir.setEnabled(true);
            jButtonStatus.setEnabled(false);
            
            atualizaTabelaSParametro();
        } catch (SQLException ex) {
            Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        try {
            
            jButtonAlterar.setEnabled(false);
            jButtonCadastrar.setEnabled(false);
            jButtonCancelar.setEnabled(false);
            jButtonExcluir.setEnabled(false);
            jButtonSalvar.setEnabled(false);
            jButtonStatus.setEnabled(true);
            atualizaTabelaSParametro();
            
        } catch (SQLException ex) {
            Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(ManterProdutoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManterProdutoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManterProdutoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManterProdutoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ManterProdutoView dialog = null;
                try {
                    dialog = new ManterProdutoView(new javax.swing.JFrame(), true, "");
                } catch (SQLException ex) {
                    Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                }
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
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonCadastrar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonConsultar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonStatus;
    private javax.swing.JComboBox<String> jComboBoxTipoPesquisa;
    private javax.swing.JComboBox<String> jComboBoxUnidadeMedida;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDataCadastro;
    private javax.swing.JLabel jLabelLegendaPesquisa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableProduto;
    private javax.swing.JTextField jTextFieldCodigoBalanca;
    private javax.swing.JTextField jTextFieldCodigoBarra;
    private javax.swing.JTextField jTextFieldDataCadastro;
    private javax.swing.JTextField jTextFieldDescricao;
    private javax.swing.JTextField jTextFieldPesquisar;
    private javax.swing.JTextField jTextFieldValor;
    // End of variables declaration//GEN-END:variables
}
