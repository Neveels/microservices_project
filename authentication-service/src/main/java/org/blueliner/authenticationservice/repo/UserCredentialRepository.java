package org.blueliner.authenticationservice.repo;


import org.blueliner.authenticationservice.model.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCredentialRepository  extends JpaRepository<UserCredential,Integer> {
    Optional<UserCredential> findByName(String username);
    Optional<UserCredential> findByEmail(String email);
}
