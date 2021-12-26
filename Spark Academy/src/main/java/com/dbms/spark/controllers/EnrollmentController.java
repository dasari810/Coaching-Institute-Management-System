package com.dbms.spark.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
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
import com.dbms.spark.dao.CourseDao;
import com.dbms.spark.dao.EnrollmentDao;
import com.dbms.spark.dao.StudentDao;
import com.dbms.spark.dao.TeacherDao;
import com.dbms.spark.dao.TransactionDao;
import com.dbms.spark.models.Batch;
import com.dbms.spark.models.Course;
import com.dbms.spark.models.Enrollment;
import com.dbms.spark.models.Student;
import com.dbms.spark.models.Transaction;
import com.dbms.spark.models.User;
import com.dbms.spark.services.PaymentServices;
import com.dbms.spark.services.SecurityServices;


@Transactional
@Controller
public class EnrollmentController {
	
	@Autowired
	PaymentServices paymentService ;
	
	@Autowired
	private TeacherDao teacherDao ;
	
	 @Autowired
	  private EnrollmentDao enrollmentDao;
	 
	 @Autowired
	 private TransactionDao transactionDao ;
	 
	 @Autowired
	  private SecurityServices securityService;
	 
	 @Autowired
	 private CourseDao courseDao;
	 
	 @Autowired
	 private BatchDao batchDao ;

	 
	 @Autowired
	 private StudentDao studentDao;

	 
	@GetMapping({ "/admin/academics/enrollments", "/student/academics/enrollments" })
     public String listEnrollments(Model model) {
		
        model.addAttribute("title", "Enrollments");
        
        List<Enrollment> enrollments = enrollmentDao.getAll();

        User user = securityService.findLoggedInUser();
        
        if (user.getRole().equals("ROLE_STUDENT")) {
        	
        	// if the user is a student get only his enrollments 
            int studentId = studentDao.getStudentIdByUserId(user.getUserId());
            enrollments = enrollmentDao.getAllByStudentId(studentId);
            
        }

        model.addAttribute("enrollments", enrollments);
        
        List<Course> courses = courseDao.getAll();
        model.addAttribute("courses", courses);
        model.addAttribute("fullenrollment", true);
        
        return "enrollment/allEnrollments";
    }
	
	
	 @GetMapping({ "/admin/academics/courses/{courseId}/{batchId}/enrollments/add",  "/staff/academics/courses/{courseId}/{batchId}/enrollments/add",  "/student/academics/courses/{courseId}/{batchId}/enrollments/add" })
    public String addEnrollment(@PathVariable("courseId") String courseId, @PathVariable("batchId") String batchId, Model model) {
		 
     
     User user = securityService.findLoggedInUser();
     
     model.addAttribute("title", "Enrollments");
     
     model.addAttribute("submiturl", "/" + user.getSmallRole() + "/academics/courses/" + courseId + "/" + batchId + "/enrollments/add");
     
     List<Student> students = studentDao.getAll();
     model.addAttribute("students", students);
     
     int amount = batchDao.getFee(batchId, courseId);
     model.addAttribute("amount", amount);
     
     Enrollment enrollment = new Enrollment();
     enrollment.setCourseId(courseId);
     enrollment.setBatchId(batchId);
     
     if (user.getRole().equals("ROLE_STUDENT")) {
    	 
         
         int studentId = studentDao.getStudentIdByUserId(user.getUserId());
         enrollment.setStudentId(studentId);
         model.addAttribute("buttonmessage", "Pay and Finish");
         model.addAttribute("transactionMode", "Online");
     }
     
     else {
         model.addAttribute("buttonmessage", "Accept Payment and Finish");
         model.addAttribute("transactionMode", "Offline");
     }
     
     model.addAttribute("enrollment", enrollment);
     return "enrollment/addEnrollment";
 }
	 
	 @PostMapping({ "/admin/academics/courses/{courseId}/{batchId}/enrollments/add", "/staff/academics/courses/{courseId}/{batchId}/enrollments/add",  "/student/academics/courses/{courseId}/{batchId}/enrollments/add" })
      public String addEnrollment(@PathVariable("courseId") String courseId, @PathVariable("batchId") String batchId,  @Valid @ModelAttribute("enrollment") Enrollment enrollment, BindingResult bindingResult, Model model) {
		 
		 User user = securityService.findLoggedInUser(); 
		 
         if (user.getRole().equals("ROLE_STUDENT")) {
    	 
        	int studentId = studentDao.getStudentIdByUserId(user.getUserId());
            enrollment.setStudentId(studentId);          
         }
         
         int studentId = enrollment.getStudentId();
     
         if (enrollmentDao.getByStudentAndCourse(studentId, courseId) != null) {
    	 
    	    bindingResult.rejectValue("studentId", "Duplicate.enrollment.studentId");
         
          }
     
       if (bindingResult.hasErrors()) {
    	 
         model.addAttribute("title", "Enrollments");

         int amount = batchDao.getFee(batchId, courseId);
         model.addAttribute("amount", amount);
         if (user.getRole().equals("ROLE_STUDENT")) {
        	 
             model.addAttribute("buttonmessage", "Pay and Finish");
             model.addAttribute("transactionMode", "Online");
         } else {
             model.addAttribute("buttonmessage", "Accept Payment and Finish");
             model.addAttribute("transactionMode", "Offline");
         }
         
         model.addAttribute("submiturl", "/" + user.getSmallRole() + "/academics/courses/" + courseId + "/" + batchId + "/enrollments/add");
         
         List<Student> students = studentDao.getAll();
         model.addAttribute("students", students);
         return "enrollment/addEnrollment";
         
     }
     
     Transaction transaction = new Transaction();
     int amount = batchDao.getFee(batchId, courseId);
     transaction.setAmount(amount);
     transaction.setTransactionMode("Offline");
     
     if (user.getRole().equals("ROLE_STUDENT")) {
    	 
         
         transaction.setTransactionMode("Online");
         transaction = transactionDao.save(transaction);
         return "redirect:" + paymentService.createPayment(user, transaction.getTransactionId(), amount, courseId, batchId);
         
     } else {
    	 
         transaction.setIsSuccess(true);
         transaction = transactionDao.save(transaction);
         enrollment.setTransaction(transaction);
         enrollmentDao.save(enrollment);
         return "redirect:/" + user.getSmallRole() + "/academics/courses/" + courseId + "/" + batchId + "/enrollments";
         
     }
 }
	 
	 
	 @GetMapping({ "/admin/academics/enrollments/{enrollmentId}", "/staff/academics/enrollments/{enrollmentId}",  "/student/academics/enrollments/{enrollmentId}" })
     public String viewEnrollment(@PathVariable("enrollmentId") int enrollmentId, Model model) {
        model.addAttribute("title", "Enrollment");
     
       Enrollment enrollment = enrollmentDao.get(enrollmentId);
       model.addAttribute("enrollment", enrollment);
       
       return "enrollment/enrollmentDetails";
 }
	 
	 @GetMapping({ "/admin/academics/enrollments/{enrollmentId}/edit", "/staff/academics/enrollments/{enrollmentId}/edit" })
	    public String editEnrollment(@PathVariable("enrollmentId") int enrollmentId, Model model) {
		 
	        User user = securityService.findLoggedInUser();
	        
	        model.addAttribute("title", "Enrollments");
	       
	        model.addAttribute("submiturl", "/" + user.getSmallRole() + "/academics/enrollments/" + enrollmentId + "/edit");
	        model.addAttribute("edit", "true");
	        
	        Enrollment enrollment = enrollmentDao.get(enrollmentId);
	        model.addAttribute("enrollment", enrollment);
	        
	        return "enrollment/addEnrollment";
	    }
	 
	 
	 @PostMapping({ "/admin/academics/enrollments/{enrollmentId}/edit", "/staff/academics/enrollments/{enrollmentId}/edit" })
	    public String editEnrollment(@PathVariable("enrollmentId") int enrollmentId, @Valid @ModelAttribute("enrollment") Enrollment enrollment, BindingResult bindingResult, Model model) {
		 
	        User user = securityService.findLoggedInUser();
	        
	        if (bindingResult.hasErrors()) {
	            model.addAttribute("title", "Enrollments");
	            
	            model.addAttribute("submiturl", "/" + user.getSmallRole() + "/academics/enrollments/" + enrollmentId + "/edit");
	            model.addAttribute("edit", "true");
	            return "enrollment/addEditEnrollment";
	        }
	        
	        Enrollment oldEnrollment = enrollmentDao.get(enrollmentId);
	        String courseId = oldEnrollment.getCourseId();
	        String batchId = oldEnrollment.getBatchId();
	        
	        enrollmentDao.update(enrollment);
	        return "redirect:/" + user.getSmallRole() + "/academics/courses/" + courseId + "/" + batchId + "/enrollments";
	    }
	 
	 
	 
	 @GetMapping({ "/admin/academics/courses/{courseId}/{batchId}/enrollments", "/staff/academics/courses/{courseId}/{batchId}/enrollments", "/teacher/academics/courses/{courseId}/{batchId}/enrollments" })
         public String listEnrollmentsByBatch(@PathVariable("courseId") String courseId, @PathVariable("batchId") String batchId, Model model) {
   
     
               User user = securityService.findLoggedInUser();
               String role = user.getRole();
               
               // check if the teacher is assigned to same batch 
               
               if (role.equals("ROLE_TEACHER")) {
            	   
               int teacherId = teacherDao.getTeacherIdByUserId(user.getUserId());
               List<Batch> batches = batchDao.getAllByTeacherId(teacherId);
               boolean forbidden = true;
               for (Batch batch : batches) {
                      if (batch.getBatchId().equals(batchId) && batch.getCourse().getCourseId().equals(courseId)) {
                                     forbidden = false;
                                           break;
                                  }
                        }
                    if (forbidden) {
                              throw new AccessDeniedException("This batch is not assigned to you");
                    }
                }
     
                    model.addAttribute("title", "Enrollments");
          
                    List<Enrollment> enrollments = enrollmentDao.getAllByBatch(courseId, batchId);
                    model.addAttribute("enrollments", enrollments);
                    
          return "enrollment/allEnrollments";
 }
	 
	 
	   
	 @GetMapping("/admin/academics/enrollments/{enrollmentId}/delete")
	 public ModelAndView deleteEnrollment(@PathVariable("enrollmentId") int enrollmentId) {
		 
	        ModelAndView model = new ModelAndView("redirect:/admin/academics/enrollments");
	        
	        enrollmentDao.delete(enrollmentId);
	        
	        return model;
	    }
	 
	 @GetMapping("/admin/academics/courses/{courseId}/enrollments")
	    public String listEnrollmentsByCourse(@PathVariable("courseId") String courseId, Model model) {
		 
	        model.addAttribute("title", "Enrollments");
	       
	        List<Enrollment> enrollments = enrollmentDao.getAllByCourseId(courseId);
	        model.addAttribute("enrollments", enrollments);
	        return "enrollment/allEnrollments";
	    }
	 
	 
	 @GetMapping("/student/transaction/{courseId}/{batchId}")
	    public String processTransaction(String transaction_id, @PathVariable("courseId") String courseId, @PathVariable("batchId") String batchId) {
		 
	        int transactionId = paymentService.getTransactionId(transaction_id);
	        String status = paymentService.getTransactionDetails(transactionId);
	        
	        if (status.equals("completed")) {
	        	
	            transactionDao.setSuccess(transactionId);
	            Enrollment enrollment = new Enrollment();
	            enrollment.setCourseId(courseId);
	            enrollment.setBatchId(batchId);
	            int userId = securityService.findLoggedInUser().getUserId();
	            int studentId = studentDao.getStudentIdByUserId(userId);
	            
	            enrollment.setStudentId(studentId);
	            Transaction transaction = transactionDao.get(transactionId);
	            enrollment.setTransaction(transaction);
	            enrollmentDao.save(enrollment);
	            
	            return "redirect:/student/academics/enrollments";
	        }
	        
	        return "redirect:/student/academics/enrollments?error";
	    }
	 
	 
	 @GetMapping({ "/admin/transactions", "/staff/transactions" })
     public String allTransactions(Model model) {
		
        model.addAttribute("title", "Transactions");
        
        List<Transaction> transactions = transactionDao.getAll();
        
        model.addAttribute("transactions", transactions );
        
        
        return "enrollment/transactions";
    }
	


}
