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

    public SupplierList() {
        this.suppliers = new ArrayList<>();
    }

    public SupplierList(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }


    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
}
