/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Control.Exceptions.NaoEncontradoException;
import DAO.CaixaDAO;
import DAO.Conexao;
import DAO.EstoqueDAO;
import DAO.FuncionarioDAO;
import DAO.ItemOrcamentoDAO;
import DAO.ItemVendaDAO;
import DAO.OrcamentoDAO;
import DAO.VendaDAO;
import Modelo.CaixaModel;
import Modelo.ClienteModel;
import Modelo.EstoqueModel;
import Modelo.FuncionarioModel;
import Modelo.ItemOrcamento;
import Modelo.ItemVenda;
import Modelo.OrcamentoModel;
import Modelo.ProdutoModel;
import Modelo.VendaModel;
import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRResultSetDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author pedro
 */
public class FinalizarVenda extends javax.swing.JDialog {

    List<ItemVenda>itensVenda = new ArrayList<>();
    ClienteModel clienteModel = new ClienteModel();
    int tipo, formaPagamento;
    double valorTotalPagar;
    double totalDesconto;
    OrcamentoModel orcamento = new OrcamentoModel();
    Venda venda;
    String tela;
    FuncionarioModel funcionario;
    public FinalizarVenda(java.awt.Frame parent, boolean modal, Venda venda,
        ClienteModel clienteModel, List<ItemVenda>itensVenda, OrcamentoModel orcamento, int tipo, String tela, int formaPagamento, double valorTotalPagar){
        super(parent, modal);
        initComponents();
        Locale localBrasil = new Locale("pt", "BR");
 
        System.out.println("VALOR "+ valorTotalPagar);
        for(ItemVenda i : itensVenda){
            System.out.println("QUANTIDADES "+i.getQuantidade());
        }
        this.orcamento = orcamento;
        this.tela = tela;
        this.clienteModel = clienteModel;
        this.itensVenda = itensVenda;
        teclaAtalho(); 
        this.formaPagamento = formaPagamento;
        this.tipo = tipo;
        this.valorTotalPagar = valorTotalPagar;
        this.venda = venda;
        
       
        jTextFieldDesconto.setDocument(new FormatacaoDinamicaDinheiro());
        jTextFieldValorRecebido.setDocument(new FormatacaoDinamicaDinheiro());
        
        jLabelTroco.setText("0,00");
        // 0 é venda, 1 é orçamento
        if(tipo == 0){
            jLabelTitulo.setText("Finalizar Venda");
            jButtonFinalizar.setText("[F8] Finalizar Venda");
        }else if(tipo == 1){
            jLabelTitulo.setText("Finalizar Orçamento");
            jButtonFinalizar.setText("[F8] Finalizar Orçamento");
        }
        
        String formaPagamentoString = "";
        pesquisarFuncioanrioLogin();
        if(formaPagamento == 0){
            formaPagamentoString = "Dinheiro";
            jPanelTroco.setVisible(true);
            jPanelValorRecebido.setVisible(true);
        }else{
            formaPagamentoString = "Cartão";
            jPanelTroco.setVisible(false);
            jPanelValorRecebido.setVisible(false);
            
        }
        if(tipo == 1){
            jPanelTroco.setVisible(false);
            jPanelValorRecebido.setVisible(false);
        }
        
        String  valorTotalString = NumberFormat.getCurrencyInstance(localBrasil).format(this.valorTotalPagar);
        valorTotalString = valorTotalString.replace("R$", "");
        jLabelTotal.setText(valorTotalString);
        jLabelValorVenda.setText(valorTotalString);
        jLabelFormaPagamento.setText(formaPagamentoString);
        
        jTextFieldDesconto.setText("0");
        jTextFieldValorRecebido.setText("0");
        
    }
     public void novaVenda(){
         
         this.venda.novaVenda();
         
     }
     
     public void sairOrcamento(){
         
         this.venda.sairParaOrcamento();
     
     }
     public void pesquisarFuncioanrioLogin(){
        
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
            try {
                this.funcionario = funcionarioDao.pesquisarDataHoralogin();
            } catch (SQLException ex) {
                Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
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
                        {
                            try {
                                concluirVenda();
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(FinalizarVenda.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
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
                            //concluirVenda();
                            break;
                            
//                        case 27:
//                             //sair();
//                            break;
//                        case KeyEvent.VK_SPACE:
//                            String quantidadeString;
//         
//                            quantidadeString = jTextFieldQuantidade.getText(); //Nao faz parte dos objetos
//
//                            adicionarTabelaProduto(quantidadeString);
//                            jTextFieldDesconto.requestFocus();
//                            
//                            break;
                            
                        default:
                            break;
                    }  
                        return;
                }
            }
        }, AWTEvent.KEY_EVENT_MASK);
    }
    
      public void concluirVenda() throws ClassNotFoundException{
        
        String tipoPagamento = null, totalString, valorString, descontoString, valorRecebidoString;
        double desconto, valor;
        int quantidade, quantidadeEstoque, id;
        
        List<ItemVenda>listaItensVenda = new ArrayList<>();
        
        EstoqueDAO estoqueDao = new EstoqueDAO();
        EstoqueModel estoqueModel = new EstoqueModel();

        CaixaModel caixaModel = new CaixaModel();
        CaixaDAO caixaDao = new CaixaDAO();
        listaItensVenda = this.itensVenda;
        
        double quantidadeEstoques = 0;
        OrcamentoModel orcamentoModel = new OrcamentoModel();
        OrcamentoDAO orcamentoDao = new OrcamentoDAO();

        ItemOrcamento itemOrcamento = new ItemOrcamento();
        ItemOrcamentoDAO itemOrcamentoDao = new ItemOrcamentoDAO();
        Class.forName("com.mysql.cj.jdbc.Driver");
        if(this.tipo==0){
            
                if(this.clienteModel != null){
                    
                    VendaModel vendaModel = new VendaModel();
                    VendaDAO vendaDao = new VendaDAO();
        
                    ItemVendaDAO itemVendaDao = new ItemVendaDAO();
                    
                            
                    if(formaPagamento == 0){
                        tipoPagamento = "D"; //Dinheiro
                     }else if(formaPagamento == 1){
                        tipoPagamento = "C"; //Cartao   
                        
                     } 
                       
                    if(jTextFieldDesconto.getText()==null || jTextFieldDesconto.getText().equals("")){
                        this.totalDesconto = desconto = 0.0;
                    }
                    
                    Date data = new Date(System.currentTimeMillis());
                    Time hora = new Time(System.currentTimeMillis());
                    data.toLocalDate();
                    hora.toLocalTime();
                    valor = this.valorTotalPagar;
                    FuncionarioModel funcionario = this.funcionario;
            
                    try {
                        
                    caixaModel = caixaDao.pesquisarCaixa();
                    
                    vendaModel.setCaixaModel(caixaModel);        
                    vendaModel.setClienteModel(this.clienteModel);           
                    vendaModel.setDataVenda(data);  
                    vendaModel.setHoraVenda(hora);
                    vendaModel.setDesconto(this.totalDesconto);
                    vendaModel.setFormaPagamento(tipoPagamento);     
                    vendaModel.setFuncionario(funcionario);
                    vendaModel.setValor(valor);
                    vendaDao.inserir(vendaModel);
                    vendaModel = vendaDao.ultimaVanda2();
                    
                    for(ItemVenda i : listaItensVenda){
                
                        i.setVenda(vendaModel);
                        
                    }
                    for(ItemVenda i : listaItensVenda){
          
                    itemVendaDao.inserir(i);

                    }
                    
                     //ALTERAR VALOR DO CAIXA
                        this.valorTotalPagar = valorTotalPagar + caixaModel.getValorFinal();
                        caixaModel.setValorFinal(this.valorTotalPagar);
                        caixaDao.alterarValorFinal(caixaModel);
                    
                    int b = JOptionPane.showConfirmDialog(null, "Venda Realizada, Deseja imprimir cupom ?", "Sucesso",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE);
                    
                    if(b == 0){
                        

                        //RELATORIO
                        JDialog viewer = new JDialog(new javax.swing.JFrame(),"", true);
                        viewer.setSize(800,600);
                        viewer.setLocationRelativeTo(null);
                        //Class.forName("com.mysql.cj.jdbc.Driver");
                        java.sql.Connection conexao = Conexao.getConexao();
                        String pastaRelatorio = "C:\\Users\\pedro\\Documents\\NetBeansProjects\\TrabalhoConclusaoCursoPedroTenorio\\src\\Relatorio\\CupomVenda.jrxml";
                        Map<String ,Object> parameters = new HashMap<>();
                        vendaModel = vendaDao.ultimaVanda2();
                        parameters.put("VendaID", vendaModel.getId());
                        JasperReport jr2 = JasperCompileManager.compileReport(pastaRelatorio);
                        JasperPrint jasperPrint = JasperFillManager.fillReport(jr2, parameters, conexao);
                        JasperViewer jrViewer = new JasperViewer(jasperPrint, true);
                        viewer.getContentPane().add(jrViewer.getContentPane());
                        viewer.setVisible(true);
                        conexao.close();
                        
                        novaVenda();
                        super.dispose();
                        
                    }else{
                        
                        novaVenda();
                        super.dispose();
                        
                    }
                    } catch (SQLException ex) {
                        Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NaoEncontradoException ex) {
                        Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JRException ex) {
                        Logger.getLogger(FinalizarVenda.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }else{
                JOptionPane.showMessageDialog(null, "Informe o cliente para concluir venda","Alerta",JOptionPane.WARNING_MESSAGE);
            }
            
        }else{
            tipoPagamento = "D";
            //ORÇAMENTO
            // VERIFICAR SE É ALTERACAO OU CADASTRO DE UM NOVO ORCAMENTO   
            
            //1 - VERIFICAR DE ONDE ESTA VINDO, TELA PRINCIPAL SEM PARAMETRO, ALTERAR ORCAMENTO TELA PRINCIPAL,
            // ALTERAR ORCAMENTO TELA ORCAMENTO, NOVO ORCAMENTO TELA ORCAMENTO;
            
            //TELA PRINCIPAL
           // if(tela.equals("")){
                if(jTextFieldDesconto.getText()==null || jTextFieldDesconto.getText().equals("")){
                      this.totalDesconto = desconto = 0.0;
                    }
                    //DATA DE HOJE 
                    Timestamp data = new Timestamp(System.currentTimeMillis());
                    data.toLocalDateTime();
                    
                    LocalDateTime today = LocalDateTime.now(); 
                    LocalDateTime dias = today.plusDays(5);
                    //DATA VENCIMENTO
                    Timestamp vencimento = Timestamp.valueOf(dias);
                    
                    valor = this.valorTotalPagar;
                    FuncionarioModel funcionario = this.funcionario;
                    
                //VERIRIFICA SE VAI ALTERAR OU INSERIR ORCAMENTO
                // SE O ORCAMENTO FOR DIFERENTE DE ZERO ELE ALTERA SE NAO CADASTRA
                 
                if(orcamento == null){
                    
                   try {
                    
                    orcamentoModel.setCliente(this.clienteModel);
                    orcamentoModel.setData(data);
                    orcamentoModel.setDataAprovacao(data);
                    orcamentoModel.setDataVencimento(vencimento);
                    orcamentoModel.setFormaPagamento(tipoPagamento);
                    orcamentoModel.setFuncionario(this.funcionario);
                    orcamentoModel.setValorTotal(valor);
                    orcamentoModel.setDesconto(this.totalDesconto);
                    orcamentoDao.inserir(orcamentoModel);
                    
                     orcamentoModel = orcamentoDao.ultimoOrcamento();
                    
                    for(ItemVenda i : listaItensVenda){
                        
                        itemOrcamento.setDescricao(i.getDescricao());
                        itemOrcamento.setOrcamento(orcamentoModel);
                        itemOrcamento.setEstoque(i.getEstoque());
                        itemOrcamento.setQuantidade(i.getQuantidade());
                        itemOrcamento.setUnidadeMedida(i.getUnidadeMedida());
                        itemOrcamento.setValorUnitario(i.getValor());
                        
                        itemOrcamentoDao.inserir(itemOrcamento);
                        
                        estoqueModel = estoqueDao.pesquisaEstoqueCodigoBarras(i.getEstoque().getProduto().getCodigoBarra());
                
                        quantidadeEstoques = quantidadeEstoques + estoqueModel.getQtdEstoque();
                        
                        estoqueModel.setQtdEstoque(quantidadeEstoques);
                        
                        estoqueModel.setFuncionario(this.funcionario);

                        estoqueDao.alteraQuantidade(estoqueModel);
                        }
                    
                         int b = JOptionPane.showConfirmDialog(null, "Orçamento Realizado, Deseja imprimir cupom ?", "Sucesso",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.INFORMATION_MESSAGE);
                    
                    if(b == 0){
                            
                            JDialog viewer2 = new JDialog(new javax.swing.JFrame(),"", true);
                            viewer2.setSize(800,600);
                            viewer2.setLocationRelativeTo(null);
                           
                            java.sql.Connection conexao2 = Conexao.getConexao();
                            String pastaRelatorios2 = "C:\\Users\\pedro\\Documents\\NetBeansProjects\\TrabalhoConclusaoCursoPedroTenorio\\src\\Relatorio\\RelatorioOrcamento.jrxml";
                            Map<String ,Object> parameters2 = new HashMap<>();
                            orcamentoModel = orcamentoDao.ultimoOrcamento();
                            parameters2.put("OrcamentoID",orcamentoModel.getOrc_id());
                            JasperReport jr3 = JasperCompileManager.compileReport(pastaRelatorios2);
                            JasperPrint jasperPrint2 = JasperFillManager.fillReport(jr3, parameters2, conexao2);
                            JasperViewer jrViewer2 = new JasperViewer(jasperPrint2, true);
                            viewer2.getContentPane().add(jrViewer2.getContentPane());
                            viewer2.setVisible(true);
                            
                            conexao2.close();

                            novaVenda();
                            super.dispose();
                            
                        }else{

                            novaVenda();
                            super.dispose();

                        }
                    
                    
//                        JOptionPane.showMessageDialog(null, "Orçamento Realizado!", "Sucesso",
//                        JOptionPane.INFORMATION_MESSAGE);
//                        
//                        if(tela.equals("")){
//                            novaVenda();
//                        }else{
//                            sairOrcamento();
//                        }
//                        
//                        super.dispose();
                        
                   
                     } catch (SQLException ex) {
                         
                        Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                        Logger.getLogger(FinalizarVenda.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NaoEncontradoException ex) {
                        Logger.getLogger(FinalizarVenda.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JRException ex) {
                    Logger.getLogger(FinalizarVenda.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                }else{
                    try {
                        //1 exclui todo itensOrcamento de um orcamento
                        itemOrcamentoDao.excluirItemOrcamento(orcamento.getOrc_id());
                        // adiciona nova lista atualizada
                        for(ItemVenda i : listaItensVenda){
                        
                        itemOrcamento.setDescricao(i.getDescricao());
                        itemOrcamento.setOrcamento(this.orcamento);
                        itemOrcamento.setEstoque(i.getEstoque());
                        itemOrcamento.setQuantidade(i.getQuantidade());
                        itemOrcamento.setUnidadeMedida(i.getUnidadeMedida());
                        itemOrcamento.setValorUnitario(i.getValor());
                        
                        itemOrcamentoDao.inserir(itemOrcamento);
                       
                        estoqueModel = estoqueDao.pesquisaEstoqueCodigoBarras(i.getEstoque().getProduto().getCodigoBarra());
                        
                        //quantidadeEstoques = quantidadeEstoques + estoqueModel.getQtdEstoque();
                        
                        //estoqueModel.setQtdEstoque(quantidadeEstoques);
                        estoqueModel.setFuncionario(this.funcionario);

                        estoqueDao.alteraQuantidade(estoqueModel);
                        
                        }
                        
                        JOptionPane.showMessageDialog(null, "Orçamento Realizado!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                        
                        if(tela.equals("")){
                            novaVenda();
                        }else{
                            sairOrcamento();
                        }
                        
                        super.dispose();
                        
                      
                     } catch (SQLException ex) {
                         System.out.println(ex);
                        Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ParseException ex) {
                    Logger.getLogger(FinalizarVenda.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NaoEncontradoException ex) {
                    Logger.getLogger(FinalizarVenda.class.getName()).log(Level.SEVERE, null, ex);
                }
                    
            }
            
        }
    
        novaVenda();
    }
    
     
      
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jTextFieldDesconto = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jComboBoxDesconto = new javax.swing.JComboBox<>();
        jPanelValorRecebido = new javax.swing.JPanel();
        jTextFieldValorRecebido = new javax.swing.JTextField();
        jPanelTroco = new javax.swing.JPanel();
        jLabelTroco = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabelValorVenda = new javax.swing.JLabel();
        jLabelFormaPagamento = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabelTotal = new javax.swing.JLabel();
        jButtonFinalizar = new javax.swing.JButton();
        jButtonVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Concluir Venda");

        jPanel1.setBackground(new java.awt.Color(3, 133, 188));

        jLabelTitulo.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Finalizar Venda");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelTitulo)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Desconto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jTextFieldDesconto.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jTextFieldDesconto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDescontoActionPerformed(evt);
            }
        });
        jTextFieldDesconto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldDescontoKeyReleased(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBoxDesconto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "R$", "%" }));
        jComboBoxDesconto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxDescontoItemStateChanged(evt);
            }
        });
        jComboBoxDesconto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDescontoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBoxDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        jPanelValorRecebido.setBackground(new java.awt.Color(255, 255, 255));
        jPanelValorRecebido.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Valor Recebido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jTextFieldValorRecebido.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jTextFieldValorRecebido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldValorRecebidoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanelValorRecebidoLayout = new javax.swing.GroupLayout(jPanelValorRecebido);
        jPanelValorRecebido.setLayout(jPanelValorRecebidoLayout);
        jPanelValorRecebidoLayout.setHorizontalGroup(
            jPanelValorRecebidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelValorRecebidoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldValorRecebido)
                .addContainerGap())
        );
        jPanelValorRecebidoLayout.setVerticalGroup(
            jPanelValorRecebidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTextFieldValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, 47, Short.MAX_VALUE)
        );

        jPanelTroco.setBackground(new java.awt.Color(255, 255, 255));
        jPanelTroco.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Troco", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jLabelTroco.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabelTroco.setText("jLabel1");

        javax.swing.GroupLayout jPanelTrocoLayout = new javax.swing.GroupLayout(jPanelTroco);
        jPanelTroco.setLayout(jPanelTrocoLayout);
        jPanelTrocoLayout.setHorizontalGroup(
            jPanelTrocoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTrocoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTroco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelTrocoLayout.setVerticalGroup(
            jPanelTrocoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTrocoLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabelTroco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Informações da Venda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jLabel3.setText("Valor da Venda:");

        jLabel4.setText("Forma de Pagamento:");

        jLabelValorVenda.setText("jLabel7");

        jLabelFormaPagamento.setText("jLabel8");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabelValorVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(jLabelFormaPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabelValorVenda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabelFormaPagamento))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Valor Total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jLabelTotal.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabelTotal.setText("jLabel5");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jLabelTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelValorRecebido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelTroco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelValorRecebido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelTroco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jButtonFinalizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Concluir30x30.png"))); // NOI18N
        jButtonFinalizar.setText("[F2] Finalizar Venda");
        jButtonFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinalizarActionPerformed(evt);
            }
        });

        jButtonVoltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/voltar24x24.png"))); // NOI18N
        jButtonVoltar.setText("[F1] Voltar");
        jButtonVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonVoltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jButtonVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonFinalizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonFinalizar, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                    .addComponent(jButtonVoltar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinalizarActionPerformed
        try {
            concluirVenda();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FinalizarVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.dispose();
    }//GEN-LAST:event_jButtonFinalizarActionPerformed

    private void jButtonVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonVoltarActionPerformed
        super.dispose();
    }//GEN-LAST:event_jButtonVoltarActionPerformed

    private void jTextFieldDescontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDescontoKeyReleased
       
       Locale localBrasil = new Locale("pt", "BR");
      
       int index = jComboBoxDesconto.getSelectedIndex();
       
       //DESCONTO MONETARIO
       if(!jTextFieldValorRecebido.equals("0,00")){
       if(index == 0){
           
        String descontoString , valorTotalString,totalPagarString;
        double desconto, valorTotal;
        
        if(jTextFieldDesconto.getText()!=null){
        
            valorTotal = this.valorTotalPagar;
            
            descontoString = jTextFieldDesconto.getText();
            descontoString = descontoString.replace(",", ".");
            descontoString = descontoString.replace(".", "");
            desconto = Double.parseDouble(descontoString);
            desconto = desconto/100;
            
        if(desconto < valorTotal){
            
            valorTotal = valorTotal - desconto;
            // possivelmente colocar valorTotal no this.valorTotalPagar
            totalPagarString = String.valueOf(valorTotal);
        
            String valorInicial = NumberFormat.getCurrencyInstance(localBrasil).format(valorTotal);
            valorInicial = valorInicial.replace("R$", "");
            jLabelTotal.setText(valorInicial); 
            
            this.totalDesconto = desconto;
            
        }else{
            evt.consume();
        }
        
        }else{
            
            jTextFieldDesconto.setText("0");
        
        }
           
        } else {
           
             String descontoString, valorTotalString,totalPagarString;
             double desconto, valorTotal, divisao, fatorMultiplicador;
             
            if (jTextFieldDesconto.getText() != null) {
            
                valorTotal = this.valorTotalPagar;
                
                descontoString = jTextFieldDesconto.getText();
       

                descontoString = descontoString.replace(",", ".");
                descontoString = descontoString.replace(".", "");
                
                desconto = Double.parseDouble(descontoString);
               
                double valorDesconto = valorTotal * desconto;
                valorDesconto = valorDesconto / 100;
                
             if(valorDesconto < valorTotal){
                    this.totalDesconto = valorDesconto;
                    valorDesconto = valorTotal - valorDesconto;

                    String valorInicial2 = NumberFormat.getCurrencyInstance(localBrasil).format(valorDesconto);
                    valorInicial2 = valorInicial2.replace("R$", "");
                    jLabelTotal.setText(valorInicial2); 

                    
                    
                }else{
                  evt.consume();
                 
                }    
                
      
            }else{
                jTextFieldDesconto.setText("0");
            }

        }
       }else{
           jTextFieldValorRecebido.setText("0");
       }
       
    }//GEN-LAST:event_jTextFieldDescontoKeyReleased

    private void jComboBoxDescontoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxDescontoItemStateChanged
        Locale localBrasil = new Locale("pt", "BR");

        if(jComboBoxDesconto.getSelectedIndex() == 0){

            //jLabelLegendaValorDesconto.setText("Informe o desconto em porcentagem");
            jTextFieldDesconto.setDocument(new FormatacaoDinamicaDinheiro());
            jTextFieldDesconto.setText("0");
            //this.valorTotalPagar = this.valorTotalReferencia;
            
            String valorInicial = NumberFormat.getCurrencyInstance(localBrasil).format(this.valorTotalPagar);
            valorInicial.replaceAll("R$", "");
            jLabelTotal.setText(valorInicial);
            jTextFieldValorRecebido.setText("0");
            
            jLabelTroco.setText("0,00");
            this.totalDesconto = 0.0;

        }else{

           // jLabelLegendaValorDesconto.setText("Informe o desconto em valor");
            jTextFieldDesconto.setDocument(new FormatacaoDinamicaQuantidadeUnid());
            jTextFieldDesconto.setText("0");
            //this.valorTotalPagar = this.valorTotalReferencia;
            String valorInicial = NumberFormat.getCurrencyInstance(localBrasil).format(this.valorTotalPagar);
            jLabelTotal.setText(valorInicial);
            jTextFieldValorRecebido.setText("0");
            jLabelTroco.setText("0,00");
            this.totalDesconto = 0.0;
        }

    }//GEN-LAST:event_jComboBoxDescontoItemStateChanged

    private void jTextFieldValorRecebidoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldValorRecebidoKeyReleased
       
            
    if(!this.itensVenda.isEmpty()){     
       Locale localBrasil = new Locale("pt", "BR");
       String descontoString, valorRecebidoString, valorTotalString, valorPagamento, totalPagarString;
       double valorTotal, totalPagar, desconto, valorRecebido;
       
       valorTotal = this.valorTotalPagar;
     
       valorTotal = valorTotal - this.totalDesconto; 
         //valorTotal = valorTotal/100;

        valorRecebidoString = jTextFieldValorRecebido.getText();
        valorRecebidoString = valorRecebidoString.replace(",", ".");
        valorRecebidoString = valorRecebidoString.replace(".","");

        valorRecebido = Double.parseDouble(valorRecebidoString);

        valorRecebido = valorRecebido/100;

        if(valorTotal <= valorRecebido){
            
            valorRecebido = valorRecebido - valorTotal;
        
            
            String valorInicial = NumberFormat.getCurrencyInstance(localBrasil).format(valorRecebido);
            
            jLabelTroco.setText(valorInicial.replace("R$", ""));
            
        }else{
            
            jLabelTroco.setText("0,00");
        
        }
    }
        
    }//GEN-LAST:event_jTextFieldValorRecebidoKeyReleased

    private void jTextFieldDescontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDescontoActionPerformed
       
        jTextFieldValorRecebido.requestFocus();
        
        
    }//GEN-LAST:event_jTextFieldDescontoActionPerformed

    private void jComboBoxDescontoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDescontoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxDescontoActionPerformed

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
            java.util.logging.Logger.getLogger(FinalizarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FinalizarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FinalizarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FinalizarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FinalizarVenda dialog = new FinalizarVenda(new javax.swing.JFrame(), true, null, null, null, null, 0,"",0,0.0);
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
    private javax.swing.JButton jButtonFinalizar;
    private javax.swing.JButton jButtonVoltar;
    private javax.swing.JComboBox<String> jComboBoxDesconto;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelFormaPagamento;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JLabel jLabelTroco;
    private javax.swing.JLabel jLabelValorVenda;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanelTroco;
    private javax.swing.JPanel jPanelValorRecebido;
    private javax.swing.JTextField jTextFieldDesconto;
    private javax.swing.JTextField jTextFieldValorRecebido;
    // End of variables declaration//GEN-END:variables
}
