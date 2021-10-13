package com.ecommerce.shop.data.model;

import javax.persistence.*;

@Entity
public class NewsLetter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String subscriberEmail;
}
