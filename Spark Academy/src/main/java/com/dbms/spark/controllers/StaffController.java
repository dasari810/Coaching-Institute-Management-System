package com.dbms.spark.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dbms.spark.dao.BatchDao;
import com.dbms.spark.dao.EmployeeDao;
import com.dbms.spark.dao.StaffDao;
import com.dbms.spark.dao.SubjectDao;
import com.dbms.spark.dao.TeacherDao;
import com.dbms.spark.dao.UserDao;
import com.dbms.spark.dao.UserPhoneNumberDao;
import com.dbms.spark.models.Employee;
import com.dbms.spark.models.Staff;
import com.dbms.spark.models.User;
import com.dbms.spark.models.UserPhoneNumber;
import com.dbms.spark.services.EmailServices;

@Transactional
@Controller
public class StaffController {
	
	@Autowired
	EmailServices emailService ;

	
	
	@Autowired
	TeacherDao teacherDao;
	
	@Autowired
	EmployeeDao employeeDao ;
	
	@Autowired 
	UserDao userDao;
	
	@Autowired
	SubjectDao subjectDao;
	
	
	@Autowired 
	UserPhoneNumberDao userPhoneNumberDao;
	
	@Autowired
	BatchDao batchDao ;
	
	@Autowired
	StaffDao staffDao;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	
	@GetMapping("/admin/staffs")
	public String staffPortal(Model model) {
		 
		List<Staff> staffs = staffDao.getAll();
		model.addAttribute("staffs", staffs);
		model.addAttribute("title", "Staff Portal");
		
		return "staff/staffPortal";
		
	}
	
	@GetMapping("/admin/staffs/add")
	public String addstaff(Model model) {
		
		model.addAttribute("title", "Add Staff");
		
		Staff staff = new Staff();
		
        model.addAttribute("staff", staff);
        
        model.addAttribute("submiturl", "/admin/staffs/add");
		
		return "staff/addStaff";
	}
	
	
	@PostMapping("/admin/staffs/add")
	public String addStaff(@Valid @ModelAttribute("staff") Staff staff, BindingResult bindingResult, Model model) {
		
		
         Employee employee = staff.getEmployee();
		 
	     User user = employee.getUser();
		
		
		if (userDao.findByUsername(user.getUsername()) != null) {
			bindingResult.rejectValue("employee.user.username", "Duplicate.user.username");
		}
		if (userDao.findByEmailAddress(user.getEmailAddress()) != null) {
			bindingResult.rejectValue("employee.user.emailAddress", "Duplicate.user.emailAddress");
		}
		
		if (bindingResult.hasErrors()) {

			System.out.println(bindingResult);
			
			return "staff/addStaff";
			
		}
		
 
		String temp = user.getUsername() + "@123";
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
	        
	      message.setText("Your account has been registered on Spark Academy as Staff role  with username = " + user.getUsername()+ " and temporary password = "+ temp + " To confirm your account, please click here :"		 
	                + hostname +"/user/confirm-account?token="+token);
	        
	        user.setIsActive(false);
		
		
		emailService.sendEmail(message);

		user.setPassword(bCryptPasswordEncoder.encode(temp));
		
		user.setRole("ROLE_STAFF");
		
		employee.setUser(user);
		userDao.save(user);
		employeeDao.save(employee);
		staffDao.save(staff);
		
		return "redirect:/admin/staffs/" + staff.getStaffId() + "/add-phone";
	}
	
	
	 @GetMapping("/admin/staffs/{staffId}/add-phone")
	    public String addStaffPhone(@PathVariable("staffId") int staffId, Model model) {
		 
	        model.addAttribute("title", "Add Staff Phone");
	        
	        Staff staff = staffDao.getByStaffId(staffId);
	        int userId =  staff.getEmployee().getUser().getUserId();
	        
	        List<UserPhoneNumber> phoneNumbers = userPhoneNumberDao.getByUserId(userId);
	        model.addAttribute("phoneNumbers", phoneNumbers);
	        
	        model.addAttribute("userId", userId);
	        
	        return "staff/addStaffPhone";
	    }
	
	
	
	@GetMapping("/admin/staffs/{staffId}")
	public String staffDeatils(@PathVariable("staffId") int staffId , Model model) {
		 
		Staff staff = staffDao.getByStaffId(staffId);
		model.addAttribute("staff", staff );
		
		int userId = staff.getEmployee().getUser().getUserId();
		
		List<UserPhoneNumber> userPhoneNumbers = userPhoneNumberDao.getByUserId(userId);
		model.addAttribute("userPhoneNumbers", userPhoneNumbers);
	    
		model.addAttribute("title", "Staff Details");
	
		return "staff/staffDetails";
		
	}
	
	
	
	@GetMapping("/admin/staffs/{staffId}/edit")
	public String editStaff(@PathVariable("staffId") int staffId, Model model) {
		
		 model.addAttribute("title", "Edit Staff Details");
	
		 Staff staff = staffDao.getByStaffId(staffId);
	     model.addAttribute("staff", staff );
	     
	     model.addAttribute("submiturl", "/admin/staffs/" + staffId + "/edit");
	     model.addAttribute("edit", "true");
	     
	     return "staff/addStaff";
	}
	
	
	
	@PostMapping("/admin/staffs/{staffId}/edit")
	public String editTeacher(@PathVariable("staffId") int staffId, @Valid @ModelAttribute("staff") Staff staff, BindingResult bindingResult, Model model) {
		
		
		if (bindingResult.hasErrors()) {

			System.out.println(bindingResult);
			
			model.addAttribute("submiturl", "/admin/teachers/" + staffId + "/edit");
			model.addAttribute("title", "Edit Staff Details");
			model.addAttribute("edit", "true");
			
            return "staff/addStaff";
		}
		
		Staff oldStaff = staffDao.getByStaffId(staffId);
		
		Employee employee = staff.getEmployee();
        User user = employee.getUser();
        user.setUserId(oldStaff.getEmployee().getUser().getUserId());
        user.setIsEmailVerified(oldStaff.getEmployee().getUser().getIsEmailVerified());
        
        employee.setUser(user);
        employee.setEmployeeId(oldStaff.getEmployee().getEmployeeId());
        staff.setEmployee(employee);
        staff.setStaffId(oldStaff.getStaffId());
        
        userDao.update(user);
        employeeDao.update(employee);
        staffDao.update(staff);

		
        return "redirect:/admin/staffs/{staffId}/edit-phone";
	}
	
	
	
	 @GetMapping("/admin/staffs/{staffId}/edit-phone")
	    public String editTeacherPhone(@PathVariable("staffId") int staffId, Model model) {
		 
	        model.addAttribute("title", "Edit teacher Phone");
	       
	        Staff staff = staffDao.getByStaffId(staffId);
	        
	        int userId = staff.getEmployee().getUser().getUserId();
	        
	        List<UserPhoneNumber> phoneNumbers = userPhoneNumberDao.getByUserId(userId);
	        
	        model.addAttribute("phoneNumbers", phoneNumbers);
	        model.addAttribute("userId", userId);
	        
	        return "staff/addStaffPhone";
	    }
	 
	 
	 @GetMapping("/admin/staffs/{userId}/delete")
	 
	    public ModelAndView deleteStaff(@PathVariable(value="userId") int userId) {
		 
	        ModelAndView model = new ModelAndView("redirect:/admin/staffs/");
	        
	        userDao.delete(userId);
	        
	        return model;
	    }
}