package com.palombetas.api.gerartimes.infra.exception.handler;

import com.palombetas.api.gerartimes.infra.exception.GenericCustomException;
import com.palombetas.api.gerartimes.infra.exception.response.GenericErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(GenericCustomException.class)
    public ResponseEntity<?> handleException(GenericCustomException ex, HttpServletRequest request) {
        var errorResponse = new GenericErrorResponse(
                ex.getType(),
                ex.getTitle(),
                ex.getStatus(),
                ex.getDetail(),
                request.getRequestURI()
        );

        return ResponseEntity.status(ex.getStatus()).body(errorResponse);
    }
}
