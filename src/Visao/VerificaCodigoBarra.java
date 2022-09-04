/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

/**
 *
 * @author pedro
 */
public class VerificaCodigoBarra {
    
    public boolean isValidBarCodeEAN(String barCode) {
        int digit;
        int calculated;
        String ean;
        String checkSum = "131313131313";
        int sum = 0;

        if (barCode.length() == 8 || barCode.length() == 13) {
            digit = Integer.parseInt("" + barCode.charAt(barCode.length() - 1));            
            ean = barCode.substring(0, barCode.length() - 1);            
            for (int i = 0; i <= ean.length() - 1; i++) {
                sum += (Integer.parseInt("" + ean.charAt(i))) * (Integer.parseInt("" + checkSum.charAt(i)));
            }            
            calculated = 10 - (sum % 10);            
            return (digit == calculated);
        } else {
            return false;
        }
    }
    
//    public boolean isCodigoBarrasValido(String codigo){
//  
//    int i = 0;
//    boolean valida;
//    String cody[] = new String[13];
//    double code[] = new double[13];
//    double total = 0;
//    for(i = 0;i < 12;i++)
//    {
//        
//         
//        codigo += cody[i];  
//         
//        code[i] = Integer.parseInt(cody[i]);
//
//
//            if(i%2== 0)
//            {
//                code[i] *= 3;                  
//            }          
//
//
//       total += code[i];
//    }   
//    
//    for(){
//        
//        
//    }
//
//    double t = total;
//    total /= 10;
//    int to = (int)total;
//
//    to += 1;
//    to *=10; 
//    to -= t;
//
//
//    if(to == code[13])
//    {
//        valida = true;            
//    }
//    else
//    {
//        valida = false;
//    }
//
//    System.out.println("Resultado: "+valida);
//        
//        return valida;
//    }
//    
}
