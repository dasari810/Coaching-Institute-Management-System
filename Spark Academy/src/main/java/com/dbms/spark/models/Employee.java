package com.dbms.spark.models;


import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;


public class Employee {
	
	
    private int employeeId;
    private int basicSalary;
    
  
    private Date joinDate;
    
    private Date endDate;

   
    
    @NotBlank
    private String houseNumber;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String state;
    
    @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "must be a valid PAN Number")
    private String panNumber;

    private String accountNumber;


    private User user;

    public Employee() {
        user = new User();
    }

    public Employee(int employeeId, int basicSalary, Date joinDate, Date endDate, String panNumber, String accountNumber, User user ,  String houseNumber, String street, String city, String state ) {
        this.employeeId = employeeId;
        this.basicSalary = basicSalary;
        this.joinDate = joinDate;
        this.endDate = endDate;
        this.panNumber = panNumber;
        this.accountNumber = accountNumber;
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.user = user;
    }

    
    public int getEmployeeId() {
        return employeeId;
    }

    
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

   
    public int getBasicSalary() {
        return basicSalary;
    }

   
    public void setBasicSalary(int basicSalary) {
        this.basicSalary = basicSalary;
    }

   
    public Date getJoinDate() {
        return joinDate;
    }

   
    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    
    public Date getEndDate() {
        return endDate;
    }

    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    
    public String getPanNumber() {
        return panNumber;
    }

    
    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

   
    public String getAccountNumber() {
        return accountNumber;
    }

    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

   
    public User getUser() {
        return user;
    }

    
    public void setUser(User user) {
        this.user = user;
    }
    

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    @Override
    public String toString() {
        return "{" +
            " employeeId='" + getEmployeeId() + "'" +
            ", basicSalary='" + getBasicSalary() + "'" +
            ", joinDate='" + getJoinDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", panNumber='" + getPanNumber() + "'" +
            ", houseNumber='" + getHouseNumber() + "'" +
            ", street='" + getStreet() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }

}
