package com.dbms.spark.models;

import javax.validation.constraints.PositiveOrZero;

public class Result {
    private Student student;
    private int testId;

    @PositiveOrZero
    private int marksScored;

    public Result() {
        student = new Student();
    }

    public Result(Student student, int testId, int marksScored, boolean hasAppliedRecheck, boolean isDoneRecheck, String recheckComments) {
        this.student = student;
        this.testId = testId;
        this.marksScored = marksScored;
       
    }

   
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    
    public int getTestId() {
        return testId;
    }

    
    public void setTestId(int testId) {
        this.testId = testId;
    }

   
    public int getMarksScored() {
        return marksScored;
    }

   
    public void setMarksScored(int marksScored) {
        this.marksScored = marksScored;
    }

   
    @Override
    public String toString() {
        return "{" +
            " student='" + getStudent() + "'" +
            ", testId='" + getTestId() + "'" +
            ", marksScored='" + getMarksScored() + "'" +
            "}";
    }

}
