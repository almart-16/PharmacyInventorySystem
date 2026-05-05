package interfaces;
import java.util.List;

import pojos.Order;

public interface OrdersManager {

	boolean createOrder(Order order);
	
	Order findOrderById(String orderId);
	
	List<Order> getAllOrders();
	
	boolean updateOrderStatus(String orderId, String status);
	
	boolean deleteOrder(String orderId);
	
}
