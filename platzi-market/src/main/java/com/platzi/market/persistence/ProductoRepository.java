package com.platzi.market.persistence;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.persistence.crud.ProductoCrudRepository;
import com.platzi.market.persistence.entity.Producto;
import com.platzi.market.persistence.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// Está interactuando directamente con la BD, agregar decorador
// Orientar el repositorio al del dominio, evitar acoplamiento de BD
@Repository
public class ProductoRepository implements ProductRepository {
    @Autowired // Anotación = permite que Spring se encarge de crear esas instancias, estar seguro que es un componente de spring
    private ProductoCrudRepository productoCrudRepository;
    @Autowired
    private ProductMapper mapper;

    public List<Product> getAll() {
        List<Producto> productos =  (List<Producto>) productoCrudRepository.findAll();
        return mapper.toProducts(productos);
    }

    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        // Convertir de lista a Optional, convertir lista de productos a lista de products
        return Optional.of(mapper.toProducts(productos));
    }

    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        // 1° recuperamos de la BD
        Optional<List<Producto>> productos = productoCrudRepository.findByCantidadStockLessThanAndEstado(quantity, true);

        return productos.map(prods -> mapper.toProducts(prods));
    }

    @Override
    public Optional<Product> getProduct(int productId) {
        return productoCrudRepository.findById(productId).map(producto -> mapper.toProduct(producto));
    }

    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct(productoCrudRepository.save(producto));
    }


    @Override
    public void delete(int productId){
        productoCrudRepository.deleteById(productId);
    }
}
