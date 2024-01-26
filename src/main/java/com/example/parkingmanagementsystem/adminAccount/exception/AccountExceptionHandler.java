package com.example.parkingmanagementsystem.adminAccount.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccountExceptionHandler {
    @ExceptionHandler(value = {AccountNotFoundException.class})
    public ResponseEntity<Object> AccountNotFoundException
            (AccountNotFoundException accountNotFoundException)
    {
        AccountException AccountException=new AccountException(accountNotFoundException.getMessage()
                ,accountNotFoundException.getCause()
                , HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(accountNotFoundException,HttpStatus.NOT_FOUND);
    }
}
