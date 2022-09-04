/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */

public class CompraModel {
    
    private int id;
    private Date dataCompra;
    private Time horaCompra;
    private Double Valor;
    private String status;
    FornecedorModel fornecedor;
    FuncionarioModel funcionario;

    public CompraModel() {
    }

    public CompraModel(int id, Date dataCompra, Time horaCompra, Double Valor, String status, FornecedorModel fornecedor, FuncionarioModel funcionario) {
        this.id = id;
        this.dataCompra = dataCompra;
        this.horaCompra = horaCompra;
        this.Valor = Valor;
        this.status = status;
        this.fornecedor = fornecedor;
        this.funcionario = funcionario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Time getHoraCompra() {
        return horaCompra;
    }

    public void setHoraCompra(Time horaCompra) {
        this.horaCompra = horaCompra;
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double Valor) {
        this.Valor = Valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FornecedorModel getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(FornecedorModel fornecedor) {
        this.fornecedor = fornecedor;
    }

    public FuncionarioModel getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioModel funcionario) {
        this.funcionario = funcionario;
    }

    
    
   
    
}
