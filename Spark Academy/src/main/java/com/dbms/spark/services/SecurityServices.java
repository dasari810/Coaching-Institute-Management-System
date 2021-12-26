package com.dbms.spark.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbms.spark.config.UserDetailsImpl;
import com.dbms.spark.models.User;

@Transactional 
@Service
public class SecurityServices {

	
    public User findLoggedInUser() {
    	
        Object myUserDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (myUserDetails instanceof UserDetailsImpl) {
            return ((UserDetailsImpl) myUserDetails).getUser();
        }
        return null;
    }
	
}
