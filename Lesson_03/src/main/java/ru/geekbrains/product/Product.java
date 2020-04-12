package ru.geekbrains.product;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 255)
    private String title;
    @Column
    private BigDecimal price;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL
    )
    @Transient
    private List<ClientsOrders> order;

    public List<ClientsOrders> getOrder() {
        return order;
    }

    public void setOrder(List<ClientsOrders> order) {
        this.order = order;
    }



    public Long getId() {
        return id;
    }

    public Product(String title, BigDecimal price) {
        this.title = title;
        this.price = price;
    }
    public Product() {

    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal cost) {
        this.price = cost;
    }

    public String printInfo () {
        return "id=" + id +
                ", title='" + title + '\'' +
                ", price='" + price ;
    }
}
