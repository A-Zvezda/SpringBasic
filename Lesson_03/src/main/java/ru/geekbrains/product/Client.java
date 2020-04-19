package ru.geekbrains.product;

import ru.geekbrains.persist.Contact;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String firstName;

    @Column(length = 255)
    private String lastName;

    @Column
    private LocalDate birthday;

    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.ALL
    )
    @Transient
    private List<ClientsOrders> order;

    public Client() {
    }

    public Client(String firstName, String lastName, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        // this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public List<ClientsOrders> getOrder() {
        return order;
    }

    public void setOrder(List<ClientsOrders> order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", orders=" + order +
                '}';
    }

    public String printInfo() {
        return "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
