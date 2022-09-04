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
public class ContaPagarModel {
   private int id;
   private String descricao;
   private Date vencimento;
   private Date data; // data atual
   private Timestamp dataCadastro; 
   private Double valor;
   private String situacao;
   private String categoria;
   
   private FornecedorModel fornecedor;

    public ContaPagarModel() {
    }

    public ContaPagarModel(int id, String descricao, Date vencimento, Date data, Timestamp dataCadastro, Double valor, String situacao, String categoria, FornecedorModel fornecedor) {
        this.id = id;
        this.descricao = descricao;
        this.vencimento = vencimento;
        this.data = data;
        this.dataCadastro = dataCadastro;
        this.valor = valor;
        this.situacao = situacao;
        this.categoria = categoria;
        this.fornecedor = fornecedor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Timestamp getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Timestamp dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public FornecedorModel getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(FornecedorModel fornecedor) {
        this.fornecedor = fornecedor;
    }
    
}
