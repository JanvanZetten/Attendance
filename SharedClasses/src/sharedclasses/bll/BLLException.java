/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sharedclasses.bll;

/**
 * Packing Exceptions in the Business Logic Layer.
 * @author Asbamz
 */
public class BLLException extends Exception
{
    /**
     * Contructor by string.
     * @param message
     */
    public BLLException(String message)
    {
        super(message);
    }

    /**
     * Contructor by string and cause.
     * @param message
     */
    public BLLException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Contructor by casue.
     * @param message
     */
    public BLLException(Throwable cause)
    {
        super(cause);
    }
}
