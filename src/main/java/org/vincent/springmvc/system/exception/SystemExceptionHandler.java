package org.vincent.springmvc.system.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class SystemExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public GenericError handleRuntimeException(RuntimeException ex) {
        String errMsg = ex.getMessage();
        logger.error("error:{}", errMsg);

        return buildErrorJsonObject(errMsg);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public GenericError handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String errMsg = "Correct Time format example: 2018-03-03T21:00";
        logger.error("error:{}", errMsg);

        return buildErrorJsonObject(errMsg);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public GenericError handleConstraintViolationException(ConstraintViolationException ex) {
        String errMsg = getConstraintViolationExceptionMessages(ex);
        logger.error("error:{}", errMsg);

        return buildErrorJsonObject(errMsg);
    }

    @ExceptionHandler(GenericException.class)
    @ResponseBody
    public GenericError handleGenericException(GenericException ex) {
        String errMsg = ex.getErrorMessage();
        logger.error("error:{}", errMsg);

        return buildErrorJsonObject(errMsg);
    }

    private GenericError buildErrorJsonObject(String errMessage) {
        return new GenericError("Error", errMessage);
    }

    private String getConstraintViolationExceptionMessages(ConstraintViolationException ex) {
        return ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).reduce("", (a, b)-> a.concat(b).concat(","));
    }

}
