package com.dbms.spark.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.dbms.spark.dao.StudentDao;
import com.dbms.spark.dao.TestDao;
import com.dbms.spark.models.Batch;
import com.dbms.spark.models.Course;
import com.dbms.spark.models.Test;
import com.dbms.spark.models.User;
import com.dbms.spark.services.SecurityServices;

@Transactional
@Controller
public class TestController {
	
	@Autowired
	StudentDao studentDao ;
	
	@Autowired
	CourseDao courseDao ;
	
	@Autowired
	TestDao testDao ; 
	
	@Autowired 
	BatchDao batchDao ;
	
	@Autowired
	SecurityServices securityServices ;
	
	@GetMapping({ "/admin/academics/tests", "/staff/academics/tests", "/teacher/academics/tests" })
    public String allTests(Model model) {
		
        model.addAttribute("title", "Tests");
       
        List<Test> tests = testDao.getAll();
        System.out.println(tests);
        model.addAttribute("tests", tests);
        
        return "test/allTests";
    }
	
	@GetMapping("/student/academics/tests")
    public String listTestsStudent(Model model) {
		
        model.addAttribute("title", "Tests");
      
        User user= securityServices.findLoggedInUser();
        
        int studentId = studentDao.getStudentIdByUserId(user.getUserId());
        List<Map<String, Object>> tests = testDao.getAllByStudentId(studentId);
        model.addAttribute("tests", tests);
        
        return "test/allTests";
    }
	
	
	 @GetMapping({ "/admin/academics/courses/{courseId}/{batchId}/tests/add", "/staff/academics/courses/{courseId}/{batchId}/tests/add" })
	    public String addTest(@PathVariable("courseId") String courseId, @PathVariable("batchId") String batchId,Model model) {
		 
		    User user= securityServices.findLoggedInUser();
		    
	        model.addAttribute("title", "Tests");
	       
	        model.addAttribute("submiturl", "/" + user.getSmallRole() + "/academics/courses/" + courseId + "/" + batchId + "/tests/add");
	        
	       Course course = courseDao.get(courseId);
	       Batch batch = batchDao.get(batchId, courseId);
	        
	        Test test = new Test();
	        test.setBatchId(batchId);
	        test.setCourseId(courseId);
	        
	        model.addAttribute("test", test);
	        model.addAttribute("course",course);
	        model.addAttribute("batch",batch);
	        return "test/addTest";
	    }
	 
	 @PostMapping({ "/admin/academics/courses/{courseId}/{batchId}/tests/add", "/staff/academics/courses/{courseId}/{batchId}/tests/add" })
	    public String addTest(@PathVariable("courseId") String courseId, @PathVariable("batchId") String batchId ,@Valid @ModelAttribute("test") Test test, BindingResult bindingResult, Model model) {
		 
		 User user= securityServices.findLoggedInUser();
		 
		 Course course = courseDao.get(courseId);
	     Batch batch = batchDao.get(batchId, courseId);
	     test.setCourseId(courseId);
	     test.setBatchId(batchId);
	        
	        if (bindingResult.hasErrors()) {
	        	
	            model.addAttribute("title", "Tests");
	            model.addAttribute("course",course);
		        model.addAttribute("batch",batch);
	           
	            model.addAttribute("submiturl", "/" + user.getSmallRole() + "/academics/courses/" + courseId + "/" + batchId  + "/tests/add");
	           
	            return "test/addTest";
	        }
	       
	        testDao.save(test);
	        return "redirect:/" + user.getSmallRole() + "/academics/tests";
	    }

	 
	 @GetMapping({ "/admin/academics/tests/{testId}", "/staff/academics/tests/{testId}" })
	    public String testDetails(@PathVariable("testId") int testId, Model model) {
		 
	        model.addAttribute("title", "Tests");
	       
	        Test test = testDao.get(testId);
	        System.out.print(test);
	        Batch batch = batchDao.get(test.getBatchId(), test.getCourseId());
	        Course course = courseDao.get(test.getCourseId());
	        model.addAttribute("test", test);
	        model.addAttribute("batch", batch);
	        model.addAttribute("course",course);
	        
	        return "test/testDetails";
	    }
	 
	 @GetMapping({ "/admin/academics/tests/{testId}/edit", "/staff/academics/tests/{testId}/edit" })
	    public String editTest(@PathVariable("testId") int testId, Model model) {
		 
	        User user  = securityServices.findLoggedInUser();
	        
	        model.addAttribute("title", "Tests");
	        
	        model.addAttribute("submiturl", "/" + user.getSmallRole() + "/academics/tests/" + testId + "/edit");
	        model.addAttribute("edit", "true");

	        Test test = testDao.get(testId);
	        Batch batch = batchDao.get(test.getBatchId(), test.getCourseId());
	        Course course = courseDao.get(test.getCourseId());
	        
	        model.addAttribute("test", test);
	        model.addAttribute("batch", batch);
	        model.addAttribute("course",course);
	        
	        return "test/addTest";
	    }
	 
	 @PostMapping({ "/admin/academics/tests/{testId}/edit", "/staff/academics/tests/{testId}/edit" })
	    public String editTest(@PathVariable("testId") int testId,  @Valid @ModelAttribute("test") Test test, BindingResult bindingResult, Model model) {
		 
		     String role  = securityServices.findLoggedInUser().getSmallRole();
		     
		     Test test1 = testDao.get(testId);
	         
	        if (bindingResult.hasErrors()) {
	            model.addAttribute("title", "Tests");
	            
	            model.addAttribute("submiturl", "/" + role + "/academics/tests/" + testId + "/edit");
	            model.addAttribute("edit", "true");
	            
		        Batch batch = batchDao.get(test1.getBatchId(), test1.getCourseId());
		        Course course = courseDao.get(test1.getCourseId());
		        
		   
		        model.addAttribute("batch", batch);
		        model.addAttribute("course",course);
	            
	            return "test/addTest";
	        }
	        
	        testDao.update(test);
	        return "redirect:/" + role + "/academics/tests/" + testId;
	        
	    }
	 
	
	 @GetMapping({"/admin/academics/tests/{testId}/delete", "/staff/academics/tests/{testId}/delete" })
	 public ModelAndView deleteTest(@PathVariable("testId") int testId) {
		 
	        ModelAndView model = new ModelAndView("redirect:/admin/academics/tests");
	        testDao.delete(testId);
	      
	        return model;
	    }
	 
	 @GetMapping({ "/teacher/academics/tests/{testId}", "/student/academics/tests/{testId}" })
	    public String viewTestRedirect(@PathVariable("testId") int testId, Model model) {
		 
	        String role = securityServices.findLoggedInUser().getSmallRole();
	        return "redirect:/" + role + "/academics/tests/" + testId + "/results";
	        
	    }


}
