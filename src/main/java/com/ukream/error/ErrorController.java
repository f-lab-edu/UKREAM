package com.ukream.error;

import com.ukream.error.exception.BrandNotFoundException;
import com.ukream.error.exception.DuplicatedBrandNameException;
import com.ukream.error.exception.DuplicatedEmailException;
import com.ukream.error.exception.LoginFailureException;
import com.ukream.error.exception.LoginRequiredException;
import com.ukream.error.exception.UserNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

  @ExceptionHandler(DuplicatedEmailException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public ResponseEntity<String> handleDuplicatedEmailException(DuplicatedEmailException ex) {
    return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult()
        .getAllErrors()
        .forEach(
            (error) -> {
              String fieldName = ((FieldError) error).getField();
              String errorMessage = error.getDefaultMessage();
              errors.put(fieldName, errorMessage);
            });
    return ResponseEntity.badRequest().body(errors);
  }


  @ExceptionHandler(LoginRequiredException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleLoginRequiredException(LoginRequiredException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  @ExceptionHandler(LoginFailureException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleLoginFailureException(LoginFailureException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  @ExceptionHandler(UserNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  @ExceptionHandler(BrandNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleBrandNotFoundException(BrandNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  @ExceptionHandler(DuplicatedBrandNameException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<String> handleDuplicatedBrandNameException(DuplicatedBrandNameException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }
}