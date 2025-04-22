package org.example.implementaciones;
import org.example.interfaces.DatabaseManager;
import org.example.interfaces.OrderManager;
import org.example.interfaces.PaymentProcessor;
import org.example.modelos.*;
import java.util.UUID;

public class OrderManagerImpl implements OrderManager {
    private final DatabaseManager dbManager;
    private final PaymentProcessor paymentProcessor;

    public OrderManagerImpl(DatabaseManager dbManager, PaymentProcessor paymentProcessor) {
        this.dbManager = dbManager;
        this.paymentProcessor = paymentProcessor;
    }

    @Override
    public Order createOrder(String userId, Pizza pizza) {
        System.out.println("Creando nuevo pedido para usuario: " + userId);
        Order order = new Order(UUID.randomUUID().toString(), userId, pizza);
        dbManager.saveOrder(order);

        if (paymentProcessor.processPayment(userId, pizza.getPrice())) {
            return order;
        }
        throw new RuntimeException("Pago fallido");
    }

    @Override
    public void updateOrder(Order order) {

    }

    @Override
    public void cancelOrder(String orderId) {

    }

    @Override
    public Order getOrder(String orderId) {
        return null;
    }

    // Resto de implementaciones...
}
