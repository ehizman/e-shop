package com.ecommerce.shop.data.dto;

import com.ecommerce.shop.data.model.Currency;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String details;
    private Double price;
    private Currency currency;

}
