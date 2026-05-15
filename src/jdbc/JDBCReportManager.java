package jdbc;

import java.sql.*;
import java.util.*;

import interfaces.ReportManager;
import pojos.GlobalStats;
import pojos.MedicationStats;

/**
 * JDBC implementation of the ReportManager interface.
 * Handles database queries to generate analytical reports regarding 
 * sales, revenues, costs, and profits of the pharmacy's inventory.
 */
public class JDBCReportManager implements ReportManager {

    private Connection connection;

    /**
     * Constructs a new JDBCReportManager with the given database connection.
     *
     * @param connection The active database connection.
     */
    public JDBCReportManager(Connection connection) {
        this.connection = connection;
    }

    /**
     * Helper method to calculate the profit margin as a percentage.
     * 
     * @param profit  The calculated profit.
     * @param revenue The total revenue.
     * @return The profit margin as a percentage, or 0 if revenue is 0.
     */
    private double calculateMargin(double profit, double revenue) {
        if (revenue == 0) return 0;
        return (profit / revenue) * 100.0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MedicationStats> getMonthlyMedicationSales(String month, String year) {
        List<MedicationStats> list = new ArrayList<>();
        String likePattern = year + "-" + month + "-%";
        
        String sql = "SELECT p.medicationId, m.name AS medicationName, " +
                     "SUM(p.quantity) AS quantitySold, " +
                     "SUM(p.price) AS totalRevenue, " +
                     "SUM(p.quantity * COALESCE(i.purchasePrice, 0)) AS totalCost " +
                     "FROM Purchase p " +
                     "JOIN Medication m ON p.medicationId = m.id " +
                     "JOIN Inventory i ON p.medicationId = i.medicationId AND p.pharmacyId = i.pharmacyId " +
                     "WHERE p.date LIKE ? " +
                     "GROUP BY p.medicationId, m.name";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, likePattern);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String medId = rs.getString("medicationId");
                String medName = rs.getString("medicationName");
                int qty = rs.getInt("quantitySold");
                double rev = rs.getDouble("totalRevenue");
                double cost = rs.getDouble("totalCost");
                double profit = rev - cost;
                double margin = calculateMargin(profit, rev);
                
                list.add(new MedicationStats(medId, medName, qty, rev, cost, profit, margin));
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
    public List<MedicationStats> getTotalRevenueAndProfitPerMedication() {
        List<MedicationStats> list = new ArrayList<>();
        String sql = "SELECT p.medicationId, m.name AS medicationName, " +
                     "SUM(p.quantity) AS quantitySold, " +
                     "SUM(p.price) AS totalRevenue, " +
                     "SUM(p.quantity * COALESCE(i.purchasePrice, 0)) AS totalCost " +
                     "FROM Purchase p " +
                     "JOIN Medication m ON p.medicationId = m.id " +
                     "JOIN Inventory i ON p.medicationId = i.medicationId AND p.pharmacyId = i.pharmacyId " +
                     "GROUP BY p.medicationId, m.name";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String medId = rs.getString("medicationId");
                String medName = rs.getString("medicationName");
                int qty = rs.getInt("quantitySold");
                double rev = rs.getDouble("totalRevenue");
                double cost = rs.getDouble("totalCost");
                double profit = rev - cost;
                double margin = calculateMargin(profit, rev);
                
                list.add(new MedicationStats(medId, medName, qty, rev, cost, profit, margin));
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
    public GlobalStats getGlobalMonthlyRevenueAndProfit(String month, String year) {
        String likePattern = year + "-" + month + "-%";
        String sql = "SELECT SUM(p.quantity) AS totalQty, " +
                     "SUM(p.price) AS globalRev, " +
                     "SUM(p.quantity * COALESCE(i.purchasePrice, 0)) AS globalCost " +
                     "FROM Purchase p " +
                     "JOIN Inventory i ON p.medicationId = i.medicationId AND p.pharmacyId = i.pharmacyId " +
                     "WHERE p.date LIKE ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, likePattern);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int qty = rs.getInt("totalQty");
                double rev = rs.getDouble("globalRev");
                double cost = rs.getDouble("globalCost");
                double profit = rev - cost;
                double margin = calculateMargin(profit, rev);
                
                return new GlobalStats(year + "-" + month, qty, rev, cost, profit, margin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new GlobalStats(year + "-" + month, 0, 0.0, 0.0, 0.0, 0.0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<MedicationStats> getTopSellingMedications(String month, String year, int limit) {
        List<MedicationStats> list = new ArrayList<>();
        String likePattern = year + "-" + month + "-%";
        
        String sql = "SELECT p.medicationId, m.name AS medicationName, " +
                     "SUM(p.quantity) AS quantitySold, " +
                     "SUM(p.price) AS totalRevenue, " +
                     "SUM(p.quantity * COALESCE(i.purchasePrice, 0)) AS totalCost " +
                     "FROM Purchase p " +
                     "JOIN Medication m ON p.medicationId = m.id " +
                     "JOIN Inventory i ON p.medicationId = i.medicationId AND p.pharmacyId = i.pharmacyId " +
                     "WHERE p.date LIKE ? " +
                     "GROUP BY p.medicationId, m.name " +
                     "ORDER BY quantitySold DESC " +
                     "LIMIT ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, likePattern);
            stmt.setInt(2, limit);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String medId = rs.getString("medicationId");
                String medName = rs.getString("medicationName");
                int qty = rs.getInt("quantitySold");
                double rev = rs.getDouble("totalRevenue");
                double cost = rs.getDouble("totalCost");
                double profit = rev - cost;
                double margin = calculateMargin(profit, rev);
                
                list.add(new MedicationStats(medId, medName, qty, rev, cost, profit, margin));
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
    public List<MedicationStats> getLowSellingMedications(int limit) {
        List<MedicationStats> list = new ArrayList<>();
        
        String sql = "SELECT m.id AS medicationId, m.name AS medicationName, " +
                     "COALESCE(SUM(p.quantity), 0) AS quantitySold, " +
                     "COALESCE(SUM(p.price), 0) AS totalRevenue, " +
                     "COALESCE(SUM(p.quantity * i.purchasePrice), 0) AS totalCost " +
                     "FROM Medication m " +
                     "LEFT JOIN Purchase p ON m.id = p.medicationId " +
                     "LEFT JOIN Inventory i ON p.medicationId = i.medicationId AND p.pharmacyId = i.pharmacyId " +
                     "GROUP BY m.id, m.name " +
                     "ORDER BY quantitySold ASC " +
                     "LIMIT ?";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String medId = rs.getString("medicationId");
                String medName = rs.getString("medicationName");
                int qty = rs.getInt("quantitySold");
                double rev = rs.getDouble("totalRevenue");
                double cost = rs.getDouble("totalCost");
                double profit = rev - cost;
                double margin = calculateMargin(profit, rev);
                
                list.add(new MedicationStats(medId, medName, qty, rev, cost, profit, margin));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
