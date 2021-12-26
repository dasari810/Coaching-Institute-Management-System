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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dbms.spark.dao.SubjectDao;
import com.dbms.spark.dao.TeacherDao;
import com.dbms.spark.models.Subject;
import com.dbms.spark.services.SecurityServices;

@Transactional
@Controller
public class SubjectController {
	
	@Autowired
	SubjectDao subjectDao ;
	
	@Autowired
	SecurityServices securityServices ;
	
	@Autowired
	TeacherDao teacherDao ;

	
	 @GetMapping({ "/admin/academics/subjects", "/staff/academics/subjects","/teacher/academics/subjects" , "/subjects" , "/student/academics/subjects" })
	 
	    public String getAllSubjects(Model model) {
		 
	        model.addAttribute("title", "Subjects");
	       
	        
	        List<Subject> subjects = subjectDao.getAll();
	        model.addAttribute("subjects", subjects);
	        
	        return "subject/allSubjects";
	    }
	 
	 
	 
	 @GetMapping("/admin/academics/subjects/{subjectId}/edit")
	    public String editSubject(@PathVariable("subjectId") String subjectId, Model model) {
		 
	        model.addAttribute("title", "Edit Subjects");
	      
	        model.addAttribute("submiturl", "/admin/academics/subjects/" + subjectId + "/edit");
	        model.addAttribute("edit", "true");
	        
	        Subject subject = subjectDao.get(subjectId);
	        model.addAttribute("subject", subject);
	        
	        return "subject/addSubject";
	    }

	 @PostMapping("/admin/academics/subjects/{subjectId}/edit")
	    public String editSubject(@PathVariable("subjectId") String subjectId, @Valid @ModelAttribute("subject") Subject subject, BindingResult bindingResult, Model model) {
		 
	        if (bindingResult.hasErrors()) {
	        	
	            model.addAttribute("title", "Edit Subjects");
	           
	            model.addAttribute("submiturl", "/admin/academics/subjects/" + subjectId + "/edit");
	            model.addAttribute("edit", "true");
	            return "subject/addSubject";
	        }
	        
	        subjectDao.update(subject);
	        return "redirect:/admin/academics/subjects";
	    }
	 
	 @GetMapping("/admin/academics/subjects/add")
	    public String addSubject(Model model) {
		 
	        model.addAttribute("title", "Add Subject ");
	       
	        model.addAttribute("submiturl", "/admin/academics/subjects/add");
	        
	        Subject subject = new Subject();
	        model.addAttribute("subject", subject);
	        
	        return "subject/addSubject";
	    }
	 
	 @PostMapping("/admin/academics/subjects/add")
	    public String addSubject(@Valid @ModelAttribute("subject") Subject subject, BindingResult bindingResult, Model model) {
		 
		  if (subjectDao.getBySubjectName(subject.getSubjectName()) != null) {
			 bindingResult.rejectValue("subjectName", "Duplicate.subject.subjectName");
	        }
		  
		  if (subjectDao.get(subject.getSubjectId()) != null) {
				 bindingResult.rejectValue("subjectId", "Duplicate.subject.subjectId");
		        }
		       
	        if (bindingResult.hasErrors()) {
	        	
	            model.addAttribute("title", "Add Subject");
	           
	            model.addAttribute("submiturl", "/admin/academics/subjects/add");
	            
	            return "subject/addSubject";
	        }
	        
	        subjectDao.save(subject);
	        return "redirect:/admin/academics/subjects";
	    }
	 
	 @GetMapping(value = "/admin/academics/subjects/{subjectId}/delete")
	    public ModelAndView deleteSubject(@PathVariable(value="subjectId") String subjectId) {
		 
	        ModelAndView model = new ModelAndView("redirect:/admin/academics/subjects");
	        
	        subjectDao.delete(subjectId);
	        
	        return model;
	    }
}
