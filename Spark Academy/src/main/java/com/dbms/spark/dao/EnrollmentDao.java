package com.dbms.spark.dao;

import java.util.List;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


import com.dbms.spark.models.Enrollment;
import com.dbms.spark.models.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class  EnrollmentDao {
	
    @Autowired
    private JdbcTemplate jt;

    
    // save enrollment details 
    
    public void save(Enrollment enrollment) {
    	
        String sql = "INSERT INTO enrollment (studentId, batchId, courseId, transactionId, joinDate, endDate) VALUES (?, ?, ?, ?, ?, ?)";
        
        jt.update(sql, 
        		enrollment.getStudentId(), 
        		enrollment.getBatchId(),
        		enrollment.getCourseId(),
        		enrollment.getTransaction().getTransactionId(),
                enrollment.getJoinDate(), 
                enrollment.getEndDate());
    }

    // get all enrollments 
    
    public List<Enrollment> getAll() {
    	
        String sql = "SELECT * FROM enrollment";
        
        List<Enrollment> enrollments = jt.query(sql, new BeanPropertyRowMapper<>(Enrollment.class));
        return enrollments;
    }
    
    // get all enrollments of a student 

    
    public List<Enrollment> getAllByStudentId(int studentId) {
    	
        String sql = "SELECT * FROM enrollment WHERE studentId = ?";
        
        List<Enrollment> enrollments = jt.query(sql, new Object[] { studentId }, new BeanPropertyRowMapper<>(Enrollment.class));
        return enrollments;
    }

    // get all enrollments of a course 
    
    public List<Enrollment> getAllByCourseId(String courseId) {
    	
        String sql = "SELECT * FROM enrollment WHERE courseId = ?";
        
        List<Enrollment> enrollments = jt.query(sql, new Object[] { courseId },
                new BeanPropertyRowMapper<>(Enrollment.class));
        return enrollments;
    }

    // get all enrollments of a batch 
    
    public List<Enrollment> getAllByBatch(String courseId, String batchId) {
    	
        String sql = "SELECT * FROM enrollment WHERE courseId = ? AND batchId = ?";
        
        List<Enrollment> enrollments = jt.query(sql, new Object[] { courseId, batchId },
                new BeanPropertyRowMapper<>(Enrollment.class));
        return enrollments;
    }

    // get an enrollment along with transaction details 
    
    public Enrollment get(int enrollmentId) {
    	
        try {
        	
            String sql = "SELECT * FROM enrollment NATURAL JOIN transaction WHERE enrollmentId = ?";
            
            return jt.queryForObject(sql, new Object[] { enrollmentId }, new RowMapper<Enrollment>() {

                
                public Enrollment mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Transaction transaction = (new BeanPropertyRowMapper<>(Transaction.class)).mapRow(rs, rowNum);

                    Enrollment enrollment = (new BeanPropertyRowMapper<>(Enrollment.class)).mapRow(rs, rowNum);
                    enrollment.setTransaction(transaction);
                    return enrollment;
                }
            });
            
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // get enrollment details of a student in a course 
   
    public Enrollment getByStudentAndCourse(int studentId, String courseId) {
        try {
        	
            String sql = "SELECT * FROM enrollment NATURAL JOIN transaction WHERE studentId = ? AND courseId = ?";
            
            return jt.queryForObject(sql, new Object[] { studentId, courseId }, new RowMapper<Enrollment>() {

                
                public Enrollment mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Transaction transaction = (new BeanPropertyRowMapper<>(Transaction.class)).mapRow(rs, rowNum);

                    Enrollment enrollment = (new BeanPropertyRowMapper<>(Enrollment.class)).mapRow(rs, rowNum);
                    enrollment.setTransaction(transaction);
                    return enrollment;
                }
            }
);
          
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // update enrollment 
    
    
    public void update(Enrollment enrollment) {
    	
        String sql = "UPDATE enrollment SET joinDate = ?, endDate = ? WHERE enrollmentId = ?";
        
        jt.update(sql, 
        		enrollment.getJoinDate(),
        		enrollment.getEndDate(), 
        		enrollment.getEnrollmentId());
    }

   // delete enrollment 
    
    public void delete(int enrollmentId) {
    	
        String sql = "DELETE FROM enrollment WHERE enrollmentId = ?";
        
        jt.update(sql, enrollmentId);
    }

}
