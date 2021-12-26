package com.dbms.spark.dao;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import com.dbms.spark.models.Staff;
import com.dbms.spark.models.User;
import com.dbms.spark.util.PreparedStatementUtil;



@Transactional
@Repository
public class  StaffDao {
	
    @Autowired
    private JdbcTemplate jt;

    @Autowired
    private PreparedStatementUtil preparedStatementUtil;

    // save staff details 
    
    public Staff save(Staff staff) {
    	
        String sql = "INSERT INTO staff ( designation , employeeId) VALUES ( ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jt.update(new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[] {"staffId"});
                preparedStatementUtil.setParameters(preparedStatement, 
                		staff.getDesignation() ,
                        staff.getEmployee().getEmployeeId());
                return preparedStatement;
            }
        }, keyHolder);
        
        int staffId = keyHolder.getKey().intValue();
        staff.setStaffId(staffId);
        return staff;
    }

    // get staff along with their employee and user details 
   
    public List<Staff> getAll() {
    	
        String sql = "SELECT * FROM staff NATURAL JOIN employee NATURAL JOIN user";
        
       return  jt.query(sql, new RowMapper<Staff>() {

    	    public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
    	        User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

    	        Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
    	        employee.setUser(user);

    	        Staff staff = (new BeanPropertyRowMapper<>(Staff.class)).mapRow(rs, rowNum);
    	        staff.setEmployee(employee);
    	        return staff;
    	    }
    	}
 );
       
    }

    // get a specific staff ( using staffId ) 

    
    public Staff getByStaffId(int staffId) {
        try {
        	
            String sql = "SELECT * FROM staff NATURAL JOIN employee NATURAL JOIN user WHERE staffId = ?";
            
            return (Staff) jt.queryForObject(sql, new Object[] { staffId }, new RowMapper<Staff>() {

        	    public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
        	        User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

        	        Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
        	        employee.setUser(user);

        	        Staff staff = (new BeanPropertyRowMapper<>(Staff.class)).mapRow(rs, rowNum);
        	        staff.setEmployee(employee);
        	        return staff;
        	    }
        	});
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    // get a specific staff ( using userId ) 


    
    public Staff getByUserId(int userId) {
        try {
        	
            String sql = "SELECT * FROM staff NATURAL JOIN employee NATURAL JOIN user WHERE userId = ?";
            
            return (Staff) jt.queryForObject(sql, new Object[] { userId }, new RowMapper<Staff>() {

        	    public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
        	        User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

        	        Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
        	        employee.setUser(user);

        	        Staff staff = (new BeanPropertyRowMapper<>(Staff.class)).mapRow(rs, rowNum);
        	        staff.setEmployee(employee);
        	        return staff;
        	    }
        	});
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    //  get a specific staffId ( using userId ) 

   
    public Integer getStaffIdByUserId(int userId) {
        try {
        	
            String sql = "SELECT staffId FROM staff NATURAL JOIN employee NATURAL JOIN user WHERE userId = ?";
            
            return jt.queryForObject(sql, new Object[] { userId }, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // update staff 
    
    public void update(Staff staff) {
    	
        String sql = "UPDATE staff SET designation = ? WHERE staffId = ?";
        
        jt.update(sql, 
        		staff.getDesignation(),
                staff.getStaffId());
    }

   // delete staff 
    
    public void delete(int staffId) {
    	
        String sql = "DELETE FROM staff WHERE staffId = ?";
        
        jt.update(sql, staffId);
        
    }

}
