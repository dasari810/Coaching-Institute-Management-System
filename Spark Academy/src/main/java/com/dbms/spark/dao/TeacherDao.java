package com.dbms.spark.dao;


import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dbms.spark.models.Employee;
import com.dbms.spark.models.Subject;
import com.dbms.spark.models.Teacher;
import com.dbms.spark.models.User;
import com.dbms.spark.util.PreparedStatementUtil;

@Transactional
@Repository
public class TeacherDao {
    @Autowired
    private JdbcTemplate jt;

    @Autowired
    private PreparedStatementUtil preparedStatementUtil;

    // save teacher details 
    
    public Teacher save(Teacher teacher) {
    	
        String sql = "INSERT INTO teacher (qualification , achievement , teachingExpirience , employeeId, subjectId) VALUES ( ?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jt.update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[] {"teacherId"});
                preparedStatementUtil.setParameters(preparedStatement, 
                		teacher.getQualification(), 
                		teacher.getAchievement(),
                        teacher.getTeachingExpirience(),  
                        teacher.getEmployee().getEmployeeId(), 
                        teacher.getSubject().getSubjectId());
                return preparedStatement;
            }
        }, keyHolder);
        int teacherId = keyHolder.getKey().intValue();
        teacher.setTeacherId(teacherId);
        return teacher;
    }

    // get all teachers along with their employee and user details 
    
    public List<Teacher> getAll() {
    	
        String sql = "SELECT * FROM teacher NATURAL JOIN employee NATURAL JOIN user NATURAL JOIN subject";
        
        return jt.query(sql, new RowMapper<Teacher>() {

           
            public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

                Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
                employee.setUser(user);

                Subject subject = (new BeanPropertyRowMapper<>(Subject.class)).mapRow(rs, rowNum);

                Teacher teacher = (new BeanPropertyRowMapper<>(Teacher.class)).mapRow(rs, rowNum);
                teacher.setEmployee(employee);
                teacher.setSubject(subject);
                return teacher;

            }
        }
        );
       
    }
    
    // get all teachers of a batch 

   
    public List<Teacher> getAllByBatch(String batchId, String courseId) {
    	
        String sql = "SELECT * FROM teacher NATURAL JOIN teacherbatchdetails NATURAL JOIN employee NATURAL JOIN user WHERE batchId = ? AND courseId = ?";
        
        return jt.query(sql, new Object[] { batchId, courseId }, new RowMapper<Teacher>() {

            
            public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

                Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
                employee.setUser(user);

                Subject subject = (new BeanPropertyRowMapper<>(Subject.class)).mapRow(rs, rowNum);

                Teacher teacher = (new BeanPropertyRowMapper<>(Teacher.class)).mapRow(rs, rowNum);
                teacher.setEmployee(employee);
                teacher.setSubject(subject);
                return teacher;

            }
        });
        
    }

    
    public List<Teacher> getTeachersInBatch(String batchId, String courseId) {
    	
        String sql = "SELECT * FROM teacher NATURAL JOIN employee NATURAL JOIN user NATURAL JOIN teacherbatchdetails WHERE batchId = ? AND courseId = ?";
        
       return jt.query(sql, new Object[] { batchId, courseId },new RowMapper<Teacher>() {

           
           public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
               User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

               Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
               employee.setUser(user);

               Subject subject = (new BeanPropertyRowMapper<>(Subject.class)).mapRow(rs, rowNum);

               Teacher teacher = (new BeanPropertyRowMapper<>(Teacher.class)).mapRow(rs, rowNum);
               teacher.setEmployee(employee);
               teacher.setSubject(subject);
               return teacher;

           }
       } );
        
    }
    
    // teachers not in a batch 

   
    public List<Teacher> getTeachersNotInBatch(String batchId, String courseId) {
    	
        String sql = "SELECT * FROM teacher NATURAL JOIN employee NATURAL JOIN user WHERE teacherId NOT IN (SELECT teacherId FROM teacherbatchdetails WHERE batchId = ? AND courseId = ?)";
        
        return jt.query(sql, new Object[] { batchId, courseId }, new RowMapper<Teacher>() {

           
            public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

                Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
                employee.setUser(user);

                Subject subject = (new BeanPropertyRowMapper<>(Subject.class)).mapRow(rs, rowNum);

                Teacher teacher = (new BeanPropertyRowMapper<>(Teacher.class)).mapRow(rs, rowNum);
                teacher.setEmployee(employee);
                teacher.setSubject(subject);
                return teacher;

            }
        });
    }

    // get a teacher ( by teacher Id ) 
   
    public Teacher getByTeacherId(int teacherId) {
        try {
        	
            String sql = "SELECT * FROM teacher NATURAL JOIN employee NATURAL JOIN user NATURAL JOIN subject WHERE teacherId = ?";
            
            return (Teacher) jt.queryForObject(sql, new Object[] { teacherId }, new RowMapper<Teacher>() {

                
                public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

                    Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
                    employee.setUser(user);

                    Subject subject = (new BeanPropertyRowMapper<>(Subject.class)).mapRow(rs, rowNum);

                    Teacher teacher = (new BeanPropertyRowMapper<>(Teacher.class)).mapRow(rs, rowNum);
                    teacher.setEmployee(employee);
                    teacher.setSubject(subject);
                    return teacher;

                }
            });
            
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // get a teacher ( by user Id ) 
    
    public Teacher getByUserId(int userId) {
        try {
        	
            String sql = "SELECT * FROM teacher NATURAL JOIN employee NATURAL JOIN user NATURAL JOIN subject WHERE userId = ?";
            
            return (Teacher) jt.queryForObject(sql, new Object[] { userId }, new RowMapper<Teacher>() {

               
                public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

                    Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
                    employee.setUser(user);

                    Subject subject = (new BeanPropertyRowMapper<>(Subject.class)).mapRow(rs, rowNum);

                    Teacher teacher = (new BeanPropertyRowMapper<>(Teacher.class)).mapRow(rs, rowNum);
                    teacher.setEmployee(employee);
                    teacher.setSubject(subject);
                    return teacher;

                }
            });
            
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // get a teacherId ( by user Id ) 
   
    public Integer getTeacherIdByUserId(int userId) {
        try {
        	
            String sql = "SELECT teacherId FROM teacher NATURAL JOIN employee NATURAL JOIN user WHERE userId = ?";
            
            return jt.queryForObject(sql, new Object[] { userId }, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    // update teacher 

    public void update(Teacher teacher) {
    	
        String sql = "UPDATE Teacher SET qualification = ?, achievement = ?, teachingExpirience = ? , subjectId = ? WHERE teacherId = ?";
        
        jt.update(sql, 
        		teacher.getQualification(), 
        		teacher.getAchievement(),
                teacher.getTeachingExpirience(),  
                teacher.getSubject().getSubjectId(), 
                teacher.getTeacherId());
    }

    // delete teacher 
   
    public void delete(int teacherId) {
    	
        String sql = "DELETE FROM teacher WHERE teacherId = ?";
        
        jt.update(sql, teacherId);
    }

}
