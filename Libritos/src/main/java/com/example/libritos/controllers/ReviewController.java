package com.example.libritos.controllers;

import com.example.libritos.models.DTOS.*;
import com.example.libritos.models.entities.Book;
import com.example.libritos.models.entities.User;
import com.example.libritos.services.BookServices;
import com.example.libritos.services.ReviewServices;
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
@RequestMapping("review")
public class ReviewController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private ReviewServices reviewServices;

    @Autowired
    private RequestErrorHandler errorHandler;

    @Autowired
    private BookServices bookServices;


    @PostMapping("/")
    public ResponseEntity<?> saveBook(@ModelAttribute @Valid SaveReviewDTO info, BindingResult validations) throws Exception{
        if (validations.hasErrors()) {
            return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }

        User userFound = userServices.findUserAuthenticated();
        if (userFound == null)
            return new ResponseEntity<>(new MessageDTO("User not authenticated"), HttpStatus.NOT_FOUND);

        try{
            reviewServices.save(info);
            return new ResponseEntity<>(new MessageDTO("Review created!"), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{title}")
    public ResponseEntity<?> getBookByTitle(@PathVariable String title){
        User userFound = userServices.findUserAuthenticated();
        if (userFound == null) {
            return new ResponseEntity<>(new MessageDTO("User not authenticated"), HttpStatus.NOT_FOUND);
        }

        Book bookFound = bookServices.getBookByTitle(title);
        if (bookFound == null){
            return new ResponseEntity<>(new MessageDTO("Book not found"), HttpStatus.NOT_FOUND);
        }

        try{
            List<ReviewDTO> reviews = reviewServices.getAllCommentsByBook(bookFound);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
