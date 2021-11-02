package com.ecommerce.shop.service;

import com.ecommerce.shop.data.dto.ProductDto;
import com.ecommerce.shop.data.model.Product;
import com.ecommerce.shop.data.repository.ProductRepository;
import com.ecommerce.shop.service.exceptions.ProductDoesNotExistException;
import com.ecommerce.shop.service.mapper.ProductMapper;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@NoArgsConstructor
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    private ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(Long id) throws ProductDoesNotExistException {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            throw new ProductDoesNotExistException(String.format("Product with %d does not exist", id));
        }
        return product;
    }

    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void updateProduct(Long id, ProductDto productForm) throws ProductDoesNotExistException {
        if (productForm == null) {
            throw new NullPointerException("Product Dto cannot be null");
        }
        Product product = productRepository.findById(id).orElse(null);
        log.info("product -> {}", product);
        if (product != null) {
            productMapper.mapDtoToProduct(productForm, product);
            product.setName(productForm.getName());
            product.setDetails(productForm.getDetails());
            log.info("prod-->{}", product);
            productRepository.save(product);
        } else {
            throw new NullPointerException();
        }
    }

    @Override
    public void addAll(List<Product> productList) {
        productRepository.saveAll(productList);
    }
}
