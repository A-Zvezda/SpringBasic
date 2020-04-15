package ru.geekbrains.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("from Product p where p.cost between :minPrice and :maxPrice")
    List<Product> findByPriceMinMax(@Param("minPrice") BigDecimal minPrice, @Param("maxPrice")BigDecimal maxPrice);

    @Query("from Product p where p.cost < :minPrice")
    List<Product> findByPriceMin(@Param("minPrice") BigDecimal minPrice);

    @Query("from Product p where p.cost > :maxPrice")
    List<Product> findByPriceMax(@Param("maxPrice") BigDecimal minPrice);


}
