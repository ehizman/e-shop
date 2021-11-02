package com.ecommerce.shop.service;

import com.ecommerce.shop.data.dto.ProductDto;
import com.ecommerce.shop.data.model.Currency;
import com.ecommerce.shop.data.model.Product;
import com.ecommerce.shop.data.repository.ProductRepository;
import com.ecommerce.shop.service.exceptions.ProductDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@Slf4j
@SpringBootTest
public class ProductServiceImplTestConcrete {
    ProductService productService;

    @Autowired
    public ProductServiceImplTestConcrete(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void testProductUpdate() {
        Product dummyProduct = new Product();
        dummyProduct.setId(110L);
        dummyProduct.setName("Dummy Product");

        ProductDto productForm = new ProductDto();
        productForm.setId(110L);
        productForm.setName("Mexican Chair By Ehizman");
        productForm.setDetails("designed and produced by ehizman");
        productForm.setCurrency(Currency.NGN);

        try {
            productService.updateProduct(110L, productForm);
            Product product = productService.findProductById(110L);
            assertThat(product.getName()).isEqualTo("Mexican Chair By Ehizman");
            assertThat(product.getDetails()).isEqualTo("designed and produced by ehizman");
            assertThat(product.getCurrency()).isEqualTo(Currency.NGN);
        } catch (ProductDoesNotExistException ex) {
            log.info("Exception occurred --> {}", ex.getMessage());
        }
    }
}
