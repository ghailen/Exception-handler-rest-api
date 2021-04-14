package com.ghailene.exceptionhandler.error.exception;

/**
 * Created by m.berriri on 03/12/2020.
 */
public class TechnicalException extends CRMException {
    public TechnicalException() { super(); }
    public TechnicalException(Throwable cause) { super(cause); }
    public TechnicalException(String message) { super(message); }
    public TechnicalException(String message, Throwable cause) { super(message, cause); }
}
