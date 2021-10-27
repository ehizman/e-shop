package com.ecommerce.shop.data.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    private Double price;

    @Column(length = 500)
    private String details;

    @ElementCollection
    @ToString.Exclude
    private List<String> imageUrl;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Feedback> feedbacks;
}
