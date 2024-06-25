package com.example.libritos.services;

import com.example.libritos.models.DTOS.ReviewDTO;
import com.example.libritos.models.DTOS.SaveReviewDTO;
import com.example.libritos.models.entities.Book;

import java.util.List;

public interface ReviewServices {
    void save(SaveReviewDTO info) throws Exception;

    List<ReviewDTO> getAllCommentsByBook(Book book);
}
