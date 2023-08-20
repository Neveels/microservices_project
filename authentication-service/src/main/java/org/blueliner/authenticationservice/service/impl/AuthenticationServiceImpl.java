package org.blueliner.authenticationservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.blueliner.authenticationservice.dto.AuthenticationRequest;
import org.blueliner.authenticationservice.dto.AuthenticationResponse;
import org.blueliner.authenticationservice.dto.RegisterRequest;
import org.blueliner.authenticationservice.exception.type.BusinessException;
import org.blueliner.authenticationservice.model.UserCredential;
import org.blueliner.authenticationservice.repo.UserCredentialRepository;
import org.blueliner.authenticationservice.service.AuthenticationService;
import org.blueliner.authenticationservice.utils.JwtService;
import org.blueliner.authenticationservice.utils.Producer;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static org.blueliner.authenticationservice.constants.Constants.USER_WITH_EMAIL_ALREADY_EXIST;
import static org.blueliner.authenticationservice.constants.Constants.USER_WITH_EMAIL_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserCredentialRepository userCredentialRepository;
    private final Producer producer;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse register(RegisterRequest request) throws JsonProcessingException {
        if (userCredentialRepository.findByEmail(request.getEmail()).isEmpty()) {
            var user = UserCredential.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
//                    .role(USER_ROLE.USER)
                    //TODO: create Image service for storing user image in AWS
//                    .avatar(PHOTO_PATH.DEFAULT_PATH.getUrl())
                    .build();
            //send object to consumer
            producer.sendMessage(user);
            userCredentialRepository.save(user);
            var jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } else {
            throw new BusinessException(String.format(USER_WITH_EMAIL_ALREADY_EXIST,
                    request.getEmail()), HttpStatus.CONFLICT);
        }
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userCredentialRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessException(String.format(USER_WITH_EMAIL_NOT_FOUND,
                        request.getEmail()), HttpStatus.NOT_FOUND)
                );
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
