package xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

import pojos.Medication;

@XmlRootElement(name = "medications")
@XmlAccessorType(XmlAccessType.FIELD)
public class MedicationList {

    @XmlElement(name = "medication")
    private List<Medication> medications;

    public MedicationList() {
        this.medications = new ArrayList<>();
    }

    //El que usa tu XMLManager
    public MedicationList(List<Medication> medications) {
        this.medications = medications;
    }

    // Getters y Setters
    public List<Medication> getMedications() {
        return medications;
    }

    public void setMedications(List<Medication> medications) {
        this.medications = medications;
    }
}
