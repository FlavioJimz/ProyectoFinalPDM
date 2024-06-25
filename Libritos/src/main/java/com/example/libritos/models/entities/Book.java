package com.example.libritos.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name="code")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idBook;

    @Column(name="title")
    private String title;

    @Column(name="author")
    private String author;

    @Column(name="genre")
    private String genre;

    @Column(name="description")
    private String description;

    @Column(name="rating")
    private Float rating;

    public Book(String title, String author, String genre, String description, Float rating) {
        super();
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.description = description;
        this.rating = rating;
    }
}
