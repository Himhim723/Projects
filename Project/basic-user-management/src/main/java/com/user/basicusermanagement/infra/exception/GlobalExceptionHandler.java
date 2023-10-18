package com.user.basicusermanagement.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.user.basicusermanagement.infra.ApiResp;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  
  @ExceptionHandler(value = UserExistedException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> userExistedHandler(UserExistedException e) {
    return ApiResp.<Void>builder() //
        .status(getRespCode(e)) //
        .data(null) //
        .build();
  }

  @ExceptionHandler(value = RuntimeException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> runtimeExceptionHandler(RuntimeException e) {
    return ApiResp.<Void>builder() //
        .status(getRespCode(e)) //
        .concatMessageIfPresent(e.getMessage()).data(null) //
        .build();
  }

  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public ApiResp<Void> exceptionHandler(Exception e) {
    return ApiResp.<Void>builder() //
        .status(getRespCode(e)) //
        .concatMessageIfPresent(e.getMessage()).data(null) //
        .build();
  }

  private static Code getRespCode(Exception e) {
    if (e instanceof IllegalArgumentException) {
      return Code.IAE_EXCEPTION;
    } else if (e instanceof UserExistedException){
      return Code.ALREADY_EXIST;
    } else if (e instanceof EntityNotFoundException) {
      return Code.ENTITY_NOT_FOUND;
    } else if (e instanceof ConstraintViolationException) {
      return Code.VALIDATOR_FAILURE;
    } 
    // ...
    return null;
  }
}
