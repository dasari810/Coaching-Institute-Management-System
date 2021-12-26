package com.dbms.spark.models;


public class GuardianPhoneNumber {
    private String phoneNumber;
    private String name;
    private int studentId;

    public GuardianPhoneNumber() {
    }

    public GuardianPhoneNumber(String phoneNumber, String name, int studentId) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.studentId = studentId;
    }

 
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "{" +
            " phoneNumber='" + getPhoneNumber() + "'" +
            ", name='" + getName() + "'" +
            ", studentId='" + getStudentId() + "'" +
            "}";
    }

}
