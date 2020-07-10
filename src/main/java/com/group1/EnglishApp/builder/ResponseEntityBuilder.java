package com.group1.EnglishApp.builder;

import com.group1.EnglishApp.enumeration.ErrorCode;
import com.group1.EnglishApp.enumeration.ResponseStatus;
import com.group1.EnglishApp.response.GenericResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @author Hai Dang
 */
public class ResponseEntityBuilder<K> {
    private HttpHeaders headers;
    private K data;
    private GenericResponse<K> responseJson;
    private HttpStatus statusCode;

    private ResponseEntityBuilder() {
        this.headers = null;
        this.data = null;
        this.success(null); // default is success
        this.statusCode = HttpStatus.OK;
    }

    public ResponseEntityBuilder<K> headers(HttpHeaders headers) {
        this.headers = headers;
        return this;
    }

    public ResponseEntityBuilder<K> data(K data) {
        this.data = data;
        return this;
    }

    public ResponseEntityBuilder<K> success() {
        this.success(null);
        return this;
    }

    public ResponseEntityBuilder<K> success(String message) {
        this.success(null, message);
        return this;
    }

    public ResponseEntityBuilder<K> success(K data, String message) {
        responseJson = new GenericResponse<K>();
        responseJson.setStatus(ResponseStatus.SUCCEEDED.getStatus());
        responseJson.setMessage(message);
        responseJson.setData(data);
        return this;
    }

    public ResponseEntityBuilder<K> error() {
        this.error(null);
        return this;
    }

    public ResponseEntityBuilder<K> error(ErrorCode errorCode) {
        if (errorCode != null) {
            this.error(errorCode.getCode(), errorCode.getMessage(), null);
        } else {
            this.error(ErrorCode.ERROR_UNEXPECTED);
        }
        return this;
    }

    public ResponseEntityBuilder<K> error(String errorCode, String errorMessage, String exception) {
        responseJson = new GenericResponse<K>();
        responseJson.setStatus(ResponseStatus.FAILED.getStatus());
        responseJson.setCode(errorCode);
        responseJson.setMessage(errorMessage);
        responseJson.setException(exception);
        return this;
    }

    public ResponseEntityBuilder<K> status(HttpStatus status) {
        this.statusCode = status;
        return this;
    }

    public ResponseEntityBuilder<K> validationErrors(List<GenericResponse.Error> errors) {
        responseJson.setErrors(errors);
        return this;
    }

    public ResponseEntity<GenericResponse<K>> build() {
        responseJson.setData(data);
        if (headers != null) {
            return new ResponseEntity<>(responseJson, headers, statusCode);
        } else {
            return new ResponseEntity<>(responseJson, statusCode);
        }
    }

    public static <K> ResponseEntityBuilder<K> createBuilder() {
        return new ResponseEntityBuilder<K>();
    }
}
