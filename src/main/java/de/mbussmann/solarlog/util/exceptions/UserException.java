package de.mbussmann.solarlog.util.exceptions;

/**
 * Generic Exception when handling with Users
 * @author Manuel Bußmann
 */
public class UserException extends Exception{

    public UserException(String reason) {
        super(reason);
    }
}
