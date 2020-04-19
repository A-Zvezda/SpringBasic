package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void insert(Product product) {
        productRepository.save(product);
    }

    @Transactional
    public void update(Product product) {
        productRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Product> findByPriceMinMax(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findByPriceMinMax(minPrice,maxPrice);
    }
    @Transactional(readOnly = true)
    public List<Product> findByPriceMin(BigDecimal minPrice) {
        return productRepository.findByPriceMin(minPrice);
    }
    @Transactional(readOnly = true)
    public List<Product> findByPriceMax(BigDecimal maxPrice) {
        return productRepository.findByPriceMax(maxPrice);
    }


//    public List<Person> findAllWithPagination() {
//       return personRepository.findAllWithPagination(PageRequest.of(1, 5));
//    }

}
