package jdbc;

import java.sql.*;
import java.util.*;

import interfaces.InventoryManager;
import pojos.Inventory;

public class JDBCInventoryManager implements InventoryManager {

    private Connection connection;
    
    public JDBCInventoryManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Inventory findInventory(String pharmacyId, String medicationId) {
        String sql = "SELECT * FROM Inventory WHERE pharmacy_id = ? AND medication_id = ?";

        try {
            PreparedStatement stmt = cm.prepareStatement(sql);
            stmt.setString(1, pharmacyId);
            stmt.setString(2, medicationId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Inventory(
                    rs.getString("id"),
                    rs.getString("pharmacy_id"),
                    rs.getString("medication_id"),
                    rs.getString("supplier_id"),
                    rs.getInt("stock_quantity"),
                    rs.getDouble("price"),
                    rs.getString("expiration_date"),
                    rs.getInt("minimum_stock")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean increaseStock(String pharmacyId, String medicationId, int quantity) {
        String sql = "UPDATE Inventory SET stock_quantity = stock_quantity + ? WHERE pharmacy_id = ? AND medication_id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, quantity);
            stmt.setString(2, pharmacyId);
            stmt.setString(3, medicationId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean reduceStock(String pharmacyId, String medicationId, int quantity) {
        String sql = "UPDATE Inventory SET stock_quantity = stock_quantity - ? WHERE pharmacy_id = ? AND medication_id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, quantity);
            stmt.setString(2, pharmacyId);
            stmt.setString(3, medicationId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean addInventory(Inventory i) {
        String sql = "INSERT INTO Inventory (id, pharmacy_id, medication_id, supplier_id, stock_quantity, price, expiration_date, minimum_stock) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, i.getId());
            stmt.setString(2, i.getPharmacyId());
            stmt.setString(3, i.getMedicationId());
            stmt.setString(4, i.getSupplierId());
            stmt.setInt(5, i.getStockQuantity());
            stmt.setDouble(6, i.getPrice());
            stmt.setString(7, i.getExpirationDate());
            stmt.setInt(8, i.getMinimumStock());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateInventoryInformation(String pharmacyId, String medicationId,
                                              double price, String expirationDate, String supplierId) {

        String sql = "UPDATE Inventory SET price = ?, expiration_date = ?, supplier_id = ? WHERE pharmacy_id = ? AND medication_id = ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setDouble(1, price);
            stmt.setString(2, expirationDate);
            stmt.setString(3, supplierId);
            stmt.setString(4, pharmacyId);
            stmt.setString(5, medicationId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<Inventory> getLowStockMedications() {
        List<Inventory> list = new ArrayList<>();
        String sql = "SELECT * FROM Inventory WHERE stock_quantity <= minimum_stock";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                list.add(new Inventory(
                    rs.getString("id"),
                    rs.getString("pharmacy_id"),
                    rs.getString("medication_id"),
                    rs.getString("supplier_id"),
                    rs.getInt("stock_quantity"),
                    rs.getDouble("price"),
                    rs.getString("expiration_date"),
                    rs.getInt("minimum_stock")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean isLowStock(String pharmacyId, String medicationId) {
        String sql = "SELECT * FROM Inventory WHERE pharmacy_id = ? AND medication_id = ? AND stock_quantity <= minimum_stock";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pharmacyId);
            stmt.setString(2, medicationId);

            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean isOutOfStock(String pharmacyId, String medicationId) {
        String sql = "SELECT * FROM Inventory WHERE pharmacy_id = ? AND medication_id = ? AND stock_quantity = 0";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pharmacyId);
            stmt.setString(2, medicationId);

            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}