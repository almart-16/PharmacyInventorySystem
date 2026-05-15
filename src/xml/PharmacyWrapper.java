package xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

import pojos.*;

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
    
    public PharmacyWrapper () {}

	public List<Medication> getMedications() {
		return medications;
	}

	public void setMedications(List<Medication> medications) {
		this.medications = medications;
	}

	public List<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

	public List<Pharmacy> getPharmacies() {
		return pharmacies;
	}

	public void setPharmacies(List<Pharmacy> pharmacies) {
		this.pharmacies = pharmacies;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	public List<Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}

	public List<History> getHistories() {
		return histories;
	}

	public void setHistories(List<History> histories) {
		this.histories = histories;
	}

	public List<Municipality> getMunicipalities() {
		return municipalities;
	}

	public void setMunicipalities(List<Municipality> municipalities) {
		this.municipalities = municipalities;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
    
    
}
