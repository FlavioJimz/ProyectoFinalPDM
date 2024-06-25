package com.example.libritos.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "favorite")
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "code", updatable = false, nullable = false)
    private UUID idFavorite;

    @ManyToOne
    @JoinColumn(name = "user_code", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_code", nullable = false)
    private Book book;

    public Favorite(User user, Book book) {
        super();
        this.user = user;
        this.book = book;
    }
}
