package Visao;

import Control.Exceptions.JaExisteException;
import Control.Exceptions.NaoEncontradoException;
import DAO.FornecedorDAO;
import DAO.FuncionarioDAO;
import Modelo.FornecedorModel;
import Modelo.FuncionarioModel;
import java.awt.Color;
import java.awt.Toolkit;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class ManterFuncionarioView extends javax.swing.JDialog {

    int idFuncionario;
    Date dataCadastro;
    String cpf, login;
    boolean verificaLogin;
    String tela;
    FuncionarioModel funcionarioAlterar = new FuncionarioModel();
    public ManterFuncionarioView(java.awt.Frame parent, boolean modal, String tela) throws SQLException, ParseException {
        super(parent, modal);
        initComponents();
        limparCampos();
        setaCampoPesquisa();
        this.tela = tela;
        jRadioButton1.setSelected(true);
        jTabbedPane1.setEnabledAt(1, false);
        jButtonCancelar.setEnabled(false);
        jButtonSalvar.setEnabled(false);

        // Incluir tabela de funcionario 
        FuncionarioDAO funcionarioControl = new FuncionarioDAO();
        ArrayList<FuncionarioModel> listaDeFuncionarios = funcionarioControl.findAll();
        
        
        if(this.tela.equals("telaPrincipal")){
            
        limparCampos();
        setaDialog(1, true, false, false, true, true, false, false, true, "Cadastrar");
            
        }else{
            
            setaDialog(0, true, true, true, false, false, true, true, false, "Cadastro");
            ativaCampos(true);
            
        }
        
        atualizaTabelaFuncionarios(listaDeFuncionarios);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel14 = new javax.swing.JLabel();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanelPrincipalFuncionario = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFuncionario = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jButtonPesquisar = new javax.swing.JButton();
        jComboBoxTipoPesquisa = new javax.swing.JComboBox<>();
        jFormattedTextFieldPesquisar = new javax.swing.JFormattedTextField();
        jLabelLegendaPesquisa = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel11 = new javax.swing.JPanel();
        jButtonStatus = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTextFieldRua = new javax.swing.JTextField();
        jTextFieldbairro = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldCidade = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jFormattedTextFieldCep = new javax.swing.JFormattedTextField();
        jTextFieldNumero = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jComboBoxUF = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jFormattedTextFieldTelefone = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextFieldCelular = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jFormattedTextFieldCpf = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldNome = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBoxCargo = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldSenha = new javax.swing.JTextField();
        jTextFieldLogin = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabelAlertaLogin = new javax.swing.JLabel();
        jLabelCPF = new javax.swing.JLabel();
        data = new javax.swing.JTextField();
        jLabelDataCadastro = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButtonExcluir = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jButtonAdicionar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButtonConsultar = new javax.swing.JButton();

        jLabel14.setText("jLabel14");

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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Funcionário");

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tabela de Funcionários", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTableFuncionario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Cpf", "Telefone", "Celular", "Cargo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableFuncionario);
        if (jTableFuncionario.getColumnModel().getColumnCount() > 0) {
            jTableFuncionario.getColumnModel().getColumn(0).setResizable(false);
            jTableFuncionario.getColumnModel().getColumn(1).setResizable(false);
            jTableFuncionario.getColumnModel().getColumn(2).setResizable(false);
            jTableFuncionario.getColumnModel().getColumn(3).setResizable(false);
            jTableFuncionario.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Pesquisar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jButtonPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lupa.png"))); // NOI18N
        jButtonPesquisar.setText("Pesquisar");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

        jComboBoxTipoPesquisa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "CPF" }));
        jComboBoxTipoPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoPesquisaActionPerformed(evt);
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

        jLabel16.setText("Tipo:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxTipoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 253, Short.MAX_VALUE)
                        .addComponent(jLabelLegendaPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jFormattedTextFieldPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPesquisar))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(jLabelLegendaPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPesquisar)
                    .addComponent(jComboBoxTipoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addGap(12, 12, 12))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Ativar Funcionário", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

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
                .addComponent(jButtonStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Principal", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Endereço", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldRua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldRuaKeyTyped(evt);
            }
        });

        jTextFieldbairro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldbairroKeyTyped(evt);
            }
        });

        jLabel5.setText("Rua:");

        jLabel6.setText("Bairro:");

        jLabel7.setText("Cidade:");

        jTextFieldCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCidadeKeyTyped(evt);
            }
        });

        jLabel10.setText("UF:");

        jLabel11.setText("Cep:");

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

        jTextFieldNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNumeroKeyTyped(evt);
            }
        });

        jLabel12.setText("Numero:");

        jComboBoxUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, 478, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jFormattedTextFieldCep, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextFieldbairro)
                            .addComponent(jTextFieldCidade))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldbairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jFormattedTextFieldCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(104, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Funcionário", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

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

        jLabel3.setText("Telefone:");

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

        jLabel4.setText("Celular:");

        try {
            jFormattedTextFieldCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldCpf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldCpfFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldCpfFocusLost(evt);
            }
        });
        jFormattedTextFieldCpf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jFormattedTextFieldCpfMouseExited(evt);
            }
        });
        jFormattedTextFieldCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldCpfActionPerformed(evt);
            }
        });

        jLabel2.setText("Cpf:");

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

        jLabel1.setText("Nome:");

        jLabel8.setText("Cargo:");

        jComboBoxCargo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Funcionário", "Gerente" }));
        jComboBoxCargo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCargoActionPerformed(evt);
            }
        });

        jLabel9.setText("Senha:");

        jTextFieldSenha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldSenhaKeyTyped(evt);
            }
        });

        jTextFieldLogin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldLoginFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldLoginFocusLost(evt);
            }
        });
        jTextFieldLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jTextFieldLoginMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldLoginMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldLoginMouseReleased(evt);
            }
        });
        jTextFieldLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLoginActionPerformed(evt);
            }
        });
        jTextFieldLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldLoginKeyTyped(evt);
            }
        });

        jLabel15.setText("Usuário/Login:");

        jLabelDataCadastro.setText("Data de Cadastro:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelAlertaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jFormattedTextFieldCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jFormattedTextFieldTelefone))
                                    .addComponent(jTextFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabelDataCadastro)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(45, 45, 45))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelCPF, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelAlertaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel15))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(data, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDataCadastro)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Cadastro", jPanel2);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelPrincipalFuncionarioLayout = new javax.swing.GroupLayout(jPanelPrincipalFuncionario);
        jPanelPrincipalFuncionario.setLayout(jPanelPrincipalFuncionarioLayout);
        jPanelPrincipalFuncionarioLayout.setHorizontalGroup(
            jPanelPrincipalFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalFuncionarioLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelPrincipalFuncionarioLayout.setVerticalGroup(
            jPanelPrincipalFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalFuncionarioLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(3, 133, 188));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Controle de Funcionários");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(182, 182, 182))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/excluir24x24.png"))); // NOI18N
        jButtonExcluir.setText("Excluir ");
        jButtonExcluir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonExcluir.setMargin(new java.awt.Insets(2, 11, 2, 11));
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/voltar24x24.png"))); // NOI18N
        jButtonCancelar.setText(" Cancelar");
        jButtonCancelar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/salvar24x24.png"))); // NOI18N
        jButtonSalvar.setText("Salvar      ");
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
        jButtonAlterar.setText("Editar       ");
        jButtonAlterar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/VoltarSimples24x24.png"))); // NOI18N
        jButton7.setText("Fechar");
        jButton7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
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
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButtonConsultar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonAdicionar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelPrincipalFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelPrincipalFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void ativaFuncionario(FuncionarioModel funcionario){
        
        try {
            FuncionarioDAO funcionarioDao = new FuncionarioDAO();
            funcionario.setStatus(true);
            funcionarioDao.alterar(funcionario);
        } catch (SQLException ex) {
            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // Se todos os funcioanrio forem excluidos um funcionarioADM é criado
    String limpaMascara(String mascara, String tipo) {

        switch (tipo) {

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

    String insereMascara(String mascara, String tipo) throws ParseException {

        switch (tipo) {
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
            case "cep": {
                MaskFormatter mf = new MaskFormatter("#####-###");
                mf.setValueContainsLiteralCharacters(false);
                return mf.valueToString(mascara);
            }
            default:
                break;
        }

        return mascara;
    }

    public void limparCampos() {

        jTextFieldCidade.setText("");
        jTextFieldNome.setText("");
        jTextFieldNumero.setText("");

        jTextFieldRua.setText("");
        jComboBoxUF.setSelectedIndex(0);
        jTextFieldSenha.setText("");
        jTextFieldbairro.setText("");
        jTextFieldLogin.setText("");
        jFormattedTextFieldCelular.setText("");
        jFormattedTextFieldCep.setText("");
        jFormattedTextFieldCpf.setText("");
        jFormattedTextFieldTelefone.setText("");
        jLabelAlertaLogin.setText("");
        data.setVisible(false);
        data.setEditable(false);
        jLabelDataCadastro.setVisible(false);
    }

    public void setaDialog(int telaAtual, boolean cadastro, boolean excluir,
            boolean alterar, boolean salvar, boolean cancelar, boolean consultar, boolean trocaTelaAtual,
            boolean trocaTelaAnterior, String legenda) {

        jTabbedPane1.setSelectedIndex(telaAtual);  //PAINEL 
        jButtonAdicionar.setEnabled(cadastro); //BOTÃO CADASTRAR
        jButtonExcluir.setEnabled(excluir); // BOTÃO EXCLUIR
        jButtonAlterar.setEnabled(alterar); //BOTÃO ALTERAR
        jButtonSalvar.setEnabled(salvar); //BOTÃO SALVAR
        jButtonCancelar.setEnabled(cancelar); //BOTÃO CANCELAR
        jButtonConsultar.setEnabled(consultar);//BOTÃO CONSULTAR
        jTabbedPane1.setEnabledAt(0, trocaTelaAtual); //TELA ATUAL
        jTabbedPane1.setEnabledAt(1, trocaTelaAnterior); //TELA ANTERIOR 
        jTabbedPane1.setTitleAt(1, legenda); //LEGENDA DO 

    }

    public void ativaCampos(boolean status) {

        jComboBoxCargo.setEnabled(status);
        jComboBoxUF.setEnabled(status);
        jTextFieldbairro.setEditable(status);
        jFormattedTextFieldCep.setEditable(status);
        jTextFieldCidade.setEditable(status);
        jTextFieldNome.setEditable(status);
        jTextFieldNumero.setEditable(status);
        jTextFieldRua.setEditable(status);
        jTextFieldLogin.setEditable(status);
        jTextFieldSenha.setEditable(status);
        jFormattedTextFieldCelular.setEditable(status);
        jFormattedTextFieldTelefone.setEditable(status);
        jFormattedTextFieldCpf.setEditable(status);

    }

    private void limpaCamposFormatados() {

        jFormattedTextFieldCelular.setValue(null);
        jFormattedTextFieldCep.setValue(null);
        jFormattedTextFieldCpf.setValue(null);
        jFormattedTextFieldTelefone.setValue(null);
        jLabelAlertaLogin.setText("");
        jLabelCPF.setText("");

    }

    public void atualizaTabelaSParametro() throws SQLException, ParseException {
        FuncionarioDAO funcionarioControl = new FuncionarioDAO();
        ArrayList<FuncionarioModel> listaDeFuncionarios = funcionarioControl.findAll();
        atualizaTabelaFuncionarios(listaDeFuncionarios);
    }

    public void atualizaTabelaFuncionarios(ArrayList<FuncionarioModel> listaFuncionarios) {

        DefaultTableModel val = (DefaultTableModel) jTableFuncionario.getModel();
        val.setNumRows(0); // excluir os registros que estão na JTable
        //listaDeClientes = listaClientes;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (jTableFuncionario != null) {

            listaFuncionarios.forEach((funcionario) -> {
                Date data = funcionario.getDataCadastro();
                String cpf, cep, telefone, celular;
                String dataString = dateFormat.format(data);
               // if(funcionario.getStatus() == true){
                try {

                    cpf = insereMascara(funcionario.getCpf(), "cpf");
                    cep = insereMascara(funcionario.getCep(), "cep");
                    telefone = insereMascara(funcionario.getTelefone(), "telefone");
                    celular = insereMascara(funcionario.getCelular(), "celular");

                    funcionario.setCpf(cpf);
                    funcionario.setCep(cep);
                    funcionario.setTelefone(telefone);
                    funcionario.setCelular(celular);
                    
                    //ATIVADOS
                    if(jRadioButton1.isSelected() && funcionario.getStatus()==true){
                        
                    val.addRow(new Object[]{funcionario.getNome(), funcionario.getCpf(),
                    funcionario.getTelefone(), funcionario.getCelular(), funcionario.getCargo()});
                        
                    }
                    else if(jRadioButton2.isSelected() && funcionario.getStatus()==false){

                    val.addRow(new Object[]{funcionario.getNome(), funcionario.getCpf(),
                    funcionario.getTelefone(), funcionario.getCelular(), funcionario.getCargo()});
                    

                    }
                    
                    
                } catch (ParseException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
                //}
            });
            
        }
    }

    public void atualizaTabelaFuncionario(FuncionarioModel funcionario) {

        DefaultTableModel val = (DefaultTableModel) jTableFuncionario.getModel();
        val.setNumRows(0); // excluir os registros que estão na JTable
        //listaDeClientes = listaClientes;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date data = funcionario.getDataCadastro();
        String dataString = dateFormat.format(data);

        if (jTableFuncionario != null) {
            if(funcionario.getStatus() == true){
            String cpf, cep, telefone, celular;
            try {

                cpf = insereMascara(funcionario.getCpf(), "cpf");
                cep = insereMascara(funcionario.getCep(), "cep");
                telefone = insereMascara(funcionario.getTelefone(), "telefone");
                celular = insereMascara(funcionario.getCelular(), "celular");

                funcionario.setCpf(cpf);
                funcionario.setCep(cep);
                funcionario.setTelefone(telefone);
                funcionario.setCelular(celular);

            } catch (ParseException ex) {
                Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
            }
            val.addRow(new Object[]{funcionario.getNome(), funcionario.getCpf(), funcionario.getTelefone(),
                funcionario.getCelular(), funcionario.getCargo()});
            }
        }
    }
    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarActionPerformed
        limparCampos();
        setaDialog(1, true, false, false, true, true, false, false, true, "Cadastrar");

    }//GEN-LAST:event_jButtonAdicionarActionPerformed

    public void setaCampoPesquisa() throws ParseException {

        jLabelLegendaPesquisa.setText("Nome:");
        jFormattedTextFieldPesquisar.setValue(null);
        jFormattedTextFieldPesquisar.setFormatterFactory(null);
        jFormattedTextFieldPesquisar.setText("");

    }

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed

        int resposta, id;

        resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir o funcionário ?", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (resposta == 0) {

            int linha = -1;

            ArrayList<FuncionarioModel> listaFuncionarios;
            FuncionarioDAO funcionarioControl = new FuncionarioDAO();
            FuncionarioModel funcionario = new FuncionarioModel();
            FuncionarioModel horaLogin = new FuncionarioModel();
            linha = jTableFuncionario.getSelectedRow();

            if (linha == -1) {

                JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta",JOptionPane.WARNING_MESSAGE);

            } else {

                String cpf = (String) jTableFuncionario.getValueAt(linha, 1);
                cpf = limpaMascara(cpf, "cpf");

                try {

                    funcionario = funcionarioControl.pesquisar(cpf, "cpf");

                    horaLogin = funcionarioControl.pesquisarDataHoralogin();

                    if (!horaLogin.getCpf().equals(funcionario.getCpf())) {
                        funcionario.setStatus(false);
                        String msg = funcionarioControl.excluir(funcionario);
                        JOptionPane.showMessageDialog(null, msg);

                        listaFuncionarios = funcionarioControl.findAll();
                        atualizaTabelaFuncionarios(listaFuncionarios);

                    } else if (horaLogin.getCpf().equals(funcionario.getCpf())) {

                        JOptionPane.showMessageDialog(null, "não é possivel excluir o usuario logado", "Erro", JOptionPane.ERROR_MESSAGE);

                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NaoEncontradoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }

        }        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jFormattedTextFieldCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCpfActionPerformed

    }//GEN-LAST:event_jFormattedTextFieldCpfActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed

        String cpf, cep, celular, telefone;
        int resposta;
        boolean verificaCpf = false, verificaCampoCpf = false, verificaLogin = false;
        
        VerificaCampos verificaCampo = new VerificaCampos();

        Date data = new Date(System.currentTimeMillis());
        
         Timestamp dataTime = new Timestamp(System.currentTimeMillis());
            
            dataTime.toLocalDateTime();
        
        String cargo = (String) jComboBoxCargo.getSelectedItem();
        
        if("Gerente".equals(cargo)){
            cargo = "gerente";
        }else{
            cargo = "funcionario";
        }
        
        ArrayList<FuncionarioModel> listaDeFuncionarios = new ArrayList<>();
        FuncionarioModel funcionarioModel = new FuncionarioModel();
        FuncionarioDAO funcionarioControl = new FuncionarioDAO();

        cpf = jFormattedTextFieldCpf.getText();
        cpf = limpaMascara(cpf, "cpf");

        celular = jFormattedTextFieldCelular.getText();
        celular = limpaMascara(celular, "celular");

        telefone = jFormattedTextFieldTelefone.getText();
        telefone = limpaMascara(telefone, "telefone");

        cep = jFormattedTextFieldCep.getText();
        cep = limpaMascara(cep, "cep");

        funcionarioModel.setNome(jTextFieldNome.getText());
        funcionarioModel.setRua(jTextFieldRua.getText());
        funcionarioModel.setNumero(jTextFieldNumero.getText());
        funcionarioModel.setLogin(jTextFieldLogin.getText());
        funcionarioModel.setSenha(jTextFieldSenha.getText());
        funcionarioModel.setBairro(jTextFieldbairro.getText());
        funcionarioModel.setUf(jComboBoxUF.getSelectedItem().toString());
        funcionarioModel.setCidade(jTextFieldCidade.getText());
        funcionarioModel.setHoraDataLogin(null);
        
        if(this.funcionarioAlterar.getHoraDataLogin() == null){
            funcionarioModel.setHoraDataLogin(null);
        }
        
        funcionarioModel.setCargo(cargo);
        funcionarioModel.setTelefone(telefone);
        funcionarioModel.setCelular(celular);
        funcionarioModel.setCpf(cpf);
        funcionarioModel.setCep(cep);

        verificaCampoCpf = verificaCampo.verificaCpf(cpf);

        // OP ADICIONAR     
        if (jButtonAdicionar.isEnabled()) {

            funcionarioModel.setDataCadastro(data);
            resposta = JOptionPane.showConfirmDialog(null, "Deseja efetuar o cadastro ?", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (resposta == 0) {
                if ("".equals(funcionarioModel.getBairro()) || "".equals(funcionarioModel.getBairro())
                        || "".equals(funcionarioModel.getCep()) || "".equals(funcionarioModel.getCidade())
                        || "".equals(funcionarioModel.getCpf()) || "".equals(funcionarioModel.getLogin())
                        || "".equals(funcionarioModel.getNome()) || "".equals(funcionarioModel.getNumero())
                        || "".equals(funcionarioModel.getRua()) || "".equals(funcionarioModel.getSenha())
                        || "".equals(funcionarioModel.getUf())) {

                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatorios", "Alerta", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {

                        if (verificaCampoCpf == true) {
                            FuncionarioModel funcionario = new FuncionarioModel();
                            //funcionario = funcionarioControl.verificaCadastro(cpf, "cpf","cadastro");
                            String f = funcionarioControl.verificaCadastroString(cpf, "cpf","cadastro");
                            if(f.equals("FuncionarioDesativado")){
                                
                                int b = JOptionPane.showConfirmDialog(null, "Este cpf já esta cadastrado no sistema, porem, está desativado, deseja reativa-lo?", "Não é possivel efetuar o cadastro!",
                                            JOptionPane.YES_NO_OPTION,
                                            JOptionPane.ERROR_MESSAGE);
                                    if (b == 0) {
                                        
                                        //ATIVA O FUNCIONARIo
                                        funcionario = funcionarioControl.pesquisar(cpf, "cpf");
                                        ativaFuncionario(funcionario);
                                        JOptionPane.showMessageDialog(null, "O funcionario foi reativado! verifique a tabela de funcionarios");
                                        atualizaTabelaSParametro();
                                        setaDialog(0, true, true, true, false, false, true, true, false, "Cadastrar");
                                        ativaCampos(true);
                                        
                                    } else if (b == 1) {
                                        jFormattedTextFieldCpf.requestFocus();
                                    }
                                
                            }else{
                            
                                funcionarioControl.verificaLogin(jTextFieldLogin.getText());
                                funcionarioModel.setStatus(true);
                                funcionarioControl.inserir(funcionarioModel);
                                atualizaTabelaSParametro();

                                limparCampos();
                                if(this.tela.equals("telaPrincipal")){
                                super.dispose();
                                }
                                setaDialog(0, true, true, true, false, false, true, true, false, "Cadastrar");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o cadastro, informe o CPF valido", "Erro", JOptionPane.ERROR_MESSAGE);
                            jFormattedTextFieldCpf.requestFocus();
                        }

                    } catch (SQLException ex) {
                        Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex);

                    } catch (ParseException ex) {
                        Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex);

                    } catch (JaExisteException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    } catch (NaoEncontradoException ex) {
                        Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

            //OP ALTERAR  
        } else if (jButtonAlterar.isEnabled()) {

            /// VERIFICA SE JA EXISTE UM CPF DIFERENTE DO CPF ATUAL
            if (!this.cpf.equals(cpf)) {

                try {

                    funcionarioControl.verificaCadastro(cpf, "cpf","cadastro");
                    //CPF que ja não esta cadastrado recebe true
                    verificaCpf = true;

                } catch (SQLException ex) {
                    Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JaExisteException ex) {

                    //CPF que ja esta cadastrado recebe false
                    verificaCpf = false;
                    JOptionPane.showMessageDialog(null, ex.getMessage());

                }

            } else {
                verificaCpf = true;
            }

            //VERIFICA LOGIN
            if (!this.login.equals(jTextFieldLogin.getText())) {

                try {

                    funcionarioControl.verificaLogin(jTextFieldLogin.getText());
                    verificaLogin = true;

                } catch (SQLException ex) {
                    Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JaExisteException ex) {

                    verificaLogin = false;

                }
            } else {
                verificaLogin = true;
            }

            if (verificaCpf == true) {

                funcionarioModel.setId(this.idFuncionario);
                funcionarioModel.setDataCadastro(this.dataCadastro);
                funcionarioModel.setDataCadastro(data);
                
                resposta = JOptionPane.showConfirmDialog(null, "Deseja alterar funcionario ?", "Confirmar",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

                if (resposta == 0) {

                    if ("".equals(funcionarioModel.getBairro()) || "".equals(funcionarioModel.getBairro())
                            || "".equals(funcionarioModel.getCep()) || "".equals(funcionarioModel.getCidade())
                            || "".equals(funcionarioModel.getCpf()) || "".equals(funcionarioModel.getLogin())
                            || "".equals(funcionarioModel.getNome()) || "".equals(funcionarioModel.getNumero())
                            || "".equals(funcionarioModel.getRua()) || "".equals(funcionarioModel.getSenha())
                            || "".equals(funcionarioModel.getUf())) {

                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatorios", "Alerta", JOptionPane.WARNING_MESSAGE);
                    } else {
                        try {
                            //VERIFICA CPF VALIDO
                            if (verificaCampoCpf == true) {
                                //VERIFICA SE HÁ CADASTRO COM O CPF FALSE quer dizer que não há cpf cadastrado
                                if (verificaCpf == true) {

                                    if (verificaLogin == true) {
                                        funcionarioModel.setStatus(true);
                                        
                                        funcionarioControl.alterar(funcionarioModel);
                                            
                                        atualizaTabelaSParametro();
                                         limparCampos();
                                        if(this.tela.equals("telaPrincipal")){
                                            super.dispose();
                                        }
                                        setaDialog(0, true, true, true, false, false, true, true, false, "Cadastrar");
                                       
                                        
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Não foi possivel efetuar a alteração, Este nome de Login ja existe", "Erro", JOptionPane.ERROR_MESSAGE);
                                        jTextFieldLogin.requestFocus();
                                    }

                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o cadastro, informe o CPF valido", "Erro", JOptionPane.ERROR_MESSAGE);
                                jFormattedTextFieldCpf.requestFocus();
                            }

                        } catch (SQLException ex) {
                            Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex);

                        } catch (ParseException ex) {
                            Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                }

            }

        }

    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jTextFieldNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNomeActionPerformed

    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed
      
        String uf, tipo, celular, telefone, cargo;
        FuncionarioDAO funcionarioControl = new FuncionarioDAO();
        FuncionarioModel funcionarioModel = new FuncionarioModel();

        int id, linha = -1;

        linha = jTableFuncionario.getSelectedRow();

        if (linha == -1) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta", JOptionPane.WARNING_MESSAGE);

        } else {

            String cpf = (String) jTableFuncionario.getValueAt(linha, 1);

            cpf = limpaMascara(cpf, "cpf");
            this.cpf = cpf;

            try {
                funcionarioModel = funcionarioControl.pesquisar(cpf, "cpf");
                this.funcionarioAlterar = funcionarioModel;
                this.login = funcionarioModel.getLogin();

                //Altera Titulo para ALTERAR
                
                setaDialog(1, false, false, true, true, true, false, false, true, "Alterar");

                cargo = funcionarioModel.getCargo();
                jComboBoxCargo.setSelectedItem(cargo);

                jTextFieldbairro.setText(funcionarioModel.getBairro());

                //jFormattedTextFieldCelular.setText(funcionarioModel.getCelular());
                jFormattedTextFieldCep.setText(funcionarioModel.getCep());
                jTextFieldCidade.setText(funcionarioModel.getCidade());
                // jFormattedTextFieldCpf.setText(funcionarioModel.getCpf());
                jTextFieldNome.setText(funcionarioModel.getNome());

                jTextFieldNumero.setText(funcionarioModel.getNumero());
                jTextFieldRua.setText(funcionarioModel.getRua());
                jTextFieldLogin.setText(funcionarioModel.getLogin());
                jTextFieldSenha.setText(funcionarioModel.getSenha());

                cpf = funcionarioModel.getCpf();
                cpf = limpaMascara(cpf, "cpf");

                telefone = funcionarioModel.getTelefone();
                if(telefone!=null){
                telefone = limpaMascara(telefone, "telefone");
                }
                 
                celular = funcionarioModel.getCelular();
                if(telefone!=null){
                celular = limpaMascara(celular, "celular");
                }
                this.login = funcionarioModel.getLogin();

                if (cpf !=null) {
                    jFormattedTextFieldCpf.setText(funcionarioModel.getCpf());
                }
                if (telefone!=null) {

                    jFormattedTextFieldTelefone.setText(funcionarioModel.getTelefone());
                }
                if (celular != null) {
                    jFormattedTextFieldCelular.setText(funcionarioModel.getCelular());
                }

                uf = funcionarioModel.getUf();
                jComboBoxCargo.setSelectedItem(uf);

                this.dataCadastro = funcionarioModel.getDataCadastro();
                this.idFuncionario = funcionarioModel.getId();

            } catch (SQLException ex) {
                Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NaoEncontradoException ex) {
                Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButtonAlterarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        if(this.tela.equals("telaPrincipal")){
                            super.dispose();
                            }
        setaDialog(0, true, true, true, false, false, true, true, false, "Cadastro");
        limpaCamposFormatados();
        limparCampos();
        ativaCampos(true);

    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jComboBoxCargoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCargoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxCargoActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        super.dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTextFieldLoginMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldLoginMouseExited

    }//GEN-LAST:event_jTextFieldLoginMouseExited

    private void jTextFieldLoginMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldLoginMouseReleased

    }//GEN-LAST:event_jTextFieldLoginMouseReleased

    private void jTextFieldLoginMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldLoginMousePressed
        jLabelAlertaLogin.setText("");
    }//GEN-LAST:event_jTextFieldLoginMousePressed

    // Verifica se Login de Funcionario ja esta Cadastrado
    private void jTextFieldLoginFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldLoginFocusLost

        Boolean verifica = false;

        FuncionarioDAO funcionarioControl = new FuncionarioDAO();

        //SE O BOTÃO ALTERAR FOR PRECIONADO É FEIRO ESSE PROCESSO    
        if (jButtonAlterar.isEnabled()) {

            //Verifica se o jTextFieldLogin esta VAZIO 
            if (!"".equals(jTextFieldLogin.getText())) {

                //Verifica se o jTextFieldLogin é igual ao Objeto a ser Atualizado
                // O objeto esta global
                // só completa a busca se o objeto for diferente
                if (!this.login.equals(jTextFieldLogin.getText())) {

                    try {
                        // se voltar verdadeiro ja existe um login com esse nome
                        verifica = funcionarioControl.pesquisaLogin(jTextFieldLogin.getText());

                    } catch (SQLException ex) {
                        Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

                // Verifica se o jTextField ja existe no banco e retorna a mensagem
                if (verifica == true) {
                    jLabelAlertaLogin.setText("Este nome de usuario já existe");
                    jLabelAlertaLogin.setForeground(Color.red);

                }

            }
            // SE O BOTÃO ADICIONAR FOR PRECIONADO É FEITO ESSE PROCESSO    
        } else if (jButtonAdicionar.isEnabled()) {

            if (!"".equals(jTextFieldLogin.getText())) {

                try {
                    verifica = funcionarioControl.pesquisaLogin(jTextFieldLogin.getText());
                } catch (SQLException ex) {
                    Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (verifica == true) {
                jLabelAlertaLogin.setText("Este nome de usuario já existe");
                jLabelAlertaLogin.setForeground(Color.red);
            }

        }


    }//GEN-LAST:event_jTextFieldLoginFocusLost

    private void jFormattedTextFieldCpfMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCpfMouseExited

    }//GEN-LAST:event_jFormattedTextFieldCpfMouseExited

    private void jFormattedTextFieldCpfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCpfFocusLost

        String cpf, verificaObj;
        boolean verifica;
        VerificaCampos verificaCampo = new VerificaCampos();
        cpf = jFormattedTextFieldCpf.getText();

        //RETIRA A FORMATACAO 
        cpf = limpaMascara(cpf, "cpf");

        //SE O BOTÃO ALTERAR FOR PRECIONADO É FEIRO ESSE PROCESSO    
        if (jButtonAlterar.isEnabled()) {
            //VERIFICA SE O CPF ESTA VAZIO SE ESTIVER É COLOCADO NULL PARA NÃO AUTO COMPLETAR
            if ("".equals(cpf)) {

                jFormattedTextFieldCpf.setValue(null);

            } else {
                //verifica se o CPF do funcionario é igual ou diferente o digitado no TextField
                if (!this.equals(jFormattedTextFieldCpf.getText())) {

                    //    verifica = verificaCpf(cpf);
                    verifica = verificaCampo.verificaCpf(cpf);
                    if (verifica == true) {
                        jLabelCPF.setText("CPF VALIDO");
                        jLabelCPF.setForeground(Color.green);
                    } else if (verifica == false) {

                        jLabelCPF.setText("Digite o cpf corretamente");
                        jLabelCPF.setForeground(Color.red);
                    }
                }
            }

            // SE O BOTÃO ADICIONAR FOR PRECIONADO É FEITO ESSE PROCESSO    
        } else if (jButtonAdicionar.isEnabled()) {

            //VERIFICA SE O CPF ESTA VAZIO SE ESTIVER É COLOCADO NULL PARA NÃO AUTO COMPLETAR
            if ("".equals(cpf)) {

                jFormattedTextFieldCpf.setValue(null);

            } else {
                verifica = verificaCampo.verificaCpf(cpf);

                if (verifica == true) {
                    jLabelCPF.setText("");

                } else if (verifica == false) {

                    jLabelCPF.setText("CPF Invalido!");
                    jLabelCPF.setForeground(Color.red);
                }
            }

        }

    }//GEN-LAST:event_jFormattedTextFieldCpfFocusLost

    private void jFormattedTextFieldCpfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCpfFocusGained
        // RETIRA A MENSAVEM DO CPF INVALIDO
        jLabelCPF.setText("");

    }//GEN-LAST:event_jFormattedTextFieldCpfFocusGained

    private void jTextFieldLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldLoginActionPerformed


    private void jFormattedTextFieldCelularFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCelularFocusLost
        String celular;
        boolean verifica;
        celular = jFormattedTextFieldCelular.getText();

        celular = celular.replace("(", "");
        celular = celular.replace(")", "");
        celular = celular.replace("-", "");
        celular = celular.replace(" ", "");

        if ("".equals(celular)) {

            jFormattedTextFieldCelular.setValue(null);

        } else {
            ///VALIDAÇÃO DE CELULAR
        }

    }//GEN-LAST:event_jFormattedTextFieldCelularFocusLost

    private void jFormattedTextFieldTelefoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldTelefoneFocusLost

        String telefone;
        boolean verifica;
        telefone = jFormattedTextFieldTelefone.getText();

        telefone = telefone.replace("(", "");
        telefone = telefone.replace(")", "");
        telefone = telefone.replace("-", "");
        telefone = telefone.replace(" ", "");

        if ("".equals(telefone)) {

            jFormattedTextFieldTelefone.setValue(null);

        } else {
            ///VALIDAÇÃO DE TELEFONE
        }
    }//GEN-LAST:event_jFormattedTextFieldTelefoneFocusLost

    private void jFormattedTextFieldCepFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCepFocusLost

        String cep;
        boolean verifica;
        cep = jFormattedTextFieldCep.getText();

        cep = cep.replace("-", "");
        cep = cep.replace(" ", "");

        if ("".equals(cep)) {

            jFormattedTextFieldCep.setValue(null);

        } else {
            ///VALIDAÇÃO DE TELEFONE
        }

    }//GEN-LAST:event_jFormattedTextFieldCepFocusLost

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed

        int index = jComboBoxTipoPesquisa.getSelectedIndex();

        String nome, cpf;

        FuncionarioDAO funcionarioControl = new FuncionarioDAO();
        FuncionarioModel funcionario = new FuncionarioModel();
        ArrayList<FuncionarioModel> listaFuncionarios = new ArrayList<>();

        switch (index) {

            case 0: {//NOME
                nome = jFormattedTextFieldPesquisar.getText();

                try {
                    listaFuncionarios = funcionarioControl.pesquisarNomeEspecifico(nome);
                    atualizaTabelaFuncionarios(listaFuncionarios);

                } catch (ParseException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NaoEncontradoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }

            break;

            case 1: //CPF
            {

                cpf = jFormattedTextFieldPesquisar.getText();
                cpf = limpaMascara(cpf, "cpf");
                try {
                    funcionario = funcionarioControl.pesquisar(cpf, "cpf");
                    atualizaTabelaFuncionario(funcionario);

                } catch (NaoEncontradoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (SQLException ex) {
                    Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            break;
        }


    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    private void jComboBoxTipoPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoPesquisaActionPerformed

        int index = jComboBoxTipoPesquisa.getSelectedIndex();
        String caractere;
        //qtd de caracteres na pesquisa do nome
        int quantidadeJFormatString = 100;

        MaskFormatter mascara = null;
        switch (index) {

            case 0: {

                try {
                    //MASCARCA DE CARACTERES STRING 
                    // mascara = new MaskFormatter(caractere);
                    setaCampoPesquisa();
                    //  jFormattedTextFieldPesquisar.setValue(null);

                } catch (ParseException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                }

                // jFormattedTextFieldPesquisar.setFormatterFactory(new DefaultFormatterFactory(mascara));
            }

            // jLabelLegendaPesquisa.setText("Nome:");
            break;

            case 1: //CPF
            {

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

    private void jFormattedTextFieldPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldPesquisarKeyReleased
        String nome, cpf;
        int index = jComboBoxTipoPesquisa.getSelectedIndex(), codigoInt;

        FuncionarioDAO funcionarioControl = new FuncionarioDAO();
        FuncionarioModel funcionarioModel = new FuncionarioModel();
        ArrayList<FuncionarioModel> listaFuncionario;

        switch (index) {

            case 0: {

                jLabelLegendaPesquisa.setText("Nome:");
                nome = jFormattedTextFieldPesquisar.getText().trim();

                if ("".equals(nome)) {

                    try {

                        listaFuncionario = funcionarioControl.findAll();
                        atualizaTabelaFuncionarios(listaFuncionario);

                    } catch (SQLException ex) {

                        Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex);

                    }

                } else if (!nome.equals("")) {

                    try {

                        listaFuncionario = funcionarioControl.pesquisarNome(nome);
                        atualizaTabelaFuncionarios(listaFuncionario);

                    } catch (SQLException ex) {

                        Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex);

                    } catch (ParseException ex) {

                        Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex);

                    } catch (NaoEncontradoException ex) {
                        try {
                            atualizaTabelaSParametro();
                        } catch (SQLException ex1) {
                            Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (ParseException ex1) {
                            Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
                }
            }

            break;

            case 1: //CPF
            {
                jLabelLegendaPesquisa.setText("CPF:");
                cpf = jFormattedTextFieldPesquisar.getText();
                cpf = limpaMascara(cpf, "cpf");

                if ("".equals(cpf)) {

                    try {

                        listaFuncionario = funcionarioControl.findAll();
                        atualizaTabelaFuncionarios(listaFuncionario);

                    } catch (SQLException ex) {
                        Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else if (!cpf.equals("")) {

                    try {

                        funcionarioModel = funcionarioControl.pesquisar(cpf, "cpf");

                    } catch (SQLException ex) {

                        try {
                            atualizaTabelaSParametro();
                        } catch (SQLException ex1) {
                            Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (ParseException ex1) {
                            Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex1);
                        }

                    } catch (NaoEncontradoException ex) {
                        Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }

            break;

        }


    }//GEN-LAST:event_jFormattedTextFieldPesquisarKeyReleased

    private void jButtonConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultarActionPerformed
        String uf, tipo, cnpj, celular, telefone, cargo, dataString;
        FuncionarioDAO funcionarioControl = new FuncionarioDAO();
        FuncionarioModel funcionarioModel = new FuncionarioModel();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        int id, linha = -1;

        linha = jTableFuncionario.getSelectedRow();

        if (linha == -1) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta", JOptionPane.WARNING_MESSAGE);

        } else {

            String cpf = (String) jTableFuncionario.getValueAt(linha, 1);
            cpf = limpaMascara(cpf, "cpf");

            try {
                funcionarioModel = funcionarioControl.pesquisar(cpf, "cpf");

                setaDialog(1, false, false, false, false, true, true, false, true, "Consultar");
                jLabelDataCadastro.setVisible(true);
                // jTabbedPane1.setTitleAt(1, "Consultar");
                data.setVisible(true);

                cargo = funcionarioModel.getCargo();
                jComboBoxCargo.setSelectedItem(cargo);

                jTextFieldbairro.setText(funcionarioModel.getBairro());
                jFormattedTextFieldCep.setText(funcionarioModel.getCep());
                jTextFieldCidade.setText(funcionarioModel.getCidade());
                jTextFieldNome.setText(funcionarioModel.getNome());

                jTextFieldNumero.setText(funcionarioModel.getNumero());
                jTextFieldRua.setText(funcionarioModel.getRua());
                jTextFieldLogin.setText(funcionarioModel.getLogin());
                jTextFieldSenha.setText(funcionarioModel.getSenha());

                dataString = formatador.format(funcionarioModel.getDataCadastro());
                data.setText(dataString);

                cpf = funcionarioModel.getCpf();
                cpf = limpaMascara(cpf, "cpf");

                telefone = funcionarioModel.getTelefone();
                telefone = limpaMascara(telefone, "telefone");

                celular = funcionarioModel.getCelular();
                celular = limpaMascara(celular, "celular");

                if (!cpf.equals("")) {
                    jFormattedTextFieldCpf.setText(funcionarioModel.getCpf());
                }
                if (!telefone.equals("")) {

                    jFormattedTextFieldTelefone.setText(funcionarioModel.getTelefone());
                }
                if (!celular.equals("")) {
                    jFormattedTextFieldCelular.setText(funcionarioModel.getCelular());
                }

                uf = funcionarioModel.getUf();
                jComboBoxCargo.setSelectedItem(uf);

                this.dataCadastro = funcionarioModel.getDataCadastro();
                this.idFuncionario = funcionarioModel.getId();

                ativaCampos(false);

            } catch (SQLException ex) {
                Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NaoEncontradoException ex) {
                Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButtonConsultarActionPerformed

    private void jTextFieldLoginFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldLoginFocusGained

        jLabelAlertaLogin.setText("");

    }//GEN-LAST:event_jTextFieldLoginFocusGained

    private void jTextFieldNomeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNomeKeyTyped
        int qtdCaracteres = 70;
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

    private void jTextFieldbairroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldbairroKeyTyped
        int qtdCaracteres = 70;
        char c = evt.getKeyChar();
        if (Character.isDigit(c)) {
            evt.consume();
        }

        if (jTextFieldbairro.getText().length() >= qtdCaracteres) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldbairroKeyTyped

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

    private void jTextFieldLoginKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldLoginKeyTyped
        int qtdCaracteres = 80;

        if (jTextFieldLogin.getText().length() >= qtdCaracteres) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldLoginKeyTyped

    private void jTextFieldSenhaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSenhaKeyTyped
        int qtdCaracteres = 16;

        if (jTextFieldSenha.getText().length() >= qtdCaracteres) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldSenhaKeyTyped

    private void jFormattedTextFieldPesquisarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldPesquisarKeyTyped
        int qtdCaracteres = 80;
        char c = evt.getKeyChar();
        if (jComboBoxTipoPesquisa.getSelectedIndex() == 0 && Character.isDigit(c)) {
            evt.consume();
        }

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
            ArrayList<FuncionarioModel> listaFuncionario;
            FuncionarioModel funcionario = new FuncionarioModel();
            
            FuncionarioDAO funcionarioDao = new FuncionarioDAO();
            
            
            linha = jTableFuncionario.getSelectedRow();

            if (linha == -1) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta",JOptionPane.WARNING_MESSAGE);

            } else {
                
                try {
                    
                    String cpf = (String) jTableFuncionario.getValueAt(linha, 1);
                    cpf = limpaMascara(cpf, "cpf");
                    funcionario = funcionarioDao.pesquisar(cpf, "cpf");
                    funcionario.setStatus(true);
                    funcionarioDao.alterar(funcionario);
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
            java.util.logging.Logger.getLogger(ManterFuncionarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManterFuncionarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManterFuncionarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManterFuncionarioView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ManterFuncionarioView dialog = null;
                try {
                    dialog = new ManterFuncionarioView(new javax.swing.JFrame(), true, "");
                } catch (SQLException ex) {
                    Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ManterFuncionarioView.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JTextField data;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonAlterar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonConsultar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonPesquisar;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JButton jButtonStatus;
    private javax.swing.JComboBox<String> jComboBoxCargo;
    private javax.swing.JComboBox<String> jComboBoxTipoPesquisa;
    private javax.swing.JComboBox<String> jComboBoxUF;
    private javax.swing.JFormattedTextField jFormattedTextFieldCelular;
    private javax.swing.JFormattedTextField jFormattedTextFieldCep;
    private javax.swing.JFormattedTextField jFormattedTextFieldCpf;
    private javax.swing.JFormattedTextField jFormattedTextFieldPesquisar;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelefone;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAlertaLogin;
    private javax.swing.JLabel jLabelCPF;
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
    private javax.swing.JPanel jPanelPrincipalFuncionario;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableFuncionario;
    private javax.swing.JTextField jTextFieldCidade;
    private javax.swing.JTextField jTextFieldLogin;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldNumero;
    private javax.swing.JTextField jTextFieldRua;
    private javax.swing.JTextField jTextFieldSenha;
    private javax.swing.JTextField jTextFieldbairro;
    // End of variables declaration//GEN-END:variables
}
