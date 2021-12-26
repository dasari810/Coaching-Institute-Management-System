package com.dbms.spark.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;


import com.dbms.spark.models.CourseSubjectDetails;
import com.dbms.spark.models.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class CourseSubjectDao {
	
    @Autowired
    private JdbcTemplate jt;

    // save 
    
    public void save(String courseId, String subjectId) {
    	
        String sql = "INSERT INTO coursesubjectdetails (courseId, subjectId) VALUES (?, ?)";
        
        jt.update(sql, courseId, subjectId);
    }

    // get all 
   
    public List<CourseSubjectDetails> getAll() {
    	
        String sql = "SELECT * FROM coursesubjectdetails NATURAL JOIN subject";
        
        return jt.query(sql, new RowMapper<CourseSubjectDetails>() {

            @Override
            public CourseSubjectDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                Subject subject = (new BeanPropertyRowMapper<>(Subject.class)).mapRow(rs, rowNum);

                CourseSubjectDetails courseSubject = (new BeanPropertyRowMapper<>(CourseSubjectDetails.class)).mapRow(rs, rowNum);
                courseSubject.setSubject(subject);
                return courseSubject;
            }
        });
    }

    // delete 
    
    public void delete(String courseId, String subjectId) {
    	
        String sql = "DELETE FROM coursesubjectdetails WHERE courseId = ? AND subjectId = ?";
        
        jt.update(sql, courseId, subjectId);
    }

}
