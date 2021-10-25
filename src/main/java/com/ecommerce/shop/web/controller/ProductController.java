package com.ecommerce.shop.web.controller;

import com.ecommerce.shop.data.model.Product;
import com.ecommerce.shop.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/")
    public List<Product> findAll(){
        return productService.findAll();
    }

    @PostMapping("/")
    public Product save(@RequestBody Product product){
      log.info("Product request -> {}", product);
      return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        productService.deleteProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
