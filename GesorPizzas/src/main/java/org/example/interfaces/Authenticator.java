package org.example.interfaces;
import org.example.modelos.*;

public interface Authenticator {
    boolean authenticate(String username, String password);
    boolean hasPermission(String username, String permission);
}

