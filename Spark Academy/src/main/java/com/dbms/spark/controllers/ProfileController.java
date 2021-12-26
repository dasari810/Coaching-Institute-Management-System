package com.dbms.spark.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dbms.spark.dao.EmployeeDao;
import com.dbms.spark.dao.GuardianDao;
import com.dbms.spark.dao.GuardianPhoneNumberDao;
import com.dbms.spark.dao.StaffDao;
import com.dbms.spark.dao.StudentDao;
import com.dbms.spark.dao.SubjectDao;
import com.dbms.spark.dao.TeacherDao;
import com.dbms.spark.dao.UserDao;
import com.dbms.spark.dao.UserPhoneNumberDao;
import com.dbms.spark.models.Employee;
import com.dbms.spark.models.Guardian;
import com.dbms.spark.models.GuardianPhoneNumber;
import com.dbms.spark.models.Staff;
import com.dbms.spark.models.Student;
import com.dbms.spark.models.Subject;
import com.dbms.spark.models.Teacher;
import com.dbms.spark.models.User;
import com.dbms.spark.models.UserPhoneNumber;
import com.dbms.spark.services.SecurityServices;
import com.dbms.spark.services.UserServices;


@Transactional
@Controller
public class ProfileController {
	
	@Autowired
	UserServices userServices ;
	
	@Autowired
	EmployeeDao employeeDao ;
	
	@Autowired
	TeacherDao teacherDao ;
	
	@Autowired
	SubjectDao subjectDao;
	
	@Autowired
	StaffDao staffDao ;
	
	@Autowired
	UserDao userDao ;

	
	@Autowired
	SecurityServices securityServices; 
	
	@Autowired
    private UserPhoneNumberDao userPhoneNumberDao;
	
	@Autowired
	GuardianPhoneNumberDao guardianPhoneNumberDao ;
	
	@Autowired
	private StudentDao studentDao ;
	
	@Autowired
	GuardianDao guardianDao ;
	
	@GetMapping("/profile")
    public String redirect(Model model) {
		
        String role = securityServices.findLoggedInUser().getSmallRole();
        
        if (role.equals("student")) {
            return "redirect:/profile/student";
        }
        else if (role.equals("teacher")) {
            return "redirect:/profile/teacher";
        }
        else if (role.equals("staff")) {
            return "redirect:/profile/staff";
        }
        else {
            return "redirect:/profile/admin";
        }
    }

	
	 @GetMapping("/profile/admin")
	    public String adminProfile(Model model, String changeSuccessful) {
		 
	        if (changeSuccessful != null)
	            model.addAttribute("successToast", "Your password has been changed successfully");
	        
	        
	        User user = securityServices.findLoggedInUser();
	        model.addAttribute("user", user);
	        
	        if (!user.getSmallRole().equals("admin"))
	            return "redirect:/profile";
	        
	        
	        model.addAttribute("title", "Admin Profile");
	        
	        List<UserPhoneNumber> userPhoneNumbers = userPhoneNumberDao.getByUserId(user.getUserId());
	       
	        model.addAttribute("userPhoneNumbers", userPhoneNumbers);
	        
	        return "adminProfile";
	    }
	 
	    @GetMapping("/profile/student")
	    public String viewStudent(Model model, String changeSuccessful) {
	    	
	        if (changeSuccessful != null)
	            model.addAttribute("successToast", "Your password has been changed successfully");
	        
	        User user = securityServices.findLoggedInUser();
	       
	        
	        if (!user.getSmallRole().equals("student"))
	            return "redirect:/profile";
	        
	        Integer studentId = studentDao.getStudentIdByUserId(user.getUserId());
	        
	      
	        model.addAttribute("title", "Student Portal");
	        
	        Student student = studentDao.get(studentId);
	        model.addAttribute("student", student);
	        
	        List<UserPhoneNumber> userPhoneNumbers = userPhoneNumberDao.getByUserId(user.getUserId());
	        model.addAttribute("userPhoneNumbers", userPhoneNumbers);
	        
	        List<GuardianPhoneNumber> guardianPhoneNumbers = guardianPhoneNumberDao.getByStudentId(studentId);
	        model.addAttribute("guardianPhoneNumbers", guardianPhoneNumbers);
	    
	        return "student/studentDetails";
	    }
	    
	    
	    @GetMapping("/profile/student/edit")
	    public String editStudent(Model model) {
	    	
	        int userId = securityServices.findLoggedInUser().getUserId();
	        String role = securityServices.findLoggedInUser().getSmallRole();
	        
	        if (!role.equals("student"))
	            return "redirect:/profile";
	        Integer studentId = studentDao.getStudentIdByUserId(userId);
	        

	        model.addAttribute("title", "Edit Student");
	        
	        model.addAttribute("submiturl", "/profile/student/edit-student");
	        model.addAttribute("edit", "true");
	        
	        Student student = studentDao.get(studentId);
	        model.addAttribute("student", student);
	        return "student/editStudent";
	    }
	    
	    @PostMapping("/profile/student/edit-student")
	    public String editStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {
	    	
	    	
	    	int userId = securityServices.findLoggedInUser().getUserId();
	        String role = securityServices.findLoggedInUser().getSmallRole();
	       
	        if (!role.equals("student"))
	            return "redirect:/profile";
	        Integer studentId = studentDao.getStudentIdByUserId(userId);
	        if (studentId == null)
	            return "redirect:/profile/student/add";

	        if (bindingResult.hasErrors()) {
	            model.addAttribute("title", "Edit Student");
	           
	            model.addAttribute("submiturl", "/profile/student/edit-student");
	           
	            model.addAttribute("edit", "true");
	            return "student/editStudent";
	        }
	        
	        student.setStudentId(studentId);
	        User user = userDao.get(userId);


	        student.setUser(user);
	        studentDao.update(student);

	        Guardian guardian = student.getGuardian();
	        guardian.setStudentId(studentId);
	        guardianDao.update(guardian);
	        return "redirect:/profile/student/edit-student-phone";
	        
	    }
	    
	    
	    @GetMapping("/profile/student/edit-student-phone")
	    public String addStudentPhone(Model model) {
	       
	    	int userId = securityServices.findLoggedInUser().getUserId();
	        String role = securityServices.findLoggedInUser().getSmallRole();
	       
	    	
	        if (!role.equals("student"))
	            return "redirect:/profile";
	        Integer studentId = studentDao.getStudentIdByUserId(userId);
	        if (studentId == null)
	            return "redirect:/profile/student/add";

	        model.addAttribute("title", "Edit Student");
	       
	        List<UserPhoneNumber> studentPhoneNumbers = userPhoneNumberDao.getByUserId(userId);
	        List<GuardianPhoneNumber> guardianPhoneNumbers = guardianPhoneNumberDao.getByStudentId(studentId);
	        
	        model.addAttribute("studentPhoneNumbers", studentPhoneNumbers);
	        model.addAttribute("guardianPhoneNumbers", guardianPhoneNumbers);
	        model.addAttribute("userId", userId);
	        
	        return "student/addStudentPhone";
	    }
	    
	   
	    @GetMapping("/profile/teacher")
	    public String teacherProfile(Model model, String changeSuccessful) {
	    	
	        if (changeSuccessful != null)
	            model.addAttribute("successToast", "Your password has been changed successfully");
	        
	        int userId = securityServices.findLoggedInUser().getUserId();
	        String role = securityServices.findLoggedInUser().getSmallRole();
	        
	        if (!role.equals("teacher"))
	            return "redirect:/profile";
	        
	        model.addAttribute("title", "Teacher Profile");
	       
	        Teacher teacher = teacherDao.getByUserId(userId);
	        model.addAttribute("teacher", teacher);
	        
	        List<UserPhoneNumber> userPhoneNumbers = userPhoneNumberDao.getByUserId(userId);	        
	        model.addAttribute("userPhoneNumbers", userPhoneNumbers);
	        return "teacher/teacherDetails";
	    }
	    
	    @GetMapping("/profile/teacher/edit-teacher")
	    public String editTeacher(Model model) {
	       
	    	int userId = securityServices.findLoggedInUser().getUserId();
	        String role = securityServices.findLoggedInUser().getSmallRole();
	        
	        if (!role.equals("teacher"))
	            return "redirect:/profile";
	       

	        model.addAttribute("title", "Edit Teacher");
	       
	        model.addAttribute("submiturl", "/profile/teacher/edit-teacher");
	        model.addAttribute("edit", "true");
	        
	        List<Subject> subjects = subjectDao.getAll();
	        model.addAttribute("subjects", subjects);

	        Teacher teacher = teacherDao.getByUserId(userId);
	        model.addAttribute("teacher", teacher);
	        
	        return "teacher/addTeacher";
	    }

	    
	    @PostMapping("/profile/teacher/edit-teacher")
	    public String editTeacher(@Valid @ModelAttribute("teacher") Teacher teacher, BindingResult bindingResult, Model model) {
	    	
	    	int userId = securityServices.findLoggedInUser().getUserId();
	        String role = securityServices.findLoggedInUser().getSmallRole();
	        
	        System.out.println(teacher);

	        if (!role.equals("teacher"))
	            return "redirect:/profile";
	        
	        if (bindingResult.hasErrors()) {
	            model.addAttribute("title", "Edit Teacher");
	           
	            model.addAttribute("submiturl", "/profile/teacher/edit-teacher");
	            model.addAttribute("edit", "true");
	            
	            return "teacher/addTeacher";
	        }
	        
	        Teacher oldTeacher = teacherDao.getByUserId(userId);

	        Employee employee = teacher.getEmployee();
	        User user = employee.getUser();
	        user.setUserId(oldTeacher.getEmployee().getUser().getUserId());
	        user.setIsEmailVerified(oldTeacher.getEmployee().getUser().getIsEmailVerified());

	        employee.setUser(user);
	        employee.setEmployeeId(oldTeacher.getEmployee().getEmployeeId());
	        employee.setBasicSalary(oldTeacher.getEmployee().getBasicSalary());
	        employee.setJoinDate(oldTeacher.getEmployee().getJoinDate());
	        
	        teacher.setEmployee(employee);
	        teacher.setTeacherId(oldTeacher.getTeacherId());
	        teacher.setSubject(oldTeacher.getSubject());

	       
	        employeeDao.updateOwnProfile(employee);
	        employeeDao.update(employee);
	        teacherDao.update(teacher);
	        return "redirect:/profile/teacher/edit-teacher-phone";
	    }
	    
	    @GetMapping("/profile/teacher/edit-teacher-phone")
	    public String addTeacherPhone(Model model) {
	    	
	    	int userId = securityServices.findLoggedInUser().getUserId();
	        String role = securityServices.findLoggedInUser().getSmallRole();
	        
	        if (!role.equals("teacher"))
	            return "redirect:/profile";
	        Integer employeeId = employeeDao.getEmployeeIdByUserId(userId);
	        if (employeeId == null)
	            return "redirect:/profile/teacher/add";

	        model.addAttribute("title", "Edit Teacher");
	       
	        List<UserPhoneNumber> phoneNumbers = userPhoneNumberDao.getByUserId(userId);
	        model.addAttribute("phoneNumbers", phoneNumbers);
	        model.addAttribute("userId", userId);
	        
	        return "teacher/addTeacherPhone";
	    }
	    
	    
	    @GetMapping("/profile/staff")
	    public String viewStaff(Model model, String changeSuccessful) {
	        if (changeSuccessful != null)
	            model.addAttribute("successToast", "Your password has been changed successfully");
	        
	        int userId = securityServices.findLoggedInUser().getUserId();
	        String role = securityServices.findLoggedInUser().getSmallRole();
	        
	        if (!role.equals("staff"))
	            return "redirect:/profile";
	        Integer employeeId = employeeDao.getEmployeeIdByUserId(userId);
	        if (employeeId == null)
	            return "redirect:/profile/staff/add";

	        model.addAttribute("title", "Staff Profile");
	       
	        Staff staff = staffDao.getByUserId(userId);
	        List<UserPhoneNumber> userPhoneNumbers = userPhoneNumberDao.getByUserId(userId);
	        model.addAttribute("staff", staff);
	        model.addAttribute("userPhoneNumbers", userPhoneNumbers);
	        return "staff/staffDetails";
	    }
	    
	    @GetMapping("/profile/staff/edit-staff")
	    public String editStaff(Model model) {
	       
	    	int userId = securityServices.findLoggedInUser().getUserId();
	        String role = securityServices.findLoggedInUser().getSmallRole();
	    	
	        if (!role.equals("staff"))
	            return "redirect:/profile";
	        Integer employeeId = employeeDao.getEmployeeIdByUserId(userId);
	        if (employeeId == null)
	            return "redirect:/profile/staff/add";

	        model.addAttribute("title", "Edit Staff");
	        
	        model.addAttribute("submiturl", "/profile/staff/edit-staff");
	        model.addAttribute("edit", "true");
	        
	        Staff staff = staffDao.getByUserId(userId);
	        model.addAttribute("staff", staff);
	        return "staff/addStaff";
	    }
	    
	    
	    @PostMapping("/profile/staff/edit-staff")
	    public String editStaff(@Valid @ModelAttribute("staff") Staff staff, BindingResult bindingResult, Model model) {
	        
	    	int userId = securityServices.findLoggedInUser().getUserId();
	        String role = securityServices.findLoggedInUser().getSmallRole();
	        
	        if (!role.equals("staff"))
	            return "redirect:/profile";
	        Integer employeeId = employeeDao.getEmployeeIdByUserId(userId);
	        if (employeeId == null)
	            return "redirect:/profile/staff/add";

	        if (bindingResult.hasErrors()) {
	            model.addAttribute("title", "Edit Staff");
	           
	            model.addAttribute("submiturl", "/profile/staff/edit-staff");
	            model.addAttribute("edit", "true");
	            
	            return "staff/addStaff";
	        }
	        Staff oldStaff = staffDao.getByUserId(userId);

	        Employee employee = staff.getEmployee();
	        User user = employee.getUser();
	        user.setUserId(oldStaff.getEmployee().getUser().getUserId());
	        user.setIsEmailVerified(oldStaff.getEmployee().getUser().getIsEmailVerified());
	        

	        employee.setEmployeeId(oldStaff.getEmployee().getEmployeeId());
	        employee.setBasicSalary(oldStaff.getEmployee().getBasicSalary());
	        employee.setJoinDate(oldStaff.getEmployee().getJoinDate());

	        employee.setUser(user);
	        employee.setEmployeeId(oldStaff.getEmployee().getEmployeeId());
	        staff.setEmployee(employee);
	        staff.setStaffId(oldStaff.getStaffId());

	        employeeDao.updateOwnProfile(employee);
	        employeeDao.update(employee);
	        staffDao.update(staff);
	        
	        return "redirect:/profile/staff/edit-staff-phone";
	    }

	    @GetMapping("/profile/staff/edit-staff-phone")
	    public String editStaffPhone(Model model) {
	    	
	      
	    	int userId = securityServices.findLoggedInUser().getUserId();
	        String role = securityServices.findLoggedInUser().getSmallRole();
	        
	        if (!role.equals("staff"))
	            return "redirect:/profile";
	        Integer employeeId = employeeDao.getEmployeeIdByUserId(userId);
	        if (employeeId == null)
	            return "redirect:/profile/staff/add";

	        model.addAttribute("title", "Staff Phone");
	       
	        List<UserPhoneNumber> phoneNumbers = userPhoneNumberDao.getByUserId(userId);
	        model.addAttribute("phoneNumbers", phoneNumbers);
	        model.addAttribute("userId", userId);
	        
	        return "staff/addStaffPhone";
	    }
	    
	    @GetMapping("/profile/change-password")
	    public String changePassword(Model model) {
	    	
	        model.addAttribute("title", "Change Password");
	       
	        model.addAttribute("submitUrl", "/profile/change-password");
	        return "ChangePassword";
	    }
	    
	    @PostMapping("/profile/change-password")
	    public String changePassword(@RequestParam("password") String password,  @RequestParam("confirmPassword") String confirmPassword,  @RequestParam("oldPassword") String oldPassword, Model model) {
	    	
	        if (!password.equals(confirmPassword)) {
	            model.addAttribute("title", "Change Password");
	          
	            model.addAttribute("errorToast", "The passwords do not match");
	            
	            model.addAttribute("submitUrl", "/profile/change-password");
	            return "ChangePassword";
	        }
	        
	        User user = securityServices.findLoggedInUser();
	        
	        if (!userServices.verifyOldPassword( user.getUserId(), oldPassword)) {
	        	
	            model.addAttribute("title", "Change Password");
	            
	            model.addAttribute("errorToast", "Old Password is incorrect");
	            
	            model.addAttribute("submitUrl", "/profile/change-password");
	            return "ChangePassword";
	        }
	        
	        userServices.changePassword(user.getUserId(), password);
	        return "redirect:/profile/" + user.getSmallRole() + "?changeSuccessful";
	    }


}
