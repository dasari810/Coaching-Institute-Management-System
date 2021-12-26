package com.dbms.spark.controllers;

import java.util.ArrayList;
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

import com.dbms.spark.dao.BatchDao;
import com.dbms.spark.dao.CourseDao;
import com.dbms.spark.dao.SubjectDao;
import com.dbms.spark.dao.TeacherBatchDao;
import com.dbms.spark.dao.TeacherDao;
import com.dbms.spark.models.Batch;
import com.dbms.spark.models.Course;
import com.dbms.spark.models.Subject;
import com.dbms.spark.models.Teacher;
import com.dbms.spark.models.User;
import com.dbms.spark.services.SecurityServices;

@Transactional
@Controller
public class BatchController {
	
	@Autowired
	SecurityServices securityServices; 
	
	@Autowired
    private TeacherBatchDao teacherBatchDao;

	@Autowired 
	private SubjectDao subjectDao; 
	
    @Autowired
    private TeacherDao teacherDao;
	
	@Autowired 
	private CourseDao courseDao;
	
	@Autowired
    private BatchDao batchDao;

	
	 @GetMapping({ "/admin/academics/batches", "/student/academics/batches", "/staff/academics/batches","/teacher/academics/batches" })
       public String listBatches(Model model) {
		 
          model.addAttribute("title", "Batches");
          
          List<Batch> batches = batchDao.getAll();
          model.addAttribute("batches", batches);
          
          return "batch/allBatches";
    }
	 
	 @GetMapping("/admin/academics/batches/add")
	    public String addBatch_direct(Model model) {
		 
	        model.addAttribute("title", "Batches");
	       
	        model.addAttribute("submiturl", "/admin/academics/batches/add");
	        
	        List<Course> courses = courseDao.getAll();
	        model.addAttribute("courses", courses);
	        
	        Batch batch = new Batch();
	        model.addAttribute("batch", batch);
	        
	        return "batch/addBatch";
	    }
	 
	 
	 @PostMapping("/admin/academics/batches/add")
	    public String addBatch_direct(@Valid @ModelAttribute("batch") Batch batch, BindingResult bindingResult, Model model) {
		 
	        String batchId = batch.getBatchId();
	        String courseId = batch.getCourse().getCourseId();
	        
	        if (batchDao.get(batchId, courseId) != null) {
	        	bindingResult.rejectValue("batchId", "Duplicate.batch.batchId");
	        }
	        
	        if (bindingResult.hasErrors()) {
	            model.addAttribute("title", "Batches");
	           
	            model.addAttribute("submiturl", "/admin/academics/batches/add");
	            
	            List<Course> courses = courseDao.getAll();
	            model.addAttribute("courses", courses);
	            
	            return "batch/addBatch";
	        }
	        
	        batchDao.save(batch);
	        return "redirect:/admin/academics/batches";
	    }

	 
	 @GetMapping({ "/admin/academics/courses/{courseId}/{batchId}", "/student/academics/courses/{courseId}/{batchId}", "/staff/academics/courses/{courseId}/{batchId}", "/teacher/academics/courses/{courseId}/{batchId}" })
       public String viewBatch(@PathVariable("courseId") String courseId, @PathVariable("batchId") String batchId, Model model) {
		 
         model.addAttribute("title", "Batches");
     
         Batch batch = batchDao.get(batchId, courseId);
         model.addAttribute("batch", batch);
     
         List<Teacher> teachers = teacherDao.getAllByBatch(batchId, courseId);
         model.addAttribute("teachers", teachers);
     
         return "batch/batchDetails";
 }
	 
	 @GetMapping("/admin/academics/courses/{courseId}/{batchId}/edit")
	    public String editBatch(@PathVariable("courseId") String courseId, @PathVariable("batchId") String batchId, Model model) {
		 
	        model.addAttribute("title", "Academic Portal - Batches");
	       
	        model.addAttribute("submiturl", "/admin/academics/courses/" + courseId + "/" + batchId + "/edit");
	        model.addAttribute("edit", "true");
	        
	        List<Course> courses = courseDao.getAll();
	        model.addAttribute("courses", courses);
	        
	        Batch batch = batchDao.get(batchId, courseId);
	        model.addAttribute("batch", batch);
	        
	        return "batch/addBatch";
	    }
	 
	 
	 @PostMapping("/admin/academics/courses/{courseId}/{batchId}/edit")
	    public String editBatch(@PathVariable("courseId") String courseId, @PathVariable("batchId") String batchId, @Valid @ModelAttribute("batch") Batch batch, BindingResult bindingResult, Model model) {
		 
	        if (bindingResult.hasErrors()) {
	        	
	            model.addAttribute("title", "Batches");
	           
	            model.addAttribute("submiturl", "/admin/academics/courses/" + courseId + "/" + batchId + "/edit");
	            model.addAttribute("edit", "true");
	            
	            List<Course> courses = courseDao.getAll();
	            model.addAttribute("courses", courses);
	            
	            return "batch/addEditBatch";
	        }
	        
	        Course course = batch.getCourse();
	        course.setCourseId(courseId);
	        batch.setCourse(course);

	        batchDao.update(batch);
	        
	        return "redirect:/admin/academics/courses/" + courseId + "/" + batchId;
	    }
	 
	 
	 @GetMapping("/admin/academics/courses/{courseId}/{batchId}/delete")
	 public ModelAndView deleteBatch(@PathVariable(value="courseId") String courseId , @PathVariable("batchId") String batchId) {
		 
	        ModelAndView model = new ModelAndView("redirect:/admin/academics/batches");
	        
	        batchDao.delete(batchId, courseId);
	        
	        return model;
	    }
	 
	 
	  @GetMapping("/admin/academics/courses/{courseId}/add-batch")
	    public String addBatch_course(@PathVariable("courseId") String courseId, Model model) {
		  
	        model.addAttribute("title", "Batches");
	       
	        model.addAttribute("submiturl", "/admin/academics/courses/" + courseId + "/add-batch");
	        
	        Course course = courseDao.get(courseId);
	        model.addAttribute("course", course);
	        
	        Batch batch = new Batch();
	        model.addAttribute("batch", batch);
	        
	        return "batch/addBatch";
	    }
	  
	  @PostMapping("/admin/academics/courses/{courseId}/add-batch")
	    public String addBatch_course(@PathVariable("courseId") String courseId, @Valid @ModelAttribute("batch") Batch batch, BindingResult bindingResult, Model model) {
		  
	        
	        
	        if (batchDao.get(batch.getBatchId(), courseId) != null) {
	        	bindingResult.rejectValue("batchId", "Duplicate.batch.batchId");
	        }
	        
	        if (bindingResult.hasErrors()) {
	        	
	            model.addAttribute("title", "Batches");
	           
	            model.addAttribute("submiturl", "/admin/academics/courses/" + courseId + "/add-batch");
	            
	            Course course = courseDao.get(courseId);
	            model.addAttribute("course", course);
	            
	            return "batch/addEditBatch";
	        }
	        
	        Course course = batch.getCourse();
	        course.setCourseId(courseId);
	        batch.setCourse(course);
	        
	        batchDao.save(batch);
	        
	        return "redirect:/admin/academics/courses/" + courseId;
	    }
	 
	  
	  @GetMapping("/admin/academics/courses/{courseId}/{batchId}/add-teacher")
	    public String addTeacherToBatch(@PathVariable("courseId") String courseId, @PathVariable("batchId") String batchId, Model model) {
		    
	        model.addAttribute("title", "Batches");
	        
	        
	        model.addAttribute("submiturl", "/admin/academics/courses/" + courseId + "/" + batchId + "/add-teacher");
	        
	        List<Teacher> teachersPresent = teacherDao.getTeachersInBatch(batchId, courseId);
	        List<Teacher> teachersNotPresentAll = teacherDao.getTeachersNotInBatch(batchId, courseId);

	        List<Teacher> teachersNotPresent = new ArrayList<>();
	        List<Subject> subjects = subjectDao.getSubjectsInCourse(courseId);
	        
	        // from all the teachers not present in the batch , filter those who teach subjects present in this batch 
	        
	        for (Teacher teacher : teachersNotPresentAll) {
	        	
	            boolean include = false;
	            for (Subject subject : subjects) {
	                if (teacher.getSubject().getSubjectId().equals(subject.getSubjectId())) {
	                    include = true;
	                    break;
	                }
	            }
	            
	            if (include) {
	                teachersNotPresent.add(teacher);
	            }
	        }
	        model.addAttribute("teachersPresent", teachersPresent);
	        model.addAttribute("teachersNotPresent", teachersNotPresent);
	        
	        return "batch/addTeacherToBatch";
	    }
	  
	  @PostMapping("/admin/academics/courses/{courseId}/{batchId}/add-teacher")
	    public ResponseEntity<String> addTeacherToBatch(@PathVariable("courseId") String courseId, @PathVariable("batchId") String batchId, @RequestParam String teacherId, Model model) {
		  
	        teacherBatchDao.save(teacherId, batchId, courseId);
	        
	        return new ResponseEntity<>(HttpStatus.OK);
	    }
	  
	  @PostMapping("/admin/academics/courses/{courseId}/{batchId}/delete-teacher")
	    public ResponseEntity<Integer> deleteTeacherFromBatch(@PathVariable("courseId") String courseId,  @PathVariable("batchId") String batchId, @RequestParam String teacherId, Model model) {
	        teacherBatchDao.delete(teacherId, batchId, courseId);
	        return new ResponseEntity<>(HttpStatus.OK);
	    }

	  
	  @GetMapping("/teacher/academics/batches-assigned")
	    public String listBatchesAssigned(Model model) {
	        model.addAttribute("title", "Academic Portal - Batches Assigned");
	        model.addAttribute("message", "View all the batches assigned to you");
	       
	        User user  = securityServices.findLoggedInUser();
	      
	        int teacherId = teacherDao.getTeacherIdByUserId(user.getUserId());
	        List<Batch> batches = batchDao.getAllByTeacherId(teacherId);	       
	       
	        model.addAttribute("batches", batches);
	        return "batch/listBatchesAssigned";
	    }

}
