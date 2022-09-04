

package Visao;

import Modelo.CaixaModel;
import Modelo.CompraModel;
import Modelo.ContaPagarModel;
import Modelo.VendaModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraficoFinanceiro {
    public GraficoFinanceiro(){
     
    }
    public CategoryDataset createDataset(int quantidadeBaixa, int quantidadeMedia, int quantidadeAlta){

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(quantidadeBaixa,"Baixo Estoque","");

        dataset.addValue(quantidadeMedia,"Médio Estoque","");

        dataset.addValue(quantidadeAlta,"Alto Estoque","");
       
    return dataset;
    
    }
    //MESES
     public CategoryDataset createDatasetDinamicoMesesDoAno(ArrayList<CompraModel>listaCompra,ArrayList<VendaModel>listaVenda, 
             ArrayList<ContaPagarModel>listaConta,ArrayList<CaixaModel>listaCaixa){
         FormataStringParaData formata = new FormataStringParaData();
        Double valor, total = 0.0, vTotal = 0.0;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //VERIFICA OS IGUAIS
        ArrayList<CaixaModel>listaCaixa2 = new ArrayList<>();
        ArrayList<ContaPagarModel>listaConta2 = new ArrayList<>();
        ArrayList<CompraModel>listaCompra2  = new ArrayList<>();
        ArrayList<VendaModel>listaVenda2 = new ArrayList<>();
        
        
        CaixaModel caixa = new CaixaModel();
        if(!listaCaixa.isEmpty()){
            for(CaixaModel caixas: listaCaixa){
                caixa = caixas;
                if(!listaCaixa2.contains(caixa)){
                   for(CaixaModel caixa2: listaCaixa){
                        String dataCaixa = new SimpleDateFormat("dd/MM/yyyy").format(caixa.getDataFechamento());
                        dataCaixa = formata.retornaMesString(dataCaixa);
                        String dataCaixa2= new SimpleDateFormat("dd/MM/yyyy").format(caixa2.getDataFechamento());
                        dataCaixa2 = formata.retornaMesString(dataCaixa2);
                        if(dataCaixa.equals(dataCaixa2)){
                           vTotal = vTotal + caixa2.getValorFinal();
                            caixa.setValorFinal(vTotal);
                            listaCaixa2.add(caixa);
                            
                        }
                   } 
                }
            }
        }
        
        else if(!listaConta.isEmpty()){
                for(ContaPagarModel contas: listaConta){

                }
        }
        else if(!listaCompra.isEmpty()){
                for(CompraModel compras: listaCompra){

                }
        }
        else if(!listaVenda.isEmpty()){
                for(VendaModel vendas: listaVenda){

                }
        }
        
        if(!listaCompra.isEmpty()){
            
            for(CompraModel compras: listaCompra){
                
            //VERIFICA E SOMA OS VALORES DO MES 
            String dataCompra = new SimpleDateFormat("dd/MM/yyyy").format(compras.getDataCompra());
            dataCompra = formata.retornaMesString(dataCompra);
            
//                if(dataCompra.equals()){
//                    
//                }   
            }
            
        }
        else if(!listaVenda.isEmpty()){
            
            for(VendaModel vendas: listaVenda){
            //VERIFICA E SOMA OS VALORES DO MES 
            
            }
            
        }
        else if(!listaConta.isEmpty()){
            
            for(ContaPagarModel contas: listaConta){
            //VERIFICA E SOMA OS VALORES DO MES 
            
            }
            
        }
        else if(!listaCaixa2.isEmpty()){
            
            for(CaixaModel caixas: listaCaixa2){
            //VERIFICA E SOMA OS VALORES DO MES 
                dataset.addValue(caixas.getValorFinal(),"",caixas.getDataFechamento()+"");
            }
            
        }
        
    return dataset;
}
    
    
    
    //DIAS 
    //COMPRA
    public CategoryDataset createDatasetDinamicoCompra(List<CompraModel>listaCompra){
        
        Double valor, total = 0.0;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for(CompraModel compras: listaCompra){
          
            String dataCompra = new SimpleDateFormat("dd/MM/yyyy").format(compras.getDataCompra());
            dataset.addValue(compras.getValor(),"",dataCompra);
             
        }

    return dataset;
}
    //VENDA
    public CategoryDataset createDatasetDinamicoVenda(List<VendaModel>listaVenda){
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        for(VendaModel venda: listaVenda){
            
            String dataVenda = new SimpleDateFormat("dd/MM/yyyy").format(venda.getDataVenda());
            dataset.addValue(venda.getValor(),"",dataVenda);
            
        }
        
    return dataset;
}   
    //CONTA A PAGAR
    public CategoryDataset createDatasetDinamicoDespesa(List<ContaPagarModel>listaConta){
        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(ContaPagarModel conta: listaConta){
            String dataConta = new SimpleDateFormat("dd/MM/yyyy").format(conta.getData());
            dataset.addValue(conta.getValor(),"",dataConta);
            
        }
    return dataset;
}
    //CAIXA
    public CategoryDataset createDatasetDinamicoEntrada(List<CaixaModel>listaCaixa){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for(CaixaModel caixa: listaCaixa){
            System.out.println("DATA "+ caixa.getDataFechamento());
            String dataCaixa = new SimpleDateFormat("dd/MM/yyyy").format(caixa.getDataFechamento());
            dataset.addValue(caixa.getValorFinal(),"",dataCaixa);
            
        }
        
    return dataset;
}
 
}

