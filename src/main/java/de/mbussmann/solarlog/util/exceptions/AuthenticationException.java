package de.mbussmann.solarlog.util.exceptions;

/**
 * @author Manuel Bußmann
 */
public class AuthenticationException extends Exception {

    /**
     * Generic Authentication Exception
     */
    private static final long serialVersionUID = 1L;

    public AuthenticationException(String message) {
        super(message);
    }
    
}
