package xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

import pojos.Supplier;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierList {

    @XmlElement(name = "supplier")
    private List<Supplier> suppliers;

    /**
	 * Default constructor for SupplierList.
	 */
	public SupplierList() {
        this.suppliers = new ArrayList<>();
    }

    public SupplierList(List<Supplier> suppliers) {
        this.suppliers = suppliers;
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
}

