/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Control.Exceptions.NaoEncontradoException;
import DAO.CaixaDAO;
import DAO.ItemVendaDAO;
import DAO.EntradaDAO;
import DAO.FuncionarioDAO;
import DAO.RetiradaDAO;
import DAO.VendaDAO;
import Modelo.CaixaModel;
import Modelo.FuncionarioModel;
import Modelo.ItemVenda;
import Modelo.EntradaModel;
import Modelo.RetiradaModel;
import Modelo.VendaModel;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author pedro
 */
public class ControleCaixaView extends javax.swing.JFrame {
    FuncionarioModel funcionarioLoginModel;
    PrincipalGerenteView p;
    PrincipalFuncionarioView f;
    int telaAtual;
    double valorTotal;
    String horaCompletaPesquisa ,minutoPesquisa, horaPesquisa;
    
    ArrayList<VendaModel> listaVenda = new ArrayList<>();
    ArrayList<RetiradaModel> listaRetirada = new ArrayList<>();
    ArrayList<EntradaModel> listaReposicao = new ArrayList<>();
    
    public ControleCaixaView(FuncionarioModel funcionarioModel, PrincipalGerenteView p, PrincipalFuncionarioView f) {
        initComponents();
        
         jTextAreaDescricao.setLineWrap(true);
        this.minutoPesquisa = "";
        this.horaPesquisa = "";
        this.funcionarioLoginModel = funcionarioModel;
        this.p = p;
        this.f = f;
         Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {
            
            public void eventDispatched(AWTEvent event) {
                KeyEvent ev = (KeyEvent) event;
                
                if (ev.getID() == KeyEvent.KEY_RELEASED) {
                    
                    switch (ev.getKeyCode()) {
                        case KeyEvent.VK_F1:
                            //preencheTabela();
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
                             //sair();
                            break;
                        default:
                            break;
                    }  
                        return;
                }
            }
        }, AWTEvent.KEY_EVENT_MASK);
          
        historicoMovimentacaoCaixa();
        
        //BOTOES E TELAS
        jTabbedPane1.setSelectedIndex(0);
        jTabbedPane1.setEnabledAt(0, true); //ALTERA STATUS TELA ATUAL
        jTabbedPane1.setEnabledAt(1, false);
        jTabbedPane1.setEnabledAt(2, false);
        jButtonCancelar.setEnabled(false);
         
        reset(); 
    }

    
    public void verificacao (){
        
        this.telaAtual = jTabbedPane1.getSelectedIndex();
        
        CaixaDAO caixaDao = new CaixaDAO();
        CaixaModel caixaModel = new CaixaModel();
        ArrayList<CaixaModel> listaCaixa = new ArrayList<>();
        ArrayList <Integer> caixaNaoFinalozado;
            
        try {
            
            boolean abrirCaixa, fecharCaixa, excluirLancamento, historico,
            consultar, cancelar, repor, retirar;
            caixaModel = caixaDao.pesquisarCaixa();
            listaCaixa = caixaDao.findAll();
            
            for(CaixaModel caixa : listaCaixa){
                
                if(caixa.getFinaliza() == false){
                    
                  jTableConsultaHistoricoCaixa.isRowSelected(caixa.getId());
                
                }
                
            }
            
            
            jTabbedPane1.setSelectedIndex(1);
            jTabbedPane1.setEnabledAt(0, false); //TELA PRINCIPAL
            jTabbedPane1.setEnabledAt(1, true); //TELA HISTORICO CAIXA
            jTabbedPane1.setEnabledAt(2, false); // TELA PESQUISA 
      
            alteraBotoes(abrirCaixa=true, fecharCaixa=true,
                         historico=false, consultar=true,
                         cancelar=true, repor=false,
                         retirar=false, caixaModel);
            
        } catch (SQLException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public void reset(){
        
        Locale localBrasil = new Locale("pt", "BR");
        CaixaDAO caixaDao = new CaixaDAO();
        CaixaModel caixaModel = new CaixaModel();
        
        //PESQUISA RESET
        
        jComboBoxCategoria.setVisible(true);

        //CONSULTA
        
        jTextAreaDescricao.setEditable(false);
        jTextFieldCategoria.setEditable(false);
        jTextFieldValor.setEditable(false);
        jTextFieldFuncionario.setEditable(false);
        jTextFieldData.setEditable(false);
        jTextFieldHora.setEditable(false);
        jTextFieldTipoPagamento.setEditable(false);
        jTextFieldCliente.setEditable(false);
        jTextFieldDesconto.setEditable(false);
        jTextAreaJustificativa.setEditable(false);
        
        try {
            caixaModel = caixaDao.pesquisarCaixa();
            //SE O CAIXA ESTIVER ABERTO
            
            jComboBoxCategoria.setVisible(true);
            if (caixaModel.getSituacao().equals("aberto")) {

                jButtonAbrirCaixa.setEnabled(false);
                jButtonFecharCaixa.setEnabled(true);
                jButtonReporValor.setEnabled(true);
                jButtonRetirarValor.setEnabled(true);
                jButtonConsulta.setEnabled(true);
                atualizaTabela();
               
                String valorAtual = NumberFormat.getCurrencyInstance(localBrasil).format(caixaModel.getValorFinal());
                jLabelResumoSaldoAtual.setText(valorAtual);
                String valorInicial = NumberFormat.getCurrencyInstance(localBrasil).format(caixaModel.getTroco());
                
                jLabelResumoSaldoInicial.setText(valorInicial);
               
            } //SE CAIXA ESTIVER FECHADO
            else if(caixaModel.getSituacao().equals("fechado")){

                jButtonAbrirCaixa.setEnabled(true);
                jButtonFecharCaixa.setEnabled(false);
                jButtonReporValor.setEnabled(false);
                jButtonRetirarValor.setEnabled(false);
                jButtonConsulta.setEnabled(false);
                
                jLabelResumoNomeStatusCaixa.setText("");
                jLabelResumoQuantidadeVenda.setText("0");
                jLabelResumoSaldoAtual.setText("0.00");
                jLabelResumoSaldoInicial.setText("0.00");
             
                jLabelResumoTotalCartao.setText("0.00");
                jLabelResumoTotalDesconto.setText("0.00");
                jLabelResumoTotalDinheiro.setText("0.00");
                jLabelResumoTotalReposto.setText("0.00");
                jLabelResumoTotalRetiradas.setText("0.00");
                
                jLabelResumoConsultaCatao.setText("0.00");
                jLabelResumoConsultaDataAbertura.setText("");
                jLabelResumoConsultaDataFechamento.setText("");
                jLabelResumoConsultaDesconto.setText("0.00");
                jLabelResumoConsultaDinheiro.setText("0.00");
                jLabelResumoConsultaEntrada.setText("0.00");
                jLabelResumoConsultaQtdVenda.setText("0");
                jLabelResumoConsultaRetirada.setText("0.00");
                jLabelResumoConsultaValorInicial.setText("0.00");
                jLabelResumoConsultaValorFinal.setText("0.00");
                
                DefaultTableModel val = (DefaultTableModel) jTableCaixaDiario.getModel();
                val.setNumRows(0);
            }
            mudaStatus(caixaModel);

        } catch (SQLException ex) {

            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);

        } catch (NaoEncontradoException ex) {
            
                jLabelCaixaStatus.setText("Caixa Inativo");
                jPanelFundoStatusCaixa.setBackground(Color.blue);
                jLabelResumoLegendaMensagemStatusCaixa.setText("Efetue a Abertura de Caixa");
                jButtonAbrirCaixa.setEnabled(true);
                jButtonFecharCaixa.setEnabled(false);

                jLabelResumoNomeStatusCaixa.setText("");
                jLabelResumoQuantidadeVenda.setText("0");
                jLabelResumoSaldoAtual.setText("0.00");
                jLabelResumoSaldoInicial.setText("0.00");
                
                jLabelResumoTotalCartao.setText("0.00");
                jLabelResumoTotalDesconto.setText("0.00");
                jLabelResumoTotalDinheiro.setText("0.00");
                jLabelResumoTotalReposto.setText("0.00");
                jLabelResumoTotalRetiradas.setText("0.00");
                
                DefaultTableModel val = (DefaultTableModel) jTableCaixaDiario.getModel();
                val.setNumRows(0);
            
        }

    }

    public void historicoMovimentacaoCaixa() {

        CaixaDAO caixaDao = new CaixaDAO();
        CaixaModel caixaModel = new CaixaModel();
        ArrayList<CaixaModel> listaCaixa = new ArrayList<>();
          Locale localBrasil = new Locale("pt", "BR");
        try {
            
            listaCaixa = caixaDao.findAll();
            DefaultTableModel val = (DefaultTableModel) jTableHistoricoCaixa.getModel();
            
            val.setNumRows(0);                                              
                
                ImageIcon icone = new ImageIcon();
                //imagemFinalizado.setIcon(icone);
                String status = "";
                for (CaixaModel caixa : listaCaixa) {
                    
                    if(caixa.getSituacao().equals("fechado")){    
                         
                        if(caixa.getFinaliza() == false){
                         
                           status = " ";
                        }else{
                            
                          status = "";
                        }
                        String data = new SimpleDateFormat("dd/MM/yyyy").format(caixa.getDataAbertura());
                        String horaInicio = new SimpleDateFormat("HH:mm:ss").format(caixa.getDataAbertura());
                        String horaFim = new SimpleDateFormat("HH:mm:ss").format(caixa.getDataFechamento());
                        
                      
                        String valorTotalString = NumberFormat.getCurrencyInstance(localBrasil).format(caixa.getValorFechamento());
                        val.addRow(new Object[]{data, horaInicio, horaFim, caixa.getFuncionarioAberturaCaixa(), caixa.getFuncionarioModel().getNome(), valorTotalString, status});
                    
                }
                jTableHistoricoCaixa.getColumn("Situação").setCellRenderer(new tableRenderer(icone, status));
                jTableHistoricoCaixa.addMouseListener(new MouseAdapter() { 
               
                public void mouseClicked(MouseEvent evt) 
                        { 
                            if (evt.getClickCount() == 2){
                                  finalizar();  
                            } 
                        }
                });
                
                     
                
        }
        } catch (SQLException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        }

    
    }

    public void resumoMovimentacaoCaixa(double quantidadeDesconto, double quantidadeCartao, double quantidadeDinheiro,
            double quantidadeTotalRetirada, double quantidadeReposto, int quantidadeTotalVenda) {
        
        DecimalFormat df = new DecimalFormat(",##0,00");
        Locale localBrasil = new Locale("pt", "BR");
        
        String quantidadeTotalVendaString, quantidadeDescontoString, quantidadeCartaoString,
        quantidadeDinheiroString, quantidadeTotalRetiradaString, quantidadeTotalRepostoString;
        
        quantidadeTotalVendaString = String.valueOf(quantidadeTotalVenda);
        quantidadeDescontoString = NumberFormat.getCurrencyInstance(localBrasil).format(quantidadeDesconto);
        quantidadeCartaoString = NumberFormat.getCurrencyInstance(localBrasil).format(quantidadeCartao);
        quantidadeDinheiroString = NumberFormat.getCurrencyInstance(localBrasil).format(quantidadeDinheiro);
        quantidadeTotalRetiradaString = NumberFormat.getCurrencyInstance(localBrasil).format(quantidadeTotalRetirada);
        quantidadeTotalRepostoString = NumberFormat.getCurrencyInstance(localBrasil).format(quantidadeReposto);
        
        jLabelResumoQuantidadeVenda.setText(quantidadeTotalVendaString);
        jLabelResumoTotalCartao.setText(quantidadeCartaoString);
        jLabelResumoTotalDesconto.setText(quantidadeDescontoString);
        jLabelResumoTotalDinheiro.setText(quantidadeDinheiroString);
        jLabelResumoTotalReposto.setText(quantidadeTotalRepostoString);
        jLabelResumoTotalRetiradas.setText(quantidadeTotalRetiradaString);

    }
    
    
    
    public void setaDialog(int telaAtual, boolean trocaTelaAtual,
            boolean trocaTelaAnterior, String legenda) {

        jTabbedPane1.setSelectedIndex(telaAtual);  //TELA ATUAL 
        jTabbedPane1.setEnabledAt(0, trocaTelaAtual); //ALTERA STATUS TELA ATUAL
        jTabbedPane1.setEnabledAt(1, trocaTelaAnterior); //ALTERA STATUS TELA ANTERIOR 
        jTabbedPane1.setTitleAt(1, legenda); //LEGENDA DA TELA

    }

    public void resetaCampos() {

        jTextFieldCliente.setVisible(true);
        jTextFieldTipoPagamento.setVisible(true);
//        jLabelLegendaCliente.setVisible(true);
//        jLabelLegendaTipoPagamento.setVisible(true);
        
        jTextFieldData.setVisible(true);
        jTextAreaDescricao.setVisible(true);
        jPanelFundoDescricaoConsulta.setVisible(true);
        jTextFieldFuncionario.setVisible(true);
        jTextFieldValor.setVisible(true);
        
        jPanelCampoCategoria.setVisible(true);
        jPanelCampoCliente.setVisible(true);
        jPanelCampoData.setVisible(true);
        jPanelCampoFuncionario.setVisible(true);
        jPanelCampoHora.setVisible(true);
        jPanelCampoTipoPagamento.setVisible(true);
        jPanelCampoValor.setVisible(true);
        
        jTextFieldCliente.setText("");
        jTextFieldTipoPagamento.setText("");
//        jLabelLegendaCliente.setText("");
//        jLabelLegendaTipoPagamento.setText("");
       
        jTextFieldData.setText("");
        jTextAreaDescricao.setText("");
        jTextFieldFuncionario.setText("");
        jTextFieldValor.setText("");
        jTextFieldCategoria.setText("");
        jTextFieldHora.setText("");
        
    }
    
    public void pesquisaDataHora(Timestamp data, Timestamp hora, CaixaModel caixaModel){
        
        VendaDAO vendaDao = new VendaDAO();
        VendaModel vendaModel = new VendaModel();
        RetiradaDAO retiradaDao = new RetiradaDAO();
        EntradaDAO reposicaoDao = new EntradaDAO();
        
        ArrayList<VendaModel> listaVenda = new ArrayList<>();
        ArrayList<RetiradaModel> listaRetirada = new ArrayList<>();
        ArrayList<EntradaModel> listaReposicao = new ArrayList<>();

        try {
            
            listaVenda = vendaDao.pesquisarVenda(caixaModel.getId());
            listaRetirada = retiradaDao.pesquisarRetirada(caixaModel.getId());
            listaReposicao = reposicaoDao.pesquisarReposicao(caixaModel.getId());
            
        } catch (SQLException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    
    public void atualizaValorFinal(){
    CaixaDAO caixaDao = new CaixaDAO();
    CaixaModel caixaModel = new CaixaModel();    
        try {
            caixaModel = caixaDao.pesquisarCaixa();
            caixaModel.getValorFinal().toString();
        } catch (SQLException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void atualizaTabelaConsulta(CaixaModel caixaModel) throws SQLException, NaoEncontradoException {
        DefaultTableModel val = (DefaultTableModel) jTableConsultaHistoricoCaixa.getModel();
        val.setNumRows(0);
       
        Locale localBrasil = new Locale("pt", "BR");
        
        double  quantidadeCartao = 0.00, quantidadeDinheiro = 0.00,
                quantidadeRetirada = 0.00, quantidadeReposto = 0.00, quantidadeDesconto = 0.00;
        int quantidadeVenda = 0;
        
        String valorAtual = NumberFormat.getCurrencyInstance(localBrasil).format(caixaModel.getValorFinal());
        jLabelResumoSaldoAtual.setText(valorAtual);
        
        VendaModel vendaModel = new VendaModel();
        RetiradaDAO retiradaDao = new RetiradaDAO();
        EntradaDAO reposicaoDao = new EntradaDAO();
        VendaDAO vendaDao = new VendaDAO();

        ArrayList<VendaModel> listaVenda = new ArrayList<>();
        ArrayList<RetiradaModel> listaRetirada = new ArrayList<>();
        ArrayList<EntradaModel> listaReposicao = new ArrayList<>();

        listaVenda = vendaDao.pesquisarVenda(caixaModel.getId());
        listaRetirada = retiradaDao.pesquisarRetirada(caixaModel.getId());
        listaReposicao = reposicaoDao.pesquisarReposicao(caixaModel.getId());

        if (listaVenda != null) {
            quantidadeVenda = listaVenda.size();
        } else {
            quantidadeVenda = 0;
        }
        
        for (VendaModel v : listaVenda) {
            quantidadeDesconto = quantidadeDesconto + v.getDesconto();
            switch (v.getFormaPagamento()) {
                case "D":
                    quantidadeDinheiro = quantidadeDinheiro + v.getValor();
                    break;
             
                default:
                    quantidadeCartao = quantidadeCartao + v.getValor();
                    break;
        }
            
           
            String valorVendaa = NumberFormat.getCurrencyInstance(localBrasil).format(v.getValor());
            
            String data = new SimpleDateFormat("dd/MM/yyyy").format(v.getDataVenda());
            String hora = new SimpleDateFormat("HH:mm:ss").format(v.getHoraVenda());
           
            val.addRow(new Object[]{"Venda", valorVendaa, data, hora});

        }

        for (RetiradaModel ret : listaRetirada) {
            
            String data = new SimpleDateFormat("dd/MM/yyyy").format(ret.getData());
            String hora = new SimpleDateFormat("HH:mm:ss").format(ret.getData());
            quantidadeRetirada = quantidadeRetirada + ret.getValor();
          
            Double valor = ret.getValor();
            String valorRetirada = NumberFormat.getCurrencyInstance(localBrasil).format(valor);
            
            val.addRow(new Object[]{"Retirada", valorRetirada ,data, hora});

        }

        for (EntradaModel rep : listaReposicao) {
            
            String data = new SimpleDateFormat("dd/MM/yyyy").format(rep.getData());
            String hora = new SimpleDateFormat("HH:mm:ss").format(rep.getData());
            quantidadeReposto = quantidadeReposto + rep.getValor();
          
            String valorReposicao = NumberFormat.getCurrencyInstance(localBrasil).format(rep.getValor());
            
            val.addRow(new Object[]{"Reposição", valorReposicao,data, hora});

        }

        resumoMovimentacaoCaixa(quantidadeDesconto, quantidadeCartao, quantidadeDinheiro,
                quantidadeRetirada, quantidadeReposto, quantidadeVenda);

    }

    public void atualizaTabela() throws SQLException, NaoEncontradoException {
      
        double  quantidadeCartao = 0.00, quantidadeDinheiro = 0.00,
                quantidadeRetirada = 0.00, quantidadeReposto = 0.00, quantidadeDesconto = 0.00;
        int quantidadeVenda = 0;
        
        Locale localBrasil = new Locale("pt", "BR");
       
        CaixaDAO caixaDao = new CaixaDAO();
        CaixaModel caixaModel = new CaixaModel();
        
        caixaModel = caixaDao.pesquisarCaixa();
       
        if ("aberto".equals(caixaModel.getSituacao())) {
            String valorInicial = NumberFormat.getCurrencyInstance(localBrasil).format(caixaModel.getTroco());
            jLabelResumoSaldoInicial.setText(valorInicial);
            String valorFinal = NumberFormat.getCurrencyInstance(localBrasil).format(caixaModel.getValorFinal());
           
            jLabelResumoSaldoAtual.setText(valorFinal);
           
            VendaModel vendaModel = new VendaModel();
            RetiradaDAO retiradaDao = new RetiradaDAO();
            EntradaDAO reposicaoDao = new EntradaDAO();
            VendaDAO vendaDao = new VendaDAO();
 
            
            ArrayList<VendaModel> listaVenda = new ArrayList<>();
            ArrayList<RetiradaModel> listaRetirada = new ArrayList<>();
            ArrayList<EntradaModel> listaReposicao = new ArrayList<>();
            
            
            listaVenda = vendaDao.pesquisarVenda(caixaModel.getId());
            listaRetirada = retiradaDao.pesquisarRetirada(caixaModel.getId());
            listaReposicao = reposicaoDao.pesquisarReposicao(caixaModel.getId());
            
            if (listaVenda != null) {
                
                quantidadeVenda = listaVenda.size();
                
                
            } else {
                
                quantidadeVenda = 0;
             
            }
             
            DefaultTableModel val = (DefaultTableModel) jTableCaixaDiario.getModel();
            val.setNumRows(0);
            
            if (listaVenda != null) {
                      

                for (VendaModel v : listaVenda) {
                            
                    quantidadeDesconto = quantidadeDesconto + v.getDesconto();
                    switch (v.getFormaPagamento()) {
                        case "D":
                            quantidadeDinheiro = quantidadeDinheiro + v.getValor();
                            break;
                        default:
                            quantidadeCartao = quantidadeCartao + v.getValor();
                            break;
                    }
                    
                    String data = new SimpleDateFormat("dd/MM/yyyy").format(v.getDataVenda());
                    String hora = new SimpleDateFormat("HH:mm:ss").format(v.getHoraVenda());
                    
                    Double valorVenda = v.getValor();
                    String valorVendaa = NumberFormat.getCurrencyInstance(localBrasil).format(valorVenda);
                    
                    val.addRow(new Object[]{"Venda", valorVendaa, data, hora, v.getFuncionario().getNome()});

                }
            }
            if (listaRetirada != null) {
                

                for (RetiradaModel ret : listaRetirada) {
                          
                    String data = new SimpleDateFormat("dd/MM/yyyy").format(ret.getData());
                    String hora = new SimpleDateFormat("HH:mm:ss").format(ret.getData());
                    quantidadeRetirada = quantidadeRetirada + ret.getValor();
                    String valorRetirada = NumberFormat.getCurrencyInstance(localBrasil).format(ret.getValor());
                    val.addRow(new Object[]{"Retirada", valorRetirada, data, hora, ret.getFuncionario().getNome()});

                }
            }
            if (listaReposicao != null) {
                        

                for (EntradaModel rep : listaReposicao) {
                     

                    String data = new SimpleDateFormat("dd/MM/yyyy").format(rep.getData());
                    String hora = new SimpleDateFormat("HH:mm:ss").format(rep.getData());
                    quantidadeReposto = quantidadeReposto + rep.getValor();
                    
                    String valorReposicao = NumberFormat.getCurrencyInstance(localBrasil).format(rep.getValor());
                    
                    val.addRow(new Object[]{"Entrada", valorReposicao, data, hora, rep.getFuncionario().getNome()});

                }
            }

            resumoMovimentacaoCaixa(quantidadeDesconto, quantidadeCartao, quantidadeDinheiro,
                    quantidadeRetirada, quantidadeReposto, quantidadeVenda);

        }
    }
    
     public void PreencherResumoConsulta(CaixaModel caixaModel) throws SQLException, NaoEncontradoException{
        
        CaixaDAO caixaDao = new CaixaDAO();
        
        double quantidadeDescontos = 0.0, quantidadeCartaos = 0.0, quantidadeDinheiros = 0.0, 
                quantidadeRetiradas = 0.0, quantidadeRepostos = 0.0, valorInicials = 0.0, valorFinals = 0.0;
        Timestamp dataAbertura, dataFechamento;
        
        dataAbertura = caixaModel.getDataAbertura();
        dataFechamento = caixaModel.getDataFechamento();
        valorInicials = caixaModel.getValorInicial();
        valorFinals = caixaModel.getValorFinal();
        
        int quantidadeVenda = 0;

        RetiradaDAO retiradaDao = new RetiradaDAO();
        EntradaDAO reposicaoDao = new EntradaDAO();
        VendaDAO vendaDao = new VendaDAO();

        ArrayList<VendaModel> listaVenda = new ArrayList<>();
        ArrayList<RetiradaModel> listaRetirada = new ArrayList<>();
        ArrayList<EntradaModel> listaReposicao = new ArrayList<>();

        listaVenda = vendaDao.pesquisarVenda(caixaModel.getId());
        listaRetirada = retiradaDao.pesquisarRetirada(caixaModel.getId());
        listaReposicao = reposicaoDao.pesquisarReposicao(caixaModel.getId());

        if (listaVenda != null) {
            quantidadeVenda = listaVenda.size();
        } else {
            quantidadeVenda = 0;
        }

        for (VendaModel v : listaVenda) {
            quantidadeDescontos = quantidadeDescontos + v.getDesconto();
            switch (v.getFormaPagamento()) {
                
                case "D":
                    quantidadeDinheiros = quantidadeDinheiros + v.getValor();
                    break;
                    
                default:
                    quantidadeCartaos = quantidadeCartaos + v.getValor();
                    break;
                    
            }

        }
        for (RetiradaModel ret : listaRetirada) {

            quantidadeRetiradas = quantidadeRetiradas + ret.getValor();

        }

        for (EntradaModel rep : listaReposicao) {

            quantidadeRepostos = quantidadeRepostos + rep.getValor();

        }
        resumoMovimentacaoConsulta(dataAbertura, dataFechamento, quantidadeDescontos,
                quantidadeCartaos, quantidadeDinheiros, 
                quantidadeRetiradas, quantidadeRepostos, quantidadeVenda, valorInicials, valorFinals, caixaModel.getSituacao());
        
    }
    
    public void resumoMovimentacaoConsulta(Timestamp dataAbertura, Timestamp dataFechamento, 
            double quantidadeDesconto,double quantidadeCartao, double quantidadeDinheiro,
            double quantidadeTotalRetirada, double quantidadeReposto, 
            int quantidadeTotalVenda, double valorInicial, double valorFinal, String situacao) {
        
        Locale localBrasil = new Locale("pt", "BR");
        String quantidadeTotalVendaString, quantidadeDescontoString,
                quantidadeDinheiroString, quantidadeTotalRetiradaString, 
                quantidadeTotalRepostoString,quantidadeTotalCartaoString, 
                valorInicialString, valorFinalString;
        double quantidadeTotalCartao = 0.0;
        String dataAberturas = new SimpleDateFormat("dd/MM/yyyy").format(dataAbertura);
        String dataFechamentos = new SimpleDateFormat("dd/MM/yyyy").format(dataFechamento);
        
        quantidadeTotalVendaString = String.valueOf(quantidadeTotalVenda);
        quantidadeDescontoString = NumberFormat.getCurrencyInstance(localBrasil).format(quantidadeDesconto);
        quantidadeDinheiroString = NumberFormat.getCurrencyInstance(localBrasil).format(quantidadeDinheiro);
        quantidadeTotalRetiradaString = NumberFormat.getCurrencyInstance(localBrasil).format(quantidadeTotalRetirada);
        quantidadeTotalRepostoString = NumberFormat.getCurrencyInstance(localBrasil).format(quantidadeReposto);
        quantidadeTotalCartaoString =  NumberFormat.getCurrencyInstance(localBrasil).format(quantidadeTotalCartao);
        valorInicialString = NumberFormat.getCurrencyInstance(localBrasil).format(valorInicial);
        valorFinalString = NumberFormat.getCurrencyInstance(localBrasil).format(valorFinal);
            
        jLabelResumoConsultaCatao.setText(quantidadeTotalCartaoString);
        jLabelResumoConsultaDataAbertura.setText(dataAberturas);
        if(situacao.equals("fechado")){
        jLabelResumoConsultaDataFechamento.setVisible(true);
        jLabelDataFechamentConsulta.setVisible(true);
        jLabelResumoConsultaDataFechamento.setText(dataFechamentos);
        }else{
        jLabelResumoConsultaDataFechamento.setVisible(false);
        jLabelDataFechamentConsulta.setVisible(false);
        }
        jLabelResumoConsultaDesconto.setText(quantidadeDescontoString);
        jLabelResumoConsultaDinheiro.setText(quantidadeDinheiroString);
        jLabelResumoConsultaEntrada.setText(quantidadeTotalRepostoString);
        jLabelResumoConsultaQtdVenda.setText(quantidadeTotalVendaString);
        jLabelResumoConsultaRetirada.setText(quantidadeTotalRetiradaString);
        jLabelResumoConsultaValorInicial.setText(valorInicialString);
        jLabelResumoConsultaValorFinal.setText(valorFinalString);

    }
    
    public void finalizar(){
    
        CaixaModel caixa = new CaixaModel();
        CaixaDAO caixaDao = new CaixaDAO();
        
        try {
            
            caixa = caixaDao.pesquisarCaixa();
            if(caixa.getFinaliza() == true){
            caixa.setFinaliza(false);
            caixaDao.alterarValorFinal(caixa);
            historicoMovimentacaoCaixa();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void mudaStatus(CaixaModel caixaModel) throws SQLException, NaoEncontradoException {

        CaixaDAO caixaDao = new CaixaDAO();

        caixaModel = caixaDao.pesquisarCaixa();

        if (caixaModel.getSituacao().equals("aberto")) {

            String data = new SimpleDateFormat("dd/MM/yyyy").format(caixaModel.getDataAbertura());
            String hora = new SimpleDateFormat("HH:mm:ss").format(caixaModel.getDataAbertura());

            jLabelData.setText(data);
            jLabelHora.setText(hora);
            jLabelCaixaStatus.setText("Caixa Aberto");
            jComboBoxCategoria.setEnabled(true);
            jPanelFundoStatusCaixa.setBackground(Color.green);
            jLabelResumoLegendaMensagemStatusCaixa.setText("Caixa aberto por:");
            jLabelResumoNomeStatusCaixa.setText(caixaModel.getFuncionarioModel().getNome());

        } else {
            
            String data = new SimpleDateFormat("dd/MM/yyyy").format(caixaModel.getDataAbertura());
            String hora = new SimpleDateFormat("HH:mm:ss").format(caixaModel.getDataAbertura());
            jComboBoxCategoria.setEnabled(false);
            jLabelCaixaStatus.setText("Caixa Fechado");
            jLabelData.setText(data);
            jLabelHora.setText(hora);
            jPanelFundoStatusCaixa.setBackground(Color.red);
            jLabelResumoLegendaMensagemStatusCaixa.setText("Caixa fechado por:");
            jLabelResumoNomeStatusCaixa.setText(caixaModel.getFuncionarioModel().getNome());
            
            atualizaTabela();
        }

    }
    
    public void pesquisarHistorico(){
        
         this.telaAtual = jTabbedPane1.getSelectedIndex();
        
        CaixaDAO caixaDao = new CaixaDAO();
        CaixaModel caixaModel = new CaixaModel();
        
        try {
            boolean abrirCaixa, fecharCaixa, excluirLancamento, historico,
            consultar, cancelar, repor, retirar;
            caixaModel = caixaDao.pesquisarCaixa();

            jTabbedPane1.setSelectedIndex(1);
            jTabbedPane1.setEnabledAt(0, false); //TELA PRINCIPAL
            jTabbedPane1.setEnabledAt(1, true); //TELA HISTORICO CAIXA
            jTabbedPane1.setEnabledAt(2, false); // TELA PESQUISA 
            
            //BOTOES DO HISTORICO
   
            alteraBotoes(abrirCaixa=true, fecharCaixa=true,
                         historico=false, consultar=true,
                         cancelar=true, repor=false,
                         retirar=false, caixaModel);
            
         } catch (SQLException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void alteraBotoes(boolean abrirCaixa, boolean fecharCaixa, boolean historico,
            boolean consultar, boolean cancelar, boolean repor, boolean retirar, CaixaModel caixaModel) {
        
        if(caixaModel.getSituacao().equals("aberto")){
            
            jButtonAbrirCaixa.setEnabled(false);
            jButtonFecharCaixa.setEnabled(true);
            
            jButtonHistorico.setEnabled(historico);
            jButtonConsulta.setEnabled(consultar);
            jButtonCancelar.setEnabled(cancelar);
            jButtonReporValor.setEnabled(repor);
            jButtonRetirarValor.setEnabled(retirar);
        
        }else{
            
            jButtonAbrirCaixa.setEnabled(true);
            jButtonFecharCaixa.setEnabled(false);
            
            jButtonHistorico.setEnabled(historico);
            jButtonConsulta.setEnabled(consultar);
            jButtonCancelar.setEnabled(cancelar);
            jButtonReporValor.setEnabled(false);
            jButtonRetirarValor.setEnabled(false);
            
        }
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanelFundoStatusCaixa = new javax.swing.JPanel();
        jLabelCaixaStatus = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabelResumoLegendaSaldoInicial = new javax.swing.JLabel();
        jLabelResumoLegendaData = new javax.swing.JLabel();
        jLabelResumoLegendaMensagemStatusCaixa = new javax.swing.JLabel();
        jLabelData = new javax.swing.JLabel();
        jLabelResumoNomeStatusCaixa = new javax.swing.JLabel();
        jLabelResumoSaldoAtual = new javax.swing.JLabel();
        jLabelResumoSaldoLegendaAtual = new javax.swing.JLabel();
        jLabelResumoSaldoInicial = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabelResumoLegendaQtdVendas = new javax.swing.JLabel();
        jLabelResumoLegendaCartao = new javax.swing.JLabel();
        jLabelResumoQuantidadeVenda = new javax.swing.JLabel();
        jLabelResumoTotalCartao = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabelResumoLegendaReposicao = new javax.swing.JLabel();
        jLabelResumoTotalReposto = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabelResumoLegendaDinheiro = new javax.swing.JLabel();
        jLabelResumoTotalDinheiro = new javax.swing.JLabel();
        jLabelResumoLegendaRetirada = new javax.swing.JLabel();
        jLabelResumoTotalRetiradas = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabelResumoLegendaDinheiro1 = new javax.swing.JLabel();
        jLabelResumoTotalDesconto = new javax.swing.JLabel();
        jLabelResumoLegendaMovimentacaoCaixa = new javax.swing.JLabel();
        jLabelResumoLegendaHora = new javax.swing.JLabel();
        jLabelHora = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabelLegendaPesquisaCaixaDiario = new javax.swing.JLabel();
        jComboBoxCategoria = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCaixaDiario = new javax.swing.JTable();
        jPanel10 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jDateChooserDataHistorico = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jPanel29 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jPanel32 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabelImagemFinalizado = new javax.swing.JLabel();
        jLabelImagemNaoFinalizado = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableHistoricoCaixa = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanelItemVendaHistorico = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableConsultaHistoricoCaixaItemVenda = new javax.swing.JTable();
        jPanelMovimentoCaixa = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableConsultaHistoricoCaixa = new javax.swing.JTable();
        jPanel15 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabelResumoConsultaDinheiro = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabelResumoConsultaCatao = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabelResumoConsultaDesconto = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabelResumoConsultaRetirada = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabelDataFechamentConsulta = new javax.swing.JLabel();
        jLabelResumoConsultaDataAbertura = new javax.swing.JLabel();
        jLabelResumoConsultaDataFechamento = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabelResumoConsultaQtdVenda = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabelResumoConsultaEntrada = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabelResumoConsultaValorFinal = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabelResumoConsultaValorInicial = new javax.swing.JLabel();
        jPanel34 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jPanelFundoDescricaoConsulta = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextAreaDescricao = new javax.swing.JTextArea();
        jPanelJustificativaConsulta = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextAreaJustificativa = new javax.swing.JTextArea();
        jPanelCampoCliente = new javax.swing.JPanel();
        jTextFieldCliente = new javax.swing.JTextField();
        jPanelCampoFuncionario = new javax.swing.JPanel();
        jTextFieldFuncionario = new javax.swing.JTextField();
        jPanelCampoCategoria = new javax.swing.JPanel();
        jTextFieldCategoria = new javax.swing.JTextField();
        jPanelCampoTipoPagamento = new javax.swing.JPanel();
        jTextFieldTipoPagamento = new javax.swing.JTextField();
        jPanelCampoHora = new javax.swing.JPanel();
        jTextFieldHora = new javax.swing.JTextField();
        jPanelCampoData = new javax.swing.JPanel();
        jTextFieldData = new javax.swing.JTextField();
        jPanelCampoValor = new javax.swing.JPanel();
        jTextFieldValor = new javax.swing.JTextField();
        jPanelDesconto = new javax.swing.JPanel();
        jTextFieldDesconto = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButtonRetirarValor = new javax.swing.JButton();
        jButtonReporValor = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButtonConsulta = new javax.swing.JButton();
        jButtonHistorico = new javax.swing.JButton();
        jButtonFecharCaixa = new javax.swing.JButton();
        jButtonAbrirCaixa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Controle de Caixa");
        setExtendedState(6);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(3, 133, 188));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Controle de Caixa");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTabbedPane1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTabbedPane1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTabbedPane1KeyReleased(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanelFundoStatusCaixa.setBackground(new java.awt.Color(0, 204, 0));

        jLabelCaixaStatus.setBackground(new java.awt.Color(255, 255, 255));
        jLabelCaixaStatus.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelCaixaStatus.setForeground(new java.awt.Color(255, 255, 255));
        jLabelCaixaStatus.setText("Caixa Aberto");

        javax.swing.GroupLayout jPanelFundoStatusCaixaLayout = new javax.swing.GroupLayout(jPanelFundoStatusCaixa);
        jPanelFundoStatusCaixa.setLayout(jPanelFundoStatusCaixaLayout);
        jPanelFundoStatusCaixaLayout.setHorizontalGroup(
            jPanelFundoStatusCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFundoStatusCaixaLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabelCaixaStatus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelFundoStatusCaixaLayout.setVerticalGroup(
            jPanelFundoStatusCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFundoStatusCaixaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelCaixaStatus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(51, 51, 51));

        jLabelResumoLegendaSaldoInicial.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabelResumoLegendaSaldoInicial.setForeground(new java.awt.Color(255, 255, 255));
        jLabelResumoLegendaSaldoInicial.setText("Saldo Inicial :");

        jLabelResumoLegendaData.setBackground(new java.awt.Color(255, 255, 255));
        jLabelResumoLegendaData.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabelResumoLegendaData.setForeground(new java.awt.Color(255, 255, 255));
        jLabelResumoLegendaData.setText("Data:");

        jLabelResumoLegendaMensagemStatusCaixa.setBackground(new java.awt.Color(255, 255, 255));
        jLabelResumoLegendaMensagemStatusCaixa.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabelResumoLegendaMensagemStatusCaixa.setForeground(new java.awt.Color(255, 255, 255));
        jLabelResumoLegendaMensagemStatusCaixa.setText("Caixa Aberto Por:");

        jLabelData.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabelData.setForeground(new java.awt.Color(255, 255, 255));

        jLabelResumoNomeStatusCaixa.setBackground(new java.awt.Color(255, 255, 255));
        jLabelResumoNomeStatusCaixa.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabelResumoNomeStatusCaixa.setForeground(new java.awt.Color(255, 255, 255));

        jLabelResumoSaldoAtual.setBackground(new java.awt.Color(255, 255, 255));
        jLabelResumoSaldoAtual.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabelResumoSaldoAtual.setForeground(new java.awt.Color(255, 255, 255));

        jLabelResumoSaldoLegendaAtual.setBackground(new java.awt.Color(255, 255, 255));
        jLabelResumoSaldoLegendaAtual.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabelResumoSaldoLegendaAtual.setForeground(new java.awt.Color(255, 255, 255));
        jLabelResumoSaldoLegendaAtual.setText("Saldo Atual :");

        jLabelResumoSaldoInicial.setBackground(new java.awt.Color(255, 255, 255));
        jLabelResumoSaldoInicial.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabelResumoSaldoInicial.setForeground(new java.awt.Color(255, 255, 255));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel16.setForeground(new java.awt.Color(255, 255, 255));

        jLabelResumoLegendaQtdVendas.setBackground(new java.awt.Color(102, 102, 102));
        jLabelResumoLegendaQtdVendas.setText("Quantidade Vendas:");

        jLabelResumoLegendaCartao.setText("Total Cartão:");

        jLabelResumoQuantidadeVenda.setText("jLabel6");

        jLabelResumoTotalCartao.setText("jLabel6");

        jPanel18.setBackground(new java.awt.Color(237, 237, 237));

        jLabelResumoLegendaReposicao.setText("Total Entradas:");

        jLabelResumoTotalReposto.setText("jLabel6");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jLabelResumoLegendaReposicao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelResumoTotalReposto)
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelResumoLegendaReposicao, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
            .addComponent(jLabelResumoTotalReposto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel21.setBackground(new java.awt.Color(238, 238, 238));

        jLabelResumoLegendaDinheiro.setText("Total Dinheiro:");

        jLabelResumoTotalDinheiro.setText("jLabel6");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jLabelResumoLegendaDinheiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40)
                .addComponent(jLabelResumoTotalDinheiro)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelResumoLegendaDinheiro, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(jLabelResumoTotalDinheiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabelResumoLegendaRetirada.setText("Total Retiradas:");

        jLabelResumoTotalRetiradas.setText("jLabel6");

        jPanel22.setBackground(new java.awt.Color(238, 238, 238));

        jLabelResumoLegendaDinheiro1.setText("Total Desconto:");

        jLabelResumoTotalDesconto.setText("jLabel6");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jLabelResumoLegendaDinheiro1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40)
                .addComponent(jLabelResumoTotalDesconto)
                .addContainerGap())
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelResumoLegendaDinheiro1, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                    .addComponent(jLabelResumoTotalDesconto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelResumoLegendaQtdVendas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelResumoLegendaCartao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelResumoTotalCartao, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelResumoQuantidadeVenda, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabelResumoLegendaRetirada, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelResumoTotalRetiradas)))
                .addContainerGap())
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelResumoLegendaQtdVendas)
                    .addComponent(jLabelResumoQuantidadeVenda))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelResumoLegendaCartao)
                    .addComponent(jLabelResumoTotalCartao))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelResumoLegendaRetirada)
                    .addComponent(jLabelResumoTotalRetiradas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabelResumoLegendaMovimentacaoCaixa.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabelResumoLegendaMovimentacaoCaixa.setForeground(new java.awt.Color(255, 255, 255));
        jLabelResumoLegendaMovimentacaoCaixa.setText("Resumo Movimentação Caixa");

        jLabelResumoLegendaHora.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabelResumoLegendaHora.setForeground(new java.awt.Color(255, 255, 255));
        jLabelResumoLegendaHora.setText("Hora:");

        jLabelHora.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabelHora.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelResumoNomeStatusCaixa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabelResumoLegendaData)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelData, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelResumoLegendaHora)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelHora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelResumoSaldoLegendaAtual)
                            .addComponent(jLabelResumoLegendaSaldoInicial))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelResumoSaldoInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelResumoSaldoAtual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelResumoLegendaMensagemStatusCaixa)
                            .addComponent(jLabelResumoLegendaMovimentacaoCaixa))
                        .addGap(0, 3, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelResumoLegendaData)
                        .addComponent(jLabelData, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelResumoLegendaHora)
                        .addComponent(jLabelHora, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelResumoLegendaMensagemStatusCaixa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelResumoNomeStatusCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelResumoLegendaSaldoInicial)
                    .addComponent(jLabelResumoSaldoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelResumoSaldoLegendaAtual)
                    .addComponent(jLabelResumoSaldoAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jLabelResumoLegendaMovimentacaoCaixa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanelFundoStatusCaixa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelFundoStatusCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Pesquisar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jLabelLegendaPesquisaCaixaDiario.setText("Categoria:");

        jComboBoxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas as categorias", "Venda", "Reposicao", "Retirada" }));
        jComboBoxCategoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxCategoriaItemStateChanged(evt);
            }
        });
        jComboBoxCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelLegendaPesquisaCaixaDiario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(733, 733, 733))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLegendaPesquisaCaixaDiario))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Movimentação de Caixa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jTableCaixaDiario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Categoria", "Valor", "Data", "Hora", "Funcionário"
            }
        ));
        jScrollPane1.setViewportView(jTableCaixaDiario);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("Movimentação do Caixa", jPanel8);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Pesquisar", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel3.setToolTipText("");

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lupa.png"))); // NOI18N
        jButton11.setText("Pesquisar");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jDateChooserDataHistorico.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jDateChooserDataHistoricoKeyReleased(evt);
            }
        });

        jLabel2.setText("Data");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooserDataHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jDateChooserDataHistorico, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Verificar Caixa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Concluir30x30.png"))); // NOI18N
        jButton1.setText("Finalizar Caixa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel32.setBackground(new java.awt.Color(255, 255, 255));
        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Legenda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabel16.setText("Caixa Finalizado");

        jLabelImagemFinalizado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/confirmar20x20.png"))); // NOI18N

        jLabelImagemNaoFinalizado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/alerta20x20.png"))); // NOI18N

        jLabel18.setText("Caixa Finalizado com valor diferente");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabelImagemFinalizado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addComponent(jLabelImagemNaoFinalizado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18)))
                .addContainerGap(321, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel16)
                    .addComponent(jLabelImagemFinalizado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabelImagemNaoFinalizado)
                    .addComponent(jLabel18))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel29Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Historico de Movimento de Caixa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jTableHistoricoCaixa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Data", "Hora Abertura", "Hora Fechamento", "Caixa Aberto Por", "Caixa Fechado Por", "Valor Final", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableHistoricoCaixa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableHistoricoCaixaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTableHistoricoCaixa);

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 116, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Historico de Caixa", jPanel10);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanelItemVendaHistorico.setBackground(new java.awt.Color(255, 255, 255));
        jPanelItemVendaHistorico.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Itens de venda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jTableConsultaHistoricoCaixaItemVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Descrição", "Quantidade", "Unidade Medida", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTableConsultaHistoricoCaixaItemVenda);

        javax.swing.GroupLayout jPanelItemVendaHistoricoLayout = new javax.swing.GroupLayout(jPanelItemVendaHistorico);
        jPanelItemVendaHistorico.setLayout(jPanelItemVendaHistoricoLayout);
        jPanelItemVendaHistoricoLayout.setHorizontalGroup(
            jPanelItemVendaHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5)
        );
        jPanelItemVendaHistoricoLayout.setVerticalGroup(
            jPanelItemVendaHistoricoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelItemVendaHistoricoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelMovimentoCaixa.setBackground(new java.awt.Color(255, 255, 255));
        jPanelMovimentoCaixa.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Movimento do Caixa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jTableConsultaHistoricoCaixa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Categoria", "Valor", "Data", "Hora"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableConsultaHistoricoCaixa.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                jTableConsultaHistoricoCaixaHierarchyChanged(evt);
            }
        });
        jTableConsultaHistoricoCaixa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTableConsultaHistoricoCaixaFocusGained(evt);
            }
        });
        jTableConsultaHistoricoCaixa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableConsultaHistoricoCaixaMouseClicked(evt);
            }
        });
        jTableConsultaHistoricoCaixa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTableConsultaHistoricoCaixaKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTableConsultaHistoricoCaixa);

        javax.swing.GroupLayout jPanelMovimentoCaixaLayout = new javax.swing.GroupLayout(jPanelMovimentoCaixa);
        jPanelMovimentoCaixa.setLayout(jPanelMovimentoCaixaLayout);
        jPanelMovimentoCaixaLayout.setHorizontalGroup(
            jPanelMovimentoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );
        jPanelMovimentoCaixaLayout.setVerticalGroup(
            jPanelMovimentoCaixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMovimentoCaixaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Movimentação", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setText("Total Dinheiro:");

        jLabelResumoConsultaDinheiro.setText("jLabel14");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelResumoConsultaDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
            .addComponent(jLabelResumoConsultaDinheiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel19.setBackground(new java.awt.Color(242, 242, 242));
        jPanel19.setPreferredSize(new java.awt.Dimension(203, 24));

        jLabel11.setText("Total Cartão");

        jLabelResumoConsultaCatao.setText("jLabel15");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelResumoConsultaCatao, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
            .addComponent(jLabelResumoConsultaCatao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setPreferredSize(new java.awt.Dimension(203, 24));

        jLabel12.setText("Total Desconto:");

        jLabelResumoConsultaDesconto.setText("jLabel16");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelResumoConsultaDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelResumoConsultaDesconto, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel23.setBackground(new java.awt.Color(242, 242, 242));
        jPanel23.setPreferredSize(new java.awt.Dimension(203, 24));

        jLabel13.setText("Total Retirada:");

        jLabelResumoConsultaRetirada.setText("jLabel22");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelResumoConsultaRetirada, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelResumoConsultaRetirada, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel24.setBackground(new java.awt.Color(102, 102, 102));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Data abertura:");

        jLabelDataFechamentConsulta.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        jLabelDataFechamentConsulta.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDataFechamentConsulta.setText("Data Fechamento:");

        jLabelResumoConsultaDataAbertura.setForeground(new java.awt.Color(255, 255, 255));
        jLabelResumoConsultaDataAbertura.setText("jLabel6");

        jLabelResumoConsultaDataFechamento.setForeground(new java.awt.Color(255, 255, 255));
        jLabelResumoConsultaDataFechamento.setText("jLabel7");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabelResumoConsultaDataAbertura, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDataFechamentConsulta)
                    .addComponent(jLabelResumoConsultaDataFechamento, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabelDataFechamentConsulta))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelResumoConsultaDataAbertura)
                    .addComponent(jLabelResumoConsultaDataFechamento))
                .addContainerGap())
        );

        jPanel26.setBackground(new java.awt.Color(242, 242, 242));
        jPanel26.setPreferredSize(new java.awt.Dimension(198, 24));

        jLabel3.setText("Quantidade de vendas:");

        jLabelResumoConsultaQtdVenda.setText("jLabel8");

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelResumoConsultaQtdVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
            .addComponent(jLabelResumoConsultaQtdVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setText("Total Entrada:");

        jLabelResumoConsultaEntrada.setText("jLabel7");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelResumoConsultaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabelResumoConsultaEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));
        jPanel27.setPreferredSize(new java.awt.Dimension(0, 24));

        jLabel8.setText("Valor Final:");

        jLabelResumoConsultaValorFinal.setText("jLabel15");

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(jLabelResumoConsultaValorFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelResumoConsultaValorFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel28.setBackground(new java.awt.Color(242, 242, 242));

        jLabel7.setText("Valor Inicial:");

        jLabelResumoConsultaValorInicial.setText("jLabel14");

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelResumoConsultaValorInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelResumoConsultaValorInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
            .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanelFundoDescricaoConsulta.setBackground(new java.awt.Color(255, 255, 255));
        jPanelFundoDescricaoConsulta.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Descrilção", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextAreaDescricao.setColumns(20);
        jTextAreaDescricao.setRows(5);
        jScrollPane4.setViewportView(jTextAreaDescricao);

        javax.swing.GroupLayout jPanelFundoDescricaoConsultaLayout = new javax.swing.GroupLayout(jPanelFundoDescricaoConsulta);
        jPanelFundoDescricaoConsulta.setLayout(jPanelFundoDescricaoConsultaLayout);
        jPanelFundoDescricaoConsultaLayout.setHorizontalGroup(
            jPanelFundoDescricaoConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFundoDescricaoConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelFundoDescricaoConsultaLayout.setVerticalGroup(
            jPanelFundoDescricaoConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFundoDescricaoConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4)
                .addContainerGap())
        );

        jPanelJustificativaConsulta.setBackground(new java.awt.Color(255, 255, 255));
        jPanelJustificativaConsulta.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Justificativa fechamento de caixa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextAreaJustificativa.setColumns(20);
        jTextAreaJustificativa.setRows(5);
        jScrollPane6.setViewportView(jTextAreaJustificativa);

        javax.swing.GroupLayout jPanelJustificativaConsultaLayout = new javax.swing.GroupLayout(jPanelJustificativaConsulta);
        jPanelJustificativaConsulta.setLayout(jPanelJustificativaConsultaLayout);
        jPanelJustificativaConsultaLayout.setHorizontalGroup(
            jPanelJustificativaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJustificativaConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelJustificativaConsultaLayout.setVerticalGroup(
            jPanelJustificativaConsultaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelJustificativaConsultaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanelCampoCliente.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCampoCliente.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        jPanelCampoCliente.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanelCampoClienteLayout = new javax.swing.GroupLayout(jPanelCampoCliente);
        jPanelCampoCliente.setLayout(jPanelCampoClienteLayout);
        jPanelCampoClienteLayout.setHorizontalGroup(
            jPanelCampoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCampoClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                .addGap(8, 8, 8))
        );
        jPanelCampoClienteLayout.setVerticalGroup(
            jPanelCampoClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCampoClienteLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTextFieldCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelCampoFuncionario.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCampoFuncionario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Funcionário", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        javax.swing.GroupLayout jPanelCampoFuncionarioLayout = new javax.swing.GroupLayout(jPanelCampoFuncionario);
        jPanelCampoFuncionario.setLayout(jPanelCampoFuncionarioLayout);
        jPanelCampoFuncionarioLayout.setHorizontalGroup(
            jPanelCampoFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCampoFuncionarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldFuncionario)
                .addGap(8, 8, 8))
        );
        jPanelCampoFuncionarioLayout.setVerticalGroup(
            jPanelCampoFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCampoFuncionarioLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTextFieldFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelCampoCategoria.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCampoCategoria.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Categoria", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        javax.swing.GroupLayout jPanelCampoCategoriaLayout = new javax.swing.GroupLayout(jPanelCampoCategoria);
        jPanelCampoCategoria.setLayout(jPanelCampoCategoriaLayout);
        jPanelCampoCategoriaLayout.setHorizontalGroup(
            jPanelCampoCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCampoCategoriaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelCampoCategoriaLayout.setVerticalGroup(
            jPanelCampoCategoriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCampoCategoriaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTextFieldCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelCampoTipoPagamento.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCampoTipoPagamento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tipo Pagamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        javax.swing.GroupLayout jPanelCampoTipoPagamentoLayout = new javax.swing.GroupLayout(jPanelCampoTipoPagamento);
        jPanelCampoTipoPagamento.setLayout(jPanelCampoTipoPagamentoLayout);
        jPanelCampoTipoPagamentoLayout.setHorizontalGroup(
            jPanelCampoTipoPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCampoTipoPagamentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldTipoPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelCampoTipoPagamentoLayout.setVerticalGroup(
            jPanelCampoTipoPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCampoTipoPagamentoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTextFieldTipoPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelCampoHora.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCampoHora.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Hora", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        javax.swing.GroupLayout jPanelCampoHoraLayout = new javax.swing.GroupLayout(jPanelCampoHora);
        jPanelCampoHora.setLayout(jPanelCampoHoraLayout);
        jPanelCampoHoraLayout.setHorizontalGroup(
            jPanelCampoHoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCampoHoraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldHora, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelCampoHoraLayout.setVerticalGroup(
            jPanelCampoHoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCampoHoraLayout.createSequentialGroup()
                .addComponent(jTextFieldHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanelCampoData.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCampoData.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Data", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        javax.swing.GroupLayout jPanelCampoDataLayout = new javax.swing.GroupLayout(jPanelCampoData);
        jPanelCampoData.setLayout(jPanelCampoDataLayout);
        jPanelCampoDataLayout.setHorizontalGroup(
            jPanelCampoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCampoDataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldData, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelCampoDataLayout.setVerticalGroup(
            jPanelCampoDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCampoDataLayout.createSequentialGroup()
                .addComponent(jTextFieldData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanelCampoValor.setBackground(new java.awt.Color(255, 255, 255));
        jPanelCampoValor.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Valor", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        javax.swing.GroupLayout jPanelCampoValorLayout = new javax.swing.GroupLayout(jPanelCampoValor);
        jPanelCampoValor.setLayout(jPanelCampoValorLayout);
        jPanelCampoValorLayout.setHorizontalGroup(
            jPanelCampoValorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCampoValorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldValor)
                .addContainerGap())
        );
        jPanelCampoValorLayout.setVerticalGroup(
            jPanelCampoValorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelCampoValorLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTextFieldValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanelDesconto.setBackground(new java.awt.Color(255, 255, 255));
        jPanelDesconto.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Desconto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));
        jPanelDesconto.setToolTipText("");

        javax.swing.GroupLayout jPanelDescontoLayout = new javax.swing.GroupLayout(jPanelDesconto);
        jPanelDesconto.setLayout(jPanelDescontoLayout);
        jPanelDescontoLayout.setHorizontalGroup(
            jPanelDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDescontoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanelDescontoLayout.setVerticalGroup(
            jPanelDescontoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDescontoLayout.createSequentialGroup()
                .addComponent(jTextFieldDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(jPanelCampoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanelDesconto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanelCampoFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelCampoValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanelCampoCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel25Layout.createSequentialGroup()
                                .addComponent(jPanelCampoData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanelCampoHora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanelCampoTipoPagamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                        .addComponent(jPanelJustificativaConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelFundoDescricaoConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(8, 8, 8))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelCampoFuncionario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelCampoCategoria, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelCampoTipoPagamento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelCampoData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelCampoCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelCampoHora, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelDesconto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelCampoValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelFundoDescricaoConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelJustificativaConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jPanelMovimentoCaixa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanelItemVendaHistorico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelMovimentoCaixa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelItemVendaHistorico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("Consultar", jPanel6);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel13.setForeground(new java.awt.Color(255, 255, 255));

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/VoltarSimples24x24.png"))); // NOI18N
        jButton6.setText("Fechar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButtonRetirarValor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/RetirarValores24x24.png"))); // NOI18N
        jButtonRetirarValor.setText("Retirar Valor  ");
        jButtonRetirarValor.setPreferredSize(new java.awt.Dimension(136, 52));
        jButtonRetirarValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRetirarValorActionPerformed(evt);
            }
        });

        jButtonReporValor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/AdicionarValores24x24.png"))); // NOI18N
        jButtonReporValor.setText("Repor Valores");
        jButtonReporValor.setPreferredSize(new java.awt.Dimension(139, 52));
        jButtonReporValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReporValorActionPerformed(evt);
            }
        });

        jButtonCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/voltar24x24.png"))); // NOI18N
        jButtonCancelar.setText("Voltar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });

        jButtonConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/consulta24x24.png"))); // NOI18N
        jButtonConsulta.setText("Consultar         ");
        jButtonConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultaActionPerformed(evt);
            }
        });

        jButtonHistorico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Historico24x24.png"))); // NOI18N
        jButtonHistorico.setText("Historico        ");
        jButtonHistorico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHistoricoActionPerformed(evt);
            }
        });

        jButtonFecharCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/FecharCaixa24x24.png"))); // NOI18N
        jButtonFecharCaixa.setText("Fechar Caixa   ");
        jButtonFecharCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFecharCaixaActionPerformed(evt);
            }
        });

        jButtonAbrirCaixa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/AbrirCaixa24x24.png"))); // NOI18N
        jButtonAbrirCaixa.setText("Abrir Caixa     ");
        jButtonAbrirCaixa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAbrirCaixaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonRetirarValor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonReporValor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonCancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonHistorico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonFecharCaixa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonConsulta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAbrirCaixa, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(2, 2, 2)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addComponent(jButtonAbrirCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonFecharCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonHistorico, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonReporValor, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonRetirarValor, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxCategoriaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxCategoriaItemStateChanged

    }//GEN-LAST:event_jComboBoxCategoriaItemStateChanged

    private void jComboBoxCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCategoriaActionPerformed
        int index = jComboBoxCategoria.getSelectedIndex();
        Timestamp dataCaixa;
        String dataCaixaString;
        int quantidadeVenda, quantidadeRetirada, quantidadeReposicao;
        double quantidadeCredito, quantidadeDebito, quantidadeDinheiro;

        Locale localBrasil = new Locale("pt", "BR");

        String categoria, data, hora;
        MaskFormatter mascara = null;
        VendaModel vendaModel = new VendaModel();
        VendaDAO vendaDao = new VendaDAO();

        EntradaModel reposicaoModel = new EntradaModel();
        EntradaDAO reposicaoDao = new EntradaDAO();

        RetiradaModel retiradaModel = new RetiradaModel();
        RetiradaDAO retiradaDao = new RetiradaDAO();

        //1 - Pegar a data do caixa aberto.
        CaixaModel caixaModel = new CaixaModel();
        CaixaDAO caixaDao = new CaixaDAO();

        ArrayList<VendaModel> listaVenda = new ArrayList<>();
        ArrayList<RetiradaModel> listaRetirada = new ArrayList<>();
        ArrayList<EntradaModel> listaReposicao = new ArrayList<>();

        try {
            caixaModel = caixaDao.pesquisarCaixa();

            switch (index) {

                case 0: {
                    atualizaTabela();
                }
                break;

                //VENDA
                case 1: {
                    if(caixaModel.getSituacao().equals("aberto")){
                        listaVenda = vendaDao.pesquisarVenda(caixaModel.getId());

                        if (listaVenda != null) {
                            quantidadeVenda = listaVenda.size();
                        } else {
                            quantidadeVenda = 0;
                        }

                        DefaultTableModel val = (DefaultTableModel) jTableCaixaDiario.getModel();
                        val.setNumRows(0);

                        for (VendaModel v : listaVenda) {

                            String datad = new SimpleDateFormat("dd/MM/yyyy").format(v.getDataVenda());
                            String horad = new SimpleDateFormat("HH:mm:ss").format(v.getHoraVenda());
                            String valorVenda = NumberFormat.getCurrencyInstance(localBrasil).format(v.getValor());
                            val.addRow(new Object[]{"Venda", valorVenda,datad, horad});

                        }

                    }
                }
                break;
                //REPOSICAO

                case 2: {

                    if(caixaModel.getSituacao().equals("aberto")){
                        listaReposicao = reposicaoDao.pesquisarReposicao(caixaModel.getId());
                        
                        DefaultTableModel val = (DefaultTableModel) jTableCaixaDiario.getModel();
                        val.setNumRows(0);

                        for (EntradaModel rep : listaReposicao) {

                            String datad = new SimpleDateFormat("dd/MM/yyyy").format(rep.getData());
                            String horad = new SimpleDateFormat("HH:mm:ss").format(rep.getData());
                            String valorReposicao = NumberFormat.getCurrencyInstance(localBrasil).format(rep.getValor());
                            val.addRow(new Object[]{"Reposição", valorReposicao,datad, horad});

                        }

                    }
                }

                break;

                //RETIRADA
                case 3: {

                    if(caixaModel.getSituacao().equals("aberto")){
                        listaRetirada = retiradaDao.pesquisarRetirada(caixaModel.getId());

                        DefaultTableModel val = (DefaultTableModel) jTableCaixaDiario.getModel();
                        val.setNumRows(0);

                        for (RetiradaModel ret : listaRetirada) {

                            String datad = new SimpleDateFormat("dd/MM/yyyy").format(ret.getData());
                            String horad = new SimpleDateFormat("HH:mm:ss").format(ret.getData());
                            String valorRetirada = NumberFormat.getCurrencyInstance(localBrasil).format(ret.getValor());
                            val.addRow(new Object[]{"Retirada", valorRetirada,datad, horad});

                        }

                    }
                }

                break;

            }

        } catch (SQLException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            JOptionPane.showMessageDialog(null, "Abra o caixa", "Alerta",JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jComboBoxCategoriaActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        CaixaDAO caixaDao = new CaixaDAO();
        CaixaModel caixaModel = new CaixaModel();
        ArrayList<CaixaModel> listaCaixa = new ArrayList<>();

        FuncionarioModel funcionarioModel = new FuncionarioModel();
        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
        DefaultTableModel val = (DefaultTableModel) jTableHistoricoCaixa.getModel();
        val.setNumRows(0);

        try {

            String datas = new SimpleDateFormat("yyyy-MM-dd").format(jDateChooserDataHistorico.getDate());
            listaCaixa = caixaDao.pesquisarData(datas);

            for (CaixaModel caixa : listaCaixa) {
                if(caixa.getSituacao().equals("fechado")){
                    String data = new SimpleDateFormat("dd/MM/yyyy").format(caixa.getDataAbertura());
                    String horaInicio = new SimpleDateFormat("HH:mm:ss").format(caixa.getDataAbertura());
                    String horaFim = new SimpleDateFormat("HH:mm:ss").format(caixa.getDataFechamento());

                    val.addRow(new Object[]{data, horaInicio, horaFim, funcionarioModel.getNome(),caixa.getFuncionarioModel().getNome()});

                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jDateChooserDataHistoricoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooserDataHistoricoKeyReleased

       

    }//GEN-LAST:event_jDateChooserDataHistoricoKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        finalizar();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTableHistoricoCaixaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableHistoricoCaixaMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jTableHistoricoCaixaMouseClicked

    private void jTableConsultaHistoricoCaixaHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jTableConsultaHistoricoCaixaHierarchyChanged

    }//GEN-LAST:event_jTableConsultaHistoricoCaixaHierarchyChanged

    private void jTableConsultaHistoricoCaixaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableConsultaHistoricoCaixaFocusGained

    }//GEN-LAST:event_jTableConsultaHistoricoCaixaFocusGained

    private void jTableConsultaHistoricoCaixaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableConsultaHistoricoCaixaMouseClicked

        Locale localBrasil = new Locale("pt", "BR");
        int linha = jTableConsultaHistoricoCaixa.getSelectedRow();
        FormataStringParaData formata = new FormataStringParaData();
        DefaultTableModel val = (DefaultTableModel) jTableConsultaHistoricoCaixaItemVenda.getModel();
        val.setNumRows(0);

        if(linha != -1){

            String categoria = (String) jTableConsultaHistoricoCaixa.getValueAt(linha, 0);

            if (categoria.equals("Retirada")) {

                try {

                    String data = (String) jTableConsultaHistoricoCaixa.getValueAt(linha, 2);

                    String hora = (String) jTableConsultaHistoricoCaixa.getValueAt(linha, 3);

                    RetiradaModel retirada = new RetiradaModel();
                    RetiradaDAO retiradaDao = new RetiradaDAO();
                    String dataCompleta = formata.formatarDataHoraStringToDateString(data, hora);
                    
                    retirada = retiradaDao.pesquisarDataHora(dataCompleta);

                    jTextFieldCategoria.setText("Retirada");
                    jTextFieldData.setText(data);
                    jTextFieldHora.setText(hora);
                    String valor = NumberFormat.getCurrencyInstance(localBrasil).format(retirada.getValor());
                    jTextFieldValor.setText(valor);
                    jTextFieldFuncionario.setText(retirada.getCaixa().getFuncionarioModel().getNome());
                    jTextAreaDescricao.setText(retirada.getDescricao());
                    jTextFieldCliente.setText("");
                    jTextFieldTipoPagamento.setText("");

                    val.setNumRows(0);

                } catch (ParseException ex) {
                    Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NaoEncontradoException ex) {
                    Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if (categoria.equals("Entrada")) {

                try {

                    String dataRp = (String) jTableConsultaHistoricoCaixa.getValueAt(linha, 2);
                    String horaRp = (String) jTableConsultaHistoricoCaixa.getValueAt(linha, 3);

                    EntradaModel reposicao = new EntradaModel();
                    EntradaDAO reposicaoDao = new EntradaDAO();

                    reposicao = reposicaoDao.pesquisarDataHora(formata.formatarDataHoraStringToDateString(dataRp, horaRp));

                    jTextFieldCategoria.setText("Entrada");
                    jTextFieldData.setText(dataRp);
                    jTextFieldHora.setText(horaRp);
                    String valor = NumberFormat.getCurrencyInstance(localBrasil).format(reposicao.getValor());
                    jTextFieldValor.setText(valor);

                    jTextFieldFuncionario.setText(reposicao.getCaixa().getFuncionarioModel().getNome());
                    jTextAreaDescricao.setText("");
                    jTextFieldCliente.setText("");
                    jTextFieldTipoPagamento.setText("");

                    val.setNumRows(0);

                } catch (ParseException ex) {
                    Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NaoEncontradoException ex) {
                    Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {

                //VENDA
                try {

                    VendaModel venda = new VendaModel();
                    VendaDAO vendaDao = new VendaDAO();
                    ItemVendaDAO itemVendaDao = new ItemVendaDAO();

                    ArrayList<ItemVenda> listaVenda = new ArrayList<>();

                    String dataVm = (String) jTableConsultaHistoricoCaixa.getValueAt(linha, 2);
                    String horaVm = (String) jTableConsultaHistoricoCaixa.getValueAt(linha, 3);

                    venda = vendaDao.pesquisarDataHora(formata.formatarDataStringToSQLString(dataVm), horaVm);

                    listaVenda = itemVendaDao.pesquisarItemVenda(venda.getId());
                    
                    for (ItemVenda itemVenda : listaVenda) {
                        
                        String quantidadeString = String.valueOf(itemVenda.getQuantidade());
                            quantidadeString = quantidadeString.replace(".", "").replace(",", "");
                            if(itemVenda.getUnidadeMedida().equals("KG")){
                            NumberFormat formatter = new DecimalFormat("#0.000"); 
                            quantidadeString = formatter.format(itemVenda.getQuantidade()).replace(".", ",");
                            }
                        
                        String valor = NumberFormat.getCurrencyInstance(localBrasil).format(itemVenda.getValor());
                        val.addRow(new Object[]{itemVenda.getDescricao(), quantidadeString, itemVenda.getUnidadeMedida(),valor});

                    }

                    jTextFieldCategoria.setText("Venda");
                    jTextFieldData.setText(dataVm);
                    jTextFieldHora.setText(horaVm);
                    String valorVenda = NumberFormat.getCurrencyInstance(localBrasil).format(venda.getValor());
                    jTextFieldValor.setText(valorVenda);
                    jTextFieldFuncionario.setText(venda.getCaixaModel().getFuncionarioModel().getNome());

                } catch (ParseException ex) {
                    Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NaoEncontradoException ex) {
                    Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
    }//GEN-LAST:event_jTableConsultaHistoricoCaixaMouseClicked

    private void jTableConsultaHistoricoCaixaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableConsultaHistoricoCaixaKeyReleased

    }//GEN-LAST:event_jTableConsultaHistoricoCaixaKeyReleased

    private void jTabbedPane1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane1KeyPressed

    }//GEN-LAST:event_jTabbedPane1KeyPressed

    private void jTabbedPane1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTabbedPane1KeyReleased

    }//GEN-LAST:event_jTabbedPane1KeyReleased

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        super.dispose();

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButtonRetirarValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRetirarValorActionPerformed

        RetiradaView retirar = new RetiradaView(null, true,this.funcionarioLoginModel, this);
        retirar.setVisible(true);
    }//GEN-LAST:event_jButtonRetirarValorActionPerformed

    private void jButtonReporValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReporValorActionPerformed

        EntradaView repor = new EntradaView(null, true, this.funcionarioLoginModel ,this);
        repor.setVisible(true);
    }//GEN-LAST:event_jButtonReporValorActionPerformed

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed

        int i, quantidadeColuna = jTableConsultaHistoricoCaixa.getColumnCount();
        //SAIU Do CAIXA E ESTA Na tela de consulta
        CaixaDAO caixaDao = new CaixaDAO();
        CaixaModel caixaModel = new CaixaModel();

        try {

            boolean abrirCaixa, fecharCaixa, excluirLancamento, historico,
            consultar, cancelar, repor, retirar;

            caixaModel = caixaDao.pesquisarCaixa();
            reset();

            //ESTAVA NA TELA PRINCIPAL E ESTA NA CONSULTA

            if(this.telaAtual == 0 && jTabbedPane1.getSelectedIndex()==2){

                jTabbedPane1.setSelectedIndex(0);
                jTabbedPane1.setEnabledAt(0, true); //TELA PRINCIPAL
                jTabbedPane1.setEnabledAt(1, false); //TELA HISTORICO CAIXA
                jTabbedPane1.setEnabledAt(2, false); // TELA PESQUISA
                resetaCampos();

                alteraBotoes(abrirCaixa=true, fecharCaixa=true,
                    historico=true, consultar=true,
                    cancelar=false, repor=true,
                    retirar=true, caixaModel);

                //SAIU Do Historico E ESTA Na tela de consulta
            }else if(this.telaAtual == 1 && jTabbedPane1.getSelectedIndex()==2){

                jTabbedPane1.setSelectedIndex(1);
                jTabbedPane1.setEnabledAt(0, false); //TELA PRINCIPAL
                jTabbedPane1.setEnabledAt(1, true); //TELA HISTORICO CAIXA
                jTabbedPane1.setEnabledAt(2, false); // TELA PESQUISA
                historicoMovimentacaoCaixa();
                resetaCampos();

                alteraBotoes(abrirCaixa=true, fecharCaixa=true,
                    historico=false, consultar=true,
                    cancelar=true, repor=false,
                    retirar=false, caixaModel);

                //alteraBotoes(false, false, false, false, true, true, false, false, caixaModel);
                // ESTAVA NO HISTORICO E ESTA NO HISTORICO
            }else if(this.telaAtual == 1 && jTabbedPane1.getSelectedIndex()==1){

                jTabbedPane1.setSelectedIndex(0);
                jTabbedPane1.setEnabledAt(0, true); //TELA PRINCIPAL
                jTabbedPane1.setEnabledAt(1, false); //TELA HISTORICO CAIXA
                jTabbedPane1.setEnabledAt(2, false); // TELA PESQUISA

                resetaCampos();

                alteraBotoes(abrirCaixa=true, fecharCaixa=true,
                    historico=true, consultar=true,
                    cancelar=false, repor=true,
                    retirar=true, caixaModel);

                // alteraBotoes(true, true, true, true, true, true, true, true, caixaModel);

                // ESTAVA NO PRINCIPAL E ESTA NO HISTORICO
            }else if(this.telaAtual == 0 && jTabbedPane1.getSelectedIndex()==1){

                jTabbedPane1.setSelectedIndex(0);
                jTabbedPane1.setEnabledAt(0, true); //TELA PRINCIPAL
                jTabbedPane1.setEnabledAt(1, false); //TELA HISTORICO CAIXA
                jTabbedPane1.setEnabledAt(2, false); // TELA PESQUISA
                resetaCampos();

                alteraBotoes(abrirCaixa=true, fecharCaixa=true,
                    historico=true, consultar=true,
                    cancelar=false, repor=true,
                    retirar=true, caixaModel);

                // alteraBotoes(true, true, true, false, true, false, false, false, caixaModel);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultaActionPerformed

        CaixaModel caixaModel = new CaixaModel();
        CaixaDAO caixaDao = new CaixaDAO();
        FormataStringParaData formata = new FormataStringParaData();

        DecimalFormat df = new DecimalFormat(",##0,00");
        Locale localBrasil = new Locale("pt", "BR");

        try {
            caixaModel = caixaDao.pesquisarCaixa();

            String situacao = caixaModel.getSituacao();

            jTabbedPane1.setEnabledAt(0, false); //TELA PRINCIPAL
            jTabbedPane1.setEnabledAt(1, false); //TELA HISTORICO CAIXA
            jTabbedPane1.setEnabledAt(2, true); // TELA PESQUISA

            //alteraBotoes(false, false, false, false, true, true, false, false, caixaModel);

        } catch (SQLException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        }

        int linha = -1;

        if (jTabbedPane1.getSelectedIndex() == 0) {

            linha = jTableCaixaDiario.getSelectedRow();

        } else if (jTabbedPane1.getSelectedIndex() == 1) {

            linha = jTableHistoricoCaixa.getSelectedRow();

        }

        if (linha == -1) {

            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta", JOptionPane.WARNING_MESSAGE);

        } else {

            //CONSULTAR CAIXA DO DIA

            if (jTabbedPane1.getSelectedIndex() == 0) {

                //BOTOES CONSULTA

                this.telaAtual = jTabbedPane1.getSelectedIndex();
                jTabbedPane1.setSelectedIndex(2);

                jButtonHistorico.setEnabled(false);
                jButtonConsulta.setEnabled(false);
                jButtonCancelar.setEnabled(true);
                jButtonReporValor.setEnabled(false);
                jButtonRetirarValor.setEnabled(false);
                jPanelJustificativaConsulta.setVisible(false);
           
                //Pegar Categoria
                String categoria = (String) jTableCaixaDiario.getValueAt(linha, 0);

                try {
                    PreencherResumoConsulta(caixaModel);
                } catch (SQLException ex) {
                    Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NaoEncontradoException ex) {
                    Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (categoria.equals("Retirada")) {

                    try {

                        String data = (String) jTableCaixaDiario.getValueAt(linha, 2);

                        String hora = (String) jTableCaixaDiario.getValueAt(linha, 3);

                        RetiradaModel retirada = new RetiradaModel();
                        RetiradaDAO retiradaDao = new RetiradaDAO();

                        retirada = retiradaDao.pesquisarDataHora(formata.formatarDataHoraStringToDateString(data, hora));

                        jPanelCampoCliente.setVisible(false);
                        jPanelCampoTipoPagamento.setVisible(false);

                        jPanelMovimentoCaixa.setVisible(false);
                        jPanelItemVendaHistorico.setVisible(false);
                        jPanelDesconto.setVisible(false);
                        //  jLabelLegendaTabelaMovimentoCaixa.setVisible(false);

                        //jPanelFundoTabelaConsultar.setVisible(false);

                        //jTextFieldCategoria.setText("Valor Retirado");
                        jTextFieldCategoria.setText("Retirada");
                        jTextFieldData.setText(data);
                        jTextFieldHora.setText(hora);
                       
                        String valorRetirada = NumberFormat.getCurrencyInstance(localBrasil).format(retirada.getValor());

                        jTextFieldValor.setText(valorRetirada);
                        jTextFieldFuncionario.setText(retirada.getCaixa().getFuncionarioModel().getNome());
                        jTextAreaDescricao.setText(retirada.getDescricao());
                        
                    } catch (ParseException ex) {
                        Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NaoEncontradoException ex) {
                        Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else if (categoria.equals("Entrada")) {

                    try {
                        String dataRp = (String) jTableCaixaDiario.getValueAt(linha, 2);
                        String horaRp = (String) jTableCaixaDiario.getValueAt(linha, 3);
                        EntradaModel reposicao = new EntradaModel();
                        EntradaDAO reposicaoDao = new EntradaDAO();

                        reposicao = reposicaoDao.pesquisarDataHora(formata.formatarDataHoraStringToDateString(dataRp, horaRp));
                        //
                        jPanelCampoCliente.setVisible(false);
                        jPanelCampoTipoPagamento.setVisible(false);

                        jPanelMovimentoCaixa.setVisible(false);
                        jPanelItemVendaHistorico.setVisible(false);
                        jPanelDesconto.setVisible(false);
                        jTextFieldCategoria.setText("Entrada");
                        jTextFieldData.setText(dataRp);
                        jTextFieldHora.setText(horaRp);
                        jTextAreaDescricao.setText(reposicao.getDescricao());
                        String valorReposicao = NumberFormat.getCurrencyInstance(localBrasil).format(reposicao.getValor());

                        jTextFieldValor.setText(valorReposicao);
                        jTextFieldFuncionario.setText(reposicao.getCaixa().getFuncionarioModel().getNome());

                    } catch (ParseException ex) {
                        Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NaoEncontradoException ex) {
                        Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {

                    //VENDA
                    try {
                        //                        jLabelLegendaCliente.setVisible(true);
                        VendaModel venda = new VendaModel();
                        VendaDAO vendaDao = new VendaDAO();
                        ItemVendaDAO itemVendaDao = new ItemVendaDAO();

                        ArrayList<ItemVenda> listaVenda = new ArrayList<>();

                        String dataVm = (String) jTableCaixaDiario.getValueAt(linha, 2);
                        String horaVm = (String) jTableCaixaDiario.getValueAt(linha, 3);
                        
                        venda = vendaDao.pesquisarDataHora(formata.formatarDataStringToSQLString(dataVm), horaVm);

                        listaVenda = itemVendaDao.pesquisarItemVenda(venda.getId());

                        DefaultTableModel val = (DefaultTableModel) jTableConsultaHistoricoCaixaItemVenda.getModel();
                        val.setNumRows(0);
                        
                      
                      
                        for (ItemVenda itemVenda : listaVenda) {
                            
                            String quantidadeString = String.valueOf(itemVenda.getQuantidade());
                            quantidadeString = quantidadeString.replace(".", "").replace(",", "");
                            if(itemVenda.getUnidadeMedida().equals("KG")){
                            NumberFormat formatter = new DecimalFormat("#0.000"); 
                            quantidadeString = formatter.format(itemVenda.getQuantidade()).replace(".", ",");
                            }
                              String valor= NumberFormat.getCurrencyInstance(localBrasil).format(itemVenda.getValor());
                            val.addRow(new Object[]{itemVenda.getDescricao(), quantidadeString, itemVenda.getUnidadeMedida(), valor});

                        }

                        //   jLabelLegendaTabelaMovimentoCaixa.setVisible(false);
                        //jPanelItemVendaHistorico.setVisible(false);
                        jPanelCampoCliente.setVisible(true);
                        jPanelCampoTipoPagamento.setVisible(false);
                        jPanelMovimentoCaixa.setVisible(false);
                        jPanelDesconto.setVisible(true);
                        jPanelItemVendaHistorico.setVisible(true);
                        jTextFieldCliente.setText(venda.getClienteModel().getNome());
                        if(venda.getFormaPagamento().equals("D")){
                            jTextFieldTipoPagamento.setText("Dinheiro");
                        }else{
                            jTextFieldTipoPagamento.setText("Cartão");
                        }

                        jTextFieldCategoria.setText("Venda");
                        jTextFieldData.setText(dataVm);
                        jTextFieldHora.setText(horaVm);
                        String valorVenda = NumberFormat.getCurrencyInstance(localBrasil).format(venda.getValor());
                        String valorDesconto = NumberFormat.getCurrencyInstance(localBrasil).format(venda.getDesconto());
                        jTextFieldDesconto.setText(valorDesconto);
                        jTextFieldValor.setText(valorVenda);
                        jTextFieldFuncionario.setText(venda.getCaixaModel().getFuncionarioModel().getNome());

                    } catch (ParseException ex) {
                        Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NaoEncontradoException ex) {
                        Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

                // SE FOR CONSULTAR HISTORICO DE CAIXA

            } else if (jTabbedPane1.getSelectedIndex() == 1) {

                int linhaHistorico = -1;
                jPanelJustificativaConsulta.setVisible(true);

                this.telaAtual = jTabbedPane1.getSelectedIndex();
                linhaHistorico = jTableHistoricoCaixa.getSelectedRow();

                if (linhaHistorico == -1) {

                    Toolkit.getDefaultToolkit().beep();
                    JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta",JOptionPane.WARNING_MESSAGE);

                } else {

                    String dataH = (String) jTableHistoricoCaixa.getValueAt(linhaHistorico,0);
                    String horaH = (String) jTableHistoricoCaixa.getValueAt(linhaHistorico,1);

                    jLabelResumoConsultaDataFechamento.setVisible(true);
                    jLabelDataFechamentConsulta.setVisible(true);

                    jLabelResumoConsultaCatao.setText("0.00");
                    jLabelResumoConsultaDataAbertura.setText("0.00");
                    jLabelResumoConsultaDataFechamento.setText("0.00");
                    jLabelResumoConsultaDesconto.setText("0.00");
                    jLabelResumoConsultaDinheiro.setText("0.00");
                    jLabelResumoConsultaEntrada.setText("0.00");
                    jLabelResumoConsultaRetirada.setText("0.00");
                    jLabelResumoConsultaQtdVenda.setText("0.00");

                    try {

                        caixaModel = caixaDao.pesquisarDataHora(formata.formatarDataHoraStringToDateString(dataH, horaH));
                        PreencherResumoConsulta(caixaModel);
                        atualizaTabelaConsulta(caixaModel);

                        jPanelMovimentoCaixa.setVisible(true);

                        jTabbedPane1.setSelectedIndex(2);

                        jButtonHistorico.setEnabled(false);
                        jButtonConsulta.setEnabled(false);
                        jButtonCancelar.setEnabled(true);
                        jButtonReporValor.setEnabled(false);
                        jButtonRetirarValor.setEnabled(false);

                    } catch (ParseException ex) {
                        Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NaoEncontradoException ex) {
                        Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }

            }
        }
    }//GEN-LAST:event_jButtonConsultaActionPerformed

    private void jButtonHistoricoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHistoricoActionPerformed

        pesquisarHistorico();

    }//GEN-LAST:event_jButtonHistoricoActionPerformed

    private void jButtonFecharCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFecharCaixaActionPerformed

        FecharCaixaView fecharCaixa = new FecharCaixaView(null, true, this.funcionarioLoginModel, p, f, this);
        fecharCaixa.setVisible(true);

    }//GEN-LAST:event_jButtonFecharCaixaActionPerformed

    private void jButtonAbrirCaixaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAbrirCaixaActionPerformed

        CaixaModel caixa = new CaixaModel();
        CaixaDAO  caixaDao = new CaixaDAO();
        boolean statusCaixa;

        try {

            caixa = caixaDao.pesquisarCaixa();
            statusCaixa = caixa.getFinaliza();

            if(statusCaixa == false){

                AbrirCaixaView abrirCaixa = null;
                abrirCaixa = new AbrirCaixaView(null, true, this.funcionarioLoginModel, p, f, this);
                super.dispose();
                abrirCaixa.setVisible(true);

            }else{
                String data = new SimpleDateFormat("dd/MM/yyyy").format(caixa.getDataAbertura());
                JOptionPane.showMessageDialog(null, "O caixa do dia "+data+" está com diferença de valores. Verifique o problema para continuar","alerta",JOptionPane.WARNING_MESSAGE);
                pesquisarHistorico();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(ControleCaixaView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonAbrirCaixaActionPerformed
    
    
    public void convertHora(String hora, String minuto){
     this.minutoPesquisa = minuto;
     this.horaPesquisa = hora;
     this.horaCompletaPesquisa = this.horaPesquisa.concat(":"+this.minutoPesquisa);
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
            java.util.logging.Logger.getLogger(ControleCaixaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ControleCaixaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ControleCaixaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ControleCaixaView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new ControleCaixaView(null,null,null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButtonAbrirCaixa;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonConsulta;
    private javax.swing.JButton jButtonFecharCaixa;
    private javax.swing.JButton jButtonHistorico;
    private javax.swing.JButton jButtonReporValor;
    private javax.swing.JButton jButtonRetirarValor;
    private javax.swing.JComboBox<String> jComboBoxCategoria;
    private com.toedter.calendar.JDateChooser jDateChooserDataHistorico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelCaixaStatus;
    private javax.swing.JLabel jLabelData;
    private javax.swing.JLabel jLabelDataFechamentConsulta;
    private javax.swing.JLabel jLabelHora;
    private javax.swing.JLabel jLabelImagemFinalizado;
    private javax.swing.JLabel jLabelImagemNaoFinalizado;
    private javax.swing.JLabel jLabelLegendaPesquisaCaixaDiario;
    private javax.swing.JLabel jLabelResumoConsultaCatao;
    private javax.swing.JLabel jLabelResumoConsultaDataAbertura;
    private javax.swing.JLabel jLabelResumoConsultaDataFechamento;
    private javax.swing.JLabel jLabelResumoConsultaDesconto;
    private javax.swing.JLabel jLabelResumoConsultaDinheiro;
    private javax.swing.JLabel jLabelResumoConsultaEntrada;
    private javax.swing.JLabel jLabelResumoConsultaQtdVenda;
    private javax.swing.JLabel jLabelResumoConsultaRetirada;
    private javax.swing.JLabel jLabelResumoConsultaValorFinal;
    private javax.swing.JLabel jLabelResumoConsultaValorInicial;
    private javax.swing.JLabel jLabelResumoLegendaCartao;
    private javax.swing.JLabel jLabelResumoLegendaData;
    private javax.swing.JLabel jLabelResumoLegendaDinheiro;
    private javax.swing.JLabel jLabelResumoLegendaDinheiro1;
    private javax.swing.JLabel jLabelResumoLegendaHora;
    private javax.swing.JLabel jLabelResumoLegendaMensagemStatusCaixa;
    private javax.swing.JLabel jLabelResumoLegendaMovimentacaoCaixa;
    private javax.swing.JLabel jLabelResumoLegendaQtdVendas;
    private javax.swing.JLabel jLabelResumoLegendaReposicao;
    private javax.swing.JLabel jLabelResumoLegendaRetirada;
    private javax.swing.JLabel jLabelResumoLegendaSaldoInicial;
    private javax.swing.JLabel jLabelResumoNomeStatusCaixa;
    private javax.swing.JLabel jLabelResumoQuantidadeVenda;
    private javax.swing.JLabel jLabelResumoSaldoAtual;
    private javax.swing.JLabel jLabelResumoSaldoInicial;
    private javax.swing.JLabel jLabelResumoSaldoLegendaAtual;
    private javax.swing.JLabel jLabelResumoTotalCartao;
    private javax.swing.JLabel jLabelResumoTotalDesconto;
    private javax.swing.JLabel jLabelResumoTotalDinheiro;
    private javax.swing.JLabel jLabelResumoTotalReposto;
    private javax.swing.JLabel jLabelResumoTotalRetiradas;
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
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelCampoCategoria;
    private javax.swing.JPanel jPanelCampoCliente;
    private javax.swing.JPanel jPanelCampoData;
    private javax.swing.JPanel jPanelCampoFuncionario;
    private javax.swing.JPanel jPanelCampoHora;
    private javax.swing.JPanel jPanelCampoTipoPagamento;
    private javax.swing.JPanel jPanelCampoValor;
    private javax.swing.JPanel jPanelDesconto;
    private javax.swing.JPanel jPanelFundoDescricaoConsulta;
    private javax.swing.JPanel jPanelFundoStatusCaixa;
    private javax.swing.JPanel jPanelItemVendaHistorico;
    private javax.swing.JPanel jPanelJustificativaConsulta;
    private javax.swing.JPanel jPanelMovimentoCaixa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableCaixaDiario;
    private javax.swing.JTable jTableConsultaHistoricoCaixa;
    private javax.swing.JTable jTableConsultaHistoricoCaixaItemVenda;
    private javax.swing.JTable jTableHistoricoCaixa;
    private javax.swing.JTextArea jTextAreaDescricao;
    private javax.swing.JTextArea jTextAreaJustificativa;
    private javax.swing.JTextField jTextFieldCategoria;
    private javax.swing.JTextField jTextFieldCliente;
    private javax.swing.JTextField jTextFieldData;
    private javax.swing.JTextField jTextFieldDesconto;
    private javax.swing.JTextField jTextFieldFuncionario;
    private javax.swing.JTextField jTextFieldHora;
    private javax.swing.JTextField jTextFieldTipoPagamento;
    private javax.swing.JTextField jTextFieldValor;
    // End of variables declaration//GEN-END:variables
}
