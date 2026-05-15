package pojos;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "medication")
@XmlAccessorType(XmlAccessType.FIELD) //esto hace que todo sea un xml automaticamente
public class Medication {
	
	@XmlAttribute(name = "id")
    private String id;
	
	@XmlElement(name = "name")
    private String name;
    
    @XmlElement(name = "targetIllness")
    private String targetIllness; 
    
    @XmlElement(name = "socialSecurity") 
    private boolean ss;
    
    @XmlElement(name = "requiresPrescription")
    private boolean prescription;

    /**
     * Default constructor for Medication.
     */
    public Medication() {}

    /**
     * Parameterized constructor for Medication.
     */
    public Medication(String id, String name, String targetIllness, boolean ss, boolean prescription) {
        this.id = id;
        this.name = name;
        this.targetIllness = targetIllness;
        this.ss = ss;
        this.prescription = prescription;
    }

    
    /**
     * Gets the value of id.
     * @return The value of id.
     */
    public String getId() { return id; }
    /**
     * Sets the value of id.
     * @param id The new value of id.
     */
    public void setId(String id) { this.id = id; }

    /**
     * Gets the value of name.
     * @return The value of name.
     */
    public String getName() { return name; }
    /**
     * Sets the value of name.
     * @param name The new value of name.
     */
    public void setName(String name) { this.name = name; }

    /**
     * Gets the value of targetIllness.
     * @return The value of targetIllness.
     */
    public String getTargetIllness() { return targetIllness; }
    /**
     * Sets the value of targetIllness.
     * @param targetIllness The new value of targetIllness.
     */
    public void setTargetIllness(String targetIllness) { this.targetIllness = targetIllness; }

    /**
     * Gets the value of ss.
     * @return The value of ss.
     */
    public boolean isSs() { return ss; }
    /**
     * Sets the value of ss.
     * @param ss The new value of ss.
     */
    public void setSs(boolean ss) { this.ss = ss; }

    /**
     * Gets the value of prescription.
     * @return The value of prescription.
     */
    public boolean isPrescription() { return prescription; }
    /**
     * Sets the value of prescription.
     * @param prescription The new value of prescription.
     */
    public void setPrescription(boolean prescription) { this.prescription = prescription; }
}

