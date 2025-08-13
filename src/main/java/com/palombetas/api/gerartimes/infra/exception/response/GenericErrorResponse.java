package com.palombetas.api.gerartimes.infra.exception.response;

import lombok.Getter;
import org.springframework.validation.FieldError;

import java.lang.reflect.Field;


// RFC 7807 pattern for error responses

public record GenericErrorResponse(
        String type,
        String title,
        int status,
        String detail,
        String instance
) {

}
