package com.dbms.spark.dao;


import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

import com.dbms.spark.models.Result;
import com.dbms.spark.models.Student;
import com.dbms.spark.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class  ResultDao {
	
    @Autowired
    private JdbcTemplate jt;

   // save a result 
    
    public void save(Result result) {
    	
        String sql = "INSERT INTO result (studentId, testId, marksScored ) VALUES (?, ?, ? )";
        
        jt.update(sql, 
        		result.getStudent().getStudentId(), 
        		result.getTestId(), 
        		result.getMarksScored());
    }

    // get all results  of a test in descending order 
    
    public List<Result> getAllByTestId(int testId) {
    	
        String sql = "SELECT * FROM result NATURAL JOIN student NATURAL JOIN user WHERE testId = ? ORDER BY marksScored DESC";
        
        return jt.query(sql, new Object[] { testId }, new RowMapper<Result>() {

           
            public Result mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

                Student student = (new BeanPropertyRowMapper<>(Student.class)).mapRow(rs, rowNum);
                student.setUser(user);

                Result result = (new BeanPropertyRowMapper<>(Result.class)).mapRow(rs, rowNum);
                result.setStudent(student);

                return result;
            }
        }
 );
       
    }


    // get a result of a test of a student 
    
    public Result get(int testId, int studentId) {
        try {
        	
            String sql = "SELECT * FROM result NATURAL JOIN student NATURAL JOIN user WHERE testId = ? AND studentId = ?";
            
           return jt.queryForObject(sql, new Object[] { testId, studentId }, new RowMapper<Result>() {

               
               public Result mapRow(ResultSet rs, int rowNum) throws SQLException {
                   User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

                   Student student = (new BeanPropertyRowMapper<>(Student.class)).mapRow(rs, rowNum);
                   student.setUser(user);

                   Result result = (new BeanPropertyRowMapper<>(Result.class)).mapRow(rs, rowNum);
                   result.setStudent(student);

                   return result;
               }
           } );
            
           
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    // check if a student appeared  in test 

    
    public int isStudentAppearedInTest(int testId, int studentId) {
        String sql = "SELECT COUNT(*) FROM result WHERE testId = ? AND studentId = ?";
        return jt.queryForObject(sql, new Object[] { testId, studentId }, Integer.class);
    }

  
    // update result 
    
    public void updateMarks(Result result) {
        String sql = "UPDATE result SET marksScored = ? WHERE testId = ? AND studentId = ?";
        jt.update(sql, result.getMarksScored(), result.getTestId(), result.getStudent().getStudentId());
    }

    // delete result 
    
    public void delete(int testId, int studentId) {
        String sql = "DELETE FROM result WHERE testId = ? AND studentId = ?";
        jt.update(sql, testId, studentId);
    }

}
