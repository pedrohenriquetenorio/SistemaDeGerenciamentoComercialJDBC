/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author pedro
 */
public class TipoPagamentoModel {
   
    private int id;
    private String formaPagamento;
    private double valor;
    private VendaModel venda;
    
    public TipoPagamentoModel() {
    }
    
    public TipoPagamentoModel(int id, String formaPagamento, double valor, VendaModel venda) {
        this.id = id;
        this.formaPagamento = formaPagamento;
        this.valor = valor;
        this.venda = venda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public VendaModel getVenda() {
        return venda;
    }

    public void setVenda(VendaModel venda) {
        this.venda = venda;
    }
    
    
    
}
