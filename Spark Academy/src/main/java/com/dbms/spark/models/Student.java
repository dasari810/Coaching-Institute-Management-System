package com.dbms.spark.models;


import java.sql.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class Student {
	
	
    private int studentId;

    private Date dateOfBirth;
    
    private String percentage10th;


	@NotBlank
    private String houseNumber;

    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String schoolAttending;

    private User user;

    @Valid
    private Guardian guardian;

    public Student() {
        user = new User();
        guardian = new Guardian();
    }

    public Student(int studentId , Date dateOfBirth, String houseNumber, String street, String city, String state, String schoolAttending, User user, Guardian guardian, String percentage10th) {
        this.studentId = studentId;
        this.dateOfBirth = dateOfBirth;
        this.percentage10th = percentage10th;
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
        this.state = state;
        this.schoolAttending = schoolAttending;
        this.user = user;
        this.guardian = guardian;
    }

   
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public String getSchoolAttending() {
        return schoolAttending;
    }

    public void setSchoolAttending(String schoolAttending) {
        this.schoolAttending = schoolAttending;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public String getPercentage10th() {
		return percentage10th;
	}

	public void setPercentage10th(String percentage10th) {
		this.percentage10th = percentage10th;
	}

    public Guardian getGuardian() {
        return guardian;
    }

    public void setGuardian(Guardian guardian) {
        this.guardian = guardian;
    }

    @Override
    public String toString() {
        return "{" +
            " studentId='" + getStudentId() + "'" +
            ", dateOfBirth='" + getDateOfBirth() + "'" +
            ", percentage10th='" + getPercentage10th() + "'" +
            ", houseNumber='" + getHouseNumber() + "'" +
            ", street='" + getStreet() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", schoolAttending='" + getSchoolAttending() + "'" +
            ", user='" + getUser() + "'" +
            ", guardian='" + getGuardian() + "'" +
            "}";
    }

}

