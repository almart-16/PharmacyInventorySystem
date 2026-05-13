package jdbc;

import interfaces.PharmacyManager;
import pojos.Pharmacy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCPharmacyManager implements PharmacyManager {
    private final Connection connection;

    public JDBCPharmacyManager(Connection connection) {
        this.connection = connection;
    }
  
    // Using DML for queries
    @Override
    public Pharmacy findbyRegistrationNumber(String number) {
        String sql = "SELECT * FROM Pharmacy WHERE registration_number = ?";
        Pharmacy pharmacy = null;

        // Using try-with-resources to ensure PreparedStatement and ResultSet are closed
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, number);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    pharmacy = extractPharmacyFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error finding pharmacy by registration number: " + number);
            e.printStackTrace();
        }
        return pharmacy;
    }

    @Override
    public Pharmacy findById(String id) {
        String sql = "SELECT * FROM Pharmacy WHERE id = ?";
        Pharmacy pharmacy = null;

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    pharmacy = extractPharmacyFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error reading pharmacy with ID: " + id);
            e.printStackTrace();
            
            // Java prints the full stack trace of the error to the screen,
            // showing where and why the program failed.
        }
        return pharmacy;
    }

    @Override
    public List<Pharmacy> getAllPharmacies() {
        String sql = "SELECT * FROM Pharmacy";
        List<Pharmacy> pharmacies = new ArrayList<>();

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                pharmacies.add(extractPharmacyFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving the list of pharmacies.");
            e.printStackTrace();
        }
        return pharmacies;
    }

    public boolean create(Pharmacy pharmacy) {
        String sql = "INSERT INTO Pharmacy (id, address, phone, registration_number, municipality_id, photo) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, pharmacy.getId());
            pstmt.setString(2, pharmacy.getAddress());
            pstmt.setString(3, pharmacy.getPhone());
            pstmt.setString(4, pharmacy.getRegistrationNumber());
            // Transform the POJO's int to SQLite's TEXT
            pstmt.setString(5, String.valueOf(pharmacy.getMunicipalityId()));
            pstmt.setBytes(6, pharmacy.getPhoto());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error creating pharmacy with ID: " + pharmacy.getId());
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Pharmacy pharmacy) {
        String sql = "UPDATE Pharmacy SET address = ?, phone = ?, registration_number = ?, municipality_id = ?, photo = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, pharmacy.getAddress());
            pstmt.setString(2, pharmacy.getPhone());
            pstmt.setString(3, pharmacy.getRegistrationNumber());
            pstmt.setString(4, String.valueOf(pharmacy.getMunicipalityId()));
            pstmt.setBytes(5, pharmacy.getPhoto());
            pstmt.setString(6, pharmacy.getId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error updating pharmacy with ID: " + pharmacy.getId());
            e.printStackTrace();
            return false;
        }
    }


    public boolean delete(String id) {
        String sql = "DELETE FROM Pharmacy WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting pharmacy with ID: " + id);
            e.printStackTrace();
            return false;
        }
    }

    // Helper methods for setting 
    private Pharmacy extractPharmacyFromResultSet(ResultSet rs) throws SQLException {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setId(rs.getString("id"));
        pharmacy.setAddress(rs.getString("address"));
        pharmacy.setPhone(rs.getString("phone"));
        pharmacy.setRegistrationNumber(rs.getString("registration_number"));
        pharmacy.setPhoto(rs.getBytes("photo"));
        
        // Inverse mapping: from TEXT in SQLite to int in the POJO
        String municipalityIdStr = rs.getString("municipality_id");
        if (municipalityIdStr != null && !municipalityIdStr.isEmpty()) {
            try {
                pharmacy.setMunicipalityId(Integer.parseInt(municipalityIdStr));
            } catch (NumberFormatException e) {
                System.err.println("Error: The municipality is not a valid number: " + municipalityIdStr);
            }
        }
        
        return pharmacy;
    }

}