/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Control.Exceptions.NaoEncontradoException;
import DAO.CompraDAO;
import DAO.EstoqueDAO;
import DAO.FuncionarioDAO;
import DAO.ItemCompraDAO;
import DAO.ProdutoDAO;
import Modelo.CompraModel;
import Modelo.EstoqueModel;
import Modelo.FornecedorModel;
import Modelo.FuncionarioModel;
import Modelo.ItemCompra;
import Modelo.ProdutoModel;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControleEstoqueView extends javax.swing.JFrame {

    int contadorAlto = 0, contadorMedio = 0, contadorBaixo = 0;
    String codigoBarras, valorTotalString;
    double valorTotal;
    boolean valida = false, modoAdicao = false;

    ArrayList<FornecedorModel> listaFornecedor;
    List<EstoqueModel> listaProdutosEstoque = new ArrayList<>();
    List<EstoqueModel> listaProdutosEstoqueAtual = new ArrayList<>();
    List<ItemCompra> listaItemCompra = new ArrayList<>(); // VEM DO BANCO 
    List<ItemCompra> listaItemCompraAtual = new ArrayList<>(); // PREECHIDO NA HORA

    ProdutoModel produtoModel;
    ItemCompra itemCompra = new ItemCompra();
    EstoqueModel estoqueModel;
    FuncionarioModel funcionario;
    FornecedorModel fornecedor;
    CompraModel compra = new CompraModel();
    PrincipalGerenteView pg;

    public ControleEstoqueView(PrincipalGerenteView pg) {
        initComponents();
        this.pg = pg;

        resetaCampos();

        jRadioButtonTodos.setSelected(true);
        jTextFieldNotaFiscal.requestFocus();
        jLabelLegendaPesquisa.setText("Código de Barras:");

        Timestamp data = new Timestamp(System.currentTimeMillis());
        data.toLocalDateTime();

        iniciaTabela();

        jTextFieldQuantidade.setDocument(new FormatacaoDinamicaQuantidade());
        //jTextFieldQuantidadeMinima.setDocument(new FormatacaoDinamicaQuantidade());
        //jTextFieldvalorUnitario.setDocument(new FormatacaoDinamicaDinheiro());
        jTextFieldProduto.setEditable(false);
        jTextFieldUnidadeMedida.setEditable(false);
        jTextFieldvalorUnitario.setEditable(false);

        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {

            public void eventDispatched(AWTEvent event) {
                KeyEvent ev = (KeyEvent) event;

                if (ev.getID() == KeyEvent.KEY_RELEASED) {

                    switch (ev.getKeyCode()) {
                        case KeyEvent.VK_F1: {
                            try {
                                preencheTabela();
                            } catch (SQLException ex) {
                                Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ParseException ex) {
                                Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;

                        case KeyEvent.VK_F2:
                            //removeTabela();
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
                        case 27:
                            sair();
                            break;
                        default:
                            break;
                    }
                    return;
                }
            }
        }, AWTEvent.KEY_EVENT_MASK);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jTextFieldPesquisar = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jComboBoxTipoPesquisa = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabelLegendaPesquisa = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButtonFechar = new javax.swing.JButton();
        jButtonQuantidadeMinima = new javax.swing.JButton();
        jButtonSalvar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonNovoProduto = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButtonQuantidadeMinima1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEstoqueProdutos = new javax.swing.JTable();
        jPanel23 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabelMedio = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jLabelBaixo = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        jLabelMedio1 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jRadioButtonTodos = new javax.swing.JRadioButton();
        jRadioButtonBaixo = new javax.swing.JRadioButton();
        jRadioButtonMedio = new javax.swing.JRadioButton();
        jPanel25 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jTextFieldProduto = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jTextFieldUnidadeMedida = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jTextFieldCodigoDeBarras = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jTextFieldQuantidadeMinima = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jTextFieldQuantidade = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jTextFieldvalorUnitario = new javax.swing.JTextField();
        jButtonAdicionar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabelNomeFornecedor = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jTextFieldNotaFiscal = new javax.swing.JTextField();
        jPanel24 = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButtonPesquisarDataCompra = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Controle de Estoque");
        setExtendedState(6);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(3, 133, 188));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Controle de Estoque");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Pesquisa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jTextFieldPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldPesquisarMouseClicked(evt);
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

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lupa20x20.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jComboBoxTipoPesquisa.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Produto" }));
        jComboBoxTipoPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTipoPesquisaActionPerformed(evt);
            }
        });

        jLabel2.setText("Tipo:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxTipoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(6, 6, 6)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabelLegendaPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTextFieldPesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabelLegendaPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTipoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButtonFechar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/VoltarSimples24x24.png"))); // NOI18N
        jButtonFechar.setText("Fechar");
        jButtonFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFecharActionPerformed(evt);
            }
        });

        jButtonQuantidadeMinima.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Atualizar24x24.png"))); // NOI18N
        jButtonQuantidadeMinima.setText("Alterar Qtde Minima");
        jButtonQuantidadeMinima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQuantidadeMinimaActionPerformed(evt);
            }
        });

        jButtonSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/salvar24x24.png"))); // NOI18N
        jButtonSalvar.setText("Salvar");
        jButtonSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarActionPerformed(evt);
            }
        });

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/voltar24x24.png"))); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonNovoProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/adicionar24x24.png"))); // NOI18N
        jButtonNovoProduto.setText("Adicionar Produto");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/excluir24x24.png"))); // NOI18N
        jButton1.setText("Remover ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonQuantidadeMinima1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Atualizar24x24.png"))); // NOI18N
        jButtonQuantidadeMinima1.setText("Alterar Qtde");
        jButtonQuantidadeMinima1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQuantidadeMinima1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonNovoProduto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSalvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonFechar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonQuantidadeMinima, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonQuantidadeMinima1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonNovoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonQuantidadeMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonQuantidadeMinima1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(jButtonFechar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tabela de Estoque", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jTableEstoqueProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo de Barras", "Produto", "Unidade Medida", "Estoque Minimo", "Estoque Atual", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableEstoqueProdutos);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Resumo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Médio estoque", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jPanel21.setBackground(new java.awt.Color(218, 218, 12));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 14, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jLabelMedio.setFont(new java.awt.Font("Dialog", 1, 28)); // NOI18N
        jLabelMedio.setText("0");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelMedio, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelMedio, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Baixo estoque", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jPanel22.setBackground(new java.awt.Color(204, 0, 0));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 14, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jLabelBaixo.setFont(new java.awt.Font("Dialog", 1, 28)); // NOI18N
        jLabelBaixo.setText("0");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelBaixo, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelBaixo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Estoque Total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jPanel26.setBackground(new java.awt.Color(51, 204, 0));

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 14, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jLabelMedio1.setFont(new java.awt.Font("Dialog", 1, 28)); // NOI18N
        jLabelMedio1.setText("0");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelMedio1, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelMedio1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Filtro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP), "Fitro Estoque Atual", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        buttonGroup1.add(jRadioButtonTodos);
        jRadioButtonTodos.setText("Todos");
        jRadioButtonTodos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButtonTodosItemStateChanged(evt);
            }
        });
        jRadioButtonTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTodosActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonBaixo);
        jRadioButtonBaixo.setText("Estoque Baixo");
        jRadioButtonBaixo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonBaixoActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonMedio);
        jRadioButtonMedio.setText("Estoque  Médio");
        jRadioButtonMedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMedioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jRadioButtonTodos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonBaixo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonMedio))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGap(0, 29, Short.MAX_VALUE)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButtonBaixo)
                    .addComponent(jRadioButtonMedio))
                .addGap(20, 20, 20))
        );

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Entrada Estoque", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldProduto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldProdutoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldProduto)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jTextFieldProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Unidade de Medida", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldUnidadeMedida, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jTextFieldUnidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Código de Barras", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldCodigoDeBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCodigoDeBarrasActionPerformed(evt);
            }
        });
        jTextFieldCodigoDeBarras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldCodigoDeBarrasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCodigoDeBarrasKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldCodigoDeBarras, javax.swing.GroupLayout.DEFAULT_SIZE, 171, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jTextFieldCodigoDeBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Quantidade Minima", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldQuantidadeMinima.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldQuantidadeMinimaFocusGained(evt);
            }
        });
        jTextFieldQuantidadeMinima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldQuantidadeMinimaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldQuantidadeMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jTextFieldQuantidadeMinima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 9, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Quantidade em Estoque", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldQuantidadeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Valor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldvalorUnitario, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jTextFieldvalorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonAdicionar.setBackground(new java.awt.Color(255, 255, 255));
        jButtonAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/adicionarSimples24x24Novo.png"))); // NOI18N
        jButtonAdicionar.setToolTipText("Adicionar Produto Estoque");
        jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 61, Short.MAX_VALUE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAdicionar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Descrição", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Fornecedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNomeFornecedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabelNomeFornecedor, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Nota Fiscal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldNotaFiscal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNotaFiscalActionPerformed(evt);
            }
        });
        jTextFieldNotaFiscal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldNotaFiscalKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldNotaFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Data da Compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jDateChooser1.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                jDateChooser1HierarchyChanged(evt);
            }
        });
        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButtonPesquisarDataCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lupa20x20.png"))); // NOI18N
        jButtonPesquisarDataCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarDataCompraActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addComponent(jButtonPesquisarDataCompra)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(10, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jButtonPesquisarDataCompra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void sair() {
        super.dispose();
    }

    public void salvar() throws NaoEncontradoException, SQLException, ParseException {

        EstoqueDAO estoqueDao = new EstoqueDAO();

        List<EstoqueModel> listaProdutosEstoqued = new ArrayList<>();
        listaProdutosEstoqued = this.listaProdutosEstoque.stream().distinct().collect(Collectors.toList());
        List<ItemCompra> listaItemCompra = new ArrayList<>();

        if (!listaProdutosEstoqued.isEmpty()) {

            for (EstoqueModel e : listaProdutosEstoqued) {

                e.setFuncionario(this.funcionario);

                estoqueDao.alteraQuantidade(e);

            }
        }

        for (ItemCompra i : this.listaItemCompra) {
            for (ItemCompra i2 : this.listaItemCompraAtual) {
                if (!i.equals(i2)) {
                    listaItemCompra.add(i2);
                    // System.out.println("STATUS DA COMPRA "+ i2.getDescricao() +" NOTA FISCAL "+i2.getNotaFiscal()+" STATUS "+i2.getStatus());
                }
            }

        }
        ItemCompraDAO itensCompraDao = new ItemCompraDAO();
        boolean verificaCompra = false;
        for (ItemCompra itemCompra : this.listaItemCompra) {
            if (itemCompra.getStatus() == false) {
                verificaCompra = true;
            }

            itemCompra.setNotaFiscal(jTextFieldNotaFiscal.getText());
            itensCompraDao.alterar(itemCompra);

        }

        if (verificaCompra == true) {
            CompraDAO compraDao = new CompraDAO();

            this.compra.setStatus("Requer Atenção");
            compraDao.alterarStatus(this.compra);
        }
        JOptionPane.showMessageDialog(null, "Manutenção realizada", "Sucesso!",JOptionPane.INFORMATION_MESSAGE);
        

    }

    public void resetaCampos() {

        jTextFieldCodigoDeBarras.setEnabled(false);
        jTextFieldProduto.setEnabled(false);
        jTextFieldQuantidade.setEnabled(false);
        jTextFieldQuantidadeMinima.setEnabled(false);
        jTextFieldvalorUnitario.setEnabled(false);
        jTextFieldUnidadeMedida.setEnabled(false);
        
        // jComboBoxFornecedor.setEnabled(false);
        jButtonAdicionar.setEnabled(false);
        //jButtonRemover.setEnabled(false);

        jButtonCancelar.setEnabled(false);
        jButtonSalvar.setEnabled(false);

        jButtonNovoProduto.setEnabled(true);
        jButtonQuantidadeMinima.setEnabled(true);
        jTextFieldNotaFiscal.setEnabled(true);
        jDateChooser1.setEnabled(true);
        limpaCampos();   
        

    }

    public void preencheCampoCompra(CompraModel compra) {
        try {

            this.fornecedor = compra.getFornecedor();
            this.compra = compra;
            jLabelNomeFornecedor.setText(compra.getFornecedor().getNome());
            ItemCompraDAO itemCompra = new ItemCompraDAO();

            this.listaItemCompra = itemCompra.pesquisarItemCompra(compra.getId());

        } catch (SQLException ex) {
            Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void ativaCampos() {

        jTextFieldCodigoDeBarras.setEnabled(true);
        jTextFieldProduto.setEnabled(true);
        jTextFieldQuantidade.setEnabled(true);
        jTextFieldQuantidadeMinima.setEnabled(true);
        jTextFieldvalorUnitario.setEnabled(true);
        jTextFieldUnidadeMedida.setEnabled(true);

        //  jComboBoxFornecedor.setEnabled(true);
        jButtonAdicionar.setEnabled(true);
       // jButtonRemover.setEnabled(true);

        jButtonCancelar.setEnabled(true);
        jButtonSalvar.setEnabled(true);

        jButtonNovoProduto.setEnabled(false);
        jButtonQuantidadeMinima.setEnabled(false);

    }

    public void limpaCampos() {

        //   boolean status = jComboBoxFornecedor.isEnabled();
        jTextFieldCodigoDeBarras.setText("");
        jTextFieldPesquisar.setText("");
        jTextFieldProduto.setText("");
        jTextFieldQuantidade.setText("");
        jTextFieldQuantidadeMinima.setText("");
        jTextFieldvalorUnitario.setText("");
        jTextFieldUnidadeMedida.setText("");
        
        if (modoAdicao == false) {
            jDateChooser1.setCalendar(null);
            jLabelNomeFornecedor.setText("");
            jTextFieldNotaFiscal.setText("");
        }

//        if (status == true) {
//            jComboBoxFornecedor.setSelectedIndex(0);
//        }
        jTextFieldNotaFiscal.requestFocus();

    }

    public void preencheTabela() throws SQLException, ParseException {

        //PREPARAÇÃO DE PARAMETROS E DADOS A SEREM INSERIDOS NO COMPRA / ESTOQUE
        //======================================================================
        String valorRecebidoString, numeroNota;
        double quantidadeEstoqueAtual = 0, valorDouble, quantidadeDouble, quantidadeMinimaDouble, quantidadeDoEstoque;
        int idEstoque = -1;
        boolean verifica = false;

        Timestamp data = new Timestamp(System.currentTimeMillis());
        data.toLocalDateTime();

        FuncionarioModel funcionario = new FuncionarioModel();
        quantidadeDoEstoque = this.estoqueModel.getQtdEstoque();        
        //UNIDADE DE MEDIDA
        if (jTextFieldUnidadeMedida.getText().equals("KG")) {

            
            quantidadeEstoqueAtual = this.estoqueModel.getQtdEstoque();
            //SE FOR KG ELE SEPARA OS PONTOS E VIRGULAS
            valorRecebidoString = jTextFieldQuantidade.getText().replace(".", "");
            valorRecebidoString = jTextFieldQuantidade.getText().replace(",", "");
            quantidadeDouble = Double.parseDouble(valorRecebidoString);

            valorRecebidoString = jTextFieldQuantidadeMinima.getText().replace(".", "");
            valorRecebidoString = jTextFieldQuantidadeMinima.getText().replace(",", "");
            quantidadeMinimaDouble = Double.parseDouble(valorRecebidoString);

            //CONVERTER A QUANTIDADE DIGITADA APÓS A REMOÇÃO DOS CARACTERES
            quantidadeDouble = quantidadeDouble / 1000;
            quantidadeDouble = quantidadeDoEstoque + quantidadeDouble;

            quantidadeMinimaDouble = quantidadeMinimaDouble / 1000;

        } else {
            // SE FOR UNID ELE NÃO SEPARA NADA POIS NAO TEM ,
            
            quantidadeDouble = Double.parseDouble(jTextFieldQuantidade.getText());
            quantidadeDouble = quantidadeDoEstoque + quantidadeDouble;
            quantidadeMinimaDouble = Double.parseDouble(jTextFieldQuantidadeMinima.getText());

        }
        //NUMERO NOTA FISCAL
        numeroNota = jTextFieldNotaFiscal.getText();
        this.codigoBarras = jTextFieldCodigoDeBarras.getText();
        // VALOR RECEBIDO, TBM SEPARA OS PONTOS VIRGULAS E SIGLAS, PARA PODER
        // FAZER A CONTA

        valorRecebidoString = jTextFieldvalorUnitario.getText();
        valorRecebidoString = valorRecebidoString.replace(",", ".");
        valorRecebidoString = valorRecebidoString.replace(".", "");
        valorRecebidoString = valorRecebidoString.replace("R$", "");
        valorDouble = Double.parseDouble(valorRecebidoString);
        valorDouble = valorDouble / 100;

        if (this.valida == true) {

            idEstoque = this.estoqueModel.getId();
            // int fornecedorId = jComboBoxFornecedor.getSelectedIndex();
            //   FornecedorModel fornecedorModel = fornecedorDao.findByID(jComboBoxFornecedor.getSelectedIndex());

            this.estoqueModel.setFuncionario(funcionario);
            this.estoqueModel.setQtdEstoque(quantidadeDouble);
            this.estoqueModel.setQtdMinima(quantidadeMinimaDouble);
            this.estoqueModel.setProduto(this.produtoModel);

            List<EstoqueModel> listaProdutosEstoqued = this.listaProdutosEstoque;

            int contador = 0;

            for (EstoqueModel e : listaProdutosEstoqued) {
                //SE O ID FOR IGUAL AO ID ESTOQUE
                //ID BANCO   ID ESTOQUE

                if (e.getId() == idEstoque) {

                    e = this.estoqueModel;
                    if (e.getProduto().getCodigoBarra().equals(this.listaProdutosEstoque.get(contador).getProduto().getCodigoBarra())) {
                        this.listaProdutosEstoque.set(contador, e);
                    }

                }
                contador++;
            }

            //adicionarItemCompra(data, this.produtoModel, notaFiscal, quantidadeDouble, jTextFieldUnidadeMedida.getText(),
            //        valorDouble, verifica, this.compra);
            atualizaTabela();
            limpaCampos();

            //SE FOR ELSE ELE NÃO ESTA CADASTRADO    
        } else {

            quantidadeDoEstoque = this.estoqueModel.getQtdEstoque();
            this.estoqueModel.setFuncionario(funcionario);
            this.estoqueModel.setQtdEstoque(quantidadeDouble);
            this.estoqueModel.setQtdMinima(quantidadeMinimaDouble);
            this.estoqueModel.setProduto(this.produtoModel);
            this.estoqueModel.setStatus(true);

            this.listaProdutosEstoqueAtual.add(this.estoqueModel);
            String notaFiscal = numeroNota;

            //adicionarItemCompra(data, this.produtoModel, notaFiscal, quantidadeDouble, jTextFieldUnidadeMedida.getText(),
            //      valorDouble, verifica, this.compra);
            //VERIFICA SE O PRODUTO DIGITADO ESTA NA LISTA CASO NAO ESTEJA ADICIONA
            atualizaTabela();
            limpaCampos();
        }

    }

    public void RemoveTabela() {

    }

    public void iniciaTabela() {
        EstoqueDAO estoqueDao = new EstoqueDAO();
        FuncionarioModel funcionario = new FuncionarioModel();
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();

        try {
            this.listaProdutosEstoque = estoqueDao.findAll();
            funcionario = funcionarioDao.pesquisarDataHoralogin();
            this.funcionario = funcionario;
            atualizaTabela();
        } catch (SQLException ex) {
            Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ItemCompra verificaItemCompraStatus(ProdutoModel produto, double quantidade, boolean status) {
        for (ItemCompra itemCompra : this.listaItemCompra) {

            if (itemCompra.getDescricao().equals(produto.getNome()) && itemCompra.getQuantidade() == quantidade) {
                status = true;
                this.itemCompra.setStatus(status);
                this.itemCompra.setQuantidade(quantidade);
                this.itemCompra.setQuantidadeVerificacao(itemCompra.getQuantidade());
                return this.itemCompra;
            }
        }
        return this.itemCompra;
    }

    public void atualizaTabela() throws SQLException, ParseException {

        DefaultTableModel val = (DefaultTableModel) jTableEstoqueProdutos.getModel();

        val.setNumRows(0); // excluir os registros que estão na JTable

        List<EstoqueModel> listaProdutosEstoque = new ArrayList<>();
        listaProdutosEstoque = this.listaProdutosEstoque;

        int cont = 0;

        if (!this.listaProdutosEstoqueAtual.isEmpty()) {

            for (EstoqueModel l : listaProdutosEstoque) {

                for (EstoqueModel l2 : this.listaProdutosEstoqueAtual) {

                    if (l.equals(l2)) {
                        listaProdutosEstoque.set(cont, l2);
                    }
                }
                cont++;
            }
        }

        listaProdutosEstoque.addAll(this.listaProdutosEstoqueAtual);
        List<EstoqueModel> produtosEstoque = new ArrayList<>();
        produtosEstoque = this.listaProdutosEstoque.stream().distinct().collect(Collectors.toList());

        if (jTableEstoqueProdutos != null) {

            produtosEstoque.forEach((EstoqueModel produtos) -> {

                String status;
                if (produtos.getStatus() == true) {

                    double quantidadeMedia = produtos.getQtdMinima();
                    double aux = 1;
                    quantidadeMedia = aux * quantidadeMedia;
                    quantidadeMedia = quantidadeMedia + quantidadeMedia;

                    //Quantidade Abaixo do ideal
                    if (produtos.getQtdEstoque() < produtos.getQtdMinima()) {
                        status = "Estoque Baixo";

                        this.contadorBaixo++;
                        jLabelBaixo.setText(this.contadorBaixo + "");
                    } else if (produtos.getQtdEstoque() >= produtos.getQtdMinima() && produtos.getQtdEstoque() <= quantidadeMedia) {
                        status = "Estoque Moderado";

                        this.contadorMedio++;
                        jLabelMedio.setText(this.contadorMedio + "");
                    } else {
                        status = "Estoque Cheio";

                        
                        // 
                    }
                    this.contadorAlto++;
                    jLabelMedio1.setText(this.contadorAlto+"");
                    if (produtos.getProduto().getUnidadeMedida().equals("KG")) {

                        String quantidadeString, quantidadeMString;
                        NumberFormat formatter = new DecimalFormat("#0.000");

                        double quantidade = produtos.getQtdEstoque();
                        double quantidadeMinima = produtos.getQtdMinima();

                        quantidadeMString = formatter.format(quantidadeMinima).replace(".", ",");
                        quantidadeString = formatter.format(quantidade).replace(".", ",");

                        val.addRow(new Object[]{produtos.getProduto().getCodigoBarra(), produtos.getProduto().getNome(), produtos.getProduto().getUnidadeMedida(), quantidadeMString, quantidadeString, status});

                    } else {
                        
                        String quantidadeString, quantidadeMString;
                        
                        double quantidade = produtos.getQtdEstoque();
                        double quantidadeMinima = produtos.getQtdMinima();

                        NumberFormat formatter = new DecimalFormat("#00"); 
                        quantidadeString = formatter.format(quantidade).replace(".", ",");
                        quantidadeMString = formatter.format(quantidadeMinima).replace(".", ",");
                        
                        val.addRow(new Object[]{produtos.getProduto().getCodigoBarra(), produtos.getProduto().getNome(), produtos.getProduto().getUnidadeMedida(), quantidadeMString, quantidadeString, status});
                    }

                }

            });

            this.contadorBaixo = 0;
            this.contadorMedio = 0;
            this.contadorAlto = 0;
         
        }
        this.listaProdutosEstoque = produtosEstoque;

    }


    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarActionPerformed
        boolean verifica = false;
        String quantidadeString;
        if (jLabelNomeFornecedor.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o fornecedor", "Alerta", JOptionPane.WARNING_MESSAGE);
        } else {
            //server para verificar se o item adicionado no estoque está no item compra 
            for (ItemCompra i : this.listaItemCompra) {
                if (i.getProduto().getCodigoBarra().equals(jTextFieldCodigoDeBarras.getText())) {
                    verifica = true;
                }
            }

            //verifica se o item adicionado tem a mesma quantidade da lista de compra
            for (ItemCompra i : this.listaItemCompra) {
                NumberFormat df = new DecimalFormat("#0.000");
                if("KG".equals(estoqueModel.getProduto().getUnidadeMedida())){
                quantidadeString = df.format(i.getQuantidade()).replace(".", ",");
                }else{
                 NumberFormat dff = new DecimalFormat("#0");    
                quantidadeString = dff.format(i.getQuantidade()).replace(".", "").replace(",","");
                }
                //System.err.println("QUANTIDADE DA COMPRA "+quantidadeString +" QUANTIDADE DIGITADA"+ jTextFieldQuantidade.getText());
                if (quantidadeString.equals(jTextFieldQuantidade.getText()) && i.getProduto().getCodigoBarra().equals(jTextFieldCodigoDeBarras.getText())) {
                    
                    i.setStatus(true);
                    i.setQuantidadeVerificacao(i.getQuantidade());
                }
            }
            double quantidade;
            //Insere a quantidade digitada no campo quantidade de verificação caso 
            // a quantidade inserida for diferente da quantidade da compra; 
            for (ItemCompra i : this.listaItemCompra) {

                if (i.getStatus() == false) {

                    quantidadeString = jTextFieldQuantidade.getText();
                    quantidadeString = quantidadeString.replace(",", "");
                    quantidade = Double.parseDouble(quantidadeString);

                    if ("KG".equals(estoqueModel.getProduto().getUnidadeMedida())) {
                        quantidade = quantidade / 1000;
                    }
                    
                    i.setQuantidadeVerificacao(quantidade);
                }
            }

            if (verifica == true) {
                jDateChooser1.setEnabled(false);
                jButtonPesquisarDataCompra.setEnabled(false);
                jTextFieldNotaFiscal.setEnabled(false);

                try {
                    //PEGA A TABELA DO BANCO,
                    //ADICIONA O ITEM NO ARRAY LIST

                    preencheTabela();
                } catch (SQLException ex) {
                    Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Este produto não está na lista de compra");
            }
        }

    }//GEN-LAST:event_jButtonAdicionarActionPerformed

    public void atualizaTabelaProdutos(ArrayList<EstoqueModel> listaProdutos) {

        Locale localBrasil = new Locale("pt", "BR");
        DefaultTableModel val = (DefaultTableModel) jTableEstoqueProdutos.getModel();
        val.setNumRows(0); // excluir os registros que estão na JTable

        if (jTableEstoqueProdutos != null) {

            listaProdutos.forEach((EstoqueModel produtos) -> {
                //Só vai aparecer os produtos ativados
                if (produtos.getStatus() == true) {

                    String status;

                    double quantidadeMedia = produtos.getQtdMinima();
                    double aux = 1;
                    quantidadeMedia = aux * quantidadeMedia;
                    quantidadeMedia = quantidadeMedia + quantidadeMedia;

                    //Quantidade Abaixo do ideal
                    if (produtos.getQtdEstoque() < produtos.getQtdMinima()) {
                        status = "Estoque Baixo";

                        this.contadorBaixo++;
                        jLabelBaixo.setText(this.contadorBaixo + "");
                    } else if (produtos.getQtdEstoque() >= produtos.getQtdMinima() && produtos.getQtdEstoque() <= quantidadeMedia) {
                        status = "Estoque Moderado";

                        this.contadorMedio++;
                        jLabelMedio.setText(this.contadorMedio + "");
                    } else {
                        status = "Estoque Cheio";

                        //this.contadorAlto++;
                        // jLabelAlto.setText(this.contadorAlto+"");
                    }
                   
                    this.contadorAlto++;
                    jLabelMedio1.setText(this.contadorAlto+"");
                    
                    double quantidades = produtos.getQtdEstoque();
                    if (produtos.getProduto().getUnidadeMedida().equals("KG")) {

                    } else {

                        int quantidadeInt = (int) quantidades;
                        String quantidadeStrings = String.valueOf(quantidadeInt);

                    }

                    String quantidadeString, quantidadeMString;
                    NumberFormat df = new DecimalFormat("#0.000");
                    quantidadeString = df.format(quantidades).replace(".", ",");

                    quantidadeMString = df.format(produtos.getQtdMinima()).replace(".", ",");

                    val.addRow(new Object[]{produtos.getProduto().getCodigoBarra(), produtos.getProduto().getNome(), produtos.getProduto().getUnidadeMedida(), quantidadeMString, quantidadeString, status});

                }
            });
            this.contadorBaixo = 0;
            this.contadorMedio = 0;
            this.contadorAlto = 0;
        }
    }


    private void jTextFieldNotaFiscalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNotaFiscalActionPerformed

        jTextFieldCodigoDeBarras.requestFocus();
    }//GEN-LAST:event_jTextFieldNotaFiscalActionPerformed

    private void jTextFieldNotaFiscalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNotaFiscalKeyReleased

        modoAdicao = true;
        ativaCampos();
    }//GEN-LAST:event_jTextFieldNotaFiscalKeyReleased

    private void jTextFieldCodigoDeBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodigoDeBarrasActionPerformed
        double quantidadeMinimaDouble;

        Locale localBrasil = new Locale("pt", "BR");

        ProdutoDAO produtoDao = new ProdutoDAO();
        EstoqueDAO estoqueDao = new EstoqueDAO();

        String quantidadeMinima;

        this.codigoBarras = jTextFieldCodigoDeBarras.getText();

        try {
            this.produtoModel = produtoDao.pesquisarCodigoBarras(this.codigoBarras);

        } catch (SQLException ex) {
            Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
        }

        //SETA OS CAMPOS COM AS MASCARAS DE QUANTIDADE POR KG OU UNIDADE
        if (this.produtoModel.getUnidadeMedida().equals("KG")) {
            //jTextFieldQuantidadeMinima.setDocument(new FormatacaoDinamicaQuantidade());
            jTextFieldQuantidade.setDocument(new FormatacaoDinamicaQuantidade());
            jTextFieldQuantidade.setText("00");
            // jTextFieldQuantidadeMinima.setText("00");
        } else {
            
            jTextFieldQuantidadeMinima.setDocument(new FormatacaoDinamicaQuantidadeUnid());
            jTextFieldQuantidade.setDocument(new FormatacaoDinamicaQuantidadeUnid());
            jTextFieldQuantidade.setText("0");
            jTextFieldQuantidadeMinima.setText("0");
            
        }

        try {

            //SE O PRODUTO ESTIVER CADASTRADO NO ESTOQUE
            String valor = NumberFormat.getCurrencyInstance(localBrasil).format(this.produtoModel.getValor());

            jTextFieldProduto.setText(this.produtoModel.getNome());
            jTextFieldUnidadeMedida.setText(this.produtoModel.getUnidadeMedida());
            jTextFieldvalorUnitario.setText(valor);
            jTextFieldQuantidade.setEditable(true);
            jTextFieldQuantidade.requestFocus();

            this.estoqueModel = estoqueDao.pesquisaProdutoEstoque(this.produtoModel.getCodigoBarra());

            //System.out.println("QUANTIDADE DO PRODUTO : "+this.estoqueModel.getQtdEstoque());
            this.valida = estoqueModel.getStatus();
           
        } catch (SQLException ex) {
            Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
        }

        //CADASTRADO NO ESTOQUE
        if (this.valida == true) {

            quantidadeMinimaDouble = this.estoqueModel.getQtdMinima();
            //quantidadeMinimaDouble = quantidadeMinimaDouble * 1000;
            if("KG".equals(this.estoqueModel.getProduto().getUnidadeMedida())){
            NumberFormat df = new DecimalFormat("#0.000");
            quantidadeMinima = df.format(quantidadeMinimaDouble).replace(".", ",");
            }else{
            NumberFormat df = new DecimalFormat("#00");
            quantidadeMinima = df.format(quantidadeMinimaDouble).replace(".", "").replace(",", "");
            
            }
            jTextFieldQuantidadeMinima.setDocument(new FormatacaoDinamicaReset());
            jTextFieldQuantidadeMinima.setText(quantidadeMinima);
            jTextFieldQuantidadeMinima.setEditable(false);

        } else {
            //NAO CADASTRADO NO ESTOQUE
            if("KG".equals(this.estoqueModel.getProduto().getUnidadeMedida())){
            jTextFieldQuantidadeMinima.setDocument(new FormatacaoDinamicaQuantidade());
            jTextFieldQuantidadeMinima.setEditable(true);
            }else{
            jTextFieldQuantidadeMinima.setDocument(new FormatacaoDinamicaReset());
            jTextFieldQuantidadeMinima.setEditable(true);
            }
            jTextFieldQuantidadeMinima.setText("0");

        }
    }//GEN-LAST:event_jTextFieldCodigoDeBarrasActionPerformed

    private void jTextFieldCodigoDeBarrasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCodigoDeBarrasKeyPressed

    }//GEN-LAST:event_jTextFieldCodigoDeBarrasKeyPressed

    private void jTextFieldCodigoDeBarrasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCodigoDeBarrasKeyReleased

    }//GEN-LAST:event_jTextFieldCodigoDeBarrasKeyReleased

    private void jTextFieldProdutoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldProdutoKeyReleased

    }//GEN-LAST:event_jTextFieldProdutoKeyReleased

    private void jTextFieldQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldQuantidadeActionPerformed
        String minima = jTextFieldQuantidadeMinima.getText();

        if ("".equals(minima)) {
            jTextFieldQuantidadeMinima.requestFocus();
        } else if (!"".equals(minima)) {
            jButtonAdicionar.requestFocus();
        }

    }//GEN-LAST:event_jTextFieldQuantidadeActionPerformed

    private void jTextFieldQuantidadeMinimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldQuantidadeMinimaActionPerformed
        jButtonAdicionar.requestFocus();
    }//GEN-LAST:event_jTextFieldQuantidadeMinimaActionPerformed

    private void jComboBoxTipoPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTipoPesquisaActionPerformed
        int index = jComboBoxTipoPesquisa.getSelectedIndex();

        String caractere;

        switch (index) {

            case 0: {

                jLabelLegendaPesquisa.setText("Código de barras:");
                jTextFieldPesquisar.setText("");
                try {
                    jRadioButtonTodos.setSelected(true);
                    atualizaTabela();
                } catch (SQLException ex) {
                    Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            break;

            case 1: //Codigo
            {

                jLabelLegendaPesquisa.setText("Produto:");
                jTextFieldPesquisar.setText("");

                try {

                    jRadioButtonTodos.setSelected(true);
                    atualizaTabela();
                } catch (SQLException ex) {
                    Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            break;

        }
    }//GEN-LAST:event_jComboBoxTipoPesquisaActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        
        this.listaFornecedor = new ArrayList<>();
        this.listaItemCompra = new ArrayList<>();
        this.listaProdutosEstoque = new ArrayList<>();
        this.listaProdutosEstoqueAtual = new ArrayList<>();
        // atualizaTabela();
        modoAdicao = false;

        resetaCampos();
        iniciaTabela();
        
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarActionPerformed

        try {

            salvar();
            modoAdicao = false;
            resetaCampos();
            iniciaTabela();
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonSalvarActionPerformed

    private void jButtonFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharActionPerformed

        if (modoAdicao == true) {
            int b = JOptionPane.showConfirmDialog(null, "As alterações não foram salvas deseja continuar?", "Alerta",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (b == 0) {
                super.dispose();
            }
        } else {
            super.dispose();
        }
    }//GEN-LAST:event_jButtonFecharActionPerformed

    private void jButtonQuantidadeMinimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQuantidadeMinimaActionPerformed
        int linha = -1;

        linha = jTableEstoqueProdutos.getSelectedRow();
        if (linha == -1) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta", JOptionPane.WARNING_MESSAGE);

        } else {

            String codigoBarras = (String) jTableEstoqueProdutos.getValueAt(linha, 0);
            EstoqueModel estoque = new EstoqueModel();
            EstoqueDAO estoqueDao = new EstoqueDAO();
            try {
                estoque = estoqueDao.pesquisaEstoqueCodigoBarras(codigoBarras);
                AlterarQtdeMinimaEstoqueView alterarQtdeMinima = new AlterarQtdeMinimaEstoqueView(null, true, estoque, this,"qtdMinima");
                alterarQtdeMinima.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NaoEncontradoException ex) {
                Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
            }

        }


    }//GEN-LAST:event_jButtonQuantidadeMinimaActionPerformed
    
    
    
    private void jRadioButtonTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTodosActionPerformed
        try {
            atualizaTabela();
        } catch (SQLException ex) {
            Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jRadioButtonTodosActionPerformed

    private void jRadioButtonBaixoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonBaixoActionPerformed
        DefaultTableModel val = (DefaultTableModel) jTableEstoqueProdutos.getModel();
        val.setNumRows(0);
        listaProdutosEstoque.forEach((EstoqueModel produtos) -> {
            String status = "Estoque Baixo";
            this.contadorBaixo++;
            double quantidades = produtos.getQtdEstoque();
            if (produtos.getProduto().getUnidadeMedida().equals("KG")) {

            } else {

                int quantidadeInt = (int) quantidades;
                String quantidadeStrings = String.valueOf(quantidadeInt);

            }

            String quantidadeString, quantidadeMString;
            NumberFormat df = new DecimalFormat("#0.000");
            quantidadeString = df.format(quantidades).replace(".", ",");

            quantidadeMString = df.format(produtos.getQtdMinima()).replace(".", ",");
            if (produtos.getQtdEstoque() < produtos.getQtdMinima()) {
                if (produtos.getStatus() == true) {
                    val.addRow(new Object[]{produtos.getProduto().getCodigoBarra(), produtos.getProduto().getNome(), produtos.getProduto().getUnidadeMedida(), quantidadeMString, quantidadeString, status});
                }
            }
        });
        this.contadorBaixo = 0;
    }//GEN-LAST:event_jRadioButtonBaixoActionPerformed

    private void jRadioButtonMedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMedioActionPerformed
        DefaultTableModel val = (DefaultTableModel) jTableEstoqueProdutos.getModel();
        val.setNumRows(0);

        listaProdutosEstoque.forEach((EstoqueModel produtos) -> {

            double quantidadeMedia = produtos.getQtdMinima();
            double aux = 1;
            quantidadeMedia = aux * quantidadeMedia;
            quantidadeMedia = quantidadeMedia + quantidadeMedia;

            String status = "Estoque Moderado";
            this.contadorMedio++;
            double quantidades = produtos.getQtdEstoque();
            if (produtos.getProduto().getUnidadeMedida().equals("KG")) {

            } else {

                int quantidadeInt = (int) quantidades;
                String quantidadeStrings = String.valueOf(quantidadeInt);

            }

            String quantidadeString, quantidadeMString;
            NumberFormat df = new DecimalFormat("#0.000");
            quantidadeString = df.format(quantidades).replace(".", ",");

            quantidadeMString = df.format(produtos.getQtdMinima()).replace(".", ",");
            if (produtos.getQtdEstoque() >= produtos.getQtdMinima() && produtos.getQtdEstoque() <= quantidadeMedia) {
                if (produtos.getStatus() == true) {
                    val.addRow(new Object[]{produtos.getProduto().getCodigoBarra(), produtos.getProduto().getNome(), produtos.getProduto().getUnidadeMedida(), quantidadeMString, quantidadeString, status});
                }
            }
        });
        this.contadorMedio = 0;
    }//GEN-LAST:event_jRadioButtonMedioActionPerformed

    private void jTextFieldPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisarKeyReleased

        int index = jComboBoxTipoPesquisa.getSelectedIndex(), codigoInt;
        EstoqueDAO produtoControl = new EstoqueDAO();

        ArrayList<EstoqueModel> listProdutos = new ArrayList<>();
        String nome, codigoDeBarras;

        switch (index) {

            case 0: {//Codigo
                codigoDeBarras = jTextFieldPesquisar.getText().trim();

                if (codigoDeBarras.equals("")) {

                    try {

                        listProdutos = produtoControl.findAll();
                        atualizaTabela();

                    } catch (SQLException ex) {
                        Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(ManterProdutoView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else if (!codigoDeBarras.equals(" ")) {

                    try {

                        listProdutos = produtoControl.pesquisaEstoqueCodigoBarrasLIKE(codigoDeBarras);
                        atualizaTabelaProdutos(listProdutos);

                    } catch (SQLException ex) {

                        try {
                            atualizaTabela();
                        } catch (SQLException ex1) {
                            Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (ParseException ex1) {
                            Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex1);
                        }

                    } catch (NaoEncontradoException ex) {

                        try {
                            atualizaTabela();
                        } catch (SQLException ex1) {
                            Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex1);
                        } catch (ParseException ex1) {
                            Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex1);
                        }

                    } catch (ParseException ex) {
                        Logger.getLogger(AdicionarProdutoVendaView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }

            break;

            case 1: //Produto
            {

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

                    }

                }
            }
        }


    }//GEN-LAST:event_jTextFieldPesquisarKeyReleased

    private void jTextFieldPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldPesquisarMouseClicked
        try {
            jRadioButtonTodos.setSelected(true);
            atualizaTabela();
        } catch (SQLException ex) {
            Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTextFieldPesquisarMouseClicked

    private void jRadioButtonTodosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButtonTodosItemStateChanged

    }//GEN-LAST:event_jRadioButtonTodosItemStateChanged

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        int index = jComboBoxTipoPesquisa.getSelectedIndex(), codigoInt;

        String nome, codigo;

        EstoqueDAO produtoControl = new EstoqueDAO();
        EstoqueModel produtoModel = new EstoqueModel();
        ArrayList<EstoqueModel> listaProdutos = new ArrayList<>();

        switch (index) {

            case 0: {//Codigo
                codigo = jTextFieldPesquisar.getText();
                codigoInt = Integer.parseInt(codigo);

                try {

                    produtoModel = produtoControl.findByID(codigoInt);
                    ArrayList<EstoqueModel> listaProdutss = new ArrayList<>();
                    listaProdutss.add(produtoModel);
                    atualizaTabelaProdutos(listaProdutss);

                } catch (SQLException ex) {

                    Logger.getLogger(ManterFornecedorView.class.getName()).log(Level.SEVERE, null, ex);

                } catch (NaoEncontradoException ex) {

                    JOptionPane.showMessageDialog(null, ex.getMessage());

                } catch (ParseException ex) {
                    Logger.getLogger(AdicionarProdutoVendaView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            break;

            case 1: //Nome
            {

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

        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextFieldPesquisarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPesquisarKeyTyped
        int qtdCaracteres = 100;

        if (jTextFieldPesquisar.getText().length() >= qtdCaracteres) {
            evt.consume();
        }

        if (jComboBoxTipoPesquisa.getSelectedIndex() == 0) {

            char c = evt.getKeyChar();

            if (!Character.isDigit(c)) {
                evt.consume();
            }

        }
    }//GEN-LAST:event_jTextFieldPesquisarKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int resposta, id;

        resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir o Produto ?");
        if (resposta == 0) {

            int linha = -1;
            ArrayList<EstoqueModel> listaProdutos;
            EstoqueDAO estoqueDao = new EstoqueDAO();
            EstoqueModel estoque = new EstoqueModel();
            linha = jTableEstoqueProdutos.getSelectedRow();

            if (linha == -1) {

                JOptionPane.showMessageDialog(null, "Selecione um campo na tabela");

            } else {

                String codigoBarras = (String) jTableEstoqueProdutos.getValueAt(linha, 0);

                try {
                    //excluir
                    estoque = estoqueDao.pesquisaEstoqueCodigoBarras(codigoBarras);
                    estoque.setQtdEstoque(0.000);
                    estoque.setQtdMinima(0.000);
                    estoque.setStatus(false);
                    estoque.setFuncionario(funcionario);
                    estoqueDao.alteraQuantidade(estoque);
                    listaProdutosEstoque = estoqueDao.findAll();
                    atualizaTabela();
                    this.pg.atualizaTabela();
                } catch (SQLException ex) {
                    Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NaoEncontradoException ex) {
                    Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jDateChooser1HierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jDateChooser1HierarchyChanged

    }//GEN-LAST:event_jDateChooser1HierarchyChanged

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange

        CompraModel compra = new CompraModel();
        CompraDAO compraDao = new CompraDAO();
        ArrayList<CompraModel> listaCompra = new ArrayList<>();

        if (jDateChooser1.getDate() != null) {
            SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd");
            String theDate = dateTimeFormat.format(jDateChooser1.getDate());

            try {

                listaCompra = compraDao.findAll();

                if (listaCompra.size() > 1) {
                    if (jLabelNomeFornecedor.getText().equals("")) {
                        AdicionarCompraEstoque adicionar = new AdicionarCompraEstoque(null, true, this);
                        adicionar.setVisible(true);
                    }
                } else {

                    for (CompraModel l : listaCompra) {
                        // VERIFICA SE A COMPRA JA FOI FINALIZADA
                        if (l.getStatus().equals("Nao Finalizado")) {
                            jLabelNomeFornecedor.setText(l.getFornecedor().getNome());
                            this.fornecedor = l.getFornecedor();
                            this.compra = l;
                        }
                    }
                    preencheCampoCompra(this.compra);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NaoEncontradoException ex) {
                Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void jButtonPesquisarDataCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarDataCompraActionPerformed
        AdicionarCompraEstoque ace = new AdicionarCompraEstoque(null, true, this);
        ace.setVisible(true);
    }//GEN-LAST:event_jButtonPesquisarDataCompraActionPerformed

    private void jTextFieldQuantidadeMinimaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldQuantidadeMinimaFocusGained

    }//GEN-LAST:event_jTextFieldQuantidadeMinimaFocusGained

    private void jButtonQuantidadeMinima1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQuantidadeMinima1ActionPerformed
        int linha = -1;

        linha = jTableEstoqueProdutos.getSelectedRow();
        if (linha == -1) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta", JOptionPane.WARNING_MESSAGE);

        } else {

            String codigoBarras = (String) jTableEstoqueProdutos.getValueAt(linha, 0);
            EstoqueModel estoque = new EstoqueModel();
            EstoqueDAO estoqueDao = new EstoqueDAO();
            try {
                estoque = estoqueDao.pesquisaEstoqueCodigoBarras(codigoBarras);
                AlterarQtdeMinimaEstoqueView alterarQtdeMinima = new AlterarQtdeMinimaEstoqueView(null, true, estoque, this, "qtd");
                alterarQtdeMinima.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NaoEncontradoException ex) {
                Logger.getLogger(ControleEstoqueView.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jButtonQuantidadeMinima1ActionPerformed

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
            java.util.logging.Logger.getLogger(ControleEstoqueView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ControleEstoqueView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ControleEstoqueView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ControleEstoqueView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ControleEstoqueView(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonFechar;
    private javax.swing.JButton jButtonNovoProduto;
    private javax.swing.JButton jButtonPesquisarDataCompra;
    private javax.swing.JButton jButtonQuantidadeMinima;
    private javax.swing.JButton jButtonQuantidadeMinima1;
    private javax.swing.JButton jButtonSalvar;
    private javax.swing.JComboBox<String> jComboBoxTipoPesquisa;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelBaixo;
    private javax.swing.JLabel jLabelLegendaPesquisa;
    private javax.swing.JLabel jLabelMedio;
    private javax.swing.JLabel jLabelMedio1;
    private javax.swing.JLabel jLabelNomeFornecedor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
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
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButtonBaixo;
    private javax.swing.JRadioButton jRadioButtonMedio;
    private javax.swing.JRadioButton jRadioButtonTodos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEstoqueProdutos;
    private javax.swing.JTextField jTextFieldCodigoDeBarras;
    private javax.swing.JTextField jTextFieldNotaFiscal;
    private javax.swing.JTextField jTextFieldPesquisar;
    private javax.swing.JTextField jTextFieldProduto;
    private javax.swing.JTextField jTextFieldQuantidade;
    private javax.swing.JTextField jTextFieldQuantidadeMinima;
    private javax.swing.JTextField jTextFieldUnidadeMedida;
    private javax.swing.JTextField jTextFieldvalorUnitario;
    // End of variables declaration//GEN-END:variables
}
