package com.example.libritos.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "code", updatable = false, nullable = false)
    private UUID idReview;

    @ManyToOne
    @JoinColumn(name = "user_code", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_code", nullable = false)
    private Book book;

    @Column(name="rating")
    private Integer rating;

    @Column(name="comment")
    private String comment;

    public Review(User user, Book book, Integer rating, String comment) {
        super();
        this.user = user;
        this.book = book;
        this.rating = rating;
        this.comment = comment;
    }
}
