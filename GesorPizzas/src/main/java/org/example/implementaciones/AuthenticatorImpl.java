package org.example.implementaciones;
import org.example.interfaces.Authenticator;

public class AuthenticatorImpl implements Authenticator {
    @Override
    public boolean authenticate(String username, String password) {
        System.out.println("Autenticando usuario: " + username);
        // Lógica de autenticación real
        return true;
    }

    @Override
    public boolean hasPermission(String username, String permission) {
        System.out.println("Verificando permiso " + permission + " para " + username);
        // Lógica de verificación de permisos
        return true;
    }
}
