package ru.geekbrains.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.persist.Person;
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
    public void insertOrUpdate(Product product) {
        Optional<Product> foundedProduct = productRepository.findById(product.getId());
        if (foundedProduct.isPresent()) {
            productRepository.save(product);
        } else {
            productRepository.save(product);
        }
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
    public Page<Product> allProducts(Optional<BigDecimal> min, Optional<BigDecimal> max, Pageable pageable) {
        if (min.isPresent() && max.isPresent()) {
            return productRepository.findAllByCostBetween(min.get(), max.get(), pageable);
        }
        if (min.isPresent()) {
            return productRepository.findAllByCostLessThanEqual(min.get(), pageable);
        }
        if (max.isPresent()) {
            return productRepository.findAllByCostGreaterThanEqual(max.get(), pageable);
        }
        return productRepository.findAll(pageable);
    }

    @Transactional
    public Optional<Product> editOrAddProduct(Optional <Long> id) {
        if (id.isPresent()) {
            return productRepository.findById(id.get());
        } else {
            Product product = new Product();
            Optional<Product> opt = Optional.ofNullable(product);
            return opt;
        }
    }
}

