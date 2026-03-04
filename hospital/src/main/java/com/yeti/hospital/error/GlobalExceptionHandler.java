package com.yeti.hospital.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ApiError> handleUsernameNotFoundException(UsernameNotFoundException e){
     ApiError apiError = new ApiError("Username not found with username" + e.getMessage(), HttpStatus.NOT_FOUND);
      return new ResponseEntity<>(apiError, apiError.getStatusCode());
    }

}
