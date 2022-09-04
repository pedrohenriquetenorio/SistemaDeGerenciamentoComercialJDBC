/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control.Exceptions;

/**
 *
 * @author pedro
 */
public class CnpjInvalidoException extends Exception {

    /**
     * Creates a new instance of <code>CnpjInvalidoException</code> without
     * detail message.
     */
    public CnpjInvalidoException() {
    }

    /**
     * Constructs an instance of <code>CnpjInvalidoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CnpjInvalidoException(String msg) {
        super(msg);
    }
}
