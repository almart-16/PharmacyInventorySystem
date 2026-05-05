
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
            
            // Si se inserto correctamente, devolvemos el mismo objeto
            if (rowsAffected > 0) {
                return supplier; 
            }
        } catch (SQLException e) {
            System.err.println("Error al añadir el proveedor: " + supplier.getName());
            e.printStackTrace();
        }
        
        // Si llegamos aquí, hubo un error o no se insertaron filas
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
            System.err.println("Error al buscar el proveedor con ID: " + id);
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
            System.err.println("Error al recuperar la lista de proveedores.");
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
            System.err.println("Error al actualizar el proveedor con ID: " + supplier.getId());
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
            System.err.println("Error al eliminar el proveedor con ID: " + id);
            e.printStackTrace();
            return false;
        }
    }
	
	private Supplier extractSupplierFromResultSet(ResultSet rs) throws SQLException {
        Supplier supplier = new Supplier();
        supplier.setId(rs.getString("id"));
        supplier.setName(rs.getString("name"));
        // El POJO y el insert contemplan el teléfono, asegúrate de que exista en la BD
        supplier.setPhone(rs.getString("phone")); 
        return supplier;

}
	
}
