
package Visao;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author pedro
 */
public class FormatacaoDinamicaQuantidade extends PlainDocument {
    
            public static final int NUMERO_DIGITOS_MAXIMO = 12;

            public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
           //PEGA O QUE ESTA SENDO DIGITADO NO JTEXTFIELD
            String texto = getText(0, getLength());
               
            for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!Character.isDigit(c)) {
            return;
            }
            }

            if(texto.length() < this.NUMERO_DIGITOS_MAXIMO){
            super.remove(0, getLength());
            
            //REPLACE NA STRING INTEIRA
            texto = texto.replace(".", "").replace(",", "");
            // JUNTAR a string de tras pra frente
            StringBuffer s = new StringBuffer(texto + str);
               
            if (s.length() > 0 && s.charAt(0) == '0') {
            s.deleteCharAt(0);
            }

            if(s.length() < 4) {
                
                if (s.length() < 2) {

                s.insert(0,"000");
                
                }else if (s.length() < 3) {
                    
                s.insert(0,"00");
                
                }else{
                    
                s.insert(0,"0");
                
                }
            }

            s.insert(s.length()-3, ",");

            if(s.length() > 7) {
            s.insert(s.length()-7, ".");
            }

            if(s.length() > 11) {
            s.insert(s.length()-11, ".");
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
