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
public class EntradaModel {
    
    
    private int id;
    private Timestamp data;
    private String descricao;
    private Double valor;
    private FuncionarioModel funcionario;
    private CaixaModel caixa;

    public EntradaModel() {
    }

    public EntradaModel(int id, Timestamp data, String descricao, Double valor, FuncionarioModel funcionario, CaixaModel caixa) {
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.funcionario = funcionario;
        this.caixa = caixa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public FuncionarioModel getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioModel funcionario) {
        this.funcionario = funcionario;
    }

    public CaixaModel getCaixa() {
        return caixa;
    }

    public void setCaixa(CaixaModel caixa) {
        this.caixa = caixa;
    }
    
    
    
    
}
