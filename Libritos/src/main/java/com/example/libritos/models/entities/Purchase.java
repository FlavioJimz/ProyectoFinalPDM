package com.example.libritos.models.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "code", updatable = false, nullable = false)
    private UUID idPurchase;

    @ManyToOne
    @JoinColumn(name = "user_code", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_code", nullable = false)
    private Book book;

    @Column(name="purchasedate", insertable = false, updatable = false)
    private String purchaseDate;

    public Purchase(User user, Book book) {
        super();
        this.user = user;
        this.book = book;
    }
}
