/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import Control.Exceptions.NaoEncontradoException;
import DAO.CaixaDAO;
import DAO.CompraDAO;
import DAO.ContasPagarDAO;
import DAO.VendaDAO;
import Modelo.CaixaModel;
import Modelo.CompraModel;
import Modelo.ContaPagarModel;
import Modelo.ContaReceberModel;
import Modelo.VendaModel;
import java.awt.Color;
import java.awt.Font;
import java.sql.Date;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;

/**
 *
 * @author pedro
 */
public class ControleFinanceiroView extends javax.swing.JFrame {
    
    List<CompraModel>listaCompra = new ArrayList<>();
   
    List<VendaModel>listaVenda = new ArrayList<>();
    List<ContaPagarModel>listaContaPagar = new ArrayList<>();
    List<ContaReceberModel>listaContaReceber = new ArrayList<>();
    List<CaixaModel>listaCaixa = new ArrayList<>();
  
    int ano;
    String dataSelecionada, nomeDataSelecionada;
    public ControleFinanceiroView() {
        Date dataAtual = new Date(System.currentTimeMillis());
        dataAtual.toLocalDate();
        
        
        initComponents();
        jRadioButtonvenda.setSelected(true);
        Font myFont = new Font("Dialog", Font.BOLD, 36);
          jYearChooser1.setFont(myFont);
       
        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataAtual);
        FormataStringParaData formata = new FormataStringParaData();
        String mesAtual = formata.retornaMesString(data);
        String mesNumeral = mesAtual;
        
        switch(mesAtual) 
        {
            case "01":
                mesAtual = "Janeiro";
                        
            break;
            case "02":
                mesAtual = "Fevereiro";
            break;
            case "03":
                mesAtual = "Março";
            break;
            case "04":
                mesAtual = "Abril";
            break;
            case "05":
                mesAtual = "Maio";
            break;
            case "06":
                mesAtual = "Junho";
            break;
            case "07":
                mesAtual = "Julho";
                
            break;
            case "08":
                mesAtual = "Agosto";
                
            break;
            case "09":
                mesAtual = "Setembro";
            break;
            case "10":
                mesAtual = "Outubro";
            break;
            case "11":
                mesAtual = "Novembro";
            break;
            case "12":
                mesAtual = "Dezembro";
            break;
        }
        
        
        
        ano = jYearChooser1.getYear();
        
        try {
        CompraDAO compraDao = new CompraDAO();
        CaixaDAO caixaDao = new CaixaDAO();
        ContasPagarDAO contaPagarDao = new ContasPagarDAO();
        VendaDAO vendaDao = new VendaDAO();
        
        // COMPRA
        listaCompra = compraDao.finalizados();
        
        // CAIXA
        listaCaixa = caixaDao.findAll();

        // CONTA A PAGAR
        listaContaPagar = contaPagarDao.findAll();

        // VENDA
        listaVenda = vendaDao.findAll();
        
        // CHAMA O GRAFICO
        
       
       
        preencheCampos(mesNumeral, mesAtual);
        
        
         } catch (SQLException ex) {
            Logger.getLogger(ControleFinanceiroView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(ControleFinanceiroView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(ControleFinanceiroView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        criaGraficoMesCompra(listaCompra,
                listaCaixa,
        listaVenda,listaContaPagar,mesAtual);
    }
    
        //Parametros
        //GRAFICO
    
        public void criaGraficoMesCompra(List<CompraModel>listaCompra,
                List<CaixaModel>listaCaixa,
                List<VendaModel>listaVenda,
                List<ContaPagarModel>listaDespesa,
        String titulo){
            
            GraficoFinanceiro g = new GraficoFinanceiro();
            CategoryDataset cds;
            
            //1 - VERIFICA QUAL É A TABELA -> parametros do RADIO BUTTON
            if (jRadioButtonCompra.isSelected() == true) {
                
                    cds = g.createDatasetDinamicoCompra(listaCompra);
                    
            } else if (jRadioButtonDespesa.isSelected() == true) {
                    
                    cds = g.createDatasetDinamicoDespesa(listaDespesa);
            
            } else if (jRadioButtonEntrada.isSelected() == true) {
             
                    cds = g.createDatasetDinamicoEntrada(listaCaixa);
                
            } else if (jRadioButtonvenda.isSelected() == true){
           
                    cds = g.createDatasetDinamicoVenda(listaVenda);
                
            } else {
            cds = g.createDatasetDinamicoVenda(listaVenda);
            }
            
            //2 - SELECIONA O TIPO DE PESQUIS TODOS OU MES
            
            String eixoy = "Valor Total por Dia";

            String txt_legenda = "";
            
           // boolean legenda = true;

           // boolean tooltips = true;

           // boolean urls = true;
            
            JFreeChart graf = ChartFactory.createBarChart3D(titulo, txt_legenda, eixoy, cds, PlotOrientation.VERTICAL, false, true, true);
            ChartPanel myChartPanel = new ChartPanel(graf, true);
            
            CategoryPlot plot = graf.getCategoryPlot();
            //largura da barra
            BarRenderer br = (BarRenderer) plot.getRenderer();
            br.setMaximumBarWidth(.10);
            
            if (jRadioButtonCompra.isSelected() == true) {

                for (int i = 0; i < listaCompra.size(); i++) {

                    plot.getRenderer().setSeriesPaint(i, new Color(3, 133, 188));

                }
            } else if (jRadioButtonDespesa.isSelected() == true) {

                for (int i = 0; i < listaDespesa.size(); i++) {

                    plot.getRenderer().setSeriesPaint(i, new Color(3, 133, 188));

                }
            } else if (jRadioButtonEntrada.isSelected() == true) {

                for (int i = 0; i < listaCaixa.size(); i++) {

                    plot.getRenderer().setSeriesPaint(i, new Color(3, 133, 188));

                }
            } else if(jRadioButtonvenda.isSelected() == true){

                for (int i = 0; i < listaVenda.size(); i++) {

                    plot.getRenderer().setSeriesPaint(i, new Color(3, 133, 188));

                }
            }
            
            myChartPanel.setSize(jPanelGraficoVenda.getWidth(), jPanelGraficoVenda.getHeight());

            myChartPanel.setVisible(true);

            jPanelGraficoVenda.removeAll();

            jPanelGraficoVenda.add(myChartPanel);

            jPanelGraficoVenda.revalidate();

           jPanelGraficoVenda.repaint();
           
        }
        
        
     //MOSTRADORES
    public void preencheCampos(String data, String nomeData) {
       this.dataSelecionada = data;
       this.nomeDataSelecionada = nomeData;
        try {
            FormataStringParaData formata = new FormataStringParaData();
            Double vTotalVenda = 0.0,vTotalCompra = 0.0,vTotalConta = 0.0,vTotalCaixa = 0.0;
            
            double totalVenda = 0, totalCompra = 0, totalEntradas = 0, totalDespesas = 0;
            
            Locale localBrasil = new Locale("pt", "BR");
            
            
            ArrayList<CompraModel>listaCompraMes = new ArrayList<>();
            ArrayList<ContaPagarModel>listaContaPagar = new ArrayList<>();
            ArrayList<VendaModel>listaVenda = new ArrayList<>();
            ArrayList<CaixaModel>listaCaixa = new ArrayList<>();
            
            String anoString = String.valueOf(this.ano);
            String dataPesquisa = anoString.concat("-"+data);
            //VERIFICA O RADIOBOX
            ContasPagarDAO contaPagarDao = new ContasPagarDAO();
            CompraDAO compraDao = new CompraDAO();
            CaixaDAO caixaDao = new CaixaDAO();
            VendaDAO vendaDao = new VendaDAO();
            
            listaVenda = vendaDao.pesquisarGraficoPorAnoMes(dataPesquisa);
            listaCompraMes = compraDao.pesquisarGraficoPorAnoMes(dataPesquisa);
            listaCaixa = caixaDao.pesquisarGraficoPorAnoMes(dataPesquisa);
            listaContaPagar = contaPagarDao.pesquisarGraficoPorAnoMes(dataPesquisa);
            
            if(listaVenda!=null){
            for(VendaModel listaVendas: listaVenda){
                totalVenda = totalVenda + listaVendas.getValor();
                String valorInicial = NumberFormat.getCurrencyInstance(localBrasil).format(totalVenda);
               
                jLabelVendas.setText(valorInicial);
                
            }   
            }else{
                
                String valorInicial = NumberFormat.getCurrencyInstance(localBrasil).format(0);
                jLabelVendas.setText(valorInicial);
            }
            
            if(listaCompraMes!=null){
            for(CompraModel listaCompras: listaCompraMes){
                totalCompra = totalCompra + listaCompras.getValor();
                String valorInicial = NumberFormat.getCurrencyInstance(localBrasil).format(totalCompra);
                jLabelCompra.setText(valorInicial);
            }
            }else{
                String valorInicial = NumberFormat.getCurrencyInstance(localBrasil).format(0);
                jLabelCompra.setText(valorInicial);
            }
            
            if(listaCaixa != null){
                
            for(CaixaModel listaEntradas: listaCaixa){
                totalEntradas = totalEntradas + listaEntradas.getValorFinal();
                String valorInicial = NumberFormat.getCurrencyInstance(localBrasil).format(totalEntradas);
                jLabelEntrada.setText(valorInicial);
            }
            
            }else{
                String valorInicial = NumberFormat.getCurrencyInstance(localBrasil).format(0);
                jLabelEntrada.setText(valorInicial);
            }
            if(listaContaPagar != null){
            for(ContaPagarModel listaConta: listaContaPagar){
                totalDespesas = totalDespesas + listaConta.getValor();
                String valorInicial = NumberFormat.getCurrencyInstance(localBrasil).format(totalDespesas);
                jLabelDespesas.setText(valorInicial);
              
            }
                      
            }else{
                String valorInicial = NumberFormat.getCurrencyInstance(localBrasil).format(0);
                jLabelDespesas.setText(valorInicial);
            }
            if(jRadioButtonCompra.isSelected()==true){
                
                if (!listaCompra.isEmpty()) {
                    
                    criaGraficoMesCompra(listaCompraMes, this.listaCaixa, this.listaVenda, this.listaContaPagar, nomeData);
                }
                
            }else if(jRadioButtonDespesa.isSelected()==true){
                if (!this.listaContaPagar.isEmpty()) {
                    
                    criaGraficoMesCompra(this.listaCompra, this.listaCaixa, this.listaVenda, listaContaPagar, nomeData);
                    
                }
            }else if(jRadioButtonEntrada.isSelected()==true){
                
                if(!this.listaCaixa.isEmpty()) {
                    
                    criaGraficoMesCompra(this.listaCompra, listaCaixa, this.listaVenda, this.listaContaPagar, nomeData);
                }
                
            }else if(jRadioButtonvenda.isSelected()==true){
                
                if(!this.listaVenda.isEmpty()) {
                    
                    criaGraficoMesCompra(this.listaCompra, this.listaCaixa, listaVenda, this.listaContaPagar, nomeData);
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControleFinanceiroView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(ControleFinanceiroView.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabelDespesas = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabelCompra = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabelEntrada = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabelVendas = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanelGraficoVenda = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanelJaneiro = new javax.swing.JPanel();
        jLabelJaneiro = new javax.swing.JLabel();
        jPanelFevereiro = new javax.swing.JPanel();
        jLabelFevereiro = new javax.swing.JLabel();
        jPanelMarco = new javax.swing.JPanel();
        jLabelMarco = new javax.swing.JLabel();
        jPanelAbril = new javax.swing.JPanel();
        jLabelAbril = new javax.swing.JLabel();
        jPanelMaio = new javax.swing.JPanel();
        jLabelMaio = new javax.swing.JLabel();
        jPanelJunho = new javax.swing.JPanel();
        jLabelJunho = new javax.swing.JLabel();
        jPanelJulho = new javax.swing.JPanel();
        jLabelJulho = new javax.swing.JLabel();
        jPanelAgosto = new javax.swing.JPanel();
        jLabelAgosto = new javax.swing.JLabel();
        jPanelSetembro = new javax.swing.JPanel();
        jLabelSetembro = new javax.swing.JLabel();
        jPanelOutubro = new javax.swing.JPanel();
        jLabelOutubro = new javax.swing.JLabel();
        jPanelNovembro = new javax.swing.JPanel();
        jLabelNovembro = new javax.swing.JLabel();
        jPanelDezembro = new javax.swing.JPanel();
        jLabelDezembro = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jRadioButtonvenda = new javax.swing.JRadioButton();
        jRadioButtonDespesa = new javax.swing.JRadioButton();
        jRadioButtonCompra = new javax.swing.JRadioButton();
        jRadioButtonEntrada = new javax.swing.JRadioButton();
        jPanel9 = new javax.swing.JPanel();
        jYearChooser1 = new com.toedter.calendar.JYearChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Controle Financeiro");
        setExtendedState(6);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(3, 133, 188));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Controle Financeiro");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel16.setBackground(new java.awt.Color(206, 48, 48));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 16, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabelDespesas.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabelDespesas.setText("R$ 0,00");

        jLabel11.setText("Valor total de Despesas");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDespesas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 157, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelDespesas, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel19.setBackground(new java.awt.Color(3, 133, 188));

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 17, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabelCompra.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabelCompra.setText("R$ 0,00");

        jLabel12.setText("Valor total de Compras");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelCompra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jLabelCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
            .addComponent(jPanel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel14.setName(""); // NOI18N

        jPanel20.setBackground(new java.awt.Color(45, 158, 45));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 17, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabelEntrada.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabelEntrada.setText("R$ 0,00");

        jLabel13.setText("Valor total de Entradas");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(0, 196, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelEntrada)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel21.setBackground(new java.awt.Color(207, 207, 47));

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 17, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabelVendas.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N
        jLabelVendas.setText("R$ 0,00");

        jLabel14.setText("Valor total de vendas");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelVendas, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelVendas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/contaPagar24x24.png"))); // NOI18N
        jButton2.setText("Contas A pagar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/contaReceber24x24.png"))); // NOI18N
        jButton3.setText("Contas A Receber");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/relatorio24x24.png"))); // NOI18N
        jButton4.setText("Relatório");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/VoltarSimples24x24.png"))); // NOI18N
        jButton5.setText("Fechar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 193, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Grafico Financeiro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jPanelGraficoVenda.setBackground(new java.awt.Color(102, 102, 255));
        jPanelGraficoVenda.setMaximumSize(new java.awt.Dimension(99999, 99999));

        javax.swing.GroupLayout jPanelGraficoVendaLayout = new javax.swing.GroupLayout(jPanelGraficoVenda);
        jPanelGraficoVenda.setLayout(jPanelGraficoVendaLayout);
        jPanelGraficoVendaLayout.setHorizontalGroup(
            jPanelGraficoVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1114, Short.MAX_VALUE)
        );
        jPanelGraficoVendaLayout.setVerticalGroup(
            jPanelGraficoVendaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 327, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelGraficoVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelGraficoVenda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Filtro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));

        jPanelJaneiro.setBackground(new java.awt.Color(102, 102, 102));
        jPanelJaneiro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabelJaneiro.setBackground(new java.awt.Color(255, 255, 255));
        jLabelJaneiro.setForeground(new java.awt.Color(255, 255, 255));
        jLabelJaneiro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelJaneiro.setText("Janeiro");
        jLabelJaneiro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelJaneiroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelJaneiroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelJaneiroMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelJaneiroLayout = new javax.swing.GroupLayout(jPanelJaneiro);
        jPanelJaneiro.setLayout(jPanelJaneiroLayout);
        jPanelJaneiroLayout.setHorizontalGroup(
            jPanelJaneiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelJaneiro, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
        );
        jPanelJaneiroLayout.setVerticalGroup(
            jPanelJaneiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelJaneiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanelFevereiro.setBackground(new java.awt.Color(102, 102, 102));
        jPanelFevereiro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabelFevereiro.setBackground(new java.awt.Color(255, 255, 255));
        jLabelFevereiro.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFevereiro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelFevereiro.setText("Fevereiro");
        jLabelFevereiro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelFevereiroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelFevereiroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelFevereiroMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelFevereiroLayout = new javax.swing.GroupLayout(jPanelFevereiro);
        jPanelFevereiro.setLayout(jPanelFevereiroLayout);
        jPanelFevereiroLayout.setHorizontalGroup(
            jPanelFevereiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelFevereiro, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
        );
        jPanelFevereiroLayout.setVerticalGroup(
            jPanelFevereiroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelFevereiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanelMarco.setBackground(new java.awt.Color(102, 102, 102));
        jPanelMarco.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabelMarco.setBackground(new java.awt.Color(255, 255, 255));
        jLabelMarco.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMarco.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMarco.setText("Março");
        jLabelMarco.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMarcoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelMarcoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelMarcoMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelMarcoLayout = new javax.swing.GroupLayout(jPanelMarco);
        jPanelMarco.setLayout(jPanelMarcoLayout);
        jPanelMarcoLayout.setHorizontalGroup(
            jPanelMarcoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelMarco, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
        );
        jPanelMarcoLayout.setVerticalGroup(
            jPanelMarcoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelMarco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanelAbril.setBackground(new java.awt.Color(102, 102, 102));
        jPanelAbril.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabelAbril.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAbril.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAbril.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAbril.setText("Abril");
        jLabelAbril.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAbrilMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelAbrilMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelAbrilMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelAbrilLayout = new javax.swing.GroupLayout(jPanelAbril);
        jPanelAbril.setLayout(jPanelAbrilLayout);
        jPanelAbrilLayout.setHorizontalGroup(
            jPanelAbrilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelAbril, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
        );
        jPanelAbrilLayout.setVerticalGroup(
            jPanelAbrilLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelAbril, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanelMaio.setBackground(new java.awt.Color(102, 102, 102));
        jPanelMaio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabelMaio.setBackground(new java.awt.Color(255, 255, 255));
        jLabelMaio.setForeground(new java.awt.Color(255, 255, 255));
        jLabelMaio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMaio.setText("Maio");
        jLabelMaio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMaioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelMaioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelMaioMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelMaioLayout = new javax.swing.GroupLayout(jPanelMaio);
        jPanelMaio.setLayout(jPanelMaioLayout);
        jPanelMaioLayout.setHorizontalGroup(
            jPanelMaioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelMaio, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
        );
        jPanelMaioLayout.setVerticalGroup(
            jPanelMaioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelMaio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanelJunho.setBackground(new java.awt.Color(102, 102, 102));
        jPanelJunho.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabelJunho.setBackground(new java.awt.Color(255, 255, 255));
        jLabelJunho.setForeground(new java.awt.Color(255, 255, 255));
        jLabelJunho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelJunho.setText("Junho");
        jLabelJunho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelJunhoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelJunhoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelJunhoMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelJunhoLayout = new javax.swing.GroupLayout(jPanelJunho);
        jPanelJunho.setLayout(jPanelJunhoLayout);
        jPanelJunhoLayout.setHorizontalGroup(
            jPanelJunhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelJunho, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
        );
        jPanelJunhoLayout.setVerticalGroup(
            jPanelJunhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelJunho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanelJulho.setBackground(new java.awt.Color(102, 102, 102));
        jPanelJulho.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabelJulho.setBackground(new java.awt.Color(255, 255, 255));
        jLabelJulho.setForeground(new java.awt.Color(255, 255, 255));
        jLabelJulho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelJulho.setText("Julho");
        jLabelJulho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelJulhoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelJulhoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelJulhoMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelJulhoLayout = new javax.swing.GroupLayout(jPanelJulho);
        jPanelJulho.setLayout(jPanelJulhoLayout);
        jPanelJulhoLayout.setHorizontalGroup(
            jPanelJulhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelJulho, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
        );
        jPanelJulhoLayout.setVerticalGroup(
            jPanelJulhoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelJulho, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanelAgosto.setBackground(new java.awt.Color(102, 102, 102));
        jPanelAgosto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabelAgosto.setBackground(new java.awt.Color(255, 255, 255));
        jLabelAgosto.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAgosto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelAgosto.setText("Agosto");
        jLabelAgosto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAgostoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelAgostoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelAgostoMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelAgostoLayout = new javax.swing.GroupLayout(jPanelAgosto);
        jPanelAgosto.setLayout(jPanelAgostoLayout);
        jPanelAgostoLayout.setHorizontalGroup(
            jPanelAgostoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelAgosto, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
        );
        jPanelAgostoLayout.setVerticalGroup(
            jPanelAgostoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelAgosto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanelSetembro.setBackground(new java.awt.Color(102, 102, 102));
        jPanelSetembro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabelSetembro.setBackground(new java.awt.Color(255, 255, 255));
        jLabelSetembro.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSetembro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSetembro.setText("Setembro");
        jLabelSetembro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelSetembroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelSetembroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelSetembroMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelSetembroLayout = new javax.swing.GroupLayout(jPanelSetembro);
        jPanelSetembro.setLayout(jPanelSetembroLayout);
        jPanelSetembroLayout.setHorizontalGroup(
            jPanelSetembroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelSetembro, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
        );
        jPanelSetembroLayout.setVerticalGroup(
            jPanelSetembroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelSetembro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanelOutubro.setBackground(new java.awt.Color(102, 102, 102));
        jPanelOutubro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabelOutubro.setBackground(new java.awt.Color(255, 255, 255));
        jLabelOutubro.setForeground(new java.awt.Color(255, 255, 255));
        jLabelOutubro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelOutubro.setText("Outubro");
        jLabelOutubro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelOutubroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelOutubroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelOutubroMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelOutubroLayout = new javax.swing.GroupLayout(jPanelOutubro);
        jPanelOutubro.setLayout(jPanelOutubroLayout);
        jPanelOutubroLayout.setHorizontalGroup(
            jPanelOutubroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelOutubro, javax.swing.GroupLayout.DEFAULT_SIZE, 69, Short.MAX_VALUE)
        );
        jPanelOutubroLayout.setVerticalGroup(
            jPanelOutubroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelOutubro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanelNovembro.setBackground(new java.awt.Color(102, 102, 102));
        jPanelNovembro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabelNovembro.setBackground(new java.awt.Color(255, 255, 255));
        jLabelNovembro.setForeground(new java.awt.Color(255, 255, 255));
        jLabelNovembro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNovembro.setText("Novenbro");
        jLabelNovembro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelNovembroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelNovembroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelNovembroMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelNovembroLayout = new javax.swing.GroupLayout(jPanelNovembro);
        jPanelNovembro.setLayout(jPanelNovembroLayout);
        jPanelNovembroLayout.setHorizontalGroup(
            jPanelNovembroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelNovembro, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
        );
        jPanelNovembroLayout.setVerticalGroup(
            jPanelNovembroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelNovembro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanelDezembro.setBackground(new java.awt.Color(102, 102, 102));
        jPanelDezembro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));

        jLabelDezembro.setBackground(new java.awt.Color(255, 255, 255));
        jLabelDezembro.setForeground(new java.awt.Color(255, 255, 255));
        jLabelDezembro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDezembro.setText("Dezembro");
        jLabelDezembro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelDezembroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelDezembroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelDezembroMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelDezembroLayout = new javax.swing.GroupLayout(jPanelDezembro);
        jPanelDezembro.setLayout(jPanelDezembroLayout);
        jPanelDezembroLayout.setHorizontalGroup(
            jPanelDezembroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelDezembro, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
        );
        jPanelDezembroLayout.setVerticalGroup(
            jPanelDezembroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelDezembro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelJaneiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanelFevereiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelMarco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelAbril, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelMaio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelJunho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelJulho, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelAgosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelSetembro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelOutubro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelNovembro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelDezembro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelDezembro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelNovembro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelOutubro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelSetembro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelAgosto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelJulho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelJunho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelMaio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelAbril, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelMarco, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelFevereiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelJaneiro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));

        buttonGroup1.add(jRadioButtonvenda);
        jRadioButtonvenda.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jRadioButtonvenda.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButtonvenda.setText("Venda");
        jRadioButtonvenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonvendaActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonDespesa);
        jRadioButtonDespesa.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jRadioButtonDespesa.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButtonDespesa.setText("Despesas");
        jRadioButtonDespesa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonDespesaActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonCompra);
        jRadioButtonCompra.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jRadioButtonCompra.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButtonCompra.setText("Compras");
        jRadioButtonCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonCompraActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonEntrada);
        jRadioButtonEntrada.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jRadioButtonEntrada.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButtonEntrada.setText("Entradas");
        jRadioButtonEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonEntradaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonvenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonDespesa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonEntrada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonCompra)
                .addContainerGap(605, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonCompra)
                    .addComponent(jRadioButtonEntrada)
                    .addComponent(jRadioButtonDespesa)
                    .addComponent(jRadioButtonvenda))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Ano", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N
        jPanel9.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N

        jYearChooser1.setBackground(new java.awt.Color(255, 255, 255));
        jYearChooser1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jYearChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jYearChooser1PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jYearChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jYearChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(5, 5, 5))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        ContaPagarView contaPagar = new ContaPagarView(null, true);
        contaPagar.setVisible(true);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       
        ContaReceberView contaReceber = new ContaReceberView(null, true);
        contaReceber.setVisible(true);
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       super.dispose();
       
    }//GEN-LAST:event_jButton5ActionPerformed

    //ENTRADA DO MOUSE
    
    private void jLabelJaneiroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJaneiroMouseEntered
         //jPanelFundoBotaoAlterar.setBackground(new Color(9, 155, 217));
        jPanelJaneiro.setBackground(new Color(153,153,153));
    }//GEN-LAST:event_jLabelJaneiroMouseEntered

    private void jLabelFevereiroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelFevereiroMouseEntered
        // TODO add your handling code here:
        jPanelFevereiro.setBackground(new Color(153,153,153));
    }//GEN-LAST:event_jLabelFevereiroMouseEntered

    private void jLabelMarcoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMarcoMouseEntered
        // TODO add your handling code here:
        jPanelMarco.setBackground(new Color(153,153,153));
    }//GEN-LAST:event_jLabelMarcoMouseEntered

    private void jLabelAbrilMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAbrilMouseEntered
        // TODO add your handling code here:
        jPanelAbril.setBackground(new Color(153,153,153));
    }//GEN-LAST:event_jLabelAbrilMouseEntered

    private void jLabelMaioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMaioMouseEntered
        // TODO add your handling code here:
        jPanelMaio.setBackground(new Color(153,153,153));
    }//GEN-LAST:event_jLabelMaioMouseEntered

    private void jLabelJunhoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJunhoMouseEntered
        // TODO add your handling code here:
        jPanelJunho.setBackground(new Color(153,153,153));
    }//GEN-LAST:event_jLabelJunhoMouseEntered

    private void jLabelJulhoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJulhoMouseEntered
        // TODO add your handling code here:
        jPanelJulho.setBackground(new Color(153,153,153));
    }//GEN-LAST:event_jLabelJulhoMouseEntered

    private void jLabelAgostoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAgostoMouseEntered
        // TODO add your handling code here:
        jPanelAgosto.setBackground(new Color(153,153,153));
    }//GEN-LAST:event_jLabelAgostoMouseEntered

    private void jLabelSetembroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSetembroMouseEntered
        // TODO add your handling code here:
        jPanelSetembro.setBackground(new Color(153,153,153));
    }//GEN-LAST:event_jLabelSetembroMouseEntered

    private void jLabelOutubroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelOutubroMouseEntered
        // TODO add your handling code here:
        jPanelOutubro.setBackground(new Color(153,153,153));
    }//GEN-LAST:event_jLabelOutubroMouseEntered

    private void jLabelNovembroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelNovembroMouseEntered
        // TODO add your handling code here:
        jPanelNovembro.setBackground(new Color(153,153,153));
    }//GEN-LAST:event_jLabelNovembroMouseEntered

    private void jLabelDezembroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelDezembroMouseEntered
        // TODO add your handling code here:
        jPanelDezembro.setBackground(new Color(153,153,153));
    }//GEN-LAST:event_jLabelDezembroMouseEntered

    //SAIDA DO MOUSE
    
    private void jLabelJaneiroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJaneiroMouseExited
        // TODO add your handling code here:
        jPanelJaneiro.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_jLabelJaneiroMouseExited

    private void jLabelFevereiroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelFevereiroMouseExited
        jPanelFevereiro.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_jLabelFevereiroMouseExited

    private void jLabelMarcoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMarcoMouseExited
        jPanelMarco.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_jLabelMarcoMouseExited

    private void jLabelAbrilMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAbrilMouseExited
        jPanelAbril.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_jLabelAbrilMouseExited

    private void jLabelMaioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMaioMouseExited
        jPanelMaio.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_jLabelMaioMouseExited

    private void jLabelJunhoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJunhoMouseExited
        jPanelJunho.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_jLabelJunhoMouseExited

    private void jLabelJulhoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJulhoMouseExited
       jPanelJulho.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_jLabelJulhoMouseExited

    private void jLabelAgostoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAgostoMouseExited
        jPanelAgosto.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_jLabelAgostoMouseExited

    private void jLabelSetembroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSetembroMouseExited
        jPanelSetembro.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_jLabelSetembroMouseExited

    private void jLabelOutubroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelOutubroMouseExited
       jPanelOutubro.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_jLabelOutubroMouseExited

    private void jLabelNovembroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelNovembroMouseExited
       jPanelNovembro.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_jLabelNovembroMouseExited

    private void jLabelDezembroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelDezembroMouseExited
        jPanelDezembro.setBackground(new Color(102,102,102));
    }//GEN-LAST:event_jLabelDezembroMouseExited
    
    public void limpaMostrador(){
    
        Locale localBrasil = new Locale("pt", "BR");
        String valorInicial = NumberFormat.getCurrencyInstance(localBrasil).format(0);     
        
        jLabelVendas.setText(valorInicial);
        jLabelCompra.setText(valorInicial);
        jLabelDespesas.setText(valorInicial);
        jLabelEntrada.setText(valorInicial);
    
    }
    
    //CLICK DO MOUSE
    
    private void jLabelJaneiroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJaneiroMouseClicked
       limpaMostrador();
        preencheCampos("01", "Janeiro");
        
    }//GEN-LAST:event_jLabelJaneiroMouseClicked

    private void jLabelFevereiroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelFevereiroMouseClicked
       limpaMostrador();
        preencheCampos("02","Fevereiro");
    }//GEN-LAST:event_jLabelFevereiroMouseClicked

    private void jLabelMarcoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMarcoMouseClicked
         limpaMostrador();
        preencheCampos("03","Março");
    }//GEN-LAST:event_jLabelMarcoMouseClicked

    private void jLabelAbrilMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAbrilMouseClicked
         limpaMostrador();
        preencheCampos("04", "Abril");
    }//GEN-LAST:event_jLabelAbrilMouseClicked

    private void jLabelMaioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMaioMouseClicked
        limpaMostrador();
        preencheCampos("05", "Maio");
    }//GEN-LAST:event_jLabelMaioMouseClicked

    private void jLabelJunhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJunhoMouseClicked
         limpaMostrador();
        preencheCampos("06", "Junho");
    }//GEN-LAST:event_jLabelJunhoMouseClicked

    private void jLabelJulhoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelJulhoMouseClicked
         limpaMostrador();
        preencheCampos("07", "Julho");
    }//GEN-LAST:event_jLabelJulhoMouseClicked

    private void jLabelAgostoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAgostoMouseClicked
         limpaMostrador();
        preencheCampos("08", "Agosto");
    }//GEN-LAST:event_jLabelAgostoMouseClicked

    private void jLabelSetembroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelSetembroMouseClicked
        limpaMostrador();
        preencheCampos("09", "Setembro");
    }//GEN-LAST:event_jLabelSetembroMouseClicked

    private void jLabelOutubroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelOutubroMouseClicked
         limpaMostrador();
        preencheCampos("10", "Outubro");
    }//GEN-LAST:event_jLabelOutubroMouseClicked

    private void jLabelNovembroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelNovembroMouseClicked
        limpaMostrador();
        preencheCampos("11", "Novembro");
    }//GEN-LAST:event_jLabelNovembroMouseClicked

    private void jLabelDezembroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelDezembroMouseClicked
        limpaMostrador();
       preencheCampos("12", "Dezembro");
    }//GEN-LAST:event_jLabelDezembroMouseClicked

    private void jYearChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jYearChooser1PropertyChange
        
        ano = jYearChooser1.getYear(); 
        
        
    }//GEN-LAST:event_jYearChooser1PropertyChange

    private void jRadioButtonvendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonvendaActionPerformed
        preencheCampos(dataSelecionada, nomeDataSelecionada);
    }//GEN-LAST:event_jRadioButtonvendaActionPerformed

    private void jRadioButtonCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonCompraActionPerformed
         preencheCampos(dataSelecionada, nomeDataSelecionada);
    }//GEN-LAST:event_jRadioButtonCompraActionPerformed

    private void jRadioButtonDespesaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonDespesaActionPerformed
        preencheCampos(dataSelecionada, nomeDataSelecionada);
    }//GEN-LAST:event_jRadioButtonDespesaActionPerformed

    private void jRadioButtonEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonEntradaActionPerformed
         preencheCampos(dataSelecionada, nomeDataSelecionada);
    }//GEN-LAST:event_jRadioButtonEntradaActionPerformed
   
   
   
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
            java.util.logging.Logger.getLogger(ControleFinanceiroView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ControleFinanceiroView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ControleFinanceiroView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ControleFinanceiroView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ControleFinanceiroView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabelAbril;
    private javax.swing.JLabel jLabelAgosto;
    private javax.swing.JLabel jLabelCompra;
    private javax.swing.JLabel jLabelDespesas;
    private javax.swing.JLabel jLabelDezembro;
    private javax.swing.JLabel jLabelEntrada;
    private javax.swing.JLabel jLabelFevereiro;
    private javax.swing.JLabel jLabelJaneiro;
    private javax.swing.JLabel jLabelJulho;
    private javax.swing.JLabel jLabelJunho;
    private javax.swing.JLabel jLabelMaio;
    private javax.swing.JLabel jLabelMarco;
    private javax.swing.JLabel jLabelNovembro;
    private javax.swing.JLabel jLabelOutubro;
    private javax.swing.JLabel jLabelSetembro;
    private javax.swing.JLabel jLabelVendas;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelAbril;
    private javax.swing.JPanel jPanelAgosto;
    private javax.swing.JPanel jPanelDezembro;
    private javax.swing.JPanel jPanelFevereiro;
    private javax.swing.JPanel jPanelGraficoVenda;
    private javax.swing.JPanel jPanelJaneiro;
    private javax.swing.JPanel jPanelJulho;
    private javax.swing.JPanel jPanelJunho;
    private javax.swing.JPanel jPanelMaio;
    private javax.swing.JPanel jPanelMarco;
    private javax.swing.JPanel jPanelNovembro;
    private javax.swing.JPanel jPanelOutubro;
    private javax.swing.JPanel jPanelSetembro;
    private javax.swing.JRadioButton jRadioButtonCompra;
    private javax.swing.JRadioButton jRadioButtonDespesa;
    private javax.swing.JRadioButton jRadioButtonEntrada;
    private javax.swing.JRadioButton jRadioButtonvenda;
    private com.toedter.calendar.JYearChooser jYearChooser1;
    // End of variables declaration//GEN-END:variables
}
