package com.dbms.spark.controllers;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import com.dbms.spark.dao.GuardianDao;
import com.dbms.spark.dao.StudentDao;
import com.dbms.spark.dao.UserDao;
import com.dbms.spark.dao.UserPhoneNumberDao;
import com.dbms.spark.models.Guardian;
import com.dbms.spark.models.Student;
import com.dbms.spark.models.User;
import com.dbms.spark.services.EmailServices;
import com.dbms.spark.services.SecurityServices;
import com.dbms.spark.services.UserServices;

@Controller
@Transactional
public class AuthController {
	
	@Autowired 
	UserPhoneNumberDao userPhoneNumberDao;
	
	@Autowired
	EmailServices emailService ;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private StudentDao studentDao ; 
	
	@Autowired
	private GuardianDao guardianDao;
	
	@Autowired 
	SecurityServices securityServices ; 
	

	@Autowired
	private UserServices userService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/register/student")
	public String registerUser(Model model , HttpServletRequest request) {
		
		 new SecurityContextLogoutHandler().logout(request, null, null);
	 	 Student newStudent = new Student();	
//	 	 List<UserPhoneNumber> userPhoneNumbers = new UserPhoneNumber();
//	 	 List<GuardianPhoneNumber>  guardianPhoneNumbers  = new  GuardianPhoneNumber();
	 	 model.addAttribute("student", newStudent);
//		 model.addAttribute("userPhoneNumber", userPhoneNumbers);
//		 model.addAttribute("guardianPhoneNumber", guardianPhoneNumbers);
	 	 model.addAttribute("role", "none");
	 	 model.addAttribute("title", "Register Page");
		 return "register";

	}

	@PostMapping("/register/student")
	public String registerUser(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {

		
		User user = student.getUser();
		
		Guardian guardian = student.getGuardian() ;
		
		// unique user name an email

		if (userService.findByUsername(user.getUsername()) != null) {
			bindingResult.rejectValue("username", "Duplicate.user.username");
		}
		if (userService.findByEmailAddress(user.getEmailAddress()) != null) {
			bindingResult.rejectValue("emailAddress", "Duplicate.user.emailAddress");
		}
		
		if (bindingResult.hasErrors()) {

			System.out.println(bindingResult);
			model.addAttribute("title", "Register Page");
			return "register";
		}

		// encrypt the password

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
		user.setRole("ROLE_STUDENT");
		
		 String token = UUID.randomUUID().toString();
		 user.setToken(token);
		 
		 SimpleMailMessage message = new SimpleMailMessage();
		 message.setFrom("spark.coaching.academy@gmail.com");
		 message.setTo(user.getEmailAddress());
		 message.setSubject("Registration Successful ! |  Verify Email ");	
	     
	     String hostname = System.getenv("SERVER_HOSTNAME");
	        if (hostname == null) {
	            hostname="http://localhost:8080";
	        }
	        
	     message.setText("Your account has been registered on Spark Academy . To confirm your account, please click here : "		 
	                + hostname +"/user/confirm-account?token="+token);
	        
	        user.setIsActive(false);
		
		
		student.setUser(user);
		userDao.save(user);
		studentDao.save(student);
		
		guardian.setStudentId(student.getStudentId());
		guardianDao.save(guardian);
		
		
		emailService.sendEmail(message);
	    
		return "redirect:/login?verificationEmailSent=true";
	}
	
	@RequestMapping(value="/user/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
	 public ModelAndView confirmAccount(@RequestParam("token")String token,HttpServletRequest request)
	 {
		
		
		 User user = userDao.findByConfirmationToken(token);
		 new SecurityContextLogoutHandler().logout(request, null, null);
		 
	     if(user != null)
	     {
	    	 ModelAndView model=new ModelAndView("confirm_account");
	    	 user.setIsActive(true);
	    	 user.setIsEmailVerified(true);
	    	 
	    	 userDao.update(user);
	    	 return model;
	     }
	     else
	     {	
	    	 ModelAndView model=new ModelAndView("message");
	         model.addObject("message", "Broken Link");		
	         return model;
	     }

	    
	 }

	@GetMapping("/login")
	public String login(Model model, String error, String logout , String verificationEmailSent , String emailSent , String brokenLink , String resetSuccessful ) {

		if (error != null)
			model.addAttribute("errorToast", "Either Your username and password is invalid or your email is not verified");
		
		 if (verificationEmailSent != null)
	        model.addAttribute("successToast", "Verification email has been sent on your email address");

		if (logout != null)
			model.addAttribute("successToast", "You have been logged out successfully.");
		
		if (emailSent != null)
            model.addAttribute("successToast", "Your password reset mail has been sent");
		
		if (brokenLink != null)
            model.addAttribute("errorToast", "Invalid Link");
		
	    if (resetSuccessful != null)
	            model.addAttribute("successToast", "Your password has been reset successfully");
	    
	    model.addAttribute("title", "Login");

		return "login";
		
	}
	
	@GetMapping("/user/forgot-password")
    public String forgotPassword(Model model) {
		
        model.addAttribute("title", "Forgot Password");
       
        model.addAttribute("submitUrl", "/user/forgot-password");
        
        return "forgotPassword";
    }
	
	@PostMapping("/user/forgot-password")
    public String resetPassword(@RequestParam("email") String email, Model model) {
		
//        if (securityServices.findLoggedInUser().getUserId() != 0) {
//            return "redirect:/";
//        }
        
        User user = userDao.findByEmailAddress(email);
        if (user == null) {
            model.addAttribute("title", "Forgot Password");
            model.addAttribute("errorToast", "The email does not exist");
            model.addAttribute("submitUrl", "/user/forgot-password");
            
            return "forgotPassword";
        }
        
        
         String token = UUID.randomUUID().toString();
		 user.setToken(token);
		 userDao.update(user);
		 
		 System.out.println(token);
         SimpleMailMessage message = new SimpleMailMessage();
		 message.setFrom("spark.coaching.academy@gmail.com");
		 message.setTo(user.getEmailAddress());
		 message.setSubject("Reset Your Password ! ");	
	     
	     String hostname = System.getenv("SERVER_HOSTNAME");
	        if (hostname == null) {
	            hostname="http://localhost:8080";
	        }
	        
	     message.setText("please go to "		 
	                + hostname +"/user/set-password?token="+token+" to reset your password , then try to login again ," + "\n\n Thankyou ! ");
	     
	    emailService.sendEmail(message);   
        
        return "redirect:/login?emailSent";
    }
	
	@GetMapping("/user/set-password")
    public String resetPassword(Model model, String token ) {
		
//        if (securityServices.findLoggedInUser().getUserId() != 0) {
//            return "redirect:/";
//        }
        
        User user  = userDao.findByConfirmationToken(token);
        Integer userId = user.getUserId();
        
        if (  userId == null ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Token"); 
        }
        
        model.addAttribute("title", "Reset Password");
       
        model.addAttribute("submitUrl", "/user/set-password?token=" + token);
        
        return "setPassword";
    }
	
	
	@PostMapping("/user/set-password")
    public String resetPassword(@RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword, Model model, String token) {
//        if (securityServices.findLoggedInUser().getUserId() != 0) {
//            return "redirect:/";
//        }
        
        User user  = userDao.findByConfirmationToken(token);
        Integer userId = user.getUserId();
        
        if (  userId == null ) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Token");
        }
        
        if (!password.equals(confirmPassword)) {
        	
            model.addAttribute("title", "Reset Password");
           
            model.addAttribute("errorToast", "The passwords do not match");
            model.addAttribute("submitUrl", "/user/set-password?token=" + token);
            return "setPassword";
        }
        
        userService.resetPassword(token, password);
        return "redirect:/login?resetSuccessful";
    }

	
	@GetMapping("/welcome")
	public String welcome(Model model) {
		
//		System.out.println(securityServices.findLoggedInUser());
		
		User user = securityServices.findLoggedInUser();
		
		if ( user.getRole().equals("ROLE_STUDENT") ) {
			
			return "redirect:/student";
		}
		else if ( user.getRole().equals("ROLE_ADMIN")) {
			
			return "redirect:/admin";
		}
        else if ( user.getRole().equals("ROLE_TEACHER")) {
			
			return "redirect:/teacher";
		}
        else if ( user.getRole().equals("ROLE_STAFF")) {
	
	       return "redirect:/staff";
        }
		
		return "home" ;
	}

}
