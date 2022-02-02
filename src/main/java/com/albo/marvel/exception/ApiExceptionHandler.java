/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.albo.marvel.exception;

import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
@Getter
public class ApiExceptionHandler extends Exception {

    private static final Logger LOG = LoggerFactory.getLogger(ApiExceptionHandler.class);
    private Integer code;

    public ApiExceptionHandler(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ApiExceptionHandler(String message) {
        super(message);
        this.code = 400;
    }

    @Override
    public String toString() {
        return "MarvelAPIException{" + "code=" + code + " Message" + getMessage() + "}";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage notFoundRequest(HttpServletRequest request, Exception ex) {
        return null;
    }

    public void badRequest(HttpServletRequest request, Exception ex) {
    }
    
    public void conflict(HttpServletRequest request, Exception ex) {
    }

    public void forbiddenRequest(HttpServletRequest request, Exception ex) {
    }

    public void unauthorized(HttpServletRequest request, Exception ex) {
    }
    
    @ExceptionHandler({HttpClientErrorException.class})
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ResponseBody
    public ErrorMessage badGateway(HttpServletRequest request, HttpClientErrorException ex){
        logger(new ErrorMessage(String.format("[%d] %s", ex.getRawStatusCode(), ex.getResponseBodyAsString())), request.getContextPath());
        return new ErrorMessage(ex.getResponseBodyAsString());
    }
    
    @ExceptionHandler({NoSuchAlgorithmException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessage fatalErrorWithAlgorithm(HttpServletRequest request, NoSuchAlgorithmException ex) {
        ErrorMessage error = new ErrorMessage("An error has occurred converting the format");
        logger(error, request.getContextPath());
        return error;
    }
    
    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessage fatalErrorUnexpected(HttpServletRequest request, Exception ex) {
        ErrorMessage error = new ErrorMessage("An error has occurred unexprected");
        logger(error, request.getContextPath());
        return error;
    }
    
    private void logger(ErrorMessage error, String contextPath){
        LOG.error(String.format("[%s] :: %s", contextPath, error.getMessage()));
    }


}
