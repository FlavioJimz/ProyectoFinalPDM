package com.example.libritos.services;

import com.example.libritos.models.DTOS.ChangePasswordDTO;
import com.example.libritos.models.DTOS.LoginDTO;
import com.example.libritos.models.DTOS.RegisterDTO;
import com.example.libritos.models.entities.Token;
import com.example.libritos.models.entities.User;

public interface UserServices {
    void save(RegisterDTO info) throws Exception;
    void login(LoginDTO info) throws Exception;
    void changePassword(ChangePasswordDTO info) throws Exception;
    Token registerToken(User user) throws Exception;
    Boolean isTokenValid(User user, String token);
    void cleanTokens(User user) throws Exception;
    User getUserFromToken (String info);
    Boolean comparePass(String toCompare, String current);
    void toggleToken(User user);
    User findUserAuthenticated();
    User findByUsername(String username);
}
