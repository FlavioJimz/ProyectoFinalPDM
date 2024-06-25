package com.example.libritos.services;

import com.example.libritos.models.DTOS.SaveBookDTO;
import com.example.libritos.models.entities.Book;

import java.util.List;

public interface BookServices {
    void save(SaveBookDTO info) throws Exception;
    void deleteByTitle(String title) throws Exception;
    Book getBookByTitle(String title);
    List<Book> getAll();
}
