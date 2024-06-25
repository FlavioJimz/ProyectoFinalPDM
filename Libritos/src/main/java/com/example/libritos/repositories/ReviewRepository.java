package com.example.libritos.repositories;

import com.example.libritos.models.entities.Book;
import com.example.libritos.models.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {
    List<Review> findAllByBook(Book book);
}
