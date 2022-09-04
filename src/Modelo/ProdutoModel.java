/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author pedro
 */
public class ProdutoModel {
    private int id;
    private String nome;
    private Double valor;
    private int codigoBalanca;
    private String codigoBarra;
    private String unidadeMedida;
    private Date dataCadastro;
    private boolean status;
    public ProdutoModel() {
    }

    public ProdutoModel(int id, String nome, Double valor, int codigoBalanca, String codigoBarra, String unidadeMedida, Date dataCadastro, boolean status) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.codigoBalanca = codigoBalanca;
        this.codigoBarra = codigoBarra;
        this.unidadeMedida = unidadeMedida;
        this.dataCadastro = dataCadastro;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int getCodigoBalanca() {
        return codigoBalanca;
    }

    public void setCodigoBalanca(int codigoBalanca) {
        this.codigoBalanca = codigoBalanca;
    }

    public String getCodigoBarra() {
        return codigoBarra;
    }

    public void setCodigoBarra(String codigoBarra) {
        this.codigoBarra = codigoBarra;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
