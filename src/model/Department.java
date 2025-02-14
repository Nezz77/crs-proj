package model;

public class Department {
    private String departmentId;
    private String name;

    public Department(String departmentId, String name) {
        this.departmentId = departmentId;
        this.name = name;
    }

    // Getters
    public String getDepartmentId() {
        return departmentId;
    }

    public String getName() {
        return name;
    }

    // Setters
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId='" + departmentId + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
