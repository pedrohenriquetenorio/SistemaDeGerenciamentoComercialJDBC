package Visao;

import Control.Exceptions.JaExisteException;
import Control.Exceptions.NaoEncontradoException;
import DAO.ContasPagarDAO;
import DAO.FornecedorDAO;
import Modelo.ContaPagarModel;
import Modelo.FornecedorModel;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class ManterFornecedorView extends javax.swing.JDialog {

    int idFornecedor;
    Date dataCadastro;
    String cnpj;
    String tela;
    
    public ManterFornecedorView(java.awt.Frame parent, boolean modal, String tela) throws SQLException, ParseException {
        super(parent, modal);
        initComponents();

        limparCampos();
        setaCampoPesquisa();
        jRadioButton1.setSelected(true);
        jTextFieldDataCadastro.setEditable(false);
        jTabbedPane1.setEnabledAt(1, false);
        jButtonCancelar.setEnabled(false);
        jButtonSalvar.setEnabled(false);
        this.tela = tela;
        
         if(this.tela.equals("telaPrincipal")){
            
            setaDialog(1, true, false, false, true, true, false, false, true, "Cadastrar");
            
        }else{
            
            setaDialog(0, true, true, true, false, false, true, true, false, "Cadastro");
            ativaCampos(true);
            
        }
         
        FornecedorDAO fornecedorControl = new FornecedorDAO();
        ArrayList<FornecedorModel> listaDeFornecedores = fornecedorControl.findAll();
        atualizaTabelaFornecedores(listaDeFornecedores);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel6 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButtonExcluir = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jButtonAdicionar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButtonConsultar = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jButtonPesquisar = new javax.swing.JButton();
        jLabelLegendaPesquisa = new javax.swing.JLabel();
        jFormattedTextFieldPesquisar = new javax.swing.JFormattedTextField();
        jComboBoxTipoPesquisa = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFornecedor = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel11 = new javax.swing.JPanel();
        jButtonStatus = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jTextFieldRua = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jTextFieldCidade = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jTextFieldBairro = new javax.swing.JTextField();
        jPanelCEP = new javax.swing.JPanel();
        jFormattedTextFieldCep = new javax.swing.JFormattedTextField();
        jPanel21 = new javax.swing.JPanel();
        jComboBoxUF = new javax.swing.JComboBox<>();
        jPanel22 = new javax.swing.JPanel();
        jTextFieldNumero = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabelEmail = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jTextFieldNome = new javax.swing.JTextField();
        jPanelCNPJ = new javax.swing.JPanel();
        jFormattedTextFieldCnpj = new javax.swing.JFormattedTextField();
        jPanel12 = new javax.swing.JPanel();
        jTextFieldEmail = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jFormattedTextFieldTelefone = new javax.swing.JFormattedTextField();
        jPanel14 = new javax.swing.JPanel();
        jFormattedTextFieldCelular = new javax.swing.JFormattedTextField();
        jPanel15 = new javax.swing.JPanel();
        jTextFieldDataCadastro = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jComboBoxCategoria = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Controle de Fornecedor");

        jPanel6.setBackground(new java.awt.Color(3, 133, 188));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Controle de Fornecedores");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(135, 135, 135))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/excluir24x24.png"))); // NOI18N
        jButtonExcluir.setText(" Excluir");
        jButtonExcluir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonExcluir.setMargin(new java.awt.Insets(2, 11, 2, 11));
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/voltar24x24.png"))); // NOI18N
        jButtonCancelar.setText(" Cancelar  ");
        jButtonCancelar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/salvar24x24.png"))); // NOI18N
        jButtonSalvar.setText(" Salvar      ");
        jButtonSalvar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/adicionar24x24.png"))); // NOI18N
        jButtonAdicionar.setText(" Adicionar");
        jButtonAdicionar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarActionPerformed(evt);
            }
        });

        jButtonAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Atualizar24x24.png"))); // NOI18N
        jButtonAlterar.setText("Editar         ");
        jButtonAlterar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/VoltarSimples24x24.png"))); // NOI18N
        jButton7.setText("Fechar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButtonConsultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/consulta24x24.png"))); // NOI18N
        jButtonConsultar.setText("Consultar");
        jButtonConsultar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(jButtonSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(jButtonCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(jButtonConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(jButtonExcluir, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(jButtonAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Pesquisar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jButtonPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lupa.png"))); // NOI18N
        jButtonPesquisar.setText("Pesquisar");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

        try {
            jFormattedTextFieldPesquisar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldPesquisar.setToolTipText("");
        jFormattedTextFieldPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldPesquisarActionPerformed(evt);
            }
        });
        jFormattedTextFieldPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldPesquisarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldPesquisarKeyTyped(evt);
            }
        });

        jComboBoxTipoPesquisa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "CNPJ" }));
        jComboBoxTipoPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoPesquisaActionPerformed(evt);
            }
        });

        jLabel14.setText("Tipo:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jComboBoxTipoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addGap(42, 42, 42)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelLegendaPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jFormattedTextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPesquisar)))
                .addGap(414, 414, 414))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel14))
                    .addComponent(jLabelLegendaPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxTipoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPesquisar))
                .addGap(13, 13, 13))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tabela de Fornecedores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTableFornecedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Cnpj", "Telefone", "Celular", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableFornecedor);
        if (jTableFornecedor.getColumnModel().getColumnCount() > 0) {
            jTableFornecedor.getColumnModel().getColumn(0).setResizable(false);
            jTableFornecedor.getColumnModel().getColumn(1).setResizable(false);
            jTableFornecedor.getColumnModel().getColumn(2).setResizable(false);
            jTableFornecedor.getColumnModel().getColumn(3).setResizable(false);
            jTableFornecedor.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton2)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Ativar Fornecedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

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
            .addComponent(jButtonStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Principal", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Endereço", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Rua", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldRua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldRuaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldRua)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jTextFieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Cidade", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCidadeKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldCidade)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Bairro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldBairro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBairroKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldBairro)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanelCEP.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCEP.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "CEP", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        try {
            jFormattedTextFieldCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldCep.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldCepFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanelCEPLayout = new javax.swing.GroupLayout(jPanelCEP);
        jPanelCEP.setLayout(jPanelCEPLayout);
        jPanelCEPLayout.setHorizontalGroup(
            jPanelCEPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCEPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFormattedTextFieldCep, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelCEPLayout.setVerticalGroup(
            jPanelCEPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCEPLayout.createSequentialGroup()
                .addComponent(jFormattedTextFieldCep)
                .addContainerGap())
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "UF", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jComboBoxUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxUF, 0, 133, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jComboBoxUF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Numero", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNumeroKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextFieldNumero, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelCEP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Fornecedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nome", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeActionPerformed(evt);
            }
        });
        jTextFieldNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNomeKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jTextFieldNome, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanelCNPJ.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCNPJ.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "CNPJ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        try {
            jFormattedTextFieldCnpj.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###/####-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldCnpj.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldCnpjFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldCnpjFocusLost(evt);
            }
        });
        jFormattedTextFieldCnpj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldCnpjActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCNPJLayout = new javax.swing.GroupLayout(jPanelCNPJ);
        jPanelCNPJ.setLayout(jPanelCNPJLayout);
        jPanelCNPJLayout.setHorizontalGroup(
            jPanelCNPJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCNPJLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFormattedTextFieldCnpj, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelCNPJLayout.setVerticalGroup(
            jPanelCNPJLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCNPJLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFormattedTextFieldCnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Email", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jTextFieldEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTextFieldEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Telefone", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        try {
            jFormattedTextFieldTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldTelefone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldTelefoneFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFormattedTextFieldTelefone, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFormattedTextFieldTelefone)
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Celular", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        try {
            jFormattedTextFieldCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldCelular.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldCelularFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFormattedTextFieldCelular)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jFormattedTextFieldCelular)
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Data Cadastro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldDataCadastro)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextFieldDataCadastro)
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Categoria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jComboBoxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Produto", "Despesa", "Outros" }));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanelCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(304, 304, 304))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabelEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(166, 166, 166))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(43, 43, 43))))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 692, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cadastro", jPanel2);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    public void ativaFornecedor(FornecedorModel fornecedor){
        
        try {
            FornecedorDAO fornecedorDao = new FornecedorDAO();
            fornecedor.setStatus(true);
            fornecedorDao.alterar(fornecedor);
        } catch (SQLException ex) {
            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //LIMPA AS MASCARAS 
    String limpaMascara(String mascara, String tipo) {

        switch (tipo) {
            case "cnpj":
                mascara = mascara.replace("-", "");
                mascara = mascara.replace(".", "");
                mascara = mascara.replace("/", "");
                mascara = mascara.replace(" ", "");
                break;
            case "telefone":
                mascara = mascara.replace("(", "");
                mascara = mascara.replace(")", "");
                mascara = mascara.replace("-", "");
                mascara = mascara.replace(" ", "");
                break;
            case "celular":
                mascara = mascara.replace("(", "");
                mascara = mascara.replace(")", "");
                mascara = mascara.replace("-", "");
                mascara = mascara.replace(" ", "");
                break;
            case "cpf":
                mascara = mascara.replace("-", "");
                mascara = mascara.replace(".", "");
                mascara = mascara.replace(" ", "");
                break;
            case "cep":
                mascara = mascara.replace("-", "");
                mascara = mascara.replace(" ", "");
                break;
            default:
                break;
        }

        return mascara;
    }

    //INSERE MASCARA NOS FORMATFIELD
    String insereMascara(String mascara, String tipo) throws ParseException {

        switch (tipo) {
            case "cnpj": {
                MaskFormatter mf = new MaskFormatter("##.###.###/####-##");
                mf.setValueContainsLiteralCharacters(false);
                return mf.valueToString(mascara);

            }
            case "telefone": {
                MaskFormatter mf = new MaskFormatter("(##)####-####");
                mf.setValueContainsLiteralCharacters(false);
                return mf.valueToString(mascara);

            }
            case "celular": {
                MaskFormatter mf = new MaskFormatter("(##)#####-####");
                mf.setValueContainsLiteralCharacters(false);
                return mf.valueToString(mascara);

            }
            case "cpf": {
                MaskFormatter mf = new MaskFormatter("###.###.###-##");
                mf.setValueContainsLiteralCharacters(false);
                return mf.valueToString(mascara);

            }
            default:
                break;
        }

        return mascara;
    }

    //SETA VALORES NULOS NOS CAMPOS
    public void limparCampos() {

        jTextFieldNome.setText("");
        jTextFieldEmail.setText("");
        jTextFieldCidade.setText("");
        jTextFieldNumero.setText("");
        jTextFieldRua.setText("");
        jComboBoxUF.setSelectedIndex(0);
        jTextFieldBairro.setText("");
        jFormattedTextFieldCelular.setText("");
        jFormattedTextFieldCep.setText("");
        jFormattedTextFieldCnpj.setText("");
        jFormattedTextFieldTelefone.setText("");
       // BorderFactory bordaCNPJ;
       // jPanelCNPJ.Border;
       // BorderFactory.
        //jLabelCNPJ.setText("");
        jFormattedTextFieldCelular.setValue(null);
        jFormattedTextFieldCep.setValue(null);
        jFormattedTextFieldCnpj.setValue(null);
        jFormattedTextFieldTelefone.setValue(null);

        jTextFieldDataCadastro.setVisible(false);
        jTextFieldDataCadastro.setEditable(false);
       // jLabelDataCadastro.setVisible(false);

    }

    //INFORMA EM QUAL DIALOG ESTÁ E ATIVA OU DESATIVA BOTOES
    public void setaDialog(int telaAtual, boolean cadastro, boolean excluir,
            boolean alterar, boolean salvar, boolean cancelar, boolean consultar, boolean trocaTelaAtual,
            boolean trocaTelaAnterior, String legenda) {

        jTabbedPane1.setSelectedIndex(telaAtual); // TELA ATUAL
        jButtonAdicionar.setEnabled(cadastro); // BOTÃO CADASTRAR
        jButtonExcluir.setEnabled(excluir); // BOTÃO EXCLUIR
        jButtonAlterar.setEnabled(alterar); //BOTÃO ALTERAR
        jButtonSalvar.setEnabled(salvar); //BOTÃO SALVAR
        jButtonCancelar.setEnabled(cancelar); //BOTÃO CANCELAR
        jButtonConsultar.setEnabled(consultar);//BOTÃO CONSULTAR
        jTabbedPane1.setEnabledAt(0, trocaTelaAtual); //TELA ATUAL
        jTabbedPane1.setEnabledAt(1, trocaTelaAnterior); //TELA ANTERIOR 
        jTabbedPane1.setTitleAt(1, legenda); //LEGENDA DO 

    }

    //ATIVA OU DESATIVA OS CAMPOS DE TEXTFIELD DA AREA DE CADASTRO,ALTERAÇÃO E CONSULTA
    public void ativaCampos(boolean status) {
        jComboBoxUF.setEnabled(status);
        jFormattedTextFieldCep.setEditable(status);
        jFormattedTextFieldCelular.setEditable(status);
        jFormattedTextFieldTelefone.setEditable(status);
        jFormattedTextFieldCnpj.setEditable(status);
        jTextFieldBairro.setEditable(status);
        jTextFieldCidade.setEditable(status);
        jTextFieldEmail.setEditable(status);
        jTextFieldNome.setEditable(status);
        jTextFieldNumero.setEditable(status);
        jTextFieldRua.setEditable(status);
    }

    public void atualizaTabelaSParametro() throws SQLException, ParseException {
        FornecedorDAO fornecedorControl = new FornecedorDAO();
        ArrayList<FornecedorModel> listaDeFornecedores = fornecedorControl.findAll();
        atualizaTabelaFornecedores(listaDeFornecedores);
    }

    public void atualizaTabelaFornecedores(ArrayList<FornecedorModel> listaFornecedores) {

        DefaultTableModel val = (DefaultTableModel) jTableFornecedor.getModel();
        val.setNumRows(0); // excluir os registros que estão na JTable
        //listaDeClientes = listaClientes;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Arrumar essa validação 
        if (jTableFornecedor != null) {

            listaFornecedores.forEach((fornecedor) -> {
                String cnpj, cep, telefone, celular;
               // if(fornecedor.getStatus()==true){
                try {
                    cnpj = insereMascara(fornecedor.getCnpj(), "cnpj");
                    cep = insereMascara(fornecedor.getCep(), "cep");
                    telefone = insereMascara(fornecedor.getTelefone(), "telefone");
                    celular = insereMascara(fornecedor.getCelular(), "celular");

                    fornecedor.setCnpj(cnpj);
                    fornecedor.setCep(cep);
                    fornecedor.setTelefone(telefone);
                    fornecedor.setCelular(celular);

                } catch (ParseException ex) {
                    Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
                }
                Date data = fornecedor.getDataCadastro();
                String dataString = dateFormat.format(data);
                
                if(jRadioButton1.isSelected() && fornecedor.getStatus()==true){
                    
                   val.addRow(new Object[]{fornecedor.getNome(), fornecedor.getCnpj(), fornecedor.getTelefone(), fornecedor.getCelular(), fornecedor.getEmail(), fornecedor.getRua(), fornecedor.getNumero(), fornecedor.getBairro(), fornecedor.getCidade(), fornecedor.getUf(), fornecedor.getCep(), dataString});
                }
                else if(jRadioButton2.isSelected() && fornecedor.getStatus()==false){
                    
                    val.addRow(new Object[]{fornecedor.getNome(), fornecedor.getCnpj(), fornecedor.getTelefone(), fornecedor.getCelular(), fornecedor.getEmail(), fornecedor.getRua(), fornecedor.getNumero(), fornecedor.getBairro(), fornecedor.getCidade(), fornecedor.getUf(), fornecedor.getCep(), dataString});
                
                }
                
                //val.addRow(new Object[]{fornecedor.getNome(), fornecedor.getCnpj(), fornecedor.getTelefone(), fornecedor.getCelular(), fornecedor.getEmail(), fornecedor.getRua(), fornecedor.getNumero(), fornecedor.getBairro(), fornecedor.getCidade(), fornecedor.getUf(), fornecedor.getCep(), dataString});
               // }
            });
        } else {

        }
    }

    public void atualizaTabelaFornecedor(FornecedorModel fornecedor) {

        DefaultTableModel val = (DefaultTableModel) jTableFornecedor.getModel();
        val.setNumRows(0); // excluir os registros que estão na JTable
        //listaDeClientes = listaClientes;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date data = fornecedor.getDataCadastro();
        String dataString = dateFormat.format(data);
        String cnpj, cep, telefone, celular;

        if (jTableFornecedor != null) {
            try {
                if(fornecedor.getStatus()==true){
                    
                cnpj = insereMascara(fornecedor.getCnpj(), "cnpj");
                cep = insereMascara(fornecedor.getCep(), "cep");
                telefone = insereMascara(fornecedor.getTelefone(), "telefone");
                celular = insereMascara(fornecedor.getCelular(), "celular");

                fornecedor.setCnpj(cnpj);
                fornecedor.setCep(cep);
                fornecedor.setTelefone(telefone);
                fornecedor.setCelular(celular);
                 
                
                val.addRow(new Object[]{fornecedor.getNome(), fornecedor.getCnpj(), fornecedor.getTelefone(), fornecedor.getCelular(), fornecedor.getEmail(), fornecedor.getRua(), fornecedor.getNumero(), fornecedor.getBairro(), fornecedor.getCidade(), fornecedor.getUf(), fornecedor.getCep(), dataString});
                }
            } catch (ParseException ex) {
                Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
            }

          
        }
    }

    public void setaCampoPesquisa() throws ParseException {
        jLabelLegendaPesquisa.setText("Nome:");
        jFormattedTextFieldPesquisar.setValue(null);
        jFormattedTextFieldPesquisar.setFormatterFactory(null);
        jFormattedTextFieldPesquisar.setText("");
    }

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
        int resposta, id;
        boolean verifica = false;
        resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir fornecedor?", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (resposta == 0) {

            int linha = -1;
            
            ArrayList<FornecedorModel> listaFornecedores;
            FornecedorDAO fornecedorControl = new FornecedorDAO();
            FornecedorModel fornecedor = new FornecedorModel();
            
            linha = jTableFornecedor.getSelectedRow();

            if (linha == -1) {

                JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta", JOptionPane.WARNING_MESSAGE);

            } else {

                String cnpj = (String) jTableFornecedor.getValueAt(linha, 1);
                String nomeFornecedor = (String) jTableFornecedor.getValueAt(linha, 0);
                cnpj = limpaMascara(cnpj, "cnpj");

                try {
                   
                    ContasPagarDAO contaDao = new ContasPagarDAO();
                    ArrayList<ContaPagarModel>listaConta = new ArrayList<>();
                    ArrayList<ContaPagarModel>contasEmAberto = new ArrayList<>();
                    listaConta = contaDao.findAll();
                    
                    for(ContaPagarModel l : listaConta){
                        if(l.getFornecedor().getNome().equals(nomeFornecedor)){ 
                            if(l.getSituacao().equals("A Pagar")){
                                contasEmAberto.add(l);
                            }
                        }
                    }
                    
                    if(contasEmAberto.isEmpty()){
                        
                        fornecedor = fornecedorControl.pesquisar(cnpj, "cnpj");
                        fornecedor.setStatus(false);
                        String msg = fornecedorControl.excluir(fornecedor);
                        for(ContaPagarModel l : listaConta){
                        if(l.getFornecedor().getNome().equals(nomeFornecedor)){ 
                           
                                contaDao.excluir(l);
                            
                        }
                    }
                        
                        JOptionPane.showMessageDialog(null, msg);
                        listaFornecedores = fornecedorControl.findAll();
                        atualizaTabelaFornecedores(listaFornecedores);
                        
                    }else{
                        JOptionPane.showMessageDialog(null, "Há contas em aberto para este fornecedor", "Verifique Contas a Pagar",  JOptionPane.ERROR_MESSAGE);
                    }
                    

                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Não foi possivel Excluir o fornecedor", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (NaoEncontradoException ex) {
                    Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed

        VerificaCampos verificaCampo = new VerificaCampos();

        boolean verifica = false, verificaCnpj=false;
        String cnpj, cep, celular, telefone;
        int resposta;
        Date data = new Date(System.currentTimeMillis());
        FornecedorModel fornecedorModel = new FornecedorModel();
        FornecedorDAO fornecedorControl = new FornecedorDAO();

        cnpj = jFormattedTextFieldCnpj.getText();
        cnpj = limpaMascara(cnpj, "cnpj");

        verifica = verificaCampo.verificaCNPJ(cnpj);

        celular = jFormattedTextFieldCelular.getText();
        celular = limpaMascara(celular, "celular");

        telefone = jFormattedTextFieldTelefone.getText();
        telefone = limpaMascara(telefone, "telefone");

        cep = jFormattedTextFieldCep.getText();
        cep = limpaMascara(cep, "cep");

        fornecedorModel.setBairro(jTextFieldBairro.getText());
        fornecedorModel.setCidade(jTextFieldCidade.getText());
        fornecedorModel.setEmail(jTextFieldEmail.getText());
        fornecedorModel.setNome(jTextFieldNome.getText());
        fornecedorModel.setRua(jTextFieldRua.getText());
        fornecedorModel.setNumero(jTextFieldNumero.getText());
        fornecedorModel.setUf(jComboBoxUF.getSelectedItem().toString());
        fornecedorModel.setCategoria(jComboBoxCategoria.getSelectedItem().toString());
        fornecedorModel.setTelefone(telefone);
        fornecedorModel.setCelular(celular);
        fornecedorModel.setCnpj(cnpj);
        fornecedorModel.setCep(cep);
        
        if (jButtonAdicionar.isEnabled()) {

            fornecedorModel.setDataCadastro(data);
            
            resposta = JOptionPane.showConfirmDialog(null, "Deseja efetuar o cadastro ?", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (resposta == 0) {
                try {

                    if ("".equals(fornecedorModel.getBairro()) || "".equals(fornecedorModel.getCep())
                            || "".equals(fornecedorModel.getCidade()) || "".equals(fornecedorModel.getCnpj())
                            || "".equals(fornecedorModel.getNome()) || "".equals(fornecedorModel.getNumero())
                            || "".equals(fornecedorModel.getUf())) {

                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatorios", "Alerta", JOptionPane.WARNING_MESSAGE);

                    } else {
                        
                        if (verifica == true) {
                            FornecedorModel fornecedores = new FornecedorModel();
                            //fornecedores = fornecedorControl.verificaCadastro(cnpj, "cnpj","cadastro");
                            String f = fornecedorControl.verificaCadastroString(cnpj, "cnpj", "cadastro");
                            if(f.equals("FonecedorDesativado")){
                                //VERIFICA SE O USUARIO DESEJA ATIVAR O FORNECEDOR DESATIVADO
                                int b = JOptionPane.showConfirmDialog(null, "Este cnpj já esta cadastrado no sistema, porem, está desativado, deseja reativa-lo?", "Não é possivel efetuar o cadastro!",
                                            JOptionPane.YES_NO_OPTION,
                                            JOptionPane.ERROR_MESSAGE);
                                    if (b == 0) {
                                        
                                        //ATIVA O CLIENTE
                                        fornecedores = fornecedorControl.pesquisar(cnpj, "cnpj");
                                        ativaFornecedor(fornecedores);
                                        atualizaTabelaSParametro();
                                        JOptionPane.showMessageDialog(null, "O fornecedor foi reativado! verifique a tabela de fornecedores");
                                        
                                        setaDialog(0, true, true, true, false, false, true, true, false, "Cadastro");
                                        ativaCampos(true);
                                        
                                    } else if (b == 1) {
                                        jFormattedTextFieldCnpj.requestFocus();
                                    }
                                
                                
                            }else{
                            
                                fornecedorModel.setStatus(true);
                                fornecedorControl.inserir(fornecedorModel);
                                atualizaTabelaSParametro();
                                limparCampos();

                                if(this.tela.equals("telaPrincipal")){
                                super.dispose();
                                }
                                setaDialog(0, true, true, true, false, false, true, true, false, "Cadastrar");
                                }
                                
                            } else {

                            JOptionPane.showMessageDialog(null, "Erro ao cadastrar, CNPJ invalido", "Erro", JOptionPane.ERROR_MESSAGE);

                        }
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JaExisteException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (ParseException ex) {
                    Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NaoEncontradoException ex) {
                    Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else if (jButtonAlterar.isEnabled()) {

            fornecedorModel.setId(this.idFornecedor);
            fornecedorModel.setDataCadastro(this.dataCadastro);
            resposta = JOptionPane.showConfirmDialog(null, "Deseja atualizar fornecedor?", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if (!this.cnpj.equals(cnpj)) {
               
                try {

                    fornecedorControl.verificaCadastro(cnpj, "cnpj","cadastro");
                    //CPF que ja não esta cadastrado recebe true
                    verificaCnpj = true;

                } catch (SQLException ex) {
                    Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JaExisteException ex) {

                    //CPF que ja esta cadastrado recebe false
                    verificaCnpj = false;
                    JOptionPane.showMessageDialog(null, ex.getMessage());

                }

            } else {
                verificaCnpj= true;
            }
          
            if (resposta == 0) {
                try {
                    // if(verifica == true){
                    if ("".equals(fornecedorModel.getBairro()) || "".equals(fornecedorModel.getCep())
                            || "".equals(fornecedorModel.getCidade()) || "".equals(fornecedorModel.getCnpj())
                            || "".equals(fornecedorModel.getNome()) || "".equals(fornecedorModel.getNumero())
                            || "".equals(fornecedorModel.getUf())) {

                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatorios", "Alerta", JOptionPane.WARNING_MESSAGE);

                    } else {
                        
                        if (verifica == true) {
                           if(verificaCnpj == true){
                            fornecedorModel.setStatus(true);   
                            fornecedorControl.alterar(fornecedorModel);
                            atualizaTabelaSParametro();
                            limparCampos();
                            if(this.tela.equals("telaPrincipal")){
                            super.dispose();
                            }
                            setaDialog(0, true, true, true, false, false, true, true, false, "Cadastrar");
                            
                           }
                        } else {

                            JOptionPane.showMessageDialog(null, "Erro ao alterar, CNPJ invalido", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }


    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarActionPerformed

        setaDialog(1, true, false, false, true, true, false, false, true, "Cadastrar");

    }//GEN-LAST:event_jButtonAdicionarActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed

        FornecedorModel fornecedorModel = new FornecedorModel();
        FornecedorDAO fornecedorControl = new FornecedorDAO();
        String uf, tipo, cep, cnpj, celular, telefone;
        int linha = -1;

        linha = jTableFornecedor.getSelectedRow();

        if (linha == -1) {

            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta", JOptionPane.WARNING_MESSAGE);

        } else {

            cnpj = (String) jTableFornecedor.getValueAt(linha, 1);
            cnpj = limpaMascara(cnpj, "cnpj");
            this.cnpj = cnpj;
            try {

                fornecedorModel = fornecedorControl.pesquisar(cnpj, "cnpj");

                //Altera Titula para ALTERAR e Desativa os Botoes
                setaDialog(1, false, false, true, true, true, false, false, true, "Alterar");

                jTextFieldBairro.setText(fornecedorModel.getBairro());
                jTextFieldCidade.setText(fornecedorModel.getCidade());
                jTextFieldEmail.setText(fornecedorModel.getEmail());
                jTextFieldNome.setText(fornecedorModel.getNome());
                jTextFieldNumero.setText(fornecedorModel.getNumero());
                jTextFieldRua.setText(fornecedorModel.getRua());

                cnpj = fornecedorModel.getCnpj();
                cnpj = limpaMascara(cnpj, "cnpj");

                telefone = fornecedorModel.getTelefone();
                telefone = limpaMascara(telefone, "telefone");

                celular = fornecedorModel.getCelular();
                celular = limpaMascara(celular, "celular");

                cep = fornecedorModel.getCep();
                cep = limpaMascara(cep, "cep");

                if (!cnpj.equals("")) {
                    jFormattedTextFieldCnpj.setText(fornecedorModel.getCnpj());
                }
                if (!telefone.equals("")) {

                    jFormattedTextFieldTelefone.setText(fornecedorModel.getTelefone());
                }
                if (!celular.equals("")) {
                    jFormattedTextFieldCelular.setText(fornecedorModel.getCelular());
                }
                jFormattedTextFieldCep.setText(cep);
                uf = fornecedorModel.getUf();
                jComboBoxUF.setSelectedItem(uf);
                String categoria = fornecedorModel.getCategoria();
                jComboBoxCategoria.setSelectedItem(categoria);
                this.dataCadastro = fornecedorModel.getDataCadastro();
                this.idFornecedor = fornecedorModel.getId();

            } catch (SQLException ex) {
                Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NaoEncontradoException ex) {
                Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        if(this.tela.equals("telaPrincipal")){
                            super.dispose();
                            }
        setaDialog(0, true, true, true, false, false, true, true, false, "Cadastro");
        limparCampos();
        ativaCampos(true);


    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jTextFieldNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeActionPerformed

    private void jFormattedTextFieldCnpjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCnpjActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextFieldCnpjActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        super.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    //FOCUSLOST CNPJ 
    private void jFormattedTextFieldCnpjFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCnpjFocusLost

        boolean verifica;
        VerificaCampos verificaCampo = new VerificaCampos();
        String verificaObj, cnpj;

        cnpj = jFormattedTextFieldCnpj.getText();

        //RETIRA A FORMATACAO 
        cnpj = limpaMascara(cnpj, "cnpj");

        //TELA ALTERAR FORNECEDOR
        if (jButtonAlterar.isEnabled()) {

            if ("".equals(cnpj)) {

                jFormattedTextFieldCnpj.setValue(null);

            } else {

                if (!this.cnpj.equals(jFormattedTextFieldCnpj.getText())) {

                    verifica = verificaCampo.verificaCNPJ(cnpj);

                    if (verifica == true) {
                       // jLabelCNPJ.setText("");

                    } else if (verifica == false) {

                        //jLabelCNPJ.setText("CNPJ Invalido!");
                        
                        
                       // jLabelCNPJ.setForeground(Color.red);

                    }
                }
            }

            //TELA CADASTRAR FORNECEDOR
        } else if (jButtonAdicionar.isEnabled()) {

            if ("".equals(cnpj)) {
                jFormattedTextFieldCnpj.setValue(null);
            } else {
                verifica = verificaCampo.verificaCNPJ(cnpj);

                if (verifica == true) {
                    //jLabelCNPJ.setText("");

                } else if (verifica == false) {

                   // jLabelCNPJ.setText("CNPJ Invalido!");
                    //jLabelCNPJ.setForeground(Color.red);
                }

            }

        }

    }//GEN-LAST:event_jFormattedTextFieldCnpjFocusLost

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed

        int index = jComboBoxTipoPesquisa.getSelectedIndex();

        String nome, cnpj;

        FornecedorDAO fornecedorControl = new FornecedorDAO();
        FornecedorModel fornecedor = new FornecedorModel();
        ArrayList<FornecedorModel> listaFornecedores = new ArrayList<>();

        switch (index) {

            case 0: {//NOME
                nome = jFormattedTextFieldPesquisar.getText();

                try {
                    listaFornecedores = fornecedorControl.pesquisarNomeEspecifico(nome);
                    atualizaTabelaFornecedores(listaFornecedores);
                } catch (ParseException ex) {
                    Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);

                } catch (NaoEncontradoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }

            break;

            case 1: //CNPJ
            {

                cnpj = jFormattedTextFieldPesquisar.getText();
                cnpj = limpaMascara(cnpj, "cnpj");
                try {
                    fornecedor = fornecedorControl.pesquisar(cnpj, "cnpj");
                    atualizaTabelaFornecedor(fornecedor);

                } catch (SQLException ex) {
                    Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NaoEncontradoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }

            break;

        }


    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jComboBoxTipoPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoPesquisaActionPerformed

        int index = jComboBoxTipoPesquisa.getSelectedIndex();

        //qtd de caracteres na pesquisa do nome
        MaskFormatter mascara = null;

 
        switch (index) {

            case 0: {

                try {
                    //MASCARCA DE CARACTERES STRING

                    setaCampoPesquisa();
                } catch (ParseException ex) {
                    Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            jLabelLegendaPesquisa.setText("Nome:");

            break;

            case 1: //CNPJ
            {
                try {
                    jFormattedTextFieldPesquisar.setValue(null);
                    mascara = new MaskFormatter("##.###.###/####-##");
                    jFormattedTextFieldPesquisar.setFormatterFactory(new DefaultFormatterFactory(mascara));
                } catch (ParseException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                }

                
            }

            jLabelLegendaPesquisa.setText("CNPJ:");

            break;

        }


    }//GEN-LAST:event_jComboBoxTipoPesquisaActionPerformed

    private void jFormattedTextFieldPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldPesquisarActionPerformed
        String verifica;

        verifica = jFormattedTextFieldPesquisar.getText();

        if (verifica == "") {

            try {
                atualizaTabelaSParametro();
            } catch (SQLException ex) {
                Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jFormattedTextFieldPesquisarActionPerformed

    private void jFormattedTextFieldPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldPesquisarKeyReleased

        int index = jComboBoxTipoPesquisa.getSelectedIndex();

        FornecedorDAO fornecedorControl = new FornecedorDAO();
        FornecedorModel fornecedorModel = new FornecedorModel();
        ArrayList<FornecedorModel> listaFornecedores;

        String nome, cnpj;

        switch (index) {

            case 0: {

                jLabelLegendaPesquisa.setText("Nome:");
                nome = jFormattedTextFieldPesquisar.getText().trim();

                if ("".equals(nome)) {

                    try {

                        listaFornecedores = fornecedorControl.findAll();
                        atualizaTabelaFornecedores(listaFornecedores);

                    } catch (SQLException ex) {
                        Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else if (!nome.equals("")) {

                    try {
                        listaFornecedores = fornecedorControl.pesquisarNome(nome);
                        atualizaTabelaFornecedores(listaFornecedores);

                    } catch (SQLException ex) {

                        Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);

                    } catch (ParseException ex) {

                        Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);

                    } catch (NaoEncontradoException ex) {
                        try {
                            atualizaTabelaSParametro();
                        } catch (SQLException ex1) {
                            Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (ParseException ex1) {
                            Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex1);
                        }

                    }

                }
            }

            break;

            case 1: //CNPJ
            {

                jLabelLegendaPesquisa.setText("CNPJ:");
                cnpj = jFormattedTextFieldPesquisar.getText();
                cnpj = limpaMascara(cnpj, "cnpj");

                if ("".equals(cnpj)) {

                    try {
                        listaFornecedores = fornecedorControl.findAll();
                        atualizaTabelaFornecedores(listaFornecedores);

                    } catch (SQLException ex) {

                    }

                } else if (!cnpj.equals("")) {

                    try {
                        fornecedorModel = fornecedorControl.pesquisar(cnpj, "cnpj");
                        atualizaTabelaFornecedor(fornecedorModel);
                    } catch (SQLException ex) {

                    } catch (NaoEncontradoException ex) {
                        try {
                            atualizaTabelaSParametro();
                        } catch (SQLException ex1) {
                            Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (ParseException ex1) {
                            Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }

                }

                break;

            }

        }
    }//GEN-LAST:event_jFormattedTextFieldPesquisarKeyReleased

    private void jButtonConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultarActionPerformed
        FornecedorModel fornecedorModel = new FornecedorModel();
        FornecedorDAO fornecedorControl = new FornecedorDAO();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        String uf, tipo, cep, cnpj, celular, telefone, dataString;
        int linha = -1;

        linha = jTableFornecedor.getSelectedRow();

        if (linha == -1) {

            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta", JOptionPane.WARNING_MESSAGE);

        } else {

            cnpj = (String) jTableFornecedor.getValueAt(linha, 1);
            cnpj = limpaMascara(cnpj, "cnpj");

            try {

                fornecedorModel = fornecedorControl.pesquisar(cnpj, "cnpj");

                setaDialog(1, false, false, false, false, true, true, false, true, "Consultar");
                jTextFieldDataCadastro.setVisible(true);
              //  jLabelDataCadastro.setVisible(true);
                jTextFieldBairro.setText(fornecedorModel.getBairro());
                jTextFieldCidade.setText(fornecedorModel.getCidade());
                jTextFieldEmail.setText(fornecedorModel.getEmail());
                jTextFieldNome.setText(fornecedorModel.getNome());
                jTextFieldNumero.setText(fornecedorModel.getNumero());
                jTextFieldRua.setText(fornecedorModel.getRua());
                dataString = formatador.format(fornecedorModel.getDataCadastro());

                jTextFieldDataCadastro.setText(dataString);
                cnpj = fornecedorModel.getCnpj();
                cnpj = limpaMascara(cnpj, "cnpj");

                telefone = fornecedorModel.getTelefone();
                telefone = limpaMascara(telefone, "telefone");

                celular = fornecedorModel.getCelular();
                celular = limpaMascara(celular, "celular");

                cep = fornecedorModel.getCep();
                cep = limpaMascara(cep, "cep");

                if (!cnpj.equals("")) {
                    jFormattedTextFieldCnpj.setText(fornecedorModel.getCnpj());
                }
                if (!telefone.equals("")) {

                    jFormattedTextFieldTelefone.setText(fornecedorModel.getTelefone());
                }
                if (!celular.equals("")) {
                    jFormattedTextFieldCelular.setText(fornecedorModel.getCelular());
                }
                jFormattedTextFieldCep.setText(cep);
                uf = fornecedorModel.getUf();
                jComboBoxUF.setSelectedItem(uf);

                this.dataCadastro = fornecedorModel.getDataCadastro();
                this.idFornecedor = fornecedorModel.getId();

                ativaCampos(false);

            } catch (SQLException ex) {
                Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NaoEncontradoException ex) {
                Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_jButtonConsultarActionPerformed

    private void jFormattedTextFieldCelularFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCelularFocusLost
        String celular;
        celular = jFormattedTextFieldCelular.getText();

        celular = limpaMascara(celular, "celular");
        if ("".equals(celular)) {
            jFormattedTextFieldCelular.setValue(null);
        }
    }//GEN-LAST:event_jFormattedTextFieldCelularFocusLost

    private void jFormattedTextFieldTelefoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldTelefoneFocusLost
        String telefone;
        telefone = jFormattedTextFieldTelefone.getText();

        telefone = limpaMascara(telefone, "telefone");
        if ("".equals(telefone)) {
            jFormattedTextFieldTelefone.setValue(null);
        }
    }//GEN-LAST:event_jFormattedTextFieldTelefoneFocusLost

    private void jFormattedTextFieldCepFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCepFocusLost
        String cep;
        cep = jFormattedTextFieldCep.getText();

        cep = limpaMascara(cep, "cep");
        if ("".equals(cep)) {
            jFormattedTextFieldCep.setValue(null);
        }
    }//GEN-LAST:event_jFormattedTextFieldCepFocusLost

    private void jFormattedTextFieldCnpjFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCnpjFocusGained
        //jLabelCNPJ.setText("");
    }//GEN-LAST:event_jFormattedTextFieldCnpjFocusGained

    private void jTextFieldNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNomeKeyTyped
        int qtdCaracteres = 100;
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            evt.consume();
        }

        if (jTextFieldNome.getText().length() >= qtdCaracteres) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldNomeKeyTyped

    private void jTextFieldRuaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldRuaKeyTyped
        int qtdCaracteres = 70;
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            evt.consume();
        }

        if (jTextFieldRua.getText().length() >= qtdCaracteres) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldRuaKeyTyped

    private void jTextFieldBairroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBairroKeyTyped
        int qtdCaracteres = 70;
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            evt.consume();
        }

        if (jTextFieldBairro.getText().length() >= qtdCaracteres) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldBairroKeyTyped

    private void jTextFieldCidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCidadeKeyTyped
        int qtdCaracteres = 70;
        char c = evt.getKeyChar();

        if (Character.isDigit(c)) {
            evt.consume();
        }

        if (jTextFieldCidade.getText().length() >= qtdCaracteres) {
            evt.consume();
        }

    }//GEN-LAST:event_jTextFieldCidadeKeyTyped

    private void jTextFieldNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNumeroKeyTyped
        int qtdCaracteres = 10;
        char c = evt.getKeyChar();

        if (!Character.isDigit(c)) {
            evt.consume();
        }

        if (jTextFieldNumero.getText().length() >= qtdCaracteres) {
            evt.consume();
        }

    }//GEN-LAST:event_jTextFieldNumeroKeyTyped

    private void jFormattedTextFieldPesquisarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldPesquisarKeyTyped
        int qtdCaracteres = 100;

        if (jFormattedTextFieldPesquisar.getText().length() >= qtdCaracteres) {
            evt.consume();
        }
    }//GEN-LAST:event_jFormattedTextFieldPesquisarKeyTyped

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        try {
            jButtonAlterar.setEnabled(true);
            jButtonAdicionar.setEnabled(true);
            jButtonExcluir.setEnabled(true);
            jButtonStatus.setEnabled(false);
            
            atualizaTabelaSParametro();
        } catch (SQLException ex) {
            Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        try {
            
            jButtonAlterar.setEnabled(false);
            jButtonAdicionar.setEnabled(false);
            jButtonCancelar.setEnabled(false);
            jButtonExcluir.setEnabled(false);
            jButtonSalvar.setEnabled(false);
            jButtonStatus.setEnabled(true);
            
            atualizaTabelaSParametro();
        } catch (SQLException ex) {
            Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButtonStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStatusActionPerformed
       int resposta, id;

        resposta = JOptionPane.showConfirmDialog(null, "Deseja ativar o Fornecedor ?", "Confirma",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (resposta == 0) {

            int linha = -1;
            ArrayList<FornecedorModel> listaFornecedores;
            FornecedorDAO fornecedorDao = new FornecedorDAO();
            FornecedorModel fornecedor = new FornecedorModel();
            linha = jTableFornecedor.getSelectedRow();

            if (linha == -1) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta",JOptionPane.WARNING_MESSAGE);

            } else {
                
                try {
                    
                    String cnpj = (String) jTableFornecedor.getValueAt(linha, 1);
                    cnpj= limpaMascara(cnpj, "cnpj");
                    fornecedor = fornecedorDao.pesquisar(cnpj, "cnpj");
                    fornecedor.setStatus(true);
                    fornecedorDao.alterar(fornecedor);
                    atualizaTabelaSParametro();
                    
                } catch (SQLException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NaoEncontradoException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                }
              
            }
        }
        
    }//GEN-LAST:event_jButtonStatusActionPerformed

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
            java.util.logging.Logger.getLogger(ManterFornecedorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManterFornecedorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManterFornecedorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManterFornecedorView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ManterFornecedorView dialog = null;
                try {
                    dialog = new ManterFornecedorView(new javax.swing.JFrame(), true, "");
                } catch (SQLException ex) {
                    Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonConsultar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonStatus;
    private javax.swing.JComboBox<String> jComboBoxCategoria;
    private javax.swing.JComboBox<String> jComboBoxTipoPesquisa;
    private javax.swing.JComboBox<String> jComboBoxUF;
    private javax.swing.JFormattedTextField jFormattedTextFieldCelular;
    private javax.swing.JFormattedTextField jFormattedTextFieldCep;
    private javax.swing.JFormattedTextField jFormattedTextFieldCnpj;
    private javax.swing.JFormattedTextField jFormattedTextFieldPesquisar;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelefone;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelLegendaPesquisa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelCEP;
    private javax.swing.JPanel jPanelCNPJ;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableFornecedor;
    private javax.swing.JTextField jTextFieldBairro;
    private javax.swing.JTextField jTextFieldCidade;
    private javax.swing.JTextField jTextFieldDataCadastro;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldNumero;
    private javax.swing.JTextField jTextFieldRua;
    // End of variables declaration//GEN-END:variables
}
