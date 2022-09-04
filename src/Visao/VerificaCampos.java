/*
    Valida o CPF e o CNPJ
 */
package Visao;

import java.util.InputMismatchException;

public class VerificaCampos {
    
     public boolean verificaCpf(String cpf){
            int soma, i, r, num, peso;
            char primeiroDigito, segundoDigito;
        
            if (cpf.equals("00000000000") ||
            cpf.equals("11111111111") ||
            cpf.equals("22222222222") || cpf.equals("33333333333") ||
            cpf.equals("44444444444") || cpf.equals("55555555555") ||
            cpf.equals("66666666666") || cpf.equals("77777777777") ||
            cpf.equals("88888888888") || cpf.equals("99999999999") ||
            (cpf.length() != 11))
            return(false);
           
            try {
                 
            // PRIMEIRO CARACTERE      
            soma = 0;
            peso = 10;
            
            //Este For Serve para pegar os valores digitados e converter em inteiro
            for (i=0; i<9; i++) {
            num = (int)(cpf.charAt(i) - 48);
            soma = soma + (num * peso);
            peso = peso - 1;
            }
            
             r = 11 - (soma % 11);
            if ((r == 10) || (r == 11))
                primeiroDigito = '0';
            else primeiroDigito = (char)(r + 48);
            
            soma = 0;
            peso = 11;
            for(i=0; i<10; i++) {
            num = (int)(cpf.charAt(i) - 48);
            soma = soma + (num * peso);
            peso = peso - 1;
            }

            r = 11 - (soma % 11);
            if ((r == 10) || (r == 11))
                 segundoDigito = '0';
            else segundoDigito = (char)(r + 48);
            
            if ((primeiroDigito == cpf.charAt(9)) && (segundoDigito == cpf.charAt(10)))
                 return(true);
            else return(false);
            
             } catch (InputMismatchException erro) {
                return(false);
            }
    
    }
    
public boolean verificaCNPJ(String cnpj){
             
// SE OS NUMERO FOREM SEQUENCIAIS DÃO ERRO
    if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111") ||
        cnpj.equals("22222222222222") || cnpj.equals("33333333333333") ||
        cnpj.equals("44444444444444") || cnpj.equals("55555555555555") ||
        cnpj.equals("66666666666666") || cnpj.equals("77777777777777") ||
        cnpj.equals("88888888888888") || cnpj.equals("99999999999999") ||
       (cnpj.length() != 14))
       return(false);

    char dig13, dig14;
    int sm, i, r, num, peso;

// "try" - protege o código para eventuais erros de conversao de tipo (int)
    try {
// Calculo do 1o. Digito Verificador
      sm = 0;
      peso = 2;
      for (i=11; i>=0; i--) {
// converte o i-ésimo caractere do CNPJ em um número:
// por exemplo, transforma o caractere '0' no inteiro 0
// (48 eh a posição de '0' na tabela ASCII)
        num = (int)(cnpj.charAt(i) - 48);
        sm = sm + (num * peso);
        peso = peso + 1;
        if (peso == 10)
           peso = 2;
      }

      r = sm % 11;
      if ((r == 0) || (r == 1))
         dig13 = '0';
      else dig13 = (char)((11-r) + 48);

// Calculo do 2o. Digito Verificador
      sm = 0;
      peso = 2;
      for (i=12; i>=0; i--) {
        num = (int)(cnpj.charAt(i)- 48);
        sm = sm + (num * peso);
        peso = peso + 1;
        if (peso == 10)
           peso = 2;
      }

      r = sm % 11;
      if ((r == 0) || (r == 1))
         dig14 = '0';
      else dig14 = (char)((11-r) + 48);

// Verifica se os dígitos calculados conferem com os dígitos informados.
      if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13)))
         return(true);
      else return(false);
    } catch (InputMismatchException erro) {
        return(false);
    }
  }

}
