package com.group1.EnglishApp.exception.handler;

import com.group1.EnglishApp.builder.ResponseEntityBuilder;
import com.group1.EnglishApp.enumeration.ErrorCode;
import com.group1.EnglishApp.exception.EnglishAppException;
import com.group1.EnglishApp.exception.EnglishAppValidationException;
import com.group1.EnglishApp.exception.TokenMalformedException;
import com.group1.EnglishApp.exception.TokenMissingException;
import com.group1.EnglishApp.response.GenericResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Hai Dang
 */
@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *
     * @param exp
     * @param request
     * @return
     */
    @ExceptionHandler({
            HttpMediaTypeNotSupportedException.class,
            HttpRequestMethodNotSupportedException.class,
            NoHandlerFoundException.class
    })
    public ResponseEntity<?> handleResourceNotFoundException(Exception exp, HttpServletRequest request) {

        logger.error(exp.getMessage());

        return ResponseEntityBuilder.createBuilder()
                .status(HttpStatus.OK)
                .error(ErrorCode.ERROR_NOT_FOUND.getCode(), ErrorCode.ERROR_NOT_FOUND.getMessage(), exp.getMessage())
                .build();
    }

    /**
     *
     * @param exp
     * @param request
     * @return
     */
    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<?> handleAccessDeniedException(Exception exp, HttpServletRequest request) {

        logger.error(exp.getMessage(), exp);

        return ResponseEntityBuilder.createBuilder()
                .status(HttpStatus.FORBIDDEN)
                .error(ErrorCode.ERROR_ACCESS_DENIED.getCode(), ErrorCode.ERROR_ACCESS_DENIED.getMessage(), null)
                .build();
    }

    /**
     *
     * @param exp
     * @param request
     * @return
     */
    @ExceptionHandler({ BadCredentialsException.class})
    public ResponseEntity<?> handleBadCredentialsException(Exception exp, HttpServletRequest request) {

        logger.error(exp.getMessage(), exp);

        return ResponseEntityBuilder.createBuilder()
                .status(HttpStatus.OK)
                .error(ErrorCode.ERROR_BAD_CREDENTIALS.getCode(), ErrorCode.ERROR_BAD_CREDENTIALS.getMessage(), null)
                .build();
    }

    /**
     *
     * @param exception
     * @return
     */
    @ExceptionHandler({ MethodArgumentNotValidException.class, ConstraintViolationException.class, BindException.class })
    public ResponseEntity<?> processValidationError(Exception exception) {

        if (exception instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) exception;
            BindingResult result = ex.getBindingResult();
            return processFieldErrors(result.getFieldErrors());
        } else if (exception instanceof BindException) {
            BindException ex = (BindException) exception;
            BindingResult result = ex.getBindingResult();
            return processFieldErrors(result.getFieldErrors());
        } else {
            ConstraintViolationException ex = (ConstraintViolationException) exception;
            return processConstraintViolations(ex.getConstraintViolations());
        }
    }

    /**
     *
     * @param errors
     * @return
     */
    private ResponseEntity<?> processFieldErrors(List<FieldError> errors) {

        List<GenericResponse.Error> errList = errors.stream().map((FieldError e) -> {
            GenericResponse.Error err = new GenericResponse.Error();
            err.setField(e.getField());
            err.setMessage(e.getDefaultMessage());
            return err;
        }).collect(Collectors.toList());

        ResponseEntity<?> response = ResponseEntityBuilder.createBuilder()
                .error(ErrorCode.ERROR_VALIDATION_FAILED)
                .validationErrors(errList)
                .build();

        return response;
    }

    /**
     *
     * @param violations
     * @return
     */
    private ResponseEntity<?> processConstraintViolations(Set<ConstraintViolation<?>> violations) {

        List<GenericResponse.Error> errList = violations.stream().map((ConstraintViolation<?> e) -> {
            GenericResponse.Error err = new GenericResponse.Error();
            List<String> propertyList = new ArrayList<>();
            e.getPropertyPath().forEach((node) -> {
                propertyList.add(node.toString());
            });
            err.setField(propertyList.get(propertyList.size() - 1));
            err.setMessage(e.getMessage());
            return err;
        }).collect(Collectors.toList());

        ResponseEntity<?> response = ResponseEntityBuilder.createBuilder()
                .error(ErrorCode.ERROR_VALIDATION_FAILED.getCode(), ErrorCode.ERROR_VALIDATION_FAILED.getMessage(), null)
                .validationErrors(errList)
                .build();

        return response;
    }

    /**
     *
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(EnglishAppException.class)
    public ResponseEntity<?> generalMessageGatewayExceptionHandler(EnglishAppException exception, HttpServletRequest request) {
        ErrorCode errorCode = exception.getErrorCode() != null ? exception.getErrorCode() : ErrorCode.ERROR_UNEXPECTED;
        String message = StringUtils.isNotBlank(exception.getMessage()) ? exception.getMessage() : errorCode.getMessage();
        return ResponseEntityBuilder.createBuilder()
                .status(HttpStatus.OK)
                .error(errorCode.getCode(), message, ExceptionUtils.getStackTrace(exception))
                .build();
    }

    /**
     *
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(EnglishAppValidationException.class)
    public ResponseEntity<?> generalMessageGatewayValidationExceptionHandler(EnglishAppValidationException exception, HttpServletRequest request) {

        return ResponseEntityBuilder.createBuilder()
                .status(HttpStatus.OK)
                .error(ErrorCode.ERROR_VALIDATION_FAILED.getCode(), exception.getMessage(), null)
                .build();
    }

    /**
     * General exception handler
     *
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> generalExceptionHandler(Exception exception, HttpServletRequest request) {

        logger.error(exception.getMessage(), exception);

        return ResponseEntityBuilder.createBuilder()
                .status(HttpStatus.OK)
                .error(ErrorCode.ERROR_BAD_REQUEST.getCode(), ErrorCode.ERROR_BAD_REQUEST.getMessage(),
                        ExceptionUtils.getStackTrace(exception))
                .build();
    }

    @ExceptionHandler({ TokenMissingException.class, TokenMalformedException.class})
    public ResponseEntity<?> handleInvalidTokenException(Exception exp, HttpServletRequest request) {

        //logger.error(exp.getMessage(), exp);

        return ResponseEntityBuilder.createBuilder()
                .status(HttpStatus.OK)
                .error(ErrorCode.ERROR_BAD_TOKEN.getCode(), ErrorCode.ERROR_BAD_TOKEN.getMessage(), exp.getMessage())
                .build();
    }
}
