package com.example.libritos.repositories;

import com.example.libritos.models.entities.Purchase;
import com.example.libritos.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {
    List<Purchase> getPurchasesByUser(User user);
}
