package edu.spring.footballplayerscatalog.handler;

import edu.spring.footballplayerscatalog.exception.PlayerNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class DefaultExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationErrors(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("\n"));
        String message = "Validation errors: \n" + errors;
        log.warn(message, ex);
        return ResponseEntity
                .badRequest()
                .body(message);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<String> handleValidationException(HttpMessageNotReadableException ex) {
        String message = "Can't parse request value: " + ex.getMessage();
        log.warn(message, ex);
        return ResponseEntity
                .badRequest()
                .body(message);
    }

    @ExceptionHandler(value = { PlayerNotFoundException.class })
    public ResponseEntity<String> handlePlayerNotFoundException(PlayerNotFoundException ex) {
        log.warn("Player not found exception", ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
