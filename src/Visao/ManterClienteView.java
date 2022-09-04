package Visao;

import Control.Exceptions.JaExisteException;
import Control.Exceptions.NaoEncontradoException;
import DAO.ClienteDAO;
import DAO.ContasReceberDAO;
import DAO.FuncionarioDAO;
import Modelo.ClienteModel;
import Modelo.ContaReceberModel;
import Modelo.FuncionarioModel;
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
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public final class ManterClienteView extends javax.swing.JDialog {

    String cnpj, cpf;
    boolean cpfValido = false, cnpjValido = false, cnpjNulo = false, cpfNulo = false;
    int idCliente;
    Date dataCadastro;
    String nomeTela;
    ArrayList<ClienteModel> listaClientes;
    FuncionarioModel funcionarioModel;
    ClienteModel cliente = new ClienteModel();
    
    public ManterClienteView(java.awt.Frame parent, boolean modal, String nomeTela) throws SQLException, ParseException, NaoEncontradoException {

        super(parent, modal);
        
        initComponents();
        setaCampoPesquisa();
        jRadioButton1.setSelected(true);
        jButtonStatus.setEnabled(false);
        jTextFieldDataCadastro.setEditable(false);
        
        //VERIFICA O ULTIMO LOGIN
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
        this.funcionarioModel = new FuncionarioModel();
        this.funcionarioModel = funcionarioDao.pesquisarDataHoralogin();
        
        ativaCampos(true);
        //Bloqueia a area de Cadastro de Cliente, botoes cancelar e salvar;
        jTabbedPane1.setEnabledAt(1, false);
        jButtonCancelar.setEnabled(false);
        jButtonSalvar.setEnabled(false);

        //Peencher Tabela de Clientes
        ClienteDAO clienteControl = new ClienteDAO();
        ArrayList<ClienteModel> listaDeClientes = clienteControl.findAll();

        atualizaTabelaClientes(listaDeClientes);
        System.out.println("TESTE DE CLIENTE"+ this.funcionarioModel.getNome());
        if (this.funcionarioModel.getCargo().equals("funcionario")) {

            jButtonAlterar.setEnabled(false);
            jButtonExcluir.setEnabled(false);

        }
        
        if("venda".equals(this.nomeTela)){
            setaDialog(1, true, false, false, true, false, false, false, true, "Cadastrar");
        }
        
        this.nomeTela = nomeTela;
        
        if(this.nomeTela.equals("telaPrincipal")){
            
            setaDialog(1, true, false, false, true, true, false, false, true, "Cadastrar");
            
        }else{
            
            setaDialog(0, true, true, true, false, false, true, true, false, "Cadastro");
            ativaCampos(true);
            
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jButtonPesquisar = new javax.swing.JButton();
        jComboBoxTipoPesquisa = new javax.swing.JComboBox<>();
        jFormattedTextFieldPesquisar = new javax.swing.JFormattedTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabelLegendaPesquisa = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTabelaDeCliente = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel15 = new javax.swing.JPanel();
        jButtonStatus = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jTextFieldRua = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        jTextFieldBairro = new javax.swing.JTextField();
        jPanel19 = new javax.swing.JPanel();
        jTextFieldCidade = new javax.swing.JTextField();
        jPanel22 = new javax.swing.JPanel();
        jTextFieldNumero = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jComboBoxUF = new javax.swing.JComboBox<>();
        jPanel21 = new javax.swing.JPanel();
        jFormattedTextFieldCep = new javax.swing.JFormattedTextField();
        jPanel8 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jTextFieldNome = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jFormattedTextFieldCpf = new javax.swing.JFormattedTextField();
        jPanel17 = new javax.swing.JPanel();
        jFormattedTextFieldCnpj = new javax.swing.JFormattedTextField();
        jLabelCPF = new javax.swing.JLabel();
        jLabelCNPJ = new javax.swing.JLabel();
        jPanelDataCadastro = new javax.swing.JPanel();
        jTextFieldDataCadastro = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jFormattedTextFieldTelefone = new javax.swing.JFormattedTextField();
        jPanel13 = new javax.swing.JPanel();
        jFormattedTextFieldCelular = new javax.swing.JFormattedTextField();
        jPanel7 = new javax.swing.JPanel();
        jButtonCadastrar = new javax.swing.JButton();
        jButtonAlterar = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButtonConsultar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        jButton5.setText("Cadastro");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Controle de Clientes");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Pesquisar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jButtonPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lupa.png"))); // NOI18N
        jButtonPesquisar.setText("Pesquisar");
        jButtonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarActionPerformed(evt);
            }
        });

        jComboBoxTipoPesquisa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nome", "Cpf", "Cnpj" }));
        jComboBoxTipoPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoPesquisaActionPerformed(evt);
            }
        });

        try {
            jFormattedTextFieldPesquisar.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jFormattedTextFieldPesquisar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldPesquisarFocusLost(evt);
            }
        });
        jFormattedTextFieldPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldPesquisarKeyReleased(evt);
            }
        });

        jLabel15.setText("Tipo:");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxTipoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabelLegendaPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(95, 95, 95))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jFormattedTextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jButtonPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(jLabelLegendaPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxTipoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPesquisar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tabela de Clientes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        jPanel10.setForeground(new java.awt.Color(153, 153, 153));

        jTableTabelaDeCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Cpf", "Celular", "Tipo", "Cnpj"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableTabelaDeCliente);
        if (jTableTabelaDeCliente.getColumnModel().getColumnCount() > 0) {
            jTableTabelaDeCliente.getColumnModel().getColumn(0).setMinWidth(250);
            jTableTabelaDeCliente.getColumnModel().getColumn(0).setMaxWidth(250);
            jTableTabelaDeCliente.getColumnModel().getColumn(1).setMinWidth(100);
            jTableTabelaDeCliente.getColumnModel().getColumn(1).setMaxWidth(100);
            jTableTabelaDeCliente.getColumnModel().getColumn(2).setMinWidth(100);
            jTableTabelaDeCliente.getColumnModel().getColumn(2).setMaxWidth(100);
            jTableTabelaDeCliente.getColumnModel().getColumn(3).setMinWidth(100);
            jTableTabelaDeCliente.getColumnModel().getColumn(3).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

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

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Ativar Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jButtonStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Concluir30x30.png"))); // NOI18N
        jButtonStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Principal", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Endereço", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Rua", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldRua.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldRuaKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldRua, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jTextFieldRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Bairro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldBairro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBairroKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldBairro)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jTextFieldBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Cidade", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldCidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCidadeKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldCidade, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jTextFieldCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Numero", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNumeroKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldNumero)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jTextFieldNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "UF", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jComboBoxUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        jComboBoxUF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jComboBoxUFKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxUF, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jComboBoxUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Cep", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        try {
            jFormattedTextFieldCep.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldCep.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldCepFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldCepFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFormattedTextFieldCep, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jFormattedTextFieldCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Nome", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNomeKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldNome)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jTextFieldNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "CPF", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

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

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFormattedTextFieldCpf, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addComponent(jFormattedTextFieldCpf, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "CNPJ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

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

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFormattedTextFieldCnpj, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jFormattedTextFieldCnpj)
                .addContainerGap())
        );

        jPanelDataCadastro.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDataCadastro.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data do Cadastro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        javax.swing.GroupLayout jPanelDataCadastroLayout = new javax.swing.GroupLayout(jPanelDataCadastro);
        jPanelDataCadastro.setLayout(jPanelDataCadastroLayout);
        jPanelDataCadastroLayout.setHorizontalGroup(
            jPanelDataCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDataCadastroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldDataCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelDataCadastroLayout.setVerticalGroup(
            jPanelDataCadastroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDataCadastroLayout.createSequentialGroup()
                .addComponent(jTextFieldDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Telefone", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

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

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFormattedTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jFormattedTextFieldTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Celular", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        try {
            jFormattedTextFieldCelular.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldCelular.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldCelularFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldCelularFocusLost(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFormattedTextFieldCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jFormattedTextFieldCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 214, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabelCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelCNPJ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCNPJ, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelDataCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cadastro", jPanel4);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonCadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/adicionar24x24.png"))); // NOI18N
        jButtonCadastrar.setText("Cadastro");
        jButtonCadastrar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastrarActionPerformed(evt);
            }
        });

        jButtonAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Atualizar24x24.png"))); // NOI18N
        jButtonAlterar.setText("Alterar     ");
        jButtonAlterar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAlterarActionPerformed(evt);
            }
        });

        jButtonExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/excluir24x24.png"))); // NOI18N
        jButtonExcluir.setText("Excluir   ");
        jButtonExcluir.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/voltar24x24.png"))); // NOI18N
        jButtonCancelar.setText("Cancelar");
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

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/VoltarSimples24x24.png"))); // NOI18N
        jButton1.setText("Fechar      ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButtonConsultar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonCadastrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonExcluir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonSalvar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
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
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(3, 133, 188));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Controle de Clientes");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(212, 212, 212)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTabbedPane1)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void limpaCampos() {

        jTextFieldBairro.setText("");
        jTextFieldCidade.setText("");
        jTextFieldNome.setText("");
        jTextFieldNumero.setText("");
        jTextFieldRua.setText("");
        jComboBoxUF.setSelectedIndex(0);
        jFormattedTextFieldCelular.setText("");
        jFormattedTextFieldCep.setText("");
        jFormattedTextFieldCnpj.setText("");
        jFormattedTextFieldCpf.setText("");
        jFormattedTextFieldTelefone.setText("");
        jLabelCNPJ.setText("");
        jLabelCPF.setText("");

        jFormattedTextFieldPesquisar.setText("");

        jPanelDataCadastro.setVisible(false);
        jTextFieldDataCadastro.setVisible(false);
        jComboBoxUF.setEnabled(true);
        jFormattedTextFieldCelular.setValue(null);
        jFormattedTextFieldCep.setValue(null);
        jFormattedTextFieldCnpj.setValue(null);
        jFormattedTextFieldCpf.setValue(null);
        jFormattedTextFieldTelefone.setValue(null);
        jFormattedTextFieldPesquisar.setValue(null);

        jTextFieldDataCadastro.setVisible(false);
        jTextFieldDataCadastro.setEditable(false);
        
    }

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

    public void ativaCampos(boolean status) {

        jFormattedTextFieldCelular.setEditable(status);
        jFormattedTextFieldCnpj.setEditable(status);
        jFormattedTextFieldTelefone.setEditable(status);
        jTextFieldNome.setEditable(status);
        jFormattedTextFieldCpf.setEditable(status);
        jTextFieldRua.setEditable(status);
        jTextFieldNumero.setEditable(status);
        jFormattedTextFieldCep.setEditable(status);
        jTextFieldCidade.setEditable(status);
        jComboBoxUF.setEnabled(status);
        jTextFieldBairro.setEditable(status);
        jTextFieldDataCadastro.setEditable(false);

    }
    public void ativaCliente(ClienteModel cliente){
        
        try {
            ClienteDAO clienteDao = new ClienteDAO();
            cliente.setStatus(true);
            clienteDao.alterar(cliente);
        } catch (SQLException ex) {
            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    String insereMascara(String mascara, String tipo) throws ParseException {
        if (mascara.equals("")) {

            return mascara;

        }
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

    // responsavel por ativar e desativar botoes e campos do clienteView, 
    // trocar a tela e titulo do jTabbedPanel
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

        if (this.funcionarioModel.getCargo().equals("funcionario")) {

            jButtonAlterar.setEnabled(false);
            jButtonExcluir.setEnabled(false);

        }
        
        if(jRadioButton2.isSelected()){
            jButtonAlterar.setEnabled(false);
            jButtonCadastrar.setEnabled(false);
            jButtonExcluir.setEnabled(false);
            
        }
        limpaCampos();
    }

    //Responsavel Por Setar o campo do JFormattedField da pesquisa
    public void setaCampoPesquisa() throws ParseException {
        
        jLabelLegendaPesquisa.setText("Nome:");
        jFormattedTextFieldPesquisar.setValue(null);
        jFormattedTextFieldPesquisar.setFormatterFactory(null);
        jFormattedTextFieldPesquisar.setText("");
        
    }

    public void atualizaTabelaSParametro() throws SQLException, ParseException, NaoEncontradoException {
       
        ClienteDAO clienteControl = new ClienteDAO();
        ArrayList<ClienteModel> listaDeClientes = clienteControl.findAll();
        atualizaTabelaClientes(listaDeClientes);
        
    }

    public void atualizaTabelaClientes(ArrayList<ClienteModel> listaClientes) {

        DefaultTableModel val = (DefaultTableModel) jTableTabelaDeCliente.getModel();
        val.setNumRows(0); // excluir os registros que estão na JTable
        //listaDeClientes = listaClientes;
        
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        listaClientes.forEach((cliente) -> {

            String cpf, cnpj, cep, telefone, celular;
               
            try {
                //if(cliente.getStatus() == true){
                cpf = insereMascara(cliente.getCpf(), "cpf");
                
                if(cliente.getCnpj() != null){
                cnpj = insereMascara(cliente.getCnpj(), "cnpj");
                cliente.setCnpj(cnpj);
                }
                
                cep = insereMascara(cliente.getCep(), "cep");
                if(cliente.getTelefone()!= null){
                telefone = insereMascara(cliente.getTelefone(), "telefone");
                 cliente.setTelefone(telefone);
                }
                
                if(cliente.getCelular()!= null){
                celular = insereMascara(cliente.getCelular(), "celular");
                cliente.setCelular(celular);
                }
                
                cliente.setCpf(cpf);
                
                cliente.setCep(cep);
                //ATIVADOS
                if(jRadioButton1.isSelected() && cliente.getStatus()==true){
                    
                    val.addRow(new Object[]{cliente.getNome(), cliente.getCpf(), cliente.getCelular(), cliente.getTipo(), cliente.getCnpj()});
                }
                else if(jRadioButton2.isSelected() && cliente.getStatus()==false){
                    
                    val.addRow(new Object[]{cliente.getNome(), cliente.getCpf(), cliente.getCelular(), cliente.getTipo(), cliente.getCnpj()});
                
                }
                
            } catch (ParseException ex) {
                // arrumar isso
                JOptionPane.showMessageDialog(null, "não foi possivel atualizar tabela", "Erro", JOptionPane.ERROR_MESSAGE);
            }
            
        });

    }

    public void atualizaTabelaCliente(ClienteModel cliente) {

        DefaultTableModel val = (DefaultTableModel) jTableTabelaDeCliente.getModel();
        val.setNumRows(0); // excluir os registros que estão na JTable
        //listaDeClientes = listaClientes;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date data = cliente.getDataCadastro();

        String dataString = dateFormat.format(data);

        // Arrumar essa validação 
        if (cliente != null) {
            
            String cpf, cnpj, cep, telefone, celular;
            if(cliente.getStatus()==true){
            try {
                cpf = insereMascara(cliente.getCpf(), "cpf");
                cnpj = insereMascara(cliente.getCnpj(), "cnpj");
                cep = insereMascara(cliente.getCep(), "cep");
                telefone = insereMascara(cliente.getTelefone(), "telefone");
                celular = insereMascara(cliente.getCelular(), "celular");

                cliente.setCpf(cpf);
                cliente.setCnpj(cnpj);
                cliente.setCep(cep);
                cliente.setTelefone(telefone);
                cliente.setCelular(celular);

                val.addRow(new Object[]{cliente.getNome(), cliente.getCpf(), cliente.getCelular(), cliente.getTipo(), cliente.getCnpj()});

            } catch (ParseException ex) {
                JOptionPane.showMessageDialog(null, "não foi possivel atualizar a tabela de cliente", "Erro ",
                        JOptionPane.ERROR_MESSAGE);
            }
            }
        }
    }

    //BOTÃO ALTERAR
    private void jButtonAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAlterarActionPerformed

        ClienteDAO clienteControl = new ClienteDAO();
        ClienteModel clienteModel = new ClienteModel();
        String uf, cnpj, cpf, celular, telefone;

        int linha = -1;

        linha = jTableTabelaDeCliente.getSelectedRow();

        if (linha == -1) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta", JOptionPane.WARNING_MESSAGE);

        } else {

            cpf = (String) jTableTabelaDeCliente.getValueAt(linha, 1);
            cnpj = (String) jTableTabelaDeCliente.getValueAt(linha, 4);

            cpf = limpaMascara(cpf, "cpf");
            cnpj = limpaMascara(cnpj, "cnpj");

            this.cpf = cpf;
            this.cnpj = cnpj;

            try {
                clienteModel = clienteControl.pesquisar(cpf, "cpf");

                setaDialog(1, false, false, true, true, true, false, false, true, "Alterar");

                cnpj = clienteModel.getCnpj();
                cnpj = limpaMascara(cnpj, "cnpj");

                telefone = clienteModel.getTelefone();
                telefone = limpaMascara(telefone, "telefone");

                celular = clienteModel.getCelular();
                celular = limpaMascara(celular, "celular");

                if (!cnpj.equals("")) {
                    jFormattedTextFieldCnpj.setText(clienteModel.getCnpj());
                }
                if (!telefone.equals("")) {

                    jFormattedTextFieldTelefone.setText(clienteModel.getTelefone());
                }
                if (!celular.equals("")) {
                    jFormattedTextFieldCelular.setText(clienteModel.getCelular());
                }

                this.dataCadastro = clienteModel.getDataCadastro();
                jTextFieldNome.setText(clienteModel.getNome());
                jFormattedTextFieldCpf.setText(clienteModel.getCpf());

                jTextFieldRua.setText(clienteModel.getRua());
                jTextFieldNumero.setText(clienteModel.getNumero());
                jTextFieldBairro.setText(clienteModel.getBairro());
                jTextFieldCidade.setText(clienteModel.getCidade());
                jFormattedTextFieldCep.setText(clienteModel.getCep());

                this.idCliente = clienteModel.getId();

                uf = clienteModel.getUf();
                jComboBoxUF.setSelectedItem(uf);

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (NaoEncontradoException ex) {
                Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_jButtonAlterarActionPerformed

    //BOTÃO CADASTRAR;
    private void jButtonCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastrarActionPerformed

        setaDialog(1, true, false, false, true, true, false, false, true, "Cadastrar");

    }//GEN-LAST:event_jButtonCadastrarActionPerformed

    //BOTÃO EXCLUIR
    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed

        int resposta, id;

        resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir cliente ?", "Confirma",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (resposta == 0) {

            int linha = -1;
            ArrayList<ClienteModel> listaClientes;
            ClienteDAO clienteControl = new ClienteDAO();
            ClienteModel cliente = new ClienteModel();
            linha = jTableTabelaDeCliente.getSelectedRow();

            if (linha == -1) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta",JOptionPane.WARNING_MESSAGE);

            } else {

                String cpf = (String) jTableTabelaDeCliente.getValueAt(linha, 1);
                cpf = limpaMascara(cpf, "cpf");
                try {
                    
                    //VERIFICAÇÃO DE CONTA A RECEBER
                    
                    ArrayList<ContaReceberModel>listaConta = new ArrayList<>();
                    ArrayList<ContaReceberModel>contasEmAberto = new ArrayList<>();
                    ContasReceberDAO contaDao = new ContasReceberDAO();
                    
                    listaConta = contaDao.pesquisarPorCpf(cpf);
                    
                    for(ContaReceberModel c: listaConta){
                        if(c.getSituacao().equals("A Receber")){
                            contasEmAberto.add(c);
                        }
                    }
                    
                    if(contasEmAberto.isEmpty()){
                    cliente = clienteControl.pesquisar(cpf, "cpf");
                    cliente.setStatus(false);
                    String msg = clienteControl.excluir(cliente);
                    JOptionPane.showMessageDialog(null, msg);
                    listaClientes = clienteControl.findAll();
                    atualizaTabelaClientes(listaClientes);
                    }else{
                        JOptionPane.showMessageDialog(null, "Há contas em aberto para este cliente", "Verifique Contas a Receber",  JOptionPane.ERROR_MESSAGE);
                    }
                    

                } catch (SQLException ex) {
                    Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NaoEncontradoException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }


    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed

        VerificaCampos verificaCampo = new VerificaCampos();

        String tipo = null, cpf, cnpj, cep, celular, telefone;
        Boolean pessoaFisica = false, pessoaJuridica = false, verificaCpf = false, verificaCnpj = false, confereCpf = false, confereCnpj = false;
        int resposta;
        ClienteModel clienteModel;
        clienteModel = new ClienteModel();
        ClienteDAO clienteControl = new ClienteDAO();

        //pega a data atual
        Date data = new Date(System.currentTimeMillis());

        // Pega o valor do combobox
        //valida AQUI
        clienteModel.setNome(jTextFieldNome.getText());

        //Limpa os campos formatados
        cpf = jFormattedTextFieldCpf.getText();
        cpf = limpaMascara(cpf, "cpf");

        cnpj = jFormattedTextFieldCnpj.getText();
        cnpj = limpaMascara(cnpj, "cnpj");

        verificaCnpj = verificaCampo.verificaCNPJ(cnpj);
        verificaCpf = verificaCampo.verificaCpf(cpf);

        celular = jFormattedTextFieldCelular.getText();
        celular = limpaMascara(celular, "celular");

        telefone = jFormattedTextFieldTelefone.getText();
        telefone = limpaMascara(telefone, "telefone");

        cep = jFormattedTextFieldCep.getText();
        cep = limpaMascara(cep, "cep");
        clienteModel.setId(0);
        clienteModel.setNumero(jTextFieldNumero.getText());
        clienteModel.setRua(jTextFieldRua.getText());
        clienteModel.setCidade(jTextFieldCidade.getText());
        clienteModel.setBairro(jTextFieldBairro.getText());
        clienteModel.setUf(jComboBoxUF.getSelectedItem().toString());
        clienteModel.setTelefone(telefone);
        clienteModel.setCelular(celular);
        clienteModel.setCpf(cpf);
        clienteModel.setCnpj(cnpj);
        clienteModel.setCep(cep);

        //OP CADASTRAR
        if (jButtonCadastrar.isEnabled()) {
            resposta = JOptionPane.showConfirmDialog(null, "Deseja efetuar o cadastro ?", "Confirmar",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            // se o cpf for valido
            if (resposta == 0) {

                /// VERIFICA SE JA EXISTE UM CNPJ DIFERENTE DO CNPJ ATUAL
                try {

                    clienteModel.setDataCadastro(data);

                    //Primeiro verifica os campos nulos 
                    if ("".equals(clienteModel.getNome()) || "".equals(clienteModel.getCelular())
                            || "".equals(clienteModel.getCpf())) {

                        JOptionPane.showMessageDialog(null, "preencha os campos obrigatorios","Alerta",JOptionPane.WARNING_MESSAGE);

                    } else {
                        //Depois verigica se o cpf é valido
                        if (verificaCpf == true) {

                            //Após verigicar o Cpf verifica o CNPJ
                            //Esta verificação é para saber o tipo do cliente
                            //se o cliente conter o CNPJ false e o Campo estiver vazio, o cliente é uma pessoa fisica 
                            if (verificaCnpj == false && cnpj.isEmpty()) {

                                pessoaFisica = true;
                                tipo = "Pessoa Fisica";
                                pessoaJuridica = false;
                                //se o cliente conter o CNPJ true e o Campo não estiver vazio, o cliente é uma pessoa Juridica

                            } else {

                                pessoaJuridica = true;
                                tipo = "Pessoa Juridica";
                                pessoaFisica = false;

                            }
                            //atribui o tipo
                            clienteModel.setTipo(tipo);

                            //se o cliente conter o CNPJ false e o Campo não estiver vazio, o campo CNPJ esta errado
                            if (verificaCnpj == false && !"".equals(cnpj)) {

                                JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o cadastro, Informe o CNPJ corretamente", "Erro",JOptionPane.ERROR_MESSAGE);

                            } else {

                                //Verifica se ja existe
                                ClienteDAO clienteDao = new ClienteDAO();
                                ClienteModel clienteModels = new ClienteModel();

                                String verifica = clienteDao.verificaCadastroString(cpf, "cpf", "cadastro");
                                if (verifica.equals("ClienteDesativado")) {
                                    int b = JOptionPane.showConfirmDialog(null, "Este cpf já esta cadastrado no sistema, porem está desativado, deseja reativa-lo?", "Não é possivel efetuar o cadastro!",
                                            JOptionPane.YES_NO_OPTION,
                                            JOptionPane.ERROR_MESSAGE);
                                    if (b == 0) {
                                        clienteModels = clienteDao.findByCPF(cpf);
                                        //ATIVA O CLIENTE
                                        ativaCliente(clienteModels);
                                        
                                        JOptionPane.showMessageDialog(null, "O cliente foi reativado! verifique a tabela de clientes");
                                        if(this.nomeTela.equals("telaPrincipal")){
                                            super.dispose();
                                        }
                                        setaDialog(0, true, true, true, false, false, true, true, false, "Cadastro");
                                        ativaCampos(true);
                                        
                                    } else if (b == 1) {
                                        jFormattedTextFieldCnpj.requestFocus();
                                    }

                                } else {
                                    if (!"".equals(cnpj) && "Pessoa Juridica".equals(tipo)) {

                                        clienteControl.verificaCadastro(cnpj, "cnpj", "cadastro");

                                    }
                                    clienteModel.setStatus(true);

                                    ArrayList<ClienteModel> lista = clienteControl.findAll();

                                    for (ClienteModel c : lista) {
                                        if (c.getCpf().equals(clienteModel.getCpf()) && c.getStatus() == false) {
                                            clienteModel.setId(c.getId());
                                        }
                                    }

                                    if (clienteModel.getId() == 0) {
                                        clienteControl.inserir(clienteModel);
                                    } else {
                                        clienteModel.setStatus(true);
                                        clienteControl.alterar(clienteModel);
                                    }

                                    atualizaTabelaSParametro();
                                    if (this.nomeTela.equals("telaPrincipal")) {
                                        super.dispose();
                                    }
                                    setaDialog(0, true, true, true, false, false, true, true, false, "Cadastrar");
                                }

                            }

                        } else {

                            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o cadastro, informe o CPF valido", "Erro", JOptionPane.ERROR_MESSAGE);
                            jFormattedTextFieldCpf.requestFocus();

                        }
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JaExisteException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (ParseException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NaoEncontradoException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } //OP ALTERAR
        else if (jButtonAlterar.isEnabled()) {

            resposta = JOptionPane.showConfirmDialog(null, "Deseja alterar cliente?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            clienteModel.setStatus(true);
            if (resposta == 0) {

                clienteModel.setId(this.idCliente);

                clienteModel.setDataCadastro(this.dataCadastro);

                try {

                    clienteModel.setDataCadastro(data);
                    //Primeiro verifica os campos nulos 
                    if ("".equals(clienteModel.getNome()) || "".equals(clienteModel.getCelular())
                            || "".equals(clienteModel.getCpf())) {

                        JOptionPane.showMessageDialog(null, "preencha os campos obrigatorios","Alerta", JOptionPane.WARNING_MESSAGE);

                    } else {
                        //Depois verigica se o cpf é valido para adicionar ao tipo
                        if (verificaCpf == true) {

                            //Após verigicar o Cpf verifica o CNPJ
                            //Esta verificação é para saber o tipo do cliente
                            //se o cliente conter o CNPJ false e o Campo estiver vazio, o cliente é uma pessoa fisica 
                            if (verificaCnpj == false && cnpj.isEmpty()) {

                                pessoaFisica = true;
                                tipo = "Pessoa Fisica";
                                pessoaJuridica = false;
                                //se o cliente conter o CNPJ true e o Campo não estiver vazio, o cliente é uma pessoa Juridica

                            } else {

                                pessoaJuridica = true;
                                tipo = "Pessoa Juridica";
                                pessoaFisica = false;

                            }
                            //atribui o tipo
                            clienteModel.setTipo(tipo);

                            //se o cliente conter o CNPJ false e o Campo não estiver vazio, o campo CNPJ esta errado
                            if (verificaCnpj == false && !"".equals(cnpj)) {

                                JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o cadastro, Informe o CNPJ corretamente",
                                        "Erro", JOptionPane.ERROR_MESSAGE);

                            } else {
                                //PESSOA JURIDICA
                               
                                if ("Pessoa Juridica".equals(tipo)) {
                                    //verifica cnpj

                                    if (cnpj.equals(this.cnpj) && cpf.equals(this.cpf)) {
                                        
                                        clienteControl.alterar(clienteModel);
                                        atualizaTabelaSParametro();
                                        if(this.nomeTela.equals("telaPrincipal")){
                                            super.dispose();
                                         }  
                                        
                                        setaDialog(0, true, true, true, false, false, true, true, false, "Cadastro");
                                        
                                    } else if (!cnpj.equals(this.cnpj) && !"".equals(cnpj)) {
                                         clienteControl.verificaCadastro(cnpj, "cnpj","cadastro");
                                        if(!cpf.equals(this.cpf)){
                                         clienteControl.verificaCadastro(cpf, "cpf","cadastro");
                                         clienteModel.setStatus(true);
                                         clienteControl.alterar(clienteModel);
                                            atualizaTabelaSParametro();
                                        }else{
                                            clienteControl.alterar(clienteModel);
                                            atualizaTabelaSParametro();
                                        }
                                        if(this.nomeTela.equals("telaPrincipal")){
                                            super.dispose();
                                         }
                                        setaDialog(0, true, true, true, false, false, true, true, false, "Cadastro");
                                    }

                                }
                                //PESSOA FISICA 
                                if ("Pessoa Fisica".equals(tipo)) {
                                  
                                    if (!cpf.equals(this.cpf)) {
                                        clienteControl.verificaCadastro(cpf, "cpf","cadastro");
                                        clienteControl.alterar(clienteModel);
                                        atualizaTabelaSParametro();
                                        
                                        
                                    } else {
                                        clienteControl.alterar(clienteModel);
                                        atualizaTabelaSParametro();
                                        
                                        
                                    }
                                    if(this.nomeTela.equals("telaPrincipal")){
                                        super.dispose();
                                    }
                                    setaDialog(0, true, true, true, false, false, true, true, false, "Cadastrar");
                             
                                }

                            }

                        } else {

                            JOptionPane.showMessageDialog(null, "Não foi possivel efetuar o cadastro, informe o CPF valido","Erro", JOptionPane.ERROR_MESSAGE);
                            jFormattedTextFieldCpf.requestFocus();

                        }
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NaoEncontradoException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JaExisteException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }

        }

    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        if(this.nomeTela.equals("telaPrincipal")){
            super.dispose();
        }
        setaDialog(0, true, true, true, false, false, true, true, false, "Cadastro");
        ativaCampos(true);

    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        super.dispose();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jFormattedTextFieldCpfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCpfFocusLost

        String cpf;
        boolean verifica;
        VerificaCampos verificaCampo = new VerificaCampos();
        cpf = jFormattedTextFieldCpf.getText();

        //  verificaObj = this.clienteModel.getCpf();
        cpf = limpaMascara(cpf, "cpf");

        //SE O BOTÃO ALTERAR FOR PRECIONADO É FEIRO ESSE PROCESSO    
        if (jButtonAlterar.isEnabled()) {

            //VERIFICA SE O CPF ESTA VAZIO SE ESTIVER É COLOCADO NULL PARA NÃO AUTO COMPLETAR
            if ("".equals(cpf)) {
                this.cpfNulo = true;
                jFormattedTextFieldCpf.setValue(null);

            } else {
                //verifica se o CPF do funcionario é igual ou diferente o digitado no TextField
                if (!this.cpf.equals(jFormattedTextFieldCpf.getText())) {

                    //    verifica = verificaCpf(cpf);
                    verifica = verificaCampo.verificaCpf(cpf);
                    if (verifica == true) {
                      
                            //jLabelCPF.setText("CPF VALIDO");
                            
                            jLabelCNPJ.setText("");
                            jLabelCPF.setForeground(Color.green);
                            this.cpfValido = true;
                            this.cpfNulo = false;
                            
                            
                            
                        
                    } else if (verifica == false) {

                        jLabelCPF.setText("CPF invalido!");
                        jLabelCPF.setForeground(Color.red);
                        this.cpfValido = false;
                        this.cpfNulo = false;
                
                    }
                }
            }

            // SE O BOTÃO ADICIONAR FOR PRECIONADO É FEITO ESSE PROCESSO    
        } else if (jButtonCadastrar.isEnabled()) {
            
            //VERIFICA SE O CPF ESTA VAZIO SE ESTIVER É COLOCADO NULL PARA NÃO ALTO COMPLETAR
            if ("".equals(cpf)) {

                jFormattedTextFieldCpf.setValue(null);

            } else {
                verifica = verificaCampo.verificaCpf(cpf);

                if (verifica == true) {
                    try {
                        // jLabelCPF.setText("CPF VALIDO");
                        
                        jLabelCNPJ.setText("");
                        jLabelCPF.setForeground(Color.green);
                        this.cpfValido = true;
                        this.cpfNulo = false;
                        //VERIFICA CADASTRO
                        
                        ClienteDAO clienteDao = new ClienteDAO();
                        ClienteModel clienteModel = new ClienteModel();
                        
                        clienteModel = clienteDao.verificaCadastro(cpf, "cpf", "cadastro");
                        //if(clienteModel==null){
                                   
                        //}
                        
                        if(clienteModel == null){
                            int b = JOptionPane.showConfirmDialog(null, "Este cpf já esta cadastrado no sistema, porem, está desativado, deseja reativa-lo?", "Alerta!",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.INFORMATION_MESSAGE);
                            if(b==0){
                                ativaCliente(clienteModel);
                            }else if(b==1){
                                
                            }
                            
                        }
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JaExisteException ex) {
                        Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                } else if (verifica == false) {

                    jLabelCPF.setText("CPF invalido!");
                    jLabelCPF.setForeground(Color.red);
                    this.cpfValido = true;
                    this.cpfNulo = false;
                    
                }
            }
        }
    }//GEN-LAST:event_jFormattedTextFieldCpfFocusLost
    public void verificaCadastroDesativado(String sql, String tipo){
       
    
    }
    private void jFormattedTextFieldCnpjFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCnpjFocusLost
        boolean verifica;

        VerificaCampos verificaCampo = new VerificaCampos();
        String cnpj;

        cnpj = jFormattedTextFieldCnpj.getText();

        cnpj = limpaMascara(cnpj, "cnpj");

        //verifica se é alterar
        if (jButtonAlterar.isEnabled()) {
            //verifica se o cnpj está vazio
            if ("".equals(cnpj)) {

                //se estiver vazio adiciona nulo para não alto completar
                jFormattedTextFieldCnpj.setValue(null);

            } else {

                if (!this.cnpj.equals(jFormattedTextFieldCnpj.getText())) {

                    verifica = verificaCampo.verificaCNPJ(cnpj);

                    if (verifica == true) {

                        jLabelCNPJ.setText("");
                        jLabelCNPJ.setForeground(Color.green);

                        cnpjValido = true;

                    } else if (verifica == false) {

                        jLabelCNPJ.setText("CNPJ invalido!");
                        jLabelCNPJ.setForeground(Color.red);
                        cnpjValido = false;

                    }
                }
            }

            //TELA CADASTRAR Cliente
        } else if (jButtonCadastrar.isEnabled()) {

            if (!"".equals(cnpj)) {

                verifica = verificaCampo.verificaCNPJ(cnpj);

                if (verifica == true) {
                    jLabelCNPJ.setText("");
                    jLabelCNPJ.setForeground(Color.green);
                    cnpjValido = true;

                } else if (verifica == false) {

                    jLabelCNPJ.setText("CNPJ invalido!");
                    jLabelCNPJ.setForeground(Color.red);
                    cnpjValido = false;
                }

            } else {
                //se estiver vazio adiciona nulo para não alto completar
                jFormattedTextFieldCnpj.setValue(null);

            }

        }
    }//GEN-LAST:event_jFormattedTextFieldCnpjFocusLost

    private void jFormattedTextFieldTelefoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldTelefoneFocusLost
        String telefone;

        telefone = jFormattedTextFieldTelefone.getText();

        telefone = limpaMascara(telefone, "telefone");
        if ("".equals(telefone)) {
            jFormattedTextFieldTelefone.setValue(null);
        }

    }//GEN-LAST:event_jFormattedTextFieldTelefoneFocusLost

    private void jFormattedTextFieldCelularFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCelularFocusLost
        String celular;
        celular = jFormattedTextFieldCelular.getText();

        celular = limpaMascara(celular, "celular");
        if ("".equals(celular)) {
            jFormattedTextFieldCelular.setValue(null);
        }

    }//GEN-LAST:event_jFormattedTextFieldCelularFocusLost

    private void jButtonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarActionPerformed

        int index = jComboBoxTipoPesquisa.getSelectedIndex();

        String cpf, nome, cnpj;

        ClienteDAO clienteControl = new ClienteDAO();
        ClienteModel clienteModel;
        clienteModel = new ClienteModel();
        ArrayList<ClienteModel> listaCliente = new ArrayList<>();

        // verificar o tipo de pesquisa
        switch (index) {
            case 0: {

                try {

                    nome = jFormattedTextFieldPesquisar.getText();

                    listaCliente = clienteControl.pesquisarNomeEspecifico(nome);
                    atualizaTabelaClientes(listaCliente);

                } catch (SQLException ex) {
                    // JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
                } catch (NaoEncontradoException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }

            break;

            case 1:

                cpf = jFormattedTextFieldPesquisar.getText();
                cpf = limpaMascara(cpf, "cpf");
                 {
                    try {
                        clienteModel = clienteControl.pesquisar(cpf, "cpf");
                        atualizaTabelaCliente(clienteModel);

                    } catch (SQLException ex) {

                        JOptionPane.showMessageDialog(null, "CPF não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
                    } catch (NaoEncontradoException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }

                break;
            case 2:

                cnpj = jFormattedTextFieldPesquisar.getText();
                cnpj = limpaMascara(cnpj, "cnpj");
                 {
                    try {
                        clienteModel = clienteControl.pesquisar(cnpj, "cnpj");
                        atualizaTabelaCliente(clienteModel);
                    } catch (SQLException ex) {

                    } catch (NaoEncontradoException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                        try {

                            atualizaTabelaSParametro();
                        } catch (SQLException ex1) {
                            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (ParseException ex1) {
                            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (NaoEncontradoException ex1) {
                            JOptionPane.showMessageDialog(null, ex1.getMessage());
                        }
                    }
                }

                break;
        }

    }//GEN-LAST:event_jButtonPesquisarActionPerformed

    //serve para formatar e colocar as mascaras no pesquisar    
    private void jComboBoxTipoPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoPesquisaActionPerformed
        int index = jComboBoxTipoPesquisa.getSelectedIndex();

        MaskFormatter mascara = null;

        switch (index) {

            case 0: {
                try {
                    //MASCARCA DE CARACTERES STRING 

                    setaCampoPesquisa();

                } catch (ParseException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                }

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

            case 2: //CNPJ
            {
                try {

                    jFormattedTextFieldPesquisar.setValue(null);
                    mascara = new MaskFormatter("##.###.###/####-##");

                } catch (ParseException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                }

                jFormattedTextFieldPesquisar.setFormatterFactory(new DefaultFormatterFactory(mascara));
            }

            jLabelLegendaPesquisa.setText("CNPJ:");

            break;

        }

    }//GEN-LAST:event_jComboBoxTipoPesquisaActionPerformed

    private void jFormattedTextFieldPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldPesquisarKeyReleased
        int index = jComboBoxTipoPesquisa.getSelectedIndex();

        ClienteDAO fornecedorControl = new ClienteDAO();
        ClienteModel clienteModel = new ClienteModel();
        String nome, cnpj, cpf;

        MaskFormatter mascara = null;
        switch (index) {

            //NOME
            case 0: {

                ArrayList<ClienteModel> listaClientes = null;

                jLabelLegendaPesquisa.setText("Nome:");
                nome = jFormattedTextFieldPesquisar.getText().trim();

                if ("".equals(nome)) {

                    try {

                        listaClientes = fornecedorControl.findAll();
                        atualizaTabelaClientes(listaClientes);

                    } catch (ParseException ex) {
                        Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else if (!nome.equals("")) {
                    try {

                        listaClientes = fornecedorControl.pesquisarNome(nome);
                        atualizaTabelaClientes(listaClientes);

                    } catch (SQLException ex) {

                    } catch (NaoEncontradoException ex) {
                        try {
                            atualizaTabelaSParametro();
                        } catch (SQLException ex1) {
                            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (ParseException ex1) {
                            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (NaoEncontradoException ex1) {
                            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex1);
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

                        listaClientes = fornecedorControl.findAll();
                        atualizaTabelaClientes(listaClientes);

                    } catch (SQLException ex) {
                        Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else if (!cpf.equals("")) {

                    try {
                        clienteModel = fornecedorControl.pesquisar(cpf, "cpf");
                        atualizaTabelaCliente(clienteModel);
                    } catch (SQLException ex) {
                        try {
                            atualizaTabelaSParametro();
                        } catch (SQLException ex1) {
                            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (ParseException ex1) {
                            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (NaoEncontradoException ex1) {
                            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    } catch (NaoEncontradoException ex) {
                        try {
                            atualizaTabelaSParametro();
                        } catch (SQLException ex1) {
                            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (ParseException ex1) {
                            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (NaoEncontradoException ex1) {
                            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }

                }

            }

            break;

            case 2: //CNPJ
            {
                jLabelLegendaPesquisa.setText("CNPJ:");
                cnpj = jFormattedTextFieldPesquisar.getText();
                cnpj = limpaMascara(cnpj, "cnpj");

                if ("".equals(cnpj)) {

                    try {
                        listaClientes = fornecedorControl.findAll();
                        atualizaTabelaClientes(listaClientes);

                    } catch (SQLException ex) {
                        try {
                            atualizaTabelaSParametro();
                        } catch (SQLException ex1) {
                            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (ParseException ex1) {
                            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (NaoEncontradoException ex1) {
                            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    } catch (ParseException ex) {
                        Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else if (!cnpj.equals("")) {

                    try {
                        clienteModel = fornecedorControl.pesquisar(cnpj, "cnpj");
                        atualizaTabelaCliente(clienteModel);
                    } catch (SQLException ex) {

                        try {
                            atualizaTabelaSParametro();
                        } catch (SQLException ex1) {
                            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (ParseException ex1) {
                            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (NaoEncontradoException ex1) {
                            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex1);
                        }

                    } catch (NaoEncontradoException ex) {
                        try {
                            atualizaTabelaSParametro();
                        } catch (SQLException ex1) {
                            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (ParseException ex1) {
                            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (NaoEncontradoException ex1) {
                            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }

                }

                break;

            }
        }

    }//GEN-LAST:event_jFormattedTextFieldPesquisarKeyReleased

    private void jFormattedTextFieldPesquisarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldPesquisarFocusLost
        String stg = jFormattedTextFieldPesquisar.getText();
        if ("   .   .   -  ".equals(stg) || "  .   .   /    -  ".equals(stg)) {
            jFormattedTextFieldPesquisar.setValue(null);
        }
    }//GEN-LAST:event_jFormattedTextFieldPesquisarFocusLost

    private void jFormattedTextFieldCpfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCpfFocusGained
        jLabelCPF.setText("");

    }//GEN-LAST:event_jFormattedTextFieldCpfFocusGained

    private void jFormattedTextFieldCnpjFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCnpjFocusGained
        jLabelCNPJ.setText("");

    }//GEN-LAST:event_jFormattedTextFieldCnpjFocusGained

    private void jFormattedTextFieldCepFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCepFocusLost

        String cep;
        cep = jFormattedTextFieldCep.getText();

        cep = limpaMascara(cep, "cep");

        if ("".equals(cep)) {
            jFormattedTextFieldCep.setValue(null);
        }

    }//GEN-LAST:event_jFormattedTextFieldCepFocusLost

    private void jButtonConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultarActionPerformed

        String uf, tipo, cpf, cnpj, celular, telefone, cargo, dataString;
        ClienteDAO clienteControl = new ClienteDAO();
        ClienteModel clienteModel = new ClienteModel();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        // cria a string   
        
        int id, linha = -1;

        linha = jTableTabelaDeCliente.getSelectedRow();

        if (linha == -1) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta", JOptionPane.WARNING_MESSAGE);

        } else {

            cpf = (String) jTableTabelaDeCliente.getValueAt(linha, 1);
            cnpj = (String) jTableTabelaDeCliente.getValueAt(linha, 4);

            cpf = limpaMascara(cpf, "cpf");
            cnpj = limpaMascara(cnpj, "cnpj");

            this.cpf = cpf;
            this.cnpj = cnpj;

            try {
                clienteModel = clienteControl.pesquisar(cpf, "cpf");

                setaDialog(1, false, false, false, false, true, true, false, true, "Consultar");

                jPanelDataCadastro.setVisible(true);

                cnpj = clienteModel.getCnpj();
                cnpj = limpaMascara(cnpj, "cnpj");

                telefone = clienteModel.getTelefone();
                telefone = limpaMascara(telefone, "telefone");

                celular = clienteModel.getCelular();
                celular = limpaMascara(celular, "celular");

                if (!cnpj.equals("")) {
                    jFormattedTextFieldCnpj.setText(clienteModel.getCnpj());

                }
                if (!telefone.equals("")) {

                    jFormattedTextFieldTelefone.setText(clienteModel.getTelefone());

                }
                if (!celular.equals("")) {
                    jFormattedTextFieldCelular.setText(clienteModel.getCelular());
                } else {
                    jFormattedTextFieldCelular.setValue(false);
                }

                this.dataCadastro = clienteModel.getDataCadastro();

                jTextFieldNome.setText(clienteModel.getNome());

                jFormattedTextFieldCpf.setText(clienteModel.getCpf());

                jTextFieldRua.setText(clienteModel.getRua());

                jTextFieldNumero.setText(clienteModel.getNumero());

                jTextFieldBairro.setText(clienteModel.getBairro());

                jTextFieldCidade.setText(clienteModel.getCidade());

                jFormattedTextFieldCep.setText(clienteModel.getCep());

                dataString = formatador.format(clienteModel.getDataCadastro());

                jTextFieldDataCadastro.setText(dataString);

                this.idCliente = clienteModel.getId();

                uf = clienteModel.getUf();
                jComboBoxUF.setSelectedItem(uf);

                tipo = clienteModel.getTipo();

                ativaCampos(false);

            } catch (SQLException ex) {
                Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NaoEncontradoException ex) {
                Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    }//GEN-LAST:event_jButtonConsultarActionPerformed

    private void jFormattedTextFieldCepFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCepFocusGained

    }//GEN-LAST:event_jFormattedTextFieldCepFocusGained

    private void jFormattedTextFieldCelularFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCelularFocusGained

        String celular;
        celular = jFormattedTextFieldCelular.getText();

        celular = limpaMascara(celular, "celular");
        if ("".equals(celular)) {
            jFormattedTextFieldCelular.setValue(null);
        }

    }//GEN-LAST:event_jFormattedTextFieldCelularFocusGained

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

        if (jTextFieldRua.getText().length() >= qtdCaracteres) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldRuaKeyTyped

    private void jTextFieldBairroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBairroKeyTyped
        int qtdCaracteres = 70;

        if (jTextFieldBairro.getText().length() >= qtdCaracteres) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextFieldBairroKeyTyped

    private void jTextFieldCidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCidadeKeyTyped
        int qtdCaracteres = 70;

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

    private void jComboBoxUFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxUFKeyTyped

        int qtdCaracteres = 2;

        if (jComboBoxUF.getSelectedItem().toString().length() >= qtdCaracteres) {
            evt.consume();
        }

    }//GEN-LAST:event_jComboBoxUFKeyTyped

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        try {
            
            jButtonAlterar.setEnabled(true);
            jButtonCadastrar.setEnabled(true);
            jButtonExcluir.setEnabled(true);
            jButtonStatus.setEnabled(false);
            
            atualizaTabelaSParametro();
        } catch (SQLException ex) {
            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButtonStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStatusActionPerformed
       
         int resposta, id;

        resposta = JOptionPane.showConfirmDialog(null, "Deseja ativar cliente ?", "Confirma",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (resposta == 0) {

            int linha = -1;
            ArrayList<ClienteModel> listaClientes;
            ClienteDAO clienteControl = new ClienteDAO();
            ClienteModel cliente = new ClienteModel();
            linha = jTableTabelaDeCliente.getSelectedRow();

            if (linha == -1) {
                Toolkit.getDefaultToolkit().beep();
                JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta",JOptionPane.WARNING_MESSAGE);

            } else {
                
                try {
                    
                    String cpf = (String) jTableTabelaDeCliente.getValueAt(linha, 1);
                    cpf = limpaMascara(cpf, "cpf");
                    cliente = clienteControl.pesquisar(cpf, "cpf");
                    cliente.setStatus(true);
                    clienteControl.alterar(cliente);
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
            java.util.logging.Logger.getLogger(ManterClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManterClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManterClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManterClienteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ManterClienteView dialog = null;
                try {
                    dialog = new ManterClienteView(new javax.swing.JFrame(), true, "");
                } catch (SQLException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NaoEncontradoException ex) {
                    Logger.getLogger(ManterClienteView.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JButton jButton1;
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
    private javax.swing.JComboBox<String> jComboBoxUF;
    private javax.swing.JFormattedTextField jFormattedTextFieldCelular;
    private javax.swing.JFormattedTextField jFormattedTextFieldCep;
    private javax.swing.JFormattedTextField jFormattedTextFieldCnpj;
    private javax.swing.JFormattedTextField jFormattedTextFieldCpf;
    private javax.swing.JFormattedTextField jFormattedTextFieldPesquisar;
    private javax.swing.JFormattedTextField jFormattedTextFieldTelefone;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelCNPJ;
    private javax.swing.JLabel jLabelCPF;
    private javax.swing.JLabel jLabelLegendaPesquisa;
    private javax.swing.JList<String> jList1;
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
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelDataCadastro;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableTabelaDeCliente;
    private javax.swing.JTextField jTextFieldBairro;
    private javax.swing.JTextField jTextFieldCidade;
    private javax.swing.JTextField jTextFieldDataCadastro;
    private javax.swing.JTextField jTextFieldNome;
    private javax.swing.JTextField jTextFieldNumero;
    private javax.swing.JTextField jTextFieldRua;
    // End of variables declaration//GEN-END:variables
}
