package com.code9.beershop.repository;

import com.code9.beershop.model.BeerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BeerOrderRepository extends JpaRepository<BeerOrder, Long> {

    List<BeerOrder> findByZip(String zip);

    List<BeerOrder> findBeerOrdersByOrderByPlacedAtDesc();

    List<BeerOrder> findBeerOrdersByPlacedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

}
