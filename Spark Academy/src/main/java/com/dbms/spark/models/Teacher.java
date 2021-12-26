package com.dbms.spark.models;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class Teacher {
    private int teacherId;

   
    @NotBlank 
    private String qualification ;
    
    
    private String achievement  ;
    
    private int  teachingExpirience;

    
	@Valid
    private Employee employee;

    private Subject subject;

    public Teacher() {
        employee = new Employee();
        subject = new Subject();
    }

    public Teacher(int teacherId, Employee employee, Subject subject, String qualification, String achievement,int teachingExpirience) {
       
    	this.teacherId = teacherId;
        this.qualification = qualification;
        this.achievement = achievement ;
        this.teachingExpirience = teachingExpirience;
        this.employee = employee;
        this.subject = subject;
    }

    /**
     * @return int return the teacherId
     */
    public int getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId the teacherId to set
     */
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
    
    public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public int getTeachingExpirience() {
		return teachingExpirience;
	}

	public void setTeachingExpirience(int teachingExpirience) {
		this.teachingExpirience = teachingExpirience;
	}
   
    public Employee getEmployee() {
        return employee;
    }

    
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    
    public Subject getSubject() {
        return subject;
    }

   
    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "{" +
            " teacherId='" + getTeacherId() + "'" +
            ", achievement='" + getAchievement() + "'" +
            ", experience='" + getTeachingExpirience() + "'" +
            ", qualification='" + getQualification() + "'" +
            ", employee='" + getEmployee() + "'" +
            ", subject='" + getSubject() + "'" +
            "}";
    }

}
