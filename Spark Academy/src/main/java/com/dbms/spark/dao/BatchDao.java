package com.dbms.spark.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.dbms.spark.models.Batch;
import com.dbms.spark.models.Course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class BatchDao {
    @Autowired
    private JdbcTemplate jt;

    // save batch details 
    
    public void save(Batch batch) {
    	
        String sql = "INSERT INTO batch (batchId, courseId, batchName, fee, roomNumber, startTime, endTime) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        jt.update(sql, 
        		batch.getBatchId(), 
        		batch.getCourse().getCourseId(), 
        		batch.getBatchName(),
        		batch.getFee(),
                batch.getRoomNumber(),
                batch.getStartTime(), 
                batch.getEndTime());
    }

    // get all batches along with their course details 
    
    public List<Batch> getAll() {
    	
        String sql = "SELECT * FROM batch NATURAL JOIN course";
        
        return jt.query(sql, new RowMapper<Batch>() {

            public Batch mapRow(ResultSet rs, int rowNum) throws SQLException {
                Course course = (new BeanPropertyRowMapper<>(Course.class)).mapRow(rs, rowNum);

                Batch batch = (new BeanPropertyRowMapper<>(Batch.class)).mapRow(rs, rowNum);
                batch.setCourse(course);

                return batch;
            }
        });
        
    }
    
    // get all batches with a specific course 

    
    public List<Batch> getAllByCourseId(String courseId) {
    	
        String sql = "SELECT * FROM batch NATURAL JOIN course WHERE courseId = ?";
        
       return jt.query(sql, new Object[] {courseId}, new RowMapper<Batch>() {

           public Batch mapRow(ResultSet rs, int rowNum) throws SQLException {
               Course course = (new BeanPropertyRowMapper<>(Course.class)).mapRow(rs, rowNum);

               Batch batch = (new BeanPropertyRowMapper<>(Batch.class)).mapRow(rs, rowNum);
               batch.setCourse(course);

               return batch;
           }
       });
        
    }
    
    // get all the batches taught by a specific teacher 

   
    public List<Batch> getAllByTeacherId(int teacherId) {
    	
        String sql = "SELECT * FROM batch NATURAL JOIN course NATURAL JOIN teacherbatchdetails WHERE teacherId = ?";
        
        return jt.query(sql, new Object[] { teacherId }, new RowMapper<Batch>() {

            public Batch mapRow(ResultSet rs, int rowNum) throws SQLException {
                Course course = (new BeanPropertyRowMapper<>(Course.class)).mapRow(rs, rowNum);

                Batch batch = (new BeanPropertyRowMapper<>(Batch.class)).mapRow(rs, rowNum);
                batch.setCourse(course);

                return batch;
            }
        });
       
    }
    
    // get all the batches with a particular subject 

   
    public List<Batch> getAllBySubjectId(String subjectId) {
    	
        String sql = "SELECT * FROM batch NATURAL JOIN course NATURAL JOIN coursesubjectdetails WHERE subjectId = ?";
        
        return jt.query(sql, new Object[] { subjectId }, new RowMapper<Batch>() {

            public Batch mapRow(ResultSet rs, int rowNum) throws SQLException {
                Course course = (new BeanPropertyRowMapper<>(Course.class)).mapRow(rs, rowNum);

                Batch batch = (new BeanPropertyRowMapper<>(Batch.class)).mapRow(rs, rowNum);
                batch.setCourse(course);

                return batch;
            }
        });
        
    }

    
   
  // get a batch 
    
    public Batch get(String batchId, String courseId) {
        try {
        	
            String sql = "SELECT * FROM batch NATURAL JOIN course WHERE batchId = ? AND courseId = ?";
            
            return jt.queryForObject(sql, new Object[] { batchId, courseId }, new RowMapper<Batch>() {

                public Batch mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Course course = (new BeanPropertyRowMapper<>(Course.class)).mapRow(rs, rowNum);

                    Batch batch = (new BeanPropertyRowMapper<>(Batch.class)).mapRow(rs, rowNum);
                    batch.setCourse(course);

                    return batch;
                }
            });
           
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // get fee of a batch 
    
    public int getFee(String batchId, String courseId) {
        try {
        	
            String sql = "SELECT fee FROM batch NATURAL JOIN course WHERE batchId = ? AND courseId = ?";
            
            return jt.queryForObject(sql, new Object[] { batchId, courseId }, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
    }

    // update a batch 
    
    public void update(Batch batch) {
    	
        String sql = "UPDATE batch SET batchName = ?, fee = ?, roomNumber = ?, startTime = ?, endTime = ? WHERE batchId = ? AND courseId = ?";
        
        jt.update(sql, 
        		batch.getBatchName(), 
        		batch.getFee(), 
        		batch.getRoomNumber(),
        		batch.getStartTime(), 
        		batch.getEndTime(),
                batch.getBatchId(), 
                batch.getCourse().getCourseId());
    }
    
    // delete a batch 

   
    public void delete(String batchId, String courseId) {
    	
        String sql = "DELETE FROM batch WHERE batchId = ? AND courseId = ?";
        
        jt.update(sql, batchId, courseId);
    }

}
