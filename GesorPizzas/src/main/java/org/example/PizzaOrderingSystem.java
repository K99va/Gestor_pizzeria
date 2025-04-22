package org.example;

import org.example.interfaces.*;
import org.example.modelos.*;

import java.util.List;

public class PizzaOrderingSystem {
    public static void main(String[] args) {
        // Obtener las dependencias inyectadas
        Authenticator authenticator = DependencyInjector.getAuthenticator();
        OrderManager orderManager = DependencyInjector.getOrderManager();

        // Simular autenticación
        String userId = "user123";
        if (authenticator.authenticate(userId, "password123")) {
            // Crear una pizza
            Pizza margherita = new Pizza("Margherita", 10.99,
                    List.of("tomato sauce", "mozzarella", "basil"));

            // Hacer un pedido
            Order order = orderManager.createOrder(userId, margherita);
            System.out.println("Pedido creado con ID: " + order.getId());
        } else {
            System.out.println("Autenticación fallida");
        }
    }
}