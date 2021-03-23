package com.example.CrudRestfulApi.CrudApplication;

import com.example.CrudRestfulApi.entity.Product;
import com.example.CrudRestfulApi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/product")
    public void httpPost(@RequestBody Product product) {

        productRepository.save(product);
        Long productId = product.getId();
        System.out.println("****************************************");
        System.out.println("Product added to database as product number " + productId + ".");
    }

    @PutMapping("/product/{productId}")

    public void httpPut(@PathVariable Long productId, @RequestBody Product product) {
        product.setId(productId);
        productRepository.save(product);
        System.out.println("****************************************");
        System.out.println("Product number " + productId + " was updated.");
    }

    @DeleteMapping("/product/{productId}")
    public void httpDelete(@PathVariable Long productId, @RequestBody Product product) {
        product.setId(productId);
        productRepository.delete(product);
        System.out.println("****************************************");
        System.out.println("Product number " + productId + " was deleted.");
    }

    @GetMapping("/product")
    public List<Product> httpGet() {
        System.out.println("****************************************");
        System.out.println("Page has been refreshed. Product list is now updated.");
        return productRepository.findAll();
    }

}

/*
        POSTMAN commands
            Pre-request script
                const dateNow= new Date();
                pm.environment.set('currentDate', dateNow.toISOString());
            Body
                {
                "id": null,
                "productName": <>,
                "productPrice": <>,
                "priceBeforePromotion": <>,
                "productUrl": <>,
                "lastUpdated": "{{currentDate}}"
                 }
*/
