package ru.geekbrains;


import org.hibernate.cfg.Configuration;
import ru.geekbrains.persist.Person;
import ru.geekbrains.product.Client;
import ru.geekbrains.product.ClientsOrders;

import ru.geekbrains.product.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Store {

    public static void main(String[] args) {
        EntityManagerFactory factory = new Configuration()
                .configure("store.xml")
                .buildSessionFactory();

        EntityManager em = factory.createEntityManager();


        Client client = new Client("Ivan", "Ivanov", LocalDate.of(1995, 2, 12));
        Client client1 = new Client("Petr", "Petrov", LocalDate.of(1995, 2, 12));
        em.getTransaction().begin();

        try {
            em.persist(client);
            em.persist(client1);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
        Random random = new Random();
        Product product = new Product("p1",new BigDecimal(random.nextInt(100)));
        Product product1 = new Product("p2",new BigDecimal(random.nextInt(100)));
        em.getTransaction().begin();

        try {
            em.persist(product);
            em.persist(product1);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }

        ClientsOrders order= new ClientsOrders(client, product, new BigDecimal(2),LocalDate.now());
        ClientsOrders order1= new ClientsOrders(client1, product1, new BigDecimal(2),LocalDate.now());
        em.getTransaction().begin();

        try {
            em.persist(order);
            em.persist(order1);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }


        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        Scanner userInput = new Scanner(System.in);
        boolean exit = false;
        while (true) {
            if (exit) {
                break;
            }
            System.out.println("Урок 3.");
            System.out.println("1. Вывести всех клиентов;");
            System.out.println("2. Вывести все товары;");
            System.out.println("3. Вывести все заказы;");
            System.out.println("4. Удалить клиента;");
            System.out.println("5. Удалить товра;");
            System.out.println("6. Удалить заказ;");
            System.out.println("6. Удалить заказ;");
            System.out.println("7. Найти заказ по ID клиента и ID товара;");
            System.out.println("8. Выход.");
            System.out.print("Ввдеите номер операции: ");
            String userInputData = userInput.nextLine();
            switch (userInputData) {
                case "1":
                    List<Client> clients = em.createQuery("from Client").getResultList();
                    for (Client c : clients) {
                        System.out.println(c.printInfo());
                    }
                    break;
                case "2":
                    List<Product> products = em.createQuery("from Product").getResultList();
                    for (Product p: products) {
                        System.out.println(p.printInfo());
                    }
                    break;
                case "3":
                    List<ClientsOrders> clientsOrders = em.createQuery("from ClientsOrders ").getResultList();
                    for (ClientsOrders c: clientsOrders) {
                        System.out.println(c.printInfo());
                    }
                    break;
                case "4":
                    System.out.println("Введите id килента для уаления или exit для выхода;");
                    userInputData = userInput.nextLine();
                    switch (userInputData) {
                        case "exit":
                            break;
                        default:
                            long id = 0L;
                            id = parseUserInputInNumber(userInputData);
                            if (id != -1L) {
                                Client foundClient = em.find(Client.class, id);
                                if (foundClient != null) {
                                    em.getTransaction().begin();
                                 try {
                                    em.remove(foundClient);
                                    em.getTransaction().commit();
                                } catch (Exception ex) {
                                    em.getTransaction().rollback();
                                }
                                } else {
                                    System.out.println("Введены не верные данные, выход");
                                }
                            } else {
                                System.out.println("Введены не верные данные, выход");
                            }
                    }
                    break;
                case "5":
                    System.out.println("Введите id товара для уаления или exit для выхода;");
                    userInputData = userInput.nextLine();
                    switch (userInputData) {
                        case "exit":
                            break;
                        default:
                            long id = 0L;
                            id = parseUserInputInNumber(userInputData);
                            if (id != -1L) {
                                Product foundProduct = em.find(Product.class, id);
                                if (foundProduct != null) {
                                    em.getTransaction().begin();
                                    try {
                                        em.remove(foundProduct);
                                        em.getTransaction().commit();
                                    } catch (Exception ex) {
                                        em.getTransaction().rollback();
                                    }
                                } else {
                                    System.out.println("Введены не верные данные, выход");
                                }
                            } else {
                                System.out.println("Введены не верные данные, выход");
                            }
                    }
                    break;
                case "6":
                    System.out.println("Введите id заказа для уаления или exit для выхода;");
                    userInputData = userInput.nextLine();
                    switch (userInputData) {
                        case "exit":
                            break;
                        default:
                            long id = 0L;
                            id = parseUserInputInNumber(userInputData);
                            if (id != -1L) {
                                ClientsOrders foundClientsOrder = em.find(ClientsOrders.class, id);
                                if (foundClientsOrder != null) {
                                    em.getTransaction().begin();
                                    try {
                                        em.remove(foundClientsOrder);
                                        em.getTransaction().commit();
                                    } catch (Exception ex) {
                                        em.getTransaction().rollback();
                                    }
                                } else {
                                    System.out.println("Введены не верные данные, выход");
                                }
                            } else {
                                System.out.println("Введены не верные данные, выход");
                            }
                    }
                    break;
                case "7":
                    System.out.println("Введите id клиента;");
                    String userId = userInput.nextLine();
                    System.out.println("Введите id товара;");
                    String productId = userInput.nextLine();
                    long clientIdLong = parseUserInputInNumber(userId);
                    long orderIdLong = parseUserInputInNumber(productId);
                    if (clientIdLong != -1L & orderIdLong != -1L ) {
                        Query query2 = em.createQuery("from ClientsOrders s where client.id = :clientId and product.id =:productId");
                        query2.setParameter("clientId", clientIdLong);
                        query2.setParameter("productId", orderIdLong);
                        List<ClientsOrders>  clientsOrdersList =  query2.getResultList();
                        if (clientsOrdersList != null) {
                            for (ClientsOrders c:clientsOrdersList) {
                                System.out.println(c.printInfo());
                            }
                        }
                    } else {
                        System.out.println("Введены не верные данные, выход");
                    }
                    break;
                case "8":
                    exit = true;
                    break;
                default:
                    System.err.println("Данная операция не предусмотренна!");
            }
        }
        userInput.close();
        em.close();
    }

    private static long parseUserInputInNumber (String userInputValue) {

        long userInputDoubleValue = 0;
        try {
            userInputDoubleValue = Long.parseLong(userInputValue);
        } catch (NumberFormatException catchedException) {
            System.err.println("Введенное значение не является числом!");
            System.err.println(catchedException);
            return -1L;
        }
        return userInputDoubleValue;
    }

}
