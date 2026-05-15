package xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

import pojos.*;

/**
 * Wrapper class representing the entire pharmacy system database for XML serialization.
 */
@XmlRootElement(name = "pharmacy_unit")
public class PharmacyWrapper {
	
	@XmlElementWrapper(name = "medications")
    @XmlElement(name = "medication") //este nombre tiene que coincides con el de los pojos 
    private List<Medication> medications = new ArrayList<>();

    @XmlElementWrapper(name = "suppliers")
    @XmlElement(name = "supplier")
    private List<Supplier> suppliers = new ArrayList<>();

    @XmlElementWrapper(name = "clients")
    @XmlElement(name = "client")
    private List<Client> clients = new ArrayList<>();

    @XmlElementWrapper(name = "pharmacies")
    @XmlElement(name = "pharmacy")
    private List<Pharmacy> pharmacies = new ArrayList<>();

    @XmlElementWrapper(name = "orders")
    @XmlElement(name = "order")
    private List<Order> orders = new ArrayList<>();

    @XmlElementWrapper(name = "purchases")
    @XmlElement(name = "purchase")
    private List<Purchase> purchases = new ArrayList<>();

    @XmlElementWrapper(name = "inventories")
    @XmlElement(name = "inventory")
    private List<Inventory> inventories = new ArrayList<>();

    @XmlElementWrapper(name = "histories")
    @XmlElement(name = "history")
    private List<History> histories = new ArrayList<>();

    @XmlElementWrapper(name = "municipalities")
    @XmlElement(name = "municipality")
    private List<Municipality> municipalities = new ArrayList<>();

    @XmlElementWrapper(name = "users")
    @XmlElement(name = "user")
    private List<User> users = new ArrayList<>();

    @XmlElementWrapper(name = "roles")
    @XmlElement(name = "role")
    private List<Role> roles = new ArrayList<>();
    
    /**
	 * Default constructor for PharmacyWrapper.
	 */
	public PharmacyWrapper() {}

	/**
	 * Gets the Medications.
	 * @return the Medications
	 */
	public List<Medication> getMedications() {
		return medications;
	}

	/**
	 * Sets the Medications.
	 * @param medications the new Medications
	 */
	public void setMedications(List<Medication> medications) {
		this.medications = medications;
	}

	/**
	 * Gets the Suppliers.
	 * @return the Suppliers
	 */
	public List<Supplier> getSuppliers() {
		return suppliers;
	}

	/**
	 * Sets the Suppliers.
	 * @param suppliers the new Suppliers
	 */
	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	/**
	 * Gets the Clients.
	 * @return the Clients
	 */
	public List<Client> getClients() {
		return clients;
	}

	/**
	 * Sets the Clients.
	 * @param clients the new Clients
	 */
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	/**
	 * Gets the Pharmacies.
	 * @return the Pharmacies
	 */
	public List<Pharmacy> getPharmacies() {
		return pharmacies;
	}

	/**
	 * Sets the Pharmacies.
	 * @param pharmacies the new Pharmacies
	 */
	public void setPharmacies(List<Pharmacy> pharmacies) {
		this.pharmacies = pharmacies;
	}

	/**
	 * Gets the Orders.
	 * @return the Orders
	 */
	public List<Order> getOrders() {
		return orders;
	}

	/**
	 * Sets the Orders.
	 * @param orders the new Orders
	 */
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	/**
	 * Gets the Purchases.
	 * @return the Purchases
	 */
	public List<Purchase> getPurchases() {
		return purchases;
	}

	/**
	 * Sets the Purchases.
	 * @param purchases the new Purchases
	 */
	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	/**
	 * Gets the Inventories.
	 * @return the Inventories
	 */
	public List<Inventory> getInventories() {
		return inventories;
	}

	/**
	 * Sets the Inventories.
	 * @param inventories the new Inventories
	 */
	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	/**
	 * Gets the Histories.
	 * @return the Histories
	 */
	public List<History> getHistories() {
		return histories;
	}

	/**
	 * Sets the Histories.
	 * @param histories the new Histories
	 */
	public void setHistories(List<History> histories) {
		this.histories = histories;
	}

	/**
	 * Gets the Municipalities.
	 * @return the Municipalities
	 */
	public List<Municipality> getMunicipalities() {
		return municipalities;
	}

	/**
	 * Sets the Municipalities.
	 * @param municipalities the new Municipalities
	 */
	public void setMunicipalities(List<Municipality> municipalities) {
		this.municipalities = municipalities;
	}

	/**
	 * Gets the Users.
	 * @return the Users
	 */
	public List<User> getUsers() {
		return users;
	}

	/**
	 * Sets the Users.
	 * @param users the new Users
	 */
	public void setUsers(List<User> users) {
		this.users = users;
	}

	/**
	 * Gets the Roles.
	 * @return the Roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * Sets the Roles.
	 * @param roles the new Roles
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
    
    
}

