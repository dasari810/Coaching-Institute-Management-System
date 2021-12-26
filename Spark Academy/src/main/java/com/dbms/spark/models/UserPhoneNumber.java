package com.dbms.spark.models;


public class UserPhoneNumber {
	
    private String phoneNumber;
    private int userId;

    public UserPhoneNumber() {
    }

    public UserPhoneNumber(String phoneNumber, int userId) {
        this.phoneNumber = phoneNumber;
        this.userId = userId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "{" +
            " phoneNumber='" + getPhoneNumber() + "'" +
            ", userId='" + getUserId() + "'" +
            "}";
    }

}
