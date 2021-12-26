package com.dbms.spark.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import com.dbms.spark.models.Batch;
import com.dbms.spark.models.TeacherBatchDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class  TeacherBatchDao {
	
    @Autowired
    private JdbcTemplate jt;

    // save a teacher batch detail 
   
    public void save(String teacherId, String batchId, String courseId) {
    	
        String sql = "INSERT INTO teacherbatchdetails (teacherId, batchId, courseId) VALUES (?, ?, ?)";
        
        jt.update(sql, teacherId, batchId, courseId);
    }

   
    // get all teacher batch details 
    
    public List<TeacherBatchDetails> getAll() {
    	
        String sql = "SELECT * FROM teacherbatchdetails NATURAL JOIN batch";
        
        return jt.query(sql, new RowMapper<TeacherBatchDetails>() {

           
            public TeacherBatchDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
                Batch batch = (new BeanPropertyRowMapper<>(Batch.class)).mapRow(rs, rowNum);

                TeacherBatchDetails teacherBatch = (new BeanPropertyRowMapper<>(TeacherBatchDetails.class)).mapRow(rs, rowNum);
                teacherBatch.setBatch(batch);
                return teacherBatch;
            }
        }
);
    }

    // delete a teacher batch detail
   
    public void delete(String teacherId, String batchId, String courseId) {
    	
        String sql = "DELETE FROM teacherbatchdetails WHERE teacherId = ? AND batchId = ? AND courseId = ?";
        
        jt.update(sql, teacherId, batchId, courseId);
    }

}
