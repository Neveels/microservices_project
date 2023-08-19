package org.blueliner.authenticationservice.service;

import org.blueliner.authenticationservice.dto.AuthenticationRequest;
import org.blueliner.authenticationservice.dto.AuthenticationResponse;
import org.blueliner.authenticationservice.dto.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
