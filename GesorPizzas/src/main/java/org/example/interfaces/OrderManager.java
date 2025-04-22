package org.example.interfaces;

import org.example.modelos.*;

public interface OrderManager {
    Order createOrder(String userId, Pizza pizza);
    void updateOrder(Order order);
    void cancelOrder(String orderId);
    Order getOrder(String orderId);
}
