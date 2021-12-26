package com.dbms.spark.models;


public class CourseSubjectDetails {
	
    private String courseId;
    private Subject subject;

    public CourseSubjectDetails() {
        subject = new Subject();
    }

    public CourseSubjectDetails(String courseId, Subject subject) {
        this.courseId = courseId;
        this.subject = subject;
    }

    
    public String getCourseId() {
        return courseId;
    }

    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
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
            " courseId='" + getCourseId() + "'" +
            ", subject='" + getSubject() + "'" +
            "}";
    }

}

