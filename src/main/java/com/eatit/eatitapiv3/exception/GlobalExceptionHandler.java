package com.eatit.eatitapiv3.exception;

import com.eatit.eatitapiv3.exception.dto.ErrorCode;
import com.eatit.eatitapiv3.exception.dto.ErrorResponse;
import jakarta.validation.ConstraintDeclarationException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EatItException.class)
    protected ResponseEntity<?> handleEatItBusinessException(EatItException exception) {
        final ErrorResponse errorResponse = new ErrorResponse(exception.getStatusCode().value(), exception.getMessage());
        log.error(exception.getMessage());
        return ResponseEntity.status(exception.getStatusCode()).body(errorResponse);
    }

    @ExceptionHandler(ConstraintDeclarationException.class)
    protected ResponseEntity<ErrorResponse> handleConstraintDeclarationException(final ConstraintDeclarationException constraintDeclarationException) {
        final var ex = constraintDeclarationException.getCause();
        if (ex instanceof ResponseStatusException) {
            final var errorResponse = new ErrorResponse(((ResponseStatusException) ex).getStatusCode().value(), ex.getMessage());
            return new ResponseEntity<>(errorResponse, ((ResponseStatusException) ex).getStatusCode());
        }
        final var errorResponse = new ErrorResponse(ErrorCode.BAD_REQUEST.getStatus(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorResponse> handleConstraintViolation(final ConstraintViolationException ex) {
        log.error(ex.getMessage());
        final var bindingResultErrors = ex.getConstraintViolations()
                                          .stream()
                                          .map(violation -> String.format("invalidProperty: %s invalidValue: %s",
                                                                          violation.getPropertyPath().toString(),
                                                                          violation.getInvalidValue()))
                                          .toList();
        final var errorResponse = new ErrorResponse(ErrorCode.BAD_REQUEST.getStatus(), bindingResultErrors.toString());
        return new ResponseEntity<>(errorResponse, new HttpHeaders(), BAD_REQUEST);
    }
}
