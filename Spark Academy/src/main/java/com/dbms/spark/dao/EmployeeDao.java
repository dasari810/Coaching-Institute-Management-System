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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.RowMapper;


import com.dbms.spark.models.Employee;
import com.dbms.spark.models.User;
import com.dbms.spark.util.PreparedStatementUtil;


@Transactional
@Repository
public class EmployeeDao {
    @Autowired
    private JdbcTemplate jt;

    @Autowired
    private PreparedStatementUtil preparedStatementUtil;

    // save the employee 
   
    public Employee save(Employee employee) {
    	
        String sql = "INSERT INTO employee (basicSalary, houseNumber, street, city, state, joinDate, endDate, panNumber, accountNumber, userId) VALUES (?, ? , ? , ? , ? , ?, ?, ?, ?, ?)";
        
        KeyHolder keyHolder = new GeneratedKeyHolder();
        
        jt.update(
        		new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
            	
                PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[] {"employeeId"});
                
                preparedStatementUtil.setParameters(preparedStatement, 
                		employee.getBasicSalary(), 
                		employee.getHouseNumber(),
                		employee.getStreet(),
                		employee.getCity(),
                		employee.getState(),
                		employee.getJoinDate(),
                        employee.getEndDate(), 
                        employee.getPanNumber(), 
                        employee.getAccountNumber(), 
                        employee.getUser().getUserId());
                return preparedStatement;
            }
        }, keyHolder);
        
        int employeeId = keyHolder.getKey().intValue();
        employee.setEmployeeId(employeeId);
        return employee;
    }

    // get all the employee along with their user details 
    
    public List<Employee> getAll() {
    	
        String sql = "SELECT * FROM employee NATURAL JOIN user ORDER BY employeeId";
        
        return jt.query(sql, new RowMapper<Employee>() {

            public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

                Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
                employee.setUser(user);

                return employee;
            }
        });
       
    }

    // get those all employees who are teachers 
    
    public List<Employee> getAllTeachers() {
    	
        String sql = "SELECT * FROM employee NATURAL JOIN user WHERE role='ROLE_TEACHER'";
        
        return jt.query(sql, new RowMapper<Employee>() {

            public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

                Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
                employee.setUser(user);

                return employee;
            }
        } );
       
    }

    // get those all those employees who are staff 
    
    public List<Employee> getAllStaffs() {
    	
        String sql = "SELECT * FROM employee NATURAL JOIN user WHERE role='ROLE_STAFF'";
        
       return jt.query(sql, new RowMapper<Employee>() {

           public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
               User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

               Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
               employee.setUser(user);

               return employee;
           }
       });
      
    }
    
    // get a specific employee ( using employeeId ) 

   
    public Employee get(int employeeId) {
    	
        try {
        	
            String sql = "SELECT * FROM employee NATURAL JOIN user WHERE employeeId = ?";
            
            return (Employee) jt.queryForObject(sql, new Object[] { employeeId }, new RowMapper<Employee>() {

                public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                    User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs, rowNum);

                    Employee employee = (new BeanPropertyRowMapper<>(Employee.class)).mapRow(rs, rowNum);
                    employee.setUser(user);

                    return employee;
                }
            });
            
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // get a specific employee ( using userId ) 
    
    public Integer getEmployeeIdByUserId(int userId) {
    	
        try {
        	
            String sql = "SELECT employeeId FROM employee WHERE userId = ?";
            
            return jt.queryForObject(sql, new Object[] { userId }, Integer.class);
            
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // get the role of a specific employee 
   
    public String getRole(int employeeId) {
    	
        try {
        	
            String sql = "SELECT role FROM employee NATURAL JOIN user WHERE employeeId = ?";
            
            return jt.queryForObject(sql, new Object[] { employeeId }, String.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    // update user except pan and account number 
    
    public void update(Employee employee) {
    	
        String sql = "UPDATE employee SET basicSalary = ?,  houseNumber = ? , street = ? , city = ? , state = ? , joinDate = ?, endDate = ?, panNumber = ?, accountNumber = ? WHERE employeeId = ?";
        
        jt.update(sql, 
        		employee.getBasicSalary(), 
        		employee.getHouseNumber(),
        		employee.getStreet(),
        		employee.getCity(),
        		employee.getState(),
        		employee.getJoinDate(), 
        		employee.getEndDate(),
                employee.getPanNumber(), 
                employee.getAccountNumber(), 
                employee.getEmployeeId());
    }

    // update only pan and account number 
    
    public void updateOwnProfile(Employee employee) {
    	
        String sql = "UPDATE employee SET panNumber = ?, accountNumber = ? WHERE employeeId = ?";
        
        jt.update(sql, 
        		employee.getPanNumber(), 
        		employee.getAccountNumber(), 
        		employee.getEmployeeId());
    }

    // delete an employee 
    
    public void delete(int employeeId) {
    	
        String sql = "DELETE FROM employee WHERE employeeId = ?";
        
        jt.update(sql, employeeId);
    }

}
