/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Visao;

import java.text.ParseException;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author pedro
 */
public class Mascara {
    
     String insereMascara(String mascara, String tipo) throws ParseException {
        if (mascara.equals("")) {

            return mascara;

        }
        switch (tipo) {

            case "cnpj": {
                MaskFormatter mf = new MaskFormatter("##.###.###/####-##");
                mf.setValueContainsLiteralCharacters(false);
                return mf.valueToString(mascara);

            }
            case "telefone": {
                MaskFormatter mf = new MaskFormatter("(##)####-####");
                mf.setValueContainsLiteralCharacters(false);
                return mf.valueToString(mascara);

            }
            case "celular": {
                MaskFormatter mf = new MaskFormatter("(##)#####-####");
                mf.setValueContainsLiteralCharacters(false);
                return mf.valueToString(mascara);

            }
            case "cpf": {
                MaskFormatter mf = new MaskFormatter("###.###.###-##");
                mf.setValueContainsLiteralCharacters(false);
                return mf.valueToString(mascara);

            }
            case "cep": {
                MaskFormatter mf = new MaskFormatter("#####-###");
                mf.setValueContainsLiteralCharacters(false);
                return mf.valueToString(mascara);
            }
            case "%":{
                MaskFormatter mf = new MaskFormatter("%");
                mf.setValueContainsLiteralCharacters(false);
                return mf.valueToString(mascara);
            }
            case "R$":{
                MaskFormatter mf = new MaskFormatter("R$");
                mf.setValueContainsLiteralCharacters(false);
                return mf.valueToString(mascara);
            }
            case "hora":{
                MaskFormatter mf = new MaskFormatter(":");
                mf.setValueContainsLiteralCharacters(false);
                return mf.valueToString(mascara);
            }
            default:
                break;
        }

        return mascara;

    }
    
    String limpaMascara(String mascara, String tipo) {

        switch (tipo) {
            case "cnpj":

                mascara = mascara.replace("-", "");
                mascara = mascara.replace(".", "");
                mascara = mascara.replace("/", "");
                mascara = mascara.replace(" ", "");

                break;
            case "telefone":

                mascara = mascara.replace("(", "");
                mascara = mascara.replace(")", "");
                mascara = mascara.replace("-", "");
                mascara = mascara.replace(" ", "");

                break;
            case "celular":

                mascara = mascara.replace("(", "");
                mascara = mascara.replace(")", "");
                mascara = mascara.replace("-", "");
                mascara = mascara.replace(" ", "");

                break;
            case "cpf":

                mascara = mascara.replace("-", "");
                mascara = mascara.replace(".", "");
                mascara = mascara.replace(" ", "");

                break;
            case "cep":

                mascara = mascara.replace("-", "");
                mascara = mascara.replace(" ", "");

                break;
            case "%":

                mascara = mascara.replace("%", "");
                mascara = mascara.replace(" ", "");

                break;
            case "R$":

                mascara = mascara.replace("R$", "");
                mascara = mascara.replace(" ", "");

                break;
            case "hora":

                mascara = mascara.replace(":", "");
                mascara = mascara.replace(" ", "");

                break;    
            case "valor":

                mascara = mascara.replace(".", "");
                mascara = mascara.replace(",", "");
                mascara = mascara.replace(" ", "");
                mascara = mascara.replace("R$", "");
                mascara = mascara.trim();

                break;     
            case "valorZero":

                mascara = mascara.replace(".", "");
                mascara = mascara.replace(",", "");
                mascara = mascara.replace(" ", "");
                mascara = mascara.replace("R$", "");
                mascara = mascara.replace("0", "");
                mascara = mascara.trim();

            break;   
            default:
                break;
        }

        return mascara;

    }
    
}
