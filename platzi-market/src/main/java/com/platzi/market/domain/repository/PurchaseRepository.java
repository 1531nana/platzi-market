package com.platzi.market.domain.repository;

import com.platzi.market.domain.Purchase;
import java.util.List;
import java.util.Optional;

// Definida la especificación para purchase
public interface PurchaseRepository {
    List<Purchase> getAll();
    Optional<List<Purchase>> getByClient(String clientId);
    Purchase save(Purchase purchase);
}
