package org.blueliner.authenticationservice.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegisterRequest {
    private String name;
    private String email;
    private String password;

}