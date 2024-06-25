package com.example.libritos.services;

import com.example.libritos.models.DTOS.SavePurchaseDTO;
import com.example.libritos.models.entities.Book;
import com.example.libritos.models.entities.User;

import java.util.List;

public interface PurchaseServices {
    void save(SavePurchaseDTO info) throws Exception;
    List<Book> getAllbyUser(User user);
}
