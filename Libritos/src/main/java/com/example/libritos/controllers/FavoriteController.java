package com.example.libritos.controllers;

import com.example.libritos.models.DTOS.MessageDTO;
import com.example.libritos.models.DTOS.SaveBookDTO;
import com.example.libritos.models.DTOS.SaveFavoriteDTO;
import com.example.libritos.models.entities.Book;
import com.example.libritos.models.entities.User;
import com.example.libritos.services.FavoriteServices;
import com.example.libritos.services.UserServices;
import com.example.libritos.utils.RequestErrorHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("favorite")
public class FavoriteController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private FavoriteServices favoriteServices;

    @Autowired
    private RequestErrorHandler errorHandler;

    @PostMapping("/")
    public ResponseEntity<?> saveFavorite(@ModelAttribute @Valid SaveFavoriteDTO info, BindingResult validations) throws Exception{
        if (validations.hasErrors()) {
            return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }

        User userFound = userServices.findUserAuthenticated();
        if (userFound == null)
            return new ResponseEntity<>(new MessageDTO("User not authenticated"), HttpStatus.NOT_FOUND);

        try{
            favoriteServices.save(info);
            return new ResponseEntity<>(new MessageDTO("Favorite created!"), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/book")
    public ResponseEntity<?> getAllBooksByFavorite(){
        User userFound = userServices.findUserAuthenticated();
        if (userFound == null) {
            return new ResponseEntity<>(new MessageDTO("User not authenticated"), HttpStatus.NOT_FOUND);
        }

        List<Book> favoriteBooks = favoriteServices.getAllByFavorite(userFound);
        if (favoriteBooks == null){
            return new ResponseEntity<>(new MessageDTO("NO FAVORITE BOOKS"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(favoriteBooks, HttpStatus.OK);
    }
}
