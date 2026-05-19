package jdbc;

import java.sql.*;
import java.util.*;

import interfaces.InventoryManager;
import pojos.Inventory;

public class JDBCInventoryManager implements InventoryManager {

    private Connection connection;
    
    /**
     * Constructs a new JDBCInventoryManager with the given database connection.
     *
     * @param connection the database connection
     */
    public JDBCInventoryManager(Connection connection) {
        this.connection = connection;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Inventory findInventory(String pharmacyId, String medicationId) {
        String sql = "SELECT * FROM Inventory WHERE pharmacyId = ? AND medicationId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pharmacyId);
            stmt.setString(2, medicationId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Inventory(
                        rs.getString("id"),
                        rs.getString("pharmacyId"),
                        rs.getString("medicationId"),
                        rs.getString("supplierId"),
                        rs.getInt("stockQuantity"),
                        rs.getDouble("price"),
                        rs.getDouble("purchasePrice"),
                        rs.getString("expirationDate"),
                        rs.getInt("minimumStock")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean increaseStock(String pharmacyId, String medicationId, int quantity) {
        String sql = "UPDATE Inventory SET stockQuantity = stockQuantity + ? WHERE pharmacyId = ? AND medicationId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setString(2, pharmacyId);
            stmt.setString(3, medicationId);

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
    public boolean reduceStock(String pharmacyId, String medicationId, int quantity) {
        String sql = "UPDATE Inventory SET stockQuantity = stockQuantity - ? WHERE pharmacyId = ? AND medicationId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, quantity);
            stmt.setString(2, pharmacyId);
            stmt.setString(3, medicationId);

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
    public boolean addInventory(Inventory i) {
        String sql = "INSERT INTO Inventory (id, pharmacyId, medicationId, supplierId, stockQuantity, price, purchasePrice, expirationDate, minimumStock) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, i.getId());
            stmt.setString(2, i.getPharmacyId());
            stmt.setString(3, i.getMedicationId());
            stmt.setString(4, i.getSupplierId());
            stmt.setInt(5, i.getStockQuantity());
            stmt.setDouble(6, i.getPrice());
            stmt.setDouble(7, i.getPurchasePrice());
            stmt.setString(8, i.getExpirationDate());
            stmt.setInt(9, i.getMinimumStock());

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
    public boolean updateInventoryInformation(String pharmacyId, String medicationId,
                                              double price, double purchasePrice, String expirationDate, String supplierId) {

        String sql = "UPDATE Inventory SET price = ?, purchasePrice = ?, expirationDate = ?, supplierId = ? WHERE pharmacyId = ? AND medicationId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setDouble(1, price);
            stmt.setDouble(2, purchasePrice);
            stmt.setString(3, expirationDate);
            stmt.setString(4, supplierId);
            stmt.setString(5, pharmacyId);
            stmt.setString(6, medicationId);

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
    public List<Inventory> getLowStockMedications() {
        List<Inventory> list = new ArrayList<>();
        String sql = "SELECT * FROM Inventory WHERE stockQuantity <= minimumStock";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                list.add(new Inventory(
                    rs.getString("id"),
                    rs.getString("pharmacyId"),
                    rs.getString("medicationId"),
                    rs.getString("supplierId"),
                    rs.getInt("stockQuantity"),
                    rs.getDouble("price"),
                    rs.getDouble("purchasePrice"),
                    rs.getString("expirationDate"),
                    rs.getInt("minimumStock")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLowStock(String pharmacyId, String medicationId) {
        String sql = "SELECT * FROM Inventory WHERE pharmacyId = ? AND medicationId = ? AND stockQuantity <= minimumStock";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pharmacyId);
            stmt.setString(2, medicationId);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOutOfStock(String pharmacyId, String medicationId) {
        String sql = "SELECT * FROM Inventory WHERE pharmacyId = ? AND medicationId = ? AND stockQuantity = 0";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, pharmacyId);
            stmt.setString(2, medicationId);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
