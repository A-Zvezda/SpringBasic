package ru.geekbrains.persist;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAllByCostBetween(BigDecimal min, BigDecimal max, Pageable pageable);

    Page<Product> findAllByCostGreaterThanEqual(BigDecimal min, Pageable pageable);

    Page<Product> findAllByCostLessThanEqual(BigDecimal max, Pageable pageable);
}
