package com.dbms.spark.controllers;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dbms.spark.dao.GuardianDao;
import com.dbms.spark.dao.GuardianPhoneNumberDao;
import com.dbms.spark.dao.StudentDao;
import com.dbms.spark.dao.UserDao;
import com.dbms.spark.dao.UserPhoneNumberDao;
import com.dbms.spark.models.GuardianPhoneNumber;
import com.dbms.spark.models.User;
import com.dbms.spark.models.UserPhoneNumber;
import com.dbms.spark.services.SecurityServices;


@Transactional
@Controller
public class UserController {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	SecurityServices securityServices ;
	
	@Autowired
	UserPhoneNumberDao userPhoneNumberDao ;
	
	@Autowired
	GuardianPhoneNumberDao guardianPhoneNumberDao ;
	
	@Autowired
	private StudentDao studentDao ;
	
	@Autowired
	GuardianDao guardianDao ;
	
	@GetMapping("/admin/users")
	public String userPortal(Model model) {
		 
		List<User> users = userDao.getAll();
		model.addAttribute("title", "Users");
		model.addAttribute("users", users);
		
		return "userPortal";
		
	}
	
	@GetMapping("/admin/users/{userId}/delete")
	
	public ModelAndView deleteStudent(@PathVariable("userId") int userId ) {
		
		ModelAndView model = new ModelAndView("redirect:/admin/users");
		
		userDao.delete(userId);
		
		return model ;
		
	}
	
	@PostMapping("/profile/users/{userId}/phoneNumber/add")
	
    public ResponseEntity<String> addUserPhoneNumber(@PathVariable("userId") int userId, @RequestParam String phoneNumber, Model model) {
		
		// validate phone number 
		
        if (!phoneNumber.matches("^[1-9][0-9]{9,9}$")) {
        	
            return new ResponseEntity<>("The phone number must be of 10 digits", HttpStatus.BAD_REQUEST);
        }
        
        Integer user_Id = userPhoneNumberDao.getuserIdByPhoneNumber(phoneNumber , userId);
        
        if(user_Id != null)
        {
        	 return new ResponseEntity<>("The phone number already exists for this user", HttpStatus.BAD_REQUEST);
        }
        
        User user = securityServices.findLoggedInUser();
        
        System.out.println(user.getRole());
        
        if (!user.getRole().equals("ROLE_ADMIN") && !user.getRole().equals("ROLE_STAFF") && user.getUserId() != userId) {
        	
            return new ResponseEntity<>("You are not allowed to perform this action", HttpStatus.BAD_REQUEST);
            
        }
        
//        if (user.getUserId() != userId) {
//        	
//        	 validateCorrectUserForStaff(userId);
//        }
      

        UserPhoneNumber userPhoneNumber = new UserPhoneNumber(phoneNumber, userId);
             
        userPhoneNumberDao.save(userPhoneNumber);       
        
        return new ResponseEntity<>(HttpStatus.OK);
    }

	
    @PostMapping("/profile/users/{userId}/phoneNumber/delete")
    public ResponseEntity<String> deleteUserPhoneNumber(@PathVariable("userId") int userId, @RequestParam String phoneNumber, Model model) {
    	
        
    	 User user = securityServices.findLoggedInUser();
         
         
         if (!user.getRole().equals("ROLE_ADMIN") && !user.getRole().equals("ROLE_STAFF") && user.getUserId() != userId) {
        	 
        	 
         	
             return new ResponseEntity<>("You are not allowed to perform this action", HttpStatus.BAD_REQUEST);
             
         } 
       
        UserPhoneNumber userPhoneNumber = new UserPhoneNumber(phoneNumber, userId);
        userPhoneNumberDao.delete(userPhoneNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    @PostMapping("/profile/student/add-guardian-phone")
    public ResponseEntity<String> addGuardianPhoneNumber(@RequestParam String phoneNumber, Model model) {
    	
        if (!phoneNumber.matches("^[1-9][0-9]{9,9}$")) {
            return new ResponseEntity<>("The phone number must be of 10 digits", HttpStatus.BAD_REQUEST);
        }
        
        int userId = securityServices.findLoggedInUser().getUserId();
        int studentId = studentDao.getStudentIdByUserId(userId);
        String guardianName = guardianDao.getNameByStudentId(studentId);
        
        Integer student_Id = guardianPhoneNumberDao.getuserIdByPhoneNumber(phoneNumber , studentId);
        
        if(student_Id != null)
        {
        	 return new ResponseEntity<>("The phone number already exists", HttpStatus.BAD_REQUEST);
        }
    
        GuardianPhoneNumber guardianPhoneNumber = new GuardianPhoneNumber(phoneNumber, guardianName, studentId);
        
        guardianPhoneNumberDao.save(guardianPhoneNumber);
       
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PostMapping("/profile/student/delete-guardian-phone")
    public ResponseEntity<Integer> deleteGuardianPhoneNumber(@RequestParam String phoneNumber, Model model) {
    	
        int userId = securityServices.findLoggedInUser().getUserId();
        int studentId = studentDao.getStudentIdByUserId(userId);
        String guardianName = guardianDao.getNameByStudentId(studentId);
        GuardianPhoneNumber guardianPhoneNumber = new GuardianPhoneNumber(phoneNumber, guardianName, studentId);
        guardianPhoneNumberDao.delete(guardianPhoneNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    @GetMapping("/user/courses")
    public String ourCourses(Model model) {
				
		return "courses" ;
		
	}
    
    @GetMapping({ "/admin/users/{userId}/activate", "/staff/users/{userId}/activate"  })
    public ResponseEntity<Integer> activateUser(@PathVariable("userId") int userId, Model model) {
       
        userDao.activate(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
