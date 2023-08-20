package org.blueliner.authenticationservice.contoller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.blueliner.authenticationservice.dto.AuthenticationRequest;
import org.blueliner.authenticationservice.dto.AuthenticationResponse;
import org.blueliner.authenticationservice.dto.RegisterRequest;
import org.blueliner.authenticationservice.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> addNewUser(@RequestBody RegisterRequest registerRequest) throws JsonProcessingException {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok()
                .body(authenticationService.authenticate(request));
    }

}