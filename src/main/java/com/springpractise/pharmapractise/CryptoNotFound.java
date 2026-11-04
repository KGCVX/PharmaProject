package com.springpractise.pharmapractise;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class CryptoNotFoundAdvice {

    @ExceptionHandler(CryptoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String cryptoNotFoundHandler(CryptoNotFoundException exception) {
        return exception.getMessage();
    }
}