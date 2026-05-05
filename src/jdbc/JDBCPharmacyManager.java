
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
  
    //Uso DML (queries) para consultas
    @Override
    public Pharmacy findbyRegistrationNumber(String number) {
        String sql = "SELECT * FROM Pharmacy WHERE registration_number = ?";
        Pharmacy pharmacy = null;

     // Uso de try-with-resources para asegurar el cierre de PreparedStatement y ResultSet
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, number);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    pharmacy = extractPharmacyFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar farmacia por número de registro: " + number);
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
            System.err.println("Error al leer la farmacia con ID: " + id);
            e.printStackTrace();
            
            //Java imprime en pantalla la 
            //traza completa del error 
            //que ha ocurrido, mostrando dónde 
            //y por qué fallo el programa.
            
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
            System.err.println("Error al obtener la lista de farmacias.");
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
            // Transformamos el int del POJO al TEXT de SQLite
            pstmt.setString(5, String.valueOf(pharmacy.getMunicipalityId()));
            pstmt.setBytes(6, pharmacy.getPhoto());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error al crear la farmacia con ID: " + pharmacy.getId());
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
            System.err.println("Error al actualizar la farmacia con ID: " + pharmacy.getId());
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
            System.err.println("Error al eliminar la farmacia con ID: " + id);
            e.printStackTrace();
            return false;
        }
    }

    //Metodos auxiliares com set 
    private Pharmacy extractPharmacyFromResultSet(ResultSet rs) throws SQLException {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setId(rs.getString("id"));
        pharmacy.setAddress(rs.getString("address"));
        pharmacy.setPhone(rs.getString("phone"));
        pharmacy.setRegistrationNumber(rs.getString("registration_number"));
        pharmacy.setPhoto(rs.getBytes("photo"));
        
        // Mapeo inverso:de TEXT en SQLite a int en el POJO
        String municipalityIdStr = rs.getString("municipality_id");
        if (municipalityIdStr != null && !municipalityIdStr.isEmpty()) {
            try {
                pharmacy.setMunicipalityId(Integer.parseInt(municipalityIdStr));
            } catch (NumberFormatException e) {
                System.err.println("Error : El municipio no es un numero valido: " + municipalityIdStr);
            }
        }
        
        return pharmacy;
    }

}
