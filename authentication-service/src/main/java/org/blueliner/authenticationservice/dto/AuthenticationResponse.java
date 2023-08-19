package org.blueliner.authenticationservice.dto;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        String token
) {
}