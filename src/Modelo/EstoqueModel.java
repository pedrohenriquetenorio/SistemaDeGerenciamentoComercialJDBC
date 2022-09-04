/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class EstoqueModel {
    
    private int id;
    private double qtdEstoque;
    private double qtdMinima;
    ProdutoModel produto;
    FuncionarioModel funcionario;
    private boolean status;

    public EstoqueModel() {
    }
    
    public EstoqueModel(int id, double qtdEstoque, double qtdMinima, ProdutoModel produto, FuncionarioModel funcionario, boolean status) {
        this.id = id;
        this.qtdEstoque = qtdEstoque;
        this.qtdMinima = qtdMinima;
        this.produto = produto;
        this.funcionario = funcionario;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getQtdEstoque() {
        return qtdEstoque;
    }

    public void setQtdEstoque(double qtdEstoque) {
        this.qtdEstoque = qtdEstoque;
    }

    public double getQtdMinima() {
        return qtdMinima;
    }

    public void setQtdMinima(double qtdMinima) {
        this.qtdMinima = qtdMinima;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public void setProduto(ProdutoModel produto) {
        this.produto = produto;
    }

    public FuncionarioModel getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioModel funcionario) {
        this.funcionario = funcionario;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
}
