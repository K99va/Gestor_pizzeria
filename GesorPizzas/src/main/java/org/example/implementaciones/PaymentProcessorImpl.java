package org.example.implementaciones;

import org.example.interfaces.PaymentProcessor;

public class PaymentProcessorImpl implements PaymentProcessor {
    @Override
    public boolean processPayment(String userId, double amount) {
        System.out.println("Procesando pago de $" + amount + " para usuario: " + userId);
        // Lógica real de procesamiento de pago
        return true;
    }

    @Override
    public boolean refundPayment(String transactionId) {
        System.out.println("Reembolsando transacción: " + transactionId);
        // Lógica real de reembolso
        return true;
    }
}