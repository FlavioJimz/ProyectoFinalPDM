package com.example.libritos.models.DTOS;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {

    @NotEmpty(message = "username can't be empty")
    private String username;

    @NotEmpty(message = "name can't be empty")
    private String name;

    @NotEmpty(message = "lastname can't be empty")
    private String lastname;

    @NotEmpty(message = "email can't be empty")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "phone can't be empty")
    @Pattern(regexp = "\\d{8}", message = "Phone must be exactly 8 digits")
    private String phone;

    @NotEmpty(message = "password can't be empty")
    private String password;
}
