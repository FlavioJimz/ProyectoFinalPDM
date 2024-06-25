package com.example.libritos.models.DTOS;

import com.example.libritos.models.entities.Token;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenDTO {
    private String token;

    public TokenDTO(Token token) {
        this.token = token.getToken();
    }
}
