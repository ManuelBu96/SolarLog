package de.mbussmann.solarlog.util.exceptions;

/**
 * Enum for different Exception Reasons to give a clear error message to the use
 * @author Manuel Bußmann
 */
public enum ExceptionReason {
    // Authentication
    EMAIL_ALREADY_IN_USE("EMAIL_ALREADY_IN_USE"),
    USERNAME_OR_PASSWORD_INCORRECT("USERNAME_OR_PASSWORD_INCORRECT"),
    JWT_GENERATION_ERROR("JWT_GENERATION_ERROR"),
    // User Exception
    GET_OWN_DATA_FAILED("GET_OWN_DATA_FAILED"),
    CANNOT_CHANGE_OWN_DATA("CANNOT_CHANGE_OWN_DATA%s"),
    CANNOT_DELETE_LAST_ADMIN("CANNOT_DELETE_LAST_ADMIN"),
    DELETE_USER_FAILED("DELETE_USER_FAILED");


    private final String reason;

    private ExceptionReason(final String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}
