/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author pedro
 */
public class VendaModel {
    
    private int id;
    private Double valor;
    private Double desconto;
    private String formaPagamento;
    private Date dataVenda;
    private Time horaVenda;
    private FuncionarioModel funcionario;
    private ClienteModel clienteModel;
    private CaixaModel caixaModel;

    public VendaModel() {
    }

    public VendaModel(int id, Double valor, Double desconto, String formaPagamento, Date dataVenda, Time horaVenda, FuncionarioModel funcionario, ClienteModel clienteModel, CaixaModel caixaModel) {
        this.id = id;
        this.valor = valor;
        this.desconto = desconto;
        this.formaPagamento = formaPagamento;
        this.dataVenda = dataVenda;
        this.horaVenda = horaVenda;
        this.funcionario = funcionario;
        this.clienteModel = clienteModel;
        this.caixaModel = caixaModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Time getHoraVenda() {
        return horaVenda;
    }

    public void setHoraVenda(Time horaVenda) {
        this.horaVenda = horaVenda;
    }

    public FuncionarioModel getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioModel funcionario) {
        this.funcionario = funcionario;
    }

    public ClienteModel getClienteModel() {
        return clienteModel;
    }

    public void setClienteModel(ClienteModel clienteModel) {
        this.clienteModel = clienteModel;
    }

    public CaixaModel getCaixaModel() {
        return caixaModel;
    }

    public void setCaixaModel(CaixaModel caixaModel) {
        this.caixaModel = caixaModel;
    }
    
    
}
