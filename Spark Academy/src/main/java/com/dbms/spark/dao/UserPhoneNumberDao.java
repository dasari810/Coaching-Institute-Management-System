package com.dbms.spark.dao;


import java.util.List;

import com.dbms.spark.models.UserPhoneNumber;

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
public class UserPhoneNumberDao {
    @Autowired
    private JdbcTemplate template;

    // save the phone number of a  user 
    
    public void save(UserPhoneNumber userPhoneNumber) {
        try {
            String sql = "INSERT INTO userphonenumber (phoneNumber, userId) VALUES (?, ?)";
            template.update(sql, userPhoneNumber.getPhoneNumber(), userPhoneNumber.getUserId());
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The given phone number already exists");
        }
    }

   // get all the phone numbers of a  user  
   
    public List<UserPhoneNumber> getByUserId(int userId) {
        String sql = "SELECT * FROM userphonenumber WHERE userId = ?";
        List<UserPhoneNumber> phoneNumbers = template.query(sql, new Object[] { userId },
                new BeanPropertyRowMapper<>(UserPhoneNumber.class));
        return phoneNumbers;
    }
    
   
  // get userId from phone number
    
    public Integer getuserIdByPhoneNumber(String  phoneNumber , int userId ) {
    	
        try {
            String sql = "SELECT userId FROM userphonenumber WHERE phoneNumber = ? AND userId = ?";
            
            return template.queryForObject(sql, new Object[] { phoneNumber , userId }, Integer.class);
            
        } 
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    

    // update a  phone number of a user 
    
    public void update(UserPhoneNumber userPhoneNumber, int oldPhoneNumber) {
        String sql = "UPDATE userphonenumber SET phoneNumber = ? WHERE phoneNumber = ? AND userId = ?";
        template.update(sql, userPhoneNumber.getPhoneNumber(), oldPhoneNumber, userPhoneNumber.getUserId());
    }

   // delete a phone number of a user 
    
    public void delete(UserPhoneNumber userPhoneNumber) {
        String sql = "DELETE FROM userphonenumber WHERE phoneNumber = ? AND userId = ?";
        template.update(sql, userPhoneNumber.getPhoneNumber(), userPhoneNumber.getUserId());
    }

}
