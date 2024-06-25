package com.example.libritos.controllers;

import com.example.libritos.models.DTOS.MessageDTO;
import com.example.libritos.models.DTOS.SaveBookDTO;
import com.example.libritos.models.entities.Book;
import com.example.libritos.models.entities.User;
import com.example.libritos.services.BookServices;
import com.example.libritos.services.UserServices;
import com.example.libritos.utils.RequestErrorHandler;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("book")
public class BookController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private BookServices bookServices;

    @Autowired
    private RequestErrorHandler errorHandler;

    @PostMapping("/")
    public ResponseEntity<?> saveBook(@RequestBody @Valid SaveBookDTO info, BindingResult validations) throws Exception{
        if (validations.hasErrors()) {
            return new ResponseEntity<>(errorHandler.mapErrors(validations.getFieldErrors()), HttpStatus.BAD_REQUEST);
        }

        User userFound = userServices.findUserAuthenticated();
        if (userFound == null)
            return new ResponseEntity<>(new MessageDTO("User not authenticated"), HttpStatus.NOT_FOUND);

        try{
            bookServices.save(info);
            return new ResponseEntity<>(new MessageDTO("Book created!"), HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{title}")
    public ResponseEntity<?> deleteBook(@PathVariable String title) throws Exception{
        User userFound = userServices.findUserAuthenticated();
        if (userFound == null)
            return new ResponseEntity<>(new MessageDTO("User not authenticated"), HttpStatus.NOT_FOUND);

        try{
            bookServices.deleteByTitle(title);
            return new ResponseEntity<>(new MessageDTO("book deleted!"), HttpStatus.OK);
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
            return new ResponseEntity<>(new MessageDTO("Municipality not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookFound, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBooks(){

        User userFound = userServices.findUserAuthenticated();
        if (userFound == null)
            return new ResponseEntity<>(new MessageDTO("User not authenticated"), HttpStatus.NOT_FOUND);

        try{
            List<Book> bookList = bookServices.getAll();
            return new ResponseEntity<>(bookList, HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(new MessageDTO("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
