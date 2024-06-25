package com.example.libritos.repositories;

import com.example.libritos.models.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    Book findByTitle(String title);
}
