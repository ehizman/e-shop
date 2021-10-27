package com.ecommerce.shop.service;

import com.ecommerce.shop.data.dto.ProductDto;
import com.ecommerce.shop.data.model.Product;
import com.ecommerce.shop.service.exceptions.ProductDoesNotExistException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product save(Product product);
    List<Product> findAll();
    Product findProductById(Long id) throws ProductDoesNotExistException;
    void deleteProductById(Long id);

    void updateProduct(Long id, ProductDto productForm) throws ProductDoesNotExistException;

    void addAll(List<Product> productList);
}
