/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class ItemCompra {
    
    private int id;
    private String descricao;
    private double valorUnitario;
    private double quantidade;
    private String unidadeMedida;
    private Timestamp dataHora;
    private boolean status;
    private String notaFiscal;
    private double quantidadeVerificacao;
    
    CompraModel compra;
    ProdutoModel produto;

    public ItemCompra() {
    }

    public ItemCompra(int id, String descricao, double valorUnitario, double quantidade, String unidadeMedida, Timestamp dataHora, boolean status, String notaFiscal, double quantidadeVerificacao, CompraModel compra, ProdutoModel produto) {
        this.id = id;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
        this.unidadeMedida = unidadeMedida;
        this.dataHora = dataHora;
        this.status = status;
        this.notaFiscal = notaFiscal;
        this.quantidadeVerificacao = quantidadeVerificacao;
        this.compra = compra;
        this.produto = produto;
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

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public Timestamp getDataHora() {
        return dataHora;
    }

    public void setDataHora(Timestamp dataHora) {
        this.dataHora = dataHora;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNotaFiscal() {
        return notaFiscal;
    }

    public void setNotaFiscal(String notaFiscal) {
        this.notaFiscal = notaFiscal;
    }

    public double getQuantidadeVerificacao() {
        return quantidadeVerificacao;
    }

    public void setQuantidadeVerificacao(double quantidadeVerificacao) {
        this.quantidadeVerificacao = quantidadeVerificacao;
    }

    public CompraModel getCompra() {
        return compra;
    }

    public void setCompra(CompraModel compra) {
        this.compra = compra;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public void setProduto(ProdutoModel produto) {
        this.produto = produto;
    }
    
    
}
