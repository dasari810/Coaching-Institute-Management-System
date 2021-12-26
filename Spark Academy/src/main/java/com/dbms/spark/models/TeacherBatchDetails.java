package com.dbms.spark.models;


public class TeacherBatchDetails {
	
	
    private int teacherId;
    private Batch batch;

    public TeacherBatchDetails() {
        batch = new Batch();
    }

    public TeacherBatchDetails(int teacherId, Batch batch) {
        this.teacherId = teacherId;
        this.batch = batch;
    }

   
    public int getTeacherId() {
        return teacherId;
    }

    
    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    
    public Batch getBatch() {
        return batch;
    }

    
    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    @Override
    public String toString() {
        return "{" +
            " teacherId='" + getTeacherId() + "'" +
            ", batch='" + getBatch() + "'" +
            "}";
    }

}
