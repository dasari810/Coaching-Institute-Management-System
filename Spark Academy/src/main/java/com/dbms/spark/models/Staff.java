package com.dbms.spark.models;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class Staff {
	
	
   private int staffId;


    @NotBlank
    private String designation;


	@Valid
    private Employee employee;

    public Staff() {
        employee = new Employee();
    }

    public Staff(int staffId, String designation, Employee employee) {
        this.staffId = staffId;
        this.designation = designation;
        
        this.employee = employee;
    }

    /**
     * @return int return the staffId
     */
    public int getStaffId() {
        return staffId;
    }

    /**
     * @param staffId the staffId to set
     */
    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }
    
    public String getDesignation() {
    	return designation;
    }

    public void setDesignation(String designation) {
    	this.designation = designation;
    }
    
    public Employee getEmployee() {
        return employee;
    }

   
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "{" +
            " staffId='" + getStaffId() + "'" +
            ", gender='" + getDesignation() + "'" +
            ", employee='" + getEmployee() + "'" +
            "}";
    }

}
