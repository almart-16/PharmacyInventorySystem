package jdbc;

import java.sql.*;
import java.util.*;

import interfaces.HistoryManager;
import pojos.History;

public class JDBCHistoryManager implements HistoryManager {

    private Connection connection;

    /**
     * Constructs a new JDBCHistoryManager with the given database connection.
     *
     * @param connection the database connection
     */
    public JDBCHistoryManager(Connection connection) {
        this.connection = connection;
    }

    // HISTORY BY PHAARMACY
    /**
     * {@inheritDoc}
     */
    @Override
    public void showHistoryByPharmacy(String pharmacyId) {

        String sql =
            "SELECT id, pharmacyId, medicationId, movementType, quantity, price, date " +
            "FROM History " +
            "WHERE pharmacyId = ? " +
            "ORDER BY date DESC";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pharmacyId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                History h = new History(
                    rs.getString("id"),
                    rs.getString("pharmacyId"),
                    rs.getString("medicationId"),
                    rs.getString("movementType"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getString("date")
                );

                System.out.println(h);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // ALL HISTORY
    /**
     * {@inheritDoc}
     */
    @Override
    public void showAllHistory() {

        String sql =
            "SELECT id, pharmacyId, medicationId, movementType, quantity, price, date " +
            "FROM History " +
            "ORDER BY date DESC";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                History h = new History(
                    rs.getString("id"),
                    rs.getString("pharmacyId"),
                    rs.getString("medicationId"),
                    rs.getString("movementType"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getString("date")
                );

                System.out.println(h);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // HISTORY BY MEDICATION
    /**
     * {@inheritDoc}
     */
    @Override
    public void showHistoryByMedication(String medicationId) {

        String sql =
            "SELECT id, pharmacyId, medicationId, movementType, quantity, price, date " +
            "FROM History " +
            "WHERE medicationId = ? " +
            "ORDER BY date DESC";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, medicationId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                History h = new History(
                    rs.getString("id"),
                    rs.getString("pharmacyId"),
                    rs.getString("medicationId"),
                    rs.getString("movementType"),
                    rs.getInt("quantity"),
                    rs.getDouble("price"),
                    rs.getString("date")
                );

                System.out.println(h);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}
