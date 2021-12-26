package com.dbms.spark.models;

import java.sql.Time;
import java.util.Objects;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class Batch {
    @NotEmpty
    private String batchId;

    private Time startTime;
    
    private Time endTime;
    
    @Min(0)
    private int fee;

    @Positive
    private int roomNumber;
    
    private Course course;

    @NotEmpty
    private String batchName;

   
    public Batch() {
        course = new Course();
    }

    public Batch(String batchId, Course course, String batchName, int fee, int roomNumber, Time startTime, Time endTime) {
        this.batchId = batchId;
        this.course = course;
        this.batchName = batchName;
        this.fee = fee;
        this.roomNumber = roomNumber;
        this.startTime = startTime;
        this.endTime = endTime;
    }

   
    public String getBatchId() {
        return batchId;
    }

    
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    
    public Course getCourse() {
        return course;
    }

    
    public void setCourse(Course course) {
        this.course = course;
    }

    
    public String getBatchName() {
        return batchName;
    }

    
    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    
    public int getFee() {
        return fee;
    }

   
    public void setFee(int fee) {
        this.fee = fee;
    }

    
    public int getRoomNumber() {
        return roomNumber;
    }

    
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
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

    @Override
    public String toString() {
        return "{" +
            " batchId='" + getBatchId() + "'" +
            ", course='" + getCourse() + "'" +
            ", batchName='" + getBatchName() + "'" +
            ", fee='" + getFee() + "'" +
            ", roomNumber='" + getRoomNumber() + "'" +
            ", startTime='" + getStartTime() + "'" +
            ", endTime='" + getEndTime() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Batch)) {
            return false;
        }
        Batch batch = (Batch) o;
        return Objects.equals(batchId, batch.batchId) && Objects.equals(course.getCourseId(), batch.course.getCourseId());
    }

}

