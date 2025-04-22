package org.example.modelos;

public class Order {
    private final String id;
    private final String userId;
    private final Pizza pizza;
    private OrderStatus status;

    public Order(String id, String userId, Pizza pizza) {
        this.id = id;
        this.userId = userId;
        this.pizza = pizza;
        this.status = OrderStatus.CREATED;
    }

    public String getId() {
        return id;
    }

    // Getters y setters...
}