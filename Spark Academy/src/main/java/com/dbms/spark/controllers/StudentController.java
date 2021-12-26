package com.dbms.spark.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.dbms.spark.dao.GuardianDao;
import com.dbms.spark.dao.GuardianPhoneNumberDao;
import com.dbms.spark.dao.StudentDao;
import com.dbms.spark.dao.UserDao;
import com.dbms.spark.dao.UserPhoneNumberDao;
import com.dbms.spark.models.Guardian;
import com.dbms.spark.models.GuardianPhoneNumber;
import com.dbms.spark.models.Student;
import com.dbms.spark.models.User;
import com.dbms.spark.models.UserPhoneNumber;
import com.dbms.spark.services.SecurityServices;

@Transactional
@Controller
public class StudentController {
	
	@Autowired
	SecurityServices securityServices ;
	
	@Autowired
	StudentDao studentDao;
	
	@Autowired 
	UserDao userDao;
	
	@Autowired
	GuardianDao guardianDao ;
	
	@Autowired
	UserPhoneNumberDao userPhoneNumberDao ;
	
	@Autowired
	GuardianPhoneNumberDao guardianPhoneNumberDao ;

	
	@GetMapping({ "/admin/students" , "/staff/students" })
	public String userPortal(Model model) {
		 
		List<Student> students = studentDao.getAll();
		model.addAttribute("students", students);
		model.addAttribute("title", "Student Portal");
		
		return "student/studentPortal"; 
		
	}
	
	
	@GetMapping( { "/admin/students/{studentId}" ,  "/staff/students/{studentId}" } )
	public String studentDeatils(@PathVariable("studentId") int studentId , Model model) {
		 
		Student student = studentDao.get(studentId);
		model.addAttribute("student", student );
		
		int userId = student.getUser().getUserId();
		
		List<UserPhoneNumber> userPhoneNumbers = userPhoneNumberDao.getByUserId(userId);
		model.addAttribute("userPhoneNumbers", userPhoneNumbers);
		
		List<GuardianPhoneNumber> guardianPhoneNumbers = guardianPhoneNumberDao.getByStudentId(studentId);
		model.addAttribute("guardianPhoneNumbers", guardianPhoneNumbers);
		
		model.addAttribute("title", "Student Details");
	
		return "student/studentDetails";
		
	}
	
	
	@GetMapping({ "/admin/students/{studentId}/edit" , "/staff/students/{studentId}/edit" })
	public String editStudent(@PathVariable("studentId") int studentId, Model model) {
		
		 model.addAttribute("title", "Edit Student Details");
		 Student student = studentDao.get(studentId);
	     model.addAttribute("student", student);
	     model.addAttribute("edit" , true );
	     
	     String role = securityServices.findLoggedInUser().getSmallRole();
	     
	     model.addAttribute("submiturl", "/" + role + "/students/" + studentId + "/edit");
	     
	     return "student/editStudent";
	}
	
	
	@PostMapping({ "/admin/students/{studentId}/edit" , "/staff/students/{studentId}/edit" })
	public String editStudent(@PathVariable("studentId") int studentId, @Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {
		
		System.out.println(student);
		
		if (bindingResult.hasErrors()) {

			System.out.println(bindingResult);
			model.addAttribute("title", "Edit Student Details");
			model.addAttribute("submiturl", "/admin/students/" + studentId + "/edit");
			return "student/editStudent";
		}
		
		Student oldStudent = studentDao.get(studentId);
		
		User user = student.getUser();
		
		user.setUserId(oldStudent.getUser().getUserId());
		user.setPassword(oldStudent.getUser().getPassword());
		user.setDateCreated(oldStudent.getUser().getDateCreated());
		user.setLastLoginDate(oldStudent.getUser().getLastLoginDate());
		user.setLastLoginTime(oldStudent.getUser().getLastLoginTime());
		user.setRole(oldStudent.getUser().getRole());
		user.setIsEmailVerified(oldStudent.getUser().getIsEmailVerified());
		
		student.setUser(user);
		
	    userDao.update(user);
	    
	    studentDao.update(student);
	    
	    Guardian guardian = student.getGuardian();
        guardian.setStudentId(studentId);
        guardianDao.update(guardian);
        
        String role = securityServices.findLoggedInUser().getSmallRole();
		
		return "redirect:/" + role + "/students/{studentId}/edit-phone";
		
	}
	
	@GetMapping({ "/admin/students/{studentId}/edit-phone" , "/staff/students/{studentId}/edit-phone" })
    public String editStudentPhone(@PathVariable("studentId") int studentId, Model model) {
	 
        model.addAttribute("title", "Edit Student Phone");
       
        Student student = studentDao.get(studentId);
        
        int userId = student.getUser().getUserId();
        
        List<UserPhoneNumber> studentPhoneNumbers = userPhoneNumberDao.getByUserId(userId);
        
        List<GuardianPhoneNumber> guardianPhoneNumbers = guardianPhoneNumberDao.getByStudentId(studentId);
        
        model.addAttribute("studentPhoneNumbers", studentPhoneNumbers);
        model.addAttribute("guardianPhoneNumbers", guardianPhoneNumbers);
        model.addAttribute("userId", userId);
        
        return "student/addStudentPhone";
    }
	
	@PostMapping({ "/admin/students/{studentId}/add-guardian-phone", "/staff/students/{studentId}/add-guardian-phone" })
    public ResponseEntity<String> addGuardianPhoneNumber(@PathVariable("studentId") int studentId,  @RequestParam String phoneNumber, Model model) {
		
        if (!phoneNumber.matches("^[1-9][0-9]{9,9}$")) {
            return new ResponseEntity<>("The phone number must be of 10 digits", HttpStatus.BAD_REQUEST);
        }
        
        String guardianName = guardianDao.getNameByStudentId(studentId);
        GuardianPhoneNumber guardianPhoneNumber = new GuardianPhoneNumber(phoneNumber, guardianName, studentId);
        
       Integer student_Id = guardianPhoneNumberDao.getuserIdByPhoneNumber(phoneNumber , studentId);
        
        if(student_Id != null)
        {
        	 return new ResponseEntity<>("The phone number already exists", HttpStatus.BAD_REQUEST);
        }
        
        guardianPhoneNumberDao.save(guardianPhoneNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping({ "/admin/students/{studentId}/delete-guardian-phone", "/staff/students/{studentId}/delete-guardian-phone" })
    public ResponseEntity<Integer> deleteGuardianPhoneNumber(@PathVariable("studentId") int studentId, @RequestParam String phoneNumber, Model model) {
        String guardianName = guardianDao.getNameByStudentId(studentId);
        GuardianPhoneNumber guardianPhoneNumber = new GuardianPhoneNumber(phoneNumber, guardianName, studentId);
        guardianPhoneNumberDao.delete(guardianPhoneNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

	
	
	@GetMapping( { "/admin/students/{userId}/delete" , "/staff/students/{userId}/delete" })
	public ModelAndView deleteStudent(@PathVariable(value="userId") int userId){
		
		ModelAndView model ; 
		String role = securityServices.findLoggedInUser().getSmallRole();
		if(role.equals("staff")) {
			model = new ModelAndView("redirect:/staff/students");
		}
		else {
			model = new ModelAndView("redirect:/admin/students");
		}
		
		userDao.delete(userId);
		return model ;
	}
	
}
