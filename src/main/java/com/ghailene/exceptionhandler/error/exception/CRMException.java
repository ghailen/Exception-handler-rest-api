package com.ghailene.exceptionhandler.error.exception;

/**
 * Created by m.berriri on 03/12/2020.
 */
public class CRMException extends Exception{

    private static final long serialVersionUID = 7850792195343542868L;

    public CRMException() { super(); }
    public CRMException(String message) { super(message); }
    public CRMException(Throwable cause) { super(cause); }
    public CRMException(String message, Throwable cause) { super(message, cause); }

}
