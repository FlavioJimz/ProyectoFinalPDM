package com.example.libritos.controllers;

import com.example.libritos.models.DTOS.*;
import com.example.libritos.models.entities.Token;
import com.example.libritos.models.entities.User;
import com.example.libritos.repositories.UserRepository;
import com.example.libritos.services.UserServices;
import com.example.libritos.utils.RequestErrorHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private UserServices userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestErrorHandler errorHandler;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginDTO info, BindingResult validations) throws Exception {
        if (validations.hasErrors()) {
            return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }


        User user = userService.findByUsername(info.getUsername());
        if (user == null)
            return new ResponseEntity<>(new MessageDTO("User not found"), HttpStatus.UNAUTHORIZED);

        try {
            userService.login(info);
            Token token = userService.registerToken(user);
            return new ResponseEntity<>(new TokenDTO(token), HttpStatus.OK);

        } catch (Exception e) {
            if (e.getMessage().equals("Invalid credentials")) {
                return new ResponseEntity<>(new MessageDTO(e.getMessage()), HttpStatus.UNAUTHORIZED);
            } else if (e.getMessage().equals("User inactive")) {
                return new ResponseEntity<>(new MessageDTO(e.getMessage()), HttpStatus.CONFLICT);
            } else {
                e.printStackTrace();
                return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> saveUser(@RequestBody @Valid RegisterDTO info, BindingResult validations) throws Exception {
        if (validations.hasErrors()) {
            return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }

        List<User> allUsers = userRepository.findAll();
        List<String> allUsersUsername = allUsers.stream().map(u -> u.getUsername()).collect(Collectors.toList());
        if (allUsersUsername.contains(info.getUsername()))
            return new ResponseEntity<>(new MessageDTO("User already exists"), HttpStatus.CONFLICT);

        try {
            userService.save(info);
            return new ResponseEntity<>(new MessageDTO("user created!"), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@RequestBody TokenDTO token) {
        try {
            User user = userService.getUserFromToken(token.getToken());
            userService.cleanTokens(user);
            return new ResponseEntity<>(new MessageDTO("User logged out"), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePasswordDTO info, BindingResult validations) {
        if (validations.hasErrors()) {
            return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }

        User user = userService.findByUsername(info.getUsername());
        if (user == null)
            return new ResponseEntity<>(new MessageDTO("User not found"), HttpStatus.UNAUTHORIZED);

        try {
            userService.changePassword(info);
            return new ResponseEntity<>(new MessageDTO("Password changed"), HttpStatus.OK);

        } catch (Exception e) {
            if (e.getMessage().equals("Invalid credentials")) {
                return new ResponseEntity<>(new MessageDTO(e.getMessage()), HttpStatus.UNAUTHORIZED);
            } else {
                e.printStackTrace();
                return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping("/info")
    public ResponseEntity<?> info(@RequestBody TokenDTO token,BindingResult validations) {
        if (validations.hasErrors()) {
            return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }

        try {
            User user = userService.getUserFromToken(token.getToken());
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
