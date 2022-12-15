package com.chatapp.backend.controller;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import java.io.FileNotFoundException;
import java.io.IOException;
import liquibase.exception.DatabaseException;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ControllerExceptionHandler {
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler({EntityNotFoundException.class, FileNotFoundException.class})
  public ErrorMessage resourceNotFoundException(EntityNotFoundException ex) {
    return new ErrorMessage(ex.getMessage(), ex.getCause());
  }


  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(IllegalArgumentException.class)
  public ErrorMessage handleBadRequestException(IllegalArgumentException ex) {
    return new ErrorMessage(ex.getMessage());
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({DataAccessException.class, DatabaseException.class, NullPointerException.class, IndexOutOfBoundsException.class,
      IOException.class})
  public ErrorMessage handleDataException(RuntimeException ex) {
    return new ErrorMessage(ex.getMessage(), ex.getCause());
  }

  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler({DataIntegrityViolationException.class, EntityExistsException.class})
  public ErrorMessage handleDataIntegrityException(RuntimeException ex) {
    return new ErrorMessage(ex.getMessage(), ex.getCause());
  }
}
