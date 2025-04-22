package org.example;

import org.example.implementaciones.*;
import org.example.interfaces.*;

public class DependencyInjector {
    public static DatabaseManager getDatabaseManager() {
        return new DatabaseManagerImpl();
    }

    public static Authenticator getAuthenticator() {
        return new AuthenticatorImpl();
    }

    public static PaymentProcessor getPaymentProcessor() {
        return new PaymentProcessorImpl();
    }

    public static OrderManager getOrderManager() {
        return new OrderManagerImpl(getDatabaseManager(), getPaymentProcessor());
    }
}