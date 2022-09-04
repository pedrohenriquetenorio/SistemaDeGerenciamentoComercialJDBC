/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Control.Exceptions.NaoEncontradoException;
import DAO.CaixaDAO;
import DAO.CompraDAO;
import DAO.EstoqueDAO;
import DAO.FornecedorDAO;
import DAO.FuncionarioDAO;
import DAO.ItemCompraDAO;
import DAO.ProdutoDAO;
import Modelo.FornecedorModel;
import Modelo.CaixaModel;
import Modelo.CompraModel;
import Modelo.FuncionarioModel;
import Modelo.ItemCompra;
import Modelo.ProdutoModel;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import Visao.FormataStringParaData;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.sql.Time;
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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class CompraView extends javax.swing.JDialog {
    
 ArrayList<FornecedorModel>listaFornecedor = new ArrayList<>();
 
    FornecedorModel fornecedor;    
    ProdutoModel produtoModel = new ProdutoModel();
    
    List<ItemCompra> itensCompra = new ArrayList<>();
    List<ItemCompra> itensCompra3 = new ArrayList<>();
    List<ItemCompra> itensCompraAtual = new ArrayList<>();  
    
    String codigoBarras, valorTotalString;
    double valorTotal;
    
    
    
    public CompraView(java.awt.Frame parent, boolean modal) {
        
        super(parent, modal);
     try {
         initComponents();
         this.valorTotal = 0;
         Locale localBrasil = new Locale("pt", "BR");
       
         jLabelLegendaPesquisa.setText("Código:");
         jPanelDataChooser.setVisible(false);
         jTextField1.setVisible(true);
         jTextFieldProduto.setEditable(false);
         jTextFieldQuantidade.setEditable(false);
         jTextFieldUnidadeMedida.setEditable(false);
         jTextFieldValorUnitario.setEditable(false);
         jButtonAdicionar.setEnabled(false);
         jButtonRemover.setEnabled(false);
         jButtonExcluir.setEnabled(false);
         jButtonSalva.setEnabled(false);
         jButtonPesquisarFornecedor.setEnabled(false);
   
         jButtonCancelar.setEnabled(false);
         jTabbedPane1.setEnabledAt(1, false);
         jTabbedPane1.setEnabledAt(0, true);
         jButtonNovaCompra.setEnabled(true);
         
         
         Timestamp data = new Timestamp(System.currentTimeMillis());
         data.toLocalDateTime();
       
         
         Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
             
             public void eventDispatched(AWTEvent event) {
                 KeyEvent ev = (KeyEvent) event;
                 
                 if (ev.getID() == KeyEvent.KEY_RELEASED) {
                     
                     switch (ev.getKeyCode()) {
                         case KeyEvent.VK_F1:
                            novaCompra();
                             break;
                         case KeyEvent.VK_F2:
                            telaHistorico();
                             break;
                         case KeyEvent.VK_F3:
                            salvar();
                             break;
                         case KeyEvent.VK_F4:
                            excluir();
                             break;
                         case KeyEvent.VK_F5:
                             cancelar();
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
         
         
            jTableCompraHistorico.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    finalizar1();
                } 
            }
        });
                 
                 
         
         jTextFieldProduto.setEditable(false);
         jTextFieldUnidadeMedida.setEditable(false);
         jTextFieldQuantidade.setDocument(new FormatacaoDinamicaQuantidade());
         jTextFieldValorUnitario.setDocument(new FormatacaoDinamicaDinheiro());
         atualizaTabelaHistoricoProdutoCompra();
         String valor= NumberFormat.getCurrencyInstance(localBrasil).format(this.valorTotal);
       
         jLabelValorTotal.setText(valor);
//atualizaTabela();
         
        } catch (SQLException ex) {
            Logger.getLogger(CompraView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(CompraView.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jTextFieldCodigoDeBarras = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jTextFieldValorUnitario = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jTextFieldUnidadeMedida = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jTextFieldQuantidade = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jTextFieldProduto = new javax.swing.JTextField();
        jButtonPesquisarProduto = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jButtonPesquisarFornecedor = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jLabelNomeFornecedor = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jButtonRemover = new javax.swing.JButton();
        jButtonAdicionar = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableItemCompra = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        jLabelValorTotal = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jPanelDataChooser = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabelLegendaPesquisa = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableCompraHistorico = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableItemCompraHistorico = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jRadioButtonTodos = new javax.swing.JRadioButton();
        jRadioButtonFinalizado = new javax.swing.JRadioButton();
        jRadioButtonNãoFinalizado = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        jButtonNovaCompra = new javax.swing.JButton();
        jButtonSalva = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButtonHistorico = new javax.swing.JButton();
        jButtonExcluir = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

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
        setTitle("Compra");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(3, 133, 188));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Compra");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Código de Barras", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldCodigoDeBarras.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCodigoDeBarrasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldCodigoDeBarrasFocusLost(evt);
            }
        });
        jTextFieldCodigoDeBarras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCodigoDeBarrasActionPerformed(evt);
            }
        });
        jTextFieldCodigoDeBarras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCodigoDeBarrasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCodigoDeBarrasKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldCodigoDeBarras, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jTextFieldCodigoDeBarras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Valor Unitário", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldValorUnitario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextFieldValorUnitarioMouseClicked(evt);
            }
        });
        jTextFieldValorUnitario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldValorUnitarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Unidade de Medida", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldUnidadeMedida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldUnidadeMedidaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldUnidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldUnidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Quantidade", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldQuantidadeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Descrição", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldProdutoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldProduto)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jTextFieldProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonPesquisarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lupa20x20.png"))); // NOI18N
        jButtonPesquisarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarProdutoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonPesquisarProduto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonPesquisarProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Fornecedor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jButtonPesquisarFornecedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lupa20x20.png"))); // NOI18N
        jButtonPesquisarFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarFornecedorActionPerformed(evt);
            }
        });

        jPanel22.setBackground(new java.awt.Color(255, 255, 255));
        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Nome", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelNomeFornecedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelNomeFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonPesquisarFornecedor)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jButtonPesquisarFornecedor))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder()));

        jButtonRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/remover20x20.png"))); // NOI18N
        jButtonRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverActionPerformed(evt);
            }
        });

        jButtonAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/adicionar20x20.png"))); // NOI18N
        jButtonAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(446, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonAdicionar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Itens de Compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jTableItemCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código de Barras", "Produto", "Quantidade", "Unidade Medida", "Valor Unitário", "Valor Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableItemCompra);

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
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 18))); // NOI18N

        jLabelValorTotal.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        jLabelValorTotal.setText("0,00");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(69, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelValorTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Principal", jPanel6);

        jPanel.setBackground(new java.awt.Color(255, 255, 255));
        jPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Pesquisar ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lupa20x20.png"))); // NOI18N
        jButton8.setText("Pesquisar");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDataChooserLayout = new javax.swing.GroupLayout(jPanelDataChooser);
        jPanelDataChooser.setLayout(jPanelDataChooserLayout);
        jPanelDataChooserLayout.setHorizontalGroup(
            jPanelDataChooserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDataChooserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );
        jPanelDataChooserLayout.setVerticalGroup(
            jPanelDataChooserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Codigo", "Data" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel9.setText("Tipo:");

        jLabelLegendaPesquisa.setText("Legenda");

        javax.swing.GroupLayout jPanelLayout = new javax.swing.GroupLayout(jPanel);
        jPanel.setLayout(jPanelLayout);
        jPanelLayout.setHorizontalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLayout.createSequentialGroup()
                        .addComponent(jTextField1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelDataChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8))
                    .addComponent(jLabelLegendaPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelLayout.setVerticalGroup(
            jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabelLegendaPesquisa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelDataChooser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox1))
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jTableCompraHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Data", "Hora", "Valor Total", "Funcionário", "Fornecedor", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableCompraHistorico.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCompraHistoricoMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTableCompraHistorico);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1058, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "ItensCompra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jTableItemCompraHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produto", "Quantidade", "Quantidade em Estoque", "Unidade de Medida", "Valor Unitario", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableItemCompraHistorico);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Finalizar Compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Concluir30x30.png"))); // NOI18N
        jButton5.setText("Finalizar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Legenda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jLabel1.setText("Compra Finalizada");

        jLabel3.setText("Requer Atenção");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/confirmar20x20.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/alerta20x20.png"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/espera20x20.png"))); // NOI18N

        jLabel8.setText("Compra Não Finalizada ");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)))
                        .addGap(0, 59, Short.MAX_VALUE))))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addContainerGap())
        );

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Filtro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        buttonGroup1.add(jRadioButtonTodos);
        jRadioButtonTodos.setText("Todos");
        jRadioButtonTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonTodosActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonFinalizado);
        jRadioButtonFinalizado.setText("Finalizado");
        jRadioButtonFinalizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonFinalizadoActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonNãoFinalizado);
        jRadioButtonNãoFinalizado.setText("Não Finalizado");
        jRadioButtonNãoFinalizado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonNãoFinalizadoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButtonNãoFinalizado)
                    .addComponent(jRadioButtonTodos)
                    .addComponent(jRadioButtonFinalizado)))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addComponent(jRadioButtonTodos, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jRadioButtonFinalizado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jRadioButtonNãoFinalizado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("Historico", jPanel3);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 616, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jButtonNovaCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/adicionar24x24.png"))); // NOI18N
        jButtonNovaCompra.setText("[F1] Nova Compra");
        jButtonNovaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNovaCompraActionPerformed(evt);
            }
        });

        jButtonSalva.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/salvar24x24.png"))); // NOI18N
        jButtonSalva.setText("[F3] Salvar");
        jButtonSalva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvaActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/VoltarSimples24x24.png"))); // NOI18N
        jButton6.setText("Fechar [esc]");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButtonHistorico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Historico24x24.png"))); // NOI18N
        jButtonHistorico.setText("[F2] Historico");
        jButtonHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHistoricoActionPerformed(evt);
            }
        });

        jButtonExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/excluir24x24.png"))); // NOI18N
        jButtonExcluir.setText("[F4] Remover");
        jButtonExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirActionPerformed(evt);
            }
        });

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/voltar24x24.png"))); // NOI18N
        jButtonCancelar.setText("[F5] Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButtonNovaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonHistorico, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                            .addComponent(jButtonCancelar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonExcluir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonSalva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonNovaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSalva, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
     
    public void sair(){
        super.dispose();
    }
    public void reset(){
         Locale localBrasil = new Locale("pt", "BR");
         jLabelNomeFornecedor.setText("");
        
         jTextFieldProduto.setEditable(false);
         jTextFieldUnidadeMedida.setEditable(false);
         jTextFieldQuantidade.setDocument(new FormatacaoDinamicaQuantidade());
         jTextFieldValorUnitario.setDocument(new FormatacaoDinamicaDinheiro());
         
        this.valorTotal = 0; 
        String valor= NumberFormat.getCurrencyInstance(localBrasil).format(this.valorTotal);
        jLabelValorTotal.setText(valor);
        
        this.limpaCampos();
        this.fornecedor = new FornecedorModel();
        this.listaFornecedor.clear();
        this.itensCompra.clear();
        this.itensCompra3.clear();
        this.itensCompraAtual.clear();
        
       atualizaTabela();
        
    }
    
    public void cancelar(){
        
        jButtonPesquisarFornecedor.setEnabled(true);
        jTextFieldProduto.setEditable(false);
        jTextFieldQuantidade.setEditable(false);
        jTextFieldUnidadeMedida.setEditable(false);
        jTextFieldValorUnitario.setEditable(false);
        jButtonAdicionar.setEnabled(false);
        jButtonRemover.setEnabled(false);
        jButtonPesquisarFornecedor.setEnabled(false);
        jButtonPesquisarProduto.setEnabled(false);
        jButtonCancelar.setEnabled(false);
        jButtonNovaCompra.setEnabled(true);
        jButtonExcluir.setEnabled(false);
        jButtonSalva.setEnabled(false);
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(0, true);
        jTabbedPane1.setSelectedIndex(0);
        jButtonHistorico.setEnabled(true);
        fornecedor = new FornecedorModel();
        itensCompra = new ArrayList<>();
        jLabelNomeFornecedor.setText("");
        
        atualizaTabela();
    
    
    }
    
    public void excluir(){
        
        jButtonRemover.setEnabled(false);
        jButtonAdicionar.setEnabled(true);
        jButtonCancelar.setEnabled(false);
        jTabbedPane1.setSelectedIndex(1);
        jTabbedPane1.setEnabledAt(0, false);
        jButtonNovaCompra.setEnabled(false);
        
    }
    public void novaCompra(){
        
        jTextFieldProduto.setEditable(true);
        jTextFieldQuantidade.setEditable(true);
        jTextFieldUnidadeMedida.setEditable(true);
        jTextFieldValorUnitario.setEditable(true);
        jButtonAdicionar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonPesquisarFornecedor.setEnabled(true);
        jButtonPesquisarProduto.setEnabled(true);
        jButtonNovaCompra.setEnabled(true);
        jButtonHistorico.setEnabled(false);
    }
    
    public void adicionarFornecedor(FornecedorModel fornecedor){
        this.fornecedor = fornecedor;
        jLabelNomeFornecedor.setText(fornecedor.getNome());
        
    }
    
    public void salvar(){
        
        Date data = new Date(System.currentTimeMillis());
        data.toLocalDate();
        
        Time hora = new Time(System.currentTimeMillis());
        hora.toLocalTime();
        
        Timestamp dataHoras = new Timestamp(System.currentTimeMillis());
        hora.toLocalTime();
        
       // FormataStringParaData formataData = new FormataStringParaData();
        
        CompraModel compraModel = new CompraModel();
        CompraDAO compraDao = new CompraDAO();
      
        FuncionarioModel funcionarioModel = new FuncionarioModel();
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
       
        CaixaModel caixaModel = new CaixaModel();
        CaixaDAO caixaDao = new CaixaDAO();
        
        ItemCompra itemCompraModel = new ItemCompra();
        ItemCompraDAO itemCompraDao = new ItemCompraDAO();
      if(!itensCompra.isEmpty()){
        try {
         
            
        int numeroNota;
        caixaModel = caixaDao.pesquisarCaixa();
  
        funcionarioModel = funcionarioDao.findByID(caixaModel.getFuncionarioModel().getId()); 
        
        //PEGA O FUNCIONARIO QUE ABRIU O CAIXA
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        compraModel.setDataCompra(data);
        compraModel.setHoraCompra(hora);
        compraModel.setFuncionario(funcionarioModel);
      
        compraModel.setValor(valorTotal);
        compraModel.setStatus("Nao Finalizado");
        compraModel.setFornecedor(this.fornecedor);
        
        compraDao.inserir(compraModel);
        compraModel = compraDao.pesquisaUltimaCompra();

        for(ItemCompra i: this.itensCompra){
            i.setDataHora(dataHoras);
            i.setCompra(compraModel);
            itemCompraDao.inserir(i);
        }
        
        atualizaTabelaHistoricoProdutoCompra();
         } catch (SQLException ex) {
             System.err.println("ERRA "+ex);
         } catch (NaoEncontradoException ex) {
              System.err.println(ex);
             Logger.getLogger(CompraView.class.getName()).log(Level.SEVERE, null, ex);
         }
         
    }else{
      
          JOptionPane.showMessageDialog(null, "Insira um produto para efetuar a compra", "Alerta",JOptionPane.WARNING_MESSAGE);
      }
        
    }
    public void atualizaTabelaHistoricoItemCompra(ArrayList<ItemCompra>itemCompras){
         ImageIcon icone = new ImageIcon();
        DefaultTableModel val = (DefaultTableModel) jTableItemCompraHistorico.getModel();
        val.setNumRows(0); 
        EstoqueDAO estoqueDao = new EstoqueDAO();
        Locale localBrasil = new Locale("pt", "BR");
       
         if (jTableItemCompraHistorico != null) {
             itemCompras.forEach((itemCompra) -> {
                  String quantidade, quantidadeEstoque;
                    if (itemCompra.getProduto().getUnidadeMedida().equals("KG")) {
                        NumberFormat formatter = new DecimalFormat("#0.000");
                        quantidade = formatter.format(itemCompra.getQuantidade());
                        quantidadeEstoque = formatter.format(itemCompra.getQuantidadeVerificacao());
                    } else {
                        int quantidadeInt = (int) itemCompra.getQuantidade();
                        int quantidadeIntEstoque = (int) itemCompra.getQuantidadeVerificacao();
                        quantidade = String.valueOf(quantidadeInt);
                        quantidadeEstoque = String.valueOf(quantidadeIntEstoque);
                    }
                    String valor = NumberFormat.getCurrencyInstance(localBrasil).format(itemCompra.getValorUnitario());
                    String status;
                    if(itemCompra.getStatus()==false){
                        status = "";
                    }else{
                        status = " ";
                    }
                    val.addRow(new Object[]{"Produto Nome",quantidade,quantidadeEstoque,itemCompra.getUnidadeMedida(),valor, status});
                    jTableItemCompraHistorico.getColumn("Status").setCellRenderer(new TableRendererItemCompra(icone, status));
            });

       }
        
    }
    public void preencheFornecedorCombobox(){
    FornecedorDAO fornecedorDao = new FornecedorDAO();
    
         try {
             
             listaFornecedor = fornecedorDao.findAll();
               //  jComboBoxFornecedor.addItem("Selecione... ");
             for(FornecedorModel fornecedor : listaFornecedor){
                
                 //jComboBoxFornecedor.addItem(fornecedor.getNome());
                 
             }
    
     } catch (SQLException ex) {
             Logger.getLogger(ContaPagarView.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
    
    private void jButtonSalvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvaActionPerformed
        
        salvar();
        reset();
        
    }//GEN-LAST:event_jButtonSalvaActionPerformed
    
    public void atualizaTabela(){
    Locale localBrasil = new Locale("pt", "BR");
        valorTotal = 0; 
        DefaultTableModel val = (DefaultTableModel) jTableItemCompra.getModel();
        val.setNumRows(0); 

        if (jTableItemCompra != null) {
           
            this.itensCompra.forEach((itemCompra) -> {
                double valorTotalUnitario = 0, valor =0;
                double quantidadeDouble;
                double valorTotalDouble = itemCompra.getValorUnitario();
                quantidadeDouble = itemCompra.getQuantidade();

                valorTotalDouble =  quantidadeDouble * valorTotalDouble;
               
                double quantidaded = itemCompra.getQuantidade();
                valorTotalUnitario = valorTotalUnitario * quantidaded;

                String quantidadeString = String.valueOf(quantidaded);
                quantidadeString = quantidadeString.replace(".", "").replace(",", "");
                if(itemCompra.getUnidadeMedida().equals("KG")){
                NumberFormat formatter = new DecimalFormat("#0.000"); 
                quantidadeString = formatter.format(quantidaded).replace(".", ",");
                }else{
                    
                NumberFormat formatter = new DecimalFormat("#00"); 
                quantidadeString = formatter.format(quantidaded).replace(".", ",");
                
                }
               
                valorTotal = valorTotal + valorTotalDouble;
                
                valor = valor + valorTotalDouble;
                String valorString= NumberFormat.getCurrencyInstance(localBrasil).format(valorTotal);
                String valorS= NumberFormat.getCurrencyInstance(localBrasil).format(itemCompra.getValorUnitario());
                String valorST= NumberFormat.getCurrencyInstance(localBrasil).format(valorTotalDouble);
                jLabelValorTotal.setText(valorString);
                
                val.addRow(new Object[]{itemCompra.getProduto().getCodigoBarra(),itemCompra.getProduto().getNome(),quantidadeString,
                
                itemCompra.getUnidadeMedida(), valorS, valorST});

            });
          
       }

    }
    
    public void atualizaTabelaHistoricoProdutoCompra() throws SQLException, NaoEncontradoException{
        ImageIcon icone = new ImageIcon();
        CompraDAO compraDao = new CompraDAO();
        DefaultTableModel val = (DefaultTableModel) jTableCompraHistorico.getModel();
        val.setNumRows(0); 
        ArrayList<CompraModel>listaCompraVerificacao = new ArrayList<>(); 
        listaCompraVerificacao = compraDao.findAll();
        Locale localBrasil = new Locale("pt", "BR");
         if (jTableCompraHistorico != null) {
          
            listaCompraVerificacao.forEach((compra) -> {
                String data = new SimpleDateFormat("dd/MM/yyyy").format(compra.getDataCompra());
                 String hora = new SimpleDateFormat("HH:mm:ss").format(compra.getHoraCompra());
                 String valor = NumberFormat.getCurrencyInstance(localBrasil).format(compra.getValor());
                val.addRow(new Object[]{compra.getId(), data, 
                    hora, valor,
                    compra.getFuncionario().getNome(), 
                    compra.getFornecedor().getNome(),compra.getStatus()});
                    jTableCompraHistorico.getColumn("Status").setCellRenderer(new TableRendererCompra(icone, compra.getStatus()));
            });

       }

    }
    
    public void atualizaTabelaHistoricoProdutoCompraParametro(ArrayList<CompraModel>listaCompra) throws SQLException, NaoEncontradoException{
       
        CompraDAO compraDao = new CompraDAO();
        DefaultTableModel val = (DefaultTableModel) jTableCompraHistorico.getModel();
        val.setNumRows(0); 
        if(listaCompra!= null){
         if (jTableCompraHistorico != null) {
          
            listaCompra.forEach((compra) -> {
                
                val.addRow(new Object[]{compra.getId(), compra.getDataCompra(), 
                    compra.getHoraCompra(), compra.getValor(),
                    compra.getFuncionario().getNome(), 
                    compra.getFornecedor().getNome(),compra.getStatus()});
                
            });

       }
        }else{
        val.setNumRows(0);
        }  
    }
    
    public void limpaCampos(){
        
        jTextFieldQuantidade.setDocument(new FormatacaoDinamicaQuantidadeUnid());
        jTextFieldValorUnitario.setDocument(new FormatacaoDinamicaQuantidade());
        jTextFieldCodigoDeBarras.setText("");
        jTextFieldProduto.setText("");
        jTextFieldQuantidade.setText("");
        jTextFieldUnidadeMedida.setText("");
        jTextFieldValorUnitario.setText("");
        jTextFieldValorUnitario.setDocument(new FormatacaoDinamicaDinheiro());
        jTextFieldQuantidade.setEditable(false);
    }
    public void preencheCampoProduto(ProdutoModel produto){
            
            this.produtoModel = produto;
            
            String valorString;
            //DecimalFormat df = new DecimalFormat("#,###.00");
            NumberFormat df = new DecimalFormat("#0.00"); 
            valorString = df.format(produto.getValor()); 
            
            //jLabelValorTotal.setText(valorString = valorString.replace(".",","));
          
            valorString = valorString.replace(",","");
            
            //jTextFieldValorUnitario.setText(valorString);

            jTextFieldCodigoDeBarras.setText(produto.getCodigoBarra()+"");
           
            
            this.codigoBarras = jTextFieldCodigoDeBarras.getText();
            jTextFieldCodigoDeBarras.setText(codigoBarras);
            jTextFieldProduto.setText(produto.getNome());
            jTextFieldUnidadeMedida.setText(produto.getUnidadeMedida());
            
            //ALTERA FORMATO DO CAMPO
            if(produto.getUnidadeMedida().equals("KG")){
             
            jTextFieldQuantidade.setDocument(new FormatacaoDinamicaQuantidade());    
            jTextFieldQuantidade.setText("00");  
             jTextFieldValorUnitario.setText(valorString); 
            }else{
            
            jTextFieldQuantidade.setDocument(new FormatacaoDinamicaQuantidadeUnid());
            
            jTextFieldQuantidade.setText("0");   
            jTextFieldValorUnitario.setText(valorString);
            }
            
            //estoque.getProduto().getValor().toString();
            jTextFieldQuantidade.requestFocus();
            
    
    }
    
    private void jTextFieldCodigoDeBarrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodigoDeBarrasActionPerformed
        
        ProdutoModel produto = new ProdutoModel();
        ProdutoDAO produtoDao = new ProdutoDAO();
        
     try {
         
         produto = produtoDao.pesquisarCodigoBarras(jTextFieldCodigoDeBarras.getText());
         preencheCampoProduto(produto);
         
     } catch (SQLException ex) {
         Logger.getLogger(CompraView.class.getName()).log(Level.SEVERE, null, ex);
     } catch (NaoEncontradoException ex) {
         Logger.getLogger(CompraView.class.getName()).log(Level.SEVERE, null, ex);
     }
        
        
    }//GEN-LAST:event_jTextFieldCodigoDeBarrasActionPerformed

    private void jButtonAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarActionPerformed
        
       preencheTabela();
        limpaCampos(); 
    }//GEN-LAST:event_jButtonAdicionarActionPerformed
    
//    public void preencheTabelaHistorico() throws SQLException, NaoEncontradoException, ParseException{
//        int id;
//        String idString;
//        
//        CompraModel compraModel = new CompraModel();
//        CompraDAO compraDao = new CompraDAO();
//        ItemCompraDAO itemCompraDao = new ItemCompraDAO();
//        
//        ArrayList<CompraModel>listaCompraVerificacao = new ArrayList<>(); 
//        ArrayList<CompraModel>listaCompra = new ArrayList<>();
//        ArrayList<String>listaIDs = new ArrayList<>();
//        ArrayList<ItemCompra>listaItemCompra = new ArrayList<>();
//        
//        listaItemCompra = itemCompraDao.findAll();
//        
//        
//        
//        DefaultTableModel val = (DefaultTableModel) jTableCompraHistorico.getModel();
//        val.setNumRows(0); 
//        
//        int contador = 0;
//        
//         if (jTableCompraHistorico != null) {
//          
//           
//               for(CompraModel compra: listaCompraVerificacao){ 
//                   
//                    for (ItemCompra i : listaItemCompra) {
//                        if(i.getCompra().equals(compra)){
//                            if(i.getStatus()==false){
//                                compra.setStatus("Não Finalizado");
//                            }
//                        }
//                    }
//                    listaCompra.add(compra);
//                }
//               
//             listaCompra.forEach((compra) -> {   
//                val.addRow(new Object[]{compra.getId(), compra.getDataCompra(), compra.getHoraCompra(), compra.getValor(), compra.getFuncionario().getNome(), 
//                    compra.getFornecedor().getNome(),compra.getStatus()});
//            });
//
//       }
//    }
    public void substituiQuantidade(int posicao , ItemCompra itemCompra){
        
       itensCompra.set(posicao, itemCompra);
        
    }
    
    public void adicionarQuantidade(ItemCompra itemCompra){
    
       itensCompra.add(itemCompra);
        
    }
    
    
    public void preencheTabela(){
        
        Locale localBrasil = new Locale("pt", "BR");
        Mascara mascara = new Mascara();
        
        if(!"".equals(jLabelNomeFornecedor.getText())){
        ItemCompra itemCompra = new ItemCompra();
        ItemCompra itemCompra2 = new ItemCompra();
        int quantidadeInt;
        double valorGlobalTotal, valorTotalDouble, valorUnitarioDouble;
        
        String quantidadeString = jTextFieldQuantidade.getText();
        String valorString = jTextFieldValorUnitario.getText();
           
        String valorSemMascara = mascara.limpaMascara(valorString, "valor");
        
        String unidMedida = jTextFieldUnidadeMedida.getText();
        valorGlobalTotal = this.valorTotal;
           
        //SE A QUANTIDADE FOR UNIDADE
        
        if(unidMedida.equals("UNID")){
          
            quantidadeString = quantidadeString.replace(",", "");
            quantidadeInt = Integer.parseInt(quantidadeString);
            
            valorString = valorString.replace(",", "").replace(".", "");
            valorUnitarioDouble = Double.parseDouble(valorSemMascara);
            valorUnitarioDouble = valorUnitarioDouble / 100;
            
            itemCompra.setDescricao(this.produtoModel.getNome());
            itemCompra.setProduto(this.produtoModel);
            itemCompra.setQuantidade(quantidadeInt);
            itemCompra.setUnidadeMedida(jTextFieldUnidadeMedida.getText());
            itemCompra.setValorUnitario(valorUnitarioDouble);
            
            valorTotalDouble =  quantidadeInt * valorUnitarioDouble;
          
            valorGlobalTotal = valorGlobalTotal + valorTotalDouble;
            valorGlobalTotal = valorGlobalTotal / 100;
            this.valorTotal = this.valorTotal + valorGlobalTotal;
        
            
            
        // SE A QUANTIDADE FOR KG    
        }else{
          
            quantidadeString = quantidadeString.replace(",","");
        
            double quantidadeDouble = Double.parseDouble(quantidadeString);
            
            valorString = valorString.replace(",", "").replace(".", "");
            valorUnitarioDouble = Double.parseDouble(valorString);
            valorUnitarioDouble = valorUnitarioDouble / 100;
            
            
            
            double quantidadeDouble2 = quantidadeDouble / 1000;
            valorTotalDouble = valorUnitarioDouble; 
            
            valorGlobalTotal = valorGlobalTotal + valorTotalDouble;
            
            itemCompra.setDescricao(this.produtoModel.getNome());        
            itemCompra.setProduto(this.produtoModel);    
            itemCompra.setQuantidade(quantidadeDouble2);    
            itemCompra.setUnidadeMedida(jTextFieldUnidadeMedida.getText());
            itemCompra.setValorUnitario(valorTotalDouble);
            
            this.valorTotal = valorGlobalTotal;
        
        }
        
       ArrayList<ItemCompra> itensCompraAtual = new ArrayList<>();   
       
       int cont = 0;
       if(!this.itensCompra.isEmpty()){
        
        //LISTA DE QUANDO TEM O PRODUTO E ADICIONA A QUANTIDADE EM UMA POSICAO ESPECIFICA 
        for(ItemCompra i: this.itensCompra){
            if(i.getDescricao().equals(itemCompra.getDescricao())){
               double quantidade1, quantidade2;
                quantidade1 = itemCompra.getQuantidade();
                quantidade2 = i.getQuantidade();
                itemCompra.setQuantidade(quantidade1 + quantidade2);
                this.itensCompra.set(cont,itemCompra);
                
            }else{
                //QUANTIDA NAO TEM ADCIONA EM UM ARRAY SEPARADO
                itensCompraAtual.add(itemCompra);
            }
            cont++;
        }
       }else{
           this.itensCompra.add(itemCompra);
       }
      this.itensCompra.addAll(itensCompraAtual);
      
      List<ItemCompra> itensCompra = new ArrayList<>();
      
      itensCompra = this.itensCompra.stream().distinct().collect(Collectors.toList());
      
      this.itensCompra = itensCompra; 
      
        atualizaTabela();
        
        }else{
            JOptionPane.showMessageDialog(null, "Informe o fornecedor", "Alerta", JOptionPane.WARNING_MESSAGE);
            //jTextFieldValorUnitario.setDocument(new FormatacaoDinamicaQuantidadeUnid());
        }
       
    }
    
    public void telaHistorico(){
    
        jButtonExcluir.setEnabled(true);
        jButtonAdicionar.setEnabled(false);
        jButtonCancelar.setEnabled(true);
        jTabbedPane1.setSelectedIndex(1);
        jTabbedPane1.setEnabledAt(0, false);
        jTabbedPane1.setEnabledAt(1, true);
        
    }
    
    public void finalizar(int id){
    
        CompraModel compra = new CompraModel();
        CompraDAO compraDao = new CompraDAO();
        
        try {
         compra = compraDao.pesquisarId(id);
         if(!"finalizado".equals(compra.getStatus())){
            compra.setStatus("finalizado");
            compraDao.alterarStatus(compra);
            atualizaTabelaHistoricoProdutoCompra();
         }else{
             //JOptionPane.showMessageDialog(rootPane, this);
         }
         
     } catch (NaoEncontradoException ex) {
         Logger.getLogger(CompraView.class.getName()).log(Level.SEVERE, null, ex);
     } catch (SQLException ex) {
         Logger.getLogger(CompraView.class.getName()).log(Level.SEVERE, null, ex);
     }
        
     
        
    }
    
    public void removeTabela(){
        
        int  produto, id;
        int quantidadeInt, valorInt;
        
        double valorTotalDouble, valorUnitarioDouble;
        
        ItemCompra itemCompra = new ItemCompra();
        
        produto = jTableItemCompra.getSelectedRow();
        
        if(produto == -1){
             
             JOptionPane.showMessageDialog(null, "Selecione um campo na tabela","Alerta", JOptionPane.WARNING_MESSAGE);

         
        }else{
        
        itemCompra = this.itensCompra.get(produto);
            
        valorTotalDouble =  itemCompra.getValorUnitario() / itemCompra.getQuantidade();
        
        this.valorTotal =  this.valorTotal - valorTotalDouble;
        this.valorTotalString = String.valueOf(this.valorTotal);
        jLabelValorTotal.setText(this.valorTotalString);
        
        this.itensCompra.remove(produto);
        
        atualizaTabela();
        
        }
    }
   
    public void alteraCampos(){
        
        jTextFieldProduto.setEditable(true);
        jTextFieldQuantidade.setEditable(true);
        jTextFieldUnidadeMedida.setEditable(true);
        jTextFieldValorUnitario.setEditable(true);
        jButtonAdicionar.setEnabled(true);
        jButtonCancelar.setEnabled(true);
        jButtonExcluir.setEnabled(false);
        jButtonRemover.setEnabled(true);
        jButtonSalva.setEnabled(true);
        jButtonPesquisarFornecedor.setEnabled(true);
        jButtonPesquisarProduto.setEnabled(true);
        jButtonNovaCompra.setEnabled(true);
        jTabbedPane1.setSelectedIndex(0);
        jTabbedPane1.setEnabledAt(1, false);
        
    }
    
    private void jButtonRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverActionPerformed
        removeTabela();
    }//GEN-LAST:event_jButtonRemoverActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        sair();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButtonPesquisarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarProdutoActionPerformed
      
        AdicionarProdutoCompra adicionarCompra = new AdicionarProdutoCompra(null, true, this);
        adicionarCompra.setVisible(true);
        
    }//GEN-LAST:event_jButtonPesquisarProdutoActionPerformed

    private void jButtonHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHistoricoActionPerformed
        
        telaHistorico();
        
    }//GEN-LAST:event_jButtonHistoricoActionPerformed

    private void jTextFieldQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldQuantidadeActionPerformed
        jTextFieldCodigoDeBarras.requestFocus();
        
        preencheTabela();
        
    }//GEN-LAST:event_jTextFieldQuantidadeActionPerformed

    private void jTextFieldValorUnitarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldValorUnitarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldValorUnitarioActionPerformed

    private void jTextFieldProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldProdutoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldProdutoActionPerformed

    private void jButtonExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirActionPerformed
      
        excluir();
        
    }//GEN-LAST:event_jButtonExcluirActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        //JOptionPane.showConfirmDialog(null, "A compra não foi salva deseja cancelar ?");
        cancelar();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonNovaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNovaCompraActionPerformed
       
        novaCompra();
        
    }//GEN-LAST:event_jButtonNovaCompraActionPerformed

    private void jTextFieldCodigoDeBarrasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCodigoDeBarrasKeyReleased
       
        alteraCampos();
        
    }//GEN-LAST:event_jTextFieldCodigoDeBarrasKeyReleased

    private void jTextFieldCodigoDeBarrasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCodigoDeBarrasFocusLost
      
    }//GEN-LAST:event_jTextFieldCodigoDeBarrasFocusLost

    private void jTextFieldValorUnitarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldValorUnitarioMouseClicked
       
      
    }//GEN-LAST:event_jTextFieldValorUnitarioMouseClicked

    private void jTextFieldUnidadeMedidaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldUnidadeMedidaKeyReleased
        
        
        
    }//GEN-LAST:event_jTextFieldUnidadeMedidaKeyReleased

    private void jButtonPesquisarFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarFornecedorActionPerformed
      AdicionarFornecedorCompra adicionarFornecedorCompra = new AdicionarFornecedorCompra(null, true, this, null);
      adicionarFornecedorCompra.setVisible(true);
    }//GEN-LAST:event_jButtonPesquisarFornecedorActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
     try {
         Date data = (Date) jDateChooser1.getDate();
         CompraDAO compraDao = new CompraDAO();
         ArrayList listaCompra = compraDao.pesquisarData(data);
         
         atualizaTabelaHistoricoProdutoCompra();
         
     } catch (SQLException ex) {
         Logger.getLogger(CompraView.class.getName()).log(Level.SEVERE, null, ex);
     } catch (NaoEncontradoException ex) {
         Logger.getLogger(CompraView.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }//GEN-LAST:event_jButton8ActionPerformed
    public void finalizar1(){
        
        int id, linha = -1;

        linha = jTableCompraHistorico.getSelectedRow();

        if (linha == -1) {
            
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Selecione um campo na tabela","Alerta", JOptionPane.WARNING_MESSAGE);

        } else {
            int codigo = (int) jTableCompraHistorico.getValueAt(linha, 0);
            finalizar(codigo);
            
        }
        
    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       
        finalizar1();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTableCompraHistoricoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCompraHistoricoMouseClicked
       
        ItemCompra itemCompra = new ItemCompra();
        ItemCompraDAO itemCompraDao = new ItemCompraDAO();
        ArrayList<ItemCompra>listaItensCompra = new ArrayList<>();
       
        int linha = jTableCompraHistorico.getSelectedRow();
        
        FormataStringParaData formata = new FormataStringParaData();
       
        if(linha == -1){
            
            JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta", JOptionPane.WARNING_MESSAGE);
            
        }else{            
            int codigo = (int) jTableCompraHistorico.getValueAt(linha, 0);
            try {
                listaItensCompra = itemCompraDao.pesquisarItemCompra(codigo);
                atualizaTabelaHistoricoItemCompra(listaItensCompra);
                
            } catch (SQLException ex) {
                Logger.getLogger(CompraView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NaoEncontradoException ex) {
                Logger.getLogger(CompraView.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(CompraView.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_jTableCompraHistoricoMouseClicked

    private void jTextFieldCodigoDeBarrasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCodigoDeBarrasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCodigoDeBarrasKeyTyped

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        
         int index = jComboBox1.getSelectedIndex();

        MaskFormatter mascara = null;
        
        switch (index) {

           
            case 0: {//CODIGO
                
                jLabelLegendaPesquisa.setText("Código:");
                jPanelDataChooser.setVisible(false);
                jTextField1.setVisible(true);
                
            }

            break;

            case 1: {//DATA
                
              jLabelLegendaPesquisa.setText("Data:");
              jPanelDataChooser.setVisible(true);
              jTextField1.setVisible(false);
              
            }

            break;

        }
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        
        
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        int index = jComboBox1.getSelectedIndex();
        CompraDAO compraDao = new CompraDAO();
        ArrayList<CompraModel> listaCompra = new ArrayList<>();

        switch (index) {
            case 0: {
                 try //Codigo
                {
                    String codigo = jTextField1.getText();
                    listaCompra = compraDao.pesquisarPorCodigo(codigo);
                    atualizaTabelaHistoricoProdutoCompraParametro(listaCompra);
                } catch (SQLException ex) {
                    Logger.getLogger(AlterarOrcamento.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NaoEncontradoException ex) {
                    Logger.getLogger(AlterarOrcamento.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            break;

            case 1: {
               
            }
            break;

        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextFieldCodigoDeBarrasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCodigoDeBarrasFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCodigoDeBarrasFocusGained

    private void jRadioButtonTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonTodosActionPerformed
        
     try {
         CompraDAO compraDao = new CompraDAO();
         DefaultTableModel val = (DefaultTableModel) jTableCompraHistorico.getModel();
         val.setNumRows(0);
         ArrayList<CompraModel>listaCompraVerificacao = new ArrayList<>();
         listaCompraVerificacao = compraDao.findAll();
         Locale localBrasil = new Locale("pt", "BR");
         if (jTableCompraHistorico != null) {
             
             listaCompraVerificacao.forEach((compra) -> {
                 String data = new SimpleDateFormat("dd/MM/yyyy").format(compra.getDataCompra());
                 String hora = new SimpleDateFormat("HH:mm:ss").format(compra.getHoraCompra());
                 String valor = NumberFormat.getCurrencyInstance(localBrasil).format(compra.getValor());
                 val.addRow(new Object[]{compra.getId(), data,
                     hora,  valor,
                     compra.getFuncionario().getNome(),
                     compra.getFornecedor().getNome(),compra.getStatus()});
                  
             });
             
         }
     } catch (SQLException ex) {
         Logger.getLogger(CompraView.class.getName()).log(Level.SEVERE, null, ex);
     } catch (NaoEncontradoException ex) {
         Logger.getLogger(CompraView.class.getName()).log(Level.SEVERE, null, ex);
     }
         
    }//GEN-LAST:event_jRadioButtonTodosActionPerformed

    private void jRadioButtonFinalizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonFinalizadoActionPerformed
       try {
         CompraDAO compraDao = new CompraDAO();
         DefaultTableModel val = (DefaultTableModel) jTableCompraHistorico.getModel();
         val.setNumRows(0);
         ArrayList<CompraModel>listaCompraVerificacao = new ArrayList<>();
         listaCompraVerificacao = compraDao.findAll();
          Locale localBrasil = new Locale("pt", "BR");
         if (jTableCompraHistorico != null) {
             
             listaCompraVerificacao.forEach((compra) -> {
                if(compra.getStatus().equals("finalizado")){ 
                    String data = new SimpleDateFormat("dd/MM/yyyy").format(compra.getDataCompra());
                 String hora = new SimpleDateFormat("HH:mm:ss").format(compra.getHoraCompra());
                 String valor = NumberFormat.getCurrencyInstance(localBrasil).format(compra.getValor());
                 val.addRow(new Object[]{compra.getId(), data,
                     hora, compra.getValor(),
                     compra.getFuncionario().getNome(),
                     compra.getFornecedor().getNome(),compra.getStatus()});
                }  
             });
             
         }
     } catch (SQLException ex) {
         Logger.getLogger(CompraView.class.getName()).log(Level.SEVERE, null, ex);
     } catch (NaoEncontradoException ex) {
         Logger.getLogger(CompraView.class.getName()).log(Level.SEVERE, null, ex);
     }
    }//GEN-LAST:event_jRadioButtonFinalizadoActionPerformed

    private void jRadioButtonNãoFinalizadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonNãoFinalizadoActionPerformed
        try {
         CompraDAO compraDao = new CompraDAO();
         DefaultTableModel val = (DefaultTableModel) jTableCompraHistorico.getModel();
         val.setNumRows(0);
         ArrayList<CompraModel>listaCompraVerificacao = new ArrayList<>();
         listaCompraVerificacao = compraDao.findAll();
          Locale localBrasil = new Locale("pt", "BR");
         if (jTableCompraHistorico != null) {
             
             listaCompraVerificacao.forEach((compra) -> {
                 String data = new SimpleDateFormat("dd/MM/yyyy").format(compra.getDataCompra());
                 String hora = new SimpleDateFormat("HH:mm:ss").format(compra.getHoraCompra());
                 String valor = NumberFormat.getCurrencyInstance(localBrasil).format(compra.getValor());
                 if(compra.getStatus().equals("Nao Finalizado")){
                 val.addRow(new Object[]{compra.getId(), data,
                     hora, valor,
                     compra.getFuncionario().getNome(),
                     compra.getFornecedor().getNome(),compra.getStatus()});
                 }
             });
             
         }
     } catch (SQLException ex) {
         Logger.getLogger(CompraView.class.getName()).log(Level.SEVERE, null, ex);
     } catch (NaoEncontradoException ex) {
         Logger.getLogger(CompraView.class.getName()).log(Level.SEVERE, null, ex);
     }
    }//GEN-LAST:event_jRadioButtonNãoFinalizadoActionPerformed

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
            java.util.logging.Logger.getLogger(CompraView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CompraView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CompraView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CompraView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CompraView dialog = new CompraView(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButtonAdicionar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonExcluir;
    private javax.swing.JButton jButtonHistorico;
    private javax.swing.JButton jButtonNovaCompra;
    private javax.swing.JButton jButtonPesquisarFornecedor;
    private javax.swing.JButton jButtonPesquisarProduto;
    private javax.swing.JButton jButtonRemover;
    private javax.swing.JButton jButtonSalva;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelLegendaPesquisa;
    private javax.swing.JLabel jLabelNomeFornecedor;
    private javax.swing.JLabel jLabelValorTotal;
    private javax.swing.JPanel jPanel;
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
    private javax.swing.JPanel jPanelDataChooser;
    private javax.swing.JRadioButton jRadioButtonFinalizado;
    private javax.swing.JRadioButton jRadioButtonNãoFinalizado;
    private javax.swing.JRadioButton jRadioButtonTodos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableCompraHistorico;
    private javax.swing.JTable jTableItemCompra;
    private javax.swing.JTable jTableItemCompraHistorico;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextFieldCodigoDeBarras;
    private javax.swing.JTextField jTextFieldProduto;
    private javax.swing.JTextField jTextFieldQuantidade;
    private javax.swing.JTextField jTextFieldUnidadeMedida;
    private javax.swing.JTextField jTextFieldValorUnitario;
    // End of variables declaration//GEN-END:variables
}
