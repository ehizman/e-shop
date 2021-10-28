package com.ecommerce.shop.service.exceptions;

public class ProductDoesNotExistException extends ShopApplicationException {
    public ProductDoesNotExistException(String message) {
        super(message);
    }
}
