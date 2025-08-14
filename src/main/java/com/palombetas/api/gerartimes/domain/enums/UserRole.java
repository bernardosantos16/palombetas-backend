package com.palombetas.api.gerartimes.domain.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("admin"),
    DIRECTOR("director");

    private final String description;

    UserRole(String description) {
        this.description = description;
    }
}
