package com.dbms.spark.dao;


import java.util.List;

import com.dbms.spark.models.GuardianPhoneNumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Transactional
@Repository
public class GuardianPhoneNumberDao {
    @Autowired
    private JdbcTemplate jt;

    // save the guardian phone number 
    
    public void save(GuardianPhoneNumber guardianPhoneNumber) {
    	
        try {
        	
            String sql = "INSERT INTO guardianphonenumber (phoneNumber, name, studentId) VALUES (?, ?, ?)";
            
            jt.update(sql, 
            		guardianPhoneNumber.getPhoneNumber(), 
            		guardianPhoneNumber.getName(), 
            		guardianPhoneNumber.getStudentId());
            
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The given phone number already exists");
        }
    }
    
    
    // get all the guardian phone numbers of a particular student

    
    public List<GuardianPhoneNumber> getByStudentId(int studentId) {
    	
        String sql = "SELECT * FROM guardianphonenumber WHERE studentId = ?";
        
        List<GuardianPhoneNumber> phoneNumbers = jt.query(sql, new Object[] { studentId },
                new BeanPropertyRowMapper<>(GuardianPhoneNumber.class));
        
        return phoneNumbers;
    }
    
    
    
  public Integer getuserIdByPhoneNumber(String  phoneNumber , int studentId ) {
    	
        try {
            String sql = "SELECT studentId FROM guardianphonenumber WHERE phoneNumber = ? AND studentId = ?";
            
            return jt.queryForObject(sql, new Object[] { phoneNumber , studentId }, Integer.class);
            
        } 
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    
    // update a particular phone number of a guardian 

    
    public void update(GuardianPhoneNumber guardianPhoneNumber, int oldPhoneNumber) {
    	
        String sql = "UPDATE guardianphonenumber SET phoneNumber = ? WHERE phoneNumber = ? AND name = ? AND studentId = ?";
        
        jt.update(sql, 
        		guardianPhoneNumber.getPhoneNumber(), 
        		oldPhoneNumber, guardianPhoneNumber.getName(), 
        		guardianPhoneNumber.getStudentId());
    }

    // delete a particular phone number of a guardian 
   
    public void delete(GuardianPhoneNumber guardianPhoneNumber) {
    	
        String sql = "DELETE FROM guardianphonenumber WHERE phoneNumber = ? AND name = ? AND studentId = ?";
        
        jt.update(sql,
        		guardianPhoneNumber.getPhoneNumber(), 
        		guardianPhoneNumber.getName(), 
        		guardianPhoneNumber.getStudentId());
    }
    

}
