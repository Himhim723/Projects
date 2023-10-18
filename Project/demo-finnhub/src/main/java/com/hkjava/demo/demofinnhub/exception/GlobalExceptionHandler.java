package com.hkjava.demo.demofinnhub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;
import com.hkjava.demo.demofinnhub.infra.ApiResp;
import com.hkjava.demo.demofinnhub.infra.Code;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(value = FinnhubException.class)
  @ResponseStatus(value = HttpStatus.OK)
  public ApiResp<Void> finnhubExceptionHandler(FinnhubException e) {
    return ApiResp.<Void>builder() //
        .status(Code.fromCode(e.getCode())) //
        .data(null) //
        .build();
  }

  @ExceptionHandler(value = ConstraintViolationException.class)
  @ResponseStatus(value = HttpStatus.OK)
  public ApiResp<Void> finnhubExceptionHandler(
      ConstraintViolationException e) {
    return ApiResp.<Void>builder() //
        .status(getRespCode(e)) //
        .concatMessageIfPresent(e.getMessage()).data(null) //
        .data(null) //
        .build();
  }
  // ConstraintViolationException

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
    } else if (e instanceof EntityNotFoundException) {
      return Code.ENTITY_NOT_FOUND;
    } else if (e instanceof ResourceAccessException) {
      return Code.REDIS_SERVER_UNAVAILABLE;
    } else if (e instanceof ConstraintViolationException) {
      return Code.VALIDATOR_FAIL;

    }
    // ...
    return null;
  }
}
