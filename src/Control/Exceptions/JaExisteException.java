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
public class JaExisteException extends Exception {

    /**
     * Creates a new instance of <code>JaExisteException</code> without detail
     * message.
     */
    public JaExisteException() {
    }

    /**
     * Constructs an instance of <code>JaExisteException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public JaExisteException(String msg) {
        super(msg);
    }
}
