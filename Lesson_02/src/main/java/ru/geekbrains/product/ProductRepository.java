package ru.geekbrains.product;

import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ProductRepository {

    private final AtomicInteger identity = new AtomicInteger(4);

    private final List<Product> products = new ArrayList<>();

    public ProductRepository() {
        insert(new Product(1,"Good1", new BigDecimal("200")));
        insert(new Product(2,"Good2", new BigDecimal("300")));
        insert(new Product(3,"Good3", new BigDecimal("400")));
    }

    public void insert(Product product) {

        product.setId(identity.incrementAndGet());
        products.add(product);
    }

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    public List<Product> findByID(int id) {
        List<Product> foundProducts = new ArrayList<>();
        for (Product product: products) {
            if (product.getId() == id) {
                foundProducts.add(product);
            }
        }
        return Collections.unmodifiableList(foundProducts);
    }


}
