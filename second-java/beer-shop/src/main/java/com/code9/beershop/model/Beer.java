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
@Table(name = "beer")
public class Beer {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime createdAt;

    private String name;

    @ManyToMany
    private List<Ingredient> ingredients;

}
