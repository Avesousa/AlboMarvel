package com.albo.marvel.exception;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ApiResponses(value = {
    @ApiResponse(responseCode = "404", description = "Data not found", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
    }),
    @ApiResponse(responseCode = "405", description = "Endpoint not allowed", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
    }),
    @ApiResponse(responseCode = "405", description = "Endpoint not allowed", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
    }),
    @ApiResponse(responseCode = "500", description = "Error unexpected or Error occurred converting format algorithm", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
    }),
    @ApiResponse(responseCode = "502", description = "Error with other services", content = {
        @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
    }),})
@ControllerAdvice
@Getter
public class ApiExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @ExceptionHandler({NotFoundContentException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorMessage noContent(HttpServletRequest request, NotFoundContentException ex) {
        ErrorMessage error = new ErrorMessage(ex.getMessage());
        logger(error, request);
        return error;
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public ErrorMessage methodNotSupported(HttpServletRequest request, HttpRequestMethodNotSupportedException ex) {
        ErrorMessage error = new ErrorMessage("The endpoint not allowed");
        logger(error, request);
        return error;
    }

    @ExceptionHandler({NoSuchAlgorithmException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessage fatalErrorWithAlgorithm(HttpServletRequest request, NoSuchAlgorithmException ex) {
        ErrorMessage error = new ErrorMessage("An error has occurred converting the format");
        logger(error, request);
        return error;
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorMessage fatalErrorUnexpected(HttpServletRequest request, Exception ex) {
        ErrorMessage error = new ErrorMessage(String.format("An error has occurred unexpected ex: %s", ex.getMessage()));
        logger(error, request);
        return error;
    }

    @ExceptionHandler({HttpClientErrorException.class})
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ResponseBody
    public ErrorMessage badGateway(HttpServletRequest request, HttpClientErrorException ex) {
        ErrorMessage error = new ErrorMessage(String.format("[%d] %s", ex.getRawStatusCode(), ex.getResponseBodyAsString()));
        logger(error, request);
        return new ErrorMessage(ex.getResponseBodyAsString());
    }

    private void logger(ErrorMessage error, HttpServletRequest request) {
        LOG.error(String.format("[%s%s] :: %s", request.getContextPath(), request.getServletPath(), error.getMessage()));
    }

}
