package com.dbms.spark.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbms.spark.models.Guardian;
import com.dbms.spark.models.Student;
import com.dbms.spark.models.User;
import com.dbms.spark.util.PreparedStatementUtil;

@Transactional
@Repository
public class StudentDao {
	
    @Autowired
    private JdbcTemplate jt;

    @Autowired
    private PreparedStatementUtil preparedStatementUtil;

   
    // save the student 
    
    public Student save(Student student) {
        String sql = "INSERT INTO student (dateOfBirth, houseNumber, street, city, state, percentage10th , schoolAttending, userId) VALUES (?, ?, ?, ?, ?, ?, ?, ? )";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jt.update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[] {"studentId"});
                preparedStatementUtil.setParameters(preparedStatement, 
                		student.getDateOfBirth(),
                        student.getHouseNumber(),
                        student.getStreet(), 
                        student.getCity(), 
                        student.getState(), 
                        student.getPercentage10th(),
                        student.getSchoolAttending(), 
                        student.getUser().getUserId());
                return preparedStatement;
            }
        }, keyHolder);
        int studentId = keyHolder.getKey().intValue();
        student.setStudentId(studentId);
        return student;
    }

    // get all the students along with his details from user table 
   
    public List<Student> getAll() {
    	
        String sql = "SELECT * FROM student NATURAL JOIN user";
        
        return  jt.query(sql, new RowMapper<Student>(){

           
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

                Student student = (new BeanPropertyRowMapper<>(Student.class)).mapRow(rs, rowNum);
                student.setUser(user);

                Guardian guardian = (new BeanPropertyRowMapper<>(Guardian.class)).mapRow(rs, rowNum);
                student.setGuardian(guardian);

                return student;
            }
        });
       
    }

    // get all students enrolled in a particular course 
    
    public List<Student> getAllByCourseId(String courseId) {
    	
        String sql = "SELECT * FROM student NATURAL JOIN user NATURAL JOIN enrollment WHERE courseId = ?";
        
       return jt.query(sql, new Object[] { courseId },new RowMapper<Student>(){

           
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

                Student student = (new BeanPropertyRowMapper<>(Student.class)).mapRow(rs, rowNum);
                student.setUser(user);

                Guardian guardian = (new BeanPropertyRowMapper<>(Guardian.class)).mapRow(rs, rowNum);
                student.setGuardian(guardian);

                return student;
            }
        });
       
    }
    
 public List<Student> getAllByBatchId(String batchId) {
    	
        String sql = "SELECT * FROM student NATURAL JOIN user NATURAL JOIN enrollment WHERE batchId = ?";
        
       return jt.query(sql, new Object[] { batchId },new RowMapper<Student>(){

           
            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

                Student student = (new BeanPropertyRowMapper<>(Student.class)).mapRow(rs, rowNum);
                student.setUser(user);

                Guardian guardian = (new BeanPropertyRowMapper<>(Guardian.class)).mapRow(rs, rowNum);
                student.setGuardian(guardian);

                return student;
            }
        });
       
    }
    
    // get a particular student along with his user details 
    
    public Student getByUserId(int userId) {
        try {
        	
            String sql = "SELECT * FROM student NATURAL JOIN user WHERE userId = ?";
            
            return (Student) jt.queryForObject(sql, new Object[] { userId },new RowMapper<Student>(){

                
                public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

                    Student student = (new BeanPropertyRowMapper<>(Student.class)).mapRow(rs, rowNum);
                    student.setUser(user);

                    Guardian guardian = (new BeanPropertyRowMapper<>(Guardian.class)).mapRow(rs, rowNum);
                    student.setGuardian(guardian);

                    return student;
                }
            });
            
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // get a particular student along with his guardian details 
   
    public Student get(int studentId) {
        try {
            String sql = "SELECT * FROM student NATURAL JOIN user NATURAL JOIN guardian WHERE studentId = ?";
            
            return (Student) jt.queryForObject(sql, new Object[] { studentId }, new RowMapper<Student>(){

                
                public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

                    Student student = (new BeanPropertyRowMapper<>(Student.class)).mapRow(rs, rowNum);
                    student.setUser(user);

                    Guardian guardian = (new BeanPropertyRowMapper<>(Guardian.class)).mapRow(rs, rowNum);
                    student.setGuardian(guardian);

                    return student;
                }
            });
            
            
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

   
   

    // get student id of a student with his user id 
    
    public Integer getStudentIdByUserId(int userId) {
    	
        try {
            String sql = "SELECT studentId FROM student WHERE userId = ?";
            
            return jt.queryForObject(sql, new Object[] { userId }, Integer.class);
            
        } 
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

   
    // update details of a student 
    
    public void update(Student student) {
        String sql = "UPDATE student SET dateOfBirth = ?, houseNumber = ?, street = ?, city = ?, state = ? , percentage10th = ? ,schoolAttending = ? WHERE studentId = ?";
        jt.update(sql, 
        		student.getDateOfBirth(), 
        		student.getHouseNumber(),
                student.getStreet(), 
                student.getCity(), 
                student.getState(),
                student.getPercentage10th(),
                student.getSchoolAttending(), 
                student.getStudentId());
    }

   
    public void delete(int studentId) {
        String sql = "DELETE FROM student WHERE studentId = ?";
        jt.update(sql, studentId);
    }

}
