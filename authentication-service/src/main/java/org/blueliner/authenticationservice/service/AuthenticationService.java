package org.blueliner.authenticationservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.blueliner.authenticationservice.dto.AuthenticationRequest;
import org.blueliner.authenticationservice.dto.AuthenticationResponse;
import org.blueliner.authenticationservice.dto.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request) throws JsonProcessingException;
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
