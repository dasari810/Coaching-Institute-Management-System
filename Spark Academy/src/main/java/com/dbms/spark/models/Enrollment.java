package com.dbms.spark.models;


import java.sql.Date;

public class Enrollment {
	
	
    private int enrollmentId;
    private int studentId;
    private String batchId;
    private String courseId;
    private Date joinDate;
    private Date endDate;
    private Transaction transaction;
   
    public Enrollment() {
        transaction = new Transaction();
    }

    public Enrollment(int enrollmentId, int studentId, String batchId, String courseId, Transaction transaction, Date joinDate, Date endDate) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.batchId = batchId;
        this.courseId = courseId;
        this.transaction = transaction;
        this.joinDate = joinDate;
        this.endDate = endDate;
    }

   
    public int getEnrollmentId() {
        return enrollmentId;
    }

    
    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

   
    public int getStudentId() {
        return studentId;
    }

    
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

   
    public String getBatchId() {
        return batchId;
    }

    
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

   
    public String getCourseId() {
        return courseId;
    }

    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    
    public Transaction getTransaction() {
        return transaction;
    }

   
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
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

    @Override
    public String toString() {
        return "{" +
            " enrollmentId='" + getEnrollmentId() + "'" +
            ", studentId='" + getStudentId() + "'" +
            ", batchId='" + getBatchId() + "'" +
            ", courseId='" + getCourseId() + "'" +
            ", transaction='" + getTransaction() + "'" +
            ", joinDate='" + getJoinDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            "}";
    }

}

