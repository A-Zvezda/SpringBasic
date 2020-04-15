package ru.geekbrains.persist;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String title;
    @Column
    private BigDecimal cost;

    public Product(String title, BigDecimal cost) {
        this.title = title;
        this.cost = cost;
    }
    public Product() {
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
