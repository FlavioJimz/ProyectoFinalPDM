package com.example.libritos.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "code")
    private UUID IdToken;

    @Column(name = "token")
    private String token;

    @Column(name = "creationdate",insertable = false, updatable = false)
    private Date creationDate;

    @Column(name = "active", insertable = false)
    private Boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_code")
    @JsonIgnore
    private User user;

    public Token(String content, User user) {
        super();
        this.token = content;
        this.user = user;
    }
}
