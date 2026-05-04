package Pojos;

public class Municipality {
	private int id;
    private String name;
    private String province;

    public Municipality() {
    }

    public Municipality(int id, String name, String province) {
        this.id = id;
        this.name = name;
        this.province = province;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "Municipality{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", province='" + province + '\'' +
                '}';
    }
    
}
	