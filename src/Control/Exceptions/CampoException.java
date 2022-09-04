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
public class CampoException extends Exception {

    /**
     * Creates a new instance of <code>CampoExceptions</code> without detail
     * message.
     */
    public CampoException() {
    }

    /**
     * Constructs an instance of <code>CampoExceptions</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public CampoException(String msg) {
        super(msg);
    }
}
