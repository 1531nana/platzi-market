package com.platzi.market.web.controller;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Indica que esta clase va a ser un controlador de una API rest
@RequestMapping("/products") // Path
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/all") // Exponer nuestro parh  - Obtener información con path
    @ApiOperation("Get all supermarket products") // Descripción de lo que hace nuestra API  # para swagger
    @ApiResponse(code = 200, message = "OK") // Respuesta que queremos que reciba el usuario # para swagger
    public ResponseEntity<List<Product>> getAll() {
                                    //Cuerpo de respuesta // Petición responde bien
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);

    }

    @GetMapping("/{id}") // ruta con llaves y anotación en params del nombre dentro de llaves
    @ApiOperation("Search a product with an ID")
    @ApiResponses({ // Como tiene dos posibles respuestas se considera cada una de estas
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Product not found")
    })
    // Api param = Agrega metadatos adicionales para los parámetros que recibe la petición.
    public ResponseEntity<Product> getProduct(@ApiParam(value = "The id of the product", required = true, example = "7") @PathVariable("id") int productId) {

        return productService.getProduct(productId)
                // Crear un responseEntity
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                // Si el producto no existe
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId) {
        return productService.getByCategory(categoryId)
                .map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save") // Anotación para guardar, los datos viajarán en el cuerpo de la petición, es necesario agregar anotación en parámetro
    public  ResponseEntity<Product> save(@RequestBody  Product product) {
        return  new ResponseEntity<>(productService.save(product), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}") // Eliminar un elemento con su id
    public  ResponseEntity delete(@PathVariable("id") int productId) {
        if (productService.delete(productId)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
