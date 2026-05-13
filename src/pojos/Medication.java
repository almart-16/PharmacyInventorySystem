package pojos;

public class Medication {
    private String id;
    private String name;
    private String targetIllness; 
    private boolean ss;
    private boolean prescription; 

    public Medication() {}

    public Medication(String id, String name, String targetIllness, boolean ss, boolean prescription) {
        this.id = id;
        this.name = name;
        this.targetIllness = targetIllness;
        this.ss = ss;
        this.prescription = prescription;
    }

    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTargetIllness() { return targetIllness; }
    public void setTargetIllness(String targetIllness) { this.targetIllness = targetIllness; }

    public boolean isSs() { return ss; }
    public void setSs(boolean ss) { this.ss = ss; }

    public boolean isPrescription() { return prescription; }
    public void setPrescription(boolean prescription) { this.prescription = prescription; } // Cambiado de setReceta
}