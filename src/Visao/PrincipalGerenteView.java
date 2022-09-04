package Visao;

import Control.Exceptions.NaoEncontradoException;
import DAO.CaixaDAO;
import DAO.EstoqueDAO;
import Modelo.CaixaModel;
import Modelo.EstoqueModel;
import Modelo.FuncionarioModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

public class PrincipalGerenteView extends javax.swing.JFrame {
    FuncionarioModel funcionarioModel = new FuncionarioModel();
    PrincipalFuncionarioView fv;
    boolean alertaAmarelo, alertaCinza;
    int contadorAlto=0, contadorMedio=0, contadorBaixo=0;
    public PrincipalGerenteView(FuncionarioModel funcionarioModel, PrincipalFuncionarioView fv) throws SQLException, NaoEncontradoException {
     
        initComponents();
        CaixaDAO caixaDao = new CaixaDAO();
        CaixaModel caixaModel = new CaixaModel();
        this.fv = fv;
        this.funcionarioModel = funcionarioModel;
        
        jLabelLegendaNome.setText(this.funcionarioModel.getNome());
        
            try{

            caixaModel = caixaDao.pesquisarCaixa();

            mudaTexto(caixaModel);
          
            }catch(NaoEncontradoException e){
                System.err.println(e);
                jLabelStatusCaixa.setText("caixa nao encontrado");
            }
            
        try {
            atualizaTabela();
        } catch (ParseException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
        jTablerResumoEstoque.addMouseListener(new MouseAdapter() { 
               
                public void mouseClicked(MouseEvent evt) 
                        { 
                            if (evt.getClickCount() == 2){
                                
                                    ControleEstoqueView estoque = null;
    
                                    estoque.setVisible(true); 
                            } 
                        }
                });
        
    }
   
    public void atualizaTabela() throws SQLException, ParseException {
        EstoqueDAO estoqueDao = new EstoqueDAO();
        EstoqueModel estoqueModel = new EstoqueModel();
        
        DefaultTableModel val = (DefaultTableModel) jTablerResumoEstoque.getModel();

        val.setNumRows(0); // excluir os registros que estão na JTable
        ArrayList<EstoqueModel> listaProdutosEstoque = new ArrayList<>();
        
        listaProdutosEstoque = estoqueDao.findAll();

        if (jTablerResumoEstoque!= null) {

            listaProdutosEstoque.forEach((EstoqueModel produtos) -> {
                
                String status;
 
                //Quantidade Abaixo do ideal
                if (produtos.getQtdEstoque() < produtos.getQtdMinima()) {
                    status = "Estoque Baixo";
               
                    this.contadorBaixo++;
                   // jLabelBaixo.setText(this.contadorBaixo+"");
                    val.addRow(new Object[]{produtos.getProduto().getNome()});
                }
                
            });
            this.contadorBaixo = 0;
            this.contadorMedio = 0;
            this.contadorAlto = 0;
        }

    }
    
    public void mudaTexto(CaixaModel caixaModel) throws SQLException, NaoEncontradoException{
        
        jLabelStatusCaixa.setText(caixaModel.getSituacao());
        
        if(caixaModel.getSituacao().equals("aberto")){
            String data = new SimpleDateFormat("dd/MM/yyyy").format(caixaModel.getDataAbertura());
            jLabelDataAbertura.setText(data);
            jLabelLegendaDataAberturaFechamento.setText("Data Abertura:");
            
        }else{
            String data = new SimpleDateFormat("dd/MM/yyyy").format(caixaModel.getDataFechamento());    
            jLabelDataAbertura.setText(data);
            jLabelLegendaDataAberturaFechamento.setText("Data Fechamento");
            
            jLabelDataAbertura.addMouseListener(new MouseAdapter() { 
                    
                public void mouseClicked(MouseEvent evt) 
                        { 
                            if (evt.getClickCount() == 2){
                                  
                            } 
                        }
                });
        } 
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButtonFinanceiro = new javax.swing.JButton();
        jButtonEstoque = new javax.swing.JButton();
        jButtonContasPagar = new javax.swing.JButton();
        jButtonContasReceber = new javax.swing.JButton();
        jButtonCliente = new javax.swing.JButton();
        jButtonVenda = new javax.swing.JButton();
        jButtonCaixa = new javax.swing.JButton();
        jButtonProduto = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanelRodape = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabelLegendaDataAberturaFechamento = new javax.swing.JLabel();
        jLabelLegendaNome = new javax.swing.JLabel();
        jLabelDataAbertura = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabelRelogio = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabelStatusCaixa = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablerResumoEstoque = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenuItemCadastroCliente = new javax.swing.JMenuItem();
        jMenuItemCadastroFornecedor = new javax.swing.JMenuItem();
        jMenuItemCadastroFuncionário = new javax.swing.JMenuItem();
        jMenuItemCadastroProduto = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemCliente = new javax.swing.JMenuItem();
        jMenuItemFornecedor = new javax.swing.JMenuItem();
        jMenuItemFuncionario = new javax.swing.JMenuItem();
        jMenuItemProduto = new javax.swing.JMenuItem();
        jMenuMovimentacao = new javax.swing.JMenu();
        jMenuCaixa = new javax.swing.JMenuItem();
        jMenuCompra = new javax.swing.JMenuItem();
        jMenuEstoque = new javax.swing.JMenuItem();
        jMenuVenda = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuSair = new javax.swing.JMenu();
        jMenuItemSairLogin = new javax.swing.JMenuItem();
        jMenuItemSairSistema = new javax.swing.JMenuItem();

        jLabel7.setText("jLabel7");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Principal");
        setExtendedState(6);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButtonFinanceiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/prancheta.png"))); // NOI18N
        jButtonFinanceiro.setText("Financeiro");
        jButtonFinanceiro.setActionCommand("Caixa");
        jButtonFinanceiro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonFinanceiro.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButtonFinanceiro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonFinanceiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinanceiroActionPerformed(evt);
            }
        });

        jButtonEstoque.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/carrinhoCaixa80x80.png"))); // NOI18N
        jButtonEstoque.setText("Estoque");
        jButtonEstoque.setActionCommand("Caixa");
        jButtonEstoque.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonEstoque.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButtonEstoque.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEstoqueActionPerformed(evt);
            }
        });

        jButtonContasPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/ModedaPagar.png"))); // NOI18N
        jButtonContasPagar.setText("Ctas.Pagar");
        jButtonContasPagar.setActionCommand("Caixa");
        jButtonContasPagar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonContasPagar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButtonContasPagar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonContasPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonContasPagarActionPerformed(evt);
            }
        });

        jButtonContasReceber.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/MoedaReceber.png"))); // NOI18N
        jButtonContasReceber.setText("Ctas.Receber");
        jButtonContasReceber.setActionCommand("Caixa");
        jButtonContasReceber.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonContasReceber.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButtonContasReceber.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonContasReceber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonContasReceberActionPerformed(evt);
            }
        });

        jButtonCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Cliente80x80.png"))); // NOI18N
        jButtonCliente.setText("Cliente");
        jButtonCliente.setActionCommand("Caixa");
        jButtonCliente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCliente.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButtonCliente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClienteActionPerformed(evt);
            }
        });

        jButtonVenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Venda70x70.png"))); // NOI18N
        jButtonVenda.setText("Venda");
        jButtonVenda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonVenda.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButtonVenda.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVendaActionPerformed(evt);
            }
        });

        jButtonCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Caixa80x80.png"))); // NOI18N
        jButtonCaixa.setText("Caixa");
        jButtonCaixa.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonCaixa.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCaixaActionPerformed(evt);
            }
        });

        jButtonProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Produtos70x70.png"))); // NOI18N
        jButtonProduto.setText("Produtos");
        jButtonProduto.setActionCommand("Caixa");
        jButtonProduto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonProduto.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jButtonProduto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButtonProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProdutoActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/compra 80x80.png"))); // NOI18N
        jButton2.setText("Compra");
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jButtonVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jButtonCaixa)
                .addGap(2, 2, 2)
                .addComponent(jButtonEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jButtonProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jButtonCliente)
                .addGap(2, 2, 2)
                .addComponent(jButtonContasPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(jButtonContasReceber)
                .addGap(2, 2, 2)
                .addComponent(jButtonFinanceiro)
                .addGap(2, 2, 2)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonCaixa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonContasPagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonFinanceiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonContasReceber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanelRodape.setBackground(new java.awt.Color(10, 177, 209));
        jPanelRodape.setPreferredSize(new java.awt.Dimension(250, 100));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nome:");

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Caixa:");

        jLabelLegendaDataAberturaFechamento.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabelLegendaDataAberturaFechamento.setForeground(new java.awt.Color(255, 255, 255));
        jLabelLegendaDataAberturaFechamento.setText("Data Abertura:");

        jLabelLegendaNome.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabelLegendaNome.setForeground(new java.awt.Color(255, 255, 255));

        jLabelDataAbertura.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabelDataAbertura.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Relógio");

        jLabelRelogio.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabelRelogio.setForeground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(10, 177, 209));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );

        jLabelStatusCaixa.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabelStatusCaixa.setForeground(new java.awt.Color(255, 255, 255));
        jLabelStatusCaixa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelStatusCaixa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelStatusCaixaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelStatusCaixaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelStatusCaixaMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelRodapeLayout = new javax.swing.GroupLayout(jPanelRodape);
        jPanelRodape.setLayout(jPanelRodapeLayout);
        jPanelRodapeLayout.setHorizontalGroup(
            jPanelRodapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelRodapeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelLegendaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelStatusCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelLegendaDataAberturaFechamento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelDataAbertura, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelRelogio, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelRodapeLayout.setVerticalGroup(
            jPanelRodapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelRodapeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelRodapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelLegendaNome, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
            .addComponent(jLabelLegendaDataAberturaFechamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelDataAbertura, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelRelogio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelRodapeLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jLabelStatusCaixa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/imagensConfig/logoCasaTempero.png"))); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Resumo Estoque", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Produtos em baixo estoque", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTablerResumoEstoque.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTablerResumoEstoque);

        jButton1.setText("Verificar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 901, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jMenu3.setText("Cadastros");

        jMenuItemCadastroCliente.setText("Cliente");
        jMenuItemCadastroCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastroClienteActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemCadastroCliente);

        jMenuItemCadastroFornecedor.setText("Fornecedor");
        jMenuItemCadastroFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastroFornecedorActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemCadastroFornecedor);

        jMenuItemCadastroFuncionário.setText("Funcionário");
        jMenuItemCadastroFuncionário.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastroFuncionárioActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemCadastroFuncionário);

        jMenuItemCadastroProduto.setText("Produto");
        jMenuItemCadastroProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadastroProdutoActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItemCadastroProduto);

        jMenuBar1.add(jMenu3);

        jMenu1.setText("Controle");

        jMenuItemCliente.setText("Cliente");
        jMenuItemCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClienteActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemCliente);

        jMenuItemFornecedor.setText("Fornecedor");
        jMenuItemFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFornecedorActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemFornecedor);

        jMenuItemFuncionario.setText("Funcionário");
        jMenuItemFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFuncionarioActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemFuncionario);

        jMenuItemProduto.setText("Produto");
        jMenuItemProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemProdutoActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemProduto);

        jMenuBar1.add(jMenu1);

        jMenuMovimentacao.setText("Movimentação");
        jMenuMovimentacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuMovimentacaoActionPerformed(evt);
            }
        });

        jMenuCaixa.setText("Caixa");
        jMenuCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCaixaActionPerformed(evt);
            }
        });
        jMenuMovimentacao.add(jMenuCaixa);

        jMenuCompra.setText("Compra");
        jMenuCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCompraActionPerformed(evt);
            }
        });
        jMenuMovimentacao.add(jMenuCompra);

        jMenuEstoque.setText("Estoque");
        jMenuEstoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuEstoqueActionPerformed(evt);
            }
        });
        jMenuMovimentacao.add(jMenuEstoque);

        jMenuVenda.setText("Venda");
        jMenuVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuVendaActionPerformed(evt);
            }
        });
        jMenuMovimentacao.add(jMenuVenda);

        jMenuItem1.setText("Orçamento");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuMovimentacao.add(jMenuItem1);

        jMenuBar1.add(jMenuMovimentacao);

        jMenu2.setText("Relatório");

        jMenuItem2.setText("Relatório de Vendas");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Relatório de Compras");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Relatório de Contas a Pagar");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenuSair.setText("Sair");

        jMenuItemSairLogin.setText("Sair para Login");
        jMenuItemSairLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSairLoginActionPerformed(evt);
            }
        });
        jMenuSair.add(jMenuItemSairLogin);

        jMenuItemSairSistema.setText("Sair do Sistema");
        jMenuItemSairSistema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemSairSistemaActionPerformed(evt);
            }
        });
        jMenuSair.add(jMenuItemSairSistema);

        jMenuBar1.add(jMenuSair);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelRodape, javax.swing.GroupLayout.DEFAULT_SIZE, 1197, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanelRodape, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFornecedorActionPerformed
        try {
            ManterFornecedorView manterFornecedor = new ManterFornecedorView(null, true, "") {
            };
            manterFornecedor.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jMenuItemFornecedorActionPerformed

    private void jButtonClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClienteActionPerformed
        try {
            ManterClienteView manterCliente = new ManterClienteView(null, true, "principal");
            manterCliente.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonClienteActionPerformed

    private void jMenuItemSairSistemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSairSistemaActionPerformed
     
            System.exit(0);
        
        
    }//GEN-LAST:event_jMenuItemSairSistemaActionPerformed

    private void jMenuItemClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClienteActionPerformed
        try {
            ManterClienteView manterCliente = new ManterClienteView(null, true, "principal");
            manterCliente.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jMenuItemClienteActionPerformed

    private void jMenuItemFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFuncionarioActionPerformed
        try {
            ManterFuncionarioView manterFuncionario = new ManterFuncionarioView(null, true, "") {
            };
            manterFuncionario.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jMenuItemFuncionarioActionPerformed

    private void jMenuItemProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemProdutoActionPerformed
        try {
            ManterProdutoView manterProduto = new ManterProdutoView(null, true, "") {
            };
            manterProduto.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jMenuItemProdutoActionPerformed

    private void jButtonProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProdutoActionPerformed
        try {
            ManterProdutoView manterProduto = new ManterProdutoView(null, true, "") {
            };
            manterProduto.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonProdutoActionPerformed

    private void jMenuItemSairLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemSairLoginActionPerformed
        LoginView login = new LoginView(null, true);
        dispose();
        login.setVisible(true);

    }//GEN-LAST:event_jMenuItemSairLoginActionPerformed

    private void jButtonEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEstoqueActionPerformed

       ControleEstoqueView estoque = null;
    
       estoque = new ControleEstoqueView(this);
       
       estoque.setVisible(true);
    }//GEN-LAST:event_jButtonEstoqueActionPerformed

    private void jButtonCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCaixaActionPerformed
        
        ControleCaixaView caixa = null;
       
        caixa = new ControleCaixaView(this.funcionarioModel, this, this.fv);
        
        caixa.setVisible(true);
        
    }//GEN-LAST:event_jButtonCaixaActionPerformed

    private void jButtonVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVendaActionPerformed
         
        CaixaModel caixaModel = new CaixaModel();
        CaixaDAO caixaDao = new CaixaDAO();
        
     
        try {
            caixaModel = caixaDao.pesquisarCaixa();
       
            if("fechado".equals(caixaModel.getSituacao())){
                
               
                JOptionPane.showMessageDialog(null, "Faça a abertura do caixa para continuar", "Alerta, O caixa não está aberto!",JOptionPane.WARNING_MESSAGE);
                
                 ControleCaixaView caixa = null;
       
                caixa = new ControleCaixaView(this.funcionarioModel, this, this.fv);
                caixa.setVisible(true);
                
            }else{
                
                if(caixaModel.getFinaliza() == false){
                    
                    Venda venda = new Venda("", null, null,this);
                    venda.setVisible(true);
                    
                }else{
                    String data = new SimpleDateFormat("dd/MM/yyyy").format(caixaModel.getDataAbertura());
                    JOptionPane.showMessageDialog(null, "O caixa do dia "+data+" esta com diferença de valores. Verifique o prodblema","alerta",JOptionPane.WARNING_MESSAGE);
                    
                    ControleCaixaView caixa = null;
       
                    caixa = new ControleCaixaView(this.funcionarioModel, this, this.fv);
                    caixa.setVisible(true);
                    
                }
            }   
        
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }//GEN-LAST:event_jButtonVendaActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        
        CaixaDAO caixaDao = new CaixaDAO();
        CaixaModel caixaModel = new CaixaModel();    
        Timer timer = new Timer(1000, new hora());
        timer.start();
        try {    
            caixaModel = caixaDao.pesquisarCaixa();
       
        if(caixaModel.getSituacao().equals("fechado")){
            
            if( caixaModel.getFinaliza() == true ){
               
                 String data = new SimpleDateFormat("dd/MM/yyyy").format(caixaModel.getDataAbertura());
                 
                    JOptionPane.showMessageDialog(null, "O caixa do dia "+data+" esta com diferença de valores. Verifique o problema","alerta",JOptionPane.WARNING_MESSAGE);
                    
                    ControleCaixaView caixa = null;
       
                    caixa = new ControleCaixaView(this.funcionarioModel, this, this.fv);
                    caixa.setVisible(true);
                
            }
        }   
        
         } catch (SQLException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_formWindowOpened

    private void jLabelStatusCaixaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelStatusCaixaMouseClicked
       
    }//GEN-LAST:event_jLabelStatusCaixaMouseClicked

    private void jLabelStatusCaixaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelStatusCaixaMouseEntered
       
    }//GEN-LAST:event_jLabelStatusCaixaMouseEntered

    private void jLabelStatusCaixaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelStatusCaixaMouseExited
        
    }//GEN-LAST:event_jLabelStatusCaixaMouseExited

    private void jMenuItemCadastroProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadastroProdutoActionPerformed
        
        try {
            ManterProdutoView manterProduto = new ManterProdutoView(null, true, "telaPrincipal") {
            };
            manterProduto.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jMenuItemCadastroProdutoActionPerformed

    private void jMenuCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCaixaActionPerformed
       
        ControleCaixaView caixa = null;
            caixa = new ControleCaixaView(this.funcionarioModel, this, this.fv);
        
        caixa.setVisible(true);
        
    }//GEN-LAST:event_jMenuCaixaActionPerformed

    private void jMenuCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCompraActionPerformed
         
        CompraView compra = new CompraView(null, true);
        compra.setVisible(true);
         
    }//GEN-LAST:event_jMenuCompraActionPerformed
    
    private void jMenuEstoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuEstoqueActionPerformed
      ControleEstoqueView estoque = null;
    
       estoque = new ControleEstoqueView(this);
       
       estoque.setVisible(true);
    }//GEN-LAST:event_jMenuEstoqueActionPerformed

    private void jMenuVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuVendaActionPerformed
       
        CaixaModel caixaModel = new CaixaModel();
        CaixaDAO caixaDao = new CaixaDAO();
        
     
        try {
            caixaModel = caixaDao.pesquisarCaixa();
       
            if("fechado".equals(caixaModel.getSituacao())){
                
               
                JOptionPane.showMessageDialog(null, "Faça a abertura do caixa para continuar", "Alerta, O caixa não está aberto!",JOptionPane.WARNING_MESSAGE);
                
                ControleCaixaView caixa = null;
       
                caixa = new ControleCaixaView(this.funcionarioModel, this, this.fv);
                caixa.setVisible(true);
                
            }else{
                
                if(caixaModel.getFinaliza() == false){
                    
                    Venda venda = new Venda("", null, null,this);
                    venda.setVisible(true);
                    
                }else{
                    
                    JOptionPane.showMessageDialog(null, "O caixa do dia "+caixaModel.getDataAbertura()+" esta com diferença de valores. Verifique","Alerta",JOptionPane.WARNING_MESSAGE);
                    
                    ControleCaixaView caixa = null;
       
                    caixa = new ControleCaixaView(this.funcionarioModel, this, this.fv);
                    caixa.setVisible(true);
                    
                }
            }   
        
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        
    }//GEN-LAST:event_jMenuVendaActionPerformed

    private void jMenuMovimentacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuMovimentacaoActionPerformed
       ControleEstoqueView estoque = null;
    
       estoque = new ControleEstoqueView(this);
       
       estoque.setVisible(true);
    }//GEN-LAST:event_jMenuMovimentacaoActionPerformed

    private void jMenuItemCadastroClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadastroClienteActionPerformed
        try {
            //Cadastro Cliente
            ManterClienteView cliente = new ManterClienteView(null, true, "telaPrincipal");
            cliente.setVisible(true);
            
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
        
    }//GEN-LAST:event_jMenuItemCadastroClienteActionPerformed

    private void jMenuItemCadastroFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadastroFornecedorActionPerformed
        try {
            ManterFornecedorView manterFornecedor = new ManterFornecedorView(null, true, "telaPrincipal") {
            };
            manterFornecedor.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jMenuItemCadastroFornecedorActionPerformed

    private void jMenuItemCadastroFuncionárioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadastroFuncionárioActionPerformed
        try {
            ManterFuncionarioView manterFuncionario = new ManterFuncionarioView(null, true, "telaPrincipal") {
            };
            manterFuncionario.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }//GEN-LAST:event_jMenuItemCadastroFuncionárioActionPerformed

    private void jButtonContasPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonContasPagarActionPerformed
        ContaPagarView contaPagar = new ContaPagarView(null, true);
        contaPagar.setVisible(true);
    }//GEN-LAST:event_jButtonContasPagarActionPerformed

    private void jButtonContasReceberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonContasReceberActionPerformed
        ContaReceberView contaReceber = new ContaReceberView(null, true);
        contaReceber.setVisible(true);
    }//GEN-LAST:event_jButtonContasReceberActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
       ControleEstoqueView estoque = null;
    
       estoque = new ControleEstoqueView(this);
       
       estoque.setVisible(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButtonFinanceiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinanceiroActionPerformed
       
        ControleFinanceiroView controleFinanceiro = new ControleFinanceiroView();
        controleFinanceiro.setVisible(true);
        
        
    }//GEN-LAST:event_jButtonFinanceiroActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        OrcamentoView orcamento = new OrcamentoView(null, true, this.funcionarioModel, this);
        orcamento.setVisible(true);
        
        
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       CompraView compra = new CompraView(null, true);
       compra.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        
        RelatorioImput relatorio = new RelatorioImput(null, true, "venda");
        relatorio.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
       RelatorioImput relatorio = new RelatorioImput(null, true, "compra");
        relatorio.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
      RelatorioImput relatorio = new RelatorioImput(null, true, "conta");
        relatorio.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed
    
    protected ImageIcon createImageIcon(String path) {
    java.net.URL imgURL = getClass().getResource(path);
    if (imgURL != null) {
        
        return new ImageIcon(imgURL);
        
    } else {
        System.err.println("Icone nao encontrado: " + path);
        return null;
    }
}
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
            java.util.logging.Logger.getLogger(PrincipalGerenteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalGerenteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalGerenteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalGerenteView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                try {
                    new PrincipalGerenteView(null, null).setVisible(true);
                    
                } catch (SQLException ex) {
                    Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NaoEncontradoException ex) {
                    Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonCaixa;
    private javax.swing.JButton jButtonCliente;
    private javax.swing.JButton jButtonContasPagar;
    private javax.swing.JButton jButtonContasReceber;
    private javax.swing.JButton jButtonEstoque;
    private javax.swing.JButton jButtonFinanceiro;
    private javax.swing.JButton jButtonProduto;
    private javax.swing.JButton jButtonVenda;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelDataAbertura;
    private javax.swing.JLabel jLabelLegendaDataAberturaFechamento;
    private javax.swing.JLabel jLabelLegendaNome;
    private javax.swing.JLabel jLabelRelogio;
    private javax.swing.JLabel jLabelStatusCaixa;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuCaixa;
    private javax.swing.JMenuItem jMenuCompra;
    private javax.swing.JMenuItem jMenuEstoque;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItemCadastroCliente;
    private javax.swing.JMenuItem jMenuItemCadastroFornecedor;
    private javax.swing.JMenuItem jMenuItemCadastroFuncionário;
    private javax.swing.JMenuItem jMenuItemCadastroProduto;
    private javax.swing.JMenuItem jMenuItemCliente;
    private javax.swing.JMenuItem jMenuItemFornecedor;
    private javax.swing.JMenuItem jMenuItemFuncionario;
    private javax.swing.JMenuItem jMenuItemProduto;
    private javax.swing.JMenuItem jMenuItemSairLogin;
    private javax.swing.JMenuItem jMenuItemSairSistema;
    private javax.swing.JMenu jMenuMovimentacao;
    private javax.swing.JMenu jMenuSair;
    private javax.swing.JMenuItem jMenuVenda;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelRodape;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTablerResumoEstoque;
    // End of variables declaration//GEN-END:variables
    class hora implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
        
    Calendar now = Calendar.getInstance();
        jLabelRelogio.setText(String.format("%1$tH:%1$tM:%1$tS", now));
        
     }
    }
    
}


