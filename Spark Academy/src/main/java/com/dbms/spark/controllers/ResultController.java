package com.dbms.spark.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.dbms.spark.dao.ResultDao;
import com.dbms.spark.dao.StudentDao;
import com.dbms.spark.dao.TestDao;
import com.dbms.spark.models.Result;
import com.dbms.spark.models.Student;
import com.dbms.spark.models.Test;
import com.dbms.spark.services.SecurityServices;

@Transactional
@Controller
public class ResultController {

	
	 @Autowired
	    private SecurityServices securityServices;


	 @Autowired
	    private TestDao testDao;
	 
	 @Autowired
      private ResultDao resultDao;

     @Autowired
       private StudentDao studentDao;
     
     public Map<Integer, Integer> getMarksToRank(List<Result> results) {
         Set<Integer> marks = new LinkedHashSet<>();
         for (Result result : results) {
             marks.add(result.getMarksScored());
         }
         ArrayList<Integer> marksList = new ArrayList<>();
         marksList.addAll(marks);
         Collections.sort(marksList, Collections.reverseOrder());
         Map<Integer, Integer> marksToRank = new HashMap<>();
         int count = 1;
         for (Integer mark : marksList) {
             marksToRank.put(mark, count++);
         }
         return marksToRank;
     }
     
     @GetMapping({ "/admin/academics/tests/{testId}/results", "/student/academics/tests/{testId}/results",   "/teacher/academics/tests/{testId}/results", "/staff/academics/tests/{testId}/results" })
      public String listResults(@PathVariable("testId") int testId, Model model) {
    	 
    
     model.addAttribute("title", "Results");
    
     List<Result> results = resultDao.getAllByTestId(testId);
     model.addAttribute("results", results);
     
     Map<Integer, Integer> marksToRank = getMarksToRank(results);
     model.addAttribute("marksToRank", marksToRank);
     
     Test test = testDao.get(testId);
     int maximumMarks = test.getMaximumMarks();
     model.addAttribute("maximumMarks", maximumMarks);
     
     return "result/allResults";
 }
     
     @GetMapping({ "/admin/academics/tests/{testId}/results/add", "/staff/academics/tests/{testId}/results/add" })
     public String addResult(@PathVariable("testId") int testId, Model model) {
    	 
         String role = securityServices.findLoggedInUser().getSmallRole();
         
         model.addAttribute("title", "Results");
         
         model.addAttribute("submiturl", "/" + role + "/academics/tests/" + testId + "/results/add");
         
         Test test = testDao.get(testId);
         List<Student> students = studentDao.getAllByBatchId(test.getBatchId());
         model.addAttribute("students", students);
         
         Result result = new Result();
         model.addAttribute("result", result);
         
         int maximumMarks = test.getMaximumMarks();
         model.addAttribute("maximumMarks", maximumMarks);
         
         return "result/addResult";
     }



     @PostMapping({ "/admin/academics/tests/{testId}/results/add", "/staff/academics/tests/{testId}/results/add" })
     public String addResult(@PathVariable("testId") int testId, @Valid @ModelAttribute("result") Result result, BindingResult bindingResult, Model model) {
    	 
         Test test = testDao.get(testId);
         
         if (result.getMarksScored() > test.getMaximumMarks()) {
             bindingResult.rejectValue("marksScored", "Invalid.result.marksScored");
         }
         
         int studentId =  result.getStudent().getStudentId();
         
         if (resultDao.get(testId, studentId) != null) {
        	 bindingResult.rejectValue("student.studentId", "Duplicate.result.studentId");
         }
         
        
         String role = securityServices.findLoggedInUser().getSmallRole();
         
         if (bindingResult.hasErrors()) {
        	 
             model.addAttribute("title", "Results");
            
             model.addAttribute("submiturl", "/" + role + "/academics/tests/" + testId + "/results/add");
             
             List<Student> students = studentDao.getAllByCourseId(test.getCourseId());
             model.addAttribute("students", students);
             
             int maximumMarks = test.getMaximumMarks();
             model.addAttribute("maximumMarks", maximumMarks);
             
             return "result/addResult";
         }
         
         resultDao.save(result);
         return "redirect:/" + role + "/academics/tests/" + testId + "/results";
     }
     
     
     @GetMapping({ "/admin/academics/tests/{testId}/results/{studentId}/edit", "/staff/academics/tests/{testId}/results/{studentId}/edit" })
     public String editResult(@PathVariable("testId") int testId, @PathVariable("studentId") int studentId, Model model) {
    	 
    	 String role = securityServices.findLoggedInUser().getSmallRole();
    	 
         model.addAttribute("title", "Results");
        
         model.addAttribute("submiturl", "/" + role + "/academics/tests/" + testId + "/results/" + studentId + "/edit");
         model.addAttribute("edit", "true");
         
         Result result = resultDao.get(testId, studentId);
         model.addAttribute("result", result);
         
         Test test = testDao.get(testId);
         int maximumMarks = test.getMaximumMarks();
         model.addAttribute("maximumMarks", maximumMarks);
         
         return "result/addResult";
     }
     
      
     @PostMapping({ "/admin/academics/tests/{testId}/results/{studentId}/edit", "/staff/academics/tests/{testId}/results/{studentId}/edit" })
     public String editResult(@PathVariable("testId") int testId, @PathVariable("studentId") int studentId, @Valid @ModelAttribute("result") Result result, BindingResult bindingResult, Model model) {
    	 
    	 System.out.println(result);
         Test test = testDao.get(result.getTestId());
         if (result.getMarksScored() > test.getMaximumMarks()) {
             bindingResult.rejectValue("marksScored", "Invalid.result.marksScored");
         }
         
         String role = securityServices.findLoggedInUser().getSmallRole();
         
         if (bindingResult.hasErrors()) {
        	 
             model.addAttribute("title", "Results");
             Result result1 = resultDao.get(testId, studentId);
             result.setStudent(result1.getStudent());
             model.addAttribute("result", result);
             model.addAttribute("submiturl", "/" + role + "/academics/tests/" + testId + "/results/" + studentId + "/edit");
             model.addAttribute("edit", "true");
             
             
             int maximumMarks = test.getMaximumMarks();
             model.addAttribute("maximumMarks", maximumMarks);
             
             return "result/addResult";
         }
         
         Student student = result.getStudent();
         student.setStudentId(studentId);
         result.setStudent(student);

         
         resultDao.updateMarks(result);
         
         return "redirect:/" + role + "/academics/tests/" + testId + "/results/";
     }
     
    
     @GetMapping({"/admin/academics/tests/{testId}/results/{studentId}/delete", "/staff/academics/tests/{testId}/results/{studentId}/delete"})
	 public ModelAndView deleteResult(@PathVariable("testId") int testId,
             @PathVariable("studentId") int studentId) {
		   
    	 
    	    String role = securityServices.findLoggedInUser().getSmallRole();
    	    
    	    ModelAndView model = new ModelAndView("redirect:/admin/academics/tests/"); 
    	    
    	    if(role.equals("staff")) {
    	    	model = new ModelAndView("redirect:/staff/academics/tests/");
    	    }
    	    else {
    	    	 model = new ModelAndView("redirect:/admin/academics/tests/");
    	    }
	       
	        resultDao.delete(testId, studentId);
	      
	        return model;
	    }
   
}
