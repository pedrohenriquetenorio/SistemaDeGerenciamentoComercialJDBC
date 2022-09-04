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
public class ContaReceberModel {
    private int id;
    private String descricao;
    private Date vencimento;
    private Timestamp dataCompra;
    private Double valor;
    private String situacao;
    private ClienteModel cliente;
    

    public ContaReceberModel() {
    }

    public ContaReceberModel(int id, String descricao, Date vencimento, Timestamp dataCompra, Double valor, String situacao, ClienteModel cliente) {
        this.id = id;
        this.descricao = descricao;
        this.vencimento = vencimento;
        this.dataCompra = dataCompra;
        this.valor = valor;
        this.situacao = situacao;
        this.cliente = cliente;
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

    public Timestamp getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Timestamp dataCompra) {
        this.dataCompra = dataCompra;
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

    public ClienteModel getCliente() {
        return cliente;
    }

    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }
    
    
}