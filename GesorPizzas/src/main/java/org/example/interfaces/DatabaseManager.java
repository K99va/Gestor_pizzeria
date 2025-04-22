package org.example.interfaces;

import org.example.modelos.*;

public interface DatabaseManager {
    void connect();
    void disconnect();
    void saveOrder(Order order);
    Order getOrder(String orderId);
    void updateOrder(Order order);
    void deleteOrder(String orderId);
}
