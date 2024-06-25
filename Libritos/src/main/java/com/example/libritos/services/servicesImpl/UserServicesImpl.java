package com.example.libritos.services.servicesImpl;

import com.example.libritos.models.DTOS.ChangePasswordDTO;
import com.example.libritos.models.DTOS.LoginDTO;
import com.example.libritos.models.DTOS.RegisterDTO;
import com.example.libritos.models.entities.Token;
import com.example.libritos.models.entities.User;
import com.example.libritos.repositories.TokenRepository;
import com.example.libritos.repositories.UserRepository;
import com.example.libritos.services.UserServices;
import com.example.libritos.utils.JWTTools;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServicesImpl implements UserServices {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private TokenRepository tokenRepository;


    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void save(RegisterDTO info) throws Exception {
        try {
            User user = new User(info.getUsername(), info.getName(), info.getLastname(), info.getEmail(), info.getPhone(), passwordEncoder.encode(info.getPassword()));

            userRepository.save(user);

        } catch (Exception e) {
            throw new Exception("Error save user");
        }

    }

    @Override
    public void login(LoginDTO info) throws Exception {
        User user = userRepository.findUserByUsername(info.getUsername());

        if (!comparePass(info.getPassword(), user.getPassword())) {
            throw new Exception("Invalid credentials");
        }
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Token registerToken(User user) throws Exception {
        cleanTokens(user);

        String tokenString = jwtTools.generateToken(user);
        Token token = new Token(tokenString, user);

        tokenRepository.save(token);

        return token;
    }

    @Override
    public Boolean isTokenValid(User user, String token) {
        try {
            cleanTokens(user);
            List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

            tokens.stream().filter(tk -> tk.getToken().equals(token)).findAny().orElseThrow(() -> new Exception());

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void cleanTokens(User user) throws Exception {
        List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

        tokens.forEach(token -> {
            if (!jwtTools.verifyToken(token.getToken())) {
                token.setActive(false);
                tokenRepository.save(token);
            }
        });
    }

    @Override
    public User getUserFromToken(String info) {
        List<Token> token = tokenRepository.findAll().stream()
                .filter(t -> t.getToken().matches(info) && t.getActive().equals(true)).collect(Collectors.toList());
        User user = token.get(0).getUser();

        return user;
    }

    @Override
    public Boolean comparePass(String toCompare, String current) {
        return passwordEncoder.matches(toCompare, current);
    }

    @Override
    public void toggleToken(User user) {
        List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

        if (!tokens.isEmpty()) {
            Token token = tokens.get(0);
            token.setActive(false);
            tokenRepository.save(token);
        }
    }

    @Override
    public User findUserAuthenticated() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findUserByUsername(username);
    }

    @Override
    public void changePassword(ChangePasswordDTO info) throws Exception {
        User user = findUserAuthenticated();

        if (!comparePass(info.getOldPassword(), user.getPassword())) {
            throw new Exception("Invalid password");
        }

        user.setPassword(passwordEncoder.encode(info.getNewPassword()));
        userRepository.save(user);
    }
}
