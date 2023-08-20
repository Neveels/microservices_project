package org.blueliner.userservice.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserCredentialDto {
    private String name;
    private String email;
    private String password;

}
