package com.example.libritos.services.servicesImpl;

import com.example.libritos.models.DTOS.SaveFavoriteDTO;
import com.example.libritos.models.entities.Book;
import com.example.libritos.models.entities.Favorite;
import com.example.libritos.models.entities.User;
import com.example.libritos.repositories.BookRepository;
import com.example.libritos.repositories.FavoriteRepository;
import com.example.libritos.services.FavoriteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FavoriteServicesImpl implements FavoriteServices {
    @Autowired
    FavoriteRepository favoriteRepository;

    @Override
    public void save(SaveFavoriteDTO info) throws Exception {
        Favorite newFavorite = new Favorite(info.getUser(),info.getBook());

        favoriteRepository.save(newFavorite);
    }

    @Override
    public void deleteByCode(UUID code) throws Exception {

    }

    @Override
    public List<Favorite> getAllByUser() {
        return null;
    }

    @Override
    public List<Book> getAllByFavorite(User user) {
        List<Favorite> favorites = favoriteRepository.getFavoritesByUser(user);
        return favorites.stream()
                .map(Favorite::getBook)
                .collect(Collectors.toList());
    }
}
