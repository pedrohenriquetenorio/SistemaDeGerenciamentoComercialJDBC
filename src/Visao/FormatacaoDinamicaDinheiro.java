/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import java.text.DecimalFormat;
import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author pedro
 */
public class FormatacaoDinamicaDinheiro extends PlainDocument{
    
            public static final int NUMERO_DIGITOS_MAXIMO = 15;
            StringBuffer ss;
            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        
            String texto = getText(0, getLength());
            
            
            for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c)) {
            return;
            }
            }

            if(texto.length() < this.NUMERO_DIGITOS_MAXIMO){
            super.remove(0, getLength());
            texto = texto.replace(".", "").replace(",", "");
               
            StringBuffer s = new StringBuffer(texto + str);
             
            if (s.length() > 0 && s.charAt(0) == '0') {
                s.deleteCharAt(0);
            }
            
            if(s.length() < 3) {
                if (s.length() < 1) {
                
                    s.insert(0,"000");
                
                }else if (s.length() < 2) {
                
                    s.insert(0,"00");
                }else{
                
                    s.insert(0,"0");
                } 
            }
            
            
            
            s.insert(s.length()-2, ",");

            if(s.length() > 6) {
            s.insert(s.length()-6, ".");
            }

            if(s.length() > 10) {
            s.insert(s.length()-10, ".");
            }
            
            if(s.length() >= 8 && s.substring(0, 3).equals("00.")){
                
         
                 s.replace(0, 3, s.substring(s.length()));
                  
                
            }    
               
            if(s.length() >= 10 && s.substring(0, 2).equals("00")){
                
         
                 s.replace(0, 2, s.substring(s.length()));
                  
                 
            }    
            
            if(s.length() >= 8 && s.substring(0, 3).equals("0.0")){
                
         
                 s.replace(0, 3, s.substring(s.length()));
                  
                
            } 
               
            if(s.length() >= 6 && s.substring(0, 2).equals("00")){
                
         
                 s.replace(0, 2, s.substring(s.length()));
                  
                
            }     
                super.insertString(0, s.toString(), a);
               
            }
            
               
            }
            
            @Override
            public void remove(int offset, int length) throws BadLocationException {
            super.remove(offset, length);
            String texto = getText(0, getLength());
            texto = texto.replace(",", "");
            texto = texto.replace(".", "");
            super.remove(0, getLength());
            insertString(0, texto, null);
            }
}


