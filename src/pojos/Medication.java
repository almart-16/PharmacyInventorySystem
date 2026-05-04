package Pojos;

public class Medication {


	    private String id;
	    private String name;
	    private String targetIllness;
	    private boolean ss;
	    private boolean receta;

	    public Medication() {
	    }

	    public Medication(String id, String name, String targetIllness, boolean ss, boolean receta) {
	        this.id = id;
	        this.name = name;
	        this.targetIllness = targetIllness;
	        this.ss = ss;
	        this.receta = receta;
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


	    public String getTargetIllness() {
	        return targetIllness;
	    }

	    public void setTargetIllness(String targetIllness) {
	        this.targetIllness = targetIllness;
	    }


	    public boolean isSs() {
	        return ss;
	    }

	    public void setSs(boolean ss) {
	        this.ss = ss;
	    }


	    public boolean isReceta() {
	        return receta;
	    }

	    public void setReceta(boolean receta) {
	        this.receta = receta;
	    }

	    @Override
	    public String toString() {
	        return "Medication{" +
	                "id='" + id + '\'' +
	                ", name='" + name + '\'' +
	                ", targetIllness='" + targetIllness + '\'' +
	                ", ss=" + ss +
	                ", receta=" + receta +
	                '}';
	    }
	    
	    
	}