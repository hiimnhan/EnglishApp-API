package com.group1.EnglishApp.enumeration;
/**
 * @author Hai Dang
 */
public enum  ResponseStatus {
    SUCCEEDED("succeeded"),
    FAILED("failed");

    private String status;

    private ResponseStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
