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
import com.dbms.spark.dao.SubjectDao;
import com.dbms.spark.dao.TeacherDao;
import com.dbms.spark.dao.UserDao;
import com.dbms.spark.dao.UserPhoneNumberDao;
import com.dbms.spark.models.Batch;
import com.dbms.spark.models.Employee;
import com.dbms.spark.models.Subject;
import com.dbms.spark.models.Teacher;
import com.dbms.spark.models.User;
import com.dbms.spark.models.UserPhoneNumber;
import com.dbms.spark.services.EmailServices;
import com.dbms.spark.services.SecurityServices;

@Transactional
@Controller
public class TeacherController {
	
	@Autowired
	EmailServices emailService ;

	
	@Autowired
	SecurityServices securityServices; 
	
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
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	
	@GetMapping("/admin/teachers")
	public String teacherPortal(Model model) {
		 
		List<Teacher> teachers = teacherDao.getAll();
		model.addAttribute("teachers", teachers);
		model.addAttribute("title", "Teacher Portal");
		
		return "teacher/teacherPortal";
		
	}
	
	@GetMapping("/admin/teachers/add")
	public String addTeacher(Model model) {
		
		model.addAttribute("title", "Add Teacher");
		
	    List<Subject> subjects = subjectDao.getAll();
	    model.addAttribute("subjects", subjects);
	    
	    Teacher teacher = new Teacher();
        model.addAttribute("teacher", teacher);
        
        model.addAttribute("submiturl", "/admin/teachers/add");
		
		return "teacher/addTeacher";
	}
	
	
	@PostMapping("/admin/teachers/add")
	public String addTeacher(@Valid @ModelAttribute("teacher") Teacher teacher, BindingResult bindingResult, Model model) {
		
		
         Employee employee = teacher.getEmployee();
		 
	     User user = employee.getUser();
		
		
		if (userDao.findByUsername(user.getUsername()) != null) {
			bindingResult.rejectValue("employee.user.username", "Duplicate.user.username");
		}
		if (userDao.findByEmailAddress(user.getEmailAddress()) != null) {
			bindingResult.rejectValue("employee.user.emailAddress", "Duplicate.user.emailAddress");
		}
		
		if (bindingResult.hasErrors()) {

			model.addAttribute("title", "Add Teacher");
			 List<Subject> subjects = subjectDao.getAll();
	         model.addAttribute("subjects", subjects);
			return "teacher/addTeacher";
			
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
	        
	      message.setText("Your account has been registered on Spark Academy as Teacher role  with username = " + user.getUsername()+ " and temporary password = "+ temp + " To confirm your account, please click here :"		 
	                + hostname +"/user/confirm-account?token="+token);
	        
	        user.setIsActive(false);
		
		
		
		
	
		emailService.sendEmail(message);
 
		user.setPassword(bCryptPasswordEncoder.encode(temp));
		
		user.setRole("ROLE_TEACHER");
		
		employee.setUser(user);
		userDao.save(user);
		employeeDao.save(employee);
		teacherDao.save(teacher);
		
		
	     return "redirect:/admin/teachers/" + teacher.getTeacherId() +"/edit-phone";
	}
	
	
	
	@GetMapping( "/admin/teachers/{teacherId}" )
	public String teacherDeatils(@PathVariable("teacherId") int teacherId , Model model) {
		    
		System.out.println(teacherId);
		Teacher teacher = teacherDao.getByTeacherId(teacherId);
		model.addAttribute("teacher", teacher );
		System.out.println(teacher);
		int userId = teacher.getEmployee().getUser().getUserId();
		
		List<UserPhoneNumber> userPhoneNumbers = userPhoneNumberDao.getByUserId(userId);
		model.addAttribute("userPhoneNumbers", userPhoneNumbers);
		
	    List<Batch> batchesAssigned = batchDao.getAllByTeacherId(teacher.getTeacherId());
	    model.addAttribute("batchesAssigned", batchesAssigned);
	    
		model.addAttribute("title", "Teacher Details");
	
		return "teacher/teacherDetails";
		
	}
	
	
	
	@GetMapping("/admin/teachers/{teacherId}/edit")
	public String editTeacher(@PathVariable("teacherId") int teacherId, Model model) {
		
		 model.addAttribute("title", "Edit Teacher Details");
	
	     List<Subject> subjects = subjectDao.getAll();
	     model.addAttribute("subjects", subjects);

	     Teacher teacher = teacherDao.getByTeacherId(teacherId);
	     model.addAttribute("teacher", teacher);
	     
	     model.addAttribute("edit", "true");
	     model.addAttribute("submiturl", "/admin/teachers/" + teacherId + "/edit");
	     
	     return "teacher/addTeacher";
	}
	
	
	
	@PostMapping("/admin/teachers/{teacherId}/edit")
	public String editTeacher(@PathVariable("teacherId") int teacherId, @Valid @ModelAttribute("teacher") Teacher teacher, BindingResult bindingResult, Model model) {
		
		
		if (bindingResult.hasErrors()) {

			System.out.println(bindingResult);
			
			model.addAttribute("submiturl", "/admin/teachers/" + teacherId + "/edit");
			model.addAttribute("title", "Edit Teacher Details");
			 model.addAttribute("edit", "true");
			 
			List<Subject> subjects = subjectDao.getAll();
            model.addAttribute("subjects", subjects);
            return "teacher/addTeacher";
		}
		
		Teacher oldTeacher = teacherDao.getByTeacherId(teacherId);
		
		Employee employee = teacher.getEmployee();
        User user = employee.getUser();
        user.setUserId(oldTeacher.getEmployee().getUser().getUserId());
        user.setIsEmailVerified(oldTeacher.getEmployee().getUser().getIsEmailVerified());
        
        employee.setUser(user);
        employee.setEmployeeId(oldTeacher.getEmployee().getEmployeeId());
        teacher.setEmployee(employee);
        teacher.setTeacherId(oldTeacher.getTeacherId());
        
        userDao.update(user);
        employeeDao.update(employee);
        teacherDao.update(teacher);

		
        return "redirect:/admin/teachers/{teacherId}/edit-phone";
	}
	
	
	
	 @GetMapping("/admin/teachers/{teacherId}/edit-phone")
	    public String editTeacherPhone(@PathVariable("teacherId") int teacherId, Model model) {
		 
	        model.addAttribute("title", "Edit teacher Phone");
	       
	        Teacher teacher = teacherDao.getByTeacherId(teacherId);
	        
	        int userId = teacher.getEmployee().getUser().getUserId();
	        
	        List<UserPhoneNumber> phoneNumbers = userPhoneNumberDao.getByUserId(userId);
	        
	        model.addAttribute("phoneNumbers", phoneNumbers);
	        model.addAttribute("userId", userId);
	        
	        return "teacher/addTeacherPhone";
	    }
	 
	 
	 @GetMapping("/admin/teachers/{userId}/delete")
	 
	    public ModelAndView deleteTeacher(@PathVariable(value="userId") int userId) {
		 
	        ModelAndView model = new ModelAndView("redirect:/admin/teachers/");
	        
	        userDao.delete(userId);
	        
	        return model;
	    }
}