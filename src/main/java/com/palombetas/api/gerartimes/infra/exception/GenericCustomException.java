package com.palombetas.api.gerartimes.infra.exception;

import lombok.Getter;

@Getter
public class GenericCustomException extends RuntimeException {
    private final String type;
    private final String title;
    private final int status;
    private final String detail;

    public GenericCustomException(
            String type,
            String title,
            int status,
            String detail
    ) {
        super(detail);
        this.type = type;
        this.title = title;
        this.status = status;
        this.detail = detail;
    }
}
