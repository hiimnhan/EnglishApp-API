package com.group1.EnglishApp.enumeration;
/**
 * @author Hai Dang
 */
public enum ErrorCode {
    ERROR_BAD_REQUEST("Bad Request"),
    ERROR_UNEXPECTED("Unexpected Error"),
    ERROR_NOT_FOUND("Not Found"),
    ERROR_VALIDATION_FAILED("Validation Failed"),
    ERROR_ACCESS_DENIED("Access Denied"),
    ERROR_INVALID_DATA("Invalid Data"),
    ERROR_UNAUTHENTICATED("Unauthenticated"),
    ERROR_BAD_CREDENTIALS("Invalid user name or password"),
    ERROR_BAD_TOKEN("Invalid token"),
    ERROR_UNVERIFIED_USER("Unverified user"),
    ERROR_UNAUTHORIZED("Unauthorized"),
    ERROR_INACTIVE_USER("Inactive user"),
    ERROR_NOT_MATCHED("Not Matched");

    private String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getCode() {
        return this.name();
    }

    public String getMessage() {
        return message;
    }
}
