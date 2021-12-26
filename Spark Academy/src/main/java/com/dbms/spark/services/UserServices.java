package com.dbms.spark.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbms.spark.dao.UserDao;
//import com.dbms.coaching.dao.UserTokenDao;
import com.dbms.spark.models.User;
//import com.dbms.spark.models.UserToken;
//import com.dbms.coaching.utils.ServerUtil;

@Transactional
@Service
public class UserServices {
	@Autowired
	private UserDao userDao;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Autowired
//    private EmailSendService emailSendService;
//
//    @Autowired
//    private ServerUtil serverUtil;
//
//    @Autowired
//    private UserTokenDao userTokenDao;

	public User save(User user) {
		if (user.getPassword() != null)
			user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userDao.save(user);
	}

	public User findByEmailAddress(String emailAddress) {
		return userDao.findByEmailAddress(emailAddress);
	}

	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

   
    public void resetPassword(String token, String password) {
    	
        User user = userDao.findByConfirmationToken(token);
        userDao.changePassword(user.getUserId(), bCryptPasswordEncoder.encode(password));
    }

	public void changePassword(int userId, String password) {
		userDao.changePassword(userId, bCryptPasswordEncoder.encode(password));
	}

	public boolean verifyOldPassword(int userId, String password) {
		String encryptedPassword = userDao.getPassword(userId);
		return bCryptPasswordEncoder.matches(password, encryptedPassword);
	}

}
