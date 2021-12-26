package com.dbms.spark.dao;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbms.spark.models.Batch;
import com.dbms.spark.models.Course;
import com.dbms.spark.models.Enrollment;
import com.dbms.spark.models.Test;

@Transactional
@Repository
public class TestDao {
	
    @Autowired
    private JdbcTemplate jt;

    // save test details 
    
    public void save(Test test) {
    	
        String sql = "INSERT INTO test (testName, roomNumber, testDate, startTime, endTime, maximumMarks , courseId , batchId ) VALUES (?, ?, ?, ?, ?, ?, ? , ?)";
        
        jt.update(sql, 
        		test.getTestName(), 
        		test.getRoomNumber(),
        		test.getTestDate(), 
        		test.getStartTime(),
                test.getEndTime(), 
                test.getMaximumMarks(), 
                test.getCourseId(),
                test.getBatchId());
    }

    // get all test details 
    
    public List<Test> getAll() {
    	
        String sql = "SELECT * FROM test NATURAL JOIN course ORDER BY testDate DESC, startTime DESC";
        
        List<Test> tests = jt.query(sql, new BeanPropertyRowMapper<>(Test.class));
        return tests;
    }

    // get all tests of a course and order by start time 
   
    public List<Test> getAllByCourseId(String courseId) {
    	
        String sql = "SELECT * FROM test NATURAL JOIN course WHERE courseId = ? ORDER BY testDate DESC, startTime DESC";
        
        return jt.query(sql, new Object[] {courseId},  new  RowMapper<Test>() {

    	    
    	    public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
    	        Course course = (new BeanPropertyRowMapper<>(Course.class)).mapRow(rs, rowNum);

    	        Test test = (new BeanPropertyRowMapper<>(Test.class)).mapRow(rs, rowNum);
    	        test.setCourseId(course.getCourseId());

    	        return test;
    	    }
    	});
        
    }
    
    // get all tests of a batch and order by start time 
    
    public List<Test> getAllByBatchId(String batchId) {
    	
        String sql = "SELECT * FROM test NATURAL JOIN course WHERE batchId = ? ORDER BY testDate DESC, startTime DESC";
        
        return jt.query(sql, new Object[] {batchId},  new  RowMapper<Test>() {

    	    
        	 public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
     	    	
     	        Course course = (new BeanPropertyRowMapper<>(Course.class)).mapRow(rs, rowNum);
     	        
     	        Batch batch = (new BeanPropertyRowMapper<>(Batch.class)).mapRow(rs, rowNum);
     	        batch.setCourse(course);

     	        Test test = (new BeanPropertyRowMapper<>(Test.class)).mapRow(rs, rowNum);
     	        test.setCourseId(course.getCourseId());
     	        test.setBatchId(batch.getBatchId());
                
     	        return test;
     	    }
    	});
        
    }

    // get the tests given by a student
    
    public List<Map<String, Object>> getAllByStudentId(int studentId) {
    	
        String sql = "SELECT * FROM test NATURAL JOIN course NATURAL JOIN enrollment LEFT JOIN result USING (testId, studentId) WHERE studentId = ? ORDER BY testDate DESC, startTime DESC";
        
        List<Map<String, Object>> tests = jt.queryForList(sql, new Object[] { studentId });
        return tests;
    }

    // get a test 
    
    public Test get(int testId) {
        try {
        	
            String sql = "SELECT * FROM test NATURAL JOIN course WHERE testId = ?";
            
            return jt.queryForObject(sql, new Object[] { testId }, new  RowMapper<Test>() {

        	    
        	    public Test mapRow(ResultSet rs, int rowNum) throws SQLException {
        	        
        	        Test test = (new BeanPropertyRowMapper<>(Test.class)).mapRow(rs, rowNum);
        	       
        	        return test;
        	    }
        	});
            
           
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // update test 
    
    public void update(Test test) {
    	
        String sql = "UPDATE test SET testName = ?, roomNumber = ?, testDate = ?, startTime = ?, endTime = ?, maximumMarks = ? WHERE testId = ?";
        
        jt.update(sql,
        		test.getTestName(),
        		test.getRoomNumber(), 
        		test.getTestDate(), 
        		test.getStartTime(), 
        		test.getEndTime(),
                test.getMaximumMarks(), 
                test.getTestId());
    }

    // delete test 
    
    public void delete(int testId) {
    	
        String sql = "DELETE FROM test WHERE testId = ?";
        
        jt.update(sql, testId);
    }

}
