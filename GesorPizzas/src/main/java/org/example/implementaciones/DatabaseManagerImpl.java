package org.example.implementaciones;

import org.example.DatabaseConfig;
import org.example.interfaces.*;
import org.example.modelos.Order;
import org.example.modelos.Pizza;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManagerImpl implements DatabaseManager {

    @Override
    public void connect() {
        // La conexión se manejará en cada método individualmente
    }

    @Override
    public void disconnect() {
        // Las conexiones se cierran en cada método después de usarlas
    }

    @Override
    public void saveOrder(Order order) {
        String sql = "INSERT INTO Pedido (idPedido, idUsuario, precioTotal, direccionEntrega) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, order.getId());
            stmt.setInt(2, Integer.parseInt(order.getUserId()));
            stmt.setDouble(3, order.getPizza().getPrice());
            stmt.setString(4, "Dirección por defecto");

            stmt.executeUpdate();

            saveOrderDetails(conn, order);

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el pedido", e);
        }
    }

    private void saveOrderDetails(Connection conn, Order order) throws SQLException {
        String sql = "INSERT INTO DetallePedido (idPedido, idPizza, cantidad, precioUnitario) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, order.getId());
            stmt.setInt(2, getPizzaId(conn, order.getPizza().getType()));
            stmt.setInt(3, 1);
            stmt.setDouble(4, order.getPizza().getPrice());

            stmt.executeUpdate();
        }
    }

    private int getPizzaId(Connection conn, String pizzaType) throws SQLException {
        String sql = "SELECT idPizza FROM Pizza WHERE nombre = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pizzaType);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("idPizza");
            } else {
                throw new RuntimeException("Tipo de pizza no encontrado: " + pizzaType);
            }
        }
    }

    @Override
    public Order getOrder(String orderId) {
        String sql = "SELECT p.*, u.nombre as usuario FROM Pedido p " +
                "JOIN Usuario u ON p.idUsuario = u.idUsuario " +
                "WHERE p.idPedido = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Pizza pizza = getPizzaFromOrder(conn, orderId);

                return new Order(
                        rs.getString("idPedido"),
                        rs.getString("usuario"),
                        pizza
                );
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener el pedido", e);
        }
    }

    private Pizza getPizzaFromOrder(Connection conn, String orderId) throws SQLException {
        String sql = "SELECT dp.idPizza, p.nombre, p.precio " +
                "FROM DetallePedido dp " +
                "JOIN Pizza p ON dp.idPizza = p.idPizza " +
                "WHERE dp.idPedido = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, orderId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Pizza(
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        getPizzaIngredients(conn, rs.getInt("idPizza"))
                );
            }
            throw new RuntimeException("No se encontraron pizzas para el pedido: " + orderId);
        }
    }

    private List<String> getPizzaIngredients(Connection conn, int pizzaId) throws SQLException {
        List<String> ingredients = new ArrayList<>();
        String sql = "SELECT i.nombre FROM PizzaIngrediente pi " +
                "JOIN Ingrediente i ON pi.idIngrediente = i.idIngrediente " +
                "WHERE pi.idPizza = ?";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pizzaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ingredients.add(rs.getString("nombre"));
            }
        }
        return ingredients;
    }

    @Override
    public void updateOrder(Order order) {
        String sql = "UPDATE Pedido SET precioTotal = ?, direccionEntrega = ? WHERE idPedido = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, order.getPizza().getPrice());
            stmt.setString(2, "Nueva dirección"); // Debería venir del objeto order
            stmt.setString(3, order.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el pedido", e);
        }
    }

    @Override
    public void deleteOrder(String orderId) {
        String sql = "DELETE FROM Pedido WHERE idPedido = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, orderId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el pedido", e);
        }
    }
}