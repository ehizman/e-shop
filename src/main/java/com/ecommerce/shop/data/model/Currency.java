package com.ecommerce.shop.data.model;

public enum Currency {
    NGN("Naira"),
    USD("Dollar"),
    SRC("Singapore Dollar"),
    GBP("British Pound"),
    FRC("Francs"),
    GHC("Ghana Cedis");

    private String name;

    Currency(String s) {
        this.name = s;
    }

    public String getName(){
        return this.name;
    }
}
