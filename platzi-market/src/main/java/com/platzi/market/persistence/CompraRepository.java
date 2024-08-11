package com.platzi.market.persistence;

import com.platzi.market.domain.Purchase;
import com.platzi.market.domain.repository.PurchaseRepository;
import com.platzi.market.persistence.crud.CompraCrudRepository;
import com.platzi.market.persistence.entity.Compra;
import com.platzi.market.persistence.mapper.PurchaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CompraRepository implements PurchaseRepository {

    @Autowired //inyección de dependencias
    private CompraCrudRepository compraCrudRepository;

    @Autowired
    private PurchaseMapper mapper;

    @Override
    public List<Purchase> getAll() {
                            // Castear, cambiar el tipo de dato que devuelve método
        return mapper.toPurchases((List<Compra>) compraCrudRepository.findAll());
    }

    @Override
    public Optional<List<Purchase>> getByClient(String clientId) {
        return compraCrudRepository.findByIdCliente(clientId)
                // Map = permite operar con lo que venga en ese cliente
                .map(compras -> mapper.toPurchases(compras));
    }

    @Override
    public Purchase save(Purchase purchase) {
        Compra compra = mapper.toCompra(purchase);
        // Guardar info en cascada
        compra.getComprasProductos().forEach(producto -> producto.setCompra(compra));

        return mapper.toPurchase(compraCrudRepository.save(compra));
    }
}
