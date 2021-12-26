package com.dbms.spark.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.dbms.spark.dao.BatchDao;
import com.dbms.spark.dao.CourseDao;
import com.dbms.spark.dao.CourseSubjectDao;
import com.dbms.spark.dao.SubjectDao;
import com.dbms.spark.models.Batch;
import com.dbms.spark.models.Course;
import com.dbms.spark.models.CourseSubjectDetails;
import com.dbms.spark.models.Subject;

@Transactional
@Controller
public class CourseController {
	
	 @Autowired
	 private CourseDao courseDao;
	 
	 @Autowired
	 private CourseSubjectDao courseSubjectDao;
	 
	 @Autowired
	 BatchDao batchDao ; 
	 
	 @Autowired
	 SubjectDao subjectDao ;


	@GetMapping({ "/admin/academics/courses", "/student/academics/courses", "/staff/academics/courses", "/teacher/academics/courses", "/courses" })
    public String listCourses(Model model) {
		
    model.addAttribute("title", "Courses");
    
    List<Map<String, Object>> courses = courseDao.getAllList();
    List<CourseSubjectDetails> courseSubjects = courseSubjectDao.getAll();
    
    for (Map<String, Object> course : courses) {
    	
        List<Subject> subjects = new ArrayList<>();
        
        for (CourseSubjectDetails courseSubject : courseSubjects) {
        	
            if (courseSubject.getCourseId().equals(course.get("courseId"))) {
            	
                subjects.add(courseSubject.getSubject());
                
            }
        }
        
        course.put("subjects", subjects);
    }
    
    model.addAttribute("courses", courses);
    
    return "course/allCourses";
}
	
	
	 @GetMapping("/admin/academics/courses/add")
	    public String addCourse(Model model) {
		 
	        model.addAttribute("title", "Courses");
	       
	        model.addAttribute("submiturl", "/admin/academics/courses/add");
	        
	        Course course = new Course();
	        
	        model.addAttribute("course", course);
	        return "course/addCourse";
	    }
	 
	 
	 @PostMapping("/admin/academics/courses/add")
	    public String addCourse(@Valid @ModelAttribute("course") Course course, BindingResult bindingResult, Model model) {
		 
	        
	        if (courseDao.get(course.getCourseId()) != null) {
	            bindingResult.rejectValue("courseId", "Duplicate.course.courseId");
	        }
	        
	        if (bindingResult.hasErrors()) {
	        	
	            model.addAttribute("title", "Courses");
	           
	            model.addAttribute("submiturl", "/admin/academics/courses/add");
	            
	            return "course/addEditCourse";
	        }
	        
	        courseDao.save(course);
	        return "redirect:/admin/academics/courses";
	    }
	 
	 
	 @GetMapping({ "/admin/academics/courses/{courseId}", "/student/academics/courses/{courseId}", "/staff/academics/courses/{courseId}", "/teacher/academics/courses/{courseId}" })
     public String viewCourse(@PathVariable("courseId") String courseId, Model model) {
		 
     model.addAttribute("title", "Courses");
     
     Course course = courseDao.get(courseId);
     model.addAttribute("course", course);
     
     List<Subject> subjects = subjectDao.getSubjectsInCourse(courseId);
     model.addAttribute("subjects", subjects);
     
     List<Batch> batches = batchDao.getAllByCourseId(courseId);
     model.addAttribute("batches", batches);
    
     return "course/courseDetails";
     
 }
	 
	 @GetMapping("/admin/academics/courses/{courseId}/edit")
	    public String editCourse(@PathVariable("courseId") String courseId, Model model) {
		 
	        model.addAttribute("title", "Courses");
	        
	        model.addAttribute("submiturl", "/admin/academics/courses/" + courseId + "/edit");
	        model.addAttribute("edit", "true");
	        
	        Course course = courseDao.get(courseId);
	        model.addAttribute("course", course);
	        
	        return "course/addCourse";
	    }
	 
	 
	 @PostMapping("/admin/academics/courses/{courseId}/edit")
	    public String editCourse(@PathVariable("courseId") String courseId, @Valid @ModelAttribute("course") Course course, BindingResult bindingResult, Model model) {
		 
	        if (bindingResult.hasErrors()) {
	        	
	            model.addAttribute("title", "Academic Portal - Courses");
	            
	            model.addAttribute("submiturl", "/admin/academics/courses/" + courseId + "/edit");
	            model.addAttribute("edit", "true");
	            return "course/addCourse";
	            
	        }
	        
	        courseDao.update(course);
	        return "redirect:/admin/academics/courses/" + courseId;
	    }
	 
	 
	 @GetMapping("/admin/academics/courses/{courseId}/delete")
	 public ModelAndView deleteCourse(@PathVariable(value="courseId") String courseId) {
		 
	        ModelAndView model = new ModelAndView("redirect:/admin/academics/courses");
	        
	        courseDao.delete(courseId);
	        
	        return model;
	    }
	 
	 @GetMapping("/admin/academics/courses/{courseId}/add-subject")
	    public String addSubjectToCourse(@PathVariable("courseId") String courseId, Model model) {
		 
	        model.addAttribute("title", "Courses");
	        
	        model.addAttribute("submiturl", "/admin/academics/courses/" + courseId + "/add-subject");
	        
	        List<Subject> subjectsPresent = subjectDao.getSubjectsInCourse(courseId);
	        model.addAttribute("subjectsPresent", subjectsPresent);
	        
	        List<Subject> subjectsNotPresent = subjectDao.getSubjectsNotInCourse(courseId);
	        model.addAttribute("subjectsNotPresent", subjectsNotPresent);
	        
	        return "course/addCourseSubject";
	    }
	 
	 
	 @PostMapping("/admin/academics/courses/{courseId}/add-subject")
	 
	    public ResponseEntity<String> addSubjectToCourse(@PathVariable("courseId") String courseId, @RequestParam String subjectId, Model model) {
		 
	        courseSubjectDao.save(courseId, subjectId);
	        
	        return new ResponseEntity<>(HttpStatus.OK);
	        
	    }
	 
	   @PostMapping("/admin/academics/courses/{courseId}/delete-subject")
	    public ResponseEntity<Integer> deleteSubjectFromCourse(@PathVariable("courseId") String courseId, @RequestParam String subjectId, Model model) {
		   
	        courseSubjectDao.delete(courseId, subjectId);
	        
	        return new ResponseEntity<>(HttpStatus.OK);
	    }
	 


}
