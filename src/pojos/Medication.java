package pojos;

public class Medication {


	    private String id;
	    private String name;
	    private String target_illness;
	    private boolean ss;
	    private boolean prescription;

	    public Medication() {
	    }

	    public Medication(String id, String name, String targetIllness, boolean ss, boolean prescription) {
	        this.id = id;
	        this.name = name;
	        this.target_illness = target_illness;
	        this.ss = ss;
	        this.prescription = prescription;
	    }

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }


	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }


	    public String getTarget_illness() {
	        return target_illness;
	    }

	    public void setTargetIllness(String target_illness) {
	        this.target_illness = target_illness;
	    }


	    public boolean isSs() {
	        return ss;
	    }

	    public void setSs(boolean ss) {
	        this.ss = ss;
	    }


	    public boolean isPrescription() {
	        return prescription;
	    }

	    public void setReceta(boolean prescription) {
	        this.prescription = prescription;
	    }

	    @Override
	    public String toString() {
	        return "Medication{" +
	                "id='" + id + '\'' +
	                ", name='" + name + '\'' +
	                ", targetIllness='" + target_illness + '\'' +
	                ", ss=" + ss +
	                ", receta=" + prescription +
	                '}';
	    }
	    
	    
	}