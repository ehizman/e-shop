package com.ecommerce.shop.service;

import com.ecommerce.shop.data.dto.ProductDto;
import com.ecommerce.shop.data.model.Product;
import com.ecommerce.shop.data.repository.ProductRepository;
import com.ecommerce.shop.service.exceptions.ProductDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.jdbc.Sql;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@Sql(scripts = {"/db/insert.sql"})
@Slf4j
class

ProductServiceImplTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService productServiceImpl;

    @BeforeEach
    void setUp() {
        productServiceImpl = new ProductServiceImpl();
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveProductMockTest() {
        Product product = new Product();
        //when
        when(productRepository.save(new Product())).thenReturn(product);
        productServiceImpl.save(product);
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void findProductByIdTest() {
        //given
        Product dummyProduct = new Product();
        dummyProduct.setId(120L);
        dummyProduct.setName("Dummy product");
        //when
        when(productRepository.findById(120L)).thenReturn(Optional.of(dummyProduct));
        try {
            Product product = productServiceImpl.findProductById(120L);
            verify(productRepository, times(1)).findById(120L);
            assertThat(product.getId()).isEqualTo(120L);
            assertThat(product.getName()).isEqualTo("Dummy product");
            assertThat(product).isNotNull();
        } catch (ProductDoesNotExistException ex) {
            log.info("exception occurred -> {}", ex.getMessage());
        }
    }
}
