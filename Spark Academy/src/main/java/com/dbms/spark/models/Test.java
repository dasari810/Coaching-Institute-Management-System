package com.dbms.spark.models;

import java.sql.Date;
import java.sql.Time;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class Test {
    private int testId;

    @NotEmpty
    private String testName;

    @Positive
    private int roomNumber;

    @Positive
    private int maximumMarks;
    
    @NotNull
    private Date testDate;

    @NotNull
    private Time startTime;

    @NotNull
    private Time endTime;


    private String batchId;
    private String courseId;

  
	public Test() {
    }

    public Test(int testId, String testName, int roomNumber, Date testDate, Time startTime, Time endTime, int maximumMarks,String courseId  , String batchId) {
        this.testId = testId;
        this.testName = testName;
        this.roomNumber = roomNumber;
        this.testDate = testDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.maximumMarks = maximumMarks;
        this.courseId = courseId;
        this.batchId = batchId ;
    }

   
    public int getTestId() {
        return testId;
    }

    
    public void setTestId(int testId) {
        this.testId = testId;
    }

    
    public String getTestName() {
        return testName;
    }

   
    public void setTestName(String testName) {
        this.testName = testName;
    }
    
   
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    
    public Date getTestDate() {
        return testDate;
    }

    
    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    
    public Time getStartTime() {
        return startTime;
    }

    
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    
    public Time getEndTime() {
        return endTime;
    }

    
    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

   
    public int getMaximumMarks() {
        return maximumMarks;
    }

    
    public void setMaximumMarks(int maximumMarks) {
        this.maximumMarks = maximumMarks;
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

   
   
    @Override
    public String toString() {
        return "{" +
            " testId='" + getTestId() + "'" +
            ", testName='" + getTestName() + "'" +
            ", roomNumber='" + getRoomNumber() + "'" +
            ", testDate='" + getTestDate() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            ", maximumMarks='" + getMaximumMarks() + "'" +
            ", courseId='" + getCourseId() + "'" +
            ", batchId='" + getBatchId() + "'" +
            "}";
    }

}

