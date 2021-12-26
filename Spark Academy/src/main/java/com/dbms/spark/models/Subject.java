package com.dbms.spark.models;

import javax.validation.constraints.NotBlank;

public class Subject {
	
    @NotBlank
    private String subjectId;

    private String description;
    
    @NotBlank
    private String subjectName;


    public Subject() {
    }

    public Subject(String subjectId, String subjectName, String description) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.description = description;
    }

   
    public String getSubjectId() {
        return subjectId;
    }

    
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    
    public String getSubjectName() {
        return subjectName;
    }

    
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    
    public String getDescription() {
        return description;
    }

    
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" +
            " subjectId='" + getSubjectId() + "'" +
            ", subjectName='" + getSubjectName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }

}

