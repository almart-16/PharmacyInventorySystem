package jdbc;

import java.sql.*;
import java.util.*;

import interfaces.OrdersManager;
import pojos.Order;

public class JDBCOrdersManager implements OrdersManager {

    private Connection connection;

    /**
     * Constructs a new JDBCOrdersManager with the given database connection.
     *
     * @param connection the database connection
     */
    public JDBCOrdersManager(Connection connection) {
        this.connection = connection;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean createOrder(Order o) {
        String sql = "INSERT INTO Orders VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, o.getId());
            stmt.setString(2, o.getPharmacyId());
            stmt.setString(3, o.getSupplierId());
            stmt.setString(4, o.getMedicationId());
            stmt.setString(5, o.getDate());
            stmt.setInt(6, o.getQuantity());
            stmt.setString(7, o.getStatus());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateOrderStatus(String id, String status) {
        String sql = "UPDATE Orders SET status = ? WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setString(2, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteOrder(String id) {
        String sql = "DELETE FROM Orders WHERE id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Order findOrderById(String id) { return null; }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Order> getAllOrders() { return new ArrayList<>(); }
}
