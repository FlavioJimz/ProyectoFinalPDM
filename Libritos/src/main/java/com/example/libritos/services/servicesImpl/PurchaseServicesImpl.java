package com.example.libritos.services.servicesImpl;

import com.example.libritos.models.DTOS.SavePurchaseDTO;
import com.example.libritos.models.entities.Book;
import com.example.libritos.models.entities.Favorite;
import com.example.libritos.models.entities.Purchase;
import com.example.libritos.models.entities.User;
import com.example.libritos.repositories.PurchaseRepository;
import com.example.libritos.services.PurchaseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseServicesImpl implements PurchaseServices {

    @Autowired
    PurchaseRepository purchaseRepository;


    @Override
    public void save(SavePurchaseDTO info) throws Exception {
        Purchase newPurchase = new Purchase(info.getUser(), info.getBook());

        purchaseRepository.save(newPurchase);
    }

    @Override
    public List<Book> getAllbyUser(User user) {
        List<Purchase> purchases = purchaseRepository.getPurchasesByUser(user);
        return purchases.stream()
                .map(Purchase::getBook)
                .collect(Collectors.toList());
    }
}
