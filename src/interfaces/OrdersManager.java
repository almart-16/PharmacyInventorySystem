package interfaces;
import java.util.List;

import pojos.Order;

/**
 * Interface defining operations for managing orders.
 */
public interface OrdersManager {

	/**
	 * Creates a new order in the system.
	 *
	 * @param order the order object to create
	 * @return true if the order was successfully created, false otherwise
	 */
	boolean createOrder(Order order);
	
	/**
	 * Finds an order by its ID.
	 *
	 * @param orderId the ID of the order
	 * @return the order object if found, null otherwise
	 */
	Order findOrderById(String orderId);
	
	/**
	 * Retrieves all orders in the system.
	 *
	 * @return a list of all orders
	 */
	List<Order> getAllOrders();
	
	/**
	 * Updates the status of an existing order.
	 *
	 * @param orderId the ID of the order to update
	 * @param status the new status to set
	 * @return true if the status was successfully updated, false otherwise
	 */
	boolean updateOrderStatus(String orderId, String status);
	
	/**
	 * Deletes an order from the system.
	 *
	 * @param orderId the ID of the order to delete
	 * @return true if the order was successfully deleted, false otherwise
	 */
	boolean deleteOrder(String orderId);
	
}
