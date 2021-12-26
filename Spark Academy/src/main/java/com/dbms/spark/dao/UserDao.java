package com.dbms.spark.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
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


import com.dbms.spark.models.User;
import com.dbms.spark.util.DateTimeUtil;
import com.dbms.spark.util.PreparedStatementUtil;

@Transactional
@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jt;

	@Autowired
	private DateTimeUtil dateTimeUtil;

	@Autowired
	private PreparedStatementUtil preparedStatementUtil;

	// check if the email id already exists

	public boolean exists(String emailAddress) {
		String sql = "SELECT COUNT(*) FROM user WHERE emailAddress = ?";
		int count = jt.queryForObject(sql, Integer.class);
		return (count > 0);
	} 	

	// find the user by Email address

	public User findByEmailAddress(String emailAddress) {
		try {
			String sql = "SELECT * FROM user WHERE emailAddress = ?";
			return (User) jt.queryForObject(sql, new Object[] { emailAddress },
					new BeanPropertyRowMapper<>(User.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	// find by user name

	public User findByUsername(String username) {
		try {
			String sql = "SELECT * FROM user WHERE username = ?";
			return (User) jt.queryForObject(sql, new Object[] { username }, new BeanPropertyRowMapper<>(User.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	// save the user

	public User save(User user) {
		String sql = "INSERT INTO user (username, password, firstName, middleName, lastName , gender, emailAddress, dateCreated, isActive, role , token ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? , ? , ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jt.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				PreparedStatement preparedStatement = conn.prepareStatement(sql, new String[] { "userId" });
				preparedStatementUtil.setParameters(preparedStatement, user.getUsername(), user.getPassword(),
						user.getFirstName(), user.getMiddleName(), user.getLastName(), user.getGender(),
						user.getEmailAddress(), 
						dateTimeUtil.getCurrentDateTime("yyyy-MM-dd"), 
						user.getIsActive(),
						user.getRole(),
						user.getToken()
						);
				return preparedStatement;
			} 	
		}, keyHolder);
		int userId = keyHolder.getKey().intValue();
		user.setUserId(userId);
		return user;
	}

	// set the role of a user

	public void setRole(User user, String role) {
		String sql = "UPDATE user SET role = ? WHERE userId = ?";
		jt.update(sql, role, user.getUserId());
	}

	// update all attributes of user except userId, password, dateCreated,
	// lastLoginDate, lastLoginTime, role

	public void update(User user) {
		String sql = "UPDATE user SET username = ?, gender = ? , firstName = ?, middleName = ?, lastName = ?, emailAddress = ?, isActive = ? , isEmailVerified = ? , token = ? WHERE userId = ?";
		jt.update(sql, user.getUsername(), user.getGender(), user.getFirstName(), user.getMiddleName(),
				user.getLastName(), user.getEmailAddress(), user.getIsActive(),user.getIsEmailVerified() , user.getToken() ,  user.getUserId() );
	}

	// delete a user

	public void delete(int userId) {
		String sql = "DELETE FROM User WHERE userId = ?";
		jt.update(sql, userId);
	}

	// get all users

	public List<User> getAll() {
		String sql = "SELECT * FROM user";
		List<User> users = jt.query(sql, new BeanPropertyRowMapper<>(User.class));
		return users;
	}

	// get a particular user by using ID

	public User get(int userId) {
		try {
			String sql = "SELECT * FROM user WHERE userId = ?";
			return (User) jt.queryForObject(sql, new Object[] { userId }, new BeanPropertyRowMapper<>(User.class));
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public String getPassword(int userId) {
		try {
			String sql = "SELECT password FROM user WHERE userId = ?";
			return jt.queryForObject(sql, new Object[] { userId }, String.class);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void activate(int userId) {
		String sql = "UPDATE user SET isActive = true WHERE userId = ?";
		jt.update(sql, userId);
	}

	public void verifyEmail(int userId) {
		String sql = "UPDATE user SET isEmailVerified = true WHERE userId = ?";
		jt.update(sql, userId);
	}

	public void changePassword(int userId, String password) {
		String sql = "UPDATE user SET password = ? WHERE userId = ?";
		jt.update(sql, password, userId);
	}
	
	
	public User findByConfirmationToken(String token) {
        String sql = "SELECT * FROM user WHERE token='" + token + "'";
        try{
        	return jt.queryForObject(sql, new RowMapper<User>() {
                public User mapRow(ResultSet row, int rowNum) throws SQLException {                	
                	User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(row,rowNum);
                	return user;
                }
            });        
        }catch(EmptyResultDataAccessException e){
        	return null;
        }         
    }

	
	public User setLoginTimestamp(User user) {
		String lastLoginDate = dateTimeUtil.getCurrentDateTime("yyyy-MM-dd");
		String lastLoginTime = dateTimeUtil.getCurrentDateTime("HH:mm:ss");
		String sql = "UPDATE user SET lastLoginDate = ?, lastLoginTime = ? WHERE userId = ?";
		jt.update(sql, lastLoginDate, lastLoginTime, user.getUserId());
		user.setLastLoginDate(Date.valueOf(lastLoginDate));
		user.setLastLoginTime(Time.valueOf(lastLoginTime));
		return user;
	}

}
