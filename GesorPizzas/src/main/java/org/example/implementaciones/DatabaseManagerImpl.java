package org.example.implementaciones;

import org.example.interfaces.DatabaseManager;
import org.example.modelos.*;

public class  DatabaseManagerImpl implements DatabaseManager {
    @Override
    public void connect() {
        System.out.println("Conectando a la base de datos...");
        // Implementación real de conexión a BD
    }

    @Override
    public void disconnect() {
        System.out.println("Desconectando de la base de datos...");
        // Implementación real
    }

    @Override
    public void saveOrder(Order order) {
        System.out.println("Guardando pedido en BD: " + order.getId());
        // Implementación real
    }

    @Override
    public void deleteOrder(String orderId) {

    }

    public Order getOrder(String orderId) {
        return null;
    }

    @Override
    public void updateOrder(Order order) {

    }





    // Resto de implementaciones...
}