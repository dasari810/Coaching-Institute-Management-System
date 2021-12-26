package com.dbms.spark.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbms.spark.dao.UserDao;
import com.dbms.spark.models.User;

@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserDao userDao;

	@Override
	public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
	    System.out.print(user);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username");
		}
		user = userDao.setLoginTimestamp(user);
		return new UserDetailsImpl(user);
	}

}
