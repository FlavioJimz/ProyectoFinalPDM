package com.example.libritos.repositories;

import com.example.libritos.models.entities.Favorite;
import com.example.libritos.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FavoriteRepository extends JpaRepository<Favorite, UUID> {
    List<Favorite> getFavoritesByUser(User user);
}
