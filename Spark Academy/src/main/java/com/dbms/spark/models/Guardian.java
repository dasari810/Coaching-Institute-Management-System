package com.dbms.spark.models;


import javax.validation.constraints.NotEmpty;

public class Guardian {
	
    @NotEmpty
    private String name;

    private int studentId;

    private String occupation;

    @NotEmpty
    private String address;

    @NotEmpty
    private String email;

    @NotEmpty
    private String relationWithStudent;

    public Guardian() {
    }

    public Guardian(String name, int studentId, String occupation, String address, String email, String relationWithStudent) {
        this.name = name;
        this.studentId = studentId;
        this.occupation = occupation;
        this.address = address;
        this.email = email;
        this.relationWithStudent = relationWithStudent;
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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRelationWithStudent() {
        return relationWithStudent;
    }

    public void setRelationWithStudent(String relationWithStudent) {
        this.relationWithStudent = relationWithStudent;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", studentId='" + getStudentId() + "'" +
            ", occupation='" + getOccupation() + "'" +
            ", address='" + getAddress() + "'" +
            ", email='" + getEmail() + "'" +
            ", relationWithStudent='" + getRelationWithStudent() + "'" +
            "}";
    }

}

