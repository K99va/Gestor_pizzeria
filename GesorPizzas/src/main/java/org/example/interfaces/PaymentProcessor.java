package org.example.interfaces;
import org.example.modelos.*;

public interface PaymentProcessor {
    boolean processPayment(String userId, double amount);
    boolean refundPayment(String transactionId);
}