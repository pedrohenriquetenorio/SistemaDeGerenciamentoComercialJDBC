
package Visao;

import Control.Exceptions.NaoEncontradoException;
import DAO.CaixaDAO;
import DAO.ClienteDAO;
import DAO.EstoqueDAO;
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
import java.awt.Toolkit;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pedro
 */
public class OrcamentoView extends javax.swing.JDialog {
 FuncionarioModel funcionario;
 PrincipalGerenteView pg;
    public OrcamentoView(java.awt.Frame parent, boolean modal, FuncionarioModel funcionario, PrincipalGerenteView pg) {
        super(parent, modal);
        initComponents();
        this.pg = pg;
        this.funcionario = funcionario;
    atualizaTabela();
    jRadioButton1.isSelected();
    }
    public void filtroPesquisa(){
         LocalDateTime dataHoje = LocalDateTime.now();
        OrcamentoDAO orcamentoDao = new OrcamentoDAO();
        ArrayList<OrcamentoModel>listaOrcamento = new ArrayList<>();
        
        DefaultTableModel val = (DefaultTableModel) jTableOrcamento.getModel();
        val.setNumRows(0); // excluir os registros que estão na JTable
     
     
        
        if(listaOrcamento!=null){
            
        try {
            
            
                    
            listaOrcamento = orcamentoDao.findAll();
            
             listaOrcamento.forEach((orcamentos) -> {
              String aprovacao = "";
              String data = new SimpleDateFormat("dd/MM/yyyy").format(orcamentos.getData());
              String hora = new SimpleDateFormat("HH:mm:ss").format(orcamentos.getData());
                
              LocalDateTime diaOrcamento = orcamentos.getData().toLocalDateTime();
              
              Timestamp dias = Timestamp.valueOf(diaOrcamento);
              LocalDateTime vencimento = dias.toLocalDateTime().plusDays(5);
              boolean compareValue = dataHoje.isBefore(vencimento);
                
                
             if(orcamentos.getData().equals(orcamentos.getDataAprovacao()) && compareValue == true){
                 
                aprovacao="Não aprovado";
              
             }else if(compareValue == false){
                 
                aprovacao="Vencido";
              
             }else{
                    
                aprovacao="Aprovado";
              
             }
                   
        });
            
            
        } catch (SQLException ex) {
            Logger.getLogger(OrcamentoView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(OrcamentoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
           
        val.setNumRows(0); 
  
    }
    
    }
    
    public void atualizaTabela(){
        
        LocalDateTime dataHoje = LocalDateTime.now();
        OrcamentoDAO orcamentoDao = new OrcamentoDAO();
        ArrayList<OrcamentoModel>listaOrcamento = new ArrayList<>();
        
        DefaultTableModel val = (DefaultTableModel) jTableOrcamento.getModel();
        val.setNumRows(0); // excluir os registros que estão na JTable
        
        if(listaOrcamento!=null){
            
        try {
               
            listaOrcamento = orcamentoDao.findAll();
              Locale localBrasil = new Locale("pt", "BR");
        
              listaOrcamento.forEach((orcamentos) -> {
              String aprovacao = "";
              String somaValor = NumberFormat.getCurrencyInstance(localBrasil).format(orcamentos.getValorTotal());  
              String data = new SimpleDateFormat("dd/MM/yyyy").format(orcamentos.getData());
              String hora = new SimpleDateFormat("HH:mm:ss").format(orcamentos.getData());
              LocalDateTime diaOrcamento = orcamentos.getData().toLocalDateTime();
              Timestamp dias = Timestamp.valueOf(diaOrcamento);
              LocalDateTime vencimento = dias.toLocalDateTime().plusDays(5);
              
                //SE O VENCIMENTO FOR ANTER DA DATA DE HOJE NÂO ESTA VENCIDO 
                // DE VOLTAR FALSE QUER DIZER QUE ESTA VENCIDO
             
               // int compareValue = vencimento.compareTo();
                
                boolean compareValue = dataHoje.isBefore(vencimento);
             
             if(orcamentos.getData().equals(orcamentos.getDataAprovacao()) && compareValue == true){
                 
                aprovacao="Não aprovado";
                val.addRow(new Object[]{orcamentos.getOrc_id(), data,hora, somaValor,orcamentos.getCliente().getNome(),orcamentos.getFuncionario().getNome(),aprovacao});
      
             }else if(compareValue == false){
                 
                aprovacao="Vencido";
                val.addRow(new Object[]{orcamentos.getOrc_id(),data,hora ,somaValor,orcamentos.getCliente().getNome(),orcamentos.getFuncionario().getNome(),aprovacao});
           
             }else{
                    
                aprovacao="Aprovado";
                val.addRow(new Object[]{orcamentos.getOrc_id(), data,hora ,somaValor,orcamentos.getCliente().getNome(),orcamentos.getFuncionario().getNome(),aprovacao});
           
             }
                   
        });
            
            
        } catch (SQLException ex) {
            Logger.getLogger(OrcamentoView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(OrcamentoView.class.getName()).log(Level.SEVERE, null, ex);
        }
        }else{
           
        val.setNumRows(0); 
  
    }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButtonAprovarOrcamento = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jTextFieldPesquisar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableOrcamento = new javax.swing.JTable();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Orçamento");

        jPanel1.setBackground(new java.awt.Color(3, 133, 188));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gerenciar Orçamento");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/adicionar24x24.png"))); // NOI18N
        jButton1.setText("Novo Orçamento");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Atualizar24x24.png"))); // NOI18N
        jButton2.setText("Alterar Orçamento");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButtonAprovarOrcamento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Concluir30x30.png"))); // NOI18N
        jButtonAprovarOrcamento.setText("Aprovar Orçamento");
        jButtonAprovarOrcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAprovarOrcamentoActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/excluir24x24.png"))); // NOI18N
        jButton4.setText("Excluir Orçamento");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/consulta24x24.png"))); // NOI18N
        jButton5.setText("Pesquisar Orçamento");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/VoltarSimples24x24.png"))); // NOI18N
        jButton6.setText("Sair");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAprovarOrcamento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAprovarOrcamento, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Pesquisar Orçamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lupa.png"))); // NOI18N
        jButton7.setText("Pesquisar");

        jLabel2.setText("Codigo:");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Status", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Todos");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Aprovados");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Não Aprovados");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setText("Vencidos");
        jRadioButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton4)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton4))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap(21, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7)
                            .addComponent(jTextFieldPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2)))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 61, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTableOrcamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Data", "Hora", "Valor", "Cliente ", "Funcionário", "Situação"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableOrcamento);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 412, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Principal", jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        super.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButtonAprovarOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAprovarOrcamentoActionPerformed
        CaixaModel caixaModel = new CaixaModel();
        CaixaDAO caixaDao = new CaixaDAO();
        OrcamentoDAO orcamentoDao = new OrcamentoDAO();
        OrcamentoModel orcamentoModel = new OrcamentoModel();
        ArrayList<ItemVenda>listaItensVenda = new ArrayList<>();
        ArrayList<ItemOrcamento>listaItensOrcamento = new ArrayList<>();
        VendaModel vendas = new VendaModel();
        VendaDAO vendaDao = new VendaDAO();
        
        ItemVenda itemVendaModel = new ItemVenda();
        ItemVendaDAO itemVendaDao = new ItemVendaDAO();
        
        ItemOrcamento itemOrcamento = new ItemOrcamento();
        ItemOrcamentoDAO itemOrcamentoDao = new ItemOrcamentoDAO();
        
        CaixaModel caixas = new CaixaModel();
 
        
        EstoqueModel estoqueModel = new EstoqueModel();
        EstoqueDAO estoqueDao = new EstoqueDAO();
        
        ProdutoModel produtoModel = new ProdutoModel();
        
        int id, linha = -1;

        linha = jTableOrcamento.getSelectedRow();

        if (linha == -1) {
            
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta", JOptionPane.WARNING_MESSAGE);

        } else {
            String verificaAprovacao = (String) jTableOrcamento.getValueAt(linha,6);
            
           if(verificaAprovacao.equals("Aprovado")){
               
               JOptionPane.showMessageDialog(null, "Este orçamento já esta aprovado","Alerta", JOptionPane.WARNING_MESSAGE);
               
           }else if(verificaAprovacao.equals("Vencido")){
               
               JOptionPane.showMessageDialog(null, "Este orçamento está vencido, faça um novo orçamento", "Erro",JOptionPane.ERROR_MESSAGE);
               
           }else{
               
                try {
                    
                    
                    
                    int ids = (int) jTableOrcamento.getValueAt(linha, 0);
                    caixas = caixaDao.pesquisarCaixa();
                    orcamentoModel = orcamentoDao.pesquisarId(ids);
                    listaItensOrcamento = itemOrcamentoDao.pesquisarItemOrcamento(orcamentoModel.getOrc_id());
                    
                    Timestamp datas = new Timestamp(System.currentTimeMillis());

                    datas.toLocalDateTime();
                    
                    orcamentoModel.setDataAprovacao(datas);
                    String dataAprovacao = new SimpleDateFormat("yyyy-MM-dd").format(orcamentoModel.getDataAprovacao());
                    String horaAprovacao = new SimpleDateFormat("HH:mm:ss").format(orcamentoModel.getDataAprovacao());
                    Time horaAprovacaoTime = Time.valueOf(horaAprovacao);
                    Date dataAprovacaoDate = Date.valueOf(dataAprovacao);
                    //Colocar Orçamento no Venda
                    vendas.setCaixaModel(caixas);
                    vendas.setClienteModel(orcamentoModel.getCliente());
                    vendas.setDataVenda(dataAprovacaoDate);
                    vendas.setHoraVenda(horaAprovacaoTime);
                    vendas.setDesconto(orcamentoModel.getDesconto());
                    vendas.setFormaPagamento(orcamentoModel.getFormaPagamento());
                    vendas.setFuncionario(orcamentoModel.getFuncionario());
                    vendas.setValor(orcamentoModel.getValorTotal());
                    
                    vendaDao.inserir(vendas);
                    vendas = vendaDao.ultimaVanda2();
                    
                    //listaItensVenda = itemVendaDao.pesquisarItemVenda(vendas.getId());
                    //listaItensOrcamento = itemOrcamentoDao.pesquisarItemOrcamento(orcamentoModel.getOrc_id());
                    double totalQuantidade;
                    double valorTotalPagar = orcamentoModel.getValorTotal() + caixas.getValorFinal();
                    caixas.setValorFinal(valorTotalPagar);
                    caixaDao.alterarValorFinal(caixas);
                    
                    for(ItemOrcamento o : listaItensOrcamento){
                        produtoModel = o.getEstoque().getProduto();
                    
                        estoqueModel = estoqueDao.pesquisaEstoqueCodigoBarras(produtoModel.getCodigoBarra());
                        itemVendaModel.setDescricao(o.getDescricao());
                        itemVendaModel.setEstoque(o.getEstoque());
                        itemVendaModel.setQuantidade(o.getQuantidade());
                        itemVendaModel.setUnidadeMedida(o.getUnidadeMedida());
                        itemVendaModel.setValor(o.getValorUnitario());
                        itemVendaModel.setVenda(vendas);
                        totalQuantidade = estoqueModel.getQtdEstoque() - o.getQuantidade();
                        estoqueModel.setFuncionario(funcionario);
                        estoqueModel.setQtdEstoque(totalQuantidade);
                        itemVendaDao.inserir(itemVendaModel);
                        estoqueDao.alteraQuantidade(estoqueModel);
                    }
                    
                    orcamentoDao.Aprovar(orcamentoModel);
                    
                    atualizaTabela();

                } catch (SQLException ex) {
                    Logger.getLogger(OrcamentoView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NaoEncontradoException ex) {
                    Logger.getLogger(OrcamentoView.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(OrcamentoView.class.getName()).log(Level.SEVERE, null, ex);
                }
           
           }
        }
    }//GEN-LAST:event_jButtonAprovarOrcamentoActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      int id, linha = -1;
        OrcamentoModel orcamento = new OrcamentoModel();
        OrcamentoDAO orcamentoDao = new OrcamentoDAO();
         linha = jTableOrcamento.getSelectedRow();
      if (linha == -1) {
            
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta", JOptionPane.WARNING_MESSAGE);

        } else {
          try {
              id = (int) jTableOrcamento.getValueAt(linha, 0);
              orcamento = orcamentoDao.pesquisarId(id);
              // Se for igual ele pergunta se realmente quer excluir orcamento
             int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir o orcamento ?", "Confirmar",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

              if (resposta == 0) {
              
                  orcamentoDao.excluir(orcamento);
                atualizaTabela();
              }
          } catch (SQLException ex) {
              Logger.getLogger(OrcamentoView.class.getName()).log(Level.SEVERE, null, ex);
          } catch (NaoEncontradoException ex) {
              Logger.getLogger(OrcamentoView.class.getName()).log(Level.SEVERE, null, ex);
          }
               
        }
    }//GEN-LAST:event_jButton4ActionPerformed
    
    
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       int id, linha = -1;

        linha = jTableOrcamento.getSelectedRow();

        if (linha == -1) {
            
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta", JOptionPane.WARNING_MESSAGE);

        } else {
            
           try {
               OrcamentoModel orcamento = new OrcamentoModel();
               OrcamentoDAO orcamentoDao = new OrcamentoDAO();
               id = (int) jTableOrcamento.getValueAt(linha, 0);
               
               orcamento = orcamentoDao.pesquisarId(id);
               
               Venda venda = new Venda("alterar orcamento", orcamento.getCliente(), orcamento, pg);
               venda.alterarOrcamento(orcamento);
               super.dispose();
               venda.setVisible(true);
               
               
           } catch (SQLException ex) {
               Logger.getLogger(OrcamentoView.class.getName()).log(Level.SEVERE, null, ex);
           } catch (NaoEncontradoException ex) {
               Logger.getLogger(OrcamentoView.class.getName()).log(Level.SEVERE, null, ex);
           }
        
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
            Venda venda = new Venda("novo orcamento", null, null, pg);
            this.dispose();
            venda.setVisible(true);
         
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int id, linha = -1;

        linha = jTableOrcamento.getSelectedRow();

        if (linha == -1) {
            
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta", JOptionPane.WARNING_MESSAGE);

        } else {
            
           try {
               
               OrcamentoModel orcamento = new OrcamentoModel();
               OrcamentoDAO orcamentoDao = new OrcamentoDAO();
               id = (int) jTableOrcamento.getValueAt(linha, 0);
               
               orcamento = orcamentoDao.pesquisarId(id);
               
               Venda venda = new Venda("pesquisar orcamento", orcamento.getCliente(), orcamento, pg);
               venda.alterarOrcamento(orcamento);
               super.dispose();
               venda.setVisible(true);
               
           } catch (SQLException ex) {
               Logger.getLogger(OrcamentoView.class.getName()).log(Level.SEVERE, null, ex);
           } catch (NaoEncontradoException ex) {
               Logger.getLogger(OrcamentoView.class.getName()).log(Level.SEVERE, null, ex);
           }
        
        }
       
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(OrcamentoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrcamentoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrcamentoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrcamentoView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                OrcamentoView dialog = new OrcamentoView(new javax.swing.JFrame(), true, null, null);
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
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButtonAprovarOrcamento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableOrcamento;
    private javax.swing.JTextField jTextFieldPesquisar;
    // End of variables declaration//GEN-END:variables
}
