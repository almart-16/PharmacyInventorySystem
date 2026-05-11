package jdbc;

import interfaces.SupplierManager;
import pojos.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCSupplierManager implements SupplierManager{
	
	private final Connection connection;
	
	public JDBCSupplierManager(Connection connection) {
        this.connection = connection;
    }
	
	@Override
    public Supplier addSupplier(Supplier supplier) {
        String sql = "INSERT INTO Supplier (id, name, phone) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, supplier.getId());
            pstmt.setString(2, supplier.getName());
            pstmt.setString(3, supplier.getPhone());

            int rowsAffected = pstmt.executeUpdate();
            
            // If inserted successfully, return the same object
            if (rowsAffected > 0) {
                return supplier; 
            }
        } catch (SQLException e) {
            System.err.println("Error adding supplier: " + supplier.getName());
            e.printStackTrace();
        }
        
        // If we reach here, there was an error or no rows were inserted
        return null;
    }
    
	@Override
    public Supplier findById(String id) {
        String sql = "SELECT * FROM Supplier WHERE id = ?";
        Supplier supplier = null;
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    supplier = extractSupplierFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error finding supplier with ID: " + id);
            e.printStackTrace();
        }
        
        return supplier;
    }
    
	@Override
    public List<Supplier> getAllSupliers() {
        String sql = "SELECT * FROM Supplier";
        List<Supplier> suppliers = new ArrayList<>();
        
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
             
            while (rs.next()) {
                suppliers.add(extractSupplierFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving the list of suppliers.");
            e.printStackTrace();
        }
        
        return suppliers;
    }
	
	public boolean updateSupplier(Supplier supplier) {
        String sql = "UPDATE Supplier SET name = ?, phone = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, supplier.getName());
            pstmt.setString(2, supplier.getPhone());
            pstmt.setString(3, supplier.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating supplier with ID: " + supplier.getId());
            e.printStackTrace();
            return false;
        }
    }
    
	public boolean deleteSupplier(String id) {
        String sql = "DELETE FROM Supplier WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting supplier with ID: " + id);
            e.printStackTrace();
            return false;
        }
    }
	
	private Supplier extractSupplierFromResultSet(ResultSet rs) throws SQLException {
        Supplier supplier = new Supplier();
        supplier.setId(rs.getString("id"));
        supplier.setName(rs.getString("name"));
        // The POJO and the insert include the phone, ensure it exists in the DB
        supplier.setPhone(rs.getString("phone")); 
        return supplier;
    }
	
}