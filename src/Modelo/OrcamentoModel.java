/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Timestamp;

/**
 *
 * @author pedro
 */
public class OrcamentoModel {
       
    private int orc_id;
    private Double valorTotal;
    private Timestamp data;
    private Timestamp dataAprovacao;
    private Timestamp dataVencimento;
    private String formaPagamento;
    private Double desconto;
    FuncionarioModel funcionario;
    ClienteModel cliente;

    public OrcamentoModel() {
    }

    public OrcamentoModel(int orc_id, Double valorTotal, Timestamp data, Timestamp dataAprovacao, Timestamp dataVencimento, String formaPagamento, Double desconto, FuncionarioModel funcionario, ClienteModel cliente) {
        this.orc_id = orc_id;
        this.valorTotal = valorTotal;
        this.data = data;
        this.dataAprovacao = dataAprovacao;
        this.dataVencimento = dataVencimento;
        this.formaPagamento = formaPagamento;
        this.desconto = desconto;
        this.funcionario = funcionario;
        this.cliente = cliente;
    }

    public int getOrc_id() {
        return orc_id;
    }

    public void setOrc_id(int orc_id) {
        this.orc_id = orc_id;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public Timestamp getDataAprovacao() {
        return dataAprovacao;
    }

    public void setDataAprovacao(Timestamp dataAprovacao) {
        this.dataAprovacao = dataAprovacao;
    }

    public Timestamp getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Timestamp dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public FuncionarioModel getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioModel funcionario) {
        this.funcionario = funcionario;
    }

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    
    
}
