package com.dbms.spark.models;

import java.sql.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Payroll {
    @NotEmpty
    private String paymentRefNo;

    @NotNull
    private Date dateCredited;
  
    @Min(2020)
    private int year;

    private double salaryCredited;
    
    @Min(1)
    @Max(12)
    private int month;


    private Employee Employee;

    public Payroll() {
    }

    public Payroll(String paymentRefNo, int month, int year, double salaryCredited, Date dateCredited, Employee Employee) {
        this.paymentRefNo = paymentRefNo;
        this.month = month;
        this.year = year;
        this.salaryCredited = salaryCredited;
        this.dateCredited = dateCredited;
        this.Employee = Employee;
    }

    /**
     * @return String return the paymentRefNo
     */
    public String getPaymentRefNo() {
        return paymentRefNo;
    }

    
    public void setPaymentRefNo(String paymentRefNo) {
        this.paymentRefNo = paymentRefNo;
    }

   
    public int getMonth() {
        return month;
    }

    
    public void setMonth(int month) {
        this.month = month;
    }

   
    public int getYear() {
        return year;
    }

    
    public void setYear(int year) {
        this.year = year;
    }

    
    public double getSalaryCredited() {
        return salaryCredited;
    }

   
    public void setSalaryCredited(double salaryCredited) {
        this.salaryCredited = salaryCredited;
    }

    
    public Date getDateCredited() {
        return dateCredited;
    }

   
    public void setDateCredited(Date dateCredited) {
        this.dateCredited = dateCredited;
    }

   
    public Employee getEmployee() {
        return Employee;
    }

   
    public void setEmployee(Employee Employee) {
        this.Employee = Employee;
    }

    @Override
    public String toString() {
        return "{" +
            " paymentRefNo='" + getPaymentRefNo() + "'" +
            ", month='" + getMonth() + "'" +
            ", year='" + getYear() + "'" +
            ", salaryCredited='" + getSalaryCredited() + "'" +
            ", dateCredited='" + getDateCredited() + "'" +
            ", Employee='" + getEmployee() + "'" +
            "}";
    }

}
