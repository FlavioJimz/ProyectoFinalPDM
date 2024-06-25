package com.example.libritos.models.DTOS;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {

    @NotEmpty(message = "username can't be empty")
    private String username;

    @NotEmpty(message = "password can't be empty")
    private String password;
}
