package com.ecommerce.shop.service.exceptions;

public class ShopApplicationException extends Exception {
    public ShopApplicationException(String message) {
        super(message);
    }

    public ShopApplicationException(String message, Exception ex) {
        super(message, ex);
    }
}
