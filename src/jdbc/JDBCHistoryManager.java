package jdbc;

import java.sql.*;
import java.util.*;

import interfaces.HistoryManager;
import pojos.History;

public class JDBCHistoryManager implements HistoryManager {

    private Connection connection;

    public JDBCHistoryManager(Connection connection) {
        this.connection = connection;
    }

    // 🔹 HISTORIAL POR FARMACIA
    @Override
    public void showHistoryByPharmacy(String pharmacyId) {

        String sql = 
            "SELECT date, 'Sale' AS type, medicationId, quantity, price " +
            "FROM Purchase WHERE pharmacy_id = ? " +
            "UNION ALL " +
            "SELECT date, 'Restock' AS type, medication_id, quantity, NULL AS price " +
            "FROM Orders WHERE pharmacy_id = ? " +
            "ORDER BY date DESC";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pharmacyId);
            stmt.setString(2, pharmacyId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                History h = new History(
                    rs.getString("date"),
                    rs.getString("type"),
                    rs.getString("medication_id"),
                    rs.getInt("quantity"),
                    rs.getDouble("price")
                );

                System.out.println(h);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // HISTORIAL POR MEDICAMENTO
    @Override
    public void showHistoryByMedication(String medicationId) {

        String sql = 
            "SELECT date, 'Sale' AS type, medication_id, quantity, price " +
            "FROM Purchase WHERE medication_id = ? " +
            "UNION ALL " +
            "SELECT date, 'Restock' AS type, medication_id, quantity, NULL AS price " +
            "FROM Orders WHERE medication_id = ? " +
            "ORDER BY date DESC";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, medicationId);
            stmt.setString(2, medicationId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                History h = new History(
                    rs.getString("date"),
                    rs.getString("type"),
                    rs.getString("medication_id"),
                    rs.getInt("quantity"),
                    rs.getDouble("price")
                );

                System.out.println(h);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 🔹 TODO EL HISTORIAL
    @Override
    public void showAllHistory() {

        String sql = 
            "SELECT date, 'Sale' AS type, medication_id, quantity, price FROM Purchase " +
            "UNION ALL " +
            "SELECT date, 'Restock' AS type, medication_id, quantity, NULL FROM Orders " +
            "ORDER BY date DESC";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                History h = new History(
                    rs.getString("date"),
                    rs.getString("type"),
                    rs.getString("medication_id"),
                    rs.getInt("quantity"),
                    rs.getDouble("price")
                );

                System.out.println(h);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}