package com.example.libritos.services;

import com.example.libritos.models.DTOS.SaveFavoriteDTO;
import com.example.libritos.models.entities.Book;
import com.example.libritos.models.entities.Favorite;
import com.example.libritos.models.entities.User;

import java.util.List;
import java.util.UUID;

public interface FavoriteServices {
    void save(SaveFavoriteDTO info) throws Exception;
    void deleteByCode(UUID code) throws Exception;
    List<Favorite> getAllByUser();
    List<Book> getAllByFavorite(User user);
}
