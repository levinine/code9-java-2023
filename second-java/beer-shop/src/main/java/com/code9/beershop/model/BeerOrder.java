package com.code9.beershop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "beer_order")
public class BeerOrder {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime placedAt;

    private String name;

    private String street;

    private String city;

    private String zip;

    private String creditCardNumber;

    private String creditCardExpiration;

    private String creditCardSecurityCode;

    @ManyToMany
    private List<Beer> beers;

}
