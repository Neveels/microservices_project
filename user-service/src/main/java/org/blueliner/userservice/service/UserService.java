package org.blueliner.userservice.service;

import org.blueliner.userservice.dto.UserCredentialDto;

public interface UserService {
    void saveUserCredentials(UserCredentialDto userCredentialDto);
}
