/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class RetiradaModel {
    private int id;
    private Double valor;
    private Timestamp data;
    private String descricao;
    private FuncionarioModel funcionario;
    private CaixaModel caixa;

    public RetiradaModel() {
    }

    public RetiradaModel(int id, Double valor, Timestamp data, String descricao, FuncionarioModel funcionario, CaixaModel caixa) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.descricao = descricao;
        this.funcionario = funcionario;
        this.caixa = caixa;
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
