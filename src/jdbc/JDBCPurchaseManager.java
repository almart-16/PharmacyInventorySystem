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
        return create(purchase); // Reutilizamos la operación CREATE del CRUD
    }

    @Override
    public boolean sellMedication(Purchase purchase) {
        boolean success = false;
        
        try {
            // Iniciamos la transacción (desactivamos el autocommit)
        	//AUTOCOMMIT:Esto significa que cada vez que ejecutas una sola sentencia 
        	//DML (un INSERT, UPDATE o DELETE), la base de datos hace un 
    
    //COMMIT automáticamente justo después de terminar esa línea.
        	//DESACTIVAR: No guardes nada de forma permanente hasta que commit. 
        	//Si te digo que canceles (rollback), olvida todo lo que te he dicho desde que empezamos.
            connection.setAutoCommit(false);

            // Comprobar si el medicamento necesita receta
            String sqlRecipe = "SELECT recipe FROM Medication WHERE id = ?";
            try (PreparedStatement pstmtRecipe = connection.prepareStatement(sqlRecipe)) {
                pstmtRecipe.setString(1, purchase.getMedicationId());
                try (ResultSet rsRecipe = pstmtRecipe.executeQuery()) {
                    if (rsRecipe.next()) {
                        String recipeRequired = rsRecipe.getString("recipe");
                        // Asumimos que guarda "yes" o "no"
                        if ("yes".equalsIgnoreCase(recipeRequired)) {
                            System.out.println("⚠️ ATENCIÓN: El medicamento " + purchase.getMedicationId() + " requiere receta. Verifique la receta del paciente.");
                  
                        }
                    } else {
                        throw new SQLException("Medicamento no encontrado en la base de datos.");
                    }
                }
            }

            // Comprobar stock actual e inventario en la farmacia específica
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
                        throw new SQLException("El medicamento no está en el inventario de esta farmacia.");
                    }
                }
            }

            // Validar REQ12: Prevenir que el stock sea negativo
            if (currentStock < purchase.getQuantity()) {
                System.err.println(" Error: Stock insuficiente. Stock actual: " + currentStock);
                connection.rollback();
                return false;
            }

            // Reducir el stock (UPDATE)
            String sqlUpdateStock = "UPDATE Inventory SET stock_quantity = stock_quantity - ? WHERE pharmacy_id = ? AND medication_id = ?";
            try (PreparedStatement pstmtUpdate = connection.prepareStatement(sqlUpdateStock)) {
                pstmtUpdate.setInt(1, purchase.getQuantity());
                pstmtUpdate.setString(2, purchase.getPharmacyId());
                pstmtUpdate.setString(3, purchase.getMedicationId());
                pstmtUpdate.executeUpdate();
            }

            // Guardar la compra (INSERT)
            savePurchase(purchase);

            // Use Case 7: Notificación de stock bajo
            int newStock = currentStock - purchase.getQuantity();
            if (newStock <= minStock) {
                System.out.println("ALERTA DE SISTEMA: El stock del medicamento " + purchase.getMedicationId() + 
                                   " en la farmacia " + purchase.getPharmacyId() + " ha llegado a " + newStock + 
                                   " (Mínimo: " + minStock + "). ¡Generar orden de pedido!");
            }

            // 8. Si todo ha ido bien, confirmamos la transacción
            connection.commit();
            success = true;
            System.out.println("Venta registrada y stock actualizado con éxito.");

        } catch (SQLException e) {
            System.err.println("Error durante la venta. Revirtiendo cambios (Rollback)...");
            e.printStackTrace();
            try {
                connection.rollback(); // Deshacemos todo si hay un fallo
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            try {
                // Siempre volvemos a dejar la conexión en su estado original
                connection.setAutoCommit(true);
            } catch (SQLException autoCommitEx) {
                autoCommitEx.printStackTrace();
            }
        }
        
        return success;
    }
    public boolean create(Purchase purchase) {
        String sql = "INSERT INTO Purchase (id, patient_id, pharmacy_id, medication_id, date, quantity, price) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, purchase.getId());
            pstmt.setString(2, purchase.getPatientId());
            pstmt.setString(3, purchase.getPharmacyId());
            pstmt.setString(4, purchase.getMedicationId());
            pstmt.setString(5, purchase.getDate());
            pstmt.setInt(6, purchase.getQuantity());
            pstmt.setDouble(7, purchase.getPrice());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar Purchase ID: " + purchase.getId());
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
            System.err.println("Error al leer Purchase ID: " + id);
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(Purchase purchase) {
        String sql = "UPDATE Purchase SET patient_id = ?, pharmacy_id = ?, medication_id = ?, date = ?, quantity = ?, price = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, purchase.getPatientId());
            pstmt.setString(2, purchase.getPharmacyId());
            pstmt.setString(3, purchase.getMedicationId());
            pstmt.setString(4, purchase.getDate());
            pstmt.setInt(5, purchase.getQuantity());
            pstmt.setDouble(6, purchase.getPrice());
            pstmt.setString(7, purchase.getId());

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar Purchase ID: " + purchase.getId());
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
            System.err.println("Error al eliminar Purchase ID: " + id);
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
            System.err.println("Error al obtener el historial de compras.");
            e.printStackTrace();
        }
        return purchases;
    }
    private Purchase extractPurchaseFromResultSet(ResultSet rs) throws SQLException {
        Purchase purchase = new Purchase();
        purchase.setId(rs.getString("id"));
        purchase.setPatientId(rs.getString("patient_id"));
        purchase.setPharmacyId(rs.getString("pharmacy_id"));
        purchase.setMedicationId(rs.getString("medication_id"));
        purchase.setDate(rs.getString("date"));
        purchase.setQuantity(rs.getInt("quantity"));
        purchase.setPrice(rs.getDouble("price"));
        return purchase;
    }
}
