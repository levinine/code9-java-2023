package com.levi9.code9.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    boolean existsByNameAndQuantityGreaterThan(String name, int quantity);

    List<Product> findByQuantityGreaterThan(int quantity);

    void deleteById(Long id);

}
