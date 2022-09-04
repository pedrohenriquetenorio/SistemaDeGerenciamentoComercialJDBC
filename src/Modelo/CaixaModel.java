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
public class CaixaModel {
   
   private int id;
   private Timestamp dataAbertura;
   private Timestamp dataFechamento;
   private Double valorInicial;
   private Double troco;
   private Double valorFinal;
   private Double valorFechamento;
   private String funcionarioAberturaCaixa;
   private String situacao;
   private boolean finaliza;
   private String justificativa;
   private FuncionarioModel funcionarioModel;
   
   //ArrayList<FuncionarioModel>listaDeFucionarios;

    public CaixaModel() {
    }

    public CaixaModel(int id, Timestamp dataAbertura, Timestamp dataFechamento, Double valorInicial, Double troco, Double valorFinal, Double valorFechamento, String funcionarioAberturaCaixa, String situacao, boolean finaliza, String justificativa, FuncionarioModel funcionarioModel) {
        this.id = id;
        this.dataAbertura = dataAbertura;
        this.dataFechamento = dataFechamento;
        this.valorInicial = valorInicial;
        this.troco = troco;
        this.valorFinal = valorFinal;
        this.valorFechamento = valorFechamento;
        this.funcionarioAberturaCaixa = funcionarioAberturaCaixa;
        this.situacao = situacao;
        this.finaliza = finaliza;
        this.justificativa = justificativa;
        this.funcionarioModel = funcionarioModel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(Timestamp dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public Timestamp getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(Timestamp dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(Double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public Double getTroco() {
        return troco;
    }

    public void setTroco(Double troco) {
        this.troco = troco;
    }

    public Double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(Double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Double getValorFechamento() {
        return valorFechamento;
    }

    public void setValorFechamento(Double valorFechamento) {
        this.valorFechamento = valorFechamento;
    }

    public String getFuncionarioAberturaCaixa() {
        return funcionarioAberturaCaixa;
    }

    public void setFuncionarioAberturaCaixa(String funcionarioAberturaCaixa) {
        this.funcionarioAberturaCaixa = funcionarioAberturaCaixa;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public boolean getFinaliza() {
        return finaliza;
    }

    public void setFinaliza(boolean finaliza) {
        this.finaliza = finaliza;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public FuncionarioModel getFuncionarioModel() {
        return funcionarioModel;
    }

    public void setFuncionarioModel(FuncionarioModel funcionarioModel) {
        this.funcionarioModel = funcionarioModel;
    }
   
   
   
    
    
    
}
