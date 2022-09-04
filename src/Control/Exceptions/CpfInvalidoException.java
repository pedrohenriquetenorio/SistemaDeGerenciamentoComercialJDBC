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
public class CpfInvalidoException extends Exception {

    /**
     * Creates a new instance of <code>CpfInvalidoException</code> without
     * detail message.
     */
    public CpfInvalidoException() {
    }

    /**
     * Constructs an instance of <code>CpfInvalidoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public CpfInvalidoException(String msg) {
        super(msg);
    }
}
