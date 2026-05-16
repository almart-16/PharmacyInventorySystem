package main;

import jdbc.*;
import jpa.JPARoleManager;
import jpa.JPAUserManager;
import pojos.*;
import interfaces.*;
import xml.PharmacyWrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.List;

public class Main {
	
	private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	private static ConnectionManager connectionManager;
	
	private static JDBCMedicationManager medicationManager;
	private static JDBCInventoryManager inventoryManager;
	private static JDBCOrdersManager ordersManager;
	private static JDBCPurchaseManager purchaseManager;
	private static JDBCSupplierManager supplierManager;
	private static JDBCHistoryManager historyManager;
	private static JDBCPharmacyManager pharmacyManager;	
	
	private static JPAUserManager userManager;
	private static JPARoleManager roleManager;
	private static xml.XMLManager xmlManager;
	
	private static User loggedUser;
	
	
		/**
	 * Main method to run the Pharmacy Inventory System.
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		
		
		try {
			
			initializeManagers();
			
			boolean exit = false;
			while(!exit) {
				System.out.println("\n PHARMACY INVENTORY SYSTEM \n");
				System.out.println("\n1. Login");
				System.out.println("\n2. Exit");
				
				System.out.println("Choose an option:");
				int option = readInt();
				
				switch(option) {
				case 1:
	                loginMenu();
	                break;

	            case 2:
	                exit = true;
	                break;

	            default:
	                System.out.println("Invalid option.");
	                break;
	        }
				}
			
		} catch(Exception e) {
			System.out.println("Unexpected error: " + e.getMessage());
		}
		
	}

		
	

	
	// Initialization of all the managers
	
	/**
	
	 * Executes the initializeManagers action.
	
	 */
	
	private static void initializeManagers() {
		connectionManager = new ConnectionManager();
		
		medicationManager = new JDBCMedicationManager(connectionManager.getc());
		inventoryManager = new JDBCInventoryManager(connectionManager.getc());
		ordersManager = new JDBCOrdersManager(connectionManager.getc());
		purchaseManager = new JDBCPurchaseManager(connectionManager.getc());
		supplierManager = new JDBCSupplierManager(connectionManager.getc());
		historyManager = new JDBCHistoryManager(connectionManager.getc());
		pharmacyManager = new JDBCPharmacyManager(connectionManager.getc());


		userManager = new JPAUserManager();
		roleManager = new JPARoleManager();
		xmlManager = new xml.XMLManager();
		
	}
	
	
	
	
	// LOGIN MENU 
	
	 /**
	
	  * Executes the loginMenu action.
	
	  */
	
	 private static void loginMenu() throws IOException {

	        System.out.println("\nLOGIN");

	        System.out.print("\nUsername: ");
	        String username = reader.readLine();

	        System.out.print("\nPassword: ");
	        String password = reader.readLine();

	        loggedUser = userManager.login(username, password);

	        if (loggedUser == null) {
	            System.out.println("Invalid credentials.");
	            return;
	        }

	        if (loggedUser.getRole() == null || loggedUser.getRole().getRoleName() == null) {
	            System.out.println("This user has no valid role assigned. Access denied.");
	            loggedUser = null;
	            return;
	        }
	        

	        String roleName = loggedUser.getRole().getRoleName();
	        System.out.println("\nLogin successfull.");
	        
	        if(roleName.equals("admin")) {
	        	adminMenu();
	        
	        } else if(roleName.equals("pharmacist")) {
	        	pharmacistMenu();
	        } else {
		        System.out.println("\nUnknown role. Access denied.");
		        loggedUser = null;

	        }
	 }
	        
	        
	// ADMIN MENU
	 
	 /**
	 
	  * Executes the adminMenu action.
	 
	  */
	 
	 private static void adminMenu() throws IOException {

	        boolean logout = false;

	        while (!logout) {
	            System.out.println("\nADMIN MENU");
	            System.out.println("1. Show all medications");
	            System.out.println("2. Search medication");
	            System.out.println("3. Add new medication");
	            System.out.println("4. Delete medication");
	            System.out.println("5. Inventory menu");
	            System.out.println("6. Sell medication");
	            System.out.println("7. Stock alerts menu");
	            System.out.println("8. Orders menu");
	            System.out.println("9. History menu");
	            System.out.println("10. Pharmacy menu");
	            System.out.println("11. User management");
	            System.out.println("12. XML menu");
	            System.out.println("13. Logout");

	            System.out.print("Choose an option: ");
	            int option = readInt();

	            switch (option) {
	                case 1:
	                    showAllMedications();
	                    break;

	                case 2:
	                    searchMedication();
	                    break;

	                case 3:
	                    addMedication();
	                    break;

	                case 4:
	                    deleteMedication();
	                    break;

	                case 5:
	                    inventoryMenu(true);
	                    break;

	                case 6:
	                    sellMedication();
	                    break;

	                case 7:
	                    stockAlertsMenu();
	                    break;

	                case 8:
	                    ordersMenu();
	                    break;

	                case 9:
	                    historyMenu();
	                    break;

	                case 10:
	                    pharmacyMenu();
	                    break;

	                case 11:
	                    userManagementMenu();
	                    break;
	                    
	                case 12:
	                    xmlMenu();
	                    break;

	                case 13:
	                    logout = true;
	                    logout();
	                    break;

	                default:
	                    System.out.println("Invalid option.");
	                    break;
	            }
	        }
	    }
	 
	 
	 
	 
	 // PHARMACIST MENU
	 
	 /**
	 
	  * Executes the pharmacistMenu action.
	 
	  */
	 
	 private static void pharmacistMenu() throws IOException {

	        boolean logout = false;

	        while (!logout) {
	            System.out.println("\nPHARMACIST MENU");
	            System.out.println("1. Show all medications");
	            System.out.println("2. Search medication");
	            System.out.println("3. Inventory menu");
	            System.out.println("4. Sell medication");
	            System.out.println("5. Stock alerts menu");
	            System.out.println("6. Orders menu");
	            System.out.println("7. History menu");
	            System.out.println("8. Pharmacy menu");
	            System.out.println("9. Logout");

	            System.out.print("Choose an option: ");
	            int option = readInt();

	            switch (option) {
	                case 1:
	                    showAllMedications();
	                    break;

	                case 2:
	                    searchMedication();
	                    break;

	                case 3:
	                    increaseStock();
	                    break;

	                case 4:
	                    inventoryMenu(false);
	                    break;

	                case 5:
	                    stockAlertsMenu();
	                    break;

	                case 6:
	                    ordersMenu();
	                    break;

	                case 7:
	                    historyMenu();
	                    break;

	                case 8 :
	                    pharmacyMenu();
	                    break; 
	                    
	                case 9:
	                    logout = true;
	                    logout();
	                    break;

	                default:
	                    System.out.println("Invalid option.");
	                    break;
	            }
	        }
	    }
	
	 
	 
	 
	 // CASES 1,2,3,4 - MEDICATION METHODS:
	
	 /**
	
	  * Executes the showAllMedications action.
	
	  */
	
	 private static void showAllMedications() {
		    System.out.println("\nMedications: ");

		    List<Medication> medications = medicationManager.getAllMedications();

		    if (medications == null || medications.isEmpty()) {
		        System.out.println("No medications found.");
		        return;
		    }

		    for (Medication medication : medications) {
		        System.out.println(medication);
		    }
		}
	 
	 /**
	 
	 
	 
	 
	  * Executes the searchMedication action.
	 
	 
	 
	 
	  */
	 
	 
	 
	 
	 private static void searchMedication() throws IOException {

		    System.out.print("Medication name: ");
		    String name = reader.readLine();

		    Medication medication = medicationManager.findByName(name);

		    if (medication == null) {
		        System.out.println("Medication not found.");
		    } else {
		        System.out.println(medication);
		    }
		}
	 
	 
	 
	 /**
	 
	 
	 
	  * Executes the addMedication action.
	 
	 
	 
	  */
	 
	 
	 
	 private static void addMedication() throws IOException {

		    System.out.print("Medication ID: ");
		    String id = reader.readLine();

		    System.out.print("Medication name: ");
		    String name = reader.readLine();

		    System.out.print("Target illness: ");
		    String targetIllness = reader.readLine();

		    System.out.print("Covered by social security? true/false: ");
		    boolean ss = readBoolean();

		    System.out.print("Needs prescription? true/false: ");
		    boolean prescription = readBoolean();

		    Medication medication = new Medication(id, name, targetIllness, ss, prescription);

		    boolean added = medicationManager.addMedication(medication);

		    if (added) {
		        System.out.println("Medication added successfully.");
		    } else {
		        System.out.println("Medication could not be added.");
		    }
		}
	 
	 
	 

	 /**
	 
	 
	 

	  * Executes the deleteMedication action.
	 
	 
	 

	  */
	 
	 
	 

	 private static void deleteMedication() throws IOException {

		    System.out.print("Medication ID: ");
		    String medicationId = reader.readLine();

		    boolean deleted = medicationManager.deleteMedication(medicationId);

		    if (deleted) {
		        System.out.println("Medication deleted successfully.");
		    } else {
		        System.out.println("Medication could not be deleted.");
		    }
		}
	 
	 
	 
	 
	 
	 // CASE 5: INVENTORY MENU AND METHODS
	 
	 private static void inventoryMenu(boolean adminAccess) throws IOException {

	        boolean back = false;

	        while (!back) {
	            System.out.println("\n INVENTORY MENU");
	            System.out.println("1. Find inventory");
	            System.out.println("2. Increase stock");
	            System.out.println("3. Reduce stock manually");

	            if (adminAccess) {
	                System.out.println("4. Update inventory information");
	                System.out.println("5. Back");
	            } else {
	                System.out.println("4. Back");
	            }

	            System.out.print("Choose an option: ");
	            int option = readInt();

	            if (adminAccess) {
	                switch (option) {
	                    case 1:
	                        findInventory();
	                        break;

	                    case 2:
	                        increaseStock();
	                        break;

	                    case 3:
	                        reduceStock();
	                        break;

	                    case 4:
	                        updateInventoryInformation();
	                        break;

	                    case 5:
	                        back = true;
	                        break;

	                    default:
	                        System.out.println("Invalid option.");
	                        break;
	                }
	            } else {
	                switch (option) {
	                    case 1:
	                        findInventory();
	                        break;

	                    case 2:
	                        increaseStock();
	                        break;

	                    case 3:
	                        reduceStock();
	                        break;

	                    case 4:
	                        back = true;
	                        break;

	                    default:
	                        System.out.println("Invalid option.");
	                        break;
	                }
	            }
	        }
	    }
	 
	 /**
	 
	  * Executes the findInventory action.
	 
	  */
	 
	 private static void findInventory() throws IOException {

	        System.out.print("Pharmacy ID: ");
	        String pharmacyId = reader.readLine();

	        System.out.print("Medication ID: ");
	        String medicationId = reader.readLine();

	        Inventory inventory = inventoryManager.findInventory(pharmacyId, medicationId);

	        if (inventory == null) {
	            System.out.println("Inventory record not found.");
	        } else {
	            System.out.println(inventory);
	        }
	    }

	 
	    /**

	 
	     * Executes the increaseStock action.

	 
	     */

	 
	    private static void increaseStock() throws IOException {
	    	
	        System.out.print("Pharmacy ID: ");
	        String pharmacyId = reader.readLine();

	        System.out.print("Medication ID: ");
	        String medicationId = reader.readLine();

	        System.out.print("Quantity to add: ");
	        int quantity = readInt();

	        if (quantity <= 0) {
	            System.out.println("Quantity must be greater than 0.");
	            return;
	        }

	        boolean increased = inventoryManager.increaseStock(pharmacyId, medicationId, quantity);

	        if (increased) {
	            System.out.println("Stock increased successfully.");
	        } else {
	            System.out.println("Stock could not be increased.");
	        }
	    }
	 
	    
	    
	    /**
	 
	    
	    
	     * Executes the reduceStock action.
	 
	    
	    
	     */
	 
	    
	    
	    private static void reduceStock() throws IOException {

	        System.out.print("Pharmacy ID: ");
	        String pharmacyId = reader.readLine();

	        System.out.print("Medication ID: ");
	        String medicationId = reader.readLine();

	        System.out.print("Quantity to reduce: ");
	        int quantity = readInt();

	        if (quantity <= 0) {
	            System.out.println("Quantity must be greater than 0.");
	            return;
	        }

	        boolean reduced = inventoryManager.reduceStock(pharmacyId, medicationId, quantity);

	        if (reduced) {
	            System.out.println("Stock reduced successfully.");
	        } else {
	            System.out.println("Stock could not be reduced. Check stock availability.");
	        }
	    }
	 
	    
	    // METHOD ONLY FOR ADMIN
	    /**
	     * Executes the updateInventoryInformation action.
	     */
	    private static void updateInventoryInformation() throws IOException {

	        System.out.print("Pharmacy ID: ");
	        String pharmacyId = reader.readLine();

	        System.out.print("Medication ID: ");
	        String medicationId = reader.readLine();

	        System.out.print("New price: ");
	        double price = readDouble();

	        System.out.print("New purchase price: ");
	        double purchasePrice = readDouble();

	        System.out.print("New expiration date: ");
	        String expirationDate = reader.readLine();

	        System.out.print("New supplier ID: ");
	        String supplierId = reader.readLine();

	        boolean updated = inventoryManager.updateInventoryInformation(
	                pharmacyId,
	                medicationId,
	                price,
	                purchasePrice,
	                expirationDate,
	                supplierId);

	        if (updated) {
	            System.out.println("Inventory information updated successfully.");
	        } else {
	            System.out.println("Inventory information could not be updated.");
	        }
	    }
	 
	
	
	    
	
	    // CASE 6: SELL MEDICATION
	    
	    /**
	    
	     * Executes the sellMedication action.
	    
	     */
	    
	    private static void sellMedication() throws IOException {

	        System.out.print("Purchase ID: ");
	        String id = reader.readLine();

	        System.out.print("Client ID: ");
	        String clientId = reader.readLine();

	        System.out.print("Pharmacy ID: ");
	        String pharmacyId = reader.readLine();

	        System.out.print("Medication ID: ");
	        String medicationId = reader.readLine();

	        System.out.print("Quantity: ");
	        int quantity = readInt();

	        if (quantity <= 0) {
	            System.out.println("Quantity must be greater than 0.");
	            return;
	        }

	        System.out.print("Price: ");
	        double price = readDouble();
	        
	        System.out.print("Medication ID: ");
	        String date = reader.readLine();

	        Purchase purchase = new Purchase(id, clientId, pharmacyId, date, medicationId, quantity, price);

	        boolean sold = purchaseManager.sellMedication(purchase);

	        if (sold) {
	            System.out.println("Medication sold successfully.");
	        } else {
	            System.out.println("Medication could not be sold.");
	        }
	    }
	    
	    
	    
	    
	    // CASE 7: STOCK ALERTS MENU AND METHODS
	    
	    /**
	    
	     * Executes the stockAlertsMenu action.
	    
	     */
	    
	    private static void stockAlertsMenu() throws IOException {

	        boolean back = false;

	        while (!back) {
	            System.out.println("\n STOCK ALERTS MENU");
	            System.out.println("1. Show all low stock medications");
	            System.out.println("2. Check if one medication is low stock");
	            System.out.println("3. Check if one medication is out of stock");
	            System.out.println("4. Back");

	            System.out.print("Choose an option: ");
	            int option = readInt();

	            switch (option) {
	                case 1:
	                    showLowStockMedications();
	                    break;

	                case 2:
	                    checkLowStock();
	                    break;

	                case 3:
	                    checkOutOfStock();
	                    break;

	                case 4:
	                    back = true;
	                    break;

	                default:
	                    System.out.println("Invalid option.");
	                    break;
	            }
	        }
	    }
	    
	    
	    /**
	    
	    
	     * Executes the showLowStockMedications action.
	    
	    
	     */
	    
	    
	    private static void showLowStockMedications() {

	        List<Inventory> inventories = inventoryManager.getLowStockMedications();

	        if (inventories == null || inventories.isEmpty()) {
	            System.out.println("There are no low stock medications.");
	            return;
	        }

	        for (Inventory inventory : inventories) {
	            System.out.println(inventory);
	        }
	    }

	    
	    /**

	    
	     * Executes the checkLowStock action.

	    
	     */

	    
	    private static void checkLowStock() throws IOException {

	        System.out.print("Pharmacy ID: ");
	        String pharmacyId = reader.readLine();

	        System.out.print("Medication ID: ");
	        String medicationId = reader.readLine();

	        boolean lowStock = inventoryManager.isLowStock(pharmacyId, medicationId);

	        if (lowStock) {
	            System.out.println("This medication is low stock.");
	        } else {
	            System.out.println("This medication is not low stock.");
	        }
	    }

	    
	    /**

	    
	     * Executes the checkOutOfStock action.

	    
	     */

	    
	    private static void checkOutOfStock() throws IOException {
	    	
	        System.out.print("Pharmacy ID: ");
	        String pharmacyId = reader.readLine();

	        System.out.print("Medication ID: ");
	        String medicationId = reader.readLine();

	        boolean outOfStock = inventoryManager.isOutOfStock(pharmacyId, medicationId);

	        if (outOfStock) {
	            System.out.println("This medication is out of stock.");
	        } else {
	            System.out.println("This medication is not out of stock.");
	        }
	    }

	    
	    
	    
	    
	    // CASE 8: ORDERS MENU AND METHODS
	    
	    /**
	    
	     * Executes the ordersMenu action.
	    
	     */
	    
	    private static void ordersMenu() throws IOException {

	        boolean back = false;

	        while (!back) {
	            System.out.println("\nORDERS MENU");
	            System.out.println("1. Create order");
	            System.out.println("2. Search order by ID");
	            System.out.println("3. Show all orders");
	            System.out.println("4. Update order status");
	            System.out.println("5. Delete order");
	            System.out.println("6. Back");

	            System.out.print("Choose an option: ");
	            int option = readInt();

	            switch (option) {
	                case 1:
	                    createOrder();
	                    break;

	                case 2:
	                    findOrderById();
	                    break;

	                case 3:
	                    showAllOrders();
	                    break;

	                case 4:
	                    updateOrderStatus();
	                    break;

	                case 5:
	                    deleteOrder();
	                    break;

	                case 6:
	                    back = true;
	                    break;

	                default:
	                    System.out.println("Invalid option.");
	                    break;
	            }
	        }
	    }

	    
	    /**

	    
	     * Executes the createOrder action.

	    
	     */

	    
	    private static void createOrder() throws IOException {

	        System.out.print("Order ID: ");
	        String id = reader.readLine();

	        System.out.print("Pharmacy ID: ");
	        String pharmacyId = reader.readLine();

	        System.out.print("Supplier ID: ");
	        String supplierId = reader.readLine();

	        System.out.print("Medication ID: ");
	        String medicationId = reader.readLine();

	        System.out.print("Date: ");
	        String date = reader.readLine();
	        
	        System.out.print("Quantity: ");
	        int quantity = readInt();

	        if (quantity <= 0) {
	            System.out.println("Quantity must be greater than 0.");
	            return;
	        }

	        String status = "Pending";

	        Order order = new Order(id, pharmacyId, supplierId, medicationId, date, quantity, status);

	        boolean created = ordersManager.createOrder(order);

	        if (created) {
	            System.out.println("Order created successfully.");
	        } else {
	            System.out.println("Order could not be created.");
	        }
	    }

	    
	    /**

	    
	     * Executes the findOrderById action.

	    
	     */

	    
	    private static void findOrderById() throws IOException {

	        System.out.print("Order ID: ");
	        String orderId = reader.readLine();

	        Order order = ordersManager.findOrderById(orderId);

	        if (order == null) {
	            System.out.println("Order not found.");
	        } else {
	            System.out.println(order);
	        }
	    }

	    
	    /**

	    
	     * Executes the showAllOrders action.

	    
	     */

	    
	    private static void showAllOrders() {

	        List<Order> orders = ordersManager.getAllOrders();

	        if (orders == null || orders.isEmpty()) {
	            System.out.println("No orders found.");
	            return;
	        }

	        for (Order order : orders) {
	            System.out.println(order);
	        }
	    }

	    
	    /**

	    
	     * Executes the updateOrderStatus action.

	    
	     */

	    
	    private static void updateOrderStatus() throws IOException {
	    	
	        System.out.print("Order ID: ");
	        String orderId = reader.readLine();

	        System.out.print("New status: ");
	        String status = reader.readLine();

	        boolean updated = ordersManager.updateOrderStatus(orderId, status);

	        if (updated) {
	            System.out.println("Order status updated successfully.");
	        } else {
	            System.out.println("Order status could not be updated.");
	        }
	    }

	    /**

	     * Executes the deleteOrder action.

	     */

	    private static void deleteOrder() throws IOException {

	        System.out.print("Order ID: ");
	        String orderId = reader.readLine();

	        boolean deleted = ordersManager.deleteOrder(orderId);

	        if (deleted) {
	            System.out.println("Order deleted successfully.");
	        } else {
	            System.out.println("Order could not be deleted.");
	        }
	    }
	    
	    
	    
	    // CASE 9: HISTORY MENU AND METHODS
	    
	    /**
	    
	     * Executes the historyMenu action.
	    
	     */
	    
	    private static void historyMenu() throws IOException {

	        boolean back = false;

	        while (!back) {
	            System.out.println("\n HISTORY MENU");
	            System.out.println("1. Show all history");
	            System.out.println("2. Show history by pharmacy");
	            System.out.println("3. Show history by medication");
	            System.out.println("4. Back");

	            System.out.print("Choose an option: ");
	            int option = readInt();

	            switch (option) {
	                case 1:
	                    historyManager.showAllHistory();
	                    break;

	                case 2:
	                    showHistoryByPharmacy();
	                    break;

	                case 3:
	                    showHistoryByMedication();
	                    break;

	                case 4:
	                    back = true;
	                    break;

	                default:
	                    System.out.println("Invalid option.");
	                    break;
	            }
	        }
	    }

	    /**

	     * Executes the showHistoryByPharmacy action.

	     */

	    private static void showHistoryByPharmacy() throws IOException {

	        System.out.print("Pharmacy ID: ");
	        String pharmacyId = reader.readLine();

	        historyManager.showHistoryByPharmacy(pharmacyId);
	    }

	    /**

	     * Executes the showHistoryByMedication action.

	     */

	    private static void showHistoryByMedication() throws IOException {

	        System.out.print("Medication ID: ");
	        String medicationId = reader.readLine();

	        historyManager.showHistoryByMedication(medicationId);
	    }
	    
	    
	    
	    
	    
	    
	    // CASE 10: PHARMACY MENU AND METHODS

	    /**

	     * Executes the pharmacyMenu action.

	     */

	    private static void pharmacyMenu() throws IOException {

	        boolean back = false;

	        while (!back) {
	            System.out.println("\n PHARMACY MENU");
	            System.out.println("1. Show all pharmacies");
	            System.out.println("2. Search pharmacy by ID");
	            System.out.println("3. Search pharmacy by registration number");
	            System.out.println("4. Back");

	            System.out.print("Choose an option: ");
	            int option = readInt();

	            switch (option) {
	                case 1:
	                    showAllPharmacies();
	                    break;

	                case 2:
	                    findPharmacyById();
	                    break;

	                case 3:
	                    findPharmacyByRegistrationNumber();
	                    break;

	                case 4:
	                    back = true;
	                    break;

	                default:
	                    System.out.println("Invalid option.");
	                    break;
	            }
	        }
	    }

	    /**

	     * Executes the showAllPharmacies action.

	     */

	    private static void showAllPharmacies() {

	        List<Pharmacy> pharmacies = pharmacyManager.getAllPharmacies();

	        if (pharmacies == null || pharmacies.isEmpty()) {
	            System.out.println("No pharmacies found.");
	            return;
	        }

	        for (Pharmacy pharmacy : pharmacies) {
	            System.out.println(pharmacy);
	        }
	    }

	    
	    /**

	    
	     * Executes the findPharmacyById action.

	    
	     */

	    
	    private static void findPharmacyById() throws IOException {

	        System.out.print("Pharmacy ID: ");
	        String pharmacyId = reader.readLine();

	        Pharmacy pharmacy = pharmacyManager.findById(pharmacyId);

	        if (pharmacy == null) {
	            System.out.println("Pharmacy not found.");
	        } else {
	            System.out.println(pharmacy);
	        }
	    }

	    /**

	     * Executes the findPharmacyByRegistrationNumber action.

	     */

	    private static void findPharmacyByRegistrationNumber() throws IOException {

	        System.out.print("Registration number: ");
	        String registrationNumber = reader.readLine();

	        Pharmacy pharmacy = pharmacyManager.findbyRegistrationNumber(registrationNumber);

	        if (pharmacy == null) {
	            System.out.println("Pharmacy not found.");
	        } else {
	            System.out.println(pharmacy);
	        }
	    }
	    
	    
	    
	    
	    // CASE 11: USER MANAGEMENT MENU
	    
	    /**
	    
	     * Executes the userManagementMenu action.
	    
	     */
	    
	    private static void userManagementMenu() throws IOException {

	        if (!isAdmin()) {
	            System.out.println("Access denied. Only admins can manage users.");
	            return;
	        }

	        boolean back = false;

	        while (!back) {
	            System.out.println("\n USER MANAGEMENT");
	            System.out.println("1. Create pharmacist user");
	            System.out.println("2. Search user by username");
	            System.out.println("3. Show all users");
	            System.out.println("4. Update password");
	            System.out.println("5. Delete user");
	            System.out.println("6. Back");

	            System.out.print("Choose an option: ");
	            int option = readInt();

	            switch (option) {
	                case 1:
	                    createPharmacistUser(); // AYUDA CON ESTE
	                    break;

	                case 2:
	                    findUserByUsername();
	                    break;

	                case 3:
	                    showAllUsers();
	                    break;

	                case 4:
	                    updatePassword();
	                    break;

	                case 5:
	                    deleteUser();
	                    break;

	                case 6:
	                    back = true;
	                    break;

	                default:
	                    System.out.println("Invalid option.");
	                    break;
	            }
	        }
	    }

	    /**

	     * Executes the createPharmacistUser action.

	     */

	    private static void createPharmacistUser() throws IOException {
	        System.out.println("\n--- Create Pharmacist User ---");
	        System.out.print("Username: ");
	        String username = reader.readLine();

	        if (userManager.findUserByUserName(username) != null) {
	            System.out.println("Username already exists.");
	            return;
	        }

	        System.out.print("Password: ");
	        String password = reader.readLine();

	        Role pharmacistRole = roleManager.findRoleByName("pharmacist");
	        if (pharmacistRole == null) {
	            System.out.println("Role 'pharmacist' does not exist in the database. Please create it first or contact admin.");
	            return;
	        }

	        User newUser = new User();
	        newUser.setUsername(username);
	        newUser.setPassword(password);
	        newUser.setRole(pharmacistRole);
	        userManager.createUser(newUser);

	        System.out.println("Pharmacist user created successfully.");
	    }

	    /**

	     * Executes the findUserByUsername action.

	     */

	    private static void findUserByUsername() throws IOException {

	        System.out.print("Username: ");
	        String username = reader.readLine();

	        User user = userManager.findUserByUserName(username);

	        if (user == null) {
	            System.out.println("User not found.");
	        } else {
	            System.out.println(user);
	        }
	    }

	    
	    /**

	    
	     * Executes the showAllUsers action.

	    
	     */

	    
	    private static void showAllUsers() {

	        List<User> users = userManager.getAllUsers();

	        if (users == null || users.isEmpty()) {
	            System.out.println("No users found.");
	            return;
	        }

	        for (User user : users) {
	            System.out.println(user);
	        }
	    }

	    
	    /**

	    
	     * Executes the updatePassword action.

	    
	     */

	    
	    private static void updatePassword() throws IOException {

	        System.out.print("Username: ");
	        String username = reader.readLine();

	        User user = userManager.findUserByUserName(username);

	        if (user == null) {
	            System.out.println("User not found.");
	            return;
	        }

	        System.out.print("New password: ");
	        String newPassword = reader.readLine();

	        userManager.updatePassword(user, newPassword);

	        System.out.println("Password updated.");
	    }

	    
	    /**

	    
	     * Executes the deleteUser action.

	    
	     */

	    
	    private static void deleteUser() throws IOException {

	        System.out.print("User ID: ");
	        int id = readInt();

	        userManager.delateUser(id);

	        System.out.println("User deleted.");
	    }
	    
	    
	    
	    // CASE 12: XML MENU
	    
	    /**
	     * Executes the xmlMenu action.
	     */
	    private static void xmlMenu() throws IOException {
	        boolean back = false;
	        while (!back) {
	            System.out.println("\n XML MANAGEMENT MENU");
	            System.out.println("1. Export whole database to XML");
	            System.out.println("2. Import whole database from XML");
	            System.out.println("3. Export medications to XML");
	            System.out.println("4. Import medications from XML");
	            System.out.println("5. Export suppliers to XML");
	            System.out.println("6. Import suppliers from XML");
	            System.out.println("7. Validate XML against XSD");
	            System.out.println("8. Back");
	            
	            System.out.print("Choose an option: ");
	            int option = readInt();
	            
	            switch (option) {
	                case 1:
	                    System.out.print("Enter output file name (e.g., database.xml): ");
	                    String outName = reader.readLine();
	                    System.out.println("Loading data to export...");
	                    PharmacyWrapper wrapper = new PharmacyWrapper();
	                    wrapper.setMedications(medicationManager.getAllMedications());
	                    wrapper.setOrders(ordersManager.getAllOrders());
	                    wrapper.setPurchases(purchaseManager.getAllPurchases());
	                    wrapper.setPharmacies(pharmacyManager.getAllPharmacies());
	                    wrapper.setSuppliers(supplierManager.getAllSupliers());
	                    
	                    xmlManager.exportWholeDatabase(wrapper, outName);
	                    break;

	                case 2:
	                    System.out.print("Enter input file name (e.g., database.xml): ");
	                    String inName = reader.readLine();
	                    PharmacyWrapper importedWrapper = xmlManager.importWholeDatabase(inName);
	                    if (importedWrapper != null) {
	                        System.out.println("Database imported successfully into wrapper.");
	                    }
	                    break;

	                case 3:
	                    System.out.print("Enter output file name (e.g., medications.xml): ");
	                    String medOut = reader.readLine();
	                    xmlManager.exportMedications(medicationManager.getAllMedications(), medOut);
	                    break;

	                case 4:
	                    System.out.print("Enter input file name (e.g., medications.xml): ");
	                    String medIn = reader.readLine();
	                    List<Medication> meds = xmlManager.importMedications(medIn);
	                    if (meds != null) {
	                        System.out.println("Medications loaded from XML.");
	                    }
	                    break;

	                case 5:
	                    System.out.print("Enter output file name (e.g., suppliers.xml): ");
	                    String supOut = reader.readLine();
	                    xmlManager.exportSuppliers(supplierManager.getAllSupliers(), supOut);
	                    break;

	                case 6:
	                    System.out.print("Enter input file name (e.g., suppliers.xml): ");
	                    String supIn = reader.readLine();
	                    List<Supplier> sups = xmlManager.importSuppliers(supIn);
	                    if (sups != null) {
	                        System.out.println("Suppliers loaded from XML.");
	                    }
	                    break;

	                case 7:
	                    System.out.print("Enter XML file name to validate: ");
	                    String xmlFile = reader.readLine();
	                    System.out.print("Enter XSD file name for validation: ");
	                    String xsdFile = reader.readLine();
	                    xmlManager.validateXML(xmlFile, xsdFile);
	                    break;

	                case 8:
	                    back = true;
	                    break;

	                default:
	                    System.out.println("Invalid option.");
	                    break;
	            }
	        }
	    }
	    
	    
	    
	    
	    // EXTRA METHODS
	    // Necessary method for using admin / pharmacist 
	    
	    /**
	    
	     * Executes the isAdmin action.
	    
	     */
	    
	    private static boolean isAdmin() {

	        if (loggedUser == null) {
	            return false;
	        }

	        if (loggedUser.getRole() == null) {
	            return false;
	        }

	        if (loggedUser.getRole().getRoleName() == null) {
	            return false;
	        }

	        return loggedUser.getRole().getRoleName().equalsIgnoreCase("admin");
	    }

	    
	    
	    // Method to log out
	    
	    /**
	    
	     * Executes the logout action.
	    
	     */
	    
	    private static void logout() {

	        loggedUser = null;
	        System.out.println("Logged out.");
	    }
	    
	    
	    // Methods for reading different types of values 
	   
		private static int readInt() throws NumberFormatException, IOException {
			return Integer.parseInt(reader.readLine());
		}
		private static boolean readBoolean() throws NumberFormatException, IOException {
			return Boolean.parseBoolean(reader.readLine());
		}
		private static double readDouble() throws NumberFormatException, IOException {
			return Double.parseDouble(reader.readLine());
		}
	    
	    
	
	
	
	
}



