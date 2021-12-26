package com.dbms.spark.models;

import java.sql.Date;
import java.sql.Time;

public class Transaction {
	
    private int transactionId;
    private Time time;	
    private String transactionMode;
    private boolean isSuccess;
    private int amount;
    private Date date;
    

    public Transaction() {
        isSuccess = false;
        transactionMode = "Offline";
    }

    public Transaction(int transactionId, int amount, Date date, Time time, String transactionMode, boolean isSuccess) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.transactionMode = transactionMode;
        this.isSuccess = isSuccess;
    }

   
    public int getTransactionId() {
        return transactionId;
    }

    
    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    
    public int getAmount() {
        return amount;
    }

    
    public void setAmount(int amount) {
        this.amount = amount;
    }

    
    public Date getDate() {
        return date;
    }

    
    public void setDate(Date date) {
        this.date = date;
    }

   
    public Time getTime() {
        return time;
    }

    
    public void setTime(Time time) {
        this.time = time;
    }

    
    public String getTransactionMode() {
        return transactionMode;
    }

   
    public void setTransactionMode(String transactionMode) {
        this.transactionMode = transactionMode;
    }

    
    public boolean getIsSuccess() {
        return isSuccess;
    }

   
    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    @Override
    public String toString() {
        return "{" +
            " transactionId='" + getTransactionId() + "'" +
            ", amount='" + getAmount() + "'" +
            ", date='" + getDate() + "'" +
            ", time='" + getTime() + "'" +
            ", transactionMode='" + getTransactionMode() + "'" +
            ", isSuccess='" + getIsSuccess() + "'" +
            "}";
    }

}
