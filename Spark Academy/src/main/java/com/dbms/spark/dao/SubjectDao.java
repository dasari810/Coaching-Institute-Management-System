package com.dbms.spark.dao;


import java.util.List;

import com.dbms.spark.models.Subject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;


@Transactional
@Repository
public class SubjectDao {
	
    @Autowired
    private JdbcTemplate jt;

    // save subject details 
    
    public void save(Subject subject) {
    	
        String sql = "INSERT INTO subject (subjectId, subjectName, description) VALUES (?, ?, ?)";
        
        jt.update(sql, subject.getSubjectId(), subject.getSubjectName(), subject.getDescription());
    }

    // get all subjects 
    
    public List<Subject> getAll() {
    	
        String sql = "SELECT * FROM subject";
        
        List<Subject> subjects = jt.query(sql, new BeanPropertyRowMapper<>(Subject.class));
        return subjects;
    }

    // get a subject ( by subjectId )
    
    public Subject get(String subjectId) {
        try {
        	
            String sql = "SELECT * FROM subject WHERE subjectId = ?";
            
            Subject subject = jt.queryForObject(sql, new Object[] { subjectId },
                    new BeanPropertyRowMapper<>(Subject.class));
            return subject;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

   
    // get a subject ( by subjectName ) 
    
    public Subject getBySubjectName(String subjectName) {
        try {
        	
            String sql = "SELECT * FROM subject WHERE subjectName = ?";
            
            Subject subject = jt.queryForObject(sql, new Object[] { subjectName },
                    new BeanPropertyRowMapper<>(Subject.class));
            return subject;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // get all the subjects in a course 
    
    public List<Subject> getSubjectsInCourse(String courseId) {
    	
        String sql = "SELECT * FROM subject NATURAL JOIN coursesubjectdetails WHERE courseId = ?";
        
        List<Subject> subjects = jt.query(sql, new Object[] { courseId },
                new BeanPropertyRowMapper<>(Subject.class));
        return subjects;
    }

    // get all those subjects not in a specific course 
    
    public List<Subject> getSubjectsNotInCourse(String courseId) {
    	
        String sql = "SELECT * FROM subject WHERE subjectId NOT IN (SELECT subjectId FROM coursesubjectdetails WHERE courseId = ?)";
        
        List<Subject> subjects = jt.query(sql, new Object[] { courseId },
                new BeanPropertyRowMapper<>(Subject.class));
        return subjects;
    }

    // get the all subjectId of the subjects read by a student 
    
    public List<String> getSubjectCodeByStudentId(int studentId) {
    	
        String sql = "SELECT DISTINCT(S.subjectId) FROM subject S, coursesubjectdetails CS, enrollment E WHERE E.studentId = ? AND E.courseId = CS.courseId AND CS.subjectId = S.subjectId";
        
        List<String> subjects = jt.queryForList(sql, new Object[] { studentId }, String.class);
        return subjects;
    }
    
    // get the subjectId of the subject taught by a teacher 

   
    public String getSubjectCodeByTeacherId(int teacherId) {
    	
        String sql = "SELECT subjectId FROM teacher WHERE teacherId = ?";
        
        String subject = jt.queryForObject(sql, new Object[] { teacherId }, String.class);
        return subject;
    }

    // update subject 
    
    public void update(Subject subject) {
    	
        String sql = "UPDATE subject SET subjectName = ?, description = ? WHERE subjectId = ?";
        
        jt.update(sql, subject.getSubjectName(), subject.getDescription(), subject.getSubjectId());
    }

    // delete subject 
    
    public void delete(String subjectId) {
    	
        String sql = "DELETE FROM subject WHERE subjectId = ?";
        
        jt.update(sql, subjectId);
    }

}
