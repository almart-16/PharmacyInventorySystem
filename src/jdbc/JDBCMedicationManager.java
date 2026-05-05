package jdbc;

import java.sql.*;
import java.util.*;

import interfaces.MedicationManager;
import pojos.Medication;

public class JDBCMedicationManager implements MedicationManager {

    private Connection cm;

    public JDBCMedicationManager(Connection connection) {
        this.cm = connection;
    }

    @Override
    public Medication findByName(String name) {
        String sql = "SELECT * FROM Medication WHERE name = ?";

        try {
            PreparedStatement stmt = cm.prepareStatement(sql);
            stmt.setString(1, name);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Medication(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("target_illnes"),
                    rs.getString("SS").equalsIgnoreCase("yes"),
                    rs.getString("recipe").equalsIgnoreCase("yes")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Medication> getAllMedications() {
        List<Medication> list = new ArrayList<>();
        String sql = "SELECT * FROM Medication";

        try {
            Statement stmt = cm.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                list.add(new Medication(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("target_illnes"),
                    rs.getString("SS").equalsIgnoreCase("yes"),
                    rs.getString("recipe").equalsIgnoreCase("yes")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean addMedication(Medication m) {
        String sql = "INSERT INTO Medication (id, name, target_illnes, SS, recipe) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = cm.prepareStatement(sql);

            stmt.setString(1, m.getId());
            stmt.setString(2, m.getName());
            stmt.setString(3, m.getTargetIllness());

            // convertimos boolean → texto
            stmt.setString(4, m.isSs() ? "yes" : "no");
            stmt.setString(5, m.isReceta() ? "yes" : "no");

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteMedication(String medicationId) {
        String sql = "DELETE FROM Medication WHERE id = ?";

        try {
            PreparedStatement stmt = cm.prepareStatement(sql);
            stmt.setString(1, medicationId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}