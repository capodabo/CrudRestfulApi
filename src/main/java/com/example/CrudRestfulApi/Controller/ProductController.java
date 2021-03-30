package com.example.CrudRestfulApi.Controller;

import com.example.CrudRestfulApi.entity.Product;
import com.example.CrudRestfulApi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/product")
    public void saveNewProduct(@RequestBody Product product) {
        product.setLastUpdated(Instant.now());
        productRepository.save(product);
        Long productId = product.getId();
        System.out.println("****************************************");
        System.out.println("Product added to database as product number " + productId + ".");
    }

    @PutMapping("/product/{productId}")
    public void saveOrUpdateProduct(@PathVariable Long productId, @RequestBody Product product) {
        if (productRepository.existsById(productId)) {
            product.setId(productId);
            product.setLastUpdated(Instant.now());
            productRepository.save(product);
            System.out.println("****************************************");
            System.out.println("Product number " + productId + " was updated.");
        }
        else {
            System.out.println("Product could not be updated because a product with the id " + productId + " does not exist.");
        }
    }

    @DeleteMapping("/product/{productId}")
    public void deleteProduct(@PathVariable Long productId, @RequestBody Product product) {
        if (productRepository.existsById(productId)) {
            product.setId(productId);
            product.setLastUpdated(Instant.now());
            productRepository.delete(product);
            System.out.println("****************************************");
            System.out.println("Product number " + productId + " was deleted.");
        }
        else {
            System.out.println("Product could not be deleted because a product with the id " + productId + " does not exist.");
        }
    }

    @GetMapping("/product")
    public List<Product> httpGet() {
        System.out.println("****************************************");
        System.out.println("Page has been refreshed. Product list is now updated.");
        return productRepository.findAll();
    }
}

/*
            Body
                {
                "id": null,
                "productName": <>,
                "productPrice": <>,
                "priceBeforePromotion": <>,
                "productUrl": <>,
                "lastUpdated": null
                 }
*/
