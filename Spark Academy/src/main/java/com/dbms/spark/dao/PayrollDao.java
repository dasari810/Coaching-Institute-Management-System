package com.dbms.spark.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.dbms.spark.models.Employee;
import com.dbms.spark.models.Payroll;
import com.dbms.spark.models.User;

@Transactional
@Repository
public class  PayrollDao {
    @Autowired
    private JdbcTemplate jt;

    // save the payroll 
    
    public void save(Payroll payroll) {
        String sql = "INSERT INTO payroll (paymentRefNo, month, year, salaryCredited, dateCredited, employeeId) VALUES (?, ?, ?, ?, ?, ?)";
        jt.update(sql, 
        		payroll.getPaymentRefNo(), 
        		payroll.getMonth(), 
        		payroll.getYear(),
                payroll.getSalaryCredited(), 
                payroll.getDateCredited(), 
                payroll.getEmployee().getEmployeeId());
    }

    
   // get the payrolls of all the employees alnog with their user details 
    
    public List<Payroll> getAll() {
    	
        String sql = "SELECT * FROM payroll NATURAL JOIN employee NATURAL JOIN user";
        
        return  jt.query(sql, new RowMapper<Payroll>() {
       	 
            
            public Payroll mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

                Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
                employee.setUser(user);

                Payroll payroll = (new BeanPropertyRowMapper<>(Payroll.class)).mapRow(rs, rowNum);
                payroll.setEmployee(employee);
                return payroll;
            }
        });
       
    }
    
    // get a particular payroll 
    
    public Payroll get(String paymentRefNo) {
        try {
            String sql = "SELECT * FROM payroll NATURAL JOIN employee NATURAL JOIN user WHERE paymentRefNo = ?";
            
            return  jt.queryForObject(sql, new Object[] { paymentRefNo }, new RowMapper<Payroll>() {
            	 
                
                public Payroll mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

                    Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
                    employee.setUser(user);

                    Payroll payroll = (new BeanPropertyRowMapper<>(Payroll.class)).mapRow(rs, rowNum);
                    payroll.setEmployee(employee);
                    return payroll;
                }
            });
            
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }


    // get all the payroll of an employee 
   
    public List<Payroll> getAllByEmployeeId(int employeeId) {
    	
        String sql = "SELECT * FROM payroll NATURAL JOIN employee NATURAL JOIN user WHERE employeeId = ?";
        
        return  jt.query(sql, new Object[] { employeeId }, new RowMapper<Payroll>() {
       	 
            
            public Payroll mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

                Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
                employee.setUser(user);

                Payroll payroll = (new BeanPropertyRowMapper<>(Payroll.class)).mapRow(rs, rowNum);
                payroll.setEmployee(employee);
                return payroll;
            }
        } );
       
    }

    
    // get the payroll of all employee of a particular month in a year 
    
    public List<Payroll> getAllByMonthYear(int month, int year) {
    	
        String sql = "SELECT * FROM payroll NATURAL JOIN employee NATURAL JOIN user WHERE month = ? AND year = ?";
        
       return jt.query(sql, new Object[] { month, year }, new RowMapper<Payroll>() {
      	 
           
           public Payroll mapRow(ResultSet rs, int rowNum) throws SQLException {
               User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

               Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
               employee.setUser(user);

               Payroll payroll = (new BeanPropertyRowMapper<>(Payroll.class)).mapRow(rs, rowNum);
               payroll.setEmployee(employee);
               return payroll;
           }
       } );
    }

    // update payroll 
    
    public void update(Payroll payroll) {
    	
        String sql = "UPDATE payroll SET month = ?, year = ?, salaryCredited = ?, dateCredited = ?, employeeId = ? WHERE paymentRefNo = ?";
        
        jt.update(sql, 
        		payroll.getMonth(), 
        		payroll.getYear(), 
        		payroll.getSalaryCredited(),
                payroll.getDateCredited(), 
                payroll.getEmployee().getEmployeeId(), 
                payroll.getPaymentRefNo());
    }

   // delete payroll 
    
    public void delete(String paymentRefNo) {
    	
        String sql = "DELETE FROM payroll WHERE paymentRefNo = ?";
        
        jt.update(sql, paymentRefNo);
    }

}
