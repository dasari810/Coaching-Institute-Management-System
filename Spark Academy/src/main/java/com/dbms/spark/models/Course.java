package com.dbms.spark.models;

import javax.validation.constraints.NotEmpty;

public class Course {
	
    @NotEmpty
    private String courseId;
    
    private String description;
    
    @NotEmpty
    private String courseName;


    public Course() {
    }

    public Course(String courseId, String courseName, String description) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.description = description;
    }

   
    public String getCourseId() {
        return courseId;
    }

    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    
    public String getCourseName() {
        return courseName;
    }

   
    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
            " courseId='" + getCourseId() + "'" +
            ", courseName='" + getCourseName() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }

}

