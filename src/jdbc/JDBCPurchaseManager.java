package jdbc;

import interfaces.PurchaseManager;
import pojos.Purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCPurchaseManager implements PurchaseManager {
    
    private final Connection connection;

    public JDBCPurchaseManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean savePurchase(Purchase purchase) {
        return create(purchase); // Reuse the CREATE operation from CRUD
    }

    @Override
    public boolean sellMedication(Purchase purchase) {
        boolean success = false;
        
        try {
            // Start the transaction (disable autocommit)
            // AUTOCOMMIT: This means that every time you execute a single DML statement 
            // (an INSERT, UPDATE or DELETE), the database does a COMMIT automatically 
            // right after finishing that line.
            // DISABLE: Do not save anything permanently until commit. 
            // If I tell you to cancel (rollback), forget everything I've told you since we started.
            connection.setAutoCommit(false);

            // Check if the medication requires a prescription
            String sqlPrescription = "SELECT prescription FROM Medication WHERE id = ?";
            try (PreparedStatement pstmtPrescription = connection.prepareStatement(sqlPrescription)) {
                pstmtPrescription.setString(1, purchase.getMedicationId());
                try (ResultSet rsPrescription = pstmtPrescription.executeQuery()) {
                    if (rsPrescription.next()) {
                        String prescriptionRequired = rsPrescription.getString("prescription");
                        // We assume it stores "yes" or "no"
                        if ("yes".equalsIgnoreCase(prescriptionRequired)) {
                            System.out.println("WARNING: Medication " + purchase.getMedicationId() + " requires a prescription. Please verify the patient's prescription.");
                        }
                    } else {
                        throw new SQLException("Medication not found in the database.");
                    }
                }
            }

            // Check current stock and minimum inventory in the specific pharmacy
            String sqlCheckStock = "SELECT stock_quantity, minimum_stock FROM Inventory WHERE pharmacy_id = ? AND medication_id = ?";
            int currentStock = 0;
            int minStock = 0;
            
            try (PreparedStatement pstmtStock = connection.prepareStatement(sqlCheckStock)) {
                pstmtStock.setString(1, purchase.getPharmacyId());
                pstmtStock.setString(2, purchase.getMedicationId());
                try (ResultSet rsStock = pstmtStock.executeQuery()) {
                    if (rsStock.next()) {
                        currentStock = rsStock.getInt("stock_quantity");
                        minStock = rsStock.getInt("minimum_stock");
                    } else {
                        throw new SQLException("The medication is not in the inventory of this pharmacy.");
                    }
                }
            }

            // Validate REQ12: Prevent stock from becoming negative
            if (currentStock < purchase.getQuantity()) {
                System.err.println("Error: Insufficient stock. Current stock: " + currentStock);
                connection.rollback();
                return false;
            }

            // Reduce stock (UPDATE)
            String sqlUpdateStock = "UPDATE Inventory SET stock_quantity = stock_quantity - ? WHERE pharmacy_id = ? AND medication_id = ?";
            try (PreparedStatement pstmtUpdate = connection.prepareStatement(sqlUpdateStock)) {
                pstmtUpdate.setInt(1, purchase.getQuantity());
                pstmtUpdate.setString(2, purchase.getPharmacyId());
                pstmtUpdate.setString(3, purchase.getMedicationId());
                pstmtUpdate.executeUpdate();
            }

            // Save the purchase (INSERT)
            savePurchase(purchase);

            // Use Case 7: Low stock notification
            int newStock = currentStock - purchase.getQuantity();
            if (newStock <= minStock) {
                System.out.println("SYSTEM ALERT: The stock for medication " + purchase.getMedicationId() + 
                                   " in pharmacy " + purchase.getPharmacyId() + " has reached " + newStock + 
                                   " (Minimum: " + minStock + "). Generate a purchase order!");
            }

            // 8. If everything went well, commit the transaction
            connection.commit();
            success = true;
            System.out.println("Sale registered and stock updated successfully.");

        } catch (SQLException e) {
            System.err.println("Error during the sale. Reverting changes (Rollback)...");
            e.printStackTrace();
            try {
                connection.rollback(); // Undo everything if there is a failure
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            try {
                // Always return the connection to its original state
                connection.setAutoCommit(true);
            } catch (SQLException autoCommitEx) {
                autoCommitEx.printStackTrace();
            }
        }
        
        return success;
    }

    public boolean create(Purchase purchase) {
        String sql = "INSERT INTO Purchase (id, client_id, pharmacy_id, medication_id, date, quantity, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, purchase.getId());
            pstmt.setString(2, purchase.getClientId()); // Changed from getPatientId to getClientId
            pstmt.setString(3, purchase.getPharmacyId());
            pstmt.setString(4, purchase.getMedicationId());
            pstmt.setString(5, purchase.getDate());
            pstmt.setInt(6, purchase.getQuantity());
            pstmt.setDouble(7, purchase.getPrice());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error inserting Purchase ID: " + purchase.getId());
            e.printStackTrace();
            return false;
        }
    }

    public Purchase read(String id) {
        String sql = "SELECT * FROM Purchase WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return extractPurchaseFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error reading Purchase ID: " + id);
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Purchase purchase) {
        String sql = "UPDATE Purchase SET client_id = ?, pharmacy_id = ?, medication_id = ?, date = ?, quantity = ?, price = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, purchase.getClientId()); // Changed from getPatientId to getClientId
            pstmt.setString(2, purchase.getPharmacyId());
            pstmt.setString(3, purchase.getMedicationId());
            pstmt.setString(4, purchase.getDate());
            pstmt.setInt(5, purchase.getQuantity());
            pstmt.setDouble(6, purchase.getPrice());
            pstmt.setString(7, purchase.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating Purchase ID: " + purchase.getId());
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String id) {
        String sql = "DELETE FROM Purchase WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting Purchase ID: " + id);
            e.printStackTrace();
            return false;
        }
    }

    public List<Purchase> readAll() {
        String sql = "SELECT * FROM Purchase";
        List<Purchase> purchases = new ArrayList<>();
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                purchases.add(extractPurchaseFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving purchase history.");
            e.printStackTrace();
        }
        return purchases;
    }

    private Purchase extractPurchaseFromResultSet(ResultSet rs) throws SQLException {
        Purchase purchase = new Purchase();
        purchase.setId(rs.getString("id"));
        purchase.setClientId(rs.getString("client_id")); // Changed from setPatientId to setClientId
        purchase.setPharmacyId(rs.getString("pharmacy_id")); // Changed camelCase to snake_case based on DB
        purchase.setMedicationId(rs.getString("medication_id"));
        purchase.setDate(rs.getString("date"));
        purchase.setQuantity(rs.getInt("quantity"));
        purchase.setPrice(rs.getDouble("price"));
        return purchase;
    }
}