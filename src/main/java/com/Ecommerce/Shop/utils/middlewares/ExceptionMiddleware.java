package com.Ecommerce.Shop.utils.middlewares;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.Ecommerce.Shop.utils.results.ErrorResult;
import com.Ecommerce.Shop.utils.results.Result;
import com.Ecommerce.Shop.utils.constants.Messages;

@RestControllerAdvice
public class ExceptionMiddleware {

    @ExceptionHandler(value = {RuntimeException.class, VirtualMachineError.class})
    public ResponseEntity<Result> handleRuntimeException() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErrorResult(Messages.error.toString()));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Result> handleAuthenticationException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ErrorResult(Messages.wrong_password.toString()));
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Result> handleNotFoundException(EntityNotFoundException ex) {
        var entityName = ex.getMessage().split(" ")[3].split("\\.")[3];

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErrorResult(entityName + " Couldn't Found!"));
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<Result> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResult(ex.getMessage()));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Result> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        var splittedMessage = ex.getMessage().split("default message");

        var validationMessage = "";

        var first = true;
        for (var message : splittedMessage) {
            if (!first) {
                validationMessage += message.split("\\[")[1].split("]")[0] + " ";
            }

            first = false;
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ErrorResult(validationMessage));
    }
}


