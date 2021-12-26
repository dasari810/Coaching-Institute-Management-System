package com.dbms.spark.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import com.dbms.spark.models.Course;


@Transactional
@Repository
public class CourseDao {
    @Autowired
    private JdbcTemplate jt;

    // save the course details 
    
    public void save(Course course) {
    	
        String sql = "INSERT INTO course (courseId, courseName, description) VALUES (?, ?, ?)";
        
        jt.update(sql, course.getCourseId(), course.getCourseName(), course.getDescription());
    }

    // get all courses 
    
    public List<Course> getAll() {
    	
        String sql = "SELECT * FROM course";
        
        List<Course> courses = jt.query(sql, new BeanPropertyRowMapper<>(Course.class));
        return courses;
    }

    // get list of courses 
    
    public List<Map<String, Object>> getAllList() {
    	
        String sql = "SELECT * FROM course";
        
        List<Map<String, Object>> courses = jt.queryForList(sql);
        return courses;
    }

    // get a course 
    
    public Course get(String courseId) {
        try {
        	
            String sql = "SELECT * FROM course WHERE courseId = ?";
            
            Course course = jt.queryForObject(sql, new Object[] { courseId },
                    new BeanPropertyRowMapper<>(Course.class));
            return course;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // get all the coursesId enrolled by a particular student 
    
    public List<String> getCourseIdByStudentId(int studentId) {
    	
        String sql = "SELECT DISTINCT(courseId) FROM enrollment WHERE studentId = ?";
        
        return jt.queryForList(sql, new Object[] { studentId }, String.class);
    }

    // update course 
    
    public void update(Course course) {
    	
        String sql = "UPDATE course SET courseName = ?, description = ? WHERE courseId = ?";
        
        jt.update(sql, course.getCourseName(), course.getDescription(), course.getCourseId());
    }

    // delete course 
   
    public void delete(String courseId) {
    	
        String sql = "DELETE FROM course WHERE courseId = ?";
        
        jt.update(sql, courseId);
    }

}
