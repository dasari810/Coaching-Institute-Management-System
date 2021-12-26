package com.dbms.spark.dao;


import com.dbms.spark.models.Guardian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class GuardianDao {
	
    @Autowired
    private JdbcTemplate  jt;

   // save the guardian details 
    
    public void save(Guardian guardian) {
    	
        String sql = "INSERT INTO guardian (name, studentId, occupation, address, email, relationWithStudent) VALUES (?, ?, ?, ?, ?, ?)";
        
        jt.update(sql, 
        		guardian.getName(), 
        		guardian.getStudentId(), 
        		guardian.getOccupation(),
                guardian.getAddress(), 
                guardian.getEmail(), 
                guardian.getRelationWithStudent());
    }

    // get the guardian name of a particular student 
   
    public String getNameByStudentId(int studentId) {
        try {
        	
            String sql = "SELECT name FROM guardian WHERE studentId = ?";
            
            String guardianName = jt.queryForObject(sql, new Object[] { studentId }, String.class);
            
            return guardianName;
            
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // update the guardian details 
    
    public void update(Guardian guardian) {
    	
        String sql = "UPDATE guardian SET name = ?, occupation = ?, address = ?, email = ?, relationWithStudent = ? WHERE studentId = ?";
        
        jt.update(sql, 
        		guardian.getName(), 
        		guardian.getOccupation(), 
        		guardian.getAddress(),
                guardian.getEmail(),
                guardian.getRelationWithStudent(), 
                guardian.getStudentId());
    }

}
