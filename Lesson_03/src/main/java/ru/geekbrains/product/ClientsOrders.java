package ru.geekbrains.product;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class ClientsOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private BigDecimal quantity;

    @Column
    private BigDecimal cost;

    @Column
    private BigDecimal price;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Product product;

    @Column
    private LocalDate orderDate;

    public ClientsOrders(Client client, Product product, BigDecimal quantity,LocalDate orderDate) {
        this.quantity = quantity;
        this.cost = product.getPrice().multiply(this.quantity);
        this.price = product.getPrice();
        this.client = client;
        this.product = product;
        this.orderDate = orderDate;
    }

    public ClientsOrders() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String printInfo() {
        return "id=" + id +
                ", OrderDate='" + orderDate + '\'' +
                ", clientID='" + client.getId() + '\'' +
                ", client='" + client.getFullName() + '\'' +
                ", productID='" + product.getId() + '\'' +
                ", productTitle='" + product.getTitle() + '\'' +
                ", productPrice='" + product.getPrice() + '\'' +
                ", orderPrice='" + price + '\'' +
                ", orderQuantity='" + quantity + '\'' +
                ", orderCost=" + cost;

    }
}
