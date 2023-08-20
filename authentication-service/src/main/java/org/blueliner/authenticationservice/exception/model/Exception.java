package org.blueliner.authenticationservice.exception.model;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Builder
public record Exception(
        String message,
        HttpStatus httpStatus,
        ZonedDateTime timestamp
) {
}
