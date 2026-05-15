package xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.*;

import pojos.Medication;

/**
 * Wrapper class for a list of medications, used for XML serialization.
 */
@XmlRootElement(name = "medications")
@XmlAccessorType(XmlAccessType.FIELD)
public class MedicationList {

    @XmlElement(name = "medication")
    private List<Medication> medications;

    /**
	 * Default constructor for MedicationList.
	 */
	public MedicationList() {
        this.medications = new ArrayList<>();
    }

    /**
     * Parameterized constructor for MedicationList.
     * @param medications the list of medications
     */
    public MedicationList(List<Medication> medications) {
        this.medications = medications;
    }

    // Getters y Setters

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
}

