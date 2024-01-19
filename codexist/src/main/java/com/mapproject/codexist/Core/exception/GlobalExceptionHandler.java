package com.mapproject.codexist.Core.exception;

import com.mapproject.codexist.Core.results.ErrorResult;
import com.mapproject.codexist.Core.results.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({org.springframework.beans.TypeMismatchException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleTypeMismatchException(org.springframework.beans.TypeMismatchException ex) {
        String message = String.format("Parametre '%s' için geçersiz veri tipi.", ex.getPropertyName());
        return new ErrorResult(message);
    }

    @ExceptionHandler({NullPointerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleNullPointerException(NullPointerException ex) {
        return new ErrorResult("Hiçbir alan null olamaz.");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        String parameterName = ex.getParameterName();
        String message = "Parametre '" + parameterName + "' eksik. Lütfen tüm parametreleri sağlayın.";
        return new ErrorResult(message);
    }


}
