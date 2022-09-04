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
public class NaoEncontradoException extends Exception {

    /**
     * Creates a new instance of <code>NaoEncontradoException</code> without
     * detail message.
     */
    public NaoEncontradoException() {
    }

    /**
     * Constructs an instance of <code>NaoEncontradoException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NaoEncontradoException(String msg) {
        
        super(msg);
    }
}
