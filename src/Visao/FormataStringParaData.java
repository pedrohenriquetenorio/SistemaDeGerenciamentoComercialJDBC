/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author pedro
 */
public class FormataStringParaData {
    
    public String formataStringSeparadoToTimestamp(String data, String hora){
    
        String horas, min, ano, mes, dia,horaSemMascara[] = hora.split(":"), dataSemMascara[] = data.split("/");
        String horaCompleta;
        
        ano = dataSemMascara[2];
        mes = dataSemMascara[1];
        dia = dataSemMascara[0];
        
        horas = horaSemMascara[0];
        min = horaSemMascara[1];
  
        horaCompleta = horas.concat(":"+min);
        String dataCompleta = ano.concat("-"+mes.concat("-"+dia));
        String dataHoraCompleta = dataCompleta.concat(" "+horaCompleta);
   
        //Timestamp ts = Timestamp.valueOf(dataHoraCompleta);
        return dataHoraCompleta;
    }
    
    public String retornaMesAnoString(String data){
 
    String ano, mes, dia, dataSemMascara[] = data.split("/");
        ano = dataSemMascara[2];
        mes = dataSemMascara[1];
        dia = dataSemMascara[0];    
    String dataCompletas = mes.concat("/"+ano);    
    return dataCompletas;
    
    }
    public String retornaAnoString(String data){
 
    String ano, mes, dia, dataSemMascara[] = data.split("/");
        ano = dataSemMascara[2];
        mes = dataSemMascara[1];
        dia = dataSemMascara[0];    
    String dataCompletas = ano;    
    return dataCompletas;
    
    }
    
    public String retornaMesString(String data){
 
    String ano, mes, dia, dataSemMascara[] = data.split("/");
        ano = dataSemMascara[2];
        mes = dataSemMascara[1];
        dia = dataSemMascara[0];    
    String dataCompletas = mes;    
    return dataCompletas;
    
    }
    
    public Date formataDataStringToDate(String data){
        
    String ano, mes, dia, dataSemMascara[] = data.split("/");
        ano = dataSemMascara[2];
        mes = dataSemMascara[1];
        dia = dataSemMascara[0];    
        
    String dataCompletas = ano.concat("-"+mes.concat("-"+dia));    
    Date dataCompleta = Date.valueOf(dataCompletas);
    
    return dataCompleta;
    }   
    
    public Timestamp formatarDataHoraStringToDate(String data, String hora) throws ParseException{
        
        String ano, mes, dia, dataSemMascara[] = data.split("/");
        ano = dataSemMascara[2];
        mes = dataSemMascara[1];
        dia = dataSemMascara[0];
        
        String dataCompleta = ano.concat("-"+mes.concat("-"+dia));
        String dataHoraCompleta = dataCompleta.concat(" "+hora);
        
        Timestamp ts = Timestamp.valueOf(dataHoraCompleta);
 
        
        return ts;
    }
    
     public String formatarDataHoraStringToDateString(String data, String hora) throws ParseException{
        int horaInt;
        String horas, min, seg, ano, mes, dia,horaSemMascara[] = hora.split(":"), dataSemMascara[] = data.split("/");
        String horaCompleta;
        
        ano = dataSemMascara[2];
        mes = dataSemMascara[1];
        dia = dataSemMascara[0];
        
       horas = horaSemMascara[0];
       min = horaSemMascara[1];
       seg = horaSemMascara[2];
       
       horaInt = Integer.parseInt(horas);
  
        horaCompleta = horas.concat(":"+min.concat(":"+seg));
        String dataCompleta = ano.concat("-"+mes.concat("-"+dia));
        String dataHoraCompleta = dataCompleta.concat(" "+horaCompleta);
   
        return dataHoraCompleta;
    }
     
   
     public Date formatarDataStringToDate(String data) throws ParseException{
     
        String ano, mes, dia, dataSemMascara[] = data.split("/");
        
        ano = dataSemMascara[2];
        mes = dataSemMascara[1];
        dia = dataSemMascara[0];
       
        String dataCompleta = ano.concat("-"+mes.concat("-"+dia));
        
        Date ts = Date.valueOf(dataCompleta);
        
        return ts;
    }
    
     public String formatarDataStringToSQLString(String data) throws ParseException{
     
        String ano, mes, dia, dataSemMascara[] = data.split("/");
        
        ano = dataSemMascara[2];
        mes = dataSemMascara[1];
        dia = dataSemMascara[0];
       
        String dataCompleta = ano.concat("-"+mes.concat("-"+dia));
        
        return dataCompleta;
    }
     
}
