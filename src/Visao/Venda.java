
package Visao;

import Control.Exceptions.NaoEncontradoException;
import DAO.EstoqueDAO;
import DAO.FuncionarioDAO;
import DAO.ItemOrcamentoDAO;
import Modelo.ClienteModel;
import Modelo.EstoqueModel;
import Modelo.FuncionarioModel;
import Modelo.ItemOrcamento;
import Modelo.ItemVenda;
import Modelo.OrcamentoModel;
import Modelo.ProdutoModel;
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
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Venda extends javax.swing.JFrame {

    String titulo;
    int quantidadeEstoque = 0;
    Double descontoFinal = 0.0;
    OrcamentoModel orcamento = new OrcamentoModel();
    Double totalDescontoAnterior = 0.0, totalDesconto = 0.0, valorTotalReferencia = 0.0, valorTotalPagar = 0.0;
    FuncionarioModel funcionario;
    ClienteModel clienteModel;
    PrincipalGerenteView pg;
    EstoqueModel estoqueModel = new EstoqueModel();
    List<ItemOrcamento> itensOrcamento = new ArrayList<>();
    List<ItemVenda> itensVenda = new ArrayList<>();
    StringBuffer quantidadeSB;
    String tela;
    boolean tipoRetorno = false;
    
    public Venda(String tela, ClienteModel clienteM, OrcamentoModel orcamento, PrincipalGerenteView pg) {
        initComponents();

        teclaAtalho();
        this.orcamento = orcamento;
        this.tela = tela;
        this.pg = pg;
        if (clienteM == null) {
            ClienteModel cliente = new ClienteModel();
            this.clienteModel = cliente;
        } else {
            this.clienteModel = clienteM;
        }
        
        if (tela.equals("")) {
            
             System.out.println("TELA "+tela);
            jRadioButtonVenda.setSelected(true);
            jButtonCancelarAlteracao.setVisible(false);
            jTextFieldCpf.setEditable(true);
            jTextFieldNomeCliente.setEditable(true);
            jTextFieldQuantidade.setEditable(true);
            jTextFieldCodigoBarra.setEditable(true);
            jButtonAdicionarCliente.setEnabled(true);
            jButtonAdicionarProduto.setEnabled(true);
            jButtonPesquisarCliente.setEnabled(true);
            jButtonPesquisarProduto.setEnabled(true);
            jButtonRemoverProduto.setEnabled(true);
            jPanelFormaPagamento.setVisible(true);
            jPanelTipo.setVisible(true);
            jButtonFinaliza.setVisible(true);
            
        } else if (tela.equals("alterar orcamento")) {
             System.out.println("TELA "+tela);
            jRadioButtonOrcamento.setSelected(true);
            jButtonCancelarAlteracao.setVisible(true);
            jPanelFormaPagamento.setVisible(false);
            jTextFieldCpf.setEditable(true);
            jTextFieldNomeCliente.setEditable(true);
            jTextFieldQuantidade.setEditable(true);
            jTextFieldCodigoBarra.setEditable(true);
            jButtonAdicionarCliente.setEnabled(true);
            jButtonAdicionarProduto.setEnabled(true);
            jButtonPesquisarCliente.setEnabled(true);
            jButtonPesquisarProduto.setEnabled(true);
            jButtonRemoverProduto.setEnabled(true);
            jButtonFinaliza.setVisible(true);
            jPanelTipo.setVisible(false);
            
        } else if (tela.equals("pesquisar orcamento")) {
             System.out.println("TELA "+tela);
            jRadioButtonOrcamento.setSelected(true);
            jPanelFormaPagamento.setVisible(false);
            jTextFieldCpf.setEditable(false);
            jTextFieldNomeCliente.setEditable(false);
            jTextFieldQuantidade.setEditable(false);
            jTextFieldCodigoBarra.setEditable(false);
            jButtonAdicionarCliente.setEnabled(false);
            jButtonAdicionarProduto.setEnabled(false);
            jButtonPesquisarCliente.setEnabled(false);
            jButtonPesquisarProduto.setEnabled(false);
            jButtonRemoverProduto.setEnabled(false);
            jButtonFinaliza.setVisible(false);
            
                        
            jPanelTipo.setVisible(false);
        }else if (tela.equals("novo orcamento")) {
            
            jRadioButtonOrcamento.setSelected(true);
            jTextFieldCpf.setEditable(true);
            jTextFieldNomeCliente.setEditable(true);
            jTextFieldQuantidade.setEditable(true);
            jTextFieldCodigoBarra.setEditable(true);
            jButtonAdicionarCliente.setEnabled(true);
            jButtonAdicionarProduto.setEnabled(true);
            jButtonPesquisarCliente.setEnabled(true);
            jButtonPesquisarProduto.setEnabled(true);
            jButtonRemoverProduto.setEnabled(true);
            jPanelFormaPagamento.setVisible(false);
            jPanelTipo.setVisible(false);
            jButtonFinaliza.setVisible(true);
        }
        
        
        alteraTitulo();
        
        //jTextFieldQuantidade.setDocument(new FormatacaoDinamicaDinheiro());
        Timestamp datas = new Timestamp(System.currentTimeMillis());
        datas.toLocalDateTime();
        // jPanelBotoesAlterarOrcamento.setVisible(false);
        String data = new SimpleDateFormat("dd/MM/yyyy").format(datas);
        jTextFieldCodigoBarra.requestFocus();
        jTextFieldCpf.setEditable(false);
        jTextFieldNomeCliente.setEditable(false);
        jTextFieldUnidadeMedida.setEditable(false);
        jTextFieldQuantidade.setEditable(false);

        jPanelAlterarOrcamento.setVisible(false);
        jRadioButtonDinheiro.setSelected(true);

        jLabelData.setText(data);

        pesquisarFuncioanrioLogin();

        jLabelFuncionario.setText(this.funcionario.getNome());

        jTableItemVenda.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2 && tipoRetorno == true) {
                    remover();
                } else if (evt.getClickCount() == 2 && tipoRetorno == false) {
                    //removerParaSempre();
                }
            }
        });

    }

    public void pesquisarFuncioanrioLogin() {

        FuncionarioDAO funcionarioDao = new FuncionarioDAO();
        try {
            this.funcionario = funcionarioDao.pesquisarDataHoralogin();
        } catch (SQLException ex) {
            Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void alterarOrcamento(OrcamentoModel orcamento) {

        this.orcamento = orcamento;

        double total;

        DefaultTableModel val = (DefaultTableModel) jTableItemVenda.getModel();
        val.setNumRows(0);
       
        Locale localBrasil = new Locale("pt", "BR");
        
        ItemOrcamentoDAO itemOrcamentoDao = new ItemOrcamentoDAO();
        ItemOrcamento itemOrcamento = new ItemOrcamento();
        ArrayList<EstoqueModel> listaEstoque = new ArrayList<>();
        //EstoqueModel estoqueModel = new EstoqueModel();
        EstoqueDAO estoqueDao = new EstoqueDAO();
        ArrayList<ItemOrcamento> listaOrcamento = new ArrayList<>();
        this.clienteModel = orcamento.getCliente();
        
        jTextFieldNomeCliente.setText(this.clienteModel.getNome());
        jTextFieldCpf.setText(this.clienteModel.getNome());
        
        try {
            jLabelPesquisar.setEnabled(false);
            jButtonCancelarAlteracao.setVisible(true);
            listaOrcamento = itemOrcamentoDao.pesquisarItemOrcamento(orcamento.getOrc_id());

            listaEstoque = estoqueDao.findAll();

            for (ItemOrcamento i : listaOrcamento) {

                total = i.getQuantidade() * i.getValorUnitario();
                total = total / 1000;
                String valorUnit = NumberFormat.getCurrencyInstance(localBrasil).format(i.getValorUnitario());
                String somaValor = NumberFormat.getCurrencyInstance(localBrasil).format(total);
                itemOrcamento = i;

                //PRIMEIRO ADICIONA OS PRODUTOS DO ESTOQUE NO ORCAMENTO, MESMO QUE NO ESTOQUE ESTEJEA 
                for (EstoqueModel estoque : listaEstoque) {
                    //DIGITAR O PRODUTO e Buscar OBJETO ESTOQUE
                    if (estoque.getProduto().getNome().equals(itemOrcamento.getDescricao())) {
                        this.estoqueModel = estoque;

                        adicionarTabelaProduto(itemOrcamento.getQuantidade());

                    }
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void sairParaOrcamento(){
        OrcamentoView orcamento = new OrcamentoView(null, true, funcionario, pg);
        super.dispose();
        orcamento.setVisible(true);
    }
    
    public void novaVenda() {

        this.clienteModel = new ClienteModel();
        this.itensOrcamento.clear();
        if (this.itensVenda != null) {
            this.itensVenda.clear();
        }
        this.quantidadeEstoque = 0;
        this.descontoFinal = 0.0;
        this.orcamento = null;
        this.totalDesconto = 0.0;
        this.totalDesconto = 0.0;
        this.valorTotalReferencia = 0.0;
        this.valorTotalPagar = 0.0;
        this.estoqueModel = new EstoqueModel();
        this.tipoRetorno = false;
        jLabelTotal.setText("0");
        jTextFieldNomeCliente.setText("");
        jTextFieldCpf.setText("");
        atualizaTabela();
        try {
            this.pg.atualizaTabela();
        } catch (SQLException ex) {
            Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void adicionarTabelaProduto(double quantidade) throws SQLException, ParseException, NaoEncontradoException {
        double valor, totalQuantidade;

        EstoqueModel estoqueModel = new EstoqueModel();
        EstoqueDAO estoqueDao = new EstoqueDAO();
        if (jRadioButtonVenda.isSelected()) {
            String codigoBarra = jTextFieldCodigoBarra.getText();
            estoqueModel = estoqueDao.pesquisaProdutoEstoque(codigoBarra);
        }
        estoqueModel = this.estoqueModel;

        valor = estoqueModel.getProduto().getValor();

        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setDescricao(estoqueModel.getProduto().getNome());
        itemVenda.setEstoque(estoqueModel);
        itemVenda.setQuantidade(quantidade);
        itemVenda.setValor(valor);
        itemVenda.setUnidadeMedida(estoqueModel.getProduto().getUnidadeMedida());

        totalQuantidade = estoqueModel.getQtdEstoque() - quantidade;

        estoqueModel.setFuncionario(this.funcionario);
        estoqueModel.setQtdEstoque(totalQuantidade);

        if (jRadioButtonVenda.isSelected()) {
            try {
                estoqueDao.alteraQuantidade(estoqueModel);
            } catch (SQLException ex) {
                Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        ArrayList<ItemVenda> itensVendaAtual = new ArrayList<>();

        int cont = 0;
        if (!this.itensVenda.isEmpty()) {

            //LISTA DE QUANDO TEM O PRODUTO E ADICIONA A QUANTIDADE EM UMA POSICAO ESPECIFICA 
            for (ItemVenda i : this.itensVenda) {
                if (i.getDescricao().equals(itemVenda.getDescricao())) {
                    double quantidade1, quantidade2;
                    quantidade1 = itemVenda.getQuantidade();
                    quantidade2 = i.getQuantidade();
                    itemVenda.setQuantidade(quantidade1 + quantidade2);
                    this.itensVenda.set(cont, itemVenda);

                } else {
                    //QUANTIDA NAO TEM ADCIONA EM UM ARRAY SEPARADO
                    itensVendaAtual.add(itemVenda);
                }
                cont++;
            }
        } else {
            this.itensVenda.add(itemVenda);
        }

        this.itensVenda.addAll(itensVendaAtual);

        List<ItemVenda> itensVenda = new ArrayList<>();

        itensVenda = this.itensVenda.stream().distinct().collect(Collectors.toList());

        this.itensVenda = itensVenda;
        preencheListOrcamento(itensVenda);

        // this.itensVenda.add(itensVenda);
        this.valorTotalPagar = 0.0;
        atualizaTabela();

        limpaCamposVenda();

    }

    public void preencheListOrcamento(List itensVenda) {

        ItemOrcamento itensOrcamento = new ItemOrcamento();
        this.itensOrcamento.clear();

        for (ItemVenda i : this.itensVenda) {

            itensOrcamento.setDescricao(i.getDescricao());
            itensOrcamento.setEstoque(i.getEstoque());
            itensOrcamento.setQuantidade(i.getQuantidade());
            itensOrcamento.setValorUnitario(i.getValor());
            itensOrcamento.setUnidadeMedida(i.getUnidadeMedida());
            itensOrcamento.setOrcamento(null);

            this.itensOrcamento.add(itensOrcamento);
        }

    }

    public void limpaCamposVenda() {

        jTextFieldCodigoBarra.setText("");
        jLabelProduto.setText("");
        jTextFieldQuantidade.setText("");
        jLabelValorProduto.setText("");
        jLabelQuantidadeEstoque.setText("");
        jTextFieldUnidadeMedida.setText("");

    }

    public void teclaAtalho() {

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
                        case KeyEvent.VK_F8:
                            //  concluirVenda();
                            break;

                        case 27:
                            //sair();
                            break;
                        case KeyEvent.VK_SPACE:

                            break;

                        default:
                            break;
                    }
                    return;
                }
            }
        }, AWTEvent.KEY_EVENT_MASK);
    }

    public void adicionarProduto(EstoqueModel estoque) {

        if (estoque.getProduto().getUnidadeMedida().equals("KG")) {
            //FORMATO DE QUANTIDADE POR QUILO
            double quantidades = estoque.getQtdEstoque();//1000;

            //quantidades = quantidades * 100;
            NumberFormat formatter = new DecimalFormat("#0.000");
            String quantidadeStrings = formatter.format(quantidades).replace(".", ",");

            jTextFieldQuantidade.setDocument(new FormatacaoDinamicaQuantidade());

            jTextFieldQuantidade.setText("00");

            jLabelQuantidadeEstoque.setText(quantidadeStrings);

        } else if (estoque.getProduto().getUnidadeMedida().equals("UNID")) {

            jTextFieldQuantidade.setDocument(new FormatacaoDinamicaQuantidadeUnid());
            int quantidade = (int) estoque.getQtdEstoque();
            jLabelQuantidadeEstoque.setText(quantidade + "");
            jTextFieldQuantidade.setText("");
        }

        this.estoqueModel = estoque;
        Locale localBrasil = new Locale("pt", "BR");
        jLabelProduto.setText(estoque.getProduto().getNome());

        jTextFieldUnidadeMedida.setText(estoque.getProduto().getUnidadeMedida());
        jTextFieldCodigoBarra.setText(estoque.getProduto().getCodigoBarra());

        jLabelProduto.setText(estoque.getProduto().getNome());

        String valorTotalString = NumberFormat.getCurrencyInstance(localBrasil).format(estoque.getProduto().getValor());

        jLabelValorProduto.setText(valorTotalString);
        String quantidadeEstoque = String.valueOf(estoque.getQtdEstoque());

        jTextFieldQuantidade.setEditable(true);
        jTextFieldQuantidade.requestFocus();

    }

    public void alteraTitulo() {

        if (jRadioButtonVenda.isSelected()) {

            this.titulo = "Venda"; //Venda
            jLabelTitulo.setText(this.titulo);
            jButtonFinaliza.setText("Finalizar Venda");

        } else if (jRadioButtonOrcamento.isSelected()) {

            this.titulo = "Orçamento"; //Orçamento
            jLabelTitulo.setText(this.titulo);
            jButtonFinaliza.setText("Finalizar Orçamento");

        }

    }

    public void remover() {

        int produto, id;

        EstoqueModel estoqueModel = new EstoqueModel();
        EstoqueDAO estoqueDao = new EstoqueDAO();

        produto = jTableItemVenda.getSelectedRow();
        if (produto == -1) {

            JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta", JOptionPane.WARNING_MESSAGE);

        } else {
            id = jTableItemVenda.getSelectedRow();

            try {

                String codigoBarra = (String) jTableItemVenda.getValueAt(id, 0);
                String quantidadeString = (String) jTableItemVenda.getValueAt(id, 2);
                quantidadeString = quantidadeString.replace(",", ".");
                double quantidade = Double.parseDouble(quantidadeString);
                if(jRadioButtonVenda.isSelected()){
                estoqueModel = estoqueDao.pesquisaEstoqueCodigoBarras(codigoBarra);
                quantidade = quantidade + estoqueModel.getQtdEstoque();
                estoqueModel.setQtdEstoque(quantidade);
                estoqueModel.setFuncionario(this.funcionario);
                estoqueDao.alteraQuantidade(estoqueModel);
                }
                this.itensVenda.remove(id);

                this.valorTotalPagar = 0.0;
                this.valorTotalReferencia = 0.0;
                atualizaTabela();

            } catch (SQLException ex) {
                Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NaoEncontradoException ex) {
                Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void excluir() {
       
        int produto, id;
        ItemOrcamento itemOrcamento = new ItemOrcamento();
        ArrayList<ItemOrcamento> listaOrcamento = new ArrayList<>();
        ItemOrcamentoDAO itemOrcamentoDao = new ItemOrcamentoDAO();

        produto = jTableItemVenda.getSelectedRow();
        if (produto == -1) {

            JOptionPane.showMessageDialog(null, "Selecione um campo na tabela", "Alerta", JOptionPane.WARNING_MESSAGE);

        } else {
            id = jTableItemVenda.getSelectedRow();

            try {
                //itemVenda = this.itensVenda.get(id);

                String codigoBarras = (String) jTableItemVenda.getValueAt(id, 0);

                listaOrcamento = itemOrcamentoDao.pesquisarItemOrcamento(this.orcamento.getOrc_id());

                for (ItemOrcamento i : listaOrcamento) {
                    i.getDescricao();
                    if (i.getDescricao().equals(itemOrcamento.getDescricao())) {
                        System.out.println("ITEM ORCAMENTO " + i.getId());
                        itemOrcamentoDao.excluir(i.getId());

                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NaoEncontradoException ex) {
                Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public void apagaCampos() {
        this.clienteModel = null;
        jTextFieldCodigoBarra.setText("");
        jTextFieldCpf.setText("");
        // jTextFieldDesconto.setText("");
        jTextFieldNomeCliente.setText("");
        jTextFieldQuantidade.setText("");
        // jTextFieldValorRecebido.setText("");
        jLabelQuantidadeEstoque.setText("");
        jLabelProduto.setText("");
        // jLabelTroco.setText("");
        jLabelValorProduto.setText("");
        atualizaTabela();
        this.itensVenda = null;

    }

    public void atualizaTabela() {

        DefaultTableModel val = (DefaultTableModel) jTableItemVenda.getModel();
        val.setNumRows(0);

        Locale localBrasil = new Locale("pt", "BR");
        
        if (jTableItemVenda != null || this.itensVenda != null) {

            this.itensVenda.forEach((itemVenda) -> {
                String quantidadeStrings;
                double quantidadeFinal = 0.0, quantidades = 0.0;

                double valorTotalDouble = itemVenda.getValor();
                quantidades = itemVenda.getQuantidade();

                if ("KG".equals(itemVenda.getUnidadeMedida())) {

                    String quantidadeString = String.valueOf(quantidades);
                    quantidadeString = quantidadeString.replace(".", "").replace(",", "");

                    NumberFormat formatter = new DecimalFormat("#0.000");
                    quantidadeStrings = formatter.format(quantidades).replace(".", ",");

                    valorTotalDouble = quantidades * valorTotalDouble;

                } else {
                    //SE FOR EM UNID
                    quantidadeStrings = "";
                    int quantidadeInt = (int) quantidades;
                    quantidadeStrings = String.valueOf(quantidadeInt);
                    this.quantidadeSB = new StringBuffer(quantidadeStrings);

                    valorTotalDouble = quantidades * valorTotalDouble;
                }

                this.valorTotalReferencia = this.valorTotalReferencia + valorTotalDouble;
                this.valorTotalPagar = this.valorTotalPagar + valorTotalDouble;

                String valorUnit = NumberFormat.getCurrencyInstance(localBrasil).format(itemVenda.getValor());
                String somaValor = NumberFormat.getCurrencyInstance(localBrasil).format(valorTotalDouble);
                
                //SE FOR EDICAO DE ORCAMENTO
if (jRadioButtonOrcamento.isSelected()) {

                    jTableItemVenda.getColumn("Código de Barras").setCellRenderer(new cellRendererOrcamento(this.itensVenda));
                    jTableItemVenda.getColumn("Produto").setCellRenderer(new cellRendererOrcamento(this.itensVenda));
                    jTableItemVenda.getColumn("Quantidade").setCellRenderer(new cellRendererOrcamento(this.itensVenda));
                    jTableItemVenda.getColumn("Unidade de Medida").setCellRenderer(new cellRendererOrcamento(this.itensVenda));
                    jTableItemVenda.getColumn("Valor do Produto").setCellRenderer(new cellRendererOrcamento(this.itensVenda));
                    jTableItemVenda.getColumn("Soma do Valor").setCellRenderer(new cellRendererOrcamento(this.itensVenda));

                }

                val.addRow(new Object[]{itemVenda.getEstoque().getProduto().getCodigoBarra(), itemVenda.getEstoque().getProduto().getNome(), quantidadeStrings, itemVenda.getUnidadeMedida(), valorUnit, somaValor});

            });

            String valorTotalString = NumberFormat.getCurrencyInstance(localBrasil).format(this.valorTotalPagar);
            jLabelTotal.setText(valorTotalString);
            jTextFieldQuantidade.setDocument(new FormatacaoDinamicaQuantidadeUnid());
            jTextFieldQuantidade.setEditable(false);
        }

        jTableItemVenda.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    System.err.println("Contador");
                    excluir();

                }
            }

        });

    }

    public void adicionaCliente(ClienteModel cliente) {
        this.clienteModel = cliente;
        jTextFieldNomeCliente.setText(cliente.getNome());
        jTextFieldCpf.setText(cliente.getCpf());

    }

    public void adicionarNaTabela() {
       
        String quantidadeString;
        double quantidade,quantidadeVerifica, quantidadeEstoque, resto;

        quantidadeEstoque = this.estoqueModel.getQtdEstoque();
        quantidadeString = jTextFieldQuantidade.getText();
        quantidadeString = quantidadeString.replace(",", "");
        quantidade = Double.parseDouble(quantidadeString);
        
        if ("KG".equals(estoqueModel.getProduto().getUnidadeMedida())) {
            quantidade = quantidade / 1000;
        }
        quantidadeVerifica = quantidade;
        if(jRadioButtonOrcamento.isSelected()){
            for(ItemVenda i: this.itensVenda){
               if(i.getEstoque().getProduto().getCodigoBarra().equals(this.estoqueModel.getProduto().getCodigoBarra())){
                   quantidadeVerifica = quantidade + i.getQuantidade();
               }
            }
        }
        
        if (quantidade != 0) {

            resto = estoqueModel.getQtdEstoque() - quantidade;
           
            if (quantidadeVerifica <= quantidadeEstoque) {
                
               // if(){
                
                try {
                    adicionarTabelaProduto(quantidade);
                    jTextFieldQuantidade.setText("");
                } catch (SQLException ex) {
                    Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ParseException ex) {
                    Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NaoEncontradoException ex) {
                    Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
                }
                jTextFieldCodigoBarra.requestFocus();
                //}else{
                //    JOptionPane.showMessageDialog(null, "A quantidade de produto no estoque é insuficiente", "Alerta", JOptionPane.WARNING_MESSAGE);
               // }
                
            } else {

                JOptionPane.showMessageDialog(null, "A quantidade de produto no estoque é insuficiente", "Alerta", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Informe a quantidade", "Alerta", JOptionPane.WARNING_MESSAGE);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jPanelTipo = new javax.swing.JPanel();
        jRadioButtonVenda = new javax.swing.JRadioButton();
        jRadioButtonOrcamento = new javax.swing.JRadioButton();
        jPanel16 = new javax.swing.JPanel();
        jLabelData = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabelFuncionario = new javax.swing.JLabel();
        jPanelAlterarOrcamento = new javax.swing.JPanel();
        jPanelFundoBotaoAlterar = new javax.swing.JPanel();
        jLabelPesquisar = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jTextFieldNomeCliente = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jTextFieldCpf = new javax.swing.JTextField();
        jButtonAdicionarCliente = new javax.swing.JButton();
        jButtonPesquisarCliente = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jTextFieldCodigoBarra = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jTextFieldQuantidade = new javax.swing.JTextField();
        jButtonAdicionarProduto = new javax.swing.JButton();
        jButtonRemoverProduto = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabelValorProduto = new javax.swing.JLabel();
        jButtonPesquisarProduto = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        jTextFieldUnidadeMedida = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabelProduto = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableItemVenda = new javax.swing.JTable();
        jButtonSair = new javax.swing.JButton();
        jButtonFinaliza = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jButtonCancelarAlteracao = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabelQuantidadeEstoque = new javax.swing.JLabel();
        jPanelFormaPagamento = new javax.swing.JPanel();
        jRadioButtonDinheiro = new javax.swing.JRadioButton();
        jRadioButtonCartao = new javax.swing.JRadioButton();
        jPanel19 = new javax.swing.JPanel();
        jLabelTotal = new javax.swing.JLabel();
        jPanel20 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Venda");
        setExtendedState(6);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(3, 133, 188));

        jLabelTitulo.setBackground(new java.awt.Color(255, 255, 255));
        jLabelTitulo.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Venda");

        jPanelTipo.setBackground(new java.awt.Color(3, 133, 188));
        jPanelTipo.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tipo:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        buttonGroup1.add(jRadioButtonVenda);
        jRadioButtonVenda.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButtonVenda.setText("Venda");
        jRadioButtonVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonVendaActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButtonOrcamento);
        jRadioButtonOrcamento.setForeground(new java.awt.Color(255, 255, 255));
        jRadioButtonOrcamento.setText("Orçamento");
        jRadioButtonOrcamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonOrcamentoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelTipoLayout = new javax.swing.GroupLayout(jPanelTipo);
        jPanelTipo.setLayout(jPanelTipoLayout);
        jPanelTipoLayout.setHorizontalGroup(
            jPanelTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTipoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jRadioButtonVenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonOrcamento)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelTipoLayout.setVerticalGroup(
            jPanelTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelTipoLayout.createSequentialGroup()
                .addGroup(jPanelTipoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonVenda)
                    .addComponent(jRadioButtonOrcamento))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(3, 133, 188));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Data:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabelData.setForeground(new java.awt.Color(255, 255, 255));
        jLabelData.setText("jLabel4");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelData, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelData)
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(3, 133, 188));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Funcionário:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabelFuncionario.setBackground(new java.awt.Color(255, 255, 255));
        jLabelFuncionario.setForeground(new java.awt.Color(255, 255, 255));
        jLabelFuncionario.setText("jLabel5");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelFuncionario)
                .addContainerGap())
        );

        jPanelAlterarOrcamento.setBackground(new java.awt.Color(3, 133, 188));
        jPanelAlterarOrcamento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Alterar Orçamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jPanelFundoBotaoAlterar.setBackground(new java.awt.Color(3, 100, 142));
        jPanelFundoBotaoAlterar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelFundoBotaoAlterar.setAutoscrolls(true);

        jLabelPesquisar.setBackground(new java.awt.Color(255, 255, 255));
        jLabelPesquisar.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabelPesquisar.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPesquisar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelPesquisar.setText("Pesquisar");
        jLabelPesquisar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelPesquisarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelPesquisarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelPesquisarMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanelFundoBotaoAlterarLayout = new javax.swing.GroupLayout(jPanelFundoBotaoAlterar);
        jPanelFundoBotaoAlterar.setLayout(jPanelFundoBotaoAlterarLayout);
        jPanelFundoBotaoAlterarLayout.setHorizontalGroup(
            jPanelFundoBotaoAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
        );
        jPanelFundoBotaoAlterarLayout.setVerticalGroup(
            jPanelFundoBotaoAlterarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelAlterarOrcamentoLayout = new javax.swing.GroupLayout(jPanelAlterarOrcamento);
        jPanelAlterarOrcamento.setLayout(jPanelAlterarOrcamentoLayout);
        jPanelAlterarOrcamentoLayout.setHorizontalGroup(
            jPanelAlterarOrcamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFundoBotaoAlterar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelAlterarOrcamentoLayout.setVerticalGroup(
            jPanelAlterarOrcamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelFundoBotaoAlterar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel10.setBackground(new java.awt.Color(3, 133, 188));

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelAlterarOrcamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanelTipo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
            .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelAlterarOrcamento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabelTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Cliente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Nome", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldNomeCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNomeClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldNomeCliente)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jTextFieldNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "CPF", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldCpf)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTextFieldCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButtonAdicionarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/adicionar20x20.png"))); // NOI18N
        jButtonAdicionarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarClienteActionPerformed(evt);
            }
        });

        jButtonPesquisarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lupa20x20.png"))); // NOI18N
        jButtonPesquisarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAdicionarCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPesquisarCliente)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonPesquisarCliente)
                            .addComponent(jButtonAdicionarCliente))
                        .addContainerGap())))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Codigo de Barra:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldCodigoBarra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCodigoBarraActionPerformed(evt);
            }
        });
        jTextFieldCodigoBarra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCodigoBarraKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldCodigoBarra)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldCodigoBarra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Quantidade:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldQuantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldQuantidadeActionPerformed(evt);
            }
        });
        jTextFieldQuantidade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldQuantidadeKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldQuantidade)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonAdicionarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/adicionar20x20.png"))); // NOI18N
        jButtonAdicionarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAdicionarProdutoActionPerformed(evt);
            }
        });

        jButtonRemoverProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/remover20x20.png"))); // NOI18N
        jButtonRemoverProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoverProdutoActionPerformed(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Valor do Produto:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jLabelValorProduto.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelValorProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelValorProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonPesquisarProduto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/lupa20x20.png"))); // NOI18N
        jButtonPesquisarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPesquisarProdutoActionPerformed(evt);
            }
        });

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Unidade de Medida:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP));

        jTextFieldUnidadeMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUnidadeMedidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldUnidadeMedida, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldUnidadeMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonPesquisarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAdicionarProduto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRemoverProduto))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButtonPesquisarProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAdicionarProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRemoverProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Descrição", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jLabelProduto.setFont(new java.awt.Font("Dialog", 1, 36)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabelProduto, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Tabela Itens de Venda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jTableItemVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código de Barras", "Produto", "Quantidade", "Unidade de Medida", "Valor do Produto", "Soma do Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableItemVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableItemVendaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableItemVenda);
        if (jTableItemVenda.getColumnModel().getColumnCount() > 0) {
            jTableItemVenda.getColumnModel().getColumn(0).setMinWidth(120);
            jTableItemVenda.getColumnModel().getColumn(0).setPreferredWidth(120);
            jTableItemVenda.getColumnModel().getColumn(0).setMaxWidth(120);
            jTableItemVenda.getColumnModel().getColumn(1).setMinWidth(200);
            jTableItemVenda.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTableItemVenda.getColumnModel().getColumn(2).setMinWidth(80);
            jTableItemVenda.getColumnModel().getColumn(2).setPreferredWidth(80);
            jTableItemVenda.getColumnModel().getColumn(2).setMaxWidth(80);
            jTableItemVenda.getColumnModel().getColumn(3).setMinWidth(120);
            jTableItemVenda.getColumnModel().getColumn(3).setPreferredWidth(120);
            jTableItemVenda.getColumnModel().getColumn(3).setMaxWidth(120);
            jTableItemVenda.getColumnModel().getColumn(4).setMinWidth(110);
            jTableItemVenda.getColumnModel().getColumn(4).setPreferredWidth(110);
            jTableItemVenda.getColumnModel().getColumn(4).setMaxWidth(110);
            jTableItemVenda.getColumnModel().getColumn(5).setMinWidth(100);
            jTableItemVenda.getColumnModel().getColumn(5).setPreferredWidth(100);
            jTableItemVenda.getColumnModel().getColumn(5).setMaxWidth(100);
        }

        jButtonSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/VoltarSimples24x24.png"))); // NOI18N
        jButtonSair.setText("[F10] Sair");
        jButtonSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSairActionPerformed(evt);
            }
        });

        jButtonFinaliza.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Concluir30x30.png"))); // NOI18N
        jButtonFinaliza.setText("[F8] Finalizar Venda");
        jButtonFinaliza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinalizaActionPerformed(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 53, Short.MAX_VALUE)
        );

        jButtonCancelarAlteracao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/voltar24x24.png"))); // NOI18N
        jButtonCancelarAlteracao.setText("Cancelar Alteração");
        jButtonCancelarAlteracao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarAlteracaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonCancelarAlteracao, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(386, 386, 386)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonFinaliza, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonFinaliza, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonSair, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonCancelarAlteracao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Estoque", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        jLabel4.setText("Quantidade Atual:");

        jLabelQuantidadeEstoque.setFont(new java.awt.Font("Dialog", 1, 30)); // NOI18N
        jLabelQuantidadeEstoque.setText("0");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelQuantidadeEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 29, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelQuantidadeEstoque, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))
        );

        jPanelFormaPagamento.setBackground(new java.awt.Color(255, 255, 255));
        jPanelFormaPagamento.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Forma Pagamento:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 14))); // NOI18N

        buttonGroup2.add(jRadioButtonDinheiro);
        jRadioButtonDinheiro.setText("Dinheiro");

        buttonGroup2.add(jRadioButtonCartao);
        jRadioButtonCartao.setText("Cartão");

        javax.swing.GroupLayout jPanelFormaPagamentoLayout = new javax.swing.GroupLayout(jPanelFormaPagamento);
        jPanelFormaPagamento.setLayout(jPanelFormaPagamentoLayout);
        jPanelFormaPagamentoLayout.setHorizontalGroup(
            jPanelFormaPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormaPagamentoLayout.createSequentialGroup()
                .addComponent(jRadioButtonDinheiro)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonCartao)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelFormaPagamentoLayout.setVerticalGroup(
            jPanelFormaPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFormaPagamentoLayout.createSequentialGroup()
                .addGroup(jPanelFormaPagamentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonDinheiro)
                    .addComponent(jRadioButtonCartao))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), "Total:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Dialog", 1, 24))); // NOI18N

        jLabelTotal.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabelTotal.setText("R$ 0,00");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelTotal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 292, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelFormaPagamento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanelFormaPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSairActionPerformed
        EstoqueModel estoqueModel = new EstoqueModel();
        EstoqueDAO estoqueDao = new EstoqueDAO();
        double quantidade = 0.0;
        String aviso;

        //1 verificar de onde esta vindo, para retornar para pagina certa
        // VEM DA PAGINA PRINCIPAL
        // PAGINA PRINCIPAL, PARAMETRO TELA VEM VAZIO
        if (tela.equals("")) {

            // VERIFICA SE É ORCAMENTO OU VENDA PARA AVISAR
            if (jTableItemVenda.getRowCount() > 0) {
                if (jLabelTitulo.getText().equals("Venda")) {
                    aviso = " uma venda ";
                } else {
                    aviso = " um orçamento ";
                }
                int b = JOptionPane.showConfirmDialog(null, "Exite" + aviso + "em andamento, deseja cancelar ?", "Alerta",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (b == 0) {
                    if (aviso.equals(" uma venda ")) {
                        ProdutoModel produto;
                        for (ItemVenda itensVenda : this.itensVenda) {
                            produto = new ProdutoModel();
                            produto = itensVenda.getEstoque().getProduto();
                            try {
                                estoqueModel = estoqueDao.pesquisaEstoqueCodigoBarras(produto.getCodigoBarra());

                                quantidade = estoqueModel.getQtdEstoque() + itensVenda.getQuantidade();

                                estoqueModel.setFuncionario(this.funcionario);
                                estoqueModel.setQtdEstoque(quantidade);
                                estoqueDao.alteraQuantidade(estoqueModel);

                            } catch (SQLException ex) {
                                Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (ParseException ex) {
                                Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (NaoEncontradoException ex) {
                                Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                        super.dispose();
                    }
                    super.dispose();
                }
            
        } else {
            super.dispose();
        }
            
        // PAGINA ORCAMENTO VEM COM PARAMETRO
    }

    
        else{
             // SE FOR PESQUISA SÓ DA DISPOSE, SE ALTERAR OU NOVO ORCAMENTO
             // AVISA QUE TEM UM ORCAMENTO E SE REALMENTE QUER SAIR
             if (!tela.equals("pesquisar orcamento")) {

            if(jTableItemVenda.getRowCount() > 0) {

                int b = JOptionPane.showConfirmDialog(null, "Exite um orçamento em andamento, deseja cancelar ?", "Alerta",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (b == 0) {
                    OrcamentoView orcamentoView = new OrcamentoView(null, true, funcionario, pg);
                    super.dispose();
                    orcamentoView.setVisible(true);
                }

            }else{
                OrcamentoView orcamentoView = new OrcamentoView(null, true, funcionario, pg);
                    super.dispose();
                    orcamentoView.setVisible(true);
                
            }
        } else {
            //PESQUISAR ORCAMENTO     
            OrcamentoView orcamentoView = new OrcamentoView(null, true, funcionario, pg);
            super.dispose();
            orcamentoView.setVisible(true);

        }
    }


    }//GEN-LAST:event_jButtonSairActionPerformed

    private void jRadioButtonVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonVendaActionPerformed
        alteraTitulo();
        jPanelAlterarOrcamento.setVisible(false);
        jPanelFormaPagamento.setVisible(true);
        novaVenda();
        limpaCamposVenda();
        
    }//GEN-LAST:event_jRadioButtonVendaActionPerformed

    private void jRadioButtonOrcamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonOrcamentoActionPerformed
        alteraTitulo();
        jPanelAlterarOrcamento.setVisible(true);
        jPanelFormaPagamento.setVisible(false);
        novaVenda();
        limpaCamposVenda();
    }//GEN-LAST:event_jRadioButtonOrcamentoActionPerformed

    private void jButtonAdicionarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarClienteActionPerformed

        try {
            ManterClienteView manterCliente = new ManterClienteView(null, true, "venda");
            manterCliente.setVisible(true);
        } catch (ParseException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(PrincipalGerenteView.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButtonAdicionarClienteActionPerformed

    private void jTextFieldNomeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNomeClienteActionPerformed

    }//GEN-LAST:event_jTextFieldNomeClienteActionPerformed

    private void jTextFieldCodigoBarraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodigoBarraActionPerformed
        EstoqueDAO estoqueDao = new EstoqueDAO();
        EstoqueModel produtoEstoque = new EstoqueModel();

        String codigoBarras = jTextFieldCodigoBarra.getText();
        System.out.println("codigo barras"+codigoBarras);
        try {
            produtoEstoque = estoqueDao.pesquisaEstoqueCodigoBarras(codigoBarras);
            this.estoqueModel = produtoEstoque;
            adicionarProduto(produtoEstoque);

        } catch (SQLException ex) {
            Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NaoEncontradoException ex) {
            Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jTextFieldCodigoBarraActionPerformed

    private void jButtonPesquisarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarClienteActionPerformed
        PesquisarClienteVendaView clienteVenda = new PesquisarClienteVendaView(null, true, this, null);
        clienteVenda.setVisible(true);
    }//GEN-LAST:event_jButtonPesquisarClienteActionPerformed

    private void jButtonPesquisarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPesquisarProdutoActionPerformed

        AdicionarProdutoVendaView produtoVenda = null;
        try {
            produtoVenda = new AdicionarProdutoVendaView(null, true, this);
        } catch (ParseException ex) {
            Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
        }
        produtoVenda.setVisible(true);

    }//GEN-LAST:event_jButtonPesquisarProdutoActionPerformed

    private void jButtonAdicionarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAdicionarProdutoActionPerformed

        adicionarNaTabela();

    }//GEN-LAST:event_jButtonAdicionarProdutoActionPerformed

    private void jButtonRemoverProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoverProdutoActionPerformed
        remover();
    }//GEN-LAST:event_jButtonRemoverProdutoActionPerformed

    private void jTextFieldQuantidadeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldQuantidadeKeyTyped
        //  if(){

        //  }
        int qtdCaracteres = 7;
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }

        if (jTextFieldQuantidade.getText().length() >= qtdCaracteres) {
            evt.consume();
        }

    }//GEN-LAST:event_jTextFieldQuantidadeKeyTyped

    private void jTextFieldCodigoBarraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCodigoBarraKeyTyped

        int qtdCaracteres = 20;

        if (jTextFieldCodigoBarra.getText().length() >= qtdCaracteres) {
            evt.consume();
        }

    }//GEN-LAST:event_jTextFieldCodigoBarraKeyTyped

    private void jButtonFinalizaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinalizaActionPerformed

        int tipo = 0;
        int formaPagamento = 0;

        if (clienteModel.getNome() != null) {

            if (jRadioButtonVenda.isSelected()) {
                tipo = 0;
            } else if (jRadioButtonOrcamento.isSelected()) {
                tipo = 1;
            }

            if (jRadioButtonDinheiro.isSelected()) {

                formaPagamento = 0;

            } else if (jRadioButtonCartao.isSelected()) {

                formaPagamento = 1;

            }
            String verifica = "verificado";
            if (!itensVenda.isEmpty()) {
                for(ItemVenda i: itensVenda){
                    if(i.getEstoque().getQtdEstoque() < i.getQuantidade()){
                        verifica = "nao";
                    }
                }
                if(verifica.equals("verificado")){
                FinalizarVenda finalizarVenda = new FinalizarVenda(null, true, this, this.clienteModel, this.itensVenda, this.orcamento, tipo, tela, formaPagamento, this.valorTotalPagar);
                finalizarVenda.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Não é possivel efetuar a venda, há produtos fora de estoque", "Alerta", JOptionPane.WARNING_MESSAGE); 
                }
            } else {
                JOptionPane.showMessageDialog(null, "Insira ao menos 1 produto para efetuar venda", "Alerta", JOptionPane.WARNING_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Informe o cliente para efetuar a venda", "Alerta", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jButtonFinalizaActionPerformed


    private void jTextFieldQuantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldQuantidadeActionPerformed
        adicionarNaTabela();
    }//GEN-LAST:event_jTextFieldQuantidadeActionPerformed

    private void jTextFieldUnidadeMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUnidadeMedidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUnidadeMedidaActionPerformed

    private void jLabelPesquisarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPesquisarMouseClicked
       if(jLabelPesquisar.isEnabled()){
        AlterarOrcamento alterarOrcamento = new AlterarOrcamento(null, true, this);
        alterarOrcamento.setVisible(true);
       }

    }//GEN-LAST:event_jLabelPesquisarMouseClicked

    private void jLabelPesquisarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPesquisarMouseEntered

        jPanelFundoBotaoAlterar.setBackground(new Color(9, 155, 217));


    }//GEN-LAST:event_jLabelPesquisarMouseEntered

    private void jLabelPesquisarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelPesquisarMouseExited

        jPanelFundoBotaoAlterar.setBackground(new Color(3, 100, 142));

    }//GEN-LAST:event_jLabelPesquisarMouseExited

    private void jTableItemVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableItemVendaMouseClicked
        int linha = -1;
        linha = jTableItemVenda.getSelectedRow();
        EstoqueModel estoque = new EstoqueModel();
        EstoqueDAO estoqueDao = new EstoqueDAO();

        if (linha != -1) {
            try {

                String codigo = (String) jTableItemVenda.getValueAt(linha, 0);
                estoque = estoqueDao.pesquisaEstoqueCodigoBarras(codigo);
                adicionarProduto(estoque);

            } catch (SQLException ex) {
                Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NaoEncontradoException ex) {
                Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_jTableItemVendaMouseClicked

    private void jButtonCancelarAlteracaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarAlteracaoActionPerformed
        jButtonCancelarAlteracao.setVisible(false);
        jLabelPesquisar.setEnabled(true);
        novaVenda();
        
    }//GEN-LAST:event_jButtonCancelarAlteracaoActionPerformed

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
            java.util.logging.Logger.getLogger(Venda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Venda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Venda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Venda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Venda("", null, null, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButtonAdicionarCliente;
    private javax.swing.JButton jButtonAdicionarProduto;
    private javax.swing.JButton jButtonCancelarAlteracao;
    private javax.swing.JButton jButtonFinaliza;
    private javax.swing.JButton jButtonPesquisarCliente;
    private javax.swing.JButton jButtonPesquisarProduto;
    private javax.swing.JButton jButtonRemoverProduto;
    private javax.swing.JButton jButtonSair;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelData;
    private javax.swing.JLabel jLabelFuncionario;
    private javax.swing.JLabel jLabelPesquisar;
    private javax.swing.JLabel jLabelProduto;
    private javax.swing.JLabel jLabelQuantidadeEstoque;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JLabel jLabelValorProduto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPanel jPanelAlterarOrcamento;
    private javax.swing.JPanel jPanelFormaPagamento;
    private javax.swing.JPanel jPanelFundoBotaoAlterar;
    private javax.swing.JPanel jPanelTipo;
    private javax.swing.JRadioButton jRadioButtonCartao;
    private javax.swing.JRadioButton jRadioButtonDinheiro;
    private javax.swing.JRadioButton jRadioButtonOrcamento;
    private javax.swing.JRadioButton jRadioButtonVenda;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableItemVenda;
    private javax.swing.JTextField jTextFieldCodigoBarra;
    private javax.swing.JTextField jTextFieldCpf;
    private javax.swing.JTextField jTextFieldNomeCliente;
    private javax.swing.JTextField jTextFieldQuantidade;
    private javax.swing.JTextField jTextFieldUnidadeMedida;
    // End of variables declaration//GEN-END:variables
}
