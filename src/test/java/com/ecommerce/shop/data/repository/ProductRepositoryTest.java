package com.ecommerce.shop.data.repository;

import com.ecommerce.shop.data.model.Currency;
import com.ecommerce.shop.data.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Sql(scripts = {"/db/insert.sql"})
class ProductRepositoryTest {
    private ProductRepository productRepositoryImpl;

    @BeforeEach
    void setUp() {

    }

    @Autowired
    ProductRepositoryTest(ProductRepository productRepositoryImpl) {
        this.productRepositoryImpl = productRepositoryImpl;
    }

    @Test
    public void createProductTest() {
        Product product = new Product();
        product.setName("Luxury Sofa");
        product.setPrice(400D);
        product.setCurrency(Currency.NGN);
        product.setDetails("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque sapien velit, aliquet eget commodo nec, auctor a sapien. Nam eu neque vulputate diam rhoncus faucibus. Curabitur quis varius libero. Lorem.");

        assertThat(product).isNotNull();
        assertThat(product.getId()).isNull();
        log.info("Product before saving -> {}", product);
        productRepositoryImpl.save(product);
        assertThat(product.getId()).isNotNull();
        log.info("Product after saving -> {}", product);
    }

    @Test
    public void whenFindAllProductIsCalledThenProductListIsReturnedTest() {
        List<Product> products = productRepositoryImpl.findAll();
        assertThat(products).hasSize(4);
        log.info("Product list after find all -> {}", products);
    }

    @Test
    public void deleteExistingProductById() {
        assertThat(productRepositoryImpl.findById(110L).orElse(null)).isNotNull();
        productRepositoryImpl.deleteById(110L);
        assertThat(productRepositoryImpl.findById(110L).orElse(null)).isNull();
    }

    @Test
    public void findExistingProductById() {
        Product existingProduct = productRepositoryImpl.findById(110L).orElse(null);
        assertThat(existingProduct).isNotNull();
        log.info("existing product -> {}", existingProduct);
    }

    @Test
    public void test_updateProduct() {
        Product product = productRepositoryImpl.findById(110L).orElse(null);
        assertThat(product).isNotNull();
        log.info("product -> {}", product);

        product.setDetails("New Details set");
        productRepositoryImpl.save(product);
        log.info("product with details set -> {}", product);
        assertThat(product.getDetails()).isEqualTo("New Details set");
    }
}