package com.dbms.spark.controllers;

import java.time.LocalDate;
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

import com.dbms.spark.dao.EmployeeDao;
import com.dbms.spark.dao.PayrollDao;
import com.dbms.spark.models.Employee;
import com.dbms.spark.models.Payroll;
import com.dbms.spark.services.SecurityServices;

@Transactional
@Controller
public class PayrollController {
	
	    @Autowired
	    private SecurityServices securityServices;

	
	    @Autowired
	    private PayrollDao payrollDao;

	    @Autowired
	    private EmployeeDao employeeDao;

	  
	    @GetMapping("/admin/payroll")
	    public String listPayrolls(Model model, String employee) {
	    	
	        model.addAttribute("title", "Payroll Management");
	        List<Payroll> payroll;
	        
	        if (employee != null) {
	        	
	            
	            int employeeId = Integer.parseInt(employee.substring(0));
	            String role = employeeDao.getRole(employeeId);
	            
	            if (!(role == "ROLE_STAFF") && (role == "ROLE_TEACHER")) {
	            	
	                return "redirect:/admin/payroll";
	                
	            }
	            payroll = payrollDao.getAllByEmployeeId(employeeId);
	        }
	        else {
	        	
	            payroll = payrollDao.getAll();
	           
	        }
	        
	        model.addAttribute("payrolls", payroll);
	        return "payroll/allPayroll";
	    }
	    
	    
	    @GetMapping({ "/staff/payroll", "/teacher/payroll" })
	    public String listOwnPayrolls(Model model) {
	    	
	        model.addAttribute("title", "Payroll Management");
	        
	        int userId = securityServices.findLoggedInUser().getUserId();
	        int employeeId = employeeDao.getEmployeeIdByUserId(userId);
	        List<Payroll> payroll = payrollDao.getAllByEmployeeId(employeeId);
	        
	        model.addAttribute("payrolls", payroll);
	        return "payroll/allPayroll";
	    }
	    
	    @GetMapping("/admin/payroll/add")
	    public String addPayroll(Model model) {
	    	
	        model.addAttribute("title", "Payroll Management");
	        
	        model.addAttribute("submiturl", "/admin/payroll/add");
	        
	        List<Employee> employees = employeeDao.getAll();
	        model.addAttribute("employees", employees);
	        
	        Payroll payroll = new Payroll();
	        model.addAttribute("payroll", payroll);
	        
	        return "payroll/addPayroll";
	    }

	    
	    @PostMapping("/admin/payroll/add")
	    public String addPayroll(@Valid @ModelAttribute("payroll") Payroll payroll, BindingResult bindingResult, Model model) {
	    	
	        LocalDate localDate = LocalDate.now();
	        int year = localDate.getYear();
	        
	        if (payroll.getYear() > year) {
	            bindingResult.rejectValue("year", "Invalid.payroll.year");
	        }
	        
	        String paymentRefNo = payroll.getPaymentRefNo();
	        
	        if (payrollDao.get(paymentRefNo) != null) {
	        	bindingResult.rejectValue("paymentRefNo", "Duplicate.payroll.paymentRefNo");
	        }
	        
	        
	        if (bindingResult.hasErrors()) {
	        	
	            model.addAttribute("title", "Payroll Management");
	           
	            model.addAttribute("submiturl", "/admin/payroll/add");
	            
	            List<Employee> employees = employeeDao.getAll();
	            model.addAttribute("employees", employees);
	            
	            return "payroll/addPayroll";
	        }
	        
	        payrollDao.save(payroll);
	        return "redirect:/admin/payroll";
	    }

	    @GetMapping("/admin/payroll/{paymentRefNo}")
	    public String viewPayroll(@PathVariable("paymentRefNo") String paymentRefNo, Model model) {
	    	
	        model.addAttribute("title", "Payroll Management");
	      
	        Payroll payroll = payrollDao.get(paymentRefNo);
	        model.addAttribute("payroll", payroll);
	        return "payroll/payrollDetails";
	    }
	    
	    @GetMapping("/admin/payroll/{paymentRefNo}/edit")
	    public String editPayroll(@PathVariable("paymentRefNo") String paymentRefNo, Model model) {
	    	
	        model.addAttribute("title", "Payroll Management");
	       
	        model.addAttribute("submiturl", "/admin/payroll/" + paymentRefNo + "/edit");
	        model.addAttribute("edit", "true");
	        
	        List<Employee> employees = employeeDao.getAll();
	        model.addAttribute("employees", employees);
	        
	        Payroll payroll = payrollDao.get(paymentRefNo);
	        model.addAttribute("payroll", payroll);
	        return "payroll/addPayroll";
	    }
	    
	    @PostMapping("/admin/payroll/{paymentRefNo}/edit")
	    public String editPayroll(@PathVariable("paymentRefNo") String paymentRefNo,   @Valid @ModelAttribute("payroll") Payroll payroll, BindingResult bindingResult, Model model) {
	    	
	        LocalDate localDate = LocalDate.now();
	        
	        int year = localDate.getYear();
	        if (payroll.getYear() > year) {
	            bindingResult.rejectValue("year", "Invalid.payroll.year");
	        }
	        
	        if (bindingResult.hasErrors()) {
	            model.addAttribute("title", "Payroll Management");
	           
	            model.addAttribute("submiturl", "/admin/payroll/" + paymentRefNo + "/edit");
	            
	            List<Employee> employees = employeeDao.getAll();
	            
	            model.addAttribute("employees", employees);
	            model.addAttribute("edit", "true");
	            return "payroll/addPayroll";
	        }
	        payrollDao.update(payroll);
	        return "redirect:/admin/payroll";
	    }

	    
	    @GetMapping("/admin/payroll/{paymentRefNo}/delete")
		 public ModelAndView deleteResult(@PathVariable("paymentRefNo") String paymentRefNo) {
			 
		        ModelAndView model = new ModelAndView("redirect:/admin/payroll");
		        payrollDao.delete(paymentRefNo);
		      
		        return model;
		    }

}
